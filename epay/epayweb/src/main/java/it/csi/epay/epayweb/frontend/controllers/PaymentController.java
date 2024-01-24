/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.controllers;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.csi.epay.epayservices.interfaces.ejb.ChiamataEsternaFacade;
import it.csi.epay.epayservices.interfaces.ejb.ChiamataEsternaSincronaSplitFacade;
import it.csi.epay.epayservices.interfaces.ejb.MailFacade;
import it.csi.epay.epayservices.interfaces.ejb.PagamentoFacade;
import it.csi.epay.epayservices.interfaces.exception.MoreResultException;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.interfaces.rs.CodiciEsito;
import it.csi.epay.epayservices.interfaces.rs.exception.TassonomiaServiceException;
import it.csi.epay.epayservices.model.Anagrafica;
import it.csi.epay.epayservices.model.CodiceAvviso;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossione;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneInput;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneOutput;
import it.csi.epay.epayservices.model.EsitoChiamataEsterna;
import it.csi.epay.epayservices.model.Mail;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PagamentoComponenti;
import it.csi.epay.epayservices.model.PagamentoSecondario;
import it.csi.epay.epayservices.model.RegistroVersamenti;
import it.csi.epay.epayservices.model.StatoPagamento;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.model.TracciabilitaChiamanteEsterno;
import it.csi.epay.epayservices.model.TransazioneMdp;
import it.csi.epay.epayweb.business.messaggi.LevelMessage;
import it.csi.epay.epayweb.business.messaggi.Messaggi;
import it.csi.epay.epayweb.business.messaggi.Messaggio;
import it.csi.epay.epayweb.frontend.models.DatiPersonali;
import it.csi.epay.epayweb.frontend.models.Riferimento;
import it.csi.epay.epayweb.integration.mdp.Core;
import it.csi.epay.epayweb.integration.mdp.Iuv;
import it.csi.epay.epayweb.integration.mdp.MdpException;
import it.csi.epay.epayweb.integration.mdp.MultiIuv;
import it.csi.epay.epayweb.integration.mdp.TeaUtils;
import it.csi.epay.epayweb.integration.mdp.TeaUtils.TypeAutenticazioneSoggetto;
import it.csi.epay.epayweb.model.enumeration.EsitoSceltaPrestatore;
import it.csi.epay.epayweb.model.enumeration.Wizzard;
import it.csi.mdpcore.DatiAccertamentoRPT;
import it.csi.mdpcore.DatiSingoloVersamentoRPT;
import it.csi.mdpcore.DatiVersamentoRPT;
import it.csi.mdpcore.ElencoRPT;
import it.csi.mdpcore.InformativePSPScelto;
import it.csi.mdpcore.RPTData;
import it.csi.mdpcore.SoggettoPagatore;
import it.csi.mdpcore.Transazione;
import it.csi.mdpcore.TransazioneExtraAttribute;
import it.csi.mdpmultiiuv.IuvComplex;
import java.net.MalformedURLException;
import java.net.URL;


@Controller
@Scope ( WebApplicationContext.SCOPE_SESSION )
@RequestMapping ( "/payment" )
public class PaymentController extends _Controller {

    @Value ( "${url.myApp}" )
    private String urlMyApp;

    @Value ( "${numberTry.callMdp:1}" )
    private int numberTry;

    @Autowired
    private Core mdpCore;

    @Autowired
    private Iuv mdpIuv;

    @Autowired
    private MultiIuv mdpMultiIuv;

    @Autowired
    private PagamentoFacade pagamentoFacade;
    
    @Autowired
    private ChiamataEsternaFacade chiamataEsternaFacade;

	@Autowired
    private ChiamataEsternaSincronaSplitFacade chiamataEsternaSincronaSplitFacade;

    @Autowired
    private MessageSource messageSource;

    TransazioneMdp transazioneMdp;

    private static final Integer ID_STATO_SUCCESSO = 4;

    private static final Integer ID_STATO_FALLITO = 3;
    
    private static final int maxErrorMessageWidth = 200;
    /**
     * Imposta lo IUV Consolita i dati del pagamento Imposta il risultato del pagamento Imposto la transazione MDP Redirect verso il wizard per la scelta del
     * PSP
     *
     * @param session the session
     * @param redirectAttributes the redirectAttributes
     * @return l'url del wizard per la scelta del PSP
     */
    @RequestMapping ( value = "/start", method = RequestMethod.POST )
    public String start ( HttpSession session, RedirectAttributes redirectAttributes ) {
        final String methodName = "start";
        log.debugStart ( methodName );
        transazioneMdp = null;

        Pagamento pagamento = null;
        try {
            pagamento = preparaPagamento ( session );
			if ( pagamento.getRequiresCostUpdate () != null && pagamento.getRequiresCostUpdate () ) {
				String msg = "Non \u00e9 possibile effettuare il pagamento da questo sito. Effettuare il pagamento dal sito PagoPa o rivolgersi all'ente creditore per ulteriori indicazioni";
				log.error ( methodName, msg );
				Wizzard wizzard = getAttributeFromSession ( Wizzard.class, session );
				Messaggi messaggi = new Messaggi ();
				messaggi.addMessaggio ( new Messaggio ( LevelMessage.WARNING, msg ) );
				return redirect ( wizzard.getUrlWispBack (), redirectAttributes, messaggi );
			}
        } catch ( Exception e3 ) {
            log.error ( methodName, "Errore in fase di preparazione della transazione", e3 );
            if( e3.getCause () instanceof TassonomiaServiceException ) {
                Messaggi messaggi = new Messaggi ();
                messaggi.addMessaggio ( new Messaggio ( LevelMessage.WARNING,
                    StringUtils.isNotEmpty ( e3.getMessage () ) ? e3.getMessage () : "Errore in fase di preparazione del pagamento" ) );

                Wizzard wizzard = getAttributeFromSession ( Wizzard.class, session );
                log.debugEnd ( methodName );
                return redirect ( wizzard.getUrlWispBack (), redirectAttributes, messaggi );
            }
        }

        Transazione transazione;
        try {
			assert pagamento != null;
			transazione = preparaTransazione ( session, pagamento );
        } catch ( RuntimeException e2 ) {
            log.error ( methodName, "Errore in fase di preparazione della transazione", e2 );
            Messaggi messaggi = new Messaggi ();
            messaggi.addMessaggio ( new Messaggio ( LevelMessage.DANGER,
                StringUtils.isNotEmpty ( e2.getMessage () ) ? e2.getMessage () : "Errore in fase di preparazione della transazione" ) );

            Wizzard wizzard = getAttributeFromSession ( Wizzard.class, session );
            log.debugEnd ( methodName );
            return redirect ( wizzard.getUrlEndPaymant (), redirectAttributes, messaggi );
        }

        // LF 21/01/2019 - RDI018/019 INIZIO

        TracciabilitaChiamanteEsterno chiamataEsterna = getAttributeFromSession ( TracciabilitaChiamanteEsterno.class,
            session );

        if ( null != chiamataEsterna && transazione != null && !StringUtils.isBlank ( transazione.getTransactionId () ) ) {

            chiamataEsterna.setIdTransazione ( transazione.getTransactionId () );

            try {
                chiamataEsternaFacade.aggiorna ( chiamataEsterna );
            } catch ( IllegalAccessException | NoDataException e1 ) {
                log.error ( methodName,
                    "Errore durante l'impostazione del transaction id sulla chiamata da sistema esterno", e1 );
            }

        }

        // LF 21/01/2019 - RDI018/019 FINE

        //BLOCCO PER RDI-02 - START - MA CREDO SIA DI TROPPO
        //String applicativoChiamante = chiamataEsterna.getChiamanteEsterno ().getCodiceChiamante ();
        //Long codiceVersamento = pagamento.getTipoPagamento ().getIdTipoPagamento ();
        //
        //if(!chiamataEsternaSincronaFacade.validaAutorizzazioneChiamanteTipoPagamento ( applicativoChiamante, codiceVersamento )) {
        //    throw new RuntimeException ( "Chiamante non autorizzato al tipo pagamento indicato" );
        //}
        //BLOCCO PER RDI-02 - STOP

        switch ( (String) session.getAttribute ( "versione" ) ) {
        default :
        case "startTransazione" :
            try {
                URL urlBack = new URL ( urlMyApp + "/payment/prestatoreBack" );
                URL urlReturn = new URL ( urlMyApp + "/payment/prestatoreReturn" );
                String urlSceltaPsp = mdpCore.urlSceltaPsp ( pagamento, transazione, urlBack, urlReturn, numberTry );
                return redirect ( urlSceltaPsp );
            } catch ( MalformedURLException e ) {
				ExceptionUtils.getStackTrace ( e );
				log.error (methodName, ExceptionUtils.getStackTrace ( e ));
                throw new RuntimeException ( "Errore url di ritorno della scelta PSP", e );
            } catch ( MdpException e ) {
				log.error (methodName, ExceptionUtils.getStackTrace ( e ));
                throw e;
            }
        case "startTransazioneCarrello" :
            return pagamentoExecutionV2 ( session, pagamento, transazione, redirectAttributes );
        }
    }

    private Pagamento preparaPagamento ( HttpSession session ) {
        Pagamento pagamento = getAttributeFromSession ( Pagamento.class, session );
        if ( pagamento == null ) {
            pagamento = inserisciPagamento ( session );
            saveAttributeIntoSession ( pagamento, session );
        }

        if ( pagamento.getIuv () == null ) {
            try {
                String iuv = mdpIuv.generateNewIuv ( pagamento.getTipoPagamento (), numberTry );
                pagamento.setIuv ( iuv );
                pagamento.setIuvRegistroVersamenti ( iuv );
                pagamentoFacade.aggiornaCodiceAvviso ( pagamento.getIdPagamento (), new CodiceAvviso ( null, null, iuv, Boolean.TRUE ) );
                inserisciRegistroVersamento ( pagamento, StatoPagamento.DA_PAGARE );
            } catch ( NoDataException e ) {
                throw new RuntimeException ( "Pagamento non trovato (id=" + pagamento.getIdPagamento () + ")" );
            }
            saveAttributeIntoSession ( pagamento, session );
        }
        return pagamento;
    }

    private Transazione preparaTransazione ( HttpSession session, Pagamento pagamento ) {
        final String methodName = "preparaTransazione";

        Transazione transazione;
        try {
            Pagamento pagamentoDb = pagamentoFacade.ricercaOttimizzata ( pagamento.getIuv () );
            if ( Boolean.TRUE.equals ( pagamento.getPagamentoSpontaneo () ) && !pagamentoDb.getIdStatoCorrente ().equals ( pagamento.getIdStatoCorrente () ) &&
                StatoPagamento.NON_DEFINITO.equals ( StatoPagamento.findById ( pagamento.getIdStatoCorrente () ) ) &&
                !StatoPagamento.DA_PAGARE.equals ( StatoPagamento.findById ( pagamentoDb.getIdStatoCorrente () ) ) ) {
                throw new RuntimeException (
                    "Lo stato del pagamento in sessione e' diverso da quanto presente in base dati. Si consiglia di verificare lo IUV prima di effettuare il pagamento oppure riprovare la procedura completa in caso di pagamento spontaneo!" );
            }
            StatoPagamento stato = StatoPagamento.findById ( pagamento.getIdStatoCorrente () );
            switch ( stato ) {
            case TRANSAZIONE_INIZIALIZZATA :
            case COMPILATO :
                RegistroVersamenti ultimoVersamento = pagamentoFacade.ricercaUltimaRegistrazioneVersamento ( pagamento.getIdPagamento (), stato );
                transazione = mdpCore.recuperaTransazione ( ultimoVersamento.getIdTransazione (), numberTry );
                transazione.setAmount ( pagamento.getImporto ().doubleValue () );
                break;
            case TRANSAZIONE_AVVIATA :
            case TRANSAZIONE_ERRORE :
            case ANNULLATO :
            case FALLITO :
                //String iuv = mdpIuv.generateNewIuv ( pagamento.getTipoPagamento (), numberTry );
                pagamento.setIuvRegistroVersamenti ( pagamento.getIuv () );
                pagamento.setIdStatoCorrente ( StatoPagamento.DA_PAGARE.getId () );
                inserisciRegistroVersamento ( pagamento, StatoPagamento.DA_PAGARE );
                saveAttributeIntoSession ( pagamento, session );
            case DA_PAGARE :
            default :
                transazione = mdpCore.initializzaTransazione ( pagamento, numberTry );
                log.debug ( methodName, toXml ( transazione ) );
                transazione.setAmount ( pagamento.getImporto ().doubleValue () );
                inserisciTransazioneMdp ( pagamento, transazione );
                inserisciRegistroVersamento ( pagamento, StatoPagamento.TRANSAZIONE_INIZIALIZZATA );
            }
        } catch ( NoDataException e ) {
            throw new RuntimeException ( "Pagamento non trovato (" + pagamento.getIdPagamento () + ").", e );
        }
        return transazione;
    }

    /**
     * Ritorna indietro dalla scelta del PSP per timeout o scelta dell'utente
     *
     * @param transactionId noinfo
     * @param esito noinfo
     * @param redirectAttributes noinfo
     * @param session noinfo
     * @return pagina che ha invocato il wizard di scelta PSP
     */
    @RequestMapping ( value = "/prestatoreBack", method = RequestMethod.GET )
    public String prestatoreBack ( @RequestParam ( "transactionId" ) String transactionId, @RequestParam ( "esito" ) String esito,
        RedirectAttributes redirectAttributes, HttpSession session ) {
        final String methodName = "prestatoreBack";
        log.debugStart ( methodName );

        Messaggi messaggi = new Messaggi ();
        messaggi.addMessaggio ( new Messaggio ( LevelMessage.INFO, "Si \u00e8 interrotta la procedura di pagamento." ) );

        Wizzard wizzard = getAttributeFromSession ( Wizzard.class, session );
        log.debugEnd ( methodName );
        return redirect ( wizzard.getUrlWispBack (), redirectAttributes, messaggi );
    }

    /**
     * Scelta PSP effettuata Aggiorno la transazione MDP con l'informativa del PSP Proseguo con il pagamento
     *
     * @param transactionId noinfo
     * @param esito noinfo
     * @param redirectAttributes noinfo
     * @param session noinfo
     * @return noinfo
     */
    @RequestMapping ( value = "/prestatoreReturn", method = RequestMethod.GET )
    public String prestatoreReturn ( @RequestParam ( "transactionId" ) String transactionId, @RequestParam ( "esito" ) String esito,
        RedirectAttributes redirectAttributes, HttpSession session ) {
        final String methodName = "prestatoreReturn";
        log.debugStart ( methodName );

        Wizzard wizzard = getAttributeFromSession ( Wizzard.class, session );

        if ( !EsitoSceltaPrestatore.OK.name ().equals ( esito ) ) {
            Messaggi messaggi = new Messaggi ();
            messaggi.addMessaggio ( new Messaggio ( LevelMessage.INFO, "Si \u00e8 verificato un errore durante la procedura di pagamento." ) );
            log.debugEnd ( methodName );
            return redirect ( wizzard.getUrlWispBack (), redirectAttributes, messaggi );
        }

        Pagamento pagamento = getAttributeFromSession ( Pagamento.class, session );

        if ( pagamento.getIdPagamento () != null ) {
            try {
                it.csi.epay.epayservices.model.Pagamento pag = pagamentoFacade.ricerca ( pagamento.getIdPagamento () );
                if ( StatoPagamento.TRANSAZIONE_AVVIATA.equals ( pag.getIdStatoCorrente () ) ) {
                    Messaggi messaggi = new Messaggi ();
                    messaggi.addMessaggio ( new Messaggio ( LevelMessage.DANGER, "C'\u00e8 gi\u00e0 un pagamento in corso. Riprovare pi\u00f9 tardi." ) );
                    log.debugEnd ( methodName );
                    return redirect ( wizzard.getUrlWispBack (), redirectAttributes, messaggi );
                }
            } catch ( IllegalArgumentException | NoDataException ignored ) {
				// bello
            }
        }

        String IdApplicazione = pagamento.getTipoPagamento ().getIdApplicazione ();
        InformativePSPScelto informativaPSP = mdpCore.datiPspScelto ( IdApplicazione, transactionId, numberTry );
        try {
            aggiornaTransazioneMdp ( informativaPSP, transactionId );
        } catch ( IllegalArgumentException | NoDataException e ) {
            log.debug ( methodName, e.getMessage () );
        }
        inserisciRegistroVersamento ( pagamento, StatoPagamento.COMPILATO );

        session.setAttribute ( "transactionId", transactionId );
        log.debugEnd ( methodName );
        return "forward:" + wizzard.getUrlWispReturn ();
    }

    /**
     * Prepara tutti i dati per effettuare il pagamento Redirect verso il nodo dei pagamenti
     *
     * @param session noinfo
     * @return noinfo
     */
    @RequestMapping ( value = "/execution" )
    public String pagamentoExecution ( HttpSession session, RedirectAttributes redirectAttributes ) {
        final String methodName = "pagamentoExecution";
        log.debugStart ( methodName );

        Pagamento pagamento = getAttributeFromSession ( Pagamento.class, session );

        if ( StatoPagamento.IN_ATTESA.equals ( pagamento.getIdStatoCorrente () ) ||
                        StatoPagamento.TRANSAZIONE_INIZIALIZZATA.equals ( pagamento.getIdStatoCorrente () ) ||
                        StatoPagamento.TRANSAZIONE_AVVIATA.equals ( pagamento.getIdStatoCorrente () ) ) {
            Messaggi messaggi = new Messaggi ();
            messaggi.addMessaggio ( new Messaggio ( LevelMessage.DANGER, "Esiste gi\u00e0 un pagamento in corso. Riprovare piu' tardi." ) );

            Wizzard wizzard = getAttributeFromSession ( Wizzard.class, session );
            log.debugEnd ( methodName );
            return redirect ( wizzard.getUrlWispBack (), redirectAttributes, messaggi );
        }

        if ( StatoPagamento.TRANSAZIONE_ERRORE.equals ( pagamento.getIdStatoCorrente () ) ) {
            Messaggi messaggi = new Messaggi ();
            messaggi.addMessaggio ( new Messaggio ( LevelMessage.DANGER,
                            "il pagamento corrente \u00e8 in errore. Per rieffettuare il pagamento \u00e8 necessario ripartire dallo step 1." ) );

            Wizzard wizzard = getAttributeFromSession ( Wizzard.class, session );
            log.debugEnd ( methodName );
            return redirect ( wizzard.getUrlWispBack (), redirectAttributes, messaggi );
        }

        Transazione transazione = mdpCore.recuperaTransazione ( transazioneMdp.getIdTransazione () );
        transazione.setAmount ( pagamento.getImporto ().doubleValue () );
        transazione.setGatewayId ( transazioneMdp.getIdGateway () );
        transazione.setGatewaypaymodeid ( transazioneMdp.getIdPaymentMode () );

		List<TransazioneExtraAttribute> teaList = new ArrayList<> ( TeaUtils.pagatore ( pagamento.getPagatore () ) );
        if ( pagamento.getVersante () != null ) {
            teaList.addAll ( TeaUtils.versante ( pagamento.getVersante () ) );
        }
        teaList.add ( TeaUtils.causale ( pagamento.getTipoPagamento ().getDescrizionePortale () ) );
        teaList.add ( TeaUtils.informativaPSP ( transazioneMdp.getIdInformativaPsp ().toString () ) );
        teaList.add ( TeaUtils.iuv ( transazioneMdp.getIuv () ) );
        teaList.add ( TeaUtils.autenticazioneSoggetto ( TypeAutenticazioneSoggetto.NON_APPLICABILE ) );
        if ( !CollectionUtils.isEmpty ( pagamento.getComponenti () ) ) {
            // :TODO capire come gestilo meglio in caso di piu' componenti.
            teaList.add ( TeaUtils.datiSpecificiRiscossione ( pagamento.getComponenti ().get ( 0 ).getDatiSpecificiRiscossione () ) );
        } else {
            if ( pagamento.getTipoPagamento () != null && StringUtils.isNotBlank ( pagamento.getTipoPagamento ().getDatiSpecificiRiscossione () ) ) {
                teaList.add ( TeaUtils.datiSpecificiRiscossione ( pagamento.getTipoPagamento ().getDatiSpecificiRiscossione () ) );
            }
        }
        TeaUtils.setTransactionId ( teaList, transazioneMdp.getIdTransazione () );
        String paymantURL;
        try {
            paymantURL = mdpCore.paymentURL ( transazione, teaList );
            inserisciRegistroVersamento ( pagamento, StatoPagamento.TRANSAZIONE_AVVIATA );
        } catch ( MdpException e ) {

            log.error ( methodName, "Errore transazione/n" + e.getCause ().getMessage () );
            log.error ( methodName, ExceptionUtils.getStackTrace ( e ) );

            inserisciRegistroVersamento ( pagamento, StatoPagamento.TRANSAZIONE_ERRORE );
            //inserisciRegistroVersamento(pagamento, StatoPagamento.DA_PAGARE);

            Throwable cause = e.getCause ();
            if ( //JIRA EPAY-17 : cause instanceof DaoException_Exception &&
                            cause.getMessage ().contains ( "RPT duplicata" ) ) {
                Messaggi messaggi = new Messaggi ();
                messaggi.addMessaggio ( new Messaggio ( LevelMessage.DANGER,
                                "Il pagamento non pu\u00f2 essere effettuato perch\u00e9 risulta gi\u00e0 una transazione di pagamento in corso." ) );

                Wizzard wizzard = getAttributeFromSession ( Wizzard.class, session );
                removeAttribute ( session );
                log.debugEnd ( methodName );
                return redirect ( wizzard.getUrlWispBack (), redirectAttributes, messaggi );
            } else {
                //throw e;
                Messaggi messaggi = new Messaggi ();
                messaggi.addMessaggio ( new Messaggio ( LevelMessage.WARNING,
                                "Errore temporaneo di comunicazione. Si prega di riprovare pi\u00f9 tardi. Se l'errore dovesse persiste contattare l'assistenza." ) );

                Wizzard wizzard = getAttributeFromSession ( Wizzard.class, session );
                log.debugEnd ( methodName );
                removeAttribute ( session );
                return redirect ( wizzard.getUrlWispBack (), redirectAttributes, messaggi );
            }
        }
        log.debug ( methodName, "url mdp pagamento : " + paymantURL );
        log.debugEnd ( methodName );
        return redirect ( paymantURL );
    }

    private String pagamentoExecutionV2 ( HttpSession session, Pagamento pagamento, Transazione transazione, RedirectAttributes redirectAttributes ) {
        final String methodName = "pagamentoExecutionV2";
        log.debugStart ( methodName );

        if ( StatoPagamento.IN_ATTESA.equals ( pagamento.getIdStatoCorrente () ) ||
                        StatoPagamento.TRANSAZIONE_INIZIALIZZATA.equals ( pagamento.getIdStatoCorrente () ) ||
                        StatoPagamento.TRANSAZIONE_AVVIATA.equals ( pagamento.getIdStatoCorrente () ) ) {
            Messaggi messaggi = new Messaggi ();
            messaggi.addMessaggio ( new Messaggio ( LevelMessage.DANGER, "Esiste gi\u00e0 un pagamento in corso. Riprovare piu' tardi." ) );

            Wizzard wizzard = getAttributeFromSession ( Wizzard.class, session );
            log.debugEnd ( methodName );
            return redirect ( wizzard.getUrlWispBack (), redirectAttributes, messaggi );
        }

        if ( StatoPagamento.TRANSAZIONE_ERRORE.equals ( pagamento.getIdStatoCorrente () ) ) {
            Messaggi messaggi = new Messaggi ();
            messaggi.addMessaggio ( new Messaggio ( LevelMessage.DANGER,
                            "il pagamento corrente \u00e8 in errore. Per rieffettuare il pagamento \u00e8 necessario ripartire dallo step 1." ) );

            Wizzard wizzard = getAttributeFromSession ( Wizzard.class, session );
            log.debugEnd ( methodName );
            return redirect ( wizzard.getUrlWispBack (), redirectAttributes, messaggi );
        }

        transazione.setAmount ( pagamento.getImporto ().doubleValue () );

        /*
         * List<TransazioneExtraAttribute> teaList = new ArrayList<TransazioneExtraAttribute>(); teaList.addAll(TeaUtils.pagatore(pagamento.getPagatore())); if
         * (pagamento.getVersante() != null) { teaList.addAll(TeaUtils.versante(pagamento.getVersante())); }
         * teaList.add(TeaUtils.causale(pagamento.getTipoPagamento().getDescrizionePortale()));
         * teaList.add(TeaUtils.informativaPSP(transazioneMdp.getIdInformativaPsp().toString())); teaList.add(TeaUtils.iuv(transazioneMdp.getIuv()));
         * teaList.add(TeaUtils.autenticazioneSoggetto(TypeAutenticazioneSoggetto.NON_APPLICABILE)); if (pagamento.getTipoPagamento() != null &&
         * StringUtils.isNotBlank(pagamento.getTipoPagamento().getDatiSpecificiRiscossione())) {
         * teaList.add(TeaUtils.datiSpecificiRiscossione(pagamento.getTipoPagamento().getDatiSpecificiRiscossione())); } TeaUtils.setTransactionId(teaList,
         * transazioneMdp.getIdTransazione());
         */

        /* DATI RPT */
        SoggettoPagatore soggettoPagatore = new SoggettoPagatore ();
        soggettoPagatore.setIdentificativoUnivocoPagatore ( pagamento.getPagatore ().getCodiceFiscale () );
        if ( pagamento.getPagatore ().getFlagPersonaFisica () ) {
            soggettoPagatore.setTipoIdentificativoUnivocoPagatore ( "F" );
            soggettoPagatore.setAnagraficaPagatore ( StringUtils.substring (
                StringUtils.join ( new String [] { pagamento.getPagatore ().getNome (), pagamento.getPagatore ().getCognome () }, " " ), 0, 70) );
        } else {
            soggettoPagatore.setTipoIdentificativoUnivocoPagatore ( "G" );
            soggettoPagatore.setAnagraficaPagatore ( StringUtils.substring ( pagamento.getPagatore ().getRagioneSociale (), 0, 70) );
        }
        soggettoPagatore.setEMailPagatore ( pagamento.getPagatore ().getEmail () );
        if ( StringUtils.isNotBlank ( pagamento.getPagatore ().getIndirizzo () ) ) {
            soggettoPagatore.setIndirizzoPagatore ( pagamento.getPagatore ().getIndirizzo () );
        }
        if ( StringUtils.isNotBlank ( pagamento.getPagatore ().getCivico () ) ) {
            soggettoPagatore.setCivicoPagatore ( pagamento.getPagatore ().getCivico () );
        }
        if ( StringUtils.isNotBlank ( pagamento.getPagatore ().getCap () ) ) {
            soggettoPagatore.setCapPagatore ( pagamento.getPagatore ().getCap () );
        }
        if ( StringUtils.isNotBlank ( pagamento.getPagatore ().getLocalita () ) ) {
            soggettoPagatore.setLocalitaPagatore ( pagamento.getPagatore ().getLocalita () );
        }
        if ( StringUtils.isNotBlank ( pagamento.getPagatore ().getProvincia () ) ) {
            soggettoPagatore.setProvinciaPagatore ( pagamento.getPagatore ().getProvincia () );
        }
        if ( StringUtils.isNotBlank ( pagamento.getPagatore ().getNazione () ) ) {
            soggettoPagatore.setNazionePagatore ( pagamento.getPagatore ().getNazione () );
        }

        DatiVersamentoRPT datiVersamentoRpt = new DatiVersamentoRPT ();
        datiVersamentoRpt.setImportoTotaleDaVersare (
            Boolean.TRUE.equals ( pagamento.getTipoPagamento ().getFlagMultibeneficiario () ) ? pagamento.getImportoPrincipale () : pagamento.getImporto () );
        datiVersamentoRpt.setIdentificativoUnivocoVersamento ( transazioneMdp.getIuv () );

        // a tendere con la nuova evolutiva questo if non sara' utilizzato. Le componenti saranno sempre specificati.
        if ( pagamento.getComponenti () == null || pagamento.getComponenti ().isEmpty () ) {
            String causaleVersamento = componiCausaleVersamento ( pagamento.getIuvRegistroVersamenti (),
                Boolean.TRUE.equals ( pagamento.getTipoPagamento ().getFlagMultibeneficiario () ) ? pagamento.getImportoPrincipale () : pagamento.getImporto (),
                pagamento.getTipoPagamento ().getDescrizionePortale () );
            DatiSingoloVersamentoRPT datiSingoloVersamentoRPT = new DatiSingoloVersamentoRPT ();
            datiSingoloVersamentoRPT.setCausaleVersamento ( causaleVersamento );
            datiSingoloVersamentoRPT.setImportoSingoloVersamento ( Boolean.TRUE.equals ( pagamento.getTipoPagamento ().getFlagMultibeneficiario () ) ? pagamento.getImportoPrincipale () : pagamento.getImporto () );
            datiSingoloVersamentoRPT.setDatiSpecificiRiscossione ( pagamento.getTipoPagamento ().getDatiSpecificiRiscossione () );

            DatiAccertamentoRPT datiAccertamentoRPT = new DatiAccertamentoRPT ();
            datiAccertamentoRPT.setAnnoAccertamento ( pagamento.getAnnoAccertamento () );
            try {
                datiAccertamentoRPT
                .setNumeroAccertamento ( pagamento.getNumeroAccertamento () != null ? Integer.valueOf ( pagamento.getNumeroAccertamento () ) : null );
            } catch ( NumberFormatException nfe ) {
                throw new RuntimeException ( "Numero pagamento " + pagamento.getIdPagamento () + " ha il formato del numero accertamento errato" );
            }
            datiSingoloVersamentoRPT.setDatiAccertamento ( datiAccertamentoRPT );

            datiVersamentoRpt.getDatiSingoloVersamento ().add ( datiSingoloVersamentoRPT );
        } else {
            for ( PagamentoComponenti componente: pagamento.getComponenti () ) {
                String causaleVersamento
                = componiCausaleVersamento ( pagamento.getIuvRegistroVersamenti (), componente.getImporto (), componente.getCausale () );
                DatiSingoloVersamentoRPT datiSingoloVersamentoRPT = new DatiSingoloVersamentoRPT ();
                datiSingoloVersamentoRPT.setCausaleVersamento ( causaleVersamento );
                datiSingoloVersamentoRPT.setImportoSingoloVersamento ( componente.getImporto () );
                datiSingoloVersamentoRPT.setDatiSpecificiRiscossione ( componente.getDatiSpecificiRiscossione () );
                DatiAccertamentoRPT datiAccertamentoRPT = new DatiAccertamentoRPT ();
                datiAccertamentoRPT.setAnnoAccertamento ( componente.getAnnoAccertamento () );
                try {
                    datiAccertamentoRPT
                    .setNumeroAccertamento ( componente.getNumeroAccertamento () != null ? Integer.valueOf ( componente.getNumeroAccertamento () ) : null );
                } catch ( NumberFormatException nfe ) {
                    throw new RuntimeException ( "Numero pagamento " + pagamento.getIdPagamento () + " ha il formato del numero accertamento errato" );
                }
                datiSingoloVersamentoRPT.setDatiAccertamento ( datiAccertamentoRPT );

                datiVersamentoRpt.getDatiSingoloVersamento ().add ( datiSingoloVersamentoRPT );
            }
        }

        RPTData rptData = new RPTData ();
        rptData.setAutenticazioneSoggetto ( "N/A" );
        rptData.setSoggettoPagatore ( soggettoPagatore );
        rptData.setSoggettoVersante ( null );
        rptData.setDatiVersamento ( datiVersamentoRpt );
        rptData.setApplicationId ( transazione.getApplicationId () );

        ElencoRPT elencoRPT = new ElencoRPT ();
        elencoRPT.getRptdata ().add ( rptData );
        if ( Boolean.TRUE.equals ( pagamento.getTipoPagamento ().getFlagMultibeneficiario () ) ) {
            PagamentoSecondario pagamentoSecondario = null;
            try {
                pagamentoSecondario = pagamentoFacade.ricercaPagamentoSecondarioByIdPagamentoPrincipale ( pagamento.getIdPagamento () );
            } catch ( MoreResultException e ) {
                log.error ( "costruisciRPTMultibeneficiario", "Errore in fase di composizione del pagamento secondario con iuv : " + pagamento.getIuv (), e );
                throw new RuntimeException ( "Errore in fase di composizione del pagamento secondario con iuv : " + pagamento.getIuv () );
            }
            // gestione multibeneficiario
            if ( null != pagamentoSecondario ) {
                DatiVersamentoRPT datiVersamentoRpt2 = new DatiVersamentoRPT ();
                datiVersamentoRpt2.setImportoTotaleDaVersare ( pagamentoSecondario.getImportoComplessivo () );
                datiVersamentoRpt2.setIdentificativoUnivocoVersamento ( pagamento.getIuvRegistroVersamenti () );

                for ( PagamentoComponenti componenteSecondaria: pagamentoSecondario.getComponenti () ) {
                    String causaleVersamento
                        = componiCausaleVersamento ( pagamento.getIuvRegistroVersamenti (), componenteSecondaria.getImporto (),
                            componenteSecondaria.getCausale () );
                    DatiSingoloVersamentoRPT datiSingoloVersamentoRPT = new DatiSingoloVersamentoRPT ();
                    datiSingoloVersamentoRPT.setCausaleVersamento ( causaleVersamento );
                    datiSingoloVersamentoRPT.setImportoSingoloVersamento ( componenteSecondaria.getImporto () );
                    datiSingoloVersamentoRPT.setDatiSpecificiRiscossione ( componenteSecondaria.getDatiSpecificiRiscossione () );
                    DatiAccertamentoRPT datiAccertamentoRPT = new DatiAccertamentoRPT ();
                    datiAccertamentoRPT.setAnnoAccertamento ( componenteSecondaria.getAnnoAccertamento () );
                    try {
                        datiAccertamentoRPT
                            .setNumeroAccertamento (
                                componenteSecondaria.getNumeroAccertamento () != null ? Integer.valueOf ( componenteSecondaria.getNumeroAccertamento () )
                                                : null );
                    } catch ( NumberFormatException nfe ) {
                        throw new RuntimeException ( "Numero pagamento " + pagamento.getIdPagamento () + " ha il formato del numero accertamento errato" );
                    }
                    datiSingoloVersamentoRPT.setDatiAccertamento ( datiAccertamentoRPT );

                    datiVersamentoRpt2.getDatiSingoloVersamento ().add ( datiSingoloVersamentoRPT );
                }

                RPTData rptData2 = new RPTData ();
                rptData2.setAutenticazioneSoggetto ( "N/A" );
                rptData2.setSoggettoPagatore ( soggettoPagatore );
                rptData2.setSoggettoVersante ( null );
                rptData2.setDatiVersamento ( datiVersamentoRpt2 );

                rptData2.setApplicationId ( pagamentoSecondario.getTipoPagamento ().getIdApplicazione () );

                elencoRPT.getRptdata ().add ( rptData2 );
            }
        }
        String paymantURL;
        try {
            paymantURL = mdpCore.paymentURL ( transazione, elencoRPT,  Boolean.TRUE.equals (pagamento.getTipoPagamento ().getFlagMultibeneficiario () ) );
            inserisciRegistroVersamento ( pagamento, StatoPagamento.TRANSAZIONE_AVVIATA );
        } catch ( MdpException e ) {

            log.error ( methodName, "Errore transazione/n" + e.getCause ().getMessage () );
            log.error ( methodName, ExceptionUtils.getStackTrace ( e ) );

            inserisciRegistroVersamento ( pagamento, StatoPagamento.TRANSAZIONE_ERRORE );
            //inserisciRegistroVersamento(pagamento, StatoPagamento.DA_PAGARE);

            Throwable cause = e.getCause ();
            if ( //cause instanceof DaoException_Exception &&
                            cause.getMessage ().contains ( "RPT duplicata" ) ) {
                Messaggi messaggi = new Messaggi ();
                messaggi.addMessaggio ( new Messaggio ( LevelMessage.DANGER,
                                "Il pagamento non pu\u00f2 essere effettuato perch\u00e9 risulta gi\u00e0 una transazione di pagamento in corso." ) );

                Wizzard wizzard = getAttributeFromSession ( Wizzard.class, session );
                removeAttribute ( session );
                log.debugEnd ( methodName );
                return redirect ( wizzard.getUrlWispBack (), redirectAttributes, messaggi );
            } else {
                Messaggi messaggi = new Messaggi ();
                messaggi.addMessaggio ( new Messaggio ( LevelMessage.WARNING,
                                "Errore temporaneo di comunicazione. Si prega di riprovare pi\u00f9 tardi. Se l'errore dovesse persiste contattare l'assistenza." ) );

                Wizzard wizzard = getAttributeFromSession ( Wizzard.class, session );
                removeAttribute ( session );
                log.debugEnd ( methodName );
                return redirect ( wizzard.getUrlWispBack (), redirectAttributes, messaggi );
            }
        }
        log.debug ( methodName, "url mdp pagamento : " + paymantURL );
        log.debugEnd ( methodName );
        return redirect ( paymantURL );
    }

    private String componiCausaleVersamento ( String iuv, BigDecimal importo, String descrizione ) {
        StringBuilder composizioneCausale = new StringBuilder ( iuv.length () == 25 ? "/RFS/" : "/RFB/" );
        composizioneCausale.append ( iuv );
        composizioneCausale.append ( "/" );
        composizioneCausale.append ( importo.toString () );
        if ( StringUtils.isNotBlank ( descrizione ) ) {
            composizioneCausale.append ( "/TXT/" ).append ( descrizione );
        }
        return composizioneCausale.substring ( 0, Math.min ( composizioneCausale.length (), 140 ) );
    }

    /**
     * Ritorno dal pagamento effettuato (ok) sia con esito positivo che negativo
     *
     * @param transactionId noinfo
     * @param session noinfo
     * @return noinfo
     */
    @RequestMapping ( value = "/conclusione_ok", method = RequestMethod.GET )
    public String conclusioneOk ( @RequestParam ( "tranId" ) String transactionId,
        HttpSession session, RedirectAttributes redirectAttributes ) {
        final String methodName = "conclusioneOk";
        log.debugStart ( methodName );

        Pagamento pagamentoFromRedirect = getPagamentoFromRedirectSincrona ( transactionId );
        log.info ( methodName, "pagamentoFromRedirect is " + ( pagamentoFromRedirect != null ? "NOT null" : "NULL" ) );
        if ( pagamentoFromRedirect != null ) {
            return redirect ( completaRedirectSincrona ( transactionId, pagamentoFromRedirect, StatoPagamento.SUCCESSO ) );
        }

        Messaggi messaggi = conclusione ( session, transactionId, StatoPagamento.IN_ATTESA );
        Wizzard wizzard = getAttributeFromSession ( Wizzard.class, session );

        log.debugEnd ( methodName );
        return redirect ( wizzard.getUrlEndPaymant (), redirectAttributes, messaggi );
    }

    /**
     * Ritorno dal pagamento effettuato (ko) sia con esito positivo che negativo
     *
     * @param transactionId noinfo
     * @param session noinfo
     * @return noinfo
     */
    @RequestMapping ( value = "/conclusione_ko", method = RequestMethod.GET )
    public String conclusioneKo ( @RequestParam ( "tranId" ) String transactionId,
        HttpSession session, RedirectAttributes redirectAttributes ) {
        final String methodName = "conclusioneKo";
        log.debugStart ( methodName );

        Pagamento pagamentoFromRedirect = getPagamentoFromRedirectSincrona ( transactionId );
        log.info ( methodName, "pagamentoFromRedirect is " + ( pagamentoFromRedirect != null ? "NOT null" : "NULL" ) );
        if ( pagamentoFromRedirect != null ) {
            return redirect ( completaRedirectSincrona ( transactionId, pagamentoFromRedirect, StatoPagamento.FALLITO ) );
        }

        Messaggi messaggi = conclusione ( session, transactionId, StatoPagamento.ANNULLATO );
        Wizzard wizzard = getAttributeFromSession ( Wizzard.class, session );

        log.debugEnd ( methodName );
        return redirect ( wizzard.getUrlEndPaymant (), redirectAttributes, messaggi );
    }

    /**
     * Ritorno dal pagamento effettuato (back) sia con esito positivo che negativo
     *
     * @param transactionId noinfo
     * @param session noinfo
     * @return noinfo
     */
    @RequestMapping ( value = "/conclusione_Back", method = RequestMethod.GET )
    public String conclusioneBack ( @RequestParam ( "tranId" ) String transactionId,
        HttpSession session, RedirectAttributes redirectAttributes ) {
        final String methodName = "conclusioneBack";
        log.debugStart ( methodName );

        Pagamento pagamentoFromRedirect = getPagamentoFromRedirectSincrona ( transactionId );
        log.info ( methodName, "pagamentoFromRedirect is " + ( pagamentoFromRedirect != null ? "NOT null" : "NULL" ) );
        if ( pagamentoFromRedirect != null ) {
            return redirect ( completaRedirectSincrona ( transactionId, pagamentoFromRedirect, StatoPagamento.ANNULLATO ) );
        }

        Messaggi messaggi = conclusione ( session, transactionId, StatoPagamento.ANNULLATO );
        Wizzard wizzard = getAttributeFromSession ( Wizzard.class, session );

        log.debugEnd ( methodName );
        return redirect ( wizzard.getUrlEndPaymant (), redirectAttributes, messaggi );
    }

    private Messaggi conclusione ( HttpSession session, String transactionId, StatoPagamento stato ) {
        final String methodName = "conclusione";
        log.debugStart ( methodName );

        Pagamento pagamento = getAttributeFromSession ( Pagamento.class, session );
        if ( pagamento == null ) {
            TransazioneMdp remoteTransazioneMdp;
            try {
                remoteTransazioneMdp = pagamentoFacade.ricercaTransazioneMdp ( transactionId );
            } catch ( IllegalArgumentException | NoDataException e ) {
                log.warn ( methodName, "Transazione MDP non trovata: " + transactionId, e );
                return new Messaggi ( new Messaggio ( LevelMessage.WARNING, "Transazione MDP non trovata: " + transactionId ) );
            }

            String iuv = remoteTransazioneMdp.getIuv ();
            if ( StringUtils.isEmpty ( iuv ) ) {
                log.warn ( methodName, "IUV della transazione MDP assente: " + transactionId );
                return new Messaggi ( new Messaggio ( LevelMessage.WARNING, "IUV della transazione MDP assente: " + transactionId ) );
            }

            try {
                pagamento = pagamentoFacade.ricerca ( iuv );

            } catch ( IllegalArgumentException | NoDataException e ) {
                log.warn ( methodName, "Pagamento da IUV non trovato: " + iuv, e );
                return new Messaggi ( new Messaggio ( LevelMessage.WARNING, "Pagamento da IUV non trovato: " + iuv ) );
            }
        }
        
        log.info ( methodName, "TEST ATTRIBUTO: pagamento.getIuv (): " + pagamento.getIuv () + " pagamento.getIdPagamento (): " + pagamento.getIdPagamento ()
        + " transactionId: " + transactionId );

        Pagamento pagamentoDB = null;
        try {
            pagamentoDB = pagamentoFacade.ricerca ( pagamento.getIuv () );
        } catch ( IllegalArgumentException | NoDataException e ) {
            log.warn ( methodName,
                "Errore in fase di recupero del pagamento con IUV: " + pagamento.getIuv () + " e idPagamento: " + pagamento.getIdPagamentoCifrato () );
        }

        if ( null != pagamentoDB
                        && ( !ID_STATO_SUCCESSO.equals ( pagamentoDB.getIdStatoCorrente () ) && !ID_STATO_FALLITO.equals ( pagamentoDB.getIdStatoCorrente () ) ) ) {
            inserisciRegistroVersamento ( pagamento, StatoPagamento.IN_ATTESA );
        } else {
            log.warn ( methodName, "Attenzione si sta tentando di modificare lo stato del pagamento con IUV: " + pagamento.getIuv () + " e idPagamento: "
                            + pagamento.getIdPagamentoCifrato () + " concluso!" );
        }

        switch ( stato ) {
        case IN_ATTESA :
            Transazione transazione = mdpCore.recuperaTransazione ( transactionId, numberTry );

            inviaMailInAttesa ( pagamento, transazione );
            log.debugEnd ( methodName );
            return new Messaggi ( new Messaggio ( LevelMessage.SUCCESS, "Operazione terminata." ) );
        case ANNULLATO :
            log.debugEnd ( methodName );
            return new Messaggi ( new Messaggio ( LevelMessage.WARNING, "ATTENZIONE! Operazione annullata!" ) );
        case FALLITO :
        default :
            log.debugEnd ( methodName );
            return new Messaggi ( new Messaggio ( LevelMessage.DANGER, "ATTENZIONE! Operazione in errore!" ) );
        }
    }

    private String completaRedirectSincrona ( String idTransazione, Pagamento pagamento, StatoPagamento esito ) {
        String methodName = "completaRedirectSincrona";
        // esito puo' essere SUCCESSO, FALLITO o ANNULLATO

        TracciabilitaChiamanteEsterno tracciabilitaChiamanteEsterno;
        try {
            tracciabilitaChiamanteEsterno = chiamataEsternaFacade.ricerca ( idTransazione );
        } catch ( IllegalAccessException | NoDataException e ) {
            log.warn ( methodName, "Tracciabilita' chiamante esterno non trovata per id transazione " + idTransazione, e );
            throw new RuntimeException ( "Tracciabilita' chiamante esterno non trovata per id transazione " + idTransazione, e );
        }

        String codiceEsito = esito == StatoPagamento.SUCCESSO ? EsitoChiamataEsterna.OPERAZIONE_COMPLETATA_CON_SUCCESSO : EsitoChiamataEsterna.ERRORE_GENERICO;

        String source = "pagamentoIUV";
        if ( pagamento.getTipoPagamento ().getTipologiaPagamento () != null
                        && (pagamento.getTipoPagamento ().getTipologiaPagamento ().getCodice ().equals ( "MABL" ) ||
                                        pagamento.getTipoPagamento ().getTipologiaPagamento ().getCodice ().equals ( "PABL" ))) {
            source = "pagamentoMarcaBollo";
        }

        String url = null;
        if ( pagamento.getTipoPagamento ().getTipologiaPagamento () != null
                        && pagamento.getTipoPagamento ().getTipologiaPagamento ().getCodice ().equals ( "REDS" ) ) {
            url = tracciabilitaChiamanteEsterno.getChiamanteEsterno ().getUrl () + "?"
                            + "idPagamento=" + tracciabilitaChiamanteEsterno.getIdentificativoPagamento () + "&"
                            + "iuv=" + tracciabilitaChiamanteEsterno.getIuv() + "&"
                            + "descEsito=" + esito.name () + "&"
                            + "codEsito=" + codiceEsito + "&"
                            + "source=epayapi-sync";
        } else {
            url = tracciabilitaChiamanteEsterno.getChiamanteEsterno ().getUrl () + "?"
                            + "idPagamento=" + tracciabilitaChiamanteEsterno.getIdentificativoPagamento () + "&"
                            + "iuv=" + tracciabilitaChiamanteEsterno.getIuv() + "&"
                            + "descEsito=" + esito.name () + "&"
                            + "codEsito=" + codiceEsito + "&"
                            + "source="+source;
        }

        return url;
    }

    private Pagamento getPagamentoFromRedirectSincrona ( String idTransazione ) {
        String methodName = "getPagamentoFromRedirectSincrona";
		log.info ( methodName, "BEGIN" );
        
        TransazioneMdp remoteTransazioneMdp;
        try {
            remoteTransazioneMdp = pagamentoFacade.ricercaTransazioneMdp ( idTransazione );
        } catch ( IllegalArgumentException | NoDataException e ) {
            log.warn ( methodName, "Transazione MDP:" + idTransazione + " non trovata", e );
            return null;
        }
		log.info ( methodName, "Trasazione MDP:" + ( remoteTransazioneMdp != null ? "NOT null" : "NULL" ) + " trovata per idTransazione:" + idTransazione );

        String iuv = remoteTransazioneMdp.getIuv ();
        if ( StringUtils.isEmpty ( iuv ) ) {
            log.warn ( methodName, "IUV della transazione MDP:" + idTransazione + " assente" );
            return null;
        }
		log.info ( methodName, "IUV:" + iuv );

        Pagamento pagamento;
        try {
            pagamento = pagamentoFacade.ricerca ( iuv );

        } catch ( IllegalArgumentException | NoDataException e ) {
            log.warn ( methodName, "Pagamento da IUV:" + iuv + " non trovato", e );
            return null;
        }
        log.info ( methodName, "Pagamento:" + ( pagamento != null ? "NOT null" : "NULL" ) + " trovato per IUV:" + iuv);

        // check tipologia pagamento
        if ( pagamento == null || pagamento.getTipoPagamento () == null || pagamento.getTipoPagamento ().getTipologiaPagamento () == null ) {
            log.warn ( methodName, "pagamamento null || pagamento.idPagamento null || pagamento.tipoPagamento.tipologiaPagamento null");
            return null;
        }

		// check origine chiamata
        log.info ( methodName, "Check origine chiamata");
        final int ID_ORIGINE_CHIAMATA_CITTA_FACILE = 1;
		if ( pagamentoFacade.verificaOrigineChiamata ( pagamento.getIdPagamento (), idTransazione, ID_ORIGINE_CHIAMATA_CITTA_FACILE ) ) {
	        log.info ( methodName, "Origine chiamata " + ID_ORIGINE_CHIAMATA_CITTA_FACILE + " presente" );
			return pagamento;
		}
        log.info ( methodName, "Origine chiamata " + ID_ORIGINE_CHIAMATA_CITTA_FACILE + " assente" );
        
        if ( "REDS".equals ( pagamento.getTipoPagamento ().getTipologiaPagamento ().getCodice () ) ||
                        "PABL".equals ( pagamento.getTipoPagamento ().getTipologiaPagamento ().getCodice () ) ||
                        "MABL".equals ( pagamento.getTipoPagamento ().getTipologiaPagamento ().getCodice () ) ) {
            return pagamento;
        } else if ( pagamento.getTipoPagamento ().getTipologiaPagamento () != null
                        && "REDI".equals ( pagamento.getTipoPagamento ().getTipologiaPagamento ().getCodice () ) ) {
            // Se tipo pagamento non e' REDS, verifico se il tipoPagamento sia associato ad un chiamante esterno.
            // Se e' associato ad un chiamante esterno, significa che il pagamento e' stato fatto
            // tramite la chiamata sincrona da chiamante esterno (RDI-27)
            Boolean verificaChiamanteEsternoTipoPagamento
            = chiamataEsternaSincronaSplitFacade.verificaAutorizzazione ( idTransazione, pagamento.getTipoPagamento ().getIdTipoPagamento () );
            if ( verificaChiamanteEsternoTipoPagamento ) {
                return pagamento;
            }
        }

        return null;
    }

	@RequestMapping ( value = "/salvaPagamento", method = RequestMethod.GET )
    public String salvaPagamento ( HttpSession session, @RequestParam ( required = false, defaultValue = "false" ) Boolean stampa, RedirectAttributes redirectAttributes ) {
        final String methodName = "salvaPagamento";
        log.debugStart ( methodName );
        transazioneMdp = null;

        if ( getAttributeFromSession ( Pagamento.class, session ) == null ) {
            try {
                Pagamento pagamento = inserisciPagamento ( session );
//              TODO : inserire qui la data fine??? solo per la stampa?
                Riferimento riferimento = getAttributeFromSession ( Riferimento.class, session );
                TipoPagamento tipoPagamento = pagamentoFacade.ricercaTipoPagamento ( riferimento.getPagamentoId () );
                String passphrase = (String) session.getAttribute ( "passphrase" );
                IuvComplex iuv = mdpMultiIuv.generateNewIuv ( tipoPagamento, passphrase, numberTry );
                pagamento.setAuxDigit ( iuv.getAuxDigit () );
                pagamento.setApplicationCode ( iuv.getApplicationCode () );
                pagamento.setIuv ( iuv.getIuvOttico () );
                pagamento.setIuvRegistroVersamenti ( iuv.getIuvOttico () );
                pagamentoFacade.aggiornaCodiceAvviso ( pagamento.getIdPagamento (),
                    new CodiceAvviso ( pagamento.getAuxDigit (), pagamento.getApplicationCode (), pagamento.getIuv () ) );
                inserisciRegistroVersamento ( pagamento, StatoPagamento.DA_PAGARE );
                saveAttributeIntoSession ( pagamento, session );
            } catch ( NoDataException e ) {
                throw new RuntimeException ( "Errore salvataggio pagamento" );
            } catch ( Exception e3 ) {
                log.error ( methodName, "Errore in fase di preparazione della transazione", e3 );
                if( e3.getCause () instanceof TassonomiaServiceException ) {
                    Messaggi messaggi = new Messaggi ();
                    messaggi.addMessaggio ( new Messaggio ( LevelMessage.WARNING,
                        StringUtils.isNotEmpty ( e3.getMessage () ) ? e3.getMessage () : "Errore in fase di preparazione del pagamento" ) );

                    log.debugEnd ( methodName );
                    return redirect ( "/accessoLibero/stampaAvviso/riepilogo", redirectAttributes, messaggi );
                }
            }
        }

        log.debugEnd ( methodName );
        return stampa ? "forward:/stampe/avvisoPagamento" : null;
    }

    private Pagamento inserisciPagamento ( HttpSession session ) {
        final String methodName = "inserisciPagamento";
        log.debugStart ( methodName );

        Pagamento pagamento;
        Riferimento riferimento = getAttributeFromSession ( Riferimento.class, session );
        DatiPersonali datiPersonali = getAttributeFromSession ( DatiPersonali.class, session );

        Anagrafica pagatore = map ( datiPersonali, Anagrafica.class );
        pagatore.setFlagPersonaFisica ( "personaFisica".equals ( datiPersonali.getSoggettoGiuridico () ) );

        TipoPagamento tipoPagamento = new TipoPagamento ();
        tipoPagamento.setIdTipoPagamento ( riferimento.getPagamentoId () );

        pagamento = new Pagamento ();
        pagamento.setCausale ( riferimento.getPagamentoDesc () );
        pagamento.setImporto ( datiPersonali.getImporto () );
        pagamento.setNote ( datiPersonali.getNote () );
        pagamento.setPagatore ( pagatore );
        pagamento.setTipoPagamento ( tipoPagamento );
        pagamento.setConsensoPrivacy ( Boolean.TRUE );
        pagamento.setIdStatoCorrente ( StatoPagamento.NON_DEFINITO.getId () );
        pagamento.setPagamentoSpontaneo ( Boolean.TRUE );
//      TODO : inserire qui la data scadenza???
        pagamento.setFineValidita ( lastDayOfTheYear () );
        


        try {
            DatiSpecificiRiscossioneInput request = new DatiSpecificiRiscossioneInput ();
            request.setCodiceFiscaleEnte ( riferimento.getCodiceFiscaleEnte () );
            request.setTipoPagamento ( riferimento.getCodiceVersamento () );
            request.setAnnoEsercizio ( Calendar.getInstance ().get ( Calendar.YEAR ) );
            DatiSpecificiRiscossioneOutput dsr = pagamentoFacade.getDatiSpecificiRiscossione ( request );
            pagamento.getComponenti ().add ( creaComponenteDiDefault (pagamento, dsr, datiPersonali.getImporto () ));
        } catch ( TassonomiaServiceException e1 ) {
            throw new RuntimeException ( e1.getMessage (), e1 );
        } catch ( Exception e1 ) {
            throw new RuntimeException ( "Errore in fase di invocazinoe del catalogo!", e1 );
        }
        pagamento.setConsensoPrivacy ( true );
        Long idPagamento = pagamentoFacade.inserisci ( pagamento );
        pagamento.setIdPagamento ( idPagamento );
        try {
            pagamento = pagamentoFacade.ricerca ( idPagamento );// :FIXME Valutare di eliminare questa ricerca a mio parere inutile.
            saveAttributeIntoSession ( pagamento, session );
        } catch ( NoDataException e ) {
            throw new RuntimeException ( "Pagamento non trovato (id=" + idPagamento + ")" );
        }

        log.debugEnd ( methodName );
        return pagamento;
    }

    
    private PagamentoComponenti creaComponenteDiDefault (Pagamento pagamento, DatiSpecificiRiscossioneOutput dsr, BigDecimal importo ) throws TassonomiaServiceException {

        PagamentoComponenti componente = new PagamentoComponenti ();
        componente.setProgressivo ( 1 );
        componente.setImporto ( importo );
        componente.setUtenteUltimaModifica ( "System" );
        List<DatiSpecificiRiscossione> listDati = new LinkedList<> ();
        for ( DatiSpecificiRiscossione dato: dsr.getElencoDatiSpecifici () ) {
            if ( null != dato.getAnnoAccertamento () && null != dato.getNumeroAccertamento () ) {
                listDati.add ( dato );
            }

        }
        if ( CollectionUtils.isEmpty ( listDati ) || listDati.size () > 1 ) {
            CodiciEsito ce = CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;
            throw new TassonomiaServiceException ( ce.getCodice (),
                ce.getMessaggio ( maxErrorMessageWidth ) + " , nessun riferimento contabile valido per l'anno in corso su Catalogo!" );
        } else {
            componente.setAnnoAccertamento ( listDati.get ( 0 ).getAnnoAccertamento () );
            componente.setDatiSpecificiRiscossione ( listDati.get ( 0 ).getCodice () );
            componente.setNumeroAccertamento ( listDati.get ( 0 ).getNumeroAccertamento () );
            componente.setCausale ( pagamento.getCausale () );// sarebbe riferimento.getPagamentoDesc () che viene valorizzato con il campo tipoPagamento.getDescrizionePortale
            // per retrocompatibilita'
            //TAG_PPU - 2019 - RDI 004 - RDI 005
            pagamento.setAnnoAccertamento ( listDati.get ( 0 ).getAnnoAccertamento () );
            pagamento.setNumeroAccertamento ( StringUtils.isNotBlank ( listDati.get ( 0 ).getNumeroAccertamento () )
                            ? Integer.valueOf ( listDati.get ( 0 ).getNumeroAccertamento () ) : null );
            //TAG_PPU - 2019 - RDI 004 - RDI 005
        }
        return componente;
    }
    
    private void inserisciRegistroVersamento ( Pagamento pagamento, StatoPagamento stato ) {
        final String methodName = "inserisciRegistroVersamento";
        log.debugStart ( methodName );

        RegistroVersamenti registroVersamenti = new RegistroVersamenti ();
        registroVersamenti.setIdPagamento ( pagamento.getIdPagamento () );
        registroVersamenti.setRisultato ( stato.getDescrizione () );
        if ( transazioneMdp != null ) {
            registroVersamenti.setIdTransazione ( transazioneMdp.getIdTransazione () );
        }
        registroVersamenti
        .setIuv ( StringUtils.isNotEmpty ( pagamento.getIuvRegistroVersamenti () ) ? pagamento.getIuvRegistroVersamenti () : pagamento.getIuv () );
        registroVersamenti.setIdStato ( stato.getId () );
        registroVersamenti.setOrigineInserimento ( "Portale Pagamenti" );
        pagamentoFacade.inserisciRegistroVersamentiEGestioneStato ( registroVersamenti );

        log.debugEnd ( methodName );
    }

    private void inserisciTransazioneMdp ( Pagamento pagamento, Transazione transazione ) {
        final String methodName = "inserisciTransazioneMdp";
        log.debugStart ( methodName );

        transazioneMdp = new TransazioneMdp ();
        transazioneMdp.setIdTransazione ( transazione.getTransactionId () );
        transazioneMdp.setIuv ( pagamento.getIuvRegistroVersamenti () );
        pagamentoFacade.inserisciTransazioneMdp ( transazioneMdp );

        log.debugEnd ( methodName );
    }

    private void aggiornaTransazioneMdp ( InformativePSPScelto informativaPSP, final String idTransazione ) throws IllegalArgumentException, NoDataException {
        final String methodName = "aggiornaTransazioneMdp";
        log.debugStart ( methodName );

        if ( transazioneMdp == null ) {
            transazioneMdp = pagamentoFacade.ricercaTransazioneMdp ( idTransazione );
        }

        transazioneMdp.setIdGateway ( informativaPSP.getGatewayId () );
        transazioneMdp.setIdPaymentMode ( informativaPSP.getPaymentModeId () );
        transazioneMdp.setIdInformativaPsp ( informativaPSP.getIdinformativapsp () );
        transazioneMdp.setIdentificativoPsp ( informativaPSP.getIdentificativoPSP () );
        transazioneMdp.setRagioneSocialePsp ( informativaPSP.getRagioneSociale () );
        transazioneMdp.setIdentificativoCanalePsp ( informativaPSP.getIdentificativoCanale () );
        transazioneMdp.setTipoVersamentoPsp ( informativaPSP.getTipoVersamento () );
        transazioneMdp.setModelloPagamentoPsp ( informativaPSP.getModelloPagamento () );
        pagamentoFacade.aggiornaTransazioneMdp ( transazioneMdp );

        log.debugEnd ( methodName );
    }

    @Autowired
    private MailFacade mailFacade;

    private void inviaMailInAttesa ( Pagamento pagamento, Transazione transazione ) {
        final String methodName = "inviaMailInAttesa";
        log.debugStart ( methodName );

        try {
            String iuv = pagamento.getIuv ();
            String ente = pagamento.getEnte ().getNome ();
            String cfEnte = pagamento.getEnte ().getCodiceFiscale ();
            String tipoPagamento = pagamento.getTipoPagamento ().getDescrizionePortale ();
            String idTRansazione = transazione.getTransactionId ();
            String codiceAvviso = pagamento.getCodiceAvviso ();
            String iuvTransazione = ( transazioneMdp == null ) ? null : transazioneMdp.getIuv ();
            String ragioneSocialePSP = ( transazioneMdp == null ) ? "" : transazioneMdp.getRagioneSocialePsp ();
            String dataOra = "";
            if ( transazione.getStartDate () != null ) {
                Calendar calendar = transazione.getStartDate ().toGregorianCalendar ();
                SimpleDateFormat formatter = new SimpleDateFormat ( "dd/MM/yyyy  hh:mm:ss" );
                formatter.setTimeZone ( calendar.getTimeZone () );
                dataOra = formatter.format ( calendar.getTime () );
            }
            String importo = ( pagamento.getImporto () == null ) ? "" : pagamento.getImporto ().toPlainString ();

            StringBuilder sb = new StringBuilder ();
            sb.append ( getMessage ( "riga1", null ) );
            sb.append ( getMessage ( "riga2", null ) );
            sb.append ( "\n" );
            sb.append ( getMessage ( "riga3", null ) );
            sb.append ( "\n" );
            sb.append ( getMessage ( "dato01", ente ) );
            sb.append ( getMessage ( "dato02", cfEnte ) );
            sb.append ( "\n" );
            sb.append ( getMessage ( "dato03", tipoPagamento ) );
            if ( StringUtils.isNotBlank ( idTRansazione ) ) {
                sb.append ( getMessage ( "dato04", idTRansazione ) );
            }
            if ( StringUtils.isNotBlank ( codiceAvviso ) ) {
                sb.append ( getMessage ( "dato05", codiceAvviso ) );
            }
            if ( StringUtils.isNotBlank ( iuvTransazione ) ) {
                sb.append ( getMessage ( "dato06", iuvTransazione ) );
            }
            if ( StringUtils.isNotBlank ( ragioneSocialePSP ) ) {
                sb.append ( getMessage ( "dato07", ragioneSocialePSP ) );
            }
            sb.append ( "\n" );
            sb.append ( getMessage ( "dato08", dataOra ) );
            sb.append ( "\n" );
            sb.append ( getMessage ( "dato09", importo ) );
            sb.append ( "\n" );
            sb.append ( getMessage ( "saluto", null ) );
            sb.append ( getMessage ( "firma", null ) );

            Mail mail = new Mail ();
            mail.setTo ( pagamento.getPagatore ().getEmail () );
            mail.setSubject ( getMessage ( "oggetto", iuv ) );
            mail.setText ( sb.toString () );

            mailFacade.inviaMail ( mail );
        } catch ( UnsupportedEncodingException | MessagingException e ) {
            log.debug ( methodName, ExceptionUtils.getStackTrace ( e ) );
        }

        log.debugEnd ( methodName );
    }

    private String getMessage ( String riga, String argument ) {
        return messageSource.getMessage ( "mailRicezionePagamento." + riga, new Object [] { argument }, Locale.ITALIAN ) + "\n";
    }
    
    
    private Date lastDayOfTheYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set ( Calendar.DAY_OF_MONTH, 31 );
        calendar.set ( Calendar.MONTH, 11 );
        Date now = calendar.getTime();
        return now;
    }

}

/*
 * COME STAMPARE DIRETTAMENTE A VIDEO (httpServletresponse -> res) PrintWriter out = null; try { out = res.getWriter(); } catch (IOException e) {
 * e.printStackTrace(); } out.println("Effettua il pagamento..."); out.close();
 */

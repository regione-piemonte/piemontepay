/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.business;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import it.csi.epay.epayservices.integration.db.manager.AnagraficaManager;
import it.csi.epay.epayservices.integration.db.manager.ChiamataEsternaManager;
import it.csi.epay.epayservices.integration.db.manager.ChiamataEsternaSincronaManager;
import it.csi.epay.epayservices.integration.db.manager.ConfigurazioneManager;
import it.csi.epay.epayservices.integration.db.manager.EnteManager;
import it.csi.epay.epayservices.integration.db.manager.EsitoChiamataEsternaManager;
import it.csi.epay.epayservices.integration.db.manager.MarcaBolloManager;
import it.csi.epay.epayservices.integration.db.manager.PagamentoManager;
import it.csi.epay.epayservices.integration.db.manager.RegistroVersamentiManager;
import it.csi.epay.epayservices.integration.db.manager.TipoPagamentoManager;
import it.csi.epay.epayservices.integration.db.manager.TransazioneMdpManager;
import it.csi.epay.epayservices.integration.mdp.Iuv;
import it.csi.epay.epayservices.integration.mdp.MdpCore;
import it.csi.epay.epayservices.integration.mdp.MdpException;
import it.csi.epay.epayservices.interfaces.ejb.PagamentoMarcaDaBolloFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.interfaces.rs.CodiciEsito;
import it.csi.epay.epayservices.interfaces.rs.TassonomiaAdapterClient;
import it.csi.epay.epayservices.interfaces.rs.exception.TassonomiaServiceException;
import it.csi.epay.epayservices.model.AccessoChiamanteEsternoSincronoComponentePagamentoInput;
import it.csi.epay.epayservices.model.Anagrafica;
import it.csi.epay.epayservices.model.ChiamanteEsterno;
import it.csi.epay.epayservices.model.ChiamataEsternaNonValida;
import it.csi.epay.epayservices.model.ChiamataEsternaSincronaNonValida;
import it.csi.epay.epayservices.model.CodiceAvviso;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossione;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneInput;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneOutput;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.EsitoChiamataEsterna;
import it.csi.epay.epayservices.model.MarcaDigitale;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PagamentoComponenti;
import it.csi.epay.epayservices.model.PagamentoMarcaBolloChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.PagamentoMarcaDaBolloChiamanteEsternoInput;
import it.csi.epay.epayservices.model.RegistroVersamenti;
import it.csi.epay.epayservices.model.StatoPagamento;
import it.csi.epay.epayservices.model.TaglioMarca;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.model.TracciabilitaChiamanteEsterno;
import it.csi.epay.epayservices.model.TracciabilitaChiamanteEsternoSincrono;
import it.csi.epay.epayservices.model.TransazioneMdp;
import it.csi.epay.epayservices.utilities.LogUtil;
import it.csi.epay.epayservices.utilities.XmlUtil;
import it.csi.mdpcore.DatiAccertamentoRPT;
import it.csi.mdpcore.DatiMarcaBolloDigitale;
import it.csi.mdpcore.DatiSingoloVersamentoRPT;
import it.csi.mdpcore.DatiVersamentoRPT;
import it.csi.mdpcore.ElencoRPT;
import it.csi.mdpcore.RPTData;
import it.csi.mdpcore.SoggettoPagatore;
import it.csi.mdpcore.Transazione;


@Stateless ( name = "PagamentoMarcaDaBolloFacade", mappedName = "PagamentoMarcaDaBollo" )
public class PagamentoMarcaDaBolloBean extends _BaseBean implements PagamentoMarcaDaBolloFacade {

    private static final String PABL = "PABL";

    private static final String REGISTRO_VERSAMENTO_ORIGINE_INSERIMENTO = "Gestionale esterno (chiamata sincrona) per marca";
    
    private static final int MAX_ERROR_MESSAGE_WIDTH = 200;

    private static final int MDP_NUMBER_RETRY = 3;

    private static LogUtil log = new LogUtil ( PagamentoMarcaDaBolloBean.class );

    @EJB
    private ChiamataEsternaSincronaManager chiamataEsternaSincronaManager;

    @EJB
    private EnteManager enteManager;

    @EJB
    private ChiamataEsternaManager chiamataEsternaManager;

    @EJB
    private EsitoChiamataEsternaManager esitoChiamataEsternaManager;

    @EJB
    private PagamentoManager pagamentoManager;

    @EJB
    private TipoPagamentoManager tipoPagamentoManager;

    @EJB
    private AnagraficaManager anagraficaManager;

    @EJB
    private RegistroVersamentiManager registroVersamentiManager;

    @EJB
    private TransazioneMdpManager transazioneMdpManager;

    @EJB
    private MarcaBolloManager marcaBolloManager;

    @EJB
    private Iuv mdpIuv;

    @EJB
    private MdpCore mdpCore;

    @EJB
    private ConfigurazioneManager configurazioneManager;

    /**
     * Effettua il pagamento di una marca da bollo con o senza istanza collegata
     *
     * @param input i dati di identificazione del pagamento e marca da bollo
     * @return l'url presso il quale effettuare il pagamento
     *
     */
    @TransactionAttribute ( TransactionAttributeType.REQUIRED )
    @Override
    public PagamentoMarcaBolloChiamanteEsternoOutput pagamentoMarcaDaBollo ( PagamentoMarcaDaBolloChiamanteEsternoInput input ) {
		String methodName = "pagamentoMarcaDaBollo";

        log.debug ( methodName, "input : " + XmlUtil.obj2Xml ( input ) );
        PagamentoMarcaBolloChiamanteEsternoOutput result;

        try {
            result = doAccessoChiamanteEsternoSincrono ( input );
        } catch ( Exception t ) {
            result = fail ( input, EsitoChiamataEsterna.ERRORE_GENERICO, null, null, t );
        }

        log.debug ( methodName, "output : " + XmlUtil.obj2Xml ( result ) );

        return result;
    }

    private PagamentoMarcaBolloChiamanteEsternoOutput doAccessoChiamanteEsternoSincrono ( PagamentoMarcaDaBolloChiamanteEsternoInput input ) {
		String methodName = "doAccessoChiamanteEsternoSincrono";

        String iuvDocumento = input.getIuvIstanzaAssociata ();
        String iuvMarca = null;
		List<String> elencoIuvMarca = new ArrayList<> ();
        Transazione transazione = null;
        TransazioneMdp transazioneMdp = null;
        Pagamento pagamentoIstanza = null;
        ChiamanteEsterno chiamante;
        Ente ente;
        TipoPagamento tipoPagamentoIstanza = null;
        TipoPagamento tipoPagamentoMarca = null;
        TracciabilitaChiamanteEsterno traccia;
        String urlPagamento = null;

        PagamentoMarcaBolloChiamanteEsternoOutput risultatoValidazione;

        try {
            chiamante = chiamataEsternaManager.recuperaChiamanteEsterno ( input.getCodiceChiamante () ); //passo 2
            log.info ( methodName, "recuperato chiamante : " + chiamante.getCodiceChiamante () );
        } catch ( IllegalAccessException | NoDataException e1 ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_GENERICO, iuvDocumento, "Chiamante [" + input.getCodiceChiamante () + "] non trovato", e1 );
        }

        risultatoValidazione = validaChiamanteEsterno ( input, chiamante ); //passo 3
        if ( risultatoValidazione != null ) {
            log.info ( methodName, "validazione chiamante esterna fallita : " + risultatoValidazione.getDescrizioneEsito () );
            return risultatoValidazione;
        }

        //passo 4
        risultatoValidazione = validaInputMarca ( input );
        if ( risultatoValidazione != null ) {
            log.info ( methodName, "validazione input fallita : " + risultatoValidazione.getDescrizioneEsito () );
            return risultatoValidazione;
        }

        try {
            if ( chiamataEsternaManager.ricercaIdentificativoPagamento ( input.getIdentificativoPagamento () ) > 0 ) {
                return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, iuvDocumento,
                    "Identificativo pagamento [" + input.getIdentificativoPagamento () + "] duplicato", null );
            }
        } catch ( IllegalAccessException | NoDataException e1 ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_GENERICO, iuvDocumento,
                "Errore nella verifica per l'IUP [" + input.getIdentificativoPagamento () + "]",
                e1 );
        }

        Pagamento pagamentoIstanzaAssociata = null;
        if ( StringUtils.isNoneEmpty ( input.getIuvIstanzaAssociata () ) ) {
            pagamentoIstanzaAssociata = pagamentoManager.getPagamentoAttivo ( input.getIuvIstanzaAssociata () );
            if ( pagamentoIstanzaAssociata == null ) {
                return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, iuvDocumento,
                    "IUV istanza associata [" + input.getIuvIstanzaAssociata () + "] non trovato", null );
            }
            if ( pagamentoIstanzaAssociata.getTipoPagamento ().getTipologiaPagamento () == null
                || !PABL.equals ( pagamentoIstanzaAssociata.getTipoPagamento ().getTipologiaPagamento ().getCodice () ) ) {
                return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, iuvDocumento,
                    "Il tipo di istanza associata non e' compatibile con il pagamento della marca da bollo",
                    null );
            }
        }

        traccia = tracciaChiamataEsterna ( null, input, chiamante, iuvDocumento, transazione );

        log.info ( methodName, "inserita voce di traccia : " + traccia.getIdChiamata () );

        try {
            ente = enteManager.getByCF ( input.getCodiceFiscaleEnte () );
            log.info ( methodName, "recuperato ente : " + ente.getIdEnte () );
        } catch ( NoDataException e1 ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, iuvDocumento, "Ente [" + input.getCodiceFiscaleEnte () + "] non trovato", e1 );
        }

        // verifica del tipo pagamento in input per il documento istanza, solo se richiesto
        List<TipoPagamento> ricercaTipoPagamento = null;
        if ( !input.getFlagSoloMarca () ) {
            ricercaTipoPagamento = tipoPagamentoManager.getByEnteECodiceVersamento ( ente, input.getTipoPagamento () );
            if ( ricercaTipoPagamento != null && ricercaTipoPagamento.size () < 1 ) {
                return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, iuvDocumento,
                    "Codice versamento del documento [" + input.getTipoPagamento () + "] per l'ente [" + ente.getCodiceFiscale () + "] non trovato", null );
            } else if ( ricercaTipoPagamento.size () > 1 ) {
                return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, iuvDocumento,
                    "Codice versamento del documento [" + input.getTipoPagamento () + "] per l'ente [" + ente.getCodiceFiscale () + "] non univoco", null );
            } else {
                tipoPagamentoIstanza = ricercaTipoPagamento.get ( 0 );
                if ( tipoPagamentoIstanza.getTipologiaPagamento () == null
                    || !PABL.equalsIgnoreCase ( tipoPagamentoIstanza.getTipologiaPagamento ().getCodice () ) ) {
                    return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, iuvDocumento,
                        "Codice versamento del documento [" + input.getTipoPagamento () + "] per l'ente [" + ente.getCodiceFiscale ()
                            + "] non abilitato al pagamento della marca da bollo",
                        null );
                }
                log.info ( methodName, "recuperato tipo pagamento : " + tipoPagamentoIstanza.getIdTipoPagamento () );
            }
            risultatoValidazione = validaTipoPagamento ( input, tipoPagamentoIstanza );
            if ( risultatoValidazione != null ) {
                log.info ( methodName, "validazione tipo pagamento fallita : " + risultatoValidazione.getDescrizioneEsito () );
                return risultatoValidazione;
            }

            log.info ( methodName, "valuida aut : " + chiamante + " tipo " + tipoPagamentoIstanza.getIdTipoPagamento () );
            risultatoValidazione = validaAutorizzazioneChiamanteTipoPagamento ( input, chiamante, tipoPagamentoIstanza );
            if ( risultatoValidazione != null ) {
                log.info ( methodName, "validazione chiamante - tipo pagamento fallita : " + risultatoValidazione.getDescrizioneEsito () );
                return risultatoValidazione;
            }

//            log.info ( methodName, "Valida le componenti" );
//            risultatoValidazione = validaComponentiPagamento ( input, ente, tipoPagamentoIstanza );
//            if ( risultatoValidazione != null ) {
//                log.info ( methodName, "validazione componenti pagamento fallita : " + risultatoValidazione.getDescrizioneEsito () );
//                return risultatoValidazione;
//            }
        }

        // controllo del tipo pagamento della marca, basandomi sull'importo valorizzato
        if ( input.getImportoBollo () != null ) {
            ricercaTipoPagamento = tipoPagamentoManager.getByEnteMarca ( ente );

            if ( ricercaTipoPagamento != null && ricercaTipoPagamento.size () < 1 ) {
                return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, iuvDocumento,
                    "Codice versamento della marca da bollo [MABL] per l'ente [" + ente.getCodiceFiscale () + "] non trovato", null );
            } else if ( ricercaTipoPagamento.size () > 1 ) {
                return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, iuvDocumento,
                    "Codice versamento della marca da bollo [MABL] per l'ente [" + ente.getCodiceFiscale () + "] non univoco", null );
            } else {
                tipoPagamentoMarca = ricercaTipoPagamento.get ( 0 );
                log.info ( methodName, "recuperato tipo pagamento della marca da bollo: " + tipoPagamentoMarca.getIdTipoPagamento () );
            }
        }

        // controllo che il taglio ed il numero di marche che si desidera pagare siano corrette
        if ( input.getImportoBollo () != null ) {
            try {
                input.setImportoBollo ( input.getImportoBollo ().setScale ( 2 ) );
                TaglioMarca taglioMarca = marcaBolloManager.recuperaLimitiPerCodiceMarca ( input.getTipoBollo (), input.getImportoBollo () );
                if ( !taglioMarca.getImporto ().equals ( input.getImportoBollo () ) ) {
                    return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                        "importo marca da bollo " + input.getImportoBollo () + " errato, atteso importo= " + taglioMarca.getImporto (), null );
                }
                if ( taglioMarca.getLimite () < input.getQuantita () ) {
                    return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                        "quantita' di marche da bollo " + input.getQuantita () + " superiore al limite (" + taglioMarca.getImporto () + ")", null );
                }
                if ( taglioMarca.getControlloHash () && StringUtils.length ( input.getHashDocumento () ) != taglioMarca.getLunghezzaHash () ) {
                    return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                        "Lunghezza del'hash documento errata (lunghezza ammmessa " + taglioMarca.getLunghezzaHash () + ")", null );

                }
            } catch ( NoDataException e ) {
                return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                    "Tipo marca da bollo " + input.getTipoBollo () + " non trovato per importo = " + input.getImportoBollo (), e );
            }
        }
        // fine controlli formali

        /*
         * Inizializza la transazione, compone i dati di pagamento del bollo ed eventualmente aggiunge il pagamento di un documento.
         */
        ElencoRPT datiRPT = new ElencoRPT ();
        try {
            //siccome passa il pagamento intero per fornire solo l'application id, per non rifattorizzaare tutto creo un pagamento fittizio
            Pagamento applicationIdDaPagamento = new Pagamento ();
            applicationIdDaPagamento.setTipoPagamento ( tipoPagamentoMarca );

            transazione = mdpCore.initializzaTransazione ( applicationIdDaPagamento, MDP_NUMBER_RETRY );
            transazioneMdp = new TransazioneMdp ();
            transazioneMdp.setIdTransazione ( transazione.getTransactionId () );

            double amount = ( valoreNullSafe ( input.getImportoBollo () ) * valoreNullSafe ( input.getQuantita () ) );
            if ( Boolean.FALSE.equals ( input.getFlagSoloMarca () ) ) {
                amount += valoreNullSafe ( input.getImporto () );
            }
            transazione.setAmount ( amount );

            // aggiunge la componente di pagamento del documento (se flagSoloMarca = false)
            if ( !input.getFlagSoloMarca () && StringUtils.isEmpty ( input.getIuvIstanzaAssociata () ) ) {
                iuvDocumento = mdpIuv.generateNewIuv ( tipoPagamentoIstanza, MDP_NUMBER_RETRY );
                transazioneMdp.setIuv( iuvDocumento );
                pagamentoIstanza = costruisciPagamento ( input, tipoPagamentoIstanza, iuvDocumento );
                pagamentoIstanzaAssociata = pagamentoIstanza;//in questo caso non e' lo iuvIstanzaAssociata ma un pagamento nuovo
                tracciaRegistroPagamento ( pagamentoIstanza, StatoPagamento.TRANSAZIONE_INIZIALIZZATA, transazioneMdp );
                datiRPT.getRptdata ().add ( costruisciRPT ( pagamentoIstanza, false, input ) );
                log.debug ( methodName, "costruito payload RPT : " + XmlUtil.obj2Xml ( datiRPT ) );
            }

            /*
             * Lavoro necessario per modifiche V02 di analisi: posso pagare un documento nuovo, solo iuv associato, solo bollo, entrambi. Mi baso sull'importo
             * bollo, se presente sicuramente devo comporre la parte di pagamento bollo.
             */
            List<Pagamento> elencoPagamentiBollo = new LinkedList<>();
            if ( input.getImportoBollo () != null ) {
                //una RPT per marca (ciclo quantita')
                for ( int i = 0; i < input.getQuantita (); i++ ) {
                    iuvMarca = mdpIuv.generateNewIuv ( tipoPagamentoMarca, MDP_NUMBER_RETRY );
                    elencoIuvMarca.add ( iuvMarca );
                    Pagamento pagamentoBollo = costruisciPagamentoMarcaBollo ( input, tipoPagamentoMarca, iuvMarca );
                    elencoPagamentiBollo.add(pagamentoBollo);
                    datiRPT.getRptdata ().add ( costruisciRPT ( pagamentoBollo, true, input ) );
                    if (transazioneMdp.getIuv() == null )
                    	transazioneMdp.setIuv ( pagamentoBollo.getIuvRegistroVersamenti () );

                    MarcaDigitale daSalvare = new MarcaDigitale ();
                    daSalvare.setEpayTPagamentoDocumentoAssociato ( pagamentoIstanzaAssociata );
                    daSalvare.setEpayTPagamentoMarcaBollo ( pagamentoBollo );
                    daSalvare.setHashDocumento ( input.getHashDocumento () );
                    daSalvare.setIuvMarca ( iuvMarca );
                    marcaBolloManager.salvaDatiMarca ( daSalvare );
                    if ( pagamentoIstanzaAssociata != null )
                        marcaBolloManager.salvaAssociazioneMarca ( iuvMarca, pagamentoIstanzaAssociata.getIuv () );
                }
                transazioneMdpManager.inserisci ( transazioneMdp );
                log.debug ( methodName, "inserita traccia transazione : \n" + XmlUtil.obj2Xml ( transazioneMdp ) );
                log.info(methodName, "ORA PROVO A REGISTRARE VERSAMENTI CORRETTAMENTE");
                for (Pagamento singoloBollo : elencoPagamentiBollo)
                	tracciaRegistroPagamento ( singoloBollo, StatoPagamento.TRANSAZIONE_INIZIALIZZATA, transazioneMdp );
                tracciaChiamataEsterna ( traccia, input, chiamante, iuvMarca, transazione );
            }

            // chiamata a MDP per avviare il pagamento e ricevere l'url WISP
            try {
                urlPagamento = mdpCore.paymentURL ( transazione, datiRPT, Boolean.FALSE );
                for (Pagamento singoloBollo : elencoPagamentiBollo)
                	tracciaRegistroPagamento ( singoloBollo, StatoPagamento.TRANSAZIONE_AVVIATA, transazioneMdp );
                tracciaRegistroPagamento ( pagamentoIstanza, StatoPagamento.TRANSAZIONE_AVVIATA, transazioneMdp );

            } catch ( MdpException e ) {
                log.error ( methodName, "Errore MDP", e );
                for (Pagamento singoloBollo : elencoPagamentiBollo)
                	tracciaRegistroPagamento ( singoloBollo, StatoPagamento.TRANSAZIONE_ERRORE, transazioneMdp );
                tracciaRegistroPagamento ( pagamentoIstanza, StatoPagamento.TRANSAZIONE_ERRORE, transazioneMdp );

                String message;
                if ( e.getCause () != null && e.getCause ().getMessage () != null && e.getCause ().getMessage ().contains ( "RPT duplicata" ) ) {
                    message = "Il pagamento non pu\u00f2 essere effettuato perch\u00e9 risulta gi\u00e0 una transazione di pagamento in corso.";
                    return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, iuvDocumento, message, e );
                } else {
                    message = "Errore temporaneo di comunicazione. Si prega di riprovare pi\u00f9 tardi. Se l'errore dovesse persiste contattare l'assistenza.";
                    return fail ( input, EsitoChiamataEsterna.ERRORE_GENERICO, iuvDocumento, message, e );
                }

            }
        } catch ( TassonomiaServiceException e ) {
            return fail ( input, e.getCode (), iuvDocumento, e.getMessage (), e );
        } catch ( Exception e ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_GENERICO, iuvDocumento, "Errore in fase di generazione dello IUV", e );
        }

        PagamentoMarcaBolloChiamanteEsternoOutput output
            = success ( input, EsitoChiamataEsterna.OPERAZIONE_COMPLETATA_CON_SUCCESSO, iuvDocumento, urlPagamento );
        output.setIuvDocumento ( iuvDocumento );
        output.setElencoIuvMarcaBollo ( elencoIuvMarca );
        return output;
    }

    private double valoreNullSafe ( Number daTrasformare ) {
        if ( daTrasformare == null )
            return 0.0d;
        return daTrasformare.doubleValue ();
    }

    private PagamentoMarcaBolloChiamanteEsternoOutput validaComponentiPagamento ( PagamentoMarcaDaBolloChiamanteEsternoInput input, Ente ente,
        TipoPagamento tipoPagamento ) {
        if ( ente.getFlagRiconciliazioneVersamenti () == null || !ente.getFlagRiconciliazioneVersamenti () ) {
            // ente.getFlagRiconciliazioneVersamenti () FALSE
            //          Il sistema non effettua controlli sui campi specifici datiSpecificiRiscossione, annoAccertamento, numeroAccertamento.
            //          Puo' non esserci nessuna componente nel pagamento.
        } else {
            // ente.getFlagRiconciliazioneVersamenti () TRUE
            if ( input.getComponentiPagamento () != null && input.getComponentiPagamento ().size () == 1 ) {
                //            Il sistema effettua un controllo bloccante di match dei campi datiSpecificiRiscossione, annoAccertamento, numeroAccertamento
                //            rispetto al contenuto sul DB dello Sportello in
                //            <epay_t_tipo_pagamento>.dati_specifici_riscossione, <epay_t_tipo_pagamento>.numero_accertamento, <epay_t_tipo_pagamento>.anno_acceramento
                //            se e solo se in ingresso c'e' una sola componente.
                //            Se vi sono piu' componenti specificate non effettuare alcun controllo.
                //            Nel caso i dati oggetto del controllo non corrispondano e' necessario rifiutare la chiamata motivandone il motivo.
                //            Attenzione! Attualmente sullo Sportello e' possibile per questo tipo di pagamento
                //            (creazione della posizione debitoria contestuale alla chiamata dal gestionale esterno, equivalente al pagamento Spontaneo)
                //            l'indicazione dei riferimenti contabili di una sola componente, la principale in <epay_t_tipo_pagamento>.
                //            Quindi il controllo e' possibile se in ingresso corrispondentemente vi e' una sola voce come componente.
                AccessoChiamanteEsternoSincronoComponentePagamentoInput componente = input.getComponentiPagamento ().get ( 0 );
                if ( !equals ( componente.getAnnoAccertamento (), tipoPagamento.getAnnoAccertamento () ) ) {
                    return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, null, null );
                }
                if ( !equals ( componente.getNumeroAccertamento (), tipoPagamento.getNumeroAccertamento () ) ) {
                    return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, null, null );
                }
            }
        }

        return null;
    }

    private boolean equals ( Object o1, Object o2 ) {
        String v1 = o1 == null ? "" : o1.toString ();
        String v2 = o2 == null ? "" : o2.toString ();
        log.debug ( "validaComponentiPagamento.equals", "comparing [" + v1 + "] with [" + v2 + "]" );
        return v1.equals ( v2 );
    }

    @Override
    public boolean validaAutorizzazioneChiamanteTipoPagamento ( String codiceChiamante, Long idTipoPagamento ) {

        try {

            if ( !chiamataEsternaSincronaManager.verificaAurizzazioneChiamanteEsternoSincrono ( codiceChiamante, idTipoPagamento ) ) {
                return false;
            }
        } catch ( IllegalArgumentException | NoDataException e1 ) {
            return false;
        }

        return true;
    }

    private PagamentoMarcaBolloChiamanteEsternoOutput validaAutorizzazioneChiamanteTipoPagamento ( PagamentoMarcaDaBolloChiamanteEsternoInput input,
        ChiamanteEsterno chiamante, TipoPagamento tipoPagamento ) {

        try {
            log.info ( "validaAutorizzazioneChiamanteTipoPagamento",
                "provo a validare" + chiamante.getCodiceChiamante () + tipoPagamento.getIdTipoPagamento () );
            if ( !chiamataEsternaSincronaManager.verificaAurizzazioneChiamanteEsternoSincrono ( chiamante.getCodiceChiamante (),
                tipoPagamento.getIdTipoPagamento () ) ) {
                log.info ( "validaAutorizzazioneChiamanteTipoPagamento",
                    "entrato nell'if" + chiamante.getCodiceChiamante () + tipoPagamento.getIdTipoPagamento () );
                return fail ( input, EsitoChiamataEsterna.ERRORE_GESTIONALE_NON_AUTORIZZATO_AL_TIPO_PAGAMENTO, null, null, null );
            }
        } catch ( IllegalArgumentException | NoDataException e1 ) {
            log.error ( "validaAutorizzazioneChiamanteTipoPagamento", e1 );
            return fail ( input, EsitoChiamataEsterna.ERRORE_GESTIONALE_NON_AUTORIZZATO_AL_TIPO_PAGAMENTO, null,
                "Errore nella verifica delle autorizzazioni del gestionale per i tipi pagamento", e1 );
        } catch ( Exception e1 ) {
            log.error ( "validaAutorizzazioneChiamanteTipoPagamento", "ERRORE" );
            log.error ( "validaAutorizzazioneChiamanteTipoPagamento", e1 );
            return fail ( input, EsitoChiamataEsterna.ERRORE_GESTIONALE_NON_AUTORIZZATO_AL_TIPO_PAGAMENTO, null,
                "Errore nella verifica delle autorizzazioni del gestionale per i tipi pagamento", e1 );
        }

        return null;
    }

    private PagamentoMarcaBolloChiamanteEsternoOutput validaTipoPagamento ( PagamentoMarcaDaBolloChiamanteEsternoInput input, TipoPagamento tipoPagamento ) {
        Date today = Date.from ( LocalDate.now ().atStartOfDay ( ZoneId.systemDefault () ).toInstant () );

        if ( tipoPagamento.getTipologiaPagamento () == null || !tipoPagamento.getTipologiaPagamento ().getCodice ().equals ( "PABL" ) ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Codice versamento [" + input.getTipoPagamento () + "] non abilitato al pagamento tramite redirect sincrona", null );
        }

        if ( tipoPagamento.getInizioValidita () == null || tipoPagamento.getInizioValidita ().after ( today ) ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Codice versamento [" + input.getTipoPagamento () + "] non ancora in periodo di validita'", null );
        }

        if ( tipoPagamento.getFineValidita () != null && tipoPagamento.getFineValidita ().before ( today ) ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Codice versamento [" + input.getTipoPagamento () + "] non piu' valido", null );
        }

        return null;
    }

    private PagamentoMarcaBolloChiamanteEsternoOutput validaChiamanteEsterno ( PagamentoMarcaDaBolloChiamanteEsternoInput input, ChiamanteEsterno chiamante ) {
        Date today = Date.from ( LocalDate.now ().atStartOfDay ( ZoneId.systemDefault () ).toInstant () );

        if ( chiamante.getDataInizioValidita () == null || chiamante.getDataInizioValidita ().after ( today ) ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Chiamante [" + chiamante.getCodiceChiamante () + "] non ancora in periodo di validita'", null );
        }

        if ( chiamante.getDataFineValidita () != null && chiamante.getDataFineValidita ().before ( today ) ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Chiamante [" + chiamante.getCodiceChiamante () + "] non piu' valido", null );
        }

        return null;
    }

    /*
     * Valida i dati in input
     */
    private PagamentoMarcaBolloChiamanteEsternoOutput validaInputMarca ( PagamentoMarcaDaBolloChiamanteEsternoInput input ) {

        if ( StringUtils.isBlank ( input.getIdentificativoPagamento () ) ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Il campo identificativoPagamento e' obbligatorio", null );
        }
        
        if ( input.getIdentificativoPagamento ().length () > 50 ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Il campo identificativoPagamento deve essere minore di 50 caratteri!", null );
        }
        // codice chiamante obbligatorio
        if ( StringUtils.isBlank ( input.getCodiceChiamante () ) ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo codiceChiamante e' obbligatorio", null );
        }

        // codice fiscale ente
        if ( StringUtils.isBlank ( input.getCodiceFiscaleEnte () ) ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo codiceFiscaleEnte e' obbligatorio", null );
        }

        // causale obbligatorio
        if ( StringUtils.isBlank ( input.getCausale () ) ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo causale e' obbligatorio", null );
        }

        // nome, cognome e ragione sociale
        if ( StringUtils.isBlank ( input.getRagioneSociale () ) ) {
            if ( StringUtils.isBlank ( input.getNome () ) || StringUtils.isBlank ( input.getCognome () ) ) {
                return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "E' obbligatorio indicare i campi nome e cognome, oppure ragioneSociale",
                    null );
            }
        }

        // email
        if ( StringUtils.isBlank ( input.getEmail () ) ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo email e' obbligatorio", null );
        } else {
			if ( !input.getEmail ().matches ( "[a-zA-Z0-9_\\.\\+\\-]+@[a-zA-Z0-9\\-]+(\\.[a-zA-Z0-9\\-]+)*" ) ) {
				return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il formato della email non e' corretto", null );
			}
		}

        // codice fiscale partita iva pagatore
        if ( StringUtils.isBlank ( input.getCodiceFiscalePartitaIVAPagatore () ) ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo codiceFiscalePartitaIVAPagatore e' obbligatorio", null );
        }

        // hash documento
        if ( StringUtils.isEmpty ( input.getHashDocumento () ) ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo hashDocumento e' obbligatorio", null );
        }

        // provincia
        if ( StringUtils.isEmpty ( input.getProvincia () ) ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo provincia e' obbligatorio", null );
        }

        // tipo bollo
        /*
         * if ( StringUtils.isEmpty ( input.getTipoBollo () ) ) { return fail ( input, EsitoChiamataEsterna.ERRORE_GENERICO, null,
         * "Il campo tipoBollo e' obbligatorio", null ); } else if ( StringUtils.length ( input.getTipoBollo () ) > 2 ) { return fail ( input,
         * EsitoChiamataEsterna.ERRORE_GENERICO, null, "Il campo tipoBollo non e' valido", null ); } // importo bollo if ( input.getImportoBollo () == null ) {
         * return fail ( input, EsitoChiamataEsterna.ERRORE_GENERICO, null, "Il campo importoBollo e' obbligatorio", null ); } else if ( input.getImportoBollo
         * ().compareTo ( BigDecimal.ZERO ) <= 0 ) { return fail ( input, EsitoChiamataEsterna.ERRORE_GENERICO, null,
         * "Il campo importoBollo deve essere maggiore di zero", null ); } // quantita bollo if ( input.getQuantita () == null ) { return fail ( input,
         * EsitoChiamataEsterna.ERRORE_GENERICO, null, "Il campo quantita e' obbligatorio", null ); } else if ( input.getQuantita () <= 0 ) { return fail (
         * input, EsitoChiamataEsterna.ERRORE_GENERICO, null, "Il campo quantita deve essere maggiore di zero", null ); }
         */
        // identificativo pagamento
        if ( StringUtils.isBlank ( input.getIdentificativoPagamento () ) ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo identificativoPagamento e' obbligatorio", null );
        }
        
        if ( Boolean.TRUE.equals ( input.getFlagSoloMarca () )
            && ( null != input.getImporto () && BigDecimal.ZERO.setScale ( 2 ).compareTo ( input.getImporto ().setScale ( 2 ) ) != 0 ) ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Il campo importo deve essere valorizzato con un valore diverso da 0 solo se il flag solo marca e' a false.", null );
        }

        return validaComponentiPagamentoFlagSoloMarca ( input );

    }

    /*
     * Valida i componenti di pagamento
     */
    private PagamentoMarcaBolloChiamanteEsternoOutput validaComponentiPagamentoFlagSoloMarca ( PagamentoMarcaDaBolloChiamanteEsternoInput input ) {
        // flag solo marca e iuv istanza associata
        if ( input.getFlagSoloMarca () == null ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo flagSoloMarca e' obbligatorio", null );
        } else if ( input.getFlagSoloMarca () ) {

            //            if ( StringUtils.isEmpty ( input.getIuvIstanzaAssociata () ) ) {
            //                return fail ( input, EsitoChiamataEsterna.ERRORE_GENERICO, null, "Il campo iuvIstanzaAssociata e' obbligatorio con flag solo marca=true",
            //                    null );
            //            }

            if ( input.getComponentiPagamento () != null ) {
                if ( StringUtils.isEmpty ( input.getIuvIstanzaAssociata () ) ) {
                    return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Le componenti del pagamento non sono ammesse ",
                        null );
                }
            }

            if ( input.getComponentiPagamento () != null ) {
                return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                    "In caso di pagamento del solo bollo i componenti di pagamento sono da omettere",
                    null );
            }
        } else {

            // tipo pagamento obbligatorio
            if ( StringUtils.isBlank ( input.getTipoPagamento () ) ) {
                return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo tipoPagamento e' obbligatorio", null );
            }

            // importo obbligatorio solo se flag solo marca
            if ( input.getImporto () == null ) {
                return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo importo e' obbligatorio con flag solo marca=false", null );
            }

            if ( input.getImporto ().compareTo ( BigDecimal.ZERO ) < 0 ) {
                return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo importo non e' valido", null );
            }
            input.setImporto ( input.getImporto ().setScale ( 2 ) );
        }

        if ( input.getComponentiPagamento () != null ) {
            if ( input.getImporto () == null ) {
                return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo importo non e' valido", null );
            }
            BigDecimal totaleComponenti = BigDecimal.ZERO;
            for ( AccessoChiamanteEsternoSincronoComponentePagamentoInput componente: input.getComponentiPagamento () ) {
                if ( componente.getImporto () == null ) {
                    return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo importo delle componenti del pagamento e' obbligatorio", null );
                }

                if ( componente.getProgressivo () == null ) {
                    return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo progressivo delle componenti del pagamento e' obbligatorio",
                        null );
                }

                if ( StringUtils.isBlank ( componente.getCausale () ) ) {
                    return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo causale delle componenti del pagamento e' obbligatorio", null );
                }
                if ( componente.getAnnoAccertamento () == null ) {
                    return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo annoAccertamento delle componenti del pagamento e' obbligatorio",
                        null );
                }
                if ( StringUtils.isBlank ( componente.getNumeroAccertamento () ) ) {
                    return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                        "Il campo numeroAccertamento delle componenti del pagamento e' obbligatorio", null );
                }
                if ( componente.getImporto ().compareTo ( BigDecimal.ZERO ) < 0 ) {
                    return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo importo delle componenti del pagamento non e' valido", null );
                }
                componente.setImporto ( componente.getImporto ().setScale ( 2 ) );

                if ( componente.getProgressivo () < 0 ) {
                    return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo progressivo delle componenti del pagamento non e' valido",
                        null );
                }
                if ( componente.getAnnoAccertamento () < 0 ) {
                    return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo annoAccertamento delle componenti del pagamento non e' valido",
                        null );
                }
                totaleComponenti = totaleComponenti.add ( componente.getImporto () );
            }
            if ( !input.getImporto ().setScale ( 2 ).equals ( totaleComponenti.setScale ( 2 ) ) ) {
                return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "La somma degli importi delle componenti e' diversa dall'importo totale",
                    null );
            }
        }
        return null;
    }

    private RPTData costruisciRPT ( Pagamento pagamento, boolean marca, PagamentoMarcaDaBolloChiamanteEsternoInput input ) {

        SoggettoPagatore soggettoPagatore = new SoggettoPagatore ();
        soggettoPagatore.setIdentificativoUnivocoPagatore ( pagamento.getPagatore ().getCodiceFiscale () );
        if ( pagamento.getPagatore ().getFlagPersonaFisica () != null && pagamento.getPagatore ().getFlagPersonaFisica () ) {
            soggettoPagatore.setTipoIdentificativoUnivocoPagatore ( "F" );
            soggettoPagatore.setAnagraficaPagatore (StringUtils.substring (
                StringUtils.join ( new String [] { pagamento.getPagatore ().getNome (), pagamento.getPagatore ().getCognome () }, " " ), 0, 70) );
        } else {
            soggettoPagatore.setTipoIdentificativoUnivocoPagatore ( "G" );
            soggettoPagatore.setAnagraficaPagatore ( StringUtils.substring ( pagamento.getPagatore ().getRagioneSociale (), 0, 70) );
        }
        if ( StringUtils.isNotBlank ( pagamento.getPagatore ().getEmail () ) ) {
            soggettoPagatore.setEMailPagatore ( pagamento.getPagatore ().getEmail () );
        }

        DatiVersamentoRPT datiVersamentoRpt = new DatiVersamentoRPT ();
        datiVersamentoRpt.setImportoTotaleDaVersare ( pagamento.getImporto () );
        datiVersamentoRpt.setIdentificativoUnivocoVersamento ( pagamento.getIuvRegistroVersamenti () );

        // Ramo di codice morto in quanto la nuova logira RDI-41 prevede sempre almeno una componente.
        if ( pagamento.getComponenti () == null || pagamento.getComponenti ().isEmpty () ) {
            String causaleVersamento = componiCausaleVersamento ( pagamento.getIuvRegistroVersamenti (), pagamento.getImporto (),
                pagamento.getTipoPagamento ().getDescrizionePortale () );
            DatiSingoloVersamentoRPT datiSingoloVersamentoRPT = new DatiSingoloVersamentoRPT ();
            datiSingoloVersamentoRPT.setCausaleVersamento ( causaleVersamento );
            datiSingoloVersamentoRPT.setImportoSingoloVersamento ( pagamento.getImporto () );
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

            if ( marca ) {
                DatiMarcaBolloDigitale marcaRPT = new DatiMarcaBolloDigitale ();
                marcaRPT.setHashDocumento ( input.getHashDocumento () );
                marcaRPT.setProvinciaResidenza ( input.getProvincia () );
                marcaRPT.setTipoBollo ( input.getTipoBollo () );
                datiSingoloVersamentoRPT.setDatiMarcaBolloDigitale ( marcaRPT );
            }

            datiVersamentoRpt.getDatiSingoloVersamento ().add ( datiSingoloVersamentoRPT );
        } else {
            for ( PagamentoComponenti componente: pagamento.getComponenti () ) {
                DatiSingoloVersamentoRPT datiSingoloVersamentoRPT = new DatiSingoloVersamentoRPT ();
                datiSingoloVersamentoRPT.setCausaleVersamento ( componiCausaleVersamento ( pagamento.getIuvRegistroVersamenti (), componente.getImporto (),
                    pagamento.getTipoPagamento ().getDescrizionePortale () ) );
                datiSingoloVersamentoRPT.setImportoSingoloVersamento ( componente.getImporto () );
                datiSingoloVersamentoRPT.setDatiSpecificiRiscossione ( componente.getDatiSpecificiRiscossione () );
                DatiAccertamentoRPT datiAccertamentoRPT = new DatiAccertamentoRPT ();
                datiAccertamentoRPT.setAnnoAccertamento ( componente.getAnnoAccertamento () );
                try {
                    datiAccertamentoRPT
                        .setNumeroAccertamento (
                            componente.getNumeroAccertamento () != null ? Integer.valueOf ( componente.getNumeroAccertamento () ) : null );
                } catch ( NumberFormatException nfe ) {
                    throw new RuntimeException ( "Numero pagamento " + pagamento.getIdPagamento () + " ha il formato del numero accertamento errato" );
                }
                datiSingoloVersamentoRPT.setDatiAccertamento ( datiAccertamentoRPT );

                if ( marca ) {
                    DatiMarcaBolloDigitale marcaRPT = new DatiMarcaBolloDigitale ();
                    marcaRPT.setHashDocumento ( input.getHashDocumento () );
                    marcaRPT.setProvinciaResidenza ( input.getProvincia () );
                    marcaRPT.setTipoBollo ( input.getTipoBollo () );
                    datiSingoloVersamentoRPT.setDatiMarcaBolloDigitale ( marcaRPT );
                }

                datiVersamentoRpt.getDatiSingoloVersamento ().add ( datiSingoloVersamentoRPT );
            }
        }

        RPTData rptData = new RPTData ();
        rptData.setAutenticazioneSoggetto ( "N/A" );
        rptData.setSoggettoPagatore ( soggettoPagatore );
        rptData.setSoggettoVersante ( null );
        rptData.setDatiVersamento ( datiVersamentoRpt );
        rptData.setApplicationId ( pagamento.getTipoPagamento ().getIdApplicazione () );

        /*
         * ElencoRPT elencoRPT = new ElencoRPT (); elencoRPT.getRptdata ().add ( rptData ); return elencoRPT;
         */
        return rptData;
    }

    private Pagamento costruisciPagamento ( PagamentoMarcaDaBolloChiamanteEsternoInput input, TipoPagamento tipoPagamento, String iuv ) throws TassonomiaServiceException {

        Pagamento pagamento = componentiComuniIstanzaMarca ( input, tipoPagamento, iuv );
        
        DatiSpecificiRiscossioneOutput dsr = getDatiSpecificiRiscossione ( input.getCodiceFiscaleEnte (), input.getTipoPagamento () );

        List<PagamentoComponenti> listaComponenti = new ArrayList<> ();

        if ( input.getComponentiPagamento () != null && !input.getComponentiPagamento ().isEmpty () ) {
            for ( AccessoChiamanteEsternoSincronoComponentePagamentoInput componenteInput: input.getComponentiPagamento () ) {
                PagamentoComponenti componente = new PagamentoComponenti ();
                componente.setAnnoAccertamento ( componenteInput.getAnnoAccertamento () );
                componente.setCausale ( componiCausaleVersamento ( iuv, componenteInput.getImporto (), componenteInput.getCausale () ) );
                componente.setIdComponente ( null );
                componente.setImporto ( componenteInput.getImporto () );
                componente.setNumeroAccertamento ( componenteInput.getNumeroAccertamento () );
                componente.setProgressivo ( componenteInput.getProgressivo () );
                componente.setUtenteUltimaModifica ( input.getCodiceFiscalePartitaIVAPagatore () );
                inserisciDatoSpecificoRiscossioneInComponente ( componente, dsr );

                listaComponenti.add ( componente );
            }
        } else {
            listaComponenti
                .add ( creaComponenteDiDefault ( dsr, componiCausaleVersamento ( iuv, input.getImporto (), input.getCausale () ), input.getImporto (),
                    input.getIdentificativoPagamento () ) );
        }

        pagamento.setComponenti ( listaComponenti );
        pagamento = salvataggioPagamento ( iuv, pagamento );

        return pagamento;
    }

    private Pagamento costruisciPagamentoMarcaBollo ( PagamentoMarcaDaBolloChiamanteEsternoInput input, TipoPagamento tipoPagamento, String iuv ) throws TassonomiaServiceException {

        DatiSpecificiRiscossioneOutput dsr = getDatiSpecificiRiscossione ( input.getCodiceFiscaleEnte (), tipoPagamento.getCodiceVersamento () );
        
        Pagamento pagamento = componentiComuniIstanzaMarca ( input, tipoPagamento, iuv );

        //per il bollo l'importo viene riacalcolato
        pagamento.setImporto ( input.getImportoBollo () );
        pagamento.getComponenti ().add ( creaComponenteDiDefault ( dsr, input.getCausale (), input.getImportoBollo (), input.getIdentificativoPagamento () ) );
        pagamento = salvataggioPagamento ( iuv, pagamento );

        return pagamento;
    }

    private Pagamento salvataggioPagamento ( String iuv, Pagamento pagamento ) {

        pagamento = pagamentoManager.mappaPagamentoEsteso ( pagamentoManager.inserisciAndRetEntity ( pagamento ) );
        pagamentoManager.aggiornaCodiceAvviso ( pagamento.getIdPagamento (), new CodiceAvviso ( null, null, iuv, Boolean.TRUE ) );

        tracciaRegistroPagamento ( pagamento, StatoPagamento.DA_PAGARE, null );

        byte [] oldLogo = pagamento.getEnte ().getLogo ();
        Ente oldEnti = pagamento.getTipoPagamento ().getEpayTEnti ();
        pagamento.getEnte ().setLogo ( null );
        pagamento.getTipoPagamento ().setEpayTEnti ( null );
        //        log.debug ( "costruisciPagamento", "costruito pagamento : " + XmlUtil.obj2Xml ( pagamento ) );
        pagamento.getEnte ().setLogo ( oldLogo );
        pagamento.getTipoPagamento ().setEpayTEnti ( oldEnti );
        return pagamento;
    }

    private Pagamento componentiComuniIstanzaMarca ( PagamentoMarcaDaBolloChiamanteEsternoInput input, TipoPagamento tipoPagamento, String iuv ) {
        Anagrafica pagatore = new Anagrafica ();
        pagatore.setCodiceFiscale ( input.getCodiceFiscalePartitaIVAPagatore () );
        pagatore.setCognome ( input.getCognome () );
        pagatore.setEmail ( input.getEmail () );
        pagatore.setNome ( input.getNome () );
        pagatore.setRagioneSociale ( input.getRagioneSociale () );
        pagatore.setFlagPersonaFisica ( StringUtils.isEmpty ( input.getRagioneSociale () ) );

        Anagrafica inserita = anagraficaManager.inserisci ( pagatore );
        pagatore.setIdAnagrafica ( inserita.getIdAnagrafica () );
        log.debug ( "costruisciPagamento", "inserita anagrafica pagatore: " + XmlUtil.obj2Xml ( inserita ) );

        Pagamento pagamento = new Pagamento ();
        if ( Boolean.TRUE.equals ( input.getFlagSoloMarca () ) ) {
            pagamento.setCausale ( componiCausaleVersamento ( iuv, input.getImportoBollo (), tipoPagamento.getDescrizionePortale () ) );
        } else {
            pagamento.setCausale ( componiCausaleVersamento ( iuv, input.getImporto (), tipoPagamento.getDescrizionePortale () ) );
        }
        pagamento.setImporto ( input.getImporto () );
        pagamento.setNote ( "Da redirect sincrona per tipo: " + tipoPagamento.getDescrizionePortale () );
        pagamento.setPagatore ( pagatore );
        pagamento.setTipoPagamento ( tipoPagamento );
        pagamento.setConsensoPrivacy ( Boolean.TRUE );
        pagamento.setPagamentoSpontaneo ( Boolean.FALSE );
        pagamento.setIdStatoCorrente ( StatoPagamento.NON_DEFINITO.getId () );
        pagamento.setAnnoAccertamento ( null );
        pagamento.setNumeroAccertamento ( null );
        pagamento.setIuv ( iuv );
        pagamento.setIuvRegistroVersamenti ( iuv );
        pagamento.setInizioValidita ( tipoPagamento.getInizioValidita () );
        pagamento.setFineValidita ( tipoPagamento.getFineValidita () );
        pagamento.setCodicePagamentoRifEnte ( input.getIdentificativoPagamento () );
        return pagamento;
    }

    private TracciabilitaChiamanteEsterno tracciaChiamataEsterna ( TracciabilitaChiamanteEsterno entity, PagamentoMarcaDaBolloChiamanteEsternoInput input,
        ChiamanteEsterno chiamanteEsterno, String iuv, Transazione transazione ) {

        if ( entity == null ) {
			entity = TracciabilitaChiamanteEsterno.builder ()
				.withChiamanteEsterno ( chiamanteEsterno )
				.withCodiceFiscale ( input.getCodiceFiscalePartitaIVAPagatore () )
				.withIdentificativoPagamento ( StringUtils.abbreviate ( input.getIdentificativoPagamento (), 50 ) )
				.withRemoteHost ( input.getIpChiamante () )
				.withTimestampChiamata ( input.getTimestampChiamata () )
				.withIdTransazione ( transazione != null ? transazione.getTransactionId () : null )
				.withIuv ( iuv )
				.withDescrizioneChiamante ( "pagamento bollo" ).build ();

            Long id = chiamataEsternaManager.inserisci ( entity );
            entity.setIdChiamata ( id );
            log.debug ( "tracciaChiamataEsterna", "inserita voce di tracciatura chiamata esterna : \n" + XmlUtil.obj2Xml ( entity ) );
            return entity;
        } else {

            entity.setIdTransazione ( transazione != null ? transazione.getTransactionId () : null );
            entity.setIuv ( iuv );

            try {
                chiamataEsternaManager.aggiorna ( entity );
                log.debug ( "tracciaChiamataEsterna", "aggiornata voce di tracciatura chiamata esterna : \n" + XmlUtil.obj2Xml ( entity ) );
            } catch ( IllegalAccessException | NoDataException e ) {
                throw new RuntimeException ( "Errore nell'aggiornamento della tracciabilita'", e );
            }
            return entity;
        }
    }

    private PagamentoMarcaBolloChiamanteEsternoOutput success ( PagamentoMarcaDaBolloChiamanteEsternoInput input, String codiceEsito,
        String iuv, String urlWisp ) {

        EsitoChiamataEsterna esito;
        try {
            esito = esitoChiamataEsternaManager.ricerca ( codiceEsito );
        } catch ( IllegalAccessException | NoDataException e ) {
            throw new RuntimeException ( "Esito chiamata esterna non trovato: " + codiceEsito, e );
        }

        PagamentoMarcaBolloChiamanteEsternoOutput output = new PagamentoMarcaBolloChiamanteEsternoOutput ();

        output.setCodiceEsito ( codiceEsito );
        output.setDescrizioneEsito ( esito.getDescrizione () );
        output.setIdentificativoPagamento ( input.getIdentificativoPagamento () );
        output.setUrlWisp ( urlWisp );

        return output;
    }

    private PagamentoMarcaBolloChiamanteEsternoOutput fail ( PagamentoMarcaDaBolloChiamanteEsternoInput input, String codiceEsito,
        String iuv, String dettagli, Exception err ) {

        if ( dettagli != null ) {
            log.error ( "accessoChiamanteEsternoSincrono", "Errore transazione: " + dettagli );
        }

        if ( err != null ) {
            log.error ( "accessoChiamanteEsternoSincrono", ExceptionUtils.getStackTrace ( err ) );
        }

        EsitoChiamataEsterna esito;
        try {
            esito = esitoChiamataEsternaManager.ricerca ( codiceEsito );
        } catch ( IllegalAccessException | NoDataException e ) {
            throw new RuntimeException ( "Esito chiamata esterna non trovato: " + codiceEsito, e );
        }

        tracciaFallimentoSuDB ( input, esito, iuv, dettagli, err );
        PagamentoMarcaBolloChiamanteEsternoOutput output = new PagamentoMarcaBolloChiamanteEsternoOutput ();

        output.setCodiceEsito ( codiceEsito );
        output.setDescrizioneEsito ( StringUtils.isBlank ( dettagli ) ? esito.getDescrizione () : dettagli );
        output.setIdentificativoPagamento ( input.getIdentificativoPagamento () );
        output.setUrlWisp ( null );

        return output;
    }

    private ChiamataEsternaNonValida tracciaFallimentoSuDB ( PagamentoMarcaDaBolloChiamanteEsternoInput input, EsitoChiamataEsterna esito, String iuv,
        String dettagli, Exception err ) {
        ChiamataEsternaNonValida entity = new ChiamataEsternaNonValida ();

        entity.setCodiceChiamante ( input.getCodiceChiamante () );
        entity.setCodiceFiscale ( input.getCodiceFiscalePartitaIVAPagatore () );
        entity.setDescrizioneErrore ( StringUtils.abbreviate ( StringUtils.isBlank ( dettagli ) ? esito.getDescrizione () : dettagli, 500 ) );
        entity.setIdentificativoPagamento ( StringUtils.abbreviate ( input.getIdentificativoPagamento (), 50 ) );
        entity.setIuv ( iuv );
        entity.setRemoteHost ( input.getIpChiamante () );
        entity.setTimestampChiamata ( input.getTimestampChiamata () );

        try {
            Long id = chiamataEsternaManager.inserisci ( entity );
            entity.setIdChiamata ( id );
            log.debug ( "tracciaFallimentoSuDB", "inserita voce di tracciatura fallimento chiamata esterna : \n" + XmlUtil.obj2Xml ( entity ) );

        } catch ( Exception e ) {
            throw new RuntimeException ( "Errore inserimento della tracciabilita' di errore", e );
        }
        return entity;
    }

    private Long tracciaRegistroPagamento ( Pagamento pagamento, StatoPagamento stato, TransazioneMdp transazioneMdp ) {

        if ( pagamento == null )
            return -1L;

        RegistroVersamenti registroVersamenti = new RegistroVersamenti ();
        registroVersamenti.setIdPagamento ( pagamento.getIdPagamento () );
        registroVersamenti.setRisultato ( stato.getDescrizione () );
        if ( transazioneMdp != null ) {
            registroVersamenti.setIdTransazione ( transazioneMdp.getIdTransazione () );
        }
        registroVersamenti
            .setIuv ( StringUtils.isNotEmpty ( pagamento.getIuvRegistroVersamenti () ) ? pagamento.getIuvRegistroVersamenti () : pagamento.getIuv () );
        registroVersamenti.setIdStato ( stato.getId () );
        registroVersamenti.setOrigineInserimento ( REGISTRO_VERSAMENTO_ORIGINE_INSERIMENTO );

        if ( registroVersamenti.getAnagraficaVersante () != null ) {
            Anagrafica newAnagrafica = anagraficaManager.inserisci ( registroVersamenti.getAnagraficaVersante () );
            registroVersamenti.setAnagraficaVersante ( newAnagrafica );
        }

        Long result = registroVersamentiManager.inserisci ( registroVersamenti );
        registroVersamenti.setIdRegistro ( result );

        log.debug ( "tracciaRegistroPagamento", "inserita voce di tracciatura versamento : \n" + XmlUtil.obj2Xml ( registroVersamenti ) );

        return result;
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

    @Override
    public Long inserisci ( TracciabilitaChiamanteEsternoSincrono chiamataEsternaSincrona ) throws IllegalArgumentException {
        return chiamataEsternaSincronaManager.inserisci ( chiamataEsternaSincrona );
    }

    @Override
    public Long ricercaIdentificativoPagamento ( String identificativoPagamento )
                    throws NoDataException, IllegalAccessException {
        return chiamataEsternaSincronaManager.ricercaIdentificativoPagamento ( identificativoPagamento );
    }

    @Override
    public Long aggiorna ( TracciabilitaChiamanteEsternoSincrono chiamataEsternaSincrona )
                    throws NoDataException, IllegalAccessException {
        return chiamataEsternaSincronaManager.aggiorna ( chiamataEsternaSincrona );
    }

    @Override
    public ChiamanteEsterno recuperaChiamanteEsterno ( String codiceChiamanteEsterno )
                    throws NoDataException, IllegalAccessException {
        return chiamataEsternaSincronaManager.recuperaChiamanteEsterno ( codiceChiamanteEsterno );
    }

    @Override
    public Long inserisciChiamataEsternaSincronaNonValida (
        ChiamataEsternaSincronaNonValida chiamataEsternaSincronaNonValida ) throws IllegalArgumentException {
        return chiamataEsternaSincronaManager.inserisci ( chiamataEsternaSincronaNonValida );
    }

    @Override
    public ChiamataEsternaSincronaNonValida ricercaChiamataEsternaSincronaNonValida (
        ChiamataEsternaSincronaNonValida chiamataEsternaSincronaNonValida )
                    throws NoDataException, IllegalAccessException {
        return chiamataEsternaSincronaManager.ricerca ( chiamataEsternaSincronaNonValida );
    }

    @Override
    public TracciabilitaChiamanteEsternoSincrono ricerca ( String idTransazione )
                    throws NoDataException, IllegalAccessException {
        return chiamataEsternaSincronaManager.ricerca ( idTransazione );
    }

    @Override
    public Boolean verificaAurizzazioneChiamanteEsternoSincrono (
        TracciabilitaChiamanteEsternoSincrono chiamataEsternaSincrona )
                    throws NoDataException, IllegalArgumentException {
        chiamataEsternaSincronaManager.verificaAutorizzazioneChiamanteEsternoSincrono ( chiamataEsternaSincrona );
        return null;
    }

	private DatiSpecificiRiscossioneOutput getDatiSpecificiRiscossione ( String codiceFiscaleEnte, String tipoPagamento ) {
		DatiSpecificiRiscossioneOutput dsr = null;

		try {
			TassonomiaAdapterClient client = new TassonomiaAdapterClient ( configurazioneManager );
			DatiSpecificiRiscossioneInput request = client.buildDatiSpecificiRiscossioneInput ( codiceFiscaleEnte, tipoPagamento );
			dsr = client.getDatiSpecificiRiscossione ( request );

		} catch ( TassonomiaServiceException e ) {
			log.error ( "Errore in fase di reperimento del dato specifico riscossione", e );
			throw new RuntimeException ( e.getMessage (), e );
		} catch ( Exception e ) { // DA FARE gestire correttamente eccezioni e stati
			log.error ( "Errore in fase di reperimento del dato specifico riscossione", e );
			throw new RuntimeException ( "Errore in fase di reperimento del dato specifico riscossione", e );
		}
		return dsr;
	}

    private void inserisciDatoSpecificoRiscossioneInComponente ( PagamentoComponenti componente, DatiSpecificiRiscossioneOutput dsr )
                    throws TassonomiaServiceException {
        if ( componente.getImporto () != null ) {
            //:TODO ottimizzare le performance
            Optional<DatiSpecificiRiscossione> trovato = dsr.getElencoDatiSpecifici ().stream ()
                            .filter ( new Predicate<DatiSpecificiRiscossione> () {

                                @Override
                                public boolean test ( DatiSpecificiRiscossione s ) {
                                    // Cerco il dato specifico riscossione con anno accertamento e numero accertamento per evitare che l'equals vada in errore
                                    if ( null == s.getAnnoAccertamento () && StringUtils.isEmpty ( s.getNumeroAccertamento () ) ) {
                                        return ( null == componente.getAnnoAccertamento () && StringUtils.isEmpty ( componente.getNumeroAccertamento () ) );
                                    }
                                    return s.getAnnoAccertamento ().equals ( componente.getAnnoAccertamento () ) &&
                                                    s.getNumeroAccertamento ().equals ( componente.getNumeroAccertamento () );
                                }
                            } )
                            .findFirst ();
            if ( trovato.isPresent () ) {
                componente.setDatiSpecificiRiscossione ( trovato.get ().getCodice () );
                componente.setCodiceTributo (  trovato.get ().getCodiceTributo () );
            } else {
                // Cerco il DSR di default
                Optional<DatiSpecificiRiscossione> trovatoDefault = dsr.getElencoDatiSpecifici ().stream ()
                                .filter ( new Predicate<DatiSpecificiRiscossione> () {

                                    @Override
                                    public boolean test ( DatiSpecificiRiscossione s ) {
                                        return null == s.getAnnoAccertamento () && StringUtils.isEmpty ( s.getNumeroAccertamento () );
                                    }
                                } )
                                .findFirst ();
                if ( trovatoDefault.isPresent () ) {
                    componente.setDatiSpecificiRiscossione ( trovatoDefault.get ().getCodice () );
                    componente.setCodiceTributo (  trovatoDefault.get ().getCodiceTributo () );
                } else {
                    CodiciEsito ce = CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;
                    throw new TassonomiaServiceException ( CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE.getCodice (),
                        ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) + ", per la componente con anno e numero accertamento: " + componente.getAnnoAccertamento ()
                        + " - " + componente.getNumeroAccertamento () + " non sono presenti valori su Catalogo!");
                }
            }
        }
    }

    
    /**
     * Creazione componente di default
     * 
     * @param dsr
     * @param causale
     * @param importo
     * @param identificativoPagamento
     * @return
     * @throws TassonomiaServiceException
     */
    private PagamentoComponenti creaComponenteDiDefault ( DatiSpecificiRiscossioneOutput dsr, String causale, BigDecimal importo,
        String identificativoPagamento )
                    throws TassonomiaServiceException {

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
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) + " , per la posizione debitoria: " + identificativoPagamento
                    + ", non essendo specificate componenti viene richiesto un'unico riferimento contabile, con anno e numero accertamento specificato, valido per l'anno in corso su Catalogo!" );
        } else {
            componente.setAnnoAccertamento ( listDati.get ( 0 ).getAnnoAccertamento () );
            componente.setDatiSpecificiRiscossione ( listDati.get ( 0 ).getCodice () );
            componente.setNumeroAccertamento ( listDati.get ( 0 ).getNumeroAccertamento () );
            componente.setCodiceTributo (  listDati.get ( 0 ).getCodiceTributo () );
            if ( StringUtils.isNotBlank ( causale ) ) {
                componente.setCausale ( causale );
            } else {
                componente.setCausale ( listDati.get ( 0 ).getDescrizione () );
            }
        }
        return componente;
    }
    
}

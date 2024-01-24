/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.business;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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

import it.csi.epay.epayservices.integration.db.manager.AnagraficaManager;
import it.csi.epay.epayservices.integration.db.manager.ChiamataEsternaManager;
import it.csi.epay.epayservices.integration.db.manager.ChiamataEsternaSincronaManager;
import it.csi.epay.epayservices.integration.db.manager.ConfigurazioneManager;
import it.csi.epay.epayservices.integration.db.manager.EnteManager;
import it.csi.epay.epayservices.integration.db.manager.EsitoChiamataEsternaManager;
import it.csi.epay.epayservices.integration.db.manager.PagamentoManager;
import it.csi.epay.epayservices.integration.db.manager.RegistroVersamentiManager;
import it.csi.epay.epayservices.integration.db.manager.TipoPagamentoManager;
import it.csi.epay.epayservices.integration.db.manager.TransazioneMdpManager;
import it.csi.epay.epayservices.integration.mdp.Iuv;
import it.csi.epay.epayservices.integration.mdp.MdpCore;
import it.csi.epay.epayservices.integration.mdp.MdpException;
import it.csi.epay.epayservices.interfaces.ejb.ChiamataEsternaSincronaFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.interfaces.rs.CodiciEsito;
import it.csi.epay.epayservices.interfaces.rs.TassonomiaAdapterClient;
import it.csi.epay.epayservices.interfaces.rs.exception.TassonomiaServiceException;
import it.csi.epay.epayservices.model.AccessoChiamanteEsternoSincronoComponentePagamentoInput;
import it.csi.epay.epayservices.model.AccessoChiamanteEsternoSincronoInput;
import it.csi.epay.epayservices.model.AccessoChiamanteEsternoSincronoOutput;
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
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PagamentoComponenti;
import it.csi.epay.epayservices.model.RegistroVersamenti;
import it.csi.epay.epayservices.model.StatoPagamento;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.model.TracciabilitaChiamanteEsterno;
import it.csi.epay.epayservices.model.TracciabilitaChiamanteEsternoSincrono;
import it.csi.epay.epayservices.model.TransazioneMdp;
import it.csi.epay.epayservices.utilities.LogUtil;
import it.csi.epay.epayservices.utilities.XmlUtil;
import it.csi.mdpcore.DatiAccertamentoRPT;
import it.csi.mdpcore.DatiSingoloVersamentoRPT;
import it.csi.mdpcore.DatiVersamentoRPT;
import it.csi.mdpcore.ElencoRPT;
import it.csi.mdpcore.RPTData;
import it.csi.mdpcore.SoggettoPagatore;
import it.csi.mdpcore.Transazione;


@Stateless ( name = "ChiamataEsternaSincronaFacade", mappedName = "ChiamataEsternaSincrona" )
public class ChiamataEsternaSincronaBean extends _BaseBean implements ChiamataEsternaSincronaFacade {

    private static final String REGISTRO_VERSAMENTO_ORIGINE_INSERIMENTO = "Gestionale esterno (chiamata sincrona)";

    private static final int MDP_NUMBER_RETRY = 3;

    private static LogUtil log = new LogUtil ( ChiamataEsternaSincronaBean.class );
    
    private static final int maxErrorMessageWidth = 200;
    
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
    private Iuv mdpIuv;

    @EJB
    private MdpCore mdpCore;
    
	@EJB
	private ConfigurazioneManager configurazioneManager;

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

    @TransactionAttribute ( TransactionAttributeType.REQUIRED )
    @Override
    public AccessoChiamanteEsternoSincronoOutput accessoChiamanteEsternoSincrono ( AccessoChiamanteEsternoSincronoInput input ) {
        String methodName = "accessoChiamanteEsternoSincrono";

        log.debug ( methodName, "input : " + XmlUtil.obj2Xml ( input ) );
        AccessoChiamanteEsternoSincronoOutput result;

        try {
            result = doAccessoChiamanteEsternoSincrono ( input );
        } catch ( Exception t ) {
            result = fail ( input, EsitoChiamataEsterna.ERRORE_GENERICO, null, null, t );
        }

        log.debug ( methodName, "output : " + XmlUtil.obj2Xml ( result ) );

        return result;
    }

    private AccessoChiamanteEsternoSincronoOutput doAccessoChiamanteEsternoSincrono ( AccessoChiamanteEsternoSincronoInput input ) {
        String methodName = "accessoChiamanteEsternoSincrono";

        String iuv = null;
        Transazione transazione = null;
        TransazioneMdp transazioneMdp;
        Pagamento pagamento;
        ChiamanteEsterno chiamante;
        Ente ente;
        TipoPagamento tipoPagamento;
        TracciabilitaChiamanteEsterno traccia;

        AccessoChiamanteEsternoSincronoOutput risultatoValidazione;

        risultatoValidazione = validaInput ( input );
        if ( risultatoValidazione != null ) {
            log.info ( methodName, "validazione input fallita : " + risultatoValidazione.getDescrizioneEsito () );
            return risultatoValidazione;
        }

        try {
            chiamante = chiamataEsternaManager.recuperaChiamanteEsterno ( input.getCodiceChiamante () );
            log.info ( methodName, "recuperato chiamante : " + chiamante.getCodiceChiamante () );
        } catch ( IllegalAccessException | NoDataException e1 ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_GENERICO, iuv, "Chiamante [" + input.getCodiceChiamante () + "] non trovato", e1 );
        }

        risultatoValidazione = validaChiamanteEsterno ( input, chiamante );
        if ( risultatoValidazione != null ) {
            log.info ( methodName, "validazione chiamante esterna fallita : " + risultatoValidazione.getDescrizioneEsito () );
            return risultatoValidazione;
        }

        try {
            if ( chiamataEsternaManager.ricercaIdentificativoPagamento ( input.getIdentificativoPagamento () ) > 0 ) {
                return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, iuv,
                    "Identificativo pagamento [" + input.getIdentificativoPagamento () + "] duplicato", null );
            }
        } catch ( IllegalAccessException | NoDataException e1 ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, iuv, "Errore nella verifica per l'IUP [" + input.getIdentificativoPagamento () + "]",
                e1 );
        }

        traccia = tracciaChiamataEsterna ( null, input, chiamante, iuv, transazione );

        log.info ( methodName, "inserita voce di traccia : " + traccia.getIdChiamata () );

        try {
            ente = enteManager.getByCF ( input.getCodiceFiscaleEnte () );
            log.info ( methodName, "recuperato ente : " + ente.getIdEnte () );
        } catch ( NoDataException e1 ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, iuv, "Ente [" + input.getCodiceFiscaleEnte () + "] non trovato", e1 );
        }

        List<TipoPagamento> attuali = tipoPagamentoManager.getByEnteECodiceVersamento ( ente, input.getTipoPagamento () );
        if ( attuali != null && attuali.size () < 1 ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, iuv,
                "Codice versamento [" + input.getTipoPagamento () + "] per l'ente [" + ente.getCodiceFiscale () + "] non trovato", null );
        } else if ( attuali.size () > 1 ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, iuv,
                "Codice versamento [" + input.getTipoPagamento () + "] per l'ente [" + ente.getCodiceFiscale () + "] non univoco", null );
        } else {
            tipoPagamento = attuali.get ( 0 );
            log.info ( methodName, "recuperato tipo pagamento : " + tipoPagamento.getIdTipoPagamento () );
        }

        risultatoValidazione = validaTipoPagamento ( input, tipoPagamento );
        if ( risultatoValidazione != null ) {
            log.info ( methodName, "validazione tipo pagamento fallita : " + risultatoValidazione.getDescrizioneEsito () );
            return risultatoValidazione;
        }

        risultatoValidazione = validaAutorizzazioneChiamanteTipoPagamento ( input, chiamante, tipoPagamento );
        if ( risultatoValidazione != null ) {
            log.info ( methodName, "validazione chiamante - tipo pagamento fallita : " + risultatoValidazione.getDescrizioneEsito () );
            return risultatoValidazione;
        }

        try {
            log.info ( methodName,
                "richiedo IUV per tipo pagamento : " + tipoPagamento.getIdTipoPagamento () + " con APP ID " + tipoPagamento.getIdApplicazione () );
            iuv = mdpIuv.generateNewIuv ( tipoPagamento, MDP_NUMBER_RETRY );
            log.info ( methodName, "generato IUV : " + iuv );
        } catch ( Exception e ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_GENERICO, iuv, "Errore in fase di generazione dello IUV", e );
        }

        tracciaChiamataEsterna ( traccia, input, chiamante, iuv, transazione );

        try {
            pagamento = costruisciPagamento ( input, tipoPagamento, iuv );
        } catch ( TassonomiaServiceException e1 ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, iuv, e1.getMessage (), e1 );
        }

        transazione = mdpCore.initializzaTransazione ( pagamento, MDP_NUMBER_RETRY );
        transazione.setAmount ( pagamento.getImporto ().doubleValue () );
        log.info ( methodName, "inizializzata transazione : " + transazione.getTransactionId () );

        transazioneMdp = new TransazioneMdp ();
        transazioneMdp.setIdTransazione ( transazione.getTransactionId () );
        transazioneMdp.setIuv ( pagamento.getIuvRegistroVersamenti () );
        transazioneMdpManager.inserisci ( transazioneMdp );
        log.debug ( methodName, "inserita traccia transazione : \n" + XmlUtil.obj2Xml ( transazioneMdp ) );
        // log.info ( methodName, "inserita traccia transazione : " + transazioneMdp.getIdTransazione () );

        tracciaChiamataEsterna ( traccia, input, chiamante, iuv, transazione );
        tracciaRegistroPagamento ( pagamento, StatoPagamento.TRANSAZIONE_INIZIALIZZATA, transazioneMdp );

        ElencoRPT datiRPT = costruisciRPT ( pagamento, transazione );
        log.debug ( methodName, "costruito payload RPT : " + XmlUtil.obj2Xml ( datiRPT ) );

        String urlPagamento = null;

        try {
            urlPagamento = mdpCore.paymentURL ( transazione, datiRPT, Boolean.TRUE.equals ( tipoPagamento.getFlagMultibeneficiario () ) );
            tracciaRegistroPagamento ( pagamento, StatoPagamento.TRANSAZIONE_AVVIATA, transazioneMdp );
        } catch ( MdpException e ) {

            tracciaRegistroPagamento ( pagamento, StatoPagamento.TRANSAZIONE_ERRORE, transazioneMdp );

            String message;
            if ( e.getCause () != null && e.getCause ().getMessage () != null && e.getCause ().getMessage ().contains ( "RPT duplicata" ) ) {
                message = "Il pagamento non pu\u00f2 essere effettuato perch\u00e9 risulta gi\u00e0 una transazione di pagamento in corso.";
                return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, iuv, message, e );

            } else {
                message = "Errore temporaneo di comunicazione. Si prega di riprovare pi\u00f9 tardi. Se l'errore dovesse persiste contattare l'assistenza.";
                return fail ( input, EsitoChiamataEsterna.ERRORE_GENERICO, iuv, message, e );

            }


        }

        log.info ( methodName, "url mdp pagamento : " + urlPagamento );

        return success ( input, EsitoChiamataEsterna.OPERAZIONE_COMPLETATA_CON_SUCCESSO, iuv, urlPagamento );
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

    private AccessoChiamanteEsternoSincronoOutput validaAutorizzazioneChiamanteTipoPagamento ( AccessoChiamanteEsternoSincronoInput input,
        ChiamanteEsterno chiamante, TipoPagamento tipoPagamento ) {

        try {
            if ( !chiamataEsternaSincronaManager.verificaAurizzazioneChiamanteEsternoSincrono ( chiamante.getCodiceChiamante (),
                tipoPagamento.getIdTipoPagamento () ) ) {
                return fail ( input, EsitoChiamataEsterna.ERRORE_GESTIONALE_NON_AUTORIZZATO_AL_TIPO_PAGAMENTO, null, null, null );
            }
        } catch ( IllegalArgumentException | NoDataException e1 ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_GESTIONALE_NON_AUTORIZZATO_AL_TIPO_PAGAMENTO, null,
                "Errore nella verifica delle autorizzazioni del gestionale per i tipi pagamento", e1 );
        }

        return null;
    }

    private AccessoChiamanteEsternoSincronoOutput validaTipoPagamento ( AccessoChiamanteEsternoSincronoInput input, TipoPagamento tipoPagamento ) {
        Date today = Date.from ( LocalDate.now ().atStartOfDay ( ZoneId.systemDefault () ).toInstant () );

        if ( tipoPagamento.getTipologiaPagamento () == null || !tipoPagamento.getTipologiaPagamento ().getCodice ().equals ( "REDS" ) ) {
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
        
        if (Boolean.TRUE.equals ( tipoPagamento.getFlagMultibeneficiario () ))
        {
                return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                    "Codice versamento [" + input.getTipoPagamento () + "] multibeneficiario", null );
        }
        if (tipoPagamentoManager.countByIdPagamentoSecondario ( tipoPagamento )>0)
        {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Codice versamento [" + input.getTipoPagamento () + "] multibeneficiario", null );
        }

        return null;
    }

    private AccessoChiamanteEsternoSincronoOutput validaChiamanteEsterno ( AccessoChiamanteEsternoSincronoInput input, ChiamanteEsterno chiamante ) {
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

    private AccessoChiamanteEsternoSincronoOutput validaInput ( AccessoChiamanteEsternoSincronoInput input ) {
        if ( StringUtils.isBlank ( input.getCodiceChiamante () ) ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo codiceChiamante e' obbligatorio", null );
        }
        if ( StringUtils.isBlank ( input.getPassword () ) ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo password e' obbligatorio", null );
        }
        if ( StringUtils.isBlank ( input.getCodiceFiscaleEnte () ) ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo codiceFiscaleEnte e' obbligatorio", null );
        }
        if ( StringUtils.isBlank ( input.getCausale () ) ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo causale e' obbligatorio", null );
        }
        if ( StringUtils.isBlank ( input.getTipoPagamento () ) ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo tipoPagamento e' obbligatorio", null );
        }
        if ( input.getImporto () == null ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo importo e' obbligatorio", null );
        }
        if ( input.getImporto ().compareTo ( BigDecimal.ZERO ) < 0 ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo importo non e' valido", null );
        }
        input.setImporto ( input.getImporto ().setScale ( 2 ) );

        if ( StringUtils.isBlank ( input.getRagioneSociale () ) ) {
            if ( StringUtils.isBlank ( input.getNome () ) || StringUtils.isBlank ( input.getCognome () ) ) {
                return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "E' obbligatorio indicare i campi nome e cognome, oppure ragioneSociale",
                    null );
            }
        }
        if ( StringUtils.isBlank ( input.getCodiceFiscalePartitaIVAPagatore () ) ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo codiceFiscalePartitaIVAPagatore e' obbligatorio", null );
        }

        if ( StringUtils.isBlank ( input.getIdentificativoPagamento () ) ) {
            return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo identificativoPagamento e' obbligatorio", null );
        }
        
        if (null!= input.getDataInizioValidita () && null!= input.getDataFineValidita ())
        {
            if ( input.getDataFineValidita ().before ( input.getDataInizioValidita () ) ) {
                return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "La data fine validita' e' precedente alla data inizio validita'", null );
            }
        }

        if ( input.getComponentiPagamento () != null ) {
            for ( AccessoChiamanteEsternoSincronoComponentePagamentoInput componente: input.getComponentiPagamento () ) {

                if ( componente.getProgressivo () == null ) {
                    return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo progressivo delle componenti del pagamento e' obbligatorio",
                        null );
                }
                if ( componente.getImporto () == null ) {
                    return fail ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Il campo importo delle componenti del pagamento e' obbligatorio", null );
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
            }
        }

        return null;
    }

    private ElencoRPT costruisciRPT ( Pagamento pagamento, Transazione transazione ) {

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
                    .setNumeroAccertamento (
                        componente.getNumeroAccertamento () != null ? Integer.valueOf ( componente.getNumeroAccertamento () ) : null );
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

        return elencoRPT;
    }

    private Pagamento costruisciPagamento ( AccessoChiamanteEsternoSincronoInput input, TipoPagamento tipoPagamento, String iuv ) throws TassonomiaServiceException {

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
        pagamento.setCausale ( input.getCausale () );
        pagamento.setImporto ( input.getImporto () );
        pagamento.setNote ( input.getNotePerIlPagatore () );
        pagamento.setPagatore ( pagatore );
        pagamento.setTipoPagamento ( tipoPagamento );
        pagamento.setConsensoPrivacy ( Boolean.TRUE );
        pagamento.setPagamentoSpontaneo ( Boolean.FALSE );
        pagamento.setIdStatoCorrente ( StatoPagamento.NON_DEFINITO.getId () );
        pagamento.setAnnoAccertamento ( null );
        pagamento.setNumeroAccertamento ( null );
        pagamento.setIuv ( iuv );
        pagamento.setIuvRegistroVersamenti ( iuv );
        pagamento.setInizioValidita ( input.getDataInizioValidita () );
        pagamento.setFineValidita ( input.getDataFineValidita () );
        pagamento.setDataScadenza ( input.getDataScadenza () );

        List<PagamentoComponenti> listaComponenti = new ArrayList<> ();
        
        DatiSpecificiRiscossioneOutput dsr = getDatiSpecificiRiscosssione ( input );
        
        if ( input.getComponentiPagamento () != null && !input.getComponentiPagamento ().isEmpty () ) {
            for ( AccessoChiamanteEsternoSincronoComponentePagamentoInput componenteInput: input.getComponentiPagamento () ) {
                PagamentoComponenti componente = new PagamentoComponenti ();
                componente.setAnnoAccertamento ( componenteInput.getAnnoAccertamento () );
                componente.setCausale ( componenteInput.getCausale () );
                componente.setIdComponente ( null );
                componente.setImporto ( componenteInput.getImporto () );
                componente.setNumeroAccertamento ( componenteInput.getNumeroAccertamento () );
                componente.setProgressivo ( componenteInput.getProgressivo () );
                componente.setUtenteUltimaModifica ( input.getCodiceFiscalePartitaIVAPagatore () );
                inserisciDatoSpecificoRiscossioneInComponente( componente, dsr);
                
                listaComponenti.add ( componente );
            }
        } else {
            pagamento.getComponenti ().add ( creaComponenteDiDefault ( dsr, input.getCausale (), input.getImporto (), input.getIdentificativoPagamento () ) );
        }

        pagamento.setComponenti ( listaComponenti );

        pagamento = pagamentoManager.mappaPagamentoEsteso ( pagamentoManager.inserisciAndRetEntity ( pagamento ) );

        pagamentoManager.aggiornaCodiceAvviso ( pagamento.getIdPagamento (), new CodiceAvviso ( null, null, iuv, Boolean.TRUE ) );

        tracciaRegistroPagamento ( pagamento, StatoPagamento.DA_PAGARE, null );

        byte [] oldLogo = pagamento.getEnte ().getLogo ();
        Ente oldEnti = pagamento.getTipoPagamento ().getEpayTEnti ();
        pagamento.getEnte ().setLogo ( null );
        pagamento.getTipoPagamento ().setEpayTEnti ( null );
        if ( log.isDebugEnabled () ) {
            log.debug ( "costruisciPagamento", "costruito pagamento : " + XmlUtil.obj2Xml ( pagamento ) );
        }
        pagamento.getEnte ().setLogo ( oldLogo );
        pagamento.getTipoPagamento ().setEpayTEnti ( oldEnti );

        return pagamento;
    }

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
                ce.getMessaggio ( maxErrorMessageWidth ) + " , per la posizione debitoria: " + identificativoPagamento
                    + ", non essendo specificate componenti viene richiesto un'unico riferimento contabile, con anno e numero accertamento specificato, valido per l'anno in corso su Catalogo!" );
        } else {
            componente.setAnnoAccertamento ( listDati.get ( 0 ).getAnnoAccertamento () );
            componente.setDatiSpecificiRiscossione ( listDati.get ( 0 ).getCodice () );
            componente.setNumeroAccertamento ( listDati.get ( 0 ).getNumeroAccertamento () );
            componente.setCodiceTributo ( listDati.get ( 0 ).getCodiceTributo () );
            if ( StringUtils.isNotBlank ( causale ) ) {
                componente.setCausale ( causale );
            } else {
                componente.setCausale ( listDati.get ( 0 ).getDescrizione () );
            }
        }
        return componente;
    }

	private DatiSpecificiRiscossioneOutput getDatiSpecificiRiscosssione ( AccessoChiamanteEsternoSincronoInput input ) {
		DatiSpecificiRiscossioneOutput dsr = null;
		try {
			TassonomiaAdapterClient client = new TassonomiaAdapterClient ( configurazioneManager );
			DatiSpecificiRiscossioneInput request = client.buildDatiSpecificiRiscossioneInput ( input.getCodiceFiscaleEnte (), input.getTipoPagamento () );
			dsr = client.getDatiSpecificiRiscossione ( request );

		} catch ( TassonomiaServiceException e ) { // DA FARE gestire correttamente eccezioni e stati
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
                componente.setCodiceTributo ( trovato.get ().getCodiceTributo () );
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
                    componente.setCodiceTributo ( trovatoDefault.get ().getCodiceTributo () );
                } else {
                    CodiciEsito ce = CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;
                    throw new TassonomiaServiceException ( CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE.getCodice (),
                        ce.getMessaggio ( maxErrorMessageWidth ) + ", per la componente con anno e numero accertamento: " + componente.getAnnoAccertamento ()
                        + " - " + componente.getNumeroAccertamento () + " non sono presenti valori su Catalogo!");
                }
            }
        }
    }

    private TracciabilitaChiamanteEsterno tracciaChiamataEsterna ( TracciabilitaChiamanteEsterno entity, AccessoChiamanteEsternoSincronoInput input,
        ChiamanteEsterno chiamanteEsterno, String iuv, Transazione transazione ) {

        if ( entity == null ) {
    		entity = TracciabilitaChiamanteEsterno.builder ()
				.withChiamanteEsterno ( chiamanteEsterno )
				.withCodiceFiscale ( input.getCodiceFiscalePartitaIVAPagatore () )
				.withIdentificativoPagamento ( input.getIdentificativoPagamento () )
				.withRemoteHost ( input.getIpChiamante () )
				.withTimestampChiamata ( input.getTimestampChiamata () )
				.withIdTransazione ( transazione != null ? transazione.getTransactionId () : null )
				.withIuv ( iuv ).build ();

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

    private AccessoChiamanteEsternoSincronoOutput success ( AccessoChiamanteEsternoSincronoInput input, String codiceEsito,
        String iuv, String urlWisp ) {

        EsitoChiamataEsterna esito;
        try {
            esito = esitoChiamataEsternaManager.ricerca ( codiceEsito );
        } catch ( IllegalAccessException | NoDataException e ) {
            throw new RuntimeException ( "Esito chiamata esterna non trovato: " + codiceEsito, e );
        }

        AccessoChiamanteEsternoSincronoOutput output = new AccessoChiamanteEsternoSincronoOutput ();

        output.setCodiceEsito ( codiceEsito );
        output.setDescrizioneEsito ( esito.getDescrizione () );
        output.setIdentificativoPagamento ( input.getIdentificativoPagamento () );
        output.setIuv ( iuv );
        output.setUrlWisp ( urlWisp );

        return output;
    }

    private AccessoChiamanteEsternoSincronoOutput fail ( AccessoChiamanteEsternoSincronoInput input, String codiceEsito,
        String iuv, String dettagli, Exception err ) {

        if ( dettagli != null ) {
            log.error ( "accessoChiamanteEsternoSincrono", "Errore transazione: " + dettagli );
        }

        if ( err != null ) {
            log.error ( "accessoChiamanteEsternoSincrono", err );
        }

        EsitoChiamataEsterna esito;
        try {
            esito = esitoChiamataEsternaManager.ricerca ( codiceEsito );
        } catch ( IllegalAccessException | NoDataException e ) {
            throw new RuntimeException ( "Esito chiamata esterna non trovato: " + codiceEsito, e );
        }

        tracciaFallimentoSuDB ( input, esito, iuv, dettagli, err );
        AccessoChiamanteEsternoSincronoOutput output = new AccessoChiamanteEsternoSincronoOutput ();

        output.setCodiceEsito ( codiceEsito );
        output.setDescrizioneEsito ( StringUtils.isBlank ( dettagli ) ? esito.getDescrizione () : dettagli );
        output.setIdentificativoPagamento ( input.getIdentificativoPagamento () );
        output.setIuv ( iuv );
        output.setUrlWisp ( null );

        return output;
    }

    private ChiamataEsternaNonValida tracciaFallimentoSuDB ( AccessoChiamanteEsternoSincronoInput input, EsitoChiamataEsterna esito, String iuv,
        String dettagli, Exception err ) {
        ChiamataEsternaNonValida entity = new ChiamataEsternaNonValida ();

        entity.setCodiceChiamante ( input.getCodiceChiamante () );
        entity.setCodiceFiscale ( input.getCodiceFiscalePartitaIVAPagatore () );
        entity.setDescrizioneErrore ( StringUtils.isBlank ( dettagli ) ? esito.getDescrizione () : dettagli );
        entity.setIdentificativoPagamento ( input.getIdentificativoPagamento () );
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

    //    @RequestMapping ( value = "/conclusione", method = RequestMethod.GET )
    //    public String conclusione ( HttpSession session, Model model ) {
    //        final String methodName = "conclusione";
    //        log.infoStart ( methodName );
    //
    //        setAttributeIntoModel ( Riferimento.class, model, session );
    //        Riferimento riferimento = getAttributeFromSession ( Riferimento.class, session );
    //        Pagamento pagamento = getAttributeFromSession ( Pagamento.class, session );
    //        riferimento.setIuv ( pagamento.getIuv () );
    //        model.addAttribute ( riferimento );
    //        setAttributeIntoModel ( DatiPersonali.class, model, session );
    //
    //        model.addAttribute ( "commonData", initCommonData ( null, 6, Boolean.FALSE, Boolean.FALSE, null ) );
    //
    //        //LF 21/01/2019 - RDI018/019 INIZIO
    //
    //        TracciabilitaChiamanteEsterno tracciabilitaChiamanteEsterno = getAttributeFromSession ( TracciabilitaChiamanteEsterno.class, session );
    //
    //        if ( null != tracciabilitaChiamanteEsterno && !StringUtils.isBlank ( tracciabilitaChiamanteEsterno.getChiamanteEsterno ().getUrl () ) ) {
    //
    //            Messaggi messaggi = (Messaggi) model.asMap ().get ( "messaggi" );
    //
    //            String esito = "";
    //
    //            if ( null != messaggi && null != messaggi.getFirstMessage () && null != messaggi.getFirstMessage ().getLevel () ) {
    //
    //                LevelMessage levelMessage = messaggi.getFirstMessage ().getLevel ();
    //
    //                esito = "&" + URL_RITORNO_CHIAMANTE_ESTERNO_PARAM_COD_ESITO + "=";
    //
    //                if ( LevelMessage.SUCCESS.equals ( levelMessage ) ) {
    //                    esito = esito + EsitoChiamataEsterna.OPERAZIONE_COMPLETATA_CON_SUCCESSO;
    //                } else {
    //                    esito = esito + EsitoChiamataEsterna.ERRORE_GENERICO;
    //                }
    //
    //            }
    //
    //            String url = tracciabilitaChiamanteEsterno.getChiamanteEsterno ().getUrl () + "?"
    //                + URL_RITORNO_CHIAMANTE_ESTERNO_PARAM_ID_PAGAMENTO + "="
    //                + tracciabilitaChiamanteEsterno.getIdentificativoPagamento () + "&"
    //                + URL_RITORNO_CHIAMANTE_ESTERNO_PARAM_DIGEST + "=" + tracciabilitaChiamanteEsterno.getDigest ()
    //                + esito;
    //
    //            model.addAttribute ( "urlChiamanteEsterno", url );
    //            removeAttributeFromSession ( TracciabilitaChiamanteEsterno.class, session );
    //        }
    //        //LF 21/01/2019 - RDI018/019 FINE
    //
    //        log.infoEnd ( methodName );
    //        return VIEWNAMEPAGA5;
    //    }

}

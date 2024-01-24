/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import it.csi.epay.epayservices.integration.db.entities.EpayDStatoPagamento;
import it.csi.epay.epayservices.integration.db.entities.EpayRTipoPagamentoCollegato;
import it.csi.epay.epayservices.integration.db.entities.EpayTPagamento;
import it.csi.epay.epayservices.integration.db.manager.AnagraficaManager;
import it.csi.epay.epayservices.integration.db.manager.ChiamataEsternaManager;
import it.csi.epay.epayservices.integration.db.manager.ChiamataEsternaSincronaSplitManager;
import it.csi.epay.epayservices.integration.db.manager.ConfigurazioneManager;
import it.csi.epay.epayservices.integration.db.manager.EnteManager;
import it.csi.epay.epayservices.integration.db.manager.EsitoChiamataEsternaManager;
import it.csi.epay.epayservices.integration.db.manager.PagamentoManager;
import it.csi.epay.epayservices.integration.db.manager.QuietanzaManager;
import it.csi.epay.epayservices.integration.db.manager.RegistroVersamentiManager;
import it.csi.epay.epayservices.integration.db.manager.RtManager;
import it.csi.epay.epayservices.integration.db.manager.TipoPagamentoManager;
import it.csi.epay.epayservices.integration.db.manager.TransazioneMdpManager;
import it.csi.epay.epayservices.integration.mdp.MdpCore;
import it.csi.epay.epayservices.integration.mdp.MdpException;
import it.csi.epay.epayservices.integration.mdp.MultiIuv;
import it.csi.epay.epayservices.interfaces.ejb.ChiamataEsternaSincronaSplitFacade;
import it.csi.epay.epayservices.interfaces.exception.MoreResultException;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.interfaces.exception.PosizioneDebitoriaException;
import it.csi.epay.epayservices.interfaces.exception.TestataException;
import it.csi.epay.epayservices.interfaces.rs.CodiciEsito;
import it.csi.epay.epayservices.interfaces.rs.TassonomiaAdapterClient;
import it.csi.epay.epayservices.interfaces.rs.exception.TassonomiaServiceException;
import it.csi.epay.epayservices.model.AccessoChiamanteEsternoSincronoComponentePagamentoDSRInput;
import it.csi.epay.epayservices.model.AccessoChiamanteEsternoSincronoComponentePagamentoInput;
import it.csi.epay.epayservices.model.AccessoChiamanteEsternoSincronoSplitInput;
import it.csi.epay.epayservices.model.AccessoChiamanteEsternoSincronoSplitOutput;
import it.csi.epay.epayservices.model.AggiornaPosizioneDebitoriaChiamanteEsternoInput;
import it.csi.epay.epayservices.model.AggiornaPosizioneDebitoriaChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoInput;
import it.csi.epay.epayservices.model.AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.Anagrafica;
import it.csi.epay.epayservices.model.ChiamanteEsterno;
import it.csi.epay.epayservices.model.ChiamataEsternaNonValida;
import it.csi.epay.epayservices.model.CodiceAvviso;
import it.csi.epay.epayservices.model.ComponentePagamentoEnteSecondaroInput;
import it.csi.epay.epayservices.model.ComponentiImporto;
import it.csi.epay.epayservices.model.Configurazione;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossione;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneInput;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneOutput;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.EsitoChiamataEsterna;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoInputContainer;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoOutputContainer;
import it.csi.epay.epayservices.model.GetIuvCommonChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetIuvMultibeneficiarioChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetIuvMultibeneficiarioChiamanteEsternoInputContainer;
import it.csi.epay.epayservices.model.GetIuvMultibeneficiarioChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.GetIuvMultibeneficiarioChiamanteEsternoOutputContainer;
import it.csi.epay.epayservices.model.GetRTChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetRTChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.OutputContainer;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PagamentoComponenti;
import it.csi.epay.epayservices.model.PagamentoIuvChiamanteEsternoInput;
import it.csi.epay.epayservices.model.PagamentoIuvChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.PagamentoRiferimenti;
import it.csi.epay.epayservices.model.PagamentoSecondario;
import it.csi.epay.epayservices.model.PagamentoSecondarioComponenti;
import it.csi.epay.epayservices.model.PosizioneDaAggiornare;
import it.csi.epay.epayservices.model.PosizioneMultibeneficiarioDaAggiornare;
import it.csi.epay.epayservices.model.QuietanzaEsito;
import it.csi.epay.epayservices.model.RegistroVersamenti;
import it.csi.epay.epayservices.model.Ricevuta;
import it.csi.epay.epayservices.model.RiferimentiPagamento;
import it.csi.epay.epayservices.model.Rt;
import it.csi.epay.epayservices.model.StatoPagamento;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.model.TipologiaPagamento;
import it.csi.epay.epayservices.model.TracciabilitaChiamanteEsterno;
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
import it.csi.mdpmultiiuv.IuvComplex;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 */
@Stateless ( name = "ChiamataEsternaSincronaSplitFacade", mappedName = "ChiamataEsternaSincronaSplit" )
public class ChiamataEsternaSincronaSplitBean extends _BaseBean implements ChiamataEsternaSincronaSplitFacade {

    private static final String PREFISSO_GET_R_TDA_GE = "[getRTdaGE] ";

    private static final String PREFISSO_GET_RT = PREFISSO_GET_R_TDA_GE;

    private static final String REGISTRO_VERSAMENTO_ORIGINE_INSERIMENTO = "Gestionale esterno (chiamata sincrona IUV)";

    private static final int MDP_NUMBER_RETRY = 3;

    private static final LogUtil log = new LogUtil ( ChiamataEsternaSincronaSplitBean.class );

    private static final String CREAZIONE_IUV = "CREAZIONE_IUV";

    private static final String STAMPA_RT_GE = "STAMPA_RT_GE";

    private static final String FORMATO_PDF = "PDF";

    private static final String FORMATO_XML = "XML";

    private static final Integer COD_ESITO_PAGAMENTO_NON_ESEGUITO = 1;

    static final private int maxErrorMessageWidth = 200;

    @EJB
    private ChiamataEsternaSincronaSplitManager chiamataEsternaSincronaSplitManager;

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
    MultiIuv multiIuv;

	@EJB
	private RtManager rtManager;

	@EJB
	private QuietanzaManager quietanzaManager;

    @EJB
    private MdpCore mdpCore;

    @EJB
    private ConfigurazioneManager configurazioneManager;

    @PersistenceContext
    private EntityManager entityManager;

    @TransactionAttribute ( TransactionAttributeType.REQUIRED )
    @Override
    public GetIuvChiamanteEsternoOutput getIUVChiamanteEsterno ( GetIuvChiamanteEsternoInput input ) {
        String methodName = "getIUVChiamanteEsterno";

        log.debug ( methodName, "input : " + XmlUtil.obj2Xml ( input ) );
        GetIuvChiamanteEsternoOutput result;

        try {
            result = (GetIuvChiamanteEsternoOutput) doGetIUVChiamanteEsterno ( input );
        } catch ( Exception t ) {
            String esitoFail = fail ( input, input.getCodiceFiscalePartitaIVAPagatore (),
                EsitoChiamataEsterna.ERRORE_GENERICO, null, null, null );
            result = (GetIuvChiamanteEsternoOutput) mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_GENERICO, esitoFail,
                input.getIdentificativoPagamento (), null, null );
        }

        log.debug ( methodName, "output : " + XmlUtil.obj2Xml ( result ) );

        return result;
    }

    @TransactionAttribute ( TransactionAttributeType.REQUIRED )
    @Override
    public GetIuvMultibeneficiarioChiamanteEsternoOutput getIUVMultibeneficiarioChiamanteEsterno ( GetIuvMultibeneficiarioChiamanteEsternoInput input ) {
        String methodName = "getIUVMultibeneficiarioChiamanteEsterno";

        log.debug ( methodName, "input : " + XmlUtil.obj2Xml ( input ) );
        GetIuvMultibeneficiarioChiamanteEsternoOutput result;

        try {
            result = (GetIuvMultibeneficiarioChiamanteEsternoOutput) doGetIUVChiamanteEsterno ( input );
        } catch ( Exception t ) {
            String esitoFail = fail ( input, input.getCodiceFiscalePartitaIVAPagatore (),
                EsitoChiamataEsterna.ERRORE_GENERICO, null, null, null );
            result = (GetIuvMultibeneficiarioChiamanteEsternoOutput) mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_GENERICO, esitoFail,
                input.getIdentificativoPagamento (), null, null );
        }

        log.debug ( methodName, "output : " + XmlUtil.obj2Xml ( result ) );

        return result;
    }
    
    @TransactionAttribute ( TransactionAttributeType.REQUIRED )
    @Override
    public GetIuvMultibeneficiarioChiamanteEsternoOutput getIUVMultibeneficiarioChiamanteEsternov1 ( it.csi.epay.epayservices.model.v1.GetIuvMultibeneficiarioChiamanteEsternoInput input ) {
        String methodName = "getIUVMultibeneficiarioChiamanteEsternov1";
          

        log.debug ( methodName, "input : " + XmlUtil.obj2Xml ( input ) );
        GetIuvMultibeneficiarioChiamanteEsternoOutput result;
        GetIuvMultibeneficiarioChiamanteEsternoInput inputv0 = new GetIuvMultibeneficiarioChiamanteEsternoInput ();
        
        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        mapper.map ( input, inputv0 );
        inputv0.setImportoPrincipale ( input.getImportoTotaleEntePrimario () );
        inputv0.setImportoSecondarioAltroEnte  ( input.getImportoTotaleEntiSecondari () );
        
        inputv0.setComponentiPagamentoEntiSecondari ( input.getComponentiPagamentoEntiSecondari () );
        inputv0.setComponentiPagamento  ( input.getComponentiPagamentoEntePrimario () );
        

        try {
            result = (GetIuvMultibeneficiarioChiamanteEsternoOutput) doGetIUVChiamanteEsterno ( inputv0 );
        } catch ( Exception t ) {
            String esitoFail = fail ( inputv0, inputv0.getCodiceFiscalePartitaIVAPagatore (),
                EsitoChiamataEsterna.ERRORE_GENERICO, null, null, null );
            result = (GetIuvMultibeneficiarioChiamanteEsternoOutput) mapGetIuvFailOrSuccess ( inputv0, EsitoChiamataEsterna.ERRORE_GENERICO, esitoFail,
                inputv0.getIdentificativoPagamento (), null, null );
        }

        log.debug ( methodName, "output : " + XmlUtil.obj2Xml ( result ) );

        return result;
    }

    private String recuperaTipoPagamento ( GetIuvCommonChiamanteEsternoInput input ) {
        if ( input instanceof GetIuvChiamanteEsternoInput ) {
            return ( (GetIuvChiamanteEsternoInput) input ).getTipoPagamento ();
        } else if ( input instanceof GetIuvMultibeneficiarioChiamanteEsternoInput ) {
            return ( (GetIuvMultibeneficiarioChiamanteEsternoInput) input ).getTipoPagamento ();
        }
        return null;
    }
    private AccessoChiamanteEsternoSincronoSplitOutput doGetIUVChiamanteEsterno ( GetIuvCommonChiamanteEsternoInput input ) {
        String methodName = "doGetIUVChiamanteEsterno";

        List<IuvComplex> iuvs;
        ChiamanteEsterno chiamante;
        Ente ente;
        TipoPagamento tipoPagamento;
        TipoPagamento tipoPagamentoSecondario = null;
        TracciabilitaChiamanteEsterno traccia;

        String tipoPagamentoString = recuperaTipoPagamento ( input );
        String risultatoValidazione = validaInputGetIuv ( input, tipoPagamentoString );
        if ( risultatoValidazione != null ) {
            log.warn ( methodName, "validazione input fallita : " + risultatoValidazione );
            return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), null, null );
        }

        try {
            chiamante = chiamataEsternaManager.recuperaChiamanteEsterno ( input.getCodiceChiamante () );
            log.info ( methodName, "recuperato chiamante : " + chiamante.getCodiceChiamante () );
        } catch ( IllegalAccessException | NoDataException e1 ) {
            risultatoValidazione = fail ( input, input.getCodiceFiscalePartitaIVAPagatore (),
                EsitoChiamataEsterna.ERRORE_GENERICO, null,
                "Chiamante [" + input.getCodiceChiamante () + "] non trovato", e1 );
            return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), null, null );
        }

        risultatoValidazione = validaChiamanteEsterno ( input, input.getCodiceFiscalePartitaIVAPagatore (), chiamante, "" );
        if ( risultatoValidazione != null ) {
            log.warn ( methodName, "validazione chiamante esterna fallita : " + risultatoValidazione );
            return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), null, null );
        }

        try {
            if ( !chiamataEsternaSincronaSplitManager
                            .verificaAutorizzazioneChiamanteEsternoAutorizzazioneChiamante ( chiamante.getCodiceChiamante (), CREAZIONE_IUV ) ) {
                risultatoValidazione = fail ( input, input.getCodiceFiscalePartitaIVAPagatore (),
                    EsitoChiamataEsterna.ERRORE_GESTIONALE_NON_AUTORIZZATO_AL_TIPO_PAGAMENTO, null, null, null );

                return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_GESTIONALE_NON_AUTORIZZATO_AL_TIPO_PAGAMENTO, risultatoValidazione,
                    input.getIdentificativoPagamento (), null, null );
            }
        } catch ( IllegalArgumentException | NoDataException e1 ) {
            risultatoValidazione = fail ( input, input.getCodiceFiscalePartitaIVAPagatore (),
                EsitoChiamataEsterna.ERRORE_GESTIONALE_NON_AUTORIZZATO_AL_TIPO_PAGAMENTO, null,
                "Gestionale non autorizzato a tale operazione", e1 );

            return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_GESTIONALE_NON_AUTORIZZATO_AL_TIPO_PAGAMENTO, risultatoValidazione,
                input.getIdentificativoPagamento (), null, null );
        }

        try {
            if ( chiamataEsternaManager.ricercaIdentificativoPagamento ( input.getIdentificativoPagamento () ) > 0 ) {
                risultatoValidazione = fail ( input, input.getCodiceFiscalePartitaIVAPagatore (),
                    EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                    "Identificativo pagamento [" + input.getIdentificativoPagamento () + "] duplicato", null );
                return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                    input.getIdentificativoPagamento (), null, null );
            }
        } catch ( IllegalAccessException | NoDataException e1 ) {
            risultatoValidazione = fail ( input, input.getCodiceFiscalePartitaIVAPagatore (),
                EsitoChiamataEsterna.ERRORE_GENERICO, null,
                "Errore nella verifica per l'IUP [" + input.getIdentificativoPagamento () + "]", e1 );
            return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_GENERICO, risultatoValidazione,
                input.getIdentificativoPagamento (), null, null );
        }

        traccia = tracciaChiamataEsterna ( null, input, chiamante, null, null, input.getCodiceFiscalePartitaIVAPagatore () );

        log.info ( methodName, "inserita voce di traccia : " + traccia.getIdChiamata () );

        try {
            ente = enteManager.getByCF ( input.getCodiceFiscaleEnte () );
            log.info ( methodName, "recuperato ente : " + ente.getIdEnte () );
        } catch ( NoDataException e1 ) {
            risultatoValidazione = fail ( input, input.getCodiceFiscalePartitaIVAPagatore (),
                EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Ente [" + input.getCodiceFiscaleEnte () + "] non trovato", e1 );
            return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), null, null );
        }

        List<TipoPagamento> attuali = tipoPagamentoManager.getByEnteECodiceVersamento ( ente, tipoPagamentoString );
        if ( CollectionUtils.isEmpty ( attuali ) ) {
            risultatoValidazione = fail ( input, input.getCodiceFiscalePartitaIVAPagatore (),
                EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Codice versamento [" + tipoPagamentoString
                + "] per l'ente [" + ente.getCodiceFiscale () + "] non trovato",
                null );
            return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), null, null );
        } else if ( attuali.size () > 1 ) {
            risultatoValidazione = fail ( input, input.getCodiceFiscalePartitaIVAPagatore (),
                EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, "Codice versamento [" + tipoPagamentoString
                + "] per l'ente [" + ente.getCodiceFiscale () + "] non univoco",
                null );
            return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), null, null );
        } else {
            tipoPagamento = attuali.get ( 0 );
            log.info ( methodName, "recuperato tipo pagamento : " + tipoPagamento.getIdTipoPagamento () );
        }
        risultatoValidazione = validaTipoPagamento ( input, input.getCodiceFiscalePartitaIVAPagatore (), tipoPagamentoString, tipoPagamento );
        if ( risultatoValidazione != null ) {
            log.warn ( methodName, "validazione tipo pagamento fallita : " + risultatoValidazione );
            return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), null, null );
        }

        risultatoValidazione = validaAutorizzazioneChiamanteTipoPagamento ( input, input.getCodiceFiscalePartitaIVAPagatore (), chiamante, tipoPagamento );
        if ( risultatoValidazione != null ) {
            log.warn ( methodName, "validazione chiamante - tipo pagamento fallita : " + risultatoValidazione );
            return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_GESTIONALE_NON_AUTORIZZATO_AL_TIPO_PAGAMENTO, risultatoValidazione,
                input.getIdentificativoPagamento (), null, null );
        }

        risultatoValidazione = validaComponentiPagamento ( input, false );
        if ( risultatoValidazione != null ) {
            log.warn ( methodName, "validazione componenti - pagamento fallita : " + risultatoValidazione );
            return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), null, null );
        }
        if ( input instanceof GetIuvMultibeneficiarioChiamanteEsternoInput ) {
            List<EpayRTipoPagamentoCollegato> attualiSecondarie = tipoPagamentoManager.getPagamentoSecondarioByidPagamentoPrincipale ( tipoPagamento );
            if ( CollectionUtils.isEmpty ( attualiSecondarie ) ) {
                risultatoValidazione = fail ( input, input.getCodiceFiscalePartitaIVAPagatore (),
                    EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                    "Non esiste alcun Codice Versamento secondario correttamente associato al codice versamento  [" + tipoPagamentoString
                    + "] per l'ente [" + ente.getCodiceFiscale () + "]",
                    null );
                return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                    input.getIdentificativoPagamento (), null, null );
            } else if ( attualiSecondarie.size () > 1 ) {
                risultatoValidazione = fail ( input, input.getCodiceFiscalePartitaIVAPagatore (),
                    EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                    "Il codice versamento associato al codice versamento [" + tipoPagamentoString
                    + "] per l'ente [" + ente.getCodiceFiscale () + "] non e' univoco",
                    null );
                return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                    input.getIdentificativoPagamento (), null, null );
            } else {
                tipoPagamentoSecondario = tipoPagamentoManager.getById ( attualiSecondarie.get ( 0 ).getIdTipoPagamentoSecondario () );
                log.info ( methodName, "recuperato tipo pagamento secondario: " + tipoPagamentoSecondario.getIdTipoPagamento () );
            }
            risultatoValidazione = validaTipoPagamento ( input, tipoPagamentoSecondario.getEpayTEnti ().getCodiceFiscale (),
                tipoPagamentoSecondario.getCodiceVersamento (), tipoPagamentoSecondario );
            if ( risultatoValidazione != null ) {
                log.warn ( methodName, "validazione tipo pagamento secondario fallita : " + risultatoValidazione );
                return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                    input.getIdentificativoPagamento (), null, null );
            }

            risultatoValidazione = validaComponentiPagamento ( input, true );
            if ( risultatoValidazione != null ) {
                log.warn ( methodName, "validazione componenti secondari - pagamento fallita : " + risultatoValidazione );
                return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                    input.getIdentificativoPagamento (), null, null );
            }
            risultatoValidazione = validaTotaliPagamento ( input );
            if ( risultatoValidazione != null ) {
                log.warn ( methodName, "validazione componenti secondari - pagamento fallita : " + risultatoValidazione );
                return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                    input.getIdentificativoPagamento (), null, null );
            }
        }

        try {
            log.info ( methodName, "richiedo IUV per tipo pagamento : " + tipoPagamento.getIdTipoPagamento ()
            + " con APP ID " + tipoPagamento.getIdApplicazione () );

            iuvs = multiIuv.generateNewIuv ( tipoPagamento, 1, 3 );

            if ( CollectionUtils.isEmpty ( iuvs ) ) {
                risultatoValidazione = fail ( input, input.getCodiceFiscalePartitaIVAPagatore (),
                    EsitoChiamataEsterna.ERRORE_GENERICO, null, "Nessuno IUV generato", null );
                return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_GENERICO, risultatoValidazione,
                    input.getIdentificativoPagamento (), null, null );
            } else if ( iuvs.size () > 1 ) {
                risultatoValidazione = fail ( input, input.getCodiceFiscalePartitaIVAPagatore (),
                    EsitoChiamataEsterna.ERRORE_GENERICO, null, "Generati piu' iuv del necessario", null );
                return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_GENERICO, risultatoValidazione,
                    input.getIdentificativoPagamento (), null, null );
            }
        } catch ( Exception e ) {
            risultatoValidazione = fail ( input, input.getCodiceFiscalePartitaIVAPagatore (),
                EsitoChiamataEsterna.ERRORE_GENERICO, null, "Errore in fase di generazione dello IUV", e );
            return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_GENERICO, risultatoValidazione,
                input.getIdentificativoPagamento (), null, null );
        }

        tracciaChiamataEsterna ( traccia, input, chiamante, iuvs.get ( 0 ).getIuvOttico (), null, input.getCodiceFiscalePartitaIVAPagatore () );

        CodiceAvviso codiceAvviso;
        try {
            codiceAvviso = costruisciPagamento ( input, tipoPagamento, iuvs.get ( 0 ), tipoPagamentoSecondario );
        } catch ( TassonomiaServiceException e ) {
            risultatoValidazione = fail ( input, input.getCodiceFiscalePartitaIVAPagatore (),
                EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null, e.getMessage (), e );
            return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), null, null );
        } catch ( Exception e ) {
            String msg = StringUtils.isNoneBlank ( e.getLocalizedMessage () )?e.getLocalizedMessage () : "Errore in fase di composizione del pagamento!";
            risultatoValidazione = fail ( input, input.getCodiceFiscalePartitaIVAPagatore (),
                EsitoChiamataEsterna.ERRORE_GENERICO, null, msg, e );
            return mapGetIuvFailOrSuccess ( input, EsitoChiamataEsterna.ERRORE_GENERICO, risultatoValidazione,
                input.getIdentificativoPagamento (), null, null );
        }

        EsitoChiamataEsterna esito = cercaEsitoChiamataEsterna ( EsitoChiamataEsterna.OPERAZIONE_COMPLETATA_CON_SUCCESSO );
        return mapGetIuvFailOrSuccess ( input,
            EsitoChiamataEsterna.OPERAZIONE_COMPLETATA_CON_SUCCESSO, esito.getDescrizione (),
            input.getIdentificativoPagamento (), codiceAvviso.getIuv (), codiceAvviso.toString () );

    }

    @TransactionAttribute ( TransactionAttributeType.REQUIRED )
    @Override
    public PagamentoIuvChiamanteEsternoOutput getPagamentoIUVChiamanteEsterno ( PagamentoIuvChiamanteEsternoInput input ) {
        String methodName = "getPagamentoIUVChiamanteEsterno";

        log.debug ( methodName, "input : " + XmlUtil.obj2Xml ( input ) );
        PagamentoIuvChiamanteEsternoOutput result;

        try {
            result = doGetPagamentoIUVChiamanteEsterno ( input );
        } catch ( Exception t ) {
            String descrizioneEsito = fail ( input, input.getCodiceFiscale (), EsitoChiamataEsterna.ERRORE_GENERICO, null,
                null, null );
            result = mapGetUrlPagamentoFailOrSuccess ( EsitoChiamataEsterna.ERRORE_GENERICO, descrizioneEsito,
                input.getIdentificativoPagamento (), null );
        }

        log.debug ( methodName, "output : " + XmlUtil.obj2Xml ( result ) );

        return result;
    }

    private PagamentoIuvChiamanteEsternoOutput doGetPagamentoIUVChiamanteEsterno (
        PagamentoIuvChiamanteEsternoInput input ) {
        String methodName = "doGetPagamentoIUVChiamanteEsterno";

        ChiamanteEsterno chiamante;
        Transazione transazione;
        TransazioneMdp transazioneMdp;

        // Verifica dell'input della chiamata
        String risultatoValidazione = validaInputPagamenti ( input );
        if ( risultatoValidazione != null ) {
            log.warn ( methodName, "validazione input fallita : " + risultatoValidazione );
            return mapGetUrlPagamentoFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), null );
        }

        try {
            chiamante = chiamataEsternaManager.recuperaChiamanteEsterno ( input.getCodiceChiamante () );
            //			log.info(methodName, "recuperato chiamante : " + chiamante.getCodiceChiamante());
        } catch ( IllegalAccessException | NoDataException e1 ) {
            risultatoValidazione = fail ( input, input.getCodiceFiscale (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                input.getIuv (), "Chiamante [" + input.getCodiceChiamante () + "] non trovato", e1 );
            return mapGetUrlPagamentoFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), null );

        }

        risultatoValidazione = validaChiamanteEsterno ( input, input.getCodiceFiscale (), chiamante, "" );
        if ( risultatoValidazione != null ) {
            log.warn ( methodName, "validazione chiamante esterna fallita : " + risultatoValidazione );
            return mapGetUrlPagamentoFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), null );
        }

        try {
            if ( !chiamataEsternaSincronaSplitManager
                            .verificaAutorizzazioneChiamanteEsternoAutorizzazioneChiamante ( chiamante.getCodiceChiamante (), CREAZIONE_IUV ) ) {
                risultatoValidazione = fail ( input, input.getCodiceFiscale (),
                    EsitoChiamataEsterna.ERRORE_GESTIONALE_NON_AUTORIZZATO_AL_TIPO_PAGAMENTO, null, null, null );

                return mapGetUrlPagamentoFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                    input.getIdentificativoPagamento (), null );
            }
        } catch ( IllegalArgumentException | NoDataException e1 ) {
            risultatoValidazione = fail ( input, input.getCodiceFiscale (),
                EsitoChiamataEsterna.ERRORE_GESTIONALE_NON_AUTORIZZATO_AL_TIPO_PAGAMENTO, null,
                "Gestionale non autorizzato a tale operazione", e1 );

            return mapGetUrlPagamentoFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), null );
        }

        // Verifica dello IUV e del codiceFiscale

        Pagamento pagamento = pagamentoManager.getPagamentoAttivoAndPagabile ( input.getIuv () );
        if ( pagamento == null ) {
            log.warn ( methodName,
                            "Codice Iuv riferito a pagamento effettuato, annullato, in attesa di ricevuta, non piu' valido perche' scaduto oppure non trovato." );
            risultatoValidazione = fail ( input, input.getCodiceFiscale (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                input.getIuv (),
                "Codice Iuv riferito a pagamento effettuato, annullato, in attesa di ricevuta, non piu' valido perche' scaduto oppure non trovato.", null );
            return mapGetUrlPagamentoFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), null );
        }

        if ( !StringUtils.equals ( input.getIdentificativoPagamento (), pagamento.getCodicePagamentoRifEnte () ) ) {
            risultatoValidazione = fail ( input, input.getCodiceFiscale (),
                EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, input.getIuv (),
                "Identificativo pagamento: [ " + input.getIdentificativoPagamento () + " ] non associato allo IUV", new NoDataException (), null );
            return mapGetUrlPagamentoFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), null );
        }

        if ( !pagamento.getPagatore ().getCodiceFiscale ().equalsIgnoreCase ( input.getCodiceFiscale () ) ) {
            log.warn ( methodName, "Codice fiscale del pagatore non coincidente con codice fiscale inviato" );
            risultatoValidazione = fail ( input, input.getCodiceFiscale (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                input.getIuv (), "Codice fiscale del pagatore non coincidente con codice fiscale inviato", null );
            return mapGetUrlPagamentoFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), null );
        }
        //controllo se il pagamento e' stato gia' tentato
        StatoPagamento enumStatoPagamento = StatoPagamento.findById ( pagamento.getIdStatoCorrente () );
		if ( enumStatoPagamento == StatoPagamento.ANNULLATO || /*
                                                                * enumStatoPagamento == StatoPagamento.FALLITO ||
                                                                */enumStatoPagamento == StatoPagamento.TRANSAZIONE_ERRORE ) {
            log.warn ( methodName, "Codice Iuv riferito a pagamento annullato, fallito o non piu' valido." );
            risultatoValidazione = fail ( input, input.getCodiceFiscale (), EsitoChiamataEsterna.ERRORE_GENERICO,
                input.getIuv (), "Codice Iuv riferito a pagamento annullato, fallito o non piu' valido.", null );
            return mapGetUrlPagamentoFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), null );
        }

        transazione = mdpCore.initializzaTransazione ( pagamento, MDP_NUMBER_RETRY );
        transazione.setAmount ( pagamento.getImporto ().doubleValue () );
        log.info ( methodName, "inizializzata transazione : " + transazione.getTransactionId () );

        transazioneMdp = new TransazioneMdp ();
        transazioneMdp.setIdTransazione ( transazione.getTransactionId () );
        transazioneMdp.setIuv ( pagamento.getIuv () );
        transazioneMdpManager.inserisci ( transazioneMdp );
        log.debug ( methodName, "inserita traccia transazione : \n" + XmlUtil.obj2Xml ( transazioneMdp ) );
        tracciaChiamataEsterna ( null, input, chiamante, input.getIuv (), transazione, input.getCodiceFiscale () );

        tracciaRegistroPagamento ( pagamento, StatoPagamento.TRANSAZIONE_INIZIALIZZATA, transazioneMdp );

        ElencoRPT datiRPT = costruisciRPT ( pagamento, transazione, pagamento.getTipoPagamento ().getFlagMultibeneficiario () );
        log.debug ( methodName, "costruito payload RPT : " + XmlUtil.obj2Xml ( datiRPT ) );

        try {
            String urlPagamento
                = mdpCore.paymentURL ( transazione, datiRPT, Boolean.TRUE.equals ( pagamento.getTipoPagamento ().getFlagMultibeneficiario () ) );
            tracciaRegistroPagamento ( pagamento, StatoPagamento.TRANSAZIONE_AVVIATA, transazioneMdp );

            log.info ( methodName, "url mdp pagamento : " + urlPagamento );

            EsitoChiamataEsterna esito = cercaEsitoChiamataEsterna (
                EsitoChiamataEsterna.OPERAZIONE_COMPLETATA_CON_SUCCESSO );
            return mapGetUrlPagamentoFailOrSuccess (
                EsitoChiamataEsterna.OPERAZIONE_COMPLETATA_CON_SUCCESSO, esito.getDescrizione (),
                input.getIdentificativoPagamento (), urlPagamento );
        } catch ( MdpException e ) {

            tracciaRegistroPagamento ( pagamento, StatoPagamento.TRANSAZIONE_ERRORE, transazioneMdp );

            String message;
            if ( e.getCause () != null && e.getCause ().getMessage () != null
                            && e.getCause ().getMessage ().contains ( "RPT duplicata" ) ) {
                message = "Il pagamento non puo' essere effettuato perche' risulta gia' una transazione di pagamento in corso.";
                risultatoValidazione = fail ( input, input.getCodiceFiscale (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                    input.getIuv (), message, e );
			} else {
                message = "Errore temporaneo di comunicazione. Si prega di riprovare piu' tardi. Se l'errore dovesse persiste contattare l'assistenza.";
                risultatoValidazione = fail ( input, input.getCodiceFiscale (), EsitoChiamataEsterna.ERRORE_GENERICO,
                    input.getIuv (), message, e );
			}
			return mapGetUrlPagamentoFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
				input.getIdentificativoPagamento (), null );
		}
    }

    @TransactionAttribute ( TransactionAttributeType.NOT_SUPPORTED )
    @Override
    public GetRTChiamanteEsternoOutput getRTChiamanteEsterno ( GetRTChiamanteEsternoInput input ) {
        String methodName = "getRTChiamanteEsterno";

        log.debug ( methodName, "input : " + XmlUtil.obj2Xml ( input ) );
        GetRTChiamanteEsternoOutput result;

        try {
            result = doGetRTChiamanteEsterno ( input );
        } catch ( Exception t ) {
            String esitoFail = fail ( input, input.getCodiceFiscale (),
                EsitoChiamataEsterna.ERRORE_GENERICO, input.getIuv (), null, null, PREFISSO_GET_R_TDA_GE );
            result = mapGetRTFailOrSuccess ( EsitoChiamataEsterna.ERRORE_GENERICO, esitoFail, input.getIdentificativoPagamento (),"", "", "", null, null, null );
        }

        log.debug ( methodName, "output : " + XmlUtil.obj2Xml ( result ) );

        return result;
    }

    private GetRTChiamanteEsternoOutput doGetRTChiamanteEsterno ( GetRTChiamanteEsternoInput input ) {
        String methodName = "doGetRTChiamanteEsterno";

        ChiamanteEsterno chiamante;
        Pagamento pagamento;
        RegistroVersamenti registroVersamenti;
        TracciabilitaChiamanteEsterno traccia;
        Rt rt = null;

        String risultatoValidazione = validaInputRT ( input );
        if ( risultatoValidazione != null ) {
            log.warn ( methodName, "validazione input fallita : " + risultatoValidazione );
            return mapGetRTFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), "", "", "", null, null, null );
        }

        try {
            chiamante = chiamataEsternaManager.recuperaChiamanteEsterno ( input.getCodiceChiamante () );
            log.info ( methodName, "recuperato chiamante : " + chiamante.getCodiceChiamante () );
        } catch ( IllegalAccessException | NoDataException e1 ) {
            risultatoValidazione = fail ( input, input.getCodiceFiscale (),
                EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Chiamante [" + input.getCodiceChiamante () + "] non trovato", null, PREFISSO_GET_R_TDA_GE );
            return mapGetRTFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), "", "", "", null, null, null );
        }

        risultatoValidazione = validaChiamanteEsterno ( input, input.getCodiceFiscale (), chiamante, PREFISSO_GET_RT );
        if ( risultatoValidazione != null ) {
            log.warn ( methodName, "validazione chiamante esterna fallita : " + risultatoValidazione );
            return mapGetRTFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (),"", "", "", null, null, null );
        }

        try {
            if ( !chiamataEsternaSincronaSplitManager
                            .verificaAutorizzazioneChiamanteEsternoAutorizzazioneChiamante ( chiamante.getCodiceChiamante (), STAMPA_RT_GE ) ) {
                risultatoValidazione = fail ( input, input.getCodiceFiscale (),
                    EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, "Errore. Gestionale non e' autorizzato ad effettuare la stampa RT.", null, null,
                    PREFISSO_GET_R_TDA_GE );

                return mapGetRTFailOrSuccess ( EsitoChiamataEsterna.ERRORE_GENERICO, risultatoValidazione,
                    input.getIdentificativoPagamento (), "", "", "", null, null, null );
            }
        } catch ( IllegalArgumentException | NoDataException e1 ) {
            risultatoValidazione = fail ( input, input.getCodiceFiscale (),
                EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, input.getIuv (),
                "Il Gestionale non e' autorizzato a tale operazione", null, PREFISSO_GET_R_TDA_GE );

            return mapGetRTFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), "", "", "", null, null, null );
        }

        // Controlli specifici del servizio rt

        // ERRORE PER PAGAMENTO NON TROVATO
        pagamento = pagamentoManager.getPagamentoOttimizzata ( input.getIuv () );
        if ( null == pagamento ) {
            risultatoValidazione = fail ( input, input.getCodiceFiscale (),
                EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, input.getIuv (),
                "Pagamento con iuv: [ " + input.getIuv () + " ] non presente su Sportello", null, PREFISSO_GET_R_TDA_GE );
            return mapGetRTFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (),"", "", "", null, null, null );

        }

        if ( !StringUtils.equals ( input.getIdentificativoPagamento (), pagamento.getCodicePagamentoRifEnte () ) ) {
            risultatoValidazione = fail ( input, input.getCodiceFiscale (),
                EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, input.getIuv (),
                "Identificativo pagamento: [ " + input.getIdentificativoPagamento () + " ] non associato allo IUV", new NoDataException (),
                PREFISSO_GET_R_TDA_GE );
            return mapGetRTFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), "", "", "", null, null, null );
        }

        //      ERRORE PER ANAGRAFICA PAGATORE NON TROVATA
        Anagrafica pagatore = anagraficaManager.getAnagrafica ( pagamento.getPagatore ().getIdAnagrafica () );
        if ( !input.getCodiceFiscale ().equals ( pagatore.getCodiceFiscale () ) ) {
            risultatoValidazione = fail ( input, input.getCodiceFiscale (),
                EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, input.getIuv (),
                "Pagamento con iuv : [" + input.getIuv () + " ] non associato al codice fiscale ricevuto", null, PREFISSO_GET_R_TDA_GE );
            return mapGetRTFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), "", "", "", null, null, null );
        }

        // ERRORE PER ENTE NON TROVATO
        if ( !input.getCodiceFiscaleEnte ().equals ( pagamento.getEnte ().getCodiceFiscale () ) ) {
            risultatoValidazione = fail ( input, input.getCodiceFiscale (),
                EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, input.getIuv (),
                "Pagamento con iuv : [" + input.getIuv () + "] non associato al codice fiscale ente ricevuto", null, PREFISSO_GET_R_TDA_GE );
            return mapGetRTFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), "", "", "", null, null, null );
        }

        // ERRORE ID REGISTRO NON TROVATO
        try {
            registroVersamenti = registroVersamentiManager.ricercaUltimaRegistrazioneByIdStato ( pagamento.getIdPagamento (), pagamento.getIdStatoCorrente () ); // pagamento.getIdStatoCorrente () = 4 ?
        } catch ( NoDataException e ) {
            risultatoValidazione = fail ( input, input.getCodiceFiscale (),
                EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, input.getIuv (),
                "Pagamento con iuv: [" + input.getIuv () + "] in stato: [" + pagamento.getDescStatoCorrente () + "] non presente nel registro versamenti", null,
                PREFISSO_GET_R_TDA_GE );
            return mapGetRTFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), pagamento.getDescStatoCorrente (), "", "", null, null, null );
        }

        // ERRORE RT NON TROVATA
		try {
			if ( ( FORMATO_XML.equalsIgnoreCase ( input.getFormatoRT () ) ) ) {
				rt = rtManager.ricercaRtXmlByIdRegistro ( registroVersamenti.getIdRegistro (), input.getCodiceFiscaleEnte () );
			} else {
				rt = rtManager.ricercaRtPdfByIdRegistro ( registroVersamenti.getIdRegistro (), input.getCodiceFiscaleEnte () );
			}
		} catch ( NoDataException e ) {
			log.info ( methodName, "Rt non trovata, continuo!" );
		}

		Ricevuta ricevuta = null;
		if ( rt != null ) { // se rt e' != null setto campo di output della ricevuta a 'rt' e faccio comunque il CONTROLLO FORMATO
			ricevuta = Ricevuta.RT;
		} else { // altrimenti
			if ( registroVersamenti.getEsitoRicevuto ().getIdModalitaRicezione () == 4 ) { // se = 4 -> setto l'output con FLUSSO_DI_RENDICONTAZIONE non e' da fare il CONTROLLO FORMATO
				ricevuta = Ricevuta.FLUSSO_DI_RIVERSAMENTO;
			} else {
				if ( registroVersamenti.getEsitoRicevuto ().getIdModalitaRicezione () == 5 ) { // si potrebbe trattare di una receipt, andare sulla t_quietanza_esito
					long idQuietanzaEsito = registroVersamenti.getEsitoRicevuto ().getIdQuietanzaEsito ();
					QuietanzaEsito quietanzaEsito;
					try {
						quietanzaEsito = quietanzaManager.ricercaQuietanzaById ( idQuietanzaEsito );
					} catch ( NoDataException e ) {
						log.info ( methodName, "Quietanza esito non trovata per idQuietanzaEsito = " + idQuietanzaEsito );
						risultatoValidazione = fail ( input, input.getCodiceFiscale (),
										EsitoChiamataEsterna.ERRORE_GENERICO, input.getIuv (),
										"Non esiste alcuna quietanza per il pagamento con iuv: [" + input.getIuv () + "] in stato: [" + pagamento.getDescStatoCorrente () + "]", null,
										PREFISSO_GET_R_TDA_GE );
						return mapGetRTFailOrSuccess ( EsitoChiamataEsterna.ERRORE_GENERICO, risultatoValidazione,
										input.getIdentificativoPagamento (), pagamento.getDescStatoCorrente (), "", "", null, null, null );
					}
					if ( null != quietanzaEsito ) {
						ricevuta = Ricevuta.RECEIPT;
						EsitoChiamataEsterna esito = cercaEsitoChiamataEsterna ( EsitoChiamataEsterna.OPERAZIONE_COMPLETATA_CON_SUCCESSO );
						String descrizioneEsito = esito.getDescrizione ();
						return mapGetRTFailOrSuccess ( EsitoChiamataEsterna.OPERAZIONE_COMPLETATA_CON_SUCCESSO,
										descrizioneEsito, input.getIdentificativoPagamento (), pagamento.getDescStatoCorrente (),
										input.getIuv (), registroVersamenti.getIuv (),
										FORMATO_PDF.equalsIgnoreCase ( input.getFormatoRT () ) ? quietanzaEsito.getRicevutaPdf () : null, null, ricevuta );
					}
				} else {
					risultatoValidazione = fail ( input, input.getCodiceFiscale (),
									EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, input.getIuv (),
									"Non esiste alcuna RT o quietanza per il pagamento con iuv: [" + input.getIuv () + "] in stato: [" + pagamento.getDescStatoCorrente () + "]", null,
									PREFISSO_GET_R_TDA_GE );
					return mapGetRTFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
									input.getIdentificativoPagamento (), pagamento.getDescStatoCorrente (), "", "", null, null, null );
				}
			}
		}
        // CONTROLLO FORMATO ERRORE PER STAMPA NON TROVATA NEL FORMATO RICHIESTO
        if ( rt != null && ( ( FORMATO_PDF.equalsIgnoreCase ( input.getFormatoRT () ) && null == rt.getRicevutaPdf () ) || ( FORMATO_XML.equalsIgnoreCase ( input.getFormatoRT () ) && null == rt.getRtXml () ) ) ) {
            risultatoValidazione = fail ( input, input.getCodiceFiscale (),
                EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, input.getIuv (),
                "Formato richiesto non disponibile per il pagamento con iuv : [" + input.getIuv () + "]", null, PREFISSO_GET_R_TDA_GE );
            return mapGetRTFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
                input.getIdentificativoPagamento (), pagamento.getDescStatoCorrente (), "", "", null, null, ricevuta );
        }

        EsitoChiamataEsterna esito = cercaEsitoChiamataEsterna ( EsitoChiamataEsterna.OPERAZIONE_COMPLETATA_CON_SUCCESSO );
        String descrizioneEsito = esito.getDescrizione ();
        if ( rt != null && COD_ESITO_PAGAMENTO_NON_ESEGUITO.equals ( rt.getCodEsitoPagamento () ) ) {
            descrizioneEsito = "Pagamento con iuv: [" + input.getIuv () + "] in stato: [" + pagamento.getDescStatoCorrente () + "] con RT negativa";
        }
        traccia = tracciaChiamataEsterna ( null, input, chiamante, input.getIuv (), null, input.getCodiceFiscale () );
        log.info ( methodName, "inserita voce di traccia : " + traccia.getIdChiamata () );

		if ( rt == null ) {
			return mapGetRTFailOrSuccess ( EsitoChiamataEsterna.OPERAZIONE_COMPLETATA_CON_SUCCESSO,
							descrizioneEsito, input.getIdentificativoPagamento (), pagamento.getDescStatoCorrente (),
							input.getIuv (), registroVersamenti.getIuv (), null, null, ricevuta );
		} else {
			return mapGetRTFailOrSuccess ( EsitoChiamataEsterna.OPERAZIONE_COMPLETATA_CON_SUCCESSO,
							descrizioneEsito, input.getIdentificativoPagamento (), pagamento.getDescStatoCorrente (),
							input.getIuv (), registroVersamenti.getIuv (),
							FORMATO_PDF.equalsIgnoreCase ( input.getFormatoRT () ) ? rt.getRicevutaPdf () : null,
							FORMATO_XML.equalsIgnoreCase ( input.getFormatoRT () ) ? rt.getRtXml () : null, ricevuta );
		}
    }

    private String validaInputGetIuv ( GetIuvCommonChiamanteEsternoInput input, String tipoPagamento ) {

        validaDatiComuni ( input, input.getCodiceFiscalePartitaIVAPagatore (), "" );

        if ( StringUtils.isBlank ( input.getIdentificativoPagamento () ) ) {
            return fail ( input, input.getCodiceFiscalePartitaIVAPagatore (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Il campo identificativoPagamento e' obbligatorio", null );
        }
        if ( input.getIdentificativoPagamento ().length () > 50 ) {
            return fail ( input, input.getCodiceFiscalePartitaIVAPagatore (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Il campo identificativoPagamento deve essere minore di 50 caratteri!", null );
        }
        if ( StringUtils.isBlank ( input.getCodiceFiscaleEnte () ) ) {
            return fail ( input, input.getCodiceFiscalePartitaIVAPagatore (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Il campo codiceFiscaleEnte e' obbligatorio", null );
        }
        if ( StringUtils.isBlank ( input.getCausale () ) ) {
            return fail ( input, input.getCodiceFiscalePartitaIVAPagatore (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Il campo causale e' obbligatorio", null );
        }
        if ( StringUtils.isBlank ( tipoPagamento ) ) {
            return fail ( input, input.getCodiceFiscalePartitaIVAPagatore (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Il campo tipoPagamento e' obbligatorio", null );
        }
        if ( input instanceof GetIuvMultibeneficiarioChiamanteEsternoInput ) {
            if ( ( (GetIuvMultibeneficiarioChiamanteEsternoInput) input ).getImportoPrincipale () == null ) {
                return fail ( input, input.getCodiceFiscalePartitaIVAPagatore (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                    "Il campo importo principale e' obbligatorio", null );
            }
            if ( ( (GetIuvMultibeneficiarioChiamanteEsternoInput) input ).getImportoSecondarioAltroEnte () == null ) {
                return fail ( input, input.getCodiceFiscalePartitaIVAPagatore (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                    "Il campo importo secondario e' obbligatorio", null );
            }
            if ( ( (GetIuvMultibeneficiarioChiamanteEsternoInput) input ).getImportoPrincipale ().compareTo ( BigDecimal.ZERO ) < 0 ) {
                return fail ( input, input.getCodiceFiscalePartitaIVAPagatore (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                    "Il campo importo principale non e' valido", null );
            }
            if ( ( (GetIuvMultibeneficiarioChiamanteEsternoInput) input ).getImportoSecondarioAltroEnte ().compareTo ( BigDecimal.ZERO ) < 0 ) {
                return fail ( input, input.getCodiceFiscalePartitaIVAPagatore (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                    "Il campo importo secondario non e' valido", null );
            }
        } else {
            if ( ( (GetIuvChiamanteEsternoInput) input ).getImporto () == null ) {
                return fail ( input, input.getCodiceFiscalePartitaIVAPagatore (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                    "Il campo importo e' obbligatorio", null );
            }
            if ( ( (GetIuvChiamanteEsternoInput) input ).getImporto ().compareTo ( BigDecimal.ZERO ) < 0 ) {
                return fail ( input, input.getCodiceFiscalePartitaIVAPagatore (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                    "Il campo importo non e' valido", null );
            }
        }
        if ( StringUtils.isBlank ( input.getRagioneSociale () ) ) {
            if ( StringUtils.isBlank ( input.getNome () ) || StringUtils.isBlank ( input.getCognome () ) ) {
                return fail ( input, input.getCodiceFiscalePartitaIVAPagatore (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                    null, "E' obbligatorio indicare i campi nome e cognome, oppure ragioneSociale", null );
            }
        }
        if ( StringUtils.isBlank ( input.getCodiceFiscalePartitaIVAPagatore () ) ) {
            return fail ( input, input.getCodiceFiscalePartitaIVAPagatore (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Il campo codiceFiscalePartitaIVAPagatore e' obbligatorio", null );
        }

        if ( null != input.getDataInizioValidita () && null != input.getDataFineValidita () ) {
            if ( input.getDataFineValidita ().before ( input.getDataInizioValidita () ) ) {
                return fail ( input, input.getCodiceFiscalePartitaIVAPagatore (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                    "La data fine validita' e' precedente alla data inizio validita'", null );
            }
        }

		if ( null != input.getEmail () ) {
			    if ( !input.getEmail ().matches ( "[a-zA-Z0-9_\\.\\+\\-]+@[a-zA-Z0-9\\-]+(\\.[a-zA-Z0-9\\-]+)*" ) ) {
				return fail ( input, input.getEmail (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
					"Il formato della email non e' corretto",
					null );
			}
		}

        String retComponenti;
        if ( input instanceof GetIuvChiamanteEsternoInput ) {
            if ( ( (GetIuvChiamanteEsternoInput) input ).getComponentiPagamento () != null ) {
                for ( AccessoChiamanteEsternoSincronoComponentePagamentoInput componente: ( (GetIuvChiamanteEsternoInput) input ).getComponentiPagamento () ) {
                    retComponenti = validaInputGetIuvComponenti ( input, componente, input.getCodiceFiscalePartitaIVAPagatore () );
                    if ( retComponenti != null ) {
                        return retComponenti;
                    }
                }
            }

        } else {
            if ( ( (GetIuvMultibeneficiarioChiamanteEsternoInput) input ).getComponentiPagamento () != null ) {
                for ( AccessoChiamanteEsternoSincronoComponentePagamentoInput componente: ( (GetIuvMultibeneficiarioChiamanteEsternoInput) input )
                                .getComponentiPagamento () ) {
                    retComponenti = validaInputGetIuvComponenti ( input, componente, input.getCodiceFiscalePartitaIVAPagatore () );
                    if ( retComponenti != null ) {
                        return retComponenti;
                    }
                }
            }

        }

        if ( input instanceof GetIuvMultibeneficiarioChiamanteEsternoInput ) {
            if ( ( (GetIuvMultibeneficiarioChiamanteEsternoInput) input ).getComponentiPagamentoEntiSecondari () != null ) {
                for ( ComponentePagamentoEnteSecondaroInput componente: ( (GetIuvMultibeneficiarioChiamanteEsternoInput) input )
                                .getComponentiPagamentoEntiSecondari () ) {
                    retComponenti = validaInputGetIuvComponenti ( input, componente, input.getCodiceFiscalePartitaIVAPagatore () );
                    if ( retComponenti != null ) {
                        return retComponenti;
                    }
                }
            }
        }

        return null;
    }

    private String validaInputPagamenti ( PagamentoIuvChiamanteEsternoInput input ) {
        if ( StringUtils.isBlank ( input.getCodiceFiscale () ) ) {
            return fail ( input, input.getCodiceFiscale (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Il campo codiceFiscale e' obbligatorio", null );
        }

        validaDatiComuni ( input, input.getCodiceFiscale (), "" );

        if ( StringUtils.isBlank ( input.getIuv () ) ) {
            return fail ( input, input.getCodiceFiscale (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Il campo IUV e' obbligatorio", null );
        }
        return null;
    }

    private String validaDatiComuni ( AccessoChiamanteEsternoSincronoSplitInput input, String codiceFiscale, String prefisso ) {
        if ( StringUtils.isBlank ( input.getCodiceChiamante () ) ) {
            return fail ( input, codiceFiscale, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Il campo codiceChiamante e' obbligatorio", null, prefisso );
        }
        if ( StringUtils.isBlank ( input.getIdentificativoPagamento () ) ) {
            return fail ( input, codiceFiscale, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Il campo identificativoPagamento e' obbligatorio", null, prefisso );
        }
        return null;
    }

    private String validaInputRT ( GetRTChiamanteEsternoInput input ) {

        String validaDatiComuni = validaDatiComuni ( input, input.getCodiceFiscale (), ChiamataEsternaSincronaSplitBean.PREFISSO_GET_RT );

        if ( !StringUtils.isBlank ( validaDatiComuni ) ) {
            return validaDatiComuni;
        }

        if ( StringUtils.isBlank ( input.getCodiceFiscale () ) ) {
            return fail ( input, input.getCodiceFiscale (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Il campo codiceFiscale e' obbligatorio", null, ChiamataEsternaSincronaSplitBean.PREFISSO_GET_RT );
        }
        if ( !input.getCodiceFiscale ().matches ( "[a-zA-Z0-9]+" ) ) {
            return fail ( input, null, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Il campo codiceFiscale contiene caratteri non accettati", null, ChiamataEsternaSincronaSplitBean.PREFISSO_GET_RT );
        }

        if ( StringUtils.isBlank ( input.getIuv () ) ) {
            return fail ( input, input.getCodiceFiscale (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Il campo IUV e' obbligatorio", null, ChiamataEsternaSincronaSplitBean.PREFISSO_GET_RT );
        }
        if ( !input.getIuv ().matches ( "[a-zA-Z0-9]+" ) ) {
            return fail ( input, input.getCodiceFiscale (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Il campo iuv contiene caratteri non accettati", null, ChiamataEsternaSincronaSplitBean.PREFISSO_GET_RT );
        }

        if ( StringUtils.isBlank ( input.getFormatoRT () ) ) {
            return fail ( input, input.getCodiceFiscale (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Il campo formato RT e' obbligatorio", null, ChiamataEsternaSincronaSplitBean.PREFISSO_GET_RT );
        }

        if ( StringUtils.isBlank ( input.getCodiceFiscaleEnte () ) ) {
            return fail ( input, input.getCodiceFiscale (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Il campo codiceFiscaleEnte e' obbligatorio", null, ChiamataEsternaSincronaSplitBean.PREFISSO_GET_RT );
        }

        if ( !input.getCodiceFiscaleEnte ().matches ( "[a-zA-Z0-9]+" ) ) {
            return fail ( input, input.getCodiceFiscale (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Il campo codice fiscale ente contiene caratteri non accettati", null, ChiamataEsternaSincronaSplitBean.PREFISSO_GET_RT );
        }

        if ( StringUtils.isBlank ( input.getIdentificativoPagamento () ) ) {
            return fail ( input, input.getCodiceFiscale (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Il campo Identificativo Pagamento e' obbligatorio", null, ChiamataEsternaSincronaSplitBean.PREFISSO_GET_RT );
        }

        if ( !FORMATO_PDF.equalsIgnoreCase ( input.getFormatoRT () ) && !FORMATO_XML.equalsIgnoreCase ( input.getFormatoRT () ) ) {
            return fail ( input, input.getCodiceFiscale (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Il campo formato RT non e' valorizzato correttamente: " + input.getFormatoRT (), null, ChiamataEsternaSincronaSplitBean.PREFISSO_GET_RT );
        }

        return null;
    }

    private String validaComponentiPagamento ( GetIuvCommonChiamanteEsternoInput input,
					boolean isCompSecondario ) {
        String risultatoValidazione;
        //CSI_PAG-1889 quete prime validazioni si fanno solo per le componenti principali
		risultatoValidazione = validaTotaliComponentiPagamento ( input, isCompSecondario );

		return risultatoValidazione;
    }

    private String validaAutorizzazioneChiamanteTipoPagamento ( AccessoChiamanteEsternoSincronoSplitInput input, String codiceFiscalePartitaIVAPagatore,
        ChiamanteEsterno chiamante, TipoPagamento tipoPagamento ) {

        try {
            if ( !chiamataEsternaSincronaSplitManager.verificaAutorizzazioneChiamanteEsterno (
                chiamante.getCodiceChiamante (), tipoPagamento.getIdTipoPagamento () ) ) {
                return fail ( input, codiceFiscalePartitaIVAPagatore,
                    EsitoChiamataEsterna.ERRORE_GESTIONALE_NON_AUTORIZZATO_AL_TIPO_PAGAMENTO, null, null, null );
            }
        } catch ( IllegalArgumentException | NoDataException e1 ) {
            return fail ( input, codiceFiscalePartitaIVAPagatore,
                EsitoChiamataEsterna.ERRORE_GESTIONALE_NON_AUTORIZZATO_AL_TIPO_PAGAMENTO, null,
                "Errore nella verifica delle autorizzazioni del gestionale per i tipi pagamento", e1 );
        }

        return null;
    }

    private String validaTipoPagamento ( AccessoChiamanteEsternoSincronoSplitInput input, String codiceFiscalePartitaIVAPagatore, String tipoPagamentoStr,
        TipoPagamento tipoPagamento ) {
        Date today = Date.from ( LocalDate.now ().atStartOfDay ( ZoneId.systemDefault () ).toInstant () );

        if ( tipoPagamento.getInizioValidita () == null || tipoPagamento.getInizioValidita ().after ( today ) ) {
            return fail ( input, codiceFiscalePartitaIVAPagatore, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Codice versamento [" + tipoPagamentoStr + "] non ancora in periodo di validita'", null );
        }

        if ( tipoPagamento.getFineValidita () != null && tipoPagamento.getFineValidita ().before ( today ) ) {
            return fail ( input, codiceFiscalePartitaIVAPagatore, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Codice versamento [" + tipoPagamentoStr + "] non piu' valido", null );
        }

        if ( Boolean.TRUE.equals ( tipoPagamento.getFlagMultibeneficiario () ) && input instanceof GetIuvChiamanteEsternoInput ) {
            return fail ( input, codiceFiscalePartitaIVAPagatore, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Codice versamento [" + tipoPagamentoStr + "] multibeneficiario", null );
        }
        if ( input instanceof GetIuvChiamanteEsternoInput && tipoPagamentoManager.countByIdPagamentoSecondario ( tipoPagamento ) > 0 ) {
            return fail ( input, codiceFiscalePartitaIVAPagatore, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Codice versamento [" + tipoPagamentoStr + "] multibeneficiario", null );
        }

        return null;
    }

    private String validaChiamanteEsterno ( AccessoChiamanteEsternoSincronoSplitInput input, String codiceFiscale,
        ChiamanteEsterno chiamante, String prefisso ) {
        Date today = Date.from ( LocalDate.now ().atStartOfDay ( ZoneId.systemDefault () ).toInstant () );

        if ( chiamante.getDataInizioValidita () == null || chiamante.getDataInizioValidita ().after ( today ) ) {
            return fail ( input, codiceFiscale, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Chiamante [" + chiamante.getCodiceChiamante () + "] non ancora in periodo di validita'", null, prefisso );
        }

        if ( chiamante.getDataFineValidita () != null && chiamante.getDataFineValidita ().before ( today ) ) {
            return fail ( input, codiceFiscale, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, null,
                "Chiamante [" + chiamante.getCodiceChiamante () + "] non piu' valido", null, prefisso );
        }

        return null;
    }

    private ElencoRPT costruisciRPT ( Pagamento pagamento, Transazione transazione, Boolean multibeneficiario ) {

        //RDI-45 multibeneficiario
        if ( multibeneficiario != null && multibeneficiario ) {
			return costruisciRPTMultibeneficiario ( pagamento, transazione );
        }

        SoggettoPagatore soggettoPagatore = new SoggettoPagatore ();
        soggettoPagatore.setIdentificativoUnivocoPagatore ( pagamento.getPagatore ().getCodiceFiscale () );
        if ( pagamento.getPagatore ().getFlagPersonaFisica () != null && pagamento.getPagatore ().getFlagPersonaFisica () ) {
            soggettoPagatore.setTipoIdentificativoUnivocoPagatore ( "F" );
            soggettoPagatore.setAnagraficaPagatore ( StringUtils.substring (StringUtils.join (
                new String [] { pagamento.getPagatore ().getNome (), pagamento.getPagatore ().getCognome () }, " " ), 0, 70) );
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
            String causaleVersamento = componiCausaleVersamento ( pagamento.getIuvRegistroVersamenti (),
                pagamento.getImporto (), pagamento.getTipoPagamento ().getDescrizionePortale () );
            DatiSingoloVersamentoRPT datiSingoloVersamentoRPT = new DatiSingoloVersamentoRPT ();
            datiSingoloVersamentoRPT.setCausaleVersamento ( causaleVersamento );
            datiSingoloVersamentoRPT.setImportoSingoloVersamento ( pagamento.getImporto () );
            datiSingoloVersamentoRPT
            .setDatiSpecificiRiscossione ( pagamento.getTipoPagamento ().getDatiSpecificiRiscossione () );

            DatiAccertamentoRPT datiAccertamentoRPT = new DatiAccertamentoRPT ();
            datiAccertamentoRPT.setAnnoAccertamento ( pagamento.getAnnoAccertamento () );
            try {
                datiAccertamentoRPT.setNumeroAccertamento (
                    pagamento.getNumeroAccertamento () != null ? pagamento.getNumeroAccertamento () : null );
            } catch ( NumberFormatException nfe ) {
                throw new RuntimeException ( "Numero pagamento " + pagamento.getIdPagamento ()
                + " ha il formato del numero accertamento errato" );
            }
            datiSingoloVersamentoRPT.setDatiAccertamento ( datiAccertamentoRPT );

            datiVersamentoRpt.getDatiSingoloVersamento ().add ( datiSingoloVersamentoRPT );
        } else {
            for ( PagamentoComponenti componente: pagamento.getComponenti () ) {
                String causaleVersamento = componiCausaleVersamento ( pagamento.getIuvRegistroVersamenti (),
                    componente.getImporto (), componente.getCausale () );
                DatiSingoloVersamentoRPT datiSingoloVersamentoRPT = new DatiSingoloVersamentoRPT ();
                datiSingoloVersamentoRPT.setCausaleVersamento ( causaleVersamento );
                datiSingoloVersamentoRPT.setImportoSingoloVersamento ( componente.getImporto () );
                datiSingoloVersamentoRPT.setDatiSpecificiRiscossione ( componente.getDatiSpecificiRiscossione () );
                DatiAccertamentoRPT datiAccertamentoRPT = new DatiAccertamentoRPT ();
                datiAccertamentoRPT.setAnnoAccertamento ( componente.getAnnoAccertamento () );
                try {
                    datiAccertamentoRPT.setNumeroAccertamento ( componente.getNumeroAccertamento () != null
                                    ? Integer.valueOf ( componente.getNumeroAccertamento () )
                                                    : null );
                } catch ( NumberFormatException nfe ) {
                    throw new RuntimeException ( "Numero pagamento " + pagamento.getIdPagamento ()
                    + " ha il formato del numero accertamento errato" );
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

    private ElencoRPT costruisciRPTMultibeneficiario ( Pagamento pagamento, Transazione transazione ) {

        SoggettoPagatore soggettoPagatore = new SoggettoPagatore ();
        soggettoPagatore.setIdentificativoUnivocoPagatore ( pagamento.getPagatore ().getCodiceFiscale () );
        if ( pagamento.getPagatore ().getFlagPersonaFisica () != null && pagamento.getPagatore ().getFlagPersonaFisica () ) {
            soggettoPagatore.setTipoIdentificativoUnivocoPagatore ( "F" );
            soggettoPagatore.setAnagraficaPagatore (
                StringUtils.join ( new String [] { pagamento.getPagatore ().getNome (), pagamento.getPagatore ().getCognome () }, " " ) );
        } else {
            soggettoPagatore.setTipoIdentificativoUnivocoPagatore ( "G" );
            soggettoPagatore.setAnagraficaPagatore ( pagamento.getPagatore ().getRagioneSociale () );
        }
        if ( StringUtils.isNotBlank ( pagamento.getPagatore ().getEmail () ) ) {
            soggettoPagatore.setEMailPagatore ( pagamento.getPagatore ().getEmail () );
        }

        DatiVersamentoRPT datiVersamentoRpt = new DatiVersamentoRPT ();
        datiVersamentoRpt.setImportoTotaleDaVersare ( pagamento.getImportoPrincipale () );
        datiVersamentoRpt.setIdentificativoUnivocoVersamento ( pagamento.getIuvRegistroVersamenti () );

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

        RPTData rptData1 = new RPTData ();
        rptData1.setAutenticazioneSoggetto ( "N/A" );
        rptData1.setSoggettoPagatore ( soggettoPagatore );
        rptData1.setSoggettoVersante ( null );
        rptData1.setDatiVersamento ( datiVersamentoRpt );
        rptData1.setApplicationId ( transazione.getApplicationId () );

        ElencoRPT elencoRPT = new ElencoRPT ();
        elencoRPT.getRptdata ().add ( rptData1 );

        //recupero il pagamento secondario associato al principale
        try {
            DatiVersamentoRPT datiVersamentoRpt2 = new DatiVersamentoRPT ();
            PagamentoSecondario pagamentoSecondario = pagamentoManager.getPagamentoSecondarioByIdPagamentoPrincipale ( pagamento.getIdPagamento () );
            datiVersamentoRpt2.setImportoTotaleDaVersare ( pagamentoSecondario.getImportoComplessivo () );
            datiVersamentoRpt2.setIdentificativoUnivocoVersamento ( pagamento.getIuvRegistroVersamenti () );

            //recuperare record da epay_t_pagamento_secondario_componenti
            List<PagamentoSecondarioComponenti> pagamentoSecondarioComponenti
                = pagamentoManager.getPagamentoSecondarioComponentiById ( pagamentoSecondario.getIdPagamentoSecondario () );

            for ( PagamentoSecondarioComponenti componenteSecondaria: pagamentoSecondarioComponenti ) {
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
                            componenteSecondaria.getNumeroAccertamento () != null ? Integer.valueOf ( componenteSecondaria.getNumeroAccertamento () ) : null );
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
            return elencoRPT;
        } catch ( NoDataException | MoreResultException e ) {
            log.error ( "costruisciRPTMultibeneficiario", "Errore in fase di composizione del pagamento secondario con iuv : " + pagamento.getIuv (), e );
            throw new RuntimeException ( "Errore in fase di composizione del pagamento secondario con iuv : " + pagamento.getIuv () );
        }
    }

    private CodiceAvviso costruisciPagamento ( GetIuvCommonChiamanteEsternoInput input, TipoPagamento tipoPagamento, IuvComplex iuvComplex,
        TipoPagamento tipoPagamentoSecondario ) throws TassonomiaServiceException {
        Anagrafica pagatore = new Anagrafica ();
        pagatore.setCodiceFiscale ( input.getCodiceFiscalePartitaIVAPagatore () );
        pagatore.setCognome ( input.getCognome () );
        pagatore.setEmail ( input.getEmail () );
        pagatore.setNome ( input.getNome () );
        pagatore.setRagioneSociale ( input.getRagioneSociale () );
        pagatore.setFlagPersonaFisica ( StringUtils.isEmpty ( input.getRagioneSociale () ) );

        Anagrafica inserita = anagraficaManager.inserisci ( pagatore );
        pagatore.setIdAnagrafica ( inserita.getIdAnagrafica () );

        CodiceAvviso codiceAvviso = new CodiceAvviso ( iuvComplex.getAuxDigit (), iuvComplex.getApplicationCode (), iuvComplex.getIuvOttico () );

        Pagamento pagamento = new Pagamento ();
        pagamento.setCausale ( input.getCausale () );
        if ( input instanceof GetIuvChiamanteEsternoInput ) {
            pagamento.setImporto ( ( (GetIuvChiamanteEsternoInput) input ).getImporto () );
        } else {
            pagamento.setImporto ( ( (GetIuvMultibeneficiarioChiamanteEsternoInput) input ).getImportoTotale () );
            pagamento.setImportoPrincipale ( ( (GetIuvMultibeneficiarioChiamanteEsternoInput) input ).getImportoPrincipale () );
        }
        pagamento.setNote ( input.getNotePerIlPagatore () );
        pagamento.setPagatore ( pagatore );
        pagamento.setTipoPagamento ( tipoPagamento );
        pagamento.setConsensoPrivacy ( Boolean.TRUE );
        pagamento.setPagamentoSpontaneo ( Boolean.FALSE );
        pagamento.setIdStatoCorrente ( StatoPagamento.NON_DEFINITO.getId () );
        pagamento.setAnnoAccertamento ( null );
        pagamento.setNumeroAccertamento ( null );
        pagamento.setIuv ( codiceAvviso.getIuv () );
        pagamento.setIuvRegistroVersamenti ( codiceAvviso.getIuv () );
        pagamento.setInizioValidita ( input.getDataInizioValidita () );
        pagamento.setFineValidita ( input.getDataFineValidita () );
        pagamento.setAuxDigit ( codiceAvviso.getAuxDigit () );
        pagamento.setApplicationCode ( codiceAvviso.getApplicationCode () );
        pagamento.setCodicePagamentoRifEnte ( input.getIdentificativoPagamento () );
        pagamento.setIdentificativoDominio ( input.getCodiceFiscaleEnte () );
        pagamento.setDataScadenza ( input.getDataScadenza () );
        pagamento.setRequiresCostUpdate(input.getRequiresCostUpdate());

        List<PagamentoComponenti> listaComponenti = new ArrayList<> ();
        DatiSpecificiRiscossioneOutput dsr = getDatiSpecificiRiscossione ( input.getCodiceFiscaleEnte (), tipoPagamento.getCodiceVersamento () );
        if ( input instanceof GetIuvChiamanteEsternoInput ) {
            if ( ( (GetIuvChiamanteEsternoInput) input ).getComponentiPagamento () != null
                            && ! ( (GetIuvChiamanteEsternoInput) input ).getComponentiPagamento ().isEmpty () ) {
                for ( AccessoChiamanteEsternoSincronoComponentePagamentoInput componenteInput: ( (GetIuvChiamanteEsternoInput) input )
                                .getComponentiPagamento () ) {

                    PagamentoComponenti componente = new PagamentoComponenti ();
                    componente.setAnnoAccertamento ( componenteInput.getAnnoAccertamento () );
                    componente.setCausale ( componenteInput.getCausale () );
                    componente.setIdComponente ( null );
                    componente.setImporto ( componenteInput.getImporto () );
                    componente.setNumeroAccertamento ( componenteInput.getNumeroAccertamento () );
                    componente.setProgressivo ( componenteInput.getProgressivo () );
                    componente.setUtenteUltimaModifica ( input.getCodiceFiscalePartitaIVAPagatore () );
                    inserisciDatoSpecificoRiscossioneInComponente ( componente, dsr );
                    listaComponenti.add ( componente );

                }
            } else {
                //gestione DSR senza componenti inserisco componente di default.
                listaComponenti.add ( creaComponenteDiDefault ( dsr, input.getCausale (), pagamento.getImporto (), input.getIdentificativoPagamento () ) );
            }
        } else {
            if ( ( (GetIuvMultibeneficiarioChiamanteEsternoInput) input ).getComponentiPagamento () != null
                            && ! ( (GetIuvMultibeneficiarioChiamanteEsternoInput) input ).getComponentiPagamento ().isEmpty () ) {
                for ( AccessoChiamanteEsternoSincronoComponentePagamentoInput componenteInput: ( (GetIuvMultibeneficiarioChiamanteEsternoInput) input )
                                .getComponentiPagamento () ) {

                    PagamentoComponenti componente = new PagamentoComponenti ();
                    componente.setAnnoAccertamento ( componenteInput.getAnnoAccertamento () );
                    componente.setCausale ( componenteInput.getCausale () );
                    componente.setIdComponente ( null );
                    componente.setImporto ( componenteInput.getImporto () );
                    componente.setNumeroAccertamento ( componenteInput.getNumeroAccertamento () );
                    componente.setProgressivo ( componenteInput.getProgressivo () );
                    componente.setUtenteUltimaModifica ( input.getCodiceFiscalePartitaIVAPagatore () );
                    inserisciDatoSpecificoRiscossioneInComponente ( componente, dsr );
                    listaComponenti.add ( componente );

                }
            } else {
                //gestione DSR senza componenti inserisco componente di default.
                listaComponenti.add ( creaComponenteDiDefault ( dsr, input.getCausale (), pagamento.getImporto (), input.getIdentificativoPagamento () ) );
            }
        }

        pagamento.setComponenti ( listaComponenti );

        EpayTPagamento pagamentoEntity = pagamentoManager.inserisciAndRetEntity ( pagamento );
        pagamento = pagamentoManager.mappaPagamentoEsteso ( pagamentoEntity );
        //ricarichiamo il pagamento, altrimenti fallisce il metodo PagamentoManager.aggiornaStato.
        //        pagamento = pagamentoManager.getPagamento ( pagamentoEntity.getIdPagamento () );
        // Commentata per motivi di performance
        //        log.debug ( methodName, "inserito pagamento: " + XmlUtil.obj2Xml ( pagamento ) );

        Long idPagamentoSecondario = null;
        if ( input instanceof GetIuvMultibeneficiarioChiamanteEsternoInput ) {
            List<PagamentoComponenti> listaComponentiSecondari = new ArrayList<> ();
            PagamentoSecondario pagamentoSecondario = new PagamentoSecondario ();
            pagamentoSecondario.setIdPagamento ( pagamentoEntity.getIdPagamento () );
            pagamentoSecondario.setCausale ( tipoPagamentoSecondario.getDescrizionePortale () );
            pagamentoSecondario.setTipoPagamento ( tipoPagamentoSecondario );
            BigDecimal importoComplessivoPagamentoSecondario = BigDecimal.ZERO;
            DatiSpecificiRiscossioneOutput dsrSecondario
            = getDatiSpecificiRiscossione ( tipoPagamentoSecondario.getEpayTEnti ().getCodiceFiscale (), tipoPagamentoSecondario.getCodiceVersamento () );
            //Ci deve essere uno e uno solo componente importo secondario (vedi metodo verificaImportoSecondarioAltroEnte)
            for ( ComponentePagamentoEnteSecondaroInput componenteInput: ( (GetIuvMultibeneficiarioChiamanteEsternoInput) input )
                            .getComponentiPagamentoEntiSecondari () ) {

                PagamentoComponenti componente = new PagamentoComponenti ();
                componente.setAnnoAccertamento ( componenteInput.getAnnoAccertamento () );
                componente.setCausale ( componenteInput.getCausale () );
                //                componente.setDatiSpecificiRiscossione ( componenteInput.getDatiSpecificiRiscossione () );:TODO inserire logica di valorizzazione del DSR secondo RDI-41
                componente.setIdComponente ( null );
                componente.setImporto ( componenteInput.getImporto () );
                componente.setNumeroAccertamento ( componenteInput.getNumeroAccertamento () );
                componente.setProgressivo ( componenteInput.getProgressivo () );
                componente.setUtenteUltimaModifica ( input.getCodiceFiscalePartitaIVAPagatore () );
                inserisciDatoSpecificoRiscossioneInComponente ( componente, dsrSecondario );
                listaComponentiSecondari.add ( componente );
                importoComplessivoPagamentoSecondario = importoComplessivoPagamentoSecondario.add ( componenteInput.getImporto () );
            }
            pagamentoSecondario.setComponenti ( listaComponentiSecondari );
            pagamentoSecondario.setImportoComplessivo ( importoComplessivoPagamentoSecondario );
            pagamentoSecondario.setIdentificativoDominio ( tipoPagamentoSecondario.getEpayTEnti ().getCodiceFiscale () );
            idPagamentoSecondario = pagamentoManager.inserisciPagamentoSecondario ( pagamentoSecondario, pagamentoEntity );
        }

        tracciaRegistroPagamento ( pagamento, StatoPagamento.DA_PAGARE, null, idPagamentoSecondario );

        return codiceAvviso;
    }

    private void tracciaRegistroPagamento ( Pagamento pagamento, StatoPagamento stato, TransazioneMdp transazioneMdp ) {
		tracciaRegistroPagamento ( pagamento, stato, transazioneMdp, null );
	}

    private void tracciaRegistroPagamento ( Pagamento pagamento, StatoPagamento stato, TransazioneMdp transazioneMdp, Long idPagamentoSecondario ) {

        RegistroVersamenti registroVersamenti = new RegistroVersamenti ();
        registroVersamenti.setIdPagamento ( pagamento.getIdPagamento () );
        registroVersamenti.setIdPagamentoSecondario ( idPagamentoSecondario );
        registroVersamenti.setRisultato ( stato.getDescrizione () );
        if ( transazioneMdp != null ) {
            registroVersamenti.setIdTransazione ( transazioneMdp.getIdTransazione () );
        }
        registroVersamenti.setIuv (
            StringUtils.isNotEmpty ( pagamento.getIuvRegistroVersamenti () ) ? pagamento.getIuvRegistroVersamenti ()
                            : pagamento.getIuv () );
        registroVersamenti.setIdStato ( stato.getId () );
        registroVersamenti.setOrigineInserimento ( REGISTRO_VERSAMENTO_ORIGINE_INSERIMENTO );

        if ( registroVersamenti.getAnagraficaVersante () != null ) {
            Anagrafica newAnagrafica = anagraficaManager.inserisci ( registroVersamenti.getAnagraficaVersante () );
            registroVersamenti.setAnagraficaVersante ( newAnagrafica );
        }

        Long result = registroVersamentiManager.inserisci ( registroVersamenti );
        registroVersamenti.setIdRegistro ( result );

        log.debug ( "tracciaRegistroPagamento",
            "inserita voce di tracciatura versamento : \n" + XmlUtil.obj2Xml ( registroVersamenti ) );

	}

    private TracciabilitaChiamanteEsterno tracciaChiamataEsterna ( TracciabilitaChiamanteEsterno entity,
        AccessoChiamanteEsternoSincronoSplitInput input, ChiamanteEsterno chiamanteEsterno, String iuv,
        Transazione transazione, String codiceFiscale ) {

        if ( entity == null ) {
    		entity = TracciabilitaChiamanteEsterno.builder ()
				.withChiamanteEsterno ( chiamanteEsterno )
				.withCodiceFiscale ( codiceFiscale )
				.withIdentificativoPagamento ( StringUtils.abbreviate (input.getIdentificativoPagamento (), 50) )
				.withRemoteHost ( input.getIpChiamante () )
				.withTimestampChiamata ( input.getTimestampChiamata () )
				.withIdTransazione ( transazione != null ? transazione.getTransactionId () : null )
				.withIuv ( iuv ).build ();

            Long id = chiamataEsternaManager.inserisci ( entity );
            entity.setIdChiamata ( id );
            log.debug ( "tracciaChiamataEsterna",
                "inserita voce di tracciatura chiamata esterna : \n" + XmlUtil.obj2Xml ( entity ) );
		} else {
            entity.setIdTransazione ( transazione != null ? transazione.getTransactionId () : null );
            entity.setIuv ( iuv );

            try {
                chiamataEsternaManager.aggiorna ( entity );
                log.debug ( "tracciaChiamataEsterna",
                    "aggiornata voce di tracciatura chiamata esterna : \n" + XmlUtil.obj2Xml ( entity ) );
            } catch ( IllegalAccessException | NoDataException e ) {
                throw new RuntimeException ( "Errore nell'aggiornamento della tracciabilita'", e );
            }
		}
		return entity;
	}

    private EsitoChiamataEsterna cercaEsitoChiamataEsterna ( String codiceEsito ) {

        EsitoChiamataEsterna esito;
        try {
            esito = esitoChiamataEsternaManager.ricerca ( codiceEsito );
            log.debug ( "cercaEsitoChiamataEsterna",
                "Trovato l'esito di default della chiamata esterna : \n" + XmlUtil.obj2Xml ( esito ) );
        } catch ( IllegalAccessException | NoDataException e ) {
            throw new RuntimeException ( "Esito chiamata esterna non trovato: " + codiceEsito, e );
        }

        return esito;
    }

    private String fail ( AccessoChiamanteEsternoSincronoSplitInput input, String codiceFiscale, String codiceEsito,
        String iuv, String dettagli, Exception err ) {

        if ( dettagli != null ) {
            log.error ( "accessoChiamanteEsterno", "Errore transazione: " + dettagli );
        }

        if ( err != null ) {
            log.error ( "accessoChiamanteEsterno", ExceptionUtils.getStackTrace ( err ) );
        }

        EsitoChiamataEsterna esito = cercaEsitoChiamataEsterna ( codiceEsito );

        try {
            tracciaFallimentoSuDB ( input, codiceFiscale, esito, iuv, dettagli, "" );
            log.warn ( "fail", "Tracciata informazione dell'errore sul database" );
        } catch ( Exception e ) {
            log.error ( "accessoChiamanteEsterno", ExceptionUtils.getStackTrace ( e ) );
            return "Errore nella chiamata al servizio";
        }
        return StringUtils.isBlank ( dettagli ) ? esito.getDescrizione () : dettagli;

    }

    private String fail ( AccessoChiamanteEsternoSincronoSplitInput input, String codiceFiscale, String codiceEsito,
        String iuv, String dettagli, Exception err, String prefisso ) {

        if ( dettagli != null ) {
            log.error ( "accessoChiamanteEsterno", "Errore transazione: " + dettagli );
        }

        if ( err != null ) {
            log.error ( "accessoChiamanteEsterno", ExceptionUtils.getStackTrace ( err ) );
        }

        EsitoChiamataEsterna esito = cercaEsitoChiamataEsterna ( codiceEsito );

        try {
            tracciaFallimentoSuDB ( input, codiceFiscale, esito, iuv, dettagli, prefisso );
            log.warn ( "fail", "Tracciata informazione dell'errore sul database" );
        } catch ( Exception e ) {
            log.error ( "accessoChiamanteEsterno", ExceptionUtils.getStackTrace ( e ) );
            return "Errore nella chiamata al servizio";
        }

        return StringUtils.isBlank ( dettagli ) ? esito.getDescrizione () : dettagli;

    }

    private void tracciaFallimentoSuDB ( AccessoChiamanteEsternoSincronoSplitInput input,
        String codiceFiscale, EsitoChiamataEsterna esito, String iuv, String dettagli, String prefisso ) {

        ChiamataEsternaNonValida entity = new ChiamataEsternaNonValida ();

        entity.setCodiceChiamante ( input.getCodiceChiamante () );
        entity.setCodiceFiscale ( codiceFiscale );
        entity.setDescrizioneErrore ( StringUtils.abbreviate (StringUtils.isBlank ( dettagli ) ? prefisso + esito.getDescrizione () : prefisso + dettagli, 500) );
        entity.setIdentificativoPagamento ( StringUtils.abbreviate ( input.getIdentificativoPagamento (), 50 ));
        entity.setIuv ( iuv );
        entity.setRemoteHost ( input.getIpChiamante () );
        entity.setTimestampChiamata ( input.getTimestampChiamata () );

        try {
            Long id = chiamataEsternaManager.inserisci ( entity );
            entity.setIdChiamata ( id );
            log.debug ( "tracciaFallimentoSuDB",
                "inserita voce di tracciatura fallimento chiamata esterna : \n" + XmlUtil.obj2Xml ( entity ) );

        } catch ( Exception e ) {
            throw new RuntimeException ( "Errore inserimento della tracciabilita' di errore", e );
        }
	}

    private AccessoChiamanteEsternoSincronoSplitOutput mapGetIuvFailOrSuccess ( GetIuvCommonChiamanteEsternoInput input, String codiceEsito,
        String descrizioneEsito,
        String identificativoPagamento, String iuv, String codiceAvviso ) {

        AccessoChiamanteEsternoSincronoSplitOutput result = input instanceof GetIuvMultibeneficiarioChiamanteEsternoInput
                        ? new GetIuvMultibeneficiarioChiamanteEsternoOutput () : new GetIuvChiamanteEsternoOutput ();
                        result.setCodiceEsito ( codiceEsito );
                        result.setDescrizioneEsito ( descrizioneEsito );
                        result.setIdentificativoPagamento ( identificativoPagamento );
                        if ( input instanceof GetIuvMultibeneficiarioChiamanteEsternoInput ) {
                            ( (GetIuvMultibeneficiarioChiamanteEsternoOutput) result ).setIuv ( iuv );
                            ( (GetIuvMultibeneficiarioChiamanteEsternoOutput) result ).setCodiceAvviso ( codiceAvviso );
                        } else {
                            ( (GetIuvChiamanteEsternoOutput) result ).setIuv ( iuv );
                            ( (GetIuvChiamanteEsternoOutput) result ).setCodiceAvviso ( codiceAvviso );
                        }
                        return result;
    }

    private PagamentoIuvChiamanteEsternoOutput mapGetUrlPagamentoFailOrSuccess ( String codiceEsito,
        String descrizioneEsito, String identificativoPagamento, String urlWisp ) {

        PagamentoIuvChiamanteEsternoOutput result = new PagamentoIuvChiamanteEsternoOutput ();
        result.setCodiceEsito ( codiceEsito );
        result.setDescrizioneEsito ( descrizioneEsito );
        result.setIdentificativoPagamento ( identificativoPagamento );
        result.setUrlWisp ( urlWisp );

        return result;
    }

	private GetRTChiamanteEsternoOutput mapGetRTFailOrSuccess ( String codiceEsito, String descrizioneEsito,
					String idPagamentoGestore, String descStatoPagamento, String iuvOriginario, String iuvEffettivo,
					byte[] ricevutaPdf, byte[] rtXml, Ricevuta ricevuta ) {
		GetRTChiamanteEsternoOutput result = new GetRTChiamanteEsternoOutput ();
		result.setCodiceEsito ( codiceEsito );
		result.setDescrizioneEsito ( StringUtils.isEmpty ( descrizioneEsito ) ? null : descrizioneEsito.replace ( PREFISSO_GET_RT, "" ) );
		result.setIdentificativoPagamento ( idPagamentoGestore );
		result.setDescrizioneStatoPagamento ( descStatoPagamento );
		result.setIuvOriginario ( iuvOriginario );
		result.setIuvEffettivo ( iuvEffettivo );
		result.setRicevuta ( ricevuta );
		result.setRicevutaPdf ( ricevutaPdf );
		result.setRtXml ( rtXml );
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
    public Boolean verificaAutorizzazione ( String idTransazione, Long idTipoPagamento ) {

        try {
            TracciabilitaChiamanteEsterno tracciabilitaChiamanteEsterno = chiamataEsternaManager.ricerca ( idTransazione );
            if ( tracciabilitaChiamanteEsterno != null && tracciabilitaChiamanteEsterno.getChiamanteEsterno () != null
                            && tracciabilitaChiamanteEsterno.getChiamanteEsterno ().getCodiceChiamante () != null ) {

                return chiamataEsternaSincronaSplitManager.verificaAutorizzazioneChiamanteEsterno (
                    tracciabilitaChiamanteEsterno.getChiamanteEsterno ().getCodiceChiamante (), idTipoPagamento );
            } else {
                return false;
            }
        } catch ( NoDataException | IllegalAccessException e ) {
            return false;
        }

    }

    @Override
	public GetIuvChiamanteEsternoOutputContainer getListIUVChiamanteEsterno ( GetIuvChiamanteEsternoInputContainer input ) {
        String methodName = "getListIUVChiamanteEsterno";

        log.debug ( methodName, "input : " + XmlUtil.obj2Xml ( input ) );
		GetIuvChiamanteEsternoOutputContainer resultContainer = new GetIuvChiamanteEsternoOutputContainer ();
        List<GetIuvChiamanteEsternoOutput> result = new ArrayList<> ();
		boolean error = false;
		boolean partialOk = false;
		for ( GetIuvChiamanteEsternoInput inputSingleIUV: input.getElencoPosizioniDaInserire () ) {
            GetIuvChiamanteEsternoOutput resultSingleIUV;
            try {
                inputSingleIUV.setTipoPagamento ( input.getTipoPagamento () );
                inputSingleIUV.setCodiceFiscaleEnte ( input.getCodiceFiscaleEnte () );
                resultSingleIUV = (GetIuvChiamanteEsternoOutput) doGetIUVChiamanteEsterno ( inputSingleIUV );
				partialOk = true;
            } catch ( Exception t ) {
				error = true;
                String esitoFail = fail ( inputSingleIUV, inputSingleIUV.getCodiceFiscalePartitaIVAPagatore (),
                    EsitoChiamataEsterna.ERRORE_GENERICO, null, null, null );
                resultSingleIUV = (GetIuvChiamanteEsternoOutput) mapGetIuvFailOrSuccess ( inputSingleIUV, EsitoChiamataEsterna.ERRORE_GENERICO, esitoFail,
                    inputSingleIUV.getIdentificativoPagamento (), null, null );
            }
            result.add ( resultSingleIUV );
        }

		resultContainer.setElementiPosizioneDebitoria ( result );
		setEsito ( resultContainer, error, partialOk );

		log.debug ( methodName, "output : " + XmlUtil.obj2Xml ( resultContainer ) );

		return resultContainer;
    }

    @Override
	public GetIuvMultibeneficiarioChiamanteEsternoOutputContainer
		getListIUVMultibeneficiarioChiamanteEsterno ( GetIuvMultibeneficiarioChiamanteEsternoInputContainer input ) {
        String methodName = "getListIUVMultibeneficiarioChiamanteEsterno";

        log.debug ( methodName, "input : " + XmlUtil.obj2Xml ( input ) );
		GetIuvMultibeneficiarioChiamanteEsternoOutputContainer resultContainer = new GetIuvMultibeneficiarioChiamanteEsternoOutputContainer ();
        List<GetIuvMultibeneficiarioChiamanteEsternoOutput> result = new ArrayList<> ();
		boolean error = false;
		boolean partialOk = false;
		for ( GetIuvMultibeneficiarioChiamanteEsternoInput inputSingleIUV: input.getElencoPosizioniDaInserire () ) {
            GetIuvMultibeneficiarioChiamanteEsternoOutput resultSingleIUV;
            try {
                inputSingleIUV.setTipoPagamento ( input.getTipoPagamento () );
                inputSingleIUV.setCodiceFiscaleEnte ( input.getCodiceFiscaleEnte () );
                resultSingleIUV = (GetIuvMultibeneficiarioChiamanteEsternoOutput) doGetIUVChiamanteEsterno ( inputSingleIUV );
            } catch ( Exception t ) {
                String esitoFail = fail ( inputSingleIUV, inputSingleIUV.getCodiceFiscalePartitaIVAPagatore (),
                    EsitoChiamataEsterna.ERRORE_GENERICO, null, null, null );
                resultSingleIUV
                = (GetIuvMultibeneficiarioChiamanteEsternoOutput) mapGetIuvFailOrSuccess ( inputSingleIUV, EsitoChiamataEsterna.ERRORE_GENERICO, esitoFail,
                    inputSingleIUV.getIdentificativoPagamento (), null, null );
            }
            result.add ( resultSingleIUV );
        }

		resultContainer.setElementiPosizioneDebitoria ( result );
		setEsito ( resultContainer, error, partialOk );

        log.debug ( methodName, "output : " + XmlUtil.obj2Xml ( result ) );

		return resultContainer;
    }

	private void setEsito ( OutputContainer resultContainer, boolean error, boolean partialOk ) {
		if ( !partialOk ) {
			resultContainer.setCodiceEsito ( CodiciEsito.ESECUZIONE_TUTTA_KO.getCodice () );
			resultContainer.setDescrizioneEsito ( CodiciEsito.ESECUZIONE_TUTTA_KO.getMessaggio ( maxErrorMessageWidth ) );
		} else {
			if ( !error ) {
				resultContainer.setCodiceEsito ( CodiciEsito.ESECUZIONE_OK.getCodice () );
				resultContainer.setDescrizioneEsito ( CodiciEsito.ESECUZIONE_OK.getMessaggio ( maxErrorMessageWidth ) );
			} else {
				resultContainer.setCodiceEsito ( CodiciEsito.ESECUZIONE_PARZIALMENTE_OK.getCodice () );
				resultContainer.setDescrizioneEsito ( CodiciEsito.ESECUZIONE_PARZIALMENTE_OK.getMessaggio ( maxErrorMessageWidth ) );
			}
		}
	}

    @TransactionAttribute ( TransactionAttributeType.REQUIRED )
    @Override
    public AggiornaPosizioneDebitoriaChiamanteEsternoOutput
    aggiornaPosizioneDebitoriaChiamanteEsterno ( AggiornaPosizioneDebitoriaChiamanteEsternoInput input ) {
        String methodName = "aggiornaPosizioneDebitoriaChiamanteEsterno";

        log.debug ( methodName, "input : " + XmlUtil.obj2Xml ( input ) );
        AggiornaPosizioneDebitoriaChiamanteEsternoOutput result;

        try {
            result = doAggiornaPosizioneDebitoriaChiamanteEsterno ( input );
        } catch ( Exception t ) {
            result = mapAggiornaPosizioneFailOrSuccess ( EsitoChiamataEsterna.ERRORE_GENERICO, EsitoChiamataEsterna.ERRORE_GENERICO );
        }

        log.debug ( methodName, "output : " + XmlUtil.obj2Xml ( result ) );

        return result;
    }

    private AggiornaPosizioneDebitoriaChiamanteEsternoOutput
    doAggiornaPosizioneDebitoriaChiamanteEsterno ( AggiornaPosizioneDebitoriaChiamanteEsternoInput input ) {
        final String methodName = "doAggiornaPosizioneDebitoriaChiamanteEsterno";
        log.debugStart ( methodName );

        AggiornaPosizioneDebitoriaChiamanteEsternoOutput res = new AggiornaPosizioneDebitoriaChiamanteEsternoOutput ();
        Ente ente;
        TipoPagamento tipoPagamento;
        try {
            //recupero posizioni da aggiornare ricevute dall'input
            List<PosizioneDaAggiornare> posizioniDaAggiornare = input.getElencoPosizioniDaAggiornare ();

            if ( posizioniDaAggiornare.size () > 1 ) {
                throw new TestataException ( CodiciEsito.POSIZIONE_DEBITORIE_NUMERO_ERRORE.getCodice (),
                    CodiciEsito.POSIZIONE_DEBITORIE_NUMERO_ERRORE.getMessaggio ( maxErrorMessageWidth ) );
            }
            //Controllo esistenza dell'Ente in base al CFEnteCreditore

            ente = verificaEsistenzaEnte ( input.getCodiceFiscaleEnte () );
            log.info ( methodName, "recuperato ente : " + ente.getCodiceFiscale () );

            try {
                tipoPagamento = verificaTipoPagamento ( ente, input.getTipoPagamento (), Boolean.FALSE );
            } catch ( TestataException e1 ) {
                return mapAggiornaPosizioneFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, e1.getMessage () );
            }
            //verifica congruenza numero delle posizioni inserite

            try {
                verificaNumeroPosizioni ( input.getNumeroPosizioniDebitorie (), posizioniDaAggiornare.size () );
            } catch ( TestataException e1 ) {
                return mapAggiornaPosizioneFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, e1.getMessage () );
            }
            //            RegistroElaborazioni registroElaborazioni = inserisciRegistroElaborazioniInizio(testata, ente, tipoPagamento);
            //            List<Pagamento> pagamenti = registroElaborazioni.getPagamenti();
            //            List<RegistroElaborazioniFault> faults = registroElaborazioni.getFaults();

            //            EsitoAggiornamentoType.ElencoPosizioniDebitorieAggiornate elencoPosizioniDebitorieAggiornate
            //            = new EsitoAggiornamentoType.ElencoPosizioniDebitorieAggiornate ();
            //            List<PosizioneDebitoriaType> listaPosizioneDebitoriaResponse = elencoPosizioniDebitorieAggiornate.getPosizioneDebitoriaAggiornata ();

            //superati i controlli preliminari, ciclo sulle posizioni da aggiornare
            DatiSpecificiRiscossioneOutput dsr = null;
            for ( PosizioneDaAggiornare posizione: posizioniDaAggiornare ) {
                Pagamento pagamento;
                try {
                    verificaElementiObbligatori ( posizione );
                    try {
                        pagamento = pagamentoManager.getPagamentoPerCodRifEnte ( ente.getCodiceFiscale (), tipoPagamento.getCodiceVersamento (),
                            posizione.getIdPosizioneDebitoria () );
                        if ( pagamento == null ) {
                            throw new NoDataException ();
                        }
                    } catch ( NoDataException e ) {
                        CodiciEsito ce = CodiciEsito.PAGAMENTO_NON_TROVATO_PER_POSIZIONE_DEBITORIA;
                        throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                            ce.getMessaggio ( maxErrorMessageWidth, ente.getCodiceFiscale (),
                                tipoPagamento.getCodiceVersamento (), posizione.getIdPosizioneDebitoria () ) );
                    } catch ( MoreResultException e ) {
                        CodiciEsito ce = CodiciEsito.CODICE_ID_POSIZIONE_DEBITORIA_DUPLICATA;
                        throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                            ce.getMessaggio ( maxErrorMessageWidth, ente.getCodiceFiscale (),
                                tipoPagamento.getCodiceVersamento (), posizione.getIdPosizioneDebitoria () ) );
                    }
                    try {
                        verificaStato ( pagamento );
                    } catch ( PosizioneDebitoriaException e ) {
                        return mapAggiornaPosizioneFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, e.getMessage () );
                    }
                    switch ( posizione.getTipoAggiornamento () ) {
                    case "ANNULLAMENTO" :
                        pagamentoManager.aggiornaNote ( pagamento.getIdPagamento (), posizione.getMotivazione () );
                        pagamentoManager.aggiornaStato ( pagamento.getIdPagamento (), StatoPagamento.INVALIDATO );

                        RegistroVersamenti registroVersamenti = new RegistroVersamenti ();
                        registroVersamenti.setIdPagamento ( pagamento.getIdPagamento () );
                        registroVersamenti.setDataOperazione ( null );
                        registroVersamenti.setIdStato ( StatoPagamento.INVALIDATO.getId () );
                        registroVersamenti.setRisultato ( StatoPagamento.INVALIDATO.getDescrizione () );
                        registroVersamenti.setOrigineInserimento ( this.getClass ().getName () );
                        registroVersamentiManager.inserisci ( registroVersamenti );

                        break;
                    case "MODIFICA" :
                        if ( true ) {
                            throw new PosizioneDebitoriaException ( CodiciEsito.SERVIZIO_DISABILITATO.getCodice (),
                                CodiciEsito.SERVIZIO_DISABILITATO.getMessaggio ( maxErrorMessageWidth ) );
                        }
                        List<Date> dateValidita = verificaDataIniziFineValidita ( ente, tipoPagamento, posizione, pagamento );
                        boolean hasComponenti = verificaComponentiPagamento ( ente, tipoPagamento, posizione, pagamento.getImporto () );
                        boolean hasRiferimenti = verificaRiferimentiPagamento ( posizione );
                        verificaValiditaPagamento ( tipoPagamento, dateValidita );
                        verificaAnagrafica ( posizione.getSoggettoPagatore (), pagamento );

                        if ( StringUtils.isNoneEmpty ( posizione.getDescrizioneCausaleVersamento () ) ) {
                            pagamentoManager.aggiornaCausale ( pagamento.getIdPagamento (), posizione.getDescrizioneCausaleVersamento () );
                        }

                        if ( StringUtils.isNoneEmpty ( posizione.getMotivazione () ) ) {
                            pagamentoManager.aggiornaNote ( pagamento.getIdPagamento (), posizione.getMotivazione () );
                        }

                        if ( hasRiferimenti ) {
                            Pagamento pagamentoImporti = new Pagamento ();
                            pagamentoImporti.setIdPagamento ( pagamento.getIdPagamento () );
                            pagamentoImporti.setUtenteUltimaModifica ( "AggiornaPosizioniDebitorie" );
                            for ( RiferimentiPagamento riferimentoType: posizione.getRiferimentiPagamento () ) {
                                PagamentoRiferimenti riferimento = new PagamentoRiferimenti ();
                                riferimento.setNome ( riferimentoType.getNome () );
                                riferimento.setValore ( riferimentoType.getValore () );
                                riferimento.setUtenteUltimaModifica ( "AggiornaPosizioniDebitorie" );
                                pagamentoImporti.getRiferimenti ().add ( riferimento );
                            }
                            pagamentoManager.aggiornaRiferimenti ( pagamentoImporti );
                        }
                        pagamento.setIdPagamento ( pagamento.getIdPagamento () );
                        pagamento.setImporto ( posizione.getImportoTotale () );
                        pagamento.setUtenteUltimaModifica ( "AggiornaPosizioniDebitorie" );
                        if ( hasComponenti ) {
                            if ( null == dsr ) {
                                try {
                                    dsr = getDatiSpecificiRiscossione ( input.getCodiceFiscaleEnte (), input.getTipoPagamento () );
                                } catch ( TassonomiaServiceException e1 ) {
                                    throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, e1.getMessage () );
                                }
                            }
                            pagamento.setComponenti (new LinkedList<> ()); // Istruzione per eliminare i componenti precedenti.
                            for ( ComponentiImporto componenteImportoType: posizione.getComponentiImporto () ) {
                                if ( null != componenteImportoType.getImporto () ) {
                                    PagamentoComponenti componente = new PagamentoComponenti ();
                                    componente.setImporto ( componenteImportoType.getImporto () );
                                    componente.setCausale ( componenteImportoType.getCausaleDescrittiva () );
                                    componente.setAnnoAccertamento (
                                        componenteImportoType.getAnnoAccertamento () != null ? componenteImportoType.getAnnoAccertamento () : null );
                                    componente.setNumeroAccertamento (
                                        StringUtils.isBlank ( componenteImportoType.getNumeroAccertamento () ) ? null
                                                        : componenteImportoType.getNumeroAccertamento () );
                                    componente.setUtenteUltimaModifica ( "AggiornaPosizioniDebitorie" );
                                    inserisciDatoSpecificoRiscossioneInComponente ( componente, dsr );

                                    pagamento.getComponenti ().add ( componente );
                                }
                            }
                            pagamentoManager.aggiornaImporti ( pagamento );
                        } else if ( CollectionUtils.isEmpty ( pagamento.getComponenti () ) ) {
                            if ( null == dsr ) {
                                try {
                                    dsr = getDatiSpecificiRiscossione ( input.getCodiceFiscaleEnte (), input.getTipoPagamento () );
                                } catch ( TassonomiaServiceException e1 ) {
                                    throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, e1.getMessage () );
                                }
                            }
                            pagamento.getComponenti ().add ( creaComponenteDiDefault ( dsr, posizione.getDescrizioneCausaleVersamento (),
                                posizione.getImportoTotale (), posizione.getIdPosizioneDebitoria () ) );
                            pagamentoManager.aggiornaImporti ( pagamento );
                        } else {
                            if ( pagamento.getComponenti ().size () == 1 ) {
                                pagamento.getComponenti ().get ( 0 ).setImporto ( posizione.getImportoTotale () );
                            }
                            pagamentoManager.aggiornaImporti ( pagamento );
                        }

                        if ( posizione.getDataInizioValidita () != null || posizione.getDataFineValidita () != null || posizione.getDataScadenza () != null ) {
                            pagamentoManager.aggiornaDate2 ( pagamento.getIdPagamento (), posizione.getDataInizioValidita (), posizione.getDataFineValidita (),
                                posizione.getDataScadenza () );
                        }

                        if ( null != posizione.getSoggettoPagatore () && ( null != posizione.getSoggettoPagatore ().getPersonaFisica ()
                                        || null != posizione.getSoggettoPagatore ().getPersonaGiuridica () ) ) {
                            Anagrafica anagraficaNew = toAnagrafica ( posizione.getSoggettoPagatore () );
                            anagraficaNew.setIdAnagrafica ( pagamento.getPagatore ().getIdAnagrafica () );
                            anagraficaManager.aggiorna ( pagamento.getIdPagamento (), anagraficaNew );

                        }

                        break;
                    default :
                        CodiciEsito ce = CodiciEsito.TIPOAGGIORNAMENTO_SCONOSCIUTO;
                        throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                            ce.getMessaggio ( maxErrorMessageWidth ) );
                    }

				} catch ( TassonomiaServiceException | PosizioneDebitoriaException poe ) {
                    log.error ( methodName, "Errore relativo alla posizione " + posizione.getIdPosizioneDebitoria () );
                    log.error ( methodName, poe );
                    return mapAggiornaPosizioneFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, poe.getMessage () );
                } catch ( Exception e ) {
                    e.printStackTrace ();
                    log.error ( methodName, "Errore relativo alla posizione " + posizione.getIdPosizioneDebitoria () );
                    log.error ( methodName, e );
                    CodiciEsito ce = CodiciEsito.AGGIORNAMENTO_PAGAMENTO_FALLITO;
                    return mapAggiornaPosizioneFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                        ce.getMessaggio ( maxErrorMessageWidth ) );
                }
            }

            res.setCodice ( CodiciEsito.ESECUZIONE_OK.getCodice () );
            res.setMessaggio ( CodiciEsito.ESECUZIONE_OK.getMessaggio ( maxErrorMessageWidth ) );

        } catch ( TestataException te ) {
            log.error ( methodName, "Errore in testate", te );
            res.setCodice ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI );
            res.setMessaggio ( te.getMessage () );
        } catch ( MdpException me ) {
            log.error ( methodName, "Errore in MDP", me );
            CodiciEsito ce = CodiciEsito.MDP_SERVICES_ERRORE;
            res.setCodice ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI );
            res.setMessaggio ( StringUtils.abbreviate ( ce.getMessaggio ( maxErrorMessageWidth ), 500 ) );
        } catch ( Throwable t ) {
            log.error ( methodName, "Errore inatteso", t );
            throw t;
        }

        log.debugEnd ( methodName );
        return res;
    }

    private Ente verificaEsistenzaEnte ( String codiceFiscaleEnte ) throws TestataException {
        final String methodName = "verificaEsistenzaEnte";

        log.debugStart ( methodName );

        try {
            Ente ente = enteManager.getByCF ( codiceFiscaleEnte );
            if ( ente == null ) {
                log.debug ( methodName, "KO" );
                throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                    CodiciEsito.CF_ENTE_ERRORE.getMessaggio ( maxErrorMessageWidth, codiceFiscaleEnte ) );
            }
            log.debug ( methodName, "OK" );
            return ente;
        } catch ( NoDataException e ) {
            log.debug ( methodName, "KO" );
            CodiciEsito ce = CodiciEsito.CF_ENTE_ERRORE;
            throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( maxErrorMessageWidth, codiceFiscaleEnte ) );
        } finally {
            log.debugEnd ( methodName );
        }
    }

    private TipoPagamento verificaTipoPagamento ( Ente ente, String codiceVersamento, Boolean isMultibeneficiario ) throws TestataException {
        final String methodName = "verificaTestataTipoPagamento";

        log.debugStart ( methodName );
        List<TipoPagamento> listaTipoPagamento = tipoPagamentoManager.getByEnteECodiceVersamento ( ente, codiceVersamento );
        if ( listaTipoPagamento == null || listaTipoPagamento.isEmpty () ) {
            log.debug ( methodName, "KO1" );
            log.debugEnd ( methodName );
            CodiciEsito ce = CodiciEsito.CODICE_VERSAMENTO_NON_TROVATO_ERRORE;
            throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( maxErrorMessageWidth, codiceVersamento ) );
        }
        if ( listaTipoPagamento.size () > 1 ) {
            log.debug ( methodName, "KO2" );
            log.debugEnd ( methodName );
            CodiciEsito ce = CodiciEsito.CODICE_VERSAMENTO_NON_UNIVOCO_ERRORE;
            throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( maxErrorMessageWidth, codiceVersamento ) );
        }
        TipoPagamento tipoPagamento = listaTipoPagamento.get ( 0 );
        if ( tipoPagamento.getFineValidita () != null ) {
            if ( tipoPagamento.getFineValidita ().before ( new Date () ) ) {
                log.debug ( methodName, "KO3" );
                log.debugEnd ( methodName );
                CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_NON_ATTIVO_ERRORE;
                throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                    ce.getMessaggio ( maxErrorMessageWidth, codiceVersamento ) );
            }
        }

        if ( Boolean.TRUE.equals ( tipoPagamento.getFlagMultibeneficiario () ) && Boolean.FALSE.equals ( isMultibeneficiario ) ) {
            log.debug ( methodName, "KO5" );
            log.debugEnd ( methodName );
            CodiciEsito ce = CodiciEsito.ERRORE_TIPO_PAGAMENTO_MULTIBENEFICIARIO;
            throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( maxErrorMessageWidth, codiceVersamento ) );
        }
        if ( Boolean.FALSE.equals ( isMultibeneficiario ) && tipoPagamentoManager.countByIdPagamentoSecondario ( tipoPagamento ) > 0 ) {
            log.debug ( methodName, "KO5" );
            log.debugEnd ( methodName );
            CodiciEsito ce = CodiciEsito.ERRORE_TIPO_PAGAMENTO_MULTIBENEFICIARIO;
            throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( maxErrorMessageWidth, codiceVersamento ) );
        }

        //EPAY-347
        if ( tipoPagamento.getIdTipoPagamento () != null && tipoPagamento.getTipologiaPagamento () != null ) {

            if ( TipologiaPagamento.TipoPagamentoType.REDS.name ().equalsIgnoreCase ( tipoPagamento.getTipologiaPagamento ().getCodice () ) ) {
                log.debug ( methodName, "KO4" );
                log.debugEnd ( methodName );
                CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_NON_PERMESSO_ERRORE;
                throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                    ce.getMessaggio ( maxErrorMessageWidth, codiceVersamento ) );
            }

            if ( TipologiaPagamento.TipoPagamentoType.MABL.name ().equalsIgnoreCase ( tipoPagamento.getTipologiaPagamento ().getCodice () ) ) {
                log.debug ( methodName, "KO4" );
                log.debugEnd ( methodName );
                CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_MARCA_ERRORE;
                throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                    ce.getMessaggio ( maxErrorMessageWidth, codiceVersamento ) );
            }

            if ( TipologiaPagamento.TipoPagamentoType.PS.name ().equalsIgnoreCase ( tipoPagamento.getTipologiaPagamento ().getCodice () ) ) {
                log.debug ( methodName, "KO4" );
                log.debugEnd ( methodName );
                CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_SPONTANEO_ERRORE;
                throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                    ce.getMessaggio ( maxErrorMessageWidth, codiceVersamento ) );
            }

        }
        log.debug ( methodName, "OK" );
        log.debugEnd ( methodName );
        return tipoPagamento;
    }

    private void verificaNumeroPosizioni ( int numeroPosizioniDebitorie, int numeroListaPosizioneDebitorie ) throws TestataException {
        final String methodName = "verificaTestataNumeroPosizioniEImportoTotale";

        log.debugStart ( methodName );

        if ( numeroListaPosizioneDebitorie != numeroPosizioniDebitorie ) {
            log.debug ( methodName, "KO1" );
            log.debugEnd ( methodName );
            CodiciEsito ce = CodiciEsito.POSIZIONE_DEBITORIE_NUMERO_ERRORE;
            throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, ce.getMessaggio ( maxErrorMessageWidth ) );
        }
        log.debug ( methodName, "OK" );
        log.debugEnd ( methodName );
    }

    private void verificaElementiObbligatori ( PosizioneDaAggiornare posizione ) throws PosizioneDebitoriaException {
        testField ( posizione.getIdPosizioneDebitoria (), CodiciEsito.CODICE_ID_POSIZIONE_DEBITORIA_OBBLIGATORIO );
        //testField(posizione.getMotivazione(), CodiciEsito.MOTIVAZIONE_OBBLIGATORIO);
        testField ( posizione.getTipoAggiornamento (), CodiciEsito.TIPOAGGIORNAMENTO_OBBLIGATORIO );
    }

    private void testField ( final String str, final CodiciEsito ce ) throws PosizioneDebitoriaException {
        if ( StringUtils.isBlank ( str ) ) {
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_GENERICO, ce.getMessaggio ( maxErrorMessageWidth ) );
        }
    }

    private void verificaStato ( Pagamento pagamento ) throws PosizioneDebitoriaException {
        StatoPagamento statoPagamento = StatoPagamento.findById ( pagamento.getIdStatoCorrente () );
        CodiciEsito ce;
        switch ( statoPagamento ) {
        case IN_ATTESA :
        case TRANSAZIONE_INIZIALIZZATA :
        case TRANSAZIONE_AVVIATA :
            ce = CodiciEsito.PAGAMENTO_IN_ATTESA;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, ce.getMessaggio ( maxErrorMessageWidth ) );
        case SUCCESSO :
            ce = CodiciEsito.PAGAMENTO_GIA_EFFETTUATO;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, ce.getMessaggio ( maxErrorMessageWidth ) );
        case INVALIDATO :
            ce = CodiciEsito.PAGAMENTO_GIA_ANNULLATO;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, ce.getMessaggio ( maxErrorMessageWidth ) );
        default :
            EpayDStatoPagamento sp = entityManager.find ( EpayDStatoPagamento.class, statoPagamento.getId () );
            if ( !sp.getModificabile () ) {
                ce = CodiciEsito.PAGAMENTO_NON_MODIFICABILE;
                throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                    ce.getMessaggio ( maxErrorMessageWidth, pagamento.getCodicePagamentoRifEnte () ) );
            }
            break;
        }
    }

    private List<Date> verificaDataIniziFineValidita ( Ente ente, TipoPagamento tipoPagamento, PosizioneDaAggiornare posizione, Pagamento pagamento )
                    throws PosizioneDebitoriaException {
        final String methodName = "verificaDataIniziFineValidita";
        log.debugStart ( methodName );

        List<Date> dateValidita = new ArrayList<> ();
        dateValidita.add ( posizione.getDataInizioValidita () == null ? pagamento.getInizioValidita () : posizione.getDataInizioValidita () );
        dateValidita.add ( posizione.getDataFineValidita () == null ? pagamento.getFineValidita () : posizione.getDataFineValidita () );

        if ( dateValidita.get ( 0 ) == null ) {
            log.debug ( methodName, "OK" );
            return dateValidita;
        }

        if ( dateValidita.get ( 1 ) == null ) {
            log.debug ( methodName, "OK" );
            return dateValidita;
        }

        if ( dateValidita.get ( 1 ).before ( dateValidita.get ( 0 ) ) ) {
            log.debug ( methodName, "KO" );
            CodiciEsito ce = CodiciEsito.ERRORE_DATE_VALIDITA;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( maxErrorMessageWidth, ente.getCodiceFiscale (),
                    tipoPagamento.getCodiceVersamento (), posizione.getIdPosizioneDebitoria () ) );
        }

        log.debug ( methodName, "OK" );
        log.debugEnd ( methodName );
        return dateValidita;
    }

    private void verificaValiditaPagamento ( TipoPagamento tipoPagamento, List<Date> dateValidita ) throws PosizioneDebitoriaException {
        final String methodName = "verificaValiditaPagamento";
        log.debugStart ( methodName );
        if ( dateValidita.get ( 1 ) != null &&
                        dateValidita.get ( 1 ).before ( new Date () ) ) {
            CodiciEsito ce = CodiciEsito.PAGAMENTO_SCADUTO;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, ce.getMessaggio ( maxErrorMessageWidth ) );
        }

        if ( tipoPagamento.getInizioValidita () != null &&
                        dateValidita.get ( 1 ) != null &&
                        dateValidita.get ( 1 ).before ( tipoPagamento.getInizioValidita () ) ) {
            CodiciEsito ce = CodiciEsito.PAGAMENTO_MAI_ATTIVO;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, ce.getMessaggio ( maxErrorMessageWidth ) );
        }

        if ( tipoPagamento.getFineValidita () != null &&
                        dateValidita.get ( 0 ) != null &&
                        dateValidita.get ( 0 ).after ( tipoPagamento.getInizioValidita () ) ) {
            CodiciEsito ce = CodiciEsito.PAGAMENTO_MAI_ATTIVO;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, ce.getMessaggio ( maxErrorMessageWidth ) );
        }

        log.debugEnd ( methodName );
    }

    private boolean verificaComponentiPagamento ( Ente ente, TipoPagamento tipoPagamento, PosizioneDaAggiornare posizione, BigDecimal importoTotale )
                    throws PosizioneDebitoriaException {
        final String methodName = "verificaComponentiPagamento";

        log.debugStart ( methodName );
        try {
            if ( posizione.getComponentiImporto () == null ) {
                log.debug ( methodName, "OK" );
                return false;
            }
            List<ComponentiImporto> componenteImportoType = posizione.getComponentiImporto ();
            BigDecimal importoCalcolato = BigDecimal.ZERO;
            for ( ComponentiImporto componente: componenteImportoType ) {
                importoCalcolato = importoCalcolato.add ( componente.getImporto () );

                if ( ( !StringUtils.isBlank ( componente.getNumeroAccertamento () )
                                && ( componente.getAnnoAccertamento () == null || componente.getAnnoAccertamento () == 0 ) ) ) {
                    CodiciEsito ce = CodiciEsito.COMPONENTE_PAGAMENTO_ANNO_ACCERTAMENTO;
                    throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                        ce.getMessaggio ( maxErrorMessageWidth ) );
                }
                if ( StringUtils.isBlank ( componente.getNumeroAccertamento () ) && componente.getAnnoAccertamento () != null
                                && componente.getAnnoAccertamento () > 0 ) {
                    CodiciEsito ce = CodiciEsito.COMPONENTE_PAGAMENTO_NUMERO_ACCETAMENTO;
                    throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                        ce.getMessaggio ( maxErrorMessageWidth ) );
                }
            }

            BigDecimal importoTotaleAtteso = posizione.getImportoTotale () == null ? importoTotale : posizione.getImportoTotale ();
            if ( posizione instanceof PosizioneMultibeneficiarioDaAggiornare ) {
                // calcolo importo totale per aggiornamento posizione debitoria multibeneficiario
                PosizioneMultibeneficiarioDaAggiornare pos = (PosizioneMultibeneficiarioDaAggiornare) posizione;
                if ( pos.getImportoSecondario ().compareTo ( pos.getComponentiImportoSecondario ().get ( 0 ).getImporto () ) != 0 ) {
                    CodiciEsito ce = CodiciEsito.ERRORE_SOMMA_IMPORTI_SECONDARIO;
                    throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, ce.getMessaggio (
                        maxErrorMessageWidth ) );
                }
                importoCalcolato = importoCalcolato.add ( pos.getImportoSecondario () );
            }
            if ( importoCalcolato.compareTo ( importoTotaleAtteso ) != 0 ) {
                log.debug ( methodName, "KO" );
                CodiciEsito ce = CodiciEsito.ERRORE_TOTALE_IMPORTO_COMPONENTI;
                throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, ce.getMessaggio (
                    maxErrorMessageWidth,
                    ente.getCodiceFiscale (),
                    tipoPagamento.getCodiceVersamento (),
                    posizione.getIdPosizioneDebitoria (),
                    posizione.getImportoTotale ().toString (),
                    importoCalcolato.toString () ) );
            }
            log.debug ( methodName, "OK" );
        } finally {
            log.debugEnd ( methodName );
        }
        return true;
    }

    private boolean verificaRiferimentiPagamento ( PosizioneDaAggiornare posizione ) throws PosizioneDebitoriaException {
        final String methodName = "verificaRiferimentiPagamento";

        log.debugStart ( methodName );

        try {
            if ( posizione.getRiferimentiPagamento () == null ) {
                log.debug ( methodName, "OK" );
                return false;
            }

            List<RiferimentiPagamento> riferimenti = posizione.getRiferimentiPagamento ();
            for ( RiferimentiPagamento riferimento: riferimenti ) {
                if ( ( !StringUtils.isBlank ( riferimento.getNome () ) && StringUtils.isBlank ( riferimento.getValore () ) )
                                || ( StringUtils.isBlank ( riferimento.getNome () ) && !StringUtils.isBlank ( riferimento.getValore () ) ) ) {
                    CodiciEsito ce = CodiciEsito.RIFERIMENTO_PAGAMENTO_VUOTO;
                    throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                        ce.getMessaggio ( maxErrorMessageWidth ) );
                }
            }

            log.debug ( methodName, "OK" );
        } finally {
            log.debugEnd ( methodName );
        }
        return true;
    }

    private void verificaAnagrafica ( it.csi.epay.epayservices.model.SoggettoPagatore soggettoType, Pagamento pagamento ) throws PosizioneDebitoriaException {

        if ( null != soggettoType && ( null != soggettoType.getPersonaFisica () || null != soggettoType.getPersonaGiuridica () ) ) {
            if ( !"ANONIMO".equals ( pagamento.getPagatore ().getCodiceFiscale () )
                            || !"ANONIMO".equals ( pagamento.getPagatore ().getCognome () )
                            || !"ANONIMO".equals ( pagamento.getPagatore ().getNome () ) ) {

                throw new PosizioneDebitoriaException ( CodiciEsito.SOGGETTO_PAGATORE_NON_ANONIMO.getCodice (),
                    CodiciEsito.SOGGETTO_PAGATORE_NON_ANONIMO.getMessaggio ( maxErrorMessageWidth ) );
            }
            if ( new BigDecimal ( 0 ).compareTo ( pagamento.getImporto () ) != 0 ) {
                throw new PosizioneDebitoriaException ( CodiciEsito.IMPORTO_MAGGIORE_DI_ZERO.getCodice (),
                    CodiciEsito.IMPORTO_MAGGIORE_DI_ZERO.getMessaggio ( maxErrorMessageWidth ) );
            }
            if ( null != soggettoType.getPersonaFisica () ) {
                testField ( soggettoType.getPersonaFisica ().getNome (), CodiciEsito.NOME_OBBLIGATORIO );
                testField ( soggettoType.getPersonaFisica ().getCognome (), CodiciEsito.COGNOME_OBBLIGATORIO );

            } else if ( null != soggettoType.getPersonaGiuridica () ) {
                testField ( soggettoType.getPersonaGiuridica ().getRagioneSociale (), CodiciEsito.RAGIONESOCIALE_OBBLIGATORIA );
            }
            testField ( soggettoType.getIdentificativoUnivocoFiscale (), CodiciEsito.CODICE_FISCALE_OBBLIGATORIO );

        }

    }

    private final Function<String, String> stringTrim = ( s ) -> s == null ? null : s.trim ();

    private Anagrafica toAnagrafica ( it.csi.epay.epayservices.model.SoggettoPagatore soggettoPagatore ) {
        final String methodName = "inserisciAnagrafica";

        log.debugStart ( methodName );
        try {
            Anagrafica anagrafica = new Anagrafica ();
            if ( soggettoPagatore.getPersonaFisica () != null && soggettoPagatore.getPersonaGiuridica () == null ) {
                anagrafica.setFlagPersonaFisica ( Boolean.TRUE );
                anagrafica.setNome ( stringTrim.apply ( soggettoPagatore.getPersonaFisica ().getNome () ) );
                anagrafica.setCognome ( stringTrim.apply ( soggettoPagatore.getPersonaFisica ().getCognome () ) );
            }
            if ( soggettoPagatore.getPersonaFisica () == null && soggettoPagatore.getPersonaGiuridica () != null ) {
                anagrafica.setFlagPersonaFisica ( Boolean.FALSE );
                anagrafica.setRagioneSociale ( stringTrim.apply ( soggettoPagatore.getPersonaGiuridica ().getRagioneSociale () ) );
            }
            anagrafica.setCodiceFiscale ( stringTrim.apply ( soggettoPagatore.getIdentificativoUnivocoFiscale () ) );
            if ( StringUtils.isNotBlank ( soggettoPagatore.getIndirizzo () ) ) {
                anagrafica.setIndirizzo ( stringTrim.apply ( soggettoPagatore.getIndirizzo () ) );
            }
            if ( StringUtils.isNotBlank ( soggettoPagatore.getCivico () ) ) {
                anagrafica.setCivico ( stringTrim.apply ( soggettoPagatore.getCivico () ) );
            }
            if ( StringUtils.isNotBlank ( soggettoPagatore.getCap () ) ) {
                anagrafica.setCap ( stringTrim.apply ( soggettoPagatore.getCap () ) );
            }
            if ( StringUtils.isNotBlank ( soggettoPagatore.getLocalita () ) ) {
                anagrafica.setLocalita ( stringTrim.apply ( soggettoPagatore.getLocalita () ) );
            }
            if ( StringUtils.isNotBlank ( soggettoPagatore.getProvincia () ) ) {
                anagrafica.setProvincia ( stringTrim.apply ( soggettoPagatore.getProvincia () ) );
            }
            if ( StringUtils.isNotBlank ( soggettoPagatore.getNazione () ) ) {
                anagrafica.setNazione ( stringTrim.apply ( soggettoPagatore.getNazione () ) );
            }
            anagrafica.setEmail ( stringTrim.apply ( soggettoPagatore.getEmail () ) );

            return anagrafica;
        } finally {
            log.debugEnd ( methodName );
        }

    }

    private AggiornaPosizioneDebitoriaChiamanteEsternoOutput mapAggiornaPosizioneFailOrSuccess ( String codiceEsito, String descrizioneEsito ) {

        AggiornaPosizioneDebitoriaChiamanteEsternoOutput result = new AggiornaPosizioneDebitoriaChiamanteEsternoOutput ();
        result.setCodice ( codiceEsito );
        result.setMessaggio ( descrizioneEsito );
        return result;
    }

    private AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoOutput mapAggiornaPosizioneMultiFailOrSuccess ( String codiceEsito,
        String descrizioneEsito ) {

        AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoOutput result = new AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoOutput ();
        result.setCodice ( codiceEsito );
        result.setMessaggio ( descrizioneEsito );
        return result;
    }

    //CSI_PAG-1889 INIZIO
    private String validaInputGetIuvComponenti ( GetIuvCommonChiamanteEsternoInput input,
        AccessoChiamanteEsternoSincronoComponentePagamentoInput componente, String codiceFiscale ) {
        String componentType = " ";
        if ( input instanceof GetIuvMultibeneficiarioChiamanteEsternoInput ) {
			componentType = " principali ";
		}
        if ( componente.getProgressivo () == null ) {
            return fail ( input, codiceFiscale, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                null, String.format ( "Il campo progressivo delle componenti%sdel pagamento e' obbligatorio", componentType ), null );
        }
        if ( componente.getImporto () == null ) {
            return fail ( input, codiceFiscale, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                null, String.format ( "Il campo importo delle componenti%sdel pagamento e' obbligatorio", componentType ), null );
        }
        if ( StringUtils.isBlank ( componente.getCausale () ) ) {
            return fail ( input, codiceFiscale, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                null, String.format ( "Il campo causale delle componenti%sdel pagamento e' obbligatorio", componentType ), null );
        }
        if ( componente.getAnnoAccertamento () == null ) {
            return fail ( input, codiceFiscale, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                null, String.format ( "Il campo annoAccertamento delle componenti%sdel pagamento e' obbligatorio", componentType ), null );
        }
        if ( StringUtils.isBlank ( componente.getNumeroAccertamento () ) ) {
            return fail ( input, codiceFiscale, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                null, String.format ( "Il campo numeroAccertamento delle componenti%sdel pagamento e' obbligatorio", componentType ), null );
        }
        if ( componente.getImporto ().compareTo ( BigDecimal.ZERO ) < 0 ) {
            return fail ( input, codiceFiscale, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                null, String.format ( "Il campo importo delle componenti%sdel pagamento non e' valido", componentType ), null );
        }
        if ( componente.getProgressivo () < 0 ) {
            return fail ( input, codiceFiscale, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                null, String.format ( "Il campo progressivo delle componenti%sdel pagamento non e' valido", componentType ), null );
        }
        if ( componente.getAnnoAccertamento () < 0 ) {
            return fail ( input, codiceFiscale, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                null, String.format ( "Il campo annoAccertamento delle componenti%sdel pagamento non e' valido", componentType ), null );
        }
        return null;
    }

	private String validaInputGetIuvComponenti ( GetIuvCommonChiamanteEsternoInput input,
					ComponentePagamentoEnteSecondaroInput componente, String codiceFiscale ) {
		String componentType = " ";
		if ( input instanceof GetIuvMultibeneficiarioChiamanteEsternoInput ) {
			componentType = " secondarie ";
		}
		if ( componente.getProgressivo () == null ) {
			return fail ( input, codiceFiscale, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
							null, String.format ( "Il campo progressivo delle componenti%sdel pagamento e' obbligatorio", componentType ), null );
		}
		if ( componente.getImporto () == null ) {
			return fail ( input, codiceFiscale, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
							null, String.format ( "Il campo importo delle componenti%sdel pagamento e' obbligatorio", componentType ), null );
		}
		if ( StringUtils.isBlank ( componente.getCausale () ) ) {
			return fail ( input, codiceFiscale, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
							null, String.format ( "Il campo causale delle componenti%sdel pagamento e' obbligatorio", componentType ), null );
		}
		if ( componente.getImporto ().compareTo ( BigDecimal.ZERO ) < 0 ) {
			return fail ( input, codiceFiscale, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
							null, String.format ( "Il campo importo delle componenti%sdel pagamento non e' valido", componentType ), null );
		}
		if ( componente.getProgressivo () < 0 ) {
			return fail ( input, codiceFiscale, EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
							null, String.format ( "Il campo progressivo delle componenti%sdel pagamento non e' valido", componentType ), null );
		}
		return null;
	}

    private String validaTotaliComponentiPagamento ( GetIuvCommonChiamanteEsternoInput input, boolean isCompSecondario ) {
        BigDecimal importoTotaleComponente = BigDecimal.ZERO.setScale ( 2, RoundingMode.HALF_UP );
        List<?> componenti = null;
        if ( isCompSecondario ) {
            componenti = ( (GetIuvMultibeneficiarioChiamanteEsternoInput) input ).getComponentiPagamentoEntiSecondari ();
            if ( !CollectionUtils.isEmpty ( componenti ) ) {
                for ( ComponentePagamentoEnteSecondaroInput componente: ( (GetIuvMultibeneficiarioChiamanteEsternoInput) input )
                                .getComponentiPagamentoEntiSecondari () ) {
                    importoTotaleComponente = importoTotaleComponente.add ( componente.getImporto () );
                }
            } else {
                return fail ( input, input.getCodiceFiscalePartitaIVAPagatore (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                    null, "Errore. parametro ComponentiImportoSecondario non valorizzato.",
                    null );
            }
        } else {
            if ( input instanceof GetIuvChiamanteEsternoInput ) {
                componenti = ( (GetIuvChiamanteEsternoInput) input ).getComponentiPagamento ();
                if ( !CollectionUtils.isEmpty ( componenti ) ) {
                    for ( AccessoChiamanteEsternoSincronoComponentePagamentoDSRInput componente: ( (GetIuvChiamanteEsternoInput) input ).getComponentiPagamento () ) {
                        importoTotaleComponente = importoTotaleComponente.add ( componente.getImporto () );
                    }
                }
            } else if ( input instanceof GetIuvMultibeneficiarioChiamanteEsternoInput ) {
                componenti = ( (GetIuvMultibeneficiarioChiamanteEsternoInput) input ).getComponentiPagamento ();
                if ( !CollectionUtils.isEmpty ( componenti ) ) {
                    for ( AccessoChiamanteEsternoSincronoComponentePagamentoInput componente: ( (GetIuvMultibeneficiarioChiamanteEsternoInput) input ).getComponentiPagamento () ) {
                        importoTotaleComponente = importoTotaleComponente.add ( componente.getImporto () );
                    }
                }
            }
        }

        BigDecimal importoDaVerificare;
        if ( !isCompSecondario ) {
            if ( input instanceof GetIuvMultibeneficiarioChiamanteEsternoInput ) {
                importoDaVerificare = ( (GetIuvMultibeneficiarioChiamanteEsternoInput) input ).getImportoPrincipale ().setScale ( 2, RoundingMode.HALF_UP );
            } else {
				assert input instanceof GetIuvChiamanteEsternoInput;
				importoDaVerificare = ( (GetIuvChiamanteEsternoInput) input ).getImporto ().setScale ( 2, RoundingMode.HALF_UP );
            }
        } else {
            importoDaVerificare = ( (GetIuvMultibeneficiarioChiamanteEsternoInput) input ).getImportoSecondarioAltroEnte ().setScale ( 2, RoundingMode.HALF_UP );
        }
        if ( !CollectionUtils.isEmpty ( componenti ) && !importoTotaleComponente.equals ( importoDaVerificare ) ) {
            if ( !isCompSecondario ) {
                if ( input instanceof GetIuvMultibeneficiarioChiamanteEsternoInput ) {
                    return fail ( input, input.getCodiceFiscalePartitaIVAPagatore (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                        null, "Errore. La somma degli importi indicati in ComponenteImporto non corrisponde al valore ImportoPrincipale.", null );
                } else {
                    return fail ( input, input.getCodiceFiscalePartitaIVAPagatore (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                        null, "Errore. La somma degli importi indicati in ComponenteImporto non corrisponde al valore Importo.", null );
                }
            } else {
                return fail ( input, input.getCodiceFiscalePartitaIVAPagatore (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                    null, "Errore. La somma degli importi indicati in ComponentiImportoSecondario non corrisponde al valore ImportoSecondarioAltroEnte.",
                    null );
            }
        }
        return null;
    }

    private String validaTotaliPagamento ( GetIuvCommonChiamanteEsternoInput input ) {
        if ( input instanceof GetIuvMultibeneficiarioChiamanteEsternoInput ) {
            GetIuvMultibeneficiarioChiamanteEsternoInput mBenInput = (GetIuvMultibeneficiarioChiamanteEsternoInput) input;
            BigDecimal importoDaVerificare = BigDecimal.ZERO.setScale ( 2, RoundingMode.HALF_UP );
            importoDaVerificare = importoDaVerificare.add ( mBenInput.getImportoPrincipale () );
            importoDaVerificare = importoDaVerificare.add ( mBenInput.getImportoSecondarioAltroEnte () );
            BigDecimal importototale =mBenInput.getImportoTotale ().setScale ( 2, RoundingMode.HALF_UP );
            if ( !importototale.equals ( importoDaVerificare ) ) {
                return fail ( input, input.getCodiceFiscalePartitaIVAPagatore (), EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                    null, "Errore. La somma degli importi indicati in ImportoPrincipale e ImportoSecondarioAltroEnte non corrisponde al valore ImportoTotale.",
                    null );
            }
        }
        return null;
    }

	private DatiSpecificiRiscossioneOutput getDatiSpecificiRiscossione ( String codiceFiscaleEnte, String tipoPagamento ) throws TassonomiaServiceException {
		DatiSpecificiRiscossioneOutput dsr;

		try {
			TassonomiaAdapterClient client = new TassonomiaAdapterClient ( configurazioneManager );
			DatiSpecificiRiscossioneInput request = client.buildDatiSpecificiRiscossioneInput ( codiceFiscaleEnte, tipoPagamento );
			dsr = client.getDatiSpecificiRiscossione ( request );

		} catch ( TassonomiaServiceException e ) {
			log.error ( "Errore in fase di reperimento del dato specifico riscossione", e );
			throw e;
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
                        ce.getMessaggio ( maxErrorMessageWidth ) + ", per la componente con anno e numero accertamento: " + componente.getAnnoAccertamento ()
                        + " - " + componente.getNumeroAccertamento () + " non sono presenti valori su Catalogo!");
                }
            }
        }
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

    //multibeneficiario-inizio

    @TransactionAttribute ( TransactionAttributeType.REQUIRED )
    @Override
    public AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoOutput
    aggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiario ( AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoInput input ) {
        String methodName = "aggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiario";

        log.debug ( methodName, "input : " + XmlUtil.obj2Xml ( input ) );
        AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoOutput result;

        try {
            result = doAggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiario ( input );
        } catch ( Exception t ) {
            result = mapAggiornaPosizioneMultiFailOrSuccess ( EsitoChiamataEsterna.ERRORE_GENERICO, EsitoChiamataEsterna.ERRORE_GENERICO );
        }

        log.debug ( methodName, "output : " + XmlUtil.obj2Xml ( result ) );

        return result;
    }

	private AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoOutput
    doAggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiario ( AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoInput input ) {
        final String methodName = "doAggiornaPosizioneDebitoriaChiamanteEsterno";
        log.debugStart ( methodName );

        AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoOutput res = new AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoOutput ();
        Ente ente;
        TipoPagamento tipoPagamento;
        TipoPagamento tipoPagamentoSecondario;
        try {
            //recupero posizioni da aggiornare ricevute dall'input
            List<PosizioneMultibeneficiarioDaAggiornare> posizioniDaAggiornare = input.getElencoPosizioniDaAggiornare ();

            //Controllo esistenza dell'Ente in base al CFEnteCreditore

            ente = verificaEsistenzaEnte ( input.getCodiceFiscaleEnte () );
            log.info ( methodName, "recuperato ente : " + ente.getCodiceFiscale () );

            try {
                tipoPagamento = verificaTipoPagamento ( ente, input.getTipoPagamento (), Boolean.TRUE );
            } catch ( TestataException e1 ) {
                return mapAggiornaPosizioneMultiFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, e1.getMessage () );
            }
            //CSI_PAG-1888 INIZIO
            verificaCompatibilitaFlagMultibeneficiario ( tipoPagamento, tipoPagamento.getFlagMultibeneficiario () );
            if ( tipoPagamento.getFlagMultibeneficiario () ) {
                tipoPagamentoSecondario = verificaEsistenzaPagamentoCollegato ( tipoPagamento );
                verificaTestataTipoPagamento ( tipoPagamentoSecondario );
            }
            //CSI_PAG-1888 FINE

            //verifica congruenza numero delle posizioni inserite

            try {
                verificaNumeroPosizioni ( input.getNumeroPosizioniDebitorie (), posizioniDaAggiornare.size () );
            } catch ( TestataException e1 ) {
                return mapAggiornaPosizioneMultiFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, e1.getMessage () );
            }
            DatiSpecificiRiscossioneOutput dsr = null;
            DatiSpecificiRiscossioneOutput dsrSec = null;
            //superati i controlli preliminari, ciclo sulle posizioni da aggiornare
            for ( PosizioneMultibeneficiarioDaAggiornare posizione: posizioniDaAggiornare ) {
                Pagamento pagamento;
                try {
                    verificaElementiObbligatori ( posizione );
                    try {
                        pagamento = pagamentoManager.getPagamentoPerCodRifEnte ( ente.getCodiceFiscale (), tipoPagamento.getCodiceVersamento (),
                            posizione.getIdPosizioneDebitoria () );
                        if ( pagamento == null ) {
                            throw new NoDataException ();
                        }
                    } catch ( NoDataException e ) {
                        CodiciEsito ce = CodiciEsito.PAGAMENTO_NON_TROVATO_PER_POSIZIONE_DEBITORIA;
                        throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                            ce.getMessaggio ( maxErrorMessageWidth, ente.getCodiceFiscale (),
                                tipoPagamento.getCodiceVersamento (), posizione.getIdPosizioneDebitoria () ) );
                    } catch ( MoreResultException e ) {
                        CodiciEsito ce = CodiciEsito.CODICE_ID_POSIZIONE_DEBITORIA_DUPLICATA;
                        throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                            ce.getMessaggio ( maxErrorMessageWidth, ente.getCodiceFiscale (),
                                tipoPagamento.getCodiceVersamento (), posizione.getIdPosizioneDebitoria () ) );
                    }
                    try {
                        verificaStato ( pagamento );
                    } catch ( PosizioneDebitoriaException e ) {
                        return mapAggiornaPosizioneMultiFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, e.getMessage () );
                    }
                    switch ( posizione.getTipoAggiornamento () ) {
                    case "ANNULLAMENTO" :
                        //pagamentoManager.aggiornaDateValidita(pagamento.getIdPagamento(), pagamento.getInizioValidita(), new Date());
                        pagamentoManager.aggiornaNote ( pagamento.getIdPagamento (), posizione.getMotivazione () );
                        pagamentoManager.aggiornaStato ( pagamento.getIdPagamento (), StatoPagamento.INVALIDATO );

                        RegistroVersamenti registroVersamenti = new RegistroVersamenti ();
                        registroVersamenti.setIdPagamento ( pagamento.getIdPagamento () );
                        registroVersamenti.setDataOperazione ( null );
                        registroVersamenti.setIdStato ( StatoPagamento.INVALIDATO.getId () );
                        registroVersamenti.setRisultato ( StatoPagamento.INVALIDATO.getDescrizione () );
                        registroVersamenti.setOrigineInserimento ( this.getClass ().getName () );
                        registroVersamentiManager.inserisci ( registroVersamenti );

                        break;
                    case "MODIFICA" :
                        List<Date> dateValidita = verificaDataIniziFineValidita ( ente, tipoPagamento, posizione, pagamento );
                        boolean hasComponenti = verificaComponentiPagamento ( ente, tipoPagamento, posizione, pagamento.getImporto () );
                        boolean hasRiferimenti = verificaRiferimentiPagamento ( posizione );
                        verificaValiditaPagamento ( tipoPagamento, dateValidita );
                        verificaAnagrafica ( posizione.getSoggettoPagatore (), pagamento );

                        if ( StringUtils.isNoneEmpty ( posizione.getDescrizioneCausaleVersamento () ) ) {
                            pagamentoManager.aggiornaCausale ( pagamento.getIdPagamento (), posizione.getDescrizioneCausaleVersamento () );
                        }

                        if ( StringUtils.isNoneEmpty ( posizione.getMotivazione () ) ) {
                            pagamentoManager.aggiornaNote ( pagamento.getIdPagamento (), posizione.getMotivazione () );
                        }

                        if ( hasRiferimenti ) {
                            Pagamento pagamentoImporti = new Pagamento ();
                            pagamentoImporti.setIdPagamento ( pagamento.getIdPagamento () );
                            pagamentoImporti.setUtenteUltimaModifica ( "AggiornaPosizioniDebitorie" );
                            for ( RiferimentiPagamento riferimentoType: posizione.getRiferimentiPagamento () ) {
                                PagamentoRiferimenti riferimento = new PagamentoRiferimenti ();
                                riferimento.setNome ( riferimentoType.getNome () );
                                riferimento.setValore ( riferimentoType.getValore () );
                                riferimento.setUtenteUltimaModifica ( "AggiornaPosizioniDebitorie" );
                                pagamentoImporti.getRiferimenti ().add ( riferimento );
                            }
                            pagamentoManager.aggiornaRiferimenti ( pagamentoImporti );
                        }

                        pagamento.setIdPagamento ( pagamento.getIdPagamento () );
                        pagamento.setImporto ( posizione.getImportoTotale () );
                        pagamento.setUtenteUltimaModifica ( "AggiornaPosizioniDebitorie" );
                        if ( hasComponenti ) {
                            if ( null == dsr ) {
                                try {
                                    dsr = getDatiSpecificiRiscossione ( input.getCodiceFiscaleEnte (), input.getTipoPagamento () );
                                } catch ( TassonomiaServiceException e1 ) {
                                    throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, e1.getMessage () );
                                }
                            }
                            pagamento.setComponenti ( new LinkedList<> () );
                            for ( ComponentiImporto componenteImportoType: posizione.getComponentiImporto () ) {
                                if ( null != componenteImportoType.getImporto () ) {
                                    PagamentoComponenti componente = new PagamentoComponenti ();
                                    componente.setImporto ( componenteImportoType.getImporto () );
                                    componente.setCausale ( componenteImportoType.getCausaleDescrittiva () );
                                    componente.setAnnoAccertamento (
                                        componenteImportoType.getAnnoAccertamento () != null ? componenteImportoType.getAnnoAccertamento () : null );
                                    componente.setNumeroAccertamento (
                                        StringUtils.isBlank ( componenteImportoType.getNumeroAccertamento () ) ? null
                                                        : componenteImportoType.getNumeroAccertamento () );
                                    componente.setUtenteUltimaModifica ( "AggiornaPosizioniDebitorie" );
                                    inserisciDatoSpecificoRiscossioneInComponente ( componente, dsr );

                                    pagamento.getComponenti ().add ( componente );
                                }
                            }
                        } else if ( CollectionUtils.isEmpty ( pagamento.getComponenti () ) ) {
                            if ( null == dsr ) {
                                try {
                                    dsr = getDatiSpecificiRiscossione ( input.getCodiceFiscaleEnte (), input.getTipoPagamento () );
                                } catch ( TassonomiaServiceException e1 ) {
                                    throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, e1.getMessage () );
                                }
                            }
                            pagamento.getComponenti ().add ( creaComponenteDiDefault ( dsr, posizione.getDescrizioneCausaleVersamento (),
                                posizione.getImportoTotale (), posizione.getIdPosizioneDebitoria () ) );
                        } else if ( pagamento.getComponenti ().size () == 1 ) {
                            pagamento.getComponenti ().get ( 0 ).setImporto ( pagamento.getImporto () );
                        }

                        if ( posizione.getDataInizioValidita () != null || posizione.getDataFineValidita () != null || posizione.getDataScadenza () != null ) {
                            pagamentoManager.aggiornaDate2 ( pagamento.getIdPagamento (), posizione.getDataInizioValidita (), posizione.getDataFineValidita (),
                                posizione.getDataScadenza () );
                        }

                        if ( null != posizione.getSoggettoPagatore () && ( null != posizione.getSoggettoPagatore ().getPersonaFisica ()
                                        || null != posizione.getSoggettoPagatore ().getPersonaGiuridica () ) ) {
                            Anagrafica anagraficaNew = toAnagrafica ( posizione.getSoggettoPagatore () );
                            anagraficaNew.setIdAnagrafica ( pagamento.getPagatore ().getIdAnagrafica () );
                            anagraficaManager.aggiorna ( pagamento.getIdPagamento (), anagraficaNew );

                        }

                        //c) Aggiornamento dell'importo secondario
                        if ( Boolean.TRUE.equals ( tipoPagamento.getFlagMultibeneficiario () ) ) {

                            //Nelle verifiche fatte in precedenza i componenti degli importi e i riferimenti erano a posto.
                            boolean hasComponentiSecondari
                            = ! ( posizione.getComponentiImportoSecondario () == null
                            || posizione.getComponentiImportoSecondario ().isEmpty () );
                            PagamentoSecondario pagamentoImportiSecondario = pagamentoManager.getPagamentoSecondarioByIdPagamentoPrincipale ( pagamento.getIdPagamento () );
                            pagamentoImportiSecondario.setImportoComplessivo ( pagamentoImportiSecondario.getImportoComplessivo () );
                            pagamentoImportiSecondario.setCausale ( pagamentoImportiSecondario.getCausale () );
                            if ( hasComponentiSecondari ) { //ce ne deve essere solo uno
                                if ( null == dsrSec ) {
                                    try {
                                        dsrSec = getDatiSpecificiRiscossione ( input.getCodiceFiscaleEnte (), input.getTipoPagamento () );
                                    } catch ( TassonomiaServiceException e1 ) {
                                        throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, e1.getMessage () );
                                    }
                                }
                                pagamentoImportiSecondario.setComponenti ( new LinkedList<> () );
                                for ( ComponentiImporto componenteImporto: posizione.getComponentiImportoSecondario () ) {
                                    PagamentoComponenti componente = new PagamentoComponenti ();
                                    componente.setImporto ( componenteImporto.getImporto () );
                                    componente.setCausale ( componenteImporto.getCausaleDescrittiva () );
                                    componente.setAnnoAccertamento (
                                        componenteImporto.getAnnoAccertamento () != null ? componenteImporto.getAnnoAccertamento () : null );
                                    componente.setNumeroAccertamento (
                                        StringUtils.isBlank ( componenteImporto.getNumeroAccertamento () ) ? null
                                                        : componenteImporto.getNumeroAccertamento () );
                                    componente.setUtenteUltimaModifica ( "AggiornaPosizioniDebitorie" );
                                    inserisciDatoSpecificoRiscossioneInComponente ( componente, dsrSec );
                                    pagamentoImportiSecondario.getComponenti ().add ( componente );
                                }
                            }
                            pagamentoManager.aggiornaImportiSecondari ( pagamentoImportiSecondario );
                        }

                        break;
                    default :
                        CodiciEsito ce = CodiciEsito.TIPOAGGIORNAMENTO_SCONOSCIUTO;
                        throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                            ce.getMessaggio ( maxErrorMessageWidth ) );
                    }
                } catch ( TassonomiaServiceException | PosizioneDebitoriaException poe ) {
                    log.error ( methodName, "Errore relativo alla posizione " + posizione.getIdPosizioneDebitoria () );
                    log.error ( methodName, poe );
                    return mapAggiornaPosizioneMultiFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, poe.getMessage () );
                } catch ( Exception e ) {
                    log.error ( methodName, "Errore relativo alla posizione " + posizione.getIdPosizioneDebitoria () );
                    log.error ( methodName, e );
                    CodiciEsito ce = CodiciEsito.AGGIORNAMENTO_PAGAMENTO_FALLITO;
                    return mapAggiornaPosizioneMultiFailOrSuccess ( EsitoChiamataEsterna.ERRORE_GENERICO, ce.getMessaggio ( maxErrorMessageWidth ) );
                }
                //listaPosizioneDebitoriaResponse.add ( posizioneDebitoriaResponse );
            }
            res.setCodice ( EsitoChiamataEsterna.OPERAZIONE_COMPLETATA_CON_SUCCESSO );
            res.setMessaggio ( CodiciEsito.ESECUZIONE_OK.getMessaggio ( maxErrorMessageWidth ) );

        } catch ( TestataException te ) {
            log.error ( methodName, "Errore in testate", te );
            res.setCodice ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI );
            res.setMessaggio ( te.getMessage () );
        } catch ( MdpException me ) {
            log.error ( methodName, "Errore in MDP", me );
            CodiciEsito ce = CodiciEsito.MDP_SERVICES_ERRORE;
            res.setCodice ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI );
            res.setMessaggio ( StringUtils.abbreviate ( ce.getMessaggio ( maxErrorMessageWidth ), 500 ) );
        } catch ( Throwable t ) {
            log.error ( methodName, "Errore inatteso", t );
            throw t;
        }

        log.debugEnd ( methodName );
        return res;

    }

    private void verificaCompatibilitaFlagMultibeneficiario ( TipoPagamento tipoPagamento, Boolean flagMultiBeneficiario ) throws TestataException {
        final String methodName = "verificaCompatibilitaFlagMultibeneficiario";
        log.debug ( methodName, "OK" );
        log.debugEnd ( methodName );
        //Il pagamento principale deve avere il flagMultibeneficiario
        //Uguale a quello passato nella richiesta
        boolean btipoPagFlagMulti = tipoPagamento.getFlagMultibeneficiario () != null && tipoPagamento.getFlagMultibeneficiario ();
        boolean bFlagMultiDaRequest = flagMultiBeneficiario != null && flagMultiBeneficiario;
        if ( bFlagMultiDaRequest && !btipoPagFlagMulti ) {
            log.debug ( methodName, "KO2" );
            log.debugEnd ( methodName );
            //Da scatenare solo per i principali
            CodiciEsito ce = CodiciEsito.ERRORE_TIPO_PAGAMENTO_NON_MULTIBENEFICIARIO;
            throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( maxErrorMessageWidth, tipoPagamento.getCodiceVersamento () ) );
        }
        if ( btipoPagFlagMulti && !bFlagMultiDaRequest ) {
            log.debug ( methodName, "KO2" );
            log.debugEnd ( methodName );
            //Da scatenare solo per i principali
            CodiciEsito ce = CodiciEsito.ERRORE_TIPO_PAGAMENTO_MULTIBENEFICIARIO;
            throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( maxErrorMessageWidth, tipoPagamento.getCodiceVersamento () ) );
        }
    }

    private TipoPagamento verificaEsistenzaPagamentoCollegato ( TipoPagamento tipoPagamentoPrincipale ) throws TestataException {
        final String methodName = "verificaEsistenzaPagamentoCollegato";

        log.debugStart ( methodName );
        List<EpayRTipoPagamentoCollegato> listaTipoPagamentoCollegato
        = tipoPagamentoManager.getPagamentoSecondarioByidPagamentoPrincipale ( tipoPagamentoPrincipale );
        if ( listaTipoPagamentoCollegato == null || listaTipoPagamentoCollegato.isEmpty () ) {
            log.debug ( methodName, "KO1" );
            log.debugEnd ( methodName );
            CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_SECONDARIO_NON_TROVATO_ERRORE;
            throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( maxErrorMessageWidth, tipoPagamentoPrincipale.getCodiceVersamento () ) );
        }
        if ( listaTipoPagamentoCollegato.size () > 1 ) {
            log.debug ( methodName, "KO2" );
            log.debugEnd ( methodName );
            CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_SECONDARIO_NON_UNIVOCO_ERRORE;
            throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( maxErrorMessageWidth, tipoPagamentoPrincipale.getCodiceVersamento () ) );
        }
        log.debug ( methodName, "OK" );
        log.debugEnd ( methodName );
        return tipoPagamentoManager.getById ( listaTipoPagamentoCollegato.get ( 0 ).getIdTipoPagamentoSecondario () );
    }

    private void verificaTestataTipoPagamento ( TipoPagamento tipoPagamento ) throws TestataException {
        final String methodName = "verificaTestataTipoPagamento";

        log.debugStart ( methodName );
        if ( tipoPagamento.getFineValidita () != null ) {
            if ( tipoPagamento.getFineValidita ().before ( new Date () ) ) {
                log.debug ( methodName, "KO3" );
                log.debugEnd ( methodName );
                CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_NON_ATTIVO_ERRORE;
                throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                    ce.getMessaggio ( maxErrorMessageWidth, tipoPagamento.getCodiceVersamento () ) );
            }
        }

        //EPAY-347
        if ( tipoPagamento.getIdTipoPagamento () != null && tipoPagamento.getTipologiaPagamento () != null ) {

            if ( TipologiaPagamento.TipoPagamentoType.REDS.name ().equalsIgnoreCase ( tipoPagamento.getTipologiaPagamento ().getCodice () ) ) {
                log.debug ( methodName, "KO4" );
                log.debugEnd ( methodName );
                CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_NON_PERMESSO_ERRORE;
                throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                    ce.getMessaggio ( maxErrorMessageWidth, tipoPagamento.getCodiceVersamento () ) );
            }

            if ( TipologiaPagamento.TipoPagamentoType.MABL.name ().equalsIgnoreCase ( tipoPagamento.getTipologiaPagamento ().getCodice () ) ) {
                log.debug ( methodName, "KO4" );
                log.debugEnd ( methodName );
                CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_MARCA_ERRORE;
                throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                    ce.getMessaggio ( maxErrorMessageWidth, tipoPagamento.getCodiceVersamento () ) );
            }

            if ( TipologiaPagamento.TipoPagamentoType.PS.name ().equalsIgnoreCase ( tipoPagamento.getTipologiaPagamento ().getCodice () ) ) {
                log.debug ( methodName, "KO4" );
                log.debugEnd ( methodName );
                CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_SPONTANEO_ERRORE;
                throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                    ce.getMessaggio ( maxErrorMessageWidth, tipoPagamento.getCodiceVersamento () ) );
            }

        }
        log.debug ( methodName, "OK" );
        log.debugEnd ( methodName );
	}

    //multibeneficiario-fine

    //CSI_PAG-1889 FINE
}

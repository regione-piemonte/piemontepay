/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.service.impl;

import it.csi.epay.epaypacatalogsrv.business.enums.ParametriConfigurazione;
import it.csi.epay.epaypacatalogsrv.business.service.ConfigurazioneService;
import it.csi.epay.epaypacatalogsrv.business.service.PropagazioneExecutorService;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneCommitDTO;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneDatiDTO;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneRollbackDTO;
import it.csi.epay.epaypacatalogsrv.dto.EsitoVerificaStatoServizioDTO;
import it.csi.epay.epaypacatalogsrv.dto.enums.AzioneDaPropagare;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoCommitFruitore;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoInvioFruitore;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoRollbackFruitore;
import it.csi.epay.epaypacatalogsrv.integration.facade.CoopApplicativaPECFacade;
import it.csi.epay.epaypacatalogsrv.integration.facade.RichiediApplicationIdFacade;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.AggiornaCodiceVersamentoRequest;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.AggiornaCodiceVersamentoResponse;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.AggiornaEnteRequest;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.AggiornaEnteResponse;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.AggiornaRiferimentoContabileRequest;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.AggiornaRiferimentoContabileResponse;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.AnnullaOperazioneRequest;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.AnnullaOperazioneResponse;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.CodiceVersamentoType;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.CodiciVersamentoType;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.CodiciVersamentoType.ElencoCodiciVersamento;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.ConfermaOperazioneRequest;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.ConfermaOperazioneResponse;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.CoopApplicativaPEC;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.EnteType;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.ModalitaAcquisizioneProvvisoriType;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.ModalitaDiIntegrazioneType;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.PeriodicitaSchedulazioneType;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.ProtocolloAggiornamentoAzioneType;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.ResultType;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.RiferimentoContabileType;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.TestResourcesRequest;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.TestResourcesResponse;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.TipoMultibeneficiarioType;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.TipoPagamentoType;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.TipologiaAccertamentoType;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.TipologiaDatoSpecificoRiscossioneType;
import it.csi.epay.epaypacatalogsrv.integration.stubs.richiediapplicationid.RichiediApplicationIdRequest;
import it.csi.epay.epaypacatalogsrv.integration.stubs.richiediapplicationid.RichiediApplicationIdResponse;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.LogTransazione;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.Servizio;
import it.csi.epay.epaypacatalogsrv.model.Sottoscrittore;
import it.csi.epay.epaypacatalogsrv.model.StoricoRiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.Transazione;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.LogTransazioneRepository;
import it.csi.epay.epaypacatalogsrv.repository.TransazioneRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class PropagazioneExecutorServiceImpl implements PropagazioneExecutorService {

    private static final String RESULT_CODE_OK = "000";

    private final Logger logger = LogManager.getLogger ( PropagazioneExecutorService.class );

    @Autowired
    protected TransazioneRepository transazioneRepository;

    @Autowired
    protected LogTransazioneRepository logTransazioneRepository;

    @Autowired
    protected ConfigurazioneService configurazioneService;

    @Autowired
    protected CodiceVersamentoRepository codiceVersamentoRepository;

    private RichiediApplicationIdFacade applicationIdFacade;

    private final Map<String, CoopApplicativaPEC> facades = new HashMap<> ();

    @Override
    public EsitoPropagazioneDatiDTO propagaEnte ( Transazione transazione, Sottoscrittore target, Servizio servizio, Ente enteNew, AzioneDaPropagare azione,
        String ibanTesoreria, String bicTesoreria ) {
        CoopApplicativaPEC facade;
        AggiornaEnteRequest parameters;
        AggiornaEnteResponse response;

        try {
            facade = getFacade ( target, servizio );

            parameters = mappaEnte ( enteNew, ibanTesoreria, bicTesoreria );

            parameters.setIdOperazione ( transazione.getIdTransazioneEsterna () );

        } catch ( Exception e ) {
            logger.error ( "eccezione non prevista durante la propagazione", e );
            return new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.FAILED,
                "Errore nella preparazione dei dati di invio: " + e.getMessage () );
        }

        try {
            response = facade.aggiornaEnte ( parameters );
        } catch ( Exception e ) {
            logger.error ( "eccezione non prevista durante la propagazione", e );

            return new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.FAILED,
                "Errore nella fase di invio dei dati: " + e.getMessage () );
        }

        try {
            ResultType result = response.getEsito ().getResult ();

            switch ( result.getCodice () ) {
            case RESULT_CODE_OK :
                return new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, result.getMessaggio () );
            default :
                return new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.REJECTED, result.getMessaggio () );
            }

        } catch ( Exception e ) {
            logger.error ( "eccezione non prevista durante la propagazione", e );
            return new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.FAILED,
                "Errore nel formato della risposta: " + e.getMessage () );
        }
    }

    @Override
    public EsitoPropagazioneDatiDTO propagaCodiceVersamento ( Transazione transazione, Sottoscrittore target, Servizio servizio, CodiceVersamento corrente,
        AzioneDaPropagare azione ) {
        CoopApplicativaPEC facade;
        AggiornaCodiceVersamentoRequest parameters;
        AggiornaCodiceVersamentoResponse response;

        try {
            facade = getFacade ( target, servizio );

            parameters = mappaCodiceVersamento ( transazione, corrente );
            parameters.setIdOperazione ( transazione.getIdTransazioneEsterna () );

            if ( azione == AzioneDaPropagare.INSERIMENTO ) {
                parameters.setProtocolloAggiornamentoAzione ( ProtocolloAggiornamentoAzioneType.INSERIMENTO );
            } else if ( azione == AzioneDaPropagare.MODIFICA ) {
                parameters.setProtocolloAggiornamentoAzione ( ProtocolloAggiornamentoAzioneType.MODIFICA );
            } else if ( azione == AzioneDaPropagare.ELIMINAZIONE ) {
                parameters.setProtocolloAggiornamentoAzione ( ProtocolloAggiornamentoAzioneType.CANCELLAZIONE );
            } else {
                throw new RuntimeException ( "Azione non valida" );
            }

        } catch ( Exception e ) {
            logger.error ( "eccezione non prevista durante la propagazione", e );
            return new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.FAILED,
                "Errore nella preparazione dei dati di invio: " + e.getMessage () );
        }

        try {
            response = facade.aggiornaCodiceVersamento ( parameters );
        } catch ( Exception e ) {
            logger.error ( "eccezione non prevista durante la propagazione", e );
            return new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.FAILED,
                "Errore nella fase di invio dei dati: " + e.getMessage () );
        }

        try {
            ResultType result = response.getEsito ().getResult ();

            switch ( result.getCodice () ) {
            case RESULT_CODE_OK :
                return new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, result.getMessaggio () );
            default :
                return new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.REJECTED, result.getMessaggio () );
            }

        } catch ( Exception e ) {
            logger.error ( "eccezione non prevista durante la propagazione", e );
            return new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.FAILED,
                "Errore nel formato della risposta: " + e.getMessage () );
        }
    }

    @Override
    public EsitoPropagazioneDatiDTO propagaRiferimentoContabile ( Transazione transazione, Sottoscrittore target, Servizio servizio,
        RiferimentoContabile corrente,
        AzioneDaPropagare azione ) {
        CoopApplicativaPEC facade;
        AggiornaRiferimentoContabileRequest parameters;
        AggiornaRiferimentoContabileResponse response;

        try {
            facade = getFacade ( target, servizio );

            parameters = mappaRiferimentoContabile ( corrente );
            parameters.setIdOperazione ( transazione.getIdTransazioneEsterna () );

            if ( azione == AzioneDaPropagare.INSERIMENTO ) {
                parameters.setProtocolloAggiornamentoAzione ( ProtocolloAggiornamentoAzioneType.INSERIMENTO );
            } else if ( azione == AzioneDaPropagare.MODIFICA ) {
                parameters.setProtocolloAggiornamentoAzione ( ProtocolloAggiornamentoAzioneType.MODIFICA );
            } else if ( azione == AzioneDaPropagare.ELIMINAZIONE ) {
                parameters.setProtocolloAggiornamentoAzione ( ProtocolloAggiornamentoAzioneType.CANCELLAZIONE );
            } else {
                throw new RuntimeException ( "Azione non valida" );
            }

        } catch ( Exception e ) {
            logger.error ( "eccezione non prevista durante la propagazione", e );
            return new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.FAILED,
                "Errore nella preparazione dei dati di invio: " + e.getMessage () );
        }

        try {
            response = facade.aggiornaRiferimentoContabile ( parameters );
        } catch ( Exception e ) {
            logger.error ( "eccezione non prevista durante la propagazione", e );
            return new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.FAILED,
                "Errore nella fase di invio dei dati: " + e.getMessage () );
        }

        try {
            ResultType result = response.getEsito ().getResult ();

            switch ( result.getCodice () ) {
            case RESULT_CODE_OK :
                return new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, result.getMessaggio () );
            default :
                return new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.REJECTED, result.getMessaggio () );
            }

        } catch ( Exception e ) {
            logger.error ( "eccezione non prevista durante la propagazione", e );
            return new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.FAILED,
                "Errore nel formato della risposta: " + e.getMessage () );
        }
    }

    @Override
    public EsitoPropagazioneDatiDTO propagaRiferimentoContabile ( Transazione transazione, Sottoscrittore target, Servizio servizio,
        StoricoRiferimentoContabile corrente,
        AzioneDaPropagare azione ) {

        CoopApplicativaPEC facade;
        AggiornaRiferimentoContabileRequest parameters;
        AggiornaRiferimentoContabileResponse response;

        try {
            facade = getFacade ( target, servizio );

            parameters = mappaStoricoRiferimentoContabile ( corrente );
            parameters.setIdOperazione ( transazione.getIdTransazioneEsterna () );

            if ( azione == AzioneDaPropagare.INSERIMENTO ) {
                parameters.setProtocolloAggiornamentoAzione ( ProtocolloAggiornamentoAzioneType.INSERIMENTO );
            } else if ( azione == AzioneDaPropagare.MODIFICA ) {
                parameters.setProtocolloAggiornamentoAzione ( ProtocolloAggiornamentoAzioneType.MODIFICA );
            } else if ( azione == AzioneDaPropagare.ELIMINAZIONE ) {
                parameters.setProtocolloAggiornamentoAzione ( ProtocolloAggiornamentoAzioneType.CANCELLAZIONE );
            } else {
                throw new RuntimeException ( "Azione non valida" );
            }

        } catch ( Exception e ) {
            logger.error ( "eccezione non prevista durante la propagazione", e );
            return new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.FAILED,
                "Errore nella preparazione dei dati di invio: " + e.getMessage () );
        }

        try {
            response = facade.aggiornaRiferimentoContabile ( parameters );
        } catch ( Exception e ) {
            logger.error ( "eccezione non prevista durante la propagazione", e );
            return new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.FAILED,
                "Errore nella fase di invio dei dati: " + e.getMessage () );
        }

        try {
            ResultType result = response.getEsito ().getResult ();

            switch ( result.getCodice () ) {
            case RESULT_CODE_OK :
                return new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, result.getMessaggio () );
            default :
                return new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.REJECTED, result.getMessaggio () );
            }

        } catch ( Exception e ) {
            logger.error ( "eccezione non prevista durante la propagazione", e );
            return new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.FAILED,
                "Errore nel formato della risposta: " + e.getMessage () );
        }
    }

    private AggiornaRiferimentoContabileRequest mappaStoricoRiferimentoContabile ( StoricoRiferimentoContabile input ) {

        AggiornaRiferimentoContabileRequest output = new AggiornaRiferimentoContabileRequest ();

        RiferimentoContabileType dto = new RiferimentoContabileType ();
        dto.setChiaveIntersistema ( input.getChiaveIntersistema () );
        dto.setAnnoAccertamento ( input.getAnnoAccertamento () );
        dto.setAnnoEsercizio ( input.getAnnoEsercizio () );

        dto.setDatoSpecificoRiscossione (
            ( input.getTipologiaDatoSpecificoRiscossione () != null ? input.getTipologiaDatoSpecificoRiscossione ().getCodice () + "/" : "" ) +
            input.getDatoSpecificoRiscossione () );

        dto.setDescrizioneDatoSpecificoRiscossione ( input.getDescrizioneDatoSpecificoRiscossione () );
        dto.setLivelloPDC ( input.getLivelloPdc () );
        dto.setNumeroAccertamento ( input.getNumeroAccertamento () );
        dto.setNumeroArticolo ( input.getNumeroArticolo () );
        dto.setNumeroCapitolo ( input.getNumeroCapitolo () );
        dto.setTitolo ( input.getTitolo () );

        if ( input.getCodiceVersamento () != null ) {
            dto.setCodiceVersamento ( input.getCodiceVersamento ().getCodice () );
            dto.setCodiceFiscaleEnte ( input.getCodiceVersamento ().getEnte ().getCodiceFiscale () );
        }

        if ( input.getDataFineValidita () != null ) {
            dto.setDataFineValidita ( dateToCalendar ( input.getDataFineValidita () ) );
        }

        if ( input.getDataInizioValidita () != null ) {
            dto.setDataInizioValidita ( dateToCalendar ( input.getDataInizioValidita () ) );
        }

        if ( input.getCategoria () != null ) {
            // DONE : WSDL CAMBIATO
            dto.setCodiceCategoria ( input.getCategoria () );
        }

        if ( input.getTipologia () != null ) {
            // DONE : WSDL CAMBIATO
            dto.setCodiceTipologia ( input.getTipologia () );
        }

        if ( input.getTipologiaDatoSpecificoRiscossione () != null ) {
            switch ( input.getTipologiaDatoSpecificoRiscossione ().getCodice () ) {
            case "9" :
                dto.setTipologiaDatoSpecificoRiscossione ( TipologiaDatoSpecificoRiscossioneType.ENTE );
                break;
            case "2" :
                dto.setTipologiaDatoSpecificoRiscossione ( TipologiaDatoSpecificoRiscossioneType.SIOPE );
                break;
            default :
                throw new RuntimeException ( "Errore nella mappatura: TipologiaDatoSpecificoRiscossione non riconosciuto" );
            }
        }

        output.setRiferimentoContabile ( dto );

        return output;
    }

    @Override
    public EsitoPropagazioneCommitDTO propagaCommit ( Transazione transazione, Sottoscrittore target, Servizio servizio ) {
        CoopApplicativaPEC facade;
        ConfermaOperazioneRequest parameters;
        ConfermaOperazioneResponse response;

        try {
            facade = getFacade ( target, servizio );

            parameters = new ConfermaOperazioneRequest ();
            parameters.setIdOperazione ( transazione.getIdTransazioneEsterna () );

        } catch ( Exception e ) {
            logger.error ( "eccezione non prevista durante la propagazione", e );
            return new EsitoPropagazioneCommitDTO ( EsitoCommitFruitore.FAILED,
                "Errore nella preparazione della richiesta di commit: " + e.getMessage () );
        }

        try {
            response = facade.confermaOperazione ( parameters );
        } catch ( Exception e ) {
            logger.error ( "eccezione non prevista durante la propagazione", e );
            return new EsitoPropagazioneCommitDTO ( EsitoCommitFruitore.FAILED,
                "Errore nella fase di invio del commit: " + e.getMessage () );
        }

        try {
            ResultType result = response.getEsito ().getResult ();

            switch ( result.getCodice () ) {
            case RESULT_CODE_OK :
                return new EsitoPropagazioneCommitDTO ( EsitoCommitFruitore.COMPLETED, result.getMessaggio () );
            default :
                return new EsitoPropagazioneCommitDTO ( EsitoCommitFruitore.REJECTED, result.getMessaggio () );
            }

        } catch ( Exception e ) {
            logger.error ( "eccezione non prevista durante la propagazione", e );
            return new EsitoPropagazioneCommitDTO ( EsitoCommitFruitore.FAILED,
                "Errore nel formato della risposta alla richiesta di commit: " + e.getMessage () );
        }
    }

    @Override
    public EsitoPropagazioneRollbackDTO propagaRollback ( Transazione transazione, Sottoscrittore target, Servizio servizio ) {
        CoopApplicativaPEC facade;
        AnnullaOperazioneRequest parameters;
        AnnullaOperazioneResponse response;

        try {
            facade = getFacade ( target, servizio );

            parameters = new AnnullaOperazioneRequest ();
            parameters.setIdOperazione ( transazione.getIdTransazioneEsterna () );

        } catch ( Exception e ) {
            logger.error ( "eccezione non prevista durante la propagazione", e );
            return new EsitoPropagazioneRollbackDTO ( EsitoRollbackFruitore.FAILED,
                "Errore nella preparazione della richiesta di rollback: " + e.getMessage () );
        }

        try {
            response = facade.annullaOperazione ( parameters );
        } catch ( Exception e ) {
            logger.error ( "eccezione non prevista durante la propagazione", e );
            return new EsitoPropagazioneRollbackDTO ( EsitoRollbackFruitore.FAILED,
                "Errore nella fase di invio del rollback: " + e.getMessage () );
        }

        try {
            ResultType result = response.getEsito ().getResult ();

            switch ( result.getCodice () ) {
            case RESULT_CODE_OK :
                return new EsitoPropagazioneRollbackDTO ( EsitoRollbackFruitore.COMPLETED, result.getMessaggio () );
            default :
                return new EsitoPropagazioneRollbackDTO ( EsitoRollbackFruitore.REJECTED, result.getMessaggio () );
            }

        } catch ( Exception e ) {
            logger.error ( "eccezione non prevista durante la propagazione", e );
            return new EsitoPropagazioneRollbackDTO ( EsitoRollbackFruitore.FAILED,
                "Errore nel formato della risposta alla richiesta di rollback: " + e.getMessage () );
        }
    }

    private CoopApplicativaPEC getFacade ( Sottoscrittore target, Servizio servizio ) {
        CoopApplicativaPEC cachedFacade;
        String endpoint = target.getIndirizzo () + servizio.getWsdl ();

        if ( !facades.containsKey ( endpoint ) ) {
            logger.debug ( "istanzio nuovo servizio all endpoint " + endpoint );

            // Creo nuovo facade
            cachedFacade = new CoopApplicativaPECFacade ( endpoint );

            facades.put ( endpoint, cachedFacade );
        } else {
            cachedFacade = facades.get ( endpoint );
        }

        return cachedFacade;
    }

    private AggiornaEnteRequest mappaEnte ( Ente enteNew, String ibanTesoreria, String bicTesoreria ) {

        AggiornaEnteRequest output = new AggiornaEnteRequest ();

        EnteType dto = new EnteType ();

        dto.setAccertamento ( toPrimitive ( enteNew.getFlagAccertamento () ) );
        dto.setBic ( enteNew.getBic () );
        dto.setCap ( enteNew.getCap () );
        dto.setCbill ( enteNew.getCbill () );
        dto.setCivico ( enteNew.getCivico () );
        dto.setCodiceFiscale ( enteNew.getCodiceFiscale () );
        dto.setDenominazione ( enteNew.getDenominazione () );
        dto.setEmail ( enteNew.getEmail () );
        dto.setEntePlurintermediato ( toPrimitive ( enteNew.getFlagEntePlurintermediato () ) );
        dto.setGiornoSchedulazione ( enteNew.getGiornoSchedulazione () );
        dto.setGs1Gln ( enteNew.getGs1Gln () );
        dto.setIban ( enteNew.getIban () );
        dto.setIndirizzo ( enteNew.getIndirizzo () );
        dto.setLocalita ( enteNew.getLocalita () );
        dto.setQualsiasiCodiceVersamento ( toPrimitive ( enteNew.getFlagQualsiasiCodiceVersamento () ) );
        dto.setRicezioneErrori ( toPrimitive ( enteNew.getFlagRicezioneErrori () ) );
        dto.setRicezioneFlussoBaseRendicontazione ( toPrimitive ( enteNew.getFlagRicezioneFlussoBaseRendicontazione () ) );
        dto.setRiconciliazioneVersamenti ( toPrimitive ( enteNew.getFlagRiconciliazioneVersamenti () ) );
        dto.setSiglaProvincia ( enteNew.getSiglaProvincia () );
        dto.setLogo ( enteNew.getLogoContent () );
        dto.setFlagGestionePpay ( enteNew.isFlagGestionePpay () );
		dto.setFlagAdesioneCittaFacile ( toPrimitive ( enteNew.getFlagAdesioneCittaFacile () ) );
		dto.setCodiceIstat ( enteNew.getCodiceIstat () );
		dto.setTemplateEmailId  ( enteNew.getTemplateEmailId () );
		dto.setUrlDominio  ( enteNew.getUrlDominio () );
		dto.setCodiceIpa ( enteNew.getCodiceIpa () );

        //Ho imposto in EnteType nillable a true ma meglio passare sempre un valore
        dto.setIbanAppoggio ( "" );
        dto.setBicAppoggio ( "" );
        dto.setIbanTesoreria ( ibanTesoreria );
        dto.setBicTesoreria ( bicTesoreria );

        if ( enteNew.getModalitaAcquisizioneProvvisori () != null ) {
            switch ( enteNew.getModalitaAcquisizioneProvvisori ().getCodice () ) {
            case "CAM" :
                dto.setModalitaAcquisizioneProvvisori ( ModalitaAcquisizioneProvvisoriType.CAM );
                break;
            case "CON" :
                dto.setModalitaAcquisizioneProvvisori ( ModalitaAcquisizioneProvvisoriType.CON );
                break;
            case "NES" :
                dto.setModalitaAcquisizioneProvvisori ( ModalitaAcquisizioneProvvisoriType.NES );
                break;
            default :
                throw new RuntimeException ( "Errore nella mappatura: ModalitaAcquisizioneProvvisori non riconosciuta" );
            }
        }

        if ( enteNew.getPeriodicitaSchedulazioneRiconciliazione () != null ) {
            switch ( enteNew.getPeriodicitaSchedulazioneRiconciliazione ().getCodice () ) {
            case "GIO" :
                dto.setPeriodicitaSchedulazioneRiconciliazione ( PeriodicitaSchedulazioneType.GIO );
                break;
            case "SET" :
                dto.setPeriodicitaSchedulazioneRiconciliazione ( PeriodicitaSchedulazioneType.SET );
                break;
            case "MEN" :
                dto.setPeriodicitaSchedulazioneRiconciliazione ( PeriodicitaSchedulazioneType.MEN );
                break;
            case "BIM" :
                dto.setPeriodicitaSchedulazioneRiconciliazione ( PeriodicitaSchedulazioneType.BIM );
                break;
            case "TRI" :
                dto.setPeriodicitaSchedulazioneRiconciliazione ( PeriodicitaSchedulazioneType.TRI );
                break;
            case "QUA" :
                dto.setPeriodicitaSchedulazioneRiconciliazione ( PeriodicitaSchedulazioneType.QUA );
                break;
            case "SEM" :
                dto.setPeriodicitaSchedulazioneRiconciliazione ( PeriodicitaSchedulazioneType.SEM );
                break;
            case "ANN" :
                dto.setPeriodicitaSchedulazioneRiconciliazione ( PeriodicitaSchedulazioneType.ANN );
                break;
            case "SGF" :
                dto.setPeriodicitaSchedulazioneRiconciliazione ( PeriodicitaSchedulazioneType.SGF );
                break;
            default :
                throw new RuntimeException ( "Errore nella mappatura: PeriodicitaSchedulazioneRiconciliazione non riconosciuta" );
            }
        }

        if ( enteNew.getTipologiaAccertamento () != null ) {
            switch ( enteNew.getTipologiaAccertamento ().getCodice () ) {
            case "UNI" :
                dto.setTipologiaAccertamento ( TipologiaAccertamentoType.UNI );
                break;
            case "PIN" :
                dto.setTipologiaAccertamento ( TipologiaAccertamentoType.PIN );
                break;
            default :
                throw new RuntimeException ( "Errore nella mappatura: TipologiaAccertamento non riconosciuta" );
            }
        }

        output.setEnte ( dto );

        CodiciVersamentoType codiciVersamentoDto = new CodiciVersamentoType ();
        ElencoCodiciVersamento elencoCodiciVersamentoType = new ElencoCodiciVersamento ();

        if ( enteNew.getCodiciVersamento () != null && enteNew.getCodiciVersamento ().size () > 0 ) {
            for ( CodiceVersamento codiceVersamento: enteNew.getCodiciVersamento () ) {
                elencoCodiciVersamentoType.getCodiceVersamento ().add ( codiceVersamento.getCodice () );
            }
        } else {
            List<CodiceVersamento> cvs = codiceVersamentoRepository.findByEnte_Id ( enteNew.getId (), new Sort ( "id" ) );
            for ( CodiceVersamento codiceVersamento: cvs ) {
                if ( codiceVersamento.getFlagAnnullato () == null || !codiceVersamento.getFlagAnnullato () ) {
                    elencoCodiciVersamentoType.getCodiceVersamento ().add ( codiceVersamento.getCodice () );
                }
            }
        }

        codiciVersamentoDto.setElencoCodiciVersamento ( elencoCodiciVersamentoType );
        output.setCodiciVersamento ( codiciVersamentoDto );

        return output;
    }

    private AggiornaCodiceVersamentoRequest mappaCodiceVersamento ( Transazione transazione, CodiceVersamento input ) {

        AggiornaCodiceVersamentoRequest output = new AggiornaCodiceVersamentoRequest ();

        CodiceVersamentoType dto = new CodiceVersamentoType ();

        dto.setIbanAppoggio(input.getIbanAppoggio());
        dto.setBicAppoggio(input.getBicAppoggio());
        dto.setFlagCodiceCorrentePostaleAppoggio( toPrimitive ( input.getIbanAppoggioPostale()));
        dto.setFlagCodiceCorrentePostaleTesoreria( toPrimitive ( input.getIbanPostale()));
        dto.setFlagPresenzaBollettinoPostale ( toPrimitive ( input.getFlagPresenzaBollettinoPostale ()) );
        dto.setAnnullato ( toPrimitive ( input.getFlagAnnullato () ) );
        dto.setBic ( input.getBic () );
        dto.setCFEnte ( input.getEnte ().getCodiceFiscale () );
        dto.setCodice ( input.getCodice () );
        dto.setDescrizione ( input.getDescrizione () );
        dto.setEmail ( input.getEmail () );
        dto.setIban ( input.getIban () );
        dto.setInvioFlussi ( toPrimitive ( input.getFlagInvioFlussi () ) );
        dto.setFattura ( toPrimitive ( input.getFattura () ) );
       

        if ( null != input.getModalitaIntegrazione () ) {
            dto.setModalitaDiIntegrazione ( ModalitaDiIntegrazioneType.valueOf ( input.getModalitaIntegrazione ().getCodice () ) );
        }
        
       
        RichiediApplicationIdRequest parameters = new RichiediApplicationIdRequest ();

        parameters.setCodiceFiscaleEnte ( input.getEnte ().getCodiceFiscale () );
        parameters.setIbanCodiceVersamento ( input.getIban () );
        parameters.setIbanEnte ( input.getEnte ().getIban () );
        if (!StringUtils.isBlank(input.getIbanAppoggio())) {
            parameters.setIbanAppoggio(input.getIbanAppoggio());
        }
        parameters.setIdOperazione(transazione.getIdTransazioneEsterna());

        RichiediApplicationIdResponse response = null;

        //String applicationId = calcolaApplicationIdDefault ( input );
        String applicationId = null;

        applicationIdFacade = istanziaApplicationIdFacade ();

        try {
            response = applicationIdFacade.richiediApplicationId ( parameters );

            if ( null != response && null != response.getEsito () && null != response.getEsito ().getResult ()
                            && "000".equalsIgnoreCase ( response.getEsito ().getResult ().getCodice () ) && StringUtils.isNotBlank ( response.getApplicationId () ) ) {
                applicationId = response.getApplicationId ();
            }
        } catch ( Exception e ) {
            logger.warn ( "Recupero application id da MDP fallito, verra' utilizzato quello di default",e);
        }

        dto.setApplicationId ( applicationId );

        if ( input.getVoceEntrata () != null ) {
            dto.setVoceEntrata ( input.getVoceEntrata ().getCodice () );
        }

        if ( input.getModalitaIntegrazione () != null ) {
            switch ( input.getModalitaIntegrazione ().getCodice () ) {
            case "ALL" :
                dto.setModalitaDiIntegrazione ( ModalitaDiIntegrazioneType.ALL );
                break;
            case "PEC" :
                dto.setModalitaDiIntegrazione ( ModalitaDiIntegrazioneType.PEC );
                break;
            case "SRV" :
                dto.setModalitaDiIntegrazione ( ModalitaDiIntegrazioneType.SRV );
                break;
            default :
                throw new RuntimeException ( "Errore nella mappatura: Modalita Integrazione non riconosciuta" );
            }
        }

        if ( input.getTipoPagamento () != null ) {
            switch ( input.getTipoPagamento ().getCodice () ) {
            case "LDC" :
                dto.setTipoPagamento ( TipoPagamentoType.LDC );
                break;
            case "PS" :
                dto.setTipoPagamento ( TipoPagamentoType.PS );
                break;
            case "REDS" :
                dto.setTipoPagamento ( TipoPagamentoType.REDS );
                break;
            case "MABL" :
                dto.setTipoPagamento ( TipoPagamentoType.MABL );
                break;
            case "PABL" :
                dto.setTipoPagamento ( TipoPagamentoType.PABL );
                break;
            default :
                throw new RuntimeException ( "Errore nella mappatura: TipoPagamento non riconosciuto" );
            }
        }

        if (Boolean.TRUE.equals ( input.getFlagMbPrimario () ) )
        {
            dto.setTipoMultibeneficiario ( TipoMultibeneficiarioType.PRIMARIO );
        }
        else if (Boolean.TRUE.equals ( input.getFlagMbSecondario ()))
        {
            dto.setTipoMultibeneficiario ( TipoMultibeneficiarioType.SECONDARIO );
        }
        else
        {
            dto.setTipoMultibeneficiario ( TipoMultibeneficiarioType.ORDINARIO );
        }

        if (!CollectionUtils.isEmpty ( input.getCodiciVersamentoSecondariCollegati () )
                        && Boolean.TRUE.equals ( input.getFlagMbPrimario () ) )
        {

            dto.setCodiceVersamentoSecondario ( input.getCodiciVersamentoSecondariCollegati ().get ( 0 ).getCodice () );
            dto.setCfEnteSecondario (  input.getCodiciVersamentoSecondariCollegati ().get ( 0 ).getEnte ().getCodiceFiscale ()  );
        }
         // EPAY 431
        dto.setFlagInvioNotificatore (  input.getFlagInvioNotificatore () );
        dto.setFlagPersonalizzazioneCov ( input.getFlagPersonalizzazioneCov () );
        dto.setDescrizioneTextCov ( input.getDescrizioneTextCov () );
        dto.setPassphrase ( input.getPassphrase () );
        
        dto.setCredenzialiPnd (  input.getCredenzialiPnd ()  ); 
        dto.setUrlAttualizzazionePnd (   input.getUrlAttualizzazionePnd ()   );

        
//        Visualizzazione da sportello
        dto.setFlagVisualizzaDaSportello ( input.getFlagVisualizzaDaSportello ()!= null? input.getFlagVisualizzaDaSportello (): Boolean.FALSE );

        dto.setDataInizioValidita ( input.getDataInizioValidita () != null? dateToCalendar ( input.getDataInizioValidita () ):null );
        dto.setDataFineValidita ( input.getDataFineValidita () != null? dateToCalendar ( input.getDataFineValidita () ):null );
        
        output.setCodiceVersamento ( dto );


        return output;
    }

    private RichiediApplicationIdFacade istanziaApplicationIdFacade () {

        if ( null == applicationIdFacade ) {
            applicationIdFacade = new RichiediApplicationIdFacade (
                configurazioneService.getParametro ( ParametriConfigurazione.RICHIEDI_APPLICATION_ID_ENDPOINT ).asString () );
        }

        return applicationIdFacade;
    }

	private AggiornaRiferimentoContabileRequest mappaRiferimentoContabile ( RiferimentoContabile input ) {

        AggiornaRiferimentoContabileRequest output = new AggiornaRiferimentoContabileRequest ();

        RiferimentoContabileType dto = new RiferimentoContabileType ();

        dto.setChiaveIntersistema ( input.getChiaveIntersistema () );
        dto.setAnnoAccertamento ( input.getAnnoAccertamento () );
        dto.setAnnoEsercizio ( input.getAnnoEsercizio () );

        dto.setDatoSpecificoRiscossione (
            ( ( input.getCodiceTipologiaDatoSpecificoRiscossione()!= null ) ? input.getCodiceTipologiaDatoSpecificoRiscossione() + "/" : "" ) +
            input.getDatoSpecificoRiscossione () );

        dto.setDescrizioneDatoSpecificoRiscossione (null!= input.getTassonomia()? input.getTassonomia().getDescrTipoServizio(): "" );
        dto.setLivelloPDC ( input.getLivelloPdc () );
        dto.setNumeroAccertamento ( input.getNumeroAccertamento () );
        dto.setNumeroArticolo ( input.getNumeroArticolo () );
        dto.setNumeroCapitolo ( input.getNumeroCapitolo () );
        dto.setTitolo ( input.getTitolo () );

        if ( input.getCodiceVersamento () != null ) {
            dto.setCodiceVersamento ( input.getCodiceVersamento ().getCodice () );
            dto.setCodiceFiscaleEnte ( input.getCodiceVersamento ().getEnte ().getCodiceFiscale () );
        }

        if ( input.getDataFineValidita () != null ) {
            dto.setDataFineValidita ( dateToCalendar ( input.getDataFineValidita () ) );
        }

        if ( input.getDataInizioValidita () != null ) {
            dto.setDataInizioValidita ( dateToCalendar ( input.getDataInizioValidita () ) );
        }

        if ( input.getCategoria () != null ) {
            // DONE : WSDL CAMBIATO
            dto.setCodiceCategoria ( input.getCategoria () );
        }

        if ( input.getTipologia () != null ) {
            // DONE : WSDL CAMBIATO
            dto.setCodiceTipologia ( input.getTipologia () );
        }

        dto.setTipologiaDatoSpecificoRiscossione ( TipologiaDatoSpecificoRiscossioneType.ENTE );

		output.setRiferimentoContabile ( dto );

        return output;
    }

    public static XMLGregorianCalendar dateToCalendar ( Date date ) {
        if ( date == null ) {
            return null;
        }
        GregorianCalendar c = new GregorianCalendar ();
        c.setTime ( date );
        XMLGregorianCalendar date2;
        try {
            date2 = DatatypeFactory.newInstance ().newXMLGregorianCalendar ( c );
        } catch ( DatatypeConfigurationException e ) {
            throw new RuntimeException ( e );
        }
        return date2;
    }

    private boolean toPrimitive ( Boolean raw ) {
        if ( raw == null ) {
            return false;
        } else {
            return raw.booleanValue ();
        }
    }

    @Override
    public EsitoVerificaStatoServizioDTO testStatoServizio ( Sottoscrittore target, Servizio servizio ) {
        CoopApplicativaPEC facade;
        TestResourcesRequest parameters;
        TestResourcesResponse response;

        try {
            facade = getFacade ( target, servizio );
            parameters = new TestResourcesRequest ();

        } catch ( Exception e ) {
            logger.error ( "eccezione non prevista durante la verifica dello stato del servizio", e );
            return new EsitoVerificaStatoServizioDTO ( target, false,
                "Errore nella verifica dello stato del servizio: " + e.getMessage () );
        }

        try {
            response = facade.testResources ( parameters );
        } catch ( Exception e ) {
            logger.error ( "eccezione non prevista durante la verifica dello stato del servizio", e );

            return new EsitoVerificaStatoServizioDTO ( target, false,
                "Errore nella fase di verifica dello stato del servizio: " + e.getMessage () );
        }

        try {
            ResultType result = response.getEsito ().getResult ();

            switch ( result.getCodice () ) {
            case RESULT_CODE_OK :
                return new EsitoVerificaStatoServizioDTO ( target, true, result.getMessaggio () );
            default :
                return new EsitoVerificaStatoServizioDTO ( target, false, "Stato del servizio non atteso: " + result.getMessaggio () );
            }

        } catch ( Exception e ) {
            logger.error ( "eccezione non prevista durante la verifica dello stato del servizio", e );
            return new EsitoVerificaStatoServizioDTO ( target, false,
                "Errore nel formato della risposta della verifica dello stato del servizio: " + e.getMessage () );
        }
    }

    @Override
    @Transactional ( propagation = Propagation.REQUIRES_NEW )
    public void salvaStatoTransazione ( Transazione transazione, List<LogTransazione> statoSottoscrittori ) {
        transazioneRepository.save ( transazione );

        if ( statoSottoscrittori != null ) {
            for ( LogTransazione statoSottoscrittore: statoSottoscrittori ) {
                logTransazioneRepository.save ( statoSottoscrittore );
            }
        }

	}
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim.manager.impl;

import static it.csi.epay.epaysim.business.epaysim.repository.custom.FlussoOrigineErrorePagoPaSpecificationCustom.ricercaFlussoOrigineInErroreSpecification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.xml.transform.StringSource;

import it.csi.epay.epaysim.business.epaysim.builders.SimTFlussoDettaglioPagopaBuilder;
import it.csi.epay.epaysim.business.epaysim.builders.SimTFlussoOrigineErroreBuilder;
import it.csi.epay.epaysim.business.epaysim.builders.SimTFlussoOriginePagopaBuilder;
import it.csi.epay.epaysim.business.epaysim.builders.SimTFlussoSintesiPagopaBuilder;
import it.csi.epay.epaysim.business.epaysim.manager.FlussiManager;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoDettaglioPagopa;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoOrigineErrore;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoOriginePagopa;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoSintesiPagopa;
import it.csi.epay.epaysim.business.epaysim.repository.RicercaFlussoErroreRepository;
import it.csi.epay.epaysim.business.epaysim.repository.SimTFlussoDettaglioPagopaRepository;
import it.csi.epay.epaysim.business.epaysim.repository.SimTFlussoOrigineErroreRepository;
import it.csi.epay.epaysim.business.epaysim.repository.SimTFlussoOriginePagopaRepository;
import it.csi.epay.epaysim.business.epaysim.repository.SimTFlussoSintesiPagopaRepository;
import it.csi.epay.epaysim.business.epaysim.utils.Costanti;
import it.csi.epay.epaysim.business.epaysim.utils.MapperDTOUtils;
import it.csi.epay.epaysim.dto.FlussoDettaglioPagopaDTO;
import it.csi.epay.epaysim.dto.FlussoDettaglioPagopaOutputDTO;
import it.csi.epay.epaysim.dto.FlussoOrigineErrorePagopaOutputDTO;
import it.csi.epay.epaysim.dto.FlussoOriginePagopaDTO;
import it.csi.epay.epaysim.dto.FlussoOriginePagopaOutputDTO;
import it.csi.epay.epaysim.dto.FlussoSintesiPagopaDTO;
import it.csi.epay.epaysim.dto.FlussoSintesiPagopaOutputDTO;
import it.csi.epay.epaysim.dto.RicercaFlussoErroreInputDTO;
import it.csi.epay.epaysim.dto.RicercaFlussoInputDTO;
import it.csi.epay.epaysim.dto.RicercaFlussoOutputResponse;
import it.csi.epay.epaysim.dto.RicercaProvvisorioInputDTO;
import it.csi.epay.epaysim.dto.RicercaProvvisorioOutputDTO;
import it.csi.epay.epaysim.dto.flussi.riconciliazioneversamentipsptypes.FlussiInErroreType;
import it.csi.epay.epaysim.dto.flussi.riconciliazioneversamentipsptypes.FlussoDettaglioType;
import it.csi.epay.epaysim.dto.flussi.riconciliazioneversamentipsptypes.FlussoRiconciliazione;
import it.csi.epay.epaysim.dto.flussi.riconciliazioneversamentipsptypes.FlussoSintesiType;
import it.csi.epay.epaysim.dto.flussi.riconciliazioneversamentipsptypes.ResultType;
import it.csi.epay.epaysim.dto.flussi.riconciliazioneversamentipsptypes.TestataFlussoRiconciliazioneType;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.EsitoFlussiPagoPAResponse;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.StatoElaborazioneFlussoType;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.TestataTrasmissioneFlussiType;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.TrasmettiFlussiInErrorePagoPARequest;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.TrasmettiFlussiPagoPARequest;
import it.csi.epay.epaysim.exception.epaysim.EpaysimulatorException;
import it.csi.epay.epaysim.exception.epaysim.UnrecoverableException;
import it.csi.epay.epaysim.interfacews.epaysim.CostantiErrori;
import it.csi.epay.epaysim.util.LogConfig;


/**
 *
 */
@Service
public class FlussiManagerImpl implements FlussiManager {

    private static final Logger LOGGER = Logger.getLogger ( LogConfig.HANDLER_SERVICES );

    @Autowired
    private SimTFlussoOrigineErroreRepository simTFlussoOrigineErroreRepository;

    @Autowired
    private SimTFlussoDettaglioPagopaRepository simTFlussoDettaglioPagopaRepository;

    @Autowired
    private SimTFlussoSintesiPagopaRepository simTFlussoSintesiPagopaRepository;

    @Autowired
    private SimTFlussoOriginePagopaRepository simTFlussoOriginePagopaRepository;

    @Autowired
    private RicercaFlussoErroreRepository ricercaFlussoErroreRepository;

    @Autowired
    private Jaxb2Marshaller jaxb2Marshaller;

    @Override
    @Transactional ( rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW )
    public SimTFlussoOrigineErrore salvaFlussoInErrore ( TrasmettiFlussiInErrorePagoPARequest trasmettiFlussiInErrorePagoPARequest,
        FlussiInErroreType flussiInErroreType ) {
        SimTFlussoOrigineErrore newflussoOrigine = creaSimTFlussoOrigineErrore ( flussiInErroreType, trasmettiFlussiInErrorePagoPARequest );
        // eliminato aggiornamento su flusso in errore. Deve andare sempre in inserimento come da richiesta.
        //        SimTFlussoOrigineErrore oldflussoOrigine = simTFlussoOrigineErroreRepository.findOneByIdentificativoFlusso ( newflussoOrigine.getIdentificativoFlusso () );
        //        //se presente aggiorna.
        //        if ( null != oldflussoOrigine ) {
        //            newflussoOrigine.setId ( oldflussoOrigine.getId () );
        //    }
        return simTFlussoOrigineErroreRepository.save ( newflussoOrigine );
    }

    @Override
    public SimTFlussoOriginePagopa salvaTestataFlussoRiconciliazione ( TestataFlussoRiconciliazioneType testataFlussoRiconciliazioneType )
                    throws EpaysimulatorException {

        SimTFlussoOriginePagopa newTestata = createSimTFlussoOriginePagopa ( testataFlussoRiconciliazioneType );
        SimTFlussoOriginePagopa oldTestata = simTFlussoOriginePagopaRepository.findOneByIdentificativoFlusso ( newTestata.getIdentificativoFlusso () );
        //aggiornamento
        if ( null != oldTestata ) {
            //cancella figli e nipoti.
            SimTFlussoOriginePagopa testataCompleta = simTFlussoOriginePagopaRepository.findOneWithchildren ( oldTestata.getId () );
            for ( SimTFlussoSintesiPagopa simTFlussoSintesiPagopa: testataCompleta.getSimTFlussoSintesiPagopas () ) {
                simTFlussoDettaglioPagopaRepository.deleteInBatch ( simTFlussoSintesiPagopa.getSimTFlussoDettaglioPagopas () );
            }
            simTFlussoSintesiPagopaRepository.deleteInBatch ( testataCompleta.getSimTFlussoSintesiPagopas () );

            simTFlussoOriginePagopaRepository.delete ( testataCompleta );
        }
        return simTFlussoOriginePagopaRepository.save ( newTestata );
    }

    @Override
    @Transactional ( rollbackFor = Exception.class, propagation = Propagation.REQUIRED )
    public SimTFlussoSintesiPagopa salvaRigaFlussoRiconciliazione ( TrasmettiFlussiPagoPARequest trasmettiFlussiPagoPARequest,
        FlussoSintesiType flussoSintesiType, SimTFlussoOriginePagopa simTFlussoOriginePagopa ) {
        //creazioe sim_t_flusso_sintesi_pagopa
        SimTFlussoSintesiPagopa simTFlussoSintesiPagopa = simTFlussoSintesiPagopaRepository
                        .save ( creaSimTFlussoSintesiPagopa ( flussoSintesiType, trasmettiFlussiPagoPARequest, simTFlussoOriginePagopa ) );
        if ( null != flussoSintesiType.getRigheDettaglio () ) {
            for ( FlussoDettaglioType flussoDettaglioType: flussoSintesiType.getRigheDettaglio ().getSingolaRigaDettaglio () ) {
                //creazioe sim_t_flusso_dettaglio_pagopa
                simTFlussoDettaglioPagopaRepository
                .save ( creaSimTFlussoDettaglioPagopa ( flussoSintesiType, simTFlussoSintesiPagopa, flussoDettaglioType ) );
            }
        }
        return simTFlussoSintesiPagopa;
    }

    @Override
    @Transactional ( readOnly = true )
    public EsitoFlussiPagoPAResponse esitoFlussiPagoPA ( TestataTrasmissioneFlussiType testataTrasmissioneFlussiType )
                    throws EpaysimulatorException {//, UnrecoverableException, Exception;, UnrecoverableException, Exception;, UnrecoverableException, Exception {

        EsitoFlussiPagoPAResponse answer = new EsitoFlussiPagoPAResponse ();

        ResultType value = new ResultType ();
        value.setCodice ( "000" );
        value.setMessaggio ( "Elaborazione completata con successo" );

        SimTFlussoOriginePagopa simTFlussoOriginePagopa
        = simTFlussoOriginePagopaRepository.findOneByIdentificativoFlusso ( testataTrasmissioneFlussiType.getIdentificativoFlusso () );

        if ( null != simTFlussoOriginePagopa ) {

            Boolean item = simTFlussoOriginePagopa.getStatoElaborazioneFlusso ();
            if ( item == null )
                answer.setStatoElaborazioneFlusso ( StatoElaborazioneFlussoType.DA_ELABORARE );
            else if ( item.equals ( Boolean.TRUE ) )
                answer.setStatoElaborazioneFlusso ( StatoElaborazioneFlussoType.ELABORATO );
            else if ( item.equals ( Boolean.FALSE ) )
                answer.setStatoElaborazioneFlusso ( StatoElaborazioneFlussoType.IN_ERRORE );
        } else {
            value.setCodice ( "200" );
            value.setMessaggio ( String.format ( "Flusso %s non trovato", testataTrasmissioneFlussiType.getIdentificativoFlusso () ) );
        }

        answer.setResult ( value );

        return answer;
    }

    @Override
    @Transactional ( readOnly = true )
    public RicercaFlussoOutputResponse ricercaFlusso ( RicercaFlussoInputDTO ricercaFlussoInputDTO )
                    throws EpaysimulatorException, DatatypeConfigurationException {//, UnrecoverableException, Exception;, UnrecoverableException, Exception {

        RicercaFlussoOutputResponse answer = new RicercaFlussoOutputResponse ();
        answer.setCodiceEsito ( Costanti.RESULT_CODE_OPERATION_KO );
        answer.setCodiceStato ( Integer.valueOf ( CostantiErrori.ERRORE_DI_SISTEMA ) );
        answer.setDescrizioneEsito ( Costanti.RESULT_CODE_INTERNAL_ERROR );

        List<SimTFlussoOriginePagopa> simTFlussoOriginePagopa = simTFlussoOriginePagopaRepository.findAll ( ricercaFlussoInputDTO );

        if ( simTFlussoOriginePagopa.size () <= 0 ) {
            answer.setCodiceEsito ( CostantiErrori.WS_ESITO_OK_NO_RESULT );
            answer.setCodiceStato ( Integer.valueOf ( CostantiErrori.WS_ESITO_OK_NO_RESULT ) );
            answer.setDescrizioneEsito ( Costanti.RESULT_CODE_ITEM_NOT_FOUND );
        } else {
            for ( SimTFlussoOriginePagopa flussoOrigine: simTFlussoOriginePagopa ) {
                FlussoOriginePagopaOutputDTO flussoOriginePagopaOutputDTO = MapperDTOUtils.trasformaInFlussoOriginePagopaOutputDTO ( flussoOrigine );
                answer.getTestata ().add ( flussoOriginePagopaOutputDTO );
            }

            answer.setCodiceEsito ( CostantiErrori.WS_ESITO_OK_DEFAULT );
            answer.setCodiceStato ( Integer.valueOf ( CostantiErrori.WS_ESITO_OK_DEFAULT ) );
            answer.setDescrizioneEsito ( Costanti.RESULT_CODE_OK );
        }

        return answer;
    }

    @Override
    @Transactional ( rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW )
    public void updateEsitoStatoElaborazioneFlussoSingolo ( Long idFlusso, String cf_utente, Boolean nuovoStato )
                    throws EpaysimulatorException, UnrecoverableException, Exception {
        SimTFlussoOriginePagopa flusso = simTFlussoOriginePagopaRepository.findOne ( idFlusso );
        if ( null != flusso ) {
            LOGGER.info (
                String.format ( "Flusso con id: %s TROVATO! Aggiornamento stato in corso.", flusso.getId () ) );
            flusso.setStatoElaborazioneFlusso ( nuovoStato );
            flusso.setDataModifica ( new Date () );
            flusso.setUtenteModifica ( cf_utente );
            simTFlussoOriginePagopaRepository.save ( flusso );
        } else {
            LOGGER.info ( String.format ( "Flusso con id: %s NON TROVATO!", idFlusso ) );
            throw new EpaysimulatorException ( CostantiErrori.FLUSSI_NON_PRESENTI, String.format ( "Flusso con id: %s NON TROVATO!", idFlusso ) );
        }
    }

    /**
     * Metodo che mi permette di creare la entity jpa a partire dai parametrid i input del servizio.
     *
     * @param flussiInErroreType input
     * @param trasmettiFlussiInErrorePagoPARequest input
     * @return SimTFlussoOrigineErrore appena creato.
     */
    private SimTFlussoOrigineErrore creaSimTFlussoOrigineErrore ( FlussiInErroreType flussiInErroreType,
        TrasmettiFlussiInErrorePagoPARequest trasmettiFlussiInErrorePagoPARequest ) {
        byte [] base64Bytes = trasmettiFlussiInErrorePagoPARequest.getFlussiInErrore ();
        return new SimTFlussoOrigineErroreBuilder ()
                        .withIdentificativoUnivocoRegolamento ( flussiInErroreType.getIdentificativoUnivocoRegolamento () )
                        .withIdentificativoFlusso ( flussiInErroreType.getIdentificativoFlusso () )
                        .withCodiceErrore ( flussiInErroreType.getCodiceErrore () )
                        .withDescrizioneErrore ( flussiInErroreType.getDescrizioneErrore () )
                        .withCodiceVersamento ( flussiInErroreType.getCodiceVersamento () )
                        .withIdMessaggio ( trasmettiFlussiInErrorePagoPARequest.getTestataFlussiInErrore ().getIdMessaggio () )
                        .withDataRegolamento (
                            ( null != flussiInErroreType.getDataRegolamento () )
                            ? new Date ( flussiInErroreType.getDataRegolamento ().toGregorianCalendar ().getTime ().getTime () )
                                            : null )
                        .withIdentificativoPsp ( flussiInErroreType.getIdPsp () )
                        .withNumeroTotalePagamenti (
                            ( null != flussiInErroreType.getNumeroTotalePagamentiFlusso () ) ? flussiInErroreType.getNumeroTotalePagamentiFlusso ().intValue ()
                                            : null )
                        .withImportoTotalePagamenti ( flussiInErroreType.getImportoTotalePagamentiFlusso () )
                        .withDataoraFlusso ( ( null != trasmettiFlussiInErrorePagoPARequest.getTestataFlussiInErrore () )
                            ? new Date ( trasmettiFlussiInErrorePagoPARequest.getTestataFlussiInErrore ().getDataOra ().toGregorianCalendar ()
                                .getTime ().getTime () )
                                            : null )
                        .withDataInserimento ( new Date () )
                        .withXmlFlusso ( new String ( base64Bytes ) )
                        .withImportoTotalePagamentiIntermediati ( flussiInErroreType.getImportoTotalePagamentiIntermediati () )
                        .withNumeroTotalePagamentiIntermediati ( ( null != flussiInErroreType.getNumeroTotalePagamentiIntermediati () )
                            ? flussiInErroreType.getNumeroTotalePagamentiIntermediati ().intValue () : null )
                        .withCfEnteRicevente ( trasmettiFlussiInErrorePagoPARequest.getTestataFlussiInErrore ().getCFEnteCreditore () )
                        .withUtenteInserimento ( Costanti.UTENTE_TECNICO )
                        .withUtenteModifica ( Costanti.UTENTE_TECNICO )
                        .withDataModifica ( new Date () )
                        .withDataInserimento ( new Date () )
                        .build ();
    }

    /**
     * Metodo che mi permette di creare la entity jpa a partire dai parametrid i input del servizio.
     *
     * @param flussoSintesiType input
     * @param simTFlussoSintesiPagopa relazione
     * @param flussoDettaglioType input
     * @return SimTFlussoDettaglioPagopa entity appena creata.
     */
    private SimTFlussoDettaglioPagopa creaSimTFlussoDettaglioPagopa ( FlussoSintesiType flussoSintesiType, SimTFlussoSintesiPagopa simTFlussoSintesiPagopa,
        FlussoDettaglioType flussoDettaglioType ) {
        return new SimTFlussoDettaglioPagopaBuilder ()
                        .withAnagraficaPagatore ( ( null != flussoDettaglioType.getAnagraficaPagatore ().getPersonaFisica () )
                            ? flussoDettaglioType.getAnagraficaPagatore ().getPersonaFisica ().getNome ()
                                            + " " + flussoDettaglioType.getAnagraficaPagatore ().getPersonaFisica ().getCognome ()
                                            : flussoDettaglioType.getAnagraficaPagatore ().getPersonaGiuridica ().getRagioneSociale () )
                        .withCausale ( flussoDettaglioType.getCausale () )
                        .withDataPagamento ( ( null != flussoDettaglioType.getDataPagamento () )
                            ? flussoDettaglioType.getDataPagamento ().toGregorianCalendar ().getTime () : null )
                        .withDescrizioneCausaleVersamento ( flussoDettaglioType.getDescrizioneCausaleVersamento () )
                        .withEsitoPagamento ( flussoDettaglioType.getEsitoPagamento () )
                        .withIdentificativoUnicoRiscossione ( flussoDettaglioType.getIdentificativoUnivocoRiscossione () )
                        .withIdentificativoUnicoVersamento ( flussoDettaglioType.getIdentificativoUnivocoVersamento () )
                        .withImportoSingoloVersamento ( flussoDettaglioType.getImportoSingoloVersamento () )
                        .withIndiceSingoloVersamento ( flussoDettaglioType.getIndiceSingoloVersamento () )
                        .withSimTFlussoSintesiPagopa ( simTFlussoSintesiPagopa )
                        .withTransactionid ( flussoDettaglioType.getTransactionid () )
                        .withUtenteInserimento ( Costanti.UTENTE_TECNICO )
                        .withUtenteModifica ( Costanti.UTENTE_TECNICO )
                        .withDataInserimento ( new Date () )
                        .withDataModifica ( new Date () )
                        .build ();
    }

    /**
     * Metodo che mi permette di creare la entity jpa a partire dai parametrid i input del servizio
     *
     * @param flussoSintesiType input
     * @param trasmettiFlussiPagoPARequest input
     * @param ente ente di riferimento
     * @param simTFlussoOriginePagopa tabella di relazione
     * @return SimTFlussoSintesiPagopa entity appena creata
     */
    private SimTFlussoSintesiPagopa creaSimTFlussoSintesiPagopa ( FlussoSintesiType flussoSintesiType,
        TrasmettiFlussiPagoPARequest trasmettiFlussiPagoPARequest, SimTFlussoOriginePagopa simTFlussoOriginePagopa ) {
        return new SimTFlussoSintesiPagopaBuilder ()
                        .withAccertamentoAnno ( flussoSintesiType.getAccertamentoAnno () )
                        .withAccertamentoNumero ( flussoSintesiType.getAccertamentoNro () )
                        .withArticolo ( ( null != flussoSintesiType.getArticolo () ) ? new BigDecimal ( flussoSintesiType.getArticolo () ) : null )
                        .withCapitolo ( ( null != flussoSintesiType.getCapitolo () ) ? new String ( flussoSintesiType.getCapitolo () ) : null )
                        .withCodiceVersamento ( flussoSintesiType.getCodiceVersamento () )
                        .withDatiSpecificiDiRiscossione ( flussoSintesiType.getDatiSpecificiDiRiscossione () )
                        .withDescrizioneCodiceVersamento ( flussoSintesiType.getDescrizioneCodiceVersamento () )
                        .withDescrizioneDatiSpecifici ( flussoSintesiType.getDescrizioneDatiSpecifici () )
                        .withImportoQuotaAggregazione ( flussoSintesiType.getImportoQuotaAggregazione () )
                        .withMacrotipo ( flussoSintesiType.getMacrotipo () )
                        .withNumeroPagamentiAggregazione ( new BigDecimal ( flussoSintesiType.getNroPagamentiAggregazione () ) )
                        .withPdc ( flussoSintesiType.getPdC () )
                        .withProgressivoFlussoSintetico ( flussoSintesiType.getProgressivoFlussoSintetico () )
                        .withSimTFlussoOriginePagopa ( simTFlussoOriginePagopa )
                        .withTematica ( flussoSintesiType.getTematica () )
                        .withUtenteInserimento ( Costanti.UTENTE_TECNICO )
                        .withUtenteModifica ( Costanti.UTENTE_TECNICO )
                        .withDataInserimento ( new Date () )
                        .withDataModifica ( new Date () )
                        .build ();
    }

    /**
     * Metodo che mi permette di creare la entity jpa a partire dai parametrid i input del servizio
     *
     * @param trasmettiFlussiPagoPARequest input
     * @param ente ente di riferimento
     * @return SimTFlussoOriginePagopa entity appena creata.
     */
    private SimTFlussoOriginePagopa createSimTFlussoOriginePagopa ( TestataFlussoRiconciliazioneType testata ) {
        return new SimTFlussoOriginePagopaBuilder ()
                        .withCfEnteCreditore ( testata.getCFEnteCreditore () )
                        .withDataOraMessaggio ( testata.getDataOraMessaggio () )
                        .withDataRegolamento ( testata.getDataRegolamento () )
                        .withDenominazioneEnte ( testata.getDenominazioneEnte () )
                        .withDenominazionePsp ( testata.getDenominazionePSP () )
                        .withIdentificativoFlusso ( testata.getIdentificativoFlusso () )
                        .withIdentificativoPsp ( testata.getIdPSP () )
                        .withIdentificativoUnivocoRegolamento ( testata.getIdentificativoUnivocoRegolamento () )
                        .withIdMessaggio ( testata.getIdMessaggio () )
                        .withImportoTotalePagamenti ( testata.getImportoTotalePagamentiFlusso () )
                        .withImportoTotalePagamentiIntermediati ( testata.getImportoTotalePagamentiIntermediati () )
                        .withNumeroTotalePagamenti ( null != testata.getNumeroTotalePagamentiFlusso () ? testata.getNumeroTotalePagamentiFlusso ().intValue () : null )
                        .withNumeroTotalePagamentiIntermediati (
                            null != testata.getNumeroTotalePagamentiIntermediati () ? testata.getNumeroTotalePagamentiIntermediati ().intValue () : null )
                        .withProvvisorioAnno ( testata.getProvvisorioAnno () )
                        .withProvvisorioNumero ( testata.getProvvisorioNumero () )
                        .withUtenteInserimento ( Costanti.UTENTE_TECNICO )
                        .withUtenteModifica ( Costanti.UTENTE_TECNICO )
                        .withDataInserimento ( new Date () )
                        .withDataModifica ( new Date () )
                        .build ();
    }


    @Override
    @Transactional
    public List<FlussoOrigineErrorePagopaOutputDTO> ricercaFlussiInErrore ( RicercaFlussoErroreInputDTO inputRicercaFlussoErrore )
                    throws EpaysimulatorException {
        List<SimTFlussoOrigineErrore> simTFlussoOrigineErrore = new ArrayList<SimTFlussoOrigineErrore> ();
        List<FlussoOrigineErrorePagopaOutputDTO> flussoOrigineErrorePagopaOutputDTO = new ArrayList<FlussoOrigineErrorePagopaOutputDTO> ();
        FlussoOrigineErrorePagopaOutputDTO OrigineErrorePagopaOutputDTO = null;
        try {
            simTFlussoOrigineErrore = ricercaFlussoErroreRepository.findAll ( ricercaFlussoOrigineInErroreSpecification ( inputRicercaFlussoErrore ) );

            for ( SimTFlussoOrigineErrore simTFlussoOrigineErr: simTFlussoOrigineErrore ) {
                OrigineErrorePagopaOutputDTO = MapperDTOUtils.trasformaInFlussoOrigineErrorePagopaOutputDTO ( simTFlussoOrigineErr );
                flussoOrigineErrorePagopaOutputDTO.add ( OrigineErrorePagopaOutputDTO );
            }

        } catch ( Exception e ) {
            LOGGER.info ( String.format ( "Errore durante la ricerca del flusso in errore con id: %s NON TROVATO!", inputRicercaFlussoErrore.getId () ) );
            throw new EpaysimulatorException ( CostantiErrori.WS_ESITO_KO_DEFAULT,
                String.format ( "Errore durante la ricerca del flusso in errore con id: %s NON TROVATO!", inputRicercaFlussoErrore.getId () ) );
        }

        return flussoOrigineErrorePagopaOutputDTO;
    }

    @Override
    public RicercaProvvisorioOutputDTO ricercaProvvisori ( RicercaProvvisorioInputDTO inputRicercaProvvisorio )
                    throws EpaysimulatorException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional ( readOnly = true )
    public FlussoOriginePagopaOutputDTO ricercaFlussoOriginePagoPa ( FlussoOriginePagopaDTO flussoOriginePagopaDTO ) {

        FlussoOriginePagopaOutputDTO flussoOriginePagopaOutputDTO = new FlussoOriginePagopaOutputDTO ();
        flussoOriginePagopaOutputDTO.setCodiceEsito ( Costanti.RESULT_CODE_OPERATION_KO );
        flussoOriginePagopaOutputDTO.setCodiceStato ( Integer.valueOf ( CostantiErrori.ERRORE_DI_SISTEMA ) );
        flussoOriginePagopaOutputDTO.setDescrizioneEsito ( Costanti.RESULT_CODE_INTERNAL_ERROR );

        SimTFlussoOriginePagopa simTFlussoOriginePagopa = new SimTFlussoOriginePagopa ();

        simTFlussoOriginePagopa = simTFlussoOriginePagopaRepository.findOne ( flussoOriginePagopaDTO.getId () );
        List<SimTFlussoSintesiPagopa> listaSimTFlussoSintesiPagopa = new ArrayList<SimTFlussoSintesiPagopa> ();
        if ( null != simTFlussoOriginePagopa ) {
            flussoOriginePagopaOutputDTO = MapperDTOUtils.trasformaInFlussoOriginePagopaOutputDTO ( simTFlussoOriginePagopa );
            listaSimTFlussoSintesiPagopa = simTFlussoSintesiPagopaRepository.findAllBySimTFlussoOriginePagopa_id ( simTFlussoOriginePagopa.getId () );

            FlussoSintesiPagopaOutputDTO flussoSintesiPagopaOutputDTO = null;
            List<FlussoSintesiPagopaOutputDTO> listaFlussoSintesiPagopaOutputDTO = new ArrayList<FlussoSintesiPagopaOutputDTO> ();
            if ( null != listaSimTFlussoSintesiPagopa ) {
                for ( SimTFlussoSintesiPagopa simTFlussoSintesiPagopa: listaSimTFlussoSintesiPagopa ) {
                    flussoSintesiPagopaOutputDTO = new FlussoSintesiPagopaOutputDTO ();
                    flussoSintesiPagopaOutputDTO = MapperDTOUtils.trasformaInFlussoSintesiPagopaOutputDTO ( simTFlussoSintesiPagopa );
                    listaFlussoSintesiPagopaOutputDTO.add ( flussoSintesiPagopaOutputDTO );
                }
                flussoOriginePagopaOutputDTO.setListFlussoSintesiPagopaOutputDTO ( listaFlussoSintesiPagopaOutputDTO );

                flussoOriginePagopaOutputDTO.setCodiceEsito ( CostantiErrori.WS_ESITO_OK_DEFAULT );
                flussoOriginePagopaOutputDTO.setCodiceStato ( Integer.valueOf ( CostantiErrori.WS_ESITO_OK_DEFAULT ) );
                flussoOriginePagopaOutputDTO.setDescrizioneEsito ( Costanti.RESULT_CODE_OK );
            }
        } else {
            flussoOriginePagopaOutputDTO.setCodiceEsito ( CostantiErrori.WS_ESITO_OK_NO_RESULT );
            flussoOriginePagopaOutputDTO.setCodiceStato ( Integer.valueOf ( CostantiErrori.WS_ESITO_OK_NO_RESULT ) );
            flussoOriginePagopaOutputDTO.setDescrizioneEsito ( Costanti.RESULT_CODE_ITEM_NOT_FOUND );
        }

        return flussoOriginePagopaOutputDTO;
    }

    @Override
    @Transactional ( readOnly = true )
    public FlussoDettaglioPagopaOutputDTO ricercaFlussoDettaglioPagoPa ( FlussoDettaglioPagopaDTO inputFlussoDettaglioPagopaDTO ) {
        SimTFlussoDettaglioPagopa flussoDettaglioPagopa = new SimTFlussoDettaglioPagopa ();
        FlussoDettaglioPagopaOutputDTO flussoDettaglioPagopaOutputDTO = new FlussoDettaglioPagopaOutputDTO ();
        flussoDettaglioPagopaOutputDTO.setCodiceEsito ( Costanti.RESULT_CODE_OPERATION_KO );
        flussoDettaglioPagopaOutputDTO.setCodiceStato ( Integer.valueOf ( CostantiErrori.ERRORE_DI_SISTEMA ) );
        flussoDettaglioPagopaOutputDTO.setDescrizioneEsito ( Costanti.RESULT_CODE_INTERNAL_ERROR );

        flussoDettaglioPagopa = simTFlussoDettaglioPagopaRepository.findOne ( inputFlussoDettaglioPagopaDTO.getId () );
        if ( null != flussoDettaglioPagopa ) {
            flussoDettaglioPagopaOutputDTO = MapperDTOUtils.trasformaInFlussoDettaglioPagopaOutputDTO ( flussoDettaglioPagopa );
            flussoDettaglioPagopaOutputDTO.setCodiceEsito ( CostantiErrori.WS_ESITO_OK_DEFAULT );
            flussoDettaglioPagopaOutputDTO.setCodiceStato ( Integer.valueOf ( CostantiErrori.WS_ESITO_OK_DEFAULT ) );
            flussoDettaglioPagopaOutputDTO.setDescrizioneEsito ( Costanti.RESULT_CODE_OK );
        } else {
            flussoDettaglioPagopaOutputDTO.setCodiceEsito ( CostantiErrori.WS_ESITO_OK_NO_RESULT );
            flussoDettaglioPagopaOutputDTO.setCodiceStato ( Integer.valueOf ( CostantiErrori.WS_ESITO_OK_NO_RESULT ) );
            flussoDettaglioPagopaOutputDTO.setDescrizioneEsito ( Costanti.RESULT_CODE_ITEM_NOT_FOUND );
        }

        return flussoDettaglioPagopaOutputDTO;
    }

    @Override
    @Transactional ( readOnly = true )
    public FlussoSintesiPagopaOutputDTO ricercaFlussoSintesiPagoPa ( FlussoSintesiPagopaDTO inputFlussoSintesiPagopaDTO ) {

        FlussoSintesiPagopaOutputDTO flussoSintesiPagopaOutputDTO = new FlussoSintesiPagopaOutputDTO ();
        flussoSintesiPagopaOutputDTO.setCodiceEsito ( Costanti.RESULT_CODE_OPERATION_KO );
        flussoSintesiPagopaOutputDTO.setCodiceStato ( Integer.valueOf ( CostantiErrori.ERRORE_DI_SISTEMA ) );
        flussoSintesiPagopaOutputDTO.setDescrizioneEsito ( Costanti.RESULT_CODE_INTERNAL_ERROR );

        SimTFlussoSintesiPagopa flussoSintesi = new SimTFlussoSintesiPagopa ();

        List<SimTFlussoDettaglioPagopa> listaFlussoDettaglioPagopa = new ArrayList<SimTFlussoDettaglioPagopa> ();

        flussoSintesi = simTFlussoSintesiPagopaRepository.findOne ( inputFlussoSintesiPagopaDTO.getId () );

        if ( null != flussoSintesi ) {

            flussoSintesiPagopaOutputDTO = MapperDTOUtils.trasformaInFlussoSintesiPagopaOutputDTO ( flussoSintesi );
            flussoSintesiPagopaOutputDTO.setCodiceEsito ( CostantiErrori.WS_ESITO_OK_DEFAULT );
            flussoSintesiPagopaOutputDTO.setCodiceStato ( Integer.valueOf ( CostantiErrori.WS_ESITO_OK_DEFAULT ) );
            flussoSintesiPagopaOutputDTO.setDescrizioneEsito ( Costanti.RESULT_CODE_OK );

            FlussoDettaglioPagopaOutputDTO flussoDettaglio = null;

            List<FlussoDettaglioPagopaOutputDTO> listaDettaglioPagopa = new ArrayList<FlussoDettaglioPagopaOutputDTO> ();

            listaFlussoDettaglioPagopa = simTFlussoDettaglioPagopaRepository.findAllBySimTFlussoSintesiPagopa_Id ( flussoSintesi.getId () );
            if ( null != listaFlussoDettaglioPagopa ) {
                for ( SimTFlussoDettaglioPagopa simTFlussoDettaglioPagopa: listaFlussoDettaglioPagopa ) {
                    flussoDettaglio = new FlussoDettaglioPagopaOutputDTO ();
                    flussoDettaglio = MapperDTOUtils.trasformaInFlussoDettaglioPagopaOutputDTO ( simTFlussoDettaglioPagopa );
                    listaDettaglioPagopa.add ( flussoDettaglio );
                }
                flussoSintesiPagopaOutputDTO.setListFlussoDettaglioPagopaOutputDTO ( listaDettaglioPagopa );
            }
        } else {
            flussoSintesiPagopaOutputDTO.setCodiceEsito ( CostantiErrori.WS_ESITO_OK_NO_RESULT );
            flussoSintesiPagopaOutputDTO.setCodiceStato ( Integer.valueOf ( CostantiErrori.WS_ESITO_OK_NO_RESULT ) );
            flussoSintesiPagopaOutputDTO.setDescrizioneEsito ( Costanti.RESULT_CODE_ITEM_NOT_FOUND );
        }

        return flussoSintesiPagopaOutputDTO;
    }

    @Override
    public int salvaFlussoRiconciliazione ( TrasmettiFlussiPagoPARequest trasmettiFlussiPagoPARequest )
                    throws EpaysimulatorException, UnrecoverableException, Exception {
        FlussoRiconciliazione flussoRiconciliazioneType = unmarshal ( trasmettiFlussiPagoPARequest );
        Assert.notNull ( flussoRiconciliazioneType, "Oggetto flussoRiconciliazioneType null!" );
        Assert.notNull ( flussoRiconciliazioneType.getTestataFlusso (), "Oggetto flussoRiconciliazioneType null!" );
        Assert.notNull ( flussoRiconciliazioneType.getRigheSintesi (), "Oggetto righeSintesi null!" );
        SimTFlussoOriginePagopa simTFlussoOriginePagopa = salvaTestataFlussoRiconciliazione ( flussoRiconciliazioneType.getTestataFlusso () );
        for ( FlussoSintesiType flussoSintesiType: flussoRiconciliazioneType.getRigheSintesi ().getSingolaRigaSintesi () ) {
            try {
                //creazione riga di sintesi con relativo dettaglio
                salvaRigaFlussoRiconciliazione ( trasmettiFlussiPagoPARequest, flussoSintesiType, simTFlussoOriginePagopa );
            } catch ( Exception e ) {
                LOGGER.error ( String.format ( "Errore in fase di salvataggio del flusso sintesi con id flusso: %s e codice versamento: %s",
                    trasmettiFlussiPagoPARequest.getTestataTrasmissioneFlussi ().getIdentificativoFlusso (), flussoSintesiType.getCodiceVersamento () ), e );
                return 1;
            }
        }
        return 0;

    }

    private FlussoRiconciliazione unmarshal ( TrasmettiFlussiPagoPARequest trasmettiFlussiPagoPARequest ) throws EpaysimulatorException {
        Assert.notNull ( trasmettiFlussiPagoPARequest.getFlussoRiconciliato (), "Oggetto FlussoRiconciliato null" );
        byte [] base64Bytes = trasmettiFlussiPagoPARequest.getFlussoRiconciliato ();
        FlussoRiconciliazione flussoRiconciliazioneType = new FlussoRiconciliazione ();
        StringSource stringSource = new StringSource ( new String ( base64Bytes ) );

        flussoRiconciliazioneType = (FlussoRiconciliazione) jaxb2Marshaller.unmarshal ( stringSource );

        return flussoRiconciliazioneType;
    }
}

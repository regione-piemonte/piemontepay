/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.manager.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import it.csi.epay.epaysim.business.epaysim.builders.SimTGiornaleDiCassaBuilder;
import it.csi.epay.epaysim.business.epaysim.manager.ProvvisorioManager;
import it.csi.epay.epaysim.business.epaysim.model.SimTGiornaleDiCassa;
import it.csi.epay.epaysim.business.epaysim.model.SimTProvvisorio;
import it.csi.epay.epaysim.business.epaysim.repository.GiornaleDiCassaRepository;
import it.csi.epay.epaysim.business.epaysim.repository.ProvvisorioRepository;
import it.csi.epay.epaysim.business.epaysim.utils.Costanti;
import it.csi.epay.epaysim.business.epaysim.utils.Messaggi;
import it.csi.epay.epaysim.business.epaysim.utils.StatoProvvisorio;
import it.csi.epay.epaysim.dto.GiornaleDiCassaDTO;
import it.csi.epay.epaysim.dto.InserisciProvvisorioOutputDTO;
import it.csi.epay.epaysim.dto.ParentOutput;
import it.csi.epay.epaysim.dto.ProvvisorioDTO;
import it.csi.epay.epaysim.dto.flussi.riconciliazioneversamentipsptypes.ResultType;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.ProvvisorioType;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.RicercaProvvisoriPagoPARequest;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.RicercaProvvisoriPagoPAResponse;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.StatoProvvisorioType;


@Service
public class ProvvisorioManagerImpl implements ProvvisorioManager {

    @Autowired
    private ProvvisorioRepository provvisorioRepository;

    @Autowired
    private GiornaleDiCassaRepository giornaleDiCassaRepository;

    @Override
    public RicercaProvvisoriPagoPAResponse ricercaProvvisoriPagoPA (
        RicercaProvvisoriPagoPARequest ricercaProvvisoriPagoPARequest ) {

        RicercaProvvisoriPagoPAResponse ricercaProvvisoriPagoPAResponse = new RicercaProvvisoriPagoPAResponse ();

        List<SimTProvvisorio> epaysimTProvvisorioList = provvisorioRepository.search ( ricercaProvvisoriPagoPARequest );
        ResultType rt = new ResultType ();
        rt.setCodice ( Costanti.RESULT_CODE_OK );
        ricercaProvvisoriPagoPAResponse.setResult ( rt );

        if ( epaysimTProvvisorioList != null ) {

            ricercaProvvisoriPagoPAResponse.setElencoProvvisori ( new RicercaProvvisoriPagoPAResponse.ElencoProvvisori () );
            GregorianCalendar gcal = new GregorianCalendar ();
            for ( SimTProvvisorio provvisorio: epaysimTProvvisorioList ) {

                ProvvisorioType pt = new ProvvisorioType ();

                pt.setAnnoEsercizio ( BigInteger.valueOf ( provvisorio.getAnnoEsercizio () ) );
                pt.setCausaleVersamento ( provvisorio.getCausale () );

                gcal.setTime ( provvisorio.getDataMovimento () );
                try {
                    pt.setData ( DatatypeFactory.newInstance ().newXMLGregorianCalendar ( gcal ) );
                } catch ( DatatypeConfigurationException e ) {
                    throw new IllegalArgumentException ( e );
                }
                pt.setImporto ( provvisorio.getImportoProvvisorio () );
                pt.setNumero ( BigInteger.valueOf ( provvisorio.getNumeroProvvisorio () ) );
                //                pt.setSoggetto ( provvisorio.getDescrizione () );
                pt.setStato ( StatoProvvisorioType.fromValue ( provvisorio.getStato () ) );

                ricercaProvvisoriPagoPAResponse.getElencoProvvisori ().getProvvisorio ().add ( pt );
            }
        }
        return ricercaProvvisoriPagoPAResponse;
    }

    @Override
    @Transactional ( rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW )
    public InserisciProvvisorioOutputDTO inserisciProvvisorio ( ProvvisorioDTO provv, Long idGiornaleDiCassa, String cf_utente ) {
        InserisciProvvisorioOutputDTO outputDTO = new InserisciProvvisorioOutputDTO ();
        checkProvvisorioDTO ( provv, outputDTO, false );
        if ( outputDTO.getCodiceEsito ().equals ( Costanti.RESULT_CODE_OK ) ) {
            List<SimTProvvisorio> provvDaAnnullare = provvisorioRepository.findAllByCausaleAndStato ( provv.getCausale (), StatoProvvisorio.VALIDO.value () );
            if ( !CollectionUtils.isEmpty ( provvDaAnnullare ) ) {
                for ( SimTProvvisorio simTProvvisorio: provvDaAnnullare ) {
                    simTProvvisorio.setDataModifica ( new Date () );
                    simTProvvisorio.setUtenteModifica ( cf_utente );
                    simTProvvisorio.setStato ( StatoProvvisorio.ANNULLATO.value () );
                    provvisorioRepository.save ( simTProvvisorio );
                }
            }
            SimTProvvisorio epaysimTProvvisorio = buildEpaysimTProvvisorio ( provv, idGiornaleDiCassa, cf_utente );
            epaysimTProvvisorio = provvisorioRepository.save ( epaysimTProvvisorio );
            outputDTO.setProvvisorioDTO ( provv );
        }
        return outputDTO;
    }

    @Override
    @Transactional ( rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW )
    public GiornaleDiCassaDTO ricercaGiornaleDiCassa ( String identificativoFlussoBT ) throws DatatypeConfigurationException {

        GiornaleDiCassaDTO outputDTO = null;

        SimTGiornaleDiCassa giornaleDiCassa = giornaleDiCassaRepository.findOneByIdentificativoFlussoBt ( identificativoFlussoBT );
        if ( giornaleDiCassa != null ) {
            outputDTO = map ( giornaleDiCassa );
        }
        return outputDTO;
    }

    @Override
    @Transactional ( rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW )
    public GiornaleDiCassaDTO inserisciGiornaleDiCassa ( String xmlFlusso, String identificativoFlussoBT, String cf_utente )
                    throws DatatypeConfigurationException {

        GiornaleDiCassaDTO output = null;

        SimTGiornaleDiCassa giornale = giornaleDiCassaRepository.findOneByIdentificativoFlussoBt ( identificativoFlussoBT );
        if ( giornale == null ) {
                SimTGiornaleDiCassa giornaleToSave = new SimTGiornaleDiCassaBuilder ()
                .withXml ( xmlFlusso )
                .withIdentificativoFlussoBT ( identificativoFlussoBT )
                .withDataInserimento ( new Date () )
                .withUtenteInserimento ( Costanti.UTENTE_TECNICO )
                .withDataModifica ( new Date () )
                .withUtenteModifica ( cf_utente )
                .build ();
            giornale = giornaleDiCassaRepository.save ( giornaleToSave );
        } else {
            giornale.setXml ( xmlFlusso );
            giornale.setDataModifica ( new Date () );
            giornale.setUtenteModifica ( cf_utente );
            giornale = giornaleDiCassaRepository.save ( giornale );
        }
        output = map ( giornale );
        return output;


    }

    private void checkProvvisorioDTO ( ProvvisorioDTO provvisorioDTO, ParentOutput parentOutput, boolean checkId ) {

        if ( provvisorioDTO == null ) {
            parentOutput.setCodiceEsito ( Costanti.RESULT_CODE_FIELD_REQUIRED );
            parentOutput.setDescrizioneEsito ( String.format ( Messaggi.FIELD_REQUIRED, "ProvvisorioDTO" ) );
            return;
        }

        //check id
        if ( checkId && provvisorioDTO.getId () < 1L ) {
            parentOutput.setCodiceEsito ( Costanti.RESULT_CODE_INVALID_FIELD );
            parentOutput.setDescrizioneEsito (
                String.format ( Messaggi.INVALID_FIELD, "ID", String.valueOf ( provvisorioDTO.getId () ) ) );

            return;
        }

        //check anno esercizio
        if ( provvisorioDTO.getAnnoEsercizio () == null ) {
            parentOutput.setCodiceEsito ( Costanti.RESULT_CODE_FIELD_REQUIRED );
            parentOutput.setDescrizioneEsito ( String.format ( Messaggi.FIELD_REQUIRED, "Anno Esercizio" ) );
            return;
        }
        if ( provvisorioDTO.getAnnoEsercizio () < 1 ) {
            parentOutput.setCodiceEsito ( Costanti.RESULT_CODE_INVALID_FIELD );
            parentOutput
            .setDescrizioneEsito ( String.format ( Messaggi.INVALID_FIELD, "Anno Esercizio", String.valueOf ( provvisorioDTO.getAnnoEsercizio () ) ) );
            return;
        }

        //check anno provvisorio
        if ( provvisorioDTO.getAnnoProvvisorio () == null ) {
            parentOutput.setCodiceEsito ( Costanti.RESULT_CODE_FIELD_REQUIRED );
            parentOutput.setDescrizioneEsito ( String.format ( Messaggi.FIELD_REQUIRED, "Anno Provvisorio" ) );
            return;
        }
        if ( provvisorioDTO.getAnnoProvvisorio () < 1 ) {
            parentOutput.setCodiceEsito ( Costanti.RESULT_CODE_INVALID_FIELD );
            parentOutput
            .setDescrizioneEsito ( String.format ( Messaggi.INVALID_FIELD, "Anno Provvisorio", String.valueOf ( provvisorioDTO.getAnnoProvvisorio () ) ) );
            return;
        }

        //check causale
        if ( StringUtils.isEmpty ( provvisorioDTO.getCausale () ) ) {
            parentOutput.setCodiceEsito ( Costanti.RESULT_CODE_FIELD_REQUIRED );
            parentOutput.setDescrizioneEsito ( String.format ( Messaggi.FIELD_REQUIRED, "Causale" ) );
            return;
        }

        //check codice fiscale ente
        if ( StringUtils.isEmpty ( provvisorioDTO.getCodiceFiscaleEnte () ) ) {
            parentOutput.setCodiceEsito ( Costanti.RESULT_CODE_FIELD_REQUIRED );
            parentOutput.setDescrizioneEsito ( String.format ( Messaggi.FIELD_REQUIRED, "Codice Fiscale Ente" ) );
            return;
        }

        //check data Fine
        //        if ( provvisorioDTO.getDataFine () == null ) {
        //            parentOutput.setCodiceEsito ( Costanti.RESULT_CODE_FIELD_REQUIRED );
        //            parentOutput.setDescrizioneEsito ( String.format ( Messaggi.FIELD_REQUIRED, "Data Fine" ) );
        //            return;
        //        }

        //check data Movimento
        if ( provvisorioDTO.getDataMovimento () == null ) {
            parentOutput.setCodiceEsito ( Costanti.RESULT_CODE_FIELD_REQUIRED );
            parentOutput.setDescrizioneEsito ( String.format ( Messaggi.FIELD_REQUIRED, "Data Movimento" ) );
            return;
        }

        //check descrizione
        //        if ( StringUtils.isEmpty ( provvisorioDTO.getDescrizione () ) ) {
        //            parentOutput.setCodiceEsito ( Costanti.RESULT_CODE_FIELD_REQUIRED );
        //            parentOutput.setDescrizioneEsito ( String.format ( Messaggi.FIELD_REQUIRED, "Descrizione" ) );
        //            return;
        //        }

        //check identificativo flusso
        if ( StringUtils.isEmpty ( provvisorioDTO.getIdentificativoFlusso () ) ) {
            parentOutput.setCodiceEsito ( Costanti.RESULT_CODE_FIELD_REQUIRED );
            parentOutput.setDescrizioneEsito ( String.format ( Messaggi.FIELD_REQUIRED, "Identificativo Flusso" ) );
            return;
        }

        //check stato
        if ( StringUtils.isEmpty ( provvisorioDTO.getStato () ) ) {
            parentOutput.setCodiceEsito ( Costanti.RESULT_CODE_FIELD_REQUIRED );
            parentOutput.setDescrizioneEsito ( String.format ( Messaggi.FIELD_REQUIRED, "Stato" ) );
            return;
        }

        //check tipo movimento
        //        if ( StringUtils.isEmpty ( provvisorioDTO.getTipoMovimento () ) ) {
        //            parentOutput.setCodiceEsito ( Costanti.RESULT_CODE_FIELD_REQUIRED );
        //            parentOutput.setDescrizioneEsito ( String.format ( Messaggi.FIELD_REQUIRED, "Tipo Movimento" ) );
        //            return;
        //        }

        //check importo provvisorio
        if ( provvisorioDTO.getImportoProvvisorio () == null ) {
            parentOutput.setCodiceEsito ( Costanti.RESULT_CODE_FIELD_REQUIRED );
            parentOutput.setDescrizioneEsito ( String.format ( Messaggi.FIELD_REQUIRED, "Importo Provvisorio" ) );
            return;
        }
        //        if ( provvisorioDTO.getImportoProvvisorio ().doubleValue () < 1 ) {
        //            parentOutput.setCodiceEsito ( Costanti.RESULT_CODE_INVALID_FIELD );
        //            parentOutput.setDescrizioneEsito (
        //                String.format ( Messaggi.INVALID_FIELD, "Importo Provvisorio", String.valueOf ( provvisorioDTO.getImportoProvvisorio ().doubleValue () ) ) );
        //            return;
        //        }

        //check numero provvisorio
        if ( provvisorioDTO.getNumeroProvvisorio () == null ) {
            parentOutput.setCodiceEsito ( Costanti.RESULT_CODE_FIELD_REQUIRED );
            parentOutput.setDescrizioneEsito ( String.format ( Messaggi.FIELD_REQUIRED, "Numero Provvisorio" ) );
            return;
        }
        if ( provvisorioDTO.getNumeroProvvisorio () < 1 ) {
            parentOutput.setCodiceEsito ( Costanti.RESULT_CODE_INVALID_FIELD );
            parentOutput.setDescrizioneEsito (
                String.format ( Messaggi.INVALID_FIELD, "Numero Provvisorio", String.valueOf ( provvisorioDTO.getNumeroProvvisorio () ) ) );
            return;
        }
        parentOutput.setCodiceEsito ( Costanti.RESULT_CODE_OK );
    }

    private SimTProvvisorio buildEpaysimTProvvisorio ( ProvvisorioDTO provvisorioDTO, Long idGiornaleDiCassa, String cf_utente ) {
        SimTProvvisorio provvisorio = new SimTProvvisorio ();

        provvisorio.setAnnoEsercizio ( provvisorioDTO.getAnnoEsercizio () );
        provvisorio.setAnnoProvvisorio ( provvisorioDTO.getAnnoProvvisorio () );
        provvisorio.setCausale ( provvisorioDTO.getCausale () );
        provvisorio.setCodiceFiscaleEnte ( provvisorioDTO.getCodiceFiscaleEnte () );
        //        provvisorio.setDataInsVar ( new Timestamp ( provvisorioDTO.getDataInsVar ().toGregorianCalendar ().getTimeInMillis () ) );
        //        provvisorio.setUtenteInsVar ( provvisorioDTO.getUtenteInsVar () );
        provvisorio.setDataMovimento (
            new Timestamp ( provvisorioDTO.getDataMovimento ().toGregorianCalendar ().getTimeInMillis () ) );
        //        provvisorio.setDescrizione ( provvisorioDTO.getDescrizione () );
        provvisorio.setIdentificativoFlusso ( provvisorioDTO.getIdentificativoFlusso () );
        provvisorio.setImportoProvvisorio ( provvisorioDTO.getImportoProvvisorio () );
        provvisorio.setNumeroProvvisorio ( provvisorioDTO.getNumeroProvvisorio () );
        provvisorio.setStato ( provvisorioDTO.getStato () );
        //        provvisorio.setTipoMovimento ( provvisorioDTO.getTipoMovimento () );
        provvisorio.setSimTGiornaleDiCassa ( new SimTGiornaleDiCassaBuilder ()
            .withId ( idGiornaleDiCassa )
            .build () );
        provvisorio.setDataInserimento ( new Date () );
        provvisorio.setDataModifica ( new Date () );
        provvisorio.setUtenteInserimento ( Costanti.UTENTE_TECNICO );
        provvisorio.setUtenteModifica ( cf_utente );
        return provvisorio;
    }

    private GiornaleDiCassaDTO map ( SimTGiornaleDiCassa input ) throws DatatypeConfigurationException {
        GiornaleDiCassaDTO output = new GiornaleDiCassaDTO ();
        output.setId ( null != input.getId () ? input.getId ().longValue () : null );
        output.setXml ( input.getXml () );
        output.setIdentificativoFlussoBT ( input.getIdentificativoFlussoBT () );
        GregorianCalendar c = new GregorianCalendar ();
        c.setTime ( input.getDataInserimento () );
        XMLGregorianCalendar date2 = DatatypeFactory.newInstance ().newXMLGregorianCalendar ( c );
        output.setDataInserimento ( date2 );
        return output;
    }

    @Override
    public SimTProvvisorio find ( Long id ) {
        return provvisorioRepository.findOne ( id.intValue () );
    }

    @Override
    public List<SimTProvvisorio> find ( String cfEnte, String idFlusso, Collection<String> stati, Date fromDate, Date toDate ) {
        List<SimTProvvisorio> provvisoriList = null;
        if ( toDate != null ) {
            provvisoriList = provvisorioRepository.findByCodiceFiscaleEnteAndIdentificativoFlussoIgnoreCaseContainingAndStatoInAndDataMovimentoGreaterThanEqualAndDataMovimentoLessThanEqual (
                cfEnte,
                "%" + idFlusso + "%",
                stati,
                fromDate,
                toDate );
        } else {
            provvisoriList = provvisorioRepository.findByCodiceFiscaleEnteAndIdentificativoFlussoIgnoreCaseContainingAndStatoInAndDataMovimentoGreaterThanEqual (
                cfEnte,
                "%" + idFlusso + "%",
                stati,
                fromDate );
        }
        return provvisoriList;
    }

}

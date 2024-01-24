/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import it.csi.epay.epaypacatalogsrv.api.util.CodiciEsito;
import it.csi.epay.epaypacatalogsrv.business.enums.ParametriConfigurazione;
import it.csi.epay.epaypacatalogsrv.business.service.ConfigurazioneService;
import it.csi.epay.epaypacatalogsrv.business.service.RiferimentiContabiliInScadenzaService;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.EnteRiferimentiContabiliInScadenza;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.EntiRiferimentiContabiliInScadenzaOutput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.RiferimentiContabiliInScadenzaOutput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.RiferimentoContabileInScadenza;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.RiferimentoContabileInScadenzaInput;
import it.csi.epay.epaypacatalogsrv.model.EnteLight;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.repository.EnteLightRepository;
import it.csi.epay.epaypacatalogsrv.repository.RiferimentoContabileRepository;
import it.csi.epay.epaypacatalogsrv.vo.ConfigurazioneVO;


/**
 * 
 */
@Service
@Transactional
public class RiferimentiContabiliInScadenzaServiceImpl implements RiferimentiContabiliInScadenzaService {

    private static final Logger LOG = LogManager.getLogger(RiferimentiContabiliInScadenzaService.class);
    
    @Autowired
    public ConfigurazioneService configurazioneService;

    @Autowired
    private EnteLightRepository enteRepository;

    @Autowired
    private RiferimentoContabileRepository riferimentoContabileRepository;

    @Override
    public EntiRiferimentiContabiliInScadenzaOutput getEntiRiferimentiContabiliInScadenza () {
        EntiRiferimentiContabiliInScadenzaOutput output = new EntiRiferimentiContabiliInScadenzaOutput ();
        try {
            List<EnteRiferimentiContabiliInScadenza> enti = new LinkedList<> ();

            List<EnteLight> listEnti = enteRepository.findEntiWithRiferimentiContabiliInScadenza ( recuperaParamGiorni () );
            if ( !CollectionUtils.isEmpty ( listEnti ) ) {
                for ( EnteLight temp: listEnti ) {
                    EnteRiferimentiContabiliInScadenza ente = new EnteRiferimentiContabiliInScadenza ();
                    ente.setCodiceFiscale ( temp.getCodiceFiscale () );
                    ente.setIdEnte ( temp.getId ().toString () );
                    ente.setDenominazione ( temp.getDenominazione () );
                    ente.setEmailAddress ( temp.getEmail () );
                    enti.add ( ente );

                }
                output.setCodiceEsito ( CodiciEsito.ESITO_OK.getCodice () );
                output.setDescrizioneEsito ( CodiciEsito.ESITO_OK.getMessaggio () );
                output.setEnti ( enti );
            } else {
                output.setCodiceEsito ( CodiciEsito.DATI_NON_TROVATI.getCodice () );
                output.setDescrizioneEsito ( CodiciEsito.DATI_NON_TROVATI.getMessaggio () );
            }
        } catch ( Exception e ) {
            LOG.error ( CodiciEsito.ERRORE_REPERIMENTO_ENTI, e );
            output.setCodiceEsito ( CodiciEsito.ERRORE_REPERIMENTO_ENTI.getCodice () );
            output.setDescrizioneEsito ( CodiciEsito.ERRORE_REPERIMENTO_ENTI.getMessaggio () );
        }
        return output;
    }

    /**
     * Metodo per gestire il recupero del parametro.
     * @return numero di giorni
     */
    private Long recuperaParamGiorni () {
        ConfigurazioneVO paramGiorni = configurazioneService.getParametro ( ParametriConfigurazione.JOB_ALERT_SCADENZA_RIF_CONTAB_RANGE_GIORNI );
        Long numGiorni = 3L;
        if ( null != paramGiorni && StringUtils.hasText ( paramGiorni.getValore () ) ) {
            try {
                numGiorni = Long.valueOf ( paramGiorni.getValore () );
            } catch ( Exception e ) {
                LOG.warn ( CodiciEsito.ERRORE_REPERIMENTO_PARAM_JOB_ALERT );
            }
        }

        return numGiorni;
    }
    
    @Override
    public RiferimentiContabiliInScadenzaOutput getRiferimentiContabiliInScadenza ( RiferimentoContabileInScadenzaInput input ) {
        RiferimentiContabiliInScadenzaOutput output = new RiferimentiContabiliInScadenzaOutput ();
        List<RiferimentoContabileInScadenza> riferimentiContabiliInScadenza = new LinkedList<> ();
        output.setRiferimentiContabili ( riferimentiContabiliInScadenza );
        try {
            Assert.notNull ( input, "Oggetto di inputo non valorizzato." );
            Assert.hasText ( input.getIdEnte (), "Ente non valorizzato" );
        } catch ( IllegalArgumentException e1 ) {
            output.setCodiceEsito ( CodiciEsito.DATI_INPUT_MANCANTI.getCodice () );
            output.setDescrizioneEsito ( CodiciEsito.DATI_INPUT_MANCANTI.getMessaggio () );
            return output;
        }
        try {
            List<RiferimentoContabile> listRiferimentiContabili
                = riferimentoContabileRepository.ricercaInScadenza ( new Long ( input.getIdEnte () ), recuperaParamGiorni () );

            if ( !CollectionUtils.isEmpty ( listRiferimentiContabili ) ) {
                for ( RiferimentoContabile temp: listRiferimentiContabili ) {
                    DateFormat df = new SimpleDateFormat ( "dd/MM/yyyy" );
                    RiferimentoContabileInScadenza rif = new RiferimentoContabileInScadenza ();
                    rif.setId ( temp.getId ().toString () );
                    rif.setDatoSpecificoRiscossione ( temp.getDatoSpecificoRiscossione () );
                    rif.setAnnoAccertamento ( null != temp.getAnnoAccertamento () ? temp.getAnnoAccertamento ().toString () : "" );
                    rif.setNumAccertamento ( null != temp.getNumeroAccertamento () ? temp.getNumeroAccertamento ().toString () : "" );
                    rif.setDataFineValidita ( null != temp.getDataFineValidita () ? df.format ( temp.getDataFineValidita () ) : "" );
                    CodiceVersamento cov = new CodiceVersamento ();
                    cov.setId ( temp.getCodiceVersamento ().getId ().toString () );
                    cov.setCodiceVersamento ( temp.getCodiceVersamento ().getCodice () );
                    cov.setDesCodiceVeramento ( temp.getCodiceVersamento ().getDescrizione () );
                    rif.setCodiceVersamento ( cov );
                    riferimentiContabiliInScadenza.add ( rif );
                }
                output.setCodiceEsito ( CodiciEsito.ESITO_OK.getCodice () );
                output.setDescrizioneEsito ( CodiciEsito.ESITO_OK.getMessaggio () );
            } else {
                output.setCodiceEsito ( CodiciEsito.DATI_NON_TROVATI.getCodice () );
                output.setDescrizioneEsito ( CodiciEsito.DATI_NON_TROVATI.getMessaggio () );
            }
        } catch ( Exception e ) {
            LOG.error ( String.format ( CodiciEsito.ERRORE_REPERIMENTO_RIFERIMENTI_CONTAB_ENTE.getMessaggio (), input.getIdEnte () ), e );
            output.setCodiceEsito ( CodiciEsito.ERRORE_REPERIMENTO_RIFERIMENTI_CONTAB_ENTE.getCodice () );
            output.setDescrizioneEsito (  String.format ( CodiciEsito.ERRORE_REPERIMENTO_RIFERIMENTI_CONTAB_ENTE.getMessaggio (), input.getIdEnte () ) );
        }
        return output;
    }

}

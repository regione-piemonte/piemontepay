/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import it.csi.epay.epaymodric.business.epaymodric.bo.Configurazione;
import it.csi.epay.epaymodric.business.epaymodric.manager.ConfigurazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.ConfigurazioneUtility;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTConfigurazione;
import it.csi.epay.epaymodric.business.epaymodric.repository.ConfigurazioneRepository;
import it.csi.epay.epaymodric.business.epaymodric.utils.Costanti;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicecaLimiteEsportazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicercaLimiteElaborazioneReport;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException;

/**
 * @author vsgro
 */
@Service
public class ConfigurazioneManagerImpl implements ConfigurazioneManager {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    private static java.util.ResourceBundle res;

    static {
        try {
            res = java.util.ResourceBundle.getBundle ( "config" );
        } catch ( MissingResourceException e ) {
            System.out.println ( e );
        }
    }

    public static java.util.ResourceBundle getRes () {
        return res;
    }

    @Autowired
    private ConfigurazioneRepository repository;

    public List<Configurazione> findAll(){
        List<Configurazione> configurazioni = new LinkedList<Configurazione> ();
        logger.info ( "ConfigurazioneManagerImpl.findAll: INIZIO" );
        List<CblTConfigurazione> cblTConfigurazione = repository.findAll ();
        for(int i=0; i<cblTConfigurazione.size (); i++) {
            configurazioni.add ( ConfigurazioneUtility.getConfigurazione ( cblTConfigurazione.get ( i ) ) );
        }
        logger.info ( "ConfigurazioneManagerImpl.findAll: FINE" );
        return configurazioni;
    }

    public List<Configurazione> leggi(String idEnte){
        List<Configurazione> configurazioni = new LinkedList<Configurazione> ();
        logger.info ( "ConfigurazioneManagerImpl.findByIdEnte: INIZIO" );
        List<CblTConfigurazione> cblTConfigurazione = repository.findByIdEnte ( idEnte );
        for(int i=0; i<cblTConfigurazione.size (); i++) {
            configurazioni.add ( ConfigurazioneUtility.getConfigurazione ( cblTConfigurazione.get ( i ) ) );
        }
        logger.info ( "ConfigurazioneManagerImpl.findByIdEnte: FINE" );
        return configurazioni;
    }

    @Override
    public Configurazione leggi ( String idEnte, String nomeAttributo ) {
        Configurazione configurazione = new Configurazione ();
        logger.info ( "ConfigurazioneManagerImpl.findByIdEnteAndNomeAttributo: INIZIO" );
        CblTConfigurazione cblTConfigurazione = repository.findByIdEnteAndNomeAttributo( idEnte, nomeAttributo );
        configurazione = ConfigurazioneUtility.getConfigurazione ( cblTConfigurazione );
        logger.info ( "ConfigurazioneManagerImpl.findByIdEnteAndNomeAttributo: FINE" );
        return configurazione;
    }

    @Override
    public Configurazione leggiAttributoGenerale ( String nomeAttributo ) {
        Configurazione configurazione = new Configurazione ();
        logger.info ( "ConfigurazioneManagerImpl.leggiAttributoGenerale: INIZIO" );
        CblTConfigurazione cblTConfigurazione = repository.findByNomeAttributo ( nomeAttributo );
        configurazione = ConfigurazioneUtility.getConfigurazione ( cblTConfigurazione );
        logger.info ( "ConfigurazioneManagerImpl.leggiAttributoGenerale: FINE" );
        return configurazione;
    }

    @Override
    public Map<String, Configurazione> recuperaConfigurazionePerElaborazione ( String idEnte ) throws EpaymodricException {

        logger.info ( "ConfigurazioneManagerImpl.recuperaConfigurazionePerElaborazione: INIZIO" );

        Map<String, Configurazione> configurazioneEnte = new HashMap<String, Configurazione> ();

        try {

            Assert.notNull ( idEnte, "Id Ente obbligatorio" );

            logger.info ( "1 - Recupero numero massimo tentativi da DB - INIZIO " );

            Configurazione numMaxTentativi
            = recuperaConfigurazioneDaDbOProperty ( idEnte, Costanti.CONFIG_ATTR_MAX_NUM_TENTATIVI, Costanti.CONFIG_ATTR_MAX_NUM_TENTATIVI_PROPERTY,
                false );

            configurazioneEnte.put ( Costanti.CONFIG_ATTR_MAX_NUM_TENTATIVI, numMaxTentativi );

            logger.info ( "1 - Recupero numero massimo tentativi da DB - FINE " );

            logger.info ( "2 - Recupero endpoint recupero provvisori - INIZIO" );

            Configurazione endpointRecuperoProvvisori = recuperaConfigurazioneDaDbOProperty ( idEnte, Costanti.CONFIG_ATTR_SERVICE_ENDPOINT_RECUPERO_PROVVISORI,
                Costanti.CONFIG_ATTR_SERVICE_ENDPOINT_RECUPERO_PROVVISORI_PROPERTY, false );

            configurazioneEnte.put ( Costanti.CONFIG_ATTR_SERVICE_ENDPOINT_RECUPERO_PROVVISORI, endpointRecuperoProvvisori );

            logger.info ( "2 - Recupero endpoint recupero provvisori - FINE" );

            logger.info ( "3 - Recupero endpoint invio flussi - INIZIO" );

            Configurazione endpointInvioFlussi = recuperaConfigurazioneDaDbOProperty ( idEnte, Costanti.CONFIG_ATTR_SERVICE_ENDPOINT_INVIO_FLUSSI,
                Costanti.CONFIG_ATTR_SERVICE_ENDPOINT_INVIO_FLUSSI_PROPERTY, false );

            configurazioneEnte.put ( Costanti.CONFIG_ATTR_SERVICE_ENDPOINT_INVIO_FLUSSI, endpointInvioFlussi );

            logger.info ( "3 - Recupero endpoint invio flussi - FINE" );

            logger.info ( "4 - Recupero numero massimo flussi origine (per pagina) - INIZIO" );

            Configurazione pageSizeFlussiOrig = recuperaConfigurazioneDaDbOProperty ( idEnte, Costanti.PAGE_SIZE_FLUSSI_ORIG,
                Costanti.PAGE_SIZE_FLUSSI_ORIG_PROPERTY, true );

            configurazioneEnte.put ( Costanti.PAGE_SIZE_FLUSSI_ORIG, pageSizeFlussiOrig );

            logger.info ( "4 - Recupero numero massimo flussi origine (per pagina) - FINE" );
            
            logger.info ( "5 - Recupero flag invio pagamenti sconosciuti - INIZIO" );

            Configurazione abilitaInvioPagamentoSconosciuto = recuperaConfigurazioneDaDbOProperty ( idEnte, Costanti.ABILITA_INVIO_PAGAMENTI_SCONOSCIUTI,
                Costanti.ABILITA_INVIO_PAGAMENTI_SCONOSCIUTI_PROPERTY, true );

            configurazioneEnte.put ( Costanti.ABILITA_INVIO_PAGAMENTI_SCONOSCIUTI, abilitaInvioPagamentoSconosciuto );

            logger.info ( "5 - Recupero flag invio pagamenti sconosciuti - FINE" );
            
        } catch ( IncorrectResultSizeDataAccessException e ) {
        	logger.error( String.format ( "Impossibile recuperare la configurazione. Causa: %s ", e.getMessage () ) );           
        } catch ( IllegalArgumentException e ) {
        	logger.error( String.format ( "Impossibile recuperare la configurazione. Causa: %s ", e.getMessage () ) );
        } finally {
            logger.info ( "ConfigurazioneManagerImpl.recuperaConfigurazionePerElaborazione: FINE" );
        }


        return configurazioneEnte;
    }

    private Configurazione recuperaConfigurazioneDaDbOProperty ( String idEnte, String attributoDb, String attributoProperty, boolean recuperaDaProperty )
                    throws EpaymodricException {

        Configurazione conf = this.leggi ( idEnte, attributoDb );

        if ( null == conf || !StringUtils.hasText ( conf.getValore () ) ) {

            logger.warn ( String.format ( Costanti.ATTRIBUTO_CONF_ENTE_NON_PRESENTE_SU_DB, attributoDb, idEnte ) );

            if ( recuperaDaProperty ) {

                String numeroMaxTentativiProperty = getRes ().getString ( attributoProperty );

                if ( !StringUtils.hasText ( numeroMaxTentativiProperty ) ) {
                    logger.warn ( String.format ( Costanti.ATTRIBUTO_CONF_NON_PRESENTE_SU_PROPERTY, attributoProperty ) );
                    conf = null;
                }

                conf = new Configurazione ();

                conf.setIdEnte ( idEnte );
                conf.setNomeAttributo ( attributoDb );
                conf.setValore ( numeroMaxTentativiProperty );

                logger.info ( String.format ( Costanti.ATTRIBUTO_CONF_PRESENTE_SU_PROPERTY, attributoProperty ) );

            }

        } else {

            logger.info ( String.format ( Costanti.ATTRIBUTO_CONF_ENTE_PRESENTE_SU_DB, attributoDb, idEnte ) );

        }


        return conf;

    }

    @Override
    public String getConfigFromProperties ( String property ) {
        return getRes ().getString ( property );
    }

    @Override
    public String getConfigFromDatabase(String attributo) {
        Configurazione conf = this.leggiAttributoGenerale(attributo);

        if ( null == conf || !StringUtils.hasText ( conf.getValore () ) ) {
            logger.warn ( String.format ( Costanti.ATTRIBUTO_CONF_NON_PRESENTE_SU_DB, attributo ) );
            return null;

        } else {
            logger.info ( String.format ( Costanti.ATTRIBUTO_CONF_PRESENTE_SU_DB, attributo ) );
            return conf.getValore ();
        }

    }

    public ConfigurazioneRepository getRepository () {
        return repository;
    }


    public void setRepository ( ConfigurazioneRepository repository ) {
        this.repository = repository;
    }

    @Override
    public DTOOutputWsRicecaLimiteEsportazione ricercaLimiteEsportazione() {

        DTOOutputWsRicecaLimiteEsportazione dto = new DTOOutputWsRicecaLimiteEsportazione();
        CblTConfigurazione limite = repository.findByNomeAttributo(Costanti.ATTRIBUTO_CONF_NUM_MAX_RECORDS_EXPORT);
        if(limite != null) {
            dto.setNumMaxRecordsReport(limite.getValore());
            dto.setEsito("OK");
        }
        return dto;
    }

    @Override
    public DTOOutputWsRicercaLimiteElaborazioneReport ricercaLimiteElaborazioneReport() {

        DTOOutputWsRicercaLimiteElaborazioneReport dto = new DTOOutputWsRicercaLimiteElaborazioneReport();
        CblTConfigurazione limite = repository.findByNomeAttributo(Costanti.ATTRIBUTO_CONF_NUM_MAX_RECORDS_REPORT);
        if(limite != null) {
            dto.setNumMaxRecordsReport(limite.getValore());
            dto.setEsito("OK");
        }
        return dto;
    }




}

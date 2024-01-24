/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.service.impl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.enums.Caches;
import it.csi.epay.epaypacatalogsrv.business.enums.ParametriConfigurazione;
import it.csi.epay.epaypacatalogsrv.business.service.ConfigurazioneService;
import it.csi.epay.epaypacatalogsrv.model.Configurazione;
import it.csi.epay.epaypacatalogsrv.repository.ConfigurazioneRepository;
import it.csi.epay.epaypacatalogsrv.vo.ConfigurazioneVO;


@Component
@Transactional
public class ConfigurazioneServiceImpl implements ConfigurazioneService {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    private ConfigurazioneRepository configurazioneRepository;

    private static final java.util.ResourceBundle res;

    static {
        res = java.util.ResourceBundle.getBundle ( "config" );
    }

    public static java.util.ResourceBundle getRes () {
        return res;
    }

    @Override
    @Cacheable ( value = Caches.CONFIGURAZIONE, key = "#parametro.name()" )
    public ConfigurazioneVO getParametro ( ParametriConfigurazione parametro ) {
        return getParametro ( parametro, null );
    }

    @Cacheable ( value = Caches.CONFIGURAZIONE, key = "#parametro.name() + \"_\" + #idEnte" )
    @Override
    public ConfigurazioneVO getParametro ( ParametriConfigurazione parametro, Long idEnte ) {
        String codiceOriginale = parametro.name ();

        logger.debug ( "risolvo parametro " + codiceOriginale );

        String codiceDb = propertyToConfig ( codiceOriginale );
        Configurazione configEntity = null;

        if ( idEnte != null ) {
            logger.debug ( "(1) risolvo parametro " + codiceOriginale + " per ente specifico su database" );

            configEntity = configurazioneRepository.findOneByCodiceAndIdEnte ( codiceOriginale, idEnte );

            if ( configEntity == null && !codiceDb.equals ( codiceOriginale ) ) {
                logger.debug ( "(2) risolvo parametro " + codiceDb + " per ente specifico su database" );
                configEntity = configurazioneRepository.findOneByCodiceAndIdEnte ( codiceDb, idEnte );
            }
        }

        if ( configEntity == null ) {
            logger
                .debug (
                    "(3) risolvo parametro " + codiceOriginale
                        + " per ente generico su database perche' non e' stata trovata una configurazione specifica per l'ente" );
            configEntity = configurazioneRepository.findOneByCodiceAndIdEnteIsNull ( codiceOriginale );
        }

        if ( configEntity == null && !codiceDb.equals ( codiceOriginale ) ) {
            logger
                .debug (
                    "(4) risolvo parametro " + codiceDb + " per ente generico su database perche' non e' stata trovata una configurazione specifica per l'ente" );
            configEntity = configurazioneRepository.findOneByCodiceAndIdEnteIsNull ( codiceDb );
        }

        if ( configEntity == null ) {
            logger.debug (
                "(5) risolvo parametro " + codiceOriginale + " da configurazione pacchetto perche' non e' stata trovata una configurazione su database" );
            if ( getRes ().containsKey ( codiceOriginale ) ) {
                configEntity = new Configurazione ();
                configEntity.setCodice ( codiceOriginale );
                configEntity.setValore ( getRes ().getString ( codiceOriginale ) );
            }
        }
        String codiceProp = configToProperty ( codiceOriginale );

        if ( configEntity == null && !codiceProp.equals ( codiceOriginale ) ) {
            logger.debug (
                "(6) risolvo parametro " + codiceProp + " da configurazione pacchetto perche' non e' stata trovata una configurazione su database" );
            if ( getRes ().containsKey ( codiceProp ) ) {
                configEntity = new Configurazione ();
                configEntity.setCodice ( codiceProp );
                configEntity.setValore ( getRes ().getString ( codiceProp ) );
            }
        }

        ConfigurazioneVO configVO = new ConfigurazioneVO ();

        if ( configEntity == null ) {
            logger.warn ( "Parametro " + codiceOriginale + " non configurato" );
            // throw new RuntimeException ( "Parametro " + codiceOriginale + " non configurato" );
            configVO.setId ( null );
            configVO.setIdEnte ( null );
            configVO.setCodice ( codiceOriginale );
            configVO.setValore ( null );
        } else {
            configVO.setId ( configEntity.getId () );
            configVO.setIdEnte ( configEntity.getIdEnte () );
            configVO.setCodice ( configEntity.getCodice () );
            configVO.setValore ( configEntity.getValore () );
        }

        logger.debug ( "risolto parametro " + codiceOriginale + " al valore [" + configVO.getValore () + "]" );

        return configVO;
    }

    private String propertyToConfig ( String input ) {
        return input.toUpperCase ().replaceAll ( "[^A-Z0-9]+", "_" );
    }

    private String configToProperty ( String input ) {
        return input.toLowerCase ().replaceAll ( "[^a-z0-9]+", "." );
    }

    @Override
    @Cacheable ( value = Caches.CONFIGURAZIONE, key = "#parametro.name() + \"_\" + #idEnte" )
    public ConfigurazioneVO richiediParametro ( ParametriConfigurazione parametro, Long idEnte ) {
        ConfigurazioneVO parametroVO = getParametro ( parametro, idEnte );
        if ( parametroVO == null || parametroVO.isEmpty () ) {
            throw new RuntimeException ( "Parametro " + parametro + " non configurato" );
        }
        return parametroVO;
    }

    @Override
    @Cacheable ( value = Caches.CONFIGURAZIONE, key = "#parametro.name()" )
    public ConfigurazioneVO richiediParametro ( ParametriConfigurazione parametro ) {
        ConfigurazioneVO parametroVO = getParametro ( parametro );
        if ( parametroVO == null || parametroVO.isEmpty () ) {
            throw new RuntimeException ( "Parametro " + parametro + " non configurato" );
        }
        return parametroVO;
    }

    @Override
    public ConfigurazioneVO getParametroNoCache ( ParametriConfigurazione parametro ) {
        return getParametro ( parametro, null );
    }
}

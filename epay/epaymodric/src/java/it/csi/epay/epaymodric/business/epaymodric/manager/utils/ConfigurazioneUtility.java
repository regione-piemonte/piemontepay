/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.utils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import it.csi.epay.epaymodric.business.epaymodric.bo.Configurazione;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTConfigurazione;


/**
 * 
 * @author vsgro
 */
public class ConfigurazioneUtility {

    final static Logger logger = LogManager.getLogger(ConfigurazioneUtility.class);

    
    public static Configurazione getConfigurazione(CblTConfigurazione configurazioneEntity) {
        Configurazione configurazione = null;
        if (configurazioneEntity==null) {
            logger.warn ( "ConfigurazioneUtility.getConfigurazione: oggetto null" );
        } else {
            configurazione = new Configurazione();
            configurazione.setId ( configurazioneEntity.getId () );
            configurazione.setIdEnte ( configurazioneEntity.getIdEnte () );
            configurazione.setNomeAttributo ( configurazioneEntity.getNomeAttributo () );
            configurazione.setValore ( configurazioneEntity.getValore () );
        }
        return configurazione;
    }
    
    
}

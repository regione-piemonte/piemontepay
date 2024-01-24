/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.util;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import it.csi.epay.epaypacatalogsrv.model.Configurazione;
import it.csi.epay.epaypacatalogsrv.vo.ConfigurazioneVO;


public class ConfigurazioneUtils {

    final static Logger logger = LogManager.getLogger(ConfigurazioneUtils.class);

    public static ConfigurazioneVO getConfigurazione ( Configurazione configurazioneEntity ) {
        ConfigurazioneVO configurazione = null;
        if (configurazioneEntity==null) {
            logger.warn ( "ConfigurazioneUtils.getConfigurazione: oggetto null" );
        } else {
            configurazione = new ConfigurazioneVO ();
            configurazione.setId ( configurazioneEntity.getId () );
            configurazione.setIdEnte ( configurazioneEntity.getIdEnte () );
            configurazione.setCodice ( configurazioneEntity.getCodice () );
            configurazione.setValore ( configurazioneEntity.getValore () );
        }
        return configurazione;
    }


}

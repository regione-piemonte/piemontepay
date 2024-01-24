/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.epay.epaymodric.business.epaymodric.bo.Errore;
import it.csi.epay.epaymodric.business.epaymodric.manager.ErroreManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.EsitoManager;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsEsito;

/**
 * @author vsgro
 * Caso d'uso: 2.2.19
 */

@Component
public class EsitoServiceImpl implements EsitoManager {
    
    private final Logger logger = LogManager.getLogger (this.getClass());
    
    @Autowired
    ErroreManager erroreManager;

    @Override
    public DTOOutputWsEsito getEsito ( String codiceErrore, String... infoAggiuntive ) {
        DTOOutputWsEsito dtoEsito = null;
        logger.info ( "EsitoServiceImpl.getEsitoInErrore: INIZIO" );
        Errore errore = erroreManager.leggi ( codiceErrore );
        if (errore!=null) {
            String esito = "OK";
            if ("FATAL".equalsIgnoreCase ( errore.getTipologia () ) 
                || "ERROR".equalsIgnoreCase ( errore.getTipologia () )) {
                esito = "KO";
            }
            if (infoAggiuntive!=null) {
                errore.setDescrizioneErrore(errore.getDescrizioneErrore ());
                int i = 1;
                for (String infoAggiuntiva : infoAggiuntive ) {
                    errore.setDescrizioneErrore ( errore.getDescrizioneErrore ().replace ( "$" + i, infoAggiuntiva ) );
                    i++;
                }
            }
            dtoEsito = new DTOOutputWsEsito ( esito, errore.getCodiceErrore (), errore.getDescrizioneErrore () );
        }
        logger.info ( "EsitoServiceImpl.getEsitoInErrore: FINE" );
        return dtoEsito;
    }
    
   

}

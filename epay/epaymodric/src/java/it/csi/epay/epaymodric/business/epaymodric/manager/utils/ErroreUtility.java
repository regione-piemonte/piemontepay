/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.utils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import it.csi.epay.epaymodric.business.epaymodric.bo.Errore;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDErrore;


/**
 * 
 * @author vsgro
 */
public class ErroreUtility {

    final static Logger logger = LogManager.getLogger(ErroreUtility.class);

   
    public static Errore getErrore(CblDErrore entity) {
        Errore errore = null;
        if (entity==null) {
            logger.warn ( "Errore.getErrore: oggetto null" );
        } else {
            errore = new Errore();
            errore.setId ( entity.getId () );
            errore.setCodiceErrore ( entity.getCodiceErrore () );
            errore.setDescrizioneErrore ( entity.getDescrizioneErrore () );
            errore.setFlagMail ( entity.getFlagMail () );
            errore.setFlagRielaborazione ( entity.getFlagRielaborazione () );
            errore.setTipologia ( entity.getTipologia () );
        }
        return errore;
    }
    
    public static CblDErrore getErrore(Errore errore) {
        CblDErrore entity = null;
        if (errore==null) {
            logger.warn ( "Errore.getErrore - to entity: oggetto null" );
        } else {
            entity = new CblDErrore();
            entity.setId ( errore.getId () );
            entity.setCodiceErrore ( errore.getCodiceErrore () );
            entity.setDescrizioneErrore ( errore.getDescrizioneErrore () );
            entity.setFlagMail ( errore.getFlagMail () );
            entity.setFlagRielaborazione ( errore.getFlagRielaborazione () );
            entity.setTipologia ( errore.getTipologia () );
        }
        return entity;
    }
    
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.utils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import it.csi.epay.epaymodric.business.epaymodric.bo.Psp;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTPsp;


/**
 * 
 * @author vsgro
 */
public class PspUtility {

    final static Logger logger = LogManager.getLogger(PspUtility.class);

  
    public static Psp getPsp(CblTPsp entity) {
        Psp psp = null;
        if (entity==null) {
            logger.warn ( "PspUtility.getPsp: oggetto null" );
        } else {
            psp = new Psp();
            psp.setId ( entity.getId () );
            psp.setIdentificativo ( entity.getIdentificativoPsp () );
            psp.setDenominazione ( entity.getDenominazionePsp () );
            psp.setFlagRiconciliabile ( entity.getFlagRiconciliabile () );
        }
        return psp;
    }
    
}

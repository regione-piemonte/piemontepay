/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaymodric.business.epaymodric.bo.Errore;
import it.csi.epay.epaymodric.business.epaymodric.manager.ErroreManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.ErroreUtility;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDErrore;
import it.csi.epay.epaymodric.business.epaymodric.repository.ErroreRepository;

/**
 * @author vsgro
 */
@Service
public class ErroreManagerImpl implements ErroreManager {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    private ErroreRepository repository;
    
    @Override
    public Errore leggi(String codiceErrore){
        Errore errore = new Errore ();
        logger.info ( "ErroreManagerImpl.findByCodiceErrore: INIZIO" );
        CblDErrore cblDErrore = repository.findByCodiceErrore ( codiceErrore );
        if(cblDErrore!=null) {
            errore = ErroreUtility.getErrore ( cblDErrore );
        }
        logger.info ( "ErroreManagerImpl.findByCodiceErrore: FINE" );
        return errore;
    }

    public ErroreRepository getRepository () {
        return repository;
    }

    public void setRepository ( ErroreRepository repository ) {
        this.repository = repository;
    }
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaymodric.business.epaymodric.bo.Psp;
import it.csi.epay.epaymodric.business.epaymodric.manager.PspManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.PspUtility;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTPsp;
import it.csi.epay.epaymodric.business.epaymodric.repository.PspRepository;


/**
 * @author vsgro
 */

@Service
public class PspManagerImpl implements PspManager {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    private PspRepository repository;


    
    @Override
    public Psp leggiPerIdentificativoPsp(String identificativoPsp) {
        logger.info ( "PspManagerImpl.leggiPerIdentificativoPsp: INIZIO" );
        CblTPsp cblTPsp = repository.findByIdentificativoPsp ( identificativoPsp );
        logger.info ( "PspManagerImpl.leggiPerIdentificativoPsp: FINE" );
        return PspUtility.getPsp ( cblTPsp );
    }
    

    public PspRepository getRepository () {
        return repository;
    }

    public void setRepository ( PspRepository repository ) {
        this.repository = repository;
    }

}

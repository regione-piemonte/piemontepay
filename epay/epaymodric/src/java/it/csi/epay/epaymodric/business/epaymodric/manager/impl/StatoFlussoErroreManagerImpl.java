/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaymodric.business.epaymodric.bo.StatoFlussoErrore;
import it.csi.epay.epaymodric.business.epaymodric.manager.StatoFlussoErroreManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.StatoFlussoErroreUtility;
import it.csi.epay.epaymodric.business.epaymodric.model.CblRStatoFlussoErrore;
import it.csi.epay.epaymodric.business.epaymodric.repository.StatoFlussoErroreRepository;


/**
 * @author vsgro
 */
@Service
public class StatoFlussoErroreManagerImpl implements StatoFlussoErroreManager {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    private StatoFlussoErroreRepository repository;
    
    @Override
    @Transactional
    public StatoFlussoErrore inserisciFlussoErrore ( StatoFlussoErrore statoFlussoErrore ) throws ServiceException {
        logger.info ( "StatoFlussoErroreManagerImpl.saveAndFlush: INIZIO" );
        CblRStatoFlussoErrore  errore = repository.saveAndFlush (StatoFlussoErroreUtility.getStatoFlussoErrore ( statoFlussoErrore ) );
        logger.info ( "StatoFlussoErroreManagerImpl.saveAndFlush: FINE" );
        return StatoFlussoErroreUtility.getStatoFlussoErrore(errore);
    }

    public StatoFlussoErroreRepository getRepository () {
        return repository;
    }

    public void setRepository ( StatoFlussoErroreRepository repository ) {
        this.repository = repository;
    }

}

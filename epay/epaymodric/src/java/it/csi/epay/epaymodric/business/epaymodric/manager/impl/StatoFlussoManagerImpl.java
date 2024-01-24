/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.csi.epay.epaymodric.business.epaymodric.manager.StatoFlussoManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.FlussiUtilityWS;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDStatoFlusso;
import it.csi.epay.epaymodric.business.epaymodric.repository.StatoFlussoRepository;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOStatoFlusso;


/**
 * @author vsgro
 */
@Service
public class StatoFlussoManagerImpl implements StatoFlussoManager {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    private StatoFlussoRepository repository;

    public StatoFlussoRepository getRepository () {
        return repository;
    }

    public void setRepository ( StatoFlussoRepository repository ) {
        this.repository = repository;
    }

    @Override
    public ArrayList<DTOStatoFlusso> ricercaStatiFlussi () {
        logger.info ( "StatoFlussoManagerImpl.ricercaStatiFlussi: INIZIO" );
        ArrayList<DTOStatoFlusso> statiFlussi = new ArrayList<DTOStatoFlusso> ();
        List<CblDStatoFlusso> entities = repository.findAll (new Sort(Sort.Direction.ASC, "descrizioneStato"));
		for ( CblDStatoFlusso entity : entities ) {
			statiFlussi.add ( FlussiUtilityWS.getDTOStatoFlusso ( entity ) );
		}
        logger.info ( "StatoFlussoManagerImpl.ricercaStatiFlussi: FINE" );
        return statiFlussi;
    }

}

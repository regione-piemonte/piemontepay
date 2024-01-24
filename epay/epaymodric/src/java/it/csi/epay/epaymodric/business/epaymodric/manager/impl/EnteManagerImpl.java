/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaymodric.business.epaymodric.bo.Ente;
import it.csi.epay.epaymodric.business.epaymodric.manager.EnteManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.EnteUtility;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;
import it.csi.epay.epaymodric.business.epaymodric.repository.EnteRepository;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOEnte;


/**
 * @author vsgro
 */
@Service
public class EnteManagerImpl implements EnteManager {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    private EnteRepository repository;

    @Override
    public Ente leggiPerIdEnte ( String idEnte ) {
        logger.info ( "EnteManagerImpl.findByIdEnte: INIZIO" );
        Ente ente = EnteUtility.getEnte ( repository.findByIdEnte ( idEnte ) );
        logger.info ( "EnteManagerImpl.findByIdEnte: FINE" );
        return ente;
    }

    @Override
    public Ente leggiPerCodiceFiscaleEnte ( String codiceFiscale ) {
        logger.info ( "EnteManagerImpl.findByCodiceFiscale: INIZIO" );
        Ente ente = EnteUtility.getEnte ( repository.findByCodiceFiscale ( codiceFiscale ) );
        logger.info ( "EnteManagerImpl.findByCodiceFiscale: FINE" );
        return ente;
    }

    public EnteRepository getRepository () {
        return repository;
    }

    public void setRepository ( EnteRepository repository ) {
        this.repository = repository;
    }

    @Override
    public List<DTOEnte> elencaEnti () {
        List<CblTEnte> enti = repository.findAll ();
        List<DTOEnte> enteList = new ArrayList<DTOEnte> ();

		for ( CblTEnte enteCurr : enti ) {
			DTOEnte ente = EnteUtility.getDTOEnte ( enteCurr );
			enteList.add ( ente );
		}

        return enteList;
    }
}

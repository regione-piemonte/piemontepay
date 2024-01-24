/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaymodric.business.epaymodric.bo.Catalogo;
import it.csi.epay.epaymodric.business.epaymodric.manager.SchedulazioneManager;

/**
 * @author vsgro
 */

@Component
@Transactional
public class SchedulazioneManagerImpl implements SchedulazioneManager {
    
    private final Logger logger = LogManager.getLogger (this.getClass());

    @Override
    public List<Catalogo> leggiSchedulazione ( String idEnte ) {
        logger.info ( "SchedulazioneServiceImpl.leggiSchedulazione: INIZIO" );
        //FIXME [VS] da implementare leggiSchedulazione ( )
        logger.info ( "SchedulazioneServiceImpl.leggiSchedulazione: FINE" );
        return null;
    }

    @Override
    public List<String> verificaAttivazioneSchedulazione ( String idEnte ) {
        logger.info ( "SchedulazioneServiceImpl.verificaAttivazioneSchedulazione: INIZIO" );
        //FIXME [VS] da implementare verificaAttivazioneSchedulazione ( )
        logger.info ( "SchedulazioneServiceImpl.verificaAttivazioneSchedulazione: FINE" );
        return null;
    }

}

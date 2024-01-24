/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaymodric.business.epaymodric.manager.StatoElaborazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDStatoElaborazione;
import it.csi.epay.epaymodric.business.epaymodric.repository.StatoElaborazioneRepository;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOStatoElaborazione;


/**
 *
 */
@Service
public class StatoElaborazioneManagerImpl implements StatoElaborazioneManager {

    @Autowired
    private StatoElaborazioneRepository statoElaborazioneRepository;

    @Override
    public List<DTOStatoElaborazione> recuperaStatiElaborazione () {

        List<DTOStatoElaborazione> statiElaborazione = new ArrayList<DTOStatoElaborazione>();

        List<CblDStatoElaborazione> statiElaborazioneEntities = statoElaborazioneRepository.findAll ();

        if (null != statiElaborazioneEntities) {
            for (CblDStatoElaborazione entity : statiElaborazioneEntities) {
                DTOStatoElaborazione dto = new DTOStatoElaborazione();
                dto.setCodiceStato ( entity.getCodiceStato () );
                dto.setDescrizioneStato ( entity.getDescrizioneStato () );
                dto.setId ( Long.toString ( entity.getId () ) );
                statiElaborazione.add ( dto );
            }
        }

        return statiElaborazione;
    }

}

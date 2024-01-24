/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.mdp.mdppagopaapi.business.StatoTransazioneService;
import it.csi.mdp.mdppagopaapi.integration.domain.StatoTransazione;
import it.csi.mdp.mdppagopaapi.integration.repository.StatoTransazioneRepository;

@Service
public class StatoTransazioneServiceImpl implements StatoTransazioneService {

    @Autowired
    private StatoTransazioneRepository statoTransazioneRepository;

    @Override
    public StatoTransazione findByCodStato ( Long codStato ) {

        return statoTransazioneRepository.findOne ( codStato );
    }


}

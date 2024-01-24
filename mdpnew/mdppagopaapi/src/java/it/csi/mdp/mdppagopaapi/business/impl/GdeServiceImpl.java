/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business.impl;

import it.csi.mdp.mdppagopaapi.business.GdeService;
import it.csi.mdp.mdppagopaapi.integration.domain.Gde;
import it.csi.mdp.mdppagopaapi.integration.repository.GdeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GdeServiceImpl implements GdeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GdeServiceImpl.class);

	@Autowired
	private GdeRepository gdeRepository;


	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public Gde inserisciEventoGiornale(Gde gde) {
		try {
			return gdeRepository.save(gde);
		} catch (Exception e) {
			LOGGER.error("Errore in fase salvataggio sul GDE", e);
			return null;
		}
	}

}

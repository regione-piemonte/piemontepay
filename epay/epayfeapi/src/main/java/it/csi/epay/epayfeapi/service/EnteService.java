/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import it.csi.epay.epayfeapi.entity.EpayTEnti;
import it.csi.epay.epayfeapi.repository.EnteRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;


@ApplicationScoped
@Transactional
public class EnteService {

	@Inject
	EnteRepository enteRepository;

	public EpayTEnti findByCodiceFiscale ( String codiceFiscale ) {
		return enteRepository.findByCodiceFiscale ( codiceFiscale );
	}

}

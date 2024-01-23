/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import it.csi.epay.epayfeapi.entity.EpayDEsitoChiamataEsterna;
import it.csi.epay.epayfeapi.repository.EsitoChiamataEsternaRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;


@ApplicationScoped
@Transactional
public class EsitoChiamataEsternaService {

	@Inject
	EsitoChiamataEsternaRepository esitoChiamataEsternaRepository;

	public EpayDEsitoChiamataEsterna findByCodice ( String codice ) {
		return esitoChiamataEsternaRepository.findByCodice ( codice );
	}
}

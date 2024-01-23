/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import it.csi.epay.epayfeapi.repository.ChiamanteAutorizzazioneChiamanteRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;


@ApplicationScoped
@Transactional
public class ChiamanteAutorizzazioneChiamanteService {

	@Inject
	ChiamanteAutorizzazioneChiamanteRepository chiamanteAutorizzazioneChiamanteRepository;

	public long countByCodiceChiamanteAndCodiceAutorizzazioneChiamante ( String codiceChiamante, String codiceAutorizzazioneChiamante ) {
		return chiamanteAutorizzazioneChiamanteRepository.countByCodiceChiamanteAndCodiceAutorizzazioneChiamante (
						codiceChiamante,
						codiceAutorizzazioneChiamante );
	}
}

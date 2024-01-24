/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import it.csi.epay.epayfeapi.entity.EpayTEsitiRicevuti;
import it.csi.epay.epayfeapi.repository.EsitiRicevutiRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;


@ApplicationScoped
@Transactional
public class EsitiRicevutiService {

	@Inject
	EsitiRicevutiRepository repository;

	public EpayTEsitiRicevuti findByIdRegistro ( Long idRegistro ) {
		return repository.findByIdRegistro ( idRegistro );
	}

	public EpayTEsitiRicevuti findByIdRegistroAndIdQuietanzaNotNull ( Long idRegistro ) {
		return repository.findByIdRegistroAndIdQuietanzaNotNull ( idRegistro );
	}

}

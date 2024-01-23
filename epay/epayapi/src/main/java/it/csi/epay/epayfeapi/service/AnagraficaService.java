/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import it.csi.epay.epayfeapi.entity.EpayTAnagrafica;
import it.csi.epay.epayfeapi.repository.AnagraficaRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;


@ApplicationScoped
@Transactional
public class AnagraficaService {

	@Inject
	AnagraficaRepository anagraficaRepository;

	public boolean isPersistent ( EpayTAnagrafica epayTAnagrafica ) {
		return anagraficaRepository.isPersistent ( epayTAnagrafica );
	}

	public EpayTAnagrafica save ( EpayTAnagrafica epayTAnagrafica ) {
		anagraficaRepository.persist ( epayTAnagrafica );
		return epayTAnagrafica;
	}

	public EpayTAnagrafica getByAnagrafica ( EpayTAnagrafica epayTAnagrafica ) {
		return anagraficaRepository.findById ( epayTAnagrafica.getIdAnagrafica () );
	}
}

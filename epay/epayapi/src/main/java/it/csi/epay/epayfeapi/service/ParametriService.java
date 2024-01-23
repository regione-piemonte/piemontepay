/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import it.csi.epay.epayfeapi.entity.EpayTParametri;
import it.csi.epay.epayfeapi.repository.ParametriRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;


@ApplicationScoped
@Transactional
public class ParametriService {

	@Inject
	ParametriRepository repository;

	public List<EpayTParametri> findByGruppo ( String gruppo ) {
		return repository.findByGruppo ( gruppo );
	}

	public EpayTParametri getParametro ( String gruppo, String codice ) {
		return repository.getParametro ( gruppo, codice );
	}

}

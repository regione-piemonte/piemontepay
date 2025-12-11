/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import it.csi.epay.epayfeapi.entity.EpayDChiamanteEsterno;
import it.csi.epay.epayfeapi.repository.ChiamanteEsternoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;


@ApplicationScoped
@Transactional
public class ChiamanteEsternoService {

	private final ChiamanteEsternoRepository chiamanteEsternoRepository;

	public ChiamanteEsternoService ( ChiamanteEsternoRepository chiamanteEsternoRepository ) {
		this.chiamanteEsternoRepository = chiamanteEsternoRepository;
	}

	public EpayDChiamanteEsterno findByCodiceChiamante ( String codiceChiamante ) {
		return chiamanteEsternoRepository.findByCodiceChiamante ( codiceChiamante );
	}
}

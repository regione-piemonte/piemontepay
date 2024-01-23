/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import it.csi.epay.epayfeapi.dto.ChiamanteEsternoDTO;
import it.csi.epay.epayfeapi.entity.EpayDChiamanteEsterno;
import it.csi.epay.epayfeapi.mapper.ChiamanteEsternoMapper;
import it.csi.epay.epayfeapi.repository.ChiamanteEsternoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;


@ApplicationScoped
@Transactional
public class ChiamanteEsternoService {

	private final ChiamanteEsternoRepository chiamanteEsternoRepository;

	private final ChiamanteEsternoMapper chiamanteEsternoMapper;

	public ChiamanteEsternoService ( ChiamanteEsternoRepository chiamanteEsternoRepository, ChiamanteEsternoMapper chiamanteEsternoMapper ) {
		this.chiamanteEsternoRepository = chiamanteEsternoRepository;
		this.chiamanteEsternoMapper = chiamanteEsternoMapper;
	}

	public List<ChiamanteEsternoDTO> findAllChiamanteEsterno () {
		List<EpayDChiamanteEsterno> epayDChiamanteEsterno = chiamanteEsternoRepository.findAll ().list ();
		return chiamanteEsternoMapper.toDtoList ( epayDChiamanteEsterno );
	}

	public EpayDChiamanteEsterno findByCodiceChiamante ( String codiceChiamante ) {
		return chiamanteEsternoRepository.findByCodiceChiamante ( codiceChiamante );
	}
}

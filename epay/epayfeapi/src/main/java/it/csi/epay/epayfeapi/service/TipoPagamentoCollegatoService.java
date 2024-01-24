/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import it.csi.epay.epayfeapi.repository.TipoPagamentoCollegatoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;


@ApplicationScoped
@Transactional
public class TipoPagamentoCollegatoService {

	@Inject
	TipoPagamentoCollegatoRepository tipoPagamentoCollegatoRepository;

	public long countByIdPagamentoSecondario ( Long idTipoPagamento ) {
		return tipoPagamentoCollegatoRepository.countByIdPagamentoSecondario ( idTipoPagamento );
	}

}

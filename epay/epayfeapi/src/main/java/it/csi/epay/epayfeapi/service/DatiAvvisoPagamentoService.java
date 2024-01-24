/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import it.csi.epay.epayfeapi.entity.EpayTDatiAvvisoPagamento;
import it.csi.epay.epayfeapi.repository.DatiAvvisoPagamentoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;


@ApplicationScoped
@Transactional
public class DatiAvvisoPagamentoService {

	@Inject
	DatiAvvisoPagamentoRepository repository;

	public EpayTDatiAvvisoPagamento findByIdTipoPagamento ( Long idTipoPagamento ) {
		return repository.findByIdTipoPagamento ( idTipoPagamento );
	}

}

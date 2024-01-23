/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import it.csi.epay.epayfeapi.entity.EpayDStatoPagamento;
import it.csi.epay.epayfeapi.repository.StatoPagamentoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;


@ApplicationScoped
@Transactional
public class StatoPagamentoService {

	@Inject
	StatoPagamentoRepository statoPagamentoRepository;

	public EpayDStatoPagamento findById ( Integer idPagamento ) {
		return statoPagamentoRepository.find ( "idStato", idPagamento ).firstResult ();
	}
}

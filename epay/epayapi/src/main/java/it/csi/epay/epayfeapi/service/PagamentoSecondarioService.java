/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import it.csi.epay.epayfeapi.entity.EpayTPagamentoSecondario;
import it.csi.epay.epayfeapi.repository.PagamentoSecondarioRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;


@ApplicationScoped
@Transactional
public class PagamentoSecondarioService {

	@Inject
	PagamentoSecondarioRepository pagamentoSecondarioRepository;

	public EpayTPagamentoSecondario getPagamentoSecondarioByIdPagamentoPrincipale ( Long idPagamento ) {
		return pagamentoSecondarioRepository.getPagamentoSecondarioByIdPagamentoPrincipale ( idPagamento );
	}

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import it.csi.epay.epayfeapi.entity.EpayTPagamentoSecondarioComponenti;
import it.csi.epay.epayfeapi.repository.PagamentoSecondarioComponentiRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;


@ApplicationScoped
@Transactional
public class PagamentoSecondarioComponentiService {

	@Inject
	PagamentoSecondarioComponentiRepository pagamentoSecondarioComponentiRepository;

	public List<EpayTPagamentoSecondarioComponenti> getPagamentoSecondarioComponentiById ( Long idPagamentoSecondario ) {
		return pagamentoSecondarioComponentiRepository.getPagamentoSecondarioComponentiById ( idPagamentoSecondario );
	}
}

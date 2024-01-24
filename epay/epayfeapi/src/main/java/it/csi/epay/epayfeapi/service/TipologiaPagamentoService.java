/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import it.csi.epay.epayfeapi.entity.EpayDTipologiaPagamento;
import it.csi.epay.epayfeapi.repository.TipologiaPagamentoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;


@ApplicationScoped
@Transactional
public class TipologiaPagamentoService {

	@Inject
	TipologiaPagamentoRepository tipologiaPagamentoRepository;

	public EpayDTipologiaPagamento findByCodice ( String codice ) {
		return tipologiaPagamentoRepository.findByCodice ( codice );
	}

}

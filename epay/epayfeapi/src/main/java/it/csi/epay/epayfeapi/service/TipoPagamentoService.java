/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import it.csi.epay.epayfeapi.dto.PagedListResultDTO;
import it.csi.epay.epayfeapi.entity.EpayDTipologiaPagamento;
import it.csi.epay.epayfeapi.entity.EpayTEnti;
import it.csi.epay.epayfeapi.entity.EpayTTipoPagamento;
import it.csi.epay.epayfeapi.repository.TipoPagamantoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;


@ApplicationScoped
@Transactional
public class TipoPagamentoService {

	@Inject
	TipoPagamantoRepository tipoPagamantoRepository;

	public List<EpayTTipoPagamento> findByEnteAndCodiceVersamento ( EpayTEnti ente, String codiceVersamento ) {
		return tipoPagamantoRepository.findByEnteAndCodiceVersamento ( ente, codiceVersamento );
	}

	public PagedListResultDTO<EpayTTipoPagamento> findByEnteAndTipologiaPagamentoAndLikeDescrizione (
					EpayTEnti enteEntity,
					EpayDTipologiaPagamento tipologiaPagamentoEntity,
					String likeDescrizione,
					String[] sortableFields,
					String inputSortString,
					int pageIndex, // N.B zero-based
					int pageSize ) {

		return tipoPagamantoRepository.findByEnteAndTipologiaPagamentoAndLikeDescrizione (
						enteEntity,
						tipologiaPagamentoEntity,
						likeDescrizione,
						sortableFields,
						inputSortString,
						pageIndex,
						pageSize );
	}

	public EpayTTipoPagamento findByTipoPagamento ( EpayTTipoPagamento tipoPagamento ) {
		return tipoPagamantoRepository.findByTipoPagamento ( tipoPagamento );
	}

	public List<EpayTTipoPagamento> findByEnte ( EpayTEnti ente ) {
		return tipoPagamantoRepository.findByEnte ( ente );
	}
}

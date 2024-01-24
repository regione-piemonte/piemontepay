/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import it.csi.epay.epayservices.integration.db.entities.EpayTPagamentoSecondario;
import it.csi.epay.epayservices.model.PagamentoSecondario;
import it.csi.epay.epayservices.model.TipoPagamento;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Stateless ( name = "PagamentoSecondarioManager", mappedName = "PagamentoSecondarioManager" )
public class PagamentoSecondarioManager extends _Manager {

	@PersistenceContext
	private EntityManager entityManager;

	public PagamentoSecondario getPagamentoSecondario ( Long idPagamento ) {
		try {
			TypedQuery<EpayTPagamentoSecondario> query = entityManager.createNamedQuery ( "EpayTPagamentoSecondario.ricercaByIdPagamento", EpayTPagamentoSecondario.class );
			query.setParameter ( "idPagamento", idPagamento );
			EpayTPagamentoSecondario tPagamentoSecondario = query.getSingleResult ();
			PagamentoSecondario ps = map ( tPagamentoSecondario, PagamentoSecondario.class );
			TipoPagamento tp = map ( tPagamentoSecondario.getEpayTTipoPagamento (), TipoPagamento.class );
			ps.setTipoPagamento ( tp );
			return ps;
		} catch ( NoResultException e ) {
			return null;
		}
	}
}

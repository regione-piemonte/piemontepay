/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.csi.epay.epayservices.integration.db.entities.EpayTPagamentoRiferimenti;


@Stateless ( name = "PagamentoRiferimentiManager", mappedName = "PagamentoRiferimentiManager" )
public class PagamentoRiferimentiManager extends _Manager {

	@PersistenceContext
	private EntityManager entityManager;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteAllPagamentoRiferimento ( Long idPagamento ) {
		try {
            TypedQuery<EpayTPagamentoRiferimenti> query
                = entityManager.createNamedQuery ( "EpayTPagamentoRiferimenti.elencoRiferimentiByIdPagamento", EpayTPagamentoRiferimenti.class );
			query.setParameter("idPagamento", idPagamento);
            List<EpayTPagamentoRiferimenti> tPagamentoRiferimenti = query.getResultList ();
            for ( EpayTPagamentoRiferimenti epayTPagamentoRiferimenti: tPagamentoRiferimenti ) {
                entityManager.remove ( epayTPagamentoRiferimenti );
			}
			entityManager.flush();
		} catch (NoResultException nre) {
			;
		}
	}	
	
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.csi.epay.epayservices.integration.db.entities.EpayDTipologiaPagamento;
import it.csi.epay.epayservices.model.TipologiaPagamento;


@Stateless ( name = "TipologiaPagamentoManager", mappedName = "TipologiaPagamentoManager" )
public class TipologiaPagamentoManager extends _Manager {

    @PersistenceContext
    private EntityManager entityManager;

    public TipologiaPagamento getTipologiaPagamentoByCodice ( String codice ) {
        try {
            TypedQuery<EpayDTipologiaPagamento> query
                = entityManager.createNamedQuery ( "EpayDTipologiaPagamento.findOneByCodice", EpayDTipologiaPagamento.class );
            query.setParameter ( "codice", codice );
            EpayDTipologiaPagamento tTipologiaPagamento = query.getSingleResult ();
            TipologiaPagamento output = mappaDatiAvvisiPagamento ( tTipologiaPagamento );
            return output;
        } catch ( NoResultException e ) {
            return null;
        }
    }

    private TipologiaPagamento mappaDatiAvvisiPagamento ( EpayDTipologiaPagamento entity ) {
        TipologiaPagamento datiAvvisoPagamento = map ( entity, TipologiaPagamento.class );
        // DOZER
        return datiAvvisoPagamento;
    }
}

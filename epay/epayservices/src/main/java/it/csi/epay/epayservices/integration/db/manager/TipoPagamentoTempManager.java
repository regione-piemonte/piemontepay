/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.csi.epay.epayservices.integration.db.entities.EpayTTipoPagamentoTemp;
import it.csi.epay.epayservices.model.TipoPagamentoTemp;


@Stateless ( name = "TipoPagamentoTempManager", mappedName = "TipoPagamentoTempManager" )
public class TipoPagamentoTempManager extends _Manager {

    @PersistenceContext
    private EntityManager entityManager;

    public List<TipoPagamentoTemp> getByIdOperazione ( String idOperazione ) {
        TypedQuery<EpayTTipoPagamentoTemp> query = entityManager.createNamedQuery (
            "EpayTTipoPagamentoTemp.findByIdOperazione", EpayTTipoPagamentoTemp.class );
        query.setParameter ( "idOperazione", idOperazione );
        List<EpayTTipoPagamentoTemp> tEnte = query.getResultList ();
        ArrayList<TipoPagamentoTemp> risultati = new ArrayList<> ();
        for ( EpayTTipoPagamentoTemp dto: tEnte ) {
            risultati.add ( map ( dto, TipoPagamentoTemp.class ) );
        }
        return risultati;
    }

    public void inserisci ( TipoPagamentoTemp tipoPagamentoTemp ) {
        EpayTTipoPagamentoTemp mapped = map ( tipoPagamentoTemp, EpayTTipoPagamentoTemp.class );
        entityManager.persist ( mapped );
    }

    public void deleteByIdTipoPagamentoTemp ( Long id ) {
        EpayTTipoPagamentoTemp entity = entityManager.find ( EpayTTipoPagamentoTemp.class, id );
        entityManager.remove ( entity );
    }

}

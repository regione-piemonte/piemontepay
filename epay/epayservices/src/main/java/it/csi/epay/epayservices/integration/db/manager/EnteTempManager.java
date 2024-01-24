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

import it.csi.epay.epayservices.integration.db.entities.EpayTEntiTemp;
import it.csi.epay.epayservices.model.EnteTemp;


@Stateless ( name = "EnteTempManager", mappedName = "EnteTempManager" )
public class EnteTempManager extends _Manager {

	@PersistenceContext
	private EntityManager entityManager;

    public List<EnteTemp> getByIdOperazione ( String idOperazione ) {
        TypedQuery<EpayTEntiTemp> query = entityManager.createNamedQuery (
            "EpayTEntiTemp.findByIdOperazione", EpayTEntiTemp.class );
        query.setParameter ( "idOperazione", idOperazione );
        List<EpayTEntiTemp> tEnte = query.getResultList ();
        ArrayList<EnteTemp> risultati = new ArrayList<> ();
        for ( EpayTEntiTemp dto: tEnte ) {
            risultati.add ( map ( dto, EnteTemp.class ) );
        }
        return risultati;
    }

    public void inserisci ( EnteTemp enteTemp ) {
        EpayTEntiTemp mapped = map ( enteTemp, EpayTEntiTemp.class );
        mapped.setLogo ( enteTemp.getLogo () );
        entityManager.persist ( mapped );
    }

    public void modifica ( EnteTemp enteTemp ) {
        EpayTEntiTemp mapped = map ( enteTemp, EpayTEntiTemp.class );
        mapped.setLogo ( enteTemp.getLogo () );
        entityManager.merge ( mapped );
    }

    public void deleteByIdEnteTemp ( Long id ) {
        EpayTEntiTemp entity = entityManager.find ( EpayTEntiTemp.class, id );
        entityManager.remove ( entity );
    }
}

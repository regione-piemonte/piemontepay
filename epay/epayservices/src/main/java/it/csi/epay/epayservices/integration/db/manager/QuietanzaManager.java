/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import it.csi.epay.epayservices.integration.db.entities.EpayTQuietanzaEsito;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.QuietanzaEsito;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Stateless ( name = "QuietanzaManager", mappedName = "QuietanzaManager" )
public class QuietanzaManager extends _Manager {

	@PersistenceContext
	private EntityManager entityManager;

	public Long inserisciNuovaQuietanza ( QuietanzaEsito quietanzaEsito ) {
		EpayTQuietanzaEsito tQuietanzaEsito = map ( quietanzaEsito, EpayTQuietanzaEsito.class );
		entityManager.persist ( tQuietanzaEsito );
		return tQuietanzaEsito.getIdQuietanzaEsito ();
	}

	public QuietanzaEsito ricercaQuietanzaById ( final Long idQuietanzaEsito ) throws NoDataException {
		try {
			TypedQuery<EpayTQuietanzaEsito> query = entityManager.createNamedQuery ( "EpayTQuietanzaEsito.ricercaQuietanzaById", EpayTQuietanzaEsito.class );
			query.setParameter ( "idQuietanzaEsito", idQuietanzaEsito );
			EpayTQuietanzaEsito epayTQuietanzaEsito = query.getSingleResult ();
			return map ( epayTQuietanzaEsito, QuietanzaEsito.class );
		} catch ( NoResultException e ) {
			throw new NoDataException ( "Quietanza esito non trovata (idQuietanzaEsito = " + idQuietanzaEsito + ")" );
		}
	}

}

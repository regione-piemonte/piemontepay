/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import it.csi.epay.epayservices.integration.db.entities.EpayTQuietanzaEsito;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless ( name = "QuietanzaEsitoManager", mappedName = "QuietanzaEsitoManager" )
public class QuietanzaEsitoManager extends _Manager {

	@PersistenceContext
	private EntityManager entityManager;

	public EpayTQuietanzaEsito findById ( Long id ) {
		return entityManager.find ( EpayTQuietanzaEsito.class, id );
	}
}

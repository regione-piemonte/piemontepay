/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import it.csi.mdp.mdppagopacheckout.entity.Transazione;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;


@ApplicationScoped
public class TransazioneRepository implements PanacheRepository<Transazione> {

	@Inject
	EntityManager entityManager;

	public Long getNextSequenceValue () {
		var nativeQuery = "SELECT nextval('seq_transazione')";
		var query = entityManager.createNativeQuery ( nativeQuery );
		return ( (Number) query.getSingleResult () ).longValue ();
	}

	public Transazione findByTransactionId ( String transactionId ) {
		return find ( "transactionId = ?1", transactionId ).firstResult ();
	}
}

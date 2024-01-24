/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import it.csi.mdp.mdppagopacheckout.entity.Config;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ConfigRepository implements PanacheRepository<Config> {

	@SuppressWarnings ( "all" ) // la query è safe anche senza check su key, perchè è sempre e solo settata internamente, altrimenti inserire bonifica query
	public Config findByKey ( String key ) {
		// non funziona la find con o senza virgolette (su h2, su postgress funzionava), ottima la scelta del nome
		// find ( "\"key\" = ?1", key ).firstResult ();
		// non funzionano neanche le NamedQuery, proviamo con le NativeQuery (deve funzionare sia su postgres che h2)
		var value = (String) getEntityManager ().createNativeQuery ( String.format ( "SELECT \"value\" FROM config where \"key\" = '%s'", key ) )
						.getSingleResult ();
		return new Config ( value );
	}
}

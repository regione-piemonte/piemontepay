/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.entity.EpayTConfigurazione;
import it.csi.epay.epayfeapi.entity.EpayTEnti;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ConfigurazioneRepository implements PanacheRepository<EpayTConfigurazione> {

	public EpayTConfigurazione findByCodice ( String codice ) {
		var methodName = "[findByCodice] ";
		Log.infof ( "%sBEGIN", methodName );
		Log.infof ( "%scodice:%s", methodName, codice );

		var result = find ( "codice", codice ).firstResult ();

		Log.infof ( "%sresult:%s", methodName, result );
		Log.infof ( "%sEND", methodName );
		return result;
	}

	public EpayTConfigurazione findByCodiceAndEnte ( String codice, EpayTEnti ente ) {
		return find ( "codice = ?1 and epayTEnti = ?2", codice, ente ).firstResult ();
	}
}

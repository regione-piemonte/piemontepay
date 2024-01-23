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
		String methodName = "[findByCodice] ";
		Log.info ( methodName + "BEGIN" );
		Log.info ( methodName + "codice:" + codice );

		EpayTConfigurazione result = find ( "codice", codice ).firstResult ();

		Log.info ( methodName + "result:" + result );
		Log.info ( methodName + "END" );
		return result;
	}

	public EpayTConfigurazione findByCodiceAndEnte ( String codice, EpayTEnti ente ) {
		return find ( "codice = ?1 and epayTEnti = ?2", codice, ente ).firstResult ();
	}
}

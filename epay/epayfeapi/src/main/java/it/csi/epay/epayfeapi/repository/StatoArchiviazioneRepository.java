/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.entity.EpayTEnti;
import it.csi.epay.epayfeapi.entity.EpayTStatoArchiviazione;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class StatoArchiviazioneRepository implements PanacheRepository<EpayTStatoArchiviazione> {

	public EpayTStatoArchiviazione findStatoArchiviazione ( String codiceFiscale, EpayTEnti enteEntity ) {
		var methodName = "[findStatoArchiviazione] ";
		Log.infof ( "%sBEGIN", methodName );

		var query = "upper(codiceFiscale) = upper(?1) and epayTEnti = ?2";
		Log.infof ( "%scodiceFiscale:%s", methodName, codiceFiscale );
		Log.infof ( "%senteEntity:%s", methodName, enteEntity );
		Log.infof ( "%squery: select * from EpayTStatoArchiviazione where %s", methodName, query );

		var result = find ( query, codiceFiscale, enteEntity ).firstResult ();

		Log.infof ( "%sresult:%s", methodName, result );
		Log.infof ( "%sEND", methodName );
		return result;
	}
}

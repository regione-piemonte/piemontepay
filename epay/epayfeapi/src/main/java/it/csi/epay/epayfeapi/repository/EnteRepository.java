/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.entity.EpayTEnti;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class EnteRepository implements PanacheRepository<EpayTEnti> {

	public EpayTEnti findByCodiceFiscale ( String codiceFiscale ) {
		var methodName = "[findByCodiceFiscale] ";
		Log.infof ( "%sBEGIN", methodName );

		var query = "upper(codiceFiscale) = upper(?1)";
		Log.infof ( "%s codiceFiscale:%s", methodName, codiceFiscale );
		Log.infof ( "%squery: select * from EpayTEnti where %s", methodName, query );

		var result = find ( query, codiceFiscale ).firstResult ();

		Log.infof ( "%sresult:%s", methodName, result );
		Log.infof ( "%sEND", methodName );
		return result;
	}
}

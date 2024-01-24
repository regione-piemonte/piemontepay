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
		String methodName = "[findStatoArchiviazione] ";
		Log.info ( methodName + "BEGIN" );

		String query = "codiceFiscale = ?1 and epayTEnti = ?2";
		Log.info ( methodName + "?1 (codiceFiscale):" + codiceFiscale );
		Log.info ( methodName + "?2 (enteEntity):" + enteEntity );
		Log.info ( methodName + "query: select * from EpayTStatoArchiviazione where " + query );

		EpayTStatoArchiviazione result = find ( query, codiceFiscale, enteEntity ).firstResult ();

		Log.info ( methodName + "result:" + result );
		Log.info ( methodName + "END" );
		return result;
	}
}

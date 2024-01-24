/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.logging.Log;
import it.csi.mdp.mdppagopacheckout.entity.TransazioneIuv;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class TransazioneIuvRepository implements PanacheRepository<TransazioneIuv> {

	public void save ( String transactionId, String iuv ) {
		Log.infof ( "Saving new TransazioneIuv, transactionId = %s, iuv = %s", transactionId, iuv );
		persist ( new TransazioneIuv ( transactionId, iuv ) );
	}
}

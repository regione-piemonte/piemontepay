/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import static it.csi.epay.epayfeapi.util.Constants.MAX_LENGTH_DESCRIZIONE_ERRORE;

import java.sql.Timestamp;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.entity.EpayTChiamataEsternaNonValida;
import it.csi.epay.epayfeapi.repository.ChiamataEsternaNonValidaRepository;
import it.csi.epay.epayfeapi.security.User;


@ApplicationScoped
@Transactional
public class ChiamataEsternaNonValidaService {

	@Inject
	ChiamataEsternaNonValidaRepository chiamataEsternaNonValidaRepository;

	public void track (
		EpayTChiamataEsternaNonValida chiamataEsternaNonValidaEntity,
		User user,
		String codiceFiscale,
		String iuv,
		String descErrore ) {

		Log.info ( "descErrore:" + descErrore );

		if ( chiamataEsternaNonValidaEntity == null ) {
			String descErroreMax100 = descErrore != null && descErrore.length () > MAX_LENGTH_DESCRIZIONE_ERRORE
							? descErrore.substring ( 0, MAX_LENGTH_DESCRIZIONE_ERRORE )
							: descErrore;
			chiamataEsternaNonValidaEntity = new EpayTChiamataEsternaNonValida ();
			chiamataEsternaNonValidaEntity.setCodiceChiamante ( user.getName () );
			chiamataEsternaNonValidaEntity.setCodiceFiscale ( codiceFiscale );
			chiamataEsternaNonValidaEntity.setDescrizioneErrore ( descErroreMax100 );
			chiamataEsternaNonValidaEntity.setIuv ( iuv );
			chiamataEsternaNonValidaEntity.setRemoteHost ( user.getRemoteAddress () );
			chiamataEsternaNonValidaEntity.setTimestampChiamata ( new Timestamp ( user.getTimestamp ().getTime () ) );

			chiamataEsternaNonValidaRepository.persist ( chiamataEsternaNonValidaEntity );
			Log.info ( "Inserita voce di tracciatura chiamataEsternaNonValidaEntity:" + chiamataEsternaNonValidaEntity );

		} else {
			chiamataEsternaNonValidaEntity.setIuv ( iuv );
			chiamataEsternaNonValidaRepository.persist ( chiamataEsternaNonValidaEntity );
			Log.info ( "Aggiornata voce di tracciatura chiamataEsternaNonValidaEntity:" + chiamataEsternaNonValidaEntity );
		}
	}

	public Long save ( EpayTChiamataEsternaNonValida epayTChiamataEsternaNonValida ) {
		chiamataEsternaNonValidaRepository.persist ( epayTChiamataEsternaNonValida );
		return epayTChiamataEsternaNonValida.getIdChiamata ();
	}
}

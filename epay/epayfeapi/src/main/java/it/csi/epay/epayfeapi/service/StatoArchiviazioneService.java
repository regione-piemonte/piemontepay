/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.dto.FlagAbilitaArchiviazioneDTO;
import it.csi.epay.epayfeapi.entity.EpayDChiamanteEsterno;
import it.csi.epay.epayfeapi.entity.EpayTEnti;
import it.csi.epay.epayfeapi.entity.EpayTStatoArchiviazione;
import it.csi.epay.epayfeapi.repository.StatoArchiviazioneRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;


@ApplicationScoped
@Transactional
public class StatoArchiviazioneService {

	@Inject
	StatoArchiviazioneRepository statoAchiviazioneRepository;

	public void setFlagAbilitaArchiviazione (
					String codiceFiscale,
					EpayTEnti enteEntity,
					boolean flagAbilitaArchiviazione,
					EpayDChiamanteEsterno chiamanteEsternoEntity ) {

		var methodName = "[setFlagAbilitaArchiviazione] ";
		Log.infof ( "%sBEGIN", methodName );
		Log.infof ( "%sparam codiceFiscale:%s", methodName, codiceFiscale );
		Log.infof ( "%sparam enteEntity:%s", methodName, enteEntity );
		Log.infof ( "%sparam flagAbilitaArchiviazione:%s", methodName, flagAbilitaArchiviazione );
		Log.infof ( "%sparam chiamanteEsternoEntity:%s", methodName, chiamanteEsternoEntity );

		var statoArchiviazioneEntity = statoAchiviazioneRepository.findStatoArchiviazione ( codiceFiscale, enteEntity );
		if ( statoArchiviazioneEntity == null ) {
			// crea l'entity
			Log.infof ( "%screa un nuovo stato di archiviazione", methodName );
			statoArchiviazioneEntity = new EpayTStatoArchiviazione ();
			statoArchiviazioneEntity.setCodiceFiscale ( codiceFiscale );
			statoArchiviazioneEntity.setEpayTEnti ( enteEntity );
			statoArchiviazioneEntity.setEpayDChiamanteEsterno ( chiamanteEsternoEntity );

		} else {
			Log.infof ( "%smodifica stato di archiviazione ottenuto", methodName );
		}

		// imposta il flag
		var now = new Date ();
		statoArchiviazioneEntity.setFlagAbilitaArchiviazione ( flagAbilitaArchiviazione );
		statoArchiviazioneEntity.setDataOraUltimaModifica ( now );

		// persiste
		Log.infof ( "%spersiste lo stato di archiviazione con il flag a %s per ente %s e codice fiscale cittadino %s in data e ora %s", methodName,
						flagAbilitaArchiviazione, enteEntity.getCodiceFiscale (), codiceFiscale, now );
		statoAchiviazioneRepository.persist ( statoArchiviazioneEntity );

		Log.infof ( "%sEND", methodName );
	}

	public FlagAbilitaArchiviazioneDTO getFlagAbilitaArchiviazione (
					String codiceFiscale,
					EpayTEnti enteEntity ) {

		var methodName = "[getFlagAbilitaArchiviazione] ";
		Log.infof ( "%sBEGIN", methodName );
		Log.infof ( "%sparam codiceFiscale:", methodName, codiceFiscale );
		Log.infof ( "%sparam enteEntity:", methodName, enteEntity );

		var statoArchiviazioneEntity = statoAchiviazioneRepository.findStatoArchiviazione ( codiceFiscale, enteEntity );

		FlagAbilitaArchiviazioneDTO result = null;
		if ( statoArchiviazioneEntity != null ) {
			result = new FlagAbilitaArchiviazioneDTO ();
			result.setAbilitato ( statoArchiviazioneEntity.getFlagAbilitaArchiviazione () );
			result.setDataOraUltimaModifica ( statoArchiviazioneEntity.getDataOraUltimaModifica () );
		}

		Log.infof ( "%sresult:%s", methodName, result );
		Log.infof ( "%sEND", methodName );
		return result;
	}
}

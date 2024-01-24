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

		String methodName = "[setFlagAbilitaArchiviazione] ";
		Log.info ( methodName + "BEGIN" );
		Log.info ( methodName + "param codiceFiscale:" + codiceFiscale );
		Log.info ( methodName + "param enteEntity:" + enteEntity );
		Log.info ( methodName + "param flagAbilitaArchiviazione:" + flagAbilitaArchiviazione );
		Log.info ( methodName + "param chiamanteEsternoEntity:" + chiamanteEsternoEntity );

		EpayTStatoArchiviazione statoArchiviazioneEntity = statoAchiviazioneRepository.findStatoArchiviazione ( codiceFiscale, enteEntity );
		if ( statoArchiviazioneEntity == null ) {
			// crea l'entity
			Log.info ( methodName + "crea un nuovo stato di archiviazione" );
			statoArchiviazioneEntity = new EpayTStatoArchiviazione ();
			statoArchiviazioneEntity.setCodiceFiscale ( codiceFiscale );
			statoArchiviazioneEntity.setEpayTEnti ( enteEntity );
			statoArchiviazioneEntity.setEpayDChiamanteEsterno ( chiamanteEsternoEntity );

		} else {
			Log.info ( methodName + "modifica stato di archiviazione ottenuto" );
		}

		// imposta il flag
		Date now = new Date ();
		statoArchiviazioneEntity.setFlagAbilitaArchiviazione ( flagAbilitaArchiviazione );
		statoArchiviazioneEntity.setDataOraUltimaModifica ( now );

		// persiste
		Log.info ( methodName
						+ "persiste lo stato di archiviazione con il flag a " + flagAbilitaArchiviazione
						+ " per ente " + enteEntity.getCodiceFiscale ()
						+ " e codice fiscale cittadino " + codiceFiscale
						+ " in data e ora " + now );
		statoAchiviazioneRepository.persist ( statoArchiviazioneEntity );

		Log.info ( methodName + "END" );
	}

	public FlagAbilitaArchiviazioneDTO getFlagAbilitaArchiviazione (
					String codiceFiscale,
					EpayTEnti enteEntity ) {

		String methodName = "[getFlagAbilitaArchiviazione] ";
		Log.info ( methodName + "BEGIN" );
		Log.info ( methodName + "param codiceFiscale:" + codiceFiscale );
		Log.info ( methodName + "param enteEntity:" + enteEntity );

		EpayTStatoArchiviazione statoArchiviazioneEntity = statoAchiviazioneRepository.findStatoArchiviazione ( codiceFiscale, enteEntity );

		FlagAbilitaArchiviazioneDTO result = null;
		if ( statoArchiviazioneEntity != null ) {
			result = new FlagAbilitaArchiviazioneDTO ();
			result.setAbilitato ( statoArchiviazioneEntity.getFlagAbilitaArchiviazione () );
			result.setDataOraUltimaModifica ( statoArchiviazioneEntity.getDataOraUltimaModifica () );
		}

		Log.info ( methodName + "result:" + result );
		Log.info ( methodName + "END" );
		return result;
	}
}

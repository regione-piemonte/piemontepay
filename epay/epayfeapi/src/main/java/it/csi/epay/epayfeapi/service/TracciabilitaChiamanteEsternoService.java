/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.entity.EpayDChiamanteEsterno;
import it.csi.epay.epayfeapi.entity.EpayTTracciabilitaChiamanteEsterno;
import it.csi.epay.epayfeapi.repository.TracciabilitaChiamanteEsternoRepository;
import it.csi.epay.epayfeapi.security.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;


@ApplicationScoped
@Transactional
public class TracciabilitaChiamanteEsternoService {

	@Inject
	TracciabilitaChiamanteEsternoRepository tracciabilitaChiamanteEsternoRepository;

	public EpayTTracciabilitaChiamanteEsterno trackExternalCall (
					EpayTTracciabilitaChiamanteEsterno tracciabilitaChiamanteEsternoEntity,
					String codiceFiscale,
					EpayDChiamanteEsterno chiamanteEsternoEntity,
					String iuv,
					User user,
					String idTransazione,
					long initialMoment,
					String serviceName) {

		if ( tracciabilitaChiamanteEsternoEntity == null ) {
			tracciabilitaChiamanteEsternoEntity = new EpayTTracciabilitaChiamanteEsterno ();
			tracciabilitaChiamanteEsternoEntity.setEpayDChiamanteEsterno ( chiamanteEsternoEntity );
			tracciabilitaChiamanteEsternoEntity.setDescrizioneChiamante ( chiamanteEsternoEntity.getDescrizioneChiamante () );
			tracciabilitaChiamanteEsternoEntity.setCodiceFiscale ( codiceFiscale );
			tracciabilitaChiamanteEsternoEntity.setRemoteHost ( user.getRemoteAddress () );
			tracciabilitaChiamanteEsternoEntity.setTimestampChiamata ( user.getTimestamp () );
			tracciabilitaChiamanteEsternoEntity.setIdTransazione ( idTransazione );
			tracciabilitaChiamanteEsternoEntity.setIuv ( iuv );
			tracciabilitaChiamanteEsternoEntity.setIdentificativoPagamento ( "test" );
			tracciabilitaChiamanteEsternoEntity.setDuration ( (int) ( System.currentTimeMillis () - initialMoment ) );
			tracciabilitaChiamanteEsternoEntity.setServiceName ( serviceName );

			tracciabilitaChiamanteEsternoRepository.persist ( tracciabilitaChiamanteEsternoEntity );
			Log.debugf ( "tracciaChiamataEsterna, inserita voce di tracciatura chiamata esterna : \n%s", tracciabilitaChiamanteEsternoEntity );

		} else {
			tracciabilitaChiamanteEsternoEntity.setIdTransazione ( idTransazione );
			tracciabilitaChiamanteEsternoEntity.setDuration ( (int) ( System.currentTimeMillis () - initialMoment ) );
			tracciabilitaChiamanteEsternoEntity.setServiceName ( serviceName );
			tracciabilitaChiamanteEsternoEntity.setIuv ( iuv );

			tracciabilitaChiamanteEsternoRepository.persist ( tracciabilitaChiamanteEsternoEntity );
			Log.debugf ( "tracciaChiamataEsterna, aggiornata voce di tracciatura chiamata esterna : \n%s", tracciabilitaChiamanteEsternoEntity );
		}
		return tracciabilitaChiamanteEsternoEntity;
	}
}

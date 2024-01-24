/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.dto.TransazioneMdpDTO;
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
					TransazioneMdpDTO transazione ) {

		if ( tracciabilitaChiamanteEsternoEntity == null ) {
			tracciabilitaChiamanteEsternoEntity = new EpayTTracciabilitaChiamanteEsterno ();
			tracciabilitaChiamanteEsternoEntity.setEpayDChiamanteEsterno ( chiamanteEsternoEntity );
			tracciabilitaChiamanteEsternoEntity.setDescrizioneChiamante ( chiamanteEsternoEntity.getDescrizioneChiamante () );
			tracciabilitaChiamanteEsternoEntity.setCodiceFiscale ( codiceFiscale );
			tracciabilitaChiamanteEsternoEntity.setRemoteHost ( user.getRemoteAddress () );
			tracciabilitaChiamanteEsternoEntity.setTimestampChiamata ( user.getTimestamp () );
			tracciabilitaChiamanteEsternoEntity.setIdTransazione ( transazione != null ? transazione.getIdTransazione () : null );
			tracciabilitaChiamanteEsternoEntity.setIuv ( iuv );
			tracciabilitaChiamanteEsternoEntity.setIdentificativoPagamento ( "test" );

			tracciabilitaChiamanteEsternoRepository.persist ( tracciabilitaChiamanteEsternoEntity );
			Log.debug ( "tracciaChiamataEsterna, inserita voce di tracciatura chiamata esterna : \n" + tracciabilitaChiamanteEsternoEntity );

		} else {
			tracciabilitaChiamanteEsternoEntity.setIdTransazione ( transazione != null ? transazione.getIdTransazione () : null );
			tracciabilitaChiamanteEsternoEntity.setIuv ( iuv );

			tracciabilitaChiamanteEsternoRepository.persist ( tracciabilitaChiamanteEsternoEntity );
			Log.debug ( "tracciaChiamataEsterna, aggiornata voce di tracciatura chiamata esterna : \n" + tracciabilitaChiamanteEsternoEntity );
		}
		return tracciabilitaChiamanteEsternoEntity;
	}
}

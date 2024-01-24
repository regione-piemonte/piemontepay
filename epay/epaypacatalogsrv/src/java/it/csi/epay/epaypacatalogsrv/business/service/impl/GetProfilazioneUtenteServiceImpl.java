/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.service.impl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.service.GetProfilazioneUtenteService;
import it.csi.epay.epaypacatalogsrv.business.service.ProfilazioneService;
import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteCorrenteOutput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteOutput;
import it.csi.epay.epaypacatalogsrv.exception.NotAuthorizedException;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalCodiceVersamentoVO;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalTematicaVO;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalVO;


/**
 *
 */
@Service
@Transactional
public class GetProfilazioneUtenteServiceImpl implements GetProfilazioneUtenteService {

	private static final Logger LOG = LogManager.getLogger(GetProfilazioneUtenteService.class);

	@Autowired
	private ProfilazioneService profilazioneService;

	@Autowired
	private Mapper dozerBeanMapper;

	@Override
	@Transactional
	public GetProfilazioneUtenteOutput getProfilazioneUtente(GetProfilazioneUtenteInput input) {

		try {
			CallerInputDto caller = input.getCaller();
			PrincipalVO principal = profilazioneService.getPrincipal(caller);

			if ( principal != null && !principal.hasScope ( Constants.SCOPES.PROFILAZIONE_UTENTE ) ) {
				throw new NotAuthorizedException();
			}

			GetProfilazioneUtenteOutput output = GetProfilazioneUtenteOutput.ok(GetProfilazioneUtenteOutput.class);

			GetProfilazioneUtenteOutput outputMapped = dozerBeanMapper.map(principal, GetProfilazioneUtenteOutput.class);

			output.setUtente(outputMapped.getUtente());
			output.setEnte(outputMapped.getEnte());
			output.setTematiche(outputMapped.getTematiche());

			return output;
		} catch (Exception e) {
			LOG.error("Errore ", e);
			return GetProfilazioneUtenteOutput.fail ( e, GetProfilazioneUtenteCorrenteOutput.class );
		}
	}

}

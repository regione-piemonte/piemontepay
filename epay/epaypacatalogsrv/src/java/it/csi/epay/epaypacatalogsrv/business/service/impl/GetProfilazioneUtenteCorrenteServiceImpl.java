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

import it.csi.epay.epaypacatalogsrv.business.service.AuditService;
import it.csi.epay.epaypacatalogsrv.business.service.GetProfilazioneUtenteCorrenteService;
import it.csi.epay.epaypacatalogsrv.business.service.ProfilazioneService;
import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteCorrenteInput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteCorrenteOutput;
import it.csi.epay.epaypacatalogsrv.exception.NotAuthorizedException;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalVO;


/**
 * 
 */
@Service
@Transactional
public class GetProfilazioneUtenteCorrenteServiceImpl implements GetProfilazioneUtenteCorrenteService {

	private static final Logger LOG = LogManager.getLogger(GetProfilazioneUtenteCorrenteService.class);

	@Autowired
	private ProfilazioneService profilazioneService;

	@Autowired
	private AuditService auditService;

	@Autowired
	private Mapper dozerBeanMapper;

	
	@Override
	public GetProfilazioneUtenteCorrenteOutput getProfilazioneUtenteCorrente (GetProfilazioneUtenteCorrenteInput input )
	{

		try {
		CallerInputDto caller = input.getCaller ();
		PrincipalVO principal = profilazioneService.getPrincipal ( caller );

		if ( principal != null ) {
			if ( !principal.hasScope ( Constants.SCOPES.PROFILAZIONE_UTENTE ) ) {
				throw new NotAuthorizedException ();
			}
		}

		GetProfilazioneUtenteCorrenteOutput output = GetProfilazioneUtenteCorrenteOutput.ok ( GetProfilazioneUtenteCorrenteOutput.class );

		GetProfilazioneUtenteCorrenteOutput outputMapped = dozerBeanMapper.map ( principal, GetProfilazioneUtenteCorrenteOutput.class );

		output.setUtente ( outputMapped.getUtente () );
		output.setEnte ( outputMapped.getEnte () );
		output.setTematiche ( outputMapped.getTematiche () );

		auditService.postAccesso ( input.getCaller (), principal );

		return output;
		}
		catch (Exception e) {
			LOG.error ( "Errore ", e );
			GetProfilazioneUtenteCorrenteOutput output = GetProfilazioneUtenteCorrenteOutput.fail ( e, GetProfilazioneUtenteCorrenteOutput.class );
			return output;
		}
		

	}



}

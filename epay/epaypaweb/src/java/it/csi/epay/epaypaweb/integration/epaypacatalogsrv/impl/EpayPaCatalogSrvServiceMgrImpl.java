/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.integration.epaypacatalogsrv.impl;

import java.security.InvalidParameterException;
import java.util.List;

import it.csi.epay.epaypaweb.dto.EnteDto;
import it.csi.epay.epaypaweb.dto.ProfilazioneEpayPaCatalogSrvDto;
import it.csi.epay.epaypaweb.exception.IntegrationException;
import it.csi.epay.epaypaweb.integration.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypaweb.integration.epaypacatalogsrv.dto.GetEntiAssociatiInput;
import it.csi.epay.epaypaweb.integration.epaypacatalogsrv.dto.GetEntiAssociatiOutput;
import it.csi.epay.epaypaweb.integration.epaypacatalogsrv.dto.GetProfilazioneUtenteInput;
import it.csi.epay.epaypaweb.integration.epaypacatalogsrv.dto.GetProfilazioneUtenteOutput;
import it.csi.epay.epaypaweb.integration.epaypacatalogsrv.dto.PrincipalInputDto;



public class EpayPaCatalogSrvServiceMgrImpl extends EpayPaCatalogSrvServiceMgrRoot {

	static private final String CLASSNAME = EpayPaCatalogSrvServiceMgrImpl.class.getSimpleName ();

	public EpayPaCatalogSrvServiceMgrImpl ( String endpointStr ) throws IntegrationException {
		super ( endpointStr );
	}

	@Override
	public List<EnteDto> getEntiAssociati ( String codiceFiscale ) throws IntegrationException {
		String methodName = "getEntiAssociati";
		
		

		List<EnteDto> output = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if ( isEmpty ( codiceFiscale ) ) {
				throw new InvalidParameterException ( "Il campo codice fiscale e' obbligatorio" );
			}

			GetEntiAssociatiInput request = new GetEntiAssociatiInput ();
			request.setCodiceFiscaleUtente ( codiceFiscale );

			GetEntiAssociatiOutput response = getSoapService ().getEntiAssociati ( request );

			validaEsito ( response );

			output = EpayPaCatalogSrvServiceMgrMapper.map ( response.getEntiAssociati () );

		} catch ( Exception e ) {
			throw new IntegrationException ( e.getMessage (), e );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return output;
	}

	@Override
	public ProfilazioneEpayPaCatalogSrvDto getProfilazioneUtente ( String codiceFiscale, String codiceEnte ) throws IntegrationException {
		String methodName = "getProfilazioneUtente";
		
		
		

		ProfilazioneEpayPaCatalogSrvDto output;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if ( isEmpty ( codiceFiscale ) ) {
				throw new InvalidParameterException ( "Il campo codice fiscale e' obbligatorio" );
			}

			if ( isEmpty ( codiceEnte ) ) {
				throw new InvalidParameterException ( "Il campo codice ente e' obbligatorio" );
			}

			GetProfilazioneUtenteInput request = new GetProfilazioneUtenteInput ();

			PrincipalInputDto principal = new PrincipalInputDto ();
			principal.setCodiceEnte ( codiceEnte );
			principal.setCodiceFiscale ( codiceFiscale );
			CallerInputDto caller = new CallerInputDto ();
			caller.setCodiceApplicativo ( CODICE_APPLICATIVO_CLIENT );
			caller.setPrincipal ( principal );
			request.setCaller ( caller );

			GetProfilazioneUtenteOutput response = getSoapService ().getProfilazioneUtente ( request );

			validaEsito ( response );

			output = EpayPaCatalogSrvServiceMgrMapper.map ( response );

		} catch ( Exception e ) {
			throw new IntegrationException ( e.getMessage (), e );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return output;
	}

}

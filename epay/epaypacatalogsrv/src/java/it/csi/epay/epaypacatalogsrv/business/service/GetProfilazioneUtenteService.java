/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.service;

import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteOutput;


/**
 * Service per i servizi epyapacatalog
 */
public interface GetProfilazioneUtenteService {

	public GetProfilazioneUtenteOutput getProfilazioneUtente ( GetProfilazioneUtenteInput input);



//	public void onException ( GetProfilazioneUtenteInput input,
//			OperationDispatchingContext<GetProfilazioneUtenteInput, GetProfilazioneUtenteOutput> context, Exception e ) ;


}

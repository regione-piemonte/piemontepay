/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteCorrenteInput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.security.Secured;
import it.csi.epay.epaypacatalogsrv.dto.test.TestResourcesInput;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


/**
 * Risorsa RestEasy per sevizi catalog
 */
@Path ( "/" )
@Consumes ( Constants.DEFAULT_PROTOCOL )
@Produces ( Constants.DEFAULT_PROTOCOL )
@ValidateRequest
public interface EpaypacatalogsrvApi {

    /**
     * TEST
     *
     * @return response JSON
     */
//	@Secured ( Scopes. )
	@Secured( notSecured = true)
    @POST
    @Path ( "/testResources" )
    public Response testResources (TestResourcesInput input );
	
	
	 /**
     * Profilazione utente corrente
     *
     * @return response JSON
     */
//	@Secured ( Scopes. )
	@Secured( notSecured = true)
    @POST
    @Path ( "/getProfilazioneUtenteCorrente" )
    public Response getProfilazioneUtenteCorrente (GetProfilazioneUtenteCorrenteInput input );
	
	 /**
     * Profilazione utente corrente
     *
     * @return response JSON
     */
//	@Secured ( Scopes. )
	@Secured( notSecured = true)
    @POST
    @Path ( "/getProfilazioneUtente" )
    public Response getProfilazioneUtente (GetProfilazioneUtenteInput input );
	

	
	
	
}

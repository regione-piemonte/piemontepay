/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import it.csi.epay.epaypacatalogsrv.dto.security.Scopes;
import it.csi.epay.epaypacatalogsrv.dto.security.Secured;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


/**
 * Risorsa RestEasy per il reperimento di informazioni sullo stato dell'applicativo
 */
@Path ( "/" )
@Consumes ( Constants.DEFAULT_PROTOCOL )
@Produces ( Constants.DEFAULT_PROTOCOL )
@ValidateRequest
public interface StatusApi {

    /**
     * Ottiene informazioni sullo stato dell'applicativo
     * potrebbe esporre dati sensibili
     *
     * @return response JSON
     */
    @Secured ( Scopes.VERIFICA_STATO )
    @GET
    @Path ( "/status" )
    public Response getStatus ();

    /**
     * Ottiene informazioni sul profilo corrente dell'applicazione
     *
     * @return response JSON
     */
    @Secured ( permitAll = true )
    @GET
    @Path ( "/profile" )
    public Response getProfileInfo ();
    
}

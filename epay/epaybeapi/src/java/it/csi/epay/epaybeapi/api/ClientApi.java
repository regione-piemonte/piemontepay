/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.api;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import it.csi.epay.epaybeapi.dto.request.ClientRegistrationRequest;
import it.csi.epay.epaybeapi.dto.request.ClientUpdateRequest;
import it.csi.epay.epaybeapi.dto.response.ClientRegistrationResponse;
import it.csi.epay.epaybeapi.dto.security.ClientInfoDTO;
import it.csi.epay.epaybeapi.dto.security.Scopes;
import it.csi.epay.epaybeapi.dto.security.Secured;
import it.csi.epay.epaybeapi.integration.dto.EpayDChiamanteEsternoDTO;
import it.csi.epay.epaybeapi.util.Constants;


/**
 * Risorsa RestEasy per il reperimento di informazioni sul client corrente
 */
@Path ( "/client" )
@Consumes ( Constants.DEFAULT_PROTOCOL )
@Produces ( Constants.DEFAULT_PROTOCOL )
@ValidateRequest
public interface ClientApi {

    /**
     * Ottiene informazioni sul client correntemente autenticato
     *
     * @return informazioni sul client corrente
     */
    @Secured ( authenticated = true )
    @GET
    @Path ( "/current" )
    public ClientInfoDTO getCurrentClient ();

    /**
     * Registra un nuovo client, generando automaticamente delle credenziali di accesso valide
     *
     * @param request le informazioni anagrafiche del client da generare
     * @return la passphrase (in chiaro) relativa al client appena creato
     */
    @Secured ( Scopes.PLATFORM_MANAGEMENT )
    @POST
    public ClientRegistrationResponse create ( @Valid ClientRegistrationRequest request );

    /**
     * Ottiene il client corrispondente al codice desiderato
     *
     * @param codice il codice del client
     * @return informazioni sul client
     */
    @Secured ( Scopes.PLATFORM_MANAGEMENT )
    @GET
    @Path ( "/{codice}" )
    public EpayDChiamanteEsternoDTO get ( @Valid @NotNull @Size ( min = 1, max = 50 ) @PathParam ( "codice" ) String codice );

    /**
     * Aggiorna le informazioni del client corrispondente al codice specificato
     *
     * @param codice il codice del client
     * @param request le informazioni da aggiornare
     * @return informazioni sul client aggiornato
     */
    @Secured ( Scopes.PLATFORM_MANAGEMENT )
    @PUT
    @Path ( "/{codice}" )
    public EpayDChiamanteEsternoDTO update ( @Valid @NotNull @Size ( min = 1, max = 50 ) @PathParam ( "codice" ) String codice,
        @Valid ClientUpdateRequest request );

    /**
     * Invalida il client corrispondente al codice specificato, valorizzandone la data fine validita'
     *
     * @param codice il codice del client
     */
    @Secured ( Scopes.PLATFORM_MANAGEMENT )
    @DELETE
    @Path ( "/{codice}" )
    public void delete ( @Valid @NotNull @Size ( min = 1, max = 50 ) @PathParam ( "codice" ) String codice );

}

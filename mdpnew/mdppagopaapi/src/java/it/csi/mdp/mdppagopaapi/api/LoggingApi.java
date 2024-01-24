/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import it.csi.mdp.mdppagopaapi.dto.common.LoggerDTO;
import it.csi.mdp.mdppagopaapi.dto.security.Scopes;
import it.csi.mdp.mdppagopaapi.dto.security.Secured;
import it.csi.mdp.mdppagopaapi.util.Constants;


/**
 * Risorsa RestEasy per la gestione dinamica dei livelli di logging
 */
@Path ( "/logger" )
@Consumes ( Constants.DEFAULT_PROTOCOL )
@Produces ( Constants.DEFAULT_PROTOCOL )
@ValidateRequest
@Secured ( Scopes.PLATFORM_MANAGEMENT )
public interface LoggingApi {

    /**
     * Ottiene i logger attivi
     *
     * @return lista di logger configurati
     */
    @GET
    public List<LoggerDTO> get ();

    /**
     * aggiorna il livello di logging per il logger selezionato
     *
     * @param payload il livello di logging da configurare col livello desiderato
     * @return
     */
    @PUT
    public LoggerDTO update ( LoggerDTO payload );

}

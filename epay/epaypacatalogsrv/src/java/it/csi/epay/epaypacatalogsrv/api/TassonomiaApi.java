/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.api;

import it.csi.epay.epaypacatalogsrv.dto.security.Scopes;
import it.csi.epay.epaypacatalogsrv.dto.security.Secured;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.ElencoTassonomiaPerCodiceTipoEnteCreditoreInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.RicercaTassonomiaInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.UpdateTassonomieInput;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import org.jboss.resteasy.spi.validation.ValidateRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


/**
 * Risorsa RestEasy per il reperimento di tassonomie
 */
@Path ( "/" )
@Consumes ( Constants.DEFAULT_PROTOCOL )
@Produces ( Constants.DEFAULT_PROTOCOL )
@ValidateRequest
public interface TassonomiaApi {

    /**
     * Ottiene una lista di tassonomie per ente
     *
     * @return response JSON
     */
    @Secured ( Scopes.RIFERIMENTI_CONTABILI )
    //	@Secured( notSecured = true)
    @POST
    @Path ( "/getElencoTassonomiaPerCodiceTipoEnteCreditore" ) Response getElencoTassonomiaPerCodiceTipoEnteCreditore (ElencoTassonomiaPerCodiceTipoEnteCreditoreInput input);


    /**
     * Ottiene una lista di tassonomie per filtro ed ente
     *
     * @return response JSON
     */
    @Secured ( Scopes.RIFERIMENTI_CONTABILI )
    //	@Secured( notSecured = true)
    @POST
    @Path ( "/ricercaTassonomia" ) Response ricercaTassonomia (RicercaTassonomiaInput input);

    /**
     * Ottiene la lista di tutte le tassonomie
     *
     * @return response JSON
     */
    @Secured ( Scopes.RIFERIMENTI_CONTABILI )
    @POST
    @Path ( "/ricercaAllTassonomie" ) Response ricercaAllTassonomie ();

    /**
     * Aggiorna le tassonomie presenti sul db a seconda del json in arrivo
     *
     * @return response JSON
     */
    @Secured ( Scopes.RIFERIMENTI_CONTABILI )
    @POST
    @Path ( "/updateTassonomie" ) Response updateTassonomie ( UpdateTassonomieInput input );

	/*
	 * end point utilizzato dal batch rdi41 AGGIORNAMENTO POSIZIONI DEBITORIE
	 */
	@Secured ( Scopes.RIFERIMENTI_CONTABILI )
	@POST
	@Path ( "/ricercaTassonomieBatchAggPosDeb" )
	Response ricercaTassonomieBatchAggPosDeb ();
}

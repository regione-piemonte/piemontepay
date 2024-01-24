/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.api;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import it.csi.epay.epaybeapi.dto.security.Scopes;
import it.csi.epay.epaybeapi.dto.security.Secured;
import it.csi.epay.epaybeapi.util.Constants;


/**
 * Risorsa RestEasy per il reperimento di informazioni sullo stato dell'applicativo
 */
@Path ( "/" )
@Consumes ( Constants.DEFAULT_PROTOCOL )
@Produces ( Constants.DEFAULT_PROTOCOL )
@ValidateRequest
public interface ComponentiImportoApi {

    /**
     * Ottiene informazioni sullo stato dell'applicativo
     * potrebbe esporre dati sensibili
     *
     * @return response JSON
     */
    @Secured ( Scopes.COMPONENTI_IMPORTO )
    @GET
    @Path ( "/componentiImporto" )
    public Response componentiImporto (  
    		 @Valid @NotNull @QueryParam("codiceFiscaleEnte") String codiceFiscaleEnte,
    		 @Valid @NotNull @QueryParam("codiceVersamento") String codiceVersamento,
    		 @Valid @NotNull @QueryParam("iuvDatiSingoloPagamento") String iuvDatiSingoloPagamento,
    		 @Valid @NotNull @QueryParam("importoDatiSingoloPagamento") String importoDatiSingoloPagamento,
    		 @Valid @NotNull @QueryParam("transactionId") String transactionId,
    		 @Valid @NotNull @QueryParam("datiSpecificiRiscossione") String datiSpecificiRiscossione,
    		 @Valid @NotNull @QueryParam("esitoPagamento") String esitoPagamento,
    		 @Valid @NotNull @QueryParam("anagraficaPagatore") String anagraficaPagatore,
    		 @Valid @NotNull @QueryParam("codiceFiscalePagatore") String codiceFiscalePagatore );

}

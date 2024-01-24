/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.facade.rest;

import java.math.BigDecimal;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;

import it.csi.epay.epaymodric.business.epaymodric.factory.model.FeignClient;
import it.csi.epay.epaymodric.dto.epaymodric.rest.ComponentiImportoOutput;

@FeignClient ( "${ epaybeapi_url }")
public interface ComponentiPagamentoDaSportello {
    
    @GET
    @Path ( "componentiImporto" )
    ComponentiImportoOutput getComponentiPagamento (@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader, @QueryParam("codiceFiscaleEnte") String codiceFiscaleEnte, 
    		@QueryParam("codiceVersamento") String codiceVersamento, @QueryParam("iuvDatiSingoloPagamento") String iuvDatiSingoloPagamento, 
    		@QueryParam("importoDatiSingoloPagamento") BigDecimal importoDatiSingoloPagamento, @QueryParam("transactionId") String transactionId, 
    		@QueryParam("datiSpecificiRiscossione") String datiSpecificiRiscossione, @QueryParam("esitoPagamento") String esitoPagamento,
    		@QueryParam("anagraficaPagatore") String anagraficaPagatore, @QueryParam("codiceFiscalePagatore") String codiceFiscalePagatore);

}

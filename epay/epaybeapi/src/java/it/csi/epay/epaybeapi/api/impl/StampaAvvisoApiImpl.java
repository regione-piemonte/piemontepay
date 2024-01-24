/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.api.impl;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.epay.epaybeapi.api.StampaAvvisoApi;
import it.csi.epay.epaybeapi.api.util.SpringSupportedResource;
import it.csi.epay.epaybeapi.business.StampaAvvisoPagamentoService;
import it.csi.epay.epaybeapi.dto.request.RichiestaAvvisoPagamentoRequest;
import it.csi.epay.epaybeapi.dto.request.StampaAvvisoPagamentoRequest;
import it.csi.epay.epaybeapi.dto.response.StampaAvvisoPagamentoResponse;


@SpringSupportedResource
public class StampaAvvisoApiImpl implements StampaAvvisoApi {

    private static Logger logger = LoggerFactory.getLogger ( StampaAvvisoApi.class );

    @Autowired
    private StampaAvvisoPagamentoService service;

    @Override
    public Response stampaAvvisoPagamento ( RichiestaAvvisoPagamentoRequest request ) {

        logger.info ( "Try to call the service" );
        StampaAvvisoPagamentoResponse result = service.getAvvisoPagamento ( request );
        logger.info ( "Returning response" );

        if ( result != null ) {
            return Response.ok ( result ).build ();
        } else {
            return Response.status ( Status.INTERNAL_SERVER_ERROR ).entity ( result ).build ();
        }
    }

    @Override
    public Response stampaAvvisoPagamentoSportello ( StampaAvvisoPagamentoRequest request ) {
        logger.info ( "Try to call the service" );
        StampaAvvisoPagamentoResponse result = service.getAvvisoPagamentoSportello ( request );
        logger.info ( "Returning response" );

        if ( result != null ) {
            return Response.ok ( result ).build ();
        } else {
            return Response.status ( Status.INTERNAL_SERVER_ERROR ).entity ( result ).build ();
        }
    }

}

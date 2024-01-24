/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.api;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import it.csi.epay.epaybeapi.dto.request.RichiestaAvvisoPagamentoRequest;
import it.csi.epay.epaybeapi.dto.request.StampaAvvisoPagamentoRequest;
import it.csi.epay.epaybeapi.dto.security.Scopes;
import it.csi.epay.epaybeapi.dto.security.Secured;
import it.csi.epay.epaybeapi.util.Constants;


/**
 * Risorsa RestEasy per la stampa dell'avviso di pagamento
 */
@Path ( "/" )
@Consumes ( Constants.DEFAULT_PROTOCOL )
@Produces ( Constants.DEFAULT_PROTOCOL )
@ValidateRequest
public interface StampaAvvisoApi {

    @Secured ( Scopes.STAMPA_AVVISO_PAGAMENTO )
    @POST
    @Path ( "/stampaAvvisoPagamento" )
    public Response stampaAvvisoPagamento ( @Valid RichiestaAvvisoPagamentoRequest request );

    @Secured ( Scopes.STAMPA_AVVISO_PAGAMENTO )
    @POST
    @Path ( "/stampaAvvisoPagamentoSportello" )
    public Response stampaAvvisoPagamentoSportello ( @Valid StampaAvvisoPagamentoRequest request );
}

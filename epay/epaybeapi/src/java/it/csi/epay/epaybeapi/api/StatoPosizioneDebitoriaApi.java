/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.validation.ValidateRequest;

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
public interface StatoPosizioneDebitoriaApi {
    /**
     * Ritorna lo stato di una posizione debitoria
     *
     * @return response JSON
     */
    @Secured ( Scopes.RICHIESTA_STATO_POS_DEB ) // deve avere valore uguale a quelli presenti nella epay_r_chiamanta_autorizzazione_chiamante
    @GET
    @Path ( "/organizations/{organization}/paymenttypes/{paymentType}/debtpositions/{iuv}/status" )
    Response getStatoPosizioneDebitoria (
                    @PathParam ( "organization" ) String organization,
                    @PathParam ( "paymentType" ) String paymentType,
                    @PathParam ( "iuv" ) String iuv );
}

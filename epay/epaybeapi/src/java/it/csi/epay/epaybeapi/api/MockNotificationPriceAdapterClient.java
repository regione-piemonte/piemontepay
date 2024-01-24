/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import it.csi.epay.epaybeapi.dto.response.NotificationPriceOutput;
import it.csi.epay.epaybeapi.util.Constants;


/**
 * Risorsa RestEasy per il reperimento di informazioni sul client corrente
 */
@Path ( "/" )
@Produces ( Constants.DEFAULT_PROTOCOL )
public interface MockNotificationPriceAdapterClient {

    /**
     * @return Restituisce un valore random tra 0 e 100
     */
    @GET
    @Path ( "/mockNotificationPrice" )
    public NotificationPriceOutput getMockNotificationPrice ();
    

}

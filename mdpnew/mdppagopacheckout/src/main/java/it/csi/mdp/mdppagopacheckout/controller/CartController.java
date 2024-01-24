/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.controller;

import io.quarkus.logging.Log;
import it.csi.mdp.mdppagopacheckout.service.external.PaymentService;
import org.openapitools.api.ApiApi;
import org.openapitools.model.Cart;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.core.Response;


public class CartController implements ApiApi {

	@Inject
	PaymentService paymentService;

	@Override
	@RolesAllowed ( "writer" )
	public Response managePaymentRequest ( Cart cart ) {
		Log.info ( "Called method managePaymentRequest" );
		Log.info ( "Payload:" );
		Log.info ( cart );
		return paymentService.manageCart ( cart );
	}
}

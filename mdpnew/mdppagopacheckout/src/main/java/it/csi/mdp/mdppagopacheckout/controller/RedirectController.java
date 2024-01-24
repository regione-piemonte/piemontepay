/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.controller;

import io.quarkus.logging.Log;
import it.csi.mdp.mdppagopacheckout.service.external.RedirectService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.validation.constraints.Pattern;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import static it.csi.mdp.mdppagopacheckout.util.Constants.M_ID;
import static it.csi.mdp.mdppagopacheckout.util.Constants.RETURN_CANCEL_URL_FIELDNAME;
import static it.csi.mdp.mdppagopacheckout.util.Constants.RETURN_ERROR_URL_FIELDNAME;
import static it.csi.mdp.mdppagopacheckout.util.Constants.RETURN_OK_URL_FIELDNAME;
import static it.csi.mdp.mdppagopacheckout.util.Constants.TRANSACTION_ID_ERROR;
import static it.csi.mdp.mdppagopacheckout.util.Constants.TRANSACTION_ID_REGEX;
import static it.csi.mdp.mdppagopacheckout.util.Constants.T_ID;


@Path ( "/api/landingpages/v1/" )
@PermitAll
public class RedirectController {

	@Inject
	RedirectService redirectService;

	@GET
	@Path ( "urlpaymentok" ) // equivalente alle vecchie url di mdp, sono quelle che andranno anche nelle config
	@Produces ( { "text/html" } )
	public Response manageOkUrl (
					@QueryParam ( T_ID ) @Pattern ( regexp = TRANSACTION_ID_REGEX, message = TRANSACTION_ID_ERROR ) String transactionId,
					@QueryParam ( M_ID ) String mac )
					throws URISyntaxException, NoSuchAlgorithmException {
		Log.info ( "Called method manageOkUrl" );
		Log.infof ( "%s: %s", T_ID, transactionId );

		return redirectService.redirectByTransactionId ( transactionId, mac, RETURN_OK_URL_FIELDNAME );
	}

	@GET
	@Path ( "urlpaymenterror" )
	@Produces ( { "text/html" } )
	public Response manageErrorUrl (
					@QueryParam ( T_ID ) @Pattern ( regexp = TRANSACTION_ID_REGEX, message = TRANSACTION_ID_ERROR ) String transactionId,
					@QueryParam ( M_ID ) String mac )
					throws URISyntaxException, NoSuchAlgorithmException {
		Log.info ( "Called method manageErrorUrl" );
		Log.infof ( "%s: %s", T_ID, transactionId );

		return redirectService.redirectByTransactionId ( transactionId, mac, RETURN_ERROR_URL_FIELDNAME );
	}

	@GET
	@Path ( "urlpaymentcancel" )
	@Produces ( { "text/html" } )
	public Response manageCancelUrl (
					@QueryParam ( T_ID ) @Pattern ( regexp = TRANSACTION_ID_REGEX, message = TRANSACTION_ID_ERROR ) String transactionId,
					@QueryParam ( M_ID ) String mac )
					throws URISyntaxException, NoSuchAlgorithmException {
		Log.info ( "Called method manageOkUrl" );
		Log.infof ( "%s: %s", T_ID, transactionId );

		return redirectService.redirectByTransactionId ( transactionId, mac, RETURN_CANCEL_URL_FIELDNAME );
	}

}

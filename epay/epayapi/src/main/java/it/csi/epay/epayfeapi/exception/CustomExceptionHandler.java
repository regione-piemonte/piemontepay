/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.exception;

import org.jfree.util.Log;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class CustomExceptionHandler implements ExceptionMapper<CustomException> {

	@Override public Response toResponse ( CustomException exception ) {
		Log.info ( "exception.message:" + exception.getMessage (), exception );
		exception.printStackTrace (); // non va in test utente?
		return Response.status ( Response.Status.INTERNAL_SERVER_ERROR ).
						entity ( new ErrorMessage ( Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase () ) ).build ();
	}
}

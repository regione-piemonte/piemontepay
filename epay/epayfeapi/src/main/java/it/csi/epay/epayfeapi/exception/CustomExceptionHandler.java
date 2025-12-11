/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.exception;

import io.quarkus.logging.Log;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class CustomExceptionHandler implements ExceptionMapper<CustomException> {

	@Override
	public Response toResponse ( CustomException exception ) {
		Log.info ( String.format ( "exception.message:%s", exception.getMessage () ), exception );
		Log.errorf ( "stack-trace of %s", ExceptionUtils.getStackTrace ( exception ) );
		return Response.status ( Response.Status.INTERNAL_SERVER_ERROR ).
						entity ( new ErrorMessage ( Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase () ) ).build ();
	}
}

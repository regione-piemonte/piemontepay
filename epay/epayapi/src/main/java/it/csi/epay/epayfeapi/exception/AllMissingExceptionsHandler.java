/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openapitools.model.Error;

import io.quarkus.arc.Priority;
import io.quarkus.logging.Log;


@Provider
@Priority ( 9999 )
public class AllMissingExceptionsHandler implements ExceptionMapper<Throwable> {

	/*
	 * Meglio di javax.ws.rs.container.ContainerResponseFilter; intercetta qualunque errore non gestito e formatta la risposta come da yaml
	 */
	@Override public Response toResponse ( Throwable exception ) {
		Log.info ( "Intercepted unhandled error!" );
		Log.info ( "exception.message:" + exception.getMessage (), exception );

		Log.error ( "stack-trace of " + ExceptionUtils.getStackTrace ( exception ) );
		
		Error error = new Error ();
		error.setCode ( Error.CodeEnum.INTERNAL_ERROR );
		error.setStatus ( String.valueOf ( Response.Status.INTERNAL_SERVER_ERROR.getStatusCode () ) );
		error.setDetail ( "An internal error has occurred" );
		Log.info ( "Replaced default entity response!" );
		return Response.status ( Response.Status.INTERNAL_SERVER_ERROR ).entity ( error ).build ();
	}

}

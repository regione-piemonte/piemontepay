/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.util;

import io.quarkus.logging.Log;
import org.openapitools.model.Error;

import javax.ws.rs.core.Response;

import static it.csi.mdp.mdppagopacheckout.util.Constants.RETURN_URLS_NOT_FOUNDS;


public class ResponseUtil {

	public static Response generateValidationErrorResponse ( String detail ) {
		Log.info ( "Invoked generateValidationErrorResponse" );
		var error = new Error ();
		error.setCode ( Error.CodeEnum.VALIDATION_ERROR );
		error.setStatus ( String.valueOf ( Response.Status.BAD_REQUEST.getStatusCode () ) );
		error.setDetail ( detail );
		Log.error ( detail );
		return Response.status ( Response.Status.BAD_REQUEST )
						.entity ( error )
						.build ();
	}

	public static Response generateInternalErrorResponse ( String message ) {
		Log.info ( "Invoked generateInternalErrorResponse" );
		var error = new Error ();
		error.setCode ( Error.CodeEnum.INTERNAL_ERROR );
		error.setStatus ( String.valueOf ( Response.Status.INTERNAL_SERVER_ERROR.getStatusCode () ) );
		error.setDetail ( message );
		Log.error ( message );
		return Response.status ( Response.Status.INTERNAL_SERVER_ERROR )
						.entity ( error )
						.build ();
	}

	public static Response generateNotFoundUrl ( String applicationId, String fieldName ) {
		Log.infof ( "Not found %s for applicationId = %s", fieldName, applicationId );
		return generateValidationErrorResponse ( RETURN_URLS_NOT_FOUNDS );
	}

	public static Response generateNotFoundUrl ( String fieldName ) {
		Log.infof ( "Not found %s", fieldName );
		return generateValidationErrorResponse ( RETURN_URLS_NOT_FOUNDS );
	}

	public static Response generateMacError ( String macCalcolato, String mac ) {
		Log.infof ( "Mac calculated %s different from mac %s", macCalcolato, mac );
		return generateValidationErrorResponse ( RETURN_URLS_NOT_FOUNDS );
	}
}

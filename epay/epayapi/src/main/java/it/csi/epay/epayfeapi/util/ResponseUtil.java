/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.util;

import io.quarkus.logging.Log;
import org.apache.commons.lang3.StringUtils;
import org.openapitools.model.Error;

import javax.ws.rs.core.Response;
import java.util.List;

import static it.csi.epay.epayfeapi.util.Constants.COMMA_AND_SPACE;


public class ResponseUtil {

	public static Response generateUnauthorizedResponse ( String serviceName ) {
		Log.info ( "Invoked generateUnauthorizedResponse" );
		Error error = new Error ();
		error.setCode ( Error.CodeEnum.AUTHORIZATION_ERROR );
		error.setStatus ( String.valueOf ( Response.Status.UNAUTHORIZED.getStatusCode () ) );
		String details = "[" + serviceName + "] Gestionale non autorizzato a operare sull'Ente indicato";
		error.setDetail ( details );
		Log.error ( details );
		return Response.status ( Response.Status.UNAUTHORIZED )
						.entity ( error )
						.build ();
	}

	public static Response generateForbiddenResponse ( String serviceName, String chiamante ) {
		Log.info ( "Invoked generateForbiddenResponse" );
		Error error = new Error ();
		error.setCode ( Error.CodeEnum.AUTHORIZATION_ERROR );
		error.setStatus ( String.valueOf ( Response.Status.FORBIDDEN.getStatusCode () ) );
		String details = "[" + serviceName + "] Chiamante non riconosciuto: " + chiamante;
		error.setDetail ( details );
		Log.error ( details );
		return Response.status ( Response.Status.UNAUTHORIZED )
						.entity ( error )
						.build ();
	}

	public static Response generateValidationErrorResponse ( String serviceName, List<String> notValids ) {
		Log.info ( "Invoked generateValidationErrorResponse" );
		Error error = new Error ();
		error.setCode ( Error.CodeEnum.VALIDATION_ERROR );
		error.setStatus ( String.valueOf ( Response.Status.BAD_REQUEST.getStatusCode () ) );
		String details = "[" + serviceName + "] Parametri inseriti non corretti o mancanti: " + String.join ( COMMA_AND_SPACE, notValids );
		error.setDetail ( details );
		Log.error ( details );
		return Response.status ( Response.Status.BAD_REQUEST )
						.entity ( error )
						.build ();
	}

	public static Response generateNotFoundErrorResponse ( String serviceName, String message, Object... params ) {
		Log.info ( "Invoked generateNotFoundErrorResponse" );
		Error error = new Error ();
		error.setCode ( Error.CodeEnum.VALIDATION_ERROR );
		error.setStatus ( String.valueOf ( Response.Status.NOT_FOUND.getStatusCode () ) );
		String details = String.format ( "[" + serviceName + "] " + message, params );
		error.setDetail ( details );
		Log.error ( details );
		return Response.status ( Response.Status.NOT_FOUND )
						.entity ( error )
						.build ();
	}

	public static Response generateBusinessErrorResponse ( String serviceName, String message, String... params ) {
		Log.info ( "Invoked generateBusinessErrorResponse" );
		Error error = new Error ();
		error.setCode ( Error.CodeEnum.BUSINESS_ERROR );
		error.setStatus ( String.valueOf ( Response.Status.BAD_REQUEST.getStatusCode () ) );
		String details = String.format ( "[" + serviceName + "] " + message, (Object[]) params );
		error.setDetail ( details );
		Log.error ( details );
		return Response.status ( Response.Status.BAD_REQUEST )
						.entity ( error )
						.build ();
	}

	public static Response generateInternalErrorResponse ( String serviceName, String message, String... params ) {
		Log.info ( "Invoked generateInternalErrorResponse" );
		Error error = new Error ();
		error.setCode ( Error.CodeEnum.INTERNAL_ERROR );
		error.setStatus ( String.valueOf ( Response.Status.INTERNAL_SERVER_ERROR.getStatusCode () ) );
		String detail = "[" + serviceName + "] Si e' verificato un errore interno";
		if ( !StringUtils.isBlank ( message ) ) {
			String trimmedMessage = message.trim ();
			if ( !trimmedMessage.isEmpty () ) {
				detail += ": " + trimmedMessage;
			}
		}
		error.setDetail ( detail );
		Log.error ( String.format ( detail, (Object[]) params ) );
		return Response.status ( Response.Status.INTERNAL_SERVER_ERROR )
						.entity ( error )
						.build ();
	}
}

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
		var error = new Error ();
		error.setCode ( Error.CodeEnum.AUTHORIZATION_ERROR );
		error.setStatus ( String.valueOf ( Response.Status.UNAUTHORIZED.getStatusCode () ) );
		var detail = String.format ( "[%s] Gestionale non autorizzato a operare sull'Ente indicato", serviceName );
		error.setDetail ( detail );
		Log.error ( detail );
		return Response.status ( Response.Status.UNAUTHORIZED )
						.entity ( error )
						.build ();
	}
	
	public static Response generateUnauthorizedEnteResponse ( String serviceName ) {
		Log.info ( "Invoked generateUnauthorizedResponse" );
		var error = new Error ();
		error.setCode ( Error.CodeEnum.AUTHORIZATION_ERROR );
		error.setStatus ( String.valueOf ( Response.Status.UNAUTHORIZED.getStatusCode () ) );
		var detail = String.format ( "[%s] Per l'Ente indicato non risulta l'adesione a Ciita' Facile ", serviceName );
		error.setDetail ( detail );
		Log.error ( detail );
		return Response.status ( Response.Status.UNAUTHORIZED )
						.entity ( error )
						.build ();
	}

	public static Response generateForbiddenResponse ( String serviceName, String chiamante ) {
		Log.info ( "Invoked generateForbiddenResponse" );
		var error = new Error ();
		error.setCode ( Error.CodeEnum.AUTHORIZATION_ERROR );
		error.setStatus ( String.valueOf ( Response.Status.FORBIDDEN.getStatusCode () ) );
		var detail = String.format ( "[%s] Chiamante non riconosciuto: %s", serviceName, chiamante );
		error.setDetail ( detail );
		Log.error ( detail );
		return Response.status ( Response.Status.UNAUTHORIZED )
						.entity ( error )
						.build ();
	}

	public static Response generateValidationErrorResponse ( String serviceName, List<String> notValids ) {
		Log.info ( "Invoked generateValidationErrorResponse" );
		var error = new Error ();
		error.setCode ( Error.CodeEnum.VALIDATION_ERROR );
		error.setStatus ( String.valueOf ( Response.Status.BAD_REQUEST.getStatusCode () ) );
		var detail = String.format ( "[%s] Parametri inseriti non corretti o mancanti: %s", serviceName, String.join ( COMMA_AND_SPACE, notValids ) );
		error.setDetail ( detail );
		Log.error ( detail );
		return Response.status ( Response.Status.BAD_REQUEST )
						.entity ( error )
						.build ();
	}

	public static Response generateNotFoundErrorResponse ( String serviceName, String message, Object... params ) {
		Log.info ( "Invoked generateNotFoundErrorResponse" );
		var error = new Error ();
		error.setCode ( Error.CodeEnum.VALIDATION_ERROR );
		error.setStatus ( String.valueOf ( Response.Status.NOT_FOUND.getStatusCode () ) );
		var detail = String.format ( "[" + serviceName + "] " + message, params );
		error.setDetail ( detail );
		Log.error ( detail );
		return Response.status ( Response.Status.NOT_FOUND )
						.entity ( error )
						.build ();
	}

	public static Response generateBusinessErrorResponse ( String serviceName, String message, String... params ) {
		Log.info ( "Invoked generateBusinessErrorResponse" );
		var error = new Error ();
		error.setCode ( Error.CodeEnum.BUSINESS_ERROR );
		error.setStatus ( String.valueOf ( Response.Status.BAD_REQUEST.getStatusCode () ) );
		var detail = String.format ( "[" + serviceName + "] " + message, (Object[]) params );
		error.setDetail ( detail );
		Log.error ( detail );
		return Response.status ( Response.Status.BAD_REQUEST )
						.entity ( error )
						.build ();
	}

	public static Response generateInternalErrorResponse ( String serviceName, String message, String... params ) {
		Log.info ( "Invoked generateInternalErrorResponse" );
		var error = new Error ();
		error.setCode ( Error.CodeEnum.INTERNAL_ERROR );
		error.setStatus ( String.valueOf ( Response.Status.INTERNAL_SERVER_ERROR.getStatusCode () ) );
		var detail = "[" + serviceName + "] Si e' verificato un errore interno";
		if ( !StringUtils.isBlank ( message ) ) {
			var trimmedMessage = message.trim ();
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

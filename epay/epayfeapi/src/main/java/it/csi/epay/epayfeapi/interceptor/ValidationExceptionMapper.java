/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.interceptor;

import org.openapitools.model.Error;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse ( ConstraintViolationException exception ) {
		StringBuilder messages = new StringBuilder ();

		for ( ConstraintViolation<?> violation : exception.getConstraintViolations () ) {
			String interpolatedMessage = violation.getMessage ();
			String invalidValue = (String) violation.getInvalidValue ();
			messages.append ( "Errore per il valore: " ).append ( invalidValue ).append ( ": " ).append ( interpolatedMessage ).append ( "\n" );
		}

		String allMessages = messages.toString ().trim ();

		Error errorEntity = new Error ();
		errorEntity.setCode ( Error.CodeEnum.VALIDATION_ERROR );
		errorEntity.setStatus ( String.valueOf ( Response.Status.BAD_REQUEST.getStatusCode () ) );
		errorEntity.setDetail ( allMessages );

		return Response.status ( Response.Status.BAD_REQUEST ).entity ( errorEntity ).build ();
	}
}

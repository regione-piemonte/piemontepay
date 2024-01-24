/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.provider;

import io.quarkus.arc.Priority;
import org.openapitools.model.Error;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.math.BigDecimal;


@Provider
@Priority ( 1 )
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse ( ConstraintViolationException exception ) {
		StringBuilder messages = new StringBuilder ();

		for ( ConstraintViolation<?> violation : exception.getConstraintViolations () ) {
			var interpolatedMessage = violation.getMessage ();
			String invalidValue;
			if ( violation.getInvalidValue () instanceof BigDecimal ) {
				invalidValue = violation.getInvalidValue ().toString ();
			} else {
				invalidValue = (String) violation.getInvalidValue ();
			}
			messages.append ( "Errore per il valore: " ).append ( invalidValue ).append ( ": " ).append ( interpolatedMessage ).append ( "\n" );
		}

		var allMessages = messages.toString ().trim ();

		var errorEntity = new Error ();
		errorEntity.setCode ( Error.CodeEnum.VALIDATION_ERROR );
		errorEntity.setStatus ( String.valueOf ( Response.Status.BAD_REQUEST.getStatusCode () ) );
		errorEntity.setDetail ( allMessages );

		return Response.status ( Response.Status.BAD_REQUEST ).entity ( errorEntity ).build ();
	}
}

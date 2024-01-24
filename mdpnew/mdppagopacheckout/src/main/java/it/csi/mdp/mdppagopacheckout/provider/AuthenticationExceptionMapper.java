/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.provider;

import io.quarkus.arc.Priority;
import io.quarkus.logging.Log;
import io.quarkus.security.AuthenticationFailedException;
import org.openapitools.model.Error;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
@Priority ( 1 )
public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationFailedException> {

	@Override
	public Response toResponse ( AuthenticationFailedException exception ) {
		Log.error ( exception.getMessage (), exception );
		var errorEntity = new Error ();
		errorEntity.setCode ( Error.CodeEnum.AUTHORIZATION_ERROR );
		errorEntity.setStatus ( String.valueOf ( Response.Status.UNAUTHORIZED.getStatusCode () ) );
		errorEntity.setDetail ( Response.Status.UNAUTHORIZED.getReasonPhrase () );

		return Response.status ( Response.Status.UNAUTHORIZED ).entity ( errorEntity ).build ();
	}
}

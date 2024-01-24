/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.provider;

import io.quarkus.arc.Priority;
import io.quarkus.logging.Log;
import org.openapitools.model.Error;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
@Priority ( 1 )
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

	@Override
	public Response toResponse ( NotFoundException exception ) {
		Log.error ( exception.getMessage (), exception );
		var errorEntity = new Error ();
		errorEntity.setCode ( Error.CodeEnum.INTERNAL_ERROR ); // manca la enum più corretta da yaml
		errorEntity.setStatus ( String.valueOf ( Response.Status.NOT_FOUND.getStatusCode () ) );
		errorEntity.setDetail ( Response.Status.NOT_FOUND.getReasonPhrase () );

		return Response.status ( Response.Status.NOT_FOUND ).entity ( errorEntity ).build ();
	}
}

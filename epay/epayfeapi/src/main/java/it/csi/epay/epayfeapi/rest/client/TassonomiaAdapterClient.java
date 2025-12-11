/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.rest.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.exception.TassonomiaServiceException;
import it.csi.epay.epayfeapi.model.epaypacatalogsrv.DatiSpecificiRiscossioneInput;
import it.csi.epay.epayfeapi.model.epaypacatalogsrv.DatiSpecificiRiscossioneOutput;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;


@ApplicationScoped
public class TassonomiaAdapterClient extends ApiClient {

	@ConfigProperty ( name = "epaypacatalogService.authusr" )
	String usr;

	@ConfigProperty ( name = "epaypacatalogService.authpwd" )
	String pwd;

	public DatiSpecificiRiscossioneOutput getDatiSpecificiRiscossione ( DatiSpecificiRiscossioneInput request, String url )
					throws IOException, TassonomiaServiceException {
		var methodName = "[getDatiSpecificiRiscossione] ";
		Log.infof ( "%sBEGIN", methodName );
		Log.infof ( "%sparam request:%s", methodName, request );
		Log.infof ( "%sparam url:%s", methodName, url );

		DatiSpecificiRiscossioneOutput response;
		// connect
		var secret = String.format ( "%s:%s", usr, pwd );
		var urlConnection = super.getResponse ( url, request, secret, "POST" );

		var statusCode = urlConnection.getResponseCode ();
		var ce = CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;
		Log.infof ( "%sstatusCode:%d", methodName, statusCode );

		var mapper = new ObjectMapper ();
		switch ( statusCode ) {
		case 200:
		case 201:
			response = mapper.readValue ( urlConnection.getInputStream (), DatiSpecificiRiscossioneOutput.class );
			break;
		default:
			response = mapper.readValue ( urlConnection.getErrorStream (), DatiSpecificiRiscossioneOutput.class );
			//
			String message;
			switch ( statusCode ) {
			case 400:
				message = response.getCodiceEsito () + " - " + response.getDescrizioneEsito ();
				break;
			case 401:
				message = statusCode + " Unauthorized -- Check request: " + url;
				break;
			case 403:
				message = statusCode + " Forbidden -- Check request: " + url;
				break;
			case 404:
				message = statusCode + " Not Found -- Check request: " + url;
				break;
			case 500:
				message = statusCode + " Internal Error -- Check request: " + url;
				break;
			default:
				message = statusCode + " Not Handled error -- Check request: " + url;
				break;
			}
			Log.errorf ( "%s%s", methodName, message );
			throw new TassonomiaServiceException ( ce.getCodice (), message );
		}

		Log.infof ( "%sBEGIN", methodName );
		return response;
	}
}

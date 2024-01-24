/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.rest.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.enterprise.context.ApplicationScoped;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.exception.TassonomiaServiceException;
import it.csi.epay.epayfeapi.model.DatiSpecificiRiscossioneInput;
import it.csi.epay.epayfeapi.model.DatiSpecificiRiscossioneOutput;


@ApplicationScoped
public class TassonomiaAdapterClient {

	@ConfigProperty ( name = "epaypacatalogService.authusr" )
	String usr;

	@ConfigProperty ( name = "epaypacatalogService.authupwd" )
	String pwd;

	private final static int TIMEOUT = 60000;

	public DatiSpecificiRiscossioneOutput getDatiSpecificiRiscossione ( DatiSpecificiRiscossioneInput request, String url )
					throws IOException, TassonomiaServiceException {
		Log.info ( "getDatiSpecificiRiscossione::start" );

		DatiSpecificiRiscossioneOutput response;
		ObjectMapper mapper = new ObjectMapper ();
		HttpURLConnection urlConnection;

		String secret = usr + ":" + pwd;
		String basicAuth = new String ( Base64.encodeBase64 ( secret.getBytes () ) );

		//Connect
		urlConnection = (HttpURLConnection) ( ( new URL ( url ).openConnection () ) );
		urlConnection.setDoOutput ( true );
		urlConnection.setRequestProperty ( "Content-Type", "application/json" );
		urlConnection.setRequestProperty ( "Authorization", "Basic " + basicAuth );
		urlConnection.setRequestProperty ( "Accept", "application/json" );
		urlConnection.setRequestMethod ( "POST" );
		urlConnection.setConnectTimeout ( TIMEOUT );
		urlConnection.connect ();

		String requestString = buildJsonInvio ( request );
		Log.info("JSON pre-call:"+requestString);

		OutputStream os = urlConnection.getOutputStream ();
		os.write ( requestString.getBytes () );
		os.flush ();

		int statusCode = urlConnection.getResponseCode ();
		Log.info("JSON post-call: code"+urlConnection.getResponseCode()+" - "+urlConnection.getResponseMessage());
		CodiciEsito ce = CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;

		if ( statusCode != 200 ) {
			response = mapper.readValue ( urlConnection.getErrorStream (), DatiSpecificiRiscossioneOutput.class );
			String message = response.getCodiceEsito () + " - " + response.getDescrizioneEsito ();
			switch ( statusCode ) {
			case 400: //Errore dato dal servizio
				Log.error ( message );
				throw new TassonomiaServiceException ( ce.getCodice (), message );
			case 401: //Unauthorized client
				message = "Unauthorized client for address - Status: " + statusCode + " -- Check request: " + url;
				Log.error ( "Unauthorized client for address - Status: " + statusCode + " -- Check request: " + url );
				throw new TassonomiaServiceException ( ce.getCodice (), message );
			case 404: //Not Fount
				message = "Service not found for address - Status: " + statusCode + " -- Check request: " + url;
				Log.error ( "Service not found for address - Status: " + statusCode + " -- Check request: " + url );
				throw new TassonomiaServiceException ( ce.getCodice (), message );
			default:
				message = "Service for address - unknown error. Status: " + statusCode + " -- Check request: " + url;
				Log.error ( "Service for address - unknown error. Status: " + statusCode + " -- Check request: " + url );
				throw new TassonomiaServiceException ( ce.getCodice (), message );
			}
		} else {
			response = mapper.readValue ( urlConnection.getInputStream (), DatiSpecificiRiscossioneOutput.class );
		}

		Log.info ( "getDatiSpecificiRiscossione::end" );
		return response;
	}

	public String buildJsonInvio ( Object model ) {
		String result;
		try {
			ObjectMapper mapper = new ObjectMapper ();
			mapper.setSerializationInclusion ( JsonInclude.Include.NON_NULL );
			result = mapper.writerWithDefaultPrettyPrinter ().writeValueAsString ( model );
		} catch ( Exception e ) {
			return "";
		}
		return result;
	}
}

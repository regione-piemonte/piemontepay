/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.csi.epay.epayjob.model.JobContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public abstract class JobTassonomia {

	protected JobContext jobContext;

	protected static final String START = "Avvio ";

	protected static final String CHARSET = java.nio.charset.StandardCharsets.UTF_8.name ();

	protected static final int TIMEOUT = 60000;

	public InputStream doPostToRestService ( String url, String token, Object json ) throws IOException {
		String methodName = "doPostToRestService";
		jobContext.info ( methodName, START + methodName );

		HttpURLConnection urlConnection = getHttpURLConnection ( url, token );

		String requestString = "{}";
		if ( null != json ) {
			requestString = buildJsonInvio ( json );
		}

		OutputStream os = urlConnection.getOutputStream ();
		os.write ( requestString.getBytes ( CHARSET ) );
		os.flush ();

		int statusCode = urlConnection.getResponseCode ();
		String message = "";

		if ( statusCode != 200 ) {
			switch ( statusCode ) {
			case 403 :
				message = "Service for address non attivo";
				jobContext.info ( methodName, message );
				break;
			case 400 :
				message = "Service for address: bad request. Check parameters: " + url;
				jobContext.info ( methodName, message );
				break;
			default :
				message = "Service for address: unknown error. Status: " + statusCode + " -- Check request: " + url;
				jobContext.info ( methodName, message );
			}
		}

		jobContext.info ( methodName, "End" );
		return urlConnection.getInputStream ();
	}

	private String buildJsonInvio ( Object model ) {
		final String methodName = "buildJsonInvio";
		jobContext.info ( methodName, START + methodName );

		String result;
		try {

			ObjectMapper mapper = new ObjectMapper ();
			mapper.setSerializationInclusion ( JsonInclude.Include.NON_NULL );

			result = mapper.writerWithDefaultPrettyPrinter ().writeValueAsString ( model );
		} catch ( Exception e ) {
			jobContext.error ( methodName, "errore nella creazione del model json ", e );
			return "";
		}

		jobContext.info ( methodName, "fine " + methodName );
		return result;
	}

	private HttpURLConnection getHttpURLConnection ( String url, String token ) throws IOException {
		HttpURLConnection urlConnection = (HttpURLConnection) ( new URL ( url ).openConnection () );
		urlConnection.setDoOutput ( true );
		urlConnection.setRequestProperty ( "Content-Type", "application/json;charset=" + CHARSET );
		urlConnection.setRequestProperty ( "Authorization", "Basic " + token );
		urlConnection.setRequestProperty ( "Accept", "application/json" );
		urlConnection.setRequestMethod ( "POST" );
		urlConnection.setConnectTimeout ( TIMEOUT );
		urlConnection.setRequestProperty ( "Accept-Charset", CHARSET );
		urlConnection.connect ();

		return urlConnection;
	}
}

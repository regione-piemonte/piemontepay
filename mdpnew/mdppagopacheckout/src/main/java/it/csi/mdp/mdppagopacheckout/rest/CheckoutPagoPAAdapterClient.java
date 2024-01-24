/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import it.csi.mdp.mdppagopacheckout.model.CheckoutPagoPAErrorOutput;
import it.csi.mdp.mdppagopacheckout.model.CheckoutPagoPAInput;
import it.csi.mdp.mdppagopacheckout.model.CheckoutPagoPAOKOutput;
import it.csi.mdp.mdppagopacheckout.model.CheckoutPagoPAOutput;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static it.csi.mdp.mdppagopacheckout.util.Constants.APPLICATION_JSON;
import static it.csi.mdp.mdppagopacheckout.util.Constants.EMPTY_STRING;
import static it.csi.mdp.mdppagopacheckout.util.Constants.OCP_APIM_SUBSCRIPTION_KEY;


@SuppressWarnings ( "unused" )
@ApplicationScoped
public class CheckoutPagoPAAdapterClient {

	private final static int TIMEOUT = 60000;

	@ConfigProperty ( name = "service.checkout.pagoPA.url" )
	String URL;

	public CheckoutPagoPAOutput postCheckoutPagoPA ( CheckoutPagoPAInput checkoutPagoPAInput, String subscriptionKey ) throws IOException {
		Log.info ( "postCheckoutPagoPA::start" );

		CheckoutPagoPAOutput response;
		var mapper = new ObjectMapper ();
		var urlConnection = getHttpURLConnection ( subscriptionKey );
		Log.infof ( "headers: %s", urlConnection.getRequestProperties () );
		urlConnection.connect ();

		var requestString = buildJsonToSend ( checkoutPagoPAInput );
		Log.infof ( "Request: %s", requestString );

		var os = urlConnection.getOutputStream ();
		os.write ( requestString.getBytes () );
		os.flush ();

		var statusCode = urlConnection.getResponseCode ();

		if ( statusCode == 200 || statusCode == 302 ) { // in teoria mai 200ok, vedere lo yaml su github di pagopa
			response = new CheckoutPagoPAOKOutput.Builder ()
							.ok ( true )
							.location ( urlConnection.getHeaderField ( "location" ) )
							.build ();
		} else {
			response = mapper.readValue ( urlConnection.getErrorStream (), CheckoutPagoPAErrorOutput.class );
			Log.error ( "Response:" );
			Log.error ( response );
			response.setOk ( false );
		}
		Log.infof ( "Response: %s", response );
		Log.info ( "postCheckoutPagoPA::end" );
		return response;
	}

	private HttpURLConnection getHttpURLConnection ( String subscriptionKey ) throws IOException {
		HttpURLConnection urlConnection;
		urlConnection = (HttpURLConnection) ( ( new URL ( URL ).openConnection () ) );
		urlConnection.setDoOutput ( true );
		urlConnection.setRequestProperty ( "Content-Type", APPLICATION_JSON );
		urlConnection.setRequestProperty ( "Accept", APPLICATION_JSON );
		urlConnection.setRequestProperty ( OCP_APIM_SUBSCRIPTION_KEY, subscriptionKey );
		urlConnection.setRequestMethod ( "POST" );
		urlConnection.setConnectTimeout ( TIMEOUT );
		urlConnection.setInstanceFollowRedirects ( false ); // importante
		return urlConnection;
	}

	public CheckoutPagoPAOutput postCheckoutPagoPA2 ( CheckoutPagoPAInput checkoutPagoPAInput, String subscriptionKey ) throws IOException {
		final var methodName = "postCheckoutPagoPA";
		Log.infof (  "%s::start", methodName );
		var mapper = new ObjectMapper ();

		CheckoutPagoPAOutput response;
		var postRequest = getHttpPost ( subscriptionKey );
		var requestString = buildJsonToSend ( checkoutPagoPAInput );
		postRequest.setEntity ( new StringEntity ( requestString, ContentType.APPLICATION_JSON ) );

		CloseableHttpClient httpClient = HttpClients.createDefault ();
		CloseableHttpResponse closeableHttpResponse = httpClient.execute ( postRequest );
		Log.infof ( "%s Ottengo la risposta con stato: %s", methodName, closeableHttpResponse.getStatusLine ().getStatusCode () );

		if ( closeableHttpResponse.getStatusLine ().getStatusCode () == HttpStatus.SC_OK
						|| closeableHttpResponse.getStatusLine ().getStatusCode () == HttpStatus.SC_MOVED_TEMPORARILY ) {
			response = new CheckoutPagoPAOKOutput.Builder ()
							.ok ( true )
							.location ( closeableHttpResponse.getFirstHeader ( "location" ).getValue () )
							.build ();

		} else {
			response = mapper.readValue ( closeableHttpResponse.getStatusLine ().getReasonPhrase (), CheckoutPagoPAErrorOutput.class );
			response.setOk ( false );
		}
		Log.infof ( "Response: %s", response );
		Log.info ( "postCheckoutPagoPA::end" );
		return response;
	}

	private HttpPost getHttpPost ( String token ) {
		var postRequest = new HttpPost ( URL );
		postRequest.addHeader ( HttpHeaders.CONTENT_TYPE, APPLICATION_JSON );
		postRequest.addHeader ( HttpHeaders.ACCEPT, APPLICATION_JSON );
		postRequest.addHeader ( OCP_APIM_SUBSCRIPTION_KEY, token );
		return postRequest;
	}

	private String buildJsonToSend ( Object model ) {
		try {
			var mapper = new ObjectMapper ();
			mapper.setSerializationInclusion ( JsonInclude.Include.NON_NULL );
			return mapper.writerWithDefaultPrettyPrinter ().writeValueAsString ( model );
		} catch ( Exception e ) {
			return EMPTY_STRING;
		}
	}
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.rest.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.exception.DatiAvvisoServiceException;
import it.csi.epay.epayfeapi.exception.TassonomiaServiceException;
import it.csi.epay.epayfeapi.model.epaypacatalogsrv.DatiAvvisoOutput;
import it.csi.epay.epayfeapi.model.epaypacatalogsrv.DatiSpecificiRiscossioneInput;
import it.csi.epay.epayfeapi.model.epaypacatalogsrv.DatiSpecificiRiscossioneOutput;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.net.HttpURLConnection;


@ApplicationScoped
public class DatiAvvisoAdapterClient extends ApiClient {

	@ConfigProperty ( name = "epaypacatalogService.authusr" )
	String usr;

	@ConfigProperty ( name = "epaypacatalogService.authpwd" )
	String pwd;

	public DatiAvvisoOutput getDatiAvviso (String codiceFiscaleEtnte, String codiceVersamento, String url )
					throws IOException, DatiAvvisoServiceException {
		var methodName = "[getDatiAvviso] ";
		Log.infof ( "%sBEGIN", methodName );
		Log.infof ( "%sparam codiceFiscaleEtnte:%s", methodName, codiceFiscaleEtnte );
		Log.infof ( "%sparam codiceVersamento:%s", methodName, codiceVersamento );
		Log.infof ( "%sparam url:%s", methodName, url );
		
		url = StringUtils.replace(url, "{organization}", codiceFiscaleEtnte);
		url = StringUtils.replace(url, "{paymentType}", codiceVersamento);

		DatiAvvisoOutput response;
		
		// connect
		var secret = String.format ( "%s:%s", usr, pwd );
		var urlConnection = super.getResponse ( url, null, secret, "GET" );

		var statusCode = urlConnection.getResponseCode ();
		var ce = CodiciEsito.ERRORE_DATI_AVVISO;
		Log.infof ( "%sstatusCode:%d", methodName, statusCode );
//
		var mapper = new ObjectMapper ();
		switch ( statusCode ) {
		case 200:
		case 201:
			response = mapper.readValue ( urlConnection.getInputStream (), DatiAvvisoOutput.class );
			break;
		default:
			response = mapper.readValue ( urlConnection.getErrorStream (), DatiAvvisoOutput.class );
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
			throw new DatiAvvisoServiceException ( ce.getCodice (), message );
		}

		Log.infof ( "%sBEGIN", methodName );
		return response;
	}
	
	
	
	
//	@Override
//	public HttpURLConnection getResponse ( String url, Object request, String secret, String requestMethod ) throws IOException {
//		var methodName = "[getResponse] ";
//
//		Log.infof ( "%sBEGIN", methodName );
//		Log.infof ( "%sparam request:%s", methodName, request );
//		Log.infof ( "%sparam secret:%s", methodName, secret );
//		Log.infof ( "%sparam requestMethod:%s", methodName, requestMethod );
//
//		var basicAuth = new String ( Base64.encodeBase64 ( secret.getBytes () ) );
//
//		// connect
//		HttpURLConnection urlConnection = getHttpURLConnection ( url, requestMethod, basicAuth );
//
//		var requestString = buildJsonInvio ( request );
//		Log.infof ( "%srequestString:%s", methodName, requestString );
//
//		var os = urlConnection.getOutputStream ();
//		os.write ( requestString.getBytes () );
//		os.flush ();
//
//		Log.infof ( "%sEND", methodName );
//		return urlConnection;
//	}
}
	

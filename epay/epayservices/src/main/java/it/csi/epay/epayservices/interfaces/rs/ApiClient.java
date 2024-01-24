/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.interfaces.rs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.csi.epay.epayservices.integration.db.manager.ConfigurazioneManager;
import it.csi.epay.epayservices.model.Configurazione;
import it.csi.epay.epayservices.utilities.Constants;
import it.csi.epay.epayservices.utilities.LogUtil;


public class ApiClient {

	protected static LogUtil log = new LogUtil ( ApiClient.class );

	protected static final String CONFIG_PROPERTIES = "META-INF/config.properties";

	protected static int TIMEOUT = 60000;

	protected ConfigurazioneManager configurazioneManager;

	protected Properties properties;

	public ApiClient ( ConfigurazioneManager configurazioneManager ) throws RuntimeException {
		String methodName = "ApiClient constructor";
		log.info ( methodName, "BEGIN" );

		this.configurazioneManager = configurazioneManager;
		
		try ( InputStream inputStream = this.getClass ().getClassLoader ().getResourceAsStream ( CONFIG_PROPERTIES ) ) {
			properties = new Properties ();
			properties.load ( inputStream );

		} catch ( IOException e ) {
			log.info ( methodName, "errore lettura parametri. " + e.getMessage () );
			throw new RuntimeException ( "Errore lettura parametri: " + e.getMessage () );
		}

		if ( properties.isEmpty () ) {
			log.info ( methodName, "File di properties non trovato. L'elenco e' vuoto!" );
		}
		log.info ( methodName, "END" );
	}

	protected String getSecret ( String authUsrPropertyName, String authPwdPropertyName ) {
		String methodName = "getSecret";
		log.info ( methodName, "BEGIN" );

		String usr = properties.getProperty ( authUsrPropertyName );
		String pwd = properties.getProperty ( authPwdPropertyName );
		String secret = usr + ":" + pwd;

		log.info ( methodName, "secret:" + secret );
		log.info ( methodName, "END" );
		return secret;
	}

	protected HttpURLConnection getResponse ( String secret, String url, String requestMethod, Object request ) throws MalformedURLException, IOException {
		String methodName = "getResponse";
		log.info ( methodName, "BEGIN" );
		log.info ( methodName, "secret:" + secret );
		log.info ( methodName, "url:" + url );
		log.info ( methodName, "requestMethod:" + requestMethod );

		String basicAuth = new String ( Base64.encodeBase64 ( secret.getBytes () ) );

		// connect
		HttpURLConnection urlConnection;
		urlConnection = (HttpURLConnection) ( ( new URL ( url ).openConnection () ) );
		urlConnection.setDoOutput ( true );
		urlConnection.setRequestProperty ( "Content-Type", "application/json" );
		urlConnection.setRequestProperty ( "Authorization", "Basic " + basicAuth );
		urlConnection.setRequestProperty ( "Accept", "application/json" );
		urlConnection.setRequestMethod ( requestMethod );
		urlConnection.setConnectTimeout ( TIMEOUT );
		urlConnection.connect ();

		String requestString = buildJsonInvio ( request );
		log.info ( methodName, "requestString:" + requestString );

		OutputStream os = urlConnection.getOutputStream ();
		os.write ( requestString.getBytes () );
		os.flush ();

		log.info ( methodName, "END" );
		return urlConnection;
	}

	protected String getServiceUrl ( String serviceUrlKey ) throws RuntimeException {
		String methodName = "getServiceUrl";
		log.info ( methodName, "BEGIN" );
		log.info ( methodName, "serviceUrlKey:" + serviceUrlKey );

		Configurazione config = configurazioneManager.getConfigurazione ( serviceUrlKey, Constants.COD_ENTE_DEFAULT );
		if ( config == null ) {
			log.error ( methodName, "errore in fase di reperimento della url del servizio, configurazione per chiave " + serviceUrlKey + " mancante" );
			throw new RuntimeException ( "Errore in fase di reperimento della url del servizio, configurazione per chiave " + serviceUrlKey + " mancante" );
		}

		String url = config.getValore ();
		log.info ( methodName, "url:" + url );
		log.info ( methodName, "END" );
		return url;
	}

	protected String buildJsonInvio ( Object model ) {
		final String methodName = "buildJsonInvio";
		log.info ( methodName, "BEGIN" );

		String result;
		try {
			ObjectMapper mapper = new ObjectMapper ();
			mapper.setSerializationInclusion ( Include.NON_NULL );
			result = mapper.writerWithDefaultPrettyPrinter ().writeValueAsString ( model );

		} catch ( Exception e ) {
			log.error ( methodName, "errore nella costruzione del JSon: " + e.getMessage () );
			return "";
		}

		log.info ( methodName, "END" );
		return result;
	}
}

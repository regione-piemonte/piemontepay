/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;


public class EpayapiService {

	static private final Logger log = LogManager.getLogger ( APPLICATION_CODE + ".presentation" );

	public static String getStatoPosizioneDebitoriaEsterna ( String organization, String paymentType, String iuv ) throws IOException {
		String result;
		Properties config = new Properties ();
		try {
			ServletContext servletContext = (ServletContext) ActionContext.getContext ().get ( StrutsStatics.SERVLET_CONTEXT );
			InputStream configInputStream = servletContext.getResourceAsStream ( "/WEB-INF/application-config.properties" );
			config.load ( configInputStream );
		} catch ( IOException e ) {
			log.error ( "Errore nella configurazione del progetto" );
		}

		String uri = config.getProperty ( "epayapi.url" );
		try {
			uri += "organizations/" + organization + "/paymenttypes/" + paymentType + "/debtpositions/" + URLEncoder.encode ( iuv,
							String.valueOf ( StandardCharsets.UTF_8 ) ) + "/status";
		} catch ( UnsupportedEncodingException e ) {
			throw new RuntimeException ( e );
		}
		String user = config.getProperty ( "epayapi.user" );
		String pass = config.getProperty ( "epayapi.pass" );
		log.info ( "user=" + user );
		log.info ( "pass=" + pass );
		log.info ( "uri=" + uri );

		HttpURLConnection conn = null;
		URL url = new URL ( uri );
		try {
			conn = (HttpURLConnection) url.openConnection ();
			conn.setRequestMethod ( "GET" );
			conn.setRequestProperty ( "Accept", "application/json" );
			String auth = user + ":" + pass;
			byte[] encodedAuth = Base64.encodeBase64 ( auth.getBytes ( StandardCharsets.UTF_8 ) );
			String authHeaderValue = "Basic " + new String ( encodedAuth );
			conn.setRequestProperty ( "Authorization", authHeaderValue );
			if ( conn.getResponseCode () != 200 ) {
				log.error ( "Errore nel reperire dati sullo stato della posizione debitoria [" + iuv + "], response code: " + conn.getResponseCode () );
			}
			ObjectMapper mapper = new ObjectMapper ();
			TestObject testObject = mapper.readValue ( conn.getInputStream (), TestObject.class );
			result = testObject.code;
		} catch ( IOException e ) {
			throw new IOException ();
		} finally {
			assert conn != null;
			conn.disconnect ();
		}
		return result;
	}

	private static class TestObject {

		public Result result;

		public String code;

		public String description;
	}


	private static class Result {

		public String code;

		public String description;
	}
}

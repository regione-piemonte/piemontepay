/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.integration.rs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.csi.epay.epaypacatalogweb.common.config.LogConfig;


//:TODO finire configurazione cached.
public class EpaypacatalogsrvRsClient {
	
	protected Logger LOGGER = LoggerFactory.getLogger(LogConfig.HANDLER_ASPECT);



	private String url ;
//	private String usr ;
//	private String pwd ;
	String basicAuthConfig;
	public static int TIMEOUT = 60000;
//    private Properties properties;
//    private static final String CONFIG_PROPERTIES = "META-INF/config.properties";
	
	
    public EpaypacatalogsrvRsClient (String url) {
//    	String methodName = "EpaypacatalogsrvRsClient constructor";
    	this.url = url;
    	this.basicAuthConfig=
    	        java.util.ResourceBundle.getBundle("config")
                .getString("epaypacatalogService.auth");
    	
        	
    }
	
	public InputStream getService(Object request) throws MalformedURLException, IOException {
		String methodName = "getServicetest";
		LOGGER.debug(methodName, "Entering...");

//		try {
				
		//PROVVISORIO
//		user e password verranno inserite in un file di properties in base 64?
//			String secret = "user001" + ":" + "_Passw0rd1!";			       
//	        String basicAuthConfig = new String(Base64.encodeBase64(secret.getBytes()));	
	        
	        
//	        @@service.tassonomia.auth=dXNlcjAwMTpfUGFzc3cwcmQxIQ==
//	        String basicAuthConfig=properties.getProperty("epaypacatalogService.auth");
//	        String token = java.util.ResourceBundle.getBundle("config")
//	                  .getString("service.epaypacatalogwebws.endpoint");
//	        
//	        if ( StringUtils.isEmpty(token) ) {
//	        	LOGGER.debug(methodName,  "Token non trovato" );
//	        }

	        HttpURLConnection urlConnection;
			
		    //Connect
	        urlConnection = (HttpURLConnection) ((new URL(url).openConnection()));
	        urlConnection.setDoOutput(true);
	        urlConnection.setRequestProperty("Content-Type", "application/json");
	        urlConnection.setRequestProperty("Authorization", "Basic "+basicAuthConfig);
	        urlConnection.setRequestProperty("Accept", "application/json");
	        urlConnection.setRequestMethod("POST");
	        urlConnection.setConnectTimeout(TIMEOUT);
	        urlConnection.connect();
			
			String requestString = buildJsonInvio(request);
			
			OutputStream os = urlConnection.getOutputStream();
	        os.write(requestString.getBytes());
	        os.flush();
			
			int statusCode = urlConnection.getResponseCode();
			String message ="";
//			CodiciEsito ce = CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;
			
			if (statusCode != 200) {
				switch (statusCode) {
				case 403: //Forbidden
					message="Service for address non attivo";
					LOGGER.warn(methodName, "Service for address non attivo");	
					return urlConnection.getErrorStream ();
//                    throw new Exception (ce.getCodice(), message); //PROVVISORIO
				case 401: //Forbidden
				    message="Utente non abilitato";
				    LOGGER.warn(methodName, "Utente non abilitato");	
				    return urlConnection.getErrorStream ();
//                    throw new Exception (ce.getCodice(), message); //PROVVISORIO
				case 400:
					message="Service for address: bad request. Check parameters: " + url;
					LOGGER.warn(methodName, "Service for address: bad request. Check parameters: " + url);
//					throw new Exception (ce.getCodice(), message);//PROVVISORIO
					return urlConnection.getErrorStream ();
//					break;
				default:
					message="Service for address: unknown error. Status: "  + statusCode + " -- Check request: " + url;
					LOGGER.warn(methodName, "Service for address: unknown error. Status: "  + statusCode + " -- Check request: " + url);	
					return urlConnection.getErrorStream ();
//					throw new Exception (ce.getCodice(), message);//PROVVISORIO
				}
			}
			
//			ObjectMapper mapper = new ObjectMapper();
//			response = mapper.readValue(urlConnection.getInputStream(), DatiSpecificiRiscossioneOutput.class);
		
			LOGGER.debug(methodName, "End");
			return urlConnection.getInputStream();
//		return response;
	}
	
	
    
    public String buildJsonInvio(Object model)  {
		final String methodName = "buildJsonInvio";
		
		LOGGER.debug(methodName, "Entering...");
		String result;
		try {

			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Inclusion.NON_NULL);

			result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(model);
		}
		catch (Exception e) {
			LOGGER.error(methodName, "Exception: " + e.toString());
			return "";
		}
		
		LOGGER.debug(methodName, "End");
		return result;
	}

}

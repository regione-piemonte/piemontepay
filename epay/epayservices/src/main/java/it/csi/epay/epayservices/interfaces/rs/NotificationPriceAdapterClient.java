/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.interfaces.rs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.csi.epay.epayservices.interfaces.rs.exception.NotificationPriceServiceException;
import it.csi.epay.epayservices.model.NotificationPriceOutput;
import it.csi.epay.epayservices.utilities.EncryptionDecryptionUtil;
import it.csi.epay.epayservices.utilities.LogUtil;

public class NotificationPriceAdapterClient {
	
	private static LogUtil log = new LogUtil (NotificationPriceAdapterClient.class );

	private String url ;
//	private String usr ;
//	private String pwd ;
	public static int TIMEOUT = 60000;
    private Properties properties;
    private static final String CONFIG_PROPERTIES = "META-INF/config.properties";
    

    public NotificationPriceAdapterClient (String url) {
    	String methodName = "NotificationPriceAdapterClient constructor";
    	this.url = url;
//        this.usr = usr;
//        this.pwd = pwd;
        
        try ( InputStream inputStream = this.getClass ().getClassLoader ().getResourceAsStream ( CONFIG_PROPERTIES ) ) {
            properties = new Properties ();
            properties.load ( inputStream );
        } catch ( IOException e ) {
        	log.debug(methodName,  "errore lettura parametri. " + e.getMessage () );
            throw new RuntimeException ( "Errore lettura parametri: " + e.getMessage () );
        }

        if ( properties.isEmpty () ) {
        	log.debug(methodName,  "File di properties non trovato. L'elenco e' vuoto!" );
        }
    }
	

	
	public NotificationPriceOutput getNotificationPrice (String organization, String iuv, byte [] credenziali) throws MalformedURLException, IOException, NotificationPriceServiceException {
        String methodName = "getNotificationPrice";
        log.debug(methodName, "Entering...");

        NotificationPriceOutput response = null ;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        HttpURLConnection urlConnection;
                  
//        String usr = properties.getProperty("epaypacatalogService.authusr");
        String pwd = properties.getProperty("password.notification.price");
//        TODO inserire un campo credenziali_Pnd sul db tipo_pagamento. 
//        Criptati come la passprase li salvo in pase 64 prima criptati poi
//        li leggo, li decripto e li dovrei gia' trovare in base 64 da inserire
//        String secret = usr + ":" + pwd;                   
        String basicAuth =EncryptionDecryptionUtil.decrypt2 ( credenziali, pwd );   
                
        //Connect
        urlConnection = (HttpURLConnection) ((new URL(url).openConnection())); //FIXME [AF] configurazione dell'url
        urlConnection.setDoOutput(true);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("Authorization", "Basic "+basicAuth);
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setRequestMethod("GET");
        urlConnection.setConnectTimeout(TIMEOUT);
        urlConnection.connect();
        
        int statusCode = urlConnection.getResponseCode();
        CodiciEsito ce = CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;
        
        if (statusCode != 200) {
//        	String result = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))
//     			   .lines().collect(Collectors.joining("\n"));
//        	log.info(methodName, result);
            response = mapper.readValue(urlConnection.getErrorStream(), NotificationPriceOutput.class);
            String message = response.getCode() + " - " + response.getStatus()  + " - " + response.getDetail();
            switch (statusCode) {
            case 400: //Errore dato dal servizio                
                log.warn(methodName, message);                    
                throw new NotificationPriceServiceException(ce.getCodice (), message );
            case 401: //Unauthorized client             
                message="Unauthorized client for address - Status: "  + statusCode + " -- Check request: " + url;
                log.warn(methodName, "Unauthorized client for address - Status: "  + statusCode + " -- Check request: " + url);
                throw new NotificationPriceServiceException (ce.getCodice(), message);  
                    
            case 404: //Not Fount               
                message="Service not found for address - Status: "  + statusCode + " -- Check request: " + url;
                log.warn(methodName, "Service not found for address - Status: "  + statusCode + " -- Check request: " + url);
                throw new NotificationPriceServiceException (ce.getCodice(), message);  
                
            default:
                message="Service for address - unknown error. Status: "  + statusCode + " -- Check request: " + url;
                log.warn(methodName, "Service for address - unknown error. Status: "  + statusCode + " -- Check request: " + url);          
                throw new NotificationPriceServiceException (ce.getCodice(), message);
            }
        } else {
//        	String result = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))
//        			   .lines().collect(Collectors.joining("\n"));
//        	log.info(methodName, result);
            response = mapper.readValue(urlConnection.getInputStream(), NotificationPriceOutput.class);
        }
        
        log.debug(methodName, "End");
        return response;
    }
    
	
	
//    protected String getDSRCached ( ComponentiAccertamento componenti ) {
//        String dsr = cachedDSR.get ( componenti );
//        if ( ( null == config ) || !config.isConnectedIMAP () ) {
//            config = getConfig ( idEnte );
//            Assert.notNull ( config, String.format ( ErrorMessages.E_ERRORE_CONFIG_NON_TROVATA, idEnte ) );
//            // in caso di configurazione mancante vengono inseriti sulla mappa una per volta e solo se non presenti.
//            synchronized ( cachedConfiguration ) {
//                cachedConfiguration.put ( idEnte, config );
//            }
//        }
//        return config;
//    }
    
    public String buildJsonInvio(Object model)  {
		final String methodName = "buildJsonInvio";
		
		log.debug(methodName, "Entering...");
		String result;
		try {

			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);

			result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(model);
		}
		catch (Exception e) {
			log.error(methodName, "Exception: " + e.toString());
			return "";
		}
		
		log.debug(methodName, "End");
		return result;
	}

}

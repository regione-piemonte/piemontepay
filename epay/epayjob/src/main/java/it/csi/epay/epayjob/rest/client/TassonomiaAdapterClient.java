/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.rest.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.csi.epay.epayjob.utilities.CodiciEsito;
import it.csi.epay.epayjob.utilities.LogUtil;
import it.csi.epay.epayservices.interfaces.ejb.ConfigurazioneFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.interfaces.rs.exception.TassonomiaServiceException;
import it.csi.epay.epayservices.model.ComponentiAccertamento;
import it.csi.epay.epayservices.model.Configurazione;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneInput;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneOutput;


public class TassonomiaAdapterClient {
    private final LogUtil log;
    private static final String CONFIG_PROPERTIES = "config.properties";
    private Properties properties;
    protected static int TIMEOUT = 60000;
    private final ConfigurazioneFacade configurazioneFacade ;
    
    public static final String COD_ENTE_DEFAULT = "PagoPa"; // codice 0

    public static final String CONFIG_ENDPOINT_SERVICE_TASSONOMIA = "ENDPOINT_SERVICE_TASSONOMIA"; // add _LOCAL for dev test


	public TassonomiaAdapterClient ( ConfigurazioneFacade configurazioneFacade) {
	    String methodName = "ApiClient constructor";
	    log = new LogUtil(this.getClass());
        log.info ( methodName, "BEGIN" );
       this.configurazioneFacade = configurazioneFacade;

        
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

	public DatiSpecificiRiscossioneInput buildDatiSpecificiRiscossioneInput ( String codiceFiscaleEnte, String tipoPagamento ) {
		String methodName = "buildDatiSpecificiRiscossioneInput";
		log.info ( methodName, "BEGIN" );
		log.info ( methodName, "codiceFiscaleEnte:" + codiceFiscaleEnte );
		log.info ( methodName, "tipoPagamento:" + tipoPagamento );

		DatiSpecificiRiscossioneInput request = new DatiSpecificiRiscossioneInput ();
		request.setCodiceFiscaleEnte ( codiceFiscaleEnte );
		request.setTipoPagamento ( tipoPagamento );
		request.setAnnoEsercizio ( Calendar.getInstance ().get ( Calendar.YEAR ) );

		log.info ( methodName, "END" );
		return request;
	}

	public DatiSpecificiRiscossioneOutput getDatiSpecificiRiscossione ( DatiSpecificiRiscossioneInput request )
					throws MalformedURLException, IOException, TassonomiaServiceException, RuntimeException, NoDataException {
		String methodName = "getDatiSpecificiRiscossione";
		log.info ( methodName, "BEGIN" );
		log.info ( methodName, "request:" + request );

		DatiSpecificiRiscossioneOutput response = null;

		// ottiene la url del servizio
		 Configurazione config= configurazioneFacade.ricerca(CONFIG_ENDPOINT_SERVICE_TASSONOMIA, COD_ENTE_DEFAULT);
		String url = config.getValore ();

		// ottiene la secret
		String secret = properties.getProperty ( "service.tassonomia.auth" );

		// connect
		HttpURLConnection urlConnection = getResponse ( secret, url, "POST", request );

		int statusCode = urlConnection.getResponseCode ();
//		CodiciEsito ce = CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;
		
		ObjectMapper mapper = new ObjectMapper ();
		if ( statusCode != 200 ) {
		    CodiciEsito ce =CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;
			response = mapper.readValue ( urlConnection.getErrorStream (), DatiSpecificiRiscossioneOutput.class );
			String message = response.getCodiceEsito () + " - " + response.getDescrizioneEsito ();
			switch ( statusCode ) {
			case 400 : //Errore dato dal servizio                
				log.warn ( methodName, message );
				throw new TassonomiaServiceException ( ce.getCodice (), message );
			case 401 : //Unauthorized client             
				message = "Unauthorized client for address - Status: " + statusCode + " -- Check request: " + url;
				log.warn ( methodName, "Unauthorized client for address - Status: " + statusCode + " -- Check request: " + url );
				throw new TassonomiaServiceException ( ce.getCodice (), message );
			case 404 : //Not Fount               
				message = "Service not found for address - Status: " + statusCode + " -- Check request: " + url;
				log.warn ( methodName, "Service not found for address - Status: " + statusCode + " -- Check request: " + url );
				throw new TassonomiaServiceException ( ce.getCodice (), message );
			default :
				message = "Service for address - unknown error. Status: " + statusCode + " -- Check request: " + url;
				log.warn ( methodName, "Service for address - unknown error. Status: " + statusCode + " -- Check request: " + url );
				throw new TassonomiaServiceException ( ce.getCodice (), message );
			}
		} else {
			response = mapper.readValue ( urlConnection.getInputStream (), DatiSpecificiRiscossioneOutput.class );
		}

		log.info ( methodName, "END" );
		return response;
	}
	
	private HttpURLConnection getResponse ( String secret, String url, String requestMethod, Object request ) throws MalformedURLException, IOException {
        String methodName = "getResponse";
        log.info ( methodName, "BEGIN" );
        log.info ( methodName, "secret:" + secret );
        log.info ( methodName, "url:" + url );
        log.info ( methodName, "requestMethod:" + requestMethod );


        // connect
        HttpURLConnection urlConnection;
        urlConnection = (HttpURLConnection) ( ( new URL ( url ).openConnection () ) );
        urlConnection.setDoOutput ( true );
        urlConnection.setRequestProperty ( "Content-Type", "application/json" );
        urlConnection.setRequestProperty ( "Authorization", "Basic " + secret );
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
	
	private String buildJsonInvio ( Object model ) {
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
	
//	protected String getSecret ( String authUsrPropertyName, String authPwdPropertyName ) {
//        String methodName = "getSecret";
//        log.info ( methodName, "BEGIN" );
//
//        String usr = properties.getProperty ( authUsrPropertyName );
//        String pwd = properties.getProperty ( authPwdPropertyName );
//        String secret = usr + ":" + pwd;
//
//        log.info ( methodName, "secret:" + secret );
//        log.info ( methodName, "END" );
//        return secret;
//    }

	/**
	 * Permette di avere una mappa con un numero di elementi massimo. Al raggiungimento di tale viene cancellato il meno recente.
	 */
	protected static final Map<ComponentiAccertamento, String> cachedDSR = new LinkedHashMap<ComponentiAccertamento, String> () {

		private static final long serialVersionUID = -581183073192350209L;

		/**
		 * Ridefinizione. Permette di cancellare, al raggiungimento del numero configurato, l'elemento meno recente.
		 */
		@Override
		protected boolean removeEldestEntry ( Map.Entry<ComponentiAccertamento, String> entry ) {
			return size () > 50;
		}
	};
}

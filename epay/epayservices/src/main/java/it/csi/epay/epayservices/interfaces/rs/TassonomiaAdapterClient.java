/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.interfaces.rs;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.csi.epay.epayservices.integration.db.manager.ConfigurazioneManager;
import it.csi.epay.epayservices.interfaces.rs.exception.TassonomiaServiceException;
import it.csi.epay.epayservices.model.ComponentiAccertamento;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneInput;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneOutput;
import it.csi.epay.epayservices.utilities.Constants;


public class TassonomiaAdapterClient extends ApiClient {

	public TassonomiaAdapterClient ( ConfigurazioneManager configurazioneManager ) {
		super ( configurazioneManager );
		log.info ( "TassonomiaAdapterClient", "CONSTRUCTED" );
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
					throws MalformedURLException, IOException, TassonomiaServiceException, RuntimeException {
		String methodName = "getDatiSpecificiRiscossione";
		log.info ( methodName, "BEGIN" );
		log.info ( methodName, "request:" + request );

		DatiSpecificiRiscossioneOutput response = null;

		// ottiene la url del servizio
		String url = super.getServiceUrl ( Constants.CONFIG_ENDPOINT_SERVICE_TASSONOMIA );

		// ottiene la secret
		String secret = super.getSecret ( Constants.CATALOG_AUTH_USR, Constants.CATALOG_AUTH_PWD );

		// connect
		HttpURLConnection urlConnection = super.getResponse ( secret, url, "POST", request );

		int statusCode = urlConnection.getResponseCode ();
		CodiciEsito ce = CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;

		ObjectMapper mapper = new ObjectMapper ();
		if ( statusCode != 200 ) {
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

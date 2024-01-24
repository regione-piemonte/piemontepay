/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.interfaces.rs;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.csi.epay.epayservices.integration.db.manager.ConfigurazioneManager;
import it.csi.epay.epayservices.interfaces.rs.exception.TransactionException;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.v1.Cart;
import it.csi.epay.epayservices.model.v1.Error;
import it.csi.epay.epayservices.model.v1.PaymentNotice;
import it.csi.epay.epayservices.model.v1.Transaction;
import it.csi.epay.epayservices.utilities.Constants;


public class TransactionAdapterClient extends ApiClient {

	public TransactionAdapterClient ( ConfigurazioneManager configurazioneManager ) {
		super ( configurazioneManager );
		log.info ( "TransactionAdapterClient", "CONSTRUCTED" );
	}

	public Cart buildCart ( List<Pagamento> pagamentoList, String email ) throws IllegalArgumentException {
		String methodName = "buildCart";
		log.info ( methodName, "BEGIN" );
		log.info ( methodName, "pagamentoList:" + pagamentoList );
		log.info ( methodName, "email:" + email );

		Cart request = new Cart ();

		List<PaymentNotice> pnList = new LinkedList<PaymentNotice> ();
		//
		for ( Pagamento pagamento: pagamentoList ) {
			try {
				PaymentNotice pn = new PaymentNotice ();
				pn.setAmount ( pagamento.getImporto () );
				pn.setApplicationId ( pagamento.getTipoPagamento ().getIdApplicazione () );
				pn.setDescription ( pagamento.getCausale () );
				pn.setNoticeNumber ( pagamento.getCodiceAvviso () );
				//
				pnList.add ( pn );

			} catch ( IllegalArgumentException e ) {
				log.warn ( methodName, e.getMessage () );
				throw e;
			}
		}

		request.setPaymentNotices ( pnList );
		request.setEmailNotice ( email );

		log.info ( methodName, "END" );
		return request;
	}

	public Transaction getTransaction ( Cart request ) throws MalformedURLException, IOException, TransactionException, RuntimeException {
		String methodName = "getTransaction";
		log.info ( methodName, "BEGIN" );
		log.info ( methodName, "request:" + request );

		Transaction response = null;

		// ottiene la url del servizio
		String url = super.getServiceUrl ( Constants.CONFIG_ENDPOINT_SERVICE_CART );

		// ottiene la secret
		String secret = super.getSecret ( Constants.MDPPAGOPA_CHECKOUT_AUTH_USR, Constants.MDPPAGOPA_CHECKOUT_AUTH_PWD );

		// connect
		HttpURLConnection urlConnection = super.getResponse ( secret, url, "POST", request );

		int statusCode = urlConnection.getResponseCode ();
		log.info ( methodName, "statusCode:" + statusCode );

		ObjectMapper mapper = new ObjectMapper ();
		switch ( statusCode ) {
		case 200 :
		case 201 :
			response = mapper.readValue ( urlConnection.getInputStream (), Transaction.class );
			break;
		default :
			Error error = mapper.readValue ( urlConnection.getErrorStream (), Error.class );
			//
			String message;
			switch ( statusCode ) {
			case 400 :
				message = "Bad Request";
				break;
			case 401 :
				message = "Unauthorized";
				break;
			case 403 :
				message = "Forbidden";
				break;
			case 404 :
				message = "Not Found";
				break;
			case 500 :
				message = "Internal error";
				break;
			default : // Not Handled, es. 204 ...
				message = "Not Handled error";
				break;
			}
			message = statusCode + " " + message + " -- Check request: " + url + " -- " + error.getDetail ();
			log.error ( methodName, message );
			throw new TransactionException ( message );
		}

		log.info ( methodName, "END" );
		return response;
	}
}

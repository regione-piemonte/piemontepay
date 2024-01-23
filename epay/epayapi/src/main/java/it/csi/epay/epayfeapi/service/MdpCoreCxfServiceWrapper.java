/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import io.quarkiverse.cxf.annotation.CXFClient;
import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.exception.MdpException;
import it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf.AppGateway;
import it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf.ElencoRPT;
import it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf.IMdpCoreCxf;
import it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf.Transazione;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;


@ApplicationScoped
@Transactional
public class MdpCoreCxfServiceWrapper {

	@Inject
	@CXFClient ( "IMdpCoreCxf" )
	@SuppressWarnings ( "all" )
	IMdpCoreCxf mdpCoreCxf;

	public String getPaymentURL ( Transazione transazione, ElencoRPT elencoRPT, boolean isMultiBeneficiario ) throws MdpException {
		String methodName = "[getPaymentURL] ";
		Log.info ( methodName + "BEGIN" );

		try {
			transazione.setCcy ( "EUR" );
			transazione.setLanguage ( "ITA" );
			List<AppGateway> appGatewayList = mdpCoreCxf.getModalitaPagamento ( transazione, transazione.getApplicationId () );
			if ( appGatewayList.isEmpty () ) {
				throw new Exception ( "Nessun gateway configurato." );
			}
			if ( appGatewayList.size () != 1 ) {
				throw new Exception ( "Trovati più di un gateway configurato." );
			}
			AppGateway appGateway = appGatewayList.get ( 0 );
			transazione.setGatewayId ( appGateway.getGatewayId () );
			transazione.setGatewaypaymodeid ( appGateway.getPaymentmodeId () );
			Log.info ( methodName + "--------------------------------------------------------------------------------" );
			Log.info ( methodName + "Call mdpCoreCxf.startTransazione" );
			Log.info ( methodName + "     Param transazione:" );
			Log.info ( methodName + transazione );
			Log.info ( methodName + "     Param elencoRPT:" );
			Log.info ( methodName + elencoRPT );
			Log.info ( methodName + "     Param multiBeneficiario: " + isMultiBeneficiario );
			String urlTransazione = mdpCoreCxf.startTransazioneCarrello ( transazione, null, elencoRPT, isMultiBeneficiario );
			Log.info ( methodName + "Response iMdpCoreCxf.startTransazione: " + urlTransazione );

			return urlTransazione;

		} catch ( Exception e ) {
			Log.error ( methodName + "response error: " + e.getMessage () );
			throw new MdpException ( "Errore getModalitaPagamento/startTransazioneCarrello", e );

		} finally {
			Log.info ( "--------------------------------------------------------------------------------" );
			Log.info ( methodName + "END" );
		}
	}
}

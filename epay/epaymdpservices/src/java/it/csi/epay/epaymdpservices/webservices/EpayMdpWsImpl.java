/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymdpservices.webservices;

import javax.ejb.EJB;
import javax.jws.WebService;

import it.csi.epay.epaymdpservices.business.interfaces.EpayMdpServicesBeanLocal;
import it.csi.epay.epaymdpservices.dto.epaymdpservices.EsitoRiceviEsito;
import it.csi.epay.epaymdpservices.dto.epaymdpservices.EsitoRiceviRT;
import it.csi.epay.epaymdpservices.dto.epaymdpservices.EsitoVerificaDatiPagamento;
import it.csi.epay.epaymdpservices.dto.epaymdpservices.ParametriRiceviEsito;
import it.csi.epay.epaymdpservices.dto.epaymdpservices.ParametriRiceviRT;
import it.csi.epay.epaymdpservices.exception.EpayMdpServicesException;
import it.csi.epay.epaymdpservices.exception.ErrorParameterException;
import it.csi.epay.epaymdpservices.exception.MissingParameterException;
import it.csi.epay.epaymdpservices.util.LoggerUtil;
import it.csi.epay.epaymdpservices.webservices.interfaces.EpayMdpWsInterface;
import it.csi.epay.epaymdpservices.webservices.interfaces.EsitoChiediDatiPagamento;
import it.csi.epay.epaymdpservices.webservices.interfaces.ParametriChiediDatiPagamento;
import it.csi.epay.epaymdpservices.webservices.interfaces.ParametriVerificaDatiPagamento;
import it.csi.epay.epaymdpservices.webservices.interfaces.RiceviEsitoException;
import it.csi.epay.epaymdpservices.webservices.interfaces.UnrecoverableException;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;

@WebService(endpointInterface = "it.csi.epay.epaymdpservices.webservices.interfaces.EpayMdpWsInterface", targetNamespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", portName = "EpayMdpWsPort", serviceName = "EpayMdpWs")
public class EpayMdpWsImpl implements EpayMdpWsInterface {

	@EJB
	private EpayMdpServicesBeanLocal epayMdpServices;

	public EsitoRiceviRT riceviRT(ParametriRiceviRT parametriRiceviRT) throws MissingParameterException, ErrorParameterException, EpayMdpServicesException {
		LoggerUtil.begin();
		EsitoRiceviRT esitoRiceviRT = new EsitoRiceviRT();		
		try {
			esitoRiceviRT = epayMdpServices.getEsitoRiceviRT(parametriRiceviRT);
		} catch (IllegalArgumentException | NoDataException e) {
			LoggerUtil.error("ERRORE", e);
		}
		LoggerUtil.end();
		return esitoRiceviRT;
	}

	public EsitoRiceviEsito riceviEsito(ParametriRiceviEsito parametri) throws RiceviEsitoException, UnrecoverableException {

		LoggerUtil.begin();
		EsitoRiceviEsito esito = null;
		try {
			esito = epayMdpServices.riceviEsito(parametri);
			return esito;
		} catch (IllegalArgumentException | NoDataException | EpayMdpServicesException | MissingParameterException | ErrorParameterException e) {
			LoggerUtil.error("ERRORE", e);
		}
		LoggerUtil.end();
		
		return esito;
	}

	public EsitoVerificaDatiPagamento verificaDatiPagamento(ParametriVerificaDatiPagamento parametri) {
		LoggerUtil.begin();
		String iuv = parametri.getIuv();
		String timestamp = parametri.getTimestamp();
		String mac = parametri.getMac();
		EsitoVerificaDatiPagamento esito = new EsitoVerificaDatiPagamento();
		
		LoggerUtil.debug ( "##### VERIFICA DATI DI PAGAMENTO ###########################################");
		
		try {
			esito = epayMdpServices.verificaDatiPagamento(iuv, timestamp, mac);
		} catch (IllegalArgumentException | NoDataException | EpayMdpServicesException | MissingParameterException | ErrorParameterException e) {
			LoggerUtil.error("ERRORE", e);
		}
		LoggerUtil.end();
		return esito;
	}

	public EsitoChiediDatiPagamento chiediDatiPagamento(ParametriChiediDatiPagamento parametri) {
		LoggerUtil.begin();
		String iuv = parametri.getIuv();
		String timestamp = parametri.getTimestamp();
		String mac = parametri.getMac();
		String importoVersamento = parametri.getImportoVersamento().toString();
		String transactionId = parametri.getTransactionId();
		EsitoChiediDatiPagamento esitoChiediDatiPagamento = new EsitoChiediDatiPagamento();
		try {
			esitoChiediDatiPagamento = epayMdpServices.chiediDatiPagamento(iuv, timestamp, mac, importoVersamento, transactionId);
		} catch (IllegalArgumentException | NoDataException | EpayMdpServicesException | MissingParameterException | ErrorParameterException e) {
			LoggerUtil.error("ERRORE", e);
		}
		LoggerUtil.end();
		return esitoChiediDatiPagamento;
	}


}

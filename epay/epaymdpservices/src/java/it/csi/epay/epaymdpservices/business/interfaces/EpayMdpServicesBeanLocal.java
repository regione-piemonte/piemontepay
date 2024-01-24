/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymdpservices.business.interfaces;

import javax.ejb.Local;

import it.csi.epay.epaymdpservices.dto.epaymdpservices.EsitoRiceviEsito;
import it.csi.epay.epaymdpservices.dto.epaymdpservices.EsitoRiceviRT;
import it.csi.epay.epaymdpservices.dto.epaymdpservices.EsitoVerificaDatiPagamento;
import it.csi.epay.epaymdpservices.dto.epaymdpservices.ParametriRiceviEsito;
import it.csi.epay.epaymdpservices.dto.epaymdpservices.ParametriRiceviRT;
import it.csi.epay.epaymdpservices.exception.EpayMdpServicesException;
import it.csi.epay.epaymdpservices.exception.ErrorParameterException;
import it.csi.epay.epaymdpservices.exception.MissingParameterException;
import it.csi.epay.epaymdpservices.webservices.interfaces.EsitoChiediDatiPagamento;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;

@Local
public interface EpayMdpServicesBeanLocal {
	
	public EsitoRiceviRT getEsitoRiceviRT(ParametriRiceviRT parametri) throws IllegalArgumentException, NoDataException, MissingParameterException, ErrorParameterException, EpayMdpServicesException;

	public EsitoVerificaDatiPagamento verificaDatiPagamento(String iuv, String timestamp, String mac) throws IllegalArgumentException, NoDataException, MissingParameterException, ErrorParameterException, EpayMdpServicesException;
	
	public EsitoRiceviEsito riceviEsito(ParametriRiceviEsito parametri) throws IllegalArgumentException, NoDataException, MissingParameterException, ErrorParameterException, EpayMdpServicesException;
	
	public EsitoChiediDatiPagamento chiediDatiPagamento(String iuv, String timestamp, String mac, String importoVersamento, String transactionId)
			throws IllegalArgumentException, NoDataException, MissingParameterException, ErrorParameterException, EpayMdpServicesException;
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model.v1;

import java.io.Serializable;


public class GetDatiPagamentoChiamanteEsternoInput extends ChiamanteEsternoCommonInput implements Serializable {

	private static final long serialVersionUID = -4203118405566742598L;

	private String paymentType;

	public String getPaymentType () {
		return paymentType;
	}

	public void setPaymentType ( String paymentType ) {
		this.paymentType = paymentType;
	}

	public GetDatiPagamentoChiamanteEsternoInput ( String organization, String paymentType, String iuv ) {
		super ();
		this.setCodiceFiscaleEnte ( organization );
		this.paymentType = paymentType;
		this.setIuv ( iuv );
	}

	@Override public String toString () {
		return "GetDatiPagamentoChiamanteEsternoInput{" +
						"paymentType='" + paymentType + '\'' +
						'}';
	}
}

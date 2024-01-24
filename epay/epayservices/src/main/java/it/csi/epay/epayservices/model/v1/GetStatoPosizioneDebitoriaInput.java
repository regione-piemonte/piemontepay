/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model.v1;

import java.io.Serializable;


public class GetStatoPosizioneDebitoriaInput extends ChiamanteEsternoCommonInput implements Serializable {

	private static final long serialVersionUID = -4950859099544985095L;
	private String paymentType;

	public GetStatoPosizioneDebitoriaInput ( String organization, String paymentType, String iuv ) {
		super ();
		this.setCodiceFiscaleEnte ( organization );
		this.paymentType = paymentType;
		this.setIuv ( iuv );
	}

	public String getPaymentType () {
		return paymentType;
	}

	public void setPaymentType ( String paymentType ) {
		this.paymentType = paymentType;
	}

	@Override public String toString () {
		return "GetStatoPosizioneDebitoriaInput{" +
						"paymentType='" + paymentType + '\'' +
						'}';
	}
}

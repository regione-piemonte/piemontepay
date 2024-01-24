/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model.v1;

import java.io.Serializable;


public class PagamentoPerStampaAvvisoInput implements Serializable {

	private static final long serialVersionUID = -9091409868529412163L;

	private String organization;

	private String paymentType;

	private String iuv;

	private String codiceChiamante;

	public PagamentoPerStampaAvvisoInput ( String organization, String paymentType, String iuv, String codiceChiamante ) {
		this.organization = organization;
		this.paymentType = paymentType;
		this.iuv = iuv;
		this.codiceChiamante = codiceChiamante;
	}

	public String getOrganization () {
		return organization;
	}

	public void setOrganization ( String organization ) {
		this.organization = organization;
	}

	public String getPaymentType () {
		return paymentType;
	}

	public void setPaymentType ( String paymentType ) {
		this.paymentType = paymentType;
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public String getCodiceChiamante () {
		return codiceChiamante;
	}

	public void setCodiceChiamante ( String codiceChiamante ) {
		this.codiceChiamante = codiceChiamante;
	}

	@Override public String toString () {
		return "PagamentoPerStampaAvvisoInput{" +
						"organization='" + organization + '\'' +
						", paymentType='" + paymentType + '\'' +
						", iuv='" + iuv + '\'' +
						", codiceChiamante='" + codiceChiamante + '\'' +
						'}';
	}
}

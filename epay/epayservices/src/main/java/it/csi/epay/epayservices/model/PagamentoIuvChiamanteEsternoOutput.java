/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class PagamentoIuvChiamanteEsternoOutput extends AccessoChiamanteEsternoSincronoSplitOutput implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty ( "paymentUrl" )
	private String urlWisp;

	public String getUrlWisp () {
		return urlWisp;
	}

	public void setUrlWisp ( String urlWisp ) {
		this.urlWisp = urlWisp;
	}

	@Override
	public String toString () {
		return "PagamentoIuvChiamanteEsternoOutput ["
						+ ( urlWisp != null ? "urlWisp=" + urlWisp + ", " : "" )
						+ "]";
	}

}

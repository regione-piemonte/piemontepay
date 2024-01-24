/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


/**
 *
 */

public class AccessoChiamanteEsternoSincronoSplitOutput implements Serializable {

	private static final long serialVersionUID = 5470376675629396372L;

	@JsonProperty ( "iuv" )
	private String identificativoPagamento;

	private String codiceEsito;

	private String descrizioneEsito;

	public String getIdentificativoPagamento () {
		return identificativoPagamento;
	}

	public void setIdentificativoPagamento ( String identificativoPagamento ) {
		this.identificativoPagamento = identificativoPagamento;
	}

	public String getCodiceEsito () {
		return codiceEsito;
	}

	public void setCodiceEsito ( String codiceEsito ) {
		this.codiceEsito = codiceEsito;
	}

	public String getDescrizioneEsito () {
		return descrizioneEsito;
	}

	public void setDescrizioneEsito ( String descrizioneEsito ) {
		this.descrizioneEsito = descrizioneEsito;
	}

	@Override
	public String toString () {
		return "AccessoChiamanteEsternoSincronoSplitOutput [identificativoPagamento=" + identificativoPagamento + ", codiceEsito=" + codiceEsito
						+ ", descrizioneEsito=" + descrizioneEsito + "]";
	}

}

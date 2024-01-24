/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


@SuppressWarnings ( "unused" )
public class DatiSpecificiRiscossione implements Serializable {

	private static final long serialVersionUID = -6025250814354997185L;

	@JsonProperty ( "codice" )
	private String codice;

	@JsonProperty ( "descrizione" )
	private String descrizione;

	@JsonProperty ( "annoEsercizio" )
	private Integer annoEsercizio;

	@JsonProperty ( "annoAccertamento" )
	private Integer annoAccertamento;

	@JsonProperty ( "numeroAccertamento" )
	private String numeroAccertamento;

	@JsonProperty ( "codiceTributo" )
	private String codiceTributo;

	public String getCodice () {
		return codice;
	}

	public void setCodice ( String codice ) {
		this.codice = codice;
	}

	public String getDescrizione () {
		return descrizione;
	}

	public void setDescrizione ( String descrizione ) {
		this.descrizione = descrizione;
	}

	public Integer getAnnoEsercizio () {
		return annoEsercizio;
	}

	public void setAnnoEsercizio ( Integer annoEsercizio ) {
		this.annoEsercizio = annoEsercizio;
	}

	public Integer getAnnoAccertamento () {
		return annoAccertamento;
	}

	public void setAnnoAccertamento ( Integer annoAccertamento ) {
		this.annoAccertamento = annoAccertamento;
	}

	public String getNumeroAccertamento () {
		return numeroAccertamento;
	}

	public void setNumeroAccertamento ( String numeroAccertamento ) {
		this.numeroAccertamento = numeroAccertamento;
	}

	public String getCodiceTributo () {
		return codiceTributo;
	}

	public void setCodiceTributo ( String codiceTributo ) {
		this.codiceTributo = codiceTributo;
	}

}

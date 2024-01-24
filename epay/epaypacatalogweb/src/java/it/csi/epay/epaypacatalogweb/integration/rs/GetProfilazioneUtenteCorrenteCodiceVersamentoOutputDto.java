/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.rs;

import org.codehaus.jackson.annotate.JsonProperty;


public class GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto {


	@JsonProperty("codice")
	protected String codice;

	@JsonProperty("descrizione")
	protected String descrizione;

	@JsonProperty("id")
	protected Long id;

	@JsonProperty ( "flagMbPrimario" )
	protected Boolean flagMbPrimario;

	@JsonProperty ( "flagMbSecondario" )
	protected Boolean flagMbSecondario;

	public Boolean getFlagMbPrimario () {
		return flagMbPrimario;
	}

	public void setFlagMbPrimario ( Boolean flagMbPrimario ) {
		this.flagMbPrimario = flagMbPrimario;
	}

	public Boolean getFlagMbSecondario () {
		return flagMbSecondario;
	}

	public void setFlagMbSecondario ( Boolean flagMbSecondario ) {
		this.flagMbSecondario = flagMbSecondario;
	}

	/**
	 * Recupera il valore della propriet codice.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * Imposta il valore della propriet codice.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setCodice(String value) {
		this.codice = value;
	}

	/**
	 * Recupera il valore della propriet descrizione.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Imposta il valore della propriet descrizione.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setDescrizione(String value) {
		this.descrizione = value;
	}

	/**
	 * Recupera il valore della propriet id.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Long }
	 *     
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Imposta il valore della propriet id.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Long }
	 *     
	 */
	public void setId(Long value) {
		this.id = value;
	}

}

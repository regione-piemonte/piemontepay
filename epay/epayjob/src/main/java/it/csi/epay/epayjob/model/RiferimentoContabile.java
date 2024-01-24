/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
//import com.fasterxml.jackson.annotation.JsonProperty;

public class RiferimentoContabile implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("datoSpecificoRiscossione")
	private String datoSpecificoRiscossione;

	@JsonProperty("codiceTipologiaDatoSpecificoRiscossione")
	private String codiceTipologiaDatoSpecificoRiscossione;
	
	@JsonProperty("annoAccertamento")
	private String annoAccertamento;
	
	@JsonProperty("numAccertamento")
	private String numAccertamento;
		
	@JsonProperty("dataFineValidita")
	private String dataFineValidita;
	
	
	@JsonProperty("codiceVersamento")
	private CodiceVersamento codiceVersamento;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getDatoSpecificoRiscossione() {
		return datoSpecificoRiscossione;
	}

	public void setDatoSpecificoRiscossione(String datoSpecificoRiscossione) {
		this.datoSpecificoRiscossione = datoSpecificoRiscossione;
	}

	public String getAnnoAccertamento() {
		return annoAccertamento;
	}

	public void setAnnoAccertamento(String annoAccertamento) {
		this.annoAccertamento = annoAccertamento;
	}

	public String getNumAccertamento() {
		return numAccertamento;
	}

	public void setNumAccertamento(String numAccertamento) {
		this.numAccertamento = numAccertamento;
	}

	public String getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(String dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}
	
	
	public CodiceVersamento getCodiceVersamento() {
		return codiceVersamento;
	}

	public void setCodiceVersamento(CodiceVersamento codiceVersamento) {
		this.codiceVersamento = codiceVersamento;
	}

	public String getCodiceTipologiaDatoSpecificoRiscossione () {
		return codiceTipologiaDatoSpecificoRiscossione;
	}

	public void setCodiceTipologiaDatoSpecificoRiscossione ( String codiceTipologiaDatoSpecificoRiscossione ) {
		this.codiceTipologiaDatoSpecificoRiscossione = codiceTipologiaDatoSpecificoRiscossione;
	}
}

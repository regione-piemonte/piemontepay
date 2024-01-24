/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;

public class SingoloComponenteImportoOutput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4961411474416619319L;
	
	private String datiSpecificiRiscossione;
	private Integer annoAccertamento;
	private String numeroAccertamento;
	private String importoSingoloVersamento;
	public String getDatiSpecificiRiscossione() {
		return datiSpecificiRiscossione;
	}
	public void setDatiSpecificiRiscossione(String datiSpecificiRiscossione) {
		this.datiSpecificiRiscossione = datiSpecificiRiscossione;
	}
	public Integer getAnnoAccertamento() {
		return annoAccertamento;
	}
	public void setAnnoAccertamento(Integer annoAccertamento) {
		this.annoAccertamento = annoAccertamento;
	}
	public String getNumeroAccertamento() {
		return numeroAccertamento;
	}
	public void setNumeroAccertamento(String numeroAccertamento) {
		this.numeroAccertamento = numeroAccertamento;
	}
	public String getImportoSingoloVersamento() {
		return importoSingoloVersamento;
	}
	public void setImportoSingoloVersamento(String importoSingoloVersamento) {
		this.importoSingoloVersamento = importoSingoloVersamento;
	}

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;

public class ComponentiImportoInput implements Serializable {


	private static final long serialVersionUID = 7003306561676631600L;
	
	private String codiceFiscaleEnte;
	private String codiceVersamento;
	private String iuvDatiSingoloPagamento;
	private String importoDatiSingoloPagamento;
	private String transactionId;
	private String datiSpecificiRiscossione;
	private String esitoPagamento;
	private String anagraficaPagatore;
	private String codiceFiscalePagatore;

	public String getCodiceFiscaleEnte() {
		return codiceFiscaleEnte;
	}

	public void setCodiceFiscaleEnte(String codiceFiscaleEnte) {
		this.codiceFiscaleEnte = codiceFiscaleEnte;
	}

	public String getCodiceVersamento() {
		return codiceVersamento;
	}

	public void setCodiceVersamento(String codiceVersamento) {
		this.codiceVersamento = codiceVersamento;
	}

	public String getIuvDatiSingoloPagamento() {
		return iuvDatiSingoloPagamento;
	}

	public void setIuvDatiSingoloPagamento(String iuvDatiSingoloPagamento) {
		this.iuvDatiSingoloPagamento = iuvDatiSingoloPagamento;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getDatiSpecificiRiscossione() {
		return datiSpecificiRiscossione;
	}

	public void setDatiSpecificiRiscossione(String datiSpecificiRiscossione) {
		this.datiSpecificiRiscossione = datiSpecificiRiscossione;
	}

	public String getEsitoPagamento() {
		return esitoPagamento;
	}

	public void setEsitoPagamento(String esitoPagamento) {
		this.esitoPagamento = esitoPagamento;
	}

	public String getAnagraficaPagatore() {
		return anagraficaPagatore;
	}

	public void setAnagraficaPagatore(String anagraficaPagatore) {
		this.anagraficaPagatore = anagraficaPagatore;
	}

	public String getCodiceFiscalePagatore() {
		return codiceFiscalePagatore;
	}

	public void setCodiceFiscalePagatore(String codiceFiscalePagatore) {
		this.codiceFiscalePagatore = codiceFiscalePagatore;
	}

	public String getImportoDatiSingoloPagamento() {
		return importoDatiSingoloPagamento;
	}

	public void setImportoDatiSingoloPagamento(String importoDatiSingoloPagamento) {
		this.importoDatiSingoloPagamento = importoDatiSingoloPagamento;
	}

}

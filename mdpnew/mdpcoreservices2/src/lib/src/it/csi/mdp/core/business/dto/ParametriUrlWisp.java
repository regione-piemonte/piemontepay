/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

public class ParametriUrlWisp {

	private String stornoPagamento;
	private String bolloDigitale;
	private String terzoModelloPagamento;
	private String idPsp;
	private String tipoVersamento;
	private String modello2;
	private String urlBackWisp;
	private String urlReturnWisp;
	private String codiceLingua;

	public String getStornoPagamento() {
		return stornoPagamento;
	}

	public void setStornoPagamento(String stornoPagamento) {
		this.stornoPagamento = stornoPagamento;
	}

	public String getBolloDigitale() {
		return bolloDigitale;
	}

	public void setBolloDigitale(String bolloDigitale) {
		this.bolloDigitale = bolloDigitale;
	}

	public String getTerzoModelloPagamento() {
		return terzoModelloPagamento;
	}

	public void setTerzoModelloPagamento(String terzoModelloPagamento) {
		this.terzoModelloPagamento = terzoModelloPagamento;
	}

	public String getIdPsp() {
		return idPsp;
	}

	public void setIdPsp(String idPsp) {
		this.idPsp = idPsp;
	}

	public String getTipoVersamento() {
		return tipoVersamento;
	}

	public void setTipoVersamento(String tipoVersamento) {
		this.tipoVersamento = tipoVersamento;
	}

	public String getUrlBackWisp() {
		return urlBackWisp;
	}

	public void setUrlBackWisp(String urlBackWisp) {
		this.urlBackWisp = urlBackWisp;
	}

	public String getUrlReturnWisp() {
		return urlReturnWisp;
	}

	public void setUrlReturnWisp(String urlReturnWisp) {
		this.urlReturnWisp = urlReturnWisp;
	}

	public String getModello2() {
		return modello2;
	}

	public void setModello2(String modello2) {
		this.modello2 = modello2;
	}

	public String getCodiceLingua() {
		return codiceLingua;
	}

	public void setCodiceLingua(String codiceLingua) {
		this.codiceLingua = codiceLingua;
	}
	
	
}

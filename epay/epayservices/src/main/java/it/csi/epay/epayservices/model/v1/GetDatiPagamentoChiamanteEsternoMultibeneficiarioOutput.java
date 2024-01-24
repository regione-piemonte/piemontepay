/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;


public class GetDatiPagamentoChiamanteEsternoMultibeneficiarioOutput implements Serializable {

	private static final long serialVersionUID = -316592385489300418L;

	@JsonProperty ( "enteBeneficiario" )
	private String enteBeneficiario;

	@JsonProperty ( "codiceFiscaleEnteBeneficiario" )
	private String codiceFiscaleEnteBeneficiario;

	@JsonProperty ( "tipologiaVersamento" )
	private String tipologiaVersamento;

	@JsonProperty ( "importoPagato" )
	private BigDecimal importoPagato;

	public String getEnteBeneficiario () {
		return enteBeneficiario;
	}

	public void setEnteBeneficiario ( String enteBeneficiario ) {
		this.enteBeneficiario = enteBeneficiario;
	}

	public String getCodiceFiscaleEnteBeneficiario () {
		return codiceFiscaleEnteBeneficiario;
	}

	public void setCodiceFiscaleEnteBeneficiario ( String codiceFiscaleEnteBeneficiario ) {
		this.codiceFiscaleEnteBeneficiario = codiceFiscaleEnteBeneficiario;
	}

	public String getTipologiaVersamento () {
		return tipologiaVersamento;
	}

	public void setTipologiaVersamento ( String tipologiaVersamento ) {
		this.tipologiaVersamento = tipologiaVersamento;
	}

	public BigDecimal getImportoPagato () {
		return importoPagato;
	}

	public void setImportoPagato ( BigDecimal importoPagato ) {
		this.importoPagato = importoPagato;
	}

	@Override public String toString () {
		return "GetDatiPagamentoChiamanteEsternoMultibeneficiarioOutput{" +
						"enteBeneficiario='" + enteBeneficiario + '\'' +
						", codiceFiscaleEnteBeneficiario='" + codiceFiscaleEnteBeneficiario + '\'' +
						", tipologiaVersamento='" + tipologiaVersamento + '\'' +
						", importoPagato=" + importoPagato +
						'}';
	}
}

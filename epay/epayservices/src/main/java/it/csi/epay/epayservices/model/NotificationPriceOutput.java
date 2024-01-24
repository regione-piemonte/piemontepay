/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationPriceOutput implements Serializable {

	private static final long serialVersionUID = 8796670046684528189L;

	@JsonProperty("importoTotale")
	private BigDecimal importoTotale;

	@JsonProperty("importoTotaleEntePrimario")
	private BigDecimal importoTotaleEntePrimario;;

	@JsonProperty("importoTotaleEntiSecondari")
	private BigDecimal importoTotaleEntiSecondari;

	@JsonProperty("importo")
	private BigDecimal importo;

	@JsonProperty("requiresCostUpdate")
	private Boolean requiresCostUpdate;

	@JsonProperty("status")
	private String status;

	@JsonProperty("code")
	private String code;

	@JsonProperty("detail")
	private String detail;

	@JsonProperty("componentiPagamento")
	private List<PaymentComponent> componentiPagamento = null;

	@JsonProperty("componentiPagamentoEntiSecondari")
	private List<PaymentComponentMultipayee> componentiPagamentoEntiSecondari = null;
	
	

	public BigDecimal getImportoTotale() {
		return importoTotale;
	}

	public void setImportoTotale(BigDecimal importoTotale) {
		this.importoTotale = importoTotale;
	}

	public BigDecimal getImportoTotaleEntePrimario() {
		return importoTotaleEntePrimario;
	}

	public void setImportoTotaleEntePrimario(BigDecimal importoTotaleEntePrimario) {
		this.importoTotaleEntePrimario = importoTotaleEntePrimario;
	}

	public BigDecimal getImportoTotaleEntiSecondari() {
		return importoTotaleEntiSecondari;
	}

	public void setImportoTotaleEntiSecondari(BigDecimal importoTotaleEntiSecondari) {
		this.importoTotaleEntiSecondari = importoTotaleEntiSecondari;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public Boolean getRequiresCostUpdate() {
		return requiresCostUpdate;
	}

	public void setRequiresCostUpdate(Boolean requiresCostUpdate) {
		this.requiresCostUpdate = requiresCostUpdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public List<PaymentComponent> getComponentiPagamento() {
		return componentiPagamento;
	}

	public void setComponentiPagamento(List<PaymentComponent> componentiPagamento) {
		this.componentiPagamento = componentiPagamento;
	}

	public List<PaymentComponentMultipayee> getComponentiPagamentoEntiSecondari() {
		return componentiPagamentoEntiSecondari;
	}

	public void setComponentiPagamentoEntiSecondari(List<PaymentComponentMultipayee> componentiPagamentoEntiSecondari) {
		this.componentiPagamentoEntiSecondari = componentiPagamentoEntiSecondari;
	}

	/**
	 * Componenti di pagamento
	 */
	public static class PaymentComponent implements Serializable {

		private static final long serialVersionUID = 4020061600670930083L;

		@JsonProperty("annoAccertamento")
		private Integer annoAccertamento;

		@JsonProperty("causale")
		private String causale;

		@JsonProperty("importo")
		private BigDecimal importo;

		@JsonProperty("numeroAccertamento")
		private String numeroAccertamento;

		public Integer getAnnoAccertamento() {
			return annoAccertamento;
		}

		public void setAnnoAccertamento(Integer annoAccertamento) {
			this.annoAccertamento = annoAccertamento;
		}

		public String getCausale() {
			return causale;
		}

		public void setCausale(String causale) {
			this.causale = causale;
		}

		public BigDecimal getImporto() {
			return importo;
		}

		public void setImporto(BigDecimal importo) {
			this.importo = importo;
		}

		public String getNumeroAccertamento() {
			return numeroAccertamento;
		}

		public void setNumeroAccertamento(String numeroAccertamento) {
			this.numeroAccertamento = numeroAccertamento;
		}
	}

	/**
	 * Componenti di pagamento in caso di presenza multibeneficiario
	 */
	public static class PaymentComponentMultipayee extends PaymentComponent implements Serializable {

		private static final long serialVersionUID = -2457360257587377443L;

		@JsonProperty("codiceFiscaleEnte")
		private String codiceFiscaleEnte;

		@JsonProperty("codiceVersamento")
		private String codiceVersamento;

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
	}

}

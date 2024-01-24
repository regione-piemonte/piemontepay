/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;

import it.csi.epay.epaypaweb.enumeration.TipoReportEnum;
import javax.annotation.Generated;

public class ReportFilterDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idEnte;
	private String codFiscaleEnte;
	private Long idUtente;
	private String codFiscaleUtente;
	
	private TipoReportEnum tipoReportEnum;

	@Generated("SparkTools")
	private ReportFilterDto(Builder builder) {
		this.idEnte = builder.idEnte;
		this.codFiscaleEnte = builder.codFiscaleEnte;
		this.idUtente = builder.idUtente;
		this.codFiscaleUtente = builder.codFiscaleUtente;
		this.tipoReportEnum = builder.tipoReportEnum;
	}

	public ReportFilterDto() {
		super();
	}

	public TipoReportEnum getTipoReportEnum() {
		return tipoReportEnum;
	}

	public void setTipoReportEnum(TipoReportEnum tipoReportEnum) {
		this.tipoReportEnum = tipoReportEnum;
	}

	public Integer getIdEnte() {
		return idEnte;
	}

	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}

	public Long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	public String getCodFiscaleEnte() {
		return codFiscaleEnte;
	}

	public void setCodFiscaleEnte(String codFiscaleEnte) {
		this.codFiscaleEnte = codFiscaleEnte;
	}

	public String getCodFiscaleUtente() {
		return codFiscaleUtente;
	}

	public void setCodFiscaleUtente(String codFiscaleUtente) {
		this.codFiscaleUtente = codFiscaleUtente;
	}

	/**
	 * Creates builder to build {@link ReportFilterDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link ReportFilterDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Integer idEnte;
		private String codFiscaleEnte;
		private Long idUtente;
		private String codFiscaleUtente;
		private TipoReportEnum tipoReportEnum;

		private Builder() {
		}

		public Builder withIdEnte(Integer idEnte) {
			this.idEnte = idEnte;
			return this;
		}

		public Builder withCodFiscaleEnte(String codFiscaleEnte) {
			this.codFiscaleEnte = codFiscaleEnte;
			return this;
		}

		public Builder withIdUtente(Long idUtente) {
			this.idUtente = idUtente;
			return this;
		}

		public Builder withCodFiscaleUtente(String codFiscaleUtente) {
			this.codFiscaleUtente = codFiscaleUtente;
			return this;
		}

		public Builder withTipoReportEnum(TipoReportEnum tipoReportEnum) {
			this.tipoReportEnum = tipoReportEnum;
			return this;
		}

		public ReportFilterDto build() {
			return new ReportFilterDto(this);
		}
	}

	
}

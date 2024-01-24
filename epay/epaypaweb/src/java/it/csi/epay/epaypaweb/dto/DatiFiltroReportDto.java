/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;

import it.csi.epay.epaypaweb.enumeration.NomeFiltroReportEnum;
import it.csi.epay.epaypaweb.enumeration.TipoCampoFiltroEnum;
import javax.annotation.Generated;

public class DatiFiltroReportDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private NomeFiltroReportEnum nomeFiltro;
	private String valore;
	private TipoCampoFiltroEnum tipoCampoFiltro;

	@Generated("SparkTools")
	private DatiFiltroReportDto(Builder builder) {
		this.id = builder.id;
		this.nomeFiltro = builder.nomeFiltro;
		this.valore = builder.valore;
		this.tipoCampoFiltro = builder.tipoCampoFiltro;
	}
	
	public DatiFiltroReportDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NomeFiltroReportEnum getNomeFiltro() {
		return nomeFiltro;
	}

	public void setNomeFiltro(NomeFiltroReportEnum nomeFiltro) {
		this.nomeFiltro = nomeFiltro;
	}

	public String getValore() {
		return valore;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}

	public TipoCampoFiltroEnum getTipoCampoFiltro() {
		return tipoCampoFiltro;
	}

	public void setTipoCampoFiltro(TipoCampoFiltroEnum tipoCampoFiltro) {
		this.tipoCampoFiltro = tipoCampoFiltro;
	}

	/**
	 * Creates builder to build {@link DatiFiltroReportDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link DatiFiltroReportDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Long id;
		private NomeFiltroReportEnum nomeFiltro;
		private String valore;
		private TipoCampoFiltroEnum tipoCampoFiltro;

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withNomeFiltro(NomeFiltroReportEnum nomeFiltro) {
			this.nomeFiltro = nomeFiltro;
			return this;
		}

		public Builder withValore(String valore) {
			this.valore = valore;
			return this;
		}

		public Builder withTipoCampoFiltro(TipoCampoFiltroEnum tipoCampoFiltro) {
			this.tipoCampoFiltro = tipoCampoFiltro;
			return this;
		}

		public DatiFiltroReportDto build() {
			return new DatiFiltroReportDto(this);
		}
	}

}

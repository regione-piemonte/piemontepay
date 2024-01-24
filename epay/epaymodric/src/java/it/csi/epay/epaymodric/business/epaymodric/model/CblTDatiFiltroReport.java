/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



/**
 * The persistent class for the cbl_t_dati_filtro_report database table.
 * 
 */
@Entity
@Table(name="cbl_t_dati_filtro_report")
@NamedQuery(name="CblTDatiFiltroReport.findAll", query="SELECT c FROM CblTDatiFiltroReport c")
public class CblTDatiFiltroReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CBL_T_DATI_FILTRO_REPORT_ID_GENERATOR", sequenceName="epaymodric.CBL_T_DATI_FILTRO_REPORT_ID_SEQ", schema="epaymodric", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CBL_T_DATI_FILTRO_REPORT_ID_GENERATOR")
	private Long id;

	@Column(name="nome_filtro")
	private String nomeFiltro;

	private String valore;

	//bi-directional many-to-one association to CblDTipoCampoFiltro
	@ManyToOne
	@JoinColumn(name="id_tipo")
	private CblDTipoCampoFiltro cblDTipoCampoFiltro;

	//bi-directional many-to-one association to CblTReport
	@ManyToOne
	@JoinColumn(name="id_report")
	private CblTReport cblTReport;
	
	
	@Generated("SparkTools")
	private CblTDatiFiltroReport(Builder builder) {
		this.id = builder.id;
		this.nomeFiltro = builder.nomeFiltro;
		this.valore = builder.valore;
		this.cblDTipoCampoFiltro = builder.cblDTipoCampoFiltro;
		this.cblTReport = builder.cblTReport;
	}
	
	/**
	 * Creates builder to build {@link CblTDatiFiltroReport}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	public CblTDatiFiltroReport() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFiltro() {
		return this.nomeFiltro;
	}

	public void setNomeFiltro(String nomeFiltro) {
		this.nomeFiltro = nomeFiltro;
	}

	public String getValore() {
		return this.valore;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}

	public CblDTipoCampoFiltro getCblDTipoCampoFiltro() {
		return this.cblDTipoCampoFiltro;
	}

	public void setCblDTipoCampoFiltro(CblDTipoCampoFiltro cblDTipoCampoFiltro) {
		this.cblDTipoCampoFiltro = cblDTipoCampoFiltro;
	}

	public CblTReport getCblTReport() {
		return this.cblTReport;
	}

	public void setCblTReport(CblTReport cblTReport) {
		this.cblTReport = cblTReport;
	}
	
	/**
	 * Builder to build {@link EpaypaTDatiFiltroReport}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Long id;
		private String nomeFiltro;
		private String valore;
		private CblDTipoCampoFiltro cblDTipoCampoFiltro;
		private CblTReport cblTReport;

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withNomeFiltro(String nomeFiltro) {
			this.nomeFiltro = nomeFiltro;
			return this;
		}

		public Builder withValore(String valore) {
			this.valore = valore;
			return this;
		}

		public Builder withCblDTipoCampoFiltro(CblDTipoCampoFiltro cblDTipoCampoFiltro) {
			this.cblDTipoCampoFiltro = cblDTipoCampoFiltro;
			return this;
		}

		public Builder withCblTReport(CblTReport cblTReport) {
			this.cblTReport = cblTReport;
			return this;
		}

		public CblTDatiFiltroReport build() {
			return new CblTDatiFiltroReport(this);
		}
	}
	

}

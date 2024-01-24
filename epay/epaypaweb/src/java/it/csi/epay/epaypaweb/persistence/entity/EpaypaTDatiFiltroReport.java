/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.annotation.Generated;

@Entity
@Table ( name = "epaypa_t_dati_filtro_report" )
public class EpaypaTDatiFiltroReport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @SequenceGenerator ( name = "EPAYPA_T_DATI_FILTRO_REPORT_ID_GENERATOR", sequenceName = "EPAYPA_T_DATI_FILTRO_REPORT_ID_SEQ", allocationSize = 1 )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAYPA_T_DATI_FILTRO_REPORT_ID_GENERATOR" )
    private Long id;
	
	@Column ( name = "nome_filtro" )
	private String nomeFiltro;
	
	private String valore;
	
	
	//bi-directional many-to-one association to EpaypaDTipoCampoFiltro
    @ManyToOne
    @JoinColumn ( name = "id_tipo" )
	private EpaypaDTipoCampoFiltro epaypaDTipoCampoFiltro;
    
    //uni-directional many-to-one association to EpaypaTRepor
  	@ManyToOne
  	@JoinColumn(name="id_report")
    private EpaypaTReport epaypaTReport;

  	public EpaypaTDatiFiltroReport () {
  		super();
  	}

	@Generated("SparkTools")
	private EpaypaTDatiFiltroReport(Builder builder) {
		this.id = builder.id;
		this.nomeFiltro = builder.nomeFiltro;
		this.valore = builder.valore;
		this.epaypaDTipoCampoFiltro = builder.epaypaDTipoCampoFiltro;
		this.epaypaTReport = builder.epaypaTReport;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNomeFiltro() {
		return nomeFiltro;
	}


	public void setNomeFiltro(String nomeFiltro) {
		this.nomeFiltro = nomeFiltro;
	}


	public String getValore() {
		return valore;
	}


	public void setValore(String valore) {
		this.valore = valore;
	}


	public EpaypaDTipoCampoFiltro getEpaypaDTipoCampoFiltro() {
		return epaypaDTipoCampoFiltro;
	}


	public void setEpaypaDTipoCampoFiltro(EpaypaDTipoCampoFiltro epaypaDTipoCampoFiltro) {
		this.epaypaDTipoCampoFiltro = epaypaDTipoCampoFiltro;
	}


	public EpaypaTReport getEpaypaTReport() {
		return epaypaTReport;
	}


	public void setEpaypaTReport(EpaypaTReport epaypaTReport) {
		this.epaypaTReport = epaypaTReport;
	}


	/**
	 * Creates builder to build {@link EpaypaTDatiFiltroReport}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}


	/**
	 * Builder to build {@link EpaypaTDatiFiltroReport}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Long id;
		private String nomeFiltro;
		private String valore;
		private EpaypaDTipoCampoFiltro epaypaDTipoCampoFiltro;
		private EpaypaTReport epaypaTReport;

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

		public Builder withEpaypaDTipoCampoFiltro(EpaypaDTipoCampoFiltro epaypaDTipoCampoFiltro) {
			this.epaypaDTipoCampoFiltro = epaypaDTipoCampoFiltro;
			return this;
		}

		public Builder withEpaypaTReport(EpaypaTReport epaypaTReport) {
			this.epaypaTReport = epaypaTReport;
			return this;
		}

		public EpaypaTDatiFiltroReport build() {
			return new EpaypaTDatiFiltroReport(this);
		}
	}
}

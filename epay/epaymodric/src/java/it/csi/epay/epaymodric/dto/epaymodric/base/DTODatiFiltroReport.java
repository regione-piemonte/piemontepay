/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.base;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.interfacews.epaymodric.enums.NomeFiltroReportEnum;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.TipoCampoFiltroEnum;



@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoDatiFiltroReport" )
public class DTODatiFiltroReport implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    private String idReport;

	private NomeFiltroReportEnum nomeFiltro;
	
	private String idTipo;
	
	private TipoCampoFiltroEnum tipoCampoFiltro;
	

	private String valore;
	
	@Generated("SparkTools")
	private DTODatiFiltroReport(Builder builder) {
		this.id = builder.id;
		this.nomeFiltro = builder.nomeFiltro;
		this.valore = builder.valore;
		this.tipoCampoFiltro = builder.tipoCampoFiltro;
	}
	
	private DTODatiFiltroReport() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getIdReport() {
		return idReport;
	}


	public void setIdReport(String idReport) {
		this.idReport = idReport;
	}



	public NomeFiltroReportEnum getNomeFiltro() {
		return nomeFiltro;
	}


	public void setNomeFiltro(NomeFiltroReportEnum nomeFiltro) {
		this.nomeFiltro = nomeFiltro;
	}


	public TipoCampoFiltroEnum getTipoCampoFiltro() {
		return tipoCampoFiltro;
	}


	public void setTipoCampoFiltro(TipoCampoFiltroEnum tipoCampoFiltro) {
		this.tipoCampoFiltro = tipoCampoFiltro;
	}


	public String getIdTipo() {
		return idTipo;
	}


	public void setIdTipo(String idTipo) {
		this.idTipo = idTipo;
	}


	public String getValore() {
		return valore;
	}


	public void setValore(String valore) {
		this.valore = valore;
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

		public DTODatiFiltroReport build() {
			return new DTODatiFiltroReport(this);
		}
	}

	
	

   

}

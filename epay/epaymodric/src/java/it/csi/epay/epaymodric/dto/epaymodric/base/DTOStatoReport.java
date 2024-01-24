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



@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoStatoReport" )
public class DTOStatoReport implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;

	private String codice;

	private String descrizione;
	
	@Generated("SparkTools")
	private DTOStatoReport(Builder builder) {
		this.id = builder.id;
		this.codice = builder.codice;
		this.descrizione = builder.descrizione;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
//	 * Builder to build {@link DatiFiltroReportDto}.
//	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Integer id;
		private String codice;
		private String descrizione;

		private Builder() {
		}

		public Builder withId(Integer id) {
			this.id = id;
			return this;
		}

		public Builder withCodice(String codice) {
			this.codice = codice;
			return this;
		}

		public Builder withDescrizione(String descrizione) {
			this.descrizione = descrizione;
			return this;
		}


		public DTOStatoReport build() {
			return new DTOStatoReport(this);
		}
	}
    
   

}

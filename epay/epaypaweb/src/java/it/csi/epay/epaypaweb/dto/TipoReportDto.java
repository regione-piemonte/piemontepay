/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import javax.annotation.Generated;

public class TipoReportDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -141957210320498048L;

	private Integer id;

    private String codice;

    private String descrizione;

	@Generated("SparkTools")
	private TipoReportDto(Builder builder) {
		this.id = builder.id;
		this.codice = builder.codice;
		this.descrizione = builder.descrizione;
	}

	public TipoReportDto() {
		super();
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
	 * Creates builder to build {@link TipoReportDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link TipoReportDto}.
	 */
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

		public TipoReportDto build() {
			return new TipoReportDto(this);
		}
	}

}

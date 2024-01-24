/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import javax.annotation.Generated;

public class TipoCampoFiltro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7251970564391916893L;

	
	private Integer id;

    private String codice;

    private String descrizione;

	@Generated("SparkTools")
	private TipoCampoFiltro(Builder builder) {
		this.id = builder.id;
		this.codice = builder.codice;
		this.descrizione = builder.descrizione;
	}

	public TipoCampoFiltro() {
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
	 * Creates builder to build {@link TipoCampoFiltro}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link TipoCampoFiltro}.
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

		public TipoCampoFiltro build() {
			return new TipoCampoFiltro(this);
		}
	}
}

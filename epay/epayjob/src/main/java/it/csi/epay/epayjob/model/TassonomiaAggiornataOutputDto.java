/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class TassonomiaAggiornataOutputDto implements Serializable {

	private static final long serialVersionUID = 6703812915198495224L;

	@JsonProperty ( "id" )
	private Long id;

	@JsonProperty ( "tipoServizio" )
	private String tipoServizio;

	@JsonProperty ( "datiSpecificiIncasso" )
	private String datiSpecificiIncasso;

	public Long getId () {
		return id;
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public String getTipoServizio () {
		return tipoServizio;
	}

	public void setTipoServizio ( String tipoServizio ) {
		this.tipoServizio = tipoServizio;
	}

	public String getDatiSpecificiIncasso () {
		return datiSpecificiIncasso;
	}

	public void setDatiSpecificiIncasso ( String datiSpecificiIncasso ) {
		this.datiSpecificiIncasso = datiSpecificiIncasso;
	}
}

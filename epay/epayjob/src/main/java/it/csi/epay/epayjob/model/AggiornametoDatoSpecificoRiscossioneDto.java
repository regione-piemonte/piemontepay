/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class AggiornametoDatoSpecificoRiscossioneDto implements Serializable {

	private static final long serialVersionUID = 2459275717404110489L;

	@JsonProperty ( "idTassonomia" )
	private Long idTassonomia;

	@JsonProperty ( "tassonomiaEsistente" )
	private String tassonomiaEsistente;

	@JsonProperty ( "tassonomiaNuova" )
	private String tassonomiaNuova;

	public AggiornametoDatoSpecificoRiscossioneDto ( Long idTassonomia, String tassonomiaEsistente, String tassonomiaNuova ) {
		this.idTassonomia = idTassonomia;
		this.tassonomiaEsistente = tassonomiaEsistente;
		this.tassonomiaNuova = tassonomiaNuova;
	}

	public Long getIdTassonomia () {
		return idTassonomia;
	}

	public void setIdTassonomia ( Long idTassonomia ) {
		this.idTassonomia = idTassonomia;
	}

	public String getTassonomiaEsistente () {
		return tassonomiaEsistente;
	}

	public void setTassonomiaEsistente ( String tassonomiaEsistente ) {
		this.tassonomiaEsistente = tassonomiaEsistente;
	}

	public String getTassonomiaNuova () {
		return tassonomiaNuova;
	}

	public void setTassonomiaNuova ( String tassonomiaNuova ) {
		this.tassonomiaNuova = tassonomiaNuova;
	}
}

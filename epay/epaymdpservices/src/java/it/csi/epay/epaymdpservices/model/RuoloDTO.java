/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymdpservices.model;

import java.io.Serializable;

public class RuoloDTO implements Serializable{

	private static final long serialVersionUID = 322346999527212168L;

	private Integer idRuolo;
	private String descRuolo;
	
	public Integer getIdRuolo() {
		return idRuolo;
	}
	
	public void setIdRuolo(Integer idRuolo) {
		this.idRuolo = idRuolo;
	}
	
	public String getDescRuolo() {
		return descRuolo;
	}
	
	public void setDescRuolo(String descRuolo) {
		this.descRuolo = descRuolo;
	}
}

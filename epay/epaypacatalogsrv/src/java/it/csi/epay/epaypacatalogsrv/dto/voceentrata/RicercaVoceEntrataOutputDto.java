/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.voceentrata;

import java.io.Serializable;

public class RicercaVoceEntrataOutputDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String codice;
	private String descrizione;
	private String codiceMacrotipo;
	private String codiceTematica;
	private String descrizioneMacrotipo;
	private String descrizioneTematica;

	public String getDescrizioneMacrotipo() {
		return descrizioneMacrotipo;
	}

	public void setDescrizioneMacrotipo(String descrizioneMacrotipo) {
		this.descrizioneMacrotipo = descrizioneMacrotipo;
	}

	public String getDescrizioneTematica() {
		return descrizioneTematica;
	}

	public void setDescrizioneTematica(String descrizioneTematica) {
		this.descrizioneTematica = descrizioneTematica;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getCodiceMacrotipo() {
		return codiceMacrotipo;
	}

	public void setCodiceMacrotipo(String codiceMacrotipo) {
		this.codiceMacrotipo = codiceMacrotipo;
	}

	public String getCodiceTematica() {
		return codiceTematica;
	}

	public void setCodiceTematica(String codiceTematica) {
		this.codiceTematica = codiceTematica;
	}

}

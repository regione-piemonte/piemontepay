/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the cbl_d_categoria_iuv database table.
 * 
 */
@Entity
@Table(name="cbl_d_categoria_iuv")
@NamedQuery(name="CblDCategoriaIuv.findAll", query="SELECT c FROM CblDCategoriaIuv c")
public class CblDCategoriaIuv implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String codice;

	private String descrizione;

	public CblDCategoriaIuv() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}

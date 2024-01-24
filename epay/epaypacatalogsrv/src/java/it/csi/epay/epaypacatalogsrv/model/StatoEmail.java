/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaycat_d_stato_email database table.
 * 
 */
@Entity
@Table(name="epaycat_d_stato_email")
@NamedQuery(name="StatoEmail.findAll", query="SELECT s FROM StatoEmail s")
public class StatoEmail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String codice;

	private String descrizione;

	public StatoEmail() {
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

	@Override
	public String toString() {
		return "StatoEmail [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + "]";
	}

}

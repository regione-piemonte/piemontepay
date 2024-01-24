/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity.cooppec;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaycat_d_modalita_integrazione database table.
 *
 */
@Entity
@Table(name="epaypa_d_modalita_integrazione")
@NamedQuery(name="EpaypaDModalitaIntegrazione.findAll", query="SELECT m FROM EpaypaDModalitaIntegrazione m")
public class EpaypaDModalitaIntegrazione implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	private Integer id;

	private String codice;

	private String descrizione;

	public EpaypaDModalitaIntegrazione() {
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

}

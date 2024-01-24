/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaypa_r_utente_ruolo database table.
 * 
 */
@Entity
@Table(schema="epaypa", name="epaypa_r_utente_ruolo")
@NamedQuery(name="EpaypaRUtenteRuolo.findAll", query="SELECT e FROM EpaypaRUtenteRuolo e")
public class EpaypaRUtenteRuolo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EpaypaRUtenteRuoloPK id;

	public EpaypaRUtenteRuolo() {
	}

	public EpaypaRUtenteRuoloPK getId() {
		return this.id;
	}

	public void setId(EpaypaRUtenteRuoloPK id) {
		this.id = id;
	}

}

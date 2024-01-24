/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaypa_r_utente_profilo database table.
 * 
 */
@Entity
@Table(schema="epaypa", name="epaypa_r_utente_profilo")
@NamedQuery(name="EpaypaRUtenteProfilo.findAll", query="SELECT e FROM EpaypaRUtenteProfilo e")
public class EpaypaRUtenteProfilo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EpaypaRUtenteProfiloPK id;

	public EpaypaRUtenteProfilo() {
	}

	public EpaypaRUtenteProfiloPK getId() {
		return this.id;
	}

	public void setId(EpaypaRUtenteProfiloPK id) {
		this.id = id;
	}

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pdpstatiesteri database table.
 * 
 */
@Entity
@NamedQuery(name="Pdpstatiesteri.findAll", query="SELECT p FROM Pdpstatiesteri p")
public class Pdpstatiesteri implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PDPSTATIESTERI_ID_GENERATOR", sequenceName="PDPSTATIESTERI_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PDPSTATIESTERI_ID_GENERATOR")
	private long id;

	private String stato;

	public Pdpstatiesteri() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

}

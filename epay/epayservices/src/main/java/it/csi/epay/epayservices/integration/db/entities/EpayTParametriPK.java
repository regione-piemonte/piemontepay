/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the epay_t_parametri database table.
 * 
 */
@Embeddable
public class EpayTParametriPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=100)
	private String gruppo;

	@Column(unique=true, nullable=false, length=100)
	private String codice;

	public EpayTParametriPK() {
	}
	public String getGruppo() {
		return this.gruppo;
	}
	public void setGruppo(String gruppo) {
		this.gruppo = gruppo;
	}
	public String getCodice() {
		return this.codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EpayTParametriPK)) {
			return false;
		}
		EpayTParametriPK castOther = (EpayTParametriPK)other;
		return 
			this.gruppo.equals(castOther.gruppo)
			&& this.codice.equals(castOther.codice);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.gruppo.hashCode();
		hash = hash * prime + this.codice.hashCode();
		
		return hash;
	}
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the iuv_attribute database table.
 * 
 */
@Embeddable
public class IuvAttributePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ente_id", insertable=false, updatable=false)
	private String enteId;

	@Temporal(TemporalType.DATE)
	@Column(name="data_validita")
	private java.util.Date dataValidita;

	public IuvAttributePK() {
	}
	public String getEnteId() {
		return this.enteId;
	}
	public void setEnteId(String enteId) {
		this.enteId = enteId;
	}
	public java.util.Date getDataValidita() {
		return this.dataValidita;
	}
	public void setDataValidita(java.util.Date dataValidita) {
		this.dataValidita = dataValidita;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof IuvAttributePK)) {
			return false;
		}
		IuvAttributePK castOther = (IuvAttributePK)other;
		return 
			this.enteId.equals(castOther.enteId)
			&& this.dataValidita.equals(castOther.dataValidita);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.enteId.hashCode();
		hash = hash * prime + this.dataValidita.hashCode();
		
		return hash;
	}
}

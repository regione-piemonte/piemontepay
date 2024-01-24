/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the icicodiciimm database table.
 * 
 */
@Embeddable
public class IcicodiciimmPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String applicationid;

	private String codiceimm;

	public IcicodiciimmPK() {
	}
	public String getApplicationid() {
		return this.applicationid;
	}
	public void setApplicationid(String applicationid) {
		this.applicationid = applicationid;
	}
	public String getCodiceimm() {
		return this.codiceimm;
	}
	public void setCodiceimm(String codiceimm) {
		this.codiceimm = codiceimm;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof IcicodiciimmPK)) {
			return false;
		}
		IcicodiciimmPK castOther = (IcicodiciimmPK)other;
		return 
			this.applicationid.equals(castOther.applicationid)
			&& this.codiceimm.equals(castOther.codiceimm);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.applicationid.hashCode();
		hash = hash * prime + this.codiceimm.hashCode();
		
		return hash;
	}
}

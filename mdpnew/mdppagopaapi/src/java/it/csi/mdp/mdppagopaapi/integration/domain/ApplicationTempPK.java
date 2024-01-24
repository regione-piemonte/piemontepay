/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the application_temp database table.
 * 
 */
@Embeddable
public class ApplicationTempPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String id;

	@Column(name="id_operazione")
	private String idOperazione;

	public ApplicationTempPK() {
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdOperazione() {
		return this.idOperazione;
	}
	public void setIdOperazione(String idOperazione) {
		this.idOperazione = idOperazione;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ApplicationTempPK)) {
			return false;
		}
		ApplicationTempPK castOther = (ApplicationTempPK)other;
		return 
			this.id.equals(castOther.id)
			&& this.idOperazione.equals(castOther.idOperazione);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.idOperazione.hashCode();
		
		return hash;
	}
}

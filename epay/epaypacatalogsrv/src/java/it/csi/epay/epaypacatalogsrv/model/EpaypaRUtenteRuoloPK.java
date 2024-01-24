/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the epaypa_r_utente_ruolo database table.
 * 
 */
@Embeddable
public class EpaypaRUtenteRuoloPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_utente", insertable=false, updatable=false)
	private Long idUtente;

	@Column(name="id_ruolo", insertable=false, updatable=false)
	private Integer idRuolo;

	public EpaypaRUtenteRuoloPK() {
	}
	public Long getIdUtente() {
		return this.idUtente;
	}
	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}
	public Integer getIdRuolo() {
		return this.idRuolo;
	}
	public void setIdRuolo(Integer idRuolo) {
		this.idRuolo = idRuolo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EpaypaRUtenteRuoloPK)) {
			return false;
		}
		EpaypaRUtenteRuoloPK castOther = (EpaypaRUtenteRuoloPK)other;
		return 
			this.idUtente.equals(castOther.idUtente)
			&& this.idRuolo.equals(castOther.idRuolo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idUtente.hashCode();
		hash = hash * prime + this.idRuolo.hashCode();
		
		return hash;
	}
}

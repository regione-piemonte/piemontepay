/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the epaypa_r_utente_ente database table.
 * 
 */
@Embeddable
public class EpaypaRUtenteEntePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_utente", insertable=false, updatable=false)
	private Long idUtente;

	@Column(name="id_ente", insertable=false, updatable=false)
	private Integer idEnte;

	public EpaypaRUtenteEntePK() {
	}
	public Long getIdUtente() {
		return this.idUtente;
	}
	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}
	public Integer getIdEnte() {
		return this.idEnte;
	}
	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EpaypaRUtenteEntePK)) {
			return false;
		}
		EpaypaRUtenteEntePK castOther = (EpaypaRUtenteEntePK)other;
		return 
			this.idUtente.equals(castOther.idUtente)
			&& this.idEnte.equals(castOther.idEnte);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idUtente.hashCode();
		hash = hash * prime + this.idEnte.hashCode();
		
		return hash;
	}
}

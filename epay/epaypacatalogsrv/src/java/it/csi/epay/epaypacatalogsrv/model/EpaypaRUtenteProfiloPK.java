/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the epaypa_r_utente_profilo database table.
 * 
 */
@Embeddable
public class EpaypaRUtenteProfiloPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_utente", insertable=false, updatable=false)
	private Long idUtente;

	@Column(name="id_profilo", insertable=false, updatable=false)
	private Integer idProfilo;

	public EpaypaRUtenteProfiloPK() {
	}
	public Long getIdUtente() {
		return this.idUtente;
	}
	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}
	public Integer getIdProfilo() {
		return this.idProfilo;
	}
	public void setIdProfilo(Integer idProfilo) {
		this.idProfilo = idProfilo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EpaypaRUtenteProfiloPK)) {
			return false;
		}
		EpaypaRUtenteProfiloPK castOther = (EpaypaRUtenteProfiloPK)other;
		return 
			this.idUtente.equals(castOther.idUtente)
			&& this.idProfilo.equals(castOther.idProfilo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idUtente.hashCode();
		hash = hash * prime + this.idProfilo.hashCode();
		
		return hash;
	}
}

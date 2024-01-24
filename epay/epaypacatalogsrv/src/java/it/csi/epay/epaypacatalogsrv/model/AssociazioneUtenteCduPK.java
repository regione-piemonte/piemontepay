/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the epaycat_r_utente_cdu database table.
 * 
 */
@Embeddable
public class AssociazioneUtenteCduPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cod_cdu", insertable=false, updatable=false)
	private String codCdu;

	@Column(name="id_utente", insertable=false, updatable=false)
	private Long idUtente;

	@Column(name="id_ente", insertable=false, updatable=false)
	private Integer idEnte;

	public AssociazioneUtenteCduPK() {
	}
	public String getCodCdu() {
		return this.codCdu;
	}
	public void setCodCdu(String codCdu) {
		this.codCdu = codCdu;
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
		if (!(other instanceof AssociazioneUtenteCduPK)) {
			return false;
		}
		AssociazioneUtenteCduPK castOther = (AssociazioneUtenteCduPK)other;
		return 
			this.codCdu.equals(castOther.codCdu)
			&& this.idUtente.equals(castOther.idUtente)
			&& this.idEnte.equals(castOther.idEnte);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codCdu.hashCode();
		hash = hash * prime + this.idUtente.hashCode();
		hash = hash * prime + this.idEnte.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "AssociazioneUtenteCduPK [codCdu=" + codCdu + ", idUtente=" + idUtente + ", idEnte=" + idEnte + "]";
	}
}

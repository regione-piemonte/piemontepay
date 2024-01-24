/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the epaycat_r_ente_codice_versamento database table.
 * 
 */
@Embeddable
public class AssociazioneEnteCodiceVersamentoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_ente", insertable=false, updatable=false)
	private Integer idEnte;

	@Column(name="id_codice_versamento", insertable=false, updatable=false)
	private Integer idCodiceVersamento;

	public AssociazioneEnteCodiceVersamentoPK() {
	}
	public Integer getIdEnte() {
		return this.idEnte;
	}
	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}
	public Integer getIdCodiceVersamento() {
		return this.idCodiceVersamento;
	}
	public void setIdCodiceVersamento(Integer idCodiceVersamento) {
		this.idCodiceVersamento = idCodiceVersamento;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AssociazioneEnteCodiceVersamentoPK)) {
			return false;
		}
		AssociazioneEnteCodiceVersamentoPK castOther = (AssociazioneEnteCodiceVersamentoPK)other;
		return 
			this.idEnte.equals(castOther.idEnte)
			&& this.idCodiceVersamento.equals(castOther.idCodiceVersamento);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idEnte.hashCode();
		hash = hash * prime + this.idCodiceVersamento.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "AssociazioneEnteCodiceVersamentoPK [idEnte=" + idEnte + ", idCodiceVersamento=" + idCodiceVersamento
				+ "]";
	}
}

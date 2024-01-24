/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the epaycat_r_codice_versamento_mb database table.
 * 
 */
@Embeddable
public class AssociazioneCodiceVersamentoPrimarioSecondarioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_codice_versamento_primario", insertable=false, updatable=false)
	private Integer idCodiceVersamentoPrimario;

	@Column(name="id_codice_versamento_secondario", insertable=false, updatable=false)
	private Integer idCodiceVersamentoSecondario;

	public AssociazioneCodiceVersamentoPrimarioSecondarioPK() {
	}


	public Integer getIdCodiceVersamentoPrimario() {
		return idCodiceVersamentoPrimario;
	}


	public void setIdCodiceVersamentoPrimario(Integer idCodiceVersamentoPrimario) {
		this.idCodiceVersamentoPrimario = idCodiceVersamentoPrimario;
	}


	public Integer getIdCodiceVersamentoSecondario() {
		return idCodiceVersamentoSecondario;
	}


	public void setIdCodiceVersamentoSecondario(Integer idCodiceVersamentoSecondario) {
		this.idCodiceVersamentoSecondario = idCodiceVersamentoSecondario;
	}


	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AssociazioneCodiceVersamentoPrimarioSecondarioPK)) {
			return false;
		}
		AssociazioneCodiceVersamentoPrimarioSecondarioPK castOther = (AssociazioneCodiceVersamentoPrimarioSecondarioPK)other;
		return 
			this.idCodiceVersamentoPrimario.equals(castOther.idCodiceVersamentoPrimario)
			&& this.idCodiceVersamentoSecondario.equals(castOther.idCodiceVersamentoSecondario);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCodiceVersamentoPrimario.hashCode();
		hash = hash * prime + this.idCodiceVersamentoSecondario.hashCode();
		
		return hash;
	}


	@Override
	public String toString() {
		return "AssociazioneCodiceVersamentoPrimarioSecondarioPK [idCodiceVersamentoPrimario="
				+ idCodiceVersamentoPrimario + ", idCodiceVersamentoSecondario=" + idCodiceVersamentoSecondario + "]";
	}
}

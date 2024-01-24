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
public class AssociazioneRiferimentoContabilePrimarioSecondarioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_riferimento_contabile_primario", insertable=false, updatable=false)
	private Integer idRiferimentoContabilePrimario;

	@Column(name="id_riferimento_contabile_secondario", insertable=false, updatable=false)
	private Integer idRiferimentoContabileSecondario;

	public AssociazioneRiferimentoContabilePrimarioSecondarioPK() {
	}
	

	public Integer getIdRiferimentoContabilePrimario() {
		return idRiferimentoContabilePrimario;
	}


	public void setIdRiferimentoContabilePrimario(Integer idRiferimentoContabilePrimario) {
		this.idRiferimentoContabilePrimario = idRiferimentoContabilePrimario;
	}



	public Integer getIdRiferimentoContabileSecondario() {
		return idRiferimentoContabileSecondario;
	}



	public void setIdRiferimentoContabileSecondario(Integer idRiferimentoContabileSecondario) {
		this.idRiferimentoContabileSecondario = idRiferimentoContabileSecondario;
	}




	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AssociazioneRiferimentoContabilePrimarioSecondarioPK)) {
			return false;
		}
		AssociazioneRiferimentoContabilePrimarioSecondarioPK castOther = (AssociazioneRiferimentoContabilePrimarioSecondarioPK)other;
		return 
			this.idRiferimentoContabilePrimario.equals(castOther.idRiferimentoContabilePrimario)
			&& this.idRiferimentoContabileSecondario.equals(castOther.idRiferimentoContabileSecondario);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idRiferimentoContabilePrimario.hashCode();
		hash = hash * prime + this.idRiferimentoContabileSecondario.hashCode();
		
		return hash;
	}


	@Override
	public String toString() {
		return "AssociazioneRiferimentoContabilePrimarioSecondarioPK [idRiferimentoContabilePrimario="
				+ idRiferimentoContabilePrimario + ", idRiferimentoContabileSecondario="
				+ idRiferimentoContabileSecondario + "]";
	}
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the epaypa_r_profilo_cod_vers database table.
 * 
 */
@Embeddable
public class EpaypaRProfiloCodVerPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_profilo", insertable=false, updatable=false)
	private Integer idProfilo;

	@Column(name="id_codice_versamento", insertable=false, updatable=false)
	private Integer idCodiceVersamento;

	public EpaypaRProfiloCodVerPK() {
	}
	public Integer getIdProfilo() {
		return this.idProfilo;
	}
	public void setIdProfilo(Integer idProfilo) {
		this.idProfilo = idProfilo;
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
		if (!(other instanceof EpaypaRProfiloCodVerPK)) {
			return false;
		}
		EpaypaRProfiloCodVerPK castOther = (EpaypaRProfiloCodVerPK)other;
		return 
			this.idProfilo.equals(castOther.idProfilo)
			&& this.idCodiceVersamento.equals(castOther.idCodiceVersamento);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idProfilo.hashCode();
		hash = hash * prime + this.idCodiceVersamento.hashCode();
		
		return hash;
	}
}

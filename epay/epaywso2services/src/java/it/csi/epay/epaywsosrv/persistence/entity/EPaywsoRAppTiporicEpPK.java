/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the epaywso_r_app_tiporic_ep database table.
 * 
 */
@Embeddable
public class EPaywsoRAppTiporicEpPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="id_applicativo", insertable=false, updatable=false)
	private Integer idApplicativo;

	@Column(name="id_endpoint", insertable=false, updatable=false)
	private Integer idEndpoint;

	@Column(name="id_tipo_richiesta", insertable=false, updatable=false)
	private Integer idTipoRichiesta;

	public EPaywsoRAppTiporicEpPK() {
	}
	public EPaywsoRAppTiporicEpPK(Integer idApplicativo, Integer idEndpoint, Integer idTipoRichiesta) {
		this.idApplicativo = idApplicativo;
		this.idEndpoint = idEndpoint;
		this.idTipoRichiesta = idTipoRichiesta;
	}
	public Integer getIdApplicativo() {
		return this.idApplicativo;
	}
	public void setIdApplicativo(Integer idApplicativo) {
		this.idApplicativo = idApplicativo;
	}
	public Integer getIdEndpoint() {
		return this.idEndpoint;
	}
	public void setIdEndpoint(Integer idEndpoint) {
		this.idEndpoint = idEndpoint;
	}
	public Integer getIdTipoRichiesta() {
		return this.idTipoRichiesta;
	}
	public void setIdTipoRichiesta(Integer idTipoRichiesta) {
		this.idTipoRichiesta = idTipoRichiesta;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EPaywsoRAppTiporicEpPK)) {
			return false;
		}
		EPaywsoRAppTiporicEpPK castOther = (EPaywsoRAppTiporicEpPK)other;
		return 
			this.idApplicativo.equals(castOther.idApplicativo)
			&& this.idEndpoint.equals(castOther.idEndpoint)
			&& this.idTipoRichiesta.equals(castOther.idTipoRichiesta);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idApplicativo.hashCode();
		hash = hash * prime + this.idEndpoint.hashCode();
		hash = hash * prime + this.idTipoRichiesta.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("idTipoRichiesta:").append(idTipoRichiesta).append(COMMA);
		s.append("idApplicativo:").append(idApplicativo).append(COMMA);
		s.append("idEndpoint:").append(idEndpoint);
		s.append(" }");
		return s.toString();
	}
}

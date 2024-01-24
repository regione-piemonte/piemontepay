/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the epaywso_r_app_ric_esito_invio database table.
 * 
 */
@Embeddable
public class EPaywsoRAppRicEsitoInvioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_richiesta", insertable=false, updatable=false)
	private Long idRichiesta;

	@Column(name="id_esito_invio", insertable=false, updatable=false)
	private Long idEsitoInvio;

	@Column(name="id_applicativo", insertable=false, updatable=false)
	private Integer idApplicativo;

	public EPaywsoRAppRicEsitoInvioPK() {
	}
	public EPaywsoRAppRicEsitoInvioPK(Long idRichiesta, Long idEsitoInvio, Integer idApplicativo) {
		this.idRichiesta = idRichiesta;
		this.idEsitoInvio = idEsitoInvio;
		this.idApplicativo = idApplicativo;
	}
	public Long getIdRichiesta() {
		return this.idRichiesta;
	}
	public void setIdRichiesta(Long idRichiesta) {
		this.idRichiesta = idRichiesta;
	}
	public Long getIdEsitoInvio() {
		return this.idEsitoInvio;
	}
	public void setIdEsitoInvio(Long idEsitoInvio) {
		this.idEsitoInvio = idEsitoInvio;
	}
	public Integer getIdApplicativo() {
		return this.idApplicativo;
	}
	public void setIdApplicativo(Integer idApplicativo) {
		this.idApplicativo = idApplicativo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EPaywsoRAppRicEsitoInvioPK)) {
			return false;
		}
		EPaywsoRAppRicEsitoInvioPK castOther = (EPaywsoRAppRicEsitoInvioPK)other;
		return 
			this.idRichiesta.equals(castOther.idRichiesta)
			&& this.idEsitoInvio.equals(castOther.idEsitoInvio)
			&& this.idApplicativo.equals(castOther.idApplicativo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idRichiesta.hashCode();
		hash = hash * prime + this.idEsitoInvio.hashCode();
		hash = hash * prime + this.idApplicativo.hashCode();
		
		return hash;
	}
}

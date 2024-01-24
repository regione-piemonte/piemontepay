/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the epaypa_r_ruolo_cdu database table.
 * 
 */
@Embeddable
public class EpaypaRRuoloCduPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cod_cdu", insertable=false, updatable=false)
	private String codCdu;

	@Column(name="cod_ruolo", insertable=false, updatable=false)
	private String codRuolo;

	public EpaypaRRuoloCduPK() {
	}
	public String getCodCdu() {
		return this.codCdu;
	}
	public void setCodCdu(String codCdu) {
		this.codCdu = codCdu;
	}
	public String getCodRuolo() {
		return this.codRuolo;
	}
	public void setCodRuolo(String codRuolo) {
		this.codRuolo = codRuolo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EpaypaRRuoloCduPK)) {
			return false;
		}
		EpaypaRRuoloCduPK castOther = (EpaypaRRuoloCduPK)other;
		return 
			this.codCdu.equals(castOther.codCdu)
			&& this.codRuolo.equals(castOther.codRuolo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codCdu.hashCode();
		hash = hash * prime + this.codRuolo.hashCode();
		
		return hash;
	}
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
@SuppressWarnings ( "unused" )
public class EpayTParametriPK implements Serializable {

	private static final long serialVersionUID = 2693214720575959833L;

	@Column ( unique = true, nullable = false, length = 100 )
	private String gruppo;

	@Column ( unique = true, nullable = false, length = 100 )
	private String codice;

	public EpayTParametriPK () {
	}

	public String getGruppo () {
		return this.gruppo;
	}

	public void setGruppo ( String gruppo ) {
		this.gruppo = gruppo;
	}

	public String getCodice () {
		return this.codice;
	}

	public void setCodice ( String codice ) {
		this.codice = codice;
	}

	public boolean equals ( Object other ) {
		if ( this == other ) {
			return true;
		}
		if ( !( other instanceof EpayTParametriPK ) ) {
			return false;
		}
		var castOther = (EpayTParametriPK) other;
		return this.gruppo.equals ( castOther.gruppo )
						&& this.codice.equals ( castOther.codice );
	}

	public int hashCode () {
		final var prime = 31;
		var hash = 17;
		hash = hash * prime + this.gruppo.hashCode ();
		hash = hash * prime + this.codice.hashCode ();
		return hash;
	}

	@Override
	public String toString () {
		return "{ " +
						"gruppo:" + gruppo +
						", codice:" + codice +
						" }";
	}
}

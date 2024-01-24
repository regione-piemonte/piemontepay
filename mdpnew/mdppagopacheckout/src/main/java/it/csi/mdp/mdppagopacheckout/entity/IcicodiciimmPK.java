/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


/**
 * The primary key class for the icicodiciimm database table.
 */
@Embeddable
@SuppressWarnings ( "unused" )
public class IcicodiciimmPK implements Serializable {

	private static final long serialVersionUID = 8291387027546398292L;

	@Column ( insertable = false, updatable = false )
	private String applicationid;

	private String codiceimm;

	public IcicodiciimmPK () {
	}

	public String getApplicationid () {
		return this.applicationid;
	}

	public void setApplicationid ( String applicationid ) {
		this.applicationid = applicationid;
	}

	public String getCodiceimm () {
		return this.codiceimm;
	}

	public void setCodiceimm ( String codiceimm ) {
		this.codiceimm = codiceimm;
	}

	public boolean equals ( Object other ) {
		if ( this == other ) {
			return true;
		}
		if ( !( other instanceof IcicodiciimmPK ) ) {
			return false;
		}
		IcicodiciimmPK castOther = (IcicodiciimmPK) other;
		return
						this.applicationid.equals ( castOther.applicationid )
										&& this.codiceimm.equals ( castOther.codiceimm );
	}

	public int hashCode () {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.applicationid.hashCode ();
		hash = hash * prime + this.codiceimm.hashCode ();

		return hash;
	}
}

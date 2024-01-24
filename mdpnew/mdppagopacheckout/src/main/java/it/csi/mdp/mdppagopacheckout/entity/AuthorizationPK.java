/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


/**
 * The primary key class for the authorizations database table.
 */
@Embeddable
@SuppressWarnings ( "unused" )
public class AuthorizationPK implements Serializable {

	private static final long serialVersionUID = -629055148155755939L;

	@Column ( insertable = false, updatable = false )
	private Integer idrole;

	private String operazione;

	public AuthorizationPK () {
	}

	public Integer getIdrole () {
		return this.idrole;
	}

	public void setIdrole ( Integer idrole ) {
		this.idrole = idrole;
	}

	public String getOperazione () {
		return this.operazione;
	}

	public void setOperazione ( String operazione ) {
		this.operazione = operazione;
	}

	public boolean equals ( Object other ) {
		if ( this == other ) {
			return true;
		}
		if ( !( other instanceof AuthorizationPK ) ) {
			return false;
		}
		AuthorizationPK castOther = (AuthorizationPK) other;
		return
						this.idrole.equals ( castOther.idrole )
										&& this.operazione.equals ( castOther.operazione );
	}

	public int hashCode () {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idrole.hashCode ();
		hash = hash * prime + this.operazione.hashCode ();

		return hash;
	}
}

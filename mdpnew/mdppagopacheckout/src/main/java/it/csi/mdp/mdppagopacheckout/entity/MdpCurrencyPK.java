/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


/**
 * The primary key class for the mdp_currency database table.
 */
@Embeddable
@SuppressWarnings ( "unused" )
public class MdpCurrencyPK implements Serializable {

	private static final long serialVersionUID = -8887391012707492904L;

	@Column ( insertable = false, updatable = false )
	private String gatewayid;

	private String mdpcurrencycode;

	private String gtwcurrencycode;

	public MdpCurrencyPK () {
	}

	public String getGatewayid () {
		return this.gatewayid;
	}

	public void setGatewayid ( String gatewayid ) {
		this.gatewayid = gatewayid;
	}

	public String getMdpcurrencycode () {
		return this.mdpcurrencycode;
	}

	public void setMdpcurrencycode ( String mdpcurrencycode ) {
		this.mdpcurrencycode = mdpcurrencycode;
	}

	public String getGtwcurrencycode () {
		return this.gtwcurrencycode;
	}

	public void setGtwcurrencycode ( String gtwcurrencycode ) {
		this.gtwcurrencycode = gtwcurrencycode;
	}

	public boolean equals ( Object other ) {
		if ( this == other ) {
			return true;
		}
		if ( !( other instanceof MdpCurrencyPK ) ) {
			return false;
		}
		MdpCurrencyPK castOther = (MdpCurrencyPK) other;
		return
						this.gatewayid.equals ( castOther.gatewayid )
										&& this.mdpcurrencycode.equals ( castOther.mdpcurrencycode )
										&& this.gtwcurrencycode.equals ( castOther.gtwcurrencycode );
	}

	public int hashCode () {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.gatewayid.hashCode ();
		hash = hash * prime + this.mdpcurrencycode.hashCode ();
		hash = hash * prime + this.gtwcurrencycode.hashCode ();

		return hash;
	}
}

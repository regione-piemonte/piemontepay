/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


/**
 * The primary key class for the applicationdetail database table.
 */
@Embeddable
@SuppressWarnings ( "unused" )
public class ApplicationdetailPK implements Serializable {

	private static final long serialVersionUID = -983414136880443018L;

	@Column ( insertable = false, updatable = false )
	private String applicationid;

	@Column ( insertable = false, updatable = false )
	private String gatewayid;

	@Column ( insertable = false, updatable = false )
	private String paymentmodeid;

	public ApplicationdetailPK () {
	}

	public String getApplicationid () {
		return this.applicationid;
	}

	public void setApplicationid ( String applicationid ) {
		this.applicationid = applicationid;
	}

	public String getGatewayid () {
		return this.gatewayid;
	}

	public void setGatewayid ( String gatewayid ) {
		this.gatewayid = gatewayid;
	}

	public String getPaymentmodeid () {
		return this.paymentmodeid;
	}

	public void setPaymentmodeid ( String paymentmodeid ) {
		this.paymentmodeid = paymentmodeid;
	}

	public boolean equals ( Object other ) {
		if ( this == other ) {
			return true;
		}
		if ( !( other instanceof ApplicationdetailPK ) ) {
			return false;
		}
		ApplicationdetailPK castOther = (ApplicationdetailPK) other;
		return
						this.applicationid.equals ( castOther.applicationid )
										&& this.gatewayid.equals ( castOther.gatewayid )
										&& this.paymentmodeid.equals ( castOther.paymentmodeid );
	}

	public int hashCode () {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.applicationid.hashCode ();
		hash = hash * prime + this.gatewayid.hashCode ();
		hash = hash * prime + this.paymentmodeid.hashCode ();

		return hash;
	}
}

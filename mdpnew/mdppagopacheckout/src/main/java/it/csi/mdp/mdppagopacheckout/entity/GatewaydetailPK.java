/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


/**
 * The primary key class for the gatewaydetail database table.
 */
@Embeddable
@SuppressWarnings ( "unused" )
public class GatewaydetailPK implements Serializable {

	private static final long serialVersionUID = 2037932204780557872L;

	@Column ( name = "gateway_id", insertable = false, updatable = false )
	private String gatewayId;

	@Column ( name = "paymentmode_id", insertable = false, updatable = false )
	private String paymentmodeId;

	public GatewaydetailPK () {
	}

	public String getGatewayId () {
		return this.gatewayId;
	}

	public void setGatewayId ( String gatewayId ) {
		this.gatewayId = gatewayId;
	}

	public String getPaymentmodeId () {
		return this.paymentmodeId;
	}

	public void setPaymentmodeId ( String paymentmodeId ) {
		this.paymentmodeId = paymentmodeId;
	}

	public boolean equals ( Object other ) {
		if ( this == other ) {
			return true;
		}
		if ( !( other instanceof GatewaydetailPK ) ) {
			return false;
		}
		GatewaydetailPK castOther = (GatewaydetailPK) other;
		return
						this.gatewayId.equals ( castOther.gatewayId )
										&& this.paymentmodeId.equals ( castOther.paymentmodeId );
	}

	public int hashCode () {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.gatewayId.hashCode ();
		hash = hash * prime + this.paymentmodeId.hashCode ();

		return hash;
	}
}

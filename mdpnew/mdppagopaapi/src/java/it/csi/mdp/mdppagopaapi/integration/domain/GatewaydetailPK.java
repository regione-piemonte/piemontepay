/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the gatewaydetail database table.
 * 
 */
@Embeddable
public class GatewaydetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="gateway_id", insertable=false, updatable=false)
	private String gatewayId;

	@Column(name="paymentmode_id", insertable=false, updatable=false)
	private String paymentmodeId;

	public GatewaydetailPK() {
	}
	public String getGatewayId() {
		return this.gatewayId;
	}
	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}
	public String getPaymentmodeId() {
		return this.paymentmodeId;
	}
	public void setPaymentmodeId(String paymentmodeId) {
		this.paymentmodeId = paymentmodeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GatewaydetailPK)) {
			return false;
		}
		GatewaydetailPK castOther = (GatewaydetailPK)other;
		return 
			this.gatewayId.equals(castOther.gatewayId)
			&& this.paymentmodeId.equals(castOther.paymentmodeId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.gatewayId.hashCode();
		hash = hash * prime + this.paymentmodeId.hashCode();
		
		return hash;
	}
}

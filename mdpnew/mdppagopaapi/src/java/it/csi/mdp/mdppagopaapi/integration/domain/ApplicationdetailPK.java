/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the applicationdetail database table.
 * 
 */
@Embeddable
public class ApplicationdetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String applicationid;

	@Column(insertable=false, updatable=false)
	private String gatewayid;

	@Column(insertable=false, updatable=false)
	private String paymentmodeid;

	public ApplicationdetailPK() {
	}
	public String getApplicationid() {
		return this.applicationid;
	}
	public void setApplicationid(String applicationid) {
		this.applicationid = applicationid;
	}
	public String getGatewayid() {
		return this.gatewayid;
	}
	public void setGatewayid(String gatewayid) {
		this.gatewayid = gatewayid;
	}
	public String getPaymentmodeid() {
		return this.paymentmodeid;
	}
	public void setPaymentmodeid(String paymentmodeid) {
		this.paymentmodeid = paymentmodeid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ApplicationdetailPK)) {
			return false;
		}
		ApplicationdetailPK castOther = (ApplicationdetailPK)other;
		return 
			this.applicationid.equals(castOther.applicationid)
			&& this.gatewayid.equals(castOther.gatewayid)
			&& this.paymentmodeid.equals(castOther.paymentmodeid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.applicationid.hashCode();
		hash = hash * prime + this.gatewayid.hashCode();
		hash = hash * prime + this.paymentmodeid.hashCode();
		
		return hash;
	}
}

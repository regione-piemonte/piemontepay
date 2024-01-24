/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the mdp_currency database table.
 * 
 */
@Embeddable
public class MdpCurrencyPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String gatewayid;

	private String mdpcurrencycode;

	private String gtwcurrencycode;

	public MdpCurrencyPK() {
	}
	public String getGatewayid() {
		return this.gatewayid;
	}
	public void setGatewayid(String gatewayid) {
		this.gatewayid = gatewayid;
	}
	public String getMdpcurrencycode() {
		return this.mdpcurrencycode;
	}
	public void setMdpcurrencycode(String mdpcurrencycode) {
		this.mdpcurrencycode = mdpcurrencycode;
	}
	public String getGtwcurrencycode() {
		return this.gtwcurrencycode;
	}
	public void setGtwcurrencycode(String gtwcurrencycode) {
		this.gtwcurrencycode = gtwcurrencycode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MdpCurrencyPK)) {
			return false;
		}
		MdpCurrencyPK castOther = (MdpCurrencyPK)other;
		return 
			this.gatewayid.equals(castOther.gatewayid)
			&& this.mdpcurrencycode.equals(castOther.mdpcurrencycode)
			&& this.gtwcurrencycode.equals(castOther.gtwcurrencycode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.gatewayid.hashCode();
		hash = hash * prime + this.mdpcurrencycode.hashCode();
		hash = hash * prime + this.gtwcurrencycode.hashCode();
		
		return hash;
	}
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the language database table.
 * 
 */
@Embeddable
public class LanguagePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String gatewayid;

	private String gtwlanguagecode;

	private String mdplanguagecode;

	public LanguagePK() {
	}
	public String getGatewayid() {
		return this.gatewayid;
	}
	public void setGatewayid(String gatewayid) {
		this.gatewayid = gatewayid;
	}
	public String getGtwlanguagecode() {
		return this.gtwlanguagecode;
	}
	public void setGtwlanguagecode(String gtwlanguagecode) {
		this.gtwlanguagecode = gtwlanguagecode;
	}
	public String getMdplanguagecode() {
		return this.mdplanguagecode;
	}
	public void setMdplanguagecode(String mdplanguagecode) {
		this.mdplanguagecode = mdplanguagecode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LanguagePK)) {
			return false;
		}
		LanguagePK castOther = (LanguagePK)other;
		return 
			this.gatewayid.equals(castOther.gatewayid)
			&& this.gtwlanguagecode.equals(castOther.gtwlanguagecode)
			&& this.mdplanguagecode.equals(castOther.mdplanguagecode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.gatewayid.hashCode();
		hash = hash * prime + this.gtwlanguagecode.hashCode();
		hash = hash * prime + this.mdplanguagecode.hashCode();
		
		return hash;
	}
}

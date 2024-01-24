/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mdp_currency database table.
 * 
 */
@Entity
@Table(name="mdp_currency")
@NamedQuery(name="MdpCurrency.findAll", query="SELECT m FROM MdpCurrency m")
public class MdpCurrency implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MdpCurrencyPK id;

	private String currencydescr;

	private String enabled;

	//bi-directional many-to-one association to Gateway
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gatewayid", insertable=false, updatable=false)
	private Gateway gateway;

	public MdpCurrency() {
	}

	public MdpCurrencyPK getId() {
		return this.id;
	}

	public void setId(MdpCurrencyPK id) {
		this.id = id;
	}

	public String getCurrencydescr() {
		return this.currencydescr;
	}

	public void setCurrencydescr(String currencydescr) {
		this.currencydescr = currencydescr;
	}

	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public Gateway getGateway() {
		return this.gateway;
	}

	public void setGateway(Gateway gateway) {
		this.gateway = gateway;
	}

}

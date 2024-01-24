/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import java.io.Serializable;


/**
 * The persistent class for the language database table.
 */
@Entity
@NamedQuery ( name = "Language.findAll", query = "SELECT l FROM Language l" )
@SuppressWarnings ( "unused" )
public class Language implements Serializable {

	private static final long serialVersionUID = -2154247486158934381L;

	@EmbeddedId
	private LanguagePK id;

	private String enabled;

	private String languagedescr;

	@ManyToOne ( fetch = FetchType.LAZY )
	@JoinColumn ( name = "gatewayid", insertable = false, updatable = false )
	private Gateway gateway;

	public Language () {
	}

	public LanguagePK getId () {
		return this.id;
	}

	public void setId ( LanguagePK id ) {
		this.id = id;
	}

	public String getEnabled () {
		return this.enabled;
	}

	public void setEnabled ( String enabled ) {
		this.enabled = enabled;
	}

	public String getLanguagedescr () {
		return this.languagedescr;
	}

	public void setLanguagedescr ( String languagedescr ) {
		this.languagedescr = languagedescr;
	}

	public Gateway getGateway () {
		return this.gateway;
	}

	public void setGateway ( Gateway gateway ) {
		this.gateway = gateway;
	}

}

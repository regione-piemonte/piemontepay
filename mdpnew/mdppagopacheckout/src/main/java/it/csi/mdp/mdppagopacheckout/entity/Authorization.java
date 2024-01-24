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
import javax.persistence.Table;
import java.io.Serializable;


/**
 * The persistent class for the authorizations database table.
 */
@Entity
@Table ( name = "authorizations" )
@NamedQuery ( name = "Authorization.findAll", query = "SELECT a FROM Authorization a" )
@SuppressWarnings ( "unused" )
public class Authorization implements Serializable {

	private static final long serialVersionUID = 6712363977566456788L;

	@EmbeddedId
	private AuthorizationPK id;

	@ManyToOne ( fetch = FetchType.LAZY )
	@JoinColumn ( name = "idrole", insertable = false, updatable = false )
	private MdpBckrole mdpBckrole;

	public Authorization () {
	}

	public AuthorizationPK getId () {
		return this.id;
	}

	public void setId ( AuthorizationPK id ) {
		this.id = id;
	}

	public MdpBckrole getMdpBckrole () {
		return this.mdpBckrole;
	}

	public void setMdpBckrole ( MdpBckrole mdpBckrole ) {
		this.mdpBckrole = mdpBckrole;
	}

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the authorizations database table.
 * 
 */
@Entity
@Table(name="authorizations")
@NamedQuery(name="Authorization.findAll", query="SELECT a FROM Authorization a")
public class Authorization implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AuthorizationPK id;

	//bi-directional many-to-one association to MdpBckrole
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idrole", insertable=false, updatable=false)
	private MdpBckrole mdpBckrole;

	public Authorization() {
	}

	public AuthorizationPK getId() {
		return this.id;
	}

	public void setId(AuthorizationPK id) {
		this.id = id;
	}

	public MdpBckrole getMdpBckrole() {
		return this.mdpBckrole;
	}

	public void setMdpBckrole(MdpBckrole mdpBckrole) {
		this.mdpBckrole = mdpBckrole;
	}

}

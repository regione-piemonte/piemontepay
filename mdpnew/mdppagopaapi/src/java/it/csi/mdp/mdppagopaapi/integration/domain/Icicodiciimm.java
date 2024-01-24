/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the icicodiciimm database table.
 * 
 */
@Entity
@NamedQuery(name="Icicodiciimm.findAll", query="SELECT i FROM Icicodiciimm i")
public class Icicodiciimm implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IcicodiciimmPK id;

	private String causale;

	//bi-directional many-to-one association to Application
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="applicationid", insertable=false, updatable=false)
	private Application application;

	public Icicodiciimm() {
	}

	public IcicodiciimmPK getId() {
		return this.id;
	}

	public void setId(IcicodiciimmPK id) {
		this.id = id;
	}

	public String getCausale() {
		return this.causale;
	}

	public void setCausale(String causale) {
		this.causale = causale;
	}

	public Application getApplication() {
		return this.application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

}

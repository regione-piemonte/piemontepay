/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table ( name = "auditactions" )

@NamedQueries({
	@NamedQuery ( name = "AuditAction.findAll", query = "SELECT a FROM AuditAction a" ),
	@NamedQuery ( name = "AuditAction.findById", query = "SELECT a FROM AuditAction a WHERE a.id = :id" )
})
public class AuditAction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column ( name = "idaction" )
	private String id;
	
	@Column ( name = "description" )
	private String descrizione;

    public AuditAction () {
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "AuditAction [id=" + id + ", descrizione=" + descrizione + "]";
	}
}

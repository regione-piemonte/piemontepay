/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the auditactions database table.
 * 
 */
@Entity
@Table(name="auditactions")
@NamedQuery(name="Auditaction.findAll", query="SELECT a FROM Auditaction a")
public class Auditaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AUDITACTIONS_IDACTION_GENERATOR", sequenceName="AUDITACTIONS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUDITACTIONS_IDACTION_GENERATOR")
	private String idaction;

	private String description;

	public Auditaction() {
	}

	public String getIdaction() {
		return this.idaction;
	}

	public void setIdaction(String idaction) {
		this.idaction = idaction;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

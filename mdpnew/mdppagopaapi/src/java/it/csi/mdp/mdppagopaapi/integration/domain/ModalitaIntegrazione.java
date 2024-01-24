/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the modalita_integrazione database table.
 * 
 */
@Entity
@Table(name="modalita_integrazione")
@NamedQuery(name="ModalitaIntegrazione.findAll", query="SELECT m FROM ModalitaIntegrazione m")
public class ModalitaIntegrazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MODALITA_INTEGRAZIONE_ID_GENERATOR", sequenceName="MODALITA_INTEGRAZIONE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MODALITA_INTEGRAZIONE_ID_GENERATOR")
	private Integer id;

	private String codice;

	private String descrizione;

	public ModalitaIntegrazione() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaypa_d_stato_flusso database table.
 * 
 */
@Entity
@Table(name="epaypa_d_stato_flusso")
@NamedQueries({
	@NamedQuery(
			name = "EpaypaDStatoFlusso.findAllByDirezione",
			query = "SELECT e "
					+ "FROM EpaypaDStatoFlusso e "
					+ "WHERE e.direzioneFlusso = :direzione "
					+ "ORDER BY e.descrizione"),
	@NamedQuery(
			name = "EpaypaDStatoFlusso.findAll",
			query = "SELECT e FROM EpaypaDStatoFlusso e "
					+ "ORDER BY e.descrizione")
	})
public class EpaypaDStatoFlusso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_stato_flusso")
	private Integer idStatoFlusso;

	private String descrizione;

	@Column(name="direzione_flusso")
	private String direzioneFlusso;

	public EpaypaDStatoFlusso() {
	}

	public Integer getIdStatoFlusso() {
		return this.idStatoFlusso;
	}

	public void setIdStatoFlusso(Integer idStatoFlusso) {
		this.idStatoFlusso = idStatoFlusso;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDirezioneFlusso() {
		return this.direzioneFlusso;
	}

	public void setDirezioneFlusso(String direzioneFlusso) {
		this.direzioneFlusso = direzioneFlusso;
	}

}

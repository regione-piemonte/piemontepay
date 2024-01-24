/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaypa_d_tipo_flusso database table.
 * 
 */
@Entity
@Table(name="epaypa_d_tipo_flusso")
@NamedQuery(name="EpaypaDTipoFlusso.findAll", query="SELECT e FROM EpaypaDTipoFlusso e")
public class EpaypaDTipoFlusso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipo_flusso")
	private Integer idTipoFlusso;

	private String descrizione;

	@Column(name="direzione_flusso")
	private String direzioneFlusso;

	public EpaypaDTipoFlusso() {
	}

	public Integer getIdTipoFlusso() {
		return this.idTipoFlusso;
	}

	public void setIdTipoFlusso(Integer idTipoFlusso) {
		this.idTipoFlusso = idTipoFlusso;
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

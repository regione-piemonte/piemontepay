/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaypa_d_formato_output database table.
 * 
 */
@Entity
@Table(name="epaypa_d_tipo_formato_output")
@NamedQuery(name="EpaypaDTipoFormatoOutput.findAll", query="SELECT e FROM EpaypaDTipoFormatoOutput e")
public class EpaypaDTipoFormatoOutput implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipo_formato_output")
	private Integer idTipoFormatoOutput;

	private String descrizione;

	public EpaypaDTipoFormatoOutput() {
	}

	public Integer getIdTipoFormatoOutput() {
		return this.idTipoFormatoOutput;
	}

	public void setIdTipoFormatoOutput(Integer idTipoFormatoOutput) {
		this.idTipoFormatoOutput = idTipoFormatoOutput;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}

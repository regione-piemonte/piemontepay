/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaypa_d_tipo_versamento database table.
 * 
 */
@Entity
@Table(name="epaypa_d_tipo_versamento")
@NamedQueries({
	@NamedQuery(
			name = "EpaypaDTipoVersamento.findOneByCodTipoVersamento",
			query = "SELECT e "
					+ "FROM EpaypaDTipoVersamento e "
					+ "WHERE e.codTipoVersamento = :codTipoVersamento"),
	@NamedQuery(
			name = "EpaypaDTipoVersamento.findAll",
			query = "SELECT e FROM EpaypaDTipoVersamento e")
})
public class EpaypaDTipoVersamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipo_versamento")
	private Integer idTipoVersamento;

	@Column(name="cod_tipo_versamento")
	private String codTipoVersamento;

	private String descrizione;

	public EpaypaDTipoVersamento() {
	}

	public Integer getIdTipoVersamento() {
		return this.idTipoVersamento;
	}

	public void setIdTipoVersamento(Integer idTipoVersamento) {
		this.idTipoVersamento = idTipoVersamento;
	}

	public String getCodTipoVersamento() {
		return this.codTipoVersamento;
	}

	public void setCodTipoVersamento(String codTipoVersamento) {
		this.codTipoVersamento = codTipoVersamento;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}

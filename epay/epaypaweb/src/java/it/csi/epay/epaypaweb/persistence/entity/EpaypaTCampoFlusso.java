/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaypa_t_campo_flusso database table.
 *
 */
@Entity
@Table(name="epaypa_t_campo_flusso")
@NamedQuery(name="EpaypaTCampoFlusso.findAll", query="SELECT e FROM EpaypaTCampoFlusso e")
public class EpaypaTCampoFlusso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_campo_flusso")
	private Integer idCampoFlusso;

	private String nome;

	@Column(name="formato_csv")
	private String formatoCsv;

	@Column(name="formato_xlsx")
	private String formatoXlsx;

	//uni-directional many-to-one association to EpaypaDTipoFlusso
	@ManyToOne
	@JoinColumn(name="id_tipo_flusso")
	private EpaypaDTipoFlusso epaypaDTipoFlusso;

	public EpaypaTCampoFlusso() {
	}

	public Integer getIdCampoFlusso() {
		return this.idCampoFlusso;
	}

	public void setIdCampoFlusso(Integer idCampoFlusso) {
		this.idCampoFlusso = idCampoFlusso;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EpaypaDTipoFlusso getEpaypaDTipoFlusso() {
		return this.epaypaDTipoFlusso;
	}

	public void setEpaypaDTipoFlusso(EpaypaDTipoFlusso epaypaDTipoFlusso) {
		this.epaypaDTipoFlusso = epaypaDTipoFlusso;
	}

	public String getFormatoCsv() {
		return formatoCsv;
	}

	public void setFormatoCsv(String formatoCsv) {
		this.formatoCsv = formatoCsv;
	}

	public String getFormatoXlsx() {
		return formatoXlsx;
	}

	public void setFormatoXlsx(String formatoXlsx) {
		this.formatoXlsx = formatoXlsx;
	}

}

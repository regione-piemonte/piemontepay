/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the epaypa_t_template database table.
 *
 */
@Entity
@Table(name="epaypa_t_template")
@NamedQueries({
	@NamedQuery(name="EpaypaTTemplate.findAll", query="SELECT e FROM EpaypaTTemplate e"),
	@NamedQuery(
                    name = "EpaypaTTemplate.findTemplateByIdEnteTipoFlusso",
                    query = "SELECT t "
                        + "FROM EpaypaTTemplate t "
                        + "WHERE t.epaypaTEnte.idEnte = :idEnte "
                        + "AND t.epaypaDTipoFlusso.idTipoFlusso = :idTipoFlusso" ),
    @NamedQuery (
                    name = "EpaypaTTemplate.findTemplateByTipoFlusso",
                    query = "SELECT t "
                        + "FROM EpaypaTTemplate t "
                        + "WHERE t.epaypaDTipoFlusso.idTipoFlusso = :idTipoFlusso" ),
	@NamedQuery(
			name = "EpaypaTTemplate.findTemplateByIdEnteNomeTemplate",
			query = "SELECT t "
					+ "FROM EpaypaTTemplate t "
					+ "WHERE t.epaypaTEnte.idEnte = :idEnte "
					+ "AND t.nome = :nomeTemplate")
})
public class EpaypaTTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_template")
	private Long idTemplate;

	private String descrizione;

	private String nome;

	//uni-directional many-to-one association to EpaypaDTipoFlusso
	@ManyToOne
	@JoinColumn(name="id_tipo_flusso")
	private EpaypaDTipoFlusso epaypaDTipoFlusso;

	//uni-directional many-to-one association to EpaypaTEnte
	@ManyToOne
	@JoinColumn(name="id_ente")
	private EpaypaTEnte epaypaTEnte;

	@OneToMany(fetch=FetchType.EAGER, mappedBy="epaypaTTemplate", cascade={CascadeType.ALL})
	private List<EpaypaTColonnaTemplate> epaypaTColonnaTemplates;

	//uni-directional many-to-one association to EpaypaDTipoFormatoOutput
	@ManyToOne
	@JoinColumn(name="id_tipo_formato_output")
	private EpaypaDTipoFormatoOutput epaypaDTipoFormatoOutput;

	public EpaypaTTemplate() {
	}

	public Long getIdTemplate() {
		return idTemplate;
	}

	public void setIdTemplate(Long idTemplate) {
		this.idTemplate = idTemplate;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EpaypaDTipoFlusso getEpaypaDTipoFlusso() {
		return epaypaDTipoFlusso;
	}

	public void setEpaypaDTipoFlusso(EpaypaDTipoFlusso epaypaDTipoFlusso) {
		this.epaypaDTipoFlusso = epaypaDTipoFlusso;
	}

	public EpaypaTEnte getEpaypaTEnte() {
		return epaypaTEnte;
	}

	public void setEpaypaTEnte(EpaypaTEnte epaypaTEnte) {
		this.epaypaTEnte = epaypaTEnte;
	}

	public List<EpaypaTColonnaTemplate> getEpaypaTColonnaTemplates() {
		return epaypaTColonnaTemplates;
	}

	public void setEpaypaTColonnaTemplates(List<EpaypaTColonnaTemplate> epaypaTColonnaTemplates) {
		this.epaypaTColonnaTemplates = epaypaTColonnaTemplates;
	}

	public EpaypaDTipoFormatoOutput getEpaypaDTipoFormatoOutput() {
		return epaypaDTipoFormatoOutput;
	}

	public void setEpaypaDTipoFormatoOutput(EpaypaDTipoFormatoOutput epaypaDTipoFormatoOutput) {
		this.epaypaDTipoFormatoOutput = epaypaDTipoFormatoOutput;
	}

}

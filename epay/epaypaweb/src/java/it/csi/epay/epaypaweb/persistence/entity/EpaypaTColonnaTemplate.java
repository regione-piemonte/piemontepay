/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaypa_t_colonna_template database table.
 *
 */
@Entity
@Table(name="epaypa_t_colonna_template")
@NamedQueries({
	@NamedQuery(name="EpaypaTColonnaTemplate.findAll",
		query="SELECT e FROM EpaypaTColonnaTemplate e"),
	@NamedQuery(name="EpaypaTColonnaTemplate.findAllByIdTemplate",
		query="SELECT e FROM EpaypaTColonnaTemplate e WHERE e.epaypaTTemplate.idTemplate = :idTemplate ORDER BY e.posizioneOrdinale")
})
public class EpaypaTColonnaTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_colonna_template")
	private Long idColonnaTemplate;

	private String intestazione;

	@Column(name="posizione_ordinale")
	private Integer posizioneOrdinale;

	//uni-directional many-to-one association to EpaypaTTemplate
	@ManyToOne
	@JoinColumn(name="id_template")
	private EpaypaTTemplate epaypaTTemplate;

	//uni-directional many-to-one association to EpaypaTCampoFlusso
	@ManyToOne
	@JoinColumn(name="id_campo_flusso")
	private EpaypaTCampoFlusso epaypaTCampoFlusso;

	public EpaypaTColonnaTemplate() {
	}

	public Long getIdColonnaTemplate() {
		return this.idColonnaTemplate;
	}

	public void setIdColonnaTemplate(Long idColonnaTemplate) {
		this.idColonnaTemplate = idColonnaTemplate;
	}

	public String getIntestazione() {
		return this.intestazione;
	}

	public void setIntestazione(String intestazione) {
		this.intestazione = intestazione;
	}

	public Integer getPosizioneOrdinale() {
		return this.posizioneOrdinale;
	}

	public void setPosizioneOrdinale(Integer posizioneOrdinale) {
		this.posizioneOrdinale = posizioneOrdinale;
	}

	public EpaypaTTemplate getEpaypaTTemplate() {
		return this.epaypaTTemplate;
	}

	public void setEpaypaTTemplate(EpaypaTTemplate epaypaTTemplate) {
		this.epaypaTTemplate = epaypaTTemplate;
	}

	public EpaypaTCampoFlusso getEpaypaTCampoFlusso() {
		return this.epaypaTCampoFlusso;
	}

	public void setEpaypaTCampoFlusso(EpaypaTCampoFlusso epaypaTCampoFlusso) {
		this.epaypaTCampoFlusso = epaypaTCampoFlusso;
	}

}

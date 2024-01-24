/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the mdp_statistiche database table.
 * 
 */
@Entity
@Table(name="mdp_statistiche")
@NamedQuery(name="MdpStatistiche.findAll", query="SELECT m FROM MdpStatistiche m")
public class MdpStatistiche implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MDP_STATISTICHE_ID_GENERATOR", sequenceName="MDP_STATISTICHE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MDP_STATISTICHE_ID_GENERATOR")
	private Long id;

	@Column(name="application_id")
	private String applicationId;

	private String csv;

	@Column(name="insert_date")
	private Timestamp insertDate;

	@Column(name="nome_report")
	private String nomeReport;

	@Column(name="periodo_report")
	private String periodoReport;

	public MdpStatistiche() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getCsv() {
		return this.csv;
	}

	public void setCsv(String csv) {
		this.csv = csv;
	}

	public Timestamp getInsertDate() {
		return this.insertDate;
	}

	public void setInsertDate(Timestamp insertDate) {
		this.insertDate = insertDate;
	}

	public String getNomeReport() {
		return this.nomeReport;
	}

	public void setNomeReport(String nomeReport) {
		this.nomeReport = nomeReport;
	}

	public String getPeriodoReport() {
		return this.periodoReport;
	}

	public void setPeriodoReport(String periodoReport) {
		this.periodoReport = periodoReport;
	}

}

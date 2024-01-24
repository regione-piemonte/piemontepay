/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="cbl_t_scheduled_job",schema="epaymodric")
public class CblTScheduledJob implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -853498118636062303L;

	@Id
	@Column(name="id_scheduled_job")
	private Integer idScheduledJob;

	@Column(name="attivo")
	private Boolean active;

	@Column(name="dt_inizio_ultima_esecuzione")
	private Timestamp inizioUltimaEsecuzione;
	
	@Column(name="dt_fine_ultima_esecuzione")
	private Timestamp fineUltimaEsecuzione;

	@Column(name="intervallo")
	private Long interval;

	@Column(name="parametri")
	private String params;

	@Column(name="schedulazione")
	private String scheduleExpression;

	@Column(name="stato")
	private String status;

	@Column(name="tipo_job")
	private String jobType;

	public CblTScheduledJob() {
		super();
	}

	public Integer getIdScheduledJob() {
		return idScheduledJob;
	}

	public void setIdScheduledJob(Integer idScheduledJob) {
		this.idScheduledJob = idScheduledJob;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Timestamp getInizioUltimaEsecuzione() {
		return inizioUltimaEsecuzione;
	}

	public void setInizioUltimaEsecuzione(Timestamp inizioUltimaEsecuzione) {
		this.inizioUltimaEsecuzione = inizioUltimaEsecuzione;
	}

	public Timestamp getFineUltimaEsecuzione() {
		return fineUltimaEsecuzione;
	}

	public void setFineUltimaEsecuzione(Timestamp fineUltimaEsecuzione) {
		this.fineUltimaEsecuzione = fineUltimaEsecuzione;
	}

	public Long getInterval() {
		return interval;
	}

	public void setInterval(Long interval) {
		this.interval = interval;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getScheduleExpression() {
		return scheduleExpression;
	}

	public void setScheduleExpression(String scheduleExpression) {
		this.scheduleExpression = scheduleExpression;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
}

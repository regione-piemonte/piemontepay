/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the epaywso_t_scheduled_job database table.
 * 
 */
@Entity
@Table(name="epaywso_t_scheduled_job")
@NamedQueries({
		@NamedQuery(
				name = "EPaywsoTScheduledJob.findAllJobs",
				query= "SELECT e "
						+ "FROM EPaywsoTScheduledJob e "),
		@NamedQuery(
				name = "EPaywsoTScheduledJob.findOneActiveJob",
				query= "SELECT e "
						+ "FROM EPaywsoTScheduledJob e "
						+ "WHERE e.active = true "
						+ "AND e.jobType = :jobType "),
		@NamedQuery(
				name = "EPaywsoTScheduledJob.findNextActiveJob",
				query= "SELECT e "
						+ "FROM EPaywsoTScheduledJob e "
						+ "WHERE e.active = true "
						+ "AND (EXTRACT(epoch FROM (current_timestamp - e.ultimaEsecuzione)) > e.interval OR e.ultimaEsecuzione IS NULL) ")
})
public class EPaywsoTScheduledJob implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_scheduled_job")
	private Integer idScheduledJob;

	@Column(name="attivo")
	private Boolean active;

	@Column(name="dt_ultima_esecuzione")
	private Timestamp ultimaEsecuzione;

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

	public EPaywsoTScheduledJob() {
	}

	public Integer getIdScheduledJob() {
		return this.idScheduledJob;
	}

	public void setIdScheduledJob(Integer idScheduledJob) {
		this.idScheduledJob = idScheduledJob;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Timestamp getUltimaEsecuzione() {
		return this.ultimaEsecuzione;
	}

	public void setUltimaEsecuzione(Timestamp ultimaEsecuzione) {
		this.ultimaEsecuzione = ultimaEsecuzione;
	}

	public Long getInterval() {
		return this.interval;
	}

	public void setInterval(Long interval) {
		this.interval = interval;
	}

	public String getParams() {
		return this.params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getScheduleExpression() {
		return this.scheduleExpression;
	}

	public void setScheduleExpression(String scheduleExpression) {
		this.scheduleExpression = scheduleExpression;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJobType() {
		return this.jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

}

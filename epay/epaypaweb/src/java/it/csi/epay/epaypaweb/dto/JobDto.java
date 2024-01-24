/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

import it.csi.epay.epaypaweb.enumeration.JobTypeEnum;
import it.csi.epay.epaypaweb.util.Util;

public class JobDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private boolean active;
	private JobTypeEnum jobTypeEmum;
	private Properties params;
	private Properties status;
	private Date ultimaEsecuzione;
	private String scheduleExpression;
	
	public JobDto(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public JobTypeEnum getJobTypeEmum() {
		return jobTypeEmum;
	}

	public void setJobTypeEmum(JobTypeEnum jobTypeEmum) {
		this.jobTypeEmum = jobTypeEmum;
	}

	public Properties getParams() {
		return params;
	}

	public void setParams(Properties params) {
		this.params = params;
	}

	public Properties getStatus() {
		return status;
	}

	public void setStatus(Properties status) {
		this.status = status;
	}

	public Date getUltimaEsecuzione() {
		return ultimaEsecuzione;
	}

	public void setUltimaEsecuzione(Date ultimaEsecuzione) {
		this.ultimaEsecuzione = ultimaEsecuzione;
	}

	public String getScheduleExpression() {
		return scheduleExpression;
	}

	public void setScheduleExpression(String scheduleExpression) {
		this.scheduleExpression = scheduleExpression;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("id:").append(id).append(COMMA);
		s.append("active:").append(active).append(COMMA);
		s.append("jobTypeEmum:").append(jobTypeEmum).append(COMMA);
		s.append("params:").append(params).append(COMMA);
		s.append("status:").append(status).append(COMMA);
		s.append("ultimaEsecuzione:").append(Util.date2strdatetime(ultimaEsecuzione)).append(COMMA);
		s.append("scheduleExpression:").append(Util.quote(scheduleExpression)).append(COMMA);
		s.append(" }");
		return s.toString();
	}

}

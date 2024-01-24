/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.bo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.annotation.Generated;

public class ScheduledJob implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private boolean active;
	private JobTypeEnum jobTypeEmum;
	private Properties params;
	private Properties status;
	private Date inizioUltimaEsecuzione;
	private Date fineUltimaEsecuzione;
	private String scheduleExpression;

	@Generated("SparkTools")
	private ScheduledJob(Builder builder) {
		this.id = builder.id;
		this.active = builder.active;
		this.jobTypeEmum = builder.jobTypeEmum;
		this.params = builder.params;
		this.status = builder.status;
		this.inizioUltimaEsecuzione = builder.inizioUltimaEsecuzione;
		this.fineUltimaEsecuzione = builder.fineUltimaEsecuzione;
		this.scheduleExpression = builder.scheduleExpression;
	}
	
	public ScheduledJob() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Date getInizioUltimaEsecuzione() {
		return inizioUltimaEsecuzione;
	}
	public void setInizioUltimaEsecuzione(Date inizioUltimaEsecuzione) {
		this.inizioUltimaEsecuzione = inizioUltimaEsecuzione;
	}


	public Date getFineUltimaEsecuzione() {
		return fineUltimaEsecuzione;
	}


	public void setFineUltimaEsecuzione(Date fineUltimaEsecuzione) {
		this.fineUltimaEsecuzione = fineUltimaEsecuzione;
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
		s.append("inizioUltimaEsecuzione:").append((inizioUltimaEsecuzione != null ? (new SimpleDateFormat("dd/MM/yyyy")).format(inizioUltimaEsecuzione) : "")).append(COMMA);
		s.append("fineUltimaEsecuzione:").append((fineUltimaEsecuzione != null ? (new SimpleDateFormat("dd/MM/yyyy")).format(fineUltimaEsecuzione) : "")).append(COMMA);
		s.append("scheduleExpression:").append(scheduleExpression).append(COMMA);
		s.append(" }");
		return s.toString();
	}
	/**
	 * Creates builder to build {@link ScheduledJob}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link ScheduledJob}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Integer id;
		private boolean active;
		private JobTypeEnum jobTypeEmum;
		private Properties params;
		private Properties status;
		private Date inizioUltimaEsecuzione;
		private Date fineUltimaEsecuzione;
		private String scheduleExpression;

		private Builder() {
		}

		public Builder withId(Integer id) {
			this.id = id;
			return this;
		}

		public Builder withActive(boolean active) {
			this.active = active;
			return this;
		}

		public Builder withJobTypeEmum(JobTypeEnum jobTypeEmum) {
			this.jobTypeEmum = jobTypeEmum;
			return this;
		}

		public Builder withParams(Properties params) {
			this.params = params;
			return this;
		}

		public Builder withStatus(Properties status) {
			this.status = status;
			return this;
		}

		public Builder withInizioUltimaEsecuzione(Date inizioUltimaEsecuzione) {
			this.inizioUltimaEsecuzione = inizioUltimaEsecuzione;
			return this;
		}

		public Builder withFineUltimaEsecuzione(Date fineUltimaEsecuzione) {
			this.fineUltimaEsecuzione = fineUltimaEsecuzione;
			return this;
		}

		public Builder withScheduleExpression(String scheduleExpression) {
			this.scheduleExpression = scheduleExpression;
			return this;
		}

		public ScheduledJob build() {
			return new ScheduledJob(this);
		}
	}
	
	
	
}

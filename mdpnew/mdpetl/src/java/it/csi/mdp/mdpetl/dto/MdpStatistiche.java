/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.dto;

import java.io.Serializable;
import java.sql.Date;

public class MdpStatistiche implements Serializable {
	private static final long serialVersionUID = -4385798072999465853L;
	
	private Long id;
	private Date insertDate;
	private String applicationId;
	private String nomeReport;
	private String periodoReport;
	private String csv;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the insertDate
	 */
	public Date getInsertDate() {
		return insertDate;
	}
	/**
	 * @param insertDate the insertDate to set
	 */
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	/**
	 * @return the applicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}
	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	/**
	 * @return the nomeReport
	 */
	public String getNomeReport() {
		return nomeReport;
	}
	/**
	 * @param nomeReport the nomeReport to set
	 */
	public void setNomeReport(String nomeReport) {
		this.nomeReport = nomeReport;
	}
	/**
	 * @return the periodoReport
	 */
	public String getPeriodoReport() {
		return periodoReport;
	}
	/**
	 * @param periodoReport the periodoReport to set
	 */
	public void setPeriodoReport(String periodoReport) {
		this.periodoReport = periodoReport;
	}
	/**
	 * @return the csv
	 */
	public String getCsv() {
		return csv;
	}
	/**
	 * @param csv the csv to set
	 */
	public void setCsv(String csv) {
		this.csv = csv;
	}

}

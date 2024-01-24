/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.dto;

import java.io.Serializable;

public class ReportTransazioni implements Serializable {

	private static final long serialVersionUID = 7276534855983351908L;

	public static final String APPLICATIONNAME = "applicationname";
	public static final String GATEWAY_DESCRIPTION = "gateway_description";
	public static final String COD_STATO = "cod_stato";
	public static final String CONTEGGIO = "conteggio";
	
	private String applicationname;
	private String gatewayDescription;
	private Integer codStato;
	private Long conteggio;
	/**
	 * @return the applicationname
	 */
	public String getApplicationname() {
		return applicationname;
	}
	/**
	 * @param applicationname the applicationname to set
	 */
	public void setApplicationname(String applicationname) {
		this.applicationname = applicationname;
	}
	/**
	 * @return the gatewayDescription
	 */
	public String getGatewayDescription() {
		return gatewayDescription;
	}
	/**
	 * @param gatewayDescription the gatewayDescription to set
	 */
	public void setGatewayDescription(String gatewayDescription) {
		this.gatewayDescription = gatewayDescription;
	}
	/**
	 * @return the codStato
	 */
	public Integer getCodStato() {
		return codStato;
	}
	/**
	 * @param codStato the codStato to set
	 */
	public void setCodStato(Integer codStato) {
		this.codStato = codStato;
	}
	/**
	 * @return the conteggio
	 */
	public Long getConteggio() {
		return conteggio;
	}
	/**
	 * @param conteggio the conteggio to set
	 */
	public void setConteggio(Long conteggio) {
		this.conteggio = conteggio;
	}
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;

public class StatisticaApplicazioneTransazione implements Serializable {
	

	private static final long serialVersionUID = 3241226158888099655L;
	String applicationId;
	Integer codStato;
	Integer numXstato;
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
	 * @return the numXstato
	 */
	public Integer getNumXstato() {
		return numXstato;
	}
	/**
	 * @param numXstato the numXstato to set
	 */
	public void setNumXstato(Integer numXstato) {
		this.numXstato = numXstato;
	}

	  
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.dto;

import java.util.Map;

public class DatiPagamentoDaIuv extends BaseDTO  {

	private static final long serialVersionUID = 5883703650909305371L;
	
	private String transactionId;
	private String applicationId;
	private String descrizionePSP;
	
	private Map<String, String> mappaAppCustomFields;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getDescrizionePSP() {
		return descrizionePSP;
	}

	public void setDescrizionePSP(String descrizionePSP) {
		this.descrizionePSP = descrizionePSP;
	}

	public Map<String, String> getMappaAppCustomFields() {
		return mappaAppCustomFields;
	}

	public void setMappaAppCustomFields(Map<String, String> mappaAppCustomFields) {
		this.mappaAppCustomFields = mappaAppCustomFields;
	}
}

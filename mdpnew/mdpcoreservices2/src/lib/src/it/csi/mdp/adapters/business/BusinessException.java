/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.adapters.business;

import java.util.List;

public class BusinessException extends Exception{
	
	private List<String> dettagli;
	
	private static final long serialVersionUID = -3681841526009972053L;
	
	public BusinessException() {
		super("");
	}

	public List<String> getDettagli() {
		return dettagli;
	}

	public void setDettagli(List<String> dettagli) {
		this.dettagli = dettagli;
	}
}

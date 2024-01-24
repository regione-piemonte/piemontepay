/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.interfaces.exception;

public class ComponentiImportoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codice;
	
	public String getCodice() {
		return codice;
	}

	public ComponentiImportoException(String codice, String message) {
		super(message);
		this.codice = codice;
	}
	
	

}

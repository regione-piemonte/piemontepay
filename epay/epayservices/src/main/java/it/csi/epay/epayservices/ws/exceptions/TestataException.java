/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.ws.exceptions;

public class TestataException extends Exception {
	private static final long serialVersionUID = 4700757887070254101L;
	
	String code;
	
	public TestataException(final String code, final String message) {
		super(message);
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

}

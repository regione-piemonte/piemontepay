/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.exception;

public class FacadeXmlMismatchException extends FacadeException {
	private static final long serialVersionUID = 1L;

	private String value;

	public FacadeXmlMismatchException(String name, String value) {
		super(name);
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

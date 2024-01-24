/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.exception;

public class FacadeXmlChoiceException extends FacadeException {
	private static final long serialVersionUID = 1L;

	private int n;

	public FacadeXmlChoiceException(String name, int n) {
		super(name);
		this.n = n;
	}

	public int getN() {
		return n;
	}

}

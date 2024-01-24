/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.exception;

public class FacadeXmlLenghtException extends FacadeException {
	private static final long serialVersionUID = 1L;

	private int minlenght;
	private int maxlenght;

	public FacadeXmlLenghtException(String name, int minlenght, int maxlenght) {
		super(name);
		this.minlenght = minlenght;
		this.maxlenght = maxlenght;
	}

	public int getMinlenght() {
		return minlenght;
	}

	public int getMaxnlenght() {
		return maxlenght;
	}
}

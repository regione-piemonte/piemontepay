/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.interfaces.exception;

public class NoDataException extends Exception {

	private static final long serialVersionUID = 2277495205608317490L;

	public NoDataException() {
		super();
	}

	public NoDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoDataException(String message) {
		super(message);
	}

	public NoDataException(Throwable cause) {
		super(cause);
	}	
}

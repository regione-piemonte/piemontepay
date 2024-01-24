/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.interfaces.exception;

public class MoreResultException extends Exception {

	private static final long serialVersionUID = 2277495205608317490L;

	public MoreResultException() {
		super();
	}

	public MoreResultException(String message, Throwable cause) {
		super(message, cause);
	}

	public MoreResultException(String message) {
		super(message);
	}

	public MoreResultException(Throwable cause) {
		super(cause);
	}	
}

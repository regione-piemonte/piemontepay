/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.common.exceptions;

public class ClientAuthenticationFailureException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6661605560692802706L;

	public ClientAuthenticationFailureException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClientAuthenticationFailureException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ClientAuthenticationFailureException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ClientAuthenticationFailureException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ClientAuthenticationFailureException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

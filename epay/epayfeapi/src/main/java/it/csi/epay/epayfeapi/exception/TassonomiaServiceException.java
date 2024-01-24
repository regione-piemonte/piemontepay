/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.exception;

@SuppressWarnings ( "unused" )
public class TassonomiaServiceException extends Exception {

	private static final long serialVersionUID = -8961652050850359297L;

	private final String code;

	public TassonomiaServiceException ( final String code, final String message ) {
		super ( message );
		this.code = code;
	}

	public TassonomiaServiceException ( final String code, final String message, Throwable cause ) {
		super ( message, cause );
		this.code = code;
	}

	public String getCode () {
		return code;
	}
}

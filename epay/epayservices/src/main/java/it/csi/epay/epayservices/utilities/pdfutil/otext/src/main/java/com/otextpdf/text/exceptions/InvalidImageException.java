/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.exceptions;

public class InvalidImageException extends RuntimeException {

	private static final long serialVersionUID = -1319471492541702697L;

	private final Throwable cause;

	public InvalidImageException ( final String message ) {
		this ( message, null );
	}

	public InvalidImageException ( String message, Throwable cause ) {
		super ( message );
		this.cause = cause;
	}

	public Throwable getCause () {
		return cause;
	}
}

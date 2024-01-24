/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.exceptions;

import java.io.IOException;


public class InvalidPdfException extends IOException {

	private static final long serialVersionUID = -2319614911517026938L;

	private final Throwable cause;

	public InvalidPdfException ( String message ) {
		this ( message, null );
	}

	public InvalidPdfException ( String message, Throwable cause ) {
		super ( message );
		this.cause = cause;
	}

	public Throwable getCause () {
		return cause;
	}
}

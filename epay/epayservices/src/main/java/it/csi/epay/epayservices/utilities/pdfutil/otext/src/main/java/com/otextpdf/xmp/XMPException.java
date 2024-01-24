/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp;

public class XMPException extends Exception {

	private static final long serialVersionUID = -5677115378183178027L;

	private final int errorCode;

	public XMPException ( String message, int errorCode ) {
		super ( message );
		this.errorCode = errorCode;
	}

	public XMPException ( String message, int errorCode, Throwable t ) {
		super ( message, t );
		this.errorCode = errorCode;
	}

	public int getErrorCode () {
		return errorCode;
	}
}

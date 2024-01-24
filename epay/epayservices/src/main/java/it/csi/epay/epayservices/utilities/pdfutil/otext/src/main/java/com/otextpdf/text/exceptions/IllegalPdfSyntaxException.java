/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.exceptions;

public class IllegalPdfSyntaxException extends IllegalArgumentException {

	private static final long serialVersionUID = -643024246596031671L;

	public IllegalPdfSyntaxException ( String message ) {
		super ( message );
	}
}

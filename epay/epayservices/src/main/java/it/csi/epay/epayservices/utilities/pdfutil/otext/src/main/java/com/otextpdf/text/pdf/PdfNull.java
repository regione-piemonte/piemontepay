/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

public class PdfNull extends PdfObject {

	public static final PdfNull PDFNULL = new PdfNull ();

	private static final String CONTENT = "null";

	public PdfNull () {
		super ( NULL, CONTENT );
	}

	public String toString () {
		return "null";
	}
}

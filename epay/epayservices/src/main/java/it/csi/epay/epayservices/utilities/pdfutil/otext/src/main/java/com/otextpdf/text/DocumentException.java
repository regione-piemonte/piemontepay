/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

public class DocumentException extends Exception {

	private static final long serialVersionUID = -2191131489390840739L;

	public DocumentException ( Exception ex ) {
		super ( ex );
	}

	public DocumentException () {
		super ();
	}

	public DocumentException ( String message ) {
		super ( message );
	}
}

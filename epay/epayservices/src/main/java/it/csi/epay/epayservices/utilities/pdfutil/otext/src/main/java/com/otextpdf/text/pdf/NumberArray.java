/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

public class NumberArray extends PdfArray {

	public NumberArray ( float... numbers ) {
		super ();
		for ( float f : numbers ) {
			add ( new PdfNumber ( f ) );
		}
	}

}

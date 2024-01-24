/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

public class PdfBorderArray extends PdfArray {

	public PdfBorderArray ( float hRadius, float vRadius, float width ) {
		this ( hRadius, vRadius, width, null );
	}

	public PdfBorderArray ( float hRadius, float vRadius, float width, PdfDashPattern dash ) {
		super ( new PdfNumber ( hRadius ) );
		add ( new PdfNumber ( vRadius ) );
		add ( new PdfNumber ( width ) );
		if ( dash != null )
			add ( dash );
	}
}

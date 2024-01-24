/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;


class PdfColor extends PdfArray {

	PdfColor ( int red, int green, int blue ) {
		super ( new PdfNumber ( (double) ( red & 0xFF ) / 0xFF ) );
		add ( new PdfNumber ( (double) ( green & 0xFF ) / 0xFF ) );
		add ( new PdfNumber ( (double) ( blue & 0xFF ) / 0xFF ) );
	}

	PdfColor ( BaseColor color ) {
		this ( color.getRed (), color.getGreen (), color.getBlue () );
	}
}

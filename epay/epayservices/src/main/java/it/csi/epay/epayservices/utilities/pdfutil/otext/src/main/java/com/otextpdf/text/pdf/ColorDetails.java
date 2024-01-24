/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

class ColorDetails {

	PdfIndirectReference indirectReference;

	PdfName colorName;

	PdfSpotColor spotcolor;

	ColorDetails ( PdfName colorName, PdfIndirectReference indirectReference, PdfSpotColor scolor ) {
		this.colorName = colorName;
		this.indirectReference = indirectReference;
		this.spotcolor = scolor;
	}

	public PdfIndirectReference getIndirectReference () {
		return indirectReference;
	}

	PdfName getColorName () {
		return colorName;
	}

	public PdfObject getSpotColor ( PdfWriter writer ) {
		return spotcolor.getSpotObject ( writer );
	}
}

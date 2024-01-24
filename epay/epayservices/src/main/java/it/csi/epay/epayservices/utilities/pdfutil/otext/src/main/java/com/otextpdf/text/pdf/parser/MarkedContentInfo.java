/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.parser;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfDictionary;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;


public class MarkedContentInfo {

	private final PdfName tag;

	public MarkedContentInfo ( PdfName tag, PdfDictionary dictionary ) {
		this.tag = tag;
		PdfDictionary dictionary1 = dictionary != null ? dictionary : new PdfDictionary ();
	}

	public PdfName getTag () {
		return tag;
	}

}

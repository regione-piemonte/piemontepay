/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.parser;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfReader;

import java.io.IOException;


public final class PdfTextExtractor {

	private PdfTextExtractor () {
	}

	public static String getTextFromPage ( PdfReader reader, int pageNumber, TextExtractionStrategy strategy ) throws IOException {
		PdfReaderContentParser parser = new PdfReaderContentParser ( reader );
		return parser.processContent ( pageNumber, strategy ).getResultantText ();

	}

}

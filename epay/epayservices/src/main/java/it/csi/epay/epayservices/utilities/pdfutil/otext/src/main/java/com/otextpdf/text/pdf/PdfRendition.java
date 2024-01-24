/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import java.io.IOException;


public class PdfRendition extends PdfDictionary {

	PdfRendition ( String file, PdfFileSpecification fs, String mimeType ) throws IOException {
		put ( PdfName.S, new PdfName ( "MR" ) );
		put ( PdfName.N, new PdfString ( "Rendition for " + file ) );
		put ( PdfName.C, new PdfMediaClipData ( file, fs, mimeType ) );
	}

}

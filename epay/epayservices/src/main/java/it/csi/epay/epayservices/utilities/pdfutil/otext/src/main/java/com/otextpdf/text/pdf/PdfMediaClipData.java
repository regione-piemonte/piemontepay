/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import java.io.IOException;


public class PdfMediaClipData extends PdfDictionary {

	PdfMediaClipData ( String file, PdfFileSpecification fs, String mimeType ) throws IOException {
		put ( PdfName.TYPE, new PdfName ( "MediaClip" ) );
		put ( PdfName.S, new PdfName ( "MCD" ) );
		put ( PdfName.N, new PdfString ( "Media clip for " + file ) );
		put ( new PdfName ( "CT" ), new PdfString ( mimeType ) );
		PdfDictionary dic = new PdfDictionary ();
		dic.put ( new PdfName ( "TF" ), new PdfString ( "TEMPACCESS" ) );
		put ( new PdfName ( "P" ), dic );
		put ( PdfName.D, fs.getReference () );
	}
}

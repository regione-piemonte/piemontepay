/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Utilities;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.IntHashtable;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfNumber;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfString;


public class CMapCidUni extends AbstractCMap {

	private final IntHashtable map = new IntHashtable ( 65537 );

	@Override
	void addChar ( PdfString mark, PdfObject code ) {
		if ( !( code instanceof PdfNumber ) )
			return;
		int codepoint;
		String s = decodeStringToUnicode ( mark );
		if ( Utilities.isSurrogatePair ( s, 0 ) )
			codepoint = Utilities.convertToUtf32 ( s, 0 );
		else
			codepoint = s.charAt ( 0 );
		map.put ( ( (PdfNumber) code ).intValue (), codepoint );
	}

	public int lookup ( int character ) {
		return map.get ( character );
	}
}

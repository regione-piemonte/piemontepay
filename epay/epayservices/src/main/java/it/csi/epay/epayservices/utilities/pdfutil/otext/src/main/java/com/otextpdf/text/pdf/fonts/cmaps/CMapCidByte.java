/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfNumber;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfString;

import java.util.HashMap;


public class CMapCidByte extends AbstractCMap {

	private final HashMap<Integer, byte[]> map = new HashMap<> ();

	private final byte[] EMPTY = {};

	@Override
	void addChar ( PdfString mark, PdfObject code ) {
		if ( !( code instanceof PdfNumber ) )
			return;
		byte[] ser = decodeStringToByte ( mark );
		map.put ( ( (PdfNumber) code ).intValue (), ser );
	}

	public byte[] lookup ( int cid ) {
		byte[] ser = map.get ( cid );
		if ( ser == null )
			return EMPTY;
		else
			return ser;
	}
}

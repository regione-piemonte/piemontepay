/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfNumber;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfString;

import java.util.ArrayList;


public class CMapByteCid extends AbstractCMap {

	private final ArrayList<char[]> planes = new ArrayList<> ();

	public CMapByteCid () {
		planes.add ( new char[256] );
	}

	@Override
	void addChar ( PdfString mark, PdfObject code ) {
		if ( !( code instanceof PdfNumber ) )
			return;
		encodeSequence ( decodeStringToByte ( mark ), (char) ( (PdfNumber) code ).intValue () );
	}

	private void encodeSequence ( byte[] seqs, char cid ) {
		int size = seqs.length - 1;
		int nextPlane = 0;
		for ( int idx = 0; idx < size; ++idx ) {
			char[] plane = planes.get ( nextPlane );
			int one = seqs[idx] & 0xff;
			char c = plane[one];
			if ( c != 0 && ( c & 0x8000 ) == 0 )
				throw new RuntimeException ( MessageLocalization.getComposedMessage ( "inconsistent.mapping" ) );
			if ( c == 0 ) {
				planes.add ( new char[256] );
				c = (char) ( planes.size () - 1 | 0x8000 );
				plane[one] = c;
			}
			nextPlane = c & 0x7fff;
		}
		char[] plane = planes.get ( nextPlane );
		int one = seqs[size] & 0xff;
		char c = plane[one];
		if ( ( c & 0x8000 ) != 0 )
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "inconsistent.mapping" ) );
		plane[one] = cid;
	}

	public int decodeSingle ( CMapSequence seq ) {
		int end = seq.off + seq.len;
		int currentPlane = 0;
		while ( seq.off < end ) {
			int one = seq.seq[seq.off++] & 0xff;
			--seq.len;
			char[] plane = planes.get ( currentPlane );
			int cid = plane[one];
			if ( ( cid & 0x8000 ) == 0 ) {
				return cid;
			} else
				currentPlane = cid & 0x7fff;
		}
		return -1;
	}

	public String decodeSequence ( CMapSequence seq ) {
		StringBuilder sb = new StringBuilder ();
		int cid;
		while ( ( cid = decodeSingle ( seq ) ) >= 0 ) {
			sb.append ( (char) cid );
		}
		return sb.toString ();
	}
}

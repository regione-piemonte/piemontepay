/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfArray;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfEncodings;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfNumber;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfString;


public abstract class AbstractCMap {

	private String registry;

	private String ordering;

	private int supplement;

	public static byte[] decodeStringToByte ( PdfString s ) {
		byte[] b = s.getBytes ();
		byte[] br = new byte[b.length];
		System.arraycopy ( b, 0, br, 0, b.length );
		return br;
	}

	void setName ( String cmapName ) {
	}

	public String getOrdering () {
		return ordering;
	}

	void setOrdering ( String ordering ) {
		this.ordering = ordering;
	}

	public String getRegistry () {
		return registry;
	}

	void setRegistry ( String registry ) {
		this.registry = registry;
	}

	public int getSupplement () {
		return supplement;
	}

	void setSupplement ( int supplement ) {
		this.supplement = supplement;
	}

	abstract void addChar ( PdfString mark, PdfObject code );

	void addRange ( PdfString from, PdfString to, PdfObject code ) {
		byte[] a1 = decodeStringToByte ( from );
		byte[] a2 = decodeStringToByte ( to );
		if ( a1.length != a2.length || a1.length == 0 )
			throw new IllegalArgumentException ( "Invalid map." );
		byte[] sout = null;
		if ( code instanceof PdfString )
			sout = decodeStringToByte ( (PdfString) code );
		int start = a1[a1.length - 1] & 0xff;
		int end = a2[a2.length - 1] & 0xff;
		for ( int k = start; k <= end; ++k ) {
			a1[a1.length - 1] = (byte) k;
			PdfString s = new PdfString ( a1 );
			s.setHexWriting ( true );
			if ( code instanceof PdfArray ) {
				addChar ( s, ( (PdfArray) code ).getPdfObject ( k - start ) );
			} else if ( code instanceof PdfNumber ) {
				int nn = ( (PdfNumber) code ).intValue () + k - start;
				addChar ( s, new PdfNumber ( nn ) );
			} else if ( code instanceof PdfString ) {
				PdfString s1 = new PdfString ( sout );
				s1.setHexWriting ( true );
				++sout[sout.length - 1];
				addChar ( s, s1 );
			}
		}
	}

	public String decodeStringToUnicode ( PdfString ps ) {
		if ( ps.isHexWriting () )
			return PdfEncodings.convertToString ( ps.getBytes (), "UnicodeBigUnmarked" );
		else
			return ps.toUnicodeString ();
	}
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;

import java.io.IOException;
import java.util.HashMap;


public class PdfPageLabels {

	public static final int DECIMAL_ARABIC_NUMERALS = 0;

	public static final int UPPERCASE_ROMAN_NUMERALS = 1;

	public static final int LOWERCASE_ROMAN_NUMERALS = 2;

	public static final int UPPERCASE_LETTERS = 3;

	public static final int LOWERCASE_LETTERS = 4;

	public static final int EMPTY = 5;

	static PdfName[] numberingStyle = new PdfName[] { PdfName.D, PdfName.R,
					new PdfName ( "r" ), PdfName.A, new PdfName ( "a" ) };

	private final HashMap<Integer, PdfDictionary> map;

	public PdfPageLabels () {
		map = new HashMap<> ();
		addPageLabel ( 1, DECIMAL_ARABIC_NUMERALS, null, 1 );
	}

	public void addPageLabel ( int page, int numberStyle, String text, int firstPage ) {
		if ( page < 1 || firstPage < 1 )
			throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "in.a.page.label.the.page.numbers.must.be.greater.or.equal.to.1" ) );
		PdfDictionary dic = new PdfDictionary ();
		if ( numberStyle >= 0 && numberStyle < numberingStyle.length )
			dic.put ( PdfName.S, numberingStyle[numberStyle] );
		if ( text != null )
			dic.put ( PdfName.P, new PdfString ( text, PdfObject.TEXT_UNICODE ) );
		if ( firstPage != 1 )
			dic.put ( PdfName.ST, new PdfNumber ( firstPage ) );
		map.put ( page - 1, dic );
	}

	public PdfDictionary getDictionary ( PdfWriter writer ) {
		try {
			return PdfNumberTree.writeTree ( map, writer );
		} catch ( IOException e ) {
			throw new ExceptionConverter ( e );
		}
	}

}

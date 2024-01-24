/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfString;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class CMapToUnicode extends AbstractCMap {

	private final Map<Integer, String> singleByteMappings = new HashMap<> ();

	private final Map<Integer, String> doubleByteMappings = new HashMap<> ();

	public CMapToUnicode () {
	}

	public String lookup ( byte[] code, int offset, int length ) {

		String result = null;
		int key;
		if ( length == 1 ) {

			key = code[offset] & 0xff;
			result = singleByteMappings.get ( key );
		} else if ( length == 2 ) {
			int intKey = code[offset] & 0xff;
			intKey <<= 8;
			intKey += code[offset + 1] & 0xff;
			key = intKey;

			result = doubleByteMappings.get ( key );
		}

		return result;
	}

	public Map<Integer, Integer> createReverseMapping () throws IOException {
		Map<Integer, Integer> result = new HashMap<> ();
		for ( Map.Entry<Integer, String> entry : singleByteMappings.entrySet () ) {
			result.put ( convertToInt ( entry.getValue () ), entry.getKey () );
		}
		for ( Map.Entry<Integer, String> entry : doubleByteMappings.entrySet () ) {
			result.put ( convertToInt ( entry.getValue () ), entry.getKey () );
		}
		return result;
	}

	public Map<Integer, Integer> createDirectMapping () {
		Map<Integer, Integer> result = new HashMap<> ();
		for ( Map.Entry<Integer, String> entry : singleByteMappings.entrySet () ) {
			result.put ( entry.getKey (), convertToInt ( entry.getValue () ) );
		}
		for ( Map.Entry<Integer, String> entry : doubleByteMappings.entrySet () ) {
			result.put ( entry.getKey (), convertToInt ( entry.getValue () ) );
		}
		return result;
	}

	private int convertToInt ( String s ) {
		byte[] b = s.getBytes ( StandardCharsets.UTF_16BE );
		int value = 0;
		for ( int i = 0; i < b.length - 1; i++ ) {
			value += b[i] & 0xff;
			value <<= 8;
		}
		value += b[b.length - 1] & 0xff;
		return value;
	}

	void addChar ( int cid, String uni ) {
		doubleByteMappings.put ( cid, uni );
	}

	@Override
	void addChar ( PdfString mark, PdfObject code ) {
		try {
			byte[] src = mark.getBytes ();
			String dest = createStringFromBytes ( code.getBytes () );
			if ( src.length == 1 ) {
				singleByteMappings.put ( src[0] & 0xff, dest );
			} else if ( src.length == 2 ) {
				int intSrc = src[0] & 0xFF;
				intSrc <<= 8;
				intSrc |= src[1] & 0xFF;
				doubleByteMappings.put ( intSrc, dest );
			} else {
				throw new IOException ( MessageLocalization.getComposedMessage ( "mapping.code.should.be.1.or.two.bytes.and.not.1", src.length ) );
			}
		} catch ( Exception ex ) {
			throw new ExceptionConverter ( ex );
		}
	}

	private String createStringFromBytes ( byte[] bytes ) {
		String retval;
		if ( bytes.length == 1 ) {
			retval = new String ( bytes );
		} else {
			retval = new String ( bytes, StandardCharsets.UTF_16BE );
		}
		return retval;
	}
}

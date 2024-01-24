/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.SplitCharacter;


public class DefaultSplitCharacter implements SplitCharacter {

	public static final SplitCharacter DEFAULT = new DefaultSplitCharacter ();

	protected char[] characters;

	public DefaultSplitCharacter () {
		// empty body
	}

	public boolean isSplitCharacter ( int start, int current, int end, char[] cc, PdfChunk[] ck ) {
		char c = getCurrentCharacter ( current, cc, ck );

		if ( characters != null ) {
			for ( char character : characters ) {
				if ( c == character ) {
					return true;
				}
			}
			return false;
		}

		if ( c <= ' ' || c == '-' || c == '\u2010' ) {
			return true;
		}
		if ( c < 0x2002 )
			return false;
		return c <= 0x200b || c >= 0x2e80 && c < 0xd7a0 || c >= 0xf900 && c < 0xfb00 || c >= 0xfe30 && c < 0xfe50 || c >= 0xff61 && c < 0xffa0;
	}

	protected char getCurrentCharacter ( int current, char[] cc, PdfChunk[] ck ) {
		if ( ck == null ) {
			return cc[current];
		}
		return (char) ck[Math.min ( current, ck.length - 1 )].getUnicodeEquivalent ( cc[current] );
	}
}

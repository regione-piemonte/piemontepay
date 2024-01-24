/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PRTokeniser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Set;


public class Utilities {

	@Deprecated
	public static <K, V> Set<K> getKeySet ( final Hashtable<K, V> table ) {
		return table == null ? Collections.emptySet () : table.keySet ();
	}

	public static Object[][] addToArray ( Object[][] original, final Object[] item ) {
		if ( original == null ) {
			original = new Object[1][];
			original[0] = item;
			return original;
		} else {
			Object[][] original2 = new Object[original.length + 1][];
			System.arraycopy ( original, 0, original2, 0, original.length );
			original2[original.length] = item;
			return original2;
		}
	}

	public static String unEscapeURL ( final String src ) {
		StringBuilder bf = new StringBuilder ();
		char[] s = src.toCharArray ();
		for ( int k = 0; k < s.length; ++k ) {
			char c = s[k];
			if ( c == '%' ) {
				if ( k + 2 >= s.length ) {
					bf.append ( c );
					continue;
				}
				int a0 = PRTokeniser.getHex ( s[k + 1] );
				int a1 = PRTokeniser.getHex ( s[k + 2] );
				if ( a0 < 0 || a1 < 0 ) {
					bf.append ( c );
					continue;
				}
				bf.append ( (char) ( a0 * 16 + a1 ) );
				k += 2;
			} else
				bf.append ( c );
		}
		return bf.toString ();
	}

	public static URL toURL ( final String filename ) throws MalformedURLException {
		try {
			return new URL ( filename );
		} catch ( Exception e ) {
			return new File ( filename ).toURI ().toURL ();
		}
	}

	static public void skip ( final InputStream is, int size ) throws IOException {
		long n;
		while ( size > 0 ) {
			n = is.skip ( size );
			if ( n <= 0 )
				break;
			size -= (int) n;
		}
	}

	public static float millimetersToPoints ( final float value ) {
		return inchesToPoints ( millimetersToInches ( value ) );
	}

	public static float millimetersToInches ( final float value ) {
		return value / 25.4f;
	}

	public static float inchesToPoints ( final float value ) {
		return value * 72f;
	}

	public static boolean isSurrogateHigh ( final char c ) {
		return c >= '\ud800' && c <= '\udbff';
	}

	public static boolean isSurrogateLow ( final char c ) {
		return c >= '\udc00' && c <= '\udfff';
	}

	public static boolean isSurrogatePair ( final String text, final int idx ) {
		if ( idx < 0 || idx > text.length () - 2 )
			return false;
		return isSurrogateHigh ( text.charAt ( idx ) ) && isSurrogateLow ( text.charAt ( idx + 1 ) );
	}

	public static boolean isSurrogatePair ( final char[] text, final int idx ) {
		if ( idx < 0 || idx > text.length - 2 )
			return false;
		return isSurrogateHigh ( text[idx] ) && isSurrogateLow ( text[idx + 1] );
	}

	public static int convertToUtf32 ( final char highSurrogate, final char lowSurrogate ) {
		return ( highSurrogate - 0xd800 ) * 0x400 + lowSurrogate - 0xdc00 + 0x10000;
	}

	public static int convertToUtf32 ( final char[] text, final int idx ) {
		return ( text[idx] - 0xd800 ) * 0x400 + text[idx + 1] - 0xdc00 + 0x10000;
	}

	public static int convertToUtf32 ( final String text, final int idx ) {
		return ( text.charAt ( idx ) - 0xd800 ) * 0x400 + text.charAt ( idx + 1 ) - 0xdc00 + 0x10000;
	}

	public static String convertFromUtf32 ( int codePoint ) {
		if ( codePoint < 0x10000 )
			return Character.toString ( (char) codePoint );
		codePoint -= 0x10000;
		return new String ( new char[] { (char) ( codePoint / 0x400 + 0xd800 ), (char) ( codePoint % 0x400 + 0xdc00 ) } );
	}

}

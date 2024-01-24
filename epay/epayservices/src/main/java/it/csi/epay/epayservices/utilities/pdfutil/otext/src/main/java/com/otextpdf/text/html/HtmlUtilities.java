/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.html;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element;

import java.util.HashMap;
import java.util.Properties;
import java.util.StringTokenizer;


public class HtmlUtilities {

	public static final float DEFAULT_FONT_SIZE = 12f;

	public final static int[] FONTSIZES = { 8, 10, 12, 14, 18, 24, 36 };

	private static final HashMap<String, Float> sizes = new HashMap<> ();

	static {
		sizes.put ( "xx-small", 4F );
		sizes.put ( "x-small", 6F );
		sizes.put ( "small", 8F );
		sizes.put ( "medium", 10F );
		sizes.put ( "large", 13F );
		sizes.put ( "x-large", 18F );
		sizes.put ( "xx-large", 26F );
	}

	public static float parseLength ( String string ) {
		return parseLength ( string, DEFAULT_FONT_SIZE );
	}

	public static float parseLength ( String string, float actualFontSize ) {
		if ( string == null )
			return 0f;
		Float fl = sizes.get ( string.toLowerCase () );
		if ( fl != null )
			return fl;
		int pos = 0;
		int length = string.length ();
		boolean ok = true;
		while ( ok && pos < length ) {
			switch ( string.charAt ( pos ) ) {
			case '+':
			case '-':
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case '.':
				pos++;
				break;
			default:
				ok = false;
			}
		}
		if ( pos == 0 )
			return 0f;
		if ( pos == length )
			return Float.parseFloat ( string + "f" );
		float f = Float.parseFloat ( string.substring ( 0, pos ) + "f" );
		string = string.substring ( pos );
		// inches
		if ( string.startsWith ( "in" ) ) {
			return f * 72f;
		}
		// centimeters
		if ( string.startsWith ( "cm" ) ) {
			return ( f / 2.54f ) * 72f;
		}
		// millimeters
		if ( string.startsWith ( "mm" ) ) {
			return ( f / 25.4f ) * 72f;
		}
		// picas
		if ( string.startsWith ( "pc" ) ) {
			return f * 12f;
		}
		// 1em is equal to the current font size
		if ( string.startsWith ( "em" ) ) {
			return f * actualFontSize;
		}
		// one ex is the x-height of a font (x-height is usually about half the
		// font-size)
		if ( string.startsWith ( "ex" ) ) {
			return f * actualFontSize / 2;
		}
		// default: we assume the length was measured in points
		return f;
	}

	public static BaseColor decodeColor ( String s ) {
		if ( s == null )
			return null;
		s = s.toLowerCase ().trim ();
		try {
			return WebColors.getRGBColor ( s );
		} catch ( IllegalArgumentException iae ) {
			return null;
		}
	}

	public static Properties parseAttributes ( String string ) {
		Properties result = new Properties ();
		if ( string == null )
			return result;
		StringTokenizer keyValuePairs = new StringTokenizer ( string, ";" );
		StringTokenizer keyValuePair;
		String key;
		String value;
		while ( keyValuePairs.hasMoreTokens () ) {
			keyValuePair = new StringTokenizer ( keyValuePairs.nextToken (), ":" );
			if ( keyValuePair.hasMoreTokens () )
				key = keyValuePair.nextToken ().trim ();
			else
				continue;
			if ( keyValuePair.hasMoreTokens () )
				value = keyValuePair.nextToken ().trim ();
			else
				continue;
			if ( value.startsWith ( "\"" ) )
				value = value.substring ( 1 );
			if ( value.endsWith ( "\"" ) )
				value = value.substring ( 0, value.length () - 1 );
			result.setProperty ( key.toLowerCase (), value );
		}
		return result;
	}

	public static String eliminateWhiteSpace ( String content ) {

		StringBuilder buf = new StringBuilder ();
		int len = content.length ();
		char character;
		boolean newline = false;
		for ( int i = 0; i < len; i++ ) {
			switch ( character = content.charAt ( i ) ) {
			case ' ':
				if ( !newline ) {
					buf.append ( character );
				}
				break;
			case '\n':
				if ( i > 0 ) {
					newline = true;
					buf.append ( ' ' );
				}
				break;
			case '\r':
			case '\t':
				break;
			default:
				newline = false;
				buf.append ( character );
			}
		}
		return buf.toString ();
	}

	public static int getIndexedFontSize ( String value, String previous ) {
		// the font is expressed as an index in a series of predefined font sizes
		int sIndex = 0;
		// the font is defined as a relative size
		if ( value.startsWith ( "+" ) || value.startsWith ( "-" ) ) {
			// fetch the previous value
			if ( previous == null )
				previous = "12";
			int c = (int) Float.parseFloat ( previous );
			// look for the nearest font size in the predefined series
			for ( int k = FONTSIZES.length - 1; k >= 0; --k ) {
				if ( c >= FONTSIZES[k] ) {
					sIndex = k;
					break;
				}
			}
			// retrieve the difference
			int diff =
							Integer.parseInt ( value.startsWith ( "+" ) ?
											value.substring ( 1 ) : value );
			// apply the difference
			sIndex += diff;
		}
		// the font is defined as an index
		else {
			try {
				sIndex = Integer.parseInt ( value ) - 1;
			} catch ( NumberFormatException ignored ) {
			}
		}
		if ( sIndex < 0 )
			sIndex = 0;
		else if ( sIndex >= FONTSIZES.length )
			sIndex = FONTSIZES.length - 1;
		return FONTSIZES[sIndex];
	}

	public static int alignmentValue ( String alignment ) {
		if ( alignment == null )
			return Element.ALIGN_UNDEFINED;
		if ( HtmlTags.ALIGN_CENTER.equalsIgnoreCase ( alignment ) ) {
			return Element.ALIGN_CENTER;
		}
		if ( HtmlTags.ALIGN_LEFT.equalsIgnoreCase ( alignment ) ) {
			return Element.ALIGN_LEFT;
		}
		if ( HtmlTags.ALIGN_RIGHT.equalsIgnoreCase ( alignment ) ) {
			return Element.ALIGN_RIGHT;
		}
		if ( HtmlTags.ALIGN_JUSTIFY.equalsIgnoreCase ( alignment ) ) {
			return Element.ALIGN_JUSTIFIED;
		}
		if ( HtmlTags.ALIGN_JUSTIFIED_ALL.equalsIgnoreCase ( alignment ) ) {
			return Element.ALIGN_JUSTIFIED_ALL;
		}
		if ( HtmlTags.ALIGN_TOP.equalsIgnoreCase ( alignment ) ) {
			return Element.ALIGN_TOP;
		}
		if ( HtmlTags.ALIGN_MIDDLE.equalsIgnoreCase ( alignment ) ) {
			return Element.ALIGN_MIDDLE;
		}
		if ( HtmlTags.ALIGN_BOTTOM.equalsIgnoreCase ( alignment ) ) {
			return Element.ALIGN_BOTTOM;
		}
		if ( HtmlTags.ALIGN_BASELINE.equalsIgnoreCase ( alignment ) ) {
			return Element.ALIGN_BASELINE;
		}
		return Element.ALIGN_UNDEFINED;
	}
}

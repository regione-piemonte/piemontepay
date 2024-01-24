/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Rectangle;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;


public class Barcode128 extends Barcode {

	public static final char CODE_AB_TO_C = 99;

	public static final char CODE_AC_TO_B = 100;

	public static final char CODE_BC_TO_A = 101;

	public static final char FNC1_INDEX = 102;

	public static final char START_A = 103;

	public static final char START_B = 104;

	public static final char START_C = 105;

	public static final char FNC1 = '\u00ca';

	public static final char DEL = '\u00c3';

	public static final char FNC3 = '\u00c4';

	public static final char FNC2 = '\u00c5';

	public static final char SHIFT = '\u00c6';

	public static final char CODE_C = '\u00c7';

	public static final char CODE_A = '\u00c8';

	public static final char FNC4 = '\u00c8';

	public static final char STARTA = '\u00cb';

	public static final char STARTB = '\u00cc';

	public static final char STARTC = '\u00cd';

	private static final byte[][] BARS =
					{
									{ 2, 1, 2, 2, 2, 2 },
									{ 2, 2, 2, 1, 2, 2 },
									{ 2, 2, 2, 2, 2, 1 },
									{ 1, 2, 1, 2, 2, 3 },
									{ 1, 2, 1, 3, 2, 2 },
									{ 1, 3, 1, 2, 2, 2 },
									{ 1, 2, 2, 2, 1, 3 },
									{ 1, 2, 2, 3, 1, 2 },
									{ 1, 3, 2, 2, 1, 2 },
									{ 2, 2, 1, 2, 1, 3 },
									{ 2, 2, 1, 3, 1, 2 },
									{ 2, 3, 1, 2, 1, 2 },
									{ 1, 1, 2, 2, 3, 2 },
									{ 1, 2, 2, 1, 3, 2 },
									{ 1, 2, 2, 2, 3, 1 },
									{ 1, 1, 3, 2, 2, 2 },
									{ 1, 2, 3, 1, 2, 2 },
									{ 1, 2, 3, 2, 2, 1 },
									{ 2, 2, 3, 2, 1, 1 },
									{ 2, 2, 1, 1, 3, 2 },
									{ 2, 2, 1, 2, 3, 1 },
									{ 2, 1, 3, 2, 1, 2 },
									{ 2, 2, 3, 1, 1, 2 },
									{ 3, 1, 2, 1, 3, 1 },
									{ 3, 1, 1, 2, 2, 2 },
									{ 3, 2, 1, 1, 2, 2 },
									{ 3, 2, 1, 2, 2, 1 },
									{ 3, 1, 2, 2, 1, 2 },
									{ 3, 2, 2, 1, 1, 2 },
									{ 3, 2, 2, 2, 1, 1 },
									{ 2, 1, 2, 1, 2, 3 },
									{ 2, 1, 2, 3, 2, 1 },
									{ 2, 3, 2, 1, 2, 1 },
									{ 1, 1, 1, 3, 2, 3 },
									{ 1, 3, 1, 1, 2, 3 },
									{ 1, 3, 1, 3, 2, 1 },
									{ 1, 1, 2, 3, 1, 3 },
									{ 1, 3, 2, 1, 1, 3 },
									{ 1, 3, 2, 3, 1, 1 },
									{ 2, 1, 1, 3, 1, 3 },
									{ 2, 3, 1, 1, 1, 3 },
									{ 2, 3, 1, 3, 1, 1 },
									{ 1, 1, 2, 1, 3, 3 },
									{ 1, 1, 2, 3, 3, 1 },
									{ 1, 3, 2, 1, 3, 1 },
									{ 1, 1, 3, 1, 2, 3 },
									{ 1, 1, 3, 3, 2, 1 },
									{ 1, 3, 3, 1, 2, 1 },
									{ 3, 1, 3, 1, 2, 1 },
									{ 2, 1, 1, 3, 3, 1 },
									{ 2, 3, 1, 1, 3, 1 },
									{ 2, 1, 3, 1, 1, 3 },
									{ 2, 1, 3, 3, 1, 1 },
									{ 2, 1, 3, 1, 3, 1 },
									{ 3, 1, 1, 1, 2, 3 },
									{ 3, 1, 1, 3, 2, 1 },
									{ 3, 3, 1, 1, 2, 1 },
									{ 3, 1, 2, 1, 1, 3 },
									{ 3, 1, 2, 3, 1, 1 },
									{ 3, 3, 2, 1, 1, 1 },
									{ 3, 1, 4, 1, 1, 1 },
									{ 2, 2, 1, 4, 1, 1 },
									{ 4, 3, 1, 1, 1, 1 },
									{ 1, 1, 1, 2, 2, 4 },
									{ 1, 1, 1, 4, 2, 2 },
									{ 1, 2, 1, 1, 2, 4 },
									{ 1, 2, 1, 4, 2, 1 },
									{ 1, 4, 1, 1, 2, 2 },
									{ 1, 4, 1, 2, 2, 1 },
									{ 1, 1, 2, 2, 1, 4 },
									{ 1, 1, 2, 4, 1, 2 },
									{ 1, 2, 2, 1, 1, 4 },
									{ 1, 2, 2, 4, 1, 1 },
									{ 1, 4, 2, 1, 1, 2 },
									{ 1, 4, 2, 2, 1, 1 },
									{ 2, 4, 1, 2, 1, 1 },
									{ 2, 2, 1, 1, 1, 4 },
									{ 4, 1, 3, 1, 1, 1 },
									{ 2, 4, 1, 1, 1, 2 },
									{ 1, 3, 4, 1, 1, 1 },
									{ 1, 1, 1, 2, 4, 2 },
									{ 1, 2, 1, 1, 4, 2 },
									{ 1, 2, 1, 2, 4, 1 },
									{ 1, 1, 4, 2, 1, 2 },
									{ 1, 2, 4, 1, 1, 2 },
									{ 1, 2, 4, 2, 1, 1 },
									{ 4, 1, 1, 2, 1, 2 },
									{ 4, 2, 1, 1, 1, 2 },
									{ 4, 2, 1, 2, 1, 1 },
									{ 2, 1, 2, 1, 4, 1 },
									{ 2, 1, 4, 1, 2, 1 },
									{ 4, 1, 2, 1, 2, 1 },
									{ 1, 1, 1, 1, 4, 3 },
									{ 1, 1, 1, 3, 4, 1 },
									{ 1, 3, 1, 1, 4, 1 },
									{ 1, 1, 4, 1, 1, 3 },
									{ 1, 1, 4, 3, 1, 1 },
									{ 4, 1, 1, 1, 1, 3 },
									{ 4, 1, 1, 3, 1, 1 },
									{ 1, 1, 3, 1, 4, 1 },
									{ 1, 1, 4, 1, 3, 1 },
									{ 3, 1, 1, 1, 4, 1 },
									{ 4, 1, 1, 1, 3, 1 },
									{ 2, 1, 1, 4, 1, 2 },
									{ 2, 1, 1, 2, 1, 4 },
									{ 2, 1, 1, 2, 3, 2 }
					};

	private static final byte[] BARS_STOP = { 2, 3, 3, 1, 1, 1, 2 };

	private static final IntHashtable ais = new IntHashtable ();

	static {
		ais.put ( 0, 20 );
		ais.put ( 1, 16 );
		ais.put ( 2, 16 );
		ais.put ( 10, -1 );
		ais.put ( 11, 9 );
		ais.put ( 12, 8 );
		ais.put ( 13, 8 );
		ais.put ( 15, 8 );
		ais.put ( 17, 8 );
		ais.put ( 20, 4 );
		ais.put ( 21, -1 );
		ais.put ( 22, -1 );
		ais.put ( 23, -1 );
		ais.put ( 240, -1 );
		ais.put ( 241, -1 );
		ais.put ( 250, -1 );
		ais.put ( 251, -1 );
		ais.put ( 252, -1 );
		ais.put ( 30, -1 );
		for ( int k = 3100; k < 3700; ++k )
			ais.put ( k, 10 );
		ais.put ( 37, -1 );
		for ( int k = 3900; k < 3940; ++k )
			ais.put ( k, -1 );
		ais.put ( 400, -1 );
		ais.put ( 401, -1 );
		ais.put ( 402, 20 );
		ais.put ( 403, -1 );
		for ( int k = 410; k < 416; ++k )
			ais.put ( k, 16 );
		ais.put ( 420, -1 );
		ais.put ( 421, -1 );
		ais.put ( 422, 6 );
		ais.put ( 423, -1 );
		ais.put ( 424, 6 );
		ais.put ( 425, 6 );
		ais.put ( 426, 6 );
		ais.put ( 7001, 17 );
		ais.put ( 7002, -1 );
		for ( int k = 7030; k < 7040; ++k )
			ais.put ( k, -1 );
		ais.put ( 8001, 18 );
		ais.put ( 8002, -1 );
		ais.put ( 8003, -1 );
		ais.put ( 8004, -1 );
		ais.put ( 8005, 10 );
		ais.put ( 8006, 22 );
		ais.put ( 8007, -1 );
		ais.put ( 8008, -1 );
		ais.put ( 8018, 22 );
		ais.put ( 8020, -1 );
		ais.put ( 8100, 10 );
		ais.put ( 8101, 14 );
		ais.put ( 8102, 6 );
		for ( int k = 90; k < 100; ++k )
			ais.put ( k, -1 );
	}

	public Barcode128 () {
		try {
			x = 0.8f;
			font = BaseFont.createFont ( "Helvetica", "winansi", false );
			size = 8;
			baseline = size;
			barHeight = size * 3;
			textAlignment = Element.ALIGN_CENTER;
			codeType = CODE128;
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
	}

	public static String removeFNC1 ( String code ) {
		int len = code.length ();
		StringBuilder buf = new StringBuilder ( len );
		for ( int k = 0; k < len; ++k ) {
			char c = code.charAt ( k );
			if ( c >= 32 && c <= 126 )
				buf.append ( c );
		}
		return buf.toString ();
	}

	public static String getHumanReadableUCCEAN ( String code ) {
		StringBuilder buf = new StringBuilder ();
		String fnc1 = String.valueOf ( FNC1 );
		try {
			while ( true ) {
				if ( code.startsWith ( fnc1 ) ) {
					code = code.substring ( 1 );
					continue;
				}
				int n = 0;
				int idlen = 0;
				for ( int k = 2; k < 5; ++k ) {
					if ( code.length () < k )
						break;
					if ( ( n = ais.get ( Integer.parseInt ( code.substring ( 0, k ) ) ) ) != 0 ) {
						idlen = k;
						break;
					}
				}
				if ( idlen == 0 )
					break;
				buf.append ( '(' ).append ( code, 0, idlen ).append ( ')' );
				code = code.substring ( idlen );
				if ( n > 0 ) {
					n -= idlen;
					if ( code.length () <= n )
						break;
					buf.append ( removeFNC1 ( code.substring ( 0, n ) ) );
					code = code.substring ( n );
				} else {
					int idx = code.indexOf ( FNC1 );
					if ( idx < 0 )
						break;
					buf.append ( code, 0, idx );
					code = code.substring ( idx + 1 );
				}
			}
		} catch ( Exception ignored ) {
		}
		buf.append ( removeFNC1 ( code ) );
		return buf.toString ();
	}

	static boolean isNextDigits ( String text, int textIndex, int numDigits ) {
		int len = text.length ();
		while ( textIndex < len && numDigits > 0 ) {
			if ( text.charAt ( textIndex ) == FNC1 ) {
				++textIndex;
				continue;
			}
			int n = Math.min ( 2, numDigits );
			if ( textIndex + n > len )
				return false;
			while ( n-- > 0 ) {
				char c = text.charAt ( textIndex++ );
				if ( c < '0' || c > '9' )
					return false;
				--numDigits;
			}
		}
		return numDigits == 0;
	}

	static String getPackedRawDigits ( String text, int textIndex, int numDigits ) {
		StringBuilder out = new StringBuilder ();
		int start = textIndex;
		while ( numDigits > 0 ) {
			if ( text.charAt ( textIndex ) == FNC1 ) {
				out.append ( FNC1_INDEX );
				++textIndex;
				continue;
			}
			numDigits -= 2;
			int c1 = text.charAt ( textIndex++ ) - '0';
			int c2 = text.charAt ( textIndex++ ) - '0';
			out.append ( (char) ( c1 * 10 + c2 ) );
		}
		return (char) ( textIndex - start ) + out.toString ();
	}

	public static String getRawText ( String text, boolean ucc ) {
		StringBuilder out = new StringBuilder ();
		int tLen = text.length ();
		if ( tLen == 0 ) {
			out.append ( START_B );
			if ( ucc )
				out.append ( FNC1_INDEX );
			return out.toString ();
		}
		int c;
		for ( int k = 0; k < tLen; ++k ) {
			c = text.charAt ( k );
			if ( c > 127 && c != FNC1 )
				throw new RuntimeException ( MessageLocalization.getComposedMessage ( "there.are.illegal.characters.for.barcode.128.in.1", text ) );
		}
		c = text.charAt ( 0 );
		char currentCode = START_B;
		int index = 0;
		if ( isNextDigits ( text, index, 2 ) ) {
			currentCode = START_C;
			out.append ( currentCode );
			if ( ucc )
				out.append ( FNC1_INDEX );
			String out2 = getPackedRawDigits ( text, index, 2 );
			index += out2.charAt ( 0 );
			out.append ( out2.substring ( 1 ) );
		} else if ( c < ' ' ) {
			currentCode = START_A;
			out.append ( currentCode );
			if ( ucc )
				out.append ( FNC1_INDEX );
			out.append ( (char) ( c + 64 ) );
			++index;
		} else {
			out.append ( currentCode );
			if ( ucc )
				out.append ( FNC1_INDEX );
			if ( c == FNC1 )
				out.append ( FNC1_INDEX );
			else
				out.append ( (char) ( c - ' ' ) );
			++index;
		}
		while ( index < tLen ) {
			switch ( currentCode ) {
			case START_A: {
				if ( isNextDigits ( text, index, 4 ) ) {
					currentCode = START_C;
					out.append ( CODE_AB_TO_C );
					String out2 = getPackedRawDigits ( text, index, 4 );
					index += out2.charAt ( 0 );
					out.append ( out2.substring ( 1 ) );
				} else {
					c = text.charAt ( index++ );
					if ( c == FNC1 )
						out.append ( FNC1_INDEX );
					else if ( c > '_' ) {
						currentCode = START_B;
						out.append ( CODE_AC_TO_B );
						out.append ( (char) ( c - ' ' ) );
					} else if ( c < ' ' )
						out.append ( (char) ( c + 64 ) );
					else
						out.append ( (char) ( c - ' ' ) );
				}
			}
			break;
			case START_B: {
				if ( isNextDigits ( text, index, 4 ) ) {
					currentCode = START_C;
					out.append ( CODE_AB_TO_C );
					String out2 = getPackedRawDigits ( text, index, 4 );
					index += out2.charAt ( 0 );
					out.append ( out2.substring ( 1 ) );
				} else {
					c = text.charAt ( index++ );
					if ( c == FNC1 )
						out.append ( FNC1_INDEX );
					else if ( c < ' ' ) {
						currentCode = START_A;
						out.append ( CODE_BC_TO_A );
						out.append ( (char) ( c + 64 ) );
					} else {
						out.append ( (char) ( c - ' ' ) );
					}
				}
			}
			break;
			case START_C: {
				if ( isNextDigits ( text, index, 2 ) ) {
					String out2 = getPackedRawDigits ( text, index, 2 );
					index += out2.charAt ( 0 );
					out.append ( out2.substring ( 1 ) );
				} else {
					c = text.charAt ( index++ );
					if ( c == FNC1 )
						out.append ( FNC1_INDEX );
					else if ( c < ' ' ) {
						currentCode = START_A;
						out.append ( CODE_BC_TO_A );
						out.append ( (char) ( c + 64 ) );
					} else {
						currentCode = START_B;
						out.append ( CODE_AC_TO_B );
						out.append ( (char) ( c - ' ' ) );
					}
				}
			}
			break;
			}
		}
		return out.toString ();
	}

	public static byte[] getBarsCode128Raw ( String text ) {
		int idx = text.indexOf ( '\uffff' );
		if ( idx >= 0 )
			text = text.substring ( 0, idx );
		int chk = text.charAt ( 0 );
		for ( int k = 1; k < text.length (); ++k )
			chk += k * text.charAt ( k );
		chk = chk % 103;
		text += (char) chk;
		byte[] bars = new byte[( text.length () + 1 ) * 6 + 7];
		int k;
		for ( k = 0; k < text.length (); ++k )
			System.arraycopy ( BARS[text.charAt ( k )], 0, bars, k * 6, 6 );
		System.arraycopy ( BARS_STOP, 0, bars, k * 6, 7 );
		return bars;
	}

	public Rectangle getBarcodeSize () {
		float fontX = 0;
		float fontY = 0;
		String fullCode;
		if ( font != null ) {
			if ( baseline > 0 )
				fontY = baseline - font.getFontDescriptor ( BaseFont.DESCENT, size );
			else
				fontY = -baseline + size;
			if ( codeType == CODE128_RAW ) {
				int idx = code.indexOf ( '\uffff' );
				if ( idx < 0 )
					fullCode = "";
				else
					fullCode = code.substring ( idx + 1 );
			} else if ( codeType == CODE128_UCC )
				fullCode = getHumanReadableUCCEAN ( code );
			else
				fullCode = removeFNC1 ( code );
			fontX = font.getWidthPoint ( altText != null ? altText : fullCode, size );
		}
		if ( codeType == CODE128_RAW ) {
			int idx = code.indexOf ( '\uffff' );
			if ( idx >= 0 )
				fullCode = code.substring ( 0, idx );
			else
				fullCode = code;
		} else {
			fullCode = getRawText ( code, codeType == CODE128_UCC );
		}
		int len = fullCode.length ();
		float fullWidth = ( len + 2 ) * 11 * x + 2 * x;
		fullWidth = Math.max ( fullWidth, fontX );
		float fullHeight = barHeight + fontY;
		return new Rectangle ( fullWidth, fullHeight );
	}

	public Rectangle placeBarcode ( PdfContentByte cb, BaseColor barColor, BaseColor textColor ) {
		String fullCode;
		if ( codeType == CODE128_RAW ) {
			int idx = code.indexOf ( '\uffff' );
			if ( idx < 0 )
				fullCode = "";
			else
				fullCode = code.substring ( idx + 1 );
		} else if ( codeType == CODE128_UCC )
			fullCode = getHumanReadableUCCEAN ( code );
		else
			fullCode = removeFNC1 ( code );
		float fontX = 0;
		if ( font != null ) {
			fontX = font.getWidthPoint ( fullCode = altText != null ? altText : fullCode, size );
		}
		String bCode;
		if ( codeType == CODE128_RAW ) {
			int idx = code.indexOf ( '\uffff' );
			if ( idx >= 0 )
				bCode = code.substring ( 0, idx );
			else
				bCode = code;
		} else {
			bCode = getRawText ( code, codeType == CODE128_UCC );
		}
		int len = bCode.length ();
		float fullWidth = ( len + 2 ) * 11 * x + 2 * x;
		float barStartX = 0;
		float textStartX = 0;
		switch ( textAlignment ) {
		case Element.ALIGN_LEFT:
			break;
		case Element.ALIGN_RIGHT:
			if ( fontX > fullWidth )
				barStartX = fontX - fullWidth;
			else
				textStartX = fullWidth - fontX;
			break;
		default:
			if ( fontX > fullWidth )
				barStartX = ( fontX - fullWidth ) / 2;
			else
				textStartX = ( fullWidth - fontX ) / 2;
			break;
		}
		float barStartY = 0;
		float textStartY = 0;
		if ( font != null ) {
			if ( baseline <= 0 )
				textStartY = barHeight - baseline;
			else {
				textStartY = -font.getFontDescriptor ( BaseFont.DESCENT, size );
				barStartY = textStartY + baseline;
			}
		}
		byte[] bars = getBarsCode128Raw ( bCode );
		boolean print = true;
		if ( barColor != null )
			cb.setColorFill ( barColor );
		for ( byte bar : bars ) {
			float w = bar * x;
			if ( print )
				cb.rectangle ( barStartX, barStartY, w - inkSpreading, barHeight );
			print = !print;
			barStartX += w;
		}
		cb.fill ();
		if ( font != null ) {
			if ( textColor != null )
				cb.setColorFill ( textColor );
			cb.beginText ();
			cb.setFontAndSize ( font, size );
			cb.setTextMatrix ( textStartX, textStartY );
			cb.showText ( fullCode );
			cb.endText ();
		}
		return getBarcodeSize ();
	}

	public void setCode ( String code ) {
		if ( getCodeType () == Barcode128.CODE128_UCC && code.startsWith ( "(" ) ) {
			int idx = 0;
			StringBuilder ret = new StringBuilder ();
			while ( idx >= 0 ) {
				int end = code.indexOf ( ')', idx );
				if ( end < 0 )
					throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "badly.formed.ucc.string.1", code ) );
				String sai = code.substring ( idx + 1, end );
				if ( sai.length () < 2 )
					throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "ai.too.short.1", sai ) );
				int ai = Integer.parseInt ( sai );
				int len = ais.get ( ai );
				if ( len == 0 )
					throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "ai.not.found.1", sai ) );
				sai = String.valueOf ( ai );
				if ( sai.length () == 1 )
					sai = "0" + sai;
				idx = code.indexOf ( '(', end );
				int next = ( idx < 0 ? code.length () : idx );
				ret.append ( sai ).append ( code, end + 1, next );
				if ( len < 0 ) {
					if ( idx >= 0 )
						ret.append ( FNC1 );
				} else if ( next - end - 1 + sai.length () != len )
					throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "invalid.ai.length.1", sai ) );
			}
			super.setCode ( ret.toString () );
		} else
			super.setCode ( code );
	}

}

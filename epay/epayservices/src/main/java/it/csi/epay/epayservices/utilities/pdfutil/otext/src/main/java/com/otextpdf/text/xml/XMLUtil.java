/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml;

public class XMLUtil {

	public static String escapeXML ( final String s, final boolean onlyASCII ) {
		char[] cc = s.toCharArray ();
		StringBuilder sb = new StringBuilder ();
		for ( int c : cc ) {
			switch ( c ) {
			case '<':
				sb.append ( "&lt;" );
				break;
			case '>':
				sb.append ( "&gt;" );
				break;
			case '&':
				sb.append ( "&amp;" );
				break;
			case '"':
				sb.append ( "&quot;" );
				break;
			case '\'':
				sb.append ( "&apos;" );
				break;
			default:
				if ( isValidCharacterValue ( c ) ) {
					if ( onlyASCII && c > 127 )
						sb.append ( "&#" ).append ( c ).append ( ';' );
					else
						sb.append ( (char) c );
				}
			}
		}
		return sb.toString ();
	}

	public static boolean isValidCharacterValue ( int c ) {
		return ( c == 0x9 || c == 0xA || c == 0xD
						|| c >= 0x20 && c <= 0xD7FF
						|| c >= 0xE000 && c <= 0xFFFD
						|| c >= 0x10000 && c <= 0x10FFFF );
	}

	public static String getEncodingName ( final byte[] b4 ) {

		int b0 = b4[0] & 0xFF;
		int b1 = b4[1] & 0xFF;
		if ( b0 == 0xFE && b1 == 0xFF ) {
			return "UTF-16BE";
		}
		if ( b0 == 0xFF && b1 == 0xFE ) {
			return "UTF-16LE";
		}

		int b2 = b4[2] & 0xFF;
		if ( b0 == 0xEF && b1 == 0xBB && b2 == 0xBF ) {
			return "UTF-8";
		}

		int b3 = b4[3] & 0xFF;
		if ( b0 == 0x00 && b1 == 0x00 && b2 == 0x00 && b3 == 0x3C ) {
			return "ISO-10646-UCS-4";
		}
		if ( b0 == 0x3C && b1 == 0x00 && b2 == 0x00 && b3 == 0x00 ) {
			return "ISO-10646-UCS-4";
		}
		if ( b0 == 0x00 && b1 == 0x00 && b2 == 0x3C && b3 == 0x00 ) {
			return "ISO-10646-UCS-4";
		}
		if ( b0 == 0x00 && b1 == 0x3C && b2 == 0x00 && b3 == 0x00 ) {
			return "ISO-10646-UCS-4";
		}
		if ( b0 == 0x00 && b1 == 0x3C && b2 == 0x00 && b3 == 0x3F ) {
			return "UTF-16BE";
		}
		if ( b0 == 0x3C && b1 == 0x00 && b2 == 0x3F && b3 == 0x00 ) {
			return "UTF-16LE";
		}
		if ( b0 == 0x4C && b1 == 0x6F && b2 == 0xA7 && b3 == 0x94 ) {
			return "CP037";
		}

		return "UTF-8";
	}
}

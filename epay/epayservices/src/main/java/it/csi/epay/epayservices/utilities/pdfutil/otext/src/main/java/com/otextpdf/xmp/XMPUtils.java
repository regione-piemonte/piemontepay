/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl.Base64;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl.ISO8601Converter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl.XMPUtilsImpl;


public class XMPUtils {

	private XMPUtils () {
		// EMPTY
	}

	public static void removeProperties ( XMPMeta xmp, String schemaNS, String propName,
					boolean doAllProperties, boolean includeAliases ) throws XMPException {
		XMPUtilsImpl.removeProperties ( xmp, schemaNS, propName, doAllProperties, includeAliases );
	}

	public static void appendProperties ( XMPMeta source, XMPMeta dest, boolean doAllProperties,
					boolean replaceOldValues ) throws XMPException {
		appendProperties ( source, dest, doAllProperties, replaceOldValues, false );
	}

	public static void appendProperties ( XMPMeta source, XMPMeta dest, boolean doAllProperties,
					boolean replaceOldValues, boolean deleteEmptyValues ) throws XMPException {
		XMPUtilsImpl.appendProperties ( source, dest, doAllProperties, replaceOldValues,
						deleteEmptyValues );
	}

	public static boolean convertToBoolean ( String value ) throws XMPException {
		if ( value == null || value.isEmpty () ) {
			throw new XMPException ( "Empty convert-string", XMPError.BADVALUE );
		}
		value = value.toLowerCase ();

		try {
			// First try interpretation as Integer (anything not 0 is true)
			return Integer.parseInt ( value ) != 0;
		} catch ( NumberFormatException e ) {
			return
							"true".equals ( value ) ||
											"t".equals ( value ) ||
											"on".equals ( value ) ||
											"yes".equals ( value );
		}
	}

	public static String convertFromBoolean ( boolean value ) {
		return value ? XMPConst.TRUESTR : XMPConst.FALSESTR;
	}

	public static int convertToInteger ( String rawValue ) throws XMPException {
		try {
			if ( rawValue == null || rawValue.isEmpty () ) {
				throw new XMPException ( "Empty convert-string", XMPError.BADVALUE );
			}
			if ( rawValue.startsWith ( "0x" ) ) {
				return Integer.parseInt ( rawValue.substring ( 2 ), 16 );
			} else {
				return Integer.parseInt ( rawValue );
			}
		} catch ( NumberFormatException e ) {
			throw new XMPException ( "Invalid integer string", XMPError.BADVALUE );
		}
	}

	public static String convertFromInteger ( int value ) {
		return String.valueOf ( value );
	}

	public static long convertToLong ( String rawValue ) throws XMPException {
		try {
			if ( rawValue == null || rawValue.isEmpty () ) {
				throw new XMPException ( "Empty convert-string", XMPError.BADVALUE );
			}
			if ( rawValue.startsWith ( "0x" ) ) {
				return Long.parseLong ( rawValue.substring ( 2 ), 16 );
			} else {
				return Long.parseLong ( rawValue );
			}
		} catch ( NumberFormatException e ) {
			throw new XMPException ( "Invalid long string", XMPError.BADVALUE );
		}
	}

	public static String convertFromLong ( long value ) {
		return String.valueOf ( value );
	}

	public static double convertToDouble ( String rawValue ) throws XMPException {
		try {
			if ( rawValue == null || rawValue.isEmpty () ) {
				throw new XMPException ( "Empty convert-string", XMPError.BADVALUE );
			} else {
				return Double.parseDouble ( rawValue );
			}
		} catch ( NumberFormatException e ) {
			throw new XMPException ( "Invalid double string", XMPError.BADVALUE );
		}
	}

	public static String convertFromDouble ( double value ) {
		return String.valueOf ( value );
	}

	public static XMPDateTime convertToDate ( String rawValue ) throws XMPException {
		if ( rawValue == null || rawValue.isEmpty () ) {
			throw new XMPException ( "Empty convert-string", XMPError.BADVALUE );
		} else {
			return ISO8601Converter.parse ( rawValue );
		}
	}

	public static String convertFromDate ( XMPDateTime value ) {
		return ISO8601Converter.render ( value );
	}

	public static String encodeBase64 ( byte[] buffer ) {
		return new String ( Base64.encode ( buffer ) );
	}

	public static byte[] decodeBase64 ( String base64String ) throws XMPException {
		try {
			return Base64.decode ( base64String.getBytes () );
		} catch ( Throwable e ) {
			throw new XMPException ( "Invalid base64 string", XMPError.BADVALUE, e );
		}
	}
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.factories;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;


public class RomanAlphabetFactory {

	public static String getString ( int index ) {
		if ( index < 1 )
			throw new NumberFormatException ( MessageLocalization.getComposedMessage ( "you.can.t.translate.a.negative.number.into.an.alphabetical.value" ) );

		index--;
		int bytes = 1;
		int start = 0;
		int symbols = 26;
		while ( index >= symbols + start ) {
			bytes++;
			start += symbols;
			symbols *= 26;
		}

		int c = index - start;
		char[] value = new char[bytes];
		while ( bytes > 0 ) {
			value[--bytes] = (char) ( 'a' + ( c % 26 ) );
			c /= 26;
		}

		return new String ( value );
	}

	public static String getLowerCaseString ( final int index ) {
		return getString ( index );
	}

	public static String getUpperCaseString ( final int index ) {
		return getString ( index ).toUpperCase ();
	}

	public static String getString ( final int index, final boolean lowercase ) {
		if ( lowercase ) {
			return getLowerCaseString ( index );
		} else {
			return getUpperCaseString ( index );
		}
	}
}

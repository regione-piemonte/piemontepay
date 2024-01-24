/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt.geom.misc;

import java.util.ResourceBundle;


public class Messages {

	static private final ResourceBundle bundle = null;

	public static String format ( String format, Object[] args ) {
		StringBuilder answer = new StringBuilder ( format.length ()
						+ ( args.length * 20 ) );
		String[] argStrings = new String[args.length];
		for ( int i = 0; i < args.length; ++i ) {
			if ( args[i] == null )
				argStrings[i] = "<null>";
			else
				argStrings[i] = args[i].toString ();
		}
		int lastI = 0;
		for ( int i = format.indexOf ( '{' ); i >= 0; i = format.indexOf ( '{',
						lastI ) ) {
			if ( i != 0 && format.charAt ( i - 1 ) == '\\' ) {
				if ( i != 1 )
					answer.append ( format, lastI, i - 1 );
				answer.append ( '{' );
				lastI = i + 1;
			} else {
				if ( i > format.length () - 3 ) {
					answer.append ( format.substring ( lastI ) );
					lastI = format.length ();
				} else {
					int argnum = (byte) Character.digit ( format.charAt ( i + 1 ),
									10 );
					if ( argnum < 0 || format.charAt ( i + 2 ) != '}' ) {
						answer.append ( format, lastI, i + 1 );
						lastI = i + 1;
					} else {
						answer.append ( format, lastI, i );
						if ( argnum >= argStrings.length )
							answer.append ( "<missing argument>" );
						else
							answer.append ( argStrings[argnum] );
						lastI = i + 3;
					}
				}
			}
		}
		if ( lastI < format.length () )
			answer.append ( format.substring ( lastI ) );
		return answer.toString ();
	}
}

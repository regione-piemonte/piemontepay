/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.hyphenation;

public class Hyphenation {

	private final int[] hyphenPoints;

	private final String word;

	private final int len;

	Hyphenation ( String word, int[] points ) {
		this.word = word;
		hyphenPoints = points;
		len = points.length;
	}

	public int length () {
		return len;
	}

	public String getPreHyphenText ( int index ) {
		return word.substring ( 0, hyphenPoints[index] );
	}

	public String getPostHyphenText ( int index ) {
		return word.substring ( hyphenPoints[index] );
	}

	public String toString () {
		StringBuilder str = new StringBuilder ();
		int start = 0;
		for ( int i = 0; i < len; i++ ) {
			str.append ( word, start, hyphenPoints[i] ).append ( '-' );
			start = hyphenPoints[i];
		}
		str.append ( word.substring ( start ) );
		return str.toString ();
	}

}

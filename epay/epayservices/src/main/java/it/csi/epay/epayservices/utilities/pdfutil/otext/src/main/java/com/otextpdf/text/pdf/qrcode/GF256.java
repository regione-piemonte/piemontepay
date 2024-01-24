/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.qrcode;

public final class GF256 {

	public static final GF256 QR_CODE_FIELD = new GF256 ( 0x011D ); // x^8 + x^4 + x^3 + x^2 + 1

	public static final GF256 DATA_MATRIX_FIELD = new GF256 ( 0x012D ); // x^8 + x^5 + x^3 + x^2 + 1

	private final int[] expTable;

	private final int[] logTable;

	private final GF256Poly zero;

	private GF256 ( int primitive ) {
		expTable = new int[256];
		logTable = new int[256];
		int x = 1;
		for ( int i = 0; i < 256; i++ ) {
			expTable[i] = x;
			x <<= 1; // x = x * 2; we're assuming the generator alpha is 2
			if ( x >= 0x100 ) {
				x ^= primitive;
			}
		}
		for ( int i = 0; i < 255; i++ ) {
			logTable[expTable[i]] = i;
		}
		// logTable[0] == 0 but this should never be used
		zero = new GF256Poly ( this, new int[] { 0 } );
		new GF256Poly ( this, new int[] { 1 } );
	}

	static int addOrSubtract ( int a, int b ) {
		return a ^ b;
	}

	GF256Poly getZero () {
		return zero;
	}

	GF256Poly buildMonomial ( int degree, int coefficient ) {
		if ( degree < 0 ) {
			throw new IllegalArgumentException ();
		}
		if ( coefficient == 0 ) {
			return zero;
		}
		int[] coefficients = new int[degree + 1];
		coefficients[0] = coefficient;
		return new GF256Poly ( this, coefficients );
	}

	int exp ( int a ) {
		return expTable[a];
	}

	int log ( int a ) {
		if ( a == 0 ) {
			throw new IllegalArgumentException ();
		}
		return logTable[a];
	}

	int inverse ( int a ) {
		if ( a == 0 ) {
			throw new ArithmeticException ();
		}
		return expTable[255 - logTable[a]];
	}

	int multiply ( int a, int b ) {
		if ( a == 0 || b == 0 ) {
			return 0;
		}
		if ( a == 1 ) {
			return b;
		}
		if ( b == 1 ) {
			return a;
		}
		return expTable[( logTable[a] + logTable[b] ) % 255];
	}

}

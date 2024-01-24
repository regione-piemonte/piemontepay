/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.qrcode;

import java.util.ArrayList;


public final class ReedSolomonEncoder {

	private final GF256 field;

	private final ArrayList<GF256Poly> cachedGenerators;

	public ReedSolomonEncoder ( GF256 field ) {
		if ( !GF256.QR_CODE_FIELD.equals ( field ) ) {
			throw new IllegalArgumentException ( "Only QR Code is supported at this time" );
		}
		this.field = field;
		this.cachedGenerators = new ArrayList<> ();
		cachedGenerators.add ( new GF256Poly ( field, new int[] { 1 } ) );
	}

	private GF256Poly buildGenerator ( int degree ) {
		if ( degree >= cachedGenerators.size () ) {
			GF256Poly lastGenerator = cachedGenerators.get ( cachedGenerators.size () - 1 );
			for ( int d = cachedGenerators.size (); d <= degree; d++ ) {
				GF256Poly nextGenerator = lastGenerator.multiply ( new GF256Poly ( field, new int[] { 1, field.exp ( d - 1 ) } ) );
				cachedGenerators.add ( nextGenerator );
				lastGenerator = nextGenerator;
			}
		}
		return cachedGenerators.get ( degree );
	}

	public void encode ( int[] toEncode, int ecBytes ) {
		if ( ecBytes == 0 ) {
			throw new IllegalArgumentException ( "No error correction bytes" );
		}
		int dataBytes = toEncode.length - ecBytes;
		if ( dataBytes <= 0 ) {
			throw new IllegalArgumentException ( "No data bytes provided" );
		}
		GF256Poly generator = buildGenerator ( ecBytes );
		int[] infoCoefficients = new int[dataBytes];
		System.arraycopy ( toEncode, 0, infoCoefficients, 0, dataBytes );
		GF256Poly info = new GF256Poly ( field, infoCoefficients );
		info = info.multiplyByMonomial ( ecBytes, 1 );
		GF256Poly remainder = info.divide ( generator )[1];
		int[] coefficients = remainder.getCoefficients ();
		int numZeroCoefficients = ecBytes - coefficients.length;
		for ( int i = 0; i < numZeroCoefficients; i++ ) {
			toEncode[dataBytes + i] = 0;
		}
		System.arraycopy ( coefficients, 0, toEncode, dataBytes + numZeroCoefficients, coefficients.length );
	}

}

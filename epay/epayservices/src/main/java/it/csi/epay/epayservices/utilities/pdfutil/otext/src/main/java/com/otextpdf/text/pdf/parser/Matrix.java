/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.parser;

import java.util.Arrays;


public class Matrix {

	public static final int I11 = 0;

	public static final int I12 = 1;

	public static final int I13 = 2;

	public static final int I21 = 3;

	public static final int I22 = 4;

	public static final int I23 = 5;

	public static final int I31 = 6;

	public static final int I32 = 7;

	public static final int I33 = 8;

	private final float[] vals = new float[] {
					1, 0, 0,
					0, 1, 0,
					0, 0, 1
	};

	public Matrix () {
	}

	public Matrix ( float tx, float ty ) {
		vals[I31] = tx;
		vals[I32] = ty;
	}

	public Matrix ( float a, float b, float c, float d, float e, float f ) {
		vals[I11] = a;
		vals[I12] = b;
		vals[I13] = 0;
		vals[I21] = c;
		vals[I22] = d;
		vals[I23] = 0;
		vals[I31] = e;
		vals[I32] = f;
	}

	public float get ( int index ) {
		return vals[index];
	}

	public Matrix multiply ( Matrix by ) {
		Matrix rslt = new Matrix ();

		float[] a = vals;
		float[] b = by.vals;
		float[] c = rslt.vals;

		c[I11] = a[I11] * b[I11] + a[I12] * b[I21] + a[I13] * b[I31];
		c[I12] = a[I11] * b[I12] + a[I12] * b[I22] + a[I13] * b[I32];
		c[I13] = a[I11] * b[I13] + a[I12] * b[I23] + a[I13] * b[I33];
		c[I21] = a[I21] * b[I11] + a[I22] * b[I21] + a[I23] * b[I31];
		c[I22] = a[I21] * b[I12] + a[I22] * b[I22] + a[I23] * b[I32];
		c[I23] = a[I21] * b[I13] + a[I22] * b[I23] + a[I23] * b[I33];
		c[I31] = a[I31] * b[I11] + a[I32] * b[I21] + a[I33] * b[I31];
		c[I32] = a[I31] * b[I12] + a[I32] * b[I22] + a[I33] * b[I32];
		c[I33] = a[I31] * b[I13] + a[I32] * b[I23] + a[I33] * b[I33];

		return rslt;
	}

	public float getDeterminant () {
		// ref http://en.wikipedia.org/wiki/Determinant
		// note that in PDF, I13 and I23 are always 0 and I33 is always 1
		// so this could be simplified/faster
		return vals[I11] * vals[I22] * vals[I33]
						+ vals[I12] * vals[I23] * vals[I31]
						+ vals[I13] * vals[I21] * vals[I32]
						- vals[I11] * vals[I23] * vals[I32]
						- vals[I12] * vals[I21] * vals[I33]
						- vals[I13] * vals[I22] * vals[I31];
	}

	public boolean equals ( Object obj ) {
		if ( !( obj instanceof Matrix ) )
			return false;

		return Arrays.equals ( vals, ( (Matrix) obj ).vals );
	}

	public int hashCode () {

		int result = 1;
		for ( float val : vals )
			result = 31 * result + Float.floatToIntBits ( val );

		return result;
	}

	public String toString () {
		return vals[I11] + "\t" + vals[I12] + "\t" + vals[I13] + "\n" +
						vals[I21] + "\t" + vals[I22] + "\t" + vals[I13] + "\n" +
						vals[I31] + "\t" + vals[I32] + "\t" + vals[I33];
	}
}

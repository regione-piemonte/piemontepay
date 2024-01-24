/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.parser;

import java.util.Arrays;


public class Vector {

	public static final int I1 = 0;

	public static final int I2 = 1;

	public static final int I3 = 2;

	private final float[] vals = new float[] {
					0, 0, 0
	};

	public Vector ( final float x, final float y, final float z ) {
		vals[I1] = x;
		vals[I2] = y;
		vals[I3] = z;
	}

	public float get ( final int index ) {
		return vals[index];
	}

	public Vector cross ( final Matrix by ) {

		float x = vals[I1] * by.get ( Matrix.I11 ) + vals[I2] * by.get ( Matrix.I21 ) + vals[I3] * by.get ( Matrix.I31 );
		float y = vals[I1] * by.get ( Matrix.I12 ) + vals[I2] * by.get ( Matrix.I22 ) + vals[I3] * by.get ( Matrix.I32 );
		float z = vals[I1] * by.get ( Matrix.I13 ) + vals[I2] * by.get ( Matrix.I23 ) + vals[I3] * by.get ( Matrix.I33 );

		return new Vector ( x, y, z );
	}

	public Vector subtract ( final Vector v ) {
		float x = vals[I1] - v.vals[I1];
		float y = vals[I2] - v.vals[I2];
		float z = vals[I3] - v.vals[I3];

		return new Vector ( x, y, z );
	}

	public Vector cross ( final Vector with ) {
		float x = vals[I2] * with.vals[I3] - vals[I3] * with.vals[I2];
		float y = vals[I3] * with.vals[I1] - vals[I1] * with.vals[I3];
		float z = vals[I1] * with.vals[I2] - vals[I2] * with.vals[I1];

		return new Vector ( x, y, z );
	}

	public Vector normalize () {
		float l = this.length ();
		float x = vals[I1] / l;
		float y = vals[I2] / l;
		float z = vals[I3] / l;
		return new Vector ( x, y, z );
	}

	public Vector multiply ( final float by ) {
		float x = vals[I1] * by;
		float y = vals[I2] * by;
		float z = vals[I3] * by;
		return new Vector ( x, y, z );
	}

	public float dot ( final Vector with ) {
		return vals[I1] * with.vals[I1] + vals[I2] * with.vals[I2] + vals[I3] * with.vals[I3];
	}

	public float length () {
		return (float) Math.sqrt ( lengthSquared () );
	}

	public float lengthSquared () {
		return vals[I1] * vals[I1] + vals[I2] * vals[I2] + vals[I3] * vals[I3];
	}

	@Override
	public String toString () {
		return vals[I1] + "," + vals[I2] + "," + vals[I3];
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode ( vals );
		return result;
	}

	@Override
	public boolean equals ( final Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( getClass () != obj.getClass () ) {
			return false;
		}
		Vector other = (Vector) obj;
		return Arrays.equals ( vals, other.vals );
	}

}

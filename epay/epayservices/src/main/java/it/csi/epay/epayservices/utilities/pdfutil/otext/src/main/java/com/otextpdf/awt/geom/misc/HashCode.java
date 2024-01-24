/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt.geom.misc;

public final class HashCode {

	public static final int EMPTY_HASH_CODE = 1;

	private int hashCode = EMPTY_HASH_CODE;

	public static int combine ( int hashCode, boolean value ) {
		int v = value ? 1231 : 1237;
		return combine ( hashCode, v );
	}

	public static int combine ( int hashCode, long value ) {
		int v = (int) ( value ^ ( value >>> 32 ) );
		return combine ( hashCode, v );
	}

	public static int combine ( int hashCode, float value ) {
		int v = Float.floatToIntBits ( value );
		return combine ( hashCode, v );
	}

	public static int combine ( int hashCode, double value ) {
		long v = Double.doubleToLongBits ( value );
		return combine ( hashCode, v );
	}

	public static int combine ( int hashCode, Object value ) {
		return combine ( hashCode, value.hashCode () );
	}

	public static int combine ( int hashCode, int value ) {
		return 31 * hashCode + value;
	}

	public int hashCode () {
		return hashCode;
	}

	public HashCode append ( int value ) {
		hashCode = combine ( hashCode, value );
		return this;
	}

	public HashCode append ( long value ) {
		hashCode = combine ( hashCode, value );
		return this;
	}

	public HashCode append ( float value ) {
		hashCode = combine ( hashCode, value );
		return this;
	}

	public HashCode append ( double value ) {
		hashCode = combine ( hashCode, value );
		return this;
	}

	public HashCode append ( boolean value ) {
		hashCode = combine ( hashCode, value );
		return this;
	}

	public HashCode append ( Object value ) {
		hashCode = combine ( hashCode, value );
		return this;
	}
}

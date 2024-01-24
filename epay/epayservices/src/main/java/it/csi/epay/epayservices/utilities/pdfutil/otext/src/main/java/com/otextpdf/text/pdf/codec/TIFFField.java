/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec;

import java.io.Serializable;


public class TIFFField implements Comparable<TIFFField>, Serializable {

	public static final int TIFF_BYTE = 1;

	public static final int TIFF_ASCII = 2;

	public static final int TIFF_SHORT = 3;

	public static final int TIFF_LONG = 4;

	public static final int TIFF_RATIONAL = 5;

	public static final int TIFF_SBYTE = 6;

	public static final int TIFF_UNDEFINED = 7;

	public static final int TIFF_SSHORT = 8;

	public static final int TIFF_SLONG = 9;

	public static final int TIFF_SRATIONAL = 10;

	public static final int TIFF_FLOAT = 11;

	public static final int TIFF_DOUBLE = 12;

	private static final long serialVersionUID = 9088332901412823834L;

	int tag;

	int type;

	int count;

	Object data;

	public TIFFField ( int tag, int type, int count, Object data ) {
		this.tag = tag;
		this.type = type;
		this.count = count;
		this.data = data;
	}

	public int getTag () {
		return tag;
	}

	public int getType () {
		return type;
	}

	public int getCount () {
		return count;
	}

	public byte[] getAsBytes () {
		return (byte[]) data;
	}

	public char[] getAsChars () {
		return (char[]) data;
	}

	public long[] getAsLongs () {
		return (long[]) data;
	}

	public int getAsInt ( int index ) {
		switch ( type ) {
		case TIFF_BYTE:
		case TIFF_UNDEFINED:
			return ( (byte[]) data )[index] & 0xff;
		case TIFF_SBYTE:
			return ( (byte[]) data )[index];
		case TIFF_SHORT:
			return ( (char[]) data )[index] & 0xffff;
		case TIFF_SSHORT:
			return ( (short[]) data )[index];
		case TIFF_SLONG:
			return ( (int[]) data )[index];
		default:
			throw new ClassCastException ();
		}
	}

	public long getAsLong ( int index ) {
		switch ( type ) {
		case TIFF_BYTE:
		case TIFF_UNDEFINED:
			return ( (byte[]) data )[index] & 0xff;
		case TIFF_SBYTE:
			return ( (byte[]) data )[index];
		case TIFF_SHORT:
			return ( (char[]) data )[index] & 0xffff;
		case TIFF_SSHORT:
			return ( (short[]) data )[index];
		case TIFF_SLONG:
			return ( (int[]) data )[index];
		case TIFF_LONG:
			return ( (long[]) data )[index];
		default:
			throw new ClassCastException ();
		}
	}

	public String getAsString ( int index ) {
		return ( (String[]) data )[index];
	}

	public long[] getAsRational ( int index ) {
		if ( type == TIFF_LONG )
			return getAsLongs ();
		return ( (long[][]) data )[index];
	}

	public int compareTo ( TIFFField o ) {
		if ( o == null ) {
			throw new IllegalArgumentException ();
		}

		int oTag = o.getTag ();

		return Integer.compare ( tag, oTag );
	}
}

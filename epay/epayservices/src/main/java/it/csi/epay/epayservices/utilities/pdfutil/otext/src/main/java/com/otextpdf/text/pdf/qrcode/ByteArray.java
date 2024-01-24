/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.qrcode;

public final class ByteArray {

	private static final int INITIAL_SIZE = 32;

	private byte[] bytes;

	private int size;

	public ByteArray () {
		bytes = null;
		size = 0;
	}

	public ByteArray ( int size ) {
		bytes = new byte[size];
		this.size = size;
	}

	public int at ( int index ) {
		return bytes[index] & 0xff;
	}

	public void set ( int index, int value ) {
		bytes[index] = (byte) value;
	}

	public int size () {
		return size;
	}

	public boolean isEmpty () {
		return size == 0;
	}

	public void set ( byte[] source, int offset, int count ) {
		bytes = new byte[count];
		size = count;
		System.arraycopy ( source, offset, bytes, 0, count );
	}

}

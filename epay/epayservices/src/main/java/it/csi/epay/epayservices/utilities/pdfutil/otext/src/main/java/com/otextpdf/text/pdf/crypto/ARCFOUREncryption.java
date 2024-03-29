/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.crypto;

public class ARCFOUREncryption {

	private final byte[] state = new byte[256];

	private int x;

	private int y;

	public ARCFOUREncryption () {
	}

	public void prepareARCFOURKey ( byte[] key ) {
		prepareARCFOURKey ( key, 0, key.length );
	}

	public void prepareARCFOURKey ( byte[] key, int off, int len ) {
		int index1 = 0;
		int index2 = 0;
		for ( int k = 0; k < 256; ++k )
			state[k] = (byte) k;
		x = 0;
		y = 0;
		byte tmp;
		for ( int k = 0; k < 256; ++k ) {
			index2 = ( key[index1 + off] + state[k] + index2 ) & 255;
			tmp = state[k];
			state[k] = state[index2];
			state[index2] = tmp;
			index1 = ( index1 + 1 ) % len;
		}
	}

	public void encryptARCFOUR ( byte[] dataIn, int off, int len, byte[] dataOut, int offOut ) {
		int length = len + off;
		byte tmp;
		for ( int k = off; k < length; ++k ) {
			x = ( x + 1 ) & 255;
			y = ( state[x] + y ) & 255;
			tmp = state[x];
			state[x] = state[y];
			state[y] = tmp;
			dataOut[k - off + offOut] = (byte) ( dataIn[k] ^ state[( state[x] + state[y] ) & 255] );
		}
	}

	public void encryptARCFOUR ( byte[] data, int off, int len ) {
		encryptARCFOUR ( data, off, len, data, off );
	}

	public void encryptARCFOUR ( byte[] dataIn, byte[] dataOut ) {
		encryptARCFOUR ( dataIn, 0, dataIn.length, dataOut, 0 );
	}

	public void encryptARCFOUR ( byte[] data ) {
		encryptARCFOUR ( data, 0, data.length, data, 0 );
	}
}

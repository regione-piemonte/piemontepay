/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.crypto.AESCipher;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.crypto.ARCFOUREncryption;


public class StandardDecryption {

	private static final int AES_128 = 4;

	private static final int AES_256 = 5;

	private final boolean aes;

	private final byte[] iv = new byte[16];

	protected ARCFOUREncryption arcfour;

	protected AESCipher cipher;

	private byte[] key;

	private boolean initiated;

	private int ivptr;

	public StandardDecryption ( byte[] key, int off, int len, int revision ) {
		aes = ( revision == AES_128 || revision == AES_256 );
		if ( aes ) {
			this.key = new byte[len];
			System.arraycopy ( key, off, this.key, 0, len );
		} else {
			arcfour = new ARCFOUREncryption ();
			arcfour.prepareARCFOURKey ( key, off, len );
		}
	}

	public byte[] update ( byte[] b, int off, int len ) {
		if ( aes ) {
			if ( initiated )
				return cipher.update ( b, off, len );
			else {
				int left = Math.min ( iv.length - ivptr, len );
				System.arraycopy ( b, off, iv, ivptr, left );
				off += left;
				len -= left;
				ivptr += left;
				if ( ivptr == iv.length ) {
					cipher = new AESCipher ( false, key, iv );
					initiated = true;
					if ( len > 0 )
						return cipher.update ( b, off, len );
				}
				return null;
			}
		} else {
			byte[] b2 = new byte[len];
			arcfour.encryptARCFOUR ( b, off, len, b2, 0 );
			return b2;
		}
	}

	public byte[] finish () {
		if ( aes ) {
			return cipher.doFinal ();
		} else
			return null;
	}
}

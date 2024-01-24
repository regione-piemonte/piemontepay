/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.crypto;

public final class IVGenerator {

	private static final ARCFOUREncryption arcfour;

	static {
		arcfour = new ARCFOUREncryption ();
		long time = System.currentTimeMillis ();
		long mem = Runtime.getRuntime ().freeMemory ();
		String s = time + "+" + mem;
		arcfour.prepareARCFOURKey ( s.getBytes () );
	}

	private IVGenerator () {
	}

	public static byte[] getIV () {
		return getIV ( 16 );
	}

	public static byte[] getIV ( int len ) {
		byte[] b = new byte[len];
		synchronized ( arcfour ) {
			arcfour.encryptARCFOUR ( b );
		}
		return b;
	}
}

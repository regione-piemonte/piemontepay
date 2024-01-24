/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

public class PdfLiteral extends PdfObject {

	private long position;

	public PdfLiteral ( String text ) {
		super ( 0, text );
	}

	public PdfLiteral ( byte[] b ) {
		super ( 0, b );
	}

	public PdfLiteral ( int size ) {
		super ( 0, (byte[]) null );
		bytes = new byte[size];
		java.util.Arrays.fill ( bytes, (byte) 32 );
	}

	public PdfLiteral ( int type, String text ) {
		super ( type, text );
	}

	public PdfLiteral ( int type, byte[] b ) {
		super ( type, b );
	}

	public void toPdf ( PdfWriter writer, java.io.OutputStream os ) throws java.io.IOException {
		if ( os instanceof OutputStreamCounter )
			position = ( (OutputStreamCounter) os ).getCounter ();
		super.toPdf ( writer, os );
	}

	public long getPosition () {
		return this.position;
	}

}

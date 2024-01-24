/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;


public class PdfNumber extends PdfObject {

	private final double value;

	public PdfNumber ( String content ) {
		super ( NUMBER );
		try {
			value = Double.parseDouble ( content.trim () );
			setContent ( content );
		} catch ( NumberFormatException nfe ) {
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "1.is.not.a.valid.number.2", content, nfe.toString () ) );
		}
	}

	public PdfNumber ( int value ) {
		super ( NUMBER );
		this.value = value;
		setContent ( String.valueOf ( value ) );
	}

	public PdfNumber ( long value ) {
		super ( NUMBER );
		this.value = value;
		setContent ( String.valueOf ( value ) );
	}

	public PdfNumber ( double value ) {
		super ( NUMBER );
		this.value = value;
		setContent ( ByteBuffer.formatDouble ( value ) );
	}

	public PdfNumber ( float value ) {
		this ( (double) value );
	}

	public int intValue () {
		return (int) value;
	}

	public long longValue () {
		return (long) value;
	}

	public double doubleValue () {
		return value;
	}

	public float floatValue () {
		return (float) value;
	}

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.qrcode;

public final class ErrorCorrectionLevel {

	public static final ErrorCorrectionLevel L = new ErrorCorrectionLevel ( 0, 0x01, "L" );

	public static final ErrorCorrectionLevel M = new ErrorCorrectionLevel ( 1, 0x00, "M" );

	public static final ErrorCorrectionLevel Q = new ErrorCorrectionLevel ( 2, 0x03, "Q" );

	public static final ErrorCorrectionLevel H = new ErrorCorrectionLevel ( 3, 0x02, "H" );

	private final int ordinal;

	private final int bits;

	private final String name;

	private ErrorCorrectionLevel ( int ordinal, int bits, String name ) {
		this.ordinal = ordinal;
		this.bits = bits;
		this.name = name;
	}

	public int ordinal () {
		return ordinal;
	}

	public int getBits () {
		return bits;
	}

	public String getName () {
		return name;
	}

	public String toString () {
		return name;
	}

}

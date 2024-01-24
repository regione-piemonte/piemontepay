/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.qrcode;

public final class Mode {

	public static final Mode TERMINATOR = new Mode ( new int[] { 0, 0, 0 }, 0x00, "TERMINATOR" ); // Not really a mode...

	public static final Mode NUMERIC = new Mode ( new int[] { 10, 12, 14 }, 0x01, "NUMERIC" );

	public static final Mode ALPHANUMERIC = new Mode ( new int[] { 9, 11, 13 }, 0x02, "ALPHANUMERIC" );

	public static final Mode STRUCTURED_APPEND = new Mode ( new int[] { 0, 0, 0 }, 0x03, "STRUCTURED_APPEND" ); // Not supported

	public static final Mode BYTE = new Mode ( new int[] { 8, 16, 16 }, 0x04, "BYTE" );

	public static final Mode ECI = new Mode ( null, 0x07, "ECI" );

	public static final Mode KANJI = new Mode ( new int[] { 8, 10, 12 }, 0x08, "KANJI" );

	public static final Mode FNC1_FIRST_POSITION = new Mode ( null, 0x05, "FNC1_FIRST_POSITION" );

	public static final Mode FNC1_SECOND_POSITION = new Mode ( null, 0x09, "FNC1_SECOND_POSITION" );

	private final int[] characterCountBitsForVersions;

	private final int bits;

	private final String name;

	private Mode ( int[] characterCountBitsForVersions, int bits, String name ) {
		this.characterCountBitsForVersions = characterCountBitsForVersions;
		this.bits = bits;
		this.name = name;
	}

	public int getCharacterCountBits ( Version version ) {
		if ( characterCountBitsForVersions == null ) {
			throw new IllegalArgumentException ( "Character count doesn't apply to this mode" );
		}
		int number = version.getVersionNumber ();
		int offset;
		if ( number <= 9 ) {
			offset = 0;
		} else if ( number <= 26 ) {
			offset = 1;
		} else {
			offset = 2;
		}
		return characterCountBitsForVersions[offset];
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

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


public class ICC_Profile {

	private static final HashMap<String, Integer> cstags = new HashMap<> ();

	static {
		cstags.put ( "XYZ ", 3 );
		cstags.put ( "Lab ", 3 );
		cstags.put ( "Luv ", 3 );
		cstags.put ( "YCbr", 3 );
		cstags.put ( "Yxy ", 3 );
		cstags.put ( "RGB ", 3 );
		cstags.put ( "GRAY", 1 );
		cstags.put ( "HSV ", 3 );
		cstags.put ( "HLS ", 3 );
		cstags.put ( "CMYK", 4 );
		cstags.put ( "CMY ", 3 );
		cstags.put ( "2CLR", 2 );
		cstags.put ( "3CLR", 3 );
		cstags.put ( "4CLR", 4 );
		cstags.put ( "5CLR", 5 );
		cstags.put ( "6CLR", 6 );
		cstags.put ( "7CLR", 7 );
		cstags.put ( "8CLR", 8 );
		cstags.put ( "9CLR", 9 );
		cstags.put ( "ACLR", 10 );
		cstags.put ( "BCLR", 11 );
		cstags.put ( "CCLR", 12 );
		cstags.put ( "DCLR", 13 );
		cstags.put ( "ECLR", 14 );
		cstags.put ( "FCLR", 15 );
	}

	protected byte[] data;

	protected int numComponents;

	protected ICC_Profile () {
	}

	public static ICC_Profile getInstance ( byte[] data, int numComponents ) {
		if ( data.length < 128 || data[36] != 0x61 || data[37] != 0x63
						|| data[38] != 0x73 || data[39] != 0x70 )
			throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "invalid.icc.profile" ) );
		ICC_Profile icc = new ICC_Profile ();
		icc.data = data;
		Integer cs;
		cs = cstags.get ( new String ( data, 16, 4, StandardCharsets.US_ASCII ) );
		int nc = cs == null ? 0 : cs;
		icc.numComponents = nc;
		// invalid ICC
		if ( nc != numComponents ) {
			throw new IllegalArgumentException (
							"ICC profile contains " + nc + " component(s), the image data contains " + numComponents + " component(s)" );
		}
		return icc;
	}

	public static ICC_Profile getInstance ( byte[] data ) {
		Integer cs;
		cs = cstags.get ( new String ( data, 16, 4, StandardCharsets.US_ASCII ) );
		int numComponents = cs == null ? 0 : cs;
		return getInstance ( data, numComponents );
	}

	public static ICC_Profile getInstance ( InputStream file ) {
		try {
			byte[] head = new byte[128];
			int remain = head.length;
			int ptr = 0;
			while ( remain > 0 ) {
				int n = file.read ( head, ptr, remain );
				if ( n < 0 )
					throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "invalid.icc.profile" ) );
				remain -= n;
				ptr += n;
			}
			if ( head[36] != 0x61 || head[37] != 0x63
							|| head[38] != 0x73 || head[39] != 0x70 )
				throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "invalid.icc.profile" ) );
			remain = ( head[0] & 0xff ) << 24 | ( head[1] & 0xff ) << 16
							| ( head[2] & 0xff ) << 8 | head[3] & 0xff;
			byte[] icc = new byte[remain];
			System.arraycopy ( head, 0, icc, 0, head.length );
			remain -= head.length;
			ptr = head.length;
			while ( remain > 0 ) {
				int n = file.read ( icc, ptr, remain );
				if ( n < 0 )
					throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "invalid.icc.profile" ) );
				remain -= n;
				ptr += n;
			}
			return getInstance ( icc );
		} catch ( Exception ex ) {
			throw new ExceptionConverter ( ex );
		}
	}

	public byte[] getData () {
		return data;
	}

	public int getNumComponents () {
		return numComponents;
	}
}

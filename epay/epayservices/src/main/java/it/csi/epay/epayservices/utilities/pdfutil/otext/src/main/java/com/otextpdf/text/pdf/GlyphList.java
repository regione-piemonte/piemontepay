/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io.StreamUtil;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.FontsResourceAnchor;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.StringTokenizer;


public class GlyphList {

	private static final HashMap<Integer, String> unicode2names = new HashMap<> ();

	private static final HashMap<String, int[]> names2unicode = new HashMap<> ();

	static {
		InputStream is = null;
		try {
			is = StreamUtil.getResourceStream ( BaseFont.RESOURCE_PATH + "glyphlist.txt", FontsResourceAnchor.class.getClassLoader () );
			if ( is == null ) {
				String msg = "glyphlist.txt not found as resource. (It must exist as resource in the package com.otextpdf.text.pdf.fonts)";
				throw new Exception ( msg );
			}
			byte[] buf = new byte[1024];
			ByteArrayOutputStream out = new ByteArrayOutputStream ();
			while ( true ) {
				int size = is.read ( buf );
				if ( size < 0 )
					break;
				out.write ( buf, 0, size );
			}
			is.close ();
			is = null;
			String s = PdfEncodings.convertToString ( out.toByteArray (), null );
			StringTokenizer tk = new StringTokenizer ( s, "\r\n" );
			while ( tk.hasMoreTokens () ) {
				String line = tk.nextToken ();
				if ( line.startsWith ( "#" ) )
					continue;
				StringTokenizer t2 = new StringTokenizer ( line, " ;\r\n\t\f" );
				String name;
				String hex;
				if ( !t2.hasMoreTokens () )
					continue;
				name = t2.nextToken ();
				if ( !t2.hasMoreTokens () )
					continue;
				hex = t2.nextToken ();
				Integer num = Integer.valueOf ( hex, 16 );
				unicode2names.put ( num, name );
				names2unicode.put ( name, new int[] { num } );
			}
		} catch ( Exception e ) {
			System.err.println ( "glyphlist.txt loading error: " + e.getMessage () );
		} finally {
			if ( is != null ) {
				try {
					is.close ();
				} catch ( Exception e ) {
					// empty on purpose
				}
			}
		}
	}

	public static int[] nameToUnicode ( String name ) {
		int[] v = names2unicode.get ( name );
		if ( v == null && name.length () == 7 && name.toLowerCase ().startsWith ( "uni" ) ) {
			try {
				return new int[] { Integer.parseInt ( name.substring ( 3 ), 16 ) };
			} catch ( Exception ignored ) {
			}
		}
		return v;
	}

	public static String unicodeToName ( int num ) {
		return unicode2names.get ( num );
	}
}

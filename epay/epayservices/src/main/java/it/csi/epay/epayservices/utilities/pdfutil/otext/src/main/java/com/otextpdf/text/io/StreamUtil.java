/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public final class StreamUtil {

	private StreamUtil () {
	}

	public static byte[] inputStreamToArray ( InputStream is ) throws IOException {
		byte[] b = new byte[8192];
		ByteArrayOutputStream out = new ByteArrayOutputStream ();
		while ( true ) {
			int read = is.read ( b );
			if ( read < 1 )
				break;
			out.write ( b, 0, read );
		}
		out.close ();
		return out.toByteArray ();
	}

	public static InputStream getResourceStream ( String key ) {
		return getResourceStream ( key, null );
	}

	public static InputStream getResourceStream ( String key, ClassLoader loader ) {
		if ( key.startsWith ( "/" ) )
			key = key.substring ( 1 );
		InputStream is = null;
		if ( loader != null ) {
			is = loader.getResourceAsStream ( key );
			if ( is != null )
				return is;
		}
		try {
			ClassLoader contextClassLoader = Thread.currentThread ().getContextClassLoader ();
			if ( contextClassLoader != null ) {
				is = contextClassLoader.getResourceAsStream ( key );
			}
		} catch ( Throwable ignored ) {
		}

		if ( is == null ) {
			is = StreamUtil.class.getResourceAsStream ( "/" + key );
		}
		if ( is == null ) {
			is = ClassLoader.getSystemResourceAsStream ( key );
		}
		return is;
	}
}

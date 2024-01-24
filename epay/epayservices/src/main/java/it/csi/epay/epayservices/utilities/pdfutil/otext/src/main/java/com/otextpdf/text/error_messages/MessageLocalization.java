/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io.StreamUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


public final class MessageLocalization {

	private static final String BASE_PATH = "com/otextpdf/text/l10n/error/";

	private static HashMap<String, String> defaultLanguage = new HashMap<> ();

	private static HashMap<String, String> currentLanguage;

	static {
		try {
			defaultLanguage = getLanguageMessages ();
		} catch ( Exception ignored ) {
		}
		if ( defaultLanguage == null )
			defaultLanguage = new HashMap<> ();
	}

	private MessageLocalization () {
	}

	public static String getMessage ( String key ) {
		return getMessage ( key, true );
	}

	public static String getMessage ( String key, boolean useDefaultLanguageIfMessageNotFound ) {
		HashMap<String, String> cl = currentLanguage;
		String val;
		if ( cl != null ) {
			val = cl.get ( key );
			if ( val != null )
				return val;
		}

		if ( useDefaultLanguageIfMessageNotFound ) {
			cl = defaultLanguage;
			val = cl.get ( key );
			if ( val != null )
				return val;
		}

		return "No message found for " + key;
	}

	public static String getComposedMessage ( String key, int p1 ) {
		return getComposedMessage ( key, String.valueOf ( p1 ), null, null, null );
	}

	public static String getComposedMessage ( final String key, final Object... param ) {
		String msg = getMessage ( key );
		if ( null != param ) {
			int i = 1;
			for ( Object o : param ) {
				if ( null != o ) {
					msg = msg.replace ( "{" + i + "}", o.toString () );
				}
				i++;
			}
		}
		return msg;
	}

	public static void setMessages ( Reader r ) throws IOException {
		currentLanguage = readLanguageStream ( r );
	}

	private static HashMap<String, String> getLanguageMessages () throws IOException {
		InputStream is = null;
		try {
			String file;
			file = "en" + ".lng";
			is = StreamUtil.getResourceStream ( BASE_PATH + file, MessageLocalization.class.getClassLoader () );
			if ( is != null )
				return readLanguageStream ( is );
			return null;
		} finally {
			try {
				if ( null != is ) {
					is.close ();
				}
			} catch ( Exception ignored ) {
			}
		}
	}

	private static HashMap<String, String> readLanguageStream ( InputStream is ) throws IOException {
		return readLanguageStream ( new InputStreamReader ( is, StandardCharsets.UTF_8 ) );
	}

	private static HashMap<String, String> readLanguageStream ( Reader r ) throws IOException {
		HashMap<String, String> lang = new HashMap<> ();
		BufferedReader br = new BufferedReader ( r );
		String line;
		while ( ( line = br.readLine () ) != null ) {
			int idxeq = line.indexOf ( '=' );
			if ( idxeq < 0 )
				continue;
			String key = line.substring ( 0, idxeq ).trim ();
			if ( key.startsWith ( "#" ) )
				continue;
			lang.put ( key, line.substring ( idxeq + 1 ) );
		}
		return lang;
	}
}

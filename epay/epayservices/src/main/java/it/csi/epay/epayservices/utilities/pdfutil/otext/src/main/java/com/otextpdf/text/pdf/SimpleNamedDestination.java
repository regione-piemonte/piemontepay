/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.simpleparser.SimpleXMLDocHandler;

import java.util.HashMap;
import java.util.Map;


public final class SimpleNamedDestination implements SimpleXMLDocHandler {

	private HashMap<String, String> xmlNames;

	private HashMap<String, String> xmlLast;

	private SimpleNamedDestination () {
	}

	public static String unEscapeBinaryString ( String s ) {
		StringBuilder buf = new StringBuilder ();
		char[] cc = s.toCharArray ();
		int len = cc.length;
		for ( int k = 0; k < len; ++k ) {
			char c = cc[k];
			if ( c == '\\' ) {
				if ( ++k >= len ) {
					buf.append ( '\\' );
					break;
				}
				c = cc[k];
				if ( c >= '0' && c <= '7' ) {
					int n = c - '0';
					++k;
					for ( int j = 0; j < 2 && k < len; ++j ) {
						c = cc[k];
						if ( c >= '0' && c <= '7' ) {
							++k;
							n = n * 8 + c - '0';
						} else {
							break;
						}
					}
					--k;
					buf.append ( (char) n );
				} else
					buf.append ( c );
			} else
				buf.append ( c );
		}
		return buf.toString ();
	}

	public void endDocument () {
	}

	public void endElement ( String tag ) {
		if ( tag.equals ( "Destination" ) ) {
			if ( xmlLast == null && xmlNames != null )
				return;
			else
				throw new RuntimeException ( MessageLocalization.getComposedMessage ( "destination.end.tag.out.of.place" ) );
		}
		if ( !tag.equals ( "Name" ) )
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "invalid.end.tag.1", tag ) );
		if ( xmlLast == null || xmlNames == null )
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "name.end.tag.out.of.place" ) );
		if ( !xmlLast.containsKey ( "Page" ) )
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "page.attribute.missing" ) );
		xmlNames.put ( unEscapeBinaryString ( xmlLast.get ( "Name" ) ), xmlLast.get ( "Page" ) );
		xmlLast = null;
	}

	public void startDocument () {
	}

	public void startElement ( String tag, Map<String, String> h ) {
		if ( xmlNames == null ) {
			if ( tag.equals ( "Destination" ) ) {
				xmlNames = new HashMap<> ();
				return;
			} else
				throw new RuntimeException ( MessageLocalization.getComposedMessage ( "root.element.is.not.destination" ) );
		}
		if ( !tag.equals ( "Name" ) )
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "tag.1.not.allowed", tag ) );
		if ( xmlLast != null )
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "nested.tags.are.not.allowed" ) );
		xmlLast = new HashMap<> ( h );
		xmlLast.put ( "Name", "" );
	}

	public void text ( String str ) {
		if ( xmlLast == null )
			return;
		String name = xmlLast.get ( "Name" );
		name += str;
		xmlLast.put ( "Name", name );
	}
}

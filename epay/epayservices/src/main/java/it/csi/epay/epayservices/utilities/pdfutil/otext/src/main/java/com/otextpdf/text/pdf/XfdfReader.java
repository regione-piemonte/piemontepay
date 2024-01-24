/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.simpleparser.SimpleXMLDocHandler;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.simpleparser.SimpleXMLParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;


public class XfdfReader implements SimpleXMLDocHandler {

	private final Stack<String> fieldNames = new Stack<> ();

	private final Stack<String> fieldValues = new Stack<> ();

	protected HashMap<String, List<String>> listFields;

	HashMap<String, String> fields;

	String fileSpec;

	private boolean foundRoot = false;

	public XfdfReader ( InputStream is ) throws IOException {
		SimpleXMLParser.parse ( this, is );
	}

	public HashMap<String, String> getFields () {
		return fields;
	}

	public String getFieldValue ( String name ) {
		return fields.get ( name );
	}

	public List<String> getListValues ( String name ) {
		return listFields.get ( name );
	}

	public void startElement ( String tag, Map<String, String> h ) {
		if ( !foundRoot ) {
			if ( !tag.equals ( "xfdf" ) )
				throw new RuntimeException ( MessageLocalization.getComposedMessage ( "root.element.is.not.xfdf.1", tag ) );
			else
				foundRoot = true;
		}

		switch ( tag ) {
		case "xfdf":

			break;
		case "f":
			fileSpec = h.get ( "href" );
			break;
		case "fields":
			fields = new HashMap<> ();
			listFields = new HashMap<> ();
			break;
		case "field":
			String fName = h.get ( "name" );
			fieldNames.push ( fName );
			break;
		case "value":
			fieldValues.push ( "" );
			break;
		}
	}

	public void endElement ( String tag ) {
		if ( tag.equals ( "value" ) ) {
			StringBuilder fName = new StringBuilder ();
			for ( int k = 0; k < fieldNames.size (); ++k ) {
				fName.append ( "." ).append ( fieldNames.elementAt ( k ) );
			}
			if ( fName.toString ().startsWith ( "." ) )
				fName = new StringBuilder ( fName.substring ( 1 ) );
			String fVal = fieldValues.pop ();
			String old = fields.put ( fName.toString (), fVal );
			if ( old != null ) {
				List<String> l = listFields.get ( fName.toString () );
				if ( l == null ) {
					l = new ArrayList<> ();
					l.add ( old );
				}
				l.add ( fVal );
				listFields.put ( fName.toString (), l );
			}
		} else if ( tag.equals ( "field" ) ) {
			if ( !fieldNames.isEmpty () )
				fieldNames.pop ();
		}
	}

	public void startDocument () {
		fileSpec = "";
	}

	public void endDocument () {

	}

	public void text ( String str ) {
		if ( fieldNames.isEmpty () || fieldValues.isEmpty () )
			return;

		String val = fieldValues.pop ();
		val += str;
		fieldValues.push ( val );
	}
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.xmp;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.XMLUtil;

import java.util.Enumeration;
import java.util.Properties;


@Deprecated
public class LangAlt extends Properties {

	public static final String DEFAULT = "x-default";

	private static final long serialVersionUID = 4396971487200843099L;

	public LangAlt ( String defaultValue ) {
		super ();
		addLanguage ( DEFAULT, defaultValue );
	}

	public void addLanguage ( String language, String value ) {
		setProperty ( language, XMLUtil.escapeXML ( value, false ) );
	}

	protected void process ( StringBuffer buf, Object lang ) {
		buf.append ( "<rdf:li xml:lang=\"" );
		buf.append ( lang );
		buf.append ( "\" >" );
		buf.append ( get ( lang ) );
		buf.append ( "</rdf:li>" );
	}

	@Override
	public String toString () {
		StringBuffer sb = new StringBuffer ();
		sb.append ( "<rdf:Alt>" );
		for ( Enumeration<?> e = this.propertyNames (); e.hasMoreElements (); ) {
			process ( sb, e.nextElement () );
		}
		sb.append ( "</rdf:Alt>" );
		return sb.toString ();
	}

}

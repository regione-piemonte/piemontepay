/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.xmp;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.XMLUtil;

import java.util.ArrayList;


@Deprecated
public class XmpArray extends ArrayList<String> {

	public static final String UNORDERED = "rdf:Bag";

	public static final String ORDERED = "rdf:Seq";

	public static final String ALTERNATIVE = "rdf:Alt";

	private static final long serialVersionUID = 5722854116328732742L;

	protected String type;

	public XmpArray ( String type ) {
		this.type = type;
	}

	@Override
	public String toString () {
		StringBuilder buf = new StringBuilder ( "<" );
		buf.append ( type );
		buf.append ( '>' );
		String s;
		for ( String string : this ) {
			s = string;
			buf.append ( "<rdf:li>" );
			buf.append ( XMLUtil.escapeXML ( s, false ) );
			buf.append ( "</rdf:li>" );
		}
		buf.append ( "</" );
		buf.append ( type );
		buf.append ( '>' );
		return buf.toString ();
	}
}

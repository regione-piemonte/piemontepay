/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.xmp;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.XMLUtil;

import java.util.Enumeration;
import java.util.Properties;


@Deprecated
public abstract class XmpSchema extends Properties {

	private static final long serialVersionUID = -176374295948945272L;

	protected String xmlns;

	public XmpSchema ( String xmlns ) {
		super ();
		this.xmlns = xmlns;
	}

	@Override
	public String toString () {
		StringBuffer buf = new StringBuffer ();
		for ( Enumeration<?> e = this.propertyNames (); e.hasMoreElements (); ) {
			process ( buf, e.nextElement () );
		}
		return buf.toString ();
	}

	protected void process ( StringBuffer buf, Object p ) {
		buf.append ( '<' );
		buf.append ( p );
		buf.append ( '>' );
		buf.append ( this.get ( p ) );
		buf.append ( "</" );
		buf.append ( p );
		buf.append ( '>' );
	}

	public String getXmlns () {
		return xmlns;
	}

	@Override
	public Object setProperty ( String key, String value ) {
		return super.setProperty ( key, XMLUtil.escapeXML ( value, false ) );
	}

	public Object setProperty ( String key, XmpArray value ) {
		return super.setProperty ( key, value.toString () );
	}

	public Object setProperty ( String key, LangAlt value ) {
		return super.setProperty ( key, value.toString () );
	}

}

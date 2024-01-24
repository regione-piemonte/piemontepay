/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.xmp;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Version;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfDate;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfDictionary;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfString;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPConst;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPMeta;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPMetaFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPUtils;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.PropertyOptions;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.SerializeOptions;

import java.io.IOException;
import java.io.OutputStream;


public class XmpWriter {

	public static final String UTF8 = "UTF-8";

	public static final String UTF16 = "UTF-16";

	public static final String UTF16BE = "UTF-16BE";

	public static final String UTF16LE = "UTF-16LE";

	protected XMPMeta xmpMeta;

	protected OutputStream outputStream;

	protected SerializeOptions serializeOptions;

	public XmpWriter ( OutputStream os, String utfEncoding, int extraSpace ) {
		outputStream = os;
		serializeOptions = new SerializeOptions ();
		if ( UTF16BE.equals ( utfEncoding ) || UTF16.equals ( utfEncoding ) )
			serializeOptions.setEncodeUTF16BE ( true );
		else if ( UTF16LE.equals ( utfEncoding ) )
			serializeOptions.setEncodeUTF16LE ( true );
		serializeOptions.setPadding ( extraSpace );
		xmpMeta = XMPMetaFactory.create ();
		xmpMeta.setObjectName ( XMPConst.TAG_XMPMETA );
		xmpMeta.setObjectName ( "" );
		try {
			xmpMeta.setProperty ( XMPConst.NS_DC, DublinCoreProperties.FORMAT, "application/pdf" );
			xmpMeta.setProperty ( XMPConst.NS_PDF, PdfProperties.PRODUCER, Version.getInstance ().getVersion () );
		} catch ( XMPException ignored ) {
		}
	}

	public XmpWriter ( OutputStream os ) {
		this ( os, UTF8, 2000 );
	}

	public XmpWriter ( OutputStream os, PdfDictionary info ) throws IOException {
		this ( os );
		if ( info != null ) {
			PdfName key;
			PdfObject obj;
			String value;
			for ( PdfName pdfName : info.getKeys () ) {
				key = pdfName;
				obj = info.get ( key );
				if ( obj == null )
					continue;
				if ( !obj.isString () )
					continue;
				value = ( (PdfString) obj ).toUnicodeString ();
				try {
					addDocInfoProperty ( key, value );
				} catch ( XMPException xmpExc ) {
					throw new IOException ( xmpExc.getMessage () );
				}
			}
		}
	}

	public XMPMeta getXmpMeta () {
		return xmpMeta;
	}

	@Deprecated
	public void addRdfDescription ( String xmlns, String content ) throws IOException {
		try {
			String str = "<rdf:RDF xmlns:rdf=\"" + XMPConst.NS_RDF + "\">" +
							"<rdf:Description rdf:about=\"" + xmpMeta.getObjectName () +
							"\" " +
							xmlns +
							">" +
							content +
							"</rdf:Description></rdf:RDF>\n";
			XMPMeta extMeta = XMPMetaFactory.parseFromString ( str );
			XMPUtils.appendProperties ( extMeta, xmpMeta, true, true );
		} catch ( XMPException xmpExc ) {
			throw new IOException ( xmpExc.getMessage () );
		}
	}

	@Deprecated
	public void addRdfDescription ( XmpSchema s ) throws IOException {
		try {
			String str = "<rdf:RDF xmlns:rdf=\"" + XMPConst.NS_RDF + "\">" +
							"<rdf:Description rdf:about=\"" + xmpMeta.getObjectName () +
							"\" " +
							s.getXmlns () +
							">" +
							s +
							"</rdf:Description></rdf:RDF>\n";
			XMPMeta extMeta = XMPMetaFactory.parseFromString ( str );
			XMPUtils.appendProperties ( extMeta, xmpMeta, true, true );
		} catch ( XMPException xmpExc ) {
			throw new IOException ( xmpExc.getMessage () );
		}
	}

	public void setProperty ( String schemaNS, String propName, Object value ) throws XMPException {
		xmpMeta.setProperty ( schemaNS, propName, value );
	}

	public void serialize ( OutputStream externalOutputStream ) throws XMPException {
		XMPMetaFactory.serialize ( xmpMeta, externalOutputStream, serializeOptions );
	}

	public void close () throws IOException {
		if ( outputStream == null )
			return;
		try {
			XMPMetaFactory.serialize ( xmpMeta, outputStream, serializeOptions );
			outputStream = null;
		} catch ( XMPException xmpExc ) {
			throw new IOException ( xmpExc.getMessage () );
		}
	}

	public void addDocInfoProperty ( Object key, String value ) throws XMPException {
		if ( key instanceof String )
			key = new PdfName ( (String) key );
		if ( PdfName.TITLE.equals ( key ) ) {
			xmpMeta.setLocalizedText ( XMPConst.NS_DC, DublinCoreProperties.TITLE, XMPConst.X_DEFAULT, XMPConst.X_DEFAULT, value );
		} else if ( PdfName.AUTHOR.equals ( key ) ) {
			xmpMeta.appendArrayItem ( XMPConst.NS_DC, DublinCoreProperties.CREATOR, new PropertyOptions ( PropertyOptions.ARRAY_ORDERED ), value, null );
		} else if ( PdfName.SUBJECT.equals ( key ) ) {
			xmpMeta.setLocalizedText ( XMPConst.NS_DC, DublinCoreProperties.DESCRIPTION, XMPConst.X_DEFAULT, XMPConst.X_DEFAULT, value );
		} else if ( PdfName.KEYWORDS.equals ( key ) ) {
			for ( String v : value.split ( ",|;" ) )
				xmpMeta.appendArrayItem ( XMPConst.NS_DC, DublinCoreProperties.SUBJECT, new PropertyOptions ( PropertyOptions.ARRAY ), v.trim (), null );
			xmpMeta.setProperty ( XMPConst.NS_PDF, PdfProperties.KEYWORDS, value );
		} else if ( PdfName.PRODUCER.equals ( key ) ) {
			xmpMeta.setProperty ( XMPConst.NS_PDF, PdfProperties.PRODUCER, value );
		} else if ( PdfName.CREATOR.equals ( key ) ) {
			xmpMeta.setProperty ( XMPConst.NS_XMP, XmpBasicProperties.CREATORTOOL, value );
		} else if ( PdfName.CREATIONDATE.equals ( key ) ) {
			xmpMeta.setProperty ( XMPConst.NS_XMP, XmpBasicProperties.CREATEDATE, PdfDate.getW3CDate ( value ) );
		} else if ( PdfName.MODDATE.equals ( key ) ) {
			xmpMeta.setProperty ( XMPConst.NS_XMP, XmpBasicProperties.MODIFYDATE, PdfDate.getW3CDate ( value ) );
		}
	}
}

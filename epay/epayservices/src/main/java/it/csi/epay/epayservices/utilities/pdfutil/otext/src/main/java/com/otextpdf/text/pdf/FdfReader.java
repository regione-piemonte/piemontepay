/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.Counter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.CounterFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


public class FdfReader extends PdfReader {

	protected static Counter COUNTER = CounterFactory.getCounter ( FdfReader.class );

	HashMap<String, PdfDictionary> fields;

	String fileSpec;

	PdfName encoding;

	public FdfReader ( InputStream is ) throws IOException {
		super ( is );
	}

	protected Counter getCounter () {
		return COUNTER;
	}

	@Override
	protected void readPdf () throws IOException {
		fields = new HashMap<> ();
		try {
			tokens.checkFdfHeader ();
			rebuildXref ();
			readDocObj ();
		} finally {
			try {
				tokens.close ();
			} catch ( Exception e ) {
				// empty on purpose
			}
		}
		readFields ();
	}

	protected void kidNode ( PdfDictionary merged, String name ) {
		PdfArray kids = merged.getAsArray ( PdfName.KIDS );
		if ( kids == null || kids.isEmpty () ) {
			if ( !name.isEmpty () )
				name = name.substring ( 1 );
			fields.put ( name, merged );
		} else {
			merged.remove ( PdfName.KIDS );
			for ( int k = 0; k < kids.size (); ++k ) {
				PdfDictionary dic = new PdfDictionary ();
				dic.merge ( merged );
				PdfDictionary newDic = kids.getAsDict ( k );
				PdfString t = newDic.getAsString ( PdfName.T );
				String newName = name;
				if ( t != null )
					newName += "." + t.toUnicodeString ();
				dic.merge ( newDic );
				dic.remove ( PdfName.T );
				kidNode ( dic, newName );
			}
		}
	}

	protected void readFields () {
		catalog = trailer.getAsDict ( PdfName.ROOT );
		PdfDictionary fdf = catalog.getAsDict ( PdfName.FDF );
		if ( fdf == null )
			return;
		PdfString fs = fdf.getAsString ( PdfName.F );
		if ( fs != null )
			fileSpec = fs.toUnicodeString ();
		PdfArray fld = fdf.getAsArray ( PdfName.FIELDS );
		if ( fld == null )
			return;
		encoding = fdf.getAsName ( PdfName.ENCODING );
		PdfDictionary merged = new PdfDictionary ();
		merged.put ( PdfName.KIDS, fld );
		kidNode ( merged, "" );
	}

	public HashMap<String, PdfDictionary> getFields () {
		return fields;
	}

	public String getFieldValue ( String name ) {
		PdfDictionary field = fields.get ( name );
		if ( field == null )
			return null;
		PdfObject v = getPdfObject ( field.get ( PdfName.V ) );
		if ( v == null )
			return null;
		if ( v.isName () )
			return PdfName.decodeName ( v.toString () );
		else if ( v.isString () ) {
			PdfString vs = (PdfString) v;
			if ( encoding == null || vs.getEncoding () != null )
				return vs.toUnicodeString ();
			byte[] b = vs.getBytes ();
			if ( b.length >= 2 && b[0] == (byte) 254 && b[1] == (byte) 255 )
				return vs.toUnicodeString ();
			try {
				if ( encoding.equals ( PdfName.SHIFT_JIS ) )
					return new String ( b, "SJIS" );
				else if ( encoding.equals ( PdfName.UHC ) )
					return new String ( b, "MS949" );
				else if ( encoding.equals ( PdfName.GBK ) )
					return new String ( b, "GBK" );
				else if ( encoding.equals ( PdfName.BIGFIVE ) )
					return new String ( b, "Big5" );
				else if ( encoding.equals ( PdfName.UTF_8 ) )
					return new String ( b, StandardCharsets.UTF_8 );
			} catch ( Exception ignored ) {
			}
			return vs.toUnicodeString ();
		}
		return null;
	}

}

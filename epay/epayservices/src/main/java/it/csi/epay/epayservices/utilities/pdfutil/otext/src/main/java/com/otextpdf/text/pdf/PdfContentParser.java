/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;

import java.io.IOException;
import java.util.ArrayList;


public class PdfContentParser {

	public static final int COMMAND_TYPE = 200;

	private final PRTokeniser tokeniser;

	public PdfContentParser ( PRTokeniser tokeniser ) {
		this.tokeniser = tokeniser;
	}

	public ArrayList<PdfObject> parse ( ArrayList<PdfObject> ls ) throws IOException {
		if ( ls == null )
			ls = new ArrayList<> ();
		else
			ls.clear ();
		PdfObject ob;
		while ( ( ob = readPRObject () ) != null ) {
			ls.add ( ob );
			if ( ob.type () == COMMAND_TYPE )
				break;
		}
		return ls;
	}

	public PRTokeniser getTokeniser () {
		return this.tokeniser;
	}

	public PdfDictionary readDictionary () throws IOException {
		PdfDictionary dic = new PdfDictionary ();
		while ( true ) {
			if ( nextValidToken () )
				throw new IOException ( MessageLocalization.getComposedMessage ( "unexpected.end.of.file" ) );
			if ( tokeniser.getTokenType () == PRTokeniser.TokenType.END_DIC )
				break;
			if ( tokeniser.getTokenType () == PRTokeniser.TokenType.OTHER && "def".equals ( tokeniser.getStringValue () ) )
				continue;
			if ( tokeniser.getTokenType () != PRTokeniser.TokenType.NAME )
				throw new IOException ( MessageLocalization.getComposedMessage ( "dictionary.key.1.is.not.a.name", tokeniser.getStringValue () ) );
			PdfName name = new PdfName ( tokeniser.getStringValue (), false );
			PdfObject obj = readPRObject ();
			int type = obj.type ();
			if ( -type == PRTokeniser.TokenType.END_DIC.ordinal () )
				throw new IOException ( MessageLocalization.getComposedMessage ( "unexpected.gt.gt" ) );
			if ( -type == PRTokeniser.TokenType.END_ARRAY.ordinal () )
				throw new IOException ( MessageLocalization.getComposedMessage ( "unexpected.close.bracket" ) );
			dic.put ( name, obj );
		}
		return dic;
	}

	public PdfArray readArray () throws IOException {
		PdfArray array = new PdfArray ();
		while ( true ) {
			PdfObject obj = readPRObject ();
			int type = obj.type ();
			if ( -type == PRTokeniser.TokenType.END_ARRAY.ordinal () )
				break;
			if ( -type == PRTokeniser.TokenType.END_DIC.ordinal () )
				throw new IOException ( MessageLocalization.getComposedMessage ( "unexpected.gt.gt" ) );
			array.add ( obj );
		}
		return array;
	}

	public PdfObject readPRObject () throws IOException {
		if ( nextValidToken () )
			return null;
		PRTokeniser.TokenType type = tokeniser.getTokenType ();
		switch ( type ) {
		case START_DIC: {
			return readDictionary ();
		}
		case START_ARRAY:
			return readArray ();
		case STRING:
			return new PdfString ( tokeniser.getStringValue (), null ).setHexWriting ( tokeniser.isHexString () );
		case NAME:
			return new PdfName ( tokeniser.getStringValue (), false );
		case NUMBER:
			return new PdfNumber ( tokeniser.getStringValue () );
		case OTHER:
			return new PdfLiteral ( COMMAND_TYPE, tokeniser.getStringValue () );
		default:
			return new PdfLiteral ( -type.ordinal (), tokeniser.getStringValue () );
		}
	}

	public boolean nextValidToken () throws IOException {
		while ( tokeniser.nextToken () ) {
			if ( tokeniser.getTokenType () == PRTokeniser.TokenType.COMMENT )
				continue;
			return false;
		}
		return true;
	}
}

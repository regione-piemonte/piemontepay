/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal.PdfIsoKeys;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;


public class PdfAction extends PdfDictionary {

	public static final int FIRSTPAGE = 1;

	public static final int PREVPAGE = 2;

	public static final int NEXTPAGE = 3;

	public static final int LASTPAGE = 4;

	public static final int PRINTDIALOG = 5;

	public static final int SUBMIT_EXCLUDE = 1;

	public static final int SUBMIT_INCLUDE_NO_VALUE_FIELDS = 2;

	public static final int SUBMIT_HTML_FORMAT = 4;

	public static final int SUBMIT_HTML_GET = 8;

	public static final int SUBMIT_COORDINATES = 16;

	public static final int SUBMIT_XFDF = 32;

	public static final int SUBMIT_INCLUDE_APPEND_SAVES = 64;

	public static final int SUBMIT_INCLUDE_ANNOTATIONS = 128;

	public static final int SUBMIT_PDF = 256;

	public static final int SUBMIT_CANONICAL_FORMAT = 512;

	public static final int SUBMIT_EXCL_NON_USER_ANNOTS = 1024;

	public static final int SUBMIT_EXCL_F_KEY = 2048;

	public static final int SUBMIT_EMBED_FORM = 8196;

	public static final int RESET_EXCLUDE = 1;

	public PdfAction () {
	}

	public PdfAction ( URL url ) {
		this ( url.toExternalForm () );
	}

	public PdfAction ( URL url, boolean isMap ) {
		this ( url.toExternalForm (), isMap );
	}

	public PdfAction ( String url ) {
		this ( url, false );
	}

	public PdfAction ( String url, boolean isMap ) {
		put ( PdfName.S, PdfName.URI );
		put ( PdfName.URI, new PdfString ( url ) );
		if ( isMap )
			put ( PdfName.ISMAP, PdfBoolean.PDFTRUE );
	}

	PdfAction ( PdfIndirectReference destination ) {
		put ( PdfName.S, PdfName.GOTO );
		put ( PdfName.D, destination );
	}

	public PdfAction ( String filename, String name ) {
		put ( PdfName.S, PdfName.GOTOR );
		put ( PdfName.F, new PdfString ( filename ) );
		put ( PdfName.D, new PdfString ( name ) );
	}

	public PdfAction ( String filename, int page ) {
		put ( PdfName.S, PdfName.GOTOR );
		put ( PdfName.F, new PdfString ( filename ) );
		put ( PdfName.D, new PdfLiteral ( "[" + ( page - 1 ) + " /FitH 10000]" ) );
	}

	public PdfAction ( int named ) {
		put ( PdfName.S, PdfName.NAMED );
		switch ( named ) {
		case FIRSTPAGE:
			put ( PdfName.N, PdfName.FIRSTPAGE );
			break;
		case LASTPAGE:
			put ( PdfName.N, PdfName.LASTPAGE );
			break;
		case NEXTPAGE:
			put ( PdfName.N, PdfName.NEXTPAGE );
			break;
		case PREVPAGE:
			put ( PdfName.N, PdfName.PREVPAGE );
			break;
		case PRINTDIALOG:
			put ( PdfName.S, PdfName.JAVASCRIPT );
			put ( PdfName.JS, new PdfString ( "this.print(true);\r" ) );
			break;
		default:
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "invalid.named.action" ) );
		}
	}

	public PdfAction ( String application, String parameters, String operation, String defaultDir ) {
		put ( PdfName.S, PdfName.LAUNCH );
		if ( parameters == null && operation == null && defaultDir == null )
			put ( PdfName.F, new PdfString ( application ) );
		else {
			PdfDictionary dic = new PdfDictionary ();
			dic.put ( PdfName.F, new PdfString ( application ) );
			if ( parameters != null )
				dic.put ( PdfName.P, new PdfString ( parameters ) );
			if ( operation != null )
				dic.put ( PdfName.O, new PdfString ( operation ) );
			if ( defaultDir != null )
				dic.put ( PdfName.D, new PdfString ( defaultDir ) );
			put ( PdfName.WIN, dic );
		}
	}

	public static PdfAction rendition ( String file, PdfFileSpecification fs, String mimeType, PdfIndirectReference ref ) throws IOException {
		PdfAction js = new PdfAction ();
		js.put ( PdfName.S, PdfName.RENDITION );
		js.put ( PdfName.R, new PdfRendition ( file, fs, mimeType ) );
		js.put ( new PdfName ( "OP" ), new PdfNumber ( 0 ) );
		js.put ( new PdfName ( "AN" ), ref );
		return js;
	}

	public static PdfAction javaScript ( String code, PdfWriter writer, boolean unicode ) {
		PdfAction js = new PdfAction ();
		js.put ( PdfName.S, PdfName.JAVASCRIPT );
		if ( unicode && code.length () < 50 ) {
			js.put ( PdfName.JS, new PdfString ( code, PdfObject.TEXT_UNICODE ) );
		} else if ( !unicode && code.length () < 100 ) {
			js.put ( PdfName.JS, new PdfString ( code ) );
		} else {
			try {
				byte[] b = PdfEncodings.convertToBytes ( code, unicode ? PdfObject.TEXT_UNICODE : PdfObject.TEXT_PDFDOCENCODING );
				PdfStream stream = new PdfStream ( b );
				stream.flateCompress ( writer.getCompressionLevel () );
				js.put ( PdfName.JS, writer.addToBody ( stream ).getIndirectReference () );
			} catch ( Exception e ) {
				js.put ( PdfName.JS, new PdfString ( code ) );
			}
		}
		return js;
	}

	public static PdfAction javaScript ( String code, PdfWriter writer ) {
		return javaScript ( code, writer, false );
	}

	public void next ( PdfAction na ) {
		PdfObject nextAction = get ( PdfName.NEXT );
		if ( nextAction == null )
			put ( PdfName.NEXT, na );
		else if ( nextAction.isDictionary () ) {
			PdfArray array = new PdfArray ( nextAction );
			array.add ( na );
			put ( PdfName.NEXT, array );
		} else {
			( (PdfArray) nextAction ).add ( na );
		}
	}

	@Override
	public void toPdf ( final PdfWriter writer, final OutputStream os ) throws IOException {
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_ACTION, this );
		super.toPdf ( writer, os );
	}

}

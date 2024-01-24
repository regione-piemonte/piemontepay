/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Chunk;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Font;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Phrase;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Utilities;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;

import java.util.ArrayList;


public class FontSelector {

	protected ArrayList<Font> fonts = new ArrayList<> ();

	protected Font currentFont = null;

	public void addFont ( Font font ) {
		if ( font.getBaseFont () != null ) {
			fonts.add ( font );
			return;
		}
		BaseFont bf = font.getCalculatedBaseFont ( true );
		Font f2 = new Font ( bf, font.getSize (), font.getCalculatedStyle (), font.getColor () );
		fonts.add ( f2 );
	}

	public Phrase process ( String text ) {
		if ( fonts.isEmpty () )
			throw new IndexOutOfBoundsException ( MessageLocalization.getComposedMessage ( "no.font.is.defined" ) );
		char[] cc = text.toCharArray ();
		int len = cc.length;
		StringBuffer sb = new StringBuffer ();
		Phrase ret = new Phrase ();
		currentFont = null;
		for ( int k = 0; k < len; ++k ) {
			Chunk newChunk = processChar ( cc, k, sb );
			if ( newChunk != null ) {
				ret.add ( newChunk );
			}
		}
		if ( sb.length () > 0 ) {
			Chunk ck = new Chunk ( sb.toString (), currentFont != null ? currentFont : fonts.get ( 0 ) );
			ret.add ( ck );
		}
		return ret;
	}

	protected Chunk processChar ( char[] cc, int k, StringBuffer sb ) {
		Chunk newChunk = null;
		char c = cc[k];
		if ( c == '\n' || c == '\r' ) {
			sb.append ( c );
		} else {
			Font font;
			if ( Utilities.isSurrogatePair ( cc, k ) ) {
				int u = Utilities.convertToUtf32 ( cc, k );
				for ( Font value : fonts ) {
					font = value;
					if ( font.getBaseFont ().charExists ( u ) ) {
						if ( currentFont != font ) {
							if ( sb.length () > 0 && currentFont != null ) {
								newChunk = new Chunk ( sb.toString (), currentFont );
								sb.setLength ( 0 );
							}
							currentFont = font;
						}
						sb.append ( c );
						sb.append ( cc[++k] );
						break;
					}
				}
			} else {
				for ( Font value : fonts ) {
					font = value;
					if ( font.getBaseFont ().charExists ( c ) ) {
						if ( currentFont != font ) {
							if ( sb.length () > 0 && currentFont != null ) {
								newChunk = new Chunk ( sb.toString (), currentFont );
								sb.setLength ( 0 );
							}
							currentFont = font;
						}
						sb.append ( c );
						break;
					}
				}
			}
		}
		return newChunk;
	}
}

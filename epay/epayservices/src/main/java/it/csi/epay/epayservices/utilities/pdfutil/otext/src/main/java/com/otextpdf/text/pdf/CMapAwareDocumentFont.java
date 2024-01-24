/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Utilities;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps.CMapByteCid;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps.CMapCache;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps.CMapCidUni;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps.CMapParserEx;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps.CMapSequence;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps.CMapToUnicode;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps.CidLocationFromByte;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps.IdentityToUnicode;

import java.io.IOException;
import java.util.Map;


public class CMapAwareDocumentFont extends DocumentFont {

	private final PdfDictionary fontDic;

	private int spaceWidth;

	private CMapToUnicode toUnicodeCmap;

	private CMapByteCid byteCid;

	private CMapCidUni cidUni;

	private char[] cidbyte2uni;

	public CMapAwareDocumentFont ( PdfDictionary font ) {
		super ( font );
		fontDic = font;
		initFont ();
	}

	public CMapAwareDocumentFont ( PRIndirectReference refFont ) {
		super ( refFont );
		fontDic = (PdfDictionary) PdfReader.getPdfObjectRelease ( refFont );
		initFont ();
	}

	private void initFont () {
		processToUnicode ();
		try {
			//if (toUnicodeCmap == null)
			processUni2Byte ();

			spaceWidth = super.getWidth ( ' ' );
			if ( spaceWidth == 0 ) {
				spaceWidth = computeAverageWidth ();
			}
			if ( cjkEncoding != null ) {
				byteCid = CMapCache.getCachedCMapByteCid ( cjkEncoding );
				cidUni = CMapCache.getCachedCMapCidUni ( uniMap );
			}
		} catch ( Exception ex ) {
			throw new ExceptionConverter ( ex );
		}
	}

	private void processToUnicode () {
		PdfObject toUni = PdfReader.getPdfObjectRelease ( fontDic.get ( PdfName.TOUNICODE ) );
		if ( toUni instanceof PRStream ) {
			try {
				byte[] touni = PdfReader.getStreamBytes ( (PRStream) toUni );
				CidLocationFromByte lb = new CidLocationFromByte ( touni );
				toUnicodeCmap = new CMapToUnicode ();
				CMapParserEx.parseCid ( "", toUnicodeCmap, lb );
				toUnicodeCmap.createReverseMapping ();
			} catch ( IOException e ) {
				toUnicodeCmap = null;
				// technically, we should log this or provide some sort of feedback... but sometimes the cmap will be junk, but it's still possible to get text, so we don't want to throw an exception
				//throw new IllegalStateException("Unable to process ToUnicode map - " + e.getMessage(), e);
			}
		} else if ( isType0 ) {
			// fake a ToUnicode for CJK Identity-H fonts
			try {
				PdfName encodingName = fontDic.getAsName ( PdfName.ENCODING );
				if ( encodingName == null )
					return;
				String enc = PdfName.decodeName ( encodingName.toString () );
				if ( !enc.equals ( "Identity-H" ) )
					return;
				PdfArray df = (PdfArray) PdfReader.getPdfObjectRelease ( fontDic.get ( PdfName.DESCENDANTFONTS ) );
				PdfDictionary cidft = (PdfDictionary) PdfReader.getPdfObjectRelease ( df.getPdfObject ( 0 ) );
				PdfDictionary cidinfo = cidft.getAsDict ( PdfName.CIDSYSTEMINFO );
				if ( cidinfo == null )
					return;
				PdfString ordering = cidinfo.getAsString ( PdfName.ORDERING );
				if ( ordering == null )
					return;
				CMapToUnicode touni = IdentityToUnicode.GetMapFromOrdering ( ordering.toUnicodeString () );
				if ( touni == null )
					return;
				toUnicodeCmap = touni;
				toUnicodeCmap.createReverseMapping ();
			} catch ( IOException e ) {
				toUnicodeCmap = null;
			}
		}
	}

	private void processUni2Byte () {

		IntHashtable byte2uni = getByte2Uni ();
		int[] e = byte2uni.toOrderedKeys ();
		if ( e.length == 0 )
			return;

		cidbyte2uni = new char[256];
		for ( int key : e ) {
			cidbyte2uni[key] = (char) byte2uni.get ( key );
		}
		if ( toUnicodeCmap != null ) {
			Map<Integer, Integer> dm = toUnicodeCmap.createDirectMapping ();
			for ( Map.Entry<Integer, Integer> kv : dm.entrySet () ) {
				if ( kv.getKey () < 256 ) {
					cidbyte2uni[kv.getKey ()] = (char) kv.getValue ().intValue ();
				}
			}
		}
		IntHashtable diffmap = getDiffmap ();
		if ( diffmap != null ) {
			// the difference array overrides the existing encoding
			e = diffmap.toOrderedKeys ();
			for ( int i : e ) {
				int n = diffmap.get ( i );
				if ( n < 256 )
					cidbyte2uni[n] = (char) i;
			}
		}
	}

	private int computeAverageWidth () {
		int count = 0;
		int total = 0;
		for ( int width : super.widths ) {
			if ( width != 0 ) {
				total += width;
				count++;
			}
		}
		return count != 0 ? total / count : 0;
	}

	@Override
	public int getWidth ( int char1 ) {
		if ( char1 == ' ' )
			return spaceWidth != 0 ? spaceWidth : defaultWidth;
		return super.getWidth ( char1 );
	}

	private String decodeSingleCID ( byte[] bytes, int offset, int len ) {
		if ( toUnicodeCmap != null ) {
			if ( offset + len > bytes.length )
				throw new ArrayIndexOutOfBoundsException ( MessageLocalization.getComposedMessage ( "invalid.index.1", offset + len ) );
			String s = toUnicodeCmap.lookup ( bytes, offset, len );
			if ( s != null )
				return s;
			if ( len != 1 || cidbyte2uni == null )
				return null;
		}

		if ( len == 1 ) {
			if ( cidbyte2uni == null )
				return "";
			else
				return new String ( cidbyte2uni, 0xff & bytes[offset], 1 );
		}

		throw new Error ( "Multi-byte glyphs not implemented yet" );
	}

	public String decode ( byte[] cidbytes, final int offset, final int len ) {
		StringBuilder sb = new StringBuilder ();
		if ( toUnicodeCmap == null && byteCid != null ) {
			CMapSequence seq = new CMapSequence ( cidbytes, offset, len );
			String cid = byteCid.decodeSequence ( seq );
			for ( int k = 0; k < cid.length (); ++k ) {
				int c = cidUni.lookup ( cid.charAt ( k ) );
				if ( c > 0 )
					sb.append ( Utilities.convertFromUtf32 ( c ) );
			}
		} else {
			for ( int i = offset; i < offset + len; i++ ) {
				String rslt = decodeSingleCID ( cidbytes, i, 1 );
				if ( rslt == null && i < offset + len - 1 ) {
					rslt = decodeSingleCID ( cidbytes, i, 2 );
					i++;
				}
				if ( rslt != null )
					sb.append ( rslt );
			}
		}
		return sb.toString ();
	}

	public String encode ( byte[] bytes, int offset, int len ) {
		return decode ( bytes, offset, len );
	}
}

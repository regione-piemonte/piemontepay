/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Utilities;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.otf.Language;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class TrueTypeFontUnicode extends TrueTypeFont implements Comparator<int[]> {

	private static final List<Language> SUPPORTED_LANGUAGES_FOR_OTF = Collections.singletonList ( Language.BENGALI );

	private static final byte[] rotbits = { (byte) 0x80, (byte) 0x40, (byte) 0x20, (byte) 0x10, (byte) 0x08, (byte) 0x04, (byte) 0x02, (byte) 0x01 };

	private Map<String, Glyph> glyphSubstitutionMap;

	private Language supportedLanguage;

	TrueTypeFontUnicode ( String ttFile, String enc, boolean emb, byte[] ttfAfm, boolean forceRead ) throws DocumentException, IOException {
		String nameBase = getBaseName ( ttFile );
		String ttcName = getTTCName ( nameBase );
		if ( nameBase.length () < ttFile.length () ) {
			style = ttFile.substring ( nameBase.length () );
		}
		encoding = enc;
		embedded = emb;
		fileName = ttcName;
		ttcIndex = "";
		if ( ttcName.length () < nameBase.length () )
			ttcIndex = nameBase.substring ( ttcName.length () + 1 );
		fontType = FONT_TYPE_TTUNI;
		if ( ( fileName.toLowerCase ().endsWith ( ".ttf" ) || fileName.toLowerCase ().endsWith ( ".otf" ) || fileName.toLowerCase ()
						.endsWith ( ".ttc" ) ) && ( enc.equals ( IDENTITY_H ) || enc.equals ( IDENTITY_V ) ) && emb ) {
			process ( ttfAfm, forceRead );
			if ( os_2.fsType == 2 )
				throw new DocumentException (
								MessageLocalization.getComposedMessage ( "1.cannot.be.embedded.due.to.licensing.restrictions", fileName + style ) );
			if ( cmap31 == null && !fontSpecific || cmap10 == null && fontSpecific )
				directTextToByte = true;
			if ( fontSpecific ) {
				fontSpecific = false;
				String tempEncoding = encoding;
				encoding = "";
				createEncoding ();
				encoding = tempEncoding;
				fontSpecific = true;
			}
		} else
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "1.2.is.not.a.ttf.font.file", fileName, style ) );
		vertical = enc.endsWith ( "V" );
	}

	private static String toHex4 ( int n ) {
		String s = "0000" + Integer.toHexString ( n );
		return s.substring ( s.length () - 4 );
	}

	static String toHex ( int n ) {
		if ( n < 0x10000 )
			return "<" + toHex4 ( n ) + ">";
		n -= 0x10000;
		int high = n / 0x400 + 0xd800;
		int low = n % 0x400 + 0xdc00;
		return "[<" + toHex4 ( high ) + toHex4 ( low ) + ">]";
	}

	@Override
	void process ( byte[] ttfAfm, boolean preload ) throws DocumentException, IOException {
		super.process ( ttfAfm, preload );
	}

	@Override
	public int getWidth ( int char1 ) {
		if ( vertical )
			return 1000;
		if ( fontSpecific ) {
			if ( ( char1 & 0xff00 ) == 0 || ( char1 & 0xff00 ) == 0xf000 )
				return getRawWidth ( char1 & 0xff, null );
			else
				return 0;
		} else {
			return getRawWidth ( char1, encoding );
		}
	}

	@Override
	public int getWidth ( String text ) {
		if ( vertical )
			return text.length () * 1000;
		int total = 0;
		if ( fontSpecific ) {
			char[] cc = text.toCharArray ();
			for ( char c : cc ) {
				if ( ( c & 0xff00 ) == 0 || ( c & 0xff00 ) == 0xf000 )
					total += getRawWidth ( c & 0xff, null );
			}
		} else {
			int len = text.length ();
			for ( int k = 0; k < len; ++k ) {
				if ( Utilities.isSurrogatePair ( text, k ) ) {
					total += getRawWidth ( Utilities.convertToUtf32 ( text, k ), encoding );
					++k;
				} else
					total += getRawWidth ( text.charAt ( k ), encoding );
			}
		}
		return total;
	}

	public PdfStream getToUnicode ( Object[] metrics ) {
		if ( metrics.length == 0 )
			return null;
		StringBuilder buf = new StringBuilder (
						"/CIDInit /ProcSet findresource begin\n" +
										"12 dict begin\n" +
										"begincmap\n" +
										"/CIDSystemInfo\n" +
										"<< /Registry (TTX+0)\n" +
										"/Ordering (T42UV)\n" +
										"/Supplement 0\n" +
										">> def\n" +
										"/CMapName /TTX+0 def\n" +
										"/CMapType 2 def\n" +
										"1 begincodespacerange\n" +
										"<0000><FFFF>\n" +
										"endcodespacerange\n" );
		int size = 0;
		for ( int k = 0; k < metrics.length; ++k ) {
			if ( size == 0 ) {
				if ( k != 0 ) {
					buf.append ( "endbfrange\n" );
				}
				size = Math.min ( 100, metrics.length - k );
				buf.append ( size ).append ( " beginbfrange\n" );
			}
			--size;
			int[] metric = (int[]) metrics[k];
			String fromTo = toHex ( metric[0] );
			buf.append ( fromTo ).append ( fromTo ).append ( toHex ( metric[2] ) ).append ( '\n' );
		}
		buf.append (
						"endbfrange\n" +
										"endcmap\n" +
										"CMapName currentdict /CMap defineresource pop\n" +
										"end end\n" );
		String s = buf.toString ();
		PdfStream stream = new PdfStream ( PdfEncodings.convertToBytes ( s, null ) );
		stream.flateCompress ( compressionLevel );
		return stream;
	}

	public PdfDictionary getCIDFontType2 ( PdfIndirectReference fontDescriptor, String subsetPrefix, Object[] metrics ) {
		PdfDictionary dic = new PdfDictionary ( PdfName.FONT );
		if ( cff ) {
			dic.put ( PdfName.SUBTYPE, PdfName.CIDFONTTYPE0 );
			dic.put ( PdfName.BASEFONT, new PdfName ( subsetPrefix + fontName + "-" + encoding ) );
		} else {
			dic.put ( PdfName.SUBTYPE, PdfName.CIDFONTTYPE2 );
			dic.put ( PdfName.BASEFONT, new PdfName ( subsetPrefix + fontName ) );
		}
		dic.put ( PdfName.FONTDESCRIPTOR, fontDescriptor );
		if ( !cff )
			dic.put ( PdfName.CIDTOGIDMAP, PdfName.IDENTITY );
		PdfDictionary cdic = new PdfDictionary ();
		cdic.put ( PdfName.REGISTRY, new PdfString ( "Adobe" ) );
		cdic.put ( PdfName.ORDERING, new PdfString ( "Identity" ) );
		cdic.put ( PdfName.SUPPLEMENT, new PdfNumber ( 0 ) );
		dic.put ( PdfName.CIDSYSTEMINFO, cdic );
		if ( !vertical ) {
			dic.put ( PdfName.DW, new PdfNumber ( 1000 ) );
			StringBuilder buf = new StringBuilder ( "[" );
			int lastNumber = -10;
			boolean firstTime = true;
			for ( Object o : metrics ) {
				int[] metric = (int[]) o;
				if ( metric[1] == 1000 )
					continue;
				int m = metric[0];
				if ( m == lastNumber + 1 ) {
					buf.append ( ' ' ).append ( metric[1] );
				} else {
					if ( !firstTime ) {
						buf.append ( ']' );
					}
					firstTime = false;
					buf.append ( m ).append ( '[' ).append ( metric[1] );
				}
				lastNumber = m;
			}
			if ( buf.length () > 1 ) {
				buf.append ( "]]" );
				dic.put ( PdfName.W, new PdfLiteral ( buf.toString () ) );
			}
		}
		return dic;
	}

	public PdfDictionary getFontBaseType ( PdfIndirectReference descendant, String subsetPrefix, PdfIndirectReference toUnicode ) {
		PdfDictionary dic = new PdfDictionary ( PdfName.FONT );

		dic.put ( PdfName.SUBTYPE, PdfName.TYPE0 );
		if ( cff )
			dic.put ( PdfName.BASEFONT, new PdfName ( subsetPrefix + fontName + "-" + encoding ) );
		else
			dic.put ( PdfName.BASEFONT, new PdfName ( subsetPrefix + fontName ) );
		dic.put ( PdfName.ENCODING, new PdfName ( encoding ) );
		dic.put ( PdfName.DESCENDANTFONTS, new PdfArray ( descendant ) );
		if ( toUnicode != null )
			dic.put ( PdfName.TOUNICODE, toUnicode );
		return dic;
	}

	public int compare ( int[] o1, int[] o2 ) {
		int m1 = o1[0];
		int m2 = o2[0];
		return Integer.compare ( m1, m2 );
	}

	@Override
	void writeFont ( PdfWriter writer, PdfIndirectReference ref, Object[] params ) throws DocumentException, IOException {
		writer.getTtfUnicodeWriter ().writeFont ( this, ref, params );
	}

	@Override
	public PdfStream getFullFontStream () throws IOException, DocumentException {
		if ( cff ) {
			return new StreamFont ( readCffFont (), "CIDFontType0C", compressionLevel );
		}
		return super.getFullFontStream ();
	}

	@Override
	public byte[] convertToBytes ( String text ) {
		return null;
	}

	@Override
	byte[] convertToBytes ( int char1 ) {
		return null;
	}

	@Override
	public int[] getMetricsTT ( int c ) {
		if ( cmapExt != null )
			return cmapExt.get ( c );
		HashMap<Integer, int[]> map;
		if ( fontSpecific )
			map = cmap10;
		else
			map = cmap31;
		if ( map == null )
			return null;
		if ( fontSpecific ) {
			if ( ( c & 0xffffff00 ) == 0 || ( c & 0xffffff00 ) == 0xf000 )
				return map.get ( c & 0xff );
			else
				return null;
		} else
			return map.get ( c );
	}

	@Override
	public boolean charExists ( int c ) {
		return getMetricsTT ( c ) != null;
	}

	protected Map<String, Glyph> getGlyphSubstitutionMap () {
		return glyphSubstitutionMap;
	}

	Language getSupportedLanguage () {
		return supportedLanguage;
	}

}

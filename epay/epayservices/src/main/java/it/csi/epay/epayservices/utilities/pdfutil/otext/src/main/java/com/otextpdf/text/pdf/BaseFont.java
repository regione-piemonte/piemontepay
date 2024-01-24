/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


public abstract class BaseFont {

	public static final String COURIER = "Courier";

	public static final String COURIER_BOLD = "Courier-Bold";

	public static final String COURIER_OBLIQUE = "Courier-Oblique";

	public static final String COURIER_BOLDOBLIQUE = "Courier-BoldOblique";

	public static final String HELVETICA = "Helvetica";

	public static final String HELVETICA_BOLD = "Helvetica-Bold";

	public static final String HELVETICA_OBLIQUE = "Helvetica-Oblique";

	public static final String HELVETICA_BOLDOBLIQUE = "Helvetica-BoldOblique";

	public static final String SYMBOL = "Symbol";

	public static final String TIMES_ROMAN = "Times-Roman";

	public static final String TIMES_BOLD = "Times-Bold";

	public static final String TIMES_ITALIC = "Times-Italic";

	public static final String TIMES_BOLDITALIC = "Times-BoldItalic";

	public static final String ZAPFDINGBATS = "ZapfDingbats";

	public static final int ASCENT = 1;

	public static final int CAPHEIGHT = 2;

	public static final int DESCENT = 3;

	public static final int ITALICANGLE = 4;

	public static final int BBOXLLX = 5;

	public static final int BBOXLLY = 6;

	public static final int BBOXURX = 7;

	public static final int BBOXURY = 8;

	public static final int AWT_ASCENT = 9;

	public static final int AWT_DESCENT = 10;

	public static final int AWT_LEADING = 11;

	public static final int AWT_MAXADVANCE = 12;

	public static final int UNDERLINE_POSITION = 13;

	public static final int UNDERLINE_THICKNESS = 14;

	public static final int STRIKETHROUGH_POSITION = 15;

	public static final int STRIKETHROUGH_THICKNESS = 16;

	public static final int SUBSCRIPT_SIZE = 17;

	public static final int SUBSCRIPT_OFFSET = 18;

	public static final int SUPERSCRIPT_SIZE = 19;

	public static final int SUPERSCRIPT_OFFSET = 20;

	public static final int WEIGHT_CLASS = 21;

	public static final int WIDTH_CLASS = 22;

	public static final int FONT_WEIGHT = 23;

	public static final int FONT_TYPE_T1 = 0;

	public static final int FONT_TYPE_TT = 1;

	public static final int FONT_TYPE_CJK = 2;

	public static final int FONT_TYPE_TTUNI = 3;

	public static final int FONT_TYPE_DOCUMENT = 4;

	public static final int FONT_TYPE_T3 = 5;

	public static final String IDENTITY_H = "Identity-H";

	public static final String IDENTITY_V = "Identity-V";

	public static final String CP1250 = "Cp1250";

	public static final String CP1252 = "Cp1252";

	public static final String CP1257 = "Cp1257";

	public static final String WINANSI = "Cp1252";

	public static final String MACROMAN = "MacRoman";

	public static final int[] CHAR_RANGE_LATIN = { 0, 0x17f, 0x2000, 0x206f, 0x20a0, 0x20cf, 0xfb00, 0xfb06 };

	public static final int[] CHAR_RANGE_ARABIC = { 0, 0x7f, 0x0600, 0x067f, 0x20a0, 0x20cf, 0xfb50, 0xfbff, 0xfe70, 0xfeff };

	public static final int[] CHAR_RANGE_HEBREW = { 0, 0x7f, 0x0590, 0x05ff, 0x20a0, 0x20cf, 0xfb1d, 0xfb4f };

	public static final int[] CHAR_RANGE_CYRILLIC = { 0, 0x7f, 0x0400, 0x052f, 0x2000, 0x206f, 0x20a0, 0x20cf };

	public static final boolean EMBEDDED = true;

	public static final boolean NOT_EMBEDDED = false;

	public static final boolean CACHED = true;

	public static final boolean NOT_CACHED = false;

	public static final String RESOURCE_PATH = "com/otextpdf/text/pdf/fonts/";

	public static final char CID_NEWLINE = '\u7fff';

	public static final char PARAGRAPH_SEPARATOR = '\u2029';

	public static final String notdef = ".notdef";

	protected static final HashMap<String, BaseFont> fontCache = new HashMap<> ();

	protected static final HashMap<String, PdfName> BuiltinFonts14 = new HashMap<> ();

	static {
		BuiltinFonts14.put ( COURIER, PdfName.COURIER );
		BuiltinFonts14.put ( COURIER_BOLD, PdfName.COURIER_BOLD );
		BuiltinFonts14.put ( COURIER_BOLDOBLIQUE, PdfName.COURIER_BOLDOBLIQUE );
		BuiltinFonts14.put ( COURIER_OBLIQUE, PdfName.COURIER_OBLIQUE );
		BuiltinFonts14.put ( HELVETICA, PdfName.HELVETICA );
		BuiltinFonts14.put ( HELVETICA_BOLD, PdfName.HELVETICA_BOLD );
		BuiltinFonts14.put ( HELVETICA_BOLDOBLIQUE, PdfName.HELVETICA_BOLDOBLIQUE );
		BuiltinFonts14.put ( HELVETICA_OBLIQUE, PdfName.HELVETICA_OBLIQUE );
		BuiltinFonts14.put ( SYMBOL, PdfName.SYMBOL );
		BuiltinFonts14.put ( TIMES_ROMAN, PdfName.TIMES_ROMAN );
		BuiltinFonts14.put ( TIMES_BOLD, PdfName.TIMES_BOLD );
		BuiltinFonts14.put ( TIMES_BOLDITALIC, PdfName.TIMES_BOLDITALIC );
		BuiltinFonts14.put ( TIMES_ITALIC, PdfName.TIMES_ITALIC );
		BuiltinFonts14.put ( ZAPFDINGBATS, PdfName.ZAPFDINGBATS );
	}

	protected ArrayList<int[]> subsetRanges;

	protected int[] widths = new int[256];

	protected String[] differences = new String[256];

	protected char[] unicodeDifferences = new char[256];

	protected int[][] charBBoxes = new int[256][];

	protected String encoding;

	protected boolean embedded;

	protected int compressionLevel = PdfStream.DEFAULT_COMPRESSION;

	protected boolean fontSpecific = true;

	protected boolean forceWidthsOutput = false;

	protected boolean directTextToByte = false;

	protected boolean subset = true;

	protected boolean fastWinansi = false;

	protected IntHashtable specialMap;

	protected boolean vertical = false;

	int fontType;

	protected BaseFont () {
	}

	public static BaseFont createFont ( String name, String encoding, boolean embedded ) throws DocumentException, IOException {
		return createFont ( name, encoding, embedded, true, null, null, false );
	}

	public static BaseFont createFont ( String name, String encoding, boolean embedded, boolean cached, byte[] ttfAfm, byte[] pfb )
					throws DocumentException, IOException {
		return createFont ( name, encoding, embedded, cached, ttfAfm, pfb, false );
	}

	public static BaseFont createFont ( String name, String encoding, boolean embedded, boolean cached, byte[] ttfAfm, byte[] pfb, boolean noThrow ) throws
					DocumentException, IOException {
		return createFont ( name, encoding, embedded, cached, ttfAfm, pfb, noThrow, false );
	}

	public static BaseFont createFont ( String name, String encoding, boolean embedded, boolean cached, byte[] ttfAfm, byte[] pfb, boolean noThrow,
					boolean forceRead ) throws DocumentException, IOException {
		String nameBase = getBaseName ( name );
		encoding = normalizeEncoding ( encoding );
		boolean isBuiltinFonts14 = BuiltinFonts14.containsKey ( name );
		boolean isCJKFont = !isBuiltinFonts14 && CJKFont.isCJKFont ( nameBase, encoding );
		if ( isBuiltinFonts14 || isCJKFont )
			embedded = false;
		else if ( encoding.equals ( IDENTITY_H ) || encoding.equals ( IDENTITY_V ) )
			embedded = true;
		BaseFont fontFound;
		BaseFont fontBuilt;
		String key = name + "\n" + encoding + "\n" + embedded;
		if ( cached ) {
			synchronized ( fontCache ) {
				fontFound = fontCache.get ( key );
			}
			if ( fontFound != null )
				return fontFound;
		}
		if ( isBuiltinFonts14 || name.toLowerCase ().endsWith ( ".afm" ) || name.toLowerCase ().endsWith ( ".pfm" ) ) {
			fontBuilt = new Type1Font ( name, encoding, embedded, ttfAfm, pfb, forceRead );
			fontBuilt.fastWinansi = encoding.equals ( CP1252 );
		} else if ( nameBase.toLowerCase ().endsWith ( ".ttf" ) || nameBase.toLowerCase ().endsWith ( ".otf" ) || nameBase.toLowerCase ()
						.indexOf ( ".ttc," ) > 0 ) {
			if ( encoding.equals ( IDENTITY_H ) || encoding.equals ( IDENTITY_V ) )
				fontBuilt = new TrueTypeFontUnicode ( name, encoding, embedded, ttfAfm, forceRead );
			else {
				fontBuilt = new TrueTypeFont ( name, encoding, embedded, ttfAfm, false, forceRead );
				fontBuilt.fastWinansi = encoding.equals ( CP1252 );
			}
		} else if ( isCJKFont )
			fontBuilt = new CJKFont ( name, encoding );
		else if ( noThrow )
			return null;
		else
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "font.1.with.2.is.not.recognized", name, encoding ) );
		if ( cached ) {
			synchronized ( fontCache ) {
				fontFound = fontCache.get ( key );
				if ( fontFound != null )
					return fontFound;
				fontCache.put ( key, fontBuilt );
			}
		}
		return fontBuilt;
	}

	protected static String getBaseName ( String name ) {
		if ( name.endsWith ( ",Bold" ) )
			return name.substring ( 0, name.length () - 5 );
		else if ( name.endsWith ( ",Italic" ) )
			return name.substring ( 0, name.length () - 7 );
		else if ( name.endsWith ( ",BoldItalic" ) )
			return name.substring ( 0, name.length () - 11 );
		else
			return name;
	}

	protected static String normalizeEncoding ( String enc ) {
		if ( enc.equals ( "winansi" ) || enc.isEmpty () )
			return CP1252;
		else if ( enc.equals ( "macroman" ) )
			return MACROMAN;
		else
			return enc;
	}

	public static String createSubsetPrefix () {
		StringBuilder s = new StringBuilder ();
		for ( int k = 0; k < 6; ++k )
			s.append ( (char) ( Math.random () * 26 + 'A' ) );
		return s + "+";
	}

	public static Object[] getAllFontNames ( String name, String encoding, byte[] ttfAfm ) throws DocumentException, IOException {
		String nameBase = getBaseName ( name );
		BaseFont fontBuilt;
		if ( nameBase.toLowerCase ().endsWith ( ".ttf" ) || nameBase.toLowerCase ().endsWith ( ".otf" ) || nameBase.toLowerCase ().indexOf ( ".ttc," ) > 0 )
			fontBuilt = new TrueTypeFont ( name, CP1252, false, ttfAfm, true, false );
		else
			fontBuilt = createFont ( name, encoding, false, false, ttfAfm, null );
		return new Object[] { fontBuilt.getPostscriptFontName (), fontBuilt.getFamilyFontName (), fontBuilt.getFullFontName () };
	}

	public static String[] enumerateTTCNames ( String ttcFile ) throws DocumentException, IOException {
		return new EnumerateTTC ( ttcFile ).getNames ();
	}

	public boolean isVertical () {
		return vertical;
	}

	protected void createEncoding () {
		if ( encoding.startsWith ( "#" ) ) {
			specialMap = new IntHashtable ();
			StringTokenizer tok = new StringTokenizer ( encoding.substring ( 1 ), " ,\t\n\r\f" );
			if ( tok.nextToken ().equals ( "full" ) ) {
				while ( tok.hasMoreTokens () ) {
					String order = tok.nextToken ();
					String name = tok.nextToken ();
					char uni = (char) Integer.parseInt ( tok.nextToken (), 16 );
					int orderK;
					if ( order.startsWith ( "'" ) )
						orderK = order.charAt ( 1 );
					else
						orderK = Integer.parseInt ( order );
					orderK %= 256;
					specialMap.put ( uni, orderK );
					differences[orderK] = name;
					unicodeDifferences[orderK] = uni;
					widths[orderK] = getRawWidth ( uni, name );
					charBBoxes[orderK] = getRawCharBBox ( uni, name );
				}
			} else {
				int k = 0;
				if ( tok.hasMoreTokens () )
					k = Integer.parseInt ( tok.nextToken () );
				while ( tok.hasMoreTokens () && k < 256 ) {
					String hex = tok.nextToken ();
					int uni = Integer.parseInt ( hex, 16 ) % 0x10000;
					String name = GlyphList.unicodeToName ( uni );
					if ( name != null ) {
						specialMap.put ( uni, k );
						differences[k] = name;
						unicodeDifferences[k] = (char) uni;
						widths[k] = getRawWidth ( uni, name );
						charBBoxes[k] = getRawCharBBox ( uni, name );
						++k;
					}
				}
			}
			for ( int k = 0; k < 256; ++k ) {
				if ( differences[k] == null ) {
					differences[k] = notdef;
				}
			}
		} else if ( fontSpecific ) {
			for ( int k = 0; k < 256; ++k ) {
				widths[k] = getRawWidth ( k, null );
				charBBoxes[k] = getRawCharBBox ( k, null );
			}
		} else {
			String s;
			String name;
			char c;
			byte[] b = new byte[1];
			for ( int k = 0; k < 256; ++k ) {
				b[0] = (byte) k;
				s = PdfEncodings.convertToString ( b, encoding );
				if ( !s.isEmpty () ) {
					c = s.charAt ( 0 );
				} else {
					c = '?';
				}
				name = GlyphList.unicodeToName ( c );
				if ( name == null )
					name = notdef;
				differences[k] = name;
				unicodeDifferences[k] = c;
				widths[k] = getRawWidth ( c, name );
				charBBoxes[k] = getRawCharBBox ( c, name );
			}
		}
	}

	abstract int getRawWidth ( int c, String name );

	public int getWidth ( int char1 ) {
		if ( fastWinansi ) {
			if ( char1 < 128 || char1 >= 160 && char1 <= 255 )
				return widths[char1];
			else
				return widths[PdfEncodings.winansi.get ( char1 )];
		} else {
			int total = 0;
			byte[] mbytes = convertToBytes ( (char) char1 );
			for ( byte mbyte : mbytes )
				total += widths[0xff & mbyte];
			return total;
		}
	}

	public int getWidth ( String text ) {
		int total = 0;
		if ( fastWinansi ) {
			int len = text.length ();
			for ( int k = 0; k < len; ++k ) {
				char char1 = text.charAt ( k );
				if ( char1 < 128 || char1 >= 160 && char1 <= 255 )
					total += widths[char1];
				else
					total += widths[PdfEncodings.winansi.get ( char1 )];
			}
			return total;
		} else {
			byte[] mbytes = convertToBytes ( text );
			for ( byte mbyte : mbytes )
				total += widths[0xff & mbyte];
		}
		return total;
	}

	public float getWidthPoint ( String text, float fontSize ) {
		return getWidth ( text ) * 0.001f * fontSize;
	}

	public float getWidthPoint ( int char1, float fontSize ) {
		return getWidth ( char1 ) * 0.001f * fontSize;
	}

	public byte[] convertToBytes ( String text ) {
		if ( directTextToByte )
			return PdfEncodings.convertToBytes ( text, null );
		if ( specialMap != null ) {
			byte[] b = new byte[text.length ()];
			int ptr = 0;
			int length = text.length ();
			for ( int k = 0; k < length; ++k ) {
				char c = text.charAt ( k );
				if ( specialMap.containsKey ( c ) )
					b[ptr++] = (byte) specialMap.get ( c );
			}
			if ( ptr < length ) {
				byte[] b2 = new byte[ptr];
				System.arraycopy ( b, 0, b2, 0, ptr );
				return b2;
			} else
				return b;
		}
		return PdfEncodings.convertToBytes ( text, encoding );
	}

	byte[] convertToBytes ( int char1 ) {
		if ( directTextToByte )
			return PdfEncodings.convertToBytes ( (char) char1, null );
		if ( specialMap != null ) {
			if ( specialMap.containsKey ( char1 ) )
				return new byte[] { (byte) specialMap.get ( char1 ) };
			else
				return new byte[0];
		}
		return PdfEncodings.convertToBytes ( (char) char1, encoding );
	}

	abstract void writeFont ( PdfWriter writer, PdfIndirectReference ref, Object[] params ) throws DocumentException, IOException;

	abstract PdfStream getFullFontStream () throws IOException, DocumentException;

	public String getEncoding () {
		return encoding;
	}

	public abstract float getFontDescriptor ( int key, float fontSize );

	public int getFontType () {
		return fontType;
	}

	public boolean isEmbedded () {
		return embedded;
	}

	public boolean isFontSpecific () {
		return fontSpecific;
	}

	char getUnicodeDifferences ( int index ) {
		return unicodeDifferences[index];
	}

	public abstract String getPostscriptFontName ();

	public abstract String[][] getFullFontName ();

	public abstract String[][] getFamilyFontName ();

	public int[] getWidths () {
		return widths;
	}

	public boolean isSubset () {
		return subset;
	}

	public void setSubset ( boolean subset ) {
		this.subset = subset;
	}

	public int getUnicodeEquivalent ( int c ) {
		return c;
	}

	public int getCidCode ( int c ) {
		return c;
	}

	public boolean charExists ( int c ) {
		byte[] b = convertToBytes ( c );
		return b.length > 0;
	}

	protected abstract int[] getRawCharBBox ( int c, String name );

	public int getCompressionLevel () {
		return compressionLevel;
	}

	public void setCompressionLevel ( int compressionLevel ) {
		if ( compressionLevel < PdfStream.NO_COMPRESSION || compressionLevel > PdfStream.BEST_COMPRESSION )
			this.compressionLevel = PdfStream.DEFAULT_COMPRESSION;
		else
			this.compressionLevel = compressionLevel;
	}

	static class StreamFont extends PdfStream {

		public StreamFont ( byte[] contents, int[] lengths, int compressionLevel ) throws DocumentException {
			try {
				bytes = contents;
				put ( PdfName.LENGTH, new PdfNumber ( bytes.length ) );
				for ( int k = 0; k < lengths.length; ++k ) {
					put ( new PdfName ( "Length" + ( k + 1 ) ), new PdfNumber ( lengths[k] ) );
				}
				flateCompress ( compressionLevel );
			} catch ( Exception e ) {
				throw new DocumentException ( e );
			}
		}

		public StreamFont ( byte[] contents, String subType, int compressionLevel ) throws DocumentException {
			try {
				bytes = contents;
				put ( PdfName.LENGTH, new PdfNumber ( bytes.length ) );
				if ( subType != null )
					put ( PdfName.SUBTYPE, new PdfName ( subType ) );
				flateCompress ( compressionLevel );
			} catch ( Exception e ) {
				throw new DocumentException ( e );
			}
		}
	}
}

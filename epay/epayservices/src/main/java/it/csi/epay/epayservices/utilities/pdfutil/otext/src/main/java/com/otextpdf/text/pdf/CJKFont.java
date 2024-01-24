/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Utilities;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io.StreamUtil;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps.CMapCache;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps.CMapCidByte;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps.CMapCidUni;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps.CMapUniCid;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;


class CJKFont extends BaseFont {

	public static final String RESOURCE_PATH_CMAP = RESOURCE_PATH + "cmaps/";

	static final String CJK_ENCODING = "UnicodeBigUnmarked";

	private static final int FIRST = 0;

	private static final int BRACKET = 1;

	private static final int SERIAL = 2;

	private static final int V1Y = 880;

	private static final HashMap<String, HashMap<String, Object>> allFonts = new HashMap<> ();

	private static final HashMap<String, Set<String>> registryNames = new HashMap<> ();

	static Properties cjkFonts = new Properties ();

	static Properties cjkEncodings = new Properties ();

	private static boolean propertiesLoaded = false;

	private final String fontName;

	private final String CMap;

	private CMapCidByte cidByte;

	private CMapUniCid uniCid;

	private CMapCidUni cidUni;

	private String uniMap;

	private String style = "";

	private boolean cidDirect = false;

	//private char[] translationMap;
	private IntHashtable vMetrics;

	private IntHashtable hMetrics;

	private HashMap<String, Object> fontDesc;

	CJKFont ( String fontName, String enc ) throws DocumentException {
		loadProperties ();
		fontType = FONT_TYPE_CJK;
		String nameBase = getBaseName ( fontName );
		if ( !isCJKFont ( nameBase, enc ) )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "font.1.with.2.encoding.is.not.a.cjk.font", fontName, enc ) );
		if ( nameBase.length () < fontName.length () ) {
			style = fontName.substring ( nameBase.length () );
			fontName = nameBase;
		}
		this.fontName = fontName;
		encoding = CJK_ENCODING;
		vertical = enc.endsWith ( "V" );
		CMap = enc;
		if ( enc.equals ( IDENTITY_H ) || enc.equals ( IDENTITY_V ) )
			cidDirect = true;
		loadCMaps ();
	}

	private static void loadProperties () {
		if ( propertiesLoaded )
			return;
		synchronized ( allFonts ) {
			if ( propertiesLoaded )
				return;
			try {
				loadRegistry ();
				for ( String font : registryNames.get ( "fonts" ) ) {
					allFonts.put ( font, readFontProperties ( font ) );
				}
			} catch ( Exception ignored ) {
			}
			propertiesLoaded = true;
		}
	}

	private static void loadRegistry () throws IOException {
		InputStream is = StreamUtil.getResourceStream ( RESOURCE_PATH_CMAP + "cjk_registry.properties" );
		Properties p = new Properties ();
		p.load ( is );
		is.close ();
		for ( Object key : p.keySet () ) {
			String value = p.getProperty ( (String) key );
			String[] sp = value.split ( " " );
			Set<String> hs = new HashSet<> ();
			for ( String s : sp ) {
				if ( !s.isEmpty () )
					hs.add ( s );
			}
			registryNames.put ( (String) key, hs );
		}

	}

	public static String GetCompatibleFont ( String enc ) {
		loadProperties ();
		String registry = null;
		for ( Entry<String, Set<String>> e : registryNames.entrySet () ) {
			if ( e.getValue ().contains ( enc ) ) {
				registry = e.getKey ();
				break;
			}
		}
		if ( registry == null )
			return null;
		for ( Entry<String, HashMap<String, Object>> e : allFonts.entrySet () ) {
			if ( registry.equals ( e.getValue ().get ( "Registry" ) ) )
				return e.getKey ();
		}
		return null;
	}

	public static boolean isCJKFont ( String fontName, String enc ) {
		loadProperties ();
		if ( !registryNames.containsKey ( "fonts" ) )
			return false;
		if ( !registryNames.get ( "fonts" ).contains ( fontName ) )
			return false;
		if ( enc.equals ( IDENTITY_H ) || enc.equals ( IDENTITY_V ) )
			return true;
		String registry = (String) allFonts.get ( fontName ).get ( "Registry" );
		Set<String> encodings = registryNames.get ( registry );
		return encodings != null && encodings.contains ( enc );
	}

	static IntHashtable createMetric ( String s ) {
		IntHashtable h = new IntHashtable ();
		StringTokenizer tk = new StringTokenizer ( s );
		while ( tk.hasMoreTokens () ) {
			int n1 = Integer.parseInt ( tk.nextToken () );
			h.put ( n1, Integer.parseInt ( tk.nextToken () ) );
		}
		return h;
	}

	static String convertToHCIDMetrics ( int[] keys, IntHashtable h ) {
		if ( keys.length == 0 )
			return null;
		int lastCid = 0;
		int lastValue = 0;
		int start;
		for ( start = 0; start < keys.length; ++start ) {
			lastCid = keys[start];
			lastValue = h.get ( lastCid );
			if ( lastValue != 0 ) {
				++start;
				break;
			}
		}
		if ( lastValue == 0 )
			return null;
		StringBuilder buf = new StringBuilder ();
		buf.append ( '[' );
		buf.append ( lastCid );
		int state = FIRST;
		for ( int k = start; k < keys.length; ++k ) {
			int cid = keys[k];
			int value = h.get ( cid );
			if ( value == 0 )
				continue;
			switch ( state ) {
			case FIRST: {
				if ( cid == lastCid + 1 && value == lastValue ) {
					state = SERIAL;
				} else if ( cid == lastCid + 1 ) {
					state = BRACKET;
					buf.append ( '[' ).append ( lastValue );
				} else {
					buf.append ( '[' ).append ( lastValue ).append ( ']' ).append ( cid );
				}
				break;
			}
			case BRACKET: {
				if ( cid == lastCid + 1 && value == lastValue ) {
					state = SERIAL;
					buf.append ( ']' ).append ( lastCid );
				} else if ( cid == lastCid + 1 ) {
					buf.append ( ' ' ).append ( lastValue );
				} else {
					state = FIRST;
					buf.append ( ' ' ).append ( lastValue ).append ( ']' ).append ( cid );
				}
				break;
			}
			case SERIAL: {
				if ( cid != lastCid + 1 || value != lastValue ) {
					buf.append ( ' ' ).append ( lastCid ).append ( ' ' ).append ( lastValue ).append ( ' ' ).append ( cid );
					state = FIRST;
				}
				break;
			}
			}
			lastValue = value;
			lastCid = cid;
		}
		switch ( state ) {
		case FIRST: {
			buf.append ( '[' ).append ( lastValue ).append ( "]]" );
			break;
		}
		case BRACKET: {
			buf.append ( ' ' ).append ( lastValue ).append ( "]]" );
			break;
		}
		case SERIAL: {
			buf.append ( ' ' ).append ( lastCid ).append ( ' ' ).append ( lastValue ).append ( ']' );
			break;
		}
		}
		return buf.toString ();
	}

	static String convertToVCIDMetrics ( int[] keys, IntHashtable v, IntHashtable h ) {
		if ( keys.length == 0 )
			return null;
		int lastCid = 0;
		int lastValue = 0;
		int lastHValue = 0;
		int start;
		for ( start = 0; start < keys.length; ++start ) {
			lastCid = keys[start];
			lastValue = v.get ( lastCid );
			if ( lastValue != 0 ) {
				++start;
				break;
			} else
				lastHValue = h.get ( lastCid );
		}
		if ( lastValue == 0 )
			return null;
		if ( lastHValue == 0 )
			lastHValue = 1000;
		StringBuilder buf = new StringBuilder ();
		buf.append ( '[' );
		buf.append ( lastCid );
		int state = FIRST;
		for ( int k = start; k < keys.length; ++k ) {
			int cid = keys[k];
			int value = v.get ( cid );
			if ( value == 0 )
				continue;
			int hValue = h.get ( lastCid );
			if ( hValue == 0 )
				hValue = 1000;
			switch ( state ) {
			case FIRST: {
				if ( cid == lastCid + 1 && value == lastValue && hValue == lastHValue ) {
					state = SERIAL;
				} else {
					buf.append ( ' ' ).append ( lastCid ).append ( ' ' ).append ( -lastValue ).append ( ' ' ).append ( lastHValue / 2 ).append ( ' ' )
									.append ( V1Y ).append ( ' ' ).append ( cid );
				}
				break;
			}
			case SERIAL: {
				if ( cid != lastCid + 1 || value != lastValue || hValue != lastHValue ) {
					buf.append ( ' ' ).append ( lastCid ).append ( ' ' ).append ( -lastValue ).append ( ' ' ).append ( lastHValue / 2 ).append ( ' ' )
									.append ( V1Y ).append ( ' ' ).append ( cid );
					state = FIRST;
				}
				break;
			}
			}
			lastValue = value;
			lastCid = cid;
			lastHValue = hValue;
		}
		buf.append ( ' ' ).append ( lastCid ).append ( ' ' ).append ( -lastValue ).append ( ' ' ).append ( lastHValue / 2 ).append ( ' ' ).append ( V1Y )
						.append ( " ]" );
		return buf.toString ();
	}

	private static HashMap<String, Object> readFontProperties ( String name ) throws IOException {
		name += ".properties";
		InputStream is = StreamUtil.getResourceStream ( RESOURCE_PATH_CMAP + name );
		Properties p = new Properties ();
		p.load ( is );
		is.close ();
		IntHashtable W = createMetric ( p.getProperty ( "W" ) );
		p.remove ( "W" );
		IntHashtable W2 = createMetric ( p.getProperty ( "W2" ) );
		p.remove ( "W2" );
		HashMap<String, Object> map = new HashMap<> ();
		for ( Enumeration<Object> e = p.keys (); e.hasMoreElements (); ) {
			Object obj = e.nextElement ();
			map.put ( (String) obj, p.getProperty ( (String) obj ) );
		}
		map.put ( "W", W );
		map.put ( "W2", W2 );
		return map;
	}

	String getUniMap () {
		return uniMap;
	}

	private void loadCMaps () throws DocumentException {
		try {
			fontDesc = allFonts.get ( fontName );
			hMetrics = (IntHashtable) fontDesc.get ( "W" );
			vMetrics = (IntHashtable) fontDesc.get ( "W2" );
			String registry = (String) fontDesc.get ( "Registry" );
			uniMap = "";
			for ( String name : registryNames.get ( registry + "_Uni" ) ) {
				uniMap = name;
				if ( name.endsWith ( "V" ) && vertical )
					break;
				if ( !name.endsWith ( "V" ) && !vertical )
					break;
			}
			if ( cidDirect ) {
				cidUni = CMapCache.getCachedCMapCidUni ( uniMap );
			} else {
				uniCid = CMapCache.getCachedCMapUniCid ( uniMap );
				cidByte = CMapCache.getCachedCMapCidByte ( CMap );
			}
		} catch ( Exception ex ) {
			throw new DocumentException ( ex );
		}
	}

	@Override
	public int getWidth ( int char1 ) {
		int c = char1;
		if ( !cidDirect )
			c = uniCid.lookup ( char1 );
		int v;
		if ( vertical )
			v = vMetrics.get ( c );
		else
			v = hMetrics.get ( c );
		if ( v > 0 )
			return v;
		else
			return 1000;
	}

	@Override
	public int getWidth ( String text ) {
		int total = 0;
		if ( cidDirect ) {
			for ( int k = 0; k < text.length (); ++k ) {
				total += getWidth ( text.charAt ( k ) );
			}
		} else {
			for ( int k = 0; k < text.length (); ++k ) {
				int val;
				if ( Utilities.isSurrogatePair ( text, k ) ) {
					val = Utilities.convertToUtf32 ( text, k );
					k++;
				} else {
					val = text.charAt ( k );
				}
				total += getWidth ( val );
			}
		}
		return total;
	}

	@Override
	int getRawWidth ( int c, String name ) {
		return 0;
	}

	private PdfDictionary getFontDescriptor () {
		PdfDictionary dic = new PdfDictionary ( PdfName.FONTDESCRIPTOR );
		dic.put ( PdfName.ASCENT, new PdfLiteral ( (String) fontDesc.get ( "Ascent" ) ) );
		dic.put ( PdfName.CAPHEIGHT, new PdfLiteral ( (String) fontDesc.get ( "CapHeight" ) ) );
		dic.put ( PdfName.DESCENT, new PdfLiteral ( (String) fontDesc.get ( "Descent" ) ) );
		dic.put ( PdfName.FLAGS, new PdfLiteral ( (String) fontDesc.get ( "Flags" ) ) );
		dic.put ( PdfName.FONTBBOX, new PdfLiteral ( (String) fontDesc.get ( "FontBBox" ) ) );
		dic.put ( PdfName.FONTNAME, new PdfName ( fontName + style ) );
		dic.put ( PdfName.ITALICANGLE, new PdfLiteral ( (String) fontDesc.get ( "ItalicAngle" ) ) );
		dic.put ( PdfName.STEMV, new PdfLiteral ( (String) fontDesc.get ( "StemV" ) ) );
		PdfDictionary pdic = new PdfDictionary ();
		pdic.put ( PdfName.PANOSE, new PdfString ( (String) fontDesc.get ( "Panose" ), null ) );
		dic.put ( PdfName.STYLE, pdic );
		return dic;
	}

	private PdfDictionary getCIDFont ( PdfIndirectReference fontDescriptor, IntHashtable cjkTag ) {
		PdfDictionary dic = new PdfDictionary ( PdfName.FONT );
		dic.put ( PdfName.SUBTYPE, PdfName.CIDFONTTYPE0 );
		dic.put ( PdfName.BASEFONT, new PdfName ( fontName + style ) );
		dic.put ( PdfName.FONTDESCRIPTOR, fontDescriptor );
		int[] keys = cjkTag.toOrderedKeys ();
		String w = convertToHCIDMetrics ( keys, hMetrics );
		if ( w != null )
			dic.put ( PdfName.W, new PdfLiteral ( w ) );
		if ( vertical ) {
			w = convertToVCIDMetrics ( keys, vMetrics, hMetrics );
			if ( w != null )
				dic.put ( PdfName.W2, new PdfLiteral ( w ) );
		} else
			dic.put ( PdfName.DW, new PdfNumber ( 1000 ) );
		PdfDictionary cdic = new PdfDictionary ();
		if ( cidDirect ) {
			cdic.put ( PdfName.REGISTRY, new PdfString ( cidUni.getRegistry (), null ) );
			cdic.put ( PdfName.ORDERING, new PdfString ( cidUni.getOrdering (), null ) );
			cdic.put ( PdfName.SUPPLEMENT, new PdfNumber ( cidUni.getSupplement () ) );
		} else {
			cdic.put ( PdfName.REGISTRY, new PdfString ( cidByte.getRegistry (), null ) );
			cdic.put ( PdfName.ORDERING, new PdfString ( cidByte.getOrdering (), null ) );
			cdic.put ( PdfName.SUPPLEMENT, new PdfNumber ( cidByte.getSupplement () ) );
		}
		dic.put ( PdfName.CIDSYSTEMINFO, cdic );
		return dic;
	}

	private PdfDictionary getFontBaseType ( PdfIndirectReference CIDFont ) {
		PdfDictionary dic = new PdfDictionary ( PdfName.FONT );
		dic.put ( PdfName.SUBTYPE, PdfName.TYPE0 );
		String name = fontName;
		if ( !style.isEmpty () )
			name += "-" + style.substring ( 1 );
		name += "-" + CMap;
		dic.put ( PdfName.BASEFONT, new PdfName ( name ) );
		dic.put ( PdfName.ENCODING, new PdfName ( CMap ) );
		dic.put ( PdfName.DESCENDANTFONTS, new PdfArray ( CIDFont ) );
		return dic;
	}

	@Override
	void writeFont ( PdfWriter writer, PdfIndirectReference ref, Object[] params ) throws IOException {
		IntHashtable cjkTag = (IntHashtable) params[0];
		PdfIndirectReference ind_font;
		PdfObject pobj;
		PdfIndirectObject obj;
		pobj = getFontDescriptor ();
		obj = writer.addToBody ( pobj );
		ind_font = obj.getIndirectReference ();
		pobj = getCIDFont ( ind_font, cjkTag );
		obj = writer.addToBody ( pobj );
		ind_font = obj.getIndirectReference ();
		pobj = getFontBaseType ( ind_font );
		writer.addToBody ( pobj, ref );
	}

	@Override
	public PdfStream getFullFontStream () {
		return null;
	}

	private float getDescNumber ( String name ) {
		return Integer.parseInt ( (String) fontDesc.get ( name ) );
	}

	private float getBBox ( int idx ) {
		String s = (String) fontDesc.get ( "FontBBox" );
		StringTokenizer tk = new StringTokenizer ( s, " []\r\n\t\f" );
		String ret = tk.nextToken ();
		for ( int k = 0; k < idx; ++k )
			ret = tk.nextToken ();
		return Integer.parseInt ( ret );
	}

	@Override
	public float getFontDescriptor ( int key, float fontSize ) {
		switch ( key ) {
		case AWT_ASCENT:
		case ASCENT:
			return getDescNumber ( "Ascent" ) * fontSize / 1000;
		case CAPHEIGHT:
			return getDescNumber ( "CapHeight" ) * fontSize / 1000;
		case AWT_DESCENT:
		case DESCENT:
			return getDescNumber ( "Descent" ) * fontSize / 1000;
		case ITALICANGLE:
			return getDescNumber ( "ItalicAngle" );
		case BBOXLLX:
			return fontSize * getBBox ( 0 ) / 1000;
		case BBOXLLY:
			return fontSize * getBBox ( 1 ) / 1000;
		case BBOXURX:
			return fontSize * getBBox ( 2 ) / 1000;
		case BBOXURY:
			return fontSize * getBBox ( 3 ) / 1000;
		case AWT_LEADING:
			return 0;
		case AWT_MAXADVANCE:
			return fontSize * ( getBBox ( 2 ) - getBBox ( 0 ) ) / 1000;
		}
		return 0;
	}

	@Override
	public String getPostscriptFontName () {
		return fontName;
	}

	@Override
	public String[][] getFullFontName () {
		return new String[][] { { "", "", "", fontName } };
	}

	@Override
	public String[][] getFamilyFontName () {
		return getFullFontName ();
	}

	@Override
	public int getUnicodeEquivalent ( int c ) {
		if ( cidDirect ) {
			if ( c == CID_NEWLINE )
				return '\n';
			return cidUni.lookup ( c );
		}
		return c;
	}

	@Override
	public int getCidCode ( int c ) {
		if ( cidDirect )
			return c;
		return uniCid.lookup ( c );
	}

	@Override
	public boolean charExists ( int c ) {
		if ( cidDirect )
			return true;
		return cidByte.lookup ( uniCid.lookup ( c ) ).length > 0;
	}

	@Override
	protected int[] getRawCharBBox ( int c, String name ) {
		return null;
	}

	@Override
	public byte[] convertToBytes ( String text ) {
		if ( cidDirect )
			return super.convertToBytes ( text );
		try {
			if ( text.length () == 1 )
				return convertToBytes ( text.charAt ( 0 ) );
			ByteArrayOutputStream bout = new ByteArrayOutputStream ();
			for ( int k = 0; k < text.length (); ++k ) {
				int val;
				if ( Utilities.isSurrogatePair ( text, k ) ) {
					val = Utilities.convertToUtf32 ( text, k );
					k++;
				} else {
					val = text.charAt ( k );
				}
				bout.write ( convertToBytes ( val ) );
			}
			return bout.toByteArray ();
		} catch ( Exception ex ) {
			throw new ExceptionConverter ( ex );
		}
	}

	@Override
	byte[] convertToBytes ( int char1 ) {
		if ( cidDirect )
			return super.convertToBytes ( char1 );
		return cidByte.lookup ( uniCid.lookup ( char1 ) );
	}

	public boolean isIdentity () {
		return cidDirect;
	}
}

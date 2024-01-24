/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Document;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


class TrueTypeFont extends BaseFont {

	static final String[] codePages = {
					"1252 Latin 1",
					"1250 Latin 2: Eastern Europe",
					"1251 Cyrillic",
					"1253 Greek",
					"1254 Turkish",
					"1255 Hebrew",
					"1256 Arabic",
					"1257 Windows Baltic",
					"1258 Vietnamese",
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					"874 Thai",
					"932 JIS/Japan",
					"936 Chinese: Simplified chars--PRC and Singapore",
					"949 Korean Wansung",
					"950 Chinese: Traditional chars--Taiwan and Hong Kong",
					"1361 Korean Johab",
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					"Macintosh Character Set (US Roman)",
					"OEM Character Set",
					"Symbol Character Set",
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					"869 IBM Greek",
					"866 MS-DOS Russian",
					"865 MS-DOS Nordic",
					"864 Arabic",
					"863 MS-DOS Canadian French",
					"862 Hebrew",
					"861 MS-DOS Icelandic",
					"860 MS-DOS Portuguese",
					"857 IBM Turkish",
					"855 IBM Cyrillic; primarily Russian",
					"852 Latin 2",
					"775 MS-DOS Baltic",
					"737 Greek; former 437 G",
					"708 Arabic; ASMO 708",
					"850 WE/Latin 1",
					"437 US" };

	protected boolean justNames = false;

	protected HashMap<String, int[]> tables;

	protected RandomAccessFileOrArray rf;

	protected String fileName;

	protected boolean cff = false;

	protected int cffOffset;

	protected int cffLength;

	protected int directoryOffset;

	protected String ttcIndex;

	protected String style = "";

	protected FontHeader head = new FontHeader ();

	protected HorizontalHeader hhea = new HorizontalHeader ();

	protected WindowsMetrics os_2 = new WindowsMetrics ();

	protected int[] glyphWidthsByIndex;

	protected int[][] bboxes;

	protected HashMap<Integer, int[]> cmap10;

	protected HashMap<Integer, int[]> cmap31;

	protected HashMap<Integer, int[]> cmapExt;

	protected IntHashtable kerning = new IntHashtable ();

	protected String fontName;

	protected String[][] fullName;

	protected String[][] allNameEntries;

	protected String[][] familyName;

	protected double italicAngle;

	protected boolean isFixedPitch = false;

	protected int underlinePosition;

	protected int underlineThickness;

	protected TrueTypeFont () {
	}

	TrueTypeFont ( String ttFile, String enc, boolean emb, byte[] ttfAfm, boolean justNames, boolean forceRead ) throws DocumentException, IOException {
		this.justNames = justNames;
		String nameBase = getBaseName ( ttFile );
		String ttcName = getTTCName ( nameBase );
		if ( nameBase.length () < ttFile.length () ) {
			style = ttFile.substring ( nameBase.length () );
		}
		encoding = enc;
		embedded = emb;
		fileName = ttcName;
		fontType = FONT_TYPE_TT;
		ttcIndex = "";
		if ( ttcName.length () < nameBase.length () )
			ttcIndex = nameBase.substring ( ttcName.length () + 1 );
		if ( fileName.toLowerCase ().endsWith ( ".ttf" ) || fileName.toLowerCase ().endsWith ( ".otf" ) || fileName.toLowerCase ().endsWith ( ".ttc" ) ) {
			process ( ttfAfm, forceRead );
			if ( !justNames && embedded && os_2.fsType == 2 )
				throw new DocumentException (
								MessageLocalization.getComposedMessage ( "1.cannot.be.embedded.due.to.licensing.restrictions", fileName + style ) );
		} else
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "1.is.not.a.ttf.otf.or.ttc.font.file", fileName + style ) );
		if ( !encoding.startsWith ( "#" ) )
			PdfEncodings.convertToBytes ( " ", enc );
		createEncoding ();
	}

	protected static String getTTCName ( String name ) {
		int idx = name.toLowerCase ().indexOf ( ".ttc," );
		if ( idx < 0 )
			return name;
		else
			return name.substring ( 0, idx + 4 );
	}

	protected static int[] compactRanges ( ArrayList<int[]> ranges ) {
		ArrayList<int[]> simp = new ArrayList<> ();
		for ( int[] r : ranges ) {
			for ( int j = 0; j < r.length; j += 2 ) {
				simp.add ( new int[] { Math.max ( 0, Math.min ( r[j], r[j + 1] ) ), Math.min ( 0xffff, Math.max ( r[j], r[j + 1] ) ) } );
			}
		}
		for ( int k1 = 0; k1 < simp.size () - 1; ++k1 ) {
			for ( int k2 = k1 + 1; k2 < simp.size (); ++k2 ) {
				int[] r1 = simp.get ( k1 );
				int[] r2 = simp.get ( k2 );
				if ( r1[0] >= r2[0] && r1[0] <= r2[1] || r1[1] >= r2[0] && r1[0] <= r2[1] ) {
					r1[0] = Math.min ( r1[0], r2[0] );
					r1[1] = Math.max ( r1[1], r2[1] );
					simp.remove ( k2 );
					--k2;
				}
			}
		}
		int[] s = new int[simp.size () * 2];
		for ( int k = 0; k < simp.size (); ++k ) {
			int[] r = simp.get ( k );
			s[k * 2] = r[0];
			s[k * 2 + 1] = r[1];
		}
		return s;
	}

	void fillTables () throws DocumentException, IOException {
		int[] table_location;
		table_location = tables.get ( "head" );
		if ( table_location == null )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "table.1.does.not.exist.in.2", "head", fileName + style ) );
		rf.seek ( table_location[0] + 16 );
		head.flags = rf.readUnsignedShort ();
		head.unitsPerEm = rf.readUnsignedShort ();
		rf.skipBytes ( 16 );
		head.xMin = rf.readShort ();
		head.yMin = rf.readShort ();
		head.xMax = rf.readShort ();
		head.yMax = rf.readShort ();
		head.macStyle = rf.readUnsignedShort ();

		table_location = tables.get ( "hhea" );
		if ( table_location == null )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "table.1.does.not.exist.in.2", "hhea", fileName + style ) );
		rf.seek ( table_location[0] + 4 );
		hhea.Ascender = rf.readShort ();
		hhea.Descender = rf.readShort ();
		hhea.LineGap = rf.readShort ();
		hhea.advanceWidthMax = rf.readUnsignedShort ();
		hhea.minLeftSideBearing = rf.readShort ();
		hhea.minRightSideBearing = rf.readShort ();
		hhea.xMaxExtent = rf.readShort ();
		hhea.caretSlopeRise = rf.readShort ();
		hhea.caretSlopeRun = rf.readShort ();
		rf.skipBytes ( 12 );
		hhea.numberOfHMetrics = rf.readUnsignedShort ();

		table_location = tables.get ( "OS/2" );
		if ( table_location == null )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "table.1.does.not.exist.in.2", "OS/2", fileName + style ) );
		rf.seek ( table_location[0] );
		int version = rf.readUnsignedShort ();
		os_2.xAvgCharWidth = rf.readShort ();
		os_2.usWeightClass = rf.readUnsignedShort ();
		os_2.usWidthClass = rf.readUnsignedShort ();
		os_2.fsType = rf.readShort ();
		os_2.ySubscriptXSize = rf.readShort ();
		os_2.ySubscriptYSize = rf.readShort ();
		os_2.ySubscriptXOffset = rf.readShort ();
		os_2.ySubscriptYOffset = rf.readShort ();
		os_2.ySuperscriptXSize = rf.readShort ();
		os_2.ySuperscriptYSize = rf.readShort ();
		os_2.ySuperscriptXOffset = rf.readShort ();
		os_2.ySuperscriptYOffset = rf.readShort ();
		os_2.yStrikeoutSize = rf.readShort ();
		os_2.yStrikeoutPosition = rf.readShort ();
		os_2.sFamilyClass = rf.readShort ();
		rf.readFully ( os_2.panose );
		rf.skipBytes ( 16 );
		rf.readFully ( os_2.achVendID );
		os_2.fsSelection = rf.readUnsignedShort ();
		os_2.usFirstCharIndex = rf.readUnsignedShort ();
		os_2.usLastCharIndex = rf.readUnsignedShort ();
		os_2.sTypoAscender = rf.readShort ();
		os_2.sTypoDescender = rf.readShort ();
		if ( os_2.sTypoDescender > 0 )
			os_2.sTypoDescender = (short) -os_2.sTypoDescender;
		os_2.sTypoLineGap = rf.readShort ();
		os_2.usWinAscent = rf.readUnsignedShort ();
		os_2.usWinDescent = rf.readUnsignedShort ();
		os_2.ulCodePageRange1 = 0;
		os_2.ulCodePageRange2 = 0;
		if ( version > 0 ) {
			os_2.ulCodePageRange1 = rf.readInt ();
			os_2.ulCodePageRange2 = rf.readInt ();
		}
		if ( version > 1 ) {
			rf.skipBytes ( 2 );
			os_2.sCapHeight = rf.readShort ();
		} else
			os_2.sCapHeight = (int) ( 0.7 * head.unitsPerEm );

		table_location = tables.get ( "post" );
		if ( table_location == null ) {
			italicAngle = -Math.atan2 ( hhea.caretSlopeRun, hhea.caretSlopeRise ) * 180 / Math.PI;
			return;
		}
		rf.seek ( table_location[0] + 4 );
		short mantissa = rf.readShort ();
		int fraction = rf.readUnsignedShort ();
		italicAngle = mantissa + fraction / 16384.0d;
		underlinePosition = rf.readShort ();
		underlineThickness = rf.readShort ();
		isFixedPitch = rf.readInt () != 0;
	}

	String getBaseFont () throws DocumentException, IOException {
		int[] table_location;
		table_location = tables.get ( "name" );
		if ( table_location == null )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "table.1.does.not.exist.in.2", "name", fileName + style ) );
		rf.seek ( table_location[0] + 2 );
		int numRecords = rf.readUnsignedShort ();
		int startOfStorage = rf.readUnsignedShort ();
		for ( int k = 0; k < numRecords; ++k ) {
			int platformID = rf.readUnsignedShort ();
			rf.readUnsignedShort ();
			rf.readUnsignedShort ();
			int nameID = rf.readUnsignedShort ();
			int length = rf.readUnsignedShort ();
			int offset = rf.readUnsignedShort ();
			if ( nameID == 6 ) {
				rf.seek ( table_location[0] + startOfStorage + offset );
				if ( platformID == 0 || platformID == 3 )
					return readUnicodeString ( length );
				else
					return readStandardString ( length );
			}
		}
		File file = new File ( fileName );
		return file.getName ().replace ( ' ', '-' );
	}

	String[][] getNames ( int id ) throws DocumentException, IOException {
		int[] table_location;
		table_location = tables.get ( "name" );
		if ( table_location == null )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "table.1.does.not.exist.in.2", "name", fileName + style ) );
		rf.seek ( table_location[0] + 2 );
		int numRecords = rf.readUnsignedShort ();
		int startOfStorage = rf.readUnsignedShort ();
		ArrayList<String[]> names = new ArrayList<> ();
		for ( int k = 0; k < numRecords; ++k ) {
			int platformID = rf.readUnsignedShort ();
			int platformEncodingID = rf.readUnsignedShort ();
			int languageID = rf.readUnsignedShort ();
			int nameID = rf.readUnsignedShort ();
			int length = rf.readUnsignedShort ();
			int offset = rf.readUnsignedShort ();
			if ( nameID == id ) {
				int pos = (int) rf.getFilePointer ();
				rf.seek ( table_location[0] + startOfStorage + offset );
				String name;
				if ( platformID == 0 || platformID == 3 || platformID == 2 && platformEncodingID == 1 ) {
					name = readUnicodeString ( length );
				} else {
					name = readStandardString ( length );
				}
				names.add ( new String[] { String.valueOf ( platformID ),
								String.valueOf ( platformEncodingID ), String.valueOf ( languageID ), name } );
				rf.seek ( pos );
			}
		}
		String[][] thisName = new String[names.size ()][];
		for ( int k = 0; k < names.size (); ++k )
			thisName[k] = names.get ( k );
		return thisName;
	}

	void checkCff () {
		int[] table_location;
		table_location = tables.get ( "CFF " );
		if ( table_location != null ) {
			cff = true;
			cffOffset = table_location[0];
			cffLength = table_location[1];
		}
	}

	void process ( byte[] ttfAfm, boolean preload ) throws DocumentException, IOException {
		tables = new HashMap<> ();

		if ( ttfAfm == null )
			rf = new RandomAccessFileOrArray ( fileName, preload, Document.plainRandomAccess );
		else
			rf = new RandomAccessFileOrArray ( ttfAfm );

		try {
			if ( !ttcIndex.isEmpty () ) {
				int dirIdx = Integer.parseInt ( ttcIndex );
				if ( dirIdx < 0 )
					throw new DocumentException ( MessageLocalization.getComposedMessage ( "the.font.index.for.1.must.be.positive", fileName ) );
				String mainTag = readStandardString ( 4 );
				if ( !mainTag.equals ( "ttcf" ) )
					throw new DocumentException ( MessageLocalization.getComposedMessage ( "1.is.not.a.valid.ttc.file", fileName ) );
				rf.skipBytes ( 4 );
				int dirCount = rf.readInt ();
				if ( dirIdx >= dirCount )
					throw new DocumentException ( MessageLocalization.getComposedMessage ( "the.font.index.for.1.must.be.between.0.and.2.it.was.3", fileName,
									String.valueOf ( dirCount - 1 ), String.valueOf ( dirIdx ) ) );
				rf.skipBytes ( dirIdx * 4 );
				directoryOffset = rf.readInt ();
			}
			rf.seek ( directoryOffset );
			int ttId = rf.readInt ();
			if ( ttId != 0x00010000 && ttId != 0x4F54544F )
				throw new DocumentException ( MessageLocalization.getComposedMessage ( "1.is.not.a.valid.ttf.or.otf.file", fileName ) );
			int num_tables = rf.readUnsignedShort ();
			rf.skipBytes ( 6 );
			for ( int k = 0; k < num_tables; ++k ) {
				String tag = readStandardString ( 4 );
				rf.skipBytes ( 4 );
				int[] table_location = new int[2];
				table_location[0] = rf.readInt ();
				table_location[1] = rf.readInt ();
				tables.put ( tag, table_location );
			}
			checkCff ();
			fontName = getBaseFont ();
			fullName = getNames ( 4 );
			familyName = getNames ( 1 );
			if ( !justNames ) {
				fillTables ();
				readGlyphWidths ();
				readCMaps ();
				readKerning ();
				readBbox ();
			}
		} finally {
			if ( !embedded ) {
				rf.close ();
				rf = null;
			}
		}
	}

	protected String readStandardString ( int length ) throws IOException {
		return rf.readString ( length, WINANSI );
	}

	protected String readUnicodeString ( int length ) throws IOException {
		StringBuilder buf = new StringBuilder ();
		length /= 2;
		for ( int k = 0; k < length; ++k ) {
			buf.append ( rf.readChar () );
		}
		return buf.toString ();
	}

	protected void readGlyphWidths () throws DocumentException, IOException {
		int[] table_location;
		table_location = tables.get ( "hmtx" );
		if ( table_location == null )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "table.1.does.not.exist.in.2", "hmtx", fileName + style ) );
		rf.seek ( table_location[0] );
		glyphWidthsByIndex = new int[hhea.numberOfHMetrics];
		for ( int k = 0; k < hhea.numberOfHMetrics; ++k ) {
			glyphWidthsByIndex[k] = rf.readUnsignedShort () * 1000 / head.unitsPerEm;
			@SuppressWarnings ( "unused" )
			int leftSideBearing = rf.readShort () * 1000 / head.unitsPerEm;
		}
	}

	protected int getGlyphWidth ( int glyph ) {
		if ( glyph >= glyphWidthsByIndex.length )
			glyph = glyphWidthsByIndex.length - 1;
		return glyphWidthsByIndex[glyph];
	}

	private void readBbox () throws DocumentException, IOException {
		int[] tableLocation;
		tableLocation = tables.get ( "head" );
		if ( tableLocation == null )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "table.1.does.not.exist.in.2", "head", fileName + style ) );
		rf.seek ( tableLocation[0] + TrueTypeFontSubSet.HEAD_LOCA_FORMAT_OFFSET );
		boolean locaShortTable = rf.readUnsignedShort () == 0;
		tableLocation = tables.get ( "loca" );
		if ( tableLocation == null )
			return;
		rf.seek ( tableLocation[0] );
		int[] locaTable;
		if ( locaShortTable ) {
			int entries = tableLocation[1] / 2;
			locaTable = new int[entries];
			for ( int k = 0; k < entries; ++k )
				locaTable[k] = rf.readUnsignedShort () * 2;
		} else {
			int entries = tableLocation[1] / 4;
			locaTable = new int[entries];
			for ( int k = 0; k < entries; ++k )
				locaTable[k] = rf.readInt ();
		}
		tableLocation = tables.get ( "glyf" );
		if ( tableLocation == null )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "table.1.does.not.exist.in.2", "glyf", fileName + style ) );
		int tableGlyphOffset = tableLocation[0];
		bboxes = new int[locaTable.length - 1][];
		for ( int glyph = 0; glyph < locaTable.length - 1; ++glyph ) {
			int start = locaTable[glyph];
			if ( start != locaTable[glyph + 1] ) {
				rf.seek ( tableGlyphOffset + start + 2 );
				bboxes[glyph] = new int[] {
								rf.readShort () * 1000 / head.unitsPerEm,
								rf.readShort () * 1000 / head.unitsPerEm,
								rf.readShort () * 1000 / head.unitsPerEm,
								rf.readShort () * 1000 / head.unitsPerEm };
			}
		}
	}

	void readCMaps () throws DocumentException, IOException {
		int[] table_location;
		table_location = tables.get ( "cmap" );
		if ( table_location == null )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "table.1.does.not.exist.in.2", "cmap", fileName + style ) );
		rf.seek ( table_location[0] );
		rf.skipBytes ( 2 );
		int num_tables = rf.readUnsignedShort ();
		fontSpecific = false;
		int map10 = 0;
		int map31 = 0;
		int map30 = 0;
		int mapExt = 0;
		for ( int k = 0; k < num_tables; ++k ) {
			int platId = rf.readUnsignedShort ();
			int platSpecId = rf.readUnsignedShort ();
			int offset = rf.readInt ();
			if ( platId == 3 && platSpecId == 0 ) {
				fontSpecific = true;
				map30 = offset;
			} else if ( platId == 3 && platSpecId == 1 ) {
				map31 = offset;
			} else if ( platId == 3 && platSpecId == 10 ) {
				mapExt = offset;
			}
			if ( platId == 1 && platSpecId == 0 ) {
				map10 = offset;
			}
		}
		if ( map10 > 0 ) {
			rf.seek ( table_location[0] + map10 );
			int format = rf.readUnsignedShort ();
			switch ( format ) {
			case 0:
				cmap10 = readFormat0 ();
				break;
			case 4:
				cmap10 = readFormat4 ();
				break;
			case 6:
				cmap10 = readFormat6 ();
				break;
			}
		}
		if ( map31 > 0 ) {
			rf.seek ( table_location[0] + map31 );
			int format = rf.readUnsignedShort ();
			if ( format == 4 ) {
				cmap31 = readFormat4 ();
			}
		}
		if ( map30 > 0 ) {
			rf.seek ( table_location[0] + map30 );
			int format = rf.readUnsignedShort ();
			if ( format == 4 ) {
				cmap10 = readFormat4 ();
			}
		}
		if ( mapExt > 0 ) {
			rf.seek ( table_location[0] + mapExt );
			int format = rf.readUnsignedShort ();
			switch ( format ) {
			case 0:
				cmapExt = readFormat0 ();
				break;
			case 4:
				cmapExt = readFormat4 ();
				break;
			case 6:
				cmapExt = readFormat6 ();
				break;
			case 12:
				cmapExt = readFormat12 ();
				break;
			}
		}
	}

	HashMap<Integer, int[]> readFormat12 () throws IOException {
		HashMap<Integer, int[]> h = new HashMap<> ();
		rf.skipBytes ( 2 );
		rf.readInt ();
		rf.skipBytes ( 4 );
		int nGroups = rf.readInt ();
		for ( int k = 0; k < nGroups; k++ ) {
			int startCharCode = rf.readInt ();
			int endCharCode = rf.readInt ();
			int startGlyphID = rf.readInt ();
			for ( int i = startCharCode; i <= endCharCode; i++ ) {
				int[] r = new int[2];
				r[0] = startGlyphID;
				r[1] = getGlyphWidth ( r[0] );
				h.put ( i, r );
				startGlyphID++;
			}
		}
		return h;
	}

	HashMap<Integer, int[]> readFormat0 () throws IOException {
		HashMap<Integer, int[]> h = new HashMap<> ();
		rf.skipBytes ( 4 );
		for ( int k = 0; k < 256; ++k ) {
			int[] r = new int[2];
			r[0] = rf.readUnsignedByte ();
			r[1] = getGlyphWidth ( r[0] );
			h.put ( k, r );
		}
		return h;
	}

	HashMap<Integer, int[]> readFormat4 () throws IOException {
		HashMap<Integer, int[]> h = new HashMap<> ();
		int table_lenght = rf.readUnsignedShort ();
		rf.skipBytes ( 2 );
		int segCount = rf.readUnsignedShort () / 2;
		rf.skipBytes ( 6 );
		int[] endCount = new int[segCount];
		for ( int k = 0; k < segCount; ++k ) {
			endCount[k] = rf.readUnsignedShort ();
		}
		rf.skipBytes ( 2 );
		int[] startCount = new int[segCount];
		for ( int k = 0; k < segCount; ++k ) {
			startCount[k] = rf.readUnsignedShort ();
		}
		int[] idDelta = new int[segCount];
		for ( int k = 0; k < segCount; ++k ) {
			idDelta[k] = rf.readUnsignedShort ();
		}
		int[] idRO = new int[segCount];
		for ( int k = 0; k < segCount; ++k ) {
			idRO[k] = rf.readUnsignedShort ();
		}
		int[] glyphId = new int[table_lenght / 2 - 8 - segCount * 4];
		for ( int k = 0; k < glyphId.length; ++k ) {
			glyphId[k] = rf.readUnsignedShort ();
		}
		for ( int k = 0; k < segCount; ++k ) {
			int glyph;
			for ( int j = startCount[k]; j <= endCount[k] && j != 0xFFFF; ++j ) {
				if ( idRO[k] == 0 ) {
					glyph = j + idDelta[k] & 0xFFFF;
				} else {
					int idx = k + idRO[k] / 2 - segCount + j - startCount[k];
					if ( idx >= glyphId.length )
						continue;
					glyph = glyphId[idx] + idDelta[k] & 0xFFFF;
				}
				int[] r = new int[2];
				r[0] = glyph;
				r[1] = getGlyphWidth ( r[0] );
				h.put ( fontSpecific ? ( ( j & 0xff00 ) == 0xf000 ? j & 0xff : j ) : j, r );
			}
		}
		return h;
	}

	HashMap<Integer, int[]> readFormat6 () throws IOException {
		HashMap<Integer, int[]> h = new HashMap<> ();
		rf.skipBytes ( 4 );
		int start_code = rf.readUnsignedShort ();
		int code_count = rf.readUnsignedShort ();
		for ( int k = 0; k < code_count; ++k ) {
			int[] r = new int[2];
			r[0] = rf.readUnsignedShort ();
			r[1] = getGlyphWidth ( r[0] );
			h.put ( k + start_code, r );
		}
		return h;
	}

	void readKerning () throws IOException {
		int[] table_location;
		table_location = tables.get ( "kern" );
		if ( table_location == null )
			return;
		rf.seek ( table_location[0] + 2 );
		int nTables = rf.readUnsignedShort ();
		int checkpoint = table_location[0] + 4;
		int length = 0;
		for ( int k = 0; k < nTables; ++k ) {
			checkpoint += length;
			rf.seek ( checkpoint );
			rf.skipBytes ( 2 );
			length = rf.readUnsignedShort ();
			int coverage = rf.readUnsignedShort ();
			if ( ( coverage & 0xfff7 ) == 0x0001 ) {
				int nPairs = rf.readUnsignedShort ();
				rf.skipBytes ( 6 );
				for ( int j = 0; j < nPairs; ++j ) {
					int pair = rf.readInt ();
					int value = rf.readShort () * 1000 / head.unitsPerEm;
					kerning.put ( pair, value );
				}
			}
		}
	}

	@Override
	int getRawWidth ( int c, String name ) {
		int[] metric = getMetricsTT ( c );
		if ( metric == null )
			return 0;
		return metric[1];
	}

	protected PdfDictionary getFontDescriptor ( PdfIndirectReference fontStream, String subsetPrefix, PdfIndirectReference cidset ) {
		PdfDictionary dic = new PdfDictionary ( PdfName.FONTDESCRIPTOR );
		dic.put ( PdfName.ASCENT, new PdfNumber ( os_2.sTypoAscender * 1000 / head.unitsPerEm ) );
		dic.put ( PdfName.CAPHEIGHT, new PdfNumber ( os_2.sCapHeight * 1000 / head.unitsPerEm ) );
		dic.put ( PdfName.DESCENT, new PdfNumber ( os_2.sTypoDescender * 1000 / head.unitsPerEm ) );
		dic.put ( PdfName.FONTBBOX, new PdfRectangle (
						(float) ( head.xMin * 1000 ) / head.unitsPerEm,
						(float) ( head.yMin * 1000 ) / head.unitsPerEm,
						(float) ( head.xMax * 1000 ) / head.unitsPerEm,
						(float) ( head.yMax * 1000 ) / head.unitsPerEm ) );
		if ( cidset != null )
			dic.put ( PdfName.CIDSET, cidset );
		if ( cff ) {
			if ( encoding.startsWith ( "Identity-" ) )
				dic.put ( PdfName.FONTNAME, new PdfName ( subsetPrefix + fontName + "-" + encoding ) );
			else
				dic.put ( PdfName.FONTNAME, new PdfName ( subsetPrefix + fontName + style ) );
		} else
			dic.put ( PdfName.FONTNAME, new PdfName ( subsetPrefix + fontName + style ) );
		dic.put ( PdfName.ITALICANGLE, new PdfNumber ( italicAngle ) );
		dic.put ( PdfName.STEMV, new PdfNumber ( 80 ) );
		if ( fontStream != null ) {
			if ( cff )
				dic.put ( PdfName.FONTFILE3, fontStream );
			else
				dic.put ( PdfName.FONTFILE2, fontStream );
		}
		int flags = 0;
		if ( isFixedPitch )
			flags |= 1;
		flags |= fontSpecific ? 4 : 32;
		if ( ( head.macStyle & 2 ) != 0 )
			flags |= 64;
		if ( ( head.macStyle & 1 ) != 0 )
			flags |= 262144;
		dic.put ( PdfName.FLAGS, new PdfNumber ( flags ) );

		return dic;
	}

	protected PdfDictionary getFontBaseType ( PdfIndirectReference fontDescriptor, String subsetPrefix, int firstChar, int lastChar, byte[] shortTag ) {
		PdfDictionary dic = new PdfDictionary ( PdfName.FONT );
		if ( cff ) {
			dic.put ( PdfName.SUBTYPE, PdfName.TYPE1 );
			dic.put ( PdfName.BASEFONT, new PdfName ( fontName + style ) );
		} else {
			dic.put ( PdfName.SUBTYPE, PdfName.TRUETYPE );
			dic.put ( PdfName.BASEFONT, new PdfName ( subsetPrefix + fontName + style ) );
		}
		dic.put ( PdfName.BASEFONT, new PdfName ( subsetPrefix + fontName + style ) );
		if ( !fontSpecific ) {
			for ( int k = firstChar; k <= lastChar; ++k ) {
				if ( !differences[k].equals ( notdef ) ) {
					firstChar = k;
					break;
				}
			}
			if ( encoding.equals ( "Cp1252" ) || encoding.equals ( "MacRoman" ) )
				dic.put ( PdfName.ENCODING, encoding.equals ( "Cp1252" ) ? PdfName.WIN_ANSI_ENCODING : PdfName.MAC_ROMAN_ENCODING );
			else {
				PdfDictionary enc = new PdfDictionary ( PdfName.ENCODING );
				PdfArray dif = new PdfArray ();
				boolean gap = true;
				for ( int k = firstChar; k <= lastChar; ++k ) {
					if ( shortTag[k] != 0 ) {
						if ( gap ) {
							dif.add ( new PdfNumber ( k ) );
							gap = false;
						}
						dif.add ( new PdfName ( differences[k] ) );
					} else
						gap = true;
				}
				enc.put ( PdfName.DIFFERENCES, dif );
				dic.put ( PdfName.ENCODING, enc );
			}
		}
		dic.put ( PdfName.FIRSTCHAR, new PdfNumber ( firstChar ) );
		dic.put ( PdfName.LASTCHAR, new PdfNumber ( lastChar ) );
		PdfArray wd = new PdfArray ();
		for ( int k = firstChar; k <= lastChar; ++k ) {
			if ( shortTag[k] == 0 )
				wd.add ( new PdfNumber ( 0 ) );
			else
				wd.add ( new PdfNumber ( widths[k] ) );
		}
		dic.put ( PdfName.WIDTHS, wd );
		if ( fontDescriptor != null )
			dic.put ( PdfName.FONTDESCRIPTOR, fontDescriptor );
		return dic;
	}

	protected byte[] getFullFont () throws IOException {
		RandomAccessFileOrArray rf2 = null;
		try {
			rf2 = new RandomAccessFileOrArray ( rf );
			rf2.reOpen ();
			byte[] b = new byte[(int) rf2.length ()];
			rf2.readFully ( b );
			return b;
		} finally {
			try {
				if ( rf2 != null ) {
					rf2.close ();
				}
			} catch ( Exception ignored ) {
			}
		}
	}

	synchronized protected byte[] getSubSet ( HashSet glyphs, boolean subsetp ) throws IOException, DocumentException {
		TrueTypeFontSubSet sb = new TrueTypeFontSubSet ( fileName, new RandomAccessFileOrArray ( rf ), glyphs, directoryOffset, true, !subsetp );
		return sb.process ();
	}

	protected void addRangeUni ( HashMap<Integer, int[]> longTag, boolean subsetp ) {
		if ( !subsetp && ( subsetRanges != null || directoryOffset > 0 ) ) {
			int[] rg = subsetRanges == null ? new int[] { 0, 0xffff } : compactRanges ( subsetRanges );
			HashMap<Integer, int[]> usemap;
			if ( !fontSpecific && cmap31 != null )
				usemap = cmap31;
			else if ( fontSpecific && cmap10 != null )
				usemap = cmap10;
			else if ( cmap31 != null )
				usemap = cmap31;
			else
				usemap = cmap10;
			assert usemap != null;
			for ( Map.Entry<Integer, int[]> e : usemap.entrySet () ) {
				int[] v = e.getValue ();
				Integer gi = v[0];
				if ( longTag.containsKey ( gi ) )
					continue;
				int c = e.getKey ();
				boolean skip = true;
				for ( int k = 0; k < rg.length; k += 2 ) {
					if ( c >= rg[k] && c <= rg[k + 1] ) {
						skip = false;
						break;
					}
				}
				if ( !skip )
					longTag.put ( gi, new int[] { v[0], v[1], c } );
			}
		}
	}

	protected void addRangeUni ( HashSet<Integer> longTag, boolean subsetp ) {
		if ( !subsetp && ( subsetRanges != null || directoryOffset > 0 ) ) {
			int[] rg = subsetRanges == null ? new int[] { 0, 0xffff } : compactRanges ( subsetRanges );
			HashMap<Integer, int[]> usemap;
			if ( !fontSpecific && cmap31 != null )
				usemap = cmap31;
			else if ( fontSpecific && cmap10 != null )
				usemap = cmap10;
			else if ( cmap31 != null )
				usemap = cmap31;
			else
				usemap = cmap10;
			assert usemap != null;
			for ( Map.Entry<Integer, int[]> e : usemap.entrySet () ) {
				int[] v = e.getValue ();
				Integer gi = v[0];
				if ( longTag.contains ( gi ) )
					continue;
				int c = e.getKey ();
				boolean skip = true;
				for ( int k = 0; k < rg.length; k += 2 ) {
					if ( c >= rg[k] && c <= rg[k + 1] ) {
						skip = false;
						break;
					}
				}
				if ( !skip )
					longTag.add ( gi );
			}
		}
	}

	@Override
	void writeFont ( PdfWriter writer, PdfIndirectReference ref, Object[] params ) throws DocumentException, IOException {
		int firstChar = (Integer) params[0];
		int lastChar = (Integer) params[1];
		byte[] shortTag = (byte[]) params[2];
		boolean subsetp = (Boolean) params[3] && subset;

		if ( !subsetp ) {
			firstChar = 0;
			lastChar = shortTag.length - 1;
			Arrays.fill ( shortTag, (byte) 1 );
		}
		PdfIndirectReference ind_font = null;
		PdfObject pobj;
		PdfIndirectObject obj;
		String subsetPrefix = "";
		if ( embedded ) {
			if ( cff ) {
				pobj = new StreamFont ( readCffFont (), "Type1C", compressionLevel );
			} else {
				if ( subsetp )
					subsetPrefix = createSubsetPrefix ();
				HashSet<Integer> glyphs = new HashSet<> ();
				for ( int k = firstChar; k <= lastChar; ++k ) {
					if ( shortTag[k] != 0 ) {
						int[] metrics = null;
						if ( specialMap != null ) {
							int[] cd = GlyphList.nameToUnicode ( differences[k] );
							if ( cd != null )
								metrics = getMetricsTT ( cd[0] );
						} else {
							if ( fontSpecific )
								metrics = getMetricsTT ( k );
							else
								metrics = getMetricsTT ( unicodeDifferences[k] );
						}
						if ( metrics != null )
							glyphs.add ( metrics[0] );
					}
				}
				addRangeUni ( glyphs, subsetp );
				byte[] b;
				if ( subsetp || directoryOffset != 0 || subsetRanges != null ) {
					b = getSubSet ( new HashSet ( glyphs ), subsetp );
				} else {
					b = getFullFont ();
				}
				int[] lengths = new int[] { b.length };
				pobj = new StreamFont ( b, lengths, compressionLevel );
			}
			obj = writer.addToBody ( pobj );
			ind_font = obj.getIndirectReference ();
		}
		pobj = getFontDescriptor ( ind_font, subsetPrefix, null );
		if ( pobj != null ) {
			obj = writer.addToBody ( pobj );
			ind_font = obj.getIndirectReference ();
		}
		pobj = getFontBaseType ( ind_font, subsetPrefix, firstChar, lastChar, shortTag );
		writer.addToBody ( pobj, ref );
	}

	protected byte[] readCffFont () throws IOException {
		RandomAccessFileOrArray rf2 = new RandomAccessFileOrArray ( rf );
		byte[] b = new byte[cffLength];
		try {
			rf2.reOpen ();
			rf2.seek ( cffOffset );
			rf2.readFully ( b );
		} finally {
			try {
				rf2.close ();
			} catch ( Exception ignored ) {
			}
		}
		return b;
	}

	@Override
	public PdfStream getFullFontStream () throws IOException, DocumentException {
		if ( cff ) {
			return new StreamFont ( readCffFont (), "Type1C", compressionLevel );
		} else {
			byte[] b = getFullFont ();
			int[] lengths = new int[] { b.length };
			return new StreamFont ( b, lengths, compressionLevel );
		}
	}

	@Override
	public float getFontDescriptor ( int key, float fontSize ) {
		switch ( key ) {
		case ASCENT:
			return os_2.sTypoAscender * fontSize / head.unitsPerEm;
		case CAPHEIGHT:
			return os_2.sCapHeight * fontSize / head.unitsPerEm;
		case DESCENT:
			return os_2.sTypoDescender * fontSize / head.unitsPerEm;
		case ITALICANGLE:
			return (float) italicAngle;
		case BBOXLLX:
			return fontSize * head.xMin / head.unitsPerEm;
		case BBOXLLY:
			return fontSize * head.yMin / head.unitsPerEm;
		case BBOXURX:
			return fontSize * head.xMax / head.unitsPerEm;
		case BBOXURY:
			return fontSize * head.yMax / head.unitsPerEm;
		case AWT_ASCENT:
			return fontSize * hhea.Ascender / head.unitsPerEm;
		case AWT_DESCENT:
			return fontSize * hhea.Descender / head.unitsPerEm;
		case AWT_LEADING:
			return fontSize * hhea.LineGap / head.unitsPerEm;
		case AWT_MAXADVANCE:
			return fontSize * hhea.advanceWidthMax / head.unitsPerEm;
		case UNDERLINE_POSITION:
			return ( underlinePosition - (float) underlineThickness / 2 ) * fontSize / head.unitsPerEm;
		case UNDERLINE_THICKNESS:
			return underlineThickness * fontSize / head.unitsPerEm;
		case STRIKETHROUGH_POSITION:
			return os_2.yStrikeoutPosition * fontSize / head.unitsPerEm;
		case STRIKETHROUGH_THICKNESS:
			return os_2.yStrikeoutSize * fontSize / head.unitsPerEm;
		case SUBSCRIPT_SIZE:
			return os_2.ySubscriptYSize * fontSize / head.unitsPerEm;
		case SUBSCRIPT_OFFSET:
			return -os_2.ySubscriptYOffset * fontSize / head.unitsPerEm;
		case SUPERSCRIPT_SIZE:
			return os_2.ySuperscriptYSize * fontSize / head.unitsPerEm;
		case SUPERSCRIPT_OFFSET:
			return os_2.ySuperscriptYOffset * fontSize / head.unitsPerEm;
		case WEIGHT_CLASS:
			return os_2.usWeightClass;
		case WIDTH_CLASS:
			return os_2.usWidthClass;
		}
		return 0;
	}

	public int[] getMetricsTT ( int c ) {
		if ( cmapExt != null )
			return cmapExt.get ( c );
		if ( !fontSpecific && cmap31 != null )
			return cmap31.get ( c );
		if ( fontSpecific && cmap10 != null )
			return cmap10.get ( c );
		if ( cmap31 != null )
			return cmap31.get ( c );
		if ( cmap10 != null )
			return cmap10.get ( c );
		return null;
	}

	@Override
	public String getPostscriptFontName () {
		return fontName;
	}

	@Override
	public String[][] getFullFontName () {
		return fullName;
	}

	@Override
	public String[][] getFamilyFontName () {
		return familyName;
	}

	@Override
	protected int[] getRawCharBBox ( int c, String name ) {
		HashMap<Integer, int[]> map;
		if ( name == null || cmap31 == null )
			map = cmap10;
		else
			map = cmap31;
		if ( map == null )
			return null;
		int[] metric = map.get ( c );
		if ( metric == null || bboxes == null )
			return null;
		return bboxes[metric[0]];
	}

	protected static class FontHeader {

		int flags;

		int unitsPerEm;

		short xMin;

		short yMin;

		short xMax;

		short yMax;

		int macStyle;
	}


	protected static class HorizontalHeader {

		short Ascender;

		short Descender;

		short LineGap;

		int advanceWidthMax;

		short minLeftSideBearing;

		short minRightSideBearing;

		short xMaxExtent;

		short caretSlopeRise;

		short caretSlopeRun;

		int numberOfHMetrics;
	}


	protected static class WindowsMetrics {

		short xAvgCharWidth;

		int usWeightClass;

		int usWidthClass;

		short fsType;

		short ySubscriptXSize;

		short ySubscriptYSize;

		short ySubscriptXOffset;

		short ySubscriptYOffset;

		short ySuperscriptXSize;

		short ySuperscriptYSize;

		short ySuperscriptXOffset;

		short ySuperscriptYOffset;

		short yStrikeoutSize;

		short yStrikeoutPosition;

		short sFamilyClass;

		byte[] panose = new byte[10];

		byte[] achVendID = new byte[4];

		int fsSelection;

		int usFirstCharIndex;

		int usLastCharIndex;

		short sTypoAscender;

		short sTypoDescender;

		short sTypoLineGap;

		int usWinAscent;

		int usWinDescent;

		int ulCodePageRange1;

		int ulCodePageRange2;

		int sCapHeight;
	}

}

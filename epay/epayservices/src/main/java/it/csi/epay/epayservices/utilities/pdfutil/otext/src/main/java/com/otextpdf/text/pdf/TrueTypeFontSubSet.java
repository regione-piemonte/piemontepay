/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;


class TrueTypeFontSubSet {

	static final String[] tableNamesSimple = { "cvt ", "fpgm", "glyf", "head",
					"hhea", "hmtx", "loca", "maxp", "prep" };

	static final String[] tableNamesCmap = { "cmap", "cvt ", "fpgm", "glyf", "head",
					"hhea", "hmtx", "loca", "maxp", "prep" };

	static final String[] tableNamesExtra = { "OS/2", "cmap", "cvt ", "fpgm", "glyf", "head",
					"hhea", "hmtx", "loca", "maxp", "name, prep" };

	static final int[] entrySelectors = { 0, 0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4 };

	static final int TABLE_CHECKSUM = 0;

	static final int TABLE_OFFSET = 1;

	static final int TABLE_LENGTH = 2;

	static final int HEAD_LOCA_FORMAT_OFFSET = 51;

	static final int ARG_1_AND_2_ARE_WORDS = 1;

	static final int WE_HAVE_A_SCALE = 8;

	static final int MORE_COMPONENTS = 32;

	static final int WE_HAVE_AN_X_AND_Y_SCALE = 64;

	static final int WE_HAVE_A_TWO_BY_TWO = 128;

	protected HashMap<String, int[]> tableDirectory;

	protected RandomAccessFileOrArray rf;

	protected String fileName;

	protected boolean includeCmap;

	protected boolean includeExtras;

	protected boolean locaShortTable;

	protected int[] locaTable;

	protected HashSet<Integer> glyphsUsed;

	protected ArrayList<Integer> glyphsInList;

	protected int tableGlyphOffset;

	protected int[] newLocaTable;

	protected byte[] newLocaTableOut;

	protected byte[] newGlyfTable;

	protected int glyfTableRealSize;

	protected int locaTableRealSize;

	protected byte[] outFont;

	protected int fontPtr;

	protected int directoryOffset;

	TrueTypeFontSubSet ( String fileName, RandomAccessFileOrArray rf, HashSet<Integer> glyphsUsed, int directoryOffset, boolean includeCmap,
					boolean includeExtras ) {
		this.fileName = fileName;
		this.rf = rf;
		this.glyphsUsed = glyphsUsed;
		this.includeCmap = includeCmap;
		this.includeExtras = includeExtras;
		this.directoryOffset = directoryOffset;
		glyphsInList = new ArrayList<> ( glyphsUsed );
	}

	byte[] process () throws IOException, DocumentException {
		try {
			rf.reOpen ();
			createTableDirectory ();
			readLoca ();
			flatGlyphs ();
			createNewGlyphTables ();
			locaTobytes ();
			assembleFont ();
			return outFont;
		} finally {
			try {
				rf.close ();
			} catch ( Exception ignored ) {
			}
		}
	}

	protected void assembleFont () throws IOException {
		int[] tableLocation;
		int fullFontSize = 0;
		String[] tableNames;
		if ( includeExtras )
			tableNames = tableNamesExtra;
		else {
			if ( includeCmap )
				tableNames = tableNamesCmap;
			else
				tableNames = tableNamesSimple;
		}
		int tablesUsed = 2;
		int len;
		for ( String name : tableNames ) {
			if ( name.equals ( "glyf" ) || name.equals ( "loca" ) )
				continue;
			tableLocation = tableDirectory.get ( name );
			if ( tableLocation == null )
				continue;
			++tablesUsed;
			fullFontSize += tableLocation[TABLE_LENGTH] + 3 & ~3;
		}
		fullFontSize += newLocaTableOut.length;
		fullFontSize += newGlyfTable.length;
		int ref = 16 * tablesUsed + 12;
		fullFontSize += ref;
		outFont = new byte[fullFontSize];
		fontPtr = 0;
		writeFontInt ( 0x00010000 );
		writeFontShort ( tablesUsed );
		int selector = entrySelectors[tablesUsed];
		writeFontShort ( ( 1 << selector ) * 16 );
		writeFontShort ( selector );
		writeFontShort ( ( tablesUsed - ( 1 << selector ) ) * 16 );
		for ( String name : tableNames ) {
			tableLocation = tableDirectory.get ( name );
			if ( tableLocation == null )
				continue;
			writeFontString ( name );
			if ( name.equals ( "glyf" ) ) {
				writeFontInt ( calculateChecksum ( newGlyfTable ) );
				len = glyfTableRealSize;
			} else if ( name.equals ( "loca" ) ) {
				writeFontInt ( calculateChecksum ( newLocaTableOut ) );
				len = locaTableRealSize;
			} else {
				writeFontInt ( tableLocation[TABLE_CHECKSUM] );
				len = tableLocation[TABLE_LENGTH];
			}
			writeFontInt ( ref );
			writeFontInt ( len );
			ref += len + 3 & ~3;
		}
		for ( String name : tableNames ) {
			tableLocation = tableDirectory.get ( name );
			if ( tableLocation == null )
				continue;
			if ( name.equals ( "glyf" ) ) {
				System.arraycopy ( newGlyfTable, 0, outFont, fontPtr, newGlyfTable.length );
				fontPtr += newGlyfTable.length;
				newGlyfTable = null;
			} else if ( name.equals ( "loca" ) ) {
				System.arraycopy ( newLocaTableOut, 0, outFont, fontPtr, newLocaTableOut.length );
				fontPtr += newLocaTableOut.length;
				newLocaTableOut = null;
			} else {
				rf.seek ( tableLocation[TABLE_OFFSET] );
				rf.readFully ( outFont, fontPtr, tableLocation[TABLE_LENGTH] );
				fontPtr += tableLocation[TABLE_LENGTH] + 3 & ~3;
			}
		}
	}

	protected void createTableDirectory () throws IOException, DocumentException {
		tableDirectory = new HashMap<> ();
		rf.seek ( directoryOffset );
		int id = rf.readInt ();
		if ( id != 0x00010000 )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "1.is.not.a.true.type.file", fileName ) );
		int num_tables = rf.readUnsignedShort ();
		rf.skipBytes ( 6 );
		for ( int k = 0; k < num_tables; ++k ) {
			String tag = readStandardString ();
			int[] tableLocation = new int[3];
			tableLocation[TABLE_CHECKSUM] = rf.readInt ();
			tableLocation[TABLE_OFFSET] = rf.readInt ();
			tableLocation[TABLE_LENGTH] = rf.readInt ();
			tableDirectory.put ( tag, tableLocation );
		}
	}

	protected void readLoca () throws IOException, DocumentException {
		int[] tableLocation;
		tableLocation = tableDirectory.get ( "head" );
		if ( tableLocation == null )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "table.1.does.not.exist.in.2", "head", fileName ) );
		rf.seek ( tableLocation[TABLE_OFFSET] + HEAD_LOCA_FORMAT_OFFSET );
		locaShortTable = rf.readUnsignedShort () == 0;
		tableLocation = tableDirectory.get ( "loca" );
		if ( tableLocation == null )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "table.1.does.not.exist.in.2", "loca", fileName ) );
		rf.seek ( tableLocation[TABLE_OFFSET] );
		if ( locaShortTable ) {
			int entries = tableLocation[TABLE_LENGTH] / 2;
			locaTable = new int[entries];
			for ( int k = 0; k < entries; ++k )
				locaTable[k] = rf.readUnsignedShort () * 2;
		} else {
			int entries = tableLocation[TABLE_LENGTH] / 4;
			locaTable = new int[entries];
			for ( int k = 0; k < entries; ++k )
				locaTable[k] = rf.readInt ();
		}
	}

	protected void createNewGlyphTables () throws IOException {
		newLocaTable = new int[locaTable.length];
		int[] activeGlyphs = new int[glyphsInList.size ()];
		for ( int k = 0; k < activeGlyphs.length; ++k )
			activeGlyphs[k] = glyphsInList.get ( k );
		Arrays.sort ( activeGlyphs );
		int glyfSize = 0;
		for ( int glyph : activeGlyphs ) {
			glyfSize += locaTable[glyph + 1] - locaTable[glyph];
		}
		glyfTableRealSize = glyfSize;
		glyfSize = glyfSize + 3 & ~3;
		newGlyfTable = new byte[glyfSize];
		int glyfPtr = 0;
		int listGlyf = 0;
		for ( int k = 0; k < newLocaTable.length; ++k ) {
			newLocaTable[k] = glyfPtr;
			if ( listGlyf < activeGlyphs.length && activeGlyphs[listGlyf] == k ) {
				++listGlyf;
				newLocaTable[k] = glyfPtr;
				int start = locaTable[k];
				int len = locaTable[k + 1] - start;
				if ( len > 0 ) {
					rf.seek ( tableGlyphOffset + start );
					rf.readFully ( newGlyfTable, glyfPtr, len );
					glyfPtr += len;
				}
			}
		}
	}

	protected void locaTobytes () {
		if ( locaShortTable )
			locaTableRealSize = newLocaTable.length * 2;
		else
			locaTableRealSize = newLocaTable.length * 4;
		newLocaTableOut = new byte[locaTableRealSize + 3 & ~3];
		outFont = newLocaTableOut;
		fontPtr = 0;
		for ( int i : newLocaTable ) {
			if ( locaShortTable )
				writeFontShort ( i / 2 );
			else
				writeFontInt ( i );
		}

	}

	protected void flatGlyphs () throws IOException, DocumentException {
		int[] tableLocation;
		tableLocation = tableDirectory.get ( "glyf" );
		if ( tableLocation == null )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "table.1.does.not.exist.in.2", "glyf", fileName ) );
		Integer glyph0 = 0;
		if ( !glyphsUsed.contains ( glyph0 ) ) {
			glyphsUsed.add ( glyph0 );
			glyphsInList.add ( glyph0 );
		}
		tableGlyphOffset = tableLocation[TABLE_OFFSET];
		for ( Integer integer : glyphsInList ) {
			int glyph = integer;
			checkGlyphComposite ( glyph );
		}
	}

	protected void checkGlyphComposite ( int glyph ) throws IOException {
		int start = locaTable[glyph];
		if ( start == locaTable[glyph + 1] )
			return;
		rf.seek ( tableGlyphOffset + start );
		int numContours = rf.readShort ();
		if ( numContours >= 0 )
			return;
		rf.skipBytes ( 8 );
		for ( ; ; ) {
			int flags = rf.readUnsignedShort ();
			Integer cGlyph = rf.readUnsignedShort ();
			if ( !glyphsUsed.contains ( cGlyph ) ) {
				glyphsUsed.add ( cGlyph );
				glyphsInList.add ( cGlyph );
			}
			if ( ( flags & MORE_COMPONENTS ) == 0 )
				return;
			int skip;
			if ( ( flags & ARG_1_AND_2_ARE_WORDS ) != 0 )
				skip = 4;
			else
				skip = 2;
			if ( ( flags & WE_HAVE_A_SCALE ) != 0 )
				skip += 2;
			else if ( ( flags & WE_HAVE_AN_X_AND_Y_SCALE ) != 0 )
				skip += 4;
			if ( ( flags & WE_HAVE_A_TWO_BY_TWO ) != 0 )
				skip += 8;
			rf.skipBytes ( skip );
		}
	}

	protected String readStandardString () throws IOException {
		byte[] buf = new byte[4];
		rf.readFully ( buf );
		try {
			return new String ( buf, BaseFont.WINANSI );
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
	}

	protected void writeFontShort ( int n ) {
		outFont[fontPtr++] = (byte) ( n >> 8 );
		outFont[fontPtr++] = (byte) n;
	}

	protected void writeFontInt ( int n ) {
		outFont[fontPtr++] = (byte) ( n >> 24 );
		outFont[fontPtr++] = (byte) ( n >> 16 );
		outFont[fontPtr++] = (byte) ( n >> 8 );
		outFont[fontPtr++] = (byte) n;
	}

	protected void writeFontString ( String s ) {
		byte[] b = PdfEncodings.convertToBytes ( s, BaseFont.WINANSI );
		System.arraycopy ( b, 0, outFont, fontPtr, b.length );
		fontPtr += b.length;
	}

	protected int calculateChecksum ( byte[] b ) {
		int len = b.length / 4;
		int v0 = 0;
		int v1 = 0;
		int v2 = 0;
		int v3 = 0;
		int ptr = 0;
		for ( int k = 0; k < len; ++k ) {
			v3 += b[ptr++] & 0xff;
			v2 += b[ptr++] & 0xff;
			v1 += b[ptr++] & 0xff;
			v0 += b[ptr++] & 0xff;
		}
		return v0 + ( v1 << 8 ) + ( v2 << 16 ) + ( v3 << 24 );
	}
}

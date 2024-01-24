/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.exceptions.InvalidPdfException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io.RandomAccessSourceFactory;

import java.io.IOException;


public class PRTokeniser {

	public static final boolean[] delims = {
					true, true, false, false, false, false, false, false, false, false,
					true, true, false, true, true, false, false, false, false, false,
					false, false, false, false, false, false, false, false, false, false,
					false, false, false, true, false, false, false, false, true, false,
					false, true, true, false, false, false, false, false, true, false,
					false, false, false, false, false, false, false, false, false, false,
					false, true, false, true, false, false, false, false, false, false,
					false, false, false, false, false, false, false, false, false, false,
					false, false, false, false, false, false, false, false, false, false,
					false, false, true, false, true, false, false, false, false, false,
					false, false, false, false, false, false, false, false, false, false,
					false, false, false, false, false, false, false, false, false, false,
					false, false, false, false, false, false, false, false, false, false,
					false, false, false, false, false, false, false, false, false, false,
					false, false, false, false, false, false, false, false, false, false,
					false, false, false, false, false, false, false, false, false, false,
					false, false, false, false, false, false, false, false, false, false,
					false, false, false, false, false, false, false, false, false, false,
					false, false, false, false, false, false, false, false, false, false,
					false, false, false, false, false, false, false, false, false, false,
					false, false, false, false, false, false, false, false, false, false,
					false, false, false, false, false, false, false, false, false, false,
					false, false, false, false, false, false, false, false, false, false,
					false, false, false, false, false, false, false, false, false, false,
					false, false, false, false, false, false, false, false, false, false,
					false, false, false, false, false, false, false };

	static final String EMPTY = "";

	private final RandomAccessFileOrArray file;

	protected TokenType type;

	protected String stringValue;

	protected int reference;

	protected int generation;

	protected boolean hexString;

	public PRTokeniser ( RandomAccessFileOrArray file ) {
		this.file = file;
	}

	public static boolean isWhitespace ( int ch ) {
		return ( ch == 0 || ch == 9 || ch == 10 || ch == 12 || ch == 13 || ch == 32 );
	}

	public static int getHex ( int v ) {
		if ( v >= '0' && v <= '9' )
			return v - '0';
		if ( v >= 'A' && v <= 'F' )
			return v - 'A' + 10;
		if ( v >= 'a' && v <= 'f' )
			return v - 'a' + 10;
		return -1;
	}

	public static long[] checkObjectStart ( byte[] line ) {
		try {
			PRTokeniser tk = new PRTokeniser ( new RandomAccessFileOrArray ( new RandomAccessSourceFactory ().createSource ( line ) ) );
			int num;
			int gen;
			if ( !tk.nextToken () || tk.getTokenType () != TokenType.NUMBER )
				return null;
			num = tk.intValue ();
			if ( !tk.nextToken () || tk.getTokenType () != TokenType.NUMBER )
				return null;
			gen = tk.intValue ();
			if ( !tk.nextToken () )
				return null;
			if ( !tk.getStringValue ().equals ( "obj" ) )
				return null;
			return new long[] { num, gen };
		} catch ( Exception ignored ) {
		}
		return null;
	}

	public void seek ( long pos ) throws IOException {
		file.seek ( pos );
	}

	public long getFilePointer () {
		return file.getFilePointer ();
	}

	public void close () throws IOException {
		file.close ();
	}

	public long length () throws IOException {
		return file.length ();
	}

	public int read () throws IOException {
		return file.read ();
	}

	public RandomAccessFileOrArray getSafeFile () {
		return new RandomAccessFileOrArray ( file );
	}

	public RandomAccessFileOrArray getFile () {
		return file;
	}

	public String readString ( int size ) throws IOException {
		StringBuilder buf = new StringBuilder ();
		int ch;
		while ( ( size-- ) > 0 ) {
			ch = read ();
			if ( ch == -1 )
				break;
			buf.append ( (char) ch );
		}
		return buf.toString ();
	}

	public TokenType getTokenType () {
		return type;
	}

	public String getStringValue () {
		return stringValue;
	}

	public int getReference () {
		return reference;
	}

	public int getGeneration () {
		return generation;
	}

	public void backOnePosition ( int ch ) {
		if ( ch != -1 )
			file.pushBack ( (byte) ch );
	}

	public void throwError ( String error ) throws IOException {
		throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "1.at.file.pointer.2", error, String.valueOf ( file.getFilePointer () ) ) );
	}

	public int getHeaderOffset () throws IOException {
		String str = readString ( 1024 );
		int idx = str.indexOf ( "%PDF-" );
		if ( idx < 0 ) {
			idx = str.indexOf ( "%FDF-" );
			if ( idx < 0 )
				throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "pdf.header.not.found" ) );
		}

		return idx;
	}

	public char checkPdfHeader () throws IOException {
		file.seek ( 0 );
		String str = readString ( 1024 );
		int idx = str.indexOf ( "%PDF-" );
		if ( idx != 0 )
			throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "pdf.header.not.found" ) );
		return str.charAt ( 7 );
	}

	public void checkFdfHeader () throws IOException {
		file.seek ( 0 );
		String str = readString ( 1024 );
		int idx = str.indexOf ( "%FDF-" );
		if ( idx != 0 )
			throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "fdf.header.not.found" ) );
	}

	public long getStartxref () throws IOException {
		int arrLength = 1024;
		long fileLength = file.length ();
		long pos = fileLength - arrLength;
		if ( pos < 1 )
			pos = 1;
		while ( pos > 0 ) {
			file.seek ( pos );
			String str = readString ( arrLength );
			int idx = str.lastIndexOf ( "startxref" );
			if ( idx >= 0 )
				return pos + idx;
			pos = pos - arrLength + 9;
		}
		throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "pdf.startxref.not.found" ) );
	}

	public void nextValidToken () throws IOException {
		int level = 0;
		String n1 = null;
		String n2 = null;
		long ptr = 0;
		while ( nextToken () ) {
			if ( type == TokenType.COMMENT )
				continue;
			switch ( level ) {
			case 0: {
				if ( type != TokenType.NUMBER )
					return;
				ptr = file.getFilePointer ();
				n1 = stringValue;
				++level;
				break;
			}
			case 1: {
				if ( type != TokenType.NUMBER ) {
					file.seek ( ptr );
					type = TokenType.NUMBER;
					stringValue = n1;
					return;
				}
				n2 = stringValue;
				++level;
				break;
			}
			default: {
				if ( type != TokenType.OTHER || !stringValue.equals ( "R" ) ) {
					file.seek ( ptr );
					type = TokenType.NUMBER;
					stringValue = n1;
					return;
				}
				type = TokenType.REF;
				reference = Integer.parseInt ( n1 );
				assert n2 != null;
				generation = Integer.parseInt ( n2 );
				return;
			}
			}
		}

		if ( level == 1 ) {
			type = TokenType.NUMBER;
		}
	}

	public boolean nextToken () throws IOException {
		int ch;
		do {
			ch = file.read ();
		} while ( ch != -1 && isWhitespace ( ch ) );
		if ( ch == -1 ) {
			type = TokenType.ENDOFFILE;
			return false;
		}

		final StringBuilder outBuf = new StringBuilder ();
		stringValue = EMPTY;

		switch ( ch ) {
		case '[':
			type = TokenType.START_ARRAY;
			break;
		case ']':
			type = TokenType.END_ARRAY;
			break;
		case '/': {
			outBuf.setLength ( 0 );
			type = TokenType.NAME;
			while ( true ) {
				ch = file.read ();
				if ( delims[ch + 1] )
					break;
				if ( ch == '#' ) {
					ch = ( getHex ( file.read () ) << 4 ) + getHex ( file.read () );
				}
				outBuf.append ( (char) ch );
			}
			backOnePosition ( ch );
			break;
		}
		case '>':
			ch = file.read ();
			if ( ch != '>' )
				throwError ( MessageLocalization.getComposedMessage ( "greaterthan.not.expected" ) );
			type = TokenType.END_DIC;
			break;
		case '<': {
			int v1 = file.read ();
			if ( v1 == '<' ) {
				type = TokenType.START_DIC;
				break;
			}
			outBuf.setLength ( 0 );
			type = TokenType.STRING;
			hexString = true;
			int v2 = 0;
			while ( true ) {
				while ( isWhitespace ( v1 ) )
					v1 = file.read ();
				if ( v1 == '>' )
					break;
				v1 = getHex ( v1 );
				if ( v1 < 0 )
					break;
				v2 = file.read ();
				while ( isWhitespace ( v2 ) )
					v2 = file.read ();
				if ( v2 == '>' ) {
					ch = v1 << 4;
					outBuf.append ( (char) ch );
					break;
				}
				v2 = getHex ( v2 );
				if ( v2 < 0 )
					break;
				ch = ( v1 << 4 ) + v2;
				outBuf.append ( (char) ch );
				v1 = file.read ();
			}
			if ( v1 < 0 || v2 < 0 )
				throwError ( MessageLocalization.getComposedMessage ( "error.reading.string" ) );
			break;
		}
		case '%':
			type = TokenType.COMMENT;
			do {
				ch = file.read ();
			} while ( ch != -1 && ch != '\r' && ch != '\n' );
			break;
		case '(': {
			outBuf.setLength ( 0 );
			type = TokenType.STRING;
			hexString = false;
			int nesting = 0;
			while ( true ) {
				ch = file.read ();
				if ( ch == -1 )
					break;
				if ( ch == '(' ) {
					++nesting;
				} else if ( ch == ')' ) {
					--nesting;
				} else if ( ch == '\\' ) {
					boolean lineBreak = false;
					ch = file.read ();
					switch ( ch ) {
					case 'n':
						ch = '\n';
						break;
					case 'r':
						ch = '\r';
						break;
					case 't':
						ch = '\t';
						break;
					case 'b':
						ch = '\b';
						break;
					case 'f':
						ch = '\f';
						break;
					case '(':
					case ')':
					case '\\':
						break;
					case '\r':
						lineBreak = true;
						ch = file.read ();
						if ( ch != '\n' )
							backOnePosition ( ch );
						break;
					case '\n':
						lineBreak = true;
						break;
					default: {
						if ( ch < '0' || ch > '7' ) {
							break;
						}
						int octal = ch - '0';
						ch = file.read ();
						if ( ch < '0' || ch > '7' ) {
							backOnePosition ( ch );
							ch = octal;
							break;
						}
						octal = ( octal << 3 ) + ch - '0';
						ch = file.read ();
						if ( ch < '0' || ch > '7' ) {
							backOnePosition ( ch );
							ch = octal;
							break;
						}
						octal = ( octal << 3 ) + ch - '0';
						ch = octal & 0xff;
						break;
					}
					}
					if ( lineBreak )
						continue;
					if ( ch < 0 )
						break;
				} else if ( ch == '\r' ) {
					ch = file.read ();
					if ( ch < 0 )
						break;
					if ( ch != '\n' ) {
						backOnePosition ( ch );
						ch = '\n';
					}
				}
				if ( nesting == -1 )
					break;
				outBuf.append ( (char) ch );
			}
			if ( ch == -1 )
				throwError ( MessageLocalization.getComposedMessage ( "error.reading.string" ) );
			break;
		}
		default: {
			outBuf.setLength ( 0 );
			if ( ch == '-' || ch == '+' || ch == '.' || ( ch >= '0' && ch <= '9' ) ) {
				type = TokenType.NUMBER;
				if ( ch == '-' ) {
					boolean minus = false;
					do {
						minus = !minus;
						ch = file.read ();
					} while ( ch == '-' );
					if ( minus )
						outBuf.append ( '-' );
				} else {
					outBuf.append ( (char) ch );
					ch = file.read ();
				}
				while ( ( ( ch >= '0' && ch <= '9' ) || ch == '.' ) ) {
					outBuf.append ( (char) ch );
					ch = file.read ();
				}
			} else {
				type = TokenType.OTHER;
				do {
					outBuf.append ( (char) ch );
					ch = file.read ();
				} while ( !delims[ch + 1] );
			}
			if ( ch != -1 )
				backOnePosition ( ch );
			break;
		}
		}
		stringValue = outBuf.toString ();
		return true;
	}

	public long longValue () {
		return Long.parseLong ( stringValue );
	}

	public int intValue () {
		return Integer.parseInt ( stringValue );
	}

	public boolean readLineSegment ( byte[] input ) throws IOException {
		int c = -1;
		boolean eol = false;
		int ptr = 0;
		int len = input.length;
		if ( ptr < len ) {
			while ( isWhitespace ( ( c = read () ) ) )
				;
		}
		while ( ptr < len ) {
			switch ( c ) {
			case -1:
			case '\n':
				eol = true;
				break;
			case '\r':
				eol = true;
				long cur = getFilePointer ();
				if ( ( read () ) != '\n' ) {
					seek ( cur );
				}
				break;
			default:
				input[ptr++] = (byte) c;
				break;
			}
			if ( eol || len <= ptr ) {
				break;
			} else {
				c = read ();
			}
		}
		if ( ptr >= len ) {
			while ( !eol ) {
				switch ( c = read () ) {
				case -1:
				case '\n':
					eol = true;
					break;
				case '\r':
					eol = true;
					long cur = getFilePointer ();
					if ( ( read () ) != '\n' ) {
						seek ( cur );
					}
					break;
				}
			}
		}

		if ( ( c == -1 ) && ( ptr == 0 ) ) {
			return true;
		}
		if ( ptr + 2 <= len ) {
			input[ptr++] = (byte) ' ';
			input[ptr] = (byte) 'X';
		}
		return false;
	}

	public boolean isHexString () {
		return this.hexString;
	}

	public enum TokenType {
		NUMBER,
		STRING,
		NAME,
		COMMENT,
		START_ARRAY,
		END_ARRAY,
		START_DIC,
		END_DIC,
		REF,
		OTHER,
		ENDOFFILE
	}

}

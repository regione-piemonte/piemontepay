/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Document;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io.IndependentRandomAccessSource;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io.RandomAccessSource;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io.RandomAccessSourceFactory;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class RandomAccessFileOrArray implements DataInput {

	private final RandomAccessSource byteSource;

	private long byteSourcePosition;

	private byte back;

	private boolean isBack = false;

	@Deprecated
	public RandomAccessFileOrArray ( String filename ) throws IOException {
		this ( new RandomAccessSourceFactory ()
						.setForceRead ( false )
						.setUsePlainRandomAccess ( Document.plainRandomAccess )
						.createBestSource ( filename ) );

	}

	@Deprecated
	public RandomAccessFileOrArray ( RandomAccessFileOrArray source ) {
		this ( new IndependentRandomAccessSource ( source.byteSource ) );
	}

	public RandomAccessFileOrArray ( RandomAccessSource byteSource ) {
		this.byteSource = byteSource;
	}

	@Deprecated
	public RandomAccessFileOrArray ( String filename, boolean forceRead, boolean plainRandomAccess ) throws IOException {
		this ( new RandomAccessSourceFactory ()
						.setForceRead ( forceRead )
						.setUsePlainRandomAccess ( plainRandomAccess )
						.createBestSource ( filename ) );
	}

	@Deprecated
	public RandomAccessFileOrArray ( URL url ) throws IOException {
		this ( new RandomAccessSourceFactory ().createSource ( url ) );
	}

	@Deprecated
	public RandomAccessFileOrArray ( InputStream is ) throws IOException {
		this ( new RandomAccessSourceFactory ().createSource ( is ) );
	}

	@Deprecated
	public RandomAccessFileOrArray ( byte[] arrayIn ) {
		this ( new RandomAccessSourceFactory ().createSource ( arrayIn ) );
	}

	@Deprecated
	protected RandomAccessSource getByteSource () {
		return byteSource;
	}

	public void pushBack ( byte b ) {
		back = b;
		isBack = true;
	}

	public int read () throws IOException {
		if ( isBack ) {
			isBack = false;
			return back & 0xff;
		}

		return byteSource.get ( byteSourcePosition++ );
	}

	public int read ( byte[] b, int off, int len ) throws IOException {
		if ( len == 0 )
			return 0;
		int count = 0;
		if ( isBack && len > 0 ) {
			isBack = false;
			b[off++] = back;
			--len;
			count++;
		}
		if ( len > 0 ) {
			int byteSourceCount = byteSource.get ( byteSourcePosition, b, off, len );
			if ( byteSourceCount > 0 ) {
				count += byteSourceCount;
				byteSourcePosition += byteSourceCount;
			}
		}
		if ( count == 0 )
			return -1;
		return count;
	}

	public int read ( byte[] b ) throws IOException {
		return read ( b, 0, b.length );
	}

	public void readFully ( byte[] b ) throws IOException {
		readFully ( b, 0, b.length );
	}

	public void readFully ( byte[] b, int off, int len ) throws IOException {
		int n = 0;
		do {
			int count = read ( b, off + n, len - n );
			if ( count < 0 )
				throw new EOFException ();
			n += count;
		} while ( n < len );
	}

	public long skip ( long n ) throws IOException {
		if ( n <= 0 ) {
			return 0;
		}
		int adj = 0;
		if ( isBack ) {
			isBack = false;
			if ( n == 1 ) {
				return 1;
			} else {
				--n;
				adj = 1;
			}
		}
		long pos;
		long len;
		long newpos;

		pos = getFilePointer ();
		len = length ();
		newpos = pos + n;
		if ( newpos > len ) {
			newpos = len;
		}
		seek ( newpos );

		return newpos - pos + adj;
	}

	public int skipBytes ( int n ) throws IOException {
		return (int) skip ( n );
	}

	@Deprecated
	public void reOpen () throws IOException {
		seek ( 0 );
	}

	public void close () throws IOException {
		isBack = false;

		byteSource.close ();
	}

	public long length () throws IOException {
		return byteSource.length ();
	}

	public void seek ( long pos ) throws IOException {
		byteSourcePosition = pos;
		isBack = false;
	}

	public long getFilePointer () {
		return byteSourcePosition - ( isBack ? 1 : 0 );
	}

	public boolean readBoolean () throws IOException {
		int ch = this.read ();
		if ( ch < 0 )
			throw new EOFException ();
		return ( ch != 0 );
	}

	public byte readByte () throws IOException {
		int ch = this.read ();
		if ( ch < 0 )
			throw new EOFException ();
		return (byte) ( ch );
	}

	public int readUnsignedByte () throws IOException {
		int ch = this.read ();
		if ( ch < 0 )
			throw new EOFException ();
		return ch;
	}

	public short readShort () throws IOException {
		int ch1 = this.read ();
		int ch2 = this.read ();
		if ( ( ch1 | ch2 ) < 0 )
			throw new EOFException ();
		return (short) ( ( ch1 << 8 ) + ch2 );
	}

	public final short readShortLE () throws IOException {
		int ch1 = this.read ();
		int ch2 = this.read ();
		if ( ( ch1 | ch2 ) < 0 )
			throw new EOFException ();
		return (short) ( ( ch2 << 8 ) + ( ch1 ) );
	}

	public int readUnsignedShort () throws IOException {
		int ch1 = this.read ();
		int ch2 = this.read ();
		if ( ( ch1 | ch2 ) < 0 )
			throw new EOFException ();
		return ( ch1 << 8 ) + ch2;
	}

	public final int readUnsignedShortLE () throws IOException {
		int ch1 = this.read ();
		int ch2 = this.read ();
		if ( ( ch1 | ch2 ) < 0 )
			throw new EOFException ();
		return ( ch2 << 8 ) + ( ch1 );
	}

	public char readChar () throws IOException {
		int ch1 = this.read ();
		int ch2 = this.read ();
		if ( ( ch1 | ch2 ) < 0 )
			throw new EOFException ();
		return (char) ( ( ch1 << 8 ) + ch2 );
	}

	public int readInt () throws IOException {
		int ch1 = this.read ();
		int ch2 = this.read ();
		int ch3 = this.read ();
		int ch4 = this.read ();
		if ( ( ch1 | ch2 | ch3 | ch4 ) < 0 )
			throw new EOFException ();
		return ( ( ch1 << 24 ) + ( ch2 << 16 ) + ( ch3 << 8 ) + ch4 );
	}

	public final int readIntLE () throws IOException {
		int ch1 = this.read ();
		int ch2 = this.read ();
		int ch3 = this.read ();
		int ch4 = this.read ();
		if ( ( ch1 | ch2 | ch3 | ch4 ) < 0 )
			throw new EOFException ();
		return ( ( ch4 << 24 ) + ( ch3 << 16 ) + ( ch2 << 8 ) + ( ch1 ) );
	}

	public final long readUnsignedInt () throws IOException {
		long ch1 = this.read ();
		long ch2 = this.read ();
		long ch3 = this.read ();
		long ch4 = this.read ();
		if ( ( ch1 | ch2 | ch3 | ch4 ) < 0 )
			throw new EOFException ();
		return ( ( ch1 << 24 ) + ( ch2 << 16 ) + ( ch3 << 8 ) + ( ch4 ) );
	}

	public final long readUnsignedIntLE () throws IOException {
		long ch1 = this.read ();
		long ch2 = this.read ();
		long ch3 = this.read ();
		long ch4 = this.read ();
		if ( ( ch1 | ch2 | ch3 | ch4 ) < 0 )
			throw new EOFException ();
		return ( ( ch4 << 24 ) + ( ch3 << 16 ) + ( ch2 << 8 ) + ( ch1 ) );
	}

	public long readLong () throws IOException {
		return ( (long) ( readInt () ) << 32 ) + ( readInt () & 0xFFFFFFFFL );
	}

	public final long readLongLE () throws IOException {
		int i1 = readIntLE ();
		int i2 = readIntLE ();
		return ( (long) i2 << 32 ) + ( i1 & 0xFFFFFFFFL );
	}

	public float readFloat () throws IOException {
		return Float.intBitsToFloat ( readInt () );
	}

	public final float readFloatLE () throws IOException {
		return Float.intBitsToFloat ( readIntLE () );
	}

	public double readDouble () throws IOException {
		return Double.longBitsToDouble ( readLong () );
	}

	public final double readDoubleLE () throws IOException {
		return Double.longBitsToDouble ( readLongLE () );
	}

	public String readLine () throws IOException {
		StringBuilder input = new StringBuilder ();
		int c = -1;
		boolean eol = false;

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
			default:
				input.append ( (char) c );
				break;
			}
		}

		if ( ( c == -1 ) && ( input.length () == 0 ) ) {
			return null;
		}
		return input.toString ();
	}

	public String readUTF () throws IOException {
		return DataInputStream.readUTF ( this );
	}

	public String readString ( int length, String encoding ) throws IOException {
		byte[] buf = new byte[length];
		readFully ( buf );
		try {
			return new String ( buf, encoding );
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
	}

}

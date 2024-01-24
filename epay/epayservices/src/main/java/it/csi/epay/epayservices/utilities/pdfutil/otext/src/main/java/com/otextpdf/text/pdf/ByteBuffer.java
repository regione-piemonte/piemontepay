/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocWriter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


public class ByteBuffer extends OutputStream {

	public static final byte ZERO = (byte) '0';

	private static final int byteCacheSize = 0;

	private static final byte[][] byteCache = new byte[byteCacheSize][];

	private static final char[] chars = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	private static final byte[] bytes = new byte[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };

	private static final DecimalFormatSymbols dfs = new DecimalFormatSymbols ( Locale.US );

	public static boolean HIGH_PRECISION = false;

	protected int count;

	protected byte[] buf;

	public ByteBuffer () {
		this ( 128 );
	}

	public ByteBuffer ( int size ) {
		if ( size < 1 )
			size = 128;
		buf = new byte[size];
	}

	public static String formatDouble ( double d ) {
		return formatDouble ( d, null );
	}

	public static String formatDouble ( double d, ByteBuffer buf ) {
		if ( HIGH_PRECISION ) {
			DecimalFormat dn = new DecimalFormat ( "0.######", dfs );
			String sform = dn.format ( d );
			if ( buf == null )
				return sform;
			else {
				buf.append ( sform );
				return null;
			}
		}
		boolean negative = false;
		if ( Math.abs ( d ) < 0.000015 ) {
			if ( buf != null ) {
				buf.append ( ZERO );
				return null;
			} else {
				return "0";
			}
		}
		if ( d < 0 ) {
			negative = true;
			d = -d;
		}
		if ( d < 1.0 ) {
			d += 0.000005;
			if ( d >= 1 ) {
				if ( negative ) {
					if ( buf != null ) {
						buf.append ( (byte) '-' );
						buf.append ( (byte) '1' );
						return null;
					} else {
						return "-1";
					}
				} else {
					if ( buf != null ) {
						buf.append ( (byte) '1' );
						return null;
					} else {
						return "1";
					}
				}
			}
			if ( buf != null ) {
				int v = (int) ( d * 100000 );

				if ( negative )
					buf.append ( (byte) '-' );
				buf.append ( (byte) '0' );
				buf.append ( (byte) '.' );

				buf.append ( (byte) ( v / 10000 + ZERO ) );
				if ( v % 10000 != 0 ) {
					buf.append ( (byte) ( ( v / 1000 ) % 10 + ZERO ) );
					if ( v % 1000 != 0 ) {
						buf.append ( (byte) ( ( v / 100 ) % 10 + ZERO ) );
						if ( v % 100 != 0 ) {
							buf.append ( (byte) ( ( v / 10 ) % 10 + ZERO ) );
							if ( v % 10 != 0 ) {
								buf.append ( (byte) ( ( v ) % 10 + ZERO ) );
							}
						}
					}
				}
				return null;
			} else {
				int x = 100000;
				int v = (int) ( d * x );

				StringBuilder res = new StringBuilder ();
				if ( negative )
					res.append ( '-' );
				res.append ( "0." );

				while ( v < x / 10 ) {
					res.append ( '0' );
					x /= 10;
				}
				res.append ( v );
				int cut = res.length () - 1;
				while ( res.charAt ( cut ) == '0' ) {
					--cut;
				}
				res.setLength ( cut + 1 );
				return res.toString ();
			}
		} else if ( d <= 32767 ) {
			d += 0.005;
			int v = (int) ( d * 100 );

			if ( buf != null ) {
				if ( v < byteCacheSize ) {
					byte[] cache;
					int size = 0;

					if ( v % 100 != 0 ) {
						size += 2;
					}
					if ( v % 10 != 0 ) {
						size++;
					}
					cache = new byte[size];
					int add = 0;

					if ( v % 100 != 0 ) {
						cache[add++] = (byte) '.';
						cache[add++] = bytes[( v / 10 ) % 10];
						if ( v % 10 != 0 ) {
							cache[add++] = bytes[v % 10];
						}
					}
					byteCache[v] = cache;
				}

				if ( negative )
					buf.append ( (byte) '-' );
				if ( v >= 1000000 ) {
					buf.append ( bytes[( v / 1000000 )] );
				}
				if ( v >= 100000 ) {
					buf.append ( bytes[( v / 100000 ) % 10] );
				}
				if ( v >= 10000 ) {
					buf.append ( bytes[( v / 10000 ) % 10] );
				}
				if ( v >= 1000 ) {
					buf.append ( bytes[( v / 1000 ) % 10] );
				}
				if ( v >= 100 ) {
					buf.append ( bytes[( v / 100 ) % 10] );
				}

				if ( v % 100 != 0 ) {
					buf.append ( (byte) '.' );
					buf.append ( bytes[( v / 10 ) % 10] );
					if ( v % 10 != 0 ) {
						buf.append ( bytes[v % 10] );
					}
				}
				return null;
			} else {
				StringBuilder res = new StringBuilder ();
				if ( negative )
					res.append ( '-' );
				if ( v >= 1000000 ) {
					res.append ( chars[( v / 1000000 )] );
				}
				if ( v >= 100000 ) {
					res.append ( chars[( v / 100000 ) % 10] );
				}
				if ( v >= 10000 ) {
					res.append ( chars[( v / 10000 ) % 10] );
				}
				if ( v >= 1000 ) {
					res.append ( chars[( v / 1000 ) % 10] );
				}
				if ( v >= 100 ) {
					res.append ( chars[( v / 100 ) % 10] );
				}

				if ( v % 100 != 0 ) {
					res.append ( '.' );
					res.append ( chars[( v / 10 ) % 10] );
					if ( v % 10 != 0 ) {
						res.append ( chars[v % 10] );
					}
				}
				return res.toString ();
			}
		} else {
			d += 0.5;
			long v = (long) d;
			if ( negative )
				return "-" + v;
			else
				return Long.toString ( v );
		}
	}

	public ByteBuffer append_i ( int b ) {
		int newcount = count + 1;
		if ( newcount > buf.length ) {
			byte[] newbuf = new byte[Math.max ( buf.length << 1, newcount )];
			System.arraycopy ( buf, 0, newbuf, 0, count );
			buf = newbuf;
		}
		buf[count] = (byte) b;
		count = newcount;
		return this;
	}

	public ByteBuffer append ( byte[] b, int off, int len ) {
		if ( ( off < 0 ) || ( off > b.length ) || ( len < 0 ) ||
						( ( off + len ) > b.length ) || ( ( off + len ) < 0 ) || len == 0 )
			return this;
		int newcount = count + len;
		if ( newcount > buf.length ) {
			byte[] newbuf = new byte[Math.max ( buf.length << 1, newcount )];
			System.arraycopy ( buf, 0, newbuf, 0, count );
			buf = newbuf;
		}
		System.arraycopy ( b, off, buf, count, len );
		count = newcount;
		return this;
	}

	public ByteBuffer append ( byte[] b ) {
		return append ( b, 0, b.length );
	}

	public ByteBuffer append ( String str ) {
		if ( str != null )
			return append ( DocWriter.getISOBytes ( str ) );
		return this;
	}

	public ByteBuffer append ( char c ) {
		return append_i ( c );
	}

	public ByteBuffer append ( ByteBuffer buf ) {
		return append ( buf.buf, 0, buf.count );
	}

	public ByteBuffer append ( int i ) {
		return append ( (double) i );
	}

	public ByteBuffer append ( long i ) {
		return append ( Long.toString ( i ) );
	}

	public ByteBuffer append ( byte b ) {
		return append_i ( b );
	}

	public void appendHex ( byte b ) {
		append ( bytes[( b >> 4 ) & 0x0f] );
		append ( bytes[b & 0x0f] );
	}

	public ByteBuffer append ( float i ) {
		return append ( (double) i );
	}

	public ByteBuffer append ( double d ) {
		append ( formatDouble ( d, this ) );
		return this;
	}

	public void reset () {
		count = 0;
	}

	public byte[] toByteArray () {
		byte[] newbuf = new byte[count];
		System.arraycopy ( buf, 0, newbuf, 0, count );
		return newbuf;
	}

	public int size () {
		return count;
	}

	public void setSize ( int size ) {
		if ( size > count || size < 0 )
			throw new IndexOutOfBoundsException ( MessageLocalization.getComposedMessage ( "the.new.size.must.be.positive.and.lt.eq.of.the.current.size" ) );
		count = size;
	}

	@Override
	public String toString () {
		return new String ( buf, 0, count );
	}

	public String toString ( String enc ) throws UnsupportedEncodingException {
		return new String ( buf, 0, count, enc );
	}

	public void writeTo ( OutputStream out ) throws IOException {
		out.write ( buf, 0, count );
	}

	public void write ( int b ) throws IOException {
		append ( (byte) b );
	}

	@Override
	public void write ( byte[] b, int off, int len ) {
		append ( b, off, len );
	}

	public byte[] getBuffer () {
		return buf;
	}
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


public class ByteBuffer {

	private byte[] buffer;

	private int length;

	private String encoding = null;

	public ByteBuffer ( int initialCapacity ) {
		this.buffer = new byte[initialCapacity];
		this.length = 0;
	}

	public ByteBuffer ( byte[] buffer ) {
		this.buffer = buffer;
		this.length = buffer.length;
	}

	public ByteBuffer ( byte[] buffer, int length ) {
		if ( length > buffer.length ) {
			throw new ArrayIndexOutOfBoundsException ( "Valid length exceeds the buffer length." );
		}
		this.buffer = buffer;
		this.length = length;
	}

	public ByteBuffer ( InputStream in ) throws IOException {
		// load stream into buffer
		int chunk = 16384;
		this.length = 0;
		this.buffer = new byte[chunk];

		int read;
		while ( ( read = in.read ( this.buffer, this.length, chunk ) ) > 0 ) {
			this.length += read;
			if ( read == chunk ) {
				ensureCapacity ( length + chunk );
			} else {
				break;
			}
		}
	}

	public ByteBuffer ( byte[] buffer, int offset, int length ) {
		if ( length > buffer.length - offset ) {
			throw new ArrayIndexOutOfBoundsException ( "Valid length exceeds the buffer length." );
		}
		this.buffer = new byte[length];
		System.arraycopy ( buffer, offset, this.buffer, 0, length );
		this.length = length;
	}

	public InputStream getByteStream () {
		return new ByteArrayInputStream ( buffer, 0, length );
	}

	public int length () {
		return length;
	}

	public int charAt ( int index ) {
		if ( index < length ) {
			return buffer[index] & 0xFF;
		} else {
			throw new IndexOutOfBoundsException ( "The index exceeds the valid buffer area" );
		}
	}

	public void append ( byte b ) {
		ensureCapacity ( length + 1 );
		buffer[length++] = b;
	}

	public void append ( byte[] bytes, int offset, int len ) {
		ensureCapacity ( length + len );
		System.arraycopy ( bytes, offset, buffer, length, len );
		length += len;
	}

	public void append ( byte[] bytes ) {
		append ( bytes, 0, bytes.length );
	}

	public void append ( ByteBuffer anotherBuffer ) {
		append ( anotherBuffer.buffer, 0, anotherBuffer.length );
	}

	public String getEncoding () {
		if ( encoding == null ) {
			// needs four byte at maximum to determine encoding
			if ( length < 2 ) {
				// only one byte length must be UTF-8
				encoding = "UTF-8";
			} else if ( buffer[0] == 0 ) {
				// These cases are:
				//   00 nn -- -- - Big endian UTF-16
				//   00 00 00 nn - Big endian UTF-32
				//   00 00 FE FF - Big endian UTF 32

				if ( length < 4 || buffer[1] != 0 ) {
					encoding = "UTF-16BE";
				} else if ( ( buffer[2] & 0xFF ) == 0xFE && ( buffer[3] & 0xFF ) == 0xFF ) {
					encoding = "UTF-32BE";
				} else {
					encoding = "UTF-32";
				}
			} else if ( ( buffer[0] & 0xFF ) < 0x80 ) {
				// These cases are:
				//   nn mm -- -- - UTF-8, includes EF BB BF case
				//   nn 00 -- -- - Little endian UTF-16

				if ( buffer[1] != 0 ) {
					encoding = "UTF-8";
				} else if ( length < 4 || buffer[2] != 0 ) {
					encoding = "UTF-16LE";
				} else {
					encoding = "UTF-32LE";
				}
			} else {
				// These cases are:
				//   EF BB BF -- - UTF-8
				//   FE FF -- -- - Big endian UTF-16
				//   FF FE 00 00 - Little endian UTF-32
				//   FF FE -- -- - Little endian UTF-16

				if ( ( buffer[0] & 0xFF ) == 0xEF ) {
					encoding = "UTF-8";
				} else if ( ( buffer[0] & 0xFF ) == 0xFE ) {
					encoding = "UTF-16"; // in fact BE 
				} else if ( length < 4 || buffer[2] != 0 ) {
					encoding = "UTF-16"; // in fact LE
				} else {
					encoding = "UTF-32"; // in fact LE
				}
			}
		}

		return encoding;
	}

	private void ensureCapacity ( int requestedLength ) {
		if ( requestedLength > buffer.length ) {
			byte[] oldBuf = buffer;
			buffer = new byte[oldBuf.length * 2];
			System.arraycopy ( oldBuf, 0, buffer, 0, oldBuf.length );
		}
	}
}

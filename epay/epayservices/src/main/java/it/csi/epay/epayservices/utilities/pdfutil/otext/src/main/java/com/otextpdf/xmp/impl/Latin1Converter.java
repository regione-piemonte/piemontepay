/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;


public class Latin1Converter {

	private static final int STATE_START = 0;

	private static final int STATE_UTF8CHAR = 11;

	private Latin1Converter () {
		// EMPTY
	}

	public static ByteBuffer convert ( ByteBuffer buffer ) {
		if ( "UTF-8".equals ( buffer.getEncoding () ) ) {
			// the buffer containing one UTF-8 char (up to 8 bytes) 
			byte[] readAheadBuffer = new byte[8];
			// the number of bytes read ahead.
			int readAhead = 0;
			// expected UTF8 bytesto come
			int expectedBytes = 0;
			// output buffer with estimated length
			ByteBuffer out = new ByteBuffer ( buffer.length () * 4 / 3 );

			int state = STATE_START;
			for ( int i = 0; i < buffer.length (); i++ ) {
				int b = buffer.charAt ( i );

				switch ( state ) {
				default:
				case STATE_START:
					if ( b < 0x7F ) {
						out.append ( (byte) b );
					} else if ( b >= 0xC0 ) {
						// start of UTF8 sequence
						expectedBytes = -1;
						int test = b;
						for ( ; expectedBytes < 8 && ( test & 0x80 ) == 0x80; test = test << 1 ) {
							expectedBytes++;
						}
						readAheadBuffer[readAhead++] = (byte) b;
						state = STATE_UTF8CHAR;
					} else //  implicitly:  b >= 0x80  &&  b < 0xC0
					{
						// invalid UTF8 start char, assume to be Latin-1
						byte[] utf8 = convertToUTF8 ( (byte) b );
						out.append ( utf8 );
					}
					break;

				case STATE_UTF8CHAR:
					if ( expectedBytes > 0 && ( b & 0xC0 ) == 0x80 ) {
						// valid UTF8 char, add to readAheadBuffer
						readAheadBuffer[readAhead++] = (byte) b;
						expectedBytes--;

						if ( expectedBytes == 0 ) {
							out.append ( readAheadBuffer, 0, readAhead );
							readAhead = 0;

							state = STATE_START;
						}
					} else {
						// invalid UTF8 char:
						// 1. convert first of seq to UTF8
						byte[] utf8 = convertToUTF8 ( readAheadBuffer[0] );
						out.append ( utf8 );

						// 2. continue processing at second byte of sequence
						i = i - readAhead;
						readAhead = 0;

						state = STATE_START;
					}
					break;
				}
			}

			// loop ends with "half" Utf8 char --> assume that the bytes are Latin-1
			if ( state == STATE_UTF8CHAR ) {
				for ( int j = 0; j < readAhead; j++ ) {
					byte b = readAheadBuffer[j];
					byte[] utf8 = convertToUTF8 ( b );
					out.append ( utf8 );
				}
			}

			return out;
		} else {
			// Latin-1 fixing applies only to UTF-8
			return buffer;
		}
	}

	private static byte[] convertToUTF8 ( byte ch ) {
		int c = ch & 0xFF;
		try {
			if ( c >= 0x80 ) {
				if ( c == 0x81 || c == 0x8D || c == 0x8F || c == 0x90 || c == 0x9D ) {
					return new byte[] { 0x20 }; // space for undefined 
				}

				// interpret byte as Windows Cp1252 char
				return new String ( new byte[] { ch }, "cp1252" ).getBytes ( StandardCharsets.UTF_8 );
			}
		} catch ( UnsupportedEncodingException e ) {
			// EMPTY
		}
		return new byte[] { ch };
	}
}

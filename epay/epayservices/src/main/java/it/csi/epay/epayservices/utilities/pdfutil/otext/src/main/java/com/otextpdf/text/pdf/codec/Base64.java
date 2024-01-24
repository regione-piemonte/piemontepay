/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;


public class Base64 {

	public final static int NO_OPTIONS = 0;

	public final static int ENCODE = 1;

	public final static int DECODE = 0;

	public final static int GZIP = 2;

	public final static int DONT_BREAK_LINES = 8;

	public final static int URL_SAFE = 16;

	public final static int ORDERED = 32;

	private final static int MAX_LINE_LENGTH = 76;

	private final static byte EQUALS_SIGN = (byte) '=';

	private final static byte NEW_LINE = (byte) '\n';

	private final static String PREFERRED_ENCODING = "UTF-8";

	private final static byte WHITE_SPACE_ENC = -5;

	private final static byte EQUALS_SIGN_ENC = -1;

	private final static byte[] _STANDARD_ALPHABET =
					{
									(byte) 'A', (byte) 'B', (byte) 'C', (byte) 'D', (byte) 'E', (byte) 'F', (byte) 'G',
									(byte) 'H', (byte) 'I', (byte) 'J', (byte) 'K', (byte) 'L', (byte) 'M', (byte) 'N',
									(byte) 'O', (byte) 'P', (byte) 'Q', (byte) 'R', (byte) 'S', (byte) 'T', (byte) 'U',
									(byte) 'V', (byte) 'W', (byte) 'X', (byte) 'Y', (byte) 'Z',
									(byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f', (byte) 'g',
									(byte) 'h', (byte) 'i', (byte) 'j', (byte) 'k', (byte) 'l', (byte) 'm', (byte) 'n',
									(byte) 'o', (byte) 'p', (byte) 'q', (byte) 'r', (byte) 's', (byte) 't', (byte) 'u',
									(byte) 'v', (byte) 'w', (byte) 'x', (byte) 'y', (byte) 'z',
									(byte) '0', (byte) '1', (byte) '2', (byte) '3', (byte) '4', (byte) '5',
									(byte) '6', (byte) '7', (byte) '8', (byte) '9', (byte) '+', (byte) '/'
					};

	private final static byte[] _STANDARD_DECODABET =
					{
									-9, -9, -9, -9, -9, -9, -9, -9, -9,                 // Decimal  0 -  8
									-5, -5,                                      // Whitespace: Tab and Linefeed
									-9, -9,                                      // Decimal 11 - 12
									-5,                                         // Whitespace: Carriage Return
									-9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9,     // Decimal 14 - 26
									-9, -9, -9, -9, -9,                             // Decimal 27 - 31
									-5,                                         // Whitespace: Space
									-9, -9, -9, -9, -9, -9, -9, -9, -9, -9,              // Decimal 33 - 42
									62,                                         // Plus sign at decimal 43
									-9, -9, -9,                                   // Decimal 44 - 46
									63,                                         // Slash at decimal 47
									52, 53, 54, 55, 56, 57, 58, 59, 60, 61,              // Numbers zero through nine
									-9, -9, -9,                                   // Decimal 58 - 60
									-1,                                         // Equals sign at decimal 61
									-9, -9, -9,                                      // Decimal 62 - 64
									0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,            // Letters 'A' through 'N'
									14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,        // Letters 'O' through 'Z'
									-9, -9, -9, -9, -9, -9,                          // Decimal 91 - 96
									26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38,     // Letters 'a' through 'm'
									39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51,     // Letters 'n' through 'z'
									-9, -9, -9, -9                                 // Decimal 123 - 126

					};

	private final static byte[] _URL_SAFE_ALPHABET =
					{
									(byte) 'A', (byte) 'B', (byte) 'C', (byte) 'D', (byte) 'E', (byte) 'F', (byte) 'G',
									(byte) 'H', (byte) 'I', (byte) 'J', (byte) 'K', (byte) 'L', (byte) 'M', (byte) 'N',
									(byte) 'O', (byte) 'P', (byte) 'Q', (byte) 'R', (byte) 'S', (byte) 'T', (byte) 'U',
									(byte) 'V', (byte) 'W', (byte) 'X', (byte) 'Y', (byte) 'Z',
									(byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f', (byte) 'g',
									(byte) 'h', (byte) 'i', (byte) 'j', (byte) 'k', (byte) 'l', (byte) 'm', (byte) 'n',
									(byte) 'o', (byte) 'p', (byte) 'q', (byte) 'r', (byte) 's', (byte) 't', (byte) 'u',
									(byte) 'v', (byte) 'w', (byte) 'x', (byte) 'y', (byte) 'z',
									(byte) '0', (byte) '1', (byte) '2', (byte) '3', (byte) '4', (byte) '5',
									(byte) '6', (byte) '7', (byte) '8', (byte) '9', (byte) '-', (byte) '_'
					};

	private final static byte[] _URL_SAFE_DECODABET =
					{
									-9, -9, -9, -9, -9, -9, -9, -9, -9,                 // Decimal  0 -  8
									-5, -5,                                      // Whitespace: Tab and Linefeed
									-9, -9,                                      // Decimal 11 - 12
									-5,                                         // Whitespace: Carriage Return
									-9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9,     // Decimal 14 - 26
									-9, -9, -9, -9, -9,                             // Decimal 27 - 31
									-5,                                         // Whitespace: Space
									-9, -9, -9, -9, -9, -9, -9, -9, -9, -9,              // Decimal 33 - 42
									-9,                                         // Plus sign at decimal 43
									-9,                                         // Decimal 44
									62,                                         // Minus sign at decimal 45
									-9,                                         // Decimal 46
									-9,                                         // Slash at decimal 47
									52, 53, 54, 55, 56, 57, 58, 59, 60, 61,              // Numbers zero through nine
									-9, -9, -9,                                   // Decimal 58 - 60
									-1,                                         // Equals sign at decimal 61
									-9, -9, -9,                                   // Decimal 62 - 64
									0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,            // Letters 'A' through 'N'
									14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,        // Letters 'O' through 'Z'
									-9, -9, -9, -9,                                // Decimal 91 - 94
									63,                                         // Underscore at decimal 95
									-9,                                         // Decimal 96
									26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38,     // Letters 'a' through 'm'
									39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51,     // Letters 'n' through 'z'
									-9, -9, -9, -9                                 // Decimal 123 - 126

					};

	private final static byte[] _ORDERED_ALPHABET =
					{
									(byte) '-',
									(byte) '0', (byte) '1', (byte) '2', (byte) '3', (byte) '4',
									(byte) '5', (byte) '6', (byte) '7', (byte) '8', (byte) '9',
									(byte) 'A', (byte) 'B', (byte) 'C', (byte) 'D', (byte) 'E', (byte) 'F', (byte) 'G',
									(byte) 'H', (byte) 'I', (byte) 'J', (byte) 'K', (byte) 'L', (byte) 'M', (byte) 'N',
									(byte) 'O', (byte) 'P', (byte) 'Q', (byte) 'R', (byte) 'S', (byte) 'T', (byte) 'U',
									(byte) 'V', (byte) 'W', (byte) 'X', (byte) 'Y', (byte) 'Z',
									(byte) '_',
									(byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f', (byte) 'g',
									(byte) 'h', (byte) 'i', (byte) 'j', (byte) 'k', (byte) 'l', (byte) 'm', (byte) 'n',
									(byte) 'o', (byte) 'p', (byte) 'q', (byte) 'r', (byte) 's', (byte) 't', (byte) 'u',
									(byte) 'v', (byte) 'w', (byte) 'x', (byte) 'y', (byte) 'z'
					};

	private final static byte[] _ORDERED_DECODABET =
					{
									-9, -9, -9, -9, -9, -9, -9, -9, -9,                 // Decimal  0 -  8
									-5, -5,                                      // Whitespace: Tab and Linefeed
									-9, -9,                                      // Decimal 11 - 12
									-5,                                         // Whitespace: Carriage Return
									-9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9,     // Decimal 14 - 26
									-9, -9, -9, -9, -9,                             // Decimal 27 - 31
									-5,                                         // Whitespace: Space
									-9, -9, -9, -9, -9, -9, -9, -9, -9, -9,              // Decimal 33 - 42
									-9,                                         // Plus sign at decimal 43
									-9,                                         // Decimal 44
									0,                                          // Minus sign at decimal 45
									-9,                                         // Decimal 46
									-9,                                         // Slash at decimal 47
									1, 2, 3, 4, 5, 6, 7, 8, 9, 10,                       // Numbers zero through nine
									-9, -9, -9,                                   // Decimal 58 - 60
									-1,                                         // Equals sign at decimal 61
									-9, -9, -9,                                   // Decimal 62 - 64
									11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,     // Letters 'A' through 'M'
									24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36,     // Letters 'N' through 'Z'
									-9, -9, -9, -9,                                // Decimal 91 - 94
									37,                                         // Underscore at decimal 95
									-9,                                         // Decimal 96
									38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50,     // Letters 'a' through 'm'
									51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63,     // Letters 'n' through 'z'
									-9, -9, -9, -9                                 // Decimal 123 - 126

					};

	private Base64 () {
	}

	private static byte[] getAlphabet ( int options ) {
		if ( ( options & URL_SAFE ) == URL_SAFE )
			return _URL_SAFE_ALPHABET;
		else if ( ( options & ORDERED ) == ORDERED )
			return _ORDERED_ALPHABET;
		else
			return _STANDARD_ALPHABET;

	}

	private static byte[] getDecodabet ( int options ) {
		if ( ( options & URL_SAFE ) == URL_SAFE )
			return _URL_SAFE_DECODABET;
		else if ( ( options & ORDERED ) == ORDERED )
			return _ORDERED_DECODABET;
		else
			return _STANDARD_DECODABET;
	}

	private static byte[] encode3to4 ( byte[] b4, byte[] threeBytes, int numSigBytes, int options ) {
		encode3to4 ( threeBytes, numSigBytes, b4, options );
		return b4;
	}

	private static void encode3to4 (
					byte[] source, int numSigBytes,
					byte[] destination, int options ) {
		byte[] ALPHABET = getAlphabet ( options );

		int inBuff = ( numSigBytes > 0 ? ( ( source[0] << 24 ) >>> 8 ) : 0 )
						| ( numSigBytes > 1 ? ( ( source[1] << 24 ) >>> 16 ) : 0 )
						| ( numSigBytes > 2 ? ( ( source[2] << 24 ) >>> 24 ) : 0 );

		switch ( numSigBytes ) {
		case 3:
			destination[0] = ALPHABET[( inBuff >>> 18 )];
			destination[1] = ALPHABET[( inBuff >>> 12 ) & 0x3f];
			destination[2] = ALPHABET[( inBuff >>> 6 ) & 0x3f];
			destination[3] = ALPHABET[( inBuff ) & 0x3f];
			return;

		case 2:
			destination[0] = ALPHABET[( inBuff >>> 18 )];
			destination[1] = ALPHABET[( inBuff >>> 12 ) & 0x3f];
			destination[2] = ALPHABET[( inBuff >>> 6 ) & 0x3f];
			destination[3] = EQUALS_SIGN;
			return;

		case 1:
			destination[0] = ALPHABET[( inBuff >>> 18 )];
			destination[1] = ALPHABET[( inBuff >>> 12 ) & 0x3f];
			destination[2] = EQUALS_SIGN;
			destination[3] = EQUALS_SIGN;
			return;

		default:
		}
	}

	private static int decode4to3 ( byte[] source, byte[] destination, int destOffset, int options ) {
		byte[] DECODABET = getDecodabet ( options );

		// Example: Dk==
		if ( source[2] == EQUALS_SIGN ) {
			int outBuff = ( ( DECODABET[source[0]] & 0xFF ) << 18 )
							| ( ( DECODABET[source[1]] & 0xFF ) << 12 );

			destination[destOffset] = (byte) ( outBuff >>> 16 );
			return 1;
		}

		// Example: DkL=
		else if ( source[3] == EQUALS_SIGN ) {
			int outBuff = ( ( DECODABET[source[0]] & 0xFF ) << 18 )
							| ( ( DECODABET[source[1]] & 0xFF ) << 12 )
							| ( ( DECODABET[source[2]] & 0xFF ) << 6 );

			destination[destOffset] = (byte) ( outBuff >>> 16 );
			destination[destOffset + 1] = (byte) ( outBuff >>> 8 );
			return 2;
		} else {
			try {
				int outBuff = ( ( DECODABET[source[0]] & 0xFF ) << 18 )
								| ( ( DECODABET[source[1]] & 0xFF ) << 12 )
								| ( ( DECODABET[source[2]] & 0xFF ) << 6 )
								| ( ( DECODABET[source[3]] & 0xFF ) );

				destination[destOffset] = (byte) ( outBuff >> 16 );
				destination[destOffset + 1] = (byte) ( outBuff >> 8 );
				destination[destOffset + 2] = (byte) ( outBuff );

				return 3;
			} catch ( Exception e ) {
				assert DECODABET != null;
				System.out.println ( source[0] + ": " + ( DECODABET[source[0]] ) );
				System.out.println ( source[1] + ": " + ( DECODABET[source[1]] ) );
				System.out.println ( source[2] + ": " + ( DECODABET[source[2]] ) );
				System.out.println ( source[3] + ": " + ( DECODABET[source[3]] ) );
				return -1;
			}   // end catch
		}
	}   // end decodeToBytes

	public static byte[] decode ( byte[] source, int off, int len, int options ) {
		byte[] DECODABET = getDecodabet ( options );

		int len34 = len * 3 / 4;
		byte[] outBuff = new byte[len34]; // Upper limit on size of output
		int outBuffPosn = 0;

		byte[] b4 = new byte[4];
		int b4Posn = 0;
		int i;
		byte sbiCrop;
		byte sbiDecode;
		for ( i = off; i < off + len; i++ ) {
			sbiCrop = (byte) ( source[i] & 0x7f ); // Only the low seven bits
			sbiDecode = DECODABET[sbiCrop];

			if ( sbiDecode >= WHITE_SPACE_ENC ) // White space, Equals sign or better
			{
				if ( sbiDecode >= EQUALS_SIGN_ENC ) {
					b4[b4Posn++] = sbiCrop;
					if ( b4Posn > 3 ) {
						outBuffPosn += decode4to3 ( b4, outBuff, outBuffPosn, options );
						b4Posn = 0;

						// If that was the equals sign, break out of 'for' loop
						if ( sbiCrop == EQUALS_SIGN )
							break;
					}   // end if: quartet built

				}   // end if: equals sign or better

			}   // end if: white space, equals sign or better
			else {
				System.err.println ( "Bad Base64 input character at " + i + ": " + source[i] + "(decimal)" );
				return null;
			}   // end else:
		}   // each input character

		byte[] out = new byte[outBuffPosn];
		System.arraycopy ( outBuff, 0, out, 0, outBuffPosn );
		return out;
	}   // end decode

	public static byte[] decode ( String s ) {
		return decode ( s, NO_OPTIONS );
	}

	public static byte[] decode ( String s, int options ) {
		byte[] bytes;
		try {
			bytes = s.getBytes ( PREFERRED_ENCODING );
		}   // end try
		catch ( java.io.UnsupportedEncodingException uee ) {
			bytes = s.getBytes ();
		}   // end catch
		//</change>

		// Decode
		bytes = decode ( bytes, 0, bytes.length, options );

		// Check to see if it's gzip-compressed
		// GZIP Magic Two-Byte Number: 0x8b1f (35615)
		if ( bytes != null && bytes.length >= 4 ) {

			int head = ( bytes[0] & 0xff ) | ( ( bytes[1] << 8 ) & 0xff00 );
			if ( java.util.zip.GZIPInputStream.GZIP_MAGIC == head ) {
				byte[] buffer = new byte[2048];
				int length;

				try ( java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream ();
								java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream ( bytes );
								java.util.zip.GZIPInputStream gzis = new java.util.zip.GZIPInputStream ( bais ) ) {

					while ( ( length = gzis.read ( buffer ) ) >= 0 ) {
						baos.write ( buffer, 0, length );
					}   // end while: reading input

					// No error? Get new bytes.
					bytes = baos.toByteArray ();

				} catch ( java.io.IOException e ) {
					// Just return originally-decoded bytes
				}

			}
		}

		return bytes;
	}

	public static class InputStream extends java.io.FilterInputStream {

		private final boolean encode;         // Encoding or decoding

		private final byte[] buffer;         // Small buffer holding converted data

		private final int bufferLength;   // Length of buffer (3 or 4)

		private final boolean breakLines;     // Break lines at less than 80 characters

		private final int options;        // Record options used to create the stream.

		private final byte[] decodabet;        // Local copies to avoid extra method calls

		private int position;       // Current position in the buffer

		private int numSigBytes;    // Number of meaningful bytes in the buffer

		private int lineLength;

		public InputStream ( java.io.InputStream in ) {
			this ( in, DECODE );
		}   // end constructor

		public InputStream ( java.io.InputStream in, int options ) {
			super ( in );
			this.breakLines = ( options & DONT_BREAK_LINES ) != DONT_BREAK_LINES;
			this.encode = ( options & ENCODE ) == ENCODE;
			this.bufferLength = encode ? 4 : 3;
			this.buffer = new byte[bufferLength];
			this.position = -1;
			this.lineLength = 0;
			this.options = options; // Record for later, mostly to determine which alphabet to use
			// Local copies to avoid extra method calls
			this.decodabet = getDecodabet ( options );
		}   // end constructor

		public int read () throws java.io.IOException {
			// Do we need to get data?
			if ( position < 0 ) {
				if ( encode ) {
					byte[] b3 = new byte[3];
					int numBinaryBytes = 0;
					for ( int i = 0; i < 3; i++ ) {
						try {
							int b = in.read ();

							// If end of stream, b is -1.
							if ( b >= 0 ) {
								b3[i] = (byte) b;
								numBinaryBytes++;
							}   // end if: not end of stream

						}   // end try: read
						catch ( java.io.IOException e ) {
							// Only a problem if we got no data at all.
							if ( i == 0 )
								throw e;

						}   // end catch
					}   // end for: each needed input byte

					if ( numBinaryBytes > 0 ) {
						encode3to4 ( b3, numBinaryBytes, buffer, options );
						position = 0;
						numSigBytes = 4;
					}   // end if: got data
					else {
						return -1;
					}   // end else
				}   // end if: encoding

				// Else decoding
				else {
					byte[] b4 = new byte[4];
					int i;
					for ( i = 0; i < 4; i++ ) {
						// Read four "meaningful" bytes:
						int b;
						do {
							b = in.read ();
						}
						while ( b >= 0 && decodabet[b & 0x7f] <= WHITE_SPACE_ENC );

						if ( b < 0 )
							break; // Reads a -1 if end of stream

						b4[i] = (byte) b;
					}   // end for: each needed input byte

					if ( i == 4 ) {
						numSigBytes = decode4to3 ( b4, buffer, 0, options );
						position = 0;
					}   // end if: got four characters
					else if ( i == 0 ) {
						return -1;
					}   // end else if: also padded correctly
					else {
						// Must have broken out from above.
						throw new java.io.IOException ( MessageLocalization.getComposedMessage ( "improperly.padded.base64.input" ) );
					}   // end

				}   // end else: decode
			}   // end else: get data

			// Got data?
			// End of relevant data?
			if ( /*!encode &&*/ position >= numSigBytes )
				return -1;

			if ( encode && breakLines && lineLength >= MAX_LINE_LENGTH ) {
				lineLength = 0;
				return '\n';
			}   // end if
			else {
				lineLength++;   // This isn't important when decoding
				// but throwing an extra "if" seems
				// just as wasteful.

				int b = buffer[position++];

				if ( position >= bufferLength )
					position = -1;

				return b & 0xFF; // This is how you "cast" a byte that's
				// intended to be unsigned.
			}   // end else
		}   // end read

		public int read ( byte[] dest, int off, int len ) throws java.io.IOException {
			int i;
			int b;
			for ( i = 0; i < len; i++ ) {
				b = read ();

				if ( b >= 0 )
					dest[off + i] = (byte) b;
				else if ( i == 0 )
					return -1;
				else
					break; // Out of 'for' loop
			}   // end for: each byte read
			return i;
		}   // end read

	}   // end inner class InputStream






	/* ********  I N N E R   C L A S S   O U T P U T S T R E A M  ******** */


	public static class OutputStream extends java.io.FilterOutputStream {

		private final boolean encode;

		private final int bufferLength;

		private final boolean breakLines;

		private final byte[] b4; // Scratch used in a few places

		private final boolean suspendEncoding;

		private final int options; // Record for later

		private final byte[] decodabet;        // Local copies to avoid extra method calls

		private int position;

		private byte[] buffer;

		private int lineLength;

		public OutputStream ( java.io.OutputStream out ) {
			this ( out, ENCODE );
		}   // end constructor

		public OutputStream ( java.io.OutputStream out, int options ) {
			super ( out );
			this.breakLines = ( options & DONT_BREAK_LINES ) != DONT_BREAK_LINES;
			this.encode = ( options & ENCODE ) == ENCODE;
			this.bufferLength = encode ? 3 : 4;
			this.buffer = new byte[bufferLength];
			this.position = 0;
			this.lineLength = 0;
			this.suspendEncoding = false;
			this.b4 = new byte[4];
			this.options = options;
			// Local copies to avoid extra method calls
			this.decodabet = getDecodabet ( options );
		}   // end constructor

		public void write ( int theByte ) throws java.io.IOException {
			// Encoding suspended?
			if ( suspendEncoding ) {
				super.out.write ( theByte );
				return;
			}   // end if: supsended

			// Encode?
			if ( encode ) {
				buffer[position++] = (byte) theByte;
				if ( position >= bufferLength )  // Enough to encode.
				{
					out.write ( encode3to4 ( b4, buffer, bufferLength, options ) );

					lineLength += 4;
					if ( breakLines && lineLength >= MAX_LINE_LENGTH ) {
						out.write ( NEW_LINE );
						lineLength = 0;
					}   // end if: end of line

					position = 0;
				}   // end if: enough to output
			}   // end if: encoding

			// Else, Decoding
			else {
				if ( decodabet[theByte & 0x7f] > WHITE_SPACE_ENC ) {
					buffer[position++] = (byte) theByte;
					if ( position >= bufferLength )  // Enough to output.
					{
						int len = Base64.decode4to3 ( buffer, b4, 0, options );
						out.write ( b4, 0, len );
						//out.write( Base64.decode4to3( buffer ) );
						position = 0;
					}   // end if: enough to output
				}   // end if: meaningful base64 character
				else if ( decodabet[theByte & 0x7f] != WHITE_SPACE_ENC ) {
					throw new java.io.IOException ( MessageLocalization.getComposedMessage ( "invalid.character.in.base64.data" ) );
				}   // end else: not white space either
			}   // end else: decoding
		}   // end write

		public void write ( byte[] theBytes, int off, int len ) throws java.io.IOException {
			// Encoding suspended?
			if ( suspendEncoding ) {
				super.out.write ( theBytes, off, len );
				return;
			}   // end if: supsended

			for ( int i = 0; i < len; i++ ) {
				write ( theBytes[off + i] );
			}

		}

		public void flushBase64 () throws java.io.IOException {
			if ( position > 0 ) {
				if ( encode ) {
					out.write ( encode3to4 ( b4, buffer, position, options ) );
					position = 0;
				} else {
					throw new java.io.IOException ( MessageLocalization.getComposedMessage ( "base64.input.not.properly.padded" ) );
				}   // end else: decoding
			}

		}   // end flush

		public void close () throws java.io.IOException {
			flushBase64 ();

			super.close ();

			buffer = null;
			out = null;
		}

	}

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.util.pdf.barcode;

import java.awt.*;
import java.awt.image.MemoryImageSource;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;


public class BarcodeDatamatrix {

	public static final int DM_NO_ERROR = 0;

	public static final int DM_ERROR_TEXT_TOO_BIG = 1;

	public static final int DM_ERROR_INVALID_SQUARE = 3;

	public static final int DM_ERROR_EXTENSION = 5;

	public static final int DM_AUTO = 0;

	public static final int DM_ASCII = 1;

	public static final int DM_C40 = 2;

	public static final int DM_TEXT = 3;

	public static final int DM_B256 = 4;

	public static final int DM_X21 = 5;

	public static final int DM_EDIFACT = 6;

	public static final int DM_RAW = 7;

	public static final int DM_EXTENSION = 32;

	public static final int DM_TEST = 64;

	private final static DmParams[] dmSizes = {
					new DmParams ( 10, 10, 10, 10, 3, 3, 5 ),
					new DmParams ( 12, 12, 12, 12, 5, 5, 7 ),
					new DmParams ( 8, 18, 8, 18, 5, 5, 7 ),
					new DmParams ( 14, 14, 14, 14, 8, 8, 10 ),
					new DmParams ( 8, 32, 8, 16, 10, 10, 11 ),
					new DmParams ( 16, 16, 16, 16, 12, 12, 12 ),
					new DmParams ( 12, 26, 12, 26, 16, 16, 14 ),
					new DmParams ( 18, 18, 18, 18, 18, 18, 14 ),
					new DmParams ( 20, 20, 20, 20, 22, 22, 18 ),
					new DmParams ( 12, 36, 12, 18, 22, 22, 18 ),
					new DmParams ( 22, 22, 22, 22, 30, 30, 20 ),
					new DmParams ( 16, 36, 16, 18, 32, 32, 24 ),
					new DmParams ( 24, 24, 24, 24, 36, 36, 24 ),
					new DmParams ( 26, 26, 26, 26, 44, 44, 28 ),
					new DmParams ( 16, 48, 16, 24, 49, 49, 28 ),
					new DmParams ( 32, 32, 16, 16, 62, 62, 36 ),
					new DmParams ( 36, 36, 18, 18, 86, 86, 42 ),
					new DmParams ( 40, 40, 20, 20, 114, 114, 48 ),
					new DmParams ( 44, 44, 22, 22, 144, 144, 56 ),
					new DmParams ( 48, 48, 24, 24, 174, 174, 68 ),
					new DmParams ( 52, 52, 26, 26, 204, 102, 42 ),
					new DmParams ( 64, 64, 16, 16, 280, 140, 56 ),
					new DmParams ( 72, 72, 18, 18, 368, 92, 36 ),
					new DmParams ( 80, 80, 20, 20, 456, 114, 48 ),
					new DmParams ( 88, 88, 22, 22, 576, 144, 56 ),
					new DmParams ( 96, 96, 24, 24, 696, 174, 68 ),
					new DmParams ( 104, 104, 26, 26, 816, 136, 56 ),
					new DmParams ( 120, 120, 20, 20, 1050, 175, 68 ),
					new DmParams ( 132, 132, 22, 22, 1304, 163, 62 ),
					new DmParams ( 144, 144, 24, 24, 1558, 156, 62 ) };

	private static final String x12 = "\r*> 0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private int extOut;

	private short[] place;

	private byte[] image;

	private int height;

	private int width;

	private int ws;

	private int options;

	public BarcodeDatamatrix () {
	}

	private void setBit ( int x, int y, int xByte ) {
		image[y * xByte + x / 8] |= (byte) ( 128 >> ( x & 7 ) );
	}

	private void draw ( byte[] data, DmParams dm ) {
		int i, j, p, x, y, xs, ys, z;
		int xByte = ( dm.width + ws * 2 + 7 ) / 8;
		Arrays.fill ( image, (byte) 0 );

		for ( i = ws; i < dm.height + ws; i += dm.heightSection ) {
			for ( j = ws; j < dm.width + ws; j += 2 ) {
				setBit ( j, i, xByte );
			}
		}
		for ( i = dm.heightSection - 1 + ws; i < dm.height + ws; i += dm.heightSection ) {
			for ( j = ws; j < dm.width + ws; ++j ) {
				setBit ( j, i, xByte );
			}
		}
		for ( i = ws; i < dm.width + ws; i += dm.widthSection ) {
			for ( j = ws; j < dm.height + ws; ++j ) {
				setBit ( i, j, xByte );
			}
		}
		for ( i = dm.widthSection - 1 + ws; i < dm.width + ws; i += dm.widthSection ) {
			for ( j = 1 + ws; j < dm.height + ws; j += 2 ) {
				setBit ( i, j, xByte );
			}
		}
		p = 0;
		for ( ys = 0; ys < dm.height; ys += dm.heightSection ) {
			for ( y = 1; y < dm.heightSection - 1; ++y ) {
				for ( xs = 0; xs < dm.width; xs += dm.widthSection ) {
					for ( x = 1; x < dm.widthSection - 1; ++x ) {
						z = place[p++];
						if ( z == 1 || ( z > 1 && ( ( data[z / 8 - 1] & 0xff ) & ( 128 >> ( z % 8 ) ) ) != 0 ) )
							setBit ( x + xs + ws, y + ys + ws, xByte );
					}
				}
			}
		}
	}

	private static void makePadding ( byte[] data, int position, int count ) {
		if ( count <= 0 )
			return;
		data[position++] = (byte) 129;
		while ( --count > 0 ) {
			int t = 129 + ( ( ( position + 1 ) * 149 ) % 253 ) + 1;
			if ( t > 254 )
				t -= 254;
			data[position++] = (byte) t;
		}
	}

	private static boolean isDigit ( int c ) {
		return c >= '0' && c <= '9';
	}

	private static int asciiEncodation ( byte[] text, int textOffset, int textLength, byte[] data, int dataOffset, int dataLength ) {
		int ptrIn, ptrOut, c;
		ptrIn = textOffset;
		ptrOut = dataOffset;
		textLength += textOffset;
		dataLength += dataOffset;
		while ( ptrIn < textLength ) {
			if ( ptrOut >= dataLength )
				return -1;
			c = text[ptrIn++] & 0xff;
			if ( isDigit ( c ) && ptrIn < textLength && isDigit ( text[ptrIn] & 0xff ) ) {
				data[ptrOut++] = (byte) ( ( c - '0' ) * 10 + ( text[ptrIn++] & 0xff ) - '0' + 130 );
			} else if ( c > 127 ) {
				if ( ptrOut + 1 >= dataLength )
					return -1;
				data[ptrOut++] = (byte) 235;
				data[ptrOut++] = (byte) ( c - 128 + 1 );
			} else {
				data[ptrOut++] = (byte) ( c + 1 );
			}
		}
		return ptrOut - dataOffset;
	}

	private static int b256Encodation ( byte[] text, int textOffset, int textLength, byte[] data, int dataOffset, int dataLength ) {
		int k, j, prn, tv, c;
		if ( textLength == 0 )
			return 0;
		if ( textLength < 250 && textLength + 2 > dataLength )
			return -1;
		if ( textLength >= 250 && textLength + 3 > dataLength )
			return -1;
		data[dataOffset] = (byte) 231;
		if ( textLength < 250 ) {
			data[dataOffset + 1] = (byte) textLength;
			k = 2;
		} else {
			data[dataOffset + 1] = (byte) ( textLength / 250 + 249 );
			data[dataOffset + 2] = (byte) ( textLength % 250 );
			k = 3;
		}
		System.arraycopy ( text, textOffset, data, k + dataOffset, textLength );
		k += textLength + dataOffset;
		for ( j = dataOffset + 1; j < k; ++j ) {
			c = data[j] & 0xff;
			prn = ( ( 149 * ( j + 1 ) ) % 255 ) + 1;
			tv = c + prn;
			if ( tv > 255 )
				tv -= 256;
			data[j] = (byte) tv;

		}
		return k - dataOffset;
	}

	private static int X12Encodation ( byte[] text, int textOffset, int textLength, byte[] data, int dataOffset, int dataLength ) {
		int ptrIn, ptrOut, count, k, n, ci;
		byte c;
		if ( textLength == 0 )
			return 0;
		ptrIn = 0;
		ptrOut = 0;
		byte[] x = new byte[textLength];
		count = 0;
		for ( ; ptrIn < textLength; ++ptrIn ) {
			int i = x12.indexOf ( (char) text[ptrIn + textOffset] );
			if ( i >= 0 ) {
				x[ptrIn] = (byte) i;
				++count;
			} else {
				x[ptrIn] = 100;
				if ( count >= 6 )
					count -= ( count / 3 ) * 3;
				for ( k = 0; k < count; ++k )
					x[ptrIn - k - 1] = 100;
				count = 0;
			}
		}
		if ( count >= 6 )
			count -= ( count / 3 ) * 3;
		for ( k = 0; k < count; ++k )
			x[ptrIn - k - 1] = 100;
		ptrIn = 0;
		for ( ; ptrIn < textLength; ++ptrIn ) {
			c = x[ptrIn];
			if ( ptrOut >= dataLength )
				break;
			if ( c < 40 ) {
				if ( ptrIn == 0 || x[ptrIn - 1] > 40 )
					data[dataOffset + ptrOut++] = (byte) 238;
				if ( ptrOut + 2 > dataLength )
					break;
				if ( x.length - 1 < ptrIn + 2 ) {
					break;
				}
				n = 1600 * x[ptrIn] + 40 * x[ptrIn + 1] + x[ptrIn + 2] + 1;
				data[dataOffset + ptrOut++] = (byte) ( n / 256 );
				data[dataOffset + ptrOut++] = (byte) n;
				ptrIn += 2;
			} else {
				if ( ptrIn > 0 && x[ptrIn - 1] < 40 )
					data[dataOffset + ptrOut++] = (byte) 254;
				ci = text[ptrIn + textOffset] & 0xff;
				if ( ci > 127 ) {
					data[dataOffset + ptrOut++] = (byte) 235;
					ci -= 128;
				}
				if ( ptrOut >= dataLength )
					break;
				data[dataOffset + ptrOut++] = (byte) ( ci + 1 );
			}
		}
		c = x[textLength - 1];
		if ( ptrIn != textLength || ( c < 40 && ptrOut >= dataLength ) )
			return -1;
		if ( c < 40 )
			data[dataOffset + ptrOut++] = (byte) ( 254 );
		return ptrOut;
	}

	private static int EdifactEncodation ( byte[] text, int textOffset, int textLength, byte[] data, int dataOffset, int dataLength ) {
		int ptrIn, ptrOut, edi, pedi, c;
		if ( textLength == 0 )
			return 0;
		ptrIn = 0;
		ptrOut = 0;
		edi = 0;
		pedi = 18;
		boolean ascii = true;
		for ( ; ptrIn < textLength; ++ptrIn ) {
			c = text[ptrIn + textOffset] & 0xff;
			if ( ( ( c & 0xe0 ) == 0x40 || ( c & 0xe0 ) == 0x20 ) && c != '_' ) {
				if ( ascii ) {
					if ( ptrOut + 1 > dataLength )
						break;
					data[dataOffset + ptrOut++] = (byte) 240;
					ascii = false;
				}
				c &= 0x3f;
				edi |= c << pedi;
				if ( pedi == 0 ) {
					if ( ptrOut + 3 > dataLength )
						break;
					data[dataOffset + ptrOut++] = (byte) ( edi >> 16 );
					data[dataOffset + ptrOut++] = (byte) ( edi >> 8 );
					data[dataOffset + ptrOut++] = (byte) edi;
					edi = 0;
					pedi = 18;
				} else
					pedi -= 6;
			} else {
				if ( !ascii ) {
					edi |= ( '_' & 0x3f ) << pedi;
					if ( ptrOut + ( 3 - pedi / 8 ) > dataLength )
						break;
					data[dataOffset + ptrOut++] = (byte) ( edi >> 16 );
					if ( pedi <= 12 )
						data[dataOffset + ptrOut++] = (byte) ( edi >> 8 );
					if ( pedi <= 6 )
						data[dataOffset + ptrOut++] = (byte) edi;
					ascii = true;
					pedi = 18;
					edi = 0;
				}
				if ( c > 127 ) {
					if ( ptrOut >= dataLength )
						break;
					data[dataOffset + ptrOut++] = (byte) 235;
					c -= 128;
				}
				if ( ptrOut >= dataLength )
					break;
				data[dataOffset + ptrOut++] = (byte) ( c + 1 );
			}
		}
		if ( ptrIn != textLength )
			return -1;
		if ( !ascii ) {
			edi |= ( '_' & 0x3f ) << pedi;
			if ( ptrOut + ( 3 - pedi / 8 ) > dataLength )
				return -1;
			data[dataOffset + ptrOut++] = (byte) ( edi >> 16 );
			if ( pedi <= 12 )
				data[dataOffset + ptrOut++] = (byte) ( edi >> 8 );
			if ( pedi <= 6 )
				data[dataOffset + ptrOut++] = (byte) edi;
		}
		return ptrOut;
	}

	private static int C40OrTextEncodation ( byte[] text, int textOffset, int textLength, byte[] data, int dataOffset, int dataLength, boolean c40 ) {
		int ptrIn, ptrOut, encPtr, last0, last1, i, a, c;
		String basic, shift2, shift3;
		if ( textLength == 0 )
			return 0;
		ptrIn = 0;
		ptrOut = 0;
		if ( c40 )
			data[dataOffset + ptrOut++] = (byte) 230;
		else
			data[dataOffset + ptrOut++] = (byte) 239;
		shift2 = "!\"#$%&'()*+,-./:;<=>?@[\\]^_";
		if ( c40 ) {
			basic = " 0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			shift3 = "`abcdefghijklmnopqrstuvwxyz{|}~\177";
		} else {
			basic = " 0123456789abcdefghijklmnopqrstuvwxyz";
			shift3 = "`ABCDEFGHIJKLMNOPQRSTUVWXYZ{|}~\177";
		}
		int[] enc = new int[textLength * 4 + 10];
		encPtr = 0;
		last0 = 0;
		last1 = 0;
		while ( ptrIn < textLength ) {
			if ( ( encPtr % 3 ) == 0 ) {
				last0 = ptrIn;
				last1 = encPtr;
			}
			c = text[textOffset + ptrIn++] & 0xff;
			if ( c > 127 ) {
				c -= 128;
				enc[encPtr++] = 1;
				enc[encPtr++] = 30;
			}
			int idx = basic.indexOf ( (char) c );
			if ( idx >= 0 ) {
				enc[encPtr++] = idx + 3;
			} else if ( c < 32 ) {
				enc[encPtr++] = 0;
				enc[encPtr++] = c;
			} else if ( ( idx = shift2.indexOf ( (char) c ) ) >= 0 ) {
				enc[encPtr++] = 1;
				enc[encPtr++] = idx;
			} else if ( ( idx = shift3.indexOf ( (char) c ) ) >= 0 ) {
				enc[encPtr++] = 2;
				enc[encPtr++] = idx;
			}
		}
		if ( ( encPtr % 3 ) != 0 ) {
			ptrIn = last0;
			encPtr = last1;
		}
		if ( encPtr / 3 * 2 > dataLength - 2 ) {
			return -1;
		}
		i = 0;
		for ( ; i < encPtr; i += 3 ) {
			a = 1600 * enc[i] + 40 * enc[i + 1] + enc[i + 2] + 1;
			data[dataOffset + ptrOut++] = (byte) ( a / 256 );
			data[dataOffset + ptrOut++] = (byte) a;
		}
		data[ptrOut++] = (byte) 254;
		i = asciiEncodation ( text, ptrIn, textLength - ptrIn, data, ptrOut, dataLength - ptrOut );
		if ( i < 0 )
			return i;
		return ptrOut + i;
	}

	private static int getEncodation ( byte[] text, int textOffset, int textSize, byte[] data, int dataOffset, int dataSize, int options, boolean firstMatch ) {
		int e, j, k;
		int[] e1 = new int[6];
		if ( dataSize < 0 )
			return -1;
		options &= 7;
		if ( options == 0 ) {
			e1[0] = asciiEncodation ( text, textOffset, textSize, data, dataOffset, dataSize );
			if ( firstMatch && e1[0] >= 0 )
				return e1[0];
			e1[1] = C40OrTextEncodation ( text, textOffset, textSize, data, dataOffset, dataSize, false );
			if ( firstMatch && e1[1] >= 0 )
				return e1[1];
			e1[2] = C40OrTextEncodation ( text, textOffset, textSize, data, dataOffset, dataSize, true );
			if ( firstMatch && e1[2] >= 0 )
				return e1[2];
			e1[3] = b256Encodation ( text, textOffset, textSize, data, dataOffset, dataSize );
			if ( firstMatch && e1[3] >= 0 )
				return e1[3];
			e1[4] = X12Encodation ( text, textOffset, textSize, data, dataOffset, dataSize );
			if ( firstMatch && e1[4] >= 0 )
				return e1[4];
			e1[5] = EdifactEncodation ( text, textOffset, textSize, data, dataOffset, dataSize );
			if ( firstMatch && e1[5] >= 0 )
				return e1[5];
			if ( e1[0] < 0 && e1[1] < 0 && e1[2] < 0 && e1[3] < 0 && e1[4] < 0 && e1[5] < 0 ) {
				return -1;
			}
			j = 0;
			e = 99999;
			for ( k = 0; k < 6; ++k ) {
				if ( e1[k] >= 0 && e1[k] < e ) {
					e = e1[k];
					j = k;
				}
			}
			if ( j == 0 )
				e = asciiEncodation ( text, textOffset, textSize, data, dataOffset, dataSize );
			else if ( j == 1 )
				e = C40OrTextEncodation ( text, textOffset, textSize, data, dataOffset, dataSize, false );
			else if ( j == 2 )
				e = C40OrTextEncodation ( text, textOffset, textSize, data, dataOffset, dataSize, true );
			else if ( j == 3 )
				e = b256Encodation ( text, textOffset, textSize, data, dataOffset, dataSize );
			else if ( j == 4 )
				e = X12Encodation ( text, textOffset, textSize, data, dataOffset, dataSize );
			return e;
		}
		switch ( options ) {
		case DM_ASCII:
			return asciiEncodation ( text, textOffset, textSize, data, dataOffset, dataSize );
		case DM_C40:
			return C40OrTextEncodation ( text, textOffset, textSize, data, dataOffset, dataSize, true );
		case DM_TEXT:
			return C40OrTextEncodation ( text, textOffset, textSize, data, dataOffset, dataSize, false );
		case DM_B256:
			return b256Encodation ( text, textOffset, textSize, data, dataOffset, dataSize );
		case DM_X21:
			return X12Encodation ( text, textOffset, textSize, data, dataOffset, dataSize );
		case DM_EDIFACT:
			return EdifactEncodation ( text, textOffset, textSize, data, dataOffset, dataSize );
		case DM_RAW:
			if ( textSize > dataSize )
				return -1;
			System.arraycopy ( text, textOffset, data, dataOffset, textSize );
			return textSize;
		}
		return -1;
	}

	private static int getNumber ( byte[] text, int ptrIn, int n ) {
		int v, j, c;
		v = 0;
		for ( j = 0; j < n; ++j ) {
			c = text[ptrIn++] & 0xff;
			if ( c < '0' || c > '9' )
				return -1;
			v = v * 10 + c - '0';
		}
		return v;
	}

	private int processExtensions ( byte[] text, int textOffset, int textSize, byte[] data ) {
		int order, ptrIn, ptrOut, eci, fn, ft, fi, c;
		if ( ( options & DM_EXTENSION ) == 0 )
			return 0;
		order = 0;
		ptrIn = 0;
		ptrOut = 0;
		while ( ptrIn < textSize ) {
			if ( order > 20 )
				return -1;
			c = text[textOffset + ptrIn++] & 0xff;
			++order;
			switch ( c ) {
			case '.':
				extOut = ptrIn;
				return ptrOut;
			case 'e':
				if ( ptrIn + 6 > textSize )
					return -1;
				eci = getNumber ( text, textOffset + ptrIn, 6 );
				if ( eci < 0 )
					return -1;
				ptrIn += 6;
				data[ptrOut++] = (byte) 241;
				if ( eci < 127 )
					data[ptrOut++] = (byte) ( eci + 1 );
				else if ( eci < 16383 ) {
					data[ptrOut++] = (byte) ( ( eci - 127 ) / 254 + 128 );
					data[ptrOut++] = (byte) ( ( ( eci - 127 ) % 254 ) + 1 );
				} else {
					data[ptrOut++] = (byte) ( ( eci - 16383 ) / 64516 + 192 );
					data[ptrOut++] = (byte) ( ( ( ( eci - 16383 ) / 254 ) % 254 ) + 1 );
					data[ptrOut++] = (byte) ( ( ( eci - 16383 ) % 254 ) + 1 );
				}
				break;
			case 's':
				if ( order != 1 )
					return -1;
				if ( ptrIn + 9 > textSize )
					return -1;
				fn = getNumber ( text, textOffset + ptrIn, 2 );
				if ( fn <= 0 || fn > 16 )
					return -1;
				ptrIn += 2;
				ft = getNumber ( text, textOffset + ptrIn, 2 );
				if ( ft <= 1 || ft > 16 )
					return -1;
				ptrIn += 2;
				fi = getNumber ( text, textOffset + ptrIn, 5 );
				if ( fi < 0 )
					return -1;
				ptrIn += 5;
				data[ptrOut++] = (byte) ( 233 );
				data[ptrOut++] = (byte) ( ( ( fn - 1 ) << 4 ) | ( 17 - ft ) );
				data[ptrOut++] = (byte) ( fi / 254 + 1 );
				data[ptrOut++] = (byte) ( ( fi % 254 ) + 1 );
				break;
			case 'p':
				if ( order != 1 )
					return -1;
				data[ptrOut++] = (byte) ( 234 );
				break;
			case 'm':
				if ( order != 1 )
					return -1;
				if ( ptrIn + 1 > textSize )
					return -1;
				c = text[textOffset + ptrIn++] & 0xff;
				if ( c != '5' )
					return -1;
				data[ptrOut++] = (byte) ( 234 );
				data[ptrOut++] = (byte) 236;
				break;
			case 'f':
				if ( order != 1 && ( order != 2 || ( text[textOffset] != 's' && text[textOffset] != 'm' ) ) )
					return -1;
				data[ptrOut++] = (byte) ( 232 );
			}
		}
		return -1;
	}

	public void generate ( String text ) {
		byte[] t = text.getBytes ( StandardCharsets.ISO_8859_1 );
		generate ( t, 0, t.length );
	}

	public void generate ( byte[] text, int textOffset, int textSize ) {
		int extCount, e, k;
		DmParams dm, last;
		byte[] data = new byte[2500];
		extOut = 0;
		extCount = processExtensions ( text, textOffset, textSize, data );
		if ( extCount < 0 ) {
			return;
		}
		if ( height == 0 || width == 0 ) {
			last = dmSizes[dmSizes.length - 1];
			e = getEncodation ( text, textOffset + extOut, textSize - extOut, data, extCount, last.dataSize - extCount, options, false );
			if ( e < 0 ) {
				return;
			}
			e += extCount;
			for ( k = 0; k < dmSizes.length; ++k ) {
				if ( dmSizes[k].dataSize >= e )
					break;
			}
			dm = dmSizes[k];
			height = dm.height;
			width = dm.width;
		} else {
			for ( k = 0; k < dmSizes.length; ++k ) {
				if ( height == dmSizes[k].height && width == dmSizes[k].width )
					break;
			}
			if ( k == dmSizes.length ) {
				return;
			}
			dm = dmSizes[k];
			e = getEncodation ( text, textOffset + extOut, textSize - extOut, data, extCount, dm.dataSize - extCount, options, true );
			if ( e < 0 ) {
				return;
			}
			e += extCount;
		}
		if ( ( options & DM_TEST ) != 0 ) {
			return;
		}
		image = new byte[( ( ( dm.width + 2 * ws ) + 7 ) / 8 ) * ( dm.height + 2 * ws )];
		makePadding ( data, e, dm.dataSize - e );
		place = Placement.doPlacement ( dm.height - ( dm.height / dm.heightSection * 2 ), dm.width - ( dm.width / dm.widthSection * 2 ) );
		ReedSolomon.generateECC ( data, dm.dataSize, dm.dataBlock, dm.errorBlock );
		draw ( data, dm );
	}

	public java.awt.Image createAwtImage ( Color foreground, Color background ) {
		if ( image == null )
			return null;
		int f = foreground.getRGB ();
		int g = background.getRGB ();
		Canvas canvas = new Canvas ();

		int w = width + 2 * ws;
		int h = height + 2 * ws;
		int[] pix = new int[w * h];
		int stride = ( w + 7 ) / 8;
		int ptr = 0;
		for ( int k = 0; k < h; ++k ) {
			int p = k * stride;
			for ( int j = 0; j < w; ++j ) {
				int b = image[p + ( j / 8 )] & 0xff;
				b <<= j % 8;
				pix[ptr++] = ( b & 0x80 ) == 0 ? g : f;
			}
		}
		return canvas.createImage ( new MemoryImageSource ( w, h, pix, 0, w ) );
	}

	private static class DmParams {

		DmParams ( int height, int width, int heightSection, int widthSection, int dataSize, int dataBlock, int errorBlock ) {
			this.height = height;
			this.width = width;
			this.heightSection = heightSection;
			this.widthSection = widthSection;
			this.dataSize = dataSize;
			this.dataBlock = dataBlock;
			this.errorBlock = errorBlock;
		}

		int height;

		int width;

		int heightSection;

		int widthSection;

		int dataSize;

		int dataBlock;

		int errorBlock;
	}

	public void setHeight ( int height ) {
		this.height = height;
	}

	public int getWidth () {
		return width;
	}

	public void setWidth ( int width ) {
		this.width = width;
	}

	public int getWs () {
		return ws;
	}

	public void setWs ( int ws ) {
		this.ws = ws;
	}

	static class Placement {

		private int nrow;

		private int ncol;

		private short[] array;

		private static final Map<Integer, short[]> cache = new Hashtable<> ();

		private Placement () {
		}

		static short[] doPlacement ( int nrow, int ncol ) {
			Integer key = nrow * 1000 + ncol;
			short[] pc = cache.get ( key );
			if ( pc != null )
				return pc;
			Placement p = new Placement ();
			p.nrow = nrow;
			p.ncol = ncol;
			p.array = new short[nrow * ncol];
			p.ecc200 ();
			cache.put ( key, p.array );
			return p.array;
		}

		private void module ( int row, int col, int chr, int bit ) {
			if ( row < 0 ) {
				row += nrow;
				col += 4 - ( ( nrow + 4 ) % 8 );
			}
			if ( col < 0 ) {
				col += ncol;
				row += 4 - ( ( ncol + 4 ) % 8 );
			}
			array[row * ncol + col] = (short) ( 8 * chr + bit );
		}

		private void utah ( int row, int col, int chr ) {
			module ( row - 2, col - 2, chr, 0 );
			module ( row - 2, col - 1, chr, 1 );
			module ( row - 1, col - 2, chr, 2 );
			module ( row - 1, col - 1, chr, 3 );
			module ( row - 1, col, chr, 4 );
			module ( row, col - 2, chr, 5 );
			module ( row, col - 1, chr, 6 );
			module ( row, col, chr, 7 );
		}

		private void corner1 ( int chr ) {
			module ( nrow - 1, 0, chr, 0 );
			module ( nrow - 1, 1, chr, 1 );
			module ( nrow - 1, 2, chr, 2 );
			module ( 0, ncol - 2, chr, 3 );
			module ( 0, ncol - 1, chr, 4 );
			module ( 1, ncol - 1, chr, 5 );
			module ( 2, ncol - 1, chr, 6 );
			module ( 3, ncol - 1, chr, 7 );
		}

		private void corner2 ( int chr ) {
			module ( nrow - 3, 0, chr, 0 );
			module ( nrow - 2, 0, chr, 1 );
			module ( nrow - 1, 0, chr, 2 );
			module ( 0, ncol - 4, chr, 3 );
			module ( 0, ncol - 3, chr, 4 );
			module ( 0, ncol - 2, chr, 5 );
			module ( 0, ncol - 1, chr, 6 );
			module ( 1, ncol - 1, chr, 7 );
		}

		private void corner3 ( int chr ) {
			module ( nrow - 3, 0, chr, 0 );
			module ( nrow - 2, 0, chr, 1 );
			module ( nrow - 1, 0, chr, 2 );
			module ( 0, ncol - 2, chr, 3 );
			module ( 0, ncol - 1, chr, 4 );
			module ( 1, ncol - 1, chr, 5 );
			module ( 2, ncol - 1, chr, 6 );
			module ( 3, ncol - 1, chr, 7 );
		}

		private void corner4 ( int chr ) {
			module ( nrow - 1, 0, chr, 0 );
			module ( nrow - 1, ncol - 1, chr, 1 );
			module ( 0, ncol - 3, chr, 2 );
			module ( 0, ncol - 2, chr, 3 );
			module ( 0, ncol - 1, chr, 4 );
			module ( 1, ncol - 3, chr, 5 );
			module ( 1, ncol - 2, chr, 6 );
			module ( 1, ncol - 1, chr, 7 );
		}

		private void ecc200 () {
			int row, col, chr;
			Arrays.fill ( array, (short) 0 );
			chr = 1;
			row = 4;
			col = 0;
			do {
				if ( ( row == nrow ) && ( col == 0 ) )
					corner1 ( chr++ );
				if ( ( row == nrow - 2 ) && ( col == 0 ) && ( ncol % 4 != 0 ) )
					corner2 ( chr++ );
				if ( ( row == nrow - 2 ) && ( col == 0 ) && ( ncol % 8 == 4 ) )
					corner3 ( chr++ );
				if ( ( row == nrow + 4 ) && ( col == 2 ) && ( ncol % 8 == 0 ) )
					corner4 ( chr++ );
				do {
					if ( ( row < nrow ) && ( col >= 0 ) && array[row * ncol + col] == 0 )
						utah ( row, col, chr++ );
					row -= 2;
					col += 2;
				} while ( ( row >= 0 ) && ( col < ncol ) );
				row += 1;
				col += 3;

				do {
					if ( ( row >= 0 ) && ( col < ncol ) && array[row * ncol + col] == 0 )
						utah ( row, col, chr++ );
					row += 2;
					col -= 2;
				} while ( ( row < nrow ) && ( col >= 0 ) );
				row += 3;
				col += 1;
			} while ( ( row < nrow ) || ( col < ncol ) );
			if ( array[nrow * ncol - 1] == 0 ) {
				array[nrow * ncol - 1] = array[nrow * ncol - ncol - 2] = 1;
			}
		}
	}


	static class ReedSolomon {

		private static final int[] log = {
						0, 255, 1, 240, 2, 225, 241, 53, 3, 38, 226, 133, 242, 43, 54, 210,
						4, 195, 39, 114, 227, 106, 134, 28, 243, 140, 44, 23, 55, 118, 211, 234,
						5, 219, 196, 96, 40, 222, 115, 103, 228, 78, 107, 125, 135, 8, 29, 162,
						244, 186, 141, 180, 45, 99, 24, 49, 56, 13, 119, 153, 212, 199, 235, 91,
						6, 76, 220, 217, 197, 11, 97, 184, 41, 36, 223, 253, 116, 138, 104, 193,
						229, 86, 79, 171, 108, 165, 126, 145, 136, 34, 9, 74, 30, 32, 163, 84,
						245, 173, 187, 204, 142, 81, 181, 190, 46, 88, 100, 159, 25, 231, 50, 207,
						57, 147, 14, 67, 120, 128, 154, 248, 213, 167, 200, 63, 236, 110, 92, 176,
						7, 161, 77, 124, 221, 102, 218, 95, 198, 90, 12, 152, 98, 48, 185, 179,
						42, 209, 37, 132, 224, 52, 254, 239, 117, 233, 139, 22, 105, 27, 194, 113,
						230, 206, 87, 158, 80, 189, 172, 203, 109, 175, 166, 62, 127, 247, 146, 66,
						137, 192, 35, 252, 10, 183, 75, 216, 31, 83, 33, 73, 164, 144, 85, 170,
						246, 65, 174, 61, 188, 202, 205, 157, 143, 169, 82, 72, 182, 215, 191, 251,
						47, 178, 89, 151, 101, 94, 160, 123, 26, 112, 232, 21, 51, 238, 208, 131,
						58, 69, 148, 18, 15, 16, 68, 17, 121, 149, 129, 19, 155, 59, 249, 70,
						214, 250, 168, 71, 201, 156, 64, 60, 237, 130, 111, 20, 93, 122, 177, 150
		};

		private static final int[] alog = {
						1, 2, 4, 8, 16, 32, 64, 128, 45, 90, 180, 69, 138, 57, 114, 228,
						229, 231, 227, 235, 251, 219, 155, 27, 54, 108, 216, 157, 23, 46, 92, 184,
						93, 186, 89, 178, 73, 146, 9, 18, 36, 72, 144, 13, 26, 52, 104, 208,
						141, 55, 110, 220, 149, 7, 14, 28, 56, 112, 224, 237, 247, 195, 171, 123,
						246, 193, 175, 115, 230, 225, 239, 243, 203, 187, 91, 182, 65, 130, 41, 82,
						164, 101, 202, 185, 95, 190, 81, 162, 105, 210, 137, 63, 126, 252, 213, 135,
						35, 70, 140, 53, 106, 212, 133, 39, 78, 156, 21, 42, 84, 168, 125, 250,
						217, 159, 19, 38, 76, 152, 29, 58, 116, 232, 253, 215, 131, 43, 86, 172,
						117, 234, 249, 223, 147, 11, 22, 44, 88, 176, 77, 154, 25, 50, 100, 200,
						189, 87, 174, 113, 226, 233, 255, 211, 139, 59, 118, 236, 245, 199, 163, 107,
						214, 129, 47, 94, 188, 85, 170, 121, 242, 201, 191, 83, 166, 97, 194, 169,
						127, 254, 209, 143, 51, 102, 204, 181, 71, 142, 49, 98, 196, 165, 103, 206,
						177, 79, 158, 17, 34, 68, 136, 61, 122, 244, 197, 167, 99, 198, 161, 111,
						222, 145, 15, 30, 60, 120, 240, 205, 183, 67, 134, 33, 66, 132, 37, 74,
						148, 5, 10, 20, 40, 80, 160, 109, 218, 153, 31, 62, 124, 248, 221, 151,
						3, 6, 12, 24, 48, 96, 192, 173, 119, 238, 241, 207, 179, 75, 150, 1
		};

		private static final int[] poly5 = {
						228, 48, 15, 111, 62
		};

		private static final int[] poly7 = {
						23, 68, 144, 134, 240, 92, 254
		};

		private static final int[] poly10 = {
						28, 24, 185, 166, 223, 248, 116, 255, 110, 61
		};

		private static final int[] poly11 = {
						175, 138, 205, 12, 194, 168, 39, 245, 60, 97, 120
		};

		private static final int[] poly12 = {
						41, 153, 158, 91, 61, 42, 142, 213, 97, 178, 100, 242
		};

		private static final int[] poly14 = {
						156, 97, 192, 252, 95, 9, 157, 119, 138, 45, 18, 186, 83, 185
		};

		private static final int[] poly18 = {
						83, 195, 100, 39, 188, 75, 66, 61, 241, 213, 109, 129, 94, 254, 225, 48,
						90, 188
		};

		private static final int[] poly20 = {
						15, 195, 244, 9, 233, 71, 168, 2, 188, 160, 153, 145, 253, 79, 108, 82,
						27, 174, 186, 172
		};

		private static final int[] poly24 = {
						52, 190, 88, 205, 109, 39, 176, 21, 155, 197, 251, 223, 155, 21, 5, 172,
						254, 124, 12, 181, 184, 96, 50, 193
		};

		private static final int[] poly28 = {
						211, 231, 43, 97, 71, 96, 103, 174, 37, 151, 170, 53, 75, 34, 249, 121,
						17, 138, 110, 213, 141, 136, 120, 151, 233, 168, 93, 255
		};

		private static final int[] poly36 = {
						245, 127, 242, 218, 130, 250, 162, 181, 102, 120, 84, 179, 220, 251, 80, 182,
						229, 18, 2, 4, 68, 33, 101, 137, 95, 119, 115, 44, 175, 184, 59, 25,
						225, 98, 81, 112
		};

		private static final int[] poly42 = {
						77, 193, 137, 31, 19, 38, 22, 153, 247, 105, 122, 2, 245, 133, 242, 8,
						175, 95, 100, 9, 167, 105, 214, 111, 57, 121, 21, 1, 253, 57, 54, 101,
						248, 202, 69, 50, 150, 177, 226, 5, 9, 5
		};

		private static final int[] poly48 = {
						245, 132, 172, 223, 96, 32, 117, 22, 238, 133, 238, 231, 205, 188, 237, 87,
						191, 106, 16, 147, 118, 23, 37, 90, 170, 205, 131, 88, 120, 100, 66, 138,
						186, 240, 82, 44, 176, 87, 187, 147, 160, 175, 69, 213, 92, 253, 225, 19
		};

		private static final int[] poly56 = {
						175, 9, 223, 238, 12, 17, 220, 208, 100, 29, 175, 170, 230, 192, 215, 235,
						150, 159, 36, 223, 38, 200, 132, 54, 228, 146, 218, 234, 117, 203, 29, 232,
						144, 238, 22, 150, 201, 117, 62, 207, 164, 13, 137, 245, 127, 67, 247, 28,
						155, 43, 203, 107, 233, 53, 143, 46
		};

		private static final int[] poly62 = {
						242, 93, 169, 50, 144, 210, 39, 118, 202, 188, 201, 189, 143, 108, 196, 37,
						185, 112, 134, 230, 245, 63, 197, 190, 250, 106, 185, 221, 175, 64, 114, 71,
						161, 44, 147, 6, 27, 218, 51, 63, 87, 10, 40, 130, 188, 17, 163, 31,
						176, 170, 4, 107, 232, 7, 94, 166, 224, 124, 86, 47, 11, 204
		};

		private static final int[] poly68 = {
						220, 228, 173, 89, 251, 149, 159, 56, 89, 33, 147, 244, 154, 36, 73, 127,
						213, 136, 248, 180, 234, 197, 158, 177, 68, 122, 93, 213, 15, 160, 227, 236,
						66, 139, 153, 185, 202, 167, 179, 25, 220, 232, 96, 210, 231, 136, 223, 239,
						181, 241, 59, 52, 172, 25, 49, 232, 211, 189, 64, 54, 108, 153, 132, 63,
						96, 103, 82, 186
		};

		private static int[] getPoly ( int nc ) {
			switch ( nc ) {
			case 5:
				return poly5;
			case 7:
				return poly7;
			case 10:
				return poly10;
			case 11:
				return poly11;
			case 12:
				return poly12;
			case 14:
				return poly14;
			case 18:
				return poly18;
			case 20:
				return poly20;
			case 24:
				return poly24;
			case 28:
				return poly28;
			case 36:
				return poly36;
			case 42:
				return poly42;
			case 48:
				return poly48;
			case 56:
				return poly56;
			case 62:
				return poly62;
			case 68:
				return poly68;
			}
			return null;
		}

		private static void reedSolomonBlock ( byte[] wd, int nd, byte[] ncout, int nc, int[] c ) {
			int i, j, k;

			for ( i = 0; i <= nc; i++ )
				ncout[i] = 0;
			for ( i = 0; i < nd; i++ ) {
				k = ( ncout[0] ^ wd[i] ) & 0xff;
				for ( j = 0; j < nc; j++ ) {
					ncout[j] = (byte) ( ncout[j + 1] ^ ( k == 0 ? 0 : (byte) alog[( log[k] + log[c[nc - j - 1]] ) % ( 255 )] ) );
				}
			}
		}

		static void generateECC ( byte[] wd, int nd, int datablock, int nc ) {
			int blocks = ( nd + 2 ) / datablock;
			int b;
			byte[] buf = new byte[256];
			byte[] ecc = new byte[256];
			int[] c = getPoly ( nc );
			for ( b = 0; b < blocks; b++ ) {
				int n, p = 0;
				for ( n = b; n < nd; n += blocks )
					buf[p++] = wd[n];
				reedSolomonBlock ( buf, p, ecc, nc, c );
				p = 0;
				for ( n = b; n < nc * blocks; n += blocks )
					wd[nd + n] = ecc[p++];
			}
		}

	}
}
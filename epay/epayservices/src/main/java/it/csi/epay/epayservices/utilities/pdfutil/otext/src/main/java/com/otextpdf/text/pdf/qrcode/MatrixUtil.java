/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.qrcode;

public final class MatrixUtil {

	private static final int[][] POSITION_DETECTION_PATTERN = {
					{ 1, 1, 1, 1, 1, 1, 1 },
					{ 1, 0, 0, 0, 0, 0, 1 },
					{ 1, 0, 1, 1, 1, 0, 1 },
					{ 1, 0, 1, 1, 1, 0, 1 },
					{ 1, 0, 1, 1, 1, 0, 1 },
					{ 1, 0, 0, 0, 0, 0, 1 },
					{ 1, 1, 1, 1, 1, 1, 1 },
	};

	private static final int[][] HORIZONTAL_SEPARATION_PATTERN = {
					{ 0, 0, 0, 0, 0, 0, 0, 0 },
	};

	private static final int[][] VERTICAL_SEPARATION_PATTERN = {
					{ 0 }, { 0 }, { 0 }, { 0 }, { 0 }, { 0 }, { 0 },
	};

	private static final int[][] POSITION_ADJUSTMENT_PATTERN = {
					{ 1, 1, 1, 1, 1 },
					{ 1, 0, 0, 0, 1 },
					{ 1, 0, 1, 0, 1 },
					{ 1, 0, 0, 0, 1 },
					{ 1, 1, 1, 1, 1 },
	};

	// From Appendix E. Table 1, JIS0510X:2004 (p 71). The table was double-checked by komatsu.
	private static final int[][] POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE = {
					{ -1, -1, -1, -1, -1, -1, -1 },  // Version 1
					{ 6, 18, -1, -1, -1, -1, -1 },  // Version 2
					{ 6, 22, -1, -1, -1, -1, -1 },  // Version 3
					{ 6, 26, -1, -1, -1, -1, -1 },  // Version 4
					{ 6, 30, -1, -1, -1, -1, -1 },  // Version 5
					{ 6, 34, -1, -1, -1, -1, -1 },  // Version 6
					{ 6, 22, 38, -1, -1, -1, -1 },  // Version 7
					{ 6, 24, 42, -1, -1, -1, -1 },  // Version 8
					{ 6, 26, 46, -1, -1, -1, -1 },  // Version 9
					{ 6, 28, 50, -1, -1, -1, -1 },  // Version 10
					{ 6, 30, 54, -1, -1, -1, -1 },  // Version 11
					{ 6, 32, 58, -1, -1, -1, -1 },  // Version 12
					{ 6, 34, 62, -1, -1, -1, -1 },  // Version 13
					{ 6, 26, 46, 66, -1, -1, -1 },  // Version 14
					{ 6, 26, 48, 70, -1, -1, -1 },  // Version 15
					{ 6, 26, 50, 74, -1, -1, -1 },  // Version 16
					{ 6, 30, 54, 78, -1, -1, -1 },  // Version 17
					{ 6, 30, 56, 82, -1, -1, -1 },  // Version 18
					{ 6, 30, 58, 86, -1, -1, -1 },  // Version 19
					{ 6, 34, 62, 90, -1, -1, -1 },  // Version 20
					{ 6, 28, 50, 72, 94, -1, -1 },  // Version 21
					{ 6, 26, 50, 74, 98, -1, -1 },  // Version 22
					{ 6, 30, 54, 78, 102, -1, -1 },  // Version 23
					{ 6, 28, 54, 80, 106, -1, -1 },  // Version 24
					{ 6, 32, 58, 84, 110, -1, -1 },  // Version 25
					{ 6, 30, 58, 86, 114, -1, -1 },  // Version 26
					{ 6, 34, 62, 90, 118, -1, -1 },  // Version 27
					{ 6, 26, 50, 74, 98, 122, -1 },  // Version 28
					{ 6, 30, 54, 78, 102, 126, -1 },  // Version 29
					{ 6, 26, 52, 78, 104, 130, -1 },  // Version 30
					{ 6, 30, 56, 82, 108, 134, -1 },  // Version 31
					{ 6, 34, 60, 86, 112, 138, -1 },  // Version 32
					{ 6, 30, 58, 86, 114, 142, -1 },  // Version 33
					{ 6, 34, 62, 90, 118, 146, -1 },  // Version 34
					{ 6, 30, 54, 78, 102, 126, 150 },  // Version 35
					{ 6, 24, 50, 76, 102, 128, 154 },  // Version 36
					{ 6, 28, 54, 80, 106, 132, 158 },  // Version 37
					{ 6, 32, 58, 84, 110, 136, 162 },  // Version 38
					{ 6, 26, 54, 82, 110, 138, 166 },  // Version 39
					{ 6, 30, 58, 86, 114, 142, 170 },  // Version 40
	};

	// Type info cells at the left top corner.
	private static final int[][] TYPE_INFO_COORDINATES = {
					{ 8, 0 },
					{ 8, 1 },
					{ 8, 2 },
					{ 8, 3 },
					{ 8, 4 },
					{ 8, 5 },
					{ 8, 7 },
					{ 8, 8 },
					{ 7, 8 },
					{ 5, 8 },
					{ 4, 8 },
					{ 3, 8 },
					{ 2, 8 },
					{ 1, 8 },
					{ 0, 8 },
	};

	// From Appendix D in JISX0510:2004 (p. 67)
	private static final int VERSION_INFO_POLY = 0x1f25;  // 1 1111 0010 0101

	// From Appendix C in JISX0510:2004 (p.65).
	private static final int TYPE_INFO_POLY = 0x537;

	private static final int TYPE_INFO_MASK_PATTERN = 0x5412;

	private MatrixUtil () {
	}

	// Set all cells to -1.  -1 means that the cell is empty (not set yet).
	//
	// JAVAPORT: We shouldn't need to do this at all. The code should be rewritten to begin encoding
	// with the ByteMatrix initialized all to zero.
	public static void clearMatrix ( ByteMatrix matrix ) {
		matrix.clear ( (byte) -1 );
	}

	// Build 2D matrix of QR Code from "dataBits" with "ecLevel", "version" and "getMaskPattern". On
	// success, store the result in "matrix" and return true.
	public static void buildMatrix ( BitVector dataBits, ErrorCorrectionLevel ecLevel, int version,
					int maskPattern, ByteMatrix matrix ) throws WriterException {
		clearMatrix ( matrix );
		embedBasicPatterns ( version, matrix );
		// Type information appear with any version.
		embedTypeInfo ( ecLevel, maskPattern, matrix );
		// Version info appear if version >= 7.
		maybeEmbedVersionInfo ( version, matrix );
		// Data should be embedded at end.
		embedDataBits ( dataBits, maskPattern, matrix );
	}

	// Embed basic patterns. On success, modify the matrix and return true.
	// The basic patterns are:
	// - Position detection patterns
	// - Timing patterns
	// - Dark dot at the left bottom corner
	// - Position adjustment patterns, if need be
	public static void embedBasicPatterns ( int version, ByteMatrix matrix ) throws WriterException {
		// Let's get started with embedding big squares at corners.
		embedPositionDetectionPatternsAndSeparators ( matrix );
		// Then, embed the dark dot at the left bottom corner.
		embedDarkDotAtLeftBottomCorner ( matrix );

		// Position adjustment patterns appear if version >= 2.
		maybeEmbedPositionAdjustmentPatterns ( version, matrix );
		// Timing patterns should be embedded after position adj. patterns.
		embedTimingPatterns ( matrix );
	}

	// Embed type information. On success, modify the matrix.
	public static void embedTypeInfo ( ErrorCorrectionLevel ecLevel, int maskPattern, ByteMatrix matrix )
					throws WriterException {
		BitVector typeInfoBits = new BitVector ();
		makeTypeInfoBits ( ecLevel, maskPattern, typeInfoBits );

		for ( int i = 0; i < typeInfoBits.size (); ++i ) {
			// Place bits in LSB to MSB order.  LSB (least significant bit) is the last value in
			// "typeInfoBits".
			int bit = typeInfoBits.at ( typeInfoBits.size () - 1 - i );

			// Type info bits at the left top corner. See 8.9 of JISX0510:2004 (p.46).
			int x1 = TYPE_INFO_COORDINATES[i][0];
			int y1 = TYPE_INFO_COORDINATES[i][1];
			matrix.set ( x1, y1, bit );

			int x2;
			int y2;
			if ( i < 8 ) {
				// Right top corner.
				x2 = matrix.getWidth () - i - 1;
				y2 = 8;
			} else {
				// Left bottom corner.
				x2 = 8;
				y2 = matrix.getHeight () - 7 + ( i - 8 );
			}
			matrix.set ( x2, y2, bit );
		}
	}

	public static void maybeEmbedVersionInfo ( int version, ByteMatrix matrix ) throws WriterException {
		if ( version < 7 ) {
			return;
		}
		BitVector versionInfoBits = new BitVector ();
		makeVersionInfoBits ( version, versionInfoBits );

		int bitIndex = 6 * 3 - 1;
		for ( int i = 0; i < 6; ++i ) {
			for ( int j = 0; j < 3; ++j ) {
				int bit = versionInfoBits.at ( bitIndex );
				bitIndex--;
				matrix.set ( i, matrix.getHeight () - 11 + j, bit );
				matrix.set ( matrix.getHeight () - 11 + j, i, bit );
			}
		}
	}

	public static void embedDataBits ( BitVector dataBits, int maskPattern, ByteMatrix matrix )
					throws WriterException {
		int bitIndex = 0;
		int direction = -1;
		// Start from the right bottom cell.
		int x = matrix.getWidth () - 1;
		int y = matrix.getHeight () - 1;
		while ( x > 0 ) {
			// Skip the vertical timing pattern.
			if ( x == 6 ) {
				x -= 1;
			}
			while ( y >= 0 && y < matrix.getHeight () ) {
				for ( int i = 0; i < 2; ++i ) {
					int xx = x - i;
					// Skip the cell if it's not empty.
					if ( !isEmpty ( matrix.get ( xx, y ) ) ) {
						continue;
					}
					int bit;
					if ( bitIndex < dataBits.size () ) {
						bit = dataBits.at ( bitIndex );
						++bitIndex;
					} else {
						// Padding bit. If there is no bit left, we'll fill the left cells with 0, as described
						// in 8.4.9 of JISX0510:2004 (p. 24).
						bit = 0;
					}

					// Skip masking if mask_pattern is -1.
					if ( maskPattern != -1 ) {
						if ( MaskUtil.getDataMaskBit ( maskPattern, xx, y ) ) {
							bit ^= 0x1;
						}
					}
					matrix.set ( xx, y, bit );
				}
				y += direction;
			}
			direction = -direction;  // Reverse the direction.
			y += direction;
			x -= 2;  // Move to the left.
		}
		// All bits should be consumed.
		if ( bitIndex != dataBits.size () ) {
			throw new WriterException ( "Not all bits consumed: " + bitIndex + '/' + dataBits.size () );
		}
	}

	public static int findMSBSet ( int value ) {
		int numDigits = 0;
		while ( value != 0 ) {
			value >>>= 1;
			++numDigits;
		}
		return numDigits;
	}

	public static int calculateBCHCode ( int value, int poly ) {
		int msbSetInPoly = findMSBSet ( poly );
		value <<= msbSetInPoly - 1;
		while ( findMSBSet ( value ) >= msbSetInPoly ) {
			value ^= poly << ( findMSBSet ( value ) - msbSetInPoly );
		}
		return value;
	}

	public static void makeTypeInfoBits ( ErrorCorrectionLevel ecLevel, int maskPattern, BitVector bits )
					throws WriterException {
		if ( !QRCode.isValidMaskPattern ( maskPattern ) ) {
			throw new WriterException ( "Invalid mask pattern" );
		}
		int typeInfo = ( ecLevel.getBits () << 3 ) | maskPattern;
		bits.appendBits ( typeInfo, 5 );

		int bchCode = calculateBCHCode ( typeInfo, TYPE_INFO_POLY );
		bits.appendBits ( bchCode, 10 );

		BitVector maskBits = new BitVector ();
		maskBits.appendBits ( TYPE_INFO_MASK_PATTERN, 15 );
		bits.xor ( maskBits );

		if ( bits.size () != 15 ) {  // Just in case.
			throw new WriterException ( "should not happen but we got: " + bits.size () );
		}
	}

	// Make bit vector of version information. On success, store the result in "bits" and return true.
	// See 8.10 of JISX0510:2004 (p.45) for details.
	public static void makeVersionInfoBits ( int version, BitVector bits ) throws WriterException {
		bits.appendBits ( version, 6 );
		int bchCode = calculateBCHCode ( version, VERSION_INFO_POLY );
		bits.appendBits ( bchCode, 12 );

		if ( bits.size () != 18 ) {  // Just in case.
			throw new WriterException ( "should not happen but we got: " + bits.size () );
		}
	}

	// Check if "value" is empty.
	private static boolean isEmpty ( int value ) {
		return value == -1;
	}

	// Check if "value" is valid.
	private static boolean isValidValue ( int value ) {
		return ( value != -1 &&  // Empty.
						value != 0 &&  // Light (white).
						value != 1 );  // Dark (black).
	}

	private static void embedTimingPatterns ( ByteMatrix matrix ) throws WriterException {
		// -8 is for skipping position detection patterns (size 7), and two horizontal/vertical
		// separation patterns (size 1). Thus, 8 = 7 + 1.
		for ( int i = 8; i < matrix.getWidth () - 8; ++i ) {
			int bit = ( i + 1 ) % 2;
			// Horizontal line.
			if ( isValidValue ( matrix.get ( i, 6 ) ) ) {
				throw new WriterException ();
			}
			if ( isEmpty ( matrix.get ( i, 6 ) ) ) {
				matrix.set ( i, 6, bit );
			}
			// Vertical line.
			if ( isValidValue ( matrix.get ( 6, i ) ) ) {
				throw new WriterException ();
			}
			if ( isEmpty ( matrix.get ( 6, i ) ) ) {
				matrix.set ( 6, i, bit );
			}
		}
	}

	// Embed the lonely dark dot at left bottom corner. JISX0510:2004 (p.46)
	private static void embedDarkDotAtLeftBottomCorner ( ByteMatrix matrix ) throws WriterException {
		if ( matrix.get ( 8, matrix.getHeight () - 8 ) == 0 ) {
			throw new WriterException ();
		}
		matrix.set ( 8, matrix.getHeight () - 8, 1 );
	}

	private static void embedHorizontalSeparationPattern ( int xStart, int yStart,
					ByteMatrix matrix ) throws WriterException {
		// We know the width and height.
		for ( int x = 0; x < 8; ++x ) {
			if ( !isEmpty ( matrix.get ( xStart + x, yStart ) ) ) {
				throw new WriterException ();
			}
			matrix.set ( xStart + x, yStart, HORIZONTAL_SEPARATION_PATTERN[0][x] );
		}
	}

	private static void embedVerticalSeparationPattern ( int xStart, int yStart,
					ByteMatrix matrix ) throws WriterException {
		// We know the width and height.
		for ( int y = 0; y < 7; ++y ) {
			if ( !isEmpty ( matrix.get ( xStart, yStart + y ) ) ) {
				throw new WriterException ();
			}
			matrix.set ( xStart, yStart + y, VERTICAL_SEPARATION_PATTERN[y][0] );
		}
	}

	private static void embedPositionAdjustmentPattern ( int xStart, int yStart,
					ByteMatrix matrix ) throws WriterException {
		// We know the width and height.
		for ( int y = 0; y < 5; ++y ) {
			for ( int x = 0; x < 5; ++x ) {
				if ( !isEmpty ( matrix.get ( xStart + x, yStart + y ) ) ) {
					throw new WriterException ();
				}
				matrix.set ( xStart + x, yStart + y, POSITION_ADJUSTMENT_PATTERN[y][x] );
			}
		}
	}

	private static void embedPositionDetectionPattern ( int xStart, int yStart,
					ByteMatrix matrix ) throws WriterException {
		// We know the width and height.
		for ( int y = 0; y < 7; ++y ) {
			for ( int x = 0; x < 7; ++x ) {
				if ( !isEmpty ( matrix.get ( xStart + x, yStart + y ) ) ) {
					throw new WriterException ();
				}
				matrix.set ( xStart + x, yStart + y, POSITION_DETECTION_PATTERN[y][x] );
			}
		}
	}

	// Embed position detection patterns and surrounding vertical/horizontal separators.
	private static void embedPositionDetectionPatternsAndSeparators ( ByteMatrix matrix ) throws WriterException {
		// Embed three big squares at corners.
		int pdpWidth = POSITION_DETECTION_PATTERN[0].length;
		// Left top corner.
		embedPositionDetectionPattern ( 0, 0, matrix );
		// Right top corner.
		embedPositionDetectionPattern ( matrix.getWidth () - pdpWidth, 0, matrix );
		// Left bottom corner.
		embedPositionDetectionPattern ( 0, matrix.getWidth () - pdpWidth, matrix );

		// Embed horizontal separation patterns around the squares.
		int hspWidth = HORIZONTAL_SEPARATION_PATTERN[0].length;
		// Left top corner.
		embedHorizontalSeparationPattern ( 0, hspWidth - 1, matrix );
		// Right top corner.
		embedHorizontalSeparationPattern ( matrix.getWidth () - hspWidth,
						hspWidth - 1, matrix );
		// Left bottom corner.
		embedHorizontalSeparationPattern ( 0, matrix.getWidth () - hspWidth, matrix );

		// Embed vertical separation patterns around the squares.
		int vspSize = VERTICAL_SEPARATION_PATTERN.length;
		// Left top corner.
		embedVerticalSeparationPattern ( vspSize, 0, matrix );
		// Right top corner.
		embedVerticalSeparationPattern ( matrix.getHeight () - vspSize - 1, 0, matrix );
		// Left bottom corner.
		embedVerticalSeparationPattern ( vspSize, matrix.getHeight () - vspSize,
						matrix );
	}

	// Embed position adjustment patterns if need be.
	private static void maybeEmbedPositionAdjustmentPatterns ( int version, ByteMatrix matrix )
					throws WriterException {
		if ( version < 2 ) {  // The patterns appear if version >= 2
			return;
		}
		int index = version - 1;
		int[] coordinates = POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE[index];
		int numCoordinates = POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE[index].length;
		for ( int i = 0; i < numCoordinates; ++i ) {
			for ( int j = 0; j < numCoordinates; ++j ) {
				int y = coordinates[i];
				int x = coordinates[j];
				if ( x == -1 || y == -1 ) {
					continue;
				}
				// If the cell is unset, we embed the position adjustment pattern here.
				if ( isEmpty ( matrix.get ( x, y ) ) ) {
					// -2 is necessary since the x/y coordinates point to the center of the pattern, not the
					// left top corner.
					embedPositionAdjustmentPattern ( x - 2, y - 2, matrix );
				}
			}
		}
	}

}

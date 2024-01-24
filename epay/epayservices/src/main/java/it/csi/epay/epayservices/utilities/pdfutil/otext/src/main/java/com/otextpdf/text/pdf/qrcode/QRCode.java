/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.qrcode;

public final class QRCode {

	public static final int NUM_MASK_PATTERNS = 8;

	private Mode mode;

	private ErrorCorrectionLevel ecLevel;

	private int version;

	private int matrixWidth;

	private int maskPattern;

	private int numTotalBytes;

	private int numDataBytes;

	private int numECBytes;

	private int numRSBlocks;

	private ByteMatrix matrix;

	public QRCode () {
		mode = null;
		ecLevel = null;
		version = -1;
		matrixWidth = -1;
		maskPattern = -1;
		numTotalBytes = -1;
		numDataBytes = -1;
		numECBytes = -1;
		numRSBlocks = -1;
		matrix = null;
	}

	// Check if "mask_pattern" is valid.
	public static boolean isValidMaskPattern ( int maskPattern ) {
		return maskPattern >= 0 && maskPattern < NUM_MASK_PATTERNS;
	}

	// Mode of the QR Code.
	public Mode getMode () {
		return mode;
	}

	public void setMode ( Mode value ) {
		mode = value;
	}

	// Error correction level of the QR Code.
	public ErrorCorrectionLevel getECLevel () {
		return ecLevel;
	}

	public void setECLevel ( ErrorCorrectionLevel value ) {
		ecLevel = value;
	}

	// Version of the QR Code.  The bigger size, the bigger version.
	public int getVersion () {
		return version;
	}

	public void setVersion ( int value ) {
		version = value;
	}

	// ByteMatrix width of the QR Code.
	public int getMatrixWidth () {
		return matrixWidth;
	}

	public void setMatrixWidth ( int value ) {
		matrixWidth = value;
	}

	// Mask pattern of the QR Code.
	public int getMaskPattern () {
		return maskPattern;
	}

	public void setMaskPattern ( int value ) {
		maskPattern = value;
	}

	// Number of total bytes in the QR Code.
	public int getNumTotalBytes () {
		return numTotalBytes;
	}

	public void setNumTotalBytes ( int value ) {
		numTotalBytes = value;
	}

	// Number of data bytes in the QR Code.
	public int getNumDataBytes () {
		return numDataBytes;
	}

	public void setNumDataBytes ( int value ) {
		numDataBytes = value;
	}

	// Number of Reedsolomon blocks in the QR Code.
	public int getNumRSBlocks () {
		return numRSBlocks;
	}

	public void setNumRSBlocks ( int value ) {
		numRSBlocks = value;
	}

	// ByteMatrix data of the QR Code.
	public ByteMatrix getMatrix () {
		return matrix;
	}

	// This takes ownership of the 2D array.
	public void setMatrix ( ByteMatrix value ) {
		matrix = value;
	}

	// Return the value of the module (cell) pointed by "x" and "y" in the matrix of the QR Code. They
	// call cells in the matrix "modules". 1 represents a black cell, and 0 represents a white cell.
	public int at ( int x, int y ) {
		// The value must be zero or one.
		int value = matrix.get ( x, y );
		if ( !( value == 0 || value == 1 ) ) {
			// this is really like an assert... not sure what better exception to use?
			throw new RuntimeException ( "Bad value" );
		}
		return value;
	}

	// Checks all the member variables are set properly. Returns true on success. Otherwise, returns
	// false.
	public boolean isValid () {
		return
						// First check if all version are not uninitialized.
						mode != null &&
										ecLevel != null &&
										version != -1 &&
										matrixWidth != -1 &&
										maskPattern != -1 &&
										numTotalBytes != -1 &&
										numDataBytes != -1 &&
										numECBytes != -1 &&
										numRSBlocks != -1 &&
										// Then check them in other ways..
										isValidMaskPattern ( maskPattern ) &&
										numTotalBytes == numDataBytes + numECBytes &&
										// ByteMatrix stuff.
										matrix != null &&
										matrixWidth == matrix.getWidth () &&
										// See 7.3.1 of JISX0510:2004 (p.5).
										matrix.getWidth () == matrix.getHeight (); // Must be square.
	}

	// Return debug String.
	public String toString () {
		StringBuilder result = new StringBuilder ( 200 );
		result.append ( "<<\n" );
		result.append ( " mode: " );
		result.append ( mode );
		result.append ( "\n ecLevel: " );
		result.append ( ecLevel );
		result.append ( "\n version: " );
		result.append ( version );
		result.append ( "\n matrixWidth: " );
		result.append ( matrixWidth );
		result.append ( "\n maskPattern: " );
		result.append ( maskPattern );
		result.append ( "\n numTotalBytes: " );
		result.append ( numTotalBytes );
		result.append ( "\n numDataBytes: " );
		result.append ( numDataBytes );
		result.append ( "\n numECBytes: " );
		result.append ( numECBytes );
		result.append ( "\n numRSBlocks: " );
		result.append ( numRSBlocks );
		if ( matrix == null ) {
			result.append ( "\n matrix: null\n" );
		} else {
			result.append ( "\n matrix:\n" );
			result.append ( matrix );
		}
		result.append ( ">>\n" );
		return result.toString ();
	}

	public void setNumECBytes ( int value ) {
		numECBytes = value;
	}
}

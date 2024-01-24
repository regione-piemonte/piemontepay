/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec;

import java.io.IOException;
import java.io.OutputStream;


public class LZWCompressor {

	int codeSize_;

	int clearCode_;

	int endOfInfo_;

	int numBits_;

	int limit_;

	short prefix_;

	BitFile bf_;

	LZWStringTable lzss_;

	boolean tiffFudge_;

	public LZWCompressor ( OutputStream out, int codeSize, boolean TIFF ) throws IOException {
		bf_ = new BitFile ( out, !TIFF );
		codeSize_ = codeSize;
		tiffFudge_ = TIFF;
		clearCode_ = 1 << codeSize_;
		endOfInfo_ = clearCode_ + 1;
		numBits_ = codeSize_ + 1;

		limit_ = ( 1 << numBits_ ) - 1;
		if ( tiffFudge_ )
			--limit_;

		prefix_ = (short) 0xFFFF;
		lzss_ = new LZWStringTable ();
		lzss_.ClearTable ( codeSize_ );
		bf_.writeBits ( clearCode_, numBits_ );
	}

	public void compress ( byte[] buf, int offset, int length )
					throws IOException {
		int idx;
		byte c;
		short index;

		int maxOffset = offset + length;
		for ( idx = offset; idx < maxOffset; ++idx ) {
			c = buf[idx];
			if ( ( index = lzss_.FindCharString ( prefix_, c ) ) != -1 )
				prefix_ = index;
			else {
				bf_.writeBits ( prefix_, numBits_ );
				if ( lzss_.AddCharString ( prefix_, c ) > limit_ ) {
					if ( numBits_ == 12 ) {
						bf_.writeBits ( clearCode_, numBits_ );
						lzss_.ClearTable ( codeSize_ );
						numBits_ = codeSize_ + 1;
					} else
						++numBits_;

					limit_ = ( 1 << numBits_ ) - 1;
					if ( tiffFudge_ )
						--limit_;
				}
				prefix_ = (short) ( (short) c & 0xFF );
			}
		}
	}

	public void flush () throws IOException {
		if ( prefix_ != -1 )
			bf_.writeBits ( prefix_, numBits_ );

		bf_.writeBits ( endOfInfo_, numBits_ );
		bf_.flush ();
	}
}

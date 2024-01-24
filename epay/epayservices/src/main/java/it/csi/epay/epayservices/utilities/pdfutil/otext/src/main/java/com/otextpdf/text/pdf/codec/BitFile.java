/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec;

import java.io.IOException;
import java.io.OutputStream;


public class BitFile {

	OutputStream output_;

	byte[] buffer_;

	int index_;

	int bitsLeft_;

	boolean blocks_;

	public BitFile ( OutputStream output, boolean blocks ) {
		output_ = output;
		blocks_ = blocks;
		buffer_ = new byte[256];
		index_ = 0;
		bitsLeft_ = 8;
	}

	public void flush () throws IOException {
		int numBytes = index_ + ( bitsLeft_ == 8 ? 0 : 1 );
		if ( numBytes > 0 ) {
			if ( blocks_ )
				output_.write ( numBytes );
			output_.write ( buffer_, 0, numBytes );
			buffer_[0] = 0;
			index_ = 0;
			bitsLeft_ = 8;
		}
	}

	public void writeBits ( int bits, int numbits ) throws IOException {
		int numBytes = 255;
		do {
			if ( ( index_ == 254 && bitsLeft_ == 0 ) || index_ > 254 ) {
				if ( blocks_ )
					output_.write ( numBytes );

				output_.write ( buffer_, 0, numBytes );

				buffer_[0] = 0;
				index_ = 0;
				bitsLeft_ = 8;
			}

			if ( numbits <= bitsLeft_ ) {
				if ( blocks_ ) // GIF
				{
					buffer_[index_] |= (byte) ( ( bits & ( ( 1 << numbits ) - 1 ) ) << ( 8 - bitsLeft_ ) );
				} else {
					buffer_[index_] |= (byte) ( ( bits & ( ( 1 << numbits ) - 1 ) ) << ( bitsLeft_ - numbits ) );

				}
				bitsLeft_ -= numbits;
				numbits = 0;
			} else    // bits overflow from current byte to next.
			{
				// next index
				// ok this many bits gone off the top
				if ( blocks_ )    // GIF
				{
					// if bits  > space left in current byte then the lowest order bits
					// of code are taken and put in current byte and rest put in next.
					buffer_[index_] |= (byte) ( ( bits & ( ( 1 << bitsLeft_ ) - 1 ) ) << ( 8 - bitsLeft_ ) );
					bits >>= bitsLeft_;
				} else {
					// if bits  > space left in current byte then the highest order bits
					// of code are taken and put in current byte and rest put in next.
					// at highest order bit location !!
					int topbits = ( bits >>> ( numbits - bitsLeft_ ) ) & ( ( 1 << bitsLeft_ ) - 1 );
					buffer_[index_] |= (byte) topbits;
				}
				numbits -= bitsLeft_;
				buffer_[++index_] = 0;
				bitsLeft_ = 8;
			}

		} while ( numbits != 0 );
	}
}

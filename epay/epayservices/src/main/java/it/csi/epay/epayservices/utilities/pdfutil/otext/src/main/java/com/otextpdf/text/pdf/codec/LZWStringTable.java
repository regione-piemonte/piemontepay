/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec;

public class LZWStringTable {

	private final static int RES_CODES = 2;

	private final static short HASH_FREE = (short) 0xFFFF;

	private final static short NEXT_FIRST = (short) 0xFFFF;

	private final static int MAXBITS = 12;

	private final static int MAXSTR = ( 1 << MAXBITS );

	private final static short HASHSIZE = 9973;

	private final static short HASHSTEP = 2039;

	byte[] strChr_;        // after predecessor character

	short[] strNxt_;        // predecessor string

	short[] strHsh_;        // hash table to find  predecessor + char pairs

	short numStrings_;        // next code if adding new prestring + char

	int[] strLen_;

	public LZWStringTable () {
		strChr_ = new byte[MAXSTR];
		strNxt_ = new short[MAXSTR];
		strLen_ = new int[MAXSTR];
		strHsh_ = new short[HASHSIZE];
	}

	static public int Hash ( short index, byte lastbyte ) {
		return ( ( (short) ( lastbyte << 8 ) ^ index ) & 0xFFFF ) % HASHSIZE;
	}

	public int AddCharString ( short index, byte b ) {
		int hshidx;

		if ( numStrings_ >= MAXSTR )    // if used up all codes
		{
			return 0xFFFF;
		}

		hshidx = Hash ( index, b );
		while ( strHsh_[hshidx] != HASH_FREE )
			hshidx = ( hshidx + HASHSTEP ) % HASHSIZE;

		strHsh_[hshidx] = numStrings_;
		strChr_[numStrings_] = b;
		if ( index == HASH_FREE ) {
			strNxt_[numStrings_] = NEXT_FIRST;
			strLen_[numStrings_] = 1;
		} else {
			strNxt_[numStrings_] = index;
			strLen_[numStrings_] = strLen_[index] + 1;
		}

		return numStrings_++;
	}

	public short FindCharString ( short index, byte b ) {
		int hshidx, nxtidx;

		if ( index == HASH_FREE )
			return (short) ( b & 0xFF );    // Rob fixed used to sign extend

		hshidx = Hash ( index, b );
		while ( ( nxtidx = strHsh_[hshidx] ) != HASH_FREE )    // search
		{
			if ( strNxt_[nxtidx] == index && strChr_[nxtidx] == b )
				return (short) nxtidx;
			hshidx = ( hshidx + HASHSTEP ) % HASHSIZE;
		}

		return (short) 0xFFFF;
	}

	public void ClearTable ( int codesize ) {
		numStrings_ = 0;

		for ( int q = 0; q < HASHSIZE; q++ )
			strHsh_[q] = HASH_FREE;

		int w = ( 1 << codesize ) + RES_CODES;
		for ( int q = 0; q < w; q++ )
			AddCharString ( (short) 0xFFFF, (byte) q );
	}

}

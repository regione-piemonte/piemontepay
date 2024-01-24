/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;


public class TtfUnicodeWriter {

	protected PdfWriter writer;

	public TtfUnicodeWriter ( PdfWriter writer ) {
		this.writer = writer;
	}

	public void writeFont ( TrueTypeFontUnicode font, PdfIndirectReference ref, Object[] params ) throws DocumentException, IOException {
		HashMap<Integer, int[]> longTag = (HashMap<Integer, int[]>) params[0];
		font.addRangeUni ( longTag, font.subset );
		int[][] metrics = longTag.values ().toArray ( new int[0][] );
		Arrays.sort ( metrics, font );
		PdfIndirectReference ind_font;
		PdfObject pobj;
		PdfIndirectObject obj;
		byte[] b;
		if ( font.cff ) {
			b = font.readCffFont ();
			if ( font.subset || font.subsetRanges != null ) {
				CFFFontSubset cff = new CFFFontSubset ( new RandomAccessFileOrArray ( b ), longTag );
				b = cff.Process ( cff.getNames ()[0] );
			}
			pobj = new BaseFont.StreamFont ( b, "CIDFontType0C", font.compressionLevel );
		} else {
			if ( font.subset || font.directoryOffset != 0 ) {
				b = font.getSubSet ( new HashSet<> ( longTag.keySet () ), true );
			} else {
				b = font.getFullFont ();
			}
			int[] lengths = new int[] { b.length };
			pobj = new BaseFont.StreamFont ( b, lengths, font.compressionLevel );
		}
		obj = writer.addToBody ( pobj );
		ind_font = obj.getIndirectReference ();
		String subsetPrefix = "";
		if ( font.subset )
			subsetPrefix = BaseFont.createSubsetPrefix ();
		PdfDictionary dic = font.getFontDescriptor ( ind_font, subsetPrefix, null );
		obj = writer.addToBody ( dic );
		ind_font = obj.getIndirectReference ();

		pobj = font.getCIDFontType2 ( ind_font, subsetPrefix, metrics );
		obj = writer.addToBody ( pobj );
		ind_font = obj.getIndirectReference ();

		pobj = font.getToUnicode ( metrics );
		PdfIndirectReference toUnicodeRef = null;

		if ( pobj != null ) {
			obj = writer.addToBody ( pobj );
			toUnicodeRef = obj.getIndirectReference ();
		}

		pobj = font.getFontBaseType ( ind_font, subsetPrefix, toUnicodeRef );
		writer.addToBody ( pobj, ref );
	}
}

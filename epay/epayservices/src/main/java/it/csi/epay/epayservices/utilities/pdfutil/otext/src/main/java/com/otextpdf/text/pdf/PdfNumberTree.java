/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;


public class PdfNumberTree {

	private static final int leafSize = 64;

	public static <O extends PdfObject> PdfDictionary writeTree ( HashMap<Integer, O> items, PdfWriter writer ) throws IOException {
		if ( items.isEmpty () )
			return null;
		Integer[] numbers = new Integer[items.size ()];
		numbers = items.keySet ().toArray ( numbers );
		Arrays.sort ( numbers );
		if ( numbers.length <= leafSize ) {
			PdfDictionary dic = new PdfDictionary ();
			PdfArray ar = new PdfArray ();
			for ( Integer number : numbers ) {
				ar.add ( new PdfNumber ( number ) );
				ar.add ( items.get ( number ) );
			}
			dic.put ( PdfName.NUMS, ar );
			return dic;
		}
		int skip = leafSize;
		PdfIndirectReference[] kids = new PdfIndirectReference[( numbers.length + leafSize - 1 ) / leafSize];
		for ( int k = 0; k < kids.length; ++k ) {
			int offset = k * leafSize;
			int end = Math.min ( offset + leafSize, numbers.length );
			PdfDictionary dic = new PdfDictionary ();
			PdfArray arr = new PdfArray ();
			arr.add ( new PdfNumber ( numbers[offset] ) );
			arr.add ( new PdfNumber ( numbers[end - 1] ) );
			dic.put ( PdfName.LIMITS, arr );
			arr = new PdfArray ();
			for ( ; offset < end; ++offset ) {
				arr.add ( new PdfNumber ( numbers[offset] ) );
				arr.add ( items.get ( numbers[offset] ) );
			}
			dic.put ( PdfName.NUMS, arr );
			kids[k] = writer.addToBody ( dic ).getIndirectReference ();
		}
		int top = kids.length;
		while ( true ) {
			if ( top <= leafSize ) {
				PdfArray arr = new PdfArray ();
				for ( int k = 0; k < top; ++k )
					arr.add ( kids[k] );
				PdfDictionary dic = new PdfDictionary ();
				dic.put ( PdfName.KIDS, arr );
				return dic;
			}
			skip *= leafSize;
			int tt = ( numbers.length + skip - 1 ) / skip;
			for ( int k = 0; k < tt; ++k ) {
				int offset = k * leafSize;
				int end = Math.min ( offset + leafSize, top );
				PdfDictionary dic = new PdfDictionary ();
				PdfArray arr = new PdfArray ();
				arr.add ( new PdfNumber ( numbers[k * skip] ) );
				arr.add ( new PdfNumber ( numbers[Math.min ( ( k + 1 ) * skip, numbers.length ) - 1] ) );
				dic.put ( PdfName.LIMITS, arr );
				arr = new PdfArray ();
				for ( ; offset < end; ++offset ) {
					arr.add ( kids[offset] );
				}
				dic.put ( PdfName.KIDS, arr );
				kids[k] = writer.addToBody ( dic ).getIndirectReference ();
			}
			top = tt;
		}
	}

}

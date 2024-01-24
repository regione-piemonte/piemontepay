/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;


public class PdfNameTree {

	private static final int leafSize = 64;

	public static PdfDictionary writeTree ( HashMap<String, ? extends PdfObject> items, PdfWriter writer ) throws IOException {
		if ( items.isEmpty () )
			return null;
		String[] names = new String[items.size ()];
		names = items.keySet ().toArray ( names );
		Arrays.sort ( names );
		if ( names.length <= leafSize ) {
			PdfDictionary dic = new PdfDictionary ();
			PdfArray ar = new PdfArray ();
			for ( String name : names ) {
				ar.add ( new PdfString ( name, null ) );
				ar.add ( items.get ( name ) );
			}
			dic.put ( PdfName.NAMES, ar );
			return dic;
		}
		int skip = leafSize;
		PdfIndirectReference[] kids = new PdfIndirectReference[( names.length + leafSize - 1 ) / leafSize];
		for ( int k = 0; k < kids.length; ++k ) {
			int offset = k * leafSize;
			int end = Math.min ( offset + leafSize, names.length );
			PdfDictionary dic = new PdfDictionary ();
			PdfArray arr = new PdfArray ();
			arr.add ( new PdfString ( names[offset], null ) );
			arr.add ( new PdfString ( names[end - 1], null ) );
			dic.put ( PdfName.LIMITS, arr );
			arr = new PdfArray ();
			for ( ; offset < end; ++offset ) {
				arr.add ( new PdfString ( names[offset], null ) );
				arr.add ( items.get ( names[offset] ) );
			}
			dic.put ( PdfName.NAMES, arr );
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
			int tt = ( names.length + skip - 1 ) / skip;
			for ( int k = 0; k < tt; ++k ) {
				int offset = k * leafSize;
				int end = Math.min ( offset + leafSize, top );
				PdfDictionary dic = new PdfDictionary ();
				PdfArray arr = new PdfArray ();
				arr.add ( new PdfString ( names[k * skip], null ) );
				arr.add ( new PdfString ( names[Math.min ( ( k + 1 ) * skip, names.length ) - 1], null ) );
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

	private static PdfString iterateItems ( PdfDictionary dic, HashMap<String, PdfObject> items, PdfString leftOverString ) {
		PdfArray nn = (PdfArray) PdfReader.getPdfObjectRelease ( dic.get ( PdfName.NAMES ) );
		if ( nn != null ) {
			for ( int k = 0; k < nn.size (); ++k ) {
				PdfString s;
				if ( leftOverString == null )
					s = (PdfString) PdfReader.getPdfObjectRelease ( nn.getPdfObject ( k++ ) );
				else {
					// this is the leftover string from the previous loop
					s = leftOverString;
					leftOverString = null;
				}
				if ( k < nn.size () ) // could have a mistake int the pdf file
					items.put ( PdfEncodings.convertToString ( s.getBytes (), null ), nn.getPdfObject ( k ) );
				else
					return s;
			}
		} else if ( ( nn = (PdfArray) PdfReader.getPdfObjectRelease ( dic.get ( PdfName.KIDS ) ) ) != null ) {
			for ( int k = 0; k < nn.size (); ++k ) {
				PdfDictionary kid = (PdfDictionary) PdfReader.getPdfObjectRelease ( nn.getPdfObject ( k ) );
				leftOverString = iterateItems ( kid, items, leftOverString );
			}
		}
		return null;
	}

	public static HashMap<String, PdfObject> readTree ( PdfDictionary dic ) {
		HashMap<String, PdfObject> items = new HashMap<> ();
		if ( dic != null )
			iterateItems ( dic, items, null );
		return items;
	}
}

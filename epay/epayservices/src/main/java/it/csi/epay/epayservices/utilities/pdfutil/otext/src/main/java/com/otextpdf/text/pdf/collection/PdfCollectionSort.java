/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.collection;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfArray;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfBoolean;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfDictionary;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;


public class PdfCollectionSort extends PdfDictionary {

	public PdfCollectionSort ( String[] keys ) {
		super ( PdfName.COLLECTIONSORT );
		PdfArray array = new PdfArray ();
		for ( String key : keys ) {
			array.add ( new PdfName ( key ) );
		}
		put ( PdfName.S, array );
	}

	public void setSortOrder ( boolean ascending ) {
		PdfObject o = get ( PdfName.S );
		if ( o instanceof PdfName ) {
			put ( PdfName.A, new PdfBoolean ( ascending ) );
		} else {
			throw new IllegalArgumentException (
							MessageLocalization.getComposedMessage ( "you.have.to.define.a.boolean.array.for.this.collection.sort.dictionary" ) );
		}
	}

	public void setSortOrder ( boolean[] ascending ) {
		PdfObject o = get ( PdfName.S );
		if ( o instanceof PdfArray ) {
			if ( ( (PdfArray) o ).size () != ascending.length ) {
				throw new IllegalArgumentException ( MessageLocalization.getComposedMessage (
								"the.number.of.booleans.in.this.array.doesn.t.correspond.with.the.number.of.fields" ) );
			}
			PdfArray array = new PdfArray ();
			for ( boolean b : ascending ) {
				array.add ( new PdfBoolean ( b ) );
			}
			put ( PdfName.A, array );
		} else {
			throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "you.need.a.single.boolean.for.this.collection.sort.dictionary" ) );
		}
	}

}

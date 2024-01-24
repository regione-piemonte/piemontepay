/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;

import java.io.IOException;
import java.util.ArrayList;


public class PdfPages {

	private final ArrayList<PdfIndirectReference> pages = new ArrayList<> ();

	private final ArrayList<PdfIndirectReference> parents = new ArrayList<> ();

	private final int leafSize = 10;

	private final PdfWriter writer;

	PdfPages ( PdfWriter writer ) {
		this.writer = writer;
	}

	void addPage ( PdfDictionary page ) {
		try {
			if ( pages.size () % leafSize == 0 )
				parents.add ( writer.getPdfIndirectReference () );
			PdfIndirectReference parent = parents.get ( parents.size () - 1 );
			page.put ( PdfName.PARENT, parent );
			PdfIndirectReference current = writer.getCurrentPage ();
			writer.addToBody ( page, current );
			pages.add ( current );
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
	}

	PdfIndirectReference addPageRef ( PdfIndirectReference pageRef ) {
		try {
			if ( pages.size () % leafSize == 0 )
				parents.add ( writer.getPdfIndirectReference () );
			pages.add ( pageRef );
			return parents.get ( parents.size () - 1 );
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
	}

	PdfIndirectReference writePageTree () throws IOException {
		if ( pages.isEmpty () )
			throw new IOException ( MessageLocalization.getComposedMessage ( "the.document.has.no.pages" ) );
		int leaf = 1;
		ArrayList<PdfIndirectReference> tParents = parents;
		ArrayList<PdfIndirectReference> tPages = pages;
		ArrayList<PdfIndirectReference> nextParents = new ArrayList<> ();
		while ( true ) {
			leaf *= leafSize;
			int stdCount = leafSize;
			int rightCount = tPages.size () % leafSize;
			if ( rightCount == 0 )
				rightCount = leafSize;
			for ( int p = 0; p < tParents.size (); ++p ) {
				int count;
				int thisLeaf = leaf;
				if ( p == tParents.size () - 1 ) {
					count = rightCount;
					thisLeaf = pages.size () % leaf;
					if ( thisLeaf == 0 )
						thisLeaf = leaf;
				} else
					count = stdCount;
				PdfDictionary top = new PdfDictionary ( PdfName.PAGES );
				top.put ( PdfName.COUNT, new PdfNumber ( thisLeaf ) );
				PdfArray kids = new PdfArray ();
				ArrayList<PdfObject> internal = kids.getArrayList ();
				internal.addAll ( tPages.subList ( p * stdCount, p * stdCount + count ) );
				top.put ( PdfName.KIDS, kids );
				if ( tParents.size () > 1 ) {
					if ( p % leafSize == 0 )
						nextParents.add ( writer.getPdfIndirectReference () );
					top.put ( PdfName.PARENT, nextParents.get ( p / leafSize ) );
				}
				writer.addToBody ( top, tParents.get ( p ) );
			}
			if ( tParents.size () == 1 ) {
				return tParents.get ( 0 );
			}
			tPages = tParents;
			tParents = nextParents;
			nextParents = new ArrayList<> ();
		}
	}

}

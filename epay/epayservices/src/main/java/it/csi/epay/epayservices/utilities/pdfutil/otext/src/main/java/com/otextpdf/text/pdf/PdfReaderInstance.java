/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


class PdfReaderInstance {

	static final PdfLiteral IDENTITYMATRIX = new PdfLiteral ( "[1 0 0 1 0 0]" );

	static final PdfNumber ONE = new PdfNumber ( 1 );

	int[] myXref;

	PdfReader reader;

	RandomAccessFileOrArray file;

	HashMap<Integer, PdfImportedPage> importedPages = new HashMap<> ();

	PdfWriter writer;

	HashSet<Integer> visited = new HashSet<> ();

	ArrayList<Integer> nextRound = new ArrayList<> ();

	PdfReaderInstance ( PdfReader reader, PdfWriter writer ) {
		this.reader = reader;
		this.writer = writer;
		file = reader.getSafeFile ();
		myXref = new int[reader.getXrefSize ()];
	}

	PdfReader getReader () {
		return reader;
	}

	int getNewObjectNumber ( int number ) {
		if ( myXref[number] == 0 ) {
			myXref[number] = writer.getIndirectReferenceNumber ();
			nextRound.add ( number );
		}
		return myXref[number];
	}

	PdfObject getResources ( int pageNumber ) {
		return PdfReader.getPdfObjectRelease ( reader.getPageNRelease ( pageNumber ).get ( PdfName.RESOURCES ) );
	}

	PdfStream getFormXObject ( int pageNumber, int compressionLevel ) throws IOException {
		PdfDictionary page = reader.getPageNRelease ( pageNumber );
		PdfObject contents = PdfReader.getPdfObjectRelease ( page.get ( PdfName.CONTENTS ) );
		PdfDictionary dic = new PdfDictionary ();
		byte[] bout = null;
		if ( contents != null ) {
			if ( contents.isStream () )
				dic.putAll ( (PRStream) contents );
			else
				bout = reader.getPageContent ( pageNumber, file );
		} else
			bout = new byte[0];
		dic.put ( PdfName.RESOURCES, PdfReader.getPdfObjectRelease ( page.get ( PdfName.RESOURCES ) ) );
		dic.put ( PdfName.TYPE, PdfName.XOBJECT );
		dic.put ( PdfName.SUBTYPE, PdfName.FORM );
		PdfImportedPage impPage = importedPages.get ( pageNumber );
		dic.put ( PdfName.BBOX, new PdfRectangle ( impPage.getBoundingBox () ) );
		PdfArray matrix = impPage.getMatrix ();
		if ( matrix == null )
			dic.put ( PdfName.MATRIX, IDENTITYMATRIX );
		else
			dic.put ( PdfName.MATRIX, matrix );
		dic.put ( PdfName.FORMTYPE, ONE );
		PRStream stream;
		if ( bout == null ) {
			stream = new PRStream ( (PRStream) contents, dic );
		} else {
			stream = new PRStream ( reader, bout, compressionLevel );
			stream.putAll ( dic );
		}
		return stream;
	}

	void writeAllVisited () throws IOException {
		while ( !nextRound.isEmpty () ) {
			ArrayList<Integer> vec = nextRound;
			nextRound = new ArrayList<> ();
			for ( Integer i : vec ) {
				if ( !visited.contains ( i ) ) {
					visited.add ( i );
					int n = i;
					writer.addToBody ( reader.getPdfObjectRelease ( n ), myXref[n] );
				}
			}
		}
	}

	public void writeAllPages () throws IOException {
		try {
			file.reOpen ();
			for ( PdfImportedPage element : importedPages.values () ) {
				if ( element.isToCopy () ) {
					writer.addToBody ( element.getFormXObject ( writer.getCompressionLevel () ), element.getIndirectReference () );
					element.setCopied ();
				}
			}
			writeAllVisited ();
		} finally {
			try {
				file.close ();
			} catch ( Exception ignored ) {
			}
		}
	}
}

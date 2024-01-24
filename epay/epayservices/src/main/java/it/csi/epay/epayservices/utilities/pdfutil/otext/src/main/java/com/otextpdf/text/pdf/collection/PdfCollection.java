/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.collection;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfDictionary;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;


public class PdfCollection extends PdfDictionary {

	public static final int DETAILS = 0;

	public static final int TILE = 1;

	public static final int HIDDEN = 2;

	public static final int CUSTOM = 3;

	public PdfCollection ( int type ) {
		super ( PdfName.COLLECTION );
		switch ( type ) {
		case TILE:
			put ( PdfName.VIEW, PdfName.T );
			break;
		case HIDDEN:
			put ( PdfName.VIEW, PdfName.H );
			break;
		case CUSTOM:
			put ( PdfName.VIEW, PdfName.C );
			break;
		default:
			put ( PdfName.VIEW, PdfName.D );
		}
	}

	public PdfCollectionSchema getSchema () {
		return (PdfCollectionSchema) get ( PdfName.SCHEMA );
	}

	public void setSchema ( PdfCollectionSchema schema ) {
		put ( PdfName.SCHEMA, schema );
	}

	public void setSort ( PdfCollectionSort sort ) {
		put ( PdfName.SORT, sort );
	}
}

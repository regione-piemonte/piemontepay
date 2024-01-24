/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;


public class PdfPattern extends PdfStream {

	PdfPattern ( PdfPatternPainter painter ) {
		this ( painter, DEFAULT_COMPRESSION );
	}

	PdfPattern ( PdfPatternPainter painter, int compressionLevel ) {
		super ();
		PdfNumber one = new PdfNumber ( 1 );
		PdfArray matrix = painter.getMatrix ();
		if ( matrix != null ) {
			put ( PdfName.MATRIX, matrix );
		}
		put ( PdfName.TYPE, PdfName.PATTERN );
		put ( PdfName.BBOX, new PdfRectangle ( painter.getBoundingBox () ) );
		put ( PdfName.RESOURCES, painter.getResources () );
		put ( PdfName.TILINGTYPE, one );
		put ( PdfName.PATTERNTYPE, one );
		if ( painter.isStencil () )
			put ( PdfName.PAINTTYPE, new PdfNumber ( 2 ) );
		else
			put ( PdfName.PAINTTYPE, one );
		put ( PdfName.XSTEP, new PdfNumber ( painter.getXStep () ) );
		put ( PdfName.YSTEP, new PdfNumber ( painter.getYStep () ) );
		bytes = painter.toPdf ();
		put ( PdfName.LENGTH, new PdfNumber ( bytes.length ) );
		try {
			flateCompress ( compressionLevel );
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
	}
}

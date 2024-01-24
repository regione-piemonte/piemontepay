/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal.PdfIsoKeys;

import java.io.IOException;
import java.io.OutputStream;


public class PdfGState extends PdfDictionary {

	public static final PdfName BM_NORMAL = new PdfName ( "Normal" );

	public static final PdfName BM_COMPATIBLE = new PdfName ( "Compatible" );

	public static final PdfName BM_MULTIPLY = new PdfName ( "Multiply" );

	public static final PdfName BM_SCREEN = new PdfName ( "Screen" );

	public static final PdfName BM_OVERLAY = new PdfName ( "Overlay" );

	public static final PdfName BM_DARKEN = new PdfName ( "Darken" );

	public static final PdfName BM_LIGHTEN = new PdfName ( "Lighten" );

	public static final PdfName BM_COLORDODGE = new PdfName ( "ColorDodge" );

	public static final PdfName BM_COLORBURN = new PdfName ( "ColorBurn" );

	public static final PdfName BM_HARDLIGHT = new PdfName ( "HardLight" );

	public static final PdfName BM_SOFTLIGHT = new PdfName ( "SoftLight" );

	public static final PdfName BM_DIFFERENCE = new PdfName ( "Difference" );

	public static final PdfName BM_EXCLUSION = new PdfName ( "Exclusion" );

	public void setStrokeOpacity ( float n ) {
		put ( PdfName.CA, new PdfNumber ( n ) );
	}

	public void setFillOpacity ( float n ) {
		put ( PdfName.ca, new PdfNumber ( n ) );
	}

	@Override
	public void toPdf ( final PdfWriter writer, final OutputStream os ) throws IOException {
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_GSTATE, this );
		super.toPdf ( writer, os );
	}
}

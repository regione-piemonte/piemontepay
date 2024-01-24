/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;

import java.io.IOException;


public class PdfShadingPattern extends PdfDictionary {

	protected PdfShading shading;

	protected PdfWriter writer;

	protected float[] matrix = { 1, 0, 0, 1, 0, 0 };

	protected PdfName patternName;

	protected PdfIndirectReference patternReference;

	public PdfShadingPattern ( PdfShading shading ) {
		writer = shading.getWriter ();
		put ( PdfName.PATTERNTYPE, new PdfNumber ( 2 ) );
		this.shading = shading;
	}

	PdfName getPatternName () {
		return patternName;
	}

	PdfIndirectReference getPatternReference () {
		if ( patternReference == null )
			patternReference = writer.getPdfIndirectReference ();
		return patternReference;
	}

	PdfIndirectReference getShadingReference () {
		return shading.getShadingReference ();
	}

	void setName ( int number ) {
		patternName = new PdfName ( "P" + number );
	}

	public void addToBody () throws IOException {
		put ( PdfName.SHADING, getShadingReference () );
		put ( PdfName.MATRIX, new PdfArray ( matrix ) );
		writer.addToBody ( this, getPatternReference () );
	}

	public float[] getMatrix () {
		return matrix;
	}

	public void setMatrix ( float[] matrix ) {
		if ( matrix.length != 6 )
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "the.matrix.size.must.be.6" ) );
		this.matrix = matrix;
	}

	public PdfShading getShading () {
		return shading;
	}

	ColorDetails getColorDetails () {
		return shading.getColorDetails ();
	}

}

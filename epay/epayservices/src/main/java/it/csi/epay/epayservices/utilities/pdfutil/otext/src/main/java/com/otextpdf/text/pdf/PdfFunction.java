/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;

import java.io.IOException;


public class PdfFunction {

	protected PdfWriter writer;

	protected PdfIndirectReference reference;

	protected PdfDictionary dictionary;

	protected PdfFunction ( PdfWriter writer ) {
		this.writer = writer;
	}

	public static PdfFunction type2 ( PdfWriter writer, float[] domain, float[] range, float[] c0, float[] c1, float n ) {
		PdfFunction func = new PdfFunction ( writer );
		func.dictionary = new PdfDictionary ();
		func.dictionary.put ( PdfName.FUNCTIONTYPE, new PdfNumber ( 2 ) );
		func.dictionary.put ( PdfName.DOMAIN, new PdfArray ( domain ) );
		if ( range != null )
			func.dictionary.put ( PdfName.RANGE, new PdfArray ( range ) );
		if ( c0 != null )
			func.dictionary.put ( PdfName.C0, new PdfArray ( c0 ) );
		if ( c1 != null )
			func.dictionary.put ( PdfName.C1, new PdfArray ( c1 ) );
		func.dictionary.put ( PdfName.N, new PdfNumber ( n ) );
		return func;
	}

	PdfIndirectReference getReference () {
		try {
			if ( reference == null ) {
				reference = writer.addToBody ( dictionary ).getIndirectReference ();
			}
		} catch ( IOException ioe ) {
			throw new ExceptionConverter ( ioe );
		}
		return reference;
	}

}

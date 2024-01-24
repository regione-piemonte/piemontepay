/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import java.io.IOException;
import java.io.OutputStream;


public class PdfDashPattern extends PdfArray {

	private final float dash;

	public PdfDashPattern ( float dash ) {
		super ( new PdfNumber ( dash ) );
		this.dash = dash;
	}

	public void add ( float n ) {
		add ( new PdfNumber ( n ) );
	}

	public void toPdf ( PdfWriter writer, OutputStream os ) throws IOException {
		os.write ( '[' );

		if ( dash >= 0 ) {
			new PdfNumber ( dash ).toPdf ( writer, os );
		}
		os.write ( ']' );
	}
}

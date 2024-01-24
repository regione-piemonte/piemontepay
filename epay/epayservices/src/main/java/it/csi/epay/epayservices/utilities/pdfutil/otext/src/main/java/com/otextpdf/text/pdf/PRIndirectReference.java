/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import java.io.IOException;
import java.io.OutputStream;


public class PRIndirectReference extends PdfIndirectReference {

	protected PdfReader reader;

	public PRIndirectReference ( PdfReader reader, int number, int generation ) {
		type = INDIRECT;
		this.number = number;
		this.generation = generation;
		this.reader = reader;
	}

	public PRIndirectReference ( PdfReader reader, int number ) {
		this ( reader, number, 0 );
	}

	public void toPdf ( PdfWriter writer, OutputStream os ) throws IOException {
		int n = writer.getNewObjectNumber ( reader, number, generation );
		os.write ( PdfEncodings.convertToBytes ( n + " " + ( reader.isAppendable () ? generation : 0 ) + " R", null ) );
	}

	public PdfReader getReader () {
		return reader;
	}

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocWriter;

import java.io.IOException;
import java.io.OutputStream;


public class PdfIndirectObject {

	static final byte[] STARTOBJ = DocWriter.getISOBytes ( " obj\n" );

	static final byte[] ENDOBJ = DocWriter.getISOBytes ( "\nendobj\n" );

	static final int SIZEOBJ = STARTOBJ.length + ENDOBJ.length;

	protected int number;

	protected int generation;

	protected PdfObject object;

	protected PdfWriter writer;

	protected PdfIndirectObject ( int number, PdfObject object, PdfWriter writer ) {
		this ( number, 0, object, writer );
	}

	PdfIndirectObject ( PdfIndirectReference ref, PdfObject object, PdfWriter writer ) {
		this ( ref.getNumber (), ref.getGeneration (), object, writer );
	}

	PdfIndirectObject ( int number, int generation, PdfObject object, PdfWriter writer ) {
		this.writer = writer;
		this.number = number;
		this.generation = generation;
		this.object = object;
		PdfEncryption crypto = null;
		if ( writer != null )
			crypto = writer.getEncryption ();
		if ( crypto != null ) {
			crypto.setHashKey ( number, generation );
		}
	}

	public PdfIndirectReference getIndirectReference () {
		return new PdfIndirectReference ( object.type (), number, generation );
	}

	protected void writeTo ( OutputStream os ) throws IOException {
		os.write ( DocWriter.getISOBytes ( String.valueOf ( number ) ) );
		os.write ( ' ' );
		os.write ( DocWriter.getISOBytes ( String.valueOf ( generation ) ) );
		os.write ( STARTOBJ );
		object.toPdf ( writer, os );
		os.write ( ENDOBJ );
	}
}

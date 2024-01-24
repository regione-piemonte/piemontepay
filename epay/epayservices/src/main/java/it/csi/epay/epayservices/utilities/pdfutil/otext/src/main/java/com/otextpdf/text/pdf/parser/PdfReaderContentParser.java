/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.parser;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfDictionary;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfReader;

import java.io.IOException;


public class PdfReaderContentParser {

	private final PdfReader reader;

	public PdfReaderContentParser ( PdfReader reader ) {
		this.reader = reader;
	}

	public <E extends RenderListener> E processContent ( int pageNumber, E renderListener ) throws IOException {
		PdfDictionary pageDic = reader.getPageN ( pageNumber );
		PdfDictionary resourcesDic = pageDic.getAsDict ( PdfName.RESOURCES );

		PdfContentStreamProcessor processor = new PdfContentStreamProcessor ( renderListener );
		processor.processContent ( ContentByteUtils.getContentBytesForPage ( reader, pageNumber ), resourcesDic );
		return renderListener;

	}
}

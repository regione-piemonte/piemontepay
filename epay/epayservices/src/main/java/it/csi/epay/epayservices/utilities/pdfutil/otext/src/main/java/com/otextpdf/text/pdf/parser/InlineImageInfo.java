/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.parser;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfDictionary;


public class InlineImageInfo {

	private final byte[] samples;

	private final PdfDictionary imageDictionary;

	public InlineImageInfo ( byte[] samples, PdfDictionary imageDictionary ) {
		this.samples = samples;
		this.imageDictionary = imageDictionary;
	}

	public PdfDictionary getImageDictionary () {
		return imageDictionary;
	}

	public byte[] getSamples () {
		return samples;
	}
}

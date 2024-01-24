/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.parser;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PRStream;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfDictionary;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfIndirectReference;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfReader;

import java.io.IOException;


public class ImageRenderInfo {

	private final Matrix ctm;

	private final PdfIndirectReference ref;

	private final InlineImageInfo inlineImageInfo;

	private final PdfDictionary colorSpaceDictionary;

	private PdfImageObject imageObject = null;

	private ImageRenderInfo ( Matrix ctm, PdfIndirectReference ref, PdfDictionary colorSpaceDictionary ) {
		this.ctm = ctm;
		this.ref = ref;
		this.inlineImageInfo = null;
		this.colorSpaceDictionary = colorSpaceDictionary;
	}

	private ImageRenderInfo ( Matrix ctm, InlineImageInfo inlineImageInfo, PdfDictionary colorSpaceDictionary ) {
		this.ctm = ctm;
		this.ref = null;
		this.inlineImageInfo = inlineImageInfo;
		this.colorSpaceDictionary = colorSpaceDictionary;
	}

	public static ImageRenderInfo createForXObject ( Matrix ctm, PdfIndirectReference ref, PdfDictionary colorSpaceDictionary ) {
		return new ImageRenderInfo ( ctm, ref, colorSpaceDictionary );
	}

	protected static ImageRenderInfo createForEmbeddedImage ( Matrix ctm, InlineImageInfo inlineImageInfo, PdfDictionary colorSpaceDictionary ) {
		return new ImageRenderInfo ( ctm, inlineImageInfo, colorSpaceDictionary );
	}

	public PdfImageObject getImage () throws IOException {
		prepareImageObject ();
		return imageObject;
	}

	private void prepareImageObject () throws IOException {
		if ( imageObject != null )
			return;

		if ( ref != null ) {
			PRStream stream = (PRStream) PdfReader.getPdfObject ( ref );
			imageObject = new PdfImageObject ( stream, colorSpaceDictionary );
		} else if ( inlineImageInfo != null ) {
			imageObject = new PdfImageObject ( inlineImageInfo.getImageDictionary (), inlineImageInfo.getSamples (), colorSpaceDictionary );
		}
	}

	public float getArea () {
		// the image space area is 1, so we multiply that by the determinant of the CTM to get the transformed area
		return ctm.getDeterminant ();
	}

	public PdfIndirectReference getRef () {
		return ref;
	}
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.parser;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PRIndirectReference;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PRStream;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfArray;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfDictionary;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfReader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class ContentByteUtils {

	private ContentByteUtils () {
	}

	public static byte[] getContentBytesFromContentObject ( final PdfObject contentObject ) throws IOException {
		final byte[] result;
		switch ( contentObject.type () ) {
		case PdfObject.INDIRECT:
			final PRIndirectReference ref = (PRIndirectReference) contentObject;
			final PdfObject directObject = PdfReader.getPdfObjectRelease ( ref );
			result = getContentBytesFromContentObject ( directObject );
			break;
		case PdfObject.STREAM:
			final PRStream stream = (PRStream) PdfReader.getPdfObjectRelease ( contentObject );
			result = PdfReader.getStreamBytes ( stream );
			break;
		case PdfObject.ARRAY:
			final ByteArrayOutputStream allBytes = new ByteArrayOutputStream ();
			final PdfArray contentArray = (PdfArray) contentObject;
			for ( PdfObject element : contentArray ) {
				allBytes.write ( getContentBytesFromContentObject ( element ) );
				allBytes.write ( (byte) ' ' );
			}
			result = allBytes.toByteArray ();
			break;
		default:
			final String msg = "Unable to handle Content of type " + contentObject.getClass ();
			throw new IllegalStateException ( msg );
		}
		return result;
	}

	public static byte[] getContentBytesForPage ( PdfReader reader, int pageNum ) throws IOException {
		final PdfDictionary pageDictionary = reader.getPageN ( pageNum );
		final PdfObject contentObject = pageDictionary.get ( PdfName.CONTENTS );
		if ( contentObject == null )
			return new byte[0];

		return ContentByteUtils.getContentBytesFromContentObject ( contentObject );
	}

}

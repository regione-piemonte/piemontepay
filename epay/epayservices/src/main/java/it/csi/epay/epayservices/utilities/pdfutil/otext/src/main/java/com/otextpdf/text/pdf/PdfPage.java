/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;

import java.util.HashMap;


public class PdfPage extends PdfDictionary {

	public static final PdfNumber PORTRAIT = new PdfNumber ( 0 );

	public static final PdfNumber LANDSCAPE = new PdfNumber ( 90 );

	public static final PdfNumber INVERTEDPORTRAIT = new PdfNumber ( 180 );

	public static final PdfNumber SEASCAPE = new PdfNumber ( 270 );

	private static final String[] boxStrings = { "crop", "trim", "art", "bleed" };

	private static final PdfName[] boxNames = { PdfName.CROPBOX, PdfName.TRIMBOX, PdfName.ARTBOX, PdfName.BLEEDBOX };

	PdfRectangle mediaBox;

	PdfPage ( PdfRectangle mediaBox, HashMap<String, PdfRectangle> boxSize, PdfDictionary resources, int rotate ) throws DocumentException {
		super ( PAGE );
		this.mediaBox = mediaBox;
		if ( mediaBox != null && ( mediaBox.width () > 14400 || mediaBox.height () > 14400 ) ) {
			throw new DocumentException (
							MessageLocalization.getComposedMessage ( "the.page.size.must.be.smaller.than.14400.by.14400.its.1.by.2", mediaBox.width (),
											mediaBox.height () ) );
		}
		put ( PdfName.MEDIABOX, mediaBox );
		put ( PdfName.RESOURCES, resources );
		if ( rotate != 0 ) {
			put ( PdfName.ROTATE, new PdfNumber ( rotate ) );
		}
		for ( int k = 0; k < boxStrings.length; ++k ) {
			PdfObject rect = boxSize.get ( boxStrings[k] );
			if ( rect != null )
				put ( boxNames[k], rect );
		}
	}

	public boolean isParent () {
		return false;
	}

	void add ( PdfIndirectReference contents ) {
		put ( PdfName.CONTENTS, contents );
	}

}

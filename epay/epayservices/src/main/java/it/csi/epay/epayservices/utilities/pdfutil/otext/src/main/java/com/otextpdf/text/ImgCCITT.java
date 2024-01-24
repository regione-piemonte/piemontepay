/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec.TIFFFaxDecoder;

import java.net.URL;


public class ImgCCITT extends Image {

	public ImgCCITT ( int width, int height, boolean reverseBits, int typeCCITT, int parameters, byte[] data ) throws BadElementException {
		super ( (URL) null );
		if ( typeCCITT != CCITTG4 && typeCCITT != CCITTG3_1D && typeCCITT != CCITTG3_2D )
			throw new BadElementException ( MessageLocalization.getComposedMessage ( "the.ccitt.compression.type.must.be.ccittg4.ccittg3.1d.or.ccittg3.2d" ) );
		if ( reverseBits )
			TIFFFaxDecoder.reverseBits ( data );
		type = IMGRAW;
		scaledHeight = height;
		setTop ( scaledHeight );
		scaledWidth = width;
		setRight ( scaledWidth );
		colorspace = parameters;
		bpc = typeCCITT;
		rawData = data;
		plainWidth = getWidth ();
		plainHeight = getHeight ();
	}
}

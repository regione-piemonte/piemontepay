/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ImgJBIG2;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.RandomAccessFileOrArray;


public class JBIG2Image {

	public static Image getJbig2Image ( RandomAccessFileOrArray ra, int page ) {
		if ( page < 1 )
			throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "the.page.number.must.be.gt.eq.1" ) );

		try {
			JBIG2SegmentReader sr = new JBIG2SegmentReader ( ra );
			sr.read ();
			JBIG2SegmentReader.JBIG2Page p = sr.getPage ( page );
			return new ImgJBIG2 ( p.pageBitmapWidth, p.pageBitmapHeight, p.getData ( true ), sr.getGlobal ( true ) );
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
	}

}

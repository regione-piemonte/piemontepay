/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BadElementException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec.CCITTG4Encoder;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.qrcode.ByteMatrix;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.qrcode.EncodeHintType;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.qrcode.QRCodeWriter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.qrcode.WriterException;

import java.util.Map;


public class BarcodeQRCode {

	ByteMatrix bm;

	public BarcodeQRCode ( String content, int width, int height, Map<EncodeHintType, Object> hints ) {
		try {
			QRCodeWriter qc = new QRCodeWriter ();
			bm = qc.encode ( content, width, height, hints );
		} catch ( WriterException ex ) {
			throw new ExceptionConverter ( ex );
		}
	}

	private byte[] getBitMatrix () {
		int width = bm.getWidth ();
		int height = bm.getHeight ();
		int stride = ( width + 7 ) / 8;
		byte[] b = new byte[stride * height];
		byte[][] mt = bm.getArray ();
		for ( int y = 0; y < height; ++y ) {
			byte[] line = mt[y];
			for ( int x = 0; x < width; ++x ) {
				if ( line[x] != 0 ) {
					int offset = stride * y + x / 8;
					b[offset] |= (byte) ( 0x80 >> ( x % 8 ) );
				}
			}
		}
		return b;
	}

	public Image getImage () throws BadElementException {
		byte[] b = getBitMatrix ();
		byte[] g4 = CCITTG4Encoder.compress ( b, bm.getWidth (), bm.getHeight () );
		return Image.getInstance ( bm.getWidth (), bm.getHeight (), false, Image.CCITTG4, Image.CCITT_BLACKIS1, g4, null );
	}

}

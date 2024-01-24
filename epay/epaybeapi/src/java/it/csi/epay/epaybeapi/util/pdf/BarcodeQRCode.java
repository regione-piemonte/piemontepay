/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.util.pdf;

import it.csi.epay.epaybeapi.util.pdf.qrcode.ByteMatrix;
import it.csi.epay.epaybeapi.util.pdf.qrcode.EncodeHintType;
import it.csi.epay.epaybeapi.util.pdf.qrcode.QRCodeWriter;
import it.csi.epay.epaybeapi.util.pdf.qrcode.WriterException;

import java.awt.*;
import java.awt.image.MemoryImageSource;
import java.util.Map;


public class BarcodeQRCode {

	ByteMatrix bm;

	public BarcodeQRCode ( String content, int width, int height, Map<EncodeHintType, Object> hints ) throws WriterException {

		QRCodeWriter qc = new QRCodeWriter ();
		bm = qc.encode ( content, width, height, hints );

	}

	/**
	 * Creates a <CODE>java.awt.Image</CODE>.
	 *
	 * @param foreground the color of the bars
	 * @param background the color of the background
	 * @return the image
	 */
	public Image createAwtImage ( Color foreground, Color background ) {
		int f = foreground.getRGB ();
		int g = background.getRGB ();
		Canvas canvas = new Canvas ();

		int width = bm.getWidth ();
		int height = bm.getHeight ();
		int[] pix = new int[width * height];
		byte[][] mt = bm.getArray ();
		for ( int y = 0; y < height; ++y ) {
			byte[] line = mt[y];
			for ( int x = 0; x < width; ++x ) {
				pix[y * width + x] = line[x] == 0 ? f : g;
			}
		}

		return canvas.createImage ( new MemoryImageSource ( width, height, pix, 0, width ) );
	}
}

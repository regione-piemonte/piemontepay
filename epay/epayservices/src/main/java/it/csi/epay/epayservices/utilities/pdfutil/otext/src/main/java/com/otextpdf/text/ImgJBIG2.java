/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import java.net.URL;
import java.security.MessageDigest;


public class ImgJBIG2 extends Image {

	private byte[] global;

	public ImgJBIG2 ( int width, int height, byte[] data, byte[] globals ) {
		super ( (URL) null );
		type = JBIG2;
		originalType = ORIGINAL_JBIG2;
		scaledHeight = height;
		setTop ( scaledHeight );
		scaledWidth = width;
		setRight ( scaledWidth );
		bpc = 1;
		colorspace = 1;
		rawData = data;
		plainWidth = getWidth ();
		plainHeight = getHeight ();
		if ( globals != null ) {
			this.global = globals;
			MessageDigest md;
			try {
				md = MessageDigest.getInstance ( "MD5" );
				md.update ( this.global );
				md.digest ();
			} catch ( Exception e ) {
				//ignore
			}

		}
	}

	public byte[] getGlobalBytes () {
		return this.global;
	}

}

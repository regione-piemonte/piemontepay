/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfTemplate;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec.wmf.InputMeta;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec.wmf.MetaDo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class ImgWMF extends Image {

	public ImgWMF ( URL url ) throws BadElementException, IOException {
		super ( url );
		processParameters ();
	}

	public ImgWMF ( byte[] img ) throws BadElementException, IOException {
		super ( (URL) null );
		rawData = img;
		originalData = img;
		processParameters ();
	}

	private void processParameters () throws BadElementException, IOException {
		type = IMGTEMPLATE;
		originalType = ORIGINAL_WMF;
		InputStream is = null;
		try {
			String errorID;
			if ( rawData == null ) {
				is = url.openStream ();
				errorID = url.toString ();
			} else {
				is = new java.io.ByteArrayInputStream ( rawData );
				errorID = "Byte array";
			}
			InputMeta in = new InputMeta ( is );
			if ( in.readInt () != 0x9AC6CDD7 ) {
				throw new BadElementException ( MessageLocalization.getComposedMessage ( "1.is.not.a.valid.placeable.windows.metafile", errorID ) );
			}
			in.readWord ();
			int left = in.readShort ();
			int top = in.readShort ();
			int right = in.readShort ();
			int bottom = in.readShort ();
			int inch = in.readWord ();
			dpiX = 72;
			dpiY = 72;
			scaledHeight = (float) ( bottom - top ) / inch * 72f;
			setTop ( scaledHeight );
			scaledWidth = (float) ( right - left ) / inch * 72f;
			setRight ( scaledWidth );
		} finally {
			if ( is != null ) {
				is.close ();
			}
			plainWidth = getWidth ();
			plainHeight = getHeight ();
		}
	}

	public void readWMF ( PdfTemplate template ) throws IOException, DocumentException {
		setTemplateData ( template );
		template.setWidth ( getWidth () );
		template.setHeight ( getHeight () );
		InputStream is = null;
		try {
			if ( rawData == null ) {
				is = url.openStream ();
			} else {
				is = new java.io.ByteArrayInputStream ( rawData );
			}
			MetaDo meta = new MetaDo ( is, template );
			meta.readAll ();
		} finally {
			if ( is != null ) {
				is.close ();
			}
		}
	}
}

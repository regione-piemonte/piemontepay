/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.SerializeOptions;

import java.io.OutputStream;


public class XMPSerializerHelper {

	public static void serialize ( XMPMetaImpl xmp, OutputStream out,
					SerializeOptions options )
					throws XMPException {
		options = options != null ? options : new SerializeOptions ();

		if ( options.getSort () ) {
			xmp.sort ();
		}
		new XMPSerializerRDF ().serialize ( xmp, out, options );
	}

}

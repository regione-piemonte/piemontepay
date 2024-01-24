/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.xmp;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Version;


@Deprecated
public class PdfSchema extends XmpSchema {

	public static final String DEFAULT_XPATH_ID = "pdf";

	public static final String DEFAULT_XPATH_URI = "http://ns.adobe.com/pdf/1.3/";

	public static final String KEYWORDS = "pdf:Keywords";

	public static final String VERSION = "pdf:PDFVersion";

	public static final String PRODUCER = "pdf:Producer";

	private static final long serialVersionUID = -1541148669123992185L;

	public PdfSchema () {
		super ( "xmlns:" + DEFAULT_XPATH_ID + "=\"" + DEFAULT_XPATH_URI + "\"" );
		addProducer ( Version.getInstance ().getVersion () );
	}

	public void addProducer ( String producer ) {
		setProperty ( PRODUCER, producer );
	}

}

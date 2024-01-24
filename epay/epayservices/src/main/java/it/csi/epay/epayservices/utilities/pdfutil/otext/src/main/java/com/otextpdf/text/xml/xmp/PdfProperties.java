/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.xmp;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPConst;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPMeta;


public class PdfProperties {

	public static final String KEYWORDS = "Keywords";

	public static final String VERSION = "PDFVersion";

	public static final String PRODUCER = "Producer";

	public static final String PART = "part";

	static public void setProducer ( XMPMeta xmpMeta, String producer ) throws XMPException {
		xmpMeta.setProperty ( XMPConst.NS_PDF, PRODUCER, producer );
	}

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.xmp;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPConst;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPMeta;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPUtils;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.PropertyOptions;


public class DublinCoreProperties {

	public static final String CONTRIBUTOR = "contributor";

	public static final String COVERAGE = "coverage";

	public static final String CREATOR = "creator";

	public static final String DATE = "date";

	public static final String DESCRIPTION = "description";

	public static final String FORMAT = "format";

	public static final String IDENTIFIER = "identifier";

	public static final String LANGUAGE = "language";

	public static final String PUBLISHER = "publisher";

	public static final String RELATION = "relation";

	public static final String RIGHTS = "rights";

	public static final String SOURCE = "source";

	public static final String SUBJECT = "subject";

	public static final String TITLE = "title";

	public static final String TYPE = "type";

	static public void setSubject ( XMPMeta xmpMeta, String[] subject ) throws XMPException {
		XMPUtils.removeProperties ( xmpMeta, XMPConst.NS_DC, SUBJECT, true, true );
		for ( String s : subject ) {
			xmpMeta.appendArrayItem ( XMPConst.NS_DC, SUBJECT, new PropertyOptions ( PropertyOptions.ARRAY ), s, null );
		}
	}

}

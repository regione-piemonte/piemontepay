/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.xmp;

@Deprecated
public class DublinCoreSchema extends XmpSchema {

	public static final String DEFAULT_XPATH_ID = "dc";

	public static final String DEFAULT_XPATH_URI = "http://purl.org/dc/elements/1.1/";

	public static final String CONTRIBUTOR = "dc:contributor";

	public static final String COVERAGE = "dc:coverage";

	public static final String CREATOR = "dc:creator";

	public static final String DATE = "dc:date";

	public static final String DESCRIPTION = "dc:description";

	public static final String FORMAT = "dc:format";

	public static final String IDENTIFIER = "dc:identifier";

	public static final String LANGUAGE = "dc:language";

	public static final String PUBLISHER = "dc:publisher";

	public static final String RELATION = "dc:relation";

	public static final String RIGHTS = "dc:rights";

	public static final String SOURCE = "dc:source";

	public static final String SUBJECT = "dc:subject";

	public static final String TITLE = "dc:title";

	public static final String TYPE = "dc:type";

	private static final long serialVersionUID = -4551741356374797330L;

	public DublinCoreSchema () {
		super ( "xmlns:" + DEFAULT_XPATH_ID + "=\"" + DEFAULT_XPATH_URI + "\"" );
		setProperty ( FORMAT, "application/pdf" );
	}

}

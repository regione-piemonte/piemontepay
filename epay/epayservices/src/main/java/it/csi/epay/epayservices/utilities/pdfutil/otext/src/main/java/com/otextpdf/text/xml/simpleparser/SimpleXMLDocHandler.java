/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.simpleparser;

import java.util.Map;


public interface SimpleXMLDocHandler {

	void startElement ( String tag, Map<String, String> h );

	void endElement ( String tag );

	void startDocument ();

	void endDocument ();

	void text ( String str );
}

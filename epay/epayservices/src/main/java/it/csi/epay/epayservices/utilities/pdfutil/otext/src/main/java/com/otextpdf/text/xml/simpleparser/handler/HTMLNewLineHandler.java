/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.simpleparser.handler;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.simpleparser.NewLineHandler;

import java.util.HashSet;
import java.util.Set;


public class HTMLNewLineHandler implements NewLineHandler {

	private final Set<String> newLineTags = new HashSet<> ();

	public HTMLNewLineHandler () {
		newLineTags.add ( "p" );
		newLineTags.add ( "blockquote" );
		newLineTags.add ( "br" );
	}

	public boolean isNewLineTag ( final String tag ) {
		return newLineTags.contains ( tag );
	}

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import java.util.ArrayList;


public class Chapter extends Section {

	private static final long serialVersionUID = 1791000695779357361L;

	public Chapter ( Paragraph title, int number ) {
		super ( title, 1 );
		numbers = new ArrayList<> ();
		numbers.add ( number );
		triggerNewPage = true;
	}

	public Chapter ( String title, int number ) {
		this ( new Paragraph ( title ), number );
	}

	@Override
	public int type () {
		return Element.CHAPTER;
	}

}

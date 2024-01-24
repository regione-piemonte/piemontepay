/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

public class ChapterAutoNumber extends Chapter {

	private static final long serialVersionUID = -9217457637987854167L;

	protected boolean numberSet = false;

	public ChapterAutoNumber ( final String title ) {
		super ( title, 0 );
	}

	public int setAutomaticNumber ( int number ) {
		if ( !numberSet ) {
			number++;
			super.setChapterNumber ( number );
			numberSet = true;
		}
		return number;
	}

}

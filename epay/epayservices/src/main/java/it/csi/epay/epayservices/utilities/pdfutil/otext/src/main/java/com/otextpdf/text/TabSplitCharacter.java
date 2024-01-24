/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfChunk;


public class TabSplitCharacter implements SplitCharacter {

	public static final SplitCharacter TAB = new TabSplitCharacter ();

	public boolean isSplitCharacter ( int start, int current, int end, char[] cc, PdfChunk[] ck ) {
		return true;
	}
}

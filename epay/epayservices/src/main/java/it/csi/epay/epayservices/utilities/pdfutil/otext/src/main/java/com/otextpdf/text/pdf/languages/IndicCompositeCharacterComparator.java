/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.languages;

import java.util.Comparator;


public class IndicCompositeCharacterComparator implements Comparator<String> {

	public int compare ( String o1, String o2 ) {
		if ( o2.length () > o1.length () ) {
			return 1;
		} else if ( o1.length () > o2.length () ) {
			return -1;
		} else {
			return o1.compareTo ( o2 );
		}
	}

}

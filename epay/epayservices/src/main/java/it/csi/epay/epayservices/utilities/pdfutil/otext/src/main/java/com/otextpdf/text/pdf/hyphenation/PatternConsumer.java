/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.hyphenation;

import java.util.ArrayList;


public interface PatternConsumer {

	void addClass ( String chargroup );

	void addException ( String word, ArrayList<Object> hyphenatedword );

	void addPattern ( String pattern, String values );

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.html.simpleparser;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;

import java.io.IOException;
import java.util.Map;


public interface HTMLTagProcessor {

	void startElement ( HTMLWorker worker, String tag, Map<String, String> attrs ) throws DocumentException, IOException;

	void endElement ( HTMLWorker worker, String tag ) throws DocumentException;
}

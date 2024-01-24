/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.security;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;

import java.io.IOException;


public interface XmlLocator {

	org.w3c.dom.Document getDocument ();

	void setDocument ( org.w3c.dom.Document document ) throws IOException, DocumentException;

	String getEncoding ();
}

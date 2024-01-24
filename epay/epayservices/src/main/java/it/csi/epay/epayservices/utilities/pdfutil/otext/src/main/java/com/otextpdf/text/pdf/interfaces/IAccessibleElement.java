/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.AccessibleElementId;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;

import java.util.HashMap;


public interface IAccessibleElement {

	PdfObject getAccessibleAttribute ( final PdfName key );

	void setAccessibleAttribute ( final PdfName key, final PdfObject value );

	HashMap<PdfName, PdfObject> getAccessibleAttributes ();

	PdfName getRole ();

	void setRole ( final PdfName role );

	AccessibleElementId getId ();

	void setId ( final AccessibleElementId id );

	boolean isInline ();
}

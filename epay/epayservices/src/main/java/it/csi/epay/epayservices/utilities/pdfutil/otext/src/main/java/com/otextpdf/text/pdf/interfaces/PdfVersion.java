/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfDeveloperExtension;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;


public interface PdfVersion {

	void setPdfVersion ( char version );

	void setAtLeastPdfVersion ( char version );

	void setPdfVersion ( PdfName version );

	void addDeveloperExtension ( PdfDeveloperExtension de );
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

public class PdfDeveloperExtension {

	public static final PdfDeveloperExtension ADOBE_1_7_EXTENSIONLEVEL3 =
					new PdfDeveloperExtension ( PdfName.ADBE, PdfWriter.PDF_VERSION_1_7, 3 );

	public static final PdfDeveloperExtension ESIC_1_7_EXTENSIONLEVEL2 =
					new PdfDeveloperExtension ( PdfName.ESIC, PdfWriter.PDF_VERSION_1_7, 2 );

	public static final PdfDeveloperExtension ESIC_1_7_EXTENSIONLEVEL5 =
					new PdfDeveloperExtension ( PdfName.ESIC, PdfWriter.PDF_VERSION_1_7, 5 );

	protected PdfName prefix;

	protected PdfName baseversion;

	protected int extensionLevel;

	public PdfDeveloperExtension ( PdfName prefix, PdfName baseversion, int extensionLevel ) {
		this.prefix = prefix;
		this.baseversion = baseversion;
		this.extensionLevel = extensionLevel;
	}

	public PdfName getPrefix () {
		return prefix;
	}

	public PdfName getBaseversion () {
		return baseversion;
	}

	public int getExtensionLevel () {
		return extensionLevel;
	}

	public PdfDictionary getDeveloperExtensions () {
		PdfDictionary developerextensions = new PdfDictionary ();
		developerextensions.put ( PdfName.BASEVERSION, baseversion );
		developerextensions.put ( PdfName.EXTENSIONLEVEL, new PdfNumber ( extensionLevel ) );
		return developerextensions;
	}
}

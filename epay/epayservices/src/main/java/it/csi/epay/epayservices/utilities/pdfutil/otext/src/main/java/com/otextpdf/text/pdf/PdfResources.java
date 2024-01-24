/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

class PdfResources extends PdfDictionary {

	PdfResources () {
		super ();
	}

	void add ( PdfName key, PdfDictionary resource ) {
		if ( resource.size () == 0 )
			return;
		PdfDictionary dic = getAsDict ( key );
		if ( dic == null )
			put ( key, resource );
		else
			dic.putAll ( resource );
	}
}

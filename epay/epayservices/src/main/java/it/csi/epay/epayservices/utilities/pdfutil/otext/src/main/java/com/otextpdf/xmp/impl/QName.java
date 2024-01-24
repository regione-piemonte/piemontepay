/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl;

public class QName {

	private final String prefix;

	private final String localName;

	public QName ( String qname ) {
		int colon = qname.indexOf ( ':' );

		if ( colon >= 0 ) {
			prefix = qname.substring ( 0, colon );
			localName = qname.substring ( colon + 1 );
		} else {
			prefix = "";
			localName = qname;
		}
	}

	public QName ( String prefix, String localName ) {
		this.prefix = prefix;
		this.localName = localName;
	}

	public boolean hasPrefix () {
		return prefix != null && !prefix.isEmpty ();
	}

	public String getPrefix () {
		return prefix;
	}
}

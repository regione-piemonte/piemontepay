/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

public class Header extends Meta {

	private final StringBuffer name;

	public Header ( String name, String content ) {
		super ( Element.HEADER, content );
		this.name = new StringBuffer ( name );
	}

	public String getName () {
		return name.toString ();
	}

}

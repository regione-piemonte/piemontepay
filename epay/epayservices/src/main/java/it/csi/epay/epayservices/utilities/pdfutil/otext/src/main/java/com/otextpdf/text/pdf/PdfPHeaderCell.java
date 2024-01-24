/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

public class PdfPHeaderCell extends PdfPCell {

	public static final int NONE = 0;

	public static final int ROW = 1;

	public static final int COLUMN = 2;

	public static final int BOTH = 3;

	protected int scope;

	protected String name = null;

	public PdfPHeaderCell ( PdfPHeaderCell headerCell ) {
		super ( headerCell );
		role = headerCell.role;
		scope = headerCell.scope;
		name = headerCell.getName ();
	}

	public String getName () {
		return name;
	}

	public void setName ( String name ) {
		this.name = name;
	}

	public int getScope () {
		return scope;
	}

	public void setScope ( int scope ) {
		this.scope = scope;
	}
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl.xpath;

public class XMPPathSegment {

	private String name;

	private int kind;

	private boolean alias;

	private int aliasForm;

	public XMPPathSegment ( String name, int kind ) {
		this.name = name;
		this.kind = kind;
	}

	public int getKind () {
		return kind;
	}

	public void setKind ( int kind ) {
		this.kind = kind;
	}

	public String getName () {
		return name;
	}

	public void setName ( String name ) {
		this.name = name;
	}

	public boolean isAlias () {
		return alias;
	}

	public void setAlias ( boolean alias ) {
		this.alias = alias;
	}

	public int getAliasForm () {
		return aliasForm;
	}

	public void setAliasForm ( int aliasForm ) {
		this.aliasForm = aliasForm;
	}

	public String toString () {
		return name;
	}
}

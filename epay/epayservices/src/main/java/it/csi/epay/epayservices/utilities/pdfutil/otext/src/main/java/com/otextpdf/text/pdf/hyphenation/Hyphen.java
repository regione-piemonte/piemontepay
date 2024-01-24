/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.hyphenation;

import java.io.Serializable;


public class Hyphen implements Serializable {

	private static final long serialVersionUID = -7666138517324763063L;

	public String preBreak;

	public String noBreak;

	public String postBreak;

	Hyphen ( String pre, String no, String post ) {
		preBreak = pre;
		noBreak = no;
		postBreak = post;
	}

	public String toString () {
		if ( noBreak == null
						&& postBreak == null
						&& preBreak != null
						&& preBreak.equals ( "-" ) ) {
			return "-";
		}
		return "{" + preBreak +
						"}{" +
						postBreak +
						"}{" +
						noBreak +
						'}';
	}

}

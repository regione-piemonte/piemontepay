/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

public class PdfIndirectReference extends PdfObject {

	protected int number;

	protected int generation = 0;

	protected PdfIndirectReference () {
		super ( 0 );
	}

	PdfIndirectReference ( int type, int number, int generation ) {
		super ( 0, number + " " + generation + " R" );
		this.number = number;
		this.generation = generation;
	}

	protected PdfIndirectReference ( int type, int number ) {
		this ( type, number, 0 );
	}

	public int getNumber () {
		return number;
	}

	public int getGeneration () {
		return generation;
	}

	public String toString () {
		return number + " " + generation + " R";
	}
}

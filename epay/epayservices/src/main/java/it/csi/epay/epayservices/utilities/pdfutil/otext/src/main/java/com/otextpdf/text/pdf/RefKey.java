/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

public class RefKey {

	int num;

	int gen;

	RefKey ( int num, int gen ) {
		this.num = num;
		this.gen = gen;
	}

	public RefKey ( PdfIndirectReference ref ) {
		num = ref.getNumber ();
		gen = ref.getGeneration ();
	}

	RefKey ( PRIndirectReference ref ) {
		num = ref.getNumber ();
		gen = ref.getGeneration ();
	}

	@Override
	public int hashCode () {
		return ( gen << 16 ) + num;
	}

	@Override
	public boolean equals ( Object o ) {
		if ( !( o instanceof RefKey ) )
			return false;
		RefKey other = (RefKey) o;
		return this.gen == other.gen && this.num == other.num;
	}

	@Override
	public String toString () {
		return Integer.toString ( num ) + ' ' + gen;
	}
}


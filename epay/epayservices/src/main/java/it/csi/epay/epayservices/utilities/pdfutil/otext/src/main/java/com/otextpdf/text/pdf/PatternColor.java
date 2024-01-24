/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

public class PatternColor extends ExtendedColor {

	private static final long serialVersionUID = -1185448552860615964L;

	PdfPatternPainter painter;

	public PatternColor ( PdfPatternPainter painter ) {
		super ( TYPE_PATTERN, .5f, .5f, .5f );
		this.painter = painter;
	}

	public PdfPatternPainter getPainter () {
		return this.painter;
	}

	public boolean equals ( Object obj ) {
		return obj instanceof PatternColor && ( ( (PatternColor) obj ).painter ).equals ( this.painter );
	}

	public int hashCode () {
		return painter.hashCode ();
	}
}

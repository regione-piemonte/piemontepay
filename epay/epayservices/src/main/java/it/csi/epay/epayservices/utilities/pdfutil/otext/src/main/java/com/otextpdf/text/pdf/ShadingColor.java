/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import java.io.Serializable;


public class ShadingColor extends ExtendedColor implements Serializable {

	private static final long serialVersionUID = 4817929454941328671L;

	PdfShadingPattern shadingPattern;

	public ShadingColor ( PdfShadingPattern shadingPattern ) {
		super ( TYPE_SHADING, .5f, .5f, .5f );
		this.shadingPattern = shadingPattern;
	}

	public PdfShadingPattern getPdfShadingPattern () {
		return shadingPattern;
	}

	public boolean equals ( Object obj ) {
		return obj instanceof ShadingColor && ( ( (ShadingColor) obj ).shadingPattern ).equals ( this.shadingPattern );
	}

	public int hashCode () {
		return shadingPattern.hashCode ();
	}

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import java.io.Serializable;


public class SpotColor extends ExtendedColor implements Serializable {

	private static final long serialVersionUID = -6257004582113248079L;

	PdfSpotColor spot;

	float tint;

	public SpotColor ( PdfSpotColor spot, float tint ) {
		super ( TYPE_SEPARATION,
						( spot.getAlternativeCS ().getRed () / 255f - 1f ) * tint + 1,
						( spot.getAlternativeCS ().getGreen () / 255f - 1f ) * tint + 1,
						( spot.getAlternativeCS ().getBlue () / 255f - 1f ) * tint + 1 );
		this.spot = spot;
		this.tint = tint;
	}

	public PdfSpotColor getPdfSpotColor () {
		return spot;
	}

	public float getTint () {
		return tint;
	}

	public boolean equals ( Object obj ) {
		return obj instanceof SpotColor && ( ( (SpotColor) obj ).spot ).equals ( this.spot ) && ( (SpotColor) obj ).tint == this.tint;
	}

	public int hashCode () {
		return spot.hashCode () ^ Float.floatToIntBits ( tint );
	}
}

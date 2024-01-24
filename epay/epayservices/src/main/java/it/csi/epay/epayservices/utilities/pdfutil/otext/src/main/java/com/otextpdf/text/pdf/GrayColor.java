/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

public class GrayColor extends ExtendedColor {

	public static final GrayColor GRAYBLACK = new GrayColor ( 0f );

	public static final GrayColor GRAYWHITE = new GrayColor ( 1f );

	private static final long serialVersionUID = -6571835680819282746L;

	private final float gray;

	public GrayColor ( int intGray ) {
		this ( intGray / 255f );
	}

	public GrayColor ( float floatGray ) {
		super ( TYPE_GRAY, floatGray, floatGray, floatGray );
		gray = normalize ( floatGray );
	}

	public float getGray () {
		return gray;
	}

	public boolean equals ( Object obj ) {
		return obj instanceof GrayColor && ( (GrayColor) obj ).gray == this.gray;
	}

	public int hashCode () {
		return Float.floatToIntBits ( gray );
	}

}

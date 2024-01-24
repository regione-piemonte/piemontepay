/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;

import java.io.Serializable;


public abstract class ExtendedColor extends BaseColor implements Serializable {

	public static final int TYPE_RGB = 0;

	public static final int TYPE_GRAY = 1;

	public static final int TYPE_CMYK = 2;

	public static final int TYPE_SEPARATION = 3;

	public static final int TYPE_PATTERN = 4;

	public static final int TYPE_SHADING = 5;

	private static final long serialVersionUID = 2722660170712380080L;

	protected int type;

	public ExtendedColor ( int type ) {
		super ( 0, 0, 0 );
		this.type = type;
	}

	public ExtendedColor ( int type, float red, float green, float blue ) {
		super ( normalize ( red ), normalize ( green ), normalize ( blue ) );
		this.type = type;
	}

	public ExtendedColor ( int type, int red, int green, int blue, int alpha ) {
		super ( normalize ( (float) red / 0xFF ), normalize ( (float) green / 0xFF ), normalize ( (float) blue / 0xFF ), normalize ( (float) alpha / 0xFF ) );
		this.type = type;
	}

	public static int getType ( BaseColor color ) {
		if ( color instanceof ExtendedColor )
			return ( (ExtendedColor) color ).getType ();
		return TYPE_RGB;
	}

	static float normalize ( float value ) {
		if ( value < 0 )
			return 0;
		if ( value > 1 )
			return 1;
		return value;
	}

	public int getType () {
		return type;
	}
}

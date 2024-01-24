/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec.wmf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;

import java.io.IOException;


public class MetaBrush extends MetaObject {

	public static final int BS_SOLID = 0;

	public static final int BS_NULL = 1;

	public static final int BS_HATCHED = 2;

	public static final int BS_PATTERN = 3;

	public static final int BS_DIBPATTERN = 5;

	public static final int HS_HORIZONTAL = 0;

	public static final int HS_VERTICAL = 1;

	public static final int HS_FDIAGONAL = 2;

	public static final int HS_BDIAGONAL = 3;

	public static final int HS_CROSS = 4;

	public static final int HS_DIAGCROSS = 5;

	int style = BS_SOLID;

	int hatch;

	BaseColor color = BaseColor.WHITE;

	public MetaBrush () {
		type = META_BRUSH;
	}

	public void init ( InputMeta in ) throws IOException {
		style = in.readWord ();
		color = in.readColor ();
		hatch = in.readWord ();
	}

	public int getStyle () {
		return style;
	}

	public BaseColor getColor () {
		return color;
	}
}

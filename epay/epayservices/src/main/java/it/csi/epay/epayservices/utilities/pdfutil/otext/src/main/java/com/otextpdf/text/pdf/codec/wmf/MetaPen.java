/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec.wmf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;

import java.io.IOException;


public class MetaPen extends MetaObject {

	public static final int PS_SOLID = 0;

	public static final int PS_DASH = 1;

	public static final int PS_DOT = 2;

	public static final int PS_DASHDOT = 3;

	public static final int PS_DASHDOTDOT = 4;

	public static final int PS_NULL = 5;

	public static final int PS_INSIDEFRAME = 6;

	int style = PS_SOLID;

	int penWidth = 1;

	BaseColor color = BaseColor.BLACK;

	public MetaPen () {
		type = META_PEN;
	}

	public void init ( InputMeta in ) throws IOException {
		style = in.readWord ();
		penWidth = in.readShort ();
		in.readWord ();
		color = in.readColor ();
	}

	public int getStyle () {
		return style;
	}

	public int getPenWidth () {
		return penWidth;
	}

	public BaseColor getColor () {
		return color;
	}
}

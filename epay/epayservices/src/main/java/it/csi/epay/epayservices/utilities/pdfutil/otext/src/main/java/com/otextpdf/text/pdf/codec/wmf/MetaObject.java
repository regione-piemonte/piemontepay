/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec.wmf;

public class MetaObject {

	public static final int META_NOT_SUPPORTED = 0;

	public static final int META_PEN = 1;

	public static final int META_BRUSH = 2;

	public static final int META_FONT = 3;

	public int type = META_NOT_SUPPORTED;

	public MetaObject () {
	}

	public int getType () {
		return type;
	}

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

public interface FontProvider {

	boolean isRegistered ( String fontname );

	Font getFont ( String fontname, String encoding, boolean embedded, float size, int style, BaseColor color );
}

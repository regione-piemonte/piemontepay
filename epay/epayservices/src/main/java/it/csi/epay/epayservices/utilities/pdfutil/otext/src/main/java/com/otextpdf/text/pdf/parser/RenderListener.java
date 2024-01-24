/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.parser;

public interface RenderListener {

	void beginTextBlock ();

	void renderText ( TextRenderInfo renderInfo );

	void endTextBlock ();

	void renderImage ( ImageRenderInfo renderInfo );

}

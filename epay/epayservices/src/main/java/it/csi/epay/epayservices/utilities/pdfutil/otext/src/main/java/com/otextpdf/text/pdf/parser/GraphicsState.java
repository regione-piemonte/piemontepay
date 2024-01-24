/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.parser;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.CMapAwareDocumentFont;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;


public class GraphicsState {

	Matrix ctm;

	float characterSpacing;

	float wordSpacing;

	float horizontalScaling;

	float leading;

	CMapAwareDocumentFont font;

	float fontSize;

	int renderMode;

	float rise;

	boolean knockout;

	PdfName colorSpaceFill;

	PdfName colorSpaceStroke;

	BaseColor fillColor;

	BaseColor strokeColor;

	public GraphicsState () {
		ctm = new Matrix ();
		characterSpacing = 0;
		wordSpacing = 0;
		horizontalScaling = 1.0f;
		leading = 0;
		font = null;
		fontSize = 0;
		renderMode = 0;
		rise = 0;
		knockout = true;
		colorSpaceFill = null;
		colorSpaceStroke = null;
		fillColor = null;
		strokeColor = null;
	}

	public GraphicsState ( GraphicsState source ) {
		// note: all of the following are immutable, with the possible exception of font
		// so it is safe to copy them as-is
		ctm = source.ctm;
		characterSpacing = source.characterSpacing;
		wordSpacing = source.wordSpacing;
		horizontalScaling = source.horizontalScaling;
		leading = source.leading;
		font = source.font;
		fontSize = source.fontSize;
		renderMode = source.renderMode;
		rise = source.rise;
		knockout = source.knockout;
		colorSpaceFill = source.colorSpaceFill;
		colorSpaceStroke = source.colorSpaceStroke;
		fillColor = source.fillColor;
		strokeColor = source.strokeColor;
	}

	public float getLeading () {
		return leading;
	}

	public CMapAwareDocumentFont getFont () {
		return font;
	}

	public float getFontSize () {
		return fontSize;
	}

}

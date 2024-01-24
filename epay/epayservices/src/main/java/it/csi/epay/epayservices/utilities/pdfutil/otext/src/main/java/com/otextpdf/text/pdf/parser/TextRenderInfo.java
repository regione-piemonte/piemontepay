/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.parser;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.DocumentFont;


public class TextRenderInfo {

	private final String text;

	private final Matrix textToUserSpaceTransformMatrix;

	private final GraphicsState gs;

	TextRenderInfo ( String text, GraphicsState gs, Matrix textMatrix ) {
		this.text = text;
		this.textToUserSpaceTransformMatrix = textMatrix.multiply ( gs.ctm );
		this.gs = gs;
	}

	public String getText () {
		return text;
	}

	float getUnscaledWidth () {
		return getStringWidth ( text );
	}

	public LineSegment getBaseline () {
		return getUnscaledBaselineWithOffset ( 0 + gs.rise ).transformBy ( textToUserSpaceTransformMatrix );
	}

	private LineSegment getUnscaledBaselineWithOffset ( float yOffset ) {

		float correctedUnscaledWidth = getUnscaledWidth () - gs.characterSpacing * gs.horizontalScaling;

		return new LineSegment ( new Vector ( 0, yOffset, 1 ), new Vector ( correctedUnscaledWidth, yOffset, 1 ) );
	}

	public DocumentFont getFont () {
		return gs.getFont ();
	}

	public float getRise () {
		if ( gs.rise == 0 )
			return 0;

		return convertHeightFromTextSpaceToUserSpace ( gs.rise );
	}

	private float convertWidthFromTextSpaceToUserSpace ( float width ) {
		LineSegment textSpace = new LineSegment ( new Vector ( 0, 0, 1 ), new Vector ( width, 0, 1 ) );
		LineSegment userSpace = textSpace.transformBy ( textToUserSpaceTransformMatrix );
		return userSpace.getLength ();
	}

	private float convertHeightFromTextSpaceToUserSpace ( float height ) {
		LineSegment textSpace = new LineSegment ( new Vector ( 0, 0, 1 ), new Vector ( 0, height, 1 ) );
		LineSegment userSpace = textSpace.transformBy ( textToUserSpaceTransformMatrix );
		return userSpace.getLength ();
	}

	public float getSingleSpaceWidth () {
		return convertWidthFromTextSpaceToUserSpace ( getUnscaledFontSpaceWidth () );
	}

	private float getUnscaledFontSpaceWidth () {
		char charToUse = ' ';
		if ( gs.font.getWidth ( charToUse ) == 0 )
			charToUse = '\u00A0';
		return getStringWidth ( String.valueOf ( charToUse ) );
	}

	private float getStringWidth ( String string ) {
		DocumentFont font = gs.font;
		char[] chars = string.toCharArray ();
		float totalWidth = 0;
		for ( char aChar : chars ) {
			float w = font.getWidth ( aChar ) / 1000.0f;
			float wordSpacing = aChar == 32 ? gs.wordSpacing : 0f;
			totalWidth += ( w * gs.fontSize + gs.characterSpacing + wordSpacing ) * gs.horizontalScaling;
		}

		return totalWidth;
	}

}

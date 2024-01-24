/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.draw;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfContentByte;


public class LineSeparator extends VerticalPositionMark {

	protected float lineWidth;

	protected float percentage;

	protected BaseColor lineColor;

	protected int alignment;

	public LineSeparator ( float lineWidth, float percentage, BaseColor lineColor, int align, float offset ) {
		this.lineWidth = lineWidth;
		this.percentage = percentage;
		this.lineColor = lineColor;
		this.alignment = align;
		this.offset = offset;
	}

	public void draw ( PdfContentByte canvas, float llx, float lly, float urx, float ury, float y ) {
		canvas.saveState ();
		drawLine ( canvas, llx, urx, y );
		canvas.restoreState ();
	}

	public void drawLine ( PdfContentByte canvas, float leftX, float rightX, float y ) {
		float w;
		if ( getPercentage () < 0 )
			w = -getPercentage ();
		else
			w = ( rightX - leftX ) * getPercentage () / 100.0f;
		float s;
		switch ( getAlignment () ) {
		case Element.ALIGN_LEFT:
			s = 0;
			break;
		case Element.ALIGN_RIGHT:
			s = rightX - leftX - w;
			break;
		default:
			s = ( rightX - leftX - w ) / 2;
			break;
		}
		canvas.setLineWidth ( getLineWidth () );
		if ( getLineColor () != null )
			canvas.setColorStroke ( getLineColor () );
		canvas.moveTo ( s + leftX, y + offset );
		canvas.lineTo ( s + w + leftX, y + offset );
		canvas.stroke ();
	}

	public float getLineWidth () {
		return lineWidth;
	}

	public float getPercentage () {
		return percentage;
	}

	public void setPercentage ( float percentage ) {
		this.percentage = percentage;
	}

	public BaseColor getLineColor () {
		return lineColor;
	}

	public int getAlignment () {
		return alignment;
	}

	public void setAlignment ( int align ) {
		this.alignment = align;
	}
}

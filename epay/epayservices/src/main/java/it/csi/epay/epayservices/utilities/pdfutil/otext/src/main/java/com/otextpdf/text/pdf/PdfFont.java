/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;


class PdfFont implements Comparable<PdfFont> {

	private final BaseFont font;

	private final float size;

	protected float hScale = 1;

	PdfFont ( BaseFont bf, float size ) {
		this.size = size;
		font = bf;
	}

	static PdfFont getDefaultFont () {
		try {
			BaseFont bf = BaseFont.createFont ( BaseFont.HELVETICA, BaseFont.WINANSI, false );
			return new PdfFont ( bf, 12 );
		} catch ( Exception ee ) {
			throw new ExceptionConverter ( ee );
		}
	}

	public int compareTo ( PdfFont pdfFont ) {
		if ( pdfFont == null ) {
			return -1;
		}
		try {
			if ( font != pdfFont.font ) {
				return 1;
			}
			if ( this.size () != pdfFont.size () ) {
				return 2;
			}
			return 0;
		} catch ( ClassCastException cce ) {
			return -2;
		}
	}

	float size () {
		return size;
	}

	float width () {
		return width ( ' ' );
	}

	float width ( int character ) {
		return font.getWidthPoint ( character, size ) * hScale;
	}

	float width ( String s ) {
		return font.getWidthPoint ( s, size ) * hScale;
	}

	BaseFont getFont () {
		return font;
	}

	float getHorizontalScaling () {
		return hScale;
	}

	void setHorizontalScaling ( float hScale ) {
		this.hScale = hScale;
	}
}

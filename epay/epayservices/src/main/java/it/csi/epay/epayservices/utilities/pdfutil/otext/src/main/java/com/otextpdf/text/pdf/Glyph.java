/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

public class Glyph {

	public final int code;

	public final int width;

	public final String chars;

	public Glyph ( int code, int width, String chars ) {
		this.code = code;
		this.width = width;
		this.chars = chars;
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( chars == null ) ? 0 : chars.hashCode () );
		result = prime * result + code;
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals ( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass () != obj.getClass () )
			return false;
		Glyph other = (Glyph) obj;
		if ( chars == null ) {
			if ( other.chars != null )
				return false;
		} else if ( !chars.equals ( other.chars ) )
			return false;
		if ( code != other.code )
			return false;
		return width == other.width;
	}

	@Override
	public String toString () {
		return Glyph.class.getSimpleName () + " [id=" + code + ", width=" + width + ", chars=" + chars + "]";
	}

}

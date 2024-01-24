/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.languages;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.Glyph;

import java.util.List;


abstract class IndicGlyphRepositioner implements GlyphRepositioner {

	public void repositionGlyphs ( List<Glyph> glyphList ) {

		for ( int i = 0; i < glyphList.size (); i++ ) {
			Glyph glyph = glyphList.get ( i );
			Glyph nextGlyph = getNextGlyph ( glyphList, i );

			if ( ( nextGlyph != null )
							&& getCharactersToBeShiftedLeftByOnePosition ().contains (
							nextGlyph.chars ) ) {
				glyphList.set ( i, nextGlyph );
				glyphList.set ( i + 1, glyph );
				i++;
			}
		}

	}

	abstract List<String> getCharactersToBeShiftedLeftByOnePosition ();

	private Glyph getNextGlyph ( List<Glyph> glyphs, int currentIndex ) {
		if ( currentIndex + 1 < glyphs.size () ) {
			return glyphs.get ( currentIndex + 1 );
		} else {
			return null;
		}
	}

}

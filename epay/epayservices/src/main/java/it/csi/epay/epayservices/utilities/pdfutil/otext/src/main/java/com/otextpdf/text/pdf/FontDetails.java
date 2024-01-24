/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Utilities;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.otf.Language;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.languages.BanglaGlyphRepositioner;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.languages.GlyphRepositioner;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.languages.IndicCompositeCharacterComparator;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


class FontDetails {

	protected boolean subset = true;

	PdfIndirectReference indirectReference;

	PdfName fontName;

	BaseFont baseFont;

	TrueTypeFontUnicode ttu;

	CJKFont cjkFont;

	byte[] shortTag;

	HashMap<Integer, int[]> longTag;

	IntHashtable cjkTag;

	int fontType;

	boolean symbolic;

	FontDetails ( PdfName fontName, PdfIndirectReference indirectReference, BaseFont baseFont ) {
		this.fontName = fontName;
		this.indirectReference = indirectReference;
		this.baseFont = baseFont;
		fontType = baseFont.getFontType ();
		switch ( fontType ) {
		case BaseFont.FONT_TYPE_T1:
		case BaseFont.FONT_TYPE_TT:
			shortTag = new byte[256];
			break;
		case BaseFont.FONT_TYPE_CJK:
			cjkTag = new IntHashtable ();
			cjkFont = (CJKFont) baseFont;
			break;
		case BaseFont.FONT_TYPE_TTUNI:
			longTag = new HashMap<> ();
			ttu = (TrueTypeFontUnicode) baseFont;
			symbolic = baseFont.isFontSpecific ();
			break;
		}
	}

	PdfIndirectReference getIndirectReference () {
		return indirectReference;
	}

	PdfName getFontName () {
		return fontName;
	}

	BaseFont getBaseFont () {
		return baseFont;
	}

	byte[] convertToBytes ( String text ) {
		byte[] b = null;
		switch ( fontType ) {
		case BaseFont.FONT_TYPE_T3:
			return baseFont.convertToBytes ( text );
		case BaseFont.FONT_TYPE_T1:
		case BaseFont.FONT_TYPE_TT: {
			b = baseFont.convertToBytes ( text );
			for ( byte value : b )
				shortTag[value & 0xff] = 1;
			break;
		}
		case BaseFont.FONT_TYPE_CJK: {
			int len = text.length ();
			if ( cjkFont.isIdentity () ) {
				for ( int k = 0; k < len; ++k ) {
					cjkTag.put ( text.charAt ( k ), 0 );
				}
			} else {
				for ( int k = 0; k < len; ++k ) {
					int val;
					if ( Utilities.isSurrogatePair ( text, k ) ) {
						val = Utilities.convertToUtf32 ( text, k );
						k++;
					} else {
						val = text.charAt ( k );
					}
					cjkTag.put ( cjkFont.getCidCode ( val ), 0 );
				}
			}
			b = cjkFont.convertToBytes ( text );
			break;
		}
		case BaseFont.FONT_TYPE_DOCUMENT: {
			b = baseFont.convertToBytes ( text );
			break;
		}
		case BaseFont.FONT_TYPE_TTUNI: {
			try {
				int len = text.length ();
				int[] metrics;
				char[] glyph = new char[len];
				int i = 0;
				if ( symbolic ) {
					b = PdfEncodings.convertToBytes ( text, "symboltt" );
					len = b.length;
					for ( int k = 0; k < len; ++k ) {
						metrics = ttu.getMetricsTT ( b[k] & 0xff );
						if ( metrics == null )
							continue;
						longTag.put ( metrics[0], new int[] { metrics[0], metrics[1], ttu.getUnicodeDifferences ( b[k] & 0xff ) } );
						glyph[i++] = (char) metrics[0];
					}
				} else if ( canApplyGlyphSubstitution () ) {
					return convertToBytesAfterGlyphSubstitution ( text );
				} else {
					for ( int k = 0; k < len; ++k ) {
						int val;
						if ( Utilities.isSurrogatePair ( text, k ) ) {
							val = Utilities.convertToUtf32 ( text, k );
							k++;
						} else {
							val = text.charAt ( k );
						}
						metrics = ttu.getMetricsTT ( val );
						if ( metrics == null )
							continue;
						int m0 = metrics[0];
						Integer gl = m0;
						if ( !longTag.containsKey ( gl ) )
							longTag.put ( gl, new int[] { m0, metrics[1], val } );
						glyph[i++] = (char) m0;
					}
				}
				String s = new String ( glyph, 0, i );
				b = s.getBytes ( CJKFont.CJK_ENCODING );
			} catch ( UnsupportedEncodingException e ) {
				throw new ExceptionConverter ( e );
			}
			break;
		}
		}
		return b;
	}

	private boolean canApplyGlyphSubstitution () {
		return ( fontType == BaseFont.FONT_TYPE_TTUNI ) && ( ttu.getGlyphSubstitutionMap () != null );
	}

	private byte[] convertToBytesAfterGlyphSubstitution ( final String text ) throws UnsupportedEncodingException {

		if ( !canApplyGlyphSubstitution () ) {
			throw new IllegalArgumentException ( "Make sure the font type if TTF Unicode and a valid GlyphSubstitutionTable exists!" );
		}

		Map<String, Glyph> glyphSubstitutionMap = ttu.getGlyphSubstitutionMap ();

		// generate a regex from the characters to be substituted

		// for Indic languages: push back the CompositeCharacters with smaller length
		Set<String> compositeCharacters = new TreeSet<> ( new IndicCompositeCharacterComparator () );
		compositeCharacters.addAll ( glyphSubstitutionMap.keySet () );

		// convert the text to a list of Glyph, also take care of the substitution
		ArrayBasedStringTokenizer tokenizer = new ArrayBasedStringTokenizer ( compositeCharacters.toArray ( new String[0] ) );
		String[] tokens = tokenizer.tokenize ( text );

		List<Glyph> glyphList = new ArrayList<> ( 50 );

		for ( String token : tokens ) {

			// first check whether this is in the substitution map
			Glyph subsGlyph = glyphSubstitutionMap.get ( token );

			if ( subsGlyph != null ) {
				glyphList.add ( subsGlyph );
			} else {
				// break up the string into individual characters
				for ( char c : token.toCharArray () ) {
					int[] metrics = ttu.getMetricsTT ( c );
					int glyphCode = metrics[0];
					int glyphWidth = metrics[1];
					glyphList.add ( new Glyph ( glyphCode, glyphWidth, String.valueOf ( c ) ) );
				}
			}

		}

		GlyphRepositioner glyphRepositioner = getGlyphRepositioner ();

		if ( glyphRepositioner != null ) {
			glyphRepositioner.repositionGlyphs ( glyphList );
		}

		char[] charEncodedGlyphCodes = new char[glyphList.size ()];

		// process each Glyph thus obtained
		for ( int i = 0; i < glyphList.size (); i++ ) {
			Glyph glyph = glyphList.get ( i );
			charEncodedGlyphCodes[i] = (char) glyph.code;
			Integer glyphCode = glyph.code;

			if ( !longTag.containsKey ( glyphCode ) ) {
				// FIXME: this is buggy as the 3rd arg. should be a String as a Glyph can represent more than 1 char
				longTag.put ( glyphCode, new int[] { glyph.code, glyph.width, glyph.chars.charAt ( 0 ) } );
			}
		}

		return new String ( charEncodedGlyphCodes ).getBytes ( CJKFont.CJK_ENCODING );
	}

	private GlyphRepositioner getGlyphRepositioner () {
		Language language = ttu.getSupportedLanguage ();

		if ( language == null ) {
			throw new IllegalArgumentException ( "The supported language field cannot be null in " + ttu.getClass ().getName () );
		}

		if ( language == Language.BENGALI ) {
			return new BanglaGlyphRepositioner ( Collections.unmodifiableMap ( ttu.cmap31 ), ttu.getGlyphSubstitutionMap () );
		}
		return null;
	}

	public void writeFont ( PdfWriter writer ) {
		try {
			switch ( fontType ) {
			case BaseFont.FONT_TYPE_T3:
				baseFont.writeFont ( writer, indirectReference, null );
				break;
			case BaseFont.FONT_TYPE_T1:
			case BaseFont.FONT_TYPE_TT: {
				int firstChar;
				int lastChar;
				for ( firstChar = 0; firstChar < 256; ++firstChar ) {
					if ( shortTag[firstChar] != 0 )
						break;
				}
				for ( lastChar = 255; lastChar >= firstChar; --lastChar ) {
					if ( shortTag[lastChar] != 0 )
						break;
				}
				if ( firstChar > 255 ) {
					firstChar = 255;
				}
				baseFont.writeFont ( writer, indirectReference,
								new Object[] { firstChar, lastChar, shortTag, subset } );
				break;
			}
			case BaseFont.FONT_TYPE_CJK:
				baseFont.writeFont ( writer, indirectReference, new Object[] { cjkTag } );
				break;
			case BaseFont.FONT_TYPE_TTUNI:
				baseFont.writeFont ( writer, indirectReference, new Object[] { longTag, subset } );
				break;
			}
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
	}

	public boolean isSubset () {
		return subset;
	}

	public void setSubset ( boolean subset ) {
		this.subset = subset;
	}

}

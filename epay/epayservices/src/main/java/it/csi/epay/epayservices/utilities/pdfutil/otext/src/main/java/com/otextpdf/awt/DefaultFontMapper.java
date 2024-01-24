/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.BaseFont;

import java.awt.*;
import java.util.HashMap;


public class DefaultFontMapper implements FontMapper {

	private final HashMap<String, String> aliases = new HashMap<> ();

	private final HashMap<String, BaseFontParameters> mapper = new HashMap<> ();

	public BaseFont awtToPdf ( Font font ) {
		try {
			BaseFontParameters p = getBaseFontParameters ( font.getFontName () );
			if ( p != null )
				return BaseFont.createFont ( p.fontName, p.encoding, p.embedded, p.cached, p.ttfAfm, p.pfb );
			String fontKey;
			String logicalName = font.getName ();

			if ( logicalName.equalsIgnoreCase ( "DialogInput" ) || logicalName.equalsIgnoreCase ( "Monospaced" ) || logicalName.equalsIgnoreCase (
							"Courier" ) ) {

				if ( font.isItalic () ) {
					if ( font.isBold () ) {
						fontKey = BaseFont.COURIER_BOLDOBLIQUE;

					} else {
						fontKey = BaseFont.COURIER_OBLIQUE;
					}

				} else {
					if ( font.isBold () ) {
						fontKey = BaseFont.COURIER_BOLD;

					} else {
						fontKey = BaseFont.COURIER;
					}
				}

			} else if ( logicalName.equalsIgnoreCase ( "Serif" ) || logicalName.equalsIgnoreCase ( "TimesRoman" ) ) {

				if ( font.isItalic () ) {
					if ( font.isBold () ) {
						fontKey = BaseFont.TIMES_BOLDITALIC;

					} else {
						fontKey = BaseFont.TIMES_ITALIC;
					}

				} else {
					if ( font.isBold () ) {
						fontKey = BaseFont.TIMES_BOLD;

					} else {
						fontKey = BaseFont.TIMES_ROMAN;
					}
				}

			} else {

				if ( font.isItalic () ) {
					if ( font.isBold () ) {
						fontKey = BaseFont.HELVETICA_BOLDOBLIQUE;

					} else {
						fontKey = BaseFont.HELVETICA_OBLIQUE;
					}

				} else {
					if ( font.isBold () ) {
						fontKey = BaseFont.HELVETICA_BOLD;
					} else {
						fontKey = BaseFont.HELVETICA;
					}
				}
			}
			return BaseFont.createFont ( fontKey, BaseFont.CP1252, false );
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
	}

	public BaseFontParameters getBaseFontParameters ( String name ) {
		String alias = aliases.get ( name );
		if ( alias == null )
			return mapper.get ( name );
		BaseFontParameters p = mapper.get ( alias );
		if ( p == null )
			return mapper.get ( name );
		else
			return p;
	}

	public HashMap<String, BaseFontParameters> getMapper () {
		return mapper;
	}

	public HashMap<String, String> getAliases () {
		return aliases;
	}

	public static class BaseFontParameters {

		public String fontName;

		public String encoding;

		public boolean embedded;

		public boolean cached;

		public byte[] ttfAfm;

		public byte[] pfb;

		public BaseFontParameters ( String fontName ) {
			this.fontName = fontName;
			encoding = BaseFont.CP1252;
			embedded = BaseFont.EMBEDDED;
			cached = BaseFont.CACHED;
		}
	}
}

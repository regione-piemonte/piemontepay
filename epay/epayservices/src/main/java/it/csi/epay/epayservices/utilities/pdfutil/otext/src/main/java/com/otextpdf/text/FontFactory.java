/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.BaseFont;


public final class FontFactory {

	public static final String COURIER = BaseFont.COURIER;

	public static final String COURIER_BOLD = BaseFont.COURIER_BOLD;

	public static final String COURIER_OBLIQUE = BaseFont.COURIER_OBLIQUE;

	public static final String COURIER_BOLDOBLIQUE = BaseFont.COURIER_BOLDOBLIQUE;

	public static final String HELVETICA = BaseFont.HELVETICA;

	public static final String HELVETICA_BOLD = BaseFont.HELVETICA_BOLD;

	public static final String HELVETICA_OBLIQUE = BaseFont.HELVETICA_OBLIQUE;

	public static final String HELVETICA_BOLDOBLIQUE = BaseFont.HELVETICA_BOLDOBLIQUE;

	public static final String SYMBOL = BaseFont.SYMBOL;

	public static final String TIMES = "Times";

	public static final String TIMES_ROMAN = BaseFont.TIMES_ROMAN;

	public static final String TIMES_BOLD = BaseFont.TIMES_BOLD;

	public static final String TIMES_ITALIC = BaseFont.TIMES_ITALIC;

	public static final String TIMES_BOLDITALIC = BaseFont.TIMES_BOLDITALIC;

	public static final String ZAPFDINGBATS = BaseFont.ZAPFDINGBATS;

	private static final FontFactoryImp fontImp = new FontFactoryImp ();

	public static String defaultEncoding = BaseFont.WINANSI;

	public static boolean defaultEmbedding = BaseFont.NOT_EMBEDDED;

	private FontFactory () {
	}

	public static Font getFont ( final String fontname, final String encoding, final boolean embedded, final float size, final int style,
					final BaseColor color ) {
		return fontImp.getFont ( fontname, encoding, embedded, size, style, color );
	}

	public static Font getFont ( final String fontname, final String encoding, final boolean embedded, final float size, final int style, final BaseColor color,
					final boolean cached ) {
		return fontImp.getFont ( fontname, encoding, embedded, size, style, color, cached );
	}

	public static Font getFont ( final String fontname, final String encoding, final boolean embedded, final float size, final int style ) {
		return getFont ( fontname, encoding, embedded, size, style, null );
	}

	public static Font getFont ( final String fontname, final String encoding, final boolean embedded, final float size ) {
		return getFont ( fontname, encoding, embedded, size, Font.UNDEFINED, null );
	}

	public static Font getFont ( final String fontname, final String encoding, final boolean embedded ) {
		return getFont ( fontname, encoding, embedded, Font.UNDEFINED, Font.UNDEFINED, null );
	}

	public static Font getFont ( final String fontname, final String encoding, final float size, final int style, final BaseColor color ) {
		return getFont ( fontname, encoding, defaultEmbedding, size, style, color );
	}

	public static Font getFont ( final String fontname, final String encoding, final float size, final int style ) {
		return getFont ( fontname, encoding, defaultEmbedding, size, style, null );
	}

	public static Font getFont ( final String fontname, final String encoding, final float size ) {
		return getFont ( fontname, encoding, defaultEmbedding, size, Font.UNDEFINED, null );
	}

	public static Font getFont ( final String fontname, final String encoding ) {
		return getFont ( fontname, encoding, defaultEmbedding, Font.UNDEFINED, Font.UNDEFINED, null );
	}

	public static Font getFont ( final String fontname, final float size, final int style, final BaseColor color ) {
		return getFont ( fontname, defaultEncoding, defaultEmbedding, size, style, color );
	}

	public static Font getFont ( final String fontname, final float size, final BaseColor color ) {
		return getFont ( fontname, defaultEncoding, defaultEmbedding, size, Font.UNDEFINED, color );
	}

	public static Font getFont ( final String fontname, final float size, final int style ) {
		return getFont ( fontname, defaultEncoding, defaultEmbedding, size, style, null );
	}

	public static Font getFont ( final String fontname, final float size ) {
		return getFont ( fontname, defaultEncoding, defaultEmbedding, size, Font.UNDEFINED, null );
	}

	public static Font getFont ( final String fontname ) {
		return getFont ( fontname, defaultEncoding, defaultEmbedding, Font.UNDEFINED, Font.UNDEFINED, null );
	}

	public static void register ( final String path ) {
		register ( path, null );
	}

	public static void register ( final String path, final String alias ) {
		fontImp.register ( path, alias );
	}

	public static boolean contains ( final String fontname ) {
		return fontImp.isRegistered ( fontname );
	}

	public static FontFactoryImp getFontImp () {
		return fontImp;
	}

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.BaseFont;


public class Font implements Comparable<Font> {

	public static final int NORMAL = 0;

	public static final int BOLD = 1;

	public static final int ITALIC = 2;

	public static final int UNDERLINE = 4;

	public static final int STRIKETHRU = 8;

	public static final int BOLDITALIC = BOLD | ITALIC;

	public static final int UNDEFINED = -1;

	public static final int DEFAULTSIZE = 12;

	private FontFamily family = FontFamily.UNDEFINED;

	private float size;

	private int style;

	private BaseColor color;

	private BaseFont baseFont = null;

	public Font ( final Font other ) {
		this.family = other.family;
		this.size = other.size;
		this.style = other.style;
		this.color = other.color;
		this.baseFont = other.baseFont;
	}

	public Font ( final FontFamily family, final float size, final int style, final BaseColor color ) {
		this.family = family;
		this.size = size;
		this.style = style;
		this.color = color;
	}

	public Font ( final BaseFont bf, final float size, final int style, final BaseColor color ) {
		this.baseFont = bf;
		this.size = size;
		this.style = style;
		this.color = color;
	}

	public Font ( final BaseFont bf, final float size, final int style ) {
		this ( bf, size, style, null );
	}

	public Font ( final BaseFont bf, final float size ) {
		this ( bf, size, UNDEFINED, null );
	}

	public Font ( final BaseFont bf ) {
		this ( bf, UNDEFINED, UNDEFINED, null );
	}

	public Font ( final FontFamily family, final float size, final int style ) {
		this ( family, size, style, null );
	}

	public Font ( final FontFamily family, final float size ) {
		this ( family, size, UNDEFINED, null );
	}

	public Font ( final FontFamily family ) {
		this ( family, UNDEFINED, UNDEFINED, null );
	}

	public Font () {
		this ( FontFamily.UNDEFINED, UNDEFINED, UNDEFINED, null );
	}

	public static FontFamily getFamily ( final String family ) {
		if ( family.equalsIgnoreCase ( FontFactory.COURIER ) ) {
			return FontFamily.COURIER;
		}
		if ( family.equalsIgnoreCase ( FontFactory.HELVETICA ) ) {
			return FontFamily.HELVETICA;
		}
		if ( family.equalsIgnoreCase ( FontFactory.TIMES_ROMAN ) ) {
			return FontFamily.TIMES_ROMAN;
		}
		if ( family.equalsIgnoreCase ( FontFactory.SYMBOL ) ) {
			return FontFamily.SYMBOL;
		}
		if ( family.equalsIgnoreCase ( FontFactory.ZAPFDINGBATS ) ) {
			return FontFamily.ZAPFDINGBATS;
		}
		return FontFamily.UNDEFINED;
	}

	public static int getStyleValue ( final String style ) {
		int s = 0;
		if ( style.contains ( FontStyle.BOLD.getValue () ) ) {
			s |= BOLD;
		}
		if ( style.contains ( FontStyle.ITALIC.getValue () ) ) {
			s |= ITALIC;
		}
		if ( style.contains ( FontStyle.OBLIQUE.getValue () ) ) {
			s |= ITALIC;
		}
		if ( style.contains ( FontStyle.UNDERLINE.getValue () ) ) {
			s |= UNDERLINE;
		}
		if ( style.contains ( FontStyle.LINETHROUGH.getValue () ) ) {
			s |= STRIKETHRU;
		}
		return s;
	}

	public int compareTo ( final Font font ) {
		if ( font == null ) {
			return -1;
		}
		try {
			if ( baseFont != null && !baseFont.equals ( font.getBaseFont () ) ) {
				return -2;
			}
			if ( this.family != font.getFamily () ) {
				return 1;
			}
			if ( this.size != font.getSize () ) {
				return 2;
			}
			if ( this.style != font.getStyle () ) {
				return 3;
			}
			if ( this.color == null ) {
				if ( font.color == null ) {
					return 0;
				}
				return 4;
			}
			if ( font.color == null ) {
				return 4;
			}
			if ( this.color.equals ( font.getColor () ) ) {
				return 0;
			}
			return 4;
		} catch ( ClassCastException cce ) {
			return -3;
		}
	}

	public FontFamily getFamily () {
		return family;
	}

	public void setFamily ( final String family ) {
		this.family = getFamily ( family );
	}

	public String getFamilyname () {
		String tmp = "unknown";
		switch ( getFamily () ) {
		case COURIER:
			return FontFactory.COURIER;
		case HELVETICA:
			return FontFactory.HELVETICA;
		case TIMES_ROMAN:
			return FontFactory.TIMES_ROMAN;
		case SYMBOL:
			return FontFactory.SYMBOL;
		case ZAPFDINGBATS:
			return FontFactory.ZAPFDINGBATS;
		default:
			if ( baseFont != null ) {
				String[][] names = baseFont.getFamilyFontName ();
				for ( String[] name : names ) {
					if ( "0".equals ( name[2] ) ) {
						return name[3];
					}
					if ( "1033".equals ( name[2] ) ) {
						tmp = name[3];
					}
					if ( "".equals ( name[2] ) ) {
						tmp = name[3];
					}
				}
			}
		}
		return tmp;
	}

	public float getSize () {
		return size;
	}

	public void setSize ( final float size ) {
		this.size = size;
	}

	public float getCalculatedSize () {
		float s = this.size;
		if ( s == UNDEFINED ) {
			s = DEFAULTSIZE;
		}
		return s;
	}

	public float getCalculatedLeading ( final float linespacing ) {
		return linespacing * getCalculatedSize ();
	}

	public int getStyle () {
		return style;
	}

	public void setStyle ( final int style ) {
		this.style = style;
	}

	public void setStyle ( final String style ) {
		if ( this.style == UNDEFINED )
			this.style = NORMAL;
		this.style |= getStyleValue ( style );
	}

	public int getCalculatedStyle () {
		int style = this.style;
		if ( style == UNDEFINED ) {
			style = NORMAL;
		}
		if ( baseFont != null )
			return style;
		if ( family == FontFamily.SYMBOL || family == FontFamily.ZAPFDINGBATS )
			return style;
		else
			return style & ~BOLDITALIC;
	}

	public boolean isBold () {
		if ( style == UNDEFINED ) {
			return false;
		}
		return ( style & BOLD ) == BOLD;
	}

	public boolean isItalic () {
		if ( style == UNDEFINED ) {
			return false;
		}
		return ( style & ITALIC ) == ITALIC;
	}

	public boolean isUnderlined () {
		if ( style == UNDEFINED ) {
			return false;
		}
		return ( style & UNDERLINE ) == UNDERLINE;
	}

	public boolean isStrikethru () {
		if ( style == UNDEFINED ) {
			return false;
		}
		return ( style & STRIKETHRU ) == STRIKETHRU;
	}

	public BaseColor getColor () {
		return color;
	}

	public void setColor ( final BaseColor color ) {
		this.color = color;
	}

	public BaseFont getBaseFont () {
		return baseFont;
	}

	public BaseFont getCalculatedBaseFont ( final boolean specialEncoding ) {
		if ( baseFont != null )
			return baseFont;
		int style = this.style;
		if ( style == UNDEFINED ) {
			style = NORMAL;
		}
		String fontName;
		String encoding = BaseFont.WINANSI;
		BaseFont cfont;
		switch ( family ) {
		case COURIER:
			switch ( style & BOLDITALIC ) {
			case BOLD:
				fontName = BaseFont.COURIER_BOLD;
				break;
			case ITALIC:
				fontName = BaseFont.COURIER_OBLIQUE;
				break;
			case BOLDITALIC:
				fontName = BaseFont.COURIER_BOLDOBLIQUE;
				break;
			default:
				fontName = BaseFont.COURIER;
				break;
			}
			break;
		case TIMES_ROMAN:
			switch ( style & BOLDITALIC ) {
			case BOLD:
				fontName = BaseFont.TIMES_BOLD;
				break;
			case ITALIC:
				fontName = BaseFont.TIMES_ITALIC;
				break;
			case BOLDITALIC:
				fontName = BaseFont.TIMES_BOLDITALIC;
				break;
			default:
			case NORMAL:
				fontName = BaseFont.TIMES_ROMAN;
				break;
			}
			break;
		case SYMBOL:
			fontName = BaseFont.SYMBOL;
			if ( specialEncoding )
				encoding = BaseFont.SYMBOL;
			break;
		case ZAPFDINGBATS:
			fontName = BaseFont.ZAPFDINGBATS;
			if ( specialEncoding )
				encoding = BaseFont.ZAPFDINGBATS;
			break;
		default:
		case HELVETICA:
			switch ( style & BOLDITALIC ) {
			case BOLD:
				fontName = BaseFont.HELVETICA_BOLD;
				break;
			case ITALIC:
				fontName = BaseFont.HELVETICA_OBLIQUE;
				break;
			case BOLDITALIC:
				fontName = BaseFont.HELVETICA_BOLDOBLIQUE;
				break;
			default:
			case NORMAL:
				fontName = BaseFont.HELVETICA;
				break;
			}
			break;
		}
		try {
			cfont = BaseFont.createFont ( fontName, encoding, false );
		} catch ( Exception ee ) {
			throw new ExceptionConverter ( ee );
		}
		return cfont;
	}

	public boolean isStandardFont () {
		return family == FontFamily.UNDEFINED && size == UNDEFINED
						&& style == UNDEFINED && color == null && baseFont == null;
	}

	public Font difference ( final Font font ) {
		if ( font == null )
			return this;
		float dSize = font.size;
		if ( dSize == UNDEFINED ) {
			dSize = this.size;
		}
		int dStyle = UNDEFINED;
		int style1 = this.style;
		int style2 = font.getStyle ();
		if ( style1 != UNDEFINED || style2 != UNDEFINED ) {
			if ( style1 == UNDEFINED )
				style1 = 0;
			if ( style2 == UNDEFINED )
				style2 = 0;
			dStyle = style1 | style2;
		}
		BaseColor dColor = font.color;
		if ( dColor == null ) {
			dColor = this.color;
		}
		if ( font.baseFont != null ) {
			return new Font ( font.baseFont, dSize, dStyle, dColor );
		}
		if ( font.getFamily () != FontFamily.UNDEFINED ) {
			return new Font ( font.family, dSize, dStyle, dColor );
		}
		if ( this.baseFont != null ) {
			if ( dStyle == style1 ) {
				return new Font ( this.baseFont, dSize, dStyle, dColor );
			} else {
				return FontFactory.getFont ( this.getFamilyname (), dSize, dStyle, dColor );
			}
		}
		return new Font ( this.family, dSize, dStyle, dColor );
	}

	public enum FontFamily {
		COURIER,
		HELVETICA,
		TIMES_ROMAN,
		SYMBOL,
		ZAPFDINGBATS,
		UNDEFINED
	}


	public enum FontStyle {
		NORMAL ( "normal" ), BOLD ( "bold" ), ITALIC ( "italic" ), OBLIQUE ( "oblique" ), UNDERLINE (
						"underline" ), LINETHROUGH ( "line-through" );

		private final String code;

		FontStyle ( final String code ) {
			this.code = code;
		}

		public String getValue () {
			return code;
		}

	}

}

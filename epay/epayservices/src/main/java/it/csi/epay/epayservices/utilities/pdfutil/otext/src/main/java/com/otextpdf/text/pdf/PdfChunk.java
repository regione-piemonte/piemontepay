/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Chunk;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Font;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.SplitCharacter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.TabSettings;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.TabStop;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Utilities;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.IAccessibleElement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class PdfChunk {

	public static final float UNDERLINE_THICKNESS = 1f / 15;

	public static final float UNDERLINE_OFFSET = -1f / 3;

	private static final char[] singleSpace = { ' ' };

	private static final PdfChunk[] thisChunk = new PdfChunk[1];

	private static final float ITALIC_ANGLE = 0.21256f;

	private static final HashSet<String> keysAttributes = new HashSet<> ();

	private static final HashSet<String> keysNoStroke = new HashSet<> ();

	private static final String TABSTOP = "TABSTOP";

	static {
		keysAttributes.add ( Chunk.ACTION );
		keysAttributes.add ( Chunk.UNDERLINE );
		keysAttributes.add ( Chunk.REMOTEGOTO );
		keysAttributes.add ( Chunk.LOCALGOTO );
		keysAttributes.add ( Chunk.LOCALDESTINATION );
		keysAttributes.add ( Chunk.GENERICTAG );
		keysAttributes.add ( Chunk.NEWPAGE );
		keysAttributes.add ( Chunk.IMAGE );
		keysAttributes.add ( Chunk.BACKGROUND );
		keysAttributes.add ( Chunk.PDFANNOTATION );
		keysAttributes.add ( Chunk.SKEW );
		keysAttributes.add ( Chunk.HSCALE );
		keysAttributes.add ( Chunk.SEPARATOR );
		keysAttributes.add ( Chunk.TAB );
		keysAttributes.add ( Chunk.TABSETTINGS );
		keysAttributes.add ( Chunk.CHAR_SPACING );
		keysAttributes.add ( Chunk.WORD_SPACING );
		keysAttributes.add ( Chunk.LINEHEIGHT );
		keysNoStroke.add ( Chunk.SUBSUPSCRIPT );
		keysNoStroke.add ( Chunk.SPLITCHARACTER );
		keysNoStroke.add ( Chunk.HYPHENATION );
		keysNoStroke.add ( Chunk.TEXTRENDERMODE );
	}

	protected String value;

	protected String encoding;

	protected PdfFont font;

	protected BaseFont baseFont;

	protected SplitCharacter splitCharacter;

	protected HashMap<String, Object> attributes = new HashMap<> ();

	protected HashMap<String, Object> noStroke = new HashMap<> ();

	protected boolean newlineSplit;

	protected Image image;

	protected float imageScalePercentage = 1.0f;

	protected float offsetX;

	protected float offsetY;

	protected boolean changeLeading = false;

	protected float leading = 0;

	protected IAccessibleElement accessibleElement;

	PdfChunk ( String string, PdfChunk other ) {
		thisChunk[0] = this;
		value = string;
		this.font = other.font;
		this.attributes = other.attributes;
		this.noStroke = other.noStroke;
		this.baseFont = other.baseFont;
		this.changeLeading = other.changeLeading;
		this.leading = other.leading;
		Object[] obj = (Object[]) attributes.get ( Chunk.IMAGE );
		if ( obj == null )
			image = null;
		else {
			image = (Image) obj[0];
			offsetX = (Float) obj[1];
			offsetY = (Float) obj[2];
			changeLeading = (Boolean) obj[3];
		}
		encoding = font.getFont ().getEncoding ();
		splitCharacter = (SplitCharacter) noStroke.get ( Chunk.SPLITCHARACTER );
		if ( splitCharacter == null )
			splitCharacter = DefaultSplitCharacter.DEFAULT;
		accessibleElement = other.accessibleElement;
	}

	PdfChunk ( Chunk chunk, PdfAction action ) {
		thisChunk[0] = this;
		value = chunk.getContent ();

		Font f = chunk.getFont ();
		float size = f.getSize ();
		if ( size == Font.UNDEFINED )
			size = 12;
		baseFont = f.getBaseFont ();
		int style = f.getStyle ();
		if ( style == Font.UNDEFINED ) {
			style = Font.NORMAL;
		}
		if ( baseFont == null ) {
			baseFont = f.getCalculatedBaseFont ( false );
		} else {
			if ( ( style & Font.BOLD ) != 0 )
				attributes.put ( Chunk.TEXTRENDERMODE,
								new Object[] { PdfContentByte.TEXT_RENDER_MODE_FILL_STROKE, size / 30f, null } );
			if ( ( style & Font.ITALIC ) != 0 )
				attributes.put ( Chunk.SKEW, new float[] { 0, ITALIC_ANGLE } );
		}
		font = new PdfFont ( baseFont, size );
		// other style possibilities
		HashMap<String, Object> attr = chunk.getAttributes ();
		if ( attr != null ) {
			for ( Map.Entry<String, Object> entry : attr.entrySet () ) {
				String name = entry.getKey ();
				if ( keysAttributes.contains ( name ) ) {
					attributes.put ( name, entry.getValue () );
				} else if ( keysNoStroke.contains ( name ) ) {
					noStroke.put ( name, entry.getValue () );
				}
			}
			if ( "".equals ( attr.get ( Chunk.GENERICTAG ) ) ) {
				attributes.put ( Chunk.GENERICTAG, chunk.getContent () );
			}
		}
		if ( f.isUnderlined () ) {
			Object[] obj = { null, new float[] { 0, UNDERLINE_THICKNESS, 0, UNDERLINE_OFFSET, 0 } };
			Object[][] unders = Utilities.addToArray ( (Object[][]) attributes.get ( Chunk.UNDERLINE ), obj );
			attributes.put ( Chunk.UNDERLINE, unders );
		}
		if ( f.isStrikethru () ) {
			Object[] obj = { null, new float[] { 0, 1f / 15, 0, 1f / 3, 0 } };
			Object[][] unders = Utilities.addToArray ( (Object[][]) attributes.get ( Chunk.UNDERLINE ), obj );
			attributes.put ( Chunk.UNDERLINE, unders );
		}
		if ( action != null )
			attributes.put ( Chunk.ACTION, action );
		noStroke.put ( Chunk.COLOR, f.getColor () );
		noStroke.put ( Chunk.ENCODING, font.getFont ().getEncoding () );

		Float lh = (Float) attributes.get ( Chunk.LINEHEIGHT );
		if ( lh != null ) {
			changeLeading = true;
			leading = lh;
		}

		Object[] obj = (Object[]) attributes.get ( Chunk.IMAGE );
		if ( obj == null ) {
			image = null;
		} else {
			attributes.remove ( Chunk.HSCALE );
			image = (Image) obj[0];
			offsetX = (Float) obj[1];
			offsetY = (Float) obj[2];
			changeLeading = (Boolean) obj[3];
		}
		Float hs = (Float) attributes.get ( Chunk.HSCALE );
		if ( hs != null )
			font.setHorizontalScaling ( hs );
		encoding = font.getFont ().getEncoding ();
		splitCharacter = (SplitCharacter) noStroke.get ( Chunk.SPLITCHARACTER );
		if ( splitCharacter == null )
			splitCharacter = DefaultSplitCharacter.DEFAULT;
		accessibleElement = chunk;
	}

	PdfChunk ( Chunk chunk, PdfAction action, TabSettings tabSettings ) {
		this ( chunk, action );
		if ( tabSettings != null && attributes.get ( Chunk.TABSETTINGS ) == null )
			attributes.put ( Chunk.TABSETTINGS, tabSettings );
	}

	static TabStop getTabStop ( PdfChunk tab, float tabPosition ) {
		TabStop tabStop = null;
		Object[] o = (Object[]) tab.attributes.get ( Chunk.TAB );
		if ( o != null ) {
			Float tabInterval = (Float) o[0];
			if ( Float.isNaN ( tabInterval ) ) {
				tabStop = TabSettings.getTabStopNewInstance ( tabPosition, (TabSettings) tab.attributes.get ( Chunk.TABSETTINGS ) );
			} else {
				tabStop = TabStop.newInstance ( tabPosition, tabInterval );
			}
		}
		return tabStop;
	}

	public static boolean noPrint ( int c ) {
		return c >= 0x200b && c <= 0x200f || c >= 0x202a && c <= 0x202e;
	}

	public int getUnicodeEquivalent ( int c ) {
		return baseFont.getUnicodeEquivalent ( c );
	}

	protected int getWord ( String text, int start ) {
		int len = text.length ();
		while ( start < len ) {
			if ( !Character.isLetter ( text.charAt ( start ) ) )
				break;
			++start;
		}
		return start;
	}

	PdfChunk split ( float width ) {
		newlineSplit = false;
		if ( image != null ) {
			if ( image.getScaledWidth () > width ) {
				PdfChunk pc = new PdfChunk ( Chunk.OBJECT_REPLACEMENT_CHARACTER, this );
				value = "";
				attributes = new HashMap<> ();
				image = null;
				font = PdfFont.getDefaultFont ();
				return pc;
			} else
				return null;
		}
		HyphenationEvent hyphenationEvent = (HyphenationEvent) noStroke.get ( Chunk.HYPHENATION );
		int currentPosition = 0;
		int splitPosition = -1;
		float currentWidth = 0;

		int lastSpace = -1;
		float lastSpaceWidth = 0;
		int length = value.length ();
		char[] valueArray = value.toCharArray ();
		char character;
		BaseFont ft = font.getFont ();
		boolean surrogate;
		if ( ft.getFontType () == BaseFont.FONT_TYPE_CJK && ft.getUnicodeEquivalent ( ' ' ) != ' ' ) {
			while ( currentPosition < length ) {
				char cidChar = valueArray[currentPosition];
				character = (char) ft.getUnicodeEquivalent ( cidChar );
				if ( character == '\n' ) {
					newlineSplit = true;
					String returnValue = value.substring ( currentPosition + 1 );
					value = value.substring ( 0, currentPosition );
					if ( value.isEmpty () ) {
						value = "\u0001";
					}
					return new PdfChunk ( returnValue, this );
				}
				currentWidth += getCharWidth ( cidChar );
				if ( character == ' ' ) {
					lastSpace = currentPosition + 1;
					lastSpaceWidth = currentWidth;
				}
				if ( currentWidth > width )
					break;
				if ( splitCharacter.isSplitCharacter ( 0, currentPosition, length, valueArray, thisChunk ) )
					splitPosition = currentPosition + 1;
				currentPosition++;
			}
		} else {
			while ( currentPosition < length ) {
				character = valueArray[currentPosition];
				if ( character == '\r' || character == '\n' ) {
					newlineSplit = true;
					int inc = 1;
					if ( character == '\r' && currentPosition + 1 < length && valueArray[currentPosition + 1] == '\n' )
						inc = 2;
					String returnValue = value.substring ( currentPosition + inc );
					value = value.substring ( 0, currentPosition );
					if ( value.isEmpty () ) {
						value = " ";
					}
					return new PdfChunk ( returnValue, this );
				}
				surrogate = Utilities.isSurrogatePair ( valueArray, currentPosition );
				if ( surrogate )
					currentWidth += getCharWidth ( Utilities.convertToUtf32 ( valueArray[currentPosition], valueArray[currentPosition + 1] ) );
				else
					currentWidth += getCharWidth ( character );
				if ( character == ' ' ) {
					lastSpace = currentPosition + 1;
					lastSpaceWidth = currentWidth;
				}
				if ( surrogate )
					currentPosition++;
				if ( currentWidth > width )
					break;
				if ( splitCharacter.isSplitCharacter ( 0, currentPosition, length, valueArray, null ) )
					splitPosition = currentPosition + 1;
				currentPosition++;
			}
		}

		if ( currentPosition == length ) {
			return null;
		}
		if ( splitPosition < 0 ) {
			String returnValue = value;
			value = "";
			return new PdfChunk ( returnValue, this );
		}
		if ( lastSpace > splitPosition && splitCharacter.isSplitCharacter ( 0, 0, 1, singleSpace, null ) )
			splitPosition = lastSpace;
		if ( hyphenationEvent != null && lastSpace >= 0 && lastSpace < currentPosition ) {
			int wordIdx = getWord ( value, lastSpace );
			if ( wordIdx > lastSpace ) {
				String pre = hyphenationEvent.getHyphenatedWordPre ( value.substring ( lastSpace, wordIdx ), font.getFont (), font.size (),
								width - lastSpaceWidth );
				String post = hyphenationEvent.getHyphenatedWordPost ();
				if ( !pre.isEmpty () ) {
					String returnValue = post + value.substring ( wordIdx );
					value = trim ( value.substring ( 0, lastSpace ) + pre );
					return new PdfChunk ( returnValue, this );
				}
			}
		}
		String returnValue = value.substring ( splitPosition );
		value = trim ( value.substring ( 0, splitPosition ) );
		return new PdfChunk ( returnValue, this );
	}

	PdfChunk truncate ( float width ) {
		if ( image != null ) {
			if ( image.getScaledWidth () > width ) {
				if ( image.isScaleToFitLineWhenOverflow () ) {
					this.setImageScalePercentage ( width / image.getWidth () );
					return null;
				}
				PdfChunk pc = new PdfChunk ( "", this );
				value = "";
				attributes.remove ( Chunk.IMAGE );
				image = null;
				font = PdfFont.getDefaultFont ();
				return pc;
			} else
				return null;
		}

		int currentPosition = 0;
		float currentWidth = 0;

		if ( width < font.width () ) {
			String returnValue = value.substring ( 1 );
			value = value.substring ( 0, 1 );
			return new PdfChunk ( returnValue, this );
		}

		int length = value.length ();
		boolean surrogate = false;
		while ( currentPosition < length ) {
			surrogate = Utilities.isSurrogatePair ( value, currentPosition );
			if ( surrogate )
				currentWidth += getCharWidth ( Utilities.convertToUtf32 ( value, currentPosition ) );
			else
				currentWidth += getCharWidth ( value.charAt ( currentPosition ) );
			if ( currentWidth > width )
				break;
			if ( surrogate )
				currentPosition++;
			currentPosition++;
		}

		if ( currentPosition == length ) {
			return null;
		}

		if ( currentPosition == 0 ) {
			currentPosition = 1;
			if ( surrogate )
				++currentPosition;
		}
		String returnValue = value.substring ( currentPosition );
		value = value.substring ( 0, currentPosition );
		return new PdfChunk ( returnValue, this );
	}

	PdfFont font () {
		return font;
	}

	BaseColor color () {
		return (BaseColor) noStroke.get ( Chunk.COLOR );
	}

	float width () {
		return width ( value );
	}

	float width ( String str ) {
		if ( isAttribute ( Chunk.SEPARATOR ) ) {
			return 0;
		}
		if ( isImage () ) {
			return getImageWidth ();
		}

		float width = font.width ( str );

		if ( isAttribute ( Chunk.CHAR_SPACING ) ) {
			Float cs = (Float) getAttribute ( Chunk.CHAR_SPACING );
			width += str.length () * cs;
		}
		if ( isAttribute ( Chunk.WORD_SPACING ) ) {
			int numberOfSpaces = 0;
			int idx = -1;
			while ( ( idx = str.indexOf ( ' ', idx + 1 ) ) >= 0 )
				++numberOfSpaces;
			Float ws = (Float) getAttribute ( Chunk.WORD_SPACING );
			width += numberOfSpaces * ws;
		}
		return width;
	}

	float height () {
		if ( isImage () ) {
			return getImageHeight ();
		} else {
			return font.size ();
		}
	}

	public boolean isNewlineSplit () {
		return newlineSplit;
	}

	public float getWidthCorrected ( float charSpacing, float wordSpacing ) {
		if ( image != null ) {
			return image.getScaledWidth () + charSpacing;
		}
		int numberOfSpaces = 0;
		int idx = -1;
		while ( ( idx = value.indexOf ( ' ', idx + 1 ) ) >= 0 )
			++numberOfSpaces;
		return font.width ( value ) + value.length () * charSpacing + numberOfSpaces * wordSpacing;
	}

	public float getTextRise () {
		Float f = (Float) getAttribute ( Chunk.SUBSUPSCRIPT );
		if ( f != null ) {
			return f;
		}
		return 0.0f;
	}

	public float trimLastSpace () {
		BaseFont ft = font.getFont ();
		if ( ft.getFontType () == BaseFont.FONT_TYPE_CJK && ft.getUnicodeEquivalent ( ' ' ) != ' ' ) {
			if ( value.length () > 1 && value.endsWith ( "\u0001" ) ) {
				value = value.substring ( 0, value.length () - 1 );
				return font.width ( '\u0001' );
			}
		} else {
			if ( value.length () > 1 && value.endsWith ( " " ) ) {
				value = value.substring ( 0, value.length () - 1 );
				return font.width ( ' ' );
			}
		}
		return 0;
	}

	public void trimFirstSpace () {
		BaseFont ft = font.getFont ();
		if ( ft.getFontType () == BaseFont.FONT_TYPE_CJK && ft.getUnicodeEquivalent ( ' ' ) != ' ' ) {
			if ( value.length () > 1 && value.startsWith ( "\u0001" ) ) {
				value = value.substring ( 1 );
				font.width ( '\u0001' );
			}
		} else {
			if ( value.length () > 1 && value.startsWith ( " " ) ) {
				value = value.substring ( 1 );
				font.width ( ' ' );
			}
		}
	}

	Object getAttribute ( String name ) {
		if ( attributes.containsKey ( name ) )
			return attributes.get ( name );
		return noStroke.get ( name );
	}

	boolean isAttribute ( String name ) {
		if ( attributes.containsKey ( name ) )
			return true;
		return noStroke.containsKey ( name );
	}

	boolean isStroked () {
		return !attributes.isEmpty ();
	}

	boolean isSeparator () {
		return isAttribute ( Chunk.SEPARATOR );
	}

	boolean isHorizontalSeparator () {
		if ( isAttribute ( Chunk.SEPARATOR ) ) {
			Object[] o = (Object[]) getAttribute ( Chunk.SEPARATOR );
			return !(Boolean) o[1];
		}
		return false;
	}

	boolean isTab () {
		return isAttribute ( Chunk.TAB );
	}

	@Deprecated
	void adjustLeft ( float newValue ) {
		Object[] o = (Object[]) attributes.get ( Chunk.TAB );
		if ( o != null ) {
			attributes.put ( Chunk.TAB, new Object[] { o[0], o[1], o[2], newValue } );
		}
	}

	TabStop getTabStop () {
		return (TabStop) attributes.get ( TABSTOP );
	}

	void setTabStop ( TabStop tabStop ) {
		attributes.put ( TABSTOP, tabStop );
	}

	boolean isImage () {
		return image != null;
	}

	Image getImage () {
		return image;
	}

	float getImageHeight () {
		return image.getScaledHeight () * imageScalePercentage;
	}

	float getImageWidth () {
		return image.getScaledWidth () * imageScalePercentage;
	}

	public float getImageScalePercentage () {
		return imageScalePercentage;
	}

	public void setImageScalePercentage ( float imageScalePercentage ) {
		this.imageScalePercentage = imageScalePercentage;
	}

	float getImageOffsetX () {
		return offsetX;
	}

	float getImageOffsetY () {
		return offsetY;
	}

	@Override
	public String toString () {
		return value;
	}

	boolean isSpecialEncoding () {
		return encoding.equals ( CJKFont.CJK_ENCODING ) || encoding.equals ( BaseFont.IDENTITY_H );
	}

	int length () {
		return value.length ();
	}

	int lengthUtf32 () {
		if ( !BaseFont.IDENTITY_H.equals ( encoding ) )
			return value.length ();
		int total = 0;
		int len = value.length ();
		for ( int k = 0; k < len; ++k ) {
			if ( Utilities.isSurrogateHigh ( value.charAt ( k ) ) )
				++k;
			++total;
		}
		return total;
	}

	boolean isExtSplitCharacter ( int start, int current, int end, char[] cc, PdfChunk[] ck ) {
		return splitCharacter.isSplitCharacter ( start, current, end, cc, ck );
	}

	String trim ( String string ) {
		BaseFont ft = font.getFont ();
		if ( ft.getFontType () == BaseFont.FONT_TYPE_CJK && ft.getUnicodeEquivalent ( ' ' ) != ' ' ) {
			while ( string.endsWith ( "\u0001" ) ) {
				string = string.substring ( 0, string.length () - 1 );
			}
		} else {
			while ( string.endsWith ( " " ) || string.endsWith ( "\t" ) ) {
				string = string.substring ( 0, string.length () - 1 );
			}
		}
		return string;
	}

	public boolean changeLeading () {
		return changeLeading;
	}

	public float getLeading () {
		return leading;
	}

	float getCharWidth ( int c ) {
		if ( noPrint ( c ) )
			return 0;
		if ( isAttribute ( Chunk.CHAR_SPACING ) ) {
			Float cs = (Float) getAttribute ( Chunk.CHAR_SPACING );
			return font.width ( c ) + cs * font.getHorizontalScaling ();
		}
		if ( isImage () ) {
			return getImageWidth ();
		}
		return font.width ( c );
	}

}

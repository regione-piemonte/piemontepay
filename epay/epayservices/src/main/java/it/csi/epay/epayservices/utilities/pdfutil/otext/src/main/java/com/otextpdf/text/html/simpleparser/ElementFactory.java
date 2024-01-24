/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.html.simpleparser;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Chunk;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocListener;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Font;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.FontFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.FontProvider;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.List;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ListItem;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Paragraph;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.html.HtmlTags;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.html.HtmlUtilities;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.BaseFont;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.HyphenationAuto;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.HyphenationEvent;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.draw.LineSeparator;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class ElementFactory {

	private FontProvider provider = FontFactory.getFontImp ();

	public ElementFactory () {
	}

	protected static void setParagraphLeading ( final Paragraph paragraph, final String leading ) {
		if ( leading == null ) {
			paragraph.setLeading ( 0, 1.5f );
			return;
		}
		try {
			StringTokenizer tk = new StringTokenizer ( leading, " ," );
			String v = tk.nextToken ();
			float v1 = Float.parseFloat ( v );
			if ( !tk.hasMoreTokens () ) {
				paragraph.setLeading ( v1, 0 );
				return;
			}
			v = tk.nextToken ();
			float v2 = Float.parseFloat ( v );
			paragraph.setLeading ( v1, v2 );
		} catch ( Exception e ) {
			paragraph.setLeading ( 0, 1.5f );
		}
	}

	public void setFontProvider ( final FontProvider provider ) {
		this.provider = provider;
	}

	public Font getFont ( final ChainedProperties chain ) {

		String face = chain.getProperty ( HtmlTags.FACE );
		if ( face == null || face.trim ().isEmpty () ) {
			face = chain.getProperty ( HtmlTags.FONTFAMILY );
		}
		if ( face != null ) {
			StringTokenizer tok = new StringTokenizer ( face, "," );
			while ( tok.hasMoreTokens () ) {
				face = tok.nextToken ().trim ();
				if ( face.startsWith ( "\"" ) )
					face = face.substring ( 1 );
				if ( face.endsWith ( "\"" ) )
					face = face.substring ( 0, face.length () - 1 );
				if ( provider.isRegistered ( face ) )
					break;
			}
		}

		String encoding = chain.getProperty ( HtmlTags.ENCODING );
		if ( encoding == null )
			encoding = BaseFont.WINANSI;

		String value = chain.getProperty ( HtmlTags.SIZE );
		float size = 12;
		if ( value != null )
			size = Float.parseFloat ( value );

		int style = 0;

		String decoration = chain.getProperty ( HtmlTags.TEXTDECORATION );
		if ( decoration != null && !decoration.trim ().isEmpty () ) {
			if ( HtmlTags.UNDERLINE.equals ( decoration ) ) {
				style |= Font.UNDERLINE;
			} else if ( HtmlTags.LINETHROUGH.equals ( decoration ) ) {
				style |= Font.STRIKETHRU;
			}
		}
		if ( chain.hasProperty ( HtmlTags.I ) )
			style |= Font.ITALIC;
		if ( chain.hasProperty ( HtmlTags.B ) )
			style |= Font.BOLD;
		if ( chain.hasProperty ( HtmlTags.U ) )
			style |= Font.UNDERLINE;
		if ( chain.hasProperty ( HtmlTags.S ) )
			style |= Font.STRIKETHRU;

		BaseColor color = HtmlUtilities.decodeColor ( chain.getProperty ( HtmlTags.COLOR ) );

		return provider.getFont ( face, encoding, true, size, style, color );
	}

	public Chunk createChunk ( final String content, final ChainedProperties chain ) {
		Font font = getFont ( chain );
		Chunk ck = new Chunk ( content, font );
		if ( chain.hasProperty ( HtmlTags.SUB ) )
			ck.setTextRise ( -font.getSize () / 2 );
		else if ( chain.hasProperty ( HtmlTags.SUP ) )
			ck.setTextRise ( font.getSize () / 2 );
		ck.setHyphenation ( getHyphenation ( chain ) );
		return ck;
	}

	public Paragraph createParagraph ( final ChainedProperties chain ) {
		Paragraph paragraph = new Paragraph ();
		updateElement ( paragraph, chain );
		return paragraph;
	}

	public ListItem createListItem ( final ChainedProperties chain ) {
		ListItem item = new ListItem ();
		updateElement ( item, chain );
		return item;
	}

	protected void updateElement ( final Paragraph paragraph, final ChainedProperties chain ) {
		String value = chain.getProperty ( HtmlTags.ALIGN );
		paragraph.setAlignment ( HtmlUtilities.alignmentValue ( value ) );
		paragraph.setHyphenation ( getHyphenation ( chain ) );
		setParagraphLeading ( paragraph, chain.getProperty ( HtmlTags.LEADING ) );
		value = chain.getProperty ( HtmlTags.AFTER );
		if ( value != null ) {
			try {
				paragraph.setSpacingBefore ( Float.parseFloat ( value ) );
			} catch ( Exception ignored ) {
			}
		}
		value = chain.getProperty ( HtmlTags.AFTER );
		if ( value != null ) {
			try {
				paragraph.setSpacingAfter ( Float.parseFloat ( value ) );
			} catch ( Exception ignored ) {
			}
		}
		// extra paragraph space
		value = chain.getProperty ( HtmlTags.EXTRAPARASPACE );
		if ( value != null ) {
			try {
				paragraph.setExtraParagraphSpace ( Float.parseFloat ( value ) );
			} catch ( Exception ignored ) {
			}
		}
		// indentation
		value = chain.getProperty ( HtmlTags.INDENT );
		if ( value != null ) {
			try {
				paragraph.setIndentationLeft ( Float.parseFloat ( value ) );
			} catch ( Exception ignored ) {
			}
		}
	}

	public HyphenationEvent getHyphenation ( final ChainedProperties chain ) {
		String value = chain.getProperty ( HtmlTags.HYPHENATION );
		// no hyphenation defined
		if ( value == null || value.isEmpty () ) {
			return null;
		}
		// language code only
		int pos = value.indexOf ( '_' );
		if ( pos == -1 ) {
			return new HyphenationAuto ( value, null, 2, 2 );
		}
		// language and country code
		String lang = value.substring ( 0, pos );
		String country = value.substring ( pos + 1 );
		// no leftMin or rightMin
		pos = country.indexOf ( ',' );
		if ( pos == -1 ) {
			return new HyphenationAuto ( lang, country, 2, 2 );
		}
		// leftMin and rightMin value
		int leftMin;
		int rightMin = 2;
		value = country.substring ( pos + 1 );
		country = country.substring ( 0, pos );
		pos = value.indexOf ( ',' );
		if ( pos == -1 ) {
			leftMin = Integer.parseInt ( value );
		} else {
			leftMin = Integer.parseInt ( value.substring ( 0, pos ) );
			rightMin = Integer.parseInt ( value.substring ( pos + 1 ) );
		}
		return new HyphenationAuto ( lang, country, leftMin, rightMin );
	}

	public LineSeparator createLineSeparator ( final Map<String, String> attrs, final float offset ) {
		// line thickness
		float lineWidth = 1;
		String size = attrs.get ( HtmlTags.SIZE );
		if ( size != null ) {
			float tmpSize = HtmlUtilities.parseLength ( size, HtmlUtilities.DEFAULT_FONT_SIZE );
			if ( tmpSize > 0 )
				lineWidth = tmpSize;
		}
		// width percentage
		String width = attrs.get ( HtmlTags.WIDTH );
		float percentage = 100;
		if ( width != null ) {
			float tmpWidth = HtmlUtilities.parseLength ( width, HtmlUtilities.DEFAULT_FONT_SIZE );
			if ( tmpWidth > 0 )
				percentage = tmpWidth;
			if ( !width.endsWith ( "%" ) )
				percentage = 100; // Treat a pixel width as 100% for now.
		}
		// alignment
		int align = HtmlUtilities.alignmentValue ( attrs.get ( HtmlTags.ALIGN ) );
		return new LineSeparator ( lineWidth, percentage, null, align, offset );
	}

	public Image createImage (
					String src,
					final Map<String, String> attrs,
					final ChainedProperties chain,
					final DocListener document,
					final ImageProvider img_provider,
					final HashMap<String, Image> img_store,
					final String img_baseurl ) throws DocumentException, IOException {
		Image img = null;
		// getting the image using an image provider
		if ( img_provider != null )
			img = img_provider.getImage ( src, attrs, chain, document );
		// getting the image from an image store
		if ( img == null && img_store != null ) {
			Image tim = img_store.get ( src );
			if ( tim != null )
				img = Image.getInstance ( tim );
		}
		if ( img != null )
			return img;
		// introducing a base url
		// relative src references only
		if ( !src.startsWith ( "http" ) && img_baseurl != null ) {
			src = img_baseurl + src;
		} else if ( !src.startsWith ( "http" ) ) {
			String path = chain.getProperty ( HtmlTags.IMAGEPATH );
			if ( path == null )
				path = "";
			src = new File ( path, src ).getPath ();
		}
		img = Image.getInstance ( src );
		if ( img == null )
			return null;

		float actualFontSize = HtmlUtilities.parseLength (
						chain.getProperty ( HtmlTags.SIZE ),
						HtmlUtilities.DEFAULT_FONT_SIZE );
		if ( actualFontSize <= 0f )
			actualFontSize = HtmlUtilities.DEFAULT_FONT_SIZE;
		String width = attrs.get ( HtmlTags.WIDTH );
		float widthInPoints = HtmlUtilities.parseLength ( width, actualFontSize );
		String height = attrs.get ( HtmlTags.HEIGHT );
		float heightInPoints = HtmlUtilities.parseLength ( height, actualFontSize );
		if ( widthInPoints > 0 && heightInPoints > 0 ) {
			img.scaleAbsolute ( widthInPoints, heightInPoints );
		} else if ( widthInPoints > 0 ) {
			heightInPoints = img.getHeight () * widthInPoints
							/ img.getWidth ();
			img.scaleAbsolute ( widthInPoints, heightInPoints );
		} else if ( heightInPoints > 0 ) {
			widthInPoints = img.getWidth () * heightInPoints
							/ img.getHeight ();
			img.scaleAbsolute ( widthInPoints, heightInPoints );
		}

		String before = chain.getProperty ( HtmlTags.BEFORE );
		if ( before != null )
			img.setSpacingBefore ( Float.parseFloat ( before ) );
		String after = chain.getProperty ( HtmlTags.AFTER );
		if ( after != null )
			img.setSpacingAfter ( Float.parseFloat ( after ) );
		img.setWidthPercentage ( 0 );
		return img;
	}

	public List createList ( final String tag, final ChainedProperties chain ) {
		List list;
		if ( HtmlTags.UL.equalsIgnoreCase ( tag ) ) {
			list = new List ( List.UNORDERED );
			list.setListSymbol ( "\u2022 " );
		} else {
			list = new List ( List.ORDERED );
		}
		try {
			list.setIndentationLeft ( new Float ( chain.getProperty ( HtmlTags.INDENT ) ) );
		} catch ( Exception e ) {
			list.setAutoindent ( true );
		}
		return list;
	}
}

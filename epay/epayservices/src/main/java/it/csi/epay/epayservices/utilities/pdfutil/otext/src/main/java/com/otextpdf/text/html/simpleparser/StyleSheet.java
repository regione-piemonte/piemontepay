/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.html.simpleparser;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.html.HtmlTags;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.html.HtmlUtilities;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class StyleSheet {

	protected Map<String, Map<String, String>> tagMap = new HashMap<> ();

	protected Map<String, Map<String, String>> classMap = new HashMap<> ();

	public StyleSheet () {
	}

	public static void resolveStyleAttribute ( Map<String, String> h, ChainedProperties chain ) {
		String style = h.get ( HtmlTags.STYLE );
		if ( style == null )
			return;
		Properties prop = HtmlUtilities.parseAttributes ( style );
		for ( Object element : prop.keySet () ) {
			String key = (String) element;
			switch ( key ) {
			case HtmlTags.FONTFAMILY:
				h.put ( HtmlTags.FACE, prop.getProperty ( key ) );
				break;
			case HtmlTags.FONTSIZE: {
				float actualFontSize = HtmlUtilities.parseLength ( chain
												.getProperty ( HtmlTags.SIZE ),
								HtmlUtilities.DEFAULT_FONT_SIZE );
				if ( actualFontSize <= 0f )
					actualFontSize = HtmlUtilities.DEFAULT_FONT_SIZE;
				h.put ( HtmlTags.SIZE, HtmlUtilities.parseLength ( prop
								.getProperty ( key ), actualFontSize )
								+ "pt" );
				break;
			}
			case HtmlTags.FONTSTYLE: {
				String ss = prop.getProperty ( key ).trim ().toLowerCase ();
				if ( ss.equals ( HtmlTags.ITALIC ) || ss.equals ( HtmlTags.OBLIQUE ) )
					h.put ( HtmlTags.I, null );
				break;
			}
			case HtmlTags.FONTWEIGHT: {
				String ss = prop.getProperty ( key ).trim ().toLowerCase ();
				if ( ss.equals ( HtmlTags.BOLD ) || ss.equals ( "700" ) || ss.equals ( "800" )
								|| ss.equals ( "900" ) )
					h.put ( HtmlTags.B, null );
				break;
			}
			case HtmlTags.TEXTDECORATION: {
				String ss = prop.getProperty ( key ).trim ().toLowerCase ();
				if ( ss.equals ( HtmlTags.UNDERLINE ) )
					h.put ( HtmlTags.U, null );
				break;
			}
			case HtmlTags.COLOR:
				BaseColor c = HtmlUtilities.decodeColor ( prop.getProperty ( key ) );
				if ( c != null ) {
					int hh = c.getRGB ();
					String hs = Integer.toHexString ( hh );
					hs = "000000" + hs;
					hs = "#" + hs.substring ( hs.length () - 6 );
					h.put ( HtmlTags.COLOR, hs );
				}
				break;
			case HtmlTags.LINEHEIGHT: {
				String ss = prop.getProperty ( key ).trim ();
				float actualFontSize = HtmlUtilities.parseLength ( chain
												.getProperty ( HtmlTags.SIZE ),
								HtmlUtilities.DEFAULT_FONT_SIZE );
				if ( actualFontSize <= 0f )
					actualFontSize = HtmlUtilities.DEFAULT_FONT_SIZE;
				float v = HtmlUtilities.parseLength ( prop.getProperty ( key ),
								actualFontSize );
				if ( ss.endsWith ( "%" ) ) {
					h.put ( HtmlTags.LEADING, "0," + v / 100 );
					return;
				}
				if ( HtmlTags.NORMAL.equalsIgnoreCase ( ss ) ) {
					h.put ( HtmlTags.LEADING, "0,1.5" );
					return;
				}
				h.put ( HtmlTags.LEADING, v + ",0" );
				break;
			}
			case HtmlTags.TEXTALIGN: {
				String ss = prop.getProperty ( key ).trim ().toLowerCase ();
				h.put ( HtmlTags.ALIGN, ss );
				break;
			}
			case HtmlTags.PADDINGLEFT: {
				String ss = prop.getProperty ( key ).trim ().toLowerCase ();
				h.put ( HtmlTags.INDENT, Float.toString ( HtmlUtilities.parseLength ( ss ) ) );
				break;
			}
			}
		}
	}

	public void applyStyle ( String tag, Map<String, String> attrs ) {
		// first fetch the styles corresponding with the tag name
		Map<String, String> map = tagMap.get ( tag.toLowerCase () );
		if ( map != null ) {
			// create a new map with properties
			Map<String, String> temp = new HashMap<> ( map );
			// override with the existing properties
			temp.putAll ( attrs );
			// update the existing properties
			attrs.putAll ( temp );
		}
		// look for the class attribute
		String cm = attrs.get ( HtmlTags.CLASS );
		if ( cm == null )
			return;
		// fetch the styles corresponding with the class attribute
		map = classMap.get ( cm.toLowerCase () );
		if ( map == null )
			return;
		// remove the class attribute from the properties
		attrs.remove ( HtmlTags.CLASS );
		// create a map with the styles corresponding with the class value
		Map<String, String> temp = new HashMap<> ( map );
		// override with the existing properties
		temp.putAll ( attrs );
		// update the properties
		attrs.putAll ( temp );
	}
}

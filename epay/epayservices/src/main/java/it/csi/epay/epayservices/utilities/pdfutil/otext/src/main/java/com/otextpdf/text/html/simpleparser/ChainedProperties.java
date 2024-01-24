/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.html.simpleparser;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.html.HtmlTags;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.html.HtmlUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ChainedProperties {

	public List<TagAttributes> chain = new ArrayList<> ();

	public ChainedProperties () {
	}

	public String getProperty ( String key ) {
		for ( int k = chain.size () - 1; k >= 0; --k ) {
			TagAttributes p = chain.get ( k );
			Map<String, String> attrs = p.attrs;
			String ret = attrs.get ( key );
			if ( ret != null )
				return ret;
		}
		return null;
	}

	public boolean hasProperty ( String key ) {
		for ( int k = chain.size () - 1; k >= 0; --k ) {
			TagAttributes p = chain.get ( k );
			Map<String, String> attrs = p.attrs;
			if ( attrs.containsKey ( key ) )
				return true;
		}
		return false;
	}

	public void addToChain ( String tag, Map<String, String> props ) {
		this.adjustFontSize ( props );
		chain.add ( new TagAttributes ( tag, props ) );
	}

	public void removeChain ( String tag ) {
		for ( int k = chain.size () - 1; k >= 0; --k ) {
			if ( tag.equals ( chain.get ( k ).tag ) ) {
				chain.remove ( k );
				return;
			}
		}
	}

	protected void adjustFontSize ( Map<String, String> attrs ) {
		// fetch the font size
		String value = attrs.get ( HtmlTags.SIZE );
		// do nothing if the font size isn't defined
		if ( value == null )
			return;
		// the font is defined as a real size: remove "pt"
		if ( value.endsWith ( "pt" ) ) {
			attrs.put ( HtmlTags.SIZE,
							value.substring ( 0, value.length () - 2 ) );
			return;
		}
		String old = getProperty ( HtmlTags.SIZE );
		attrs.put ( HtmlTags.SIZE, Integer.toString ( HtmlUtilities.getIndexedFontSize ( value, old ) ) );
	}

	private static final class TagAttributes {

		final String tag;

		final Map<String, String> attrs;

		TagAttributes ( String tag, Map<String, String> attrs ) {
			this.tag = tag;
			this.attrs = attrs;
		}
	}
}

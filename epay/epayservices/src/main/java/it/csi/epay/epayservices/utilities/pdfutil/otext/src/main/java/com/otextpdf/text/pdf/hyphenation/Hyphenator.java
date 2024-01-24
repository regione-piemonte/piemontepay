/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.hyphenation;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io.StreamUtil;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Hashtable;


public class Hyphenator {

	private static final Hashtable<String, HyphenationTree> hyphenTrees = new Hashtable<> ();

	private static final String defaultHyphLocation = "com/otextpdf/text/pdf/hyphenation/hyph/";

	private static final String hyphenDir = "";

	private final HyphenationTree hyphenTree;

	private final int remainCharCount;

	private final int pushCharCount;

	public Hyphenator ( String lang, String country, int leftMin,
					int rightMin ) {
		hyphenTree = getHyphenationTree ( lang, country );
		remainCharCount = leftMin;
		pushCharCount = rightMin;
	}

	public static HyphenationTree getHyphenationTree ( String lang,
					String country ) {
		String key = lang;
		// check whether the country code has been used
		if ( country != null && !country.equals ( "none" ) ) {
			key += "_" + country;
		}
		// first try to find it in the cache
		if ( hyphenTrees.containsKey ( key ) ) {
			return hyphenTrees.get ( key );
		}
		if ( hyphenTrees.containsKey ( lang ) ) {
			return hyphenTrees.get ( lang );
		}

		HyphenationTree hTree = getResourceHyphenationTree ( key );
		if ( hTree == null )
			hTree = getFileHyphenationTree ( key );
		// put it into the pattern cache
		if ( hTree != null ) {
			hyphenTrees.put ( key, hTree );
		}
		return hTree;
	}

	public static HyphenationTree getResourceHyphenationTree ( String key ) {
		try {
			InputStream stream = StreamUtil.getResourceStream ( defaultHyphLocation + key + ".xml" );
			if ( stream == null && key.length () > 2 )
				stream = StreamUtil.getResourceStream ( defaultHyphLocation + key.substring ( 0, 2 ) + ".xml" );
			if ( stream == null )
				return null;
			HyphenationTree hTree = new HyphenationTree ();
			hTree.loadSimplePatterns ( stream );
			return hTree;
		} catch ( Exception e ) {
			return null;
		}
	}

	public static HyphenationTree getFileHyphenationTree ( String key ) {
		try {
			InputStream stream = null;
			File hyphenFile = new File ( hyphenDir, key + ".xml" );
			if ( hyphenFile.canRead () )
				stream = Files.newInputStream ( hyphenFile.toPath () );
			if ( stream == null && key.length () > 2 ) {
				hyphenFile = new File ( hyphenDir, key.substring ( 0, 2 ) + ".xml" );
				if ( hyphenFile.canRead () )
					stream = Files.newInputStream ( hyphenFile.toPath () );
			}
			if ( stream == null )
				return null;
			HyphenationTree hTree = new HyphenationTree ();
			hTree.loadSimplePatterns ( stream );
			return hTree;
		} catch ( Exception e ) {
			return null;
		}
	}

	public static Hyphenation hyphenate ( String lang, String country,
					String word, int leftMin,
					int rightMin ) {
		HyphenationTree hTree = getHyphenationTree ( lang, country );
		if ( hTree == null ) {
			return null;
		}
		return hTree.hyphenate ( word, leftMin, rightMin );
	}

	public static Hyphenation hyphenate ( String lang, String country,
					char[] word, int offset, int len,
					int leftMin, int rightMin ) {
		HyphenationTree hTree = getHyphenationTree ( lang, country );
		if ( hTree == null ) {
			return null;
		}
		return hTree.hyphenate ( word, offset, len, leftMin, rightMin );
	}

	public Hyphenation hyphenate ( char[] word, int offset, int len ) {
		if ( hyphenTree == null ) {
			return null;
		}
		return hyphenTree.hyphenate ( word, offset, len, remainCharCount,
						pushCharCount );
	}

	public Hyphenation hyphenate ( String word ) {
		if ( hyphenTree == null ) {
			return null;
		}
		return hyphenTree.hyphenate ( word, remainCharCount, pushCharCount );
	}

}

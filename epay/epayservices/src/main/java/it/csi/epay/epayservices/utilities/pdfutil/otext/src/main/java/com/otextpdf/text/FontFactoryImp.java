/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.Level;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.Logger;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.LoggerFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.BaseFont;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;


public class FontFactoryImp implements FontProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger ( FontFactoryImp.class );

	private static final String[] TTFamilyOrder = {
					"3", "1", "1033",
					"3", "0", "1033",
					"1", "0", "0",
					"0", "3", "0"
	};

	private final Hashtable<String, String> trueTypeFonts = new Hashtable<> ();

	private final Hashtable<String, ArrayList<String>> fontFamilies = new Hashtable<> ();

	public String defaultEncoding = BaseFont.WINANSI;

	public boolean defaultEmbedding = BaseFont.NOT_EMBEDDED;

	public FontFactoryImp () {
		trueTypeFonts.put ( FontFactory.COURIER.toLowerCase (), FontFactory.COURIER );
		trueTypeFonts.put ( FontFactory.COURIER_BOLD.toLowerCase (), FontFactory.COURIER_BOLD );
		trueTypeFonts.put ( FontFactory.COURIER_OBLIQUE.toLowerCase (), FontFactory.COURIER_OBLIQUE );
		trueTypeFonts.put ( FontFactory.COURIER_BOLDOBLIQUE.toLowerCase (), FontFactory.COURIER_BOLDOBLIQUE );
		trueTypeFonts.put ( FontFactory.HELVETICA.toLowerCase (), FontFactory.HELVETICA );
		trueTypeFonts.put ( FontFactory.HELVETICA_BOLD.toLowerCase (), FontFactory.HELVETICA_BOLD );
		trueTypeFonts.put ( FontFactory.HELVETICA_OBLIQUE.toLowerCase (), FontFactory.HELVETICA_OBLIQUE );
		trueTypeFonts.put ( FontFactory.HELVETICA_BOLDOBLIQUE.toLowerCase (), FontFactory.HELVETICA_BOLDOBLIQUE );
		trueTypeFonts.put ( FontFactory.SYMBOL.toLowerCase (), FontFactory.SYMBOL );
		trueTypeFonts.put ( FontFactory.TIMES_ROMAN.toLowerCase (), FontFactory.TIMES_ROMAN );
		trueTypeFonts.put ( FontFactory.TIMES_BOLD.toLowerCase (), FontFactory.TIMES_BOLD );
		trueTypeFonts.put ( FontFactory.TIMES_ITALIC.toLowerCase (), FontFactory.TIMES_ITALIC );
		trueTypeFonts.put ( FontFactory.TIMES_BOLDITALIC.toLowerCase (), FontFactory.TIMES_BOLDITALIC );
		trueTypeFonts.put ( FontFactory.ZAPFDINGBATS.toLowerCase (), FontFactory.ZAPFDINGBATS );

		ArrayList<String> tmp;
		tmp = new ArrayList<> ();
		tmp.add ( FontFactory.COURIER );
		tmp.add ( FontFactory.COURIER_BOLD );
		tmp.add ( FontFactory.COURIER_OBLIQUE );
		tmp.add ( FontFactory.COURIER_BOLDOBLIQUE );
		fontFamilies.put ( FontFactory.COURIER.toLowerCase (), tmp );
		tmp = new ArrayList<> ();
		tmp.add ( FontFactory.HELVETICA );
		tmp.add ( FontFactory.HELVETICA_BOLD );
		tmp.add ( FontFactory.HELVETICA_OBLIQUE );
		tmp.add ( FontFactory.HELVETICA_BOLDOBLIQUE );
		fontFamilies.put ( FontFactory.HELVETICA.toLowerCase (), tmp );
		tmp = new ArrayList<> ();
		tmp.add ( FontFactory.SYMBOL );
		fontFamilies.put ( FontFactory.SYMBOL.toLowerCase (), tmp );
		tmp = new ArrayList<> ();
		tmp.add ( FontFactory.TIMES_ROMAN );
		tmp.add ( FontFactory.TIMES_BOLD );
		tmp.add ( FontFactory.TIMES_ITALIC );
		tmp.add ( FontFactory.TIMES_BOLDITALIC );
		fontFamilies.put ( FontFactory.TIMES.toLowerCase (), tmp );
		fontFamilies.put ( FontFactory.TIMES_ROMAN.toLowerCase (), tmp );
		tmp = new ArrayList<> ();
		tmp.add ( FontFactory.ZAPFDINGBATS );
		fontFamilies.put ( FontFactory.ZAPFDINGBATS.toLowerCase (), tmp );
	}

	public Font getFont ( final String fontname, final String encoding, final boolean embedded, final float size, final int style, final BaseColor color ) {
		return getFont ( fontname, encoding, embedded, size, style, color, true );
	}

	public Font getFont ( String fontname, final String encoding, final boolean embedded, final float size, int style, final BaseColor color,
					final boolean cached ) {
		if ( fontname == null )
			return new Font ( Font.FontFamily.UNDEFINED, size, style, color );
		String lowercasefontname = fontname.toLowerCase ();
		ArrayList<String> tmp = fontFamilies.get ( lowercasefontname );
		if ( tmp != null ) {
			synchronized ( tmp ) {
				// some bugs were fixed here by Daniel Marczisovszky
				int s = style == Font.UNDEFINED ? Font.NORMAL : style;
				int fs = Font.NORMAL;
				boolean found = false;
				for ( String f : tmp ) {
					String lcf = f.toLowerCase ();
					fs = Font.NORMAL;
					if ( lcf.contains ( "bold" ) )
						fs |= Font.BOLD;
					if ( lcf.contains ( "italic" ) || lcf.contains ( "oblique" ) )
						fs |= Font.ITALIC;
					if ( ( s & Font.BOLDITALIC ) == fs ) {
						fontname = f;
						found = true;
						break;
					}
				}
				if ( style != Font.UNDEFINED && found ) {
					style &= ~fs;
				}
			}
		}
		BaseFont basefont = null;
		try {
			try {
				basefont = BaseFont.createFont ( fontname, encoding, embedded, cached, null, null, true );
			} catch ( DocumentException ignored ) {
			}
			if ( basefont == null ) {
				fontname = trueTypeFonts.get ( fontname.toLowerCase () );
				if ( fontname == null )
					return new Font ( Font.FontFamily.UNDEFINED, size, style, color );
				basefont = BaseFont.createFont ( fontname, encoding, embedded, cached, null, null );
			}
		} catch ( DocumentException de ) {
			throw new ExceptionConverter ( de );
		} catch ( IOException | NullPointerException ioe ) {
			return new Font ( Font.FontFamily.UNDEFINED, size, style, color );
		}
		return new Font ( basefont, size, style, color );
	}

	public Font getFont ( final String fontname, final String encoding, final boolean embedded, final float size, final int style ) {
		return getFont ( fontname, encoding, embedded, size, style, null );
	}

	public Font getFont ( final String fontname, final String encoding, final boolean embedded, final float size ) {
		return getFont ( fontname, encoding, embedded, size, Font.UNDEFINED, null );
	}

	public Font getFont ( final String fontname, final String encoding, final boolean embedded ) {
		return getFont ( fontname, encoding, embedded, Font.UNDEFINED, Font.UNDEFINED, null );
	}

	public Font getFont ( final String fontname, final String encoding, final float size, final int style, final BaseColor color ) {
		return getFont ( fontname, encoding, defaultEmbedding, size, style, color );
	}

	public Font getFont ( final String fontname, final String encoding, final float size, final int style ) {
		return getFont ( fontname, encoding, defaultEmbedding, size, style, null );
	}

	public Font getFont ( final String fontname, final String encoding, final float size ) {
		return getFont ( fontname, encoding, defaultEmbedding, size, Font.UNDEFINED, null );
	}

	public Font getFont ( final String fontname, final float size, final BaseColor color ) {
		return getFont ( fontname, defaultEncoding, defaultEmbedding, size, Font.UNDEFINED, color );
	}

	public Font getFont ( final String fontname, final String encoding ) {
		return getFont ( fontname, encoding, defaultEmbedding, Font.UNDEFINED, Font.UNDEFINED, null );
	}

	public Font getFont ( final String fontname, final float size, final int style, final BaseColor color ) {
		return getFont ( fontname, defaultEncoding, defaultEmbedding, size, style, color );
	}

	public Font getFont ( final String fontname, final float size, final int style ) {
		return getFont ( fontname, defaultEncoding, defaultEmbedding, size, style, null );
	}

	public Font getFont ( final String fontname, final float size ) {
		return getFont ( fontname, defaultEncoding, defaultEmbedding, size, Font.UNDEFINED, null );
	}

	public Font getFont ( final String fontname ) {
		return getFont ( fontname, defaultEncoding, defaultEmbedding, Font.UNDEFINED, Font.UNDEFINED, null );
	}

	public void registerFamily ( final String familyName, final String fullName, final String path ) {
		if ( path != null )
			trueTypeFonts.put ( fullName, path );
		ArrayList<String> tmp;
		synchronized ( fontFamilies ) {
			tmp = fontFamilies.computeIfAbsent ( familyName, k -> new ArrayList<> () );
		}
		synchronized ( tmp ) {
			if ( !tmp.contains ( fullName ) ) {
				int fullNameLength = fullName.length ();
				boolean inserted = false;
				for ( int j = 0; j < tmp.size (); ++j ) {
					if ( tmp.get ( j ).length () >= fullNameLength ) {
						tmp.add ( j, fullName );
						inserted = true;
						break;
					}
				}
				if ( !inserted )
					tmp.add ( fullName );
			}
		}
	}

	public void register ( final String path ) {
		register ( path, null );
	}

	public void register ( final String path, final String alias ) {
		try {
			if ( path.toLowerCase ().endsWith ( ".ttf" ) || path.toLowerCase ().endsWith ( ".otf" ) || path.toLowerCase ().indexOf ( ".ttc," ) > 0 ) {
				Object[] allNames = BaseFont.getAllFontNames ( path, BaseFont.WINANSI, null );
				trueTypeFonts.put ( ( (String) allNames[0] ).toLowerCase (), path );
				if ( alias != null ) {
					trueTypeFonts.put ( alias.toLowerCase (), path );
				}
				String[][] names = (String[][]) allNames[2];
				for ( String[] name : names ) {
					trueTypeFonts.put ( name[3].toLowerCase (), path );
				}
				String fullName;
				String familyName = null;
				names = (String[][]) allNames[1]; //family name
				for ( int k = 0; k < TTFamilyOrder.length; k += 3 ) {
					for ( String[] name : names ) {
						if ( TTFamilyOrder[k].equals ( name[0] ) && TTFamilyOrder[k + 1].equals ( name[1] ) && TTFamilyOrder[k + 2].equals ( name[2] ) ) {
							familyName = name[3].toLowerCase ();
							k = TTFamilyOrder.length;
							break;
						}
					}
				}
				if ( familyName != null ) {
					String lastName = "";
					names = (String[][]) allNames[2]; //full name
					for ( String[] name : names ) {
						for ( int k = 0; k < TTFamilyOrder.length; k += 3 ) {
							if ( TTFamilyOrder[k].equals ( name[0] ) && TTFamilyOrder[k + 1].equals ( name[1] ) && TTFamilyOrder[k + 2].equals ( name[2] ) ) {
								fullName = name[3];
								if ( fullName.equals ( lastName ) )
									continue;
								lastName = fullName;
								registerFamily ( familyName, fullName, null );
								break;
							}
						}
					}
				}
			} else if ( path.toLowerCase ().endsWith ( ".ttc" ) ) {
				if ( alias != null )
					LOGGER.error ( "You can't define an alias for a true type collection." );
				String[] names = BaseFont.enumerateTTCNames ( path );
				for ( int i = 0; i < names.length; i++ ) {
					register ( path + "," + i );
				}
			} else if ( path.toLowerCase ().endsWith ( ".afm" ) || path.toLowerCase ().endsWith ( ".pfm" ) ) {
				BaseFont bf = BaseFont.createFont ( path, BaseFont.CP1252, false );
				String fullName = bf.getFullFontName ()[0][3].toLowerCase ();
				String familyName = bf.getFamilyFontName ()[0][3].toLowerCase ();
				String psName = bf.getPostscriptFontName ().toLowerCase ();
				registerFamily ( familyName, fullName, null );
				trueTypeFonts.put ( psName, path );
				trueTypeFonts.put ( fullName, path );
			}
			if ( LOGGER.isLogging ( Level.TRACE ) ) {
				LOGGER.trace ( String.format ( "Registered %s", path ) );
			}
		} catch ( DocumentException | IOException de ) {
			throw new ExceptionConverter ( de );
		}
	}

	public boolean isRegistered ( final String fontname ) {
		return trueTypeFonts.containsKey ( fontname.toLowerCase () );
	}
}

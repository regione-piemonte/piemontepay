/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PRTokeniser;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfContentParser;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfNumber;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfString;

import java.io.IOException;
import java.util.ArrayList;


public class CMapParserEx {

	private static final PdfName CMAPNAME = new PdfName ( "CMapName" );

	private static final String DEF = "def";

	private static final String ENDCIDRANGE = "endcidrange";

	private static final String ENDCIDCHAR = "endcidchar";

	private static final String ENDBFRANGE = "endbfrange";

	private static final String ENDBFCHAR = "endbfchar";

	private static final String USECMAP = "usecmap";

	private static final int MAXLEVEL = 10;

	public static void parseCid ( String cmapName, AbstractCMap cmap, CidLocation location ) throws IOException {
		parseCid ( cmapName, cmap, location, 0 );
	}

	private static void parseCid ( String cmapName, AbstractCMap cmap, CidLocation location, int level ) throws IOException {
		if ( level >= MAXLEVEL )
			return;
		PRTokeniser inp = location.getLocation ( cmapName );
		try {
			ArrayList<PdfObject> list = new ArrayList<> ();
			PdfContentParser cp = new PdfContentParser ( inp );
			int maxExc = 50;
			while ( true ) {
				try {
					cp.parse ( list );
				} catch ( Exception ex ) {
					if ( --maxExc < 0 )
						break;
					continue;
				}
				if ( list.isEmpty () )
					break;
				String last = list.get ( list.size () - 1 ).toString ();
				if ( level == 0 && list.size () == 3 && last.equals ( DEF ) ) {
					PdfObject key = list.get ( 0 );
					if ( PdfName.REGISTRY.equals ( key ) )
						cmap.setRegistry ( list.get ( 1 ).toString () );
					else if ( PdfName.ORDERING.equals ( key ) )
						cmap.setOrdering ( list.get ( 1 ).toString () );
					else if ( CMAPNAME.equals ( key ) )
						cmap.setName ( list.get ( 1 ).toString () );
					else if ( PdfName.SUPPLEMENT.equals ( key ) ) {
						try {
							cmap.setSupplement ( ( (PdfNumber) list.get ( 1 ) ).intValue () );
						} catch ( Exception ignored ) {
						}
					}
				} else if ( ( last.equals ( ENDCIDCHAR ) || last.equals ( ENDBFCHAR ) ) && list.size () >= 3 ) {
					int lmax = list.size () - 2;
					for ( int k = 0; k < lmax; k += 2 ) {
						if ( list.get ( k ) instanceof PdfString ) {
							cmap.addChar ( (PdfString) list.get ( k ), list.get ( k + 1 ) );
						}
					}
				} else if ( ( last.equals ( ENDCIDRANGE ) || last.equals ( ENDBFRANGE ) ) && list.size () >= 4 ) {
					int lmax = list.size () - 3;
					for ( int k = 0; k < lmax; k += 3 ) {
						if ( list.get ( k ) instanceof PdfString && list.get ( k + 1 ) instanceof PdfString ) {
							cmap.addRange ( (PdfString) list.get ( k ), (PdfString) list.get ( k + 1 ), list.get ( k + 2 ) );
						}
					}
				} else if ( last.equals ( USECMAP ) && list.size () == 2 && list.get ( 0 ) instanceof PdfName ) {
					parseCid ( PdfName.decodeName ( list.get ( 0 ).toString () ), cmap, location, level + 1 );
				}
			}
		} finally {
			inp.close ();
		}
	}

}

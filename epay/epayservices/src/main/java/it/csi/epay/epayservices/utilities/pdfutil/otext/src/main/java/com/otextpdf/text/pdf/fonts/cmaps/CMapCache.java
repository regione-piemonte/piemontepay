/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps;

import java.io.IOException;
import java.util.HashMap;


public class CMapCache {

	private static final HashMap<String, CMapUniCid> cacheUniCid = new HashMap<> ();

	private static final HashMap<String, CMapCidUni> cacheCidUni = new HashMap<> ();

	private static final HashMap<String, CMapCidByte> cacheCidByte = new HashMap<> ();

	private static final HashMap<String, CMapByteCid> cacheByteCid = new HashMap<> ();

	public static CMapUniCid getCachedCMapUniCid ( String name ) throws IOException {
		CMapUniCid cmap;
		synchronized ( cacheUniCid ) {
			cmap = cacheUniCid.get ( name );
		}
		if ( cmap == null ) {
			cmap = new CMapUniCid ();
			CMapParserEx.parseCid ( name, cmap, new CidResource () );
			synchronized ( cacheUniCid ) {
				cacheUniCid.put ( name, cmap );
			}
		}
		return cmap;
	}

	public static CMapCidUni getCachedCMapCidUni ( String name ) throws IOException {
		CMapCidUni cmap;
		synchronized ( cacheCidUni ) {
			cmap = cacheCidUni.get ( name );
		}
		if ( cmap == null ) {
			cmap = new CMapCidUni ();
			CMapParserEx.parseCid ( name, cmap, new CidResource () );
			synchronized ( cacheCidUni ) {
				cacheCidUni.put ( name, cmap );
			}
		}
		return cmap;
	}

	public static CMapCidByte getCachedCMapCidByte ( String name ) throws IOException {
		CMapCidByte cmap;
		synchronized ( cacheCidByte ) {
			cmap = cacheCidByte.get ( name );
		}
		if ( cmap == null ) {
			cmap = new CMapCidByte ();
			CMapParserEx.parseCid ( name, cmap, new CidResource () );
			synchronized ( cacheCidByte ) {
				cacheCidByte.put ( name, cmap );
			}
		}
		return cmap;
	}

	public static CMapByteCid getCachedCMapByteCid ( String name ) throws IOException {
		CMapByteCid cmap;
		synchronized ( cacheByteCid ) {
			cmap = cacheByteCid.get ( name );
		}
		if ( cmap == null ) {
			cmap = new CMapByteCid ();
			CMapParserEx.parseCid ( name, cmap, new CidResource () );
			synchronized ( cacheByteCid ) {
				cacheByteCid.put ( name, cmap );
			}
		}
		return cmap;
	}
}

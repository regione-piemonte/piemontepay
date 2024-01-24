/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps;

import java.io.IOException;


public class IdentityToUnicode {

	private static CMapToUnicode identityCNS;

	private static CMapToUnicode identityJapan;

	private static CMapToUnicode identityKorea;

	private static CMapToUnicode identityGB;

	public static CMapToUnicode GetMapFromOrdering ( String ordering ) throws IOException {
		switch ( ordering ) {
		case "CNS1":
			if ( identityCNS == null ) {
				CMapUniCid uni = CMapCache.getCachedCMapUniCid ( "UniCNS-UTF16-H" );
				identityCNS = uni.exportToUnicode ();
			}
			return identityCNS;
		case "Japan1":
			if ( identityJapan == null ) {
				CMapUniCid uni = CMapCache.getCachedCMapUniCid ( "UniJIS-UTF16-H" );
				identityJapan = uni.exportToUnicode ();
			}
			return identityJapan;
		case "Korea1":
			if ( identityKorea == null ) {
				CMapUniCid uni = CMapCache.getCachedCMapUniCid ( "UniKS-UTF16-H" );
				identityKorea = uni.exportToUnicode ();
			}
			return identityKorea;
		case "GB1":
			if ( identityGB == null ) {
				CMapUniCid uni = CMapCache.getCachedCMapUniCid ( "UniGB-UTF16-H" );
				identityGB = uni.exportToUnicode ();
			}
			return identityGB;
		}
		return null;
	}
}

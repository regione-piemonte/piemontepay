/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import java.lang.reflect.Method;


public final class Version {

	private static Version version = null;

	private final String oText = "iText\u00ae";

	private final String release = "5.5.0";

	private String key = null;

	private String oTextVersion = oText + " " + release + " \u00a92000-2013 iText Group NV";

	public static Version getInstance () {
		if ( version == null ) {
			version = new Version ();
			try {
				Class<?> klass = Class.forName ( "com.otextpdf.license.LicenseKey" );
				Method m = klass.getMethod ( "getLicenseeInfo" );
				String[] info = (String[]) m.invoke ( klass.newInstance () );
				if ( info[3] != null && !info[3].trim ().isEmpty () ) {
					version.key = info[3];
				} else {
					version.key = "Trial version ";
					if ( info[5] == null ) {
						version.key += "unauthorised";
					} else {
						version.key += info[5];
					}
				}
				if ( info[4] != null && !info[4].trim ().isEmpty () ) {
					version.oTextVersion = info[4];
				} else if ( info[2] != null && !info[2].trim ().isEmpty () ) {
					version.oTextVersion += " (" + info[2];
					if ( !version.key.toLowerCase ().startsWith ( "trial" ) ) {
						version.oTextVersion += "; licensed version)";
					} else {
						version.oTextVersion += "; " + version.key + ")";
					}
				} else if ( info[0] != null && !info[0].trim ().isEmpty () ) {
					version.oTextVersion += " (" + info[0];
					if ( !version.key.toLowerCase ().startsWith ( "trial" ) ) {
						version.oTextVersion += "; licensed version)";
					} else {
						version.oTextVersion += "; " + version.key + ")";
					}
				} else {
					throw new Exception ();
				}
			} catch ( Exception e ) {
				version.oTextVersion += " (AGPL-version)";
			}
		}
		return version;
	}

	public String getProduct () {
		return oText;
	}

	public String getRelease () {
		return release;
	}

	public String getVersion () {
		return oTextVersion;
	}

	public String getKey () {
		return key;
	}
}

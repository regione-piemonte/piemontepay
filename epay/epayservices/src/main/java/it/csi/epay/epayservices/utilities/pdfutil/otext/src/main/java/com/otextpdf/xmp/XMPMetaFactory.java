/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl.XMPMetaImpl;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl.XMPMetaParser;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl.XMPSchemaRegistryImpl;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl.XMPSerializerHelper;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.ParseOptions;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.SerializeOptions;

import java.io.InputStream;
import java.io.OutputStream;


public final class XMPMetaFactory {

	private static XMPSchemaRegistry schema = new XMPSchemaRegistryImpl ();

	private static XMPVersionInfo versionInfo = null;

	private XMPMetaFactory () {
		// EMPTY
	}

	public static XMPSchemaRegistry getSchemaRegistry () {
		return schema;
	}

	public static XMPMeta create () {
		return new XMPMetaImpl ();
	}

	public static XMPMeta parse ( InputStream in ) throws XMPException {
		return parse ( in, null );
	}

	public static XMPMeta parse ( InputStream in, ParseOptions options )
					throws XMPException {
		return XMPMetaParser.parse ( in, options );
	}

	public static XMPMeta parseFromString ( String packet ) throws XMPException {
		return parseFromString ( packet, null );
	}

	public static XMPMeta parseFromString ( String packet, ParseOptions options )
					throws XMPException {
		return XMPMetaParser.parse ( packet, options );
	}

	public static XMPMeta parseFromBuffer ( byte[] buffer ) throws XMPException {
		return parseFromBuffer ( buffer, null );
	}

	public static XMPMeta parseFromBuffer ( byte[] buffer,
					ParseOptions options ) throws XMPException {
		return XMPMetaParser.parse ( buffer, options );
	}

	public static void serialize ( XMPMeta xmp, OutputStream out, SerializeOptions options )
					throws XMPException {
		assertImplementation ( xmp );
		XMPSerializerHelper.serialize ( (XMPMetaImpl) xmp, out, options );
	}

	private static void assertImplementation ( XMPMeta xmp ) {
		if ( !( xmp instanceof XMPMetaImpl ) ) {
			throw new UnsupportedOperationException ( "The serializing service works only" +
							"with the XMPMeta implementation of this library" );
		}
	}

	public static void reset () {
		schema = new XMPSchemaRegistryImpl ();
	}

	public static synchronized XMPVersionInfo getVersionInfo () {
		if ( versionInfo == null ) {
			try {
				final int micro = 0;
				final int engBuild = 3;
				final boolean debug = false;

				final String message = "Adobe XMP Core 5.1.0-jc003";

				versionInfo = new XMPVersionInfo () {

					public int getMicro () {
						return micro;
					}

					public boolean isDebug () {
						return debug;
					}

					public int getBuild () {
						return engBuild;
					}

					public String getMessage () {
						return message;
					}

					public String toString () {
						return message;
					}
				};

			} catch ( Throwable ignored ) {

			}
		}
		return versionInfo;
	}
}

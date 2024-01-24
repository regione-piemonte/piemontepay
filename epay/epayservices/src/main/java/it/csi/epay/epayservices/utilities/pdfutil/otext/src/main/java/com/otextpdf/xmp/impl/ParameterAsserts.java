/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPConst;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPError;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPMeta;


class ParameterAsserts implements XMPConst {

	private ParameterAsserts () {
	}

	public static void assertArrayName ( String arrayName ) throws XMPException {
		if ( arrayName == null || arrayName.isEmpty () ) {
			throw new XMPException ( "Empty array name", XMPError.BADPARAM );
		}
	}

	public static void assertPropName ( String propName ) throws XMPException {
		if ( propName == null || propName.isEmpty () ) {
			throw new XMPException ( "Empty property name", XMPError.BADPARAM );
		}
	}

	public static void assertSchemaNS ( String schemaNS ) throws XMPException {
		if ( schemaNS == null || schemaNS.isEmpty () ) {
			throw new XMPException ( "Empty schema namespace URI", XMPError.BADPARAM );
		}
	}

	public static void assertPrefix ( String prefix ) throws XMPException {
		if ( prefix == null || prefix.isEmpty () ) {
			throw new XMPException ( "Empty prefix", XMPError.BADPARAM );
		}
	}

	public static void assertSpecificLang ( String specificLang ) throws XMPException {
		if ( specificLang == null || specificLang.isEmpty () ) {
			throw new XMPException ( "Empty specific language", XMPError.BADPARAM );
		}
	}

	public static void assertNotNull ( Object param ) throws XMPException {
		if ( param == null ) {
			throw new XMPException ( "Parameter must not be null", XMPError.BADPARAM );
		} else if ( ( param instanceof String ) && ( (String) param ).isEmpty () ) {
			throw new XMPException ( "Parameter must not be null or empty", XMPError.BADPARAM );
		}
	}

	public static void assertImplementation ( XMPMeta xmp ) throws XMPException {
		if ( xmp == null ) {
			throw new XMPException ( "Parameter must not be null",
							XMPError.BADPARAM );
		} else if ( !( xmp instanceof XMPMetaImpl ) ) {
			throw new XMPException ( "The XMPMeta-object is not compatible with this implementation",
							XMPError.BADPARAM );
		}
	}
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.properties.XMPAliasInfo;

import java.util.Map;


public interface XMPSchemaRegistry {

	String registerNamespace ( String namespaceURI, String suggestedPrefix ) throws XMPException;

	String getNamespacePrefix ( String namespaceURI );

	String getNamespaceURI ( String namespacePrefix );

	Map getNamespaces ();

	Map getPrefixes ();

	// ---------------------------------------------------------------------------------------------
	// Alias Functions

	XMPAliasInfo[] findAliases ( String aliasNS );

	XMPAliasInfo findAlias ( String qname );

	Map getAliases ();

}

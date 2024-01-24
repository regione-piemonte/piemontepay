/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.IteratorOptions;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.ParseOptions;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.PropertyOptions;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.properties.XMPProperty;


public interface XMPMeta extends Cloneable {

	XMPProperty getProperty ( String schemaNS, String propName ) throws XMPException;

	void setProperty (
					String schemaNS,
					String propName,
					Object propValue,
					PropertyOptions options ) throws XMPException;

	void setProperty (
					String schemaNS,
					String propName,
					Object propValue ) throws XMPException;

	void appendArrayItem (
					String schemaNS,
					String arrayName,
					PropertyOptions arrayOptions,
					String itemValue,
					PropertyOptions itemOptions ) throws XMPException;

	boolean doesPropertyExist ( String schemaNS, String propName );

	void setLocalizedText (
					String schemaNS,
					String altTextName,
					String genericLang,
					String specificLang,
					String itemValue,
					PropertyOptions options ) throws XMPException;

	void setLocalizedText (
					String schemaNS,
					String altTextName,
					String genericLang,
					String specificLang,
					String itemValue ) throws XMPException;

	XMPIterator iterator () throws XMPException;

	XMPIterator iterator ( IteratorOptions options ) throws XMPException;

	XMPIterator iterator (
					String schemaNS,
					String propName,
					IteratorOptions options ) throws XMPException;

	String getObjectName ();

	void setObjectName ( String name );

	Object clone ();

	void sort ();

	void normalize ( ParseOptions options ) throws XMPException;

}

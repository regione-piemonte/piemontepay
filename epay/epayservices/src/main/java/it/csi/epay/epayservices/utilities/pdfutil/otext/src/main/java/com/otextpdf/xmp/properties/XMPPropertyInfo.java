/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.properties;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.PropertyOptions;


public interface XMPPropertyInfo extends XMPProperty {

	String getNamespace ();

	String getPath ();

	String getValue ();

	PropertyOptions getOptions ();
}

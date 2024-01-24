/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import java.util.Collection;
import java.util.HashSet;


public class PdfLayerMembership extends PdfDictionary implements PdfOCG {

	public static final PdfName ALLON = new PdfName ( "AllOn" );

	public static final PdfName ANYON = new PdfName ( "AnyOn" );

	public static final PdfName ANYOFF = new PdfName ( "AnyOff" );

	public static final PdfName ALLOFF = new PdfName ( "AllOff" );

	PdfIndirectReference ref;

	PdfArray members = new PdfArray ();

	HashSet<PdfLayer> layers = new HashSet<> ();

	public PdfLayerMembership ( PdfWriter writer ) {
		super ( PdfName.OCMD );
		put ( PdfName.OCGS, members );
		ref = writer.getPdfIndirectReference ();
	}

	public PdfIndirectReference getRef () {
		return ref;
	}

	public Collection<PdfLayer> getLayers () {
		return layers;
	}

	public PdfObject getPdfObject () {
		return this;
	}
}

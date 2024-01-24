/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal.PdfIsoKeys;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;


public class PdfAcroForm extends PdfDictionary {

	private final PdfWriter writer;

	private final HashSet<PdfTemplate> fieldTemplates = new HashSet<> ();

	private final PdfArray documentFields = new PdfArray ();

	private final PdfArray calculationOrder = new PdfArray ();

	public PdfAcroForm ( PdfWriter writer ) {
		super ();
		this.writer = writer;
	}

	public void addFieldTemplates ( HashSet<PdfTemplate> ft ) {
		fieldTemplates.addAll ( ft );
	}

	public void addDocumentField ( PdfIndirectReference ref ) {
		documentFields.add ( ref );
	}

	public boolean isValid () {
		if ( documentFields.isEmpty () )
			return false;
		put ( PdfName.FIELDS, documentFields );
		if ( !calculationOrder.isEmpty () )
			put ( PdfName.CO, calculationOrder );
		if ( fieldTemplates.isEmpty () )
			return true;
		PdfDictionary dic = new PdfDictionary ();
		for ( PdfTemplate template : fieldTemplates ) {
			PdfFormField.mergeResources ( dic, (PdfDictionary) template.getResources () );
		}
		put ( PdfName.DR, dic );
		put ( PdfName.DA, new PdfString ( "/Helv 0 Tf 0 g " ) );
		PdfDictionary fonts = (PdfDictionary) dic.get ( PdfName.FONT );
		if ( fonts != null ) {
			writer.eliminateFontSubset ( fonts );
		}
		return true;
	}

	@Override
	public void toPdf ( final PdfWriter writer, final OutputStream os ) throws IOException {
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_FORM, this );
		super.toPdf ( writer, os );
	}

}

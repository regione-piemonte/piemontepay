/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import java.util.ArrayList;
import java.util.HashMap;


public class PRAcroForm extends PdfDictionary {

	ArrayList<FieldInformation> fields;

	ArrayList<PdfDictionary> stack;

	HashMap<String, FieldInformation> fieldByName;

	PdfReader reader;

	public PRAcroForm ( PdfReader reader ) {
		this.reader = reader;
		fields = new ArrayList<> ();
		fieldByName = new HashMap<> ();
		stack = new ArrayList<> ();
	}

	@Override
	public int size () {
		return fields.size ();
	}

	public ArrayList<FieldInformation> getFields () {
		return fields;
	}

	public static class FieldInformation {

		String fieldName;

		PdfDictionary info;

		PRIndirectReference ref;

		FieldInformation ( String fieldName, PdfDictionary info, PRIndirectReference ref ) {
			this.fieldName = fieldName;
			this.info = info;
			this.ref = ref;
		}

		public String getName () {
			return fieldName;
		}

		public PdfDictionary getInfo () {
			return info;
		}

		public PRIndirectReference getRef () {
			return ref;
		}
	}

}

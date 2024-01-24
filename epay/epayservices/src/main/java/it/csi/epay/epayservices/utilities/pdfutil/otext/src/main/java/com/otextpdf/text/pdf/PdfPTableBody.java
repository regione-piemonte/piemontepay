/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.AccessibleElementId;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.IAccessibleElement;

import java.util.ArrayList;
import java.util.HashMap;


public class PdfPTableBody implements IAccessibleElement {

	protected AccessibleElementId id = new AccessibleElementId ();

	protected ArrayList<PdfPRow> rows = null;

	protected PdfName role = PdfName.TBODY;

	protected HashMap<PdfName, PdfObject> accessibleAttributes = null;

	public PdfPTableBody () {

	}

	public PdfObject getAccessibleAttribute ( final PdfName key ) {
		if ( accessibleAttributes != null )
			return accessibleAttributes.get ( key );
		else
			return null;
	}

	public void setAccessibleAttribute ( final PdfName key, final PdfObject value ) {
		if ( accessibleAttributes == null )
			accessibleAttributes = new HashMap<> ();
		accessibleAttributes.put ( key, value );
	}

	public HashMap<PdfName, PdfObject> getAccessibleAttributes () {
		return accessibleAttributes;
	}

	public PdfName getRole () {
		return role;
	}

	public void setRole ( final PdfName role ) {
		this.role = role;
	}

	public AccessibleElementId getId () {
		return id;
	}

	public void setId ( final AccessibleElementId id ) {
		this.id = id;
	}

	public boolean isInline () {
		return false;
	}
}

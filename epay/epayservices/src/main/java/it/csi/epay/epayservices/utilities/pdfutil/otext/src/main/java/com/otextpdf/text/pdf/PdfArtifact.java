/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.AccessibleElementId;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.IAccessibleElement;

import java.util.HashMap;


public class PdfArtifact implements IAccessibleElement {

	protected PdfName role = PdfName.ARTIFACT;

	protected HashMap<PdfName, PdfObject> accessibleAttributes = null;

	protected AccessibleElementId id = new AccessibleElementId ();

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
	}

	public AccessibleElementId getId () {
		return id;
	}

	public void setId ( final AccessibleElementId id ) {
		this.id = id;
	}

	public boolean isInline () {
		return true;
	}

	public PdfString getType () {
		return accessibleAttributes == null ? null : (PdfString) accessibleAttributes.get ( PdfName.TYPE );
	}

	public void setType ( PdfString type ) {
		setAccessibleAttribute ( PdfName.TYPE, type );
	}

	public PdfArray getAttached () {
		return accessibleAttributes == null ? null : (PdfArray) accessibleAttributes.get ( PdfName.ATTACHED );
	}

	public void setAttached ( PdfArray attached ) {
		setAccessibleAttribute ( PdfName.ATTACHED, attached );
	}

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;


public class ListLabel extends ListBody {

	protected PdfName role = PdfName.LBL;

	protected float indentation = 0;

	protected ListLabel ( final ListItem parentItem ) {
		super ( parentItem );
	}

	public PdfName getRole () {
		return role;
	}

	public void setRole ( final PdfName role ) {
		this.role = role;
	}

	public float getIndentation () {
		return indentation;
	}

	public void setIndentation ( float indentation ) {
		this.indentation = indentation;
	}

	@Deprecated
	public boolean getTagLabelContent () {
		return false;
	}

	@Deprecated
	public void setTagLabelContent () {
	}

	@Override
	public boolean isInline () {
		return true;
	}
}

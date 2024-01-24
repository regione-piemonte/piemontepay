/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

public class StampContent extends PdfContentByte {

	PdfStamperImp.PageStamp ps;

	PageResources pageResources;

	StampContent ( PdfStamperImp stamper, PdfStamperImp.PageStamp ps ) {
		super ( stamper );
		this.ps = ps;
		pageResources = ps.pageResources;
	}

	public void setAction ( PdfAction action, float llx, float lly, float urx, float ury ) {
		( (PdfStamperImp) writer ).addAnnotation ( new PdfAnnotation ( writer, llx, lly, urx, ury, action ), ps.pageN );
	}

	public PdfContentByte getDuplicate () {
		return new StampContent ( (PdfStamperImp) writer, ps );
	}

	PageResources getPageResources () {
		return pageResources;
	}

	void addAnnotation ( PdfAnnotation annot ) {
		( (PdfStamperImp) writer ).addAnnotation ( annot, ps.pageN );
	}
}

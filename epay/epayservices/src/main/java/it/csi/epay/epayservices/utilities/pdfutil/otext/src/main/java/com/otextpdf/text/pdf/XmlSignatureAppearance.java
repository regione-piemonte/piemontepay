/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.security.XmlLocator;

import java.io.IOException;
import java.security.cert.Certificate;
import java.util.Calendar;


public class XmlSignatureAppearance {

	private final PdfStamperImp writer;

	private PdfStamper stamper;

	private Certificate signCertificate;

	private XmlLocator xmlLocator;

	private Calendar signDate;

	private String description;

	private String mimeType = "text/xml";

	XmlSignatureAppearance ( PdfStamperImp writer ) {
		this.writer = writer;
	}

	public PdfStamperImp getWriter () {
		return writer;
	}

	public Certificate getCertificate () {
		return signCertificate;
	}

	public void setCertificate ( Certificate signCertificate ) {
		this.signCertificate = signCertificate;
	}

	public String getDescription () {
		return description;
	}

	public void setDescription ( String description ) {
		this.description = description;
	}

	public String getMimeType () {
		return mimeType;
	}

	public void setMimeType ( String mimeType ) {
		this.mimeType = mimeType;
	}

	public void close () throws IOException, DocumentException {
		writer.close ( stamper.getMoreInfo () );
	}
}

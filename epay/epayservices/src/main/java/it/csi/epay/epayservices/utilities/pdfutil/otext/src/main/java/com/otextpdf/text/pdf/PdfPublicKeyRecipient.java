/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import java.security.cert.Certificate;


public class PdfPublicKeyRecipient {

	private final Certificate certificate;

	private final int permission;

	protected byte[] cms = null;

	public PdfPublicKeyRecipient ( Certificate certificate, int permission ) {
		this.certificate = certificate;
		this.permission = permission;
	}

	public Certificate getCertificate () {
		return certificate;
	}

	public int getPermission () {
		return permission;
	}

	protected byte[] getCms () {
		return cms;
	}

	protected void setCms ( byte[] cms ) {
		this.cms = cms;
	}
}

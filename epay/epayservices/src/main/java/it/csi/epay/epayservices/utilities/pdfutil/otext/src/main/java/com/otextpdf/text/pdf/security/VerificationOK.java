/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.security;

import java.security.cert.X509Certificate;


public class VerificationOK {

	protected X509Certificate certificate;

	protected Class<? extends CertificateVerifier> verifierClass;

	protected String message;

	public VerificationOK ( X509Certificate certificate,
					Class<? extends CertificateVerifier> verifierClass, String message ) {
		this.certificate = certificate;
		this.verifierClass = verifierClass;
		this.message = message;
	}

	public String toString () {
		StringBuilder sb = new StringBuilder ();
		if ( certificate != null ) {
			sb.append ( certificate.getSubjectDN ().getName () );
			sb.append ( " verified with " );
		}
		sb.append ( verifierClass.getName () );
		sb.append ( ": " );
		sb.append ( message );
		return sb.toString ();
	}
}

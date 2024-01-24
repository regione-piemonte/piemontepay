/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.security;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CertificateVerifier {

	protected CertificateVerifier verifier;

	protected boolean onlineCheckingAllowed = true;

	public CertificateVerifier ( CertificateVerifier verifier ) {
		this.verifier = verifier;
	}

	public List<VerificationOK> verify ( X509Certificate signCert, X509Certificate issuerCert, Date signDate )
					throws GeneralSecurityException, IOException {
		// Check if the certificate is valid on the signDate
		if ( signDate != null )
			signCert.checkValidity ( signDate );
		// Check if the signature is valid
		if ( issuerCert != null ) {
			signCert.verify ( issuerCert.getPublicKey () );
		}
		// Also in case, the certificate is self-signed
		else {
			signCert.verify ( signCert.getPublicKey () );
		}
		List<VerificationOK> result = new ArrayList<> ();
		if ( verifier != null )
			result.addAll ( verifier.verify ( signCert, issuerCert, signDate ) );
		return result;
	}
}

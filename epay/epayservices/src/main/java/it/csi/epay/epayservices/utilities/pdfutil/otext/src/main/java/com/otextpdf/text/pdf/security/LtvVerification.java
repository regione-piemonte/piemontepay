/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.security;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.Logger;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.LoggerFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfStamper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LtvVerification {

	private final Logger LOGGER = LoggerFactory.getLogger ( LtvVerification.class );

	private final Map<PdfName, ValidationData> validated = new HashMap<> ();

	private final boolean used = false;

	public LtvVerification ( PdfStamper stp ) {
		stp.getWriter ();
		stp.getReader ();
		stp.getAcroFields ();
	}

	public enum Level {

		OCSP,

		CRL,

		OCSP_CRL,

		OCSP_OPTIONAL_CRL
	}


	private static class ValidationData {

		public List<byte[]> crls = new ArrayList<> ();

		public List<byte[]> ocsps = new ArrayList<> ();

		public List<byte[]> certs = new ArrayList<> ();
	}
}

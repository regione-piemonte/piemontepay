/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.PdfEncryptionSettings;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.PdfViewerPreferences;

import java.io.OutputStream;
import java.security.cert.Certificate;


@Deprecated
public class PdfCopyFields
				implements PdfViewerPreferences, PdfEncryptionSettings {

	private final PdfCopyFieldsImp fc;

	public PdfCopyFields ( OutputStream os, char pdfVersion ) throws DocumentException {
		fc = new PdfCopyFieldsImp ( os, pdfVersion );
	}

	public void close () {
		fc.close ();
	}

	public void open () {
		fc.openDoc ();
	}

	public PdfWriter getWriter () {
		return fc;
	}

	public void setEncryption ( byte[] userPassword, byte[] ownerPassword, int permissions, int encryptionType ) throws DocumentException {
		fc.setEncryption ( userPassword, ownerPassword, permissions, encryptionType );
	}

	public void addViewerPreference ( PdfName key, PdfObject value ) {
		fc.addViewerPreference ( key, value );
	}

	public void setViewerPreferences ( int preferences ) {
		fc.setViewerPreferences ( preferences );
	}

	public void setEncryption ( Certificate[] certs, int[] permissions, int encryptionType ) throws DocumentException {
		fc.setEncryption ( certs, permissions, encryptionType );
	}
}

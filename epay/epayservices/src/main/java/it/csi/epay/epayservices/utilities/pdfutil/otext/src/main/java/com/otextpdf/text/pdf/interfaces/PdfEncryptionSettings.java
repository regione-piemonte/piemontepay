/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;

import java.security.cert.Certificate;


public interface PdfEncryptionSettings {

	void setEncryption ( byte[] userPassword, byte[] ownerPassword, int permissions, int encryptionType ) throws DocumentException;

	void setEncryption ( Certificate[] certs, int[] permissions, int encryptionType ) throws DocumentException;
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.PdfEncryptionSettings;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.PdfViewerPreferences;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.security.LtvVerification;

import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.Certificate;
import java.util.Map;


public class PdfStamper
				implements PdfViewerPreferences, PdfEncryptionSettings {

	protected PdfStamperImp stamper;

	protected boolean hasSignature;
	protected XmlSignatureAppearance sigXmlApp;

	private Map<String, String> moreInfo;

	private LtvVerification verification;

	public PdfStamper ( final PdfReader reader, final OutputStream os ) throws DocumentException, IOException {
		stamper = new PdfStamperImp ( reader, os, '\0', false );
	}

	public PdfStamper ( final PdfReader reader, final OutputStream os, final char pdfVersion ) throws DocumentException, IOException {
		stamper = new PdfStamperImp ( reader, os, pdfVersion, false );
	}

	public PdfStamper ( final PdfReader reader, final OutputStream os, final char pdfVersion, final boolean append ) throws DocumentException, IOException {
		stamper = new PdfStamperImp ( reader, os, pdfVersion, append );
	}

	protected PdfStamper () {

	}

	public Map<String, String> getMoreInfo () {
		return this.moreInfo;
	}

	public void setEncryption ( final byte[] userPassword, final byte[] ownerPassword, final int permissions, final int encryptionType )
					throws DocumentException {
		if ( stamper.isAppend () )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "append.mode.does.not.support.changing.the.encryption.status" ) );
		if ( stamper.isContentWritten () )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "content.was.already.written.to.the.output" ) );
		stamper.setEncryption ( userPassword, ownerPassword, permissions, encryptionType );
	}

	public void setEncryption ( final Certificate[] certs, final int[] permissions, final int encryptionType ) throws DocumentException {
		if ( stamper.isAppend () )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "append.mode.does.not.support.changing.the.encryption.status" ) );
		if ( stamper.isContentWritten () )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "content.was.already.written.to.the.output" ) );
		stamper.setEncryption ( certs, permissions, encryptionType );
	}

	public PdfWriter getWriter () {
		return stamper;
	}

	public PdfReader getReader () {
		return stamper.reader;
	}

	public void getAcroFields () {
		stamper.getAcroFields ();
	}

	public void addAnnotation ( final PdfAnnotation annot, final int page ) {
		stamper.addAnnotation ( annot, page );
	}

	public void setViewerPreferences ( final int preferences ) {
		stamper.setViewerPreferences ( preferences );
	}

	public void addViewerPreference ( final PdfName key, final PdfObject value ) {
		stamper.addViewerPreference ( key, value );
	}

}

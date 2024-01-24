/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal.PdfIsoKeys;

import java.io.IOException;
import java.io.OutputStream;


public class PdfString extends PdfObject {

	protected String value = NOTHING;

	protected String originalValue = null;

	protected String encoding = TEXT_PDFDOCENCODING;

	protected int objNum = 0;

	protected int objGen = 0;

	protected boolean hexWriting = false;

	public PdfString () {
		super ( STRING );
	}

	public PdfString ( String value ) {
		super ( STRING );
		this.value = value;
	}

	public PdfString ( String value, String encoding ) {
		super ( STRING );
		this.value = value;
		this.encoding = encoding;
	}

	public PdfString ( byte[] bytes ) {
		super ( STRING );
		value = PdfEncodings.convertToString ( bytes, null );
		encoding = NOTHING;
	}

	public void toPdf ( PdfWriter writer, OutputStream os ) throws IOException {
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_OBJECT, this );
		byte[] b = getBytes ();
		PdfEncryption crypto = null;
		if ( writer != null )
			crypto = writer.getEncryption ();
		if ( crypto != null && !crypto.isEmbeddedFilesOnly () )
			b = crypto.encryptByteArray ( b );
		if ( hexWriting ) {
			ByteBuffer buf = new ByteBuffer ();
			buf.append ( '<' );
			for ( byte item : b )
				buf.appendHex ( item );
			buf.append ( '>' );
			os.write ( buf.toByteArray () );
		} else
			os.write ( PdfContentByte.escapeString ( b ) );
	}

	public String toString () {
		return value;
	}

	public byte[] getBytes () {
		if ( bytes == null ) {
			if ( encoding != null && encoding.equals ( TEXT_UNICODE ) && PdfEncodings.isPdfDocEncoding ( value ) )
				bytes = PdfEncodings.convertToBytes ( value, TEXT_PDFDOCENCODING );
			else
				bytes = PdfEncodings.convertToBytes ( value, encoding );
		}
		return bytes;
	}

	public String toUnicodeString () {
		if ( encoding != null && !encoding.isEmpty () )
			return value;
		getBytes ();
		if ( bytes.length >= 2 && bytes[0] == (byte) 254 && bytes[1] == (byte) 255 )
			return PdfEncodings.convertToString ( bytes, PdfObject.TEXT_UNICODE );
		else
			return PdfEncodings.convertToString ( bytes, PdfObject.TEXT_PDFDOCENCODING );
	}

	public String getEncoding () {
		return encoding;
	}

	void setObjNum ( int objNum, int objGen ) {
		this.objNum = objNum;
		this.objGen = objGen;
	}

	void decrypt ( PdfReader reader ) {
		PdfEncryption decrypt = reader.getDecrypt ();
		if ( decrypt != null ) {
			originalValue = value;
			decrypt.setHashKey ( objNum, objGen );
			bytes = PdfEncodings.convertToBytes ( value, null );
			bytes = decrypt.decryptByteArray ( bytes );
			value = PdfEncodings.convertToString ( bytes, null );
		}
	}

	public boolean isHexWriting () {
		return hexWriting;
	}

	public PdfString setHexWriting ( boolean hexWriting ) {
		this.hexWriting = hexWriting;
		return this;
	}
}

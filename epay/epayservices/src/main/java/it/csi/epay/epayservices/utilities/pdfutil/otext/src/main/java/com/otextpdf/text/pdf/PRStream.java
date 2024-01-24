/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Document;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;


public class PRStream extends PdfStream {

	protected PdfReader reader;

	protected long offset;

	protected int length;

	protected int objNum = 0;

	protected int objGen = 0;

	public PRStream ( PRStream stream, PdfDictionary newDic ) {
		reader = stream.reader;
		offset = stream.offset;
		length = stream.length;
		compressed = stream.compressed;
		compressionLevel = stream.compressionLevel;
		streamBytes = stream.streamBytes;
		bytes = stream.bytes;
		objNum = stream.objNum;
		objGen = stream.objGen;
		if ( newDic != null )
			putAll ( newDic );
		else
			hashMap.putAll ( stream.hashMap );
	}

	public PRStream ( PRStream stream, PdfDictionary newDic, PdfReader reader ) {
		this ( stream, newDic );
		this.reader = reader;
	}

	public PRStream ( PdfReader reader, long offset ) {
		this.reader = reader;
		this.offset = offset;
	}

	public PRStream ( PdfReader reader, byte[] conts ) {
		this ( reader, conts, DEFAULT_COMPRESSION );
	}

	public PRStream ( PdfReader reader, byte[] conts, int compressionLevel ) {
		this.reader = reader;
		this.offset = -1;
		if ( Document.compress ) {
			try {
				ByteArrayOutputStream stream = new ByteArrayOutputStream ();
				Deflater deflater = new Deflater ( compressionLevel );
				DeflaterOutputStream zip = new DeflaterOutputStream ( stream, deflater );
				zip.write ( conts );
				zip.close ();
				deflater.end ();
				bytes = stream.toByteArray ();
			} catch ( IOException ioe ) {
				throw new ExceptionConverter ( ioe );
			}
			put ( PdfName.FILTER, PdfName.FLATEDECODE );
		} else
			bytes = conts;
		setLength ( bytes.length );
	}

	public void setData ( byte[] data, boolean compress ) {
		setData ( data, compress, DEFAULT_COMPRESSION );
	}

	public void setData ( byte[] data, boolean compress, int compressionLevel ) {
		remove ( PdfName.FILTER );
		this.offset = -1;
		if ( Document.compress && compress ) {
			try {
				ByteArrayOutputStream stream = new ByteArrayOutputStream ();
				Deflater deflater = new Deflater ( compressionLevel );
				DeflaterOutputStream zip = new DeflaterOutputStream ( stream, deflater );
				zip.write ( data );
				zip.close ();
				deflater.end ();
				bytes = stream.toByteArray ();
				this.compressionLevel = compressionLevel;
			} catch ( IOException ioe ) {
				throw new ExceptionConverter ( ioe );
			}
			put ( PdfName.FILTER, PdfName.FLATEDECODE );
		} else
			bytes = data;
		setLength ( bytes.length );
	}

	public void setData ( byte[] data ) {
		setData ( data, true );
	}

	public long getOffset () {
		return offset;
	}

	public int getLength () {
		return length;
	}

	public void setLength ( int length ) {
		this.length = length;
		put ( PdfName.LENGTH, new PdfNumber ( length ) );
	}

	public PdfReader getReader () {
		return reader;
	}

	public void setObjNum ( int objNum, int objGen ) {
		this.objNum = objNum;
		this.objGen = objGen;
	}

	int getObjNum () {
		return objNum;
	}

	int getObjGen () {
		return objGen;
	}

	public void toPdf ( PdfWriter writer, OutputStream os ) throws IOException {
		byte[] b = PdfReader.getStreamBytesRaw ( this );
		PdfEncryption crypto = null;
		if ( writer != null )
			crypto = writer.getEncryption ();
		PdfObject objLen = get ( PdfName.LENGTH );
		int nn = b.length;
		if ( crypto != null )
			nn = crypto.calculateStreamSize ( nn );
		put ( PdfName.LENGTH, new PdfNumber ( nn ) );
		superToPdf ( writer, os );
		put ( PdfName.LENGTH, objLen );
		os.write ( STARTSTREAM );
		if ( length > 0 ) {
			if ( crypto != null && !crypto.isEmbeddedFilesOnly () )
				b = crypto.encryptByteArray ( b );
			os.write ( b );
		}
		os.write ( ENDSTREAM );
	}
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io.StreamUtil;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal.PdfIsoKeys;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;


public class PdfFileSpecification extends PdfDictionary {

	protected PdfWriter writer;

	protected PdfIndirectReference ref;

	public PdfFileSpecification () {
		super ( PdfName.FILESPEC );
	}

	public static PdfFileSpecification url ( PdfWriter writer, String url ) {
		PdfFileSpecification fs = new PdfFileSpecification ();
		fs.writer = writer;
		fs.put ( PdfName.FS, PdfName.URL );
		fs.put ( PdfName.F, new PdfString ( url ) );
		return fs;
	}

	public static PdfFileSpecification fileEmbedded ( PdfWriter writer, String filePath, String fileDisplay, byte[] fileStore ) throws IOException {
		return fileEmbedded ( writer, filePath, fileDisplay, fileStore, PdfStream.BEST_COMPRESSION );
	}

	public static PdfFileSpecification fileEmbedded ( PdfWriter writer, String filePath, String fileDisplay, byte[] fileStore, int compressionLevel )
					throws IOException {
		return fileEmbedded ( writer, filePath, fileDisplay, fileStore, null, null, compressionLevel );
	}

	public static PdfFileSpecification fileEmbedded ( PdfWriter writer, String filePath, String fileDisplay, byte[] fileStore, String mimeType,
					PdfDictionary fileParameter, int compressionLevel ) throws IOException {
		PdfFileSpecification fs = new PdfFileSpecification ();
		fs.writer = writer;
		fs.put ( PdfName.F, new PdfString ( fileDisplay ) );
		fs.setUnicodeFileName ( fileDisplay, false );
		PdfEFStream stream;
		InputStream in = null;
		PdfIndirectReference ref;
		PdfIndirectReference refFileLength = null;
		try {
			if ( fileStore == null ) {
				refFileLength = writer.getPdfIndirectReference ();
				File file = new File ( filePath );
				if ( file.canRead () ) {
					in = Files.newInputStream ( Paths.get ( filePath ) );
				} else {
					if ( filePath.startsWith ( "file:/" ) || filePath.startsWith ( "http://" ) || filePath.startsWith ( "https://" ) || filePath.startsWith (
									"jar:" ) ) {
						in = new URL ( filePath ).openStream ();
					} else {
						in = StreamUtil.getResourceStream ( filePath );
						if ( in == null )
							throw new IOException ( MessageLocalization.getComposedMessage ( "1.not.found.as.file.or.resource", filePath ) );
					}
				}
				stream = new PdfEFStream ( in, writer );
			} else {
				stream = new PdfEFStream ( fileStore );
			}
			stream.put ( PdfName.TYPE, PdfName.EMBEDDEDFILE );
			stream.flateCompress ( compressionLevel );
			PdfDictionary param = new PdfDictionary ();
			if ( fileParameter != null ) {
				param.merge ( fileParameter );
			}
			if ( !param.contains ( PdfName.MODDATE ) ) {
				param.put ( PdfName.MODDATE, new PdfDate () );
			}
			if ( fileStore == null ) {
				stream.put ( PdfName.PARAMS, refFileLength );
			} else {
				param.put ( PdfName.SIZE, new PdfNumber ( stream.getRawLength () ) );
				stream.put ( PdfName.PARAMS, param );
			}

			if ( mimeType != null )
				stream.put ( PdfName.SUBTYPE, new PdfName ( mimeType ) );

			ref = writer.addToBody ( stream ).getIndirectReference ();
			if ( fileStore == null ) {
				stream.writeLength ();
				param.put ( PdfName.SIZE, new PdfNumber ( stream.getRawLength () ) );
				writer.addToBody ( param, refFileLength );
			}
		} finally {
			if ( in != null )
				try {
					in.close ();
				} catch ( Exception ignored ) {
				}
		}
		PdfDictionary f = new PdfDictionary ();
		f.put ( PdfName.F, ref );
		f.put ( PdfName.UF, ref );
		fs.put ( PdfName.EF, f );
		return fs;
	}

	public static PdfFileSpecification fileExtern ( PdfWriter writer, String filePath ) {
		PdfFileSpecification fs = new PdfFileSpecification ();
		fs.writer = writer;
		fs.put ( PdfName.F, new PdfString ( filePath ) );
		fs.setUnicodeFileName ( filePath, false );
		return fs;
	}

	public PdfIndirectReference getReference () throws IOException {
		if ( ref != null )
			return ref;
		ref = writer.addToBody ( this ).getIndirectReference ();
		return ref;
	}

	public void setUnicodeFileName ( String filename, boolean unicode ) {
		put ( PdfName.UF, new PdfString ( filename, unicode ? PdfObject.TEXT_UNICODE : PdfObject.TEXT_PDFDOCENCODING ) );
	}

	public void setVolatile ( boolean volatile_file ) {
		put ( PdfName.V, new PdfBoolean ( volatile_file ) );
	}

	@Override
	public void toPdf ( final PdfWriter writer, final OutputStream os ) throws IOException {
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_FILESPEC, this );
		super.toPdf ( writer, os );
	}

}

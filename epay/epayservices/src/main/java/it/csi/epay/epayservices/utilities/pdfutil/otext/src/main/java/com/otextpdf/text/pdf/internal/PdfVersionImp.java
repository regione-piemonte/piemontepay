/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocWriter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.OutputStreamCounter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfDeveloperExtension;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfDictionary;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfWriter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.PdfVersion;

import java.io.IOException;


public class PdfVersionImp implements PdfVersion {

	public static final byte[][] HEADER = {
					DocWriter.getISOBytes ( "\n" ),
					DocWriter.getISOBytes ( "%PDF-" ),
					DocWriter.getISOBytes ( "\n%\u00e2\u00e3\u00cf\u00d3\n" )
	};

	protected boolean headerWasWritten = false;

	protected boolean appendmode = false;

	protected char header_version = PdfWriter.VERSION_1_4;

	protected PdfName catalog_version = null;

	protected char version = PdfWriter.VERSION_1_4;

	protected PdfDictionary extensions = null;

	public void setPdfVersion ( char version ) {
		this.version = version;
		if ( headerWasWritten || appendmode ) {
			setPdfVersion ( getVersionAsName ( version ) );
		} else {
			this.header_version = version;
		}
	}

	public void setAtLeastPdfVersion ( char version ) {
		if ( version > header_version ) {
			setPdfVersion ( version );
		}
	}

	public void setPdfVersion ( PdfName version ) {
		if ( catalog_version == null || catalog_version.compareTo ( version ) < 0 ) {
			this.catalog_version = version;
		}
	}

	public void setAppendmode ( boolean appendmode ) {
		this.appendmode = appendmode;
	}

	public void writeHeader ( OutputStreamCounter os ) throws IOException {
		if ( appendmode ) {
			os.write ( HEADER[0] );
		} else {
			os.write ( HEADER[1] );
			os.write ( getVersionAsByteArray ( header_version ) );
			os.write ( HEADER[2] );
			headerWasWritten = true;
		}
	}

	public PdfName getVersionAsName ( char version ) {
		switch ( version ) {
		case PdfWriter.VERSION_1_2:
			return PdfWriter.PDF_VERSION_1_2;
		case PdfWriter.VERSION_1_3:
			return PdfWriter.PDF_VERSION_1_3;
		case PdfWriter.VERSION_1_5:
			return PdfWriter.PDF_VERSION_1_5;
		case PdfWriter.VERSION_1_6:
			return PdfWriter.PDF_VERSION_1_6;
		case PdfWriter.VERSION_1_7:
			return PdfWriter.PDF_VERSION_1_7;
		case PdfWriter.VERSION_1_4:
		default:
			return PdfWriter.PDF_VERSION_1_4;
		}
	}

	public byte[] getVersionAsByteArray ( char version ) {
		return DocWriter.getISOBytes ( getVersionAsName ( version ).toString ().substring ( 1 ) );
	}

	public void addToCatalog ( PdfDictionary catalog ) {
		if ( catalog_version != null ) {
			catalog.put ( PdfName.VERSION, catalog_version );
		}
		if ( extensions != null ) {
			catalog.put ( PdfName.EXTENSIONS, extensions );
		}
	}

	public void addDeveloperExtension ( PdfDeveloperExtension de ) {
		if ( extensions == null ) {
			extensions = new PdfDictionary ();
		} else {
			PdfDictionary extension = extensions.getAsDict ( de.getPrefix () );
			if ( extension != null ) {
				int diff = de.getBaseversion ().compareTo ( extension.getAsName ( PdfName.BASEVERSION ) );
				if ( diff < 0 )
					return;
				diff = de.getExtensionLevel () - extension.getAsNumber ( PdfName.EXTENSIONLEVEL ).intValue ();
				if ( diff <= 0 )
					return;
			}
		}
		extensions.put ( de.getPrefix (), de.getDeveloperExtensions () );
	}

	public char getVersion () {
		return version;
	}

}

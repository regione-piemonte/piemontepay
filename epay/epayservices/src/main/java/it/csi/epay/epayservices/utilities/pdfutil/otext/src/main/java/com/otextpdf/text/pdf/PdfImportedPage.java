/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;

import java.io.IOException;


public class PdfImportedPage extends PdfTemplate {

	protected boolean toCopy = true;

	PdfReaderInstance readerInstance;

	int pageNumber;

	int rotation;

	PdfImportedPage ( PdfReaderInstance readerInstance, PdfWriter writer, int pageNumber ) {
		this.readerInstance = readerInstance;
		this.pageNumber = pageNumber;
		this.writer = writer;
		rotation = readerInstance.getReader ().getPageRotation ( pageNumber );
		bBox = readerInstance.getReader ().getPageSize ( pageNumber );
		setMatrix ( 1, 0, 0, 1, -bBox.getLeft (), -bBox.getBottom () );
		type = TYPE_IMPORTED;
	}

	public int getRotation () {
		return rotation;
	}

	public void addImage ( Image image, float a, float b, float c, float d, float e, float f ) {
		throwError ();
	}

	public void addTemplate ( PdfTemplate template, float a, float b, float c, float d, float e, float f ) {
		throwError ();
	}

	public PdfContentByte getDuplicate () {
		throwError ();
		return null;
	}

	public PdfStream getFormXObject ( int compressionLevel ) throws IOException {
		return readerInstance.getFormXObject ( pageNumber, compressionLevel );
	}

	public void setColorFill ( PdfSpotColor sp, float tint ) {
		throwError ();
	}

	public void setColorStroke ( PdfSpotColor sp, float tint ) {
		throwError ();
	}

	PdfObject getResources () {
		return readerInstance.getResources ( pageNumber );
	}

	public void setFontAndSize ( BaseFont bf, float size ) {
		throwError ();
	}

	public void setGroup ( PdfTransparencyGroup group ) {
		throwError ();
	}

	void throwError () {
		throw new RuntimeException ( MessageLocalization.getComposedMessage ( "content.can.not.be.added.to.a.pdfimportedpage" ) );
	}

	PdfReaderInstance getPdfReaderInstance () {
		return readerInstance;
	}

	public boolean isToCopy () {
		return toCopy;
	}

	public void setCopied () {
		toCopy = false;
	}
}

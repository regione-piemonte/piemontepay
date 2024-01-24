/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Rectangle;


public abstract class Barcode {

	public static final int EAN13 = 1;

	public static final int EAN8 = 2;

	public static final int UPCA = 3;

	public static final int UPCE = 4;

	public static final int SUPP2 = 5;

	public static final int SUPP5 = 6;

	public static final int POSTNET = 7;

	public static final int PLANET = 8;

	public static final int CODE128 = 9;

	public static final int CODE128_UCC = 10;

	public static final int CODE128_RAW = 11;

	public static final int CODABAR = 12;

	protected float x;

	protected float n;

	protected BaseFont font;

	protected float size;

	protected float baseline;

	protected float barHeight;

	protected int textAlignment;

	protected boolean generateChecksum;

	protected boolean checksumText;

	protected boolean startStopText;

	protected boolean extended;

	protected String code = "";

	protected boolean guardBars;

	protected int codeType;

	protected float inkSpreading = 0;

	protected String altText;

	public float getX () {
		return x;
	}

	public void setX ( float x ) {
		this.x = x;
	}

	public float getN () {
		return n;
	}

	public void setN ( float n ) {
		this.n = n;
	}

	public BaseFont getFont () {
		return font;
	}

	public void setFont ( BaseFont font ) {
		this.font = font;
	}

	public float getSize () {
		return size;
	}

	public void setSize ( float size ) {
		this.size = size;
	}

	public float getBaseline () {
		return baseline;
	}

	public void setBaseline ( float baseline ) {
		this.baseline = baseline;
	}

	public void setBarHeight ( float barHeight ) {
		this.barHeight = barHeight;
	}

	public void setChecksumText ( boolean checksumText ) {
		this.checksumText = checksumText;
	}

	public void setStartStopText ( boolean startStopText ) {
		this.startStopText = startStopText;
	}

	public boolean isExtended () {
		return extended;
	}

	public void setExtended ( boolean extended ) {
		this.extended = extended;
	}

	public String getCode () {
		return code;
	}

	public void setCode ( String code ) {
		this.code = code;
	}

	public int getCodeType () {
		return codeType;
	}

	public void setCodeType ( int codeType ) {
		this.codeType = codeType;
	}

	public abstract Rectangle getBarcodeSize ();

	public abstract Rectangle placeBarcode ( PdfContentByte cb, BaseColor barColor, BaseColor textColor );

	public PdfTemplate createTemplateWithBarcode ( PdfContentByte cb, BaseColor barColor, BaseColor textColor ) {
		PdfTemplate tp = cb.createTemplate ( 0, 0 );
		Rectangle rect = placeBarcode ( tp, barColor, textColor );
		tp.setBoundingBox ( rect );
		return tp;
	}

	public Image createImageWithBarcode ( PdfContentByte cb, BaseColor barColor, BaseColor textColor ) {
		try {
			return Image.getInstance ( createTemplateWithBarcode ( cb, barColor, textColor ) );
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
	}

}

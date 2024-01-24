/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.BaseFont;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.ExtendedColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PatternColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfArray;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfDictionary;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfGState;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfImage;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfNumber;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfWriter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfXConformanceException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.ShadingColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.SpotColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.PdfXConformance;


public class PdfXConformanceImp implements PdfXConformance {

	protected int pdfxConformance = PdfWriter.PDFXNONE;

	protected PdfWriter writer;

	public PdfXConformanceImp ( PdfWriter writer ) {
		this.writer = writer;
	}

	public int getPDFXConformance () {
		return pdfxConformance;
	}

	public boolean isPdfIso () {
		return isPdfX ();
	}

	public boolean isPdfX () {
		return pdfxConformance != PdfWriter.PDFXNONE;
	}

	public boolean isPdfX1A2001 () {
		return pdfxConformance == PdfWriter.PDFX1A2001;
	}

	public boolean isPdfX32002 () {
		return pdfxConformance == PdfWriter.PDFX32002;
	}

	public void checkPdfIsoConformance ( int key, Object obj1 ) {
		if ( writer == null || !writer.isPdfX () )
			return;
		int conf = writer.getPDFXConformance ();
		switch ( key ) {
		case PdfIsoKeys.PDFISOKEY_COLOR:
			if ( conf == PdfWriter.PDFX1A2001 ) {
				if ( obj1 instanceof ExtendedColor ) {
					ExtendedColor ec = (ExtendedColor) obj1;
					switch ( ec.getType () ) {
					case ExtendedColor.TYPE_CMYK:
					case ExtendedColor.TYPE_GRAY:
						return;
					case ExtendedColor.TYPE_RGB:
						throw new PdfXConformanceException ( MessageLocalization.getComposedMessage ( "colorspace.rgb.is.not.allowed" ) );
					case ExtendedColor.TYPE_SEPARATION:
						SpotColor sc = (SpotColor) ec;
						checkPdfIsoConformance ( PdfIsoKeys.PDFISOKEY_COLOR, sc.getPdfSpotColor ().getAlternativeCS () );
						break;
					case ExtendedColor.TYPE_SHADING:
						ShadingColor xc = (ShadingColor) ec;
						checkPdfIsoConformance ( PdfIsoKeys.PDFISOKEY_COLOR, xc.getPdfShadingPattern ().getShading ().getColorSpace () );
						break;
					case ExtendedColor.TYPE_PATTERN:
						PatternColor pc = (PatternColor) ec;
						checkPdfIsoConformance ( PdfIsoKeys.PDFISOKEY_COLOR, pc.getPainter ().getDefaultColor () );
						break;
					}
				} else if ( obj1 instanceof BaseColor )
					throw new PdfXConformanceException ( MessageLocalization.getComposedMessage ( "colorspace.rgb.is.not.allowed" ) );
			}
			break;
		case PdfIsoKeys.PDFISOKEY_CMYK:
			break;
		case PdfIsoKeys.PDFISOKEY_RGB:
			if ( conf == PdfWriter.PDFX1A2001 )
				throw new PdfXConformanceException ( MessageLocalization.getComposedMessage ( "colorspace.rgb.is.not.allowed" ) );
			break;
		case PdfIsoKeys.PDFISOKEY_FONT:
			if ( !( (BaseFont) obj1 ).isEmbedded () )
				throw new PdfXConformanceException ( MessageLocalization.getComposedMessage ( "all.the.fonts.must.be.embedded.this.one.isn.t.1",
								( (BaseFont) obj1 ).getPostscriptFontName () ) );
			break;
		case PdfIsoKeys.PDFISOKEY_IMAGE:
			PdfImage image = (PdfImage) obj1;
			if ( image.get ( PdfName.SMASK ) != null )
				throw new PdfXConformanceException ( MessageLocalization.getComposedMessage ( "the.smask.key.is.not.allowed.in.images" ) );
			if ( conf == PdfWriter.PDFX1A2001 ) {
				PdfObject cs = image.get ( PdfName.COLORSPACE );
				if ( cs == null )
					return;
				if ( cs.isName () ) {
					if ( PdfName.DEVICERGB.equals ( cs ) )
						throw new PdfXConformanceException ( MessageLocalization.getComposedMessage ( "colorspace.rgb.is.not.allowed" ) );
				} else if ( cs.isArray () ) {
					if ( PdfName.CALRGB.equals ( ( (PdfArray) cs ).getPdfObject ( 0 ) ) )
						throw new PdfXConformanceException ( MessageLocalization.getComposedMessage ( "colorspace.calrgb.is.not.allowed" ) );
				}
			}
			break;
		case PdfIsoKeys.PDFISOKEY_GSTATE:
			PdfDictionary gs = (PdfDictionary) obj1;
			if ( gs == null )
				break;
			PdfObject obj = gs.get ( PdfName.BM );
			if ( obj != null && !PdfGState.BM_NORMAL.equals ( obj ) && !PdfGState.BM_COMPATIBLE.equals ( obj ) )
				throw new PdfXConformanceException ( MessageLocalization.getComposedMessage ( "blend.mode.1.not.allowed", obj.toString () ) );
			obj = gs.get ( PdfName.CA );
			double v;
			if ( obj != null && ( v = ( (PdfNumber) obj ).doubleValue () ) != 1.0 )
				throw new PdfXConformanceException ( MessageLocalization.getComposedMessage ( "transparency.is.not.allowed.ca.eq.1", String.valueOf ( v ) ) );
			obj = gs.get ( PdfName.ca );
			if ( obj != null && ( v = ( (PdfNumber) obj ).doubleValue () ) != 1.0 )
				throw new PdfXConformanceException ( MessageLocalization.getComposedMessage ( "transparency.is.not.allowed.ca.eq.1", String.valueOf ( v ) ) );
			break;
		case PdfIsoKeys.PDFISOKEY_LAYER:
			throw new PdfXConformanceException ( MessageLocalization.getComposedMessage ( "layers.are.not.allowed" ) );
		}
	}
}

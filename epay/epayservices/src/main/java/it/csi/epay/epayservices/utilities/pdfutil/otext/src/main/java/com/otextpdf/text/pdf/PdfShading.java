/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;

import java.io.IOException;


public class PdfShading {

	protected PdfDictionary shading;

	protected PdfWriter writer;

	protected int shadingType;

	protected ColorDetails colorDetails;

	protected PdfName shadingName;

	protected PdfIndirectReference shadingReference;

	protected float[] bBox;

	protected boolean antiAlias = false;

	private BaseColor cspace;

	protected PdfShading ( PdfWriter writer ) {
		this.writer = writer;
	}

	public static void throwColorSpaceError () {
		throw new IllegalArgumentException (
						MessageLocalization.getComposedMessage ( "a.tiling.or.shading.pattern.cannot.be.used.as.a.color.space.in.a.shading.pattern" ) );
	}

	public static void checkCompatibleColors ( BaseColor c1, BaseColor c2 ) {
		int type1 = ExtendedColor.getType ( c1 );
		int type2 = ExtendedColor.getType ( c2 );
		if ( type1 != type2 )
			throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "both.colors.must.be.of.the.same.type" ) );
		if ( type1 == ExtendedColor.TYPE_SEPARATION && ( (SpotColor) c1 ).getPdfSpotColor () != ( (SpotColor) c2 ).getPdfSpotColor () )
			throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "the.spot.color.must.be.the.same.only.the.tint.can.vary" ) );
		if ( type1 == ExtendedColor.TYPE_PATTERN || type1 == ExtendedColor.TYPE_SHADING )
			throwColorSpaceError ();
	}

	public static float[] getColorArray ( BaseColor color ) {
		int type = ExtendedColor.getType ( color );
		switch ( type ) {
		case ExtendedColor.TYPE_GRAY: {
			return new float[] { ( (GrayColor) color ).getGray () };
		}
		case ExtendedColor.TYPE_CMYK: {
			CMYKColor cmyk = (CMYKColor) color;
			return new float[] { cmyk.getCyan (), cmyk.getMagenta (), cmyk.getYellow (), cmyk.getBlack () };
		}
		case ExtendedColor.TYPE_SEPARATION: {
			return new float[] { ( (SpotColor) color ).getTint () };
		}
		case ExtendedColor.TYPE_RGB: {
			return new float[] { color.getRed () / 255f, color.getGreen () / 255f, color.getBlue () / 255f };
		}
		}
		throwColorSpaceError ();
		return null;
	}

	public static PdfShading type1 ( PdfWriter writer, BaseColor colorSpace, float[] domain, float[] tMatrix, PdfFunction function ) {
		PdfShading sp = new PdfShading ( writer );
		sp.shading = new PdfDictionary ();
		sp.shadingType = 1;
		sp.shading.put ( PdfName.SHADINGTYPE, new PdfNumber ( sp.shadingType ) );
		sp.setColorSpace ( colorSpace );
		if ( domain != null )
			sp.shading.put ( PdfName.DOMAIN, new PdfArray ( domain ) );
		if ( tMatrix != null )
			sp.shading.put ( PdfName.MATRIX, new PdfArray ( tMatrix ) );
		sp.shading.put ( PdfName.FUNCTION, function.getReference () );
		return sp;
	}

	public static PdfShading type2 ( PdfWriter writer, BaseColor colorSpace, float[] coords, float[] domain, PdfFunction function, boolean[] extend ) {
		PdfShading sp = new PdfShading ( writer );
		sp.shading = new PdfDictionary ();
		sp.shadingType = 2;
		sp.shading.put ( PdfName.SHADINGTYPE, new PdfNumber ( sp.shadingType ) );
		sp.setColorSpace ( colorSpace );
		sp.shading.put ( PdfName.COORDS, new PdfArray ( coords ) );
		if ( domain != null )
			sp.shading.put ( PdfName.DOMAIN, new PdfArray ( domain ) );
		sp.shading.put ( PdfName.FUNCTION, function.getReference () );
		if ( extend != null && ( extend[0] || extend[1] ) ) {
			PdfArray array = new PdfArray ( extend[0] ? PdfBoolean.PDFTRUE : PdfBoolean.PDFFALSE );
			array.add ( extend[1] ? PdfBoolean.PDFTRUE : PdfBoolean.PDFFALSE );
			sp.shading.put ( PdfName.EXTEND, array );
		}
		return sp;
	}

	public static PdfShading simpleAxial ( PdfWriter writer, float x0, float y0, float x1, float y1, BaseColor startColor, BaseColor endColor,
					boolean extendStart, boolean extendEnd ) {
		checkCompatibleColors ( startColor, endColor );
		PdfFunction function = PdfFunction.type2 ( writer, new float[] { 0, 1 }, null, getColorArray ( startColor ),
						getColorArray ( endColor ), 1 );
		return type2 ( writer, startColor, new float[] { x0, y0, x1, y1 }, null, function, new boolean[] { extendStart, extendEnd } );
	}

	public static PdfShading simpleAxial ( PdfWriter writer, float x0, float y0, float x1, float y1, BaseColor startColor, BaseColor endColor ) {
		return simpleAxial ( writer, x0, y0, x1, y1, startColor, endColor, true, true );
	}

	public BaseColor getColorSpace () {
		return cspace;
	}

	protected void setColorSpace ( BaseColor color ) {
		cspace = color;
		int type = ExtendedColor.getType ( color );
		PdfObject colorSpace;
		switch ( type ) {
		case ExtendedColor.TYPE_GRAY: {
			colorSpace = PdfName.DEVICEGRAY;
			break;
		}
		case ExtendedColor.TYPE_CMYK: {
			colorSpace = PdfName.DEVICECMYK;
			break;
		}
		case ExtendedColor.TYPE_SEPARATION: {
			SpotColor spot = (SpotColor) color;
			colorDetails = writer.addSimple ( spot.getPdfSpotColor () );
			colorSpace = colorDetails.getIndirectReference ();
			break;
		}
		case ExtendedColor.TYPE_PATTERN:
		case ExtendedColor.TYPE_SHADING: {
			throwColorSpaceError ();
		}
		default:
			colorSpace = PdfName.DEVICERGB;
			break;
		}
		shading.put ( PdfName.COLORSPACE, colorSpace );
	}

	PdfIndirectReference getShadingReference () {
		if ( shadingReference == null )
			shadingReference = writer.getPdfIndirectReference ();
		return shadingReference;
	}

	void setName ( int number ) {
		shadingName = new PdfName ( "Sh" + number );
	}

	public void addToBody () throws IOException {
		if ( bBox != null )
			shading.put ( PdfName.BBOX, new PdfArray ( bBox ) );
		if ( antiAlias )
			shading.put ( PdfName.ANTIALIAS, PdfBoolean.PDFTRUE );
		writer.addToBody ( shading, getShadingReference () );
	}

	PdfWriter getWriter () {
		return writer;
	}

	ColorDetails getColorDetails () {
		return colorDetails;
	}

}

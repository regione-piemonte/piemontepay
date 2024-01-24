/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;


public class PdfSpotColor {

	public PdfName name;

	public BaseColor altcs;

	public PdfSpotColor ( String name, BaseColor altcs ) {
		this.name = new PdfName ( name );
		this.altcs = altcs;
	}

	public BaseColor getAlternativeCS () {
		return altcs;
	}

	protected PdfObject getSpotObject ( PdfWriter writer ) {
		PdfArray array = new PdfArray ( PdfName.SEPARATION );
		array.add ( name );
		PdfFunction func;
		if ( altcs instanceof ExtendedColor ) {
			int type = ( (ExtendedColor) altcs ).type;
			switch ( type ) {
			case ExtendedColor.TYPE_GRAY:
				array.add ( PdfName.DEVICEGRAY );
				func = PdfFunction.type2 ( writer, new float[] { 0, 1 }, null, new float[] { 0 }, new float[] { ( (GrayColor) altcs ).getGray () }, 1 );
				break;
			case ExtendedColor.TYPE_CMYK:
				array.add ( PdfName.DEVICECMYK );
				CMYKColor cmyk = (CMYKColor) altcs;
				func = PdfFunction.type2 ( writer, new float[] { 0, 1 }, null, new float[] { 0, 0, 0, 0 },
								new float[] { cmyk.getCyan (), cmyk.getMagenta (), cmyk.getYellow (), cmyk.getBlack () }, 1 );
				break;
			default:
				throw new RuntimeException ( MessageLocalization.getComposedMessage ( "only.rgb.gray.and.cmyk.are.supported.as.alternative.color.spaces" ) );
			}
		} else {
			array.add ( PdfName.DEVICERGB );
			func = PdfFunction.type2 ( writer, new float[] { 0, 1 }, null, new float[] { 1, 1, 1 },
							new float[] { (float) altcs.getRed () / 255, (float) altcs.getGreen () / 255, (float) altcs.getBlue () / 255 }, 1 );
		}
		array.add ( func.getReference () );
		return array;
	}

	public boolean equals ( Object obj ) {
		return obj instanceof PdfSpotColor && ( (PdfSpotColor) obj ).name == this.name && ( (PdfSpotColor) obj ).altcs == this.altcs;
	}

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class PdfImage extends PdfStream {

	static final int TRANSFERSIZE = 4096;

	protected PdfName name = null;

	protected Image image;

	public PdfImage ( Image image, String name, PdfIndirectReference maskRef ) throws BadPdfFormatException {
		super ();
		this.image = image;
		if ( name == null )
			generateImgResName ( image );
		else
			this.name = new PdfName ( name );
		put ( PdfName.TYPE, PdfName.XOBJECT );
		put ( PdfName.SUBTYPE, PdfName.IMAGE );
		put ( PdfName.WIDTH, new PdfNumber ( image.getWidth () ) );
		put ( PdfName.HEIGHT, new PdfNumber ( image.getHeight () ) );
		if ( image.getLayer () != null )
			put ( PdfName.OC, image.getLayer ().getRef () );
		if ( image.isMask () && ( image.getBpc () == 1 || image.getBpc () > 0xff ) )
			put ( PdfName.IMAGEMASK, PdfBoolean.PDFTRUE );
		if ( maskRef != null ) {
			if ( image.isSmask () )
				put ( PdfName.SMASK, maskRef );
			else
				put ( PdfName.MASK, maskRef );
		}
		if ( image.isMask () && image.isInverted () )
			put ( PdfName.DECODE, new PdfLiteral ( "[1 0]" ) );
		if ( image.isInterpolation () )
			put ( PdfName.INTERPOLATE, PdfBoolean.PDFTRUE );
		InputStream is = null;
		try {
			// deal with transparency
			int[] transparency = image.getTransparency ();
			if ( transparency != null && !image.isMask () && maskRef == null ) {
				StringBuilder s = new StringBuilder ( "[" );
				for ( int i : transparency )
					s.append ( i ).append ( " " );
				s.append ( "]" );
				put ( PdfName.MASK, new PdfLiteral ( s.toString () ) );
			}
			// Raw Image data
			if ( image.isImgRaw () ) {
				// will also have the CCITT parameters
				int colorspace = image.getColorspace ();
				bytes = image.getRawData ();
				put ( PdfName.LENGTH, new PdfNumber ( bytes.length ) );
				int bpc = image.getBpc ();
				if ( bpc > 0xff ) {
					if ( !image.isMask () )
						put ( PdfName.COLORSPACE, PdfName.DEVICEGRAY );
					put ( PdfName.BITSPERCOMPONENT, new PdfNumber ( 1 ) );
					put ( PdfName.FILTER, PdfName.CCITTFAXDECODE );
					int k = bpc - Image.CCITTG3_1D;
					PdfDictionary decodeparms = new PdfDictionary ();
					if ( k != 0 )
						decodeparms.put ( PdfName.K, new PdfNumber ( k ) );
					if ( ( colorspace & Image.CCITT_BLACKIS1 ) != 0 )
						decodeparms.put ( PdfName.BLACKIS1, PdfBoolean.PDFTRUE );
					if ( ( colorspace & Image.CCITT_ENCODEDBYTEALIGN ) != 0 )
						decodeparms.put ( PdfName.ENCODEDBYTEALIGN, PdfBoolean.PDFTRUE );
					if ( ( colorspace & Image.CCITT_ENDOFLINE ) != 0 )
						decodeparms.put ( PdfName.ENDOFLINE, PdfBoolean.PDFTRUE );
					if ( ( colorspace & Image.CCITT_ENDOFBLOCK ) != 0 )
						decodeparms.put ( PdfName.ENDOFBLOCK, PdfBoolean.PDFFALSE );
					decodeparms.put ( PdfName.COLUMNS, new PdfNumber ( image.getWidth () ) );
					decodeparms.put ( PdfName.ROWS, new PdfNumber ( image.getHeight () ) );
					put ( PdfName.DECODEPARMS, decodeparms );
				} else {
					switch ( colorspace ) {
					case 1:
						put ( PdfName.COLORSPACE, PdfName.DEVICEGRAY );
						if ( image.isInverted () )
							put ( PdfName.DECODE, new PdfLiteral ( "[1 0]" ) );
						break;
					case 3:
						put ( PdfName.COLORSPACE, PdfName.DEVICERGB );
						if ( image.isInverted () )
							put ( PdfName.DECODE, new PdfLiteral ( "[1 0 1 0 1 0]" ) );
						break;
					case 4:
					default:
						put ( PdfName.COLORSPACE, PdfName.DEVICECMYK );
						if ( image.isInverted () )
							put ( PdfName.DECODE, new PdfLiteral ( "[1 0 1 0 1 0 1 0]" ) );
					}
					PdfDictionary additional = image.getAdditional ();
					if ( additional != null )
						putAll ( additional );
					if ( image.isMask () && ( image.getBpc () == 1 || image.getBpc () > 8 ) )
						remove ( PdfName.COLORSPACE );
					put ( PdfName.BITSPERCOMPONENT, new PdfNumber ( image.getBpc () ) );
					if ( image.isDeflated () )
						put ( PdfName.FILTER, PdfName.FLATEDECODE );
					else {
						flateCompress ( image.getCompressionLevel () );
					}
				}
				return;
			}
			// GIF, JPEG or PNG
			String errorID;
			if ( image.getRawData () == null ) {
				is = image.getUrl ().openStream ();
				errorID = image.getUrl ().toString ();
			} else {
				is = new java.io.ByteArrayInputStream ( image.getRawData () );
				errorID = "Byte array";
			}
			switch ( image.type () ) {
			case Image.JPEG:
				put ( PdfName.FILTER, PdfName.DCTDECODE );
				if ( image.getColorTransform () == 0 ) {
					PdfDictionary decodeparms = new PdfDictionary ();
					decodeparms.put ( PdfName.COLORTRANSFORM, new PdfNumber ( 0 ) );
					put ( PdfName.DECODEPARMS, decodeparms );
				}
				switch ( image.getColorspace () ) {
				case 1:
					put ( PdfName.COLORSPACE, PdfName.DEVICEGRAY );
					break;
				case 3:
					put ( PdfName.COLORSPACE, PdfName.DEVICERGB );
					break;
				default:
					put ( PdfName.COLORSPACE, PdfName.DEVICECMYK );
					if ( image.isInverted () ) {
						put ( PdfName.DECODE, new PdfLiteral ( "[1 0 1 0 1 0 1 0]" ) );
					}
				}
				put ( PdfName.BITSPERCOMPONENT, new PdfNumber ( 8 ) );
				if ( image.getRawData () != null ) {
					bytes = image.getRawData ();
					put ( PdfName.LENGTH, new PdfNumber ( bytes.length ) );
					return;
				}
				streamBytes = new ByteArrayOutputStream ();
				transferBytes ( is, streamBytes, -1 );
				break;
			case Image.JPEG2000:
				put ( PdfName.FILTER, PdfName.JPXDECODE );
				if ( image.getColorspace () > 0 ) {
					switch ( image.getColorspace () ) {
					case 1:
						put ( PdfName.COLORSPACE, PdfName.DEVICEGRAY );
						break;
					case 3:
						put ( PdfName.COLORSPACE, PdfName.DEVICERGB );
						break;
					default:
						put ( PdfName.COLORSPACE, PdfName.DEVICECMYK );
					}
					put ( PdfName.BITSPERCOMPONENT, new PdfNumber ( image.getBpc () ) );
				}
				if ( image.getRawData () != null ) {
					bytes = image.getRawData ();
					put ( PdfName.LENGTH, new PdfNumber ( bytes.length ) );
					return;
				}
				streamBytes = new ByteArrayOutputStream ();
				transferBytes ( is, streamBytes, -1 );
				break;
			case Image.JBIG2:
				put ( PdfName.FILTER, PdfName.JBIG2DECODE );
				put ( PdfName.COLORSPACE, PdfName.DEVICEGRAY );
				put ( PdfName.BITSPERCOMPONENT, new PdfNumber ( 1 ) );
				if ( image.getRawData () != null ) {
					bytes = image.getRawData ();
					put ( PdfName.LENGTH, new PdfNumber ( bytes.length ) );
					return;
				}
				streamBytes = new ByteArrayOutputStream ();
				transferBytes ( is, streamBytes, -1 );
				break;
			default:
				throw new BadPdfFormatException ( MessageLocalization.getComposedMessage ( "1.is.an.unknown.image.format", errorID ) );
			}
			put ( PdfName.LENGTH, new PdfNumber ( streamBytes.size () ) );
		} catch ( IOException ioe ) {
			throw new BadPdfFormatException ( ioe.getMessage () );
		} finally {
			if ( is != null ) {
				try {
					is.close ();
				} catch ( Exception ignored ) {
				}
			}
		}
	}

	static void transferBytes ( InputStream in, OutputStream out, int len ) throws IOException {
		byte[] buffer = new byte[TRANSFERSIZE];
		if ( len < 0 )
			len = 0x7fff0000;
		int size;
		while ( len != 0 ) {
			size = in.read ( buffer, 0, Math.min ( len, TRANSFERSIZE ) );
			if ( size < 0 )
				return;
			out.write ( buffer, 0, size );
			len -= size;
		}
	}

	public PdfName name () {
		return name;
	}

	public Image getImage () {
		return image;
	}

	private void generateImgResName ( Image img ) {
		name = new PdfName ( "img" + Long.toHexString ( img.getMySerialId () ) );
	}
}

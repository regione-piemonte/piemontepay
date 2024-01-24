/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.parser;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Version;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.exceptions.UnsupportedPdfException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.FilterHandlers;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PRStream;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfArray;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfDictionary;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfReader;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfString;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec.PngWriter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec.TIFFConstants;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec.TiffWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class PdfImageObject {

	private final PdfDictionary dictionary;

	private final PdfDictionary colorSpaceDic;

	private byte[] imageBytes;

	private int pngColorType = -1;

	private int width;

	private int bpc;

	private byte[] palette;

	private byte[] icc;

	private int stride;

	private ImageBytesType streamContentType = null;

	public PdfImageObject ( PRStream stream, PdfDictionary colorSpaceDic ) throws IOException {
		this ( stream, PdfReader.getStreamBytesRaw ( stream ), colorSpaceDic );
	}

	protected PdfImageObject ( PdfDictionary dictionary, byte[] samples, PdfDictionary colorSpaceDic ) throws IOException {
		this.dictionary = dictionary;
		this.colorSpaceDic = colorSpaceDic;
		TrackingFilter trackingFilter = new TrackingFilter ();
		Map<PdfName, FilterHandlers.FilterHandler> handlers = new HashMap<> ( FilterHandlers.getDefaultFilterHandlers () );
		handlers.put ( PdfName.JBIG2DECODE, trackingFilter );
		handlers.put ( PdfName.DCTDECODE, trackingFilter );
		handlers.put ( PdfName.JPXDECODE, trackingFilter );

		imageBytes = PdfReader.decodeBytes ( samples, dictionary, handlers );

		if ( trackingFilter.lastFilterName != null ) {
			if ( PdfName.JBIG2DECODE.equals ( trackingFilter.lastFilterName ) )
				streamContentType = ImageBytesType.JBIG2;
			else if ( PdfName.DCTDECODE.equals ( trackingFilter.lastFilterName ) )
				streamContentType = ImageBytesType.JPG;
			else if ( PdfName.JPXDECODE.equals ( trackingFilter.lastFilterName ) )
				streamContentType = ImageBytesType.JP2;
		} else {
			decodeImageBytes ();
		}
	}

	public PdfObject get ( PdfName key ) {
		return dictionary.get ( key );
	}

	public PdfDictionary getDictionary () {
		return dictionary;
	}

	private void findColorspace ( PdfObject colorspace, boolean allowIndexed ) throws IOException {
		if ( colorspace == null && bpc == 1 ) { // handle imagemasks
			stride = ( width * bpc + 7 ) / 8;
			pngColorType = 0;
		} else if ( PdfName.DEVICEGRAY.equals ( colorspace ) ) {
			stride = ( width * bpc + 7 ) / 8;
			pngColorType = 0;
		} else if ( PdfName.DEVICERGB.equals ( colorspace ) ) {
			if ( bpc == 8 || bpc == 16 ) {
				stride = ( width * bpc * 3 + 7 ) / 8;
				pngColorType = 2;
			}
		} else if ( colorspace instanceof PdfArray ) {
			PdfArray ca = (PdfArray) colorspace;
			PdfObject tyca = ca.getDirectObject ( 0 );
			if ( PdfName.CALGRAY.equals ( tyca ) ) {
				stride = ( width * bpc + 7 ) / 8;
				pngColorType = 0;
			} else if ( PdfName.CALRGB.equals ( tyca ) ) {
				if ( bpc == 8 || bpc == 16 ) {
					stride = ( width * bpc * 3 + 7 ) / 8;
					pngColorType = 2;
				}
			} else if ( PdfName.ICCBASED.equals ( tyca ) ) {
				PRStream pr = (PRStream) ca.getDirectObject ( 1 );
				int n = pr.getAsNumber ( PdfName.N ).intValue ();
				if ( n == 1 ) {
					stride = ( width * bpc + 7 ) / 8;
					pngColorType = 0;
					icc = PdfReader.getStreamBytes ( pr );
				} else if ( n == 3 ) {
					stride = ( width * bpc * 3 + 7 ) / 8;
					pngColorType = 2;
					icc = PdfReader.getStreamBytes ( pr );
				}
			} else if ( allowIndexed && PdfName.INDEXED.equals ( tyca ) ) {
				findColorspace ( ca.getDirectObject ( 1 ), false );
				if ( pngColorType == 2 ) {
					PdfObject id2 = ca.getDirectObject ( 3 );
					if ( id2 instanceof PdfString ) {
						palette = id2.getBytes ();
					} else if ( id2 instanceof PRStream ) {
						palette = PdfReader.getStreamBytes ( ( (PRStream) id2 ) );
					}
					stride = ( width * bpc + 7 ) / 8;
					pngColorType = 3;
				}
			}
		}
	}

	private void decodeImageBytes () throws IOException {
		if ( streamContentType != null )
			throw new IllegalStateException (
							MessageLocalization.getComposedMessage ( "Decoding.can't.happen.on.this.type.of.stream.(.1.)", streamContentType ) );

		pngColorType = -1;
		PdfArray decode = dictionary.getAsArray ( PdfName.DECODE );
		width = dictionary.getAsNumber ( PdfName.WIDTH ).intValue ();
		int height = dictionary.getAsNumber ( PdfName.HEIGHT ).intValue ();
		bpc = dictionary.getAsNumber ( PdfName.BITSPERCOMPONENT ).intValue ();
		int pngBitDepth = bpc;
		PdfObject colorspace = dictionary.getDirectObject ( PdfName.COLORSPACE );
		if ( colorspace instanceof PdfName && colorSpaceDic != null ) {
			PdfObject csLookup = colorSpaceDic.getDirectObject ( (PdfName) colorspace );
			if ( csLookup != null )
				colorspace = csLookup;
		}

		palette = null;
		icc = null;
		stride = 0;
		findColorspace ( colorspace, true );
		ByteArrayOutputStream ms = new ByteArrayOutputStream ();
		if ( pngColorType < 0 ) {
			if ( bpc != 8 )
				throw new UnsupportedPdfException ( MessageLocalization.getComposedMessage ( "the.color.depth.1.is.not.supported", bpc ) );

			if ( PdfName.DEVICECMYK.equals ( colorspace ) ) {
			} else if ( colorspace instanceof PdfArray ) {
				PdfArray ca = (PdfArray) colorspace;
				PdfObject tyca = ca.getDirectObject ( 0 );
				if ( !PdfName.ICCBASED.equals ( tyca ) )
					throw new UnsupportedPdfException ( MessageLocalization.getComposedMessage ( "the.color.space.1.is.not.supported", colorspace ) );
				PRStream pr = (PRStream) ca.getDirectObject ( 1 );
				int n = pr.getAsNumber ( PdfName.N ).intValue ();
				if ( n != 4 ) {
					throw new UnsupportedPdfException ( MessageLocalization.getComposedMessage ( "N.value.1.is.not.supported", n ) );
				}
				icc = PdfReader.getStreamBytes ( pr );
			} else
				throw new UnsupportedPdfException ( MessageLocalization.getComposedMessage ( "the.color.space.1.is.not.supported", colorspace ) );
			stride = 4 * width;
			TiffWriter wr = new TiffWriter ();
			wr.addField ( new TiffWriter.FieldShort ( TIFFConstants.TIFFTAG_SAMPLESPERPIXEL, 4 ) );
			wr.addField ( new TiffWriter.FieldShort ( TIFFConstants.TIFFTAG_BITSPERSAMPLE, new int[] { 8, 8, 8, 8 } ) );
			wr.addField ( new TiffWriter.FieldShort ( TIFFConstants.TIFFTAG_PHOTOMETRIC, TIFFConstants.PHOTOMETRIC_SEPARATED ) );
			wr.addField ( new TiffWriter.FieldLong ( TIFFConstants.TIFFTAG_IMAGEWIDTH, width ) );
			wr.addField ( new TiffWriter.FieldLong ( TIFFConstants.TIFFTAG_IMAGELENGTH, height ) );
			wr.addField ( new TiffWriter.FieldShort ( TIFFConstants.TIFFTAG_COMPRESSION, TIFFConstants.COMPRESSION_LZW ) );
			wr.addField ( new TiffWriter.FieldShort ( TIFFConstants.TIFFTAG_PREDICTOR, TIFFConstants.PREDICTOR_HORIZONTAL_DIFFERENCING ) );
			wr.addField ( new TiffWriter.FieldLong ( TIFFConstants.TIFFTAG_ROWSPERSTRIP, height ) );
			wr.addField ( new TiffWriter.FieldRational ( TIFFConstants.TIFFTAG_XRESOLUTION, new int[] { 300, 1 } ) );
			wr.addField ( new TiffWriter.FieldRational ( TIFFConstants.TIFFTAG_YRESOLUTION, new int[] { 300, 1 } ) );
			wr.addField ( new TiffWriter.FieldShort ( TIFFConstants.TIFFTAG_RESOLUTIONUNIT, TIFFConstants.RESUNIT_INCH ) );
			wr.addField ( new TiffWriter.FieldAscii ( TIFFConstants.TIFFTAG_SOFTWARE, Version.getInstance ().getVersion () ) );
			ByteArrayOutputStream comp = new ByteArrayOutputStream ();
			TiffWriter.compressLZW ( comp, 2, imageBytes, height, 4, stride );
			byte[] buf = comp.toByteArray ();
			wr.addField ( new TiffWriter.FieldImage ( buf ) );
			wr.addField ( new TiffWriter.FieldLong ( TIFFConstants.TIFFTAG_STRIPBYTECOUNTS, buf.length ) );
			if ( icc != null )
				wr.addField ( new TiffWriter.FieldUndefined ( TIFFConstants.TIFFTAG_ICCPROFILE, icc ) );
			wr.writeFile ( ms );
			streamContentType = ImageBytesType.CCITT;
		} else {
			PngWriter png = new PngWriter ( ms );
			if ( decode != null ) {
				if ( pngBitDepth == 1 ) {
					// if the decode array is 1,0, then we need to invert the image
					if ( decode.getAsNumber ( 0 ).intValue () == 1 && decode.getAsNumber ( 1 ).intValue () == 0 ) {
						int len = imageBytes.length;
						for ( int t = 0; t < len; ++t ) {
							imageBytes[t] ^= (byte) 0xff;
						}
					}
				}

			}
			png.writeHeader ( width, height, pngBitDepth, pngColorType );
			if ( icc != null )
				png.writeIccProfile ( icc );
			if ( palette != null )
				png.writePalette ( palette );
			png.writeData ( imageBytes, stride );
			png.writeEnd ();
			streamContentType = ImageBytesType.PNG;
		}
		imageBytes = ms.toByteArray ();
	}

	public enum ImageBytesType {
		PNG (), // the stream contains png encoded data
		JPG (), // the stream contains jpg encoded data
		JP2 (), // the stream contains jp2 encoded data
		CCITT (), // the stream contains ccitt encoded data
		JBIG2 () // the stream contains JBIG2 encoded data
		;

		ImageBytesType () {
		}

	}


	private static class TrackingFilter implements FilterHandlers.FilterHandler {

		public PdfName lastFilterName = null;

		public byte[] decode ( byte[] b, PdfName filterName, PdfObject decodeParams, PdfDictionary streamDictionary ) {
			lastFilterName = filterName;
			return b;
		}

	}

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.exceptions.UnsupportedPdfException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec.TIFFConstants;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec.TIFFFaxDecoder;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec.TIFFFaxDecompressor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public final class FilterHandlers {

	private static final Map<PdfName, FilterHandler> defaults;

	static {
		HashMap<PdfName, FilterHandler> map = new HashMap<> ();

		map.put ( PdfName.FLATEDECODE, new Filter_FLATEDECODE () );
		map.put ( PdfName.FL, new Filter_FLATEDECODE () );
		map.put ( PdfName.ASCIIHEXDECODE, new Filter_ASCIIHEXDECODE () );
		map.put ( PdfName.AHX, new Filter_ASCIIHEXDECODE () );
		map.put ( PdfName.ASCII85DECODE, new Filter_ASCII85DECODE () );
		map.put ( PdfName.A85, new Filter_ASCII85DECODE () );
		map.put ( PdfName.LZWDECODE, new Filter_LZWDECODE () );
		map.put ( PdfName.CCITTFAXDECODE, new Filter_CCITTFAXDECODE () );
		map.put ( PdfName.CRYPT, new Filter_DoNothing () );
		map.put ( PdfName.RUNLENGTHDECODE, new Filter_RUNLENGTHDECODE () );

		defaults = Collections.unmodifiableMap ( map );
	}

	public static Map<PdfName, FilterHandler> getDefaultFilterHandlers () {
		return defaults;
	}

	public interface FilterHandler {

		byte[] decode ( byte[] b, PdfName filterName, PdfObject decodeParams, final PdfDictionary streamDictionary ) throws IOException;
	}


	private static class Filter_FLATEDECODE implements FilterHandler {

		public byte[] decode ( byte[] b, PdfName filterName, PdfObject decodeParams, PdfDictionary streamDictionary ) {
			b = PdfReader.FlateDecode ( b );
			b = PdfReader.decodePredictor ( b, decodeParams );
			return b;
		}
	}


	private static class Filter_ASCIIHEXDECODE implements FilterHandler {

		public byte[] decode ( byte[] b, PdfName filterName, PdfObject decodeParams, PdfDictionary streamDictionary ) {
			b = PdfReader.ASCIIHexDecode ( b );
			return b;
		}
	}


	private static class Filter_ASCII85DECODE implements FilterHandler {

		public byte[] decode ( byte[] b, PdfName filterName, PdfObject decodeParams, PdfDictionary streamDictionary ) {
			b = PdfReader.ASCII85Decode ( b );
			return b;
		}
	}


	private static class Filter_LZWDECODE implements FilterHandler {

		public byte[] decode ( byte[] b, PdfName filterName, PdfObject decodeParams, PdfDictionary streamDictionary ) {
			b = PdfReader.LZWDecode ( b );
			b = PdfReader.decodePredictor ( b, decodeParams );
			return b;
		}
	}


	private static class Filter_CCITTFAXDECODE implements FilterHandler {

		public byte[] decode ( byte[] b, PdfName filterName, PdfObject decodeParams, PdfDictionary streamDictionary ) throws IOException {
			PdfNumber wn = (PdfNumber) PdfReader.getPdfObjectRelease ( streamDictionary.get ( PdfName.WIDTH ) );
			PdfNumber hn = (PdfNumber) PdfReader.getPdfObjectRelease ( streamDictionary.get ( PdfName.HEIGHT ) );
			if ( wn == null || hn == null )
				throw new UnsupportedPdfException ( MessageLocalization.getComposedMessage ( "filter.ccittfaxdecode.is.only.supported.for.images" ) );
			int width = wn.intValue ();
			int height = hn.intValue ();

			PdfDictionary param = decodeParams instanceof PdfDictionary ? (PdfDictionary) decodeParams : null;
			int k = 0;
			boolean blackIs1 = false;
			boolean byteAlign = false;
			if ( param != null ) {
				PdfNumber kn = param.getAsNumber ( PdfName.K );
				if ( kn != null )
					k = kn.intValue ();
				PdfBoolean bo = param.getAsBoolean ( PdfName.BLACKIS1 );
				if ( bo != null )
					blackIs1 = bo.booleanValue ();
				bo = param.getAsBoolean ( PdfName.ENCODEDBYTEALIGN );
				if ( bo != null )
					byteAlign = bo.booleanValue ();
			}
			byte[] outBuf = new byte[( width + 7 ) / 8 * height];
			TIFFFaxDecompressor decoder = new TIFFFaxDecompressor ();
			if ( k == 0 || k > 0 ) {
				int tiffT4Options = k > 0 ? TIFFConstants.GROUP3OPT_2DENCODING : 0;
				tiffT4Options |= byteAlign ? TIFFConstants.GROUP3OPT_FILLBITS : 0;
				decoder.SetOptions ( 1, TIFFConstants.COMPRESSION_CCITTFAX3, tiffT4Options, 0 );
				decoder.decodeRaw ( outBuf, b, width, height );
				if ( decoder.fails > 0 ) {
					byte[] outBuf2 = new byte[( width + 7 ) / 8 * height];
					int oldFails = decoder.fails;
					decoder.SetOptions ( 1, TIFFConstants.COMPRESSION_CCITTRLE, tiffT4Options, 0 );
					decoder.decodeRaw ( outBuf2, b, width, height );
					if ( decoder.fails < oldFails ) {
						outBuf = outBuf2;
					}
				}
			} else {
				TIFFFaxDecoder deca = new TIFFFaxDecoder ( 1, width );
				deca.decodeT6 ( outBuf, b, 0, height, 0 );
			}
			if ( !blackIs1 ) {
				int len = outBuf.length;
				for ( int t = 0; t < len; ++t ) {
					outBuf[t] ^= (byte) 0xff;
				}
			}
			b = outBuf;
			return b;
		}
	}


	private static class Filter_DoNothing implements FilterHandler {

		public byte[] decode ( byte[] b, PdfName filterName, PdfObject decodeParams, PdfDictionary streamDictionary ) {
			return b;
		}
	}


	private static class Filter_RUNLENGTHDECODE implements FilterHandler {

		public byte[] decode ( byte[] b, PdfName filterName, PdfObject decodeParams, PdfDictionary streamDictionary ) {
			// allocate the output buffer
			ByteArrayOutputStream baos = new ByteArrayOutputStream ();
			byte dupCount;
			for ( int i = 0; i < b.length; i++ ) {
				dupCount = b[i];
				if ( dupCount == -128 )
					break; // this is implicit end of data

				if ( dupCount >= 0 ) {
					int bytesToCopy = dupCount + 1;
					baos.write ( b, i, bytesToCopy );
					i += bytesToCopy;
				} else {
					// make dupcount copies of the next byte
					i++;
					for ( int j = 0; j < 1 - (int) ( dupCount ); j++ ) {
						baos.write ( b[i] );
					}
				}
			}

			return baos.toByteArray ();
		}
	}

}

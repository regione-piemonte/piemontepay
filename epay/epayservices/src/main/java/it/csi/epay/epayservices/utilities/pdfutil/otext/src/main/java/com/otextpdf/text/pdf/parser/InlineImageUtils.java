/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.parser;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PRTokeniser;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfArray;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfContentParser;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfDictionary;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfNumber;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public final class InlineImageUtils {

	private final static Map<PdfName, PdfName> inlineImageEntryAbbreviationMap;

	private static final Map<PdfName, PdfName> inlineImageColorSpaceAbbreviationMap;

	private static final Map<PdfName, PdfName> inlineImageFilterAbbreviationMap;

	static { // static initializer
		inlineImageEntryAbbreviationMap = new HashMap<> ();

		// allowed entries - just pass these through
		inlineImageEntryAbbreviationMap.put ( PdfName.BITSPERCOMPONENT, PdfName.BITSPERCOMPONENT );
		inlineImageEntryAbbreviationMap.put ( PdfName.COLORSPACE, PdfName.COLORSPACE );
		inlineImageEntryAbbreviationMap.put ( PdfName.DECODE, PdfName.DECODE );
		inlineImageEntryAbbreviationMap.put ( PdfName.DECODEPARMS, PdfName.DECODEPARMS );
		inlineImageEntryAbbreviationMap.put ( PdfName.FILTER, PdfName.FILTER );
		inlineImageEntryAbbreviationMap.put ( PdfName.HEIGHT, PdfName.HEIGHT );
		inlineImageEntryAbbreviationMap.put ( PdfName.IMAGEMASK, PdfName.IMAGEMASK );
		inlineImageEntryAbbreviationMap.put ( PdfName.INTENT, PdfName.INTENT );
		inlineImageEntryAbbreviationMap.put ( PdfName.INTERPOLATE, PdfName.INTERPOLATE );
		inlineImageEntryAbbreviationMap.put ( PdfName.WIDTH, PdfName.WIDTH );

		// abbreviations - transform these to corresponding correct values
		inlineImageEntryAbbreviationMap.put ( new PdfName ( "BPC" ), PdfName.BITSPERCOMPONENT );
		inlineImageEntryAbbreviationMap.put ( new PdfName ( "CS" ), PdfName.COLORSPACE );
		inlineImageEntryAbbreviationMap.put ( new PdfName ( "D" ), PdfName.DECODE );
		inlineImageEntryAbbreviationMap.put ( new PdfName ( "DP" ), PdfName.DECODEPARMS );
		inlineImageEntryAbbreviationMap.put ( new PdfName ( "F" ), PdfName.FILTER );
		inlineImageEntryAbbreviationMap.put ( new PdfName ( "H" ), PdfName.HEIGHT );
		inlineImageEntryAbbreviationMap.put ( new PdfName ( "IM" ), PdfName.IMAGEMASK );
		inlineImageEntryAbbreviationMap.put ( new PdfName ( "I" ), PdfName.INTERPOLATE );
		inlineImageEntryAbbreviationMap.put ( new PdfName ( "W" ), PdfName.WIDTH );
	}

	static {
		inlineImageColorSpaceAbbreviationMap = new HashMap<> ();

		inlineImageColorSpaceAbbreviationMap.put ( new PdfName ( "G" ), PdfName.DEVICEGRAY );
		inlineImageColorSpaceAbbreviationMap.put ( new PdfName ( "RGB" ), PdfName.DEVICERGB );
		inlineImageColorSpaceAbbreviationMap.put ( new PdfName ( "CMYK" ), PdfName.DEVICECMYK );
		inlineImageColorSpaceAbbreviationMap.put ( new PdfName ( "I" ), PdfName.INDEXED );
	}

	static {
		inlineImageFilterAbbreviationMap = new HashMap<> ();

		inlineImageFilterAbbreviationMap.put ( new PdfName ( "AHx" ), PdfName.ASCIIHEXDECODE );
		inlineImageFilterAbbreviationMap.put ( new PdfName ( "A85" ), PdfName.ASCII85DECODE );
		inlineImageFilterAbbreviationMap.put ( new PdfName ( "LZW" ), PdfName.LZWDECODE );
		inlineImageFilterAbbreviationMap.put ( new PdfName ( "Fl" ), PdfName.FLATEDECODE );
		inlineImageFilterAbbreviationMap.put ( new PdfName ( "RL" ), PdfName.RUNLENGTHDECODE );
		inlineImageFilterAbbreviationMap.put ( new PdfName ( "CCF" ), PdfName.CCITTFAXDECODE );
		inlineImageFilterAbbreviationMap.put ( new PdfName ( "DCT" ), PdfName.DCTDECODE );
	}

	private InlineImageUtils () {
	}

	public static InlineImageInfo parseInlineImage ( PdfContentParser ps, PdfDictionary colorSpaceDic ) throws IOException {
		PdfDictionary inlineImageDictionary = parseInlineImageDictionary ( ps );
		byte[] samples = parseInlineImageSamples ( inlineImageDictionary, colorSpaceDic, ps );
		return new InlineImageInfo ( samples, inlineImageDictionary );
	}

	private static PdfDictionary parseInlineImageDictionary ( PdfContentParser ps ) throws IOException {
		// by the time we get to here, we have already parsed the BI operator
		PdfDictionary dictionary = new PdfDictionary ();

		for ( PdfObject key = ps.readPRObject (); key != null && !"ID".equals ( key.toString () ); key = ps.readPRObject () ) {
			PdfObject value = ps.readPRObject ();

			PdfName resolvedKey = inlineImageEntryAbbreviationMap.get ( key );
			if ( resolvedKey == null )
				resolvedKey = (PdfName) key;

			dictionary.put ( resolvedKey, getAlternateValue ( resolvedKey, value ) );
		}

		int ch = ps.getTokeniser ().read ();
		if ( !PRTokeniser.isWhitespace ( ch ) )
			throw new IOException ( "Unexpected character " + ch + " found after ID in inline image" );

		return dictionary;
	}

	private static PdfObject getAlternateValue ( PdfName key, PdfObject value ) {
		if ( key == PdfName.FILTER ) {
			if ( value instanceof PdfName ) {
				PdfName altValue = inlineImageFilterAbbreviationMap.get ( value );
				if ( altValue != null )
					return altValue;
			} else if ( value instanceof PdfArray ) {
				PdfArray array = ( (PdfArray) value );
				PdfArray altArray = new PdfArray ();
				int count = array.size ();
				for ( int i = 0; i < count; i++ ) {
					altArray.add ( getAlternateValue ( key, array.getPdfObject ( i ) ) );
				}
				return altArray;
			}
		} else if ( key == PdfName.COLORSPACE ) {
			PdfName altValue = inlineImageColorSpaceAbbreviationMap.get ( value );
			if ( altValue != null )
				return altValue;
		}

		return value;
	}

	private static int getComponentsPerPixel ( PdfName colorSpaceName, PdfDictionary colorSpaceDic ) {
		if ( colorSpaceName == null )
			return 1;
		if ( colorSpaceName.equals ( PdfName.DEVICEGRAY ) )
			return 1;
		if ( colorSpaceName.equals ( PdfName.DEVICERGB ) )
			return 3;
		if ( colorSpaceName.equals ( PdfName.DEVICECMYK ) )
			return 4;

		if ( colorSpaceDic != null ) {
			PdfArray colorSpace = colorSpaceDic.getAsArray ( colorSpaceName );
			if ( colorSpace != null ) {
				if ( PdfName.INDEXED.equals ( colorSpace.getAsName ( 0 ) ) ) {
					return 1;
				}
			} else {
				PdfName tempName = colorSpaceDic.getAsName ( colorSpaceName );
				if ( tempName != null ) {
					return getComponentsPerPixel ( tempName, colorSpaceDic );
				}
			}
		}

		throw new IllegalArgumentException ( "Unexpected color space " + colorSpaceName );
	}

	private static int computeBytesPerRow ( PdfDictionary imageDictionary, PdfDictionary colorSpaceDic ) {
		PdfNumber wObj = imageDictionary.getAsNumber ( PdfName.WIDTH );
		PdfNumber bpcObj = imageDictionary.getAsNumber ( PdfName.BITSPERCOMPONENT );
		int cpp = getComponentsPerPixel ( imageDictionary.getAsName ( PdfName.COLORSPACE ), colorSpaceDic );

		int w = wObj.intValue ();
		int bpc = bpcObj != null ? bpcObj.intValue () : 1;

		return ( w * bpc * cpp + 7 ) / 8;
	}

	private static byte[] parseUnfilteredSamples ( PdfDictionary imageDictionary, PdfDictionary colorSpaceDic, PdfContentParser ps ) throws IOException {
		if ( imageDictionary.contains ( PdfName.FILTER ) )
			throw new IllegalArgumentException ( "Dictionary contains filters" );

		PdfNumber h = imageDictionary.getAsNumber ( PdfName.HEIGHT );

		int bytesToRead = computeBytesPerRow ( imageDictionary, colorSpaceDic ) * h.intValue ();
		byte[] bytes = new byte[bytesToRead];
		PRTokeniser tokeniser = ps.getTokeniser ();

		int shouldBeWhiteSpace = tokeniser.read (); // skip next character (which better be a whitespace character - I suppose we could check for this)
		int startIndex = 0;
		if ( !PRTokeniser.isWhitespace (
						shouldBeWhiteSpace ) || shouldBeWhiteSpace == 0 ) { // tokeniser treats 0 as whitespace, but for our purposes, we shouldn't
			bytes[0] = (byte) shouldBeWhiteSpace;
			startIndex++;
		}
		for ( int i = startIndex; i < bytesToRead; i++ ) {
			int ch = tokeniser.read ();
			if ( ch == -1 )
				throw new InlineImageParseException ( "End of content stream reached before end of image data" );

			bytes[i] = (byte) ch;
		}
		PdfObject ei = ps.readPRObject ();
		if ( !ei.toString ().equals ( "EI" ) ) {
			// Some PDF producers seem to add another non-whitespace character after the image data.
			// Let's try to handle that case here.
			PdfObject ei2 = ps.readPRObject ();
			if ( !ei2.toString ().equals ( "EI" ) )
				throw new InlineImageParseException ( "EI not found after end of image data" );
		}
		return bytes;
	}

	private static byte[] parseInlineImageSamples ( PdfDictionary imageDictionary, PdfDictionary colorSpaceDic, PdfContentParser ps ) throws IOException {
		// by the time we get to here, we have already parsed the ID operator

		if ( !imageDictionary.contains ( PdfName.FILTER ) ) {
			return parseUnfilteredSamples ( imageDictionary, colorSpaceDic, ps );
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream ();
		ByteArrayOutputStream accumulated = new ByteArrayOutputStream ();
		int ch;
		int found = 0;
		PRTokeniser tokeniser = ps.getTokeniser ();

		while ( ( ch = tokeniser.read () ) != -1 ) {
			if ( found == 0 && PRTokeniser.isWhitespace ( ch ) ) {
				found++;
				accumulated.write ( ch );
			} else if ( found == 1 && ch == 'E' ) {
				found++;
				accumulated.write ( ch );
			} else if ( found == 1 && PRTokeniser.isWhitespace ( ch ) ) {
				baos.write ( accumulated.toByteArray () );
				accumulated.reset ();
				accumulated.write ( ch );
			} else if ( found == 2 && ch == 'I' ) {
				found++;
				accumulated.write ( ch );
			} else if ( found == 3 && PRTokeniser.isWhitespace ( ch ) ) {
				byte[] tmp = baos.toByteArray ();
				try {
					new PdfImageObject ( imageDictionary, tmp, colorSpaceDic );
					return tmp;
				} catch ( Exception e ) {
					baos.write ( accumulated.toByteArray () );
					accumulated.reset ();

					baos.write ( ch );
					found = 0;
				}
			} else {
				baos.write ( accumulated.toByteArray () );
				accumulated.reset ();

				baos.write ( ch );
				found = 0;
			}
		}
		throw new InlineImageParseException ( "Could not find image data or EI" );
	}

	public static class InlineImageParseException extends IOException {

		private static final long serialVersionUID = 233760879000268548L;

		public InlineImageParseException ( String message ) {
			super ( message );
		}

	}
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.parser;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfDictionary;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfReader;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfStream;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.RandomAccessFileOrArray;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


public class PdfContentReaderTool {

	static public String getDictionaryDetail ( PdfDictionary dic ) {
		return getDictionaryDetail ( dic, 0 );
	}

	static public String getDictionaryDetail ( PdfDictionary dic, int depth ) {
		StringBuilder builder = new StringBuilder ();
		builder.append ( '(' );
		List<PdfName> subDictionaries = new ArrayList<> ();
		for ( PdfName key : dic.getKeys () ) {
			PdfObject val = dic.getDirectObject ( key );
			if ( val.isDictionary () )
				subDictionaries.add ( key );
			builder.append ( key );
			builder.append ( '=' );
			builder.append ( val );
			builder.append ( ", " );
		}
		if ( builder.length () >= 2 )
			builder.setLength ( builder.length () - 2 );
		builder.append ( ')' );
		for ( PdfName pdfSubDictionaryName : subDictionaries ) {
			builder.append ( '\n' );
			for ( int i = 0; i < depth + 1; i++ ) {
				builder.append ( '\t' );
			}
			builder.append ( "Subdictionary " );
			builder.append ( pdfSubDictionaryName );
			builder.append ( " = " );
			builder.append ( getDictionaryDetail ( dic.getAsDict ( pdfSubDictionaryName ), depth + 1 ) );
		}
		return builder.toString ();
	}

	static public String getXObjectDetail ( PdfDictionary resourceDic ) throws IOException {
		StringBuilder sb = new StringBuilder ();

		PdfDictionary xobjects = resourceDic.getAsDict ( PdfName.XOBJECT );
		if ( xobjects == null )
			return "No XObjects";
		for ( PdfName entryName : xobjects.getKeys () ) {
			PdfStream xobjectStream = xobjects.getAsStream ( entryName );

			sb.append ( "------ " ).append ( entryName ).append ( " - subtype = " ).append ( xobjectStream.get ( PdfName.SUBTYPE ) ).append ( " = " )
							.append ( xobjectStream.getAsNumber (
											PdfName.LENGTH ) ).append ( " bytes ------\n" );

			if ( !xobjectStream.get ( PdfName.SUBTYPE ).equals ( PdfName.IMAGE ) ) {

				byte[] contentBytes = ContentByteUtils.getContentBytesFromContentObject ( xobjectStream );

				InputStream is = new ByteArrayInputStream ( contentBytes );
				int ch;
				while ( ( ch = is.read () ) != -1 ) {
					sb.append ( (char) ch );
				}

				sb.append ( "------ " ).append ( entryName ).append ( " - subtype = " ).append ( xobjectStream.get ( PdfName.SUBTYPE ) )
								.append ( "End of Content" ).append ( "------\n" );
			}
		}

		return sb.toString ();
	}

	static public void listContentStreamForPage ( PdfReader reader, int pageNum, PrintWriter out ) throws IOException {
		out.println ( "==============Page " + pageNum + "====================" );
		out.println ( "- - - - - Dictionary - - - - - -" );
		PdfDictionary pageDictionary = reader.getPageN ( pageNum );
		out.println ( getDictionaryDetail ( pageDictionary ) );

		out.println ( "- - - - - XObject Summary - - - - - -" );
		out.println ( getXObjectDetail ( pageDictionary.getAsDict ( PdfName.RESOURCES ) ) );

		out.println ( "- - - - - Content Stream - - - - - -" );
		RandomAccessFileOrArray f = reader.getSafeFile ();

		byte[] contentBytes = reader.getPageContent ( pageNum, f );
		f.close ();

		out.flush ();

		InputStream is = new ByteArrayInputStream ( contentBytes );
		int ch;
		while ( ( ch = is.read () ) != -1 ) {
			out.print ( (char) ch );
		}

		out.flush ();

		out.println ( "- - - - - Text Extraction - - - - - -" );
		String extractedText = PdfTextExtractor.getTextFromPage ( reader, pageNum, new LocationTextExtractionStrategy () );
		if ( !extractedText.isEmpty () )
			out.println ( extractedText );
		else
			out.println ( "No text found on page " + pageNum );

		out.println ();

	}

	static public void listContentStream ( File pdfFile, PrintWriter out ) throws IOException {
		PdfReader reader = new PdfReader ( pdfFile.getCanonicalPath () );

		int maxPageNum = reader.getNumberOfPages ();

		for ( int pageNum = 1; pageNum <= maxPageNum; pageNum++ ) {
			listContentStreamForPage ( reader, pageNum, out );
		}

	}

	static public void listContentStream ( File pdfFile, int pageNum, PrintWriter out ) throws IOException {
		PdfReader reader = new PdfReader ( pdfFile.getCanonicalPath () );

		listContentStreamForPage ( reader, pageNum, out );
	}

	public static void main ( String[] args ) {
		try {
			if ( args.length < 1 || args.length > 3 ) {
				System.out.println ( "Usage:  PdfContentReaderTool <pdf file> [<output file>|stdout] [<page num>]" );
				return;
			}

			PrintWriter writer = new PrintWriter ( System.out );
			if ( args.length >= 2 ) {
				if ( args[1].compareToIgnoreCase ( "stdout" ) != 0 ) {
					System.out.println ( "Writing PDF content to " + args[1] );
					writer = new PrintWriter ( Files.newOutputStream ( new File ( args[1] ).toPath () ) );
				}
			}

			int pageNum = -1;
			if ( args.length == 3 ) {
				pageNum = Integer.parseInt ( args[2] );
			}

			if ( pageNum == -1 ) {
				listContentStream ( new File ( args[0] ), writer );
			} else {
				listContentStream ( new File ( args[0] ), pageNum, writer );
			}
			writer.flush ();

			if ( args.length >= 2 ) {
				writer.close ();
				System.out.println ( "Finished writing content to " + args[1] );
			}
		} catch ( Exception e ) {
			e.printStackTrace ( System.err );
		}
	}

}

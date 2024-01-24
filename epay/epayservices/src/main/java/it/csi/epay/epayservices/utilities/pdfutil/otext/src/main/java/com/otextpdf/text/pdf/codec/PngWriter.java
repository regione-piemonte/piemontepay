/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;


public class PngWriter {

	private static final byte[] PNG_SIGNTURE = { (byte) 137, 80, 78, 71, 13, 10, 26, 10 };

	private static final byte[] IHDR = DocWriter.getISOBytes ( "IHDR" );

	private static final byte[] PLTE = DocWriter.getISOBytes ( "PLTE" );

	private static final byte[] IDAT = DocWriter.getISOBytes ( "IDAT" );

	private static final byte[] IEND = DocWriter.getISOBytes ( "IEND" );

	private static final byte[] iCCP = DocWriter.getISOBytes ( "iCCP" );

	private static int[] crc_table;

	private final OutputStream outp;

	public PngWriter ( OutputStream outp ) throws IOException {
		this.outp = outp;
		outp.write ( PNG_SIGNTURE );
	}

	private static void make_crc_table () {
		if ( crc_table != null )
			return;
		int[] crc2 = new int[256];
		for ( int n = 0; n < 256; n++ ) {
			int c = n;
			for ( int k = 0; k < 8; k++ ) {
				if ( ( c & 1 ) != 0 )
					c = 0xedb88320 ^ ( c >>> 1 );
				else
					c = c >>> 1;
			}
			crc2[n] = c;
		}
		crc_table = crc2;
	}

	private static int update_crc ( int crc, byte[] buf, int len ) {
		int c = crc;

		if ( crc_table == null )
			make_crc_table ();
		for ( int n = 0; n < len; n++ ) {
			c = crc_table[( c ^ buf[n] ) & 0xff] ^ ( c >>> 8 );
		}
		return c;
	}

	public static void outputInt ( int n, OutputStream s ) throws IOException {
		s.write ( (byte) ( n >> 24 ) );
		s.write ( (byte) ( n >> 16 ) );
		s.write ( (byte) ( n >> 8 ) );
		s.write ( (byte) n );
	}

	public void writeHeader ( int width, int height, int bitDepth, int colorType ) throws IOException {
		ByteArrayOutputStream ms = new ByteArrayOutputStream ();
		outputInt ( width, ms );
		outputInt ( height, ms );
		ms.write ( bitDepth );
		ms.write ( colorType );
		ms.write ( 0 );
		ms.write ( 0 );
		ms.write ( 0 );
		writeChunk ( IHDR, ms.toByteArray () );
	}

	public void writeEnd () throws IOException {
		writeChunk ( IEND, new byte[0] );
	}

	public void writeData ( byte[] data, final int stride ) throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream ();
		DeflaterOutputStream zip = new DeflaterOutputStream ( stream );
		int k;
		for ( k = 0; k < data.length - stride; k += stride ) {
			zip.write ( 0 );
			zip.write ( data, k, stride );
		}
		int remaining = data.length - k;
		if ( remaining > 0 ) {
			zip.write ( 0 );
			zip.write ( data, k, remaining );
		}
		zip.close ();
		writeChunk ( IDAT, stream.toByteArray () );
	}

	public void writePalette ( byte[] data ) throws IOException {
		writeChunk ( PLTE, data );
	}

	public void writeIccProfile ( byte[] data ) throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream ();
		stream.write ( (byte) 'I' );
		stream.write ( (byte) 'C' );
		stream.write ( (byte) 'C' );
		stream.write ( 0 );
		stream.write ( 0 );
		DeflaterOutputStream zip = new DeflaterOutputStream ( stream );
		zip.write ( data );
		zip.close ();
		writeChunk ( iCCP, stream.toByteArray () );
	}

	public void outputInt ( int n ) throws IOException {
		outputInt ( n, outp );
	}

	public void writeChunk ( byte[] chunkType, byte[] data ) throws IOException {
		outputInt ( data.length );
		outp.write ( chunkType, 0, 4 );
		outp.write ( data );
		int c = update_crc ( 0xffffffff, chunkType, chunkType.length );
		c = ~update_crc ( c, data, data.length );
		outputInt ( c );
	}
}

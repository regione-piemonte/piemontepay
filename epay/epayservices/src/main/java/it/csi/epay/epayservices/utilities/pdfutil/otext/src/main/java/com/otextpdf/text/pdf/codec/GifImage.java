/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ImgRaw;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfArray;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfDictionary;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfNumber;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfString;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


public class GifImage {

	protected static final int MaxStackSize = 4096;   // max decoder pixel stack size

	protected DataInputStream in;

	protected int width;            // full image width

	protected int height;           // full image height

	protected boolean gctFlag;      // global color table used

	protected int bgIndex;          // background color index

	protected int bgColor;          // background color

	protected int pixelAspect;      // pixel aspect ratio

	protected boolean lctFlag;      // local color table flag

	protected boolean interlace;    // interlace flag

	protected int lctSize;          // local color table size

	protected int ix, iy, iw, ih;   // current image rectangle

	protected byte[] block = new byte[256];  // current data block

	protected int blockSize = 0;    // block size

	// last graphic control extension info
	protected int dispose = 0;   // 0=no action; 1=leave in place; 2=restore to bg; 3=restore to prev

	protected boolean transparency = false;   // use transparent color

	protected int delay = 0;        // delay in milliseconds

	protected int transIndex;       // transparent color index

	// LZW decoder working arrays
	protected short[] prefix;

	protected byte[] suffix;

	protected byte[] pixelStack;

	protected byte[] pixels;

	protected byte[] m_out;

	protected int m_bpc;

	protected int m_gbpc;

	protected byte[] m_global_table;

	protected byte[] m_local_table;

	protected byte[] m_curr_table;

	protected int m_line_stride;

	protected byte[] fromData;

	protected URL fromUrl;

	protected ArrayList<GifFrame> frames = new ArrayList<> ();     // frames read from current file

	public GifImage ( URL url ) throws IOException {
		fromUrl = url;
		InputStream is = null;
		try {
			is = url.openStream ();

			ByteArrayOutputStream baos = new ByteArrayOutputStream ();
			int read;
			byte[] bytes = new byte[1024];

			while ( ( read = is.read ( bytes ) ) != -1 ) {
				baos.write ( bytes, 0, read );
			}

			is = new ByteArrayInputStream ( baos.toByteArray () );
			baos.flush ();
			baos.close ();

			process ( is );
		} finally {
			if ( is != null ) {
				is.close ();
			}
		}
	}

	public GifImage ( byte[] data ) throws IOException {
		fromData = data;
		try ( InputStream is = new ByteArrayInputStream ( data ) ) {
			process ( is );
		}
	}

	static protected int newBpc ( int bpc ) {
		switch ( bpc ) {
		case 1:
		case 2:
		case 4:
			break;
		case 3:
			return 4;
		default:
			return 8;
		}
		return bpc;
	}

	public Image getImage ( int frame ) {
		GifFrame gf = frames.get ( frame - 1 );
		return gf.image;
	}

	void process ( InputStream is ) throws IOException {
		in = new DataInputStream ( new BufferedInputStream ( is ) );
		readHeader ();
		readContents ();
		if ( frames.isEmpty () )
			throw new IOException ( MessageLocalization.getComposedMessage ( "the.file.does.not.contain.any.valid.image" ) );
	}

	protected void readHeader () throws IOException {
		StringBuilder id = new StringBuilder ();
		for ( int i = 0; i < 6; i++ )
			id.append ( (char) in.read () );
		if ( !id.toString ().startsWith ( "GIF8" ) ) {
			throw new IOException ( MessageLocalization.getComposedMessage ( "gif.signature.nor.found" ) );
		}

		readLSD ();
		if ( gctFlag ) {
			m_global_table = readColorTable ( m_gbpc );
		}
	}

	protected void readLSD () throws IOException {

		// logical screen size
		width = readShort ();
		height = readShort ();

		// packed fields
		int packed = in.read ();
		gctFlag = ( packed & 0x80 ) != 0;      // 1   : global color table flag
		m_gbpc = ( packed & 7 ) + 1;
		bgIndex = in.read ();        // background color index
		pixelAspect = in.read ();    // pixel aspect ratio
	}

	protected int readShort () throws IOException {
		// read 16-bit value, LSB first
		return in.read () | in.read () << 8;
	}

	protected int readBlock () throws IOException {
		blockSize = in.read ();
		if ( blockSize <= 0 )
			return blockSize = 0;

		blockSize = in.read ( block, 0, blockSize );

		return blockSize;
	}

	protected byte[] readColorTable ( int bpc ) throws IOException {
		int ncolors = 1 << bpc;
		int nbytes = 3 * ncolors;
		bpc = newBpc ( bpc );
		byte[] table = new byte[( 1 << bpc ) * 3];
		in.readFully ( table, 0, nbytes );
		return table;
	}

	protected void readContents () throws IOException {
		// read GIF file content blocks
		boolean done = false;
		while ( !done ) {
			int code = in.read ();
			switch ( code ) {

			case 0x2C:    // image separator
				readImage ();
				break;

			case 0x21:    // extension
				code = in.read ();
				switch ( code ) {

				case 0xf9:    // graphics control extension
					readGraphicControlExt ();
					break;

				case 0xff:    // application extension
					readBlock ();
					skip ();        // don't care
					break;

				default:    // uninteresting extension
					skip ();
				}
				break;

			default:
				done = true;
				break;
			}
		}
	}

	protected void readImage () throws IOException {
		ix = readShort ();    // (sub)image position & size
		iy = readShort ();
		iw = readShort ();
		ih = readShort ();

		int packed = in.read ();
		lctFlag = ( packed & 0x80 ) != 0;     // 1 - local color table flag
		interlace = ( packed & 0x40 ) != 0;   // 2 - interlace flag
		// 3 - sort flag
		// 4-5 - reserved
		lctSize = 2 << ( packed & 7 );        // 6-8 - local color table size
		m_bpc = newBpc ( m_gbpc );
		if ( lctFlag ) {
			m_curr_table = readColorTable ( ( packed & 7 ) + 1 );   // read table
			m_bpc = newBpc ( ( packed & 7 ) + 1 );
		} else {
			m_curr_table = m_global_table;
		}
		if ( transparency && transIndex >= m_curr_table.length / 3 )
			transparency = false;
		if ( transparency && m_bpc == 1 ) { // Acrobat 5.05 doesn't like this combination
			byte[] tp = new byte[12];
			System.arraycopy ( m_curr_table, 0, tp, 0, 6 );
			m_curr_table = tp;
			m_bpc = 2;
		}
		boolean skipZero = decodeImageData ();   // decode pixel data
		if ( !skipZero )
			skip ();

		Image img;
		try {
			img = new ImgRaw ( iw, ih, 1, m_bpc, m_out );
			PdfArray colorspace = new PdfArray ();
			colorspace.add ( PdfName.INDEXED );
			colorspace.add ( PdfName.DEVICERGB );
			int len = m_curr_table.length;
			colorspace.add ( new PdfNumber ( len / 3 - 1 ) );
			colorspace.add ( new PdfString ( m_curr_table ) );
			PdfDictionary ad = new PdfDictionary ();
			ad.put ( PdfName.COLORSPACE, colorspace );
			img.setAdditional ( ad );
			if ( transparency ) {
				img.setTransparency ( new int[] { transIndex, transIndex } );
			}
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
		img.setOriginalType ( Image.ORIGINAL_GIF );
		img.setOriginalData ( fromData );
		img.setUrl ( fromUrl );
		GifFrame gf = new GifFrame ();
		gf.image = img;
		gf.ix = ix;
		gf.iy = iy;
		frames.add ( gf );   // add image to frame list

		//resetFrame();

	}

	protected boolean decodeImageData () throws IOException {
		int NullCode = -1;
		int npix = iw * ih;
		int available, clear, code_mask, code_size, end_of_information, in_code, old_code,
						bits, code, count, i, datum, data_size, first, top, bi;
		boolean skipZero = false;

		if ( prefix == null )
			prefix = new short[MaxStackSize];
		if ( suffix == null )
			suffix = new byte[MaxStackSize];
		if ( pixelStack == null )
			pixelStack = new byte[MaxStackSize + 1];

		m_line_stride = ( iw * m_bpc + 7 ) / 8;
		m_out = new byte[m_line_stride * ih];
		int pass = 1;
		int inc = interlace ? 8 : 1;
		int line = 0;
		int xpos = 0;

		//  Initialize GIF data stream decoder.

		data_size = in.read ();
		clear = 1 << data_size;
		end_of_information = clear + 1;
		available = clear + 2;
		old_code = NullCode;
		code_size = data_size + 1;
		code_mask = ( 1 << code_size ) - 1;
		for ( code = 0; code < clear; code++ ) {
			prefix[code] = 0;
			suffix[code] = (byte) code;
		}

		//  Decode GIF pixel stream.

		datum = bits = count = first = top = bi = 0;

		for ( i = 0; i < npix; ) {
			if ( top == 0 ) {
				if ( bits < code_size ) {
					//  Load bytes until there are enough bits for a code.
					if ( count == 0 ) {
						// Read a new data block.
						count = readBlock ();
						if ( count <= 0 ) {
							skipZero = true;
							break;
						}
						bi = 0;
					}
					datum += ( block[bi] & 0xff ) << bits;
					bits += 8;
					bi++;
					count--;
					continue;
				}

				//  Get the next code.

				code = datum & code_mask;
				datum >>= code_size;
				bits -= code_size;

				//  Interpret the code

				if ( code > available || code == end_of_information )
					break;
				if ( code == clear ) {
					//  Reset decoder.
					code_size = data_size + 1;
					code_mask = ( 1 << code_size ) - 1;
					available = clear + 2;
					old_code = NullCode;
					continue;
				}
				if ( old_code == NullCode ) {
					pixelStack[top++] = suffix[code];
					old_code = code;
					first = code;
					continue;
				}
				in_code = code;
				if ( code == available ) {
					pixelStack[top++] = (byte) first;
					code = old_code;
				}
				while ( code > clear ) {
					pixelStack[top++] = suffix[code];
					code = prefix[code];
				}
				first = suffix[code] & 0xff;

				//  Add a new string to the string table,

				if ( available >= MaxStackSize )
					break;
				pixelStack[top++] = (byte) first;
				prefix[available] = (short) old_code;
				suffix[available] = (byte) first;
				available++;
				if ( ( available & code_mask ) == 0 && available < MaxStackSize ) {
					code_size++;
					code_mask += available;
				}
				old_code = in_code;
			}

			//  Pop a pixel off the pixel stack.

			top--;
			i++;

			setPixel ( xpos, line, pixelStack[top] );
			++xpos;
			if ( xpos >= iw ) {
				xpos = 0;
				line += inc;
				if ( line >= ih ) {
					if ( interlace ) {
						do {
							pass++;
							switch ( pass ) {
							case 2:
								line = 4;
								break;
							case 3:
								line = 2;
								inc = 4;
								break;
							case 4:
								line = 1;
								inc = 2;
								break;
							default: // this shouldn't happen
								line = ih - 1;
								inc = 0;
							}
						} while ( line >= ih );
					} else {
						line = ih - 1; // this shouldn't happen
						inc = 0;
					}
				}
			}
		}
		return skipZero;
	}

	protected void setPixel ( int x, int y, int v ) {
		if ( m_bpc == 8 ) {
			int pos = x + iw * y;
			m_out[pos] = (byte) v;
		} else {
			int pos = m_line_stride * y + x / ( 8 / m_bpc );
			int vout = v << 8 - m_bpc * ( x % ( 8 / m_bpc ) ) - m_bpc;
			m_out[pos] |= (byte) vout;
		}
	}

	protected void readGraphicControlExt () throws IOException {
		in.read ();    // block size
		int packed = in.read ();   // packed fields
		dispose = ( packed & 0x1c ) >> 2;   // disposal method
		if ( dispose == 0 )
			dispose = 1;   // elect to keep old image if discretionary
		transparency = ( packed & 1 ) != 0;
		delay = readShort () * 10;   // delay in milliseconds
		transIndex = in.read ();        // transparent color index
		in.read ();                     // block terminator
	}

	protected void skip () throws IOException {
		do {
			readBlock ();
		} while ( blockSize > 0 );
	}

	static class GifFrame {

		Image image;

		int ix;

		int iy;
	}
}

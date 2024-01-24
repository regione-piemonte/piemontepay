/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


public class Jpeg2000 extends Image {

	public static final int JP2_JP = 0x6a502020;

	public static final int JP2_IHDR = 0x69686472;

	public static final int JPIP_JPIP = 0x6a706970;

	public static final int JP2_FTYP = 0x66747970;

	public static final int JP2_JP2H = 0x6a703268;

	public static final int JP2_COLR = 0x636f6c72;

	public static final int JP2_JP2C = 0x6a703263;

	public static final int JP2_URL = 0x75726c20;

	public static final int JP2_DBTL = 0x6474626c;

	public static final int JP2_BPCC = 0x62706363;

	public static final int JP2_JP2 = 0x6a703220;

	InputStream inp;

	int boxLength;

	int boxType;

	int numOfComps;

	ArrayList<ColorSpecBox> colorSpecBoxes = null;

	boolean isJp2 = false;

	byte[] bpcBoxData;

	public Jpeg2000 ( URL url ) throws IOException {
		super ( url );
		processParameters ();
	}

	public Jpeg2000 ( byte[] img ) throws IOException {
		super ( (URL) null );
		rawData = img;
		originalData = img;
		processParameters ();
	}

	public Jpeg2000 ( byte[] img, float width, float height ) throws IOException {
		this ( img );
		scaledWidth = width;
		scaledHeight = height;
	}

	private int cio_read ( int n ) throws IOException {
		int v = 0;
		for ( int i = n - 1; i >= 0; i-- ) {
			v += inp.read () << ( i << 3 );
		}
		return v;
	}

	public void jp2_read_boxhdr () throws IOException {
		boxLength = cio_read ( 4 );
		boxType = cio_read ( 4 );
		if ( boxLength == 1 ) {
			if ( cio_read ( 4 ) != 0 ) {
				throw new IOException ( MessageLocalization.getComposedMessage ( "cannot.handle.box.sizes.higher.than.2.32" ) );
			}
			boxLength = cio_read ( 4 );
			if ( boxLength == 0 )
				throw new IOException ( MessageLocalization.getComposedMessage ( "unsupported.box.size.eq.eq.0" ) );
		} else if ( boxLength == 0 ) {
			throw new ZeroBoxSizeException ( MessageLocalization.getComposedMessage ( "unsupported.box.size.eq.eq.0" ) );
		}
	}

	private void processParameters () throws IOException {
		type = JPEG2000;
		originalType = ORIGINAL_JPEG2000;
		inp = null;
		try {
			if ( rawData == null ) {
				inp = url.openStream ();
			} else {
				inp = new java.io.ByteArrayInputStream ( rawData );
			}
			boxLength = cio_read ( 4 );
			if ( boxLength == 0x0000000c ) {
				isJp2 = true;
				boxType = cio_read ( 4 );
				if ( JP2_JP != boxType ) {
					throw new IOException ( MessageLocalization.getComposedMessage ( "expected.jp.marker" ) );
				}
				if ( 0x0d0a870a != cio_read ( 4 ) ) {
					throw new IOException ( MessageLocalization.getComposedMessage ( "error.with.jp.marker" ) );
				}

				jp2_read_boxhdr ();
				if ( JP2_FTYP != boxType ) {
					throw new IOException ( MessageLocalization.getComposedMessage ( "expected.ftyp.marker" ) );
				}
				Utilities.skip ( inp, boxLength - 8 );
				jp2_read_boxhdr ();
				do {
					if ( JP2_JP2H != boxType ) {
						if ( boxType == JP2_JP2C ) {
							throw new IOException ( MessageLocalization.getComposedMessage ( "expected.jp2h.marker" ) );
						}
						Utilities.skip ( inp, boxLength - 8 );
						jp2_read_boxhdr ();
					}
				} while ( JP2_JP2H != boxType );
				jp2_read_boxhdr ();
				if ( JP2_IHDR != boxType ) {
					throw new IOException ( MessageLocalization.getComposedMessage ( "expected.ihdr.marker" ) );
				}
				scaledHeight = cio_read ( 4 );
				setTop ( scaledHeight );
				scaledWidth = cio_read ( 4 );
				setRight ( scaledWidth );
				numOfComps = cio_read ( 2 );
				bpc = -1;
				bpc = cio_read ( 1 );

				Utilities.skip ( inp, 3 );

				jp2_read_boxhdr ();
				if ( boxType == JP2_BPCC ) {
					bpcBoxData = new byte[boxLength - 8];
					inp.read ( bpcBoxData, 0, boxLength - 8 );
				} else if ( boxType == JP2_COLR ) {
					do {
						if ( colorSpecBoxes == null )
							colorSpecBoxes = new ArrayList<> ();
						colorSpecBoxes.add ( jp2_read_colr () );
						try {
							jp2_read_boxhdr ();
						} catch ( ZeroBoxSizeException ignored ) {
						}
					} while ( JP2_COLR == boxType );
				}
			} else if ( boxLength == 0xff4fff51 ) {
				Utilities.skip ( inp, 4 );
				int x1 = cio_read ( 4 );
				int y1 = cio_read ( 4 );
				int x0 = cio_read ( 4 );
				int y0 = cio_read ( 4 );
				Utilities.skip ( inp, 16 );
				colorspace = cio_read ( 2 );
				bpc = 8;
				scaledHeight = y1 - y0;
				setTop ( scaledHeight );
				scaledWidth = x1 - x0;
				setRight ( scaledWidth );
			} else {
				throw new IOException ( MessageLocalization.getComposedMessage ( "not.a.valid.jpeg2000.file" ) );
			}
		} finally {
			if ( inp != null ) {
				try {
					inp.close ();
				} catch ( Exception ignored ) {
				}
				inp = null;
			}
		}
		plainWidth = getWidth ();
		plainHeight = getHeight ();
	}

	private ColorSpecBox jp2_read_colr () throws IOException {
		int readBytes = 8;
		ColorSpecBox colr = new ColorSpecBox ();
		for ( int i = 0; i < 3; i++ ) {
			colr.add ( cio_read ( 1 ) );
			readBytes++;
		}
		if ( colr.getMeth () == 1 ) {
			colr.add ( cio_read ( 4 ) );
			readBytes += 4;
		} else {
			colr.add ( 0 );
		}

		if ( boxLength - readBytes > 0 ) {
			byte[] colorProfile = new byte[boxLength - readBytes];
			inp.read ( colorProfile, 0, boxLength - readBytes );
			colr.setColorProfile ();
		}
		return colr;
	}

	public boolean isJp2 () {
		return isJp2;
	}

	public static class ColorSpecBox extends ArrayList<Integer> {

		private static final long serialVersionUID = -1988196180625572129L;

		public int getMeth () {
			return get ( 0 );
		}

		void setColorProfile () {
		}
	}


	private static class ZeroBoxSizeException extends IOException {

		private static final long serialVersionUID = 8393675720246236484L;

		public ZeroBoxSizeException ( String s ) {
			super ( s );
		}
	}

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.ICC_Profile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class Jpeg extends Image {

	public static final int NOT_A_MARKER = -1;

	public static final int VALID_MARKER = 0;

	public static final int[] VALID_MARKERS = { 0xC0, 0xC1, 0xC2 };

	public static final int UNSUPPORTED_MARKER = 1;

	public static final int[] UNSUPPORTED_MARKERS = { 0xC3, 0xC5, 0xC6, 0xC7, 0xC8, 0xC9, 0xCA, 0xCB, 0xCD, 0xCE, 0xCF };

	public static final int NOPARAM_MARKER = 2;

	public static final int[] NOPARAM_MARKERS = { 0xD0, 0xD1, 0xD2, 0xD3, 0xD4, 0xD5, 0xD6, 0xD7, 0xD8, 0x01 };

	public static final int M_APP0 = 0xE0;

	public static final int M_APP2 = 0xE2;

	public static final int M_APPE = 0xEE;

	public static final int M_APPD = 0xED;

	public static final byte[] JFIF_ID = { 0x4A, 0x46, 0x49, 0x46, 0x00 };

	public static final byte[] PS_8BIM_RESO = { 0x38, 0x42, 0x49, 0x4d, 0x03, (byte) 0xed };

	private byte[][] icc;

	public Jpeg ( URL url ) throws BadElementException, IOException {
		super ( url );
		processParameters ();
	}

	public Jpeg ( byte[] img ) throws BadElementException, IOException {
		super ( (URL) null );
		rawData = img;
		originalData = img;
		processParameters ();
	}

	private static int getShort ( InputStream is ) throws IOException {
		return ( is.read () << 8 ) + is.read ();
	}

	private static int marker ( int marker ) {
		for ( int validMarker : VALID_MARKERS ) {
			if ( marker == validMarker ) {
				return VALID_MARKER;
			}
		}
		for ( int noparamMarker : NOPARAM_MARKERS ) {
			if ( marker == noparamMarker ) {
				return NOPARAM_MARKER;
			}
		}
		for ( int unsupportedMarker : UNSUPPORTED_MARKERS ) {
			if ( marker == unsupportedMarker ) {
				return UNSUPPORTED_MARKER;
			}
		}
		return NOT_A_MARKER;
	}

	private void processParameters () throws BadElementException, IOException {
		type = JPEG;
		originalType = ORIGINAL_JPEG;
		InputStream is = null;
		try {
			String errorID;
			if ( rawData == null ) {
				is = url.openStream ();
				errorID = url.toString ();
			} else {
				is = new java.io.ByteArrayInputStream ( rawData );
				errorID = "Byte array";
			}
			if ( is.read () != 0xFF || is.read () != 0xD8 ) {
				throw new BadElementException ( MessageLocalization.getComposedMessage ( "1.is.not.a.valid.jpeg.file", errorID ) );
			}
			boolean firstPass = true;
			int len;
			while ( true ) {
				int v = is.read ();
				if ( v < 0 )
					throw new IOException ( MessageLocalization.getComposedMessage ( "premature.eof.while.reading.jpg" ) );
				if ( v == 0xFF ) {
					int marker = is.read ();
					if ( firstPass && marker == M_APP0 ) {
						firstPass = false;
						len = getShort ( is );
						if ( len < 16 ) {
							Utilities.skip ( is, len - 2 );
							continue;
						}
						byte[] bcomp = new byte[JFIF_ID.length];
						int r = is.read ( bcomp );
						if ( r != bcomp.length )
							throw new BadElementException ( MessageLocalization.getComposedMessage ( "1.corrupted.jfif.marker", errorID ) );
						boolean found = true;
						for ( int k = 0; k < bcomp.length; ++k ) {
							if ( bcomp[k] != JFIF_ID[k] ) {
								found = false;
								break;
							}
						}
						if ( !found ) {
							Utilities.skip ( is, len - 2 - bcomp.length );
							continue;
						}
						Utilities.skip ( is, 2 );
						int units = is.read ();
						int dx = getShort ( is );
						int dy = getShort ( is );
						if ( units == 1 ) {
							dpiX = dx;
							dpiY = dy;
						} else if ( units == 2 ) {
							dpiX = (int) ( dx * 2.54f + 0.5f );
							dpiY = (int) ( dy * 2.54f + 0.5f );
						}
						Utilities.skip ( is, len - 2 - bcomp.length - 7 );
						continue;
					}
					if ( marker == M_APPE ) {
						len = getShort ( is ) - 2;
						byte[] byteappe = new byte[len];
						for ( int k = 0; k < len; ++k ) {
							byteappe[k] = (byte) is.read ();
						}
						if ( byteappe.length >= 12 ) {
							String appe = new String ( byteappe, 0, 5, StandardCharsets.ISO_8859_1 );
							if ( appe.equals ( "Adobe" ) ) {
								invert = true;
							}
						}
						continue;
					}
					if ( marker == M_APP2 ) {
						len = getShort ( is ) - 2;
						byte[] byteapp2 = new byte[len];
						for ( int k = 0; k < len; ++k ) {
							byteapp2[k] = (byte) is.read ();
						}
						if ( byteapp2.length >= 14 ) {
							String app2 = new String ( byteapp2, 0, 11, StandardCharsets.ISO_8859_1 );
							if ( app2.equals ( "ICC_PROFILE" ) ) {
								int order = byteapp2[12] & 0xff;
								int count = byteapp2[13] & 0xff;
								// some jpeg producers don't know how to count to 1
								if ( order < 1 )
									order = 1;
								if ( count < 1 )
									count = 1;
								if ( icc == null )
									icc = new byte[count][];
								icc[order - 1] = byteapp2;
							}
						}
						continue;
					}
					if ( marker == M_APPD ) {
						len = getShort ( is ) - 2;
						byte[] byteappd = new byte[len];
						for ( int k = 0; k < len; k++ ) {
							byteappd[k] = (byte) is.read ();
						}
						int k;
						for ( k = 0; k < len - PS_8BIM_RESO.length; k++ ) {
							boolean found = true;
							for ( int j = 0; j < PS_8BIM_RESO.length; j++ ) {
								if ( byteappd[k + j] != PS_8BIM_RESO[j] ) {
									found = false;
									break;
								}
							}
							if ( found )
								break;
						}

						k += PS_8BIM_RESO.length;
						if ( k < len - PS_8BIM_RESO.length ) {
							byte namelength = byteappd[k];
							namelength++;
							if ( namelength % 2 == 1 )
								namelength++;
							k += namelength;
							int resosize = ( byteappd[k] << 24 ) + ( byteappd[k + 1] << 16 ) + ( byteappd[k + 2] << 8 ) + byteappd[k + 3];
							if ( resosize != 16 ) {
								continue;
							}
							k += 4;
							int dx = ( byteappd[k] << 8 ) + ( byteappd[k + 1] & 0xff );
							k += 2;
							k += 2;
							int unitsx = ( byteappd[k] << 8 ) + ( byteappd[k + 1] & 0xff );
							k += 2;
							k += 2;
							int dy = ( byteappd[k] << 8 ) + ( byteappd[k + 1] & 0xff );
							k += 2;
							k += 2;
							int unitsy = ( byteappd[k] << 8 ) + ( byteappd[k + 1] & 0xff );

							if ( unitsx == 1 || unitsx == 2 ) {
								dx = ( unitsx == 2 ? (int) ( dx * 2.54f + 0.5f ) : dx );
								if ( dpiX != 0 && dpiX != dx ) {
								} else
									dpiX = dx;
							}
							if ( unitsy == 1 || unitsy == 2 ) {
								dy = ( unitsy == 2 ? (int) ( dy * 2.54f + 0.5f ) : dy );
								if ( dpiY != 0 && dpiY != dy ) {
								} else
									dpiY = dy;
							}
						}
						continue;
					}
					firstPass = false;
					int markertype = marker ( marker );
					if ( markertype == VALID_MARKER ) {
						Utilities.skip ( is, 2 );
						if ( is.read () != 0x08 ) {
							throw new BadElementException ( MessageLocalization.getComposedMessage ( "1.must.have.8.bits.per.component", errorID ) );
						}
						scaledHeight = getShort ( is );
						setTop ( scaledHeight );
						scaledWidth = getShort ( is );
						setRight ( scaledWidth );
						colorspace = is.read ();
						bpc = 8;
						break;
					} else if ( markertype == UNSUPPORTED_MARKER ) {
						throw new BadElementException (
										MessageLocalization.getComposedMessage ( "1.unsupported.jpeg.marker.2", errorID, String.valueOf ( marker ) ) );
					} else if ( markertype != NOPARAM_MARKER ) {
						Utilities.skip ( is, getShort ( is ) - 2 );
					}
				}
			}
		} finally {
			if ( is != null ) {
				is.close ();
			}
		}
		plainWidth = getWidth ();
		plainHeight = getHeight ();
		if ( icc != null ) {
			int total = 0;
			for ( int k = 0; k < icc.length; ++k ) {
				if ( icc[k] == null ) {
					icc = null;
					return;
				}
				total += icc[k].length - 14;
			}
			byte[] ficc = new byte[total];
			total = 0;
			for ( byte[] bytes : icc ) {
				System.arraycopy ( bytes, 14, ficc, total, bytes.length - 14 );
				total += bytes.length - 14;
			}
			try {
				ICC_Profile icc_prof = ICC_Profile.getInstance ( ficc, colorspace );
				tagICC ( icc_prof );
			} catch ( IllegalArgumentException ignored ) {
			}
			icc = null;
		}
	}
}

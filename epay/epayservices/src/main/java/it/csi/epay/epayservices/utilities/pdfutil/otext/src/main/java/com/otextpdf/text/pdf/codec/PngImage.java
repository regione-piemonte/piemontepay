/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ImgRaw;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Utilities;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.ByteBuffer;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.ICC_Profile;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfArray;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfDictionary;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfLiteral;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfNumber;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfReader;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfString;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;


public class PngImage {

	public static final int[] PNGID = { 137, 80, 78, 71, 13, 10, 26, 10 };

	public static final String IHDR = "IHDR";

	public static final String PLTE = "PLTE";

	public static final String IDAT = "IDAT";

	public static final String IEND = "IEND";

	public static final String tRNS = "tRNS";

	public static final String pHYs = "pHYs";

	public static final String gAMA = "gAMA";

	public static final String cHRM = "cHRM";

	public static final String sRGB = "sRGB";

	public static final String iCCP = "iCCP";

	private static final int TRANSFERSIZE = 4096;

	private static final int PNG_FILTER_NONE = 0;

	private static final int PNG_FILTER_SUB = 1;

	private static final int PNG_FILTER_UP = 2;

	private static final int PNG_FILTER_AVERAGE = 3;

	private static final int PNG_FILTER_PAETH = 4;

	private static final PdfName[] intents = { PdfName.PERCEPTUAL,
					PdfName.RELATIVECOLORIMETRIC, PdfName.SATURATION, PdfName.ABSOLUTECOLORIMETRIC };

	InputStream is;

	DataInputStream dataStream;

	int width;

	int height;

	int bitDepth;

	int colorType;

	int compressionMethod;

	int filterMethod;

	int interlaceMethod;

	PdfDictionary additional = new PdfDictionary ();

	byte[] image;

	byte[] smask;

	byte[] trans;

	NewByteArrayOutputStream idat = new NewByteArrayOutputStream ();

	int dpiX;

	int dpiY;

	float XYRatio;

	boolean genBWMask;

	boolean palShades;

	int transRedGray = -1;

	int transGreen = -1;

	int transBlue = -1;

	int inputBands;

	int bytesPerPixel;

	byte[] colorTable;

	float gamma = 1f;

	boolean hasCHRM = false;

	float xW, yW, xR, yR, xG, yG, xB, yB;

	PdfName intent;

	ICC_Profile icc_profile;

	PngImage ( InputStream is ) {
		this.is = is;
	}

	public static Image getImage ( URL url ) throws IOException {
		try ( InputStream is = url.openStream () ) {
			Image img = getImage ( is );
			img.setUrl ( url );
			return img;
		}
	}

	public static Image getImage ( InputStream is ) throws IOException {
		PngImage png = new PngImage ( is );
		return png.getImage ();
	}

	public static Image getImage ( String file ) throws IOException {
		return getImage ( Utilities.toURL ( file ) );
	}

	public static Image getImage ( byte[] data ) throws IOException {
		ByteArrayInputStream is = new ByteArrayInputStream ( data );
		Image img = getImage ( is );
		img.setOriginalData ( data );
		return img;
	}

	static void setPixel ( byte[] image, int[] data, int offset, int size, int x, int y, int bitDepth, int bytesPerRow ) {
		if ( bitDepth == 8 ) {
			int pos = bytesPerRow * y + size * x;
			for ( int k = 0; k < size; ++k )
				image[pos + k] = (byte) data[k + offset];
		} else if ( bitDepth == 16 ) {
			int pos = bytesPerRow * y + size * x;
			for ( int k = 0; k < size; ++k )
				image[pos + k] = (byte) ( data[k + offset] >>> 8 );
		} else {
			int pos = bytesPerRow * y + x / ( 8 / bitDepth );
			int v = data[offset] << ( 8 - bitDepth * ( x % ( 8 / bitDepth ) ) - bitDepth );
			image[pos] |= (byte) v;
		}
	}

	private static void decodeSubFilter ( byte[] curr, int count, int bpp ) {
		for ( int i = bpp; i < count; i++ ) {
			int val;

			val = curr[i] & 0xff;
			val += curr[i - bpp] & 0xff;

			curr[i] = (byte) val;
		}
	}

	private static void decodeUpFilter ( byte[] curr, byte[] prev,
					int count ) {
		for ( int i = 0; i < count; i++ ) {
			int raw = curr[i] & 0xff;
			int prior = prev[i] & 0xff;

			curr[i] = (byte) ( raw + prior );
		}
	}

	private static void decodeAverageFilter ( byte[] curr, byte[] prev,
					int count, int bpp ) {
		int raw, priorPixel, priorRow;

		for ( int i = 0; i < bpp; i++ ) {
			raw = curr[i] & 0xff;
			priorRow = prev[i] & 0xff;

			curr[i] = (byte) ( raw + priorRow / 2 );
		}

		for ( int i = bpp; i < count; i++ ) {
			raw = curr[i] & 0xff;
			priorPixel = curr[i - bpp] & 0xff;
			priorRow = prev[i] & 0xff;

			curr[i] = (byte) ( raw + ( priorPixel + priorRow ) / 2 );
		}
	}

	private static int paethPredictor ( int a, int b, int c ) {
		int p = a + b - c;
		int pa = Math.abs ( p - a );
		int pb = Math.abs ( p - b );
		int pc = Math.abs ( p - c );

		if ( ( pa <= pb ) && ( pa <= pc ) ) {
			return a;
		} else if ( pb <= pc ) {
			return b;
		} else {
			return c;
		}
	}

	private static void decodePaethFilter ( byte[] curr, byte[] prev,
					int count, int bpp ) {
		int raw, priorPixel, priorRow, priorRowPixel;

		for ( int i = 0; i < bpp; i++ ) {
			raw = curr[i] & 0xff;
			priorRow = prev[i] & 0xff;

			curr[i] = (byte) ( raw + priorRow );
		}

		for ( int i = bpp; i < count; i++ ) {
			raw = curr[i] & 0xff;
			priorPixel = curr[i - bpp] & 0xff;
			priorRow = prev[i] & 0xff;
			priorRowPixel = prev[i - bpp] & 0xff;

			curr[i] = (byte) ( raw + paethPredictor ( priorPixel,
							priorRow,
							priorRowPixel ) );
		}
	}

	public static int getInt ( InputStream is ) throws IOException {
		return ( is.read () << 24 ) + ( is.read () << 16 ) + ( is.read () << 8 ) + is.read ();
	}

	public static int getWord ( InputStream is ) throws IOException {
		return ( is.read () << 8 ) + is.read ();
	}

	public static String getString ( InputStream is ) throws IOException {
		StringBuilder buf = new StringBuilder ();
		for ( int i = 0; i < 4; i++ ) {
			buf.append ( (char) is.read () );
		}
		return buf.toString ();
	}

	boolean checkMarker ( String s ) {
		if ( s.length () != 4 )
			return false;
		for ( int k = 0; k < 4; ++k ) {
			char c = s.charAt ( k );
			if ( ( c < 'a' || c > 'z' ) && ( c < 'A' || c > 'Z' ) )
				return false;
		}
		return true;
	}

	void readPng () throws IOException {
		for ( int j : PNGID ) {
			if ( j != is.read () ) {
				throw new IOException ( MessageLocalization.getComposedMessage ( "file.is.not.a.valid.png" ) );
			}
		}
		byte[] buffer = new byte[TRANSFERSIZE];
		label:
		while ( true ) {
			int len = getInt ( is );
			String marker = getString ( is );
			if ( len < 0 || !checkMarker ( marker ) )
				throw new IOException ( MessageLocalization.getComposedMessage ( "corrupted.png.file" ) );
			switch ( marker ) {
			case IDAT:
				int size;
				while ( len != 0 ) {
					size = is.read ( buffer, 0, Math.min ( len, TRANSFERSIZE ) );
					if ( size < 0 )
						return;
					idat.write ( buffer, 0, size );
					len -= size;
				}
				break;
			case tRNS:
				switch ( colorType ) {
				case 0:
					if ( len >= 2 ) {
						len -= 2;
						int gray = getWord ( is );
						if ( bitDepth == 16 )
							transRedGray = gray;
						else
							additional.put ( PdfName.MASK, new PdfLiteral ( "[" + gray + " " + gray + "]" ) );
					}
					break;
				case 2:
					if ( len >= 6 ) {
						len -= 6;
						int red = getWord ( is );
						int green = getWord ( is );
						int blue = getWord ( is );
						if ( bitDepth == 16 ) {
							transRedGray = red;
							transGreen = green;
							transBlue = blue;
						} else
							additional.put ( PdfName.MASK,
											new PdfLiteral ( "[" + red + " " + red + " " + green + " " + green + " " + blue + " " + blue + "]" ) );
					}
					break;
				case 3:
					if ( len > 0 ) {
						trans = new byte[len];
						for ( int k = 0; k < len; ++k )
							trans[k] = (byte) is.read ();
						len = 0;
					}
					break;
				}
				Utilities.skip ( is, len );
				break;
			case IHDR:
				width = getInt ( is );
				height = getInt ( is );

				bitDepth = is.read ();
				colorType = is.read ();
				compressionMethod = is.read ();
				filterMethod = is.read ();
				interlaceMethod = is.read ();
				break;
			case PLTE:
				if ( colorType == 3 ) {
					PdfArray colorspace = new PdfArray ();
					colorspace.add ( PdfName.INDEXED );
					colorspace.add ( getColorspace () );
					colorspace.add ( new PdfNumber ( len / 3 - 1 ) );
					ByteBuffer colortable = new ByteBuffer ();
					while ( ( len-- ) > 0 ) {
						colortable.append_i ( is.read () );
					}
					colorspace.add ( new PdfString ( colorTable = colortable.toByteArray () ) );
					additional.put ( PdfName.COLORSPACE, colorspace );
				} else {
					Utilities.skip ( is, len );
				}
				break;
			case pHYs:
				int dx = getInt ( is );
				int dy = getInt ( is );
				int unit = is.read ();
				if ( unit == 1 ) {
					dpiX = (int) ( dx * 0.0254f + 0.5f );
					dpiY = (int) ( dy * 0.0254f + 0.5f );
				} else {
					if ( dy != 0 )
						XYRatio = (float) dx / (float) dy;
				}
				break;
			case cHRM:
				xW = getInt ( is ) / 100000f;
				yW = getInt ( is ) / 100000f;
				xR = getInt ( is ) / 100000f;
				yR = getInt ( is ) / 100000f;
				xG = getInt ( is ) / 100000f;
				yG = getInt ( is ) / 100000f;
				xB = getInt ( is ) / 100000f;
				yB = getInt ( is ) / 100000f;
				hasCHRM = !( Math.abs ( xW ) < 0.0001f || Math.abs ( yW ) < 0.0001f || Math.abs ( xR ) < 0.0001f || Math.abs ( yR ) < 0.0001f || Math.abs (
								xG ) < 0.0001f || Math.abs ( yG ) < 0.0001f || Math.abs ( xB ) < 0.0001f || Math.abs ( yB ) < 0.0001f );
				break;
			case sRGB:
				int ri = is.read ();
				intent = intents[ri];
				gamma = 2.2f;
				xW = 0.3127f;
				yW = 0.329f;
				xR = 0.64f;
				yR = 0.33f;
				xG = 0.3f;
				yG = 0.6f;
				xB = 0.15f;
				yB = 0.06f;
				hasCHRM = true;
				break;
			case gAMA:
				int gm = getInt ( is );
				if ( gm != 0 ) {
					gamma = 100000f / gm;
					if ( !hasCHRM ) {
						xW = 0.3127f;
						yW = 0.329f;
						xR = 0.64f;
						yR = 0.33f;
						xG = 0.3f;
						yG = 0.6f;
						xB = 0.15f;
						yB = 0.06f;
						hasCHRM = true;
					}
				}
				break;
			case iCCP:
				do {
					--len;
				} while ( is.read () != 0 );
				is.read ();
				--len;
				byte[] icccom = new byte[len];
				int p = 0;
				while ( len > 0 ) {
					int r = is.read ( icccom, p, len );
					if ( r < 0 )
						throw new IOException ( MessageLocalization.getComposedMessage ( "premature.end.of.file" ) );
					p += r;
					len -= r;
				}
				byte[] iccp = PdfReader.FlateDecode ( icccom, true );
				try {
					icc_profile = ICC_Profile.getInstance ( iccp );
				} catch ( RuntimeException e ) {
					icc_profile = null;
				}
				break;
			case IEND:
				break label;
			default:
				Utilities.skip ( is, len );
				break;
			}
			Utilities.skip ( is, 4 );
		}
	}

	PdfObject getColorspace () {
		if ( icc_profile != null ) {
			if ( ( colorType & 2 ) == 0 )
				return PdfName.DEVICEGRAY;
			else
				return PdfName.DEVICERGB;
		}
		if ( gamma == 1f && !hasCHRM ) {
			if ( ( colorType & 2 ) == 0 )
				return PdfName.DEVICEGRAY;
			else
				return PdfName.DEVICERGB;
		} else {
			PdfArray array = new PdfArray ();
			PdfDictionary dic = new PdfDictionary ();
			if ( ( colorType & 2 ) == 0 ) {
				if ( gamma == 1f )
					return PdfName.DEVICEGRAY;
				array.add ( PdfName.CALGRAY );
				dic.put ( PdfName.GAMMA, new PdfNumber ( gamma ) );
				dic.put ( PdfName.WHITEPOINT, new PdfLiteral ( "[1 1 1]" ) );
				array.add ( dic );
			} else {
				PdfObject wp = new PdfLiteral ( "[1 1 1]" );
				array.add ( PdfName.CALRGB );
				if ( gamma != 1f ) {
					PdfArray gm = new PdfArray ();
					PdfNumber n = new PdfNumber ( gamma );
					gm.add ( n );
					gm.add ( n );
					gm.add ( n );
					dic.put ( PdfName.GAMMA, gm );
				}
				if ( hasCHRM ) {
					float z = yW * ( ( xG - xB ) * yR - ( xR - xB ) * yG + ( xR - xG ) * yB );
					float YA = yR * ( ( xG - xB ) * yW - ( xW - xB ) * yG + ( xW - xG ) * yB ) / z;
					float XA = YA * xR / yR;
					float ZA = YA * ( ( 1 - xR ) / yR - 1 );
					float YB = -yG * ( ( xR - xB ) * yW - ( xW - xB ) * yR + ( xW - xR ) * yB ) / z;
					float XB = YB * xG / yG;
					float ZB = YB * ( ( 1 - xG ) / yG - 1 );
					float YC = yB * ( ( xR - xG ) * yW - ( xW - xG ) * yW + ( xW - xR ) * yG ) / z;
					float XC = YC * xB / yB;
					float ZC = YC * ( ( 1 - xB ) / yB - 1 );
					float XW = XA + XB + XC;
					float YW = 1;//YA+YB+YC;
					float ZW = ZA + ZB + ZC;
					PdfArray wpa = new PdfArray ();
					wpa.add ( new PdfNumber ( XW ) );
					wpa.add ( new PdfNumber ( YW ) );
					wpa.add ( new PdfNumber ( ZW ) );
					wp = wpa;
					PdfArray matrix = new PdfArray ();
					matrix.add ( new PdfNumber ( XA ) );
					matrix.add ( new PdfNumber ( YA ) );
					matrix.add ( new PdfNumber ( ZA ) );
					matrix.add ( new PdfNumber ( XB ) );
					matrix.add ( new PdfNumber ( YB ) );
					matrix.add ( new PdfNumber ( ZB ) );
					matrix.add ( new PdfNumber ( XC ) );
					matrix.add ( new PdfNumber ( YC ) );
					matrix.add ( new PdfNumber ( ZC ) );
					dic.put ( PdfName.MATRIX, matrix );
				}
				dic.put ( PdfName.WHITEPOINT, wp );
				array.add ( dic );
			}
			return array;
		}
	}

	Image getImage () throws IOException {
		readPng ();
		try {
			int pal0 = 0;
			int palIdx = 0;
			palShades = false;
			if ( trans != null ) {
				for ( int k = 0; k < trans.length; ++k ) {
					int n = trans[k] & 0xff;
					if ( n == 0 ) {
						++pal0;
						palIdx = k;
					}
					if ( n != 0 && n != 255 ) {
						palShades = true;
						break;
					}
				}
			}
			if ( ( colorType & 4 ) != 0 )
				palShades = true;
			genBWMask = ( !palShades && ( pal0 > 1 || transRedGray >= 0 ) );
			if ( !palShades && !genBWMask && pal0 == 1 ) {
				additional.put ( PdfName.MASK, new PdfLiteral ( "[" + palIdx + " " + palIdx + "]" ) );
			}
			boolean needDecode = ( interlaceMethod == 1 ) || ( bitDepth == 16 ) || ( ( colorType & 4 ) != 0 ) || palShades || genBWMask;
			switch ( colorType ) {
			case 0:
			case 3:
				inputBands = 1;
				break;
			case 2:
				inputBands = 3;
				break;
			case 4:
				inputBands = 2;
				break;
			case 6:
				inputBands = 4;
				break;
			}
			if ( needDecode )
				decodeIdat ();
			int components = inputBands;
			if ( ( colorType & 4 ) != 0 )
				--components;
			int bpc = bitDepth;
			if ( bpc == 16 )
				bpc = 8;
			Image img;
			if ( image != null ) {
				if ( colorType == 3 )
					img = new ImgRaw ( width, height, components, bpc, image );
				else
					img = Image.getInstance ( width, height, components, bpc, image );
			} else {
				img = new ImgRaw ( width, height, components, bpc, idat.toByteArray () );
				img.setDeflated ( true );
				PdfDictionary decodeparms = new PdfDictionary ();
				decodeparms.put ( PdfName.BITSPERCOMPONENT, new PdfNumber ( bitDepth ) );
				decodeparms.put ( PdfName.PREDICTOR, new PdfNumber ( 15 ) );
				decodeparms.put ( PdfName.COLUMNS, new PdfNumber ( width ) );
				decodeparms.put ( PdfName.COLORS, new PdfNumber ( ( colorType == 3 || ( colorType & 2 ) == 0 ) ? 1 : 3 ) );
				additional.put ( PdfName.DECODEPARMS, decodeparms );
			}
			if ( additional.get ( PdfName.COLORSPACE ) == null )
				additional.put ( PdfName.COLORSPACE, getColorspace () );
			if ( intent != null )
				additional.put ( PdfName.INTENT, intent );
			if ( additional.size () > 0 )
				img.setAdditional ( additional );
			if ( icc_profile != null )
				img.tagICC ( icc_profile );
			if ( palShades ) {
				Image im2 = Image.getInstance ( width, height, 1, 8, smask );
				im2.makeMask ();
				img.setImageMask ( im2 );
			}
			if ( genBWMask ) {
				Image im2 = Image.getInstance ( width, height, 1, 1, smask );
				im2.makeMask ();
				img.setImageMask ( im2 );
			}
			img.setDpi ( dpiX, dpiY );
			img.setXYRatio ( XYRatio );
			img.setOriginalType ( Image.ORIGINAL_PNG );
			return img;
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
	}

	void decodeIdat () {
		int nbitDepth = bitDepth;
		if ( nbitDepth == 16 )
			nbitDepth = 8;
		int size = -1;
		bytesPerPixel = ( bitDepth == 16 ) ? 2 : 1;
		switch ( colorType ) {
		case 0:
			size = ( nbitDepth * width + 7 ) / 8 * height;
			break;
		case 2:
			size = width * 3 * height;
			bytesPerPixel *= 3;
			break;
		case 3:
			if ( interlaceMethod == 1 )
				size = ( nbitDepth * width + 7 ) / 8 * height;
			bytesPerPixel = 1;
			break;
		case 4:
			size = width * height;
			bytesPerPixel *= 2;
			break;
		case 6:
			size = width * 3 * height;
			bytesPerPixel *= 4;
			break;
		}
		if ( size >= 0 )
			image = new byte[size];
		if ( palShades )
			smask = new byte[width * height];
		else if ( genBWMask )
			smask = new byte[( width + 7 ) / 8 * height];
		ByteArrayInputStream bai = new ByteArrayInputStream ( idat.getBuf (), 0, idat.size () );
		InputStream infStream = new InflaterInputStream ( bai, new Inflater () );
		dataStream = new DataInputStream ( infStream );

		if ( interlaceMethod != 1 ) {
			decodePass ( 0, 0, 1, 1, width, height );
		} else {
			decodePass ( 0, 0, 8, 8, ( width + 7 ) / 8, ( height + 7 ) / 8 );
			decodePass ( 4, 0, 8, 8, ( width + 3 ) / 8, ( height + 7 ) / 8 );
			decodePass ( 0, 4, 4, 8, ( width + 3 ) / 4, ( height + 3 ) / 8 );
			decodePass ( 2, 0, 4, 4, ( width + 1 ) / 4, ( height + 3 ) / 4 );
			decodePass ( 0, 2, 2, 4, ( width + 1 ) / 2, ( height + 1 ) / 4 );
			decodePass ( 1, 0, 2, 2, width / 2, ( height + 1 ) / 2 );
			decodePass ( 0, 1, 1, 2, width, height / 2 );
		}

	}

	void decodePass ( int xOffset, int yOffset,
					int xStep, int yStep,
					int passWidth, int passHeight ) {
		if ( ( passWidth == 0 ) || ( passHeight == 0 ) ) {
			return;
		}

		int bytesPerRow = ( inputBands * passWidth * bitDepth + 7 ) / 8;
		byte[] curr = new byte[bytesPerRow];
		byte[] prior = new byte[bytesPerRow];

		// Decode the (sub)image row-by-row
		int srcY, dstY;
		for ( srcY = 0, dstY = yOffset;
			  srcY < passHeight;
			  srcY++, dstY += yStep ) {
			// Read the filter type byte and a row of data
			int filter = 0;
			try {
				filter = dataStream.read ();
				dataStream.readFully ( curr, 0, bytesPerRow );
			} catch ( Exception e ) {
				// empty on purpose
			}

			switch ( filter ) {
			case PNG_FILTER_NONE:
				break;
			case PNG_FILTER_SUB:
				decodeSubFilter ( curr, bytesPerRow, bytesPerPixel );
				break;
			case PNG_FILTER_UP:
				decodeUpFilter ( curr, prior, bytesPerRow );
				break;
			case PNG_FILTER_AVERAGE:
				decodeAverageFilter ( curr, prior, bytesPerRow, bytesPerPixel );
				break;
			case PNG_FILTER_PAETH:
				decodePaethFilter ( curr, prior, bytesPerRow, bytesPerPixel );
				break;
			default:
				// Error -- uknown filter type
				throw new RuntimeException ( MessageLocalization.getComposedMessage ( "png.filter.unknown" ) );
			}

			processPixels ( curr, xOffset, xStep, dstY, passWidth );

			// Swap curr and prior
			byte[] tmp = prior;
			prior = curr;
			curr = tmp;
		}
	}

	void processPixels ( byte[] curr, int xOffset, int step, int y, int width ) {
		int srcX, dstX;

		int[] out = getPixel ( curr );
		int sizes = 0;
		switch ( colorType ) {
		case 0:
		case 3:
		case 4:
			sizes = 1;
			break;
		case 2:
		case 6:
			sizes = 3;
			break;
		}
		if ( image != null ) {
			dstX = xOffset;
			int yStride = ( sizes * this.width * ( bitDepth == 16 ? 8 : bitDepth ) + 7 ) / 8;
			for ( srcX = 0; srcX < width; srcX++ ) {
				setPixel ( image, out, inputBands * srcX, sizes, dstX, y, bitDepth, yStride );
				dstX += step;
			}
		}
		if ( palShades ) {
			if ( ( colorType & 4 ) != 0 ) {
				if ( bitDepth == 16 ) {
					for ( int k = 0; k < width; ++k )
						out[k * inputBands + sizes] >>>= 8;
				}
				int yStride = this.width;
				dstX = xOffset;
				for ( srcX = 0; srcX < width; srcX++ ) {
					setPixel ( smask, out, inputBands * srcX + sizes, 1, dstX, y, 8, yStride );
					dstX += step;
				}
			} else { //colorType 3
				int yStride = this.width;
				int[] v = new int[1];
				dstX = xOffset;
				for ( srcX = 0; srcX < width; srcX++ ) {
					int idx = out[srcX];
					if ( idx < trans.length )
						v[0] = trans[idx];
					else
						v[0] = 255; // Patrick Valsecchi
					setPixel ( smask, v, 0, 1, dstX, y, 8, yStride );
					dstX += step;
				}
			}
		} else if ( genBWMask ) {
			switch ( colorType ) {
			case 3: {
				int yStride = ( this.width + 7 ) / 8;
				int[] v = new int[1];
				dstX = xOffset;
				for ( srcX = 0; srcX < width; srcX++ ) {
					int idx = out[srcX];
					v[0] = ( ( idx < trans.length && trans[idx] == 0 ) ? 1 : 0 );
					setPixel ( smask, v, 0, 1, dstX, y, 1, yStride );
					dstX += step;
				}
				break;
			}
			case 0: {
				int yStride = ( this.width + 7 ) / 8;
				int[] v = new int[1];
				dstX = xOffset;
				for ( srcX = 0; srcX < width; srcX++ ) {
					int g = out[srcX];
					v[0] = ( g == transRedGray ? 1 : 0 );
					setPixel ( smask, v, 0, 1, dstX, y, 1, yStride );
					dstX += step;
				}
				break;
			}
			case 2: {
				int yStride = ( this.width + 7 ) / 8;
				int[] v = new int[1];
				dstX = xOffset;
				for ( srcX = 0; srcX < width; srcX++ ) {
					int markRed = inputBands * srcX;
					v[0] = ( out[markRed] == transRedGray && out[markRed + 1] == transGreen
									&& out[markRed + 2] == transBlue ? 1 : 0 );
					setPixel ( smask, v, 0, 1, dstX, y, 1, yStride );
					dstX += step;
				}
				break;
			}
			}
		}
	}

	int[] getPixel ( byte[] curr ) {
		switch ( bitDepth ) {
		case 8: {
			int[] out = new int[curr.length];
			for ( int k = 0; k < out.length; ++k )
				out[k] = curr[k] & 0xff;
			return out;
		}
		case 16: {
			int[] out = new int[curr.length / 2];
			for ( int k = 0; k < out.length; ++k )
				out[k] = ( ( curr[k * 2] & 0xff ) << 8 ) + ( curr[k * 2 + 1] & 0xff );
			return out;
		}
		default: {
			int[] out = new int[curr.length * 8 / bitDepth];
			int idx = 0;
			int passes = 8 / bitDepth;
			int mask = ( 1 << bitDepth ) - 1;
			for ( byte b : curr ) {
				for ( int j = passes - 1; j >= 0; --j ) {
					out[idx++] = ( b >>> ( bitDepth * j ) ) & mask;
				}
			}
			return out;
		}
		}
	}

	static class NewByteArrayOutputStream extends ByteArrayOutputStream {

		public byte[] getBuf () {
			return buf;
		}
	}

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt.PdfGraphics2D;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.api.Indentable;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.api.Spaceable;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io.RandomAccessSourceFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.ICC_Profile;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PRIndirectReference;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfContentByte;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfDictionary;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfIndirectReference;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfNumber;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfOCG;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfReader;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfStream;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfString;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfTemplate;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfWriter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.RandomAccessFileOrArray;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec.BmpImage;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec.CCITTG4Encoder;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec.GifImage;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec.JBIG2Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec.PngImage;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec.TiffImage;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.IAccessibleElement;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.IAlternateDescription;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.HashMap;


public abstract class Image extends Rectangle implements Indentable, Spaceable, IAccessibleElement, IAlternateDescription {

	public static final int DEFAULT = 0;

	public static final int RIGHT = 2;

	public static final int LEFT = 0;

	public static final int MIDDLE = 1;

	public static final int TEXTWRAP = 4;

	public static final int UNDERLYING = 8;

	public static final int AX = 0;

	public static final int AY = 1;

	public static final int BX = 2;

	public static final int BY = 3;

	public static final int CX = 4;

	public static final int CY = 5;

	public static final int DX = 6;

	public static final int DY = 7;

	public static final int ORIGINAL_NONE = 0;

	public static final int ORIGINAL_JPEG = 1;

	public static final int ORIGINAL_PNG = 2;

	public static final int ORIGINAL_GIF = 3;

	public static final int ORIGINAL_BMP = 4;

	public static final int ORIGINAL_TIFF = 5;

	public static final int ORIGINAL_WMF = 6;

	public static final int ORIGINAL_PS = 7;

	public static final int ORIGINAL_JPEG2000 = 8;

	public static final int ORIGINAL_JBIG2 = 9;

	static long serialId = 0;

	protected int type;

	protected URL url;

	protected byte[] rawData;

	protected int bpc = 1;

	protected PdfTemplate[] template = new PdfTemplate[1];

	protected int alignment;

	protected String alt;

	protected float absoluteX = Float.NaN;

	protected float absoluteY = Float.NaN;

	protected float plainWidth;

	protected float plainHeight;

	protected float scaledWidth;

	protected float scaledHeight;

	protected int compressionLevel = PdfStream.DEFAULT_COMPRESSION;

	protected Long mySerialId = getSerialId ();

	protected PdfName role = PdfName.FIGURE;

	protected HashMap<PdfName, PdfObject> accessibleAttributes = null;

	protected float rotationRadians;

	protected float indentationLeft = 0;

	protected float indentationRight = 0;

	protected float spacingBefore;

	protected float spacingAfter;

	protected boolean scaleToFitLineWhenOverflow;

	protected boolean scaleToFitHeight = true;

	protected Annotation annotation = null;

	protected PdfOCG layer;

	protected boolean interpolation;

	protected int originalType = ORIGINAL_NONE;

	protected byte[] originalData;

	protected boolean deflated = false;

	protected int dpiX = 0;

	protected int dpiY = 0;

	protected int colorspace = -1;

	protected int colortransform = 1;

	protected boolean invert = false;

	protected ICC_Profile profile = null;

	protected boolean mask = false;

	protected Image imageMask;

	protected int[] transparency;

	private AccessibleElementId id = null;

	private PdfIndirectReference directReference;

	private float initialRotation;

	private float widthPercentage = 100;

	private float XYRatio = 0;

	private PdfDictionary additional = null;

	private boolean smask;

	public Image ( final URL url ) {
		super ( 0, 0 );
		this.url = url;
		this.alignment = DEFAULT;
		rotationRadians = 0;
	}

	protected Image ( final Image image ) {
		super ( image );
		this.type = image.type;
		this.url = image.url;
		this.rawData = image.rawData;
		this.bpc = image.bpc;
		this.template = image.template;
		this.alignment = image.alignment;
		this.alt = image.alt;
		this.absoluteX = image.absoluteX;
		this.absoluteY = image.absoluteY;
		this.plainWidth = image.plainWidth;
		this.plainHeight = image.plainHeight;
		this.scaledWidth = image.scaledWidth;
		this.scaledHeight = image.scaledHeight;
		this.mySerialId = image.mySerialId;

		this.directReference = image.directReference;

		this.rotationRadians = image.rotationRadians;
		this.initialRotation = image.initialRotation;
		this.indentationLeft = image.indentationLeft;
		this.indentationRight = image.indentationRight;
		this.spacingBefore = image.spacingBefore;
		this.spacingAfter = image.spacingAfter;

		this.widthPercentage = image.widthPercentage;
		this.scaleToFitLineWhenOverflow = image.scaleToFitLineWhenOverflow;
		this.scaleToFitHeight = image.scaleToFitHeight;
		this.annotation = image.annotation;
		this.layer = image.layer;
		this.interpolation = image.interpolation;
		this.originalType = image.originalType;
		this.originalData = image.originalData;
		this.deflated = image.deflated;
		this.dpiX = image.dpiX;
		this.dpiY = image.dpiY;
		this.XYRatio = image.XYRatio;

		this.colorspace = image.colorspace;
		this.invert = image.invert;
		this.profile = image.profile;
		this.additional = image.additional;
		this.mask = image.mask;
		this.imageMask = image.imageMask;
		this.smask = image.smask;
		this.transparency = image.transparency;
		this.role = image.role;
		if ( image.accessibleAttributes != null )
			this.accessibleAttributes = new HashMap<> ( image.accessibleAttributes );
		setId ( image.getId () );
	}

	public static Image getInstance ( final URL url ) throws BadElementException, IOException {
		return Image.getInstance ( url, false );
	}

	public static Image getInstance ( final URL url, boolean recoverFromImageError ) throws BadElementException,
					IOException {
		InputStream is = null;
		RandomAccessSourceFactory randomAccessSourceFactory = new RandomAccessSourceFactory ();

		try {
			is = url.openStream ();
			int c1 = is.read ();
			int c2 = is.read ();
			int c3 = is.read ();
			int c4 = is.read ();
			int c5 = is.read ();
			int c6 = is.read ();
			int c7 = is.read ();
			int c8 = is.read ();
			is.close ();

			is = null;
			if ( c1 == 'G' && c2 == 'I' && c3 == 'F' ) {
				GifImage gif = new GifImage ( url );
				return gif.getImage ( 1 );
			}
			if ( c1 == 0xFF && c2 == 0xD8 ) {
				return new Jpeg ( url );
			}
			if ( c1 == 0x00 && c2 == 0x00 && c3 == 0x00 && c4 == 0x0c ) {
				return new Jpeg2000 ( url );
			}
			if ( c1 == 0xff && c2 == 0x4f && c3 == 0xff && c4 == 0x51 ) {
				return new Jpeg2000 ( url );
			}
			if ( c1 == PngImage.PNGID[0] && c2 == PngImage.PNGID[1]
							&& c3 == PngImage.PNGID[2] && c4 == PngImage.PNGID[3] ) {
				return PngImage.getImage ( url );
			}
			if ( c1 == 0xD7 && c2 == 0xCD ) {
				return new ImgWMF ( url );
			}
			if ( c1 == 'B' && c2 == 'M' ) {
				return BmpImage.getImage ( url );
			}
			if ( c1 == 'M' && c2 == 'M' && c3 == 0 && c4 == 42
							|| c1 == 'I' && c2 == 'I' && c3 == 42 && c4 == 0 ) {
				RandomAccessFileOrArray ra = null;
				try {
					if ( url.getProtocol ().equals ( "file" ) ) {
						String file = url.getFile ();
						file = Utilities.unEscapeURL ( file );
						ra = new RandomAccessFileOrArray ( randomAccessSourceFactory.createBestSource ( file ) );
					} else
						ra = new RandomAccessFileOrArray ( randomAccessSourceFactory.createSource ( url ) );
					Image img = TiffImage.getTiffImage ( ra, 1 );
					img.url = url;
					return img;
				} catch ( RuntimeException e ) {
					if ( recoverFromImageError ) {
						Image img = TiffImage.getTiffImage ( ra, true, 1 );
						img.url = url;
						return img;
					}
					throw e;
				} finally {
					if ( ra != null )
						ra.close ();
				}

			}
			if ( c1 == 0x97 && c2 == 'J' && c3 == 'B' && c4 == '2' &&
							c5 == '\r' && c6 == '\n' && c7 == 0x1a && c8 == '\n' ) {
				RandomAccessFileOrArray ra = null;
				try {
					if ( url.getProtocol ().equals ( "file" ) ) {
						String file = url.getFile ();
						file = Utilities.unEscapeURL ( file );
						ra = new RandomAccessFileOrArray ( randomAccessSourceFactory.createBestSource ( file ) );
					} else
						ra = new RandomAccessFileOrArray ( randomAccessSourceFactory.createSource ( url ) );
					Image img = JBIG2Image.getJbig2Image ( ra, 1 );
					img.url = url;
					return img;
				} finally {
					if ( ra != null )
						ra.close ();
				}
			}
			throw new IOException ( MessageLocalization.getComposedMessage ( "unknown.image.format", url.toString () ) );
		} finally {
			if ( is != null ) {
				is.close ();
			}
		}
	}

	public static Image getInstance ( final String filename )
					throws BadElementException, IOException {
		return getInstance ( Utilities.toURL ( filename ) );
	}

	public static Image getInstance ( final String filename, boolean recoverFromImageError ) throws IOException, BadElementException {
		return getInstance ( Utilities.toURL ( filename ), recoverFromImageError );
	}

	public static Image getInstance ( final byte[] imgb ) throws BadElementException,
					IOException {
		return getInstance ( imgb, false );
	}

	public static Image getInstance ( final byte[] imgb, boolean recoverFromImageError ) throws BadElementException,
					IOException {
		InputStream is = null;
		RandomAccessSourceFactory randomAccessSourceFactory = new RandomAccessSourceFactory ();
		try {
			is = new java.io.ByteArrayInputStream ( imgb );
			int c1 = is.read ();
			int c2 = is.read ();
			int c3 = is.read ();
			int c4 = is.read ();
			is.close ();

			is = null;
			if ( c1 == 'G' && c2 == 'I' && c3 == 'F' ) {
				GifImage gif = new GifImage ( imgb );
				return gif.getImage ( 1 );
			}
			if ( c1 == 0xFF && c2 == 0xD8 ) {
				return new Jpeg ( imgb );
			}
			if ( c1 == 0x00 && c2 == 0x00 && c3 == 0x00 && c4 == 0x0c ) {
				return new Jpeg2000 ( imgb );
			}
			if ( c1 == 0xff && c2 == 0x4f && c3 == 0xff && c4 == 0x51 ) {
				return new Jpeg2000 ( imgb );
			}
			if ( c1 == PngImage.PNGID[0] && c2 == PngImage.PNGID[1]
							&& c3 == PngImage.PNGID[2] && c4 == PngImage.PNGID[3] ) {
				return PngImage.getImage ( imgb );
			}
			if ( c1 == 0xD7 && c2 == 0xCD ) {
				return new ImgWMF ( imgb );
			}
			if ( c1 == 'B' && c2 == 'M' ) {
				return BmpImage.getImage ( imgb );
			}
			if ( c1 == 'M' && c2 == 'M' && c3 == 0 && c4 == 42
							|| c1 == 'I' && c2 == 'I' && c3 == 42 && c4 == 0 ) {
				RandomAccessFileOrArray ra = null;
				try {
					ra = new RandomAccessFileOrArray ( randomAccessSourceFactory.createSource ( imgb ) );
					Image img = TiffImage.getTiffImage ( ra, 1 );
					if ( img.getOriginalData () == null )
						img.setOriginalData ( imgb );
					return img;
				} catch ( RuntimeException e ) {
					if ( recoverFromImageError ) {
						Image img = TiffImage.getTiffImage ( ra, true, 1 );
						if ( img.getOriginalData () == null )
							img.setOriginalData ( imgb );
						return img;
					}
					throw e;
				} finally {
					if ( ra != null )
						ra.close ();
				}

			}
			if ( c1 == 0x97 && c2 == 'J' && c3 == 'B' && c4 == '2' ) {
				is = new java.io.ByteArrayInputStream ( imgb );
				is.skip ( 4 );
				int c5 = is.read ();
				int c6 = is.read ();
				int c7 = is.read ();
				int c8 = is.read ();
				is.close ();
				if ( c5 == '\r' && c6 == '\n' && c7 == 0x1a && c8 == '\n' ) {
					RandomAccessFileOrArray ra = null;
					try {
						ra = new RandomAccessFileOrArray ( randomAccessSourceFactory.createSource ( imgb ) );
						Image img = JBIG2Image.getJbig2Image ( ra, 1 );
						if ( img.getOriginalData () == null )
							img.setOriginalData ( imgb );
						return img;
					} finally {
						if ( ra != null )
							ra.close ();
					}
				}
			}
			throw new IOException ( MessageLocalization.getComposedMessage ( "the.byte.array.is.not.a.recognized.imageformat" ) );
		} finally {
			if ( is != null ) {
				is.close ();
			}
		}
	}

	public static Image getInstance ( final int width, final int height, final int components,
					final int bpc, final byte[] data ) throws BadElementException {
		return Image.getInstance ( width, height, components, bpc, data, null );
	}

	public static Image getInstance ( final int width, final int height, final byte[] data, final byte[] globals ) {
		return new ImgJBIG2 ( width, height, data, globals );
	}

	public static Image getInstance ( final int width, final int height, final boolean reverseBits,
					final int typeCCITT, final int parameters, final byte[] data )
					throws BadElementException {
		return Image.getInstance ( width, height, reverseBits, typeCCITT,
						parameters, data, null );
	}

	public static Image getInstance ( final int width, final int height, final boolean reverseBits,
					final int typeCCITT, final int parameters, final byte[] data, final int[] transparency )
					throws BadElementException {
		if ( transparency != null && transparency.length != 2 )
			throw new BadElementException ( MessageLocalization.getComposedMessage ( "transparency.length.must.be.equal.to.2.with.ccitt.images" ) );
		Image img = new ImgCCITT ( width, height, reverseBits, typeCCITT,
						parameters, data );
		img.transparency = transparency;
		return img;
	}

	public static Image getInstance ( final int width, final int height, final int components,
					final int bpc, final byte[] data, final int[] transparency )
					throws BadElementException {
		if ( transparency != null && transparency.length != components * 2 )
			throw new BadElementException ( MessageLocalization.getComposedMessage ( "transparency.length.must.be.equal.to.componentes.2" ) );
		if ( components == 1 && bpc == 1 ) {
			byte[] g4 = CCITTG4Encoder.compress ( data, width, height );
			return Image.getInstance ( width, height, false, Image.CCITTG4,
							Image.CCITT_BLACKIS1, g4, transparency );
		}
		Image img = new ImgRaw ( width, height, components, bpc, data );
		img.transparency = transparency;
		return img;
	}

	public static Image getInstance ( final PdfTemplate template )
					throws BadElementException {
		return new ImgTemplate ( template );
	}

	public static Image getInstance ( final PRIndirectReference ref ) throws BadElementException {
		PdfDictionary dic = (PdfDictionary) PdfReader.getPdfObjectRelease ( ref );
		int width = ( (PdfNumber) PdfReader.getPdfObjectRelease ( dic.get ( PdfName.WIDTH ) ) ).intValue ();
		int height = ( (PdfNumber) PdfReader.getPdfObjectRelease ( dic.get ( PdfName.HEIGHT ) ) ).intValue ();
		Image imask = null;
		PdfObject obj = dic.get ( PdfName.SMASK );
		if ( obj != null && obj.isIndirect () ) {
			imask = getInstance ( (PRIndirectReference) obj );
		} else {
			obj = dic.get ( PdfName.MASK );
			if ( obj != null && obj.isIndirect () ) {
				PdfObject obj2 = PdfReader.getPdfObjectRelease ( obj );
				if ( obj2 instanceof PdfDictionary )
					imask = getInstance ( (PRIndirectReference) obj );
			}
		}
		Image img = new ImgRaw ( width, height, 1, 1, null );
		img.imageMask = imask;
		img.directReference = ref;
		return img;
	}

	public static Image getInstance ( final Image image ) {
		if ( image == null )
			return null;
		try {
			Class<? extends Image> cs = image.getClass ();
			Constructor<? extends Image> constructor = cs
							.getDeclaredConstructor ( Image.class );
			return constructor.newInstance ( image );
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
	}

	static protected synchronized Long getSerialId () {
		++serialId;
		return serialId;
	}

	public static Image getInstance ( final java.awt.Image image, final java.awt.Color color,
					boolean forceBW ) throws BadElementException, IOException {

		if ( image instanceof java.awt.image.BufferedImage ) {
			java.awt.image.BufferedImage bi = (java.awt.image.BufferedImage) image;
			if ( bi.getType () == java.awt.image.BufferedImage.TYPE_BYTE_BINARY
							&& bi.getColorModel ().getPixelSize () == 1 ) {
				forceBW = true;
			}
		}

		java.awt.image.PixelGrabber pg = new java.awt.image.PixelGrabber ( image,
						0, 0, -1, -1, true );
		try {
			pg.grabPixels ();
		} catch ( InterruptedException e ) {
			throw new IOException ( MessageLocalization.getComposedMessage ( "java.awt.image.interrupted.waiting.for.pixels" ) );
		}
		if ( ( pg.getStatus () & java.awt.image.ImageObserver.ABORT ) != 0 ) {
			throw new IOException ( MessageLocalization.getComposedMessage ( "java.awt.image.fetch.aborted.or.errored" ) );
		}
		int w = pg.getWidth ();
		int h = pg.getHeight ();
		int[] pixels = (int[]) pg.getPixels ();
		if ( forceBW ) {
			int byteWidth = w / 8 + ( ( w & 7 ) != 0 ? 1 : 0 );
			byte[] pixelsByte = new byte[byteWidth * h];

			int index = 0;
			int size = h * w;
			int transColor = 1;
			if ( color != null ) {
				transColor = color.getRed () + color.getGreen ()
								+ color.getBlue () < 384 ? 0 : 1;
			}
			int[] transparency = null;
			int cbyte = 0x80;
			int wMarker = 0;
			int currByte = 0;
			if ( color != null ) {
				for ( int j = 0; j < size; j++ ) {
					int alpha = pixels[j] >> 24 & 0xff;
					if ( alpha < 250 ) {
						if ( transColor == 1 )
							currByte |= cbyte;
					} else {
						if ( ( pixels[j] & 0x888 ) != 0 )
							currByte |= cbyte;
					}
					cbyte >>= 1;
					if ( cbyte == 0 || wMarker + 1 >= w ) {
						pixelsByte[index++] = (byte) currByte;
						cbyte = 0x80;
						currByte = 0;
					}
					++wMarker;
					if ( wMarker >= w )
						wMarker = 0;
				}
			} else {
				for ( int j = 0; j < size; j++ ) {
					if ( transparency == null ) {
						int alpha = pixels[j] >> 24 & 0xff;
						if ( alpha == 0 ) {
							transparency = new int[2];
							/* bugfix by M.P. Liston, ASC, was: ... ? 1: 0; */
							transparency[0] = transparency[1] = ( pixels[j] & 0x888 ) != 0 ? 0xff : 0;
						}
					}
					if ( ( pixels[j] & 0x888 ) != 0 )
						currByte |= cbyte;
					cbyte >>= 1;
					if ( cbyte == 0 || wMarker + 1 >= w ) {
						pixelsByte[index++] = (byte) currByte;
						cbyte = 0x80;
						currByte = 0;
					}
					++wMarker;
					if ( wMarker >= w )
						wMarker = 0;
				}
			}
			return Image.getInstance ( w, h, 1, 1, pixelsByte, transparency );
		} else {
			byte[] pixelsByte = new byte[w * h * 3];
			byte[] smask = null;

			int index = 0;
			int size = h * w;
			int red = 255;
			int green = 255;
			int blue = 255;
			if ( color != null ) {
				red = color.getRed ();
				green = color.getGreen ();
				blue = color.getBlue ();
			}
			int[] transparency = null;
			if ( color != null ) {
				for ( int j = 0; j < size; j++ ) {
					int alpha = pixels[j] >> 24 & 0xff;
					if ( alpha < 250 ) {
						pixelsByte[index++] = (byte) red;
						pixelsByte[index++] = (byte) green;
						pixelsByte[index++] = (byte) blue;
					} else {
						pixelsByte[index++] = (byte) ( pixels[j] >> 16 & 0xff );
						pixelsByte[index++] = (byte) ( pixels[j] >> 8 & 0xff );
						pixelsByte[index++] = (byte) ( pixels[j] & 0xff );
					}
				}
			} else {
				int transparentPixel = 0;
				smask = new byte[w * h];
				boolean shades = false;
				for ( int j = 0; j < size; j++ ) {
					byte alpha = smask[j] = (byte) ( pixels[j] >> 24 & 0xff );
					if ( !shades ) {
						if ( alpha != 0 && alpha != -1 ) {
							shades = true;
						} else if ( transparency == null ) {
							if ( alpha == 0 ) {
								transparentPixel = pixels[j] & 0xffffff;
								transparency = new int[6];
								transparency[0] = transparency[1] = transparentPixel >> 16 & 0xff;
								transparency[2] = transparency[3] = transparentPixel >> 8 & 0xff;
								transparency[4] = transparency[5] = transparentPixel & 0xff;
							}
						} else if ( ( pixels[j] & 0xffffff ) != transparentPixel ) {
							shades = true;
						}
					}
					pixelsByte[index++] = (byte) ( pixels[j] >> 16 & 0xff );
					pixelsByte[index++] = (byte) ( pixels[j] >> 8 & 0xff );
					pixelsByte[index++] = (byte) ( pixels[j] & 0xff );
				}
				if ( shades )
					transparency = null;
				else
					smask = null;
			}
			Image img = Image.getInstance ( w, h, 3, 8, pixelsByte, transparency );
			if ( smask != null ) {
				Image sm = Image.getInstance ( w, h, 1, 8, smask );
				try {
					sm.makeMask ();
					img.setImageMask ( sm );
				} catch ( DocumentException de ) {
					throw new ExceptionConverter ( de );
				}
			}
			return img;
		}
	}

	public static Image getInstance ( final java.awt.Image image, final java.awt.Color color )
					throws BadElementException, IOException {
		return Image.getInstance ( image, color, false );
	}

	public static Image getInstance ( final PdfWriter writer, final java.awt.Image awtImage, final float quality ) throws BadElementException, IOException {
		return getInstance ( new PdfContentByte ( writer ), awtImage, quality );
	}

	public static Image getInstance ( final PdfContentByte cb, final java.awt.Image awtImage, final float quality ) throws BadElementException, IOException {
		java.awt.image.PixelGrabber pg = new java.awt.image.PixelGrabber ( awtImage,
						0, 0, -1, -1, true );
		try {
			pg.grabPixels ();
		} catch ( InterruptedException e ) {
			throw new IOException ( MessageLocalization.getComposedMessage ( "java.awt.image.interrupted.waiting.for.pixels" ) );
		}
		if ( ( pg.getStatus () & java.awt.image.ImageObserver.ABORT ) != 0 ) {
			throw new IOException ( MessageLocalization.getComposedMessage ( "java.awt.image.fetch.aborted.or.errored" ) );
		}
		int w = pg.getWidth ();
		int h = pg.getHeight ();
		PdfTemplate tp = cb.createTemplate ( w, h );
		PdfGraphics2D g2d = new PdfGraphics2D ( tp, w, h, null, false, true, quality );
		g2d.drawImage ( awtImage, 0, 0, null );
		g2d.dispose ();
		return getInstance ( tp );
	}

	public PdfIndirectReference getDirectReference () {
		return this.directReference;
	}

	@Override
	public int type () {
		return type;
	}

	@Override
	public boolean isNestable () {
		return true;
	}

	public boolean isJpeg () {
		return type == JPEG;
	}

	public boolean isImgRaw () {
		return type == IMGRAW;
	}

	public boolean isImgTemplate () {
		return type == IMGTEMPLATE;
	}

	public URL getUrl () {
		return url;
	}

	public void setUrl ( final URL url ) {
		this.url = url;
	}

	public byte[] getRawData () {
		return rawData;
	}

	public int getBpc () {
		return bpc;
	}

	public PdfTemplate getTemplateData () {
		return template[0];
	}

	public void setTemplateData ( final PdfTemplate template ) {
		this.template[0] = template;
	}

	public int getAlignment () {
		return alignment;
	}

	public void setAlignment ( final int alignment ) {
		this.alignment = alignment;
	}

	public String getAlt () {
		return alt;
	}

	public void setAlt ( final String alt ) {
		this.alt = alt;
		setAccessibleAttribute ( PdfName.ALT, new PdfString ( alt ) );
	}

	public void setAbsolutePosition ( final float absoluteX, final float absoluteY ) {
		this.absoluteX = absoluteX;
		this.absoluteY = absoluteY;
	}

	public boolean hasAbsoluteX () {
		return !Float.isNaN ( absoluteX );
	}

	public float getAbsoluteX () {
		return absoluteX;
	}

	public boolean hasAbsoluteY () {
		return !Float.isNaN ( absoluteY );
	}

	public float getAbsoluteY () {
		return absoluteY;
	}

	public float getScaledWidth () {
		return scaledWidth;
	}

	public float getScaledHeight () {
		return scaledHeight;
	}

	public void scaleAbsolute ( final float newWidth, final float newHeight ) {
		plainWidth = newWidth;
		plainHeight = newHeight;
		float[] matrix = matrix ();
		scaledWidth = matrix[DX] - matrix[CX];
		scaledHeight = matrix[DY] - matrix[CY];
		setWidthPercentage ( 0 );
	}

	public void scalePercent ( final float percent ) {
		scalePercent ( percent, percent );
	}

	public void scalePercent ( final float percentX, final float percentY ) {
		plainWidth = getWidth () * percentX / 100f;
		plainHeight = getHeight () * percentY / 100f;
		float[] matrix = matrix ();
		scaledWidth = matrix[DX] - matrix[CX];
		scaledHeight = matrix[DY] - matrix[CY];
		setWidthPercentage ( 0 );
	}

	public void scaleToFit ( final Rectangle rectangle ) {
		scaleToFit ( rectangle.getWidth (), rectangle.getHeight () );
	}

	public void scaleToFit ( final float fitWidth, final float fitHeight ) {
		scalePercent ( 100 );
		float percentX = fitWidth * 100 / getScaledWidth ();
		float percentY = fitHeight * 100 / getScaledHeight ();
		scalePercent ( Math.min ( percentX, percentY ) );
		setWidthPercentage ( 0 );
	}

	public float[] matrix () {
		return matrix ( 1 );
	}

	public float[] matrix ( float scalePercentage ) {
		float[] matrix = new float[8];
		float cosX = (float) Math.cos ( rotationRadians );
		float sinX = (float) Math.sin ( rotationRadians );
		matrix[AX] = plainWidth * cosX * scalePercentage;
		matrix[AY] = plainWidth * sinX * scalePercentage;
		matrix[BX] = -plainHeight * sinX * scalePercentage;
		matrix[BY] = plainHeight * cosX * scalePercentage;
		if ( rotationRadians < Math.PI / 2f ) {
			matrix[CX] = matrix[BX];
			matrix[CY] = 0;
			matrix[DX] = matrix[AX];
			matrix[DY] = matrix[AY] + matrix[BY];
		} else if ( rotationRadians < Math.PI ) {
			matrix[CX] = matrix[AX] + matrix[BX];
			matrix[CY] = matrix[BY];
			matrix[DX] = 0;
			matrix[DY] = matrix[AY];
		} else if ( rotationRadians < Math.PI * 1.5f ) {
			matrix[CX] = matrix[AX];
			matrix[CY] = matrix[AY] + matrix[BY];
			matrix[DX] = matrix[BX];
			matrix[DY] = 0;
		} else {
			matrix[CX] = 0;
			matrix[CY] = matrix[AY];
			matrix[DX] = matrix[AX] + matrix[BX];
			matrix[DY] = matrix[BY];
		}
		return matrix;
	}

	public Long getMySerialId () {
		return mySerialId;
	}

	public float getImageRotation () {
		double d = 2.0 * Math.PI;
		float rot = (float) ( ( rotationRadians - initialRotation ) % d );
		if ( rot < 0 ) {
			rot += (float) d;
		}
		return rot;
	}

	public void setRotation ( final float r ) {
		double d = 2.0 * Math.PI;
		rotationRadians = (float) ( ( r + initialRotation ) % d );
		if ( rotationRadians < 0 ) {
			rotationRadians += (float) d;
		}
		float[] matrix = matrix ();
		scaledWidth = matrix[DX] - matrix[CX];
		scaledHeight = matrix[DY] - matrix[CY];
	}

	public void setInitialRotation ( final float initialRotation ) {
		float old_rot = rotationRadians - this.initialRotation;
		this.initialRotation = initialRotation;
		setRotation ( old_rot );
	}

	public float getIndentationLeft () {
		return indentationLeft;
	}

	public void setIndentationLeft ( final float f ) {
		indentationLeft = f;
	}

	public float getIndentationRight () {
		return indentationRight;
	}

	public void setIndentationRight ( final float f ) {
		indentationRight = f;
	}

	public float getSpacingBefore () {
		return spacingBefore;
	}

	public void setSpacingBefore ( final float spacing ) {
		this.spacingBefore = spacing;
	}

	public float getSpacingAfter () {
		return spacingAfter;
	}

	public void setSpacingAfter ( final float spacing ) {
		this.spacingAfter = spacing;
	}

	public float getWidthPercentage () {
		return this.widthPercentage;
	}

	public void setWidthPercentage ( final float widthPercentage ) {
		this.widthPercentage = widthPercentage;
	}

	public boolean isScaleToFitLineWhenOverflow () {
		return scaleToFitLineWhenOverflow;
	}

	public void setScaleToFitLineWhenOverflow ( final boolean scaleToFitLineWhenOverflow ) {
		this.scaleToFitLineWhenOverflow = scaleToFitLineWhenOverflow;
	}

	public boolean isScaleToFitHeight () {
		return scaleToFitHeight;
	}

	public void setScaleToFitHeight ( final boolean scaleToFitHeight ) {
		this.scaleToFitHeight = scaleToFitHeight;
	}

	public Annotation getAnnotation () {
		return annotation;
	}

	public void setAnnotation ( final Annotation annotation ) {
		this.annotation = annotation;
	}

	public PdfOCG getLayer () {
		return layer;
	}

	public void setLayer ( final PdfOCG layer ) {
		this.layer = layer;
	}

	public boolean isInterpolation () {
		return interpolation;
	}

	public void setInterpolation ( final boolean interpolation ) {
		this.interpolation = interpolation;
	}

	public void setOriginalType ( final int originalType ) {
		this.originalType = originalType;
	}

	public byte[] getOriginalData () {
		return this.originalData;
	}

	public void setOriginalData ( final byte[] originalData ) {
		this.originalData = originalData;
	}

	public boolean isDeflated () {
		return this.deflated;
	}

	public void setDeflated ( final boolean deflated ) {
		this.deflated = deflated;
	}

	public void setDpi ( final int dpiX, final int dpiY ) {
		this.dpiX = dpiX;
		this.dpiY = dpiY;
	}

	public void setXYRatio ( final float XYRatio ) {
		this.XYRatio = XYRatio;
	}

	public int getColorspace () {
		return colorspace;
	}

	public int getColorTransform () {
		return colortransform;
	}

	public void setColorTransform ( int c ) {
		colortransform = c;
	}

	public boolean isInverted () {
		return invert;
	}

	public void setInverted ( final boolean invert ) {
		this.invert = invert;
	}

	public void tagICC ( final ICC_Profile profile ) {
		this.profile = profile;
	}

	public boolean hasICCProfile () {
		return this.profile != null;
	}

	public ICC_Profile getICCProfile () {
		return profile;
	}

	public PdfDictionary getAdditional () {
		return this.additional;
	}

	public void setAdditional ( final PdfDictionary additional ) {
		this.additional = additional;
	}

	public boolean isMask () {
		return mask;
	}

	public void makeMask () throws DocumentException {
		if ( !isMaskCandidate () )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "this.image.can.not.be.an.image.mask" ) );
		mask = true;
	}

	public boolean isMaskCandidate () {
		if ( type == IMGRAW ) {
			if ( bpc > 0xff )
				return true;
		}
		return colorspace == 1;
	}

	public Image getImageMask () {
		return imageMask;
	}

	public void setImageMask ( final Image mask ) throws DocumentException {
		if ( this.mask )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "an.image.mask.cannot.contain.another.image.mask" ) );
		if ( !mask.mask )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "the.image.mask.is.not.a.mask.did.you.do.makemask" ) );
		imageMask = mask;
		smask = mask.bpc > 1 && mask.bpc <= 8;
	}

	public boolean isSmask () {
		return this.smask;
	}

	public int[] getTransparency () {
		return transparency;
	}

	public void setTransparency ( final int[] transparency ) {
		this.transparency = transparency;
	}

	public int getCompressionLevel () {
		return compressionLevel;
	}

	public void setCompressionLevel ( final int compressionLevel ) {
		if ( compressionLevel < PdfStream.NO_COMPRESSION || compressionLevel > PdfStream.BEST_COMPRESSION )
			this.compressionLevel = PdfStream.DEFAULT_COMPRESSION;
		else
			this.compressionLevel = compressionLevel;
	}

	public PdfObject getAccessibleAttribute ( final PdfName key ) {
		if ( accessibleAttributes != null )
			return accessibleAttributes.get ( key );
		else
			return null;
	}

	public void setAccessibleAttribute ( final PdfName key, final PdfObject value ) {
		if ( accessibleAttributes == null )
			accessibleAttributes = new HashMap<> ();
		accessibleAttributes.put ( key, value );
	}

	public HashMap<PdfName, PdfObject> getAccessibleAttributes () {
		return accessibleAttributes;
	}

	public PdfName getRole () {
		return role;
	}

	public void setRole ( final PdfName role ) {
		this.role = role;
	}

	public AccessibleElementId getId () {
		if ( id == null )
			id = new AccessibleElementId ();
		return id;
	}

	public void setId ( final AccessibleElementId id ) {
		this.id = id;
	}

	public boolean isInline () {
		return true;
	}
}

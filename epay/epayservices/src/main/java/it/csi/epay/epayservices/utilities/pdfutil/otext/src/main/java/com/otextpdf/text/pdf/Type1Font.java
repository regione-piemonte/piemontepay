/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Document;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io.StreamUtil;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.FontsResourceAnchor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;


class Type1Font extends BaseFont {

	private static final int[] PFB_TYPES = { 1, 2, 1 };

	private static FontsResourceAnchor resourceAnchor;

	private final HashMap<Object, Object[]> CharMetrics = new HashMap<> ();

	private final HashMap<String, Object[]> KernPairs = new HashMap<> ();

	private final String fileName;

	protected byte[] pfb;

	private String FontName;

	private String FullName;

	private String FamilyName;

	private String Weight = "";

	private float ItalicAngle = 0.0f;

	private boolean IsFixedPitch = false;

	private int llx = -50;

	private int lly = -200;

	private int urx = 1000;

	private int ury = 900;

	private int UnderlinePosition = -100;

	private int UnderlineThickness = 50;

	private String EncodingScheme = "FontSpecific";

	private int CapHeight = 700;

	private int Ascender = 800;

	private int Descender = -200;

	private int StdVW = 80;

	private boolean builtinFont = false;

	Type1Font ( String afmFile, String enc, boolean emb, byte[] ttfAfm, byte[] pfb, boolean forceRead )
					throws DocumentException, IOException {
		if ( emb && ttfAfm != null && pfb == null )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "two.byte.arrays.are.needed.if.the.type1.font.is.embedded" ) );
		if ( emb && ttfAfm != null )
			this.pfb = pfb;
		encoding = enc;
		embedded = emb;
		fileName = afmFile;
		fontType = FONT_TYPE_T1;
		RandomAccessFileOrArray rf = null;
		InputStream is = null;
		if ( BuiltinFonts14.containsKey ( afmFile ) ) {
			embedded = false;
			builtinFont = true;
			byte[] buf = new byte[1024];
			try {
				if ( resourceAnchor == null )
					resourceAnchor = new FontsResourceAnchor ();
				is = StreamUtil.getResourceStream ( RESOURCE_PATH + afmFile + ".afm", resourceAnchor.getClass ().getClassLoader () );
				if ( is == null ) {
					String msg = MessageLocalization.getComposedMessage ( "1.not.found.as.resource", afmFile );
					System.err.println ( msg );
					throw new DocumentException ( msg );
				}
				ByteArrayOutputStream out = new ByteArrayOutputStream ();
				while ( true ) {
					int size = is.read ( buf );
					if ( size < 0 )
						break;
					out.write ( buf, 0, size );
				}
				buf = out.toByteArray ();
			} finally {
				if ( is != null ) {
					try {
						is.close ();
					} catch ( Exception ignored ) {
					}
				}
			}
			try {
				rf = new RandomAccessFileOrArray ( buf );
				process ( rf );
			} finally {
				if ( rf != null ) {
					try {
						rf.close ();
					} catch ( Exception ignored ) {
					}
				}
			}
		} else if ( afmFile.toLowerCase ().endsWith ( ".afm" ) ) {
			try {
				if ( ttfAfm == null )
					rf = new RandomAccessFileOrArray ( afmFile, forceRead, Document.plainRandomAccess );
				else
					rf = new RandomAccessFileOrArray ( ttfAfm );
				process ( rf );
			} finally {
				if ( rf != null ) {
					try {
						rf.close ();
					} catch ( Exception ignored ) {
					}
				}
			}
		} else if ( afmFile.toLowerCase ().endsWith ( ".pfm" ) ) {
			try {
				ByteArrayOutputStream ba = new ByteArrayOutputStream ();
				if ( ttfAfm == null )
					rf = new RandomAccessFileOrArray ( afmFile, forceRead, Document.plainRandomAccess );
				else
					rf = new RandomAccessFileOrArray ( ttfAfm );
				Pfm2afm.convert ( rf, ba );
				rf.close ();
				rf = new RandomAccessFileOrArray ( ba.toByteArray () );
				process ( rf );
			} finally {
				if ( rf != null ) {
					try {
						rf.close ();
					} catch ( Exception ignored ) {
					}
				}
			}
		} else
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "1.is.not.an.afm.or.pfm.font.file", afmFile ) );

		EncodingScheme = EncodingScheme.trim ();
		if ( EncodingScheme.equals ( "AdobeStandardEncoding" ) || EncodingScheme.equals ( "StandardEncoding" ) ) {
			fontSpecific = false;
		}
		if ( !encoding.startsWith ( "#" ) )
			PdfEncodings.convertToBytes ( " ", enc );
		createEncoding ();
	}

	@Override
	int getRawWidth ( int c, String name ) {
		Object[] metrics;
		if ( name == null ) {
			metrics = CharMetrics.get ( c );
		} else {
			if ( name.equals ( ".notdef" ) )
				return 0;
			metrics = CharMetrics.get ( name );
		}
		if ( metrics != null )
			return (Integer) metrics[1];
		return 0;
	}

	public void process ( RandomAccessFileOrArray rf ) throws DocumentException, IOException {
		String line;
		boolean isMetrics = false;
		label:
		while ( ( line = rf.readLine () ) != null ) {
			StringTokenizer tok = new StringTokenizer ( line, " ,\n\r\t\f" );
			if ( !tok.hasMoreTokens () )
				continue;
			String ident = tok.nextToken ();
			switch ( ident ) {
			case "FontName":
				FontName = tok.nextToken ( "\u00ff" ).substring ( 1 );
				break;
			case "FullName":
				FullName = tok.nextToken ( "\u00ff" ).substring ( 1 );
				break;
			case "FamilyName":
				FamilyName = tok.nextToken ( "\u00ff" ).substring ( 1 );
				break;
			case "Weight":
				Weight = tok.nextToken ( "\u00ff" ).substring ( 1 );
				break;
			case "ItalicAngle":
				ItalicAngle = Float.parseFloat ( tok.nextToken () );
				break;
			case "IsFixedPitch":
				IsFixedPitch = tok.nextToken ().equals ( "true" );
				break;
			case "CharacterSet":
				tok.nextToken ( "\u00ff" ).substring ( 1 );
				break;
			case "FontBBox":
				llx = (int) Float.parseFloat ( tok.nextToken () );
				lly = (int) Float.parseFloat ( tok.nextToken () );
				urx = (int) Float.parseFloat ( tok.nextToken () );
				ury = (int) Float.parseFloat ( tok.nextToken () );
				break;
			case "UnderlinePosition":
				UnderlinePosition = (int) Float.parseFloat ( tok.nextToken () );
				break;
			case "UnderlineThickness":
				UnderlineThickness = (int) Float.parseFloat ( tok.nextToken () );
				break;
			case "EncodingScheme":
				EncodingScheme = tok.nextToken ( "\u00ff" ).substring ( 1 );
				break;
			case "CapHeight":
				CapHeight = (int) Float.parseFloat ( tok.nextToken () );
				break;
			case "XHeight":
			case "StdHW":
				break;
			case "Ascender":
				Ascender = (int) Float.parseFloat ( tok.nextToken () );
				break;
			case "Descender":
				Descender = (int) Float.parseFloat ( tok.nextToken () );
				break;
			case "StdVW":
				StdVW = (int) Float.parseFloat ( tok.nextToken () );
				break;
			case "StartCharMetrics":
				isMetrics = true;
				break label;
			}
		}
		if ( !isMetrics )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "missing.startcharmetrics.in.1", fileName ) );
		while ( ( line = rf.readLine () ) != null ) {
			StringTokenizer tok = new StringTokenizer ( line );
			if ( !tok.hasMoreTokens () )
				continue;
			String ident = tok.nextToken ();
			if ( ident.equals ( "EndCharMetrics" ) ) {
				isMetrics = false;
				break;
			}
			int C = -1;
			int WX = 250;
			String N = "";
			int[] B = null;

			tok = new StringTokenizer ( line, ";" );
			while ( tok.hasMoreTokens () ) {
				StringTokenizer tokc = new StringTokenizer ( tok.nextToken () );
				if ( !tokc.hasMoreTokens () )
					continue;
				ident = tokc.nextToken ();
				switch ( ident ) {
				case "C":
					C = Integer.parseInt ( tokc.nextToken () );
					break;
				case "WX":
					WX = (int) Float.parseFloat ( tokc.nextToken () );
					break;
				case "N":
					N = tokc.nextToken ();
					break;
				case "B":
					B = new int[] { Integer.parseInt ( tokc.nextToken () ),
									Integer.parseInt ( tokc.nextToken () ),
									Integer.parseInt ( tokc.nextToken () ),
									Integer.parseInt ( tokc.nextToken () ) };
					break;
				}
			}
			Object[] metrics = new Object[] { C, WX, N, B };
			if ( C >= 0 )
				CharMetrics.put ( C, metrics );
			CharMetrics.put ( N, metrics );
		}
		if ( isMetrics )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "missing.endcharmetrics.in.1", fileName ) );
		if ( !CharMetrics.containsKey ( "nonbreakingspace" ) ) {
			Object[] space = CharMetrics.get ( "space" );
			if ( space != null )
				CharMetrics.put ( "nonbreakingspace", space );
		}
		while ( ( line = rf.readLine () ) != null ) {
			StringTokenizer tok = new StringTokenizer ( line );
			if ( !tok.hasMoreTokens () )
				continue;
			String ident = tok.nextToken ();
			if ( ident.equals ( "EndFontMetrics" ) )
				return;
			if ( ident.equals ( "StartKernPairs" ) ) {
				isMetrics = true;
				break;
			}
		}
		if ( !isMetrics )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "missing.endfontmetrics.in.1", fileName ) );
		while ( ( line = rf.readLine () ) != null ) {
			StringTokenizer tok = new StringTokenizer ( line );
			if ( !tok.hasMoreTokens () )
				continue;
			String ident = tok.nextToken ();
			if ( ident.equals ( "KPX" ) ) {
				String first = tok.nextToken ();
				String second = tok.nextToken ();
				int width = (int) Float.parseFloat ( tok.nextToken () );
				Object[] relates = KernPairs.get ( first );
				if ( relates == null )
					KernPairs.put ( first, new Object[] { second, width } );
				else {
					int n = relates.length;
					Object[] relates2 = new Object[n + 2];
					System.arraycopy ( relates, 0, relates2, 0, n );
					relates2[n] = second;
					relates2[n + 1] = width;
					KernPairs.put ( first, relates2 );
				}
			} else if ( ident.equals ( "EndKernPairs" ) ) {
				isMetrics = false;
				break;
			}
		}
		if ( isMetrics )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "missing.endkernpairs.in.1", fileName ) );
		rf.close ();
	}

	@Override
	public PdfStream getFullFontStream () throws DocumentException {
		if ( builtinFont || !embedded )
			return null;
		RandomAccessFileOrArray rf = null;
		try {
			String filePfb = fileName.substring ( 0, fileName.length () - 3 ) + "pfb";
			if ( pfb == null )
				rf = new RandomAccessFileOrArray ( filePfb, true, Document.plainRandomAccess );
			else
				rf = new RandomAccessFileOrArray ( pfb );
			int fileLength = (int) rf.length ();
			byte[] st = new byte[fileLength - 18];
			int[] lengths = new int[3];
			int bytePtr = 0;
			for ( int k = 0; k < 3; ++k ) {
				if ( rf.read () != 0x80 )
					throw new DocumentException ( MessageLocalization.getComposedMessage ( "start.marker.missing.in.1", filePfb ) );
				if ( rf.read () != PFB_TYPES[k] )
					throw new DocumentException ( MessageLocalization.getComposedMessage ( "incorrect.segment.type.in.1", filePfb ) );
				int size = rf.read ();
				size += rf.read () << 8;
				size += rf.read () << 16;
				size += rf.read () << 24;
				lengths[k] = size;
				while ( size != 0 ) {
					int got = rf.read ( st, bytePtr, size );
					if ( got < 0 )
						throw new DocumentException ( MessageLocalization.getComposedMessage ( "premature.end.in.1", filePfb ) );
					bytePtr += got;
					size -= got;
				}
			}
			return new StreamFont ( st, lengths, compressionLevel );
		} catch ( Exception e ) {
			throw new DocumentException ( e );
		} finally {
			if ( rf != null ) {
				try {
					rf.close ();
				} catch ( Exception ignored ) {
				}
			}
		}
	}

	private PdfDictionary getFontDescriptor ( PdfIndirectReference fontStream ) {
		if ( builtinFont )
			return null;
		PdfDictionary dic = new PdfDictionary ( PdfName.FONTDESCRIPTOR );
		dic.put ( PdfName.ASCENT, new PdfNumber ( Ascender ) );
		dic.put ( PdfName.CAPHEIGHT, new PdfNumber ( CapHeight ) );
		dic.put ( PdfName.DESCENT, new PdfNumber ( Descender ) );
		dic.put ( PdfName.FONTBBOX, new PdfRectangle ( llx, lly, urx, ury ) );
		dic.put ( PdfName.FONTNAME, new PdfName ( FontName ) );
		dic.put ( PdfName.ITALICANGLE, new PdfNumber ( ItalicAngle ) );
		dic.put ( PdfName.STEMV, new PdfNumber ( StdVW ) );
		if ( fontStream != null )
			dic.put ( PdfName.FONTFILE, fontStream );
		int flags = 0;
		if ( IsFixedPitch )
			flags |= 1;
		flags |= fontSpecific ? 4 : 32;
		if ( ItalicAngle < 0 )
			flags |= 64;
		if ( FontName.contains ( "Caps" ) || FontName.endsWith ( "SC" ) )
			flags |= 131072;
		if ( Weight.equals ( "Bold" ) )
			flags |= 262144;
		dic.put ( PdfName.FLAGS, new PdfNumber ( flags ) );

		return dic;
	}

	private PdfDictionary getFontBaseType ( PdfIndirectReference fontDescriptor, int firstChar, int lastChar, byte[] shortTag ) {
		PdfDictionary dic = new PdfDictionary ( PdfName.FONT );
		dic.put ( PdfName.SUBTYPE, PdfName.TYPE1 );
		dic.put ( PdfName.BASEFONT, new PdfName ( FontName ) );
		boolean stdEncoding = encoding.equals ( "Cp1252" ) || encoding.equals ( "MacRoman" );
		if ( !fontSpecific || specialMap != null ) {
			for ( int k = firstChar; k <= lastChar; ++k ) {
				if ( !differences[k].equals ( notdef ) ) {
					firstChar = k;
					break;
				}
			}
			if ( stdEncoding )
				dic.put ( PdfName.ENCODING, encoding.equals ( "Cp1252" ) ? PdfName.WIN_ANSI_ENCODING : PdfName.MAC_ROMAN_ENCODING );
			else {
				PdfDictionary enc = new PdfDictionary ( PdfName.ENCODING );
				PdfArray dif = new PdfArray ();
				boolean gap = true;
				for ( int k = firstChar; k <= lastChar; ++k ) {
					if ( shortTag[k] != 0 ) {
						if ( gap ) {
							dif.add ( new PdfNumber ( k ) );
							gap = false;
						}
						dif.add ( new PdfName ( differences[k] ) );
					} else
						gap = true;
				}
				enc.put ( PdfName.DIFFERENCES, dif );
				dic.put ( PdfName.ENCODING, enc );
			}
		}
		if ( specialMap != null || forceWidthsOutput || !( builtinFont && ( fontSpecific || stdEncoding ) ) ) {
			dic.put ( PdfName.FIRSTCHAR, new PdfNumber ( firstChar ) );
			dic.put ( PdfName.LASTCHAR, new PdfNumber ( lastChar ) );
			PdfArray wd = new PdfArray ();
			for ( int k = firstChar; k <= lastChar; ++k ) {
				if ( shortTag[k] == 0 )
					wd.add ( new PdfNumber ( 0 ) );
				else
					wd.add ( new PdfNumber ( widths[k] ) );
			}
			dic.put ( PdfName.WIDTHS, wd );
		}
		if ( !builtinFont && fontDescriptor != null )
			dic.put ( PdfName.FONTDESCRIPTOR, fontDescriptor );
		return dic;
	}

	@Override
	void writeFont ( PdfWriter writer, PdfIndirectReference ref, Object[] params ) throws DocumentException, IOException {
		int firstChar = (Integer) params[0];
		int lastChar = (Integer) params[1];
		byte[] shortTag = (byte[]) params[2];
		boolean subsetp = (Boolean) params[3] && subset;
		if ( !subsetp ) {
			firstChar = 0;
			lastChar = shortTag.length - 1;
			Arrays.fill ( shortTag, (byte) 1 );
		}
		PdfIndirectReference ind_font = null;
		PdfObject pobj;
		PdfIndirectObject obj;
		pobj = getFullFontStream ();
		if ( pobj != null ) {
			obj = writer.addToBody ( pobj );
			ind_font = obj.getIndirectReference ();
		}
		pobj = getFontDescriptor ( ind_font );
		if ( pobj != null ) {
			obj = writer.addToBody ( pobj );
			ind_font = obj.getIndirectReference ();
		}
		pobj = getFontBaseType ( ind_font, firstChar, lastChar, shortTag );
		writer.addToBody ( pobj, ref );
	}

	@Override
	public float getFontDescriptor ( int key, float fontSize ) {
		switch ( key ) {
		case AWT_ASCENT:
		case ASCENT:
			return Ascender * fontSize / 1000;
		case CAPHEIGHT:
			return CapHeight * fontSize / 1000;
		case AWT_DESCENT:
		case DESCENT:
			return Descender * fontSize / 1000;
		case ITALICANGLE:
			return ItalicAngle;
		case BBOXLLX:
			return llx * fontSize / 1000;
		case BBOXLLY:
			return lly * fontSize / 1000;
		case BBOXURX:
			return urx * fontSize / 1000;
		case BBOXURY:
			return ury * fontSize / 1000;
		case AWT_LEADING:
			return 0;
		case AWT_MAXADVANCE:
			return ( urx - llx ) * fontSize / 1000;
		case UNDERLINE_POSITION:
			return UnderlinePosition * fontSize / 1000;
		case UNDERLINE_THICKNESS:
			return UnderlineThickness * fontSize / 1000;
		}
		return 0;
	}

	@Override
	public String getPostscriptFontName () {
		return FontName;
	}

	@Override
	public String[][] getFullFontName () {
		return new String[][] { { "", "", "", FullName } };
	}

	@Override
	public String[][] getFamilyFontName () {
		return new String[][] { { "", "", "", FamilyName } };
	}

	@Override
	protected int[] getRawCharBBox ( int c, String name ) {
		Object[] metrics;
		if ( name == null ) {
			metrics = CharMetrics.get ( c );
		} else {
			if ( name.equals ( ".notdef" ) )
				return null;
			metrics = CharMetrics.get ( name );
		}
		if ( metrics != null )
			return (int[]) metrics[3];
		return null;
	}

}

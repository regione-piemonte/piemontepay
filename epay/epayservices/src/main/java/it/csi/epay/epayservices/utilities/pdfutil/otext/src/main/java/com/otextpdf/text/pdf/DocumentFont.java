/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Utilities;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io.RandomAccessSourceFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps.CMapParserEx;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps.CMapToUnicode;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps.CidLocationFromByte;

import java.util.HashMap;
import java.util.Map;


public class DocumentFont extends BaseFont {

	private static final int[] stdEnc = {
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					32, 33, 34, 35, 36, 37, 38, 8217, 40, 41, 42, 43, 44, 45, 46, 47,
					48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63,
					64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79,
					80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95,
					8216, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111,
					112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 161, 162, 163, 8260, 165, 402, 167, 164, 39, 8220, 171, 8249, 8250, 64257, 64258,
					0, 8211, 8224, 8225, 183, 0, 182, 8226, 8218, 8222, 8221, 187, 8230, 8240, 0, 191,
					0, 96, 180, 710, 732, 175, 728, 729, 168, 0, 730, 184, 0, 733, 731, 711,
					8212, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 198, 0, 170, 0, 0, 0, 0, 321, 216, 338, 186, 0, 0, 0, 0,
					0, 230, 0, 0, 0, 305, 0, 0, 322, 248, 339, 223, 0, 0, 0, 0 };

	// code, [glyph, width]
	private final HashMap<Integer, int[]> metrics = new HashMap<> ();

	private final PRIndirectReference refFont;

	private final PdfDictionary font;

	private final IntHashtable uni2byte = new IntHashtable ();

	private final IntHashtable byte2uni = new IntHashtable ();

	protected boolean isType0 = false;

	protected int defaultWidth = 1000;

	protected String cjkEncoding;

	protected String uniMap;

	private String fontName;

	private IntHashtable diffmap;

	private float ascender = 800;

	private float capHeight = 700;

	private float descender = -200;

	private float italicAngle = 0;

	private float fontWeight = 0;

	private float llx = -50;

	private float lly = -200;

	private float urx = 100;

	private float ury = 900;

	private IntHashtable hMetrics;

	private BaseFont cjkMirror;

	DocumentFont ( PdfDictionary font ) {
		this.refFont = null;
		this.font = font;
		init ();
	}

	DocumentFont ( PRIndirectReference refFont ) {
		this.refFont = refFont;
		font = (PdfDictionary) PdfReader.getPdfObject ( refFont );
		init ();
	}

	private void init () {
		encoding = "";
		fontSpecific = false;
		fontType = FONT_TYPE_DOCUMENT;
		PdfName baseFont = font.getAsName ( PdfName.BASEFONT );
		fontName = baseFont != null ? PdfName.decodeName ( baseFont.toString () ) : "Unspecified Font Name";
		PdfName subType = font.getAsName ( PdfName.SUBTYPE );
		if ( PdfName.TYPE1.equals ( subType ) || PdfName.TRUETYPE.equals ( subType ) )
			doType1TT ();
		else {
			PdfName encodingName = font.getAsName ( PdfName.ENCODING );
			if ( encodingName != null ) {
				String enc = PdfName.decodeName ( encodingName.toString () );
				String ffontname = CJKFont.GetCompatibleFont ( enc );
				if ( ffontname != null ) {
					try {
						cjkMirror = BaseFont.createFont ( ffontname, enc, false );
					} catch ( Exception e ) {
						throw new ExceptionConverter ( e );
					}
					cjkEncoding = enc;
					uniMap = ( (CJKFont) cjkMirror ).getUniMap ();
				}
				if ( PdfName.TYPE0.equals ( subType ) ) {
					isType0 = true;
					if ( !enc.equals ( "Identity-H" ) && cjkMirror != null ) {
						PdfArray df = (PdfArray) PdfReader.getPdfObjectRelease ( font.get ( PdfName.DESCENDANTFONTS ) );
						PdfDictionary cidft = (PdfDictionary) PdfReader.getPdfObjectRelease ( df.getPdfObject ( 0 ) );
						PdfNumber dwo = (PdfNumber) PdfReader.getPdfObjectRelease ( cidft.get ( PdfName.DW ) );
						if ( dwo != null )
							defaultWidth = dwo.intValue ();
						hMetrics = readWidths ( (PdfArray) PdfReader.getPdfObjectRelease ( cidft.get ( PdfName.W ) ) );

						PdfDictionary fontDesc = (PdfDictionary) PdfReader.getPdfObjectRelease ( cidft.get ( PdfName.FONTDESCRIPTOR ) );
						fillFontDesc ( fontDesc );
					} else {
						processType0 ( font );
					}
				}
			}
		}
	}

	private void processType0 ( PdfDictionary font ) {
		try {
			PdfObject toUniObject = PdfReader.getPdfObjectRelease ( font.get ( PdfName.TOUNICODE ) );
			PdfArray df = (PdfArray) PdfReader.getPdfObjectRelease ( font.get ( PdfName.DESCENDANTFONTS ) );
			PdfDictionary cidft = (PdfDictionary) PdfReader.getPdfObjectRelease ( df.getPdfObject ( 0 ) );
			PdfNumber dwo = (PdfNumber) PdfReader.getPdfObjectRelease ( cidft.get ( PdfName.DW ) );
			int dw = 1000;
			if ( dwo != null )
				dw = dwo.intValue ();
			IntHashtable widths = readWidths ( (PdfArray) PdfReader.getPdfObjectRelease ( cidft.get ( PdfName.W ) ) );
			PdfDictionary fontDesc = (PdfDictionary) PdfReader.getPdfObjectRelease ( cidft.get ( PdfName.FONTDESCRIPTOR ) );
			fillFontDesc ( fontDesc );
			if ( toUniObject instanceof PRStream ) {
				fillMetrics ( PdfReader.getStreamBytes ( (PRStream) toUniObject ), widths, dw );
			}

		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
	}

	private IntHashtable readWidths ( PdfArray ws ) {
		IntHashtable hh = new IntHashtable ();
		if ( ws == null )
			return hh;
		for ( int k = 0; k < ws.size (); ++k ) {
			int c1 = ( (PdfNumber) PdfReader.getPdfObjectRelease ( ws.getPdfObject ( k ) ) ).intValue ();
			PdfObject obj = PdfReader.getPdfObjectRelease ( ws.getPdfObject ( ++k ) );
			if ( obj.isArray () ) {
				PdfArray a2 = (PdfArray) obj;
				for ( int j = 0; j < a2.size (); ++j ) {
					int c2 = ( (PdfNumber) PdfReader.getPdfObjectRelease ( a2.getPdfObject ( j ) ) ).intValue ();
					hh.put ( c1++, c2 );
				}
			} else {
				int c2 = ( (PdfNumber) obj ).intValue ();
				int w = ( (PdfNumber) PdfReader.getPdfObjectRelease ( ws.getPdfObject ( ++k ) ) ).intValue ();
				for ( ; c1 <= c2; ++c1 )
					hh.put ( c1, w );
			}
		}
		return hh;
	}

	private String decodeString ( PdfString ps ) {
		if ( ps.isHexWriting () )
			return PdfEncodings.convertToString ( ps.getBytes (), "UnicodeBigUnmarked" );
		else
			return ps.toUnicodeString ();
	}

	private void fillMetrics ( byte[] touni, IntHashtable widths, int dw ) {
		try {
			PdfContentParser ps = new PdfContentParser (
							new PRTokeniser ( new RandomAccessFileOrArray ( new RandomAccessSourceFactory ().createSource ( touni ) ) ) );
			PdfObject ob;
			boolean notFound = true;
			int nestLevel = 0;
			int maxExc = 50;
			while ( ( notFound || nestLevel > 0 ) ) {
				try {
					ob = ps.readPRObject ();
				} catch ( Exception ex ) {
					if ( --maxExc < 0 )
						break;
					continue;
				}
				if ( ob == null )
					break;
				if ( ob.type () == PdfContentParser.COMMAND_TYPE ) {
					if ( ob.toString ().equals ( "begin" ) ) {
						notFound = false;
						nestLevel++;
					} else if ( ob.toString ().equals ( "end" ) ) {
						nestLevel--;
					} else if ( ob.toString ().equals ( "beginbfchar" ) ) {
						while ( true ) {
							PdfObject nx = ps.readPRObject ();
							if ( nx.toString ().equals ( "endbfchar" ) )
								break;
							String cid = decodeString ( (PdfString) nx );
							String uni = decodeString ( (PdfString) ps.readPRObject () );
							if ( uni.length () == 1 ) {
								int cidc = cid.charAt ( 0 );
								int unic = uni.charAt ( 0 );
								int w = dw;
								if ( widths.containsKey ( cidc ) )
									w = widths.get ( cidc );
								metrics.put ( unic, new int[] { cidc, w } );
							}
						}
					} else if ( ob.toString ().equals ( "beginbfrange" ) ) {
						while ( true ) {
							PdfObject nx = ps.readPRObject ();
							if ( nx.toString ().equals ( "endbfrange" ) )
								break;
							String cid1 = decodeString ( (PdfString) nx );
							String cid2 = decodeString ( (PdfString) ps.readPRObject () );
							int cid1c = cid1.charAt ( 0 );
							int cid2c = cid2.charAt ( 0 );
							PdfObject ob2 = ps.readPRObject ();
							if ( ob2.isString () ) {
								String uni = decodeString ( (PdfString) ob2 );
								if ( uni.length () == 1 ) {
									int unic = uni.charAt ( 0 );
									for ( ; cid1c <= cid2c; cid1c++, unic++ ) {
										int w = dw;
										if ( widths.containsKey ( cid1c ) )
											w = widths.get ( cid1c );
										metrics.put ( unic, new int[] { cid1c, w } );
									}
								}
							} else {
								PdfArray a = (PdfArray) ob2;
								for ( int j = 0; j < a.size (); ++j, ++cid1c ) {
									String uni = decodeString ( a.getAsString ( j ) );
									if ( uni.length () == 1 ) {
										int unic = uni.charAt ( 0 );
										int w = dw;
										if ( widths.containsKey ( cid1c ) )
											w = widths.get ( cid1c );
										metrics.put ( unic, new int[] { cid1c, w } );
									}
								}
							}
						}
					}
				}
			}
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
	}

	private void doType1TT () {
		CMapToUnicode toUnicode = null;
		PdfObject enc = PdfReader.getPdfObject ( font.get ( PdfName.ENCODING ) );
		if ( enc == null ) {
			fillEncoding ( null );
			try {
				toUnicode = processToUnicode ();
				if ( toUnicode != null ) {
					Map<Integer, Integer> rm = toUnicode.createReverseMapping ();
					for ( Map.Entry<Integer, Integer> kv : rm.entrySet () ) {
						uni2byte.put ( kv.getKey (), kv.getValue () );
						byte2uni.put ( kv.getValue (), kv.getKey () );
					}
				}
			} catch ( Exception ex ) {
				throw new ExceptionConverter ( ex );
			}
		} else {
			if ( enc.isName () )
				fillEncoding ( (PdfName) enc );
			else if ( enc.isDictionary () ) {
				PdfDictionary encDic = (PdfDictionary) enc;
				enc = PdfReader.getPdfObject ( encDic.get ( PdfName.BASEENCODING ) );
				if ( enc == null )
					fillEncoding ( null );
				else
					fillEncoding ( (PdfName) enc );
				PdfArray diffs = encDic.getAsArray ( PdfName.DIFFERENCES );
				if ( diffs != null ) {
					diffmap = new IntHashtable ();
					int currentNumber = 0;
					for ( int k = 0; k < diffs.size (); ++k ) {
						PdfObject obj = diffs.getPdfObject ( k );
						if ( obj.isNumber () )
							currentNumber = ( (PdfNumber) obj ).intValue ();
						else {
							int[] c = GlyphList.nameToUnicode ( PdfName.decodeName ( obj.toString () ) );
							if ( c != null && c.length > 0 ) {
								uni2byte.put ( c[0], currentNumber );
								byte2uni.put ( currentNumber, c[0] );
								diffmap.put ( c[0], currentNumber );
							} else {
								if ( toUnicode == null ) {
									toUnicode = processToUnicode ();
									if ( toUnicode == null ) {
										toUnicode = new CMapToUnicode ();
									}
								}
								final String unicode = toUnicode.lookup ( new byte[] { (byte) currentNumber }, 0, 1 );
								if ( ( unicode != null ) && ( unicode.length () == 1 ) ) {
									this.uni2byte.put ( unicode.charAt ( 0 ), currentNumber );
									this.byte2uni.put ( currentNumber, unicode.charAt ( 0 ) );
									this.diffmap.put ( unicode.charAt ( 0 ), currentNumber );
								}
							}
							++currentNumber;
						}
					}
				}
			}
		}
		PdfArray newWidths = font.getAsArray ( PdfName.WIDTHS );
		PdfNumber first = font.getAsNumber ( PdfName.FIRSTCHAR );
		PdfNumber last = font.getAsNumber ( PdfName.LASTCHAR );
		if ( BuiltinFonts14.containsKey ( fontName ) ) {
			BaseFont bf;
			try {
				bf = BaseFont.createFont ( fontName, WINANSI, false );
			} catch ( Exception e ) {
				throw new ExceptionConverter ( e );
			}
			int[] e = uni2byte.toOrderedKeys ();
			for ( int i : e ) {
				int n = uni2byte.get ( i );
				widths[n] = bf.getRawWidth ( n, GlyphList.unicodeToName ( i ) );
			}
			if ( diffmap != null ) { //widths for diffmap must override existing ones
				e = diffmap.toOrderedKeys ();
				for ( int i : e ) {
					int n = diffmap.get ( i );
					widths[n] = bf.getRawWidth ( n, GlyphList.unicodeToName ( i ) );
				}
				diffmap = null;
			}
			ascender = bf.getFontDescriptor ( ASCENT, 1000 );
			capHeight = bf.getFontDescriptor ( CAPHEIGHT, 1000 );
			descender = bf.getFontDescriptor ( DESCENT, 1000 );
			italicAngle = bf.getFontDescriptor ( ITALICANGLE, 1000 );
			fontWeight = bf.getFontDescriptor ( FONT_WEIGHT, 1000 );
			llx = bf.getFontDescriptor ( BBOXLLX, 1000 );
			lly = bf.getFontDescriptor ( BBOXLLY, 1000 );
			urx = bf.getFontDescriptor ( BBOXURX, 1000 );
			ury = bf.getFontDescriptor ( BBOXURY, 1000 );
		}
		if ( first != null && last != null && newWidths != null ) {
			int f = first.intValue ();
			int nSize = f + newWidths.size ();
			if ( widths.length < nSize ) {
				int[] tmp = new int[nSize];
				System.arraycopy ( widths, 0, tmp, 0, f );
				widths = tmp;
			}
			for ( int k = 0; k < newWidths.size (); ++k ) {
				widths[f + k] = newWidths.getAsNumber ( k ).intValue ();
			}
		}
		fillFontDesc ( font.getAsDict ( PdfName.FONTDESCRIPTOR ) );
	}

	private CMapToUnicode processToUnicode () {
		CMapToUnicode cmapRet = null;
		PdfObject toUni = PdfReader.getPdfObjectRelease ( this.font.get ( PdfName.TOUNICODE ) );
		if ( toUni instanceof PRStream ) {
			try {
				byte[] touni = PdfReader.getStreamBytes ( (PRStream) toUni );
				CidLocationFromByte lb = new CidLocationFromByte ( touni );
				cmapRet = new CMapToUnicode ();
				CMapParserEx.parseCid ( "", cmapRet, lb );
			} catch ( Exception e ) {
				cmapRet = null;
			}
		}
		return cmapRet;
	}

	private void fillFontDesc ( PdfDictionary fontDesc ) {
		if ( fontDesc == null )
			return;
		PdfNumber v = fontDesc.getAsNumber ( PdfName.ASCENT );
		if ( v != null )
			ascender = v.floatValue ();
		v = fontDesc.getAsNumber ( PdfName.CAPHEIGHT );
		if ( v != null )
			capHeight = v.floatValue ();
		v = fontDesc.getAsNumber ( PdfName.DESCENT );
		if ( v != null )
			descender = v.floatValue ();
		v = fontDesc.getAsNumber ( PdfName.ITALICANGLE );
		if ( v != null )
			italicAngle = v.floatValue ();
		v = fontDesc.getAsNumber ( PdfName.FONTWEIGHT );
		if ( v != null ) {
			fontWeight = v.floatValue ();
		}
		PdfArray bbox = fontDesc.getAsArray ( PdfName.FONTBBOX );
		if ( bbox != null ) {
			llx = bbox.getAsNumber ( 0 ).floatValue ();
			lly = bbox.getAsNumber ( 1 ).floatValue ();
			urx = bbox.getAsNumber ( 2 ).floatValue ();
			ury = bbox.getAsNumber ( 3 ).floatValue ();
			if ( llx > urx ) {
				float t = llx;
				llx = urx;
				urx = t;
			}
			if ( lly > ury ) {
				float t = lly;
				lly = ury;
				ury = t;
			}
		}
		float maxAscent = Math.max ( ury, ascender );
		float minDescent = Math.min ( lly, descender );
		ascender = maxAscent * 1000 / ( maxAscent - minDescent );
		descender = minDescent * 1000 / ( maxAscent - minDescent );
	}

	private void fillEncoding ( PdfName encoding ) {
		if ( encoding == null && isSymbolic () ) {
			for ( int k = 0; k < 256; ++k ) {
				uni2byte.put ( k, k );
				byte2uni.put ( k, k );
			}
		} else if ( PdfName.MAC_ROMAN_ENCODING.equals ( encoding ) || PdfName.WIN_ANSI_ENCODING.equals ( encoding ) ) {
			byte[] b = new byte[256];
			for ( int k = 0; k < 256; ++k )
				b[k] = (byte) k;
			String enc = WINANSI;
			if ( PdfName.MAC_ROMAN_ENCODING.equals ( encoding ) )
				enc = MACROMAN;
			String cv = PdfEncodings.convertToString ( b, enc );
			char[] arr = cv.toCharArray ();
			for ( int k = 0; k < 256; ++k ) {
				uni2byte.put ( arr[k], k );
				byte2uni.put ( k, arr[k] );
			}
		} else {
			for ( int k = 0; k < 256; ++k ) {
				uni2byte.put ( stdEnc[k], k );
				byte2uni.put ( k, stdEnc[k] );
			}
		}
	}

	@Override
	public String[][] getFamilyFontName () {
		return getFullFontName ();
	}

	@Override
	public float getFontDescriptor ( int key, float fontSize ) {
		if ( cjkMirror != null )
			return cjkMirror.getFontDescriptor ( key, fontSize );
		switch ( key ) {
		case AWT_ASCENT:
		case ASCENT:
			return ascender * fontSize / 1000;
		case CAPHEIGHT:
			return capHeight * fontSize / 1000;
		case AWT_DESCENT:
		case DESCENT:
			return descender * fontSize / 1000;
		case ITALICANGLE:
			return italicAngle;
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
		case FONT_WEIGHT:
			return fontWeight * fontSize / 1000;
		}
		return 0;
	}

	@Override
	public String[][] getFullFontName () {
		return new String[][] { { "", "", "", fontName } };
	}

	@Override
	public String getPostscriptFontName () {
		return fontName;
	}

	@Override
	int getRawWidth ( int c, String name ) {
		return 0;
	}

	@Override
	void writeFont ( PdfWriter writer, PdfIndirectReference ref, Object[] params ) {
	}

	@Override
	public PdfStream getFullFontStream () {
		return null;
	}

	@Override
	public int getWidth ( int char1 ) {
		if ( isType0 ) {
			if ( hMetrics != null && cjkMirror != null && !cjkMirror.isVertical () ) {
				int c = cjkMirror.getCidCode ( char1 );
				int v = hMetrics.get ( c );
				if ( v > 0 )
					return v;
				else
					return defaultWidth;
			} else {
				int[] ws = metrics.get ( char1 );
				if ( ws != null )
					return ws[1];
				else
					return 0;
			}
		}
		if ( cjkMirror != null )
			return cjkMirror.getWidth ( char1 );
		return super.getWidth ( char1 );
	}

	@Override
	public int getWidth ( String text ) {
		if ( isType0 ) {
			int total = 0;
			if ( hMetrics != null && cjkMirror != null && !cjkMirror.isVertical () ) {
				if ( ( (CJKFont) cjkMirror ).isIdentity () ) {
					for ( int k = 0; k < text.length (); ++k ) {
						total += getWidth ( text.charAt ( k ) );
					}
				} else {
					for ( int k = 0; k < text.length (); ++k ) {
						int val;
						if ( Utilities.isSurrogatePair ( text, k ) ) {
							val = Utilities.convertToUtf32 ( text, k );
							k++;
						} else {
							val = text.charAt ( k );
						}
						total += getWidth ( val );
					}
				}
			} else {
				char[] chars = text.toCharArray ();
				for ( char aChar : chars ) {
					int[] ws = metrics.get ( (int) aChar );
					if ( ws != null )
						total += ws[1];
				}
			}
			return total;
		}
		if ( cjkMirror != null )
			return cjkMirror.getWidth ( text );
		return super.getWidth ( text );
	}

	@Override
	public byte[] convertToBytes ( String text ) {
		if ( cjkMirror != null )
			return cjkMirror.convertToBytes ( text );
		else if ( isType0 ) {
			char[] chars = text.toCharArray ();
			int len = chars.length;
			byte[] b = new byte[len * 2];
			int bptr = 0;
			for ( char aChar : chars ) {
				int[] ws = metrics.get ( (int) aChar );
				if ( ws != null ) {
					int g = ws[0];
					b[bptr++] = (byte) ( g / 256 );
					b[bptr++] = (byte) g;
				}
			}
			if ( bptr == b.length )
				return b;
			else {
				byte[] nb = new byte[bptr];
				System.arraycopy ( b, 0, nb, 0, bptr );
				return nb;
			}
		} else {
			char[] cc = text.toCharArray ();
			byte[] b = new byte[cc.length];
			int ptr = 0;
			for ( char c : cc ) {
				if ( uni2byte.containsKey ( c ) )
					b[ptr++] = (byte) uni2byte.get ( c );
			}
			if ( ptr == b.length )
				return b;
			else {
				byte[] b2 = new byte[ptr];
				System.arraycopy ( b, 0, b2, 0, ptr );
				return b2;
			}
		}
	}

	@Override
	byte[] convertToBytes ( int char1 ) {
		if ( cjkMirror != null )
			return cjkMirror.convertToBytes ( char1 );
		else if ( isType0 ) {
			int[] ws = metrics.get ( char1 );
			if ( ws != null ) {
				int g = ws[0];
				return new byte[] { (byte) ( g / 256 ), (byte) g };
			} else
				return new byte[0];
		} else {
			if ( uni2byte.containsKey ( char1 ) )
				return new byte[] { (byte) uni2byte.get ( char1 ) };
			else
				return new byte[0];
		}
	}

	PdfIndirectReference getIndirectReference () {
		if ( refFont == null )
			throw new IllegalArgumentException ( "Font reuse not allowed with direct font objects." );
		return refFont;
	}

	@Override
	public boolean charExists ( int c ) {
		if ( cjkMirror != null )
			return cjkMirror.charExists ( c );
		else if ( isType0 ) {
			return metrics.containsKey ( c );
		} else
			return super.charExists ( c );
	}

	@Override
	protected int[] getRawCharBBox ( int c, String name ) {
		return null;
	}

	@Override
	public boolean isVertical () {
		if ( cjkMirror != null )
			return cjkMirror.isVertical ();
		else
			return super.isVertical ();
	}

	IntHashtable getByte2Uni () {
		return byte2uni;
	}

	IntHashtable getDiffmap () {
		return diffmap;
	}

	boolean isSymbolic () {
		PdfDictionary fontDescriptor = font.getAsDict ( PdfName.FONTDESCRIPTOR );
		if ( fontDescriptor == null )
			return false;
		PdfNumber flags = fontDescriptor.getAsNumber ( PdfName.FLAGS );
		if ( flags == null )
			return false;
		return ( flags.intValue () & 0x04 ) != 0;
	}
}

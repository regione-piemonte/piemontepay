/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Rectangle;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io.RandomAccessSourceFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl.Base64;
import org.w3c.dom.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AcroFields {

	public static final int DA_FONT = 0;

	public static final int DA_SIZE = 1;

	public static final int DA_COLOR = 2;

	public static final int FIELD_TYPE_NONE = 0;

	public static final int FIELD_TYPE_PUSHBUTTON = 1;

	public static final int FIELD_TYPE_CHECKBOX = 2;

	public static final int FIELD_TYPE_RADIOBUTTON = 3;

	public static final int FIELD_TYPE_TEXT = 4;

	public static final int FIELD_TYPE_LIST = 5;

	public static final int FIELD_TYPE_COMBO = 6;

	public static final int FIELD_TYPE_SIGNATURE = 7;

	private static final HashMap<String, String[]> stdFieldFontNames = new HashMap<> ();

	private static final PdfName[] buttonRemove = { PdfName.MK, PdfName.F, PdfName.FF, PdfName.Q, PdfName.BS, PdfName.BORDER };

	static {
		stdFieldFontNames.put ( "CoBO", new String[] { "Courier-BoldOblique" } );
		stdFieldFontNames.put ( "CoBo", new String[] { "Courier-Bold" } );
		stdFieldFontNames.put ( "CoOb", new String[] { "Courier-Oblique" } );
		stdFieldFontNames.put ( "Cour", new String[] { "Courier" } );
		stdFieldFontNames.put ( "HeBO", new String[] { "Helvetica-BoldOblique" } );
		stdFieldFontNames.put ( "HeBo", new String[] { "Helvetica-Bold" } );
		stdFieldFontNames.put ( "HeOb", new String[] { "Helvetica-Oblique" } );
		stdFieldFontNames.put ( "Helv", new String[] { "Helvetica" } );
		stdFieldFontNames.put ( "Symb", new String[] { "Symbol" } );
		stdFieldFontNames.put ( "TiBI", new String[] { "Times-BoldItalic" } );
		stdFieldFontNames.put ( "TiBo", new String[] { "Times-Bold" } );
		stdFieldFontNames.put ( "TiIt", new String[] { "Times-Italic" } );
		stdFieldFontNames.put ( "TiRo", new String[] { "Times-Roman" } );
		stdFieldFontNames.put ( "ZaDb", new String[] { "ZapfDingbats" } );
		stdFieldFontNames.put ( "HySm", new String[] { "HYSMyeongJo-Medium", "UniKS-UCS2-H" } );
		stdFieldFontNames.put ( "HyGo", new String[] { "HYGoThic-Medium", "UniKS-UCS2-H" } );
		stdFieldFontNames.put ( "KaGo", new String[] { "HeiseiKakuGo-W5", "UniKS-UCS2-H" } );
		stdFieldFontNames.put ( "KaMi", new String[] { "HeiseiMin-W3", "UniJIS-UCS2-H" } );
		stdFieldFontNames.put ( "MHei", new String[] { "MHei-Medium", "UniCNS-UCS2-H" } );
		stdFieldFontNames.put ( "MSun", new String[] { "MSung-Light", "UniCNS-UCS2-H" } );
		stdFieldFontNames.put ( "STSo", new String[] { "STSong-Light", "UniGB-UCS2-H" } );
	}

	private final HashMap<Integer, BaseFont> extensionFonts = new HashMap<> ();

	private final XfaForm xfa;

	private final HashMap<String, BaseFont> localFonts = new HashMap<> ();

	PdfReader reader;

	PdfWriter writer;

	Map<String, Item> fields;

	private int topFirst;

	private boolean append;

	private boolean generateAppearances = true;

	private float extraMarginLeft;

	private float extraMarginTop;

	private ArrayList<BaseFont> substitutionFonts;

	private Map<String, TextField> fieldCache;

	AcroFields ( PdfReader reader, PdfWriter writer ) {
		this.reader = reader;
		this.writer = writer;
		try {
			xfa = new XfaForm ( reader );
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
		if ( writer instanceof PdfStamperImp ) {
			append = ( (PdfStamperImp) writer ).isAppend ();
		}
		fill ();
	}

	public static Object[] splitDAelements ( String da ) {
		try {
			PRTokeniser tk = new PRTokeniser (
							new RandomAccessFileOrArray ( new RandomAccessSourceFactory ().createSource ( PdfEncodings.convertToBytes ( da, null ) ) ) );
			ArrayList<String> stack = new ArrayList<> ();
			Object[] ret = new Object[3];
			while ( tk.nextToken () ) {
				if ( tk.getTokenType () == PRTokeniser.TokenType.COMMENT )
					continue;
				if ( tk.getTokenType () == PRTokeniser.TokenType.OTHER ) {
					String operator = tk.getStringValue ();
					switch ( operator ) {
					case "Tf":
						if ( stack.size () >= 2 ) {
							ret[DA_FONT] = stack.get ( stack.size () - 2 );
							ret[DA_SIZE] = new Float ( stack.get ( stack.size () - 1 ) );
						}
						break;
					case "g":
						if ( !stack.isEmpty () ) {
							float gray = new Float ( stack.get ( stack.size () - 1 ) );
							if ( gray != 0 )
								ret[DA_COLOR] = new GrayColor ( gray );
						}
						break;
					case "rg":
						if ( stack.size () >= 3 ) {
							float red = new Float ( stack.get ( stack.size () - 3 ) );
							float green = new Float ( stack.get ( stack.size () - 2 ) );
							float blue = new Float ( stack.get ( stack.size () - 1 ) );
							ret[DA_COLOR] = new BaseColor ( red, green, blue );
						}
						break;
					case "k":
						if ( stack.size () >= 4 ) {
							float cyan = new Float ( stack.get ( stack.size () - 4 ) );
							float magenta = new Float ( stack.get ( stack.size () - 3 ) );
							float yellow = new Float ( stack.get ( stack.size () - 2 ) );
							float black = new Float ( stack.get ( stack.size () - 1 ) );
							ret[DA_COLOR] = new CMYKColor ( cyan, magenta, yellow, black );
						}
						break;
					}
					stack.clear ();
				} else
					stack.add ( tk.getStringValue () );
			}
			return ret;
		} catch ( IOException ioe ) {
			throw new ExceptionConverter ( ioe );
		}
	}

	void fill () {
		fields = new HashMap<> ();
		PdfDictionary top = (PdfDictionary) PdfReader.getPdfObjectRelease ( reader.getCatalog ().get ( PdfName.ACROFORM ) );
		if ( top == null )
			return;
		PdfBoolean needappearances = top.getAsBoolean ( PdfName.NEEDAPPEARANCES );
		setGenerateAppearances ( needappearances == null || !needappearances.booleanValue () );
		PdfArray arrfds = (PdfArray) PdfReader.getPdfObjectRelease ( top.get ( PdfName.FIELDS ) );
		if ( arrfds == null || arrfds.isEmpty () )
			return;
		for ( int k = 1; k <= reader.getNumberOfPages (); ++k ) {
			PdfDictionary page = reader.getPageNRelease ( k );
			PdfArray annots = (PdfArray) PdfReader.getPdfObjectRelease ( page.get ( PdfName.ANNOTS ), page );
			if ( annots == null )
				continue;
			for ( int j = 0; j < annots.size (); ++j ) {
				PdfDictionary annot = annots.getAsDict ( j );
				if ( annot == null ) {
					PdfReader.releaseLastXrefPartial ( annots.getAsIndirectObject ( j ) );
					continue;
				}
				if ( !PdfName.WIDGET.equals ( annot.getAsName ( PdfName.SUBTYPE ) ) ) {
					PdfReader.releaseLastXrefPartial ( annots.getAsIndirectObject ( j ) );
					continue;
				}
				PdfDictionary widget = annot;
				PdfDictionary dic = new PdfDictionary ();
				dic.putAll ( annot );
				StringBuilder name = new StringBuilder ();
				PdfDictionary value = null;
				PdfObject lastV = null;
				while ( annot != null ) {
					dic.mergeDifferent ( annot );
					PdfString t = annot.getAsString ( PdfName.T );
					if ( t != null )
						name.insert ( 0, t.toUnicodeString () + "." );
					if ( lastV == null && annot.get ( PdfName.V ) != null )
						lastV = PdfReader.getPdfObjectRelease ( annot.get ( PdfName.V ) );
					if ( value == null && t != null ) {
						value = annot;
						if ( annot.get ( PdfName.V ) == null && lastV != null )
							value.put ( PdfName.V, lastV );
					}
					annot = annot.getAsDict ( PdfName.PARENT );
				}
				if ( name.length () > 0 )
					name = new StringBuilder ( name.substring ( 0, name.length () - 1 ) );
				Item item = fields.get ( name.toString () );
				if ( item == null ) {
					item = new Item ();
					fields.put ( name.toString (), item );
				}
				if ( value == null )
					item.addValue ( widget );
				else
					item.addValue ( value );
				item.addWidget ( widget );
				item.addWidgetRef ( annots.getAsIndirectObject ( j ) ); // must be a reference
				dic.mergeDifferent ( top );
				item.addMerged ( dic );
				item.addPage ( k );
				item.addTabOrder ( j );
			}
		}

		PdfNumber sigFlags = top.getAsNumber ( PdfName.SIGFLAGS );
		if ( sigFlags == null || ( sigFlags.intValue () & 1 ) != 1 )
			return;
		for ( int j = 0; j < arrfds.size (); ++j ) {
			PdfDictionary annot = arrfds.getAsDict ( j );
			if ( annot == null ) {
				PdfReader.releaseLastXrefPartial ( arrfds.getAsIndirectObject ( j ) );
				continue;
			}
			if ( !PdfName.WIDGET.equals ( annot.getAsName ( PdfName.SUBTYPE ) ) ) {
				PdfReader.releaseLastXrefPartial ( arrfds.getAsIndirectObject ( j ) );
				continue;
			}
			PdfArray kids = (PdfArray) PdfReader.getPdfObjectRelease ( annot.get ( PdfName.KIDS ) );
			if ( kids != null )
				continue;
			PdfDictionary dic = new PdfDictionary ();
			dic.putAll ( annot );
			PdfString t = annot.getAsString ( PdfName.T );
			if ( t == null )
				continue;
			String name = t.toUnicodeString ();
			if ( fields.containsKey ( name ) )
				continue;
			Item item = new Item ();
			fields.put ( name, item );
			item.addValue ( dic );
			item.addWidget ( dic );
			item.addWidgetRef ( arrfds.getAsIndirectObject ( j ) ); // must be a reference
			item.addMerged ( dic );
			item.addPage ( -1 );
			item.addTabOrder ( -1 );
		}
	}

	private String[] getListOption ( String fieldName ) {
		Item fd = getFieldItem ( fieldName );
		if ( fd == null )
			return null;
		PdfArray ar = fd.getMerged ( 0 ).getAsArray ( PdfName.OPT );
		if ( ar == null )
			return null;
		String[] ret = new String[ar.size ()];
		for ( int k = 0; k < ar.size (); ++k ) {
			PdfObject obj = ar.getDirectObject ( k );
			try {
				if ( obj.isArray () ) {
					obj = ( (PdfArray) obj ).getDirectObject ( 0 );
				}
				if ( obj.isString () )
					ret[k] = ( (PdfString) obj ).toUnicodeString ();
				else
					ret[k] = obj.toString ();
			} catch ( Exception e ) {
				ret[k] = "";
			}
		}
		return ret;
	}

	public String[] getListOptionExport ( String fieldName ) {
		return getListOption ( fieldName );
	}

	public int getFieldType ( String fieldName ) {
		Item fd = getFieldItem ( fieldName );
		if ( fd == null )
			return FIELD_TYPE_NONE;
		PdfDictionary merged = fd.getMerged ( 0 );
		PdfName type = merged.getAsName ( PdfName.FT );
		if ( type == null )
			return FIELD_TYPE_NONE;
		int ff = 0;
		PdfNumber ffo = merged.getAsNumber ( PdfName.FF );
		if ( ffo != null ) {
			ff = ffo.intValue ();
		}
		if ( PdfName.BTN.equals ( type ) ) {
			if ( ( ff & PdfFormField.FF_PUSHBUTTON ) != 0 )
				return FIELD_TYPE_PUSHBUTTON;
			if ( ( ff & PdfFormField.FF_RADIO ) != 0 )
				return FIELD_TYPE_RADIOBUTTON;
			else
				return FIELD_TYPE_CHECKBOX;
		} else if ( PdfName.TX.equals ( type ) ) {
			return FIELD_TYPE_TEXT;
		} else if ( PdfName.CH.equals ( type ) ) {
			if ( ( ff & PdfFormField.FF_COMBO ) != 0 )
				return FIELD_TYPE_COMBO;
			else
				return FIELD_TYPE_LIST;
		} else if ( PdfName.SIG.equals ( type ) ) {
			return FIELD_TYPE_SIGNATURE;
		}
		return FIELD_TYPE_NONE;
	}

	public void decodeGenericDictionary ( PdfDictionary merged, BaseField tx ) {
		int flags;
		PdfString da = merged.getAsString ( PdfName.DA );
		if ( da != null ) {
			Object[] dab = splitDAelements ( da.toUnicodeString () );
			if ( dab[DA_SIZE] != null )
				tx.setFontSize ( (Float) dab[DA_SIZE] );
			if ( dab[DA_COLOR] != null )
				tx.setTextColor ( (BaseColor) dab[DA_COLOR] );
			if ( dab[DA_FONT] != null ) {
				PdfDictionary font = merged.getAsDict ( PdfName.DR );
				if ( font != null ) {
					font = font.getAsDict ( PdfName.FONT );
					if ( font != null ) {
						PdfObject po = font.get ( new PdfName ( (String) dab[DA_FONT] ) );
						if ( po != null && po.type () == PdfObject.INDIRECT ) {
							PRIndirectReference por = (PRIndirectReference) po;
							BaseFont bp = new DocumentFont ( (PRIndirectReference) po );
							tx.setFont ( bp );
							Integer porkey = por.getNumber ();
							BaseFont porf = extensionFonts.get ( porkey );
							if ( porf == null ) {
								if ( !extensionFonts.containsKey ( porkey ) ) {
									PdfDictionary fo = (PdfDictionary) PdfReader.getPdfObject ( po );
									PdfDictionary fd = fo.getAsDict ( PdfName.FONTDESCRIPTOR );
									if ( fd != null ) {
										PRStream prs = (PRStream) PdfReader.getPdfObject ( fd.get ( PdfName.FONTFILE2 ) );
										if ( prs == null )
											prs = (PRStream) PdfReader.getPdfObject ( fd.get ( PdfName.FONTFILE3 ) );
										if ( prs == null ) {
											extensionFonts.put ( porkey, null );
										} else {
											try {
												porf = BaseFont.createFont ( "font.ttf", BaseFont.IDENTITY_H, true, false, PdfReader.getStreamBytes ( prs ),
																null );
											} catch ( Exception ignored ) {
											}
											extensionFonts.put ( porkey, porf );
										}
									}
								}
							}
							if ( tx instanceof TextField )
								( (TextField) tx ).setExtensionFont ( porf );
						} else {
							BaseFont bf = localFonts.get ( dab[DA_FONT] );
							if ( bf == null ) {
								String[] fn = stdFieldFontNames.get ( dab[DA_FONT] );
								if ( fn != null ) {
									try {
										String enc = "winansi";
										if ( fn.length > 1 )
											enc = fn[1];
										bf = BaseFont.createFont ( fn[0], enc, false );
										tx.setFont ( bf );
									} catch ( Exception e ) {
										// empty
									}
								}
							} else
								tx.setFont ( bf );
						}
					}
				}
			}
		}
		//rotation, border and background color
		PdfDictionary mk = merged.getAsDict ( PdfName.MK );
		if ( mk != null ) {
			PdfArray ar = mk.getAsArray ( PdfName.BC );
			BaseColor border = getMKColor ( ar );
			tx.setBorderColor ( border );
			if ( border != null )
				tx.setBorderWidth ( 1 );
			ar = mk.getAsArray ( PdfName.BG );
			tx.setBackgroundColor ( getMKColor ( ar ) );
			PdfNumber rotation = mk.getAsNumber ( PdfName.R );
			if ( rotation != null )
				tx.setRotation ( rotation.intValue () );
		}
		//flags
		PdfNumber nfl = merged.getAsNumber ( PdfName.F );
		tx.setVisibility ( BaseField.VISIBLE_BUT_DOES_NOT_PRINT );
		if ( nfl != null ) {
			flags = nfl.intValue ();
			if ( ( flags & PdfFormField.FLAGS_PRINT ) != 0 && ( flags & PdfFormField.FLAGS_HIDDEN ) != 0 )
				tx.setVisibility ( BaseField.HIDDEN );
			else if ( ( flags & PdfFormField.FLAGS_PRINT ) != 0 && ( flags & PdfFormField.FLAGS_NOVIEW ) != 0 )
				tx.setVisibility ( BaseField.HIDDEN_BUT_PRINTABLE );
			else if ( ( flags & PdfFormField.FLAGS_PRINT ) != 0 )
				tx.setVisibility ( BaseField.VISIBLE );
		}
		//multiline
		nfl = merged.getAsNumber ( PdfName.FF );
		flags = 0;
		if ( nfl != null )
			flags = nfl.intValue ();
		tx.setOptions ( flags );
		if ( ( flags & PdfFormField.FF_COMB ) != 0 ) {
			PdfNumber maxLen = merged.getAsNumber ( PdfName.MAXLEN );
			int len = 0;
			if ( maxLen != null )
				len = maxLen.intValue ();
			tx.setMaxCharacterLength ( len );
		}
		//alignment
		nfl = merged.getAsNumber ( PdfName.Q );
		if ( nfl != null ) {
			if ( nfl.intValue () == PdfFormField.Q_CENTER )
				tx.setAlignment ( Element.ALIGN_CENTER );
			else if ( nfl.intValue () == PdfFormField.Q_RIGHT )
				tx.setAlignment ( Element.ALIGN_RIGHT );
		}
		//border styles
		PdfDictionary bs = merged.getAsDict ( PdfName.BS );
		if ( bs != null ) {
			PdfNumber w = bs.getAsNumber ( PdfName.W );
			if ( w != null )
				tx.setBorderWidth ( w.floatValue () );
			PdfName s = bs.getAsName ( PdfName.S );
			if ( PdfName.D.equals ( s ) )
				tx.setBorderStyle ( PdfBorderDictionary.STYLE_DASHED );
			else if ( PdfName.B.equals ( s ) )
				tx.setBorderStyle ( PdfBorderDictionary.STYLE_BEVELED );
			else if ( PdfName.I.equals ( s ) )
				tx.setBorderStyle ( PdfBorderDictionary.STYLE_INSET );
			else if ( PdfName.U.equals ( s ) )
				tx.setBorderStyle ( PdfBorderDictionary.STYLE_UNDERLINE );
		} else {
			PdfArray bd = merged.getAsArray ( PdfName.BORDER );
			if ( bd != null ) {
				if ( bd.size () >= 3 )
					tx.setBorderWidth ( bd.getAsNumber ( 2 ).floatValue () );
				if ( bd.size () >= 4 )
					tx.setBorderStyle ( PdfBorderDictionary.STYLE_DASHED );
			}
		}
	}

	PdfAppearance getAppearance ( PdfDictionary merged, String[] values, String fieldName ) throws IOException, DocumentException {
		topFirst = 0;
		String text = values.length > 0 ? values[0] : null;

		TextField tx;
		if ( fieldCache == null || !fieldCache.containsKey ( fieldName ) ) {
			tx = new TextField ( writer, null, null );
			tx.setExtraMargin ( extraMarginLeft, extraMarginTop );
			tx.setBorderWidth ( 0 );
			tx.setSubstitutionFonts ( substitutionFonts );
			decodeGenericDictionary ( merged, tx );
			//rect
			PdfArray rect = merged.getAsArray ( PdfName.RECT );
			Rectangle box = PdfReader.getNormalizedRectangle ( rect );
			if ( tx.getRotation () == 90 || tx.getRotation () == 270 )
				box = box.rotate ();
			tx.setBox ( box );
			if ( fieldCache != null )
				fieldCache.put ( fieldName, tx );
		} else {
			tx = fieldCache.get ( fieldName );
			tx.setWriter ( writer );
		}
		PdfName fieldType = merged.getAsName ( PdfName.FT );
		if ( PdfName.TX.equals ( fieldType ) ) {
			if ( values.length > 0 && values[0] != null ) {
				tx.setText ( values[0] );
			}
			return tx.getAppearance ();
		}
		if ( !PdfName.CH.equals ( fieldType ) )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "an.appearance.was.requested.without.a.variable.text.field" ) );
		PdfArray opt = merged.getAsArray ( PdfName.OPT );
		int flags = 0;
		PdfNumber nfl = merged.getAsNumber ( PdfName.FF );
		if ( nfl != null )
			flags = nfl.intValue ();
		if ( ( flags & PdfFormField.FF_COMBO ) != 0 && opt == null ) {
			tx.setText ( text );
			return tx.getAppearance ();
		}
		if ( opt != null ) {
			String[] choices = new String[opt.size ()];
			String[] choicesExp = new String[opt.size ()];
			for ( int k = 0; k < opt.size (); ++k ) {
				PdfObject obj = opt.getPdfObject ( k );
				if ( obj.isString () ) {
					choices[k] = choicesExp[k] = ( (PdfString) obj ).toUnicodeString ();
				} else {
					PdfArray a = (PdfArray) obj;
					choicesExp[k] = a.getAsString ( 0 ).toUnicodeString ();
					choices[k] = a.getAsString ( 1 ).toUnicodeString ();
				}
			}
			if ( ( flags & PdfFormField.FF_COMBO ) != 0 ) {
				for ( int k = 0; k < choices.length; ++k ) {
					if ( text.equals ( choicesExp[k] ) ) {
						text = choices[k];
						break;
					}
				}
				tx.setText ( text );
				return tx.getAppearance ();
			}
			ArrayList<Integer> indexes = new ArrayList<> ();
			for ( int k = 0; k < choicesExp.length; ++k ) {
				for ( String val : values ) {
					if ( val != null && val.equals ( choicesExp[k] ) ) {
						indexes.add ( k );
						break;
					}
				}
			}
			tx.setChoices ( choices );
			tx.setChoiceExports ();
			tx.setChoiceSelections ( indexes );
		}
		PdfAppearance app = tx.getListAppearance ();
		topFirst = tx.getTopFirst ();
		return app;
	}

	PdfAppearance getAppearance ( PdfDictionary merged, String text, String fieldName ) throws IOException, DocumentException {
		String[] valueArr = new String[1];
		valueArr[0] = text;
		return getAppearance ( merged, valueArr, fieldName );
	}

	BaseColor getMKColor ( PdfArray ar ) {
		if ( ar == null )
			return null;
		switch ( ar.size () ) {
		case 1:
			return new GrayColor ( ar.getAsNumber ( 0 ).floatValue () );
		case 3:
			return new BaseColor ( ExtendedColor.normalize ( ar.getAsNumber ( 0 ).floatValue () ),
							ExtendedColor.normalize ( ar.getAsNumber ( 1 ).floatValue () ), ExtendedColor.normalize ( ar.getAsNumber ( 2 ).floatValue () ) );
		case 4:
			return new CMYKColor ( ar.getAsNumber ( 0 ).floatValue (), ar.getAsNumber ( 1 ).floatValue (), ar.getAsNumber ( 2 ).floatValue (),
							ar.getAsNumber ( 3 ).floatValue () );
		default:
			return null;
		}
	}

	public String getField ( String name ) {
		if ( xfa.isXfaPresent () ) {
			name = xfa.findFieldName ( name, this );
			if ( name == null )
				return null;
			name = XfaForm.Xml2Som.getShortName ( name );
			return XfaForm.getNodeText ( xfa.findDatasetsNode ( name ) );
		}
		Item item = fields.get ( name );
		if ( item == null )
			return null;
		PdfDictionary mergedDict = item.getMerged ( 0 );
		PdfObject v = PdfReader.getPdfObject ( mergedDict.get ( PdfName.V ) );
		if ( v == null )
			return "";
		if ( v instanceof PRStream ) {
			byte[] valBytes;
			try {
				valBytes = PdfReader.getStreamBytes ( (PRStream) v );
				return new String ( valBytes );
			} catch ( IOException e ) {
				throw new ExceptionConverter ( e );
			}
		}

		PdfName type = mergedDict.getAsName ( PdfName.FT );
		if ( PdfName.BTN.equals ( type ) ) {
			PdfNumber ff = mergedDict.getAsNumber ( PdfName.FF );
			int flags = 0;
			if ( ff != null )
				flags = ff.intValue ();
			if ( ( flags & PdfFormField.FF_PUSHBUTTON ) != 0 )
				return "";
			String value = "";
			if ( v instanceof PdfName )
				value = PdfName.decodeName ( v.toString () );
			else if ( v instanceof PdfString )
				value = ( (PdfString) v ).toUnicodeString ();
			PdfArray opts = item.getValue ( 0 ).getAsArray ( PdfName.OPT );
			if ( opts != null ) {
				int idx;
				try {
					idx = Integer.parseInt ( value );
					PdfString ps = opts.getAsString ( idx );
					value = ps.toUnicodeString ();
				} catch ( Exception ignored ) {
				}
			}
			return value;
		}
		if ( v instanceof PdfString ) {
			return ( (PdfString) v ).toUnicodeString ();
		} else if ( v instanceof PdfName ) {
			return PdfName.decodeName ( v.toString () );
		} else
			return "";
	}

	public void setField ( String name, String value ) throws IOException, DocumentException {
		setField ( name, value, null );
	}

	public void setField ( String name, String value, String display ) throws IOException, DocumentException {
		if ( writer == null )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "this.acrofields.instance.is.read.only" ) );
		if ( xfa.isXfaPresent () ) {
			name = xfa.findFieldName ( name, this );
			if ( name == null )
				return;
			String shortName = XfaForm.Xml2Som.getShortName ( name );
			Node xn = xfa.findDatasetsNode ( shortName );
			if ( xn == null ) {
				xn = xfa.getDatasetsSom ().insertNode ( xfa.getDatasetsNode (), shortName );
			}
			xfa.setNodeText ( xn, value );
		}
		Item item = fields.get ( name );
		if ( item == null )
			return;
		PdfDictionary merged = item.getMerged ( 0 );
		PdfName type = merged.getAsName ( PdfName.FT );
		if ( PdfName.TX.equals ( type ) ) {
			PdfNumber maxLen = merged.getAsNumber ( PdfName.MAXLEN );
			int len = 0;
			if ( maxLen != null )
				len = maxLen.intValue ();
			if ( len > 0 )
				value = value.substring ( 0, Math.min ( len, value.length () ) );
		}
		if ( display == null )
			display = value;
		if ( PdfName.TX.equals ( type ) || PdfName.CH.equals ( type ) ) {
			PdfString v = new PdfString ( value, PdfObject.TEXT_UNICODE );
			for ( int idx = 0; idx < item.size (); ++idx ) {
				PdfDictionary valueDic = item.getValue ( idx );
				valueDic.put ( PdfName.V, v );
				valueDic.remove ( PdfName.I );
				markUsed ( valueDic );
				merged = item.getMerged ( idx );
				merged.remove ( PdfName.I );
				merged.put ( PdfName.V, v );
				PdfDictionary widget = item.getWidget ( idx );
				if ( generateAppearances ) {
					PdfAppearance app = getAppearance ( merged, display, name );
					if ( PdfName.CH.equals ( type ) ) {
						PdfNumber n = new PdfNumber ( topFirst );
						widget.put ( PdfName.TI, n );
						merged.put ( PdfName.TI, n );
					}
					PdfDictionary appDic = widget.getAsDict ( PdfName.AP );
					if ( appDic == null ) {
						appDic = new PdfDictionary ();
						widget.put ( PdfName.AP, appDic );
						merged.put ( PdfName.AP, appDic );
					}
					appDic.put ( PdfName.N, app.getIndirectReference () );
					writer.releaseTemplate ( app );
				} else {
					widget.remove ( PdfName.AP );
					merged.remove ( PdfName.AP );
				}
				markUsed ( widget );
			}
		} else if ( PdfName.BTN.equals ( type ) ) {
			PdfNumber ff = item.getMerged ( 0 ).getAsNumber ( PdfName.FF );
			int flags = 0;
			if ( ff != null )
				flags = ff.intValue ();
			if ( ( flags & PdfFormField.FF_PUSHBUTTON ) != 0 ) {
				//we'll assume that the value is an image in base64
				Image img;
				try {
					img = Image.getInstance ( Base64.decode ( value ) );
				} catch ( Exception e ) {
					return;
				}
				PushbuttonField pb = getNewPushbuttonFromField ( name );
				pb.setImage ( img );
				replacePushbuttonField ( name, pb.getField () );
				return;
			}
			PdfName v = new PdfName ( value );
			ArrayList<String> lopt = new ArrayList<> ();
			PdfArray opts = item.getValue ( 0 ).getAsArray ( PdfName.OPT );
			if ( opts != null ) {
				for ( int k = 0; k < opts.size (); ++k ) {
					PdfString valStr = opts.getAsString ( k );
					if ( valStr != null )
						lopt.add ( valStr.toUnicodeString () );
					else
						lopt.add ( null );
				}
			}
			int vidx = lopt.indexOf ( value );
			PdfName vt;
			if ( vidx >= 0 )
				vt = new PdfName ( String.valueOf ( vidx ) );
			else
				vt = v;
			for ( int idx = 0; idx < item.size (); ++idx ) {
				merged = item.getMerged ( idx );
				PdfDictionary widget = item.getWidget ( idx );
				PdfDictionary valDict = item.getValue ( idx );
				markUsed ( item.getValue ( idx ) );
				valDict.put ( PdfName.V, vt );
				merged.put ( PdfName.V, vt );
				markUsed ( widget );
				if ( isInAP ( widget, vt ) ) {
					merged.put ( PdfName.AS, vt );
					widget.put ( PdfName.AS, vt );
				} else {
					merged.put ( PdfName.AS, PdfName.Off );
					widget.put ( PdfName.AS, PdfName.Off );
				}
			}
		}
	}

	public void setListSelection ( String name, String[] value ) throws IOException, DocumentException {
		Item item = getFieldItem ( name );
		if ( item == null )
			return;
		PdfDictionary merged = item.getMerged ( 0 );
		PdfName type = merged.getAsName ( PdfName.FT );
		if ( !PdfName.CH.equals ( type ) ) {
			return;
		}
		String[] options = getListOptionExport ( name );
		PdfArray array = new PdfArray ();
		for ( String element : value ) {
			for ( int j = 0; j < options.length; j++ ) {
				if ( options[j].equals ( element ) ) {
					array.add ( new PdfNumber ( j ) );
					break;
				}
			}
		}
		item.writeToAll ( PdfName.I, array, Item.WRITE_MERGED | Item.WRITE_VALUE );

		PdfArray vals = new PdfArray ();
		for ( String s : value ) {
			vals.add ( new PdfString ( s ) );
		}
		item.writeToAll ( PdfName.V, vals, Item.WRITE_MERGED | Item.WRITE_VALUE );

		PdfAppearance app = getAppearance ( merged, value, name );

		PdfDictionary apDic = new PdfDictionary ();
		apDic.put ( PdfName.N, app.getIndirectReference () );
		item.writeToAll ( PdfName.AP, apDic, Item.WRITE_MERGED | Item.WRITE_WIDGET );

		writer.releaseTemplate ( app );

		item.markUsed ( this, Item.WRITE_VALUE | Item.WRITE_WIDGET );
	}

	boolean isInAP ( PdfDictionary dic, PdfName check ) {
		PdfDictionary appDic = dic.getAsDict ( PdfName.AP );
		if ( appDic == null )
			return false;
		PdfDictionary NDic = appDic.getAsDict ( PdfName.N );
		return NDic != null && NDic.get ( check ) != null;
	}

	public Map<String, Item> getFields () {
		return fields;
	}

	public void setFields ( FdfReader fdf ) throws IOException, DocumentException {
		HashMap<String, PdfDictionary> fd = fdf.getFields ();
		for ( String f : fd.keySet () ) {
			String v = fdf.getFieldValue ( f );
			if ( v != null )
				setField ( f, v );
		}
	}

	public void setFields ( XfdfReader xfdf ) throws IOException, DocumentException {
		HashMap<String, String> fd = xfdf.getFields ();
		for ( String f : fd.keySet () ) {
			String v = xfdf.getFieldValue ( f );
			if ( v != null )
				setField ( f, v );
			List<String> l = xfdf.getListValues ( f );
			if ( l != null )
				setListSelection ( v, l.toArray ( new String[0] ) );
		}
	}

	public Item getFieldItem ( String name ) {
		if ( xfa.isXfaPresent () ) {
			name = xfa.findFieldName ( name, this );
			if ( name == null )
				return null;
		}
		return fields.get ( name );
	}

	public List<FieldPosition> getFieldPositions ( String name ) {
		Item item = getFieldItem ( name );
		if ( item == null )
			return null;
		ArrayList<FieldPosition> ret = new ArrayList<> ();
		for ( int k = 0; k < item.size (); ++k ) {
			try {
				PdfDictionary wd = item.getWidget ( k );
				PdfArray rect = wd.getAsArray ( PdfName.RECT );
				if ( rect == null )
					continue;
				Rectangle r = PdfReader.getNormalizedRectangle ( rect );
				int page = item.getPage ( k );
				int rotation = reader.getPageRotation ( page );
				FieldPosition fp = new FieldPosition ();
				fp.page = page;
				if ( rotation != 0 ) {
					Rectangle pageSize = reader.getPageSize ( page );
					switch ( rotation ) {
					case 270:
						r = new Rectangle (
										pageSize.getTop () - r.getBottom (),
										r.getLeft (),
										pageSize.getTop () - r.getTop (),
										r.getRight () );
						break;
					case 180:
						r = new Rectangle (
										pageSize.getRight () - r.getLeft (),
										pageSize.getTop () - r.getBottom (),
										pageSize.getRight () - r.getRight (),
										pageSize.getTop () - r.getTop () );
						break;
					case 90:
						r = new Rectangle (
										r.getBottom (),
										pageSize.getRight () - r.getLeft (),
										r.getTop (),
										pageSize.getRight () - r.getRight () );
						break;
					}
					r.normalize ();
				}
				fp.position = r;
				ret.add ( fp );
			} catch ( Exception ignored ) {
			}
		}
		return ret;
	}

	public void setGenerateAppearances ( boolean generateAppearances ) {
		this.generateAppearances = generateAppearances;
		PdfDictionary top = reader.getCatalog ().getAsDict ( PdfName.ACROFORM );
		if ( generateAppearances )
			top.remove ( PdfName.NEEDAPPEARANCES );
		else
			top.put ( PdfName.NEEDAPPEARANCES, PdfBoolean.PDFTRUE );
	}

	private void markUsed ( PdfObject obj ) {
		if ( !append )
			return;
		( (PdfStamperImp) writer ).markUsed ( obj );
	}

	public XfaForm getXfa () {
		return xfa;
	}

	public PushbuttonField getNewPushbuttonFromField ( String field ) {
		return getNewPushbuttonFromField ( field, 0 );
	}

	public PushbuttonField getNewPushbuttonFromField ( String field, int order ) {
		try {
			if ( getFieldType ( field ) != FIELD_TYPE_PUSHBUTTON )
				return null;
			Item item = getFieldItem ( field );
			if ( order >= item.size () )
				return null;
			List<FieldPosition> pos = getFieldPositions ( field );
			Rectangle box = pos.get ( order ).position;
			PushbuttonField newButton = new PushbuttonField ( writer, box, null );
			PdfDictionary dic = item.getMerged ( order );
			decodeGenericDictionary ( dic, newButton );
			PdfDictionary mk = dic.getAsDict ( PdfName.MK );
			if ( mk != null ) {
				PdfString text = mk.getAsString ( PdfName.CA );
				if ( text != null )
					newButton.setText ( text.toUnicodeString () );
				PdfNumber tp = mk.getAsNumber ( PdfName.TP );
				if ( tp != null )
					newButton.setLayout ( tp.intValue () + 1 );
				PdfDictionary ifit = mk.getAsDict ( PdfName.IF );
				if ( ifit != null ) {
					PdfName sw = ifit.getAsName ( PdfName.SW );
					if ( sw != null ) {
						int scale = PushbuttonField.SCALE_ICON_ALWAYS;
						if ( sw.equals ( PdfName.B ) )
							scale = PushbuttonField.SCALE_ICON_IS_TOO_BIG;
						else if ( sw.equals ( PdfName.S ) )
							scale = PushbuttonField.SCALE_ICON_IS_TOO_SMALL;
						else if ( sw.equals ( PdfName.N ) )
							scale = PushbuttonField.SCALE_ICON_NEVER;
						newButton.setScaleIcon ( scale );
					}
					sw = ifit.getAsName ( PdfName.S );
					if ( sw != null ) {
						if ( sw.equals ( PdfName.A ) )
							newButton.setProportionalIcon ( false );
					}
					PdfArray aj = ifit.getAsArray ( PdfName.A );
					if ( aj != null && aj.size () == 2 ) {
						float left = aj.getAsNumber ( 0 ).floatValue ();
						float bottom = aj.getAsNumber ( 1 ).floatValue ();
						newButton.setIconHorizontalAdjustment ( left );
						newButton.setIconVerticalAdjustment ( bottom );
					}
					PdfBoolean fb = ifit.getAsBoolean ( PdfName.FB );
					if ( fb != null && fb.booleanValue () )
						newButton.setIconFitToBounds ( true );
				}
				PdfObject i = mk.get ( PdfName.I );
				if ( i != null && i.isIndirect () )
					newButton.setIconReference ( (PRIndirectReference) i );
			}
			return newButton;
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
	}

	public void replacePushbuttonField ( String field, PdfFormField button ) {
		replacePushbuttonField ( field, button, 0 );
	}

	public void replacePushbuttonField ( String field, PdfFormField button, int order ) {
		if ( getFieldType ( field ) != FIELD_TYPE_PUSHBUTTON )
			return;
		Item item = getFieldItem ( field );
		if ( order >= item.size () )
			return;
		PdfDictionary merged = item.getMerged ( order );
		PdfDictionary values = item.getValue ( order );
		PdfDictionary widgets = item.getWidget ( order );
		for ( PdfName pdfName : buttonRemove ) {
			merged.remove ( pdfName );
			values.remove ( pdfName );
			widgets.remove ( pdfName );
		}
		for ( PdfName element : button.getKeys () ) {
			if ( element.equals ( PdfName.T ) || element.equals ( PdfName.RECT ) )
				continue;
			if ( element.equals ( PdfName.FF ) )
				values.put ( element, button.get ( element ) );
			else
				widgets.put ( element, button.get ( element ) );
			merged.put ( element, button.get ( element ) );
			markUsed ( values );
			markUsed ( widgets );
		}
	}

	public static class Item {

		public static final int WRITE_MERGED = 1;

		public static final int WRITE_WIDGET = 2;

		public static final int WRITE_VALUE = 4;

		protected ArrayList<PdfDictionary> values = new ArrayList<> ();

		protected ArrayList<PdfDictionary> widgets = new ArrayList<> ();

		protected ArrayList<PdfIndirectReference> widget_refs = new ArrayList<> ();

		protected ArrayList<PdfDictionary> merged = new ArrayList<> ();

		protected ArrayList<Integer> page = new ArrayList<> ();

		protected ArrayList<Integer> tabOrder = new ArrayList<> ();

		public void writeToAll ( PdfName key, PdfObject value, int writeFlags ) {
			int i;
			PdfDictionary curDict;
			if ( ( writeFlags & WRITE_MERGED ) != 0 ) {
				for ( i = 0; i < merged.size (); ++i ) {
					curDict = getMerged ( i );
					curDict.put ( key, value );
				}
			}
			if ( ( writeFlags & WRITE_WIDGET ) != 0 ) {
				for ( i = 0; i < widgets.size (); ++i ) {
					curDict = getWidget ( i );
					curDict.put ( key, value );
				}
			}
			if ( ( writeFlags & WRITE_VALUE ) != 0 ) {
				for ( i = 0; i < values.size (); ++i ) {
					curDict = getValue ( i );
					curDict.put ( key, value );
				}
			}
		}

		public void markUsed ( AcroFields parentFields, int writeFlags ) {
			if ( ( writeFlags & WRITE_VALUE ) != 0 ) {
				for ( int i = 0; i < size (); ++i ) {
					parentFields.markUsed ( getValue ( i ) );
				}
			}
			if ( ( writeFlags & WRITE_WIDGET ) != 0 ) {
				for ( int i = 0; i < size (); ++i ) {
					parentFields.markUsed ( getWidget ( i ) );
				}
			}
		}

		public int size () {
			return values.size ();
		}

		public PdfDictionary getValue ( int idx ) {
			return values.get ( idx );
		}

		void addValue ( PdfDictionary value ) {
			values.add ( value );
		}

		public PdfDictionary getWidget ( int idx ) {
			return widgets.get ( idx );
		}

		void addWidget ( PdfDictionary widget ) {
			widgets.add ( widget );
		}

		public PdfIndirectReference getWidgetRef ( int idx ) {
			return widget_refs.get ( idx );
		}

		void addWidgetRef ( PdfIndirectReference widgRef ) {
			widget_refs.add ( widgRef );
		}

		public PdfDictionary getMerged ( int idx ) {
			return merged.get ( idx );
		}

		void addMerged ( PdfDictionary mergeDict ) {
			merged.add ( mergeDict );
		}

		public Integer getPage ( int idx ) {
			return page.get ( idx );
		}

		void addPage ( int pg ) {
			page.add ( pg );
		}

		void forcePage ( int idx, int pg ) {
			page.set ( idx, pg );
		}

		public Integer getTabOrder ( int idx ) {
			return tabOrder.get ( idx );
		}

		void addTabOrder ( int order ) {
			tabOrder.add ( order );
		}
	}


	public static class FieldPosition {

		public int page;

		public Rectangle position;
	}
}

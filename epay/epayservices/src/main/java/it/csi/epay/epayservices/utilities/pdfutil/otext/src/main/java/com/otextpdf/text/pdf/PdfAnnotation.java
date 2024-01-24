/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt.geom.AffineTransform;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Rectangle;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal.PdfIsoKeys;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;


public class PdfAnnotation extends PdfDictionary {

	public static final PdfName HIGHLIGHT_NONE = PdfName.N;

	public static final PdfName HIGHLIGHT_INVERT = PdfName.I;

	public static final PdfName HIGHLIGHT_OUTLINE = PdfName.O;

	public static final PdfName HIGHLIGHT_PUSH = PdfName.P;

	public static final PdfName HIGHLIGHT_TOGGLE = PdfName.T;

	public static final int FLAGS_INVISIBLE = 1;

	public static final int FLAGS_HIDDEN = 2;

	public static final int FLAGS_PRINT = 4;

	public static final int FLAGS_NOZOOM = 8;

	public static final int FLAGS_NOROTATE = 16;

	public static final int FLAGS_NOVIEW = 32;

	public static final int FLAGS_READONLY = 64;

	public static final int FLAGS_LOCKED = 128;

	public static final int FLAGS_TOGGLENOVIEW = 256;

	public static final PdfName APPEARANCE_NORMAL = PdfName.N;

	public static final PdfName APPEARANCE_ROLLOVER = PdfName.R;

	public static final PdfName APPEARANCE_DOWN = PdfName.D;

	public static final PdfName AA_ENTER = PdfName.E;

	public static final PdfName AA_EXIT = PdfName.X;

	public static final PdfName AA_DOWN = PdfName.D;

	public static final PdfName AA_UP = PdfName.U;

	public static final PdfName AA_FOCUS = PdfName.FO;

	public static final PdfName AA_BLUR = PdfName.BL;

	public static final PdfName AA_JS_KEY = PdfName.K;

	public static final PdfName AA_JS_FORMAT = PdfName.F;

	public static final PdfName AA_JS_CHANGE = PdfName.V;

	public static final PdfName AA_JS_OTHER_CHANGE = PdfName.C;

	public static final int MARKUP_HIGHLIGHT = 0;

	public static final int MARKUP_UNDERLINE = 1;

	public static final int MARKUP_STRIKEOUT = 2;

	public static final int MARKUP_SQUIGGLY = 3;

	protected PdfWriter writer;

	protected PdfIndirectReference reference;

	protected HashSet<PdfTemplate> templates;

	protected boolean form = false;

	protected boolean annotation = true;

	protected boolean used = false;

	public PdfAnnotation ( PdfWriter writer, Rectangle rect ) {
		this.writer = writer;
		if ( rect != null )
			put ( PdfName.RECT, new PdfRectangle ( rect ) );
	}

	public PdfAnnotation ( PdfWriter writer, float llx, float lly, float urx, float ury, PdfString title, PdfString content ) {
		this.writer = writer;
		put ( PdfName.SUBTYPE, PdfName.TEXT );
		put ( PdfName.T, title );
		put ( PdfName.RECT, new PdfRectangle ( llx, lly, urx, ury ) );
		put ( PdfName.CONTENTS, content );
	}

	public PdfAnnotation ( PdfWriter writer, float llx, float lly, float urx, float ury, PdfAction action ) {
		this.writer = writer;
		put ( PdfName.SUBTYPE, PdfName.LINK );
		put ( PdfName.RECT, new PdfRectangle ( llx, lly, urx, ury ) );
		put ( PdfName.A, action );
		put ( PdfName.BORDER, new PdfBorderArray ( 0, 0, 0 ) );
		put ( PdfName.C, new PdfColor ( 0x00, 0x00, 0xFF ) );
	}

	public static PdfAnnotation createScreen ( PdfWriter writer, Rectangle rect, String clipTitle, PdfFileSpecification fs,
					String mimeType, boolean playOnDisplay ) throws IOException {
		PdfAnnotation ann = new PdfAnnotation ( writer, rect );
		ann.put ( PdfName.SUBTYPE, PdfName.SCREEN );
		ann.put ( PdfName.F, new PdfNumber ( FLAGS_PRINT ) );
		ann.put ( PdfName.TYPE, PdfName.ANNOT );
		ann.setPage ();
		PdfIndirectReference ref = ann.getIndirectReference ();
		PdfAction action = PdfAction.rendition ( clipTitle, fs, mimeType, ref );
		PdfIndirectReference actionRef = writer.addToBody ( action ).getIndirectReference ();
		if ( playOnDisplay ) {
			PdfDictionary aa = new PdfDictionary ();
			aa.put ( new PdfName ( "PV" ), actionRef );
			ann.put ( PdfName.AA, aa );
		}
		ann.put ( PdfName.A, actionRef );
		return ann;
	}

	public static PdfArray getMKColor ( BaseColor color ) {
		PdfArray array = new PdfArray ();
		int type = ExtendedColor.getType ( color );
		switch ( type ) {
		case ExtendedColor.TYPE_GRAY: {
			array.add ( new PdfNumber ( ( (GrayColor) color ).getGray () ) );
			break;
		}
		case ExtendedColor.TYPE_CMYK: {
			CMYKColor cmyk = (CMYKColor) color;
			array.add ( new PdfNumber ( cmyk.getCyan () ) );
			array.add ( new PdfNumber ( cmyk.getMagenta () ) );
			array.add ( new PdfNumber ( cmyk.getYellow () ) );
			array.add ( new PdfNumber ( cmyk.getBlack () ) );
			break;
		}
		case ExtendedColor.TYPE_SEPARATION:
		case ExtendedColor.TYPE_PATTERN:
		case ExtendedColor.TYPE_SHADING:
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "separations.patterns.and.shadings.are.not.allowed.in.mk.dictionary" ) );
		default:
			array.add ( new PdfNumber ( color.getRed () / 255f ) );
			array.add ( new PdfNumber ( color.getGreen () / 255f ) );
			array.add ( new PdfNumber ( color.getBlue () / 255f ) );
		}
		return array;
	}

	public PdfIndirectReference getIndirectReference () {
		if ( reference == null ) {
			reference = writer.getPdfIndirectReference ();
		}
		return reference;
	}

	public void setDefaultAppearanceString ( PdfContentByte cb ) {
		byte[] b = cb.getInternalBuffer ().toByteArray ();
		int len = b.length;
		for ( int k = 0; k < len; ++k ) {
			if ( b[k] == '\n' )
				b[k] = 32;
		}
		put ( PdfName.DA, new PdfString ( b ) );
	}

	public void setFlags ( int flags ) {
		if ( flags == 0 )
			remove ( PdfName.F );
		else
			put ( PdfName.F, new PdfNumber ( flags ) );
	}

	public void setBorder ( PdfBorderArray border ) {
		put ( PdfName.BORDER, border );
	}

	public void setBorderStyle ( PdfBorderDictionary border ) {
		put ( PdfName.BS, border );
	}

	public void setHighlighting ( PdfName highlight ) {
		if ( highlight.equals ( HIGHLIGHT_INVERT ) )
			remove ( PdfName.H );
		else
			put ( PdfName.H, highlight );
	}

	public void setAppearance ( PdfName ap, PdfTemplate template ) {
		PdfDictionary dic = (PdfDictionary) get ( PdfName.AP );
		if ( dic == null )
			dic = new PdfDictionary ();
		dic.put ( ap, template.getIndirectReference () );
		put ( PdfName.AP, dic );
		if ( !form )
			return;
		if ( templates == null )
			templates = new HashSet<> ();
		templates.add ( template );
	}

	public void setColor ( BaseColor color ) {
		put ( PdfName.C, new PdfColor ( color ) );
	}

	public void setTitle ( String title ) {
		if ( title == null ) {
			remove ( PdfName.T );
			return;
		}
		put ( PdfName.T, new PdfString ( title, PdfObject.TEXT_UNICODE ) );
	}

	public void setAction ( PdfAction action ) {
		put ( PdfName.A, action );
	}

	public boolean isUsed () {
		return used;
	}

	public void setUsed () {
		used = true;
	}

	public HashSet<PdfTemplate> getTemplates () {
		return templates;
	}

	public boolean isForm () {
		return form;
	}

	public boolean isAnnotation () {
		return annotation;
	}

	public void setPage ( int page ) {
		put ( PdfName.P, writer.getPageReference ( page ) );
	}

	public void setPage () {
		put ( PdfName.P, writer.getCurrentPage () );
	}

	public int getPlaceInPage () {
		return -1;
	}

	public void setRotate ( int v ) {
		put ( PdfName.ROTATE, new PdfNumber ( v ) );
	}

	PdfDictionary getMK () {
		PdfDictionary mk = (PdfDictionary) get ( PdfName.MK );
		if ( mk == null ) {
			mk = new PdfDictionary ();
			put ( PdfName.MK, mk );
		}
		return mk;
	}

	public void setMKRotation ( int rotation ) {
		getMK ().put ( PdfName.R, new PdfNumber ( rotation ) );
	}

	public void setMKBorderColor ( BaseColor color ) {
		if ( color == null )
			getMK ().remove ( PdfName.BC );
		else
			getMK ().put ( PdfName.BC, getMKColor ( color ) );
	}

	public void setMKBackgroundColor ( BaseColor color ) {
		if ( color == null )
			getMK ().remove ( PdfName.BG );
		else
			getMK ().put ( PdfName.BG, getMKColor ( color ) );
	}

	public void setMKNormalCaption ( String caption ) {
		getMK ().put ( PdfName.CA, new PdfString ( caption, PdfObject.TEXT_UNICODE ) );
	}

	public void setMKNormalIcon ( PdfTemplate template ) {
		getMK ().put ( PdfName.I, template.getIndirectReference () );
	}

	public void setMKIconFit ( PdfName scale, PdfName scalingType, float leftoverLeft, float leftoverBottom, boolean fitInBounds ) {
		PdfDictionary dic = new PdfDictionary ();
		if ( !scale.equals ( PdfName.A ) )
			dic.put ( PdfName.SW, scale );
		if ( !scalingType.equals ( PdfName.P ) )
			dic.put ( PdfName.S, scalingType );
		if ( leftoverLeft != 0.5f || leftoverBottom != 0.5f ) {
			PdfArray array = new PdfArray ( new PdfNumber ( leftoverLeft ) );
			array.add ( new PdfNumber ( leftoverBottom ) );
			dic.put ( PdfName.A, array );
		}
		if ( fitInBounds )
			dic.put ( PdfName.FB, PdfBoolean.PDFTRUE );
		getMK ().put ( PdfName.IF, dic );
	}

	public void setMKTextPosition ( int tp ) {
		getMK ().put ( PdfName.TP, new PdfNumber ( tp ) );
	}

	public void setLayer ( PdfOCG layer ) {
		put ( PdfName.OC, layer.getRef () );
	}

	public void setName ( String name ) {
		put ( PdfName.NM, new PdfString ( name ) );
	}

	public void applyCTM ( AffineTransform ctm ) {
		PdfArray origRect = getAsArray ( PdfName.RECT );
		if ( origRect != null ) {
			PdfRectangle rect;
			if ( origRect.size () == 4 ) {
				rect = new PdfRectangle ( origRect.getAsNumber ( 0 ).floatValue (), origRect.getAsNumber ( 1 ).floatValue (),
								origRect.getAsNumber ( 2 ).floatValue (), origRect.getAsNumber ( 3 ).floatValue () );
			} else {
				rect = new PdfRectangle ( origRect.getAsNumber ( 0 ).floatValue (), origRect.getAsNumber ( 1 ).floatValue () );
			}
			put ( PdfName.RECT, rect.transform ( ctm ) );
		}
	}

	@Override
	public void toPdf ( final PdfWriter writer, final OutputStream os ) throws IOException {
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_ANNOTATION, this );
		super.toPdf ( writer, os );
	}

}

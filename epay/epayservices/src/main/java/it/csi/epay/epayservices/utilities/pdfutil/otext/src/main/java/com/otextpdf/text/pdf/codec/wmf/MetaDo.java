/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec.wmf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.BaseFont;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfContentByte;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec.BmpImage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class MetaDo {

	public static final int META_SETBKCOLOR = 0x0201;

	public static final int META_SETBKMODE = 0x0102;

	public static final int META_SETROP2 = 0x0104;

	public static final int META_SETRELABS = 0x0105;

	public static final int META_SETPOLYFILLMODE = 0x0106;

	public static final int META_SETSTRETCHBLTMODE = 0x0107;

	public static final int META_SETTEXTCHAREXTRA = 0x0108;

	public static final int META_SETTEXTCOLOR = 0x0209;

	public static final int META_SETTEXTJUSTIFICATION = 0x020A;

	public static final int META_SETWINDOWORG = 0x020B;

	public static final int META_SETWINDOWEXT = 0x020C;

	public static final int META_SETVIEWPORTORG = 0x020D;

	public static final int META_SETVIEWPORTEXT = 0x020E;

	public static final int META_OFFSETWINDOWORG = 0x020F;

	public static final int META_SCALEWINDOWEXT = 0x0410;

	public static final int META_OFFSETVIEWPORTORG = 0x0211;

	public static final int META_SCALEVIEWPORTEXT = 0x0412;

	public static final int META_LINETO = 0x0213;

	public static final int META_MOVETO = 0x0214;

	public static final int META_EXCLUDECLIPRECT = 0x0415;

	public static final int META_INTERSECTCLIPRECT = 0x0416;

	public static final int META_ARC = 0x0817;

	public static final int META_ELLIPSE = 0x0418;

	public static final int META_FLOODFILL = 0x0419;

	public static final int META_PIE = 0x081A;

	public static final int META_RECTANGLE = 0x041B;

	public static final int META_ROUNDRECT = 0x061C;

	public static final int META_PATBLT = 0x061D;

	public static final int META_SAVEDC = 0x001E;

	public static final int META_SETPIXEL = 0x041F;

	public static final int META_OFFSETCLIPRGN = 0x0220;

	public static final int META_TEXTOUT = 0x0521;

	public static final int META_BITBLT = 0x0922;

	public static final int META_STRETCHBLT = 0x0B23;

	public static final int META_POLYGON = 0x0324;

	public static final int META_POLYLINE = 0x0325;

	public static final int META_ESCAPE = 0x0626;

	public static final int META_RESTOREDC = 0x0127;

	public static final int META_FILLREGION = 0x0228;

	public static final int META_FRAMEREGION = 0x0429;

	public static final int META_INVERTREGION = 0x012A;

	public static final int META_PAINTREGION = 0x012B;

	public static final int META_SELECTCLIPREGION = 0x012C;

	public static final int META_SELECTOBJECT = 0x012D;

	public static final int META_SETTEXTALIGN = 0x012E;

	public static final int META_CHORD = 0x0830;

	public static final int META_SETMAPPERFLAGS = 0x0231;

	public static final int META_EXTTEXTOUT = 0x0a32;

	public static final int META_SETDIBTODEV = 0x0d33;

	public static final int META_SELECTPALETTE = 0x0234;

	public static final int META_REALIZEPALETTE = 0x0035;

	public static final int META_ANIMATEPALETTE = 0x0436;

	public static final int META_SETPALENTRIES = 0x0037;

	public static final int META_POLYPOLYGON = 0x0538;

	public static final int META_RESIZEPALETTE = 0x0139;

	public static final int META_DIBBITBLT = 0x0940;

	public static final int META_DIBSTRETCHBLT = 0x0b41;

	public static final int META_DIBCREATEPATTERNBRUSH = 0x0142;

	public static final int META_STRETCHDIB = 0x0f43;

	public static final int META_EXTFLOODFILL = 0x0548;

	public static final int META_DELETEOBJECT = 0x01f0;

	public static final int META_CREATEPALETTE = 0x00f7;

	public static final int META_CREATEPATTERNBRUSH = 0x01F9;

	public static final int META_CREATEPENINDIRECT = 0x02FA;

	public static final int META_CREATEFONTINDIRECT = 0x02FB;

	public static final int META_CREATEBRUSHINDIRECT = 0x02FC;

	public static final int META_CREATEREGION = 0x06FF;

	public PdfContentByte cb;

	public InputMeta in;

	int left;

	int top;

	int right;

	int bottom;

	int inch;

	MetaState state = new MetaState ();

	public MetaDo ( InputStream in, PdfContentByte cb ) {
		this.cb = cb;
		this.in = new InputMeta ( in );
	}

	static float getArc ( float xCenter, float yCenter, float xDot, float yDot ) {
		double s = Math.atan2 ( yDot - yCenter, xDot - xCenter );
		if ( s < 0 )
			s += Math.PI * 2;
		return (float) ( s / Math.PI * 180 );
	}

	public void readAll () throws IOException, DocumentException {
		if ( in.readInt () != 0x9AC6CDD7 ) {
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "not.a.placeable.windows.metafile" ) );
		}
		in.readWord ();
		left = in.readShort ();
		top = in.readShort ();
		right = in.readShort ();
		bottom = in.readShort ();
		inch = in.readWord ();
		state.setScalingX ( (float) ( right - left ) / (float) inch * 72f );
		state.setScalingY ( (float) ( bottom - top ) / (float) inch * 72f );
		state.setOffsetWx ( left );
		state.setOffsetWy ( top );
		state.setExtentWx ( right - left );
		state.setExtentWy ( bottom - top );
		in.readInt ();
		in.readWord ();
		in.skip ( 18 );

		int tsize;
		int function;
		cb.setLineCap ( 1 );
		cb.setLineJoin ( 1 );
		for ( ; ; ) {
			int lenMarker = in.getLength ();
			tsize = in.readInt ();
			if ( tsize < 3 )
				break;
			function = in.readWord ();
			switch ( function ) {
			case 0:
				break;
			case META_CREATEPALETTE:
			case META_CREATEREGION:
			case META_DIBCREATEPATTERNBRUSH:
				state.addMetaObject ( new MetaObject () );
				break;
			case META_CREATEPENINDIRECT: {
				MetaPen pen = new MetaPen ();
				pen.init ( in );
				state.addMetaObject ( pen );
				break;
			}
			case META_CREATEBRUSHINDIRECT: {
				MetaBrush brush = new MetaBrush ();
				brush.init ( in );
				state.addMetaObject ( brush );
				break;
			}
			case META_CREATEFONTINDIRECT: {
				MetaFont font = new MetaFont ();
				font.init ( in );
				state.addMetaObject ( font );
				break;
			}
			case META_SELECTOBJECT: {
				int idx = in.readWord ();
				state.selectMetaObject ( idx, cb );
				break;
			}
			case META_DELETEOBJECT: {
				int idx = in.readWord ();
				state.deleteMetaObject ( idx );
				break;
			}
			case META_SAVEDC:
				state.saveState ( cb );
				break;
			case META_RESTOREDC: {
				int idx = in.readShort ();
				state.restoreState ( idx, cb );
				break;
			}
			case META_SETWINDOWORG:
				state.setOffsetWy ( in.readShort () );
				state.setOffsetWx ( in.readShort () );
				break;
			case META_SETWINDOWEXT:
				state.setExtentWy ( in.readShort () );
				state.setExtentWx ( in.readShort () );
				break;
			case META_MOVETO: {
				int y = in.readShort ();
				Point p = new Point ( in.readShort (), y );
				state.setCurrentPoint ( p );
				break;
			}
			case META_LINETO: {
				int y = in.readShort ();
				int x = in.readShort ();
				Point p = state.getCurrentPoint ();
				cb.moveTo ( state.transformX ( p.x ), state.transformY ( p.y ) );
				cb.lineTo ( state.transformX ( x ), state.transformY ( y ) );
				cb.stroke ();
				state.setCurrentPoint ( new Point ( x, y ) );
				break;
			}
			case META_POLYLINE: {
				state.setLineJoinPolygon ( cb );
				int len = in.readWord ();
				int x = in.readShort ();
				int y = in.readShort ();
				cb.moveTo ( state.transformX ( x ), state.transformY ( y ) );
				for ( int k = 1; k < len; ++k ) {
					x = in.readShort ();
					y = in.readShort ();
					cb.lineTo ( state.transformX ( x ), state.transformY ( y ) );
				}
				cb.stroke ();
				break;
			}
			case META_POLYGON: {
				if ( isNullStrokeFill ( false ) )
					break;
				int len = in.readWord ();
				int sx = in.readShort ();
				int sy = in.readShort ();
				cb.moveTo ( state.transformX ( sx ), state.transformY ( sy ) );
				for ( int k = 1; k < len; ++k ) {
					int x = in.readShort ();
					int y = in.readShort ();
					cb.lineTo ( state.transformX ( x ), state.transformY ( y ) );
				}
				cb.lineTo ( state.transformX ( sx ), state.transformY ( sy ) );
				strokeAndFill ();
				break;
			}
			case META_POLYPOLYGON: {
				if ( isNullStrokeFill ( false ) )
					break;
				int numPoly = in.readWord ();
				int[] lens = new int[numPoly];
				for ( int k = 0; k < lens.length; ++k )
					lens[k] = in.readWord ();
				for ( int len : lens ) {
					int sx = in.readShort ();
					int sy = in.readShort ();
					cb.moveTo ( state.transformX ( sx ), state.transformY ( sy ) );
					for ( int k = 1; k < len; ++k ) {
						int x = in.readShort ();
						int y = in.readShort ();
						cb.lineTo ( state.transformX ( x ), state.transformY ( y ) );
					}
					cb.lineTo ( state.transformX ( sx ), state.transformY ( sy ) );
				}
				strokeAndFill ();
				break;
			}
			case META_ELLIPSE: {
				if ( isNullStrokeFill ( state.getLineNeutral () ) )
					break;
				int b = in.readShort ();
				int r = in.readShort ();
				int t = in.readShort ();
				int l = in.readShort ();
				cb.arc ( state.transformX ( l ), state.transformY ( b ), state.transformX ( r ), state.transformY ( t ), 0, 360 );
				strokeAndFill ();
				break;
			}
			case META_ARC: {
				if ( isNullStrokeFill ( state.getLineNeutral () ) )
					break;
				float yend = state.transformY ( in.readShort () );
				float xend = state.transformX ( in.readShort () );
				float ystart = state.transformY ( in.readShort () );
				float xstart = state.transformX ( in.readShort () );
				float b = state.transformY ( in.readShort () );
				float r = state.transformX ( in.readShort () );
				float t = state.transformY ( in.readShort () );
				float l = state.transformX ( in.readShort () );
				float cx = ( r + l ) / 2;
				float cy = ( t + b ) / 2;
				float arc1 = getArc ( cx, cy, xstart, ystart );
				float arc2 = getArc ( cx, cy, xend, yend );
				arc2 -= arc1;
				if ( arc2 <= 0 )
					arc2 += 360;
				cb.arc ( l, b, r, t, arc1, arc2 );
				cb.stroke ();
				break;
			}
			case META_PIE: {
				if ( isNullStrokeFill ( state.getLineNeutral () ) )
					break;
				float yend = state.transformY ( in.readShort () );
				float xend = state.transformX ( in.readShort () );
				float ystart = state.transformY ( in.readShort () );
				float xstart = state.transformX ( in.readShort () );
				float b = state.transformY ( in.readShort () );
				float r = state.transformX ( in.readShort () );
				float t = state.transformY ( in.readShort () );
				float l = state.transformX ( in.readShort () );
				float cx = ( r + l ) / 2;
				float cy = ( t + b ) / 2;
				float arc1 = getArc ( cx, cy, xstart, ystart );
				float arc2 = getArc ( cx, cy, xend, yend );
				arc2 -= arc1;
				if ( arc2 <= 0 )
					arc2 += 360;
				ArrayList<float[]> ar = PdfContentByte.bezierArc ( l, b, r, t, arc1, arc2 );
				if ( ar.isEmpty () )
					break;
				float[] pt = ar.get ( 0 );
				cb.moveTo ( cx, cy );
				cb.lineTo ( pt[0], pt[1] );
				for ( float[] floats : ar ) {
					pt = floats;
					cb.curveTo ( pt[2], pt[3], pt[4], pt[5], pt[6], pt[7] );
				}
				cb.lineTo ( cx, cy );
				strokeAndFill ();
				break;
			}
			case META_CHORD: {
				if ( isNullStrokeFill ( state.getLineNeutral () ) )
					break;
				float yend = state.transformY ( in.readShort () );
				float xend = state.transformX ( in.readShort () );
				float ystart = state.transformY ( in.readShort () );
				float xstart = state.transformX ( in.readShort () );
				float b = state.transformY ( in.readShort () );
				float r = state.transformX ( in.readShort () );
				float t = state.transformY ( in.readShort () );
				float l = state.transformX ( in.readShort () );
				float cx = ( r + l ) / 2;
				float cy = ( t + b ) / 2;
				float arc1 = getArc ( cx, cy, xstart, ystart );
				float arc2 = getArc ( cx, cy, xend, yend );
				arc2 -= arc1;
				if ( arc2 <= 0 )
					arc2 += 360;
				ArrayList<float[]> ar = PdfContentByte.bezierArc ( l, b, r, t, arc1, arc2 );
				if ( ar.isEmpty () )
					break;
				float[] pt = ar.get ( 0 );
				cx = pt[0];
				cy = pt[1];
				cb.moveTo ( cx, cy );
				for ( float[] floats : ar ) {
					pt = floats;
					cb.curveTo ( pt[2], pt[3], pt[4], pt[5], pt[6], pt[7] );
				}
				cb.lineTo ( cx, cy );
				strokeAndFill ();
				break;
			}
			case META_RECTANGLE: {
				if ( isNullStrokeFill ( true ) )
					break;
				float b = state.transformY ( in.readShort () );
				float r = state.transformX ( in.readShort () );
				float t = state.transformY ( in.readShort () );
				float l = state.transformX ( in.readShort () );
				cb.rectangle ( l, b, r - l, t - b );
				strokeAndFill ();
				break;
			}
			case META_ROUNDRECT: {
				if ( isNullStrokeFill ( true ) )
					break;
				float h = state.transformY ( 0 ) - state.transformY ( in.readShort () );
				float w = state.transformX ( in.readShort () ) - state.transformX ( 0 );
				float b = state.transformY ( in.readShort () );
				float r = state.transformX ( in.readShort () );
				float t = state.transformY ( in.readShort () );
				float l = state.transformX ( in.readShort () );
				cb.roundRectangle ( l, b, r - l, t - b, ( h + w ) / 4 );
				strokeAndFill ();
				break;
			}
			case META_INTERSECTCLIPRECT: {
				float b = state.transformY ( in.readShort () );
				float r = state.transformX ( in.readShort () );
				float t = state.transformY ( in.readShort () );
				float l = state.transformX ( in.readShort () );
				cb.rectangle ( l, b, r - l, t - b );
				cb.eoClip ();
				cb.newPath ();
				break;
			}
			case META_EXTTEXTOUT: {
				int y = in.readShort ();
				int x = in.readShort ();
				int count = in.readWord ();
				int flag = in.readWord ();
				if ( ( flag & ( MetaFont.ETO_CLIPPED | MetaFont.ETO_OPAQUE ) ) != 0 ) {
					in.readShort ();
					in.readShort ();
					in.readShort ();
					in.readShort ();
				}
				byte[] text = new byte[count];
				int k;
				for ( k = 0; k < count; ++k ) {
					byte c = (byte) in.readByte ();
					if ( c == 0 )
						break;
					text[k] = c;
				}
				String s;
				try {
					s = new String ( text, 0, k, "Cp1252" );
				} catch ( UnsupportedEncodingException e ) {
					s = new String ( text, 0, k );
				}
				outputText ( x, y, s );
				break;
			}
			case META_TEXTOUT: {
				int count = in.readWord ();
				byte[] text = new byte[count];
				int k;
				for ( k = 0; k < count; ++k ) {
					byte c = (byte) in.readByte ();
					if ( c == 0 )
						break;
					text[k] = c;
				}
				String s;
				try {
					s = new String ( text, 0, k, "Cp1252" );
				} catch ( UnsupportedEncodingException e ) {
					s = new String ( text, 0, k );
				}
				count = count + 1 & 0xfffe;
				in.skip ( count - k );
				int y = in.readShort ();
				int x = in.readShort ();
				outputText ( x, y, s );
				break;
			}
			case META_SETBKCOLOR:
				state.setCurrentBackgroundColor ( in.readColor () );
				break;
			case META_SETTEXTCOLOR:
				state.setCurrentTextColor ( in.readColor () );
				break;
			case META_SETTEXTALIGN:
				state.setTextAlign ( in.readWord () );
				break;
			case META_SETBKMODE:
				state.setBackgroundMode ( in.readWord () );
				break;
			case META_SETPOLYFILLMODE:
				state.setPolyFillMode ( in.readWord () );
				break;
			case META_SETPIXEL: {
				BaseColor color = in.readColor ();
				int y = in.readShort ();
				int x = in.readShort ();
				cb.saveState ();
				cb.setColorFill ( color );
				cb.rectangle ( state.transformX ( x ), state.transformY ( y ), .2f, .2f );
				cb.fill ();
				cb.restoreState ();
				break;
			}
			case META_DIBSTRETCHBLT:
			case META_STRETCHDIB: {
				in.readInt ();
				if ( function == META_STRETCHDIB ) {
					in.readWord ();
				}
				int srcHeight = in.readShort ();
				int srcWidth = in.readShort ();
				int ySrc = in.readShort ();
				int xSrc = in.readShort ();
				float destHeight = state.transformY ( in.readShort () ) - state.transformY ( 0 );
				float destWidth = state.transformX ( in.readShort () ) - state.transformX ( 0 );
				float yDest = state.transformY ( in.readShort () );
				float xDest = state.transformX ( in.readShort () );
				byte[] b = new byte[tsize * 2 - ( in.getLength () - lenMarker )];
				for ( int k = 0; k < b.length; ++k )
					b[k] = (byte) in.readByte ();
				try {
					ByteArrayInputStream inb = new ByteArrayInputStream ( b );
					Image bmp = BmpImage.getImage ( inb, true, b.length );
					cb.saveState ();
					cb.rectangle ( xDest, yDest, destWidth, destHeight );
					cb.clip ();
					cb.newPath ();
					bmp.scaleAbsolute ( destWidth * bmp.getWidth () / srcWidth, -destHeight * bmp.getHeight () / srcHeight );
					bmp.setAbsolutePosition ( xDest - destWidth * xSrc / srcWidth, yDest + destHeight * ySrc / srcHeight - bmp.getScaledHeight () );
					cb.addImage ( bmp );
					cb.restoreState ();
				} catch ( Exception e ) {
					// empty on purpose
				}
				break;
			}
			}
			in.skip ( tsize * 2 - ( in.getLength () - lenMarker ) );
		}
		state.cleanup ( cb );
	}

	public void outputText ( int x, int y, String text ) {
		MetaFont font = state.getCurrentFont ();
		float refX = state.transformX ( x );
		float refY = state.transformY ( y );
		float angle = state.transformAngle ( font.getAngle () );
		float sin = (float) Math.sin ( angle );
		float cos = (float) Math.cos ( angle );
		float fontSize = font.getFontSize ( state );
		BaseFont bf = font.getFont ();
		int align = state.getTextAlign ();
		float textWidth = bf.getWidthPoint ( text, fontSize );
		float tx = 0;
		float ty;
		float descender = bf.getFontDescriptor ( BaseFont.DESCENT, fontSize );
		float ury = bf.getFontDescriptor ( BaseFont.BBOXURY, fontSize );
		cb.saveState ();
		cb.concatCTM ( cos, sin, -sin, cos, refX, refY );
		if ( ( align & MetaState.TA_CENTER ) == MetaState.TA_CENTER )
			tx = -textWidth / 2;
		else if ( ( align & MetaState.TA_RIGHT ) == MetaState.TA_RIGHT )
			tx = -textWidth;
		if ( ( align & MetaState.TA_BASELINE ) == MetaState.TA_BASELINE )
			ty = 0;
		else if ( ( align & MetaState.TA_BOTTOM ) == MetaState.TA_BOTTOM )
			ty = -descender;
		else
			ty = -ury;
		BaseColor textColor;
		if ( state.getBackgroundMode () == MetaState.OPAQUE ) {
			textColor = state.getCurrentBackgroundColor ();
			cb.setColorFill ( textColor );
			cb.rectangle ( tx, ty + descender, textWidth, ury - descender );
			cb.fill ();
		}
		textColor = state.getCurrentTextColor ();
		cb.setColorFill ( textColor );
		cb.beginText ();
		cb.setFontAndSize ( bf, fontSize );
		cb.setTextMatrix ( tx, ty );
		cb.showText ( text );
		cb.endText ();
		if ( font.isUnderline () ) {
			cb.rectangle ( tx, ty - fontSize / 4, textWidth, fontSize / 15 );
			cb.fill ();
		}
		if ( font.isStrikeout () ) {
			cb.rectangle ( tx, ty + fontSize / 3, textWidth, fontSize / 15 );
			cb.fill ();
		}
		cb.restoreState ();
	}

	public boolean isNullStrokeFill ( boolean isRectangle ) {
		MetaPen pen = state.getCurrentPen ();
		MetaBrush brush = state.getCurrentBrush ();
		boolean noPen = pen.getStyle () == MetaPen.PS_NULL;
		int style = brush.getStyle ();
		boolean isBrush = style == MetaBrush.BS_SOLID || style == MetaBrush.BS_HATCHED && state.getBackgroundMode () == MetaState.OPAQUE;
		boolean result = noPen && !isBrush;
		if ( !noPen ) {
			if ( isRectangle )
				state.setLineJoinRectangle ( cb );
			else
				state.setLineJoinPolygon ( cb );
		}
		return result;
	}

	public void strokeAndFill () {
		MetaPen pen = state.getCurrentPen ();
		MetaBrush brush = state.getCurrentBrush ();
		int penStyle = pen.getStyle ();
		int brushStyle = brush.getStyle ();
		if ( penStyle == MetaPen.PS_NULL ) {
			cb.closePath ();
			if ( state.getPolyFillMode () == MetaState.ALTERNATE ) {
				cb.eoFill ();
			} else {
				cb.fill ();
			}
		} else {
			boolean isBrush = brushStyle == MetaBrush.BS_SOLID || brushStyle == MetaBrush.BS_HATCHED && state.getBackgroundMode () == MetaState.OPAQUE;
			if ( isBrush ) {
				if ( state.getPolyFillMode () == MetaState.ALTERNATE )
					cb.closePathEoFillStroke ();
				else
					cb.closePathFillStroke ();
			} else {
				cb.closePathStroke ();
			}
		}
	}

}

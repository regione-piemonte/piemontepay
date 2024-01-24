/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt.geom.AffineTransform;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt.geom.Point2D;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Annotation;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ImgJBIG2;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Rectangle;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.exceptions.IllegalPdfSyntaxException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.IAccessibleElement;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal.PdfAnnotationsImp;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal.PdfIsoKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PdfContentByte {

	public static final int ALIGN_CENTER = Element.ALIGN_CENTER;

	public static final int ALIGN_LEFT = Element.ALIGN_LEFT;

	public static final int ALIGN_RIGHT = Element.ALIGN_RIGHT;

	public static final int LINE_CAP_BUTT = 0;

	public static final int LINE_CAP_ROUND = 1;

	public static final int LINE_CAP_PROJECTING_SQUARE = 2;

	public static final int LINE_JOIN_MITER = 0;

	public static final int LINE_JOIN_ROUND = 1;

	public static final int LINE_JOIN_BEVEL = 2;

	public static final int TEXT_RENDER_MODE_FILL = 0;

	public static final int TEXT_RENDER_MODE_STROKE = 1;

	public static final int TEXT_RENDER_MODE_FILL_STROKE = 2;

	public static final int TEXT_RENDER_MODE_INVISIBLE = 3;

	public static final int TEXT_RENDER_MODE_FILL_CLIP = 4;

	public static final int TEXT_RENDER_MODE_STROKE_CLIP = 5;

	public static final int TEXT_RENDER_MODE_FILL_STROKE_CLIP = 6;

	public static final int TEXT_RENDER_MODE_CLIP = 7;

	private static final float[] unitRect = { 0, 0, 0, 1, 1, 0, 1, 1 };

	private static final HashMap<PdfName, String> abrev = new HashMap<> ();

	static {
		abrev.put ( PdfName.BITSPERCOMPONENT, "/BPC " );
		abrev.put ( PdfName.COLORSPACE, "/CS " );
		abrev.put ( PdfName.DECODE, "/D " );
		abrev.put ( PdfName.DECODEPARMS, "/DP " );
		abrev.put ( PdfName.FILTER, "/F " );
		abrev.put ( PdfName.HEIGHT, "/H " );
		abrev.put ( PdfName.IMAGEMASK, "/IM " );
		abrev.put ( PdfName.INTENT, "/Intent " );
		abrev.put ( PdfName.INTERPOLATE, "/I " );
		abrev.put ( PdfName.WIDTH, "/W " );
	}

	protected ByteBuffer content = new ByteBuffer ();

	protected int markedContentSize = 0;

	protected PdfWriter writer;

	protected PdfDocument pdf;

	protected GraphicState state = new GraphicState ();

	protected ArrayList<GraphicState> stateList = new ArrayList<> ();

	protected ArrayList<Integer> layerDepth;

	protected int separator = '\n';

	protected PdfContentByte duplicatedFrom = null;

	private int mcDepth = 0;

	private boolean inText = false;

	private ArrayList<IAccessibleElement> mcElements = new ArrayList<> ();

	public PdfContentByte ( final PdfWriter wr ) {
		if ( wr != null ) {
			writer = wr;
			pdf = writer.getPdfDocument ();
		}
	}

	static byte[] escapeString ( final byte[] b ) {
		ByteBuffer content = new ByteBuffer ();
		escapeString ( b, content );
		return content.toByteArray ();
	}

	static void escapeString ( final byte[] b, final ByteBuffer content ) {
		content.append_i ( '(' );
		for ( byte c : b ) {
			switch ( c ) {
			case '\r':
				content.append ( "\\r" );
				break;
			case '\n':
				content.append ( "\\n" );
				break;
			case '\t':
				content.append ( "\\t" );
				break;
			case '\b':
				content.append ( "\\b" );
				break;
			case '\f':
				content.append ( "\\f" );
				break;
			case '(':
			case ')':
			case '\\':
				content.append_i ( '\\' ).append_i ( c );
				break;
			default:
				content.append_i ( c );
			}
		}
		content.append ( ")" );
	}

	public static ArrayList<float[]> bezierArc ( float x1, float y1, float x2, float y2, final float startAng, final float extent ) {
		float tmp;
		if ( x1 > x2 ) {
			tmp = x1;
			x1 = x2;
			x2 = tmp;
		}
		if ( y2 > y1 ) {
			tmp = y1;
			y1 = y2;
			y2 = tmp;
		}

		float fragAngle;
		int Nfrag;
		if ( Math.abs ( extent ) <= 90f ) {
			fragAngle = extent;
			Nfrag = 1;
		} else {
			Nfrag = (int) Math.ceil ( Math.abs ( extent ) / 90f );
			fragAngle = extent / Nfrag;
		}
		float x_cen = ( x1 + x2 ) / 2f;
		float y_cen = ( y1 + y2 ) / 2f;
		float rx = ( x2 - x1 ) / 2f;
		float ry = ( y2 - y1 ) / 2f;
		float halfAng = (float) ( fragAngle * Math.PI / 360. );
		float kappa = (float) Math.abs ( 4. / 3. * ( 1. - Math.cos ( halfAng ) ) / Math.sin ( halfAng ) );
		ArrayList<float[]> pointList = new ArrayList<> ();
		for ( int i = 0; i < Nfrag; ++i ) {
			float theta0 = (float) ( ( startAng + i * fragAngle ) * Math.PI / 180. );
			float theta1 = (float) ( ( startAng + ( i + 1 ) * fragAngle ) * Math.PI / 180. );
			float cos0 = (float) Math.cos ( theta0 );
			float cos1 = (float) Math.cos ( theta1 );
			float sin0 = (float) Math.sin ( theta0 );
			float sin1 = (float) Math.sin ( theta1 );
			if ( fragAngle > 0f ) {
				pointList.add ( new float[] { x_cen + rx * cos0,
								y_cen - ry * sin0,
								x_cen + rx * ( cos0 - kappa * sin0 ),
								y_cen - ry * ( sin0 + kappa * cos0 ),
								x_cen + rx * ( cos1 + kappa * sin1 ),
								y_cen - ry * ( sin1 - kappa * cos1 ),
								x_cen + rx * cos1,
								y_cen - ry * sin1 } );
			} else {
				pointList.add ( new float[] { x_cen + rx * cos0,
								y_cen - ry * sin0,
								x_cen + rx * ( cos0 + kappa * sin0 ),
								y_cen - ry * ( sin0 - kappa * cos0 ),
								x_cen + rx * ( cos1 - kappa * sin1 ),
								y_cen - ry * ( sin1 + kappa * cos1 ),
								x_cen + rx * cos1,
								y_cen - ry * sin1 } );
			}
		}
		return pointList;
	}

	@Override
	public String toString () {
		return content.toString ();
	}

	public boolean isTagged () {
		return writer != null && writer.isTagged ();
	}

	public ByteBuffer getInternalBuffer () {
		return content;
	}

	public byte[] toPdf () {
		sanityCheck ();
		return content.toByteArray ();
	}

	public void add ( final PdfContentByte other ) {
		if ( other.writer != null && writer != other.writer )
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "inconsistent.writers.are.you.mixing.two.documents" ) );
		content.append ( other.content );
		markedContentSize += other.markedContentSize;
	}

	public float getXTLM () {
		return state.xTLM;
	}

	public float getYTLM () {
		return state.yTLM;
	}

	public float getLeading () {
		return state.leading;
	}

	public void setLeading ( final float leading ) {
		if ( !inText && isTagged () ) {
			beginText ( true );
		}
		state.leading = leading;
		content.append ( leading ).append ( " TL" ).append_i ( separator );
	}

	public float getCharacterSpacing () {
		return state.charSpace;
	}

	public void setCharacterSpacing ( final float charSpace ) {
		if ( !inText && isTagged () ) {
			beginText ( true );
		}
		state.charSpace = charSpace;
		content.append ( charSpace ).append ( " Tc" ).append_i ( separator );
	}

	public void setLineCap ( final int style ) {
		if ( style >= 0 && style <= 2 ) {
			content.append ( style ).append ( " J" ).append_i ( separator );
		}
	}

	public void setLineDash ( final float phase ) {
		content.append ( "[] " ).append ( phase ).append ( " d" ).append_i ( separator );
	}

	public void setLineDash ( final float unitsOn, final float phase ) {
		content.append ( "[" ).append ( unitsOn ).append ( "] " ).append ( phase ).append ( " d" ).append_i ( separator );
	}

	public void setLineDash ( final float unitsOn, final float unitsOff, final float phase ) {
		content.append ( "[" ).append ( unitsOn ).append ( ' ' ).append ( unitsOff ).append ( "] " ).append ( phase ).append ( " d" ).append_i ( separator );
	}

	public void setLineJoin ( final int style ) {
		if ( style >= 0 && style <= 2 ) {
			content.append ( style ).append ( " j" ).append_i ( separator );
		}
	}

	public void setLineWidth ( final float w ) {
		content.append ( w ).append ( " w" ).append_i ( separator );
	}

	public void setMiterLimit ( final float miterLimit ) {
		if ( miterLimit > 1 ) {
			content.append ( miterLimit ).append ( " M" ).append_i ( separator );
		}
	}

	public void clip () {
		if ( inText && isTagged () ) {
			endText ();
		}
		content.append ( "W" ).append_i ( separator );
	}

	public void eoClip () {
		if ( inText && isTagged () ) {
			endText ();
		}
		content.append ( "W*" ).append_i ( separator );
	}

	public void setGrayFill ( final float gray ) {
		saveColor ( new GrayColor ( gray ), true );
		content.append ( gray ).append ( " g" ).append_i ( separator );
	}

	public void resetGrayFill () {
		saveColor ( new GrayColor ( 0 ), true );
		content.append ( "0 g" ).append_i ( separator );
	}

	public void setGrayStroke ( final float gray ) {
		saveColor ( new GrayColor ( gray ), false );
		content.append ( gray ).append ( " G" ).append_i ( separator );
	}

	public void resetGrayStroke () {
		saveColor ( new GrayColor ( 0 ), false );
		content.append ( "0 G" ).append_i ( separator );
	}

	private void HelperRGB ( float red, float green, float blue ) {
		if ( red < 0 )
			red = 0.0f;
		else if ( red > 1.0f )
			red = 1.0f;
		if ( green < 0 )
			green = 0.0f;
		else if ( green > 1.0f )
			green = 1.0f;
		if ( blue < 0 )
			blue = 0.0f;
		else if ( blue > 1.0f )
			blue = 1.0f;
		content.append ( red ).append ( ' ' ).append ( green ).append ( ' ' ).append ( blue );
	}

	public void setRGBColorFillF ( final float red, final float green, final float blue ) {
		saveColor ( new BaseColor ( red, green, blue ), true );
		HelperRGB ( red, green, blue );
		content.append ( " rg" ).append_i ( separator );
	}

	public void resetRGBColorFill () {
		resetGrayFill ();
	}

	public void setRGBColorStrokeF ( final float red, final float green, final float blue ) {
		saveColor ( new BaseColor ( red, green, blue ), false );
		HelperRGB ( red, green, blue );
		content.append ( " RG" ).append_i ( separator );
	}

	public void resetRGBColorStroke () {
		resetGrayStroke ();
	}

	private void HelperCMYK ( float cyan, float magenta, float yellow, float black ) {
		if ( cyan < 0 )
			cyan = 0.0f;
		else if ( cyan > 1.0f )
			cyan = 1.0f;
		if ( magenta < 0 )
			magenta = 0.0f;
		else if ( magenta > 1.0f )
			magenta = 1.0f;
		if ( yellow < 0 )
			yellow = 0.0f;
		else if ( yellow > 1.0f )
			yellow = 1.0f;
		if ( black < 0 )
			black = 0.0f;
		else if ( black > 1.0f )
			black = 1.0f;
		content.append ( cyan ).append ( ' ' ).append ( magenta ).append ( ' ' ).append ( yellow ).append ( ' ' ).append ( black );
	}

	public void setCMYKColorFillF ( final float cyan, final float magenta, final float yellow, final float black ) {
		saveColor ( new CMYKColor ( cyan, magenta, yellow, black ), true );
		HelperCMYK ( cyan, magenta, yellow, black );
		content.append ( " k" ).append_i ( separator );
	}

	public void resetCMYKColorFill () {
		saveColor ( new CMYKColor ( 0, 0, 0, 1 ), true );
		content.append ( "0 0 0 1 k" ).append_i ( separator );
	}

	public void setCMYKColorStrokeF ( final float cyan, final float magenta, final float yellow, final float black ) {
		saveColor ( new CMYKColor ( cyan, magenta, yellow, black ), false );
		HelperCMYK ( cyan, magenta, yellow, black );
		content.append ( " K" ).append_i ( separator );
	}

	public void resetCMYKColorStroke () {
		saveColor ( new CMYKColor ( 0, 0, 0, 1 ), false );
		content.append ( "0 0 0 1 K" ).append_i ( separator );
	}

	public void moveTo ( final float x, final float y ) {
		if ( inText ) {
			if ( isTagged () ) {
				endText ();
			} else {
				throw new IllegalPdfSyntaxException ( MessageLocalization.getComposedMessage ( "path.construction.operator.inside.text.object" ) );
			}
		}
		content.append ( x ).append ( ' ' ).append ( y ).append ( " m" ).append_i ( separator );
	}

	public void lineTo ( final float x, final float y ) {
		if ( inText ) {
			if ( isTagged () ) {
				endText ();
			} else {
				throw new IllegalPdfSyntaxException ( MessageLocalization.getComposedMessage ( "path.construction.operator.inside.text.object" ) );
			}
		}
		content.append ( x ).append ( ' ' ).append ( y ).append ( " l" ).append_i ( separator );
	}

	public void curveTo ( final float x1, final float y1, final float x2, final float y2, final float x3, final float y3 ) {
		if ( inText ) {
			if ( isTagged () ) {
				endText ();
			} else {
				throw new IllegalPdfSyntaxException ( MessageLocalization.getComposedMessage ( "path.construction.operator.inside.text.object" ) );
			}
		}
		content.append ( x1 ).append ( ' ' ).append ( y1 ).append ( ' ' ).append ( x2 ).append ( ' ' ).append ( y2 ).append ( ' ' ).append ( x3 ).append ( ' ' )
						.append ( y3 ).append ( " c" ).append_i ( separator );
	}

	public void curveTo ( final float x2, final float y2, final float x3, final float y3 ) {
		if ( inText ) {
			if ( isTagged () ) {
				endText ();
			} else {
				throw new IllegalPdfSyntaxException ( MessageLocalization.getComposedMessage ( "path.construction.operator.inside.text.object" ) );
			}
		}
		content.append ( x2 ).append ( ' ' ).append ( y2 ).append ( ' ' ).append ( x3 ).append ( ' ' ).append ( y3 ).append ( " v" ).append_i ( separator );
	}

	public void circle ( final float x, final float y, final float r ) {
		float b = 0.5523f;
		moveTo ( x + r, y );
		curveTo ( x + r, y + r * b, x + r * b, y + r, x, y + r );
		curveTo ( x - r * b, y + r, x - r, y + r * b, x - r, y );
		curveTo ( x - r, y - r * b, x - r * b, y - r, x, y - r );
		curveTo ( x + r * b, y - r, x + r, y - r * b, x + r, y );
	}

	public void rectangle ( final float x, final float y, final float w, final float h ) {
		if ( inText ) {
			if ( isTagged () ) {
				endText ();
			} else {
				throw new IllegalPdfSyntaxException ( MessageLocalization.getComposedMessage ( "path.construction.operator.inside.text.object" ) );
			}
		}
		content.append ( x ).append ( ' ' ).append ( y ).append ( ' ' ).append ( w ).append ( ' ' ).append ( h ).append ( " re" ).append_i ( separator );
	}

	private boolean compareColors ( final BaseColor c1, final BaseColor c2 ) {
		if ( c1 == null && c2 == null )
			return true;
		if ( c1 == null || c2 == null )
			return false;
		if ( c1 instanceof ExtendedColor )
			return c1.equals ( c2 );
		return c2.equals ( c1 );
	}

	public void variableRectangle ( final Rectangle rect ) {
		float t = rect.getTop ();
		float b = rect.getBottom ();
		float r = rect.getRight ();
		float l = rect.getLeft ();
		float wt = rect.getBorderWidthTop ();
		float wb = rect.getBorderWidthBottom ();
		float wr = rect.getBorderWidthRight ();
		float wl = rect.getBorderWidthLeft ();
		BaseColor ct = rect.getBorderColorTop ();
		BaseColor cb = rect.getBorderColorBottom ();
		BaseColor cr = rect.getBorderColorRight ();
		BaseColor cl = rect.getBorderColorLeft ();
		saveState ();
		setLineCap ( PdfContentByte.LINE_CAP_BUTT );
		setLineJoin ( PdfContentByte.LINE_JOIN_MITER );
		float clw = 0;
		boolean cdef = false;
		BaseColor ccol = null;
		boolean cdefi = false;
		BaseColor cfil = null;
		// draw top
		if ( wt > 0 ) {
			setLineWidth ( clw = wt );
			cdef = true;
			if ( ct == null )
				resetRGBColorStroke ();
			else
				setColorStroke ( ct );
			ccol = ct;
			moveTo ( l, t - wt / 2f );
			lineTo ( r, t - wt / 2f );
			stroke ();
		}

		// Draw bottom
		if ( wb > 0 ) {
			if ( wb != clw )
				setLineWidth ( clw = wb );
			if ( !cdef || !compareColors ( ccol, cb ) ) {
				cdef = true;
				if ( cb == null )
					resetRGBColorStroke ();
				else
					setColorStroke ( cb );
				ccol = cb;
			}
			moveTo ( r, b + wb / 2f );
			lineTo ( l, b + wb / 2f );
			stroke ();
		}

		// Draw right
		if ( wr > 0 ) {
			if ( wr != clw )
				setLineWidth ( clw = wr );
			if ( !cdef || !compareColors ( ccol, cr ) ) {
				cdef = true;
				if ( cr == null )
					resetRGBColorStroke ();
				else
					setColorStroke ( cr );
				ccol = cr;
			}
			boolean bt = compareColors ( ct, cr );
			boolean bb = compareColors ( cb, cr );
			moveTo ( r - wr / 2f, bt ? t : t - wt );
			lineTo ( r - wr / 2f, bb ? b : b + wb );
			stroke ();
			if ( !bt || !bb ) {
				cdefi = true;
				if ( cr == null )
					resetRGBColorFill ();
				else
					setColorFill ( cr );
				cfil = cr;
				if ( !bt ) {
					moveTo ( r, t );
					lineTo ( r, t - wt );
					lineTo ( r - wr, t - wt );
					fill ();
				}
				if ( !bb ) {
					moveTo ( r, b );
					lineTo ( r, b + wb );
					lineTo ( r - wr, b + wb );
					fill ();
				}
			}
		}

		// Draw Left
		if ( wl > 0 ) {
			if ( wl != clw )
				setLineWidth ( wl );
			if ( !cdef || !compareColors ( ccol, cl ) ) {
				if ( cl == null )
					resetRGBColorStroke ();
				else
					setColorStroke ( cl );
			}
			boolean bt = compareColors ( ct, cl );
			boolean bb = compareColors ( cb, cl );
			moveTo ( l + wl / 2f, bt ? t : t - wt );
			lineTo ( l + wl / 2f, bb ? b : b + wb );
			stroke ();
			if ( !bt || !bb ) {
				if ( !cdefi || !compareColors ( cfil, cl ) ) {
					if ( cl == null )
						resetRGBColorFill ();
					else
						setColorFill ( cl );
				}
				if ( !bt ) {
					moveTo ( l, t );
					lineTo ( l, t - wt );
					lineTo ( l + wl, t - wt );
					fill ();
				}
				if ( !bb ) {
					moveTo ( l, b );
					lineTo ( l, b + wb );
					lineTo ( l + wl, b + wb );
					fill ();
				}
			}
		}
		restoreState ();
	}

	public void rectangle ( final Rectangle rectangle ) {
		// the coordinates of the border are retrieved
		float x1 = rectangle.getLeft ();
		float y1 = rectangle.getBottom ();
		float x2 = rectangle.getRight ();
		float y2 = rectangle.getTop ();

		// the backgroundcolor is set
		BaseColor background = rectangle.getBackgroundColor ();
		if ( background != null ) {
			saveState ();
			setColorFill ( background );
			rectangle ( x1, y1, x2 - x1, y2 - y1 );
			fill ();
			restoreState ();
		}

		if ( !rectangle.hasBorders () ) {
			return;
		}

		if ( rectangle.isUseVariableBorders () ) {
			variableRectangle ( rectangle );
		} else {
			if ( rectangle.getBorderWidth () != Rectangle.UNDEFINED ) {
				setLineWidth ( rectangle.getBorderWidth () );
			}
			BaseColor color = rectangle.getBorderColor ();
			if ( color != null ) {
				setColorStroke ( color );
			}

			if ( rectangle.hasBorder ( Rectangle.BOX ) ) {
				rectangle ( x1, y1, x2 - x1, y2 - y1 );
			}
			else {
				if ( rectangle.hasBorder ( Rectangle.RIGHT ) ) {
					moveTo ( x2, y1 );
					lineTo ( x2, y2 );
				}
				if ( rectangle.hasBorder ( Rectangle.LEFT ) ) {
					moveTo ( x1, y1 );
					lineTo ( x1, y2 );
				}
				if ( rectangle.hasBorder ( Rectangle.BOTTOM ) ) {
					moveTo ( x1, y1 );
					lineTo ( x2, y1 );
				}
				if ( rectangle.hasBorder ( Rectangle.TOP ) ) {
					moveTo ( x1, y2 );
					lineTo ( x2, y2 );
				}
			}

			stroke ();

			if ( color != null ) {
				resetRGBColorStroke ();
			}
		}
	}

	public void closePath () {
		if ( inText ) {
			if ( isTagged () ) {
				endText ();
			} else {
				throw new IllegalPdfSyntaxException ( MessageLocalization.getComposedMessage ( "path.construction.operator.inside.text.object" ) );
			}
		}
		content.append ( "h" ).append_i ( separator );
	}

	public void newPath () {
		if ( inText ) {
			if ( isTagged () ) {
				endText ();
			} else {
				throw new IllegalPdfSyntaxException ( MessageLocalization.getComposedMessage ( "path.construction.operator.inside.text.object" ) );
			}
		}
		content.append ( "n" ).append_i ( separator );
	}

	public void stroke () {
		if ( inText ) {
			if ( isTagged () ) {
				endText ();
			} else {
				throw new IllegalPdfSyntaxException ( MessageLocalization.getComposedMessage ( "path.construction.operator.inside.text.object" ) );
			}
		}
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_COLOR, state.colorStroke );
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_GSTATE, state.extGState );
		content.append ( "S" ).append_i ( separator );
	}

	public void closePathStroke () {
		if ( inText ) {
			if ( isTagged () ) {
				endText ();
			} else {
				throw new IllegalPdfSyntaxException ( MessageLocalization.getComposedMessage ( "path.construction.operator.inside.text.object" ) );
			}
		}
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_COLOR, state.colorStroke );
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_GSTATE, state.extGState );
		content.append ( "s" ).append_i ( separator );
	}

	public void fill () {
		if ( inText ) {
			if ( isTagged () ) {
				endText ();
			} else {
				throw new IllegalPdfSyntaxException ( MessageLocalization.getComposedMessage ( "path.construction.operator.inside.text.object" ) );
			}
		}
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_COLOR, state.colorFill );
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_GSTATE, state.extGState );
		content.append ( "f" ).append_i ( separator );
	}

	public void eoFill () {
		if ( inText ) {
			if ( isTagged () ) {
				endText ();
			} else {
				throw new IllegalPdfSyntaxException ( MessageLocalization.getComposedMessage ( "path.construction.operator.inside.text.object" ) );
			}
		}
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_COLOR, state.colorFill );
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_GSTATE, state.extGState );
		content.append ( "f*" ).append_i ( separator );
	}

	public void closePathFillStroke () {
		if ( inText ) {
			if ( isTagged () ) {
				endText ();
			} else {
				throw new IllegalPdfSyntaxException ( MessageLocalization.getComposedMessage ( "path.construction.operator.inside.text.object" ) );
			}
		}
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_COLOR, state.colorFill );
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_COLOR, state.colorStroke );
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_GSTATE, state.extGState );
		content.append ( "b" ).append_i ( separator );
	}

	public void closePathEoFillStroke () {
		if ( inText ) {
			if ( isTagged () ) {
				endText ();
			} else {
				throw new IllegalPdfSyntaxException ( MessageLocalization.getComposedMessage ( "path.construction.operator.inside.text.object" ) );
			}
		}
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_COLOR, state.colorFill );
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_COLOR, state.colorStroke );
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_GSTATE, state.extGState );
		content.append ( "b*" ).append_i ( separator );
	}

	public void addImage ( final Image image ) throws DocumentException {
		addImage ( image, false );
	}

	public void addImage ( final Image image, final boolean inlineImage ) throws DocumentException {
		if ( !image.hasAbsoluteY () )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "the.image.must.have.absolute.positioning" ) );
		float[] matrix = image.matrix ();
		matrix[Image.CX] = image.getAbsoluteX () - matrix[Image.CX];
		matrix[Image.CY] = image.getAbsoluteY () - matrix[Image.CY];
		addImage ( image, matrix[0], matrix[1], matrix[2], matrix[3], matrix[4], matrix[5], inlineImage );
	}

	public void addImage ( final Image image, final float a, final float b, final float c, final float d, final float e, final float f )
					throws DocumentException {
		addImage ( image, a, b, c, d, e, f, false );
	}

	public void addImage ( final Image image, final float a, final float b, final float c, final float d, final float e, final float f,
					final boolean inlineImage ) throws DocumentException {
		try {
			if ( image.getLayer () != null )
				beginLayer ( image.getLayer () );
			if ( isTagged () ) {
				if ( inText )
					endText ();
				AffineTransform transform = new AffineTransform ( a, b, c, d, e, f );
				Point2D[] src = new Point2D.Float[] { new Point2D.Float ( 0, 0 ), new Point2D.Float ( 1, 0 ), new Point2D.Float ( 1, 1 ),
								new Point2D.Float ( 0, 1 ) };
				Point2D[] dst = new Point2D.Float[4];
				transform.transform ( src, 0, dst, 0, 4 );
				float left = Float.MAX_VALUE;
				float right = -Float.MAX_VALUE;
				float bottom = Float.MAX_VALUE;
				float top = -Float.MAX_VALUE;
				for ( int i = 0; i < 4; i++ ) {
					if ( dst[i].getX () < left )
						left = (float) dst[i].getX ();
					if ( dst[i].getX () > right )
						right = (float) dst[i].getX ();
					if ( dst[i].getY () < bottom )
						bottom = (float) dst[i].getY ();
					if ( dst[i].getY () > top )
						top = (float) dst[i].getY ();
				}
				image.setAccessibleAttribute ( PdfName.BBOX, new PdfArray ( new float[] { left, bottom, right, top } ) );
			}
			if ( writer != null && image.isImgTemplate () ) {
				writer.addDirectImageSimple ( image );
				PdfTemplate template = image.getTemplateData ();
				float w = template.getWidth ();
				float h = template.getHeight ();
				addTemplate ( template, a / w, b / w, c / h, d / h, e, f );
			} else {
				content.append ( "q " );
				content.append ( a ).append ( ' ' );
				content.append ( b ).append ( ' ' );
				content.append ( c ).append ( ' ' );
				content.append ( d ).append ( ' ' );
				content.append ( e ).append ( ' ' );
				content.append ( f ).append ( " cm" );
				if ( inlineImage ) {
					content.append ( "\nBI\n" );
					PdfImage pimage = new PdfImage ( image, "", null );
					if ( image instanceof ImgJBIG2 ) {
						byte[] globals = ( (ImgJBIG2) image ).getGlobalBytes ();
						if ( globals != null ) {
							PdfDictionary decodeparms = new PdfDictionary ();
							decodeparms.put ( PdfName.JBIG2GLOBALS, writer.getReferenceJBIG2Globals ( globals ) );
							pimage.put ( PdfName.DECODEPARMS, decodeparms );
						}
					}
					PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_INLINE_IMAGE, pimage );
					for ( PdfName element : pimage.getKeys () ) {
						PdfObject value = pimage.get ( element );
						String s = abrev.get ( element );
						if ( s == null )
							continue;
						content.append ( s );
						boolean check = true;
						if ( element.equals ( PdfName.COLORSPACE ) && value.isArray () ) {
							PdfArray ar = (PdfArray) value;
							if ( ar.size () == 4
											&& PdfName.INDEXED.equals ( ar.getAsName ( 0 ) )
											&& ar.getPdfObject ( 1 ).isName ()
											&& ar.getPdfObject ( 2 ).isNumber ()
											&& ar.getPdfObject ( 3 ).isString ()
							) {
								check = false;
							}

						}
						if ( check && element.equals ( PdfName.COLORSPACE ) && !value.isName () ) {
							PdfName cs = writer.getColorspaceName ();
							PageResources prs = getPageResources ();
							prs.addColor ( cs, writer.addToBody ( value ).getIndirectReference () );
							value = cs;
						}
						value.toPdf ( null, content );
						content.append ( '\n' );
					}
					content.append ( "ID\n" );
					pimage.writeContent ( content );
					content.append ( "\nEI\nQ" ).append_i ( separator );
				} else {
					PdfName name;
					PageResources prs = getPageResources ();
					Image maskImage = image.getImageMask ();
					if ( maskImage != null ) {
						name = writer.addDirectImageSimple ( maskImage );
						prs.addXObject ( name, writer.getImageReference ( name ) );
					}
					name = writer.addDirectImageSimple ( image );
					name = prs.addXObject ( name, writer.getImageReference ( name ) );
					content.append ( ' ' ).append ( name.getBytes () ).append ( " Do Q" ).append_i ( separator );
				}
			}
			if ( image.hasBorders () ) {
				saveState ();
				float w = image.getWidth ();
				float h = image.getHeight ();
				concatCTM ( a / w, b / w, c / h, d / h, e, f );
				rectangle ( image );
				restoreState ();
			}
			if ( image.getLayer () != null )
				endLayer ();
			Annotation annot = image.getAnnotation ();
			if ( annot == null )
				return;
			float[] r = new float[unitRect.length];
			for ( int k = 0; k < unitRect.length; k += 2 ) {
				r[k] = a * unitRect[k] + c * unitRect[k + 1] + e;
				r[k + 1] = b * unitRect[k] + d * unitRect[k + 1] + f;
			}
			float llx = r[0];
			float lly = r[1];
			float urx = llx;
			float ury = lly;
			for ( int k = 2; k < r.length; k += 2 ) {
				llx = Math.min ( llx, r[k] );
				lly = Math.min ( lly, r[k + 1] );
				urx = Math.max ( urx, r[k] );
				ury = Math.max ( ury, r[k + 1] );
			}
			annot = new Annotation ( annot );
			annot.setDimensions ( llx, lly, urx, ury );
			PdfAnnotation an = PdfAnnotationsImp.convertAnnotation ( writer, annot, new Rectangle ( llx, lly, urx, ury ) );
			addAnnotation ( an );
		} catch ( Exception ee ) {
			throw new DocumentException ( ee );
		}
	}

	public void reset () {
		reset ( true );
	}

	public void reset ( final boolean validateContent ) {
		content.reset ();
		markedContentSize = 0;
		if ( validateContent ) {
			sanityCheck ();
		}
		state = new GraphicState ();
		stateList = new ArrayList<> ();
	}

	protected void beginText ( boolean restoreTM ) {
		if ( inText ) {
			if ( isTagged () ) {

			} else {
				throw new IllegalPdfSyntaxException ( MessageLocalization.getComposedMessage ( "unbalanced.begin.end.text.operators" ) );
			}
		} else {
			inText = true;
			content.append ( "BT" ).append_i ( separator );
			if ( restoreTM ) {
				float xTLM = state.xTLM;
				float tx = state.tx;
				setTextMatrix ( state.aTLM, state.bTLM, state.cTLM, state.dTLM, state.tx, state.yTLM );
				state.xTLM = xTLM;
				state.tx = tx;
			} else {
				state.xTLM = 0;
				state.yTLM = 0;
				state.tx = 0;
			}
			if ( isTagged () ) {
				try {
					restoreColor ();
				} catch ( IOException ignored ) {

				}
			}
		}
	}

	public void beginText () {
		beginText ( false );
	}

	public void endText () {
		if ( !inText ) {
			if ( isTagged () ) {

			} else {
				throw new IllegalPdfSyntaxException ( MessageLocalization.getComposedMessage ( "unbalanced.begin.end.text.operators" ) );
			}
		} else {
			inText = false;
			content.append ( "ET" ).append_i ( separator );
			if ( isTagged () ) {
				try {
					restoreColor ();
				} catch ( IOException ignored ) {

				}

			}
		}
	}

	public void saveState () {
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_CANVAS, "q" );
		if ( inText && isTagged () ) {
			endText ();
		}
		content.append ( "q" ).append_i ( separator );
		stateList.add ( new GraphicState ( state ) );
	}

	public void restoreState () {
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_CANVAS, "Q" );
		if ( inText && isTagged () ) {
			endText ();
		}
		content.append ( "Q" ).append_i ( separator );
		int idx = stateList.size () - 1;
		if ( idx < 0 )
			throw new IllegalPdfSyntaxException ( MessageLocalization.getComposedMessage ( "unbalanced.save.restore.state.operators" ) );
		state.restore ( stateList.get ( idx ) );
		stateList.remove ( idx );
	}

	public void setWordSpacing ( final float wordSpace ) {
		if ( !inText && isTagged () ) {
			beginText ( true );
		}
		state.wordSpace = wordSpace;
		content.append ( wordSpace ).append ( " Tw" ).append_i ( separator );
	}

	public void setHorizontalScaling ( final float scale ) {
		if ( !inText && isTagged () ) {
			beginText ( true );
		}
		state.scale = scale;
		content.append ( scale ).append ( " Tz" ).append_i ( separator );
	}

	public void setFontAndSize ( final BaseFont bf, final float size ) {
		if ( !inText && isTagged () ) {
			beginText ( true );
		}
		checkWriter ();
		if ( size < 0.0001f && size > -0.0001f )
			throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "font.size.too.small.1", String.valueOf ( size ) ) );
		state.size = size;
		state.fontDetails = writer.addSimple ( bf );
		PageResources prs = getPageResources ();
		PdfName name = state.fontDetails.getFontName ();
		name = prs.addFont ( name, state.fontDetails.getIndirectReference () );
		content.append ( name.getBytes () ).append ( ' ' ).append ( size ).append ( " Tf" ).append_i ( separator );
	}

	public void setTextRenderingMode ( final int rendering ) {
		if ( !inText && isTagged () ) {
			beginText ( true );
		}
		state.textRenderMode = rendering;
		content.append ( rendering ).append ( " Tr" ).append_i ( separator );
	}

	public void setTextRise ( final float rise ) {
		if ( !inText && isTagged () ) {
			beginText ( true );
		}
		content.append ( rise ).append ( " Ts" ).append_i ( separator );
	}

	private void showText2 ( final String text ) {
		if ( state.fontDetails == null )
			throw new NullPointerException ( MessageLocalization.getComposedMessage ( "font.and.size.must.be.set.before.writing.any.text" ) );
		byte[] b = state.fontDetails.convertToBytes ( text );
		escapeString ( b, content );
	}

	public void showText ( final String text ) {
		checkState ();
		if ( !inText && isTagged () ) {
			beginText ( true );
		}
		showText2 ( text );
		updateTx ( text, 0 );
		content.append ( "Tj" ).append_i ( separator );
	}

	public void setTextMatrix ( final float a, final float b, final float c, final float d, final float x, final float y ) {
		if ( !inText && isTagged () ) {
			beginText ( true );
		}
		state.xTLM = x;
		state.yTLM = y;
		state.aTLM = a;
		state.bTLM = b;
		state.cTLM = c;
		state.dTLM = d;
		state.tx = state.xTLM;
		content.append ( a ).append ( ' ' ).append ( b ).append_i ( ' ' )
						.append ( c ).append_i ( ' ' ).append ( d ).append_i ( ' ' )
						.append ( x ).append_i ( ' ' ).append ( y ).append ( " Tm" ).append_i ( separator );
	}

	public void setTextMatrix ( final float x, final float y ) {
		setTextMatrix ( 1, 0, 0, 1, x, y );
	}

	public void moveText ( final float x, final float y ) {
		if ( !inText && isTagged () ) {
			beginText ( true );
		}
		state.xTLM += x;
		state.yTLM += y;
		if ( isTagged () && state.xTLM != state.tx ) {
			setTextMatrix ( state.aTLM, state.bTLM, state.cTLM, state.dTLM, state.xTLM, state.yTLM );
		} else {
			content.append ( x ).append ( ' ' ).append ( y ).append ( " Td" ).append_i ( separator );
		}
	}

	int size () {
		return size ( true );
	}

	int size ( boolean includeMarkedContentSize ) {
		if ( includeMarkedContentSize )
			return content.size ();
		else
			return content.size () - markedContentSize;
	}

	private float getEffectiveStringWidth ( final String text, final float kerning ) {
		BaseFont bf = state.fontDetails.getBaseFont ();
		float w;
		w = bf.getWidthPoint ( text, state.size );
		if ( state.charSpace != 0.0f && !text.isEmpty () ) {
			w += state.charSpace * ( text.length () );
		}
		if ( state.wordSpace != 0.0f && !bf.isVertical () ) {
			for ( int i = 0; i < text.length (); i++ ) {
				if ( text.charAt ( i ) == ' ' )
					w += state.wordSpace;
			}
		}
		w -= kerning / 1000.f * state.size;
		if ( state.scale != 100.0 )
			w = w * state.scale / 100.0f;
		return w;
	}

	public void concatCTM ( final float a, final float b, final float c, final float d, final float e, final float f ) {
		if ( inText && isTagged () ) {
			endText ();
		}
		state.CTM.concatenate ( new AffineTransform ( a, b, c, d, e, f ) );
		content.append ( a ).append ( ' ' ).append ( b ).append ( ' ' ).append ( c ).append ( ' ' );
		content.append ( d ).append ( ' ' ).append ( e ).append ( ' ' ).append ( f ).append ( " cm" ).append_i ( separator );
	}

	public void arc ( final float x1, final float y1, final float x2, final float y2, final float startAng, final float extent ) {
		ArrayList<float[]> ar = bezierArc ( x1, y1, x2, y2, startAng, extent );
		if ( ar.isEmpty () )
			return;
		float[] pt = ar.get ( 0 );
		moveTo ( pt[0], pt[1] );
		for ( float[] floats : ar ) {
			pt = floats;
			curveTo ( pt[2], pt[3], pt[4], pt[5], pt[6], pt[7] );
		}
	}

	public PdfPatternPainter createPattern ( final float width, final float height, final float xstep, final float ystep ) {
		checkWriter ();
		if ( xstep == 0.0f || ystep == 0.0f )
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "xstep.or.ystep.can.not.be.zero" ) );
		PdfPatternPainter painter = new PdfPatternPainter ( writer );
		painter.setWidth ( width );
		painter.setHeight ( height );
		painter.setXStep ( xstep );
		painter.setYStep ( ystep );
		writer.addSimplePattern ( painter );
		return painter;
	}

	public PdfPatternPainter createPattern ( final float width, final float height ) {
		return createPattern ( width, height, width, height );
	}

	public PdfTemplate createTemplate ( final float width, final float height ) {
		return createTemplate ( width, height, null );
	}

	PdfTemplate createTemplate ( final float width, final float height, final PdfName forcedName ) {
		checkWriter ();
		PdfTemplate template = new PdfTemplate ( writer );
		template.setWidth ( width );
		template.setHeight ( height );
		writer.addDirectTemplateSimple ( template, forcedName );
		return template;
	}

	public void addTemplate ( final PdfTemplate template, final float a, final float b, final float c, final float d, final float e, final float f ) {
		addTemplate ( template, a, b, c, d, e, f, false );
	}

	public void addTemplate ( final PdfTemplate template, final float a, final float b, final float c, final float d, final float e, final float f,
					boolean tagContent ) {
		checkWriter ();
		checkNoPattern ( template );
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_FORM_XOBJ, template );
		PdfName name = writer.addDirectTemplateSimple ( template, null );
		PageResources prs = getPageResources ();
		name = prs.addXObject ( name, template.getIndirectReference () );
		if ( isTagged () ) {
			if ( inText )
				endText ();
			if ( template.isContentTagged () || ( template.getPageReference () != null && tagContent ) ) {
				throw new RuntimeException ( MessageLocalization.getComposedMessage ( "template.with.tagged.could.not.be.used.more.than.once" ) );
			}

			template.setPageReference ( writer.getCurrentPage () );

			if ( tagContent ) {
				template.setContentTagged ( true );
				ArrayList<IAccessibleElement> allMcElements = getMcElements ();
				if ( allMcElements != null && !allMcElements.isEmpty () )
					template.getMcElements ().add ( allMcElements.get ( allMcElements.size () - 1 ) );
			} else {
				openMCBlock ( template );
			}
		}

		content.append ( "q " );
		content.append ( a ).append ( ' ' );
		content.append ( b ).append ( ' ' );
		content.append ( c ).append ( ' ' );
		content.append ( d ).append ( ' ' );
		content.append ( e ).append ( ' ' );
		content.append ( f ).append ( " cm " );
		content.append ( name.getBytes () ).append ( " Do Q" ).append_i ( separator );

		if ( isTagged () && !tagContent ) {
			closeMCBlock ( template );
			template.setId ( null );
		}
	}

	void addTemplateReference ( final PdfIndirectReference template, PdfName name, final float a, final float d, final float e,
					final float f ) {
		if ( inText && isTagged () ) {
			endText ();
		}
		checkWriter ();
		PageResources prs = getPageResources ();
		name = prs.addXObject ( name, template );
		content.append ( "q " );
		content.append ( a ).append ( ' ' );
		content.append ( (float) 0 ).append ( ' ' );
		content.append ( (float) 0 ).append ( ' ' );
		content.append ( d ).append ( ' ' );
		content.append ( e ).append ( ' ' );
		content.append ( f ).append ( " cm " );
		content.append ( name.getBytes () ).append ( " Do Q" ).append_i ( separator );
	}

	public void addTemplate ( final PdfTemplate template, final float x, final float y ) {
		addTemplate ( template, 1, 0, 0, 1, x, y );
	}

	public void setCMYKColorFill ( final int cyan, final int magenta, final int yellow, final int black ) {
		saveColor ( new CMYKColor ( cyan, magenta, yellow, black ), true );
		content.append ( (float) ( cyan & 0xFF ) / 0xFF );
		content.append ( ' ' );
		content.append ( (float) ( magenta & 0xFF ) / 0xFF );
		content.append ( ' ' );
		content.append ( (float) ( yellow & 0xFF ) / 0xFF );
		content.append ( ' ' );
		content.append ( (float) ( black & 0xFF ) / 0xFF );
		content.append ( " k" ).append_i ( separator );
	}

	public void setCMYKColorStroke ( final int cyan, final int magenta, final int yellow, final int black ) {
		saveColor ( new CMYKColor ( cyan, magenta, yellow, black ), false );
		content.append ( (float) ( cyan & 0xFF ) / 0xFF );
		content.append ( ' ' );
		content.append ( (float) ( magenta & 0xFF ) / 0xFF );
		content.append ( ' ' );
		content.append ( (float) ( yellow & 0xFF ) / 0xFF );
		content.append ( ' ' );
		content.append ( (float) ( black & 0xFF ) / 0xFF );
		content.append ( " K" ).append_i ( separator );
	}

	public void setRGBColorFill ( final int red, final int green, final int blue ) {
		saveColor ( new BaseColor ( red, green, blue ), true );
		HelperRGB ( (float) ( red & 0xFF ) / 0xFF, (float) ( green & 0xFF ) / 0xFF, (float) ( blue & 0xFF ) / 0xFF );
		content.append ( " rg" ).append_i ( separator );
	}

	public void setRGBColorStroke ( final int red, final int green, final int blue ) {
		saveColor ( new BaseColor ( red, green, blue ), false );
		HelperRGB ( (float) ( red & 0xFF ) / 0xFF, (float) ( green & 0xFF ) / 0xFF, (float) ( blue & 0xFF ) / 0xFF );
		content.append ( " RG" ).append_i ( separator );
	}

	public void setColorStroke ( final BaseColor color ) {
		int type = ExtendedColor.getType ( color );
		switch ( type ) {
		case ExtendedColor.TYPE_GRAY: {
			setGrayStroke ( ( (GrayColor) color ).getGray () );
			break;
		}
		case ExtendedColor.TYPE_CMYK: {
			CMYKColor cmyk = (CMYKColor) color;
			setCMYKColorStrokeF ( cmyk.getCyan (), cmyk.getMagenta (), cmyk.getYellow (), cmyk.getBlack () );
			break;
		}
		case ExtendedColor.TYPE_SEPARATION: {
			SpotColor spot = (SpotColor) color;
			setColorStroke ( spot.getPdfSpotColor (), spot.getTint () );
			break;
		}
		case ExtendedColor.TYPE_PATTERN: {
			PatternColor pat = (PatternColor) color;
			setPatternStroke ( pat.getPainter () );
			break;
		}
		case ExtendedColor.TYPE_SHADING: {
			ShadingColor shading = (ShadingColor) color;
			setShadingStroke ( shading.getPdfShadingPattern () );
			break;
		}
		default:
			setRGBColorStroke ( color.getRed (), color.getGreen (), color.getBlue () );
		}
	}

	public void setColorFill ( final BaseColor color ) {
		int type = ExtendedColor.getType ( color );
		switch ( type ) {
		case ExtendedColor.TYPE_GRAY: {
			setGrayFill ( ( (GrayColor) color ).getGray () );
			break;
		}
		case ExtendedColor.TYPE_CMYK: {
			CMYKColor cmyk = (CMYKColor) color;
			setCMYKColorFillF ( cmyk.getCyan (), cmyk.getMagenta (), cmyk.getYellow (), cmyk.getBlack () );
			break;
		}
		case ExtendedColor.TYPE_SEPARATION: {
			SpotColor spot = (SpotColor) color;
			setColorFill ( spot.getPdfSpotColor (), spot.getTint () );
			break;
		}
		case ExtendedColor.TYPE_PATTERN: {
			PatternColor pat = (PatternColor) color;
			setPatternFill ( pat.getPainter () );
			break;
		}
		case ExtendedColor.TYPE_SHADING: {
			ShadingColor shading = (ShadingColor) color;
			setShadingFill ( shading.getPdfShadingPattern () );
			break;
		}
		default:
			setRGBColorFill ( color.getRed (), color.getGreen (), color.getBlue () );
		}
	}

	public void setColorFill ( final PdfSpotColor sp, final float tint ) {
		checkWriter ();
		state.colorDetails = writer.addSimple ( sp );
		PageResources prs = getPageResources ();
		PdfName name = state.colorDetails.getColorName ();
		name = prs.addColor ( name, state.colorDetails.getIndirectReference () );
		saveColor ( new SpotColor ( sp, tint ), true );
		content.append ( name.getBytes () ).append ( " cs " ).append ( tint ).append ( " scn" ).append_i ( separator );
	}

	public void setColorStroke ( final PdfSpotColor sp, final float tint ) {
		checkWriter ();
		state.colorDetails = writer.addSimple ( sp );
		PageResources prs = getPageResources ();
		PdfName name = state.colorDetails.getColorName ();
		name = prs.addColor ( name, state.colorDetails.getIndirectReference () );
		saveColor ( new SpotColor ( sp, tint ), false );
		content.append ( name.getBytes () ).append ( " CS " ).append ( tint ).append ( " SCN" ).append_i ( separator );
	}

	public void setPatternFill ( final PdfPatternPainter p ) {
		if ( p.isStencil () ) {
			setPatternFill ( p, p.getDefaultColor () );
			return;
		}
		checkWriter ();
		PageResources prs = getPageResources ();
		PdfName name = writer.addSimplePattern ( p );
		name = prs.addPattern ( name, p.getIndirectReference () );
		saveColor ( new PatternColor ( p ), true );
		content.append ( PdfName.PATTERN.getBytes () ).append ( " cs " ).append ( name.getBytes () ).append ( " scn" ).append_i ( separator );
	}

	void outputColorNumbers ( final BaseColor color, final float tint ) {
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_COLOR, color );
		int type = ExtendedColor.getType ( color );
		switch ( type ) {
		case ExtendedColor.TYPE_RGB:
			content.append ( (float) color.getRed () / 0xFF );
			content.append ( ' ' );
			content.append ( (float) color.getGreen () / 0xFF );
			content.append ( ' ' );
			content.append ( (float) color.getBlue () / 0xFF );
			break;
		case ExtendedColor.TYPE_GRAY:
			content.append ( ( (GrayColor) color ).getGray () );
			break;
		case ExtendedColor.TYPE_CMYK: {
			CMYKColor cmyk = (CMYKColor) color;
			content.append ( cmyk.getCyan () ).append ( ' ' ).append ( cmyk.getMagenta () );
			content.append ( ' ' ).append ( cmyk.getYellow () ).append ( ' ' ).append ( cmyk.getBlack () );
			break;
		}
		case ExtendedColor.TYPE_SEPARATION:
			content.append ( tint );
			break;
		default:
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "invalid.color.type" ) );
		}
	}

	public void setPatternFill ( final PdfPatternPainter p, final BaseColor color ) {
		if ( ExtendedColor.getType ( color ) == ExtendedColor.TYPE_SEPARATION )
			setPatternFill ( p, color, ( (SpotColor) color ).getTint () );
		else
			setPatternFill ( p, color, 0 );
	}

	public void setPatternFill ( final PdfPatternPainter p, final BaseColor color, final float tint ) {
		checkWriter ();
		if ( !p.isStencil () )
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "an.uncolored.pattern.was.expected" ) );
		PageResources prs = getPageResources ();
		PdfName name = writer.addSimplePattern ( p );
		name = prs.addPattern ( name, p.getIndirectReference () );
		ColorDetails csDetail = writer.addSimplePatternColorspace ( color );
		PdfName cName = prs.addColor ( csDetail.getColorName (), csDetail.getIndirectReference () );
		saveColor ( new UncoloredPattern ( p, color, tint ), true );
		content.append ( cName.getBytes () ).append ( " cs" ).append_i ( separator );
		outputColorNumbers ( color, tint );
		content.append ( ' ' ).append ( name.getBytes () ).append ( " scn" ).append_i ( separator );
	}

	public void setPatternStroke ( final PdfPatternPainter p, final BaseColor color ) {
		if ( ExtendedColor.getType ( color ) == ExtendedColor.TYPE_SEPARATION )
			setPatternStroke ( p, color, ( (SpotColor) color ).getTint () );
		else
			setPatternStroke ( p, color, 0 );
	}

	public void setPatternStroke ( final PdfPatternPainter p, final BaseColor color, final float tint ) {
		checkWriter ();
		if ( !p.isStencil () )
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "an.uncolored.pattern.was.expected" ) );
		PageResources prs = getPageResources ();
		PdfName name = writer.addSimplePattern ( p );
		name = prs.addPattern ( name, p.getIndirectReference () );
		ColorDetails csDetail = writer.addSimplePatternColorspace ( color );
		PdfName cName = prs.addColor ( csDetail.getColorName (), csDetail.getIndirectReference () );
		saveColor ( new UncoloredPattern ( p, color, tint ), false );
		content.append ( cName.getBytes () ).append ( " CS" ).append_i ( separator );
		outputColorNumbers ( color, tint );
		content.append ( ' ' ).append ( name.getBytes () ).append ( " SCN" ).append_i ( separator );
	}

	public void setPatternStroke ( final PdfPatternPainter p ) {
		if ( p.isStencil () ) {
			setPatternStroke ( p, p.getDefaultColor () );
			return;
		}
		checkWriter ();
		PageResources prs = getPageResources ();
		PdfName name = writer.addSimplePattern ( p );
		name = prs.addPattern ( name, p.getIndirectReference () );
		saveColor ( new PatternColor ( p ), false );
		content.append ( PdfName.PATTERN.getBytes () ).append ( " CS " ).append ( name.getBytes () ).append ( " SCN" ).append_i ( separator );
	}

	public void setShadingFill ( final PdfShadingPattern shading ) {
		writer.addSimpleShadingPattern ( shading );
		PageResources prs = getPageResources ();
		PdfName name = prs.addPattern ( shading.getPatternName (), shading.getPatternReference () );
		saveColor ( new ShadingColor ( shading ), true );
		content.append ( PdfName.PATTERN.getBytes () ).append ( " cs " ).append ( name.getBytes () ).append ( " scn" ).append_i ( separator );
		ColorDetails details = shading.getColorDetails ();
		if ( details != null )
			prs.addColor ( details.getColorName (), details.getIndirectReference () );
	}

	public void setShadingStroke ( final PdfShadingPattern shading ) {
		writer.addSimpleShadingPattern ( shading );
		PageResources prs = getPageResources ();
		PdfName name = prs.addPattern ( shading.getPatternName (), shading.getPatternReference () );
		saveColor ( new ShadingColor ( shading ), false );
		content.append ( PdfName.PATTERN.getBytes () ).append ( " CS " ).append ( name.getBytes () ).append ( " SCN" ).append_i ( separator );
		ColorDetails details = shading.getColorDetails ();
		if ( details != null )
			prs.addColor ( details.getColorName (), details.getIndirectReference () );
	}

	protected void checkWriter () {
		if ( writer == null )
			throw new NullPointerException ( MessageLocalization.getComposedMessage ( "the.writer.in.pdfcontentbyte.is.null" ) );
	}

	public void showText ( final PdfTextArray text ) {
		checkState ();
		if ( !inText && isTagged () ) {
			beginText ( true );
		}
		if ( state.fontDetails == null )
			throw new NullPointerException ( MessageLocalization.getComposedMessage ( "font.and.size.must.be.set.before.writing.any.text" ) );
		content.append ( "[" );
		ArrayList<Object> arrayList = text.getArrayList ();
		boolean lastWasNumber = false;
		for ( Object obj : arrayList ) {
			if ( obj instanceof String ) {
				showText2 ( (String) obj );
				updateTx ( (String) obj, 0 );
				lastWasNumber = false;
			} else {
				if ( lastWasNumber )
					content.append ( ' ' );
				else
					lastWasNumber = true;
				content.append ( (Float) obj );
				updateTx ( "", (Float) obj );
			}
		}
		content.append ( "]TJ" ).append_i ( separator );
	}

	public PdfWriter getPdfWriter () {
		return writer;
	}

	public PdfDocument getPdfDocument () {
		return pdf;
	}

	public PdfContentByte getDuplicate () {
		PdfContentByte cb = new PdfContentByte ( writer );
		cb.duplicatedFrom = this;
		return cb;
	}

	public PdfContentByte getDuplicate ( boolean inheritGraphicState ) {
		PdfContentByte cb = this.getDuplicate ();
		if ( inheritGraphicState ) {
			cb.state = state;
			cb.stateList = stateList;
		}
		return cb;
	}

	public void roundRectangle ( float x, float y, float w, float h, float r ) {
		if ( w < 0 ) {
			x += w;
			w = -w;
		}
		if ( h < 0 ) {
			y += h;
			h = -h;
		}
		if ( r < 0 )
			r = -r;
		float b = 0.4477f;
		moveTo ( x + r, y );
		lineTo ( x + w - r, y );
		curveTo ( x + w - r * b, y, x + w, y + r * b, x + w, y + r );
		lineTo ( x + w, y + h - r );
		curveTo ( x + w, y + h - r * b, x + w - r * b, y + h, x + w - r, y + h );
		lineTo ( x + r, y + h );
		curveTo ( x + r * b, y + h, x, y + h - r * b, x, y + h - r );
		lineTo ( x, y + r );
		curveTo ( x, y + r * b, x + r * b, y, x + r, y );
	}

	public void setAction ( final PdfAction action, final float llx, final float lly, final float urx, final float ury ) {
		pdf.setAction ( action, llx, lly, urx, ury );
	}

	public void setLiteral ( final String s ) {
		content.append ( s );
	}

	public void setLiteral ( final char c ) {
		content.append ( c );
	}

	public void setLiteral ( final float n ) {
		content.append ( n );
	}

	void checkNoPattern ( final PdfTemplate t ) {
		if ( t.getType () == PdfTemplate.TYPE_PATTERN )
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "invalid.use.of.a.pattern.a.template.was.expected" ) );
	}

	PageResources getPageResources () {
		return pdf.getPageResources ();
	}

	public void setGState ( final PdfGState gstate ) {
		PdfObject[] obj = writer.addSimpleExtGState ( gstate );
		PageResources prs = getPageResources ();
		PdfName name = prs.addExtGState ( (PdfName) obj[0], (PdfIndirectReference) obj[1] );
		state.extGState = gstate;
		content.append ( name.getBytes () ).append ( " gs" ).append_i ( separator );
	}

	public void beginLayer ( final PdfOCG layer ) {
		if ( layer instanceof PdfLayer && ( (PdfLayer) layer ).getTitle () != null )
			throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "a.title.is.not.a.layer" ) );
		if ( layerDepth == null )
			layerDepth = new ArrayList<> ();
		if ( layer instanceof PdfLayerMembership ) {
			layerDepth.add ( 1 );
			beginLayer2 ( layer );
			return;
		}
		int n = 0;
		assert layer instanceof PdfLayer;
		PdfLayer la = (PdfLayer) layer;
		while ( la != null ) {
			if ( la.getTitle () == null ) {
				beginLayer2 ( la );
				++n;
			}
			la = la.getParent ();
		}
		layerDepth.add ( n );
	}

	private void beginLayer2 ( final PdfOCG layer ) {
		PdfName name = (PdfName) writer.addSimpleProperty ( layer, layer.getRef () )[0];
		PageResources prs = getPageResources ();
		name = prs.addProperty ( name, layer.getRef () );
		content.append ( "/OC " ).append ( name.getBytes () ).append ( " BDC" ).append_i ( separator );
	}

	public void endLayer () {
		int n;
		if ( layerDepth != null && !layerDepth.isEmpty () ) {
			n = layerDepth.get ( layerDepth.size () - 1 );
			layerDepth.remove ( layerDepth.size () - 1 );
		} else {
			throw new IllegalPdfSyntaxException ( MessageLocalization.getComposedMessage ( "unbalanced.layer.operators" ) );
		}
		while ( n-- > 0 )
			content.append ( "EMC" ).append_i ( separator );
	}

	public void transform ( final AffineTransform af ) {
		if ( inText && isTagged () ) {
			endText ();
		}
		double[] matrix = new double[6];
		af.getMatrix ( matrix );
		state.CTM.concatenate ( af );
		content.append ( matrix[0] ).append ( ' ' ).append ( matrix[1] ).append ( ' ' ).append ( matrix[2] ).append ( ' ' );
		content.append ( matrix[3] ).append ( ' ' ).append ( matrix[4] ).append ( ' ' ).append ( matrix[5] ).append ( " cm" ).append_i ( separator );
	}

	void addAnnotation ( final PdfAnnotation annot ) {
		writer.addAnnotation ( annot );
	}

	void addAnnotation ( final PdfAnnotation annot, boolean applyCTM ) {
		if ( applyCTM && state.CTM.getType () != AffineTransform.TYPE_IDENTITY ) {
			annot.applyCTM ( state.CTM );
		}
		addAnnotation ( annot );
	}

	public void beginMarkedContentSequence ( final PdfStructureElement struc ) {
		PdfObject obj = struc.get ( PdfName.K );
		int[] structParentMarkPoint = pdf.getStructParentIndexAndNextMarkPoint ( getCurrentPage () );
		int structParent = structParentMarkPoint[0];
		int mark = structParentMarkPoint[1];
		if ( obj != null ) {
			PdfArray ar;
			if ( obj.isNumber () ) {
				ar = new PdfArray ();
				ar.add ( obj );
				struc.put ( PdfName.K, ar );
			} else if ( obj.isArray () ) {
				ar = (PdfArray) obj;
			} else
				throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "unknown.object.at.k.1", obj.getClass ().toString () ) );
			if ( ar.getAsNumber ( 0 ) != null ) {
				PdfDictionary dic = new PdfDictionary ( PdfName.MCR );
				dic.put ( PdfName.PG, getCurrentPage () );
				dic.put ( PdfName.MCID, new PdfNumber ( mark ) );
				ar.add ( dic );
			}
			struc.setPageMark ( pdf.getStructParentIndex ( getCurrentPage () ), -1 );
		} else {
			struc.setPageMark ( structParent, mark );
			struc.put ( PdfName.PG, getCurrentPage () );
		}
		setMcDepth ( getMcDepth () + 1 );
		int contentSize = content.size ();
		content.append ( struc.get ( PdfName.S ).getBytes () ).append ( " <</MCID " ).append ( mark ).append ( ">> BDC" ).append_i ( separator );
		markedContentSize += content.size () - contentSize;
	}

	protected PdfIndirectReference getCurrentPage () {
		return writer.getCurrentPage ();
	}

	public void endMarkedContentSequence () {
		if ( getMcDepth () == 0 ) {
			throw new IllegalPdfSyntaxException ( MessageLocalization.getComposedMessage ( "unbalanced.begin.end.marked.content.operators" ) );
		}
		int contentSize = content.size ();
		setMcDepth ( getMcDepth () - 1 );
		content.append ( "EMC" ).append_i ( separator );
		markedContentSize += content.size () - contentSize;
	}

	public void beginMarkedContentSequence ( final PdfName tag, final PdfDictionary property, final boolean inline ) {
		int contentSize = content.size ();
		if ( property == null ) {
			content.append ( tag.getBytes () ).append ( " BMC" ).append_i ( separator );
			setMcDepth ( getMcDepth () + 1 );
		} else {
			content.append ( tag.getBytes () ).append ( ' ' );
			if ( inline )
				try {
					property.toPdf ( writer, content );
				} catch ( Exception e ) {
					throw new ExceptionConverter ( e );
				}
			else {
				PdfObject[] objs;
				if ( writer.propertyExists ( property ) )
					objs = writer.addSimpleProperty ( property, null );
				else
					objs = writer.addSimpleProperty ( property, writer.getPdfIndirectReference () );
				PdfName name = (PdfName) objs[0];
				PageResources prs = getPageResources ();
				name = prs.addProperty ( name, (PdfIndirectReference) objs[1] );
				content.append ( name.getBytes () );
			}
			content.append ( " BDC" ).append_i ( separator );
			setMcDepth ( getMcDepth () + 1 );
		}
		markedContentSize += content.size () - contentSize;
	}

	public void sanityCheck () {
		if ( getMcDepth () != 0 ) {
			throw new IllegalPdfSyntaxException ( MessageLocalization.getComposedMessage ( "unbalanced.marked.content.operators" ) );
		}
		if ( inText ) {
			if ( isTagged () ) {
				endText ();
			} else {
				throw new IllegalPdfSyntaxException ( MessageLocalization.getComposedMessage ( "unbalanced.begin.end.text.operators" ) );
			}
		}
		if ( layerDepth != null && !layerDepth.isEmpty () ) {
			throw new IllegalPdfSyntaxException ( MessageLocalization.getComposedMessage ( "unbalanced.layer.operators" ) );
		}
		if ( !stateList.isEmpty () ) {
			throw new IllegalPdfSyntaxException ( MessageLocalization.getComposedMessage ( "unbalanced.save.restore.state.operators" ) );
		}
	}

	public void openMCBlock ( IAccessibleElement element ) {
		if ( isTagged () ) {
			if ( pdf.openMCDocument ) {
				pdf.openMCDocument = false;
				writer.getDirectContentUnder ().openMCBlock ( pdf );
			}
			if ( element != null/* && element.getRole() != null*/ ) {
				if ( !getMcElements ().contains ( element ) ) {
					PdfStructureElement structureElement = openMCBlockInt ( element );
					getMcElements ().add ( element );
					if ( structureElement != null )
						pdf.structElements.put ( element.getId (), structureElement );
				}
			}
		}
	}

	private PdfDictionary getParentStructureElement () {
		PdfDictionary parent = null;
		if ( !getMcElements ().isEmpty () )
			parent = pdf.structElements.get ( getMcElements ().get ( getMcElements ().size () - 1 ).getId () );
		if ( parent == null ) {
			parent = writer.getStructureTreeRoot ();
		}
		return parent;
	}

	private PdfStructureElement openMCBlockInt ( IAccessibleElement element ) {
		PdfStructureElement structureElement = null;
		if ( isTagged () ) {
			IAccessibleElement parent = null;
			if ( !getMcElements ().isEmpty () )
				parent = getMcElements ().get ( getMcElements ().size () - 1 );
			writer.checkElementRole ( element, parent );
			if ( element.getRole () != null ) {
				if ( !PdfName.ARTIFACT.equals ( element.getRole () ) ) {
					structureElement = pdf.structElements.get ( element.getId () );
					if ( structureElement == null ) {
						structureElement = new PdfStructureElement ( getParentStructureElement (), element.getRole () );
					}
				}
				if ( PdfName.ARTIFACT.equals ( element.getRole () ) ) {
					HashMap<PdfName, PdfObject> properties = element.getAccessibleAttributes ();
					PdfDictionary propertiesDict = null;
					if ( properties == null || properties.isEmpty () ) {
					} else {
						propertiesDict = new PdfDictionary ();
						for ( Map.Entry<PdfName, PdfObject> entry : properties.entrySet () ) {
							propertiesDict.put ( entry.getKey (), entry.getValue () );
						}
					}
					boolean inTextLocal = inText;
					if ( inText )
						endText ();
					beginMarkedContentSequence ( element.getRole (), propertiesDict, true );
					if ( inTextLocal )
						beginText ( true );
				} else {
					if ( writer.needToBeMarkedInContent ( element ) ) {
						boolean inTextLocal = inText;
						if ( inText )
							endText ();
						assert structureElement != null;
						beginMarkedContentSequence ( structureElement );
						if ( inTextLocal )
							beginText ( true );
					}
				}
			}
		}
		return structureElement;
	}

	public void closeMCBlock ( IAccessibleElement element ) {
		if ( isTagged () && element != null/* && element.getRole() != null*/ ) {
			if ( getMcElements ().contains ( element ) ) {
				closeMCBlockInt ( element );
				getMcElements ().remove ( element );
			}
		}
	}

	private void closeMCBlockInt ( IAccessibleElement element ) {
		if ( isTagged () && element.getRole () != null ) {
			PdfStructureElement structureElement = pdf.structElements.get ( element.getId () );
			if ( structureElement != null ) {
				structureElement.writeAttributes ( element );
			}
			if ( writer.needToBeMarkedInContent ( element ) ) {
				boolean inTextLocal = inText;
				if ( inText )
					endText ();
				endMarkedContentSequence ();
				if ( inTextLocal )
					beginText ( true );
			}
		}
	}

	protected ArrayList<IAccessibleElement> saveMCBlocks () {
		ArrayList<IAccessibleElement> mc = new ArrayList<> ();
		if ( isTagged () ) {
			mc = getMcElements ();
			for ( IAccessibleElement iAccessibleElement : mc ) {
				closeMCBlockInt ( iAccessibleElement );
			}
			setMcElements ( new ArrayList<> () );
		}
		return mc;
	}

	protected void restoreMCBlocks ( ArrayList<IAccessibleElement> mcElements ) {
		if ( isTagged () && mcElements != null ) {
			setMcElements ( mcElements );
			for ( int i = 0; i < this.getMcElements ().size (); i++ ) {
				openMCBlockInt ( this.getMcElements ().get ( i ) );
			}
		}
	}

	protected int getMcDepth () {
		if ( duplicatedFrom != null )
			return duplicatedFrom.getMcDepth ();
		else
			return mcDepth;
	}

	protected void setMcDepth ( int value ) {
		if ( duplicatedFrom != null )
			duplicatedFrom.setMcDepth ( value );
		else
			mcDepth = value;
	}

	protected ArrayList<IAccessibleElement> getMcElements () {
		if ( duplicatedFrom != null )
			return duplicatedFrom.getMcElements ();
		else
			return mcElements;
	}

	protected void setMcElements ( ArrayList<IAccessibleElement> value ) {
		if ( duplicatedFrom != null )
			duplicatedFrom.setMcElements ( value );
		else
			mcElements = value;
	}

	protected void updateTx ( String text, float Tj ) {
		state.tx += getEffectiveStringWidth ( text, Tj );
	}

	private void saveColor ( BaseColor color, boolean fill ) {
		if ( isTagged () ) {
			if ( inText ) {
				if ( fill ) {
					state.textColorFill = color;
				} else {
					state.textColorStroke = color;
				}
			} else {
				if ( fill ) {
					state.colorFill = color;
				} else {
					state.colorStroke = color;
				}
			}
		} else {
			if ( fill ) {
				state.colorFill = color;
			} else {
				state.colorStroke = color;
			}
		}
	}

	private void restoreColor ( BaseColor color, boolean fill ) {
		if ( isTagged () ) {
			if ( color instanceof UncoloredPattern ) {
				UncoloredPattern c = (UncoloredPattern) color;
				if ( fill )
					setPatternFill ( c.getPainter (), c.color, c.tint );
				else
					setPatternStroke ( c.getPainter (), c.color, c.tint );
			} else {
				if ( fill )
					setColorFill ( color );
				else
					setColorStroke ( color );
			}
		}
	}

	private void restoreColor () throws IOException {
		if ( isTagged () ) {
			if ( inText ) {
				if ( !state.textColorFill.equals ( state.colorFill ) ) {
					restoreColor ( state.textColorFill, true );
				}
				if ( !state.textColorStroke.equals ( state.colorStroke ) ) {
					restoreColor ( state.textColorStroke, false );
				}
			} else {
				if ( !state.textColorFill.equals ( state.colorFill ) ) {
					restoreColor ( state.colorFill, true );
				}
				if ( !state.textColorStroke.equals ( state.colorStroke ) ) {
					restoreColor ( state.colorStroke, false );
				}
			}
		}
	}

	protected boolean getInText () {
		return inText;
	}

	protected void checkState () {
		boolean stroke = false;
		boolean fill = false;
		if ( state.textRenderMode == TEXT_RENDER_MODE_FILL ) {
			fill = true;
		} else if ( state.textRenderMode == TEXT_RENDER_MODE_STROKE ) {
			stroke = true;
		} else if ( state.textRenderMode == TEXT_RENDER_MODE_FILL_STROKE ) {
			fill = true;
			stroke = true;
		}
		if ( fill ) {
			PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_COLOR, isTagged () ? state.textColorFill : state.colorFill );
		}
		if ( stroke ) {
			PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_COLOR, isTagged () ? state.textColorStroke : state.colorStroke );
		}
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_GSTATE, state.extGState );
	}

	public void transform ( final java.awt.geom.AffineTransform af ) {
		double[] matrix = new double[6];
		af.getMatrix ( matrix );
		transform ( new AffineTransform ( matrix ) );
	}

	static public class GraphicState {

		protected float xTLM = 0;

		protected float yTLM = 0;

		protected float aTLM = 1;

		protected float bTLM = 0;

		protected float cTLM = 0;

		protected float dTLM = 1;

		protected float tx = 0;

		protected float leading = 0;

		protected float scale = 100;

		protected float charSpace = 0;

		protected float wordSpace = 0;

		protected BaseColor textColorFill = new GrayColor ( 0 );

		protected BaseColor colorFill = new GrayColor ( 0 );

		protected BaseColor textColorStroke = new GrayColor ( 0 );

		protected BaseColor colorStroke = new GrayColor ( 0 );

		protected int textRenderMode = TEXT_RENDER_MODE_FILL;

		protected AffineTransform CTM = new AffineTransform ();

		protected PdfObject extGState = null;

		FontDetails fontDetails;

		ColorDetails colorDetails;

		float size;

		GraphicState () {
		}

		GraphicState ( final GraphicState cp ) {
			copyParameters ( cp );
		}

		void copyParameters ( final GraphicState cp ) {
			fontDetails = cp.fontDetails;
			colorDetails = cp.colorDetails;
			size = cp.size;
			xTLM = cp.xTLM;
			yTLM = cp.yTLM;
			aTLM = cp.aTLM;
			bTLM = cp.bTLM;
			cTLM = cp.cTLM;
			dTLM = cp.dTLM;
			tx = cp.tx;
			leading = cp.leading;
			scale = cp.scale;
			charSpace = cp.charSpace;
			wordSpace = cp.wordSpace;
			textColorFill = cp.textColorFill;
			colorFill = cp.colorFill;
			textColorStroke = cp.textColorStroke;
			colorStroke = cp.colorStroke;
			CTM = new AffineTransform ( cp.CTM );
			textRenderMode = cp.textRenderMode;
			extGState = cp.extGState;
		}

		void restore ( final GraphicState restore ) {
			copyParameters ( restore );
		}
	}


	static class UncoloredPattern extends PatternColor {

		private static final long serialVersionUID = -9162932565421829494L;

		protected BaseColor color;

		protected float tint;

		protected UncoloredPattern ( final PdfPatternPainter p, final BaseColor color, final float tint ) {
			super ( p );
			this.color = color;
			this.tint = tint;
		}

		@Override
		public boolean equals ( Object obj ) {
			return obj instanceof UncoloredPattern && ( ( (UncoloredPattern) obj ).painter ).equals (
							this.painter ) && ( ( (UncoloredPattern) obj ).color ).equals ( this.color ) && ( (UncoloredPattern) obj ).tint == this.tint;
		}

	}
}

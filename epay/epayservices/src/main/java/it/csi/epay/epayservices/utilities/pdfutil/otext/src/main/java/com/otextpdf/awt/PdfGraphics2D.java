/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt.geom.PolylineShape;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.BaseFont;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.ByteBuffer;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfAction;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfContentByte;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfGState;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfPatternPainter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfShading;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfShadingPattern;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.awt.image.renderable.RenderableImage;
import java.io.ByteArrayOutputStream;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;


public class PdfGraphics2D extends Graphics2D {

	public static final int AFM_DIVISOR = 1000;

	private static final int FILL = 1;

	private static final int STROKE = 2;

	private static final int CLIP = 3;

	private static final AffineTransform IDENTITY = new AffineTransform ();

	protected Font font;

	protected BaseFont baseFont;

	protected float fontSize;

	protected AffineTransform transform;

	protected Paint paint;

	protected Color background;

	protected float width;

	protected float height;

	protected Area clip;

	protected RenderingHints rhints = new RenderingHints ( null );

	protected Stroke stroke;

	protected Stroke originalStroke;

	protected PdfContentByte cb;

	protected HashMap<String, BaseFont> baseFonts;

	protected boolean disposeCalled = false;

	protected FontMapper fontMapper;

	protected boolean underline;

	protected boolean strikethrough;

	protected PdfGState[] fillGState;

	protected PdfGState[] strokeGState;

	protected int currentFillGState = 255;

	protected int currentStrokeGState = 255;

	private BasicStroke strokeOne = new BasicStroke ( 1 );

	private ArrayList<Kid> kids;

	private boolean kid = false;

	private Graphics2D dg2;

	private boolean onlyShapes = false;

	private Stroke oldStroke;

	private Paint paintFill;

	private Paint paintStroke;

	private MediaTracker mediaTracker;

	private boolean convertImagesToJPEG = false;

	private float jpegQuality = .95f;

	// Added by Alexej Suchov
	private float alpha;

	// Added by Alexej Suchov
	private Composite composite;

	private Paint realPaint;

	private PdfGraphics2D () {
	}

	public PdfGraphics2D ( PdfContentByte cb, float width, float height, FontMapper fontMapper, boolean onlyShapes, boolean convertImagesToJPEG,
					float quality ) {
		super ();
		this.fillGState = new PdfGState[256];
		this.strokeGState = new PdfGState[256];
		this.convertImagesToJPEG = convertImagesToJPEG;
		this.jpegQuality = quality;
		this.onlyShapes = onlyShapes;
		this.transform = new AffineTransform ();
		this.baseFonts = new HashMap<> ();
		if ( !onlyShapes ) {
			this.fontMapper = fontMapper;
			if ( this.fontMapper == null )
				this.fontMapper = new DefaultFontMapper ();
		}
		paint = Color.black;
		background = Color.white;
		setFont ( new Font ( "sanserif", Font.PLAIN, 12 ) );
		this.cb = cb;
		cb.saveState ();
		this.width = width;
		this.height = height;
		clip = new Area ( new Rectangle2D.Float ( 0, 0, width, height ) );
		clip ( clip );
		originalStroke = stroke = oldStroke = strokeOne;
		setStrokeDiff ( stroke, null );
		cb.saveState ();
	}

	public static double asPoints ( double d, int i ) {
		return d * i / AFM_DIVISOR;
	}

	private Graphics2D getDG2 () {
		if ( dg2 == null ) {
			dg2 = new BufferedImage ( 2, 2, BufferedImage.TYPE_INT_RGB ).createGraphics ();
			dg2.setRenderingHint ( RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON );
			setRenderingHint ( RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON );
			setRenderingHint ( HyperLinkKey.KEY_INSTANCE, HyperLinkKey.VALUE_HYPERLINKKEY_OFF );
		}
		return dg2;
	}

	@Override
	public void draw ( Shape s ) {
		followPath ( s, STROKE );
	}

	@Override
	public boolean drawImage ( Image img, AffineTransform xform, ImageObserver obs ) {
		return drawImage ( img, null, xform, null, obs );
	}

	@Override
	public void drawImage ( BufferedImage img, BufferedImageOp op, int x, int y ) {
		BufferedImage result = img;
		if ( op != null ) {
			result = op.createCompatibleDestImage ( img, img.getColorModel () );
			result = op.filter ( img, result );
		}
		drawImage ( result, x, y, null );
	}

	@Override
	public void drawRenderedImage ( RenderedImage img, AffineTransform xform ) {
		BufferedImage image;
		if ( img instanceof BufferedImage ) {
			image = (BufferedImage) img;
		} else {
			ColorModel cm = img.getColorModel ();
			int width = img.getWidth ();
			int height = img.getHeight ();
			WritableRaster raster = cm.createCompatibleWritableRaster ( width, height );
			boolean isAlphaPremultiplied = cm.isAlphaPremultiplied ();
			Hashtable<String, Object> properties = new Hashtable<> ();
			String[] keys = img.getPropertyNames ();
			if ( keys != null ) {
				for ( String key : keys ) {
					properties.put ( key, img.getProperty ( key ) );
				}
			}
			BufferedImage result = new BufferedImage ( cm, raster, isAlphaPremultiplied, properties );
			img.copyData ( raster );
			image = result;
		}
		drawImage ( image, xform, null );
	}

	@Override
	public void drawRenderableImage ( RenderableImage img, AffineTransform xform ) {
		drawRenderedImage ( img.createDefaultRendering (), xform );
	}

	@Override
	public void drawString ( String s, int x, int y ) {
		drawString ( s, (float) x, (float) y );
	}

	@SuppressWarnings ( "unchecked" )
	protected void doAttributes ( AttributedCharacterIterator iter ) {
		underline = false;
		strikethrough = false;
		for ( AttributedCharacterIterator.Attribute attribute : iter.getAttributes ().keySet () ) {
			if ( !( attribute instanceof TextAttribute ) )
				continue;
			TextAttribute textattribute = (TextAttribute) attribute;
			if ( textattribute.equals ( TextAttribute.FONT ) ) {
				Font font = (Font) iter.getAttributes ().get ( textattribute );
				setFont ( font );
			} else if ( textattribute.equals ( TextAttribute.UNDERLINE ) ) {
				if ( iter.getAttributes ().get ( textattribute ) == TextAttribute.UNDERLINE_ON )
					underline = true;
			} else if ( textattribute.equals ( TextAttribute.STRIKETHROUGH ) ) {
				if ( iter.getAttributes ().get ( textattribute ) == TextAttribute.STRIKETHROUGH_ON )
					strikethrough = true;
			} else if ( textattribute.equals ( TextAttribute.SIZE ) ) {
				Object obj = iter.getAttributes ().get ( textattribute );
				if ( obj instanceof Integer ) {
					int i = (Integer) obj;
					setFont ( getFont ().deriveFont ( getFont ().getStyle (), i ) );
				} else if ( obj instanceof Float ) {
					float f = (Float) obj;
					setFont ( getFont ().deriveFont ( getFont ().getStyle (), f ) );
				}
			} else if ( textattribute.equals ( TextAttribute.FOREGROUND ) ) {
				setColor ( (Color) iter.getAttributes ().get ( textattribute ) );
			} else if ( textattribute.equals ( TextAttribute.FAMILY ) ) {
				Font font = getFont ();
				Map fontAttributes = font.getAttributes ();
				fontAttributes.put ( TextAttribute.FAMILY, iter.getAttributes ().get ( textattribute ) );
				setFont ( font.deriveFont ( fontAttributes ) );
			} else if ( textattribute.equals ( TextAttribute.POSTURE ) ) {
				Font font = getFont ();
				Map fontAttributes = font.getAttributes ();
				fontAttributes.put ( TextAttribute.POSTURE, iter.getAttributes ().get ( textattribute ) );
				setFont ( font.deriveFont ( fontAttributes ) );
			} else if ( textattribute.equals ( TextAttribute.WEIGHT ) ) {
				Font font = getFont ();
				Map fontAttributes = font.getAttributes ();
				fontAttributes.put ( TextAttribute.WEIGHT, iter.getAttributes ().get ( textattribute ) );
				setFont ( font.deriveFont ( fontAttributes ) );
			}
		}
	}

	@Override
	public void drawString ( String s, float x, float y ) {
		if ( s.isEmpty () )
			return;
		setFillPaint ();
		if ( onlyShapes ) {
			drawGlyphVector ( this.font.layoutGlyphVector ( getFontRenderContext (), s.toCharArray (), 0, s.length (), Font.LAYOUT_LEFT_TO_RIGHT ), x, y );
		} else {
			boolean restoreTextRenderingMode = false;
			AffineTransform at = getTransform ();
			AffineTransform at2 = getTransform ();
			at2.translate ( x, y );
			at2.concatenate ( font.getTransform () );
			setTransform ( at2 );
			AffineTransform inverse = this.normalizeMatrix ();
			AffineTransform flipper = AffineTransform.getScaleInstance ( 1, -1 );
			inverse.concatenate ( flipper );
			double[] mx = new double[6];
			inverse.getMatrix ( mx );
			cb.beginText ();
			cb.setFontAndSize ( baseFont, fontSize );
			if ( font.isItalic () && font.getFontName ().equals ( font.getName () ) ) {
				float angle = baseFont.getFontDescriptor ( BaseFont.ITALICANGLE, 1000 );
				float angle2 = font.getItalicAngle ();
				if ( angle2 == 0 ) {
					angle2 = 15.0f;
				} else {
					angle2 = -angle2;
				}
				if ( angle == 0 ) {
					mx[2] = angle2 / 100.0f;
				}
			}
			cb.setTextMatrix ( (float) mx[0], (float) mx[1], (float) mx[2], (float) mx[3], (float) mx[4], (float) mx[5] );
			Float fontTextAttributeWidth = (Float) font.getAttributes ().get ( TextAttribute.WIDTH );
			fontTextAttributeWidth = fontTextAttributeWidth == null
							? TextAttribute.WIDTH_REGULAR
							: fontTextAttributeWidth;
			if ( !TextAttribute.WIDTH_REGULAR.equals ( fontTextAttributeWidth ) )
				cb.setHorizontalScaling ( 100.0f / fontTextAttributeWidth );

			if ( !baseFont.getPostscriptFontName ().toLowerCase ().contains ( "bold" ) ) {
				Float weight = (Float) font.getAttributes ().get ( TextAttribute.WEIGHT );
				if ( weight == null ) {
					weight = font.isBold () ? TextAttribute.WEIGHT_BOLD
									: TextAttribute.WEIGHT_REGULAR;
				}
				if ( ( font.isBold () || weight >= TextAttribute.WEIGHT_SEMIBOLD )
								&& font.getFontName ().equals ( font.getName () ) ) {
					float strokeWidth = font.getSize2D () * ( weight - TextAttribute.WEIGHT_REGULAR ) / 30f;
					if ( strokeWidth != 1 ) {
						if ( realPaint instanceof Color ) {
							cb.setTextRenderingMode ( PdfContentByte.TEXT_RENDER_MODE_FILL_STROKE );
							cb.setLineWidth ( strokeWidth );
							Color color = (Color) realPaint;
							int alpha = color.getAlpha ();
							if ( alpha != currentStrokeGState ) {
								currentStrokeGState = alpha;
								PdfGState gs = strokeGState[alpha];
								if ( gs == null ) {
									gs = new PdfGState ();
									gs.setStrokeOpacity ( alpha / 255f );
									strokeGState[alpha] = gs;
								}
								cb.setGState ( gs );
							}
							cb.setColorStroke ( new BaseColor ( color.getRGB () ) );
							restoreTextRenderingMode = true;
						}
					}
				}
			}

			double width = 0;
			if ( font.getSize2D () > 0 ) {
				float scale = 1000 / font.getSize2D ();
				Font derivedFont = font.deriveFont ( AffineTransform.getScaleInstance ( scale, scale ) );
				width = derivedFont.getStringBounds ( s, getFontRenderContext () ).getWidth ();
				if ( derivedFont.isTransformed () )
					width /= scale;
			}
			Object url = getRenderingHint ( HyperLinkKey.KEY_INSTANCE );
			if ( url != null && !url.equals ( HyperLinkKey.VALUE_HYPERLINKKEY_OFF ) ) {
				float scale = 1000 / font.getSize2D ();
				Font derivedFont = font.deriveFont ( AffineTransform.getScaleInstance ( scale, scale ) );
				double height = derivedFont.getStringBounds ( s, getFontRenderContext () ).getHeight ();
				if ( derivedFont.isTransformed () )
					height /= scale;
				double leftX = cb.getXTLM ();
				double leftY = cb.getYTLM ();
				PdfAction action = new PdfAction ( url.toString () );
				cb.setAction ( action, (float) leftX, (float) leftY, (float) ( leftX + width ), (float) ( leftY + height ) );
			}
			if ( s.length () > 1 ) {
				float adv = ( (float) width - baseFont.getWidthPoint ( s, fontSize ) ) / ( s.length () - 1 );
				cb.setCharacterSpacing ( adv );
			}
			cb.showText ( s );
			if ( s.length () > 1 ) {
				cb.setCharacterSpacing ( 0 );
			}
			if ( !TextAttribute.WIDTH_REGULAR.equals ( fontTextAttributeWidth ) )
				cb.setHorizontalScaling ( 100 );

			if ( restoreTextRenderingMode ) {
				cb.setTextRenderingMode ( PdfContentByte.TEXT_RENDER_MODE_FILL );
			}

			cb.endText ();
			setTransform ( at );
			if ( underline ) {
				int UnderlineThickness = 50;
				double d = asPoints ( UnderlineThickness, (int) fontSize );
				Stroke savedStroke = originalStroke;
				setStroke ( new BasicStroke ( (float) d ) );
				y = (float) ( y + asPoints ( UnderlineThickness, (int) fontSize ) );
				Line2D line = new Line2D.Double ( x, y, width + x, y );
				draw ( line );
				setStroke ( savedStroke );
			}
			if ( strikethrough ) {
				int StrikethroughThickness = 50;
				int StrikethroughPosition = 350;
				//
				double d = asPoints ( StrikethroughThickness, (int) fontSize );
				double p = asPoints ( StrikethroughPosition, (int) fontSize );
				Stroke savedStroke = originalStroke;
				setStroke ( new BasicStroke ( (float) d ) );
				y = (float) ( y + asPoints ( StrikethroughThickness, (int) fontSize ) );
				Line2D line = new Line2D.Double ( x, y - p, width + x, y - p );
				draw ( line );
				setStroke ( savedStroke );
			}
		}
	}

	@Override
	public void drawString ( AttributedCharacterIterator iterator, int x, int y ) {
		drawString ( iterator, (float) x, (float) y );
	}

	@Override
	public void drawString ( AttributedCharacterIterator iter, float x, float y ) {

		StringBuilder stringbuffer = new StringBuilder ( iter.getEndIndex () );
		for ( char c = iter.first (); c != '\uFFFF'; c = iter.next () ) {
			if ( iter.getIndex () == iter.getRunStart () ) {
				if ( stringbuffer.length () > 0 ) {
					drawString ( stringbuffer.toString (), x, y );
					FontMetrics fontmetrics = getFontMetrics ();
					x = (float) ( x + fontmetrics.getStringBounds ( stringbuffer.toString (), this ).getWidth () );
					stringbuffer.delete ( 0, stringbuffer.length () );
				}
				doAttributes ( iter );
			}
			stringbuffer.append ( c );
		}

		drawString ( stringbuffer.toString (), x, y );
		underline = false;
		strikethrough = false;
	}

	@Override
	public void drawGlyphVector ( GlyphVector g, float x, float y ) {
		Shape s = g.getOutline ( x, y );
		fill ( s );
	}

	@Override
	public void fill ( Shape s ) {
		followPath ( s, FILL );
	}

	@Override
	public boolean hit ( Rectangle rect, Shape s, boolean onStroke ) {
		if ( onStroke ) {
			s = stroke.createStrokedShape ( s );
		}
		s = transform.createTransformedShape ( s );
		Area area = new Area ( s );
		if ( clip != null )
			area.intersect ( clip );
		return area.intersects ( rect.x, rect.y, rect.width, rect.height );
	}

	@Override
	public GraphicsConfiguration getDeviceConfiguration () {
		return getDG2 ().getDeviceConfiguration ();
	}

	private Stroke transformStroke ( Stroke stroke ) {
		if ( !( stroke instanceof BasicStroke ) )
			return stroke;
		BasicStroke st = (BasicStroke) stroke;
		float scale = (float) Math.sqrt ( Math.abs ( transform.getDeterminant () ) );
		float[] dash = st.getDashArray ();
		if ( dash != null ) {
			for ( int k = 0; k < dash.length; ++k )
				dash[k] *= scale;
		}
		return new BasicStroke ( st.getLineWidth () * scale, st.getEndCap (), st.getLineJoin (), st.getMiterLimit (), dash, st.getDashPhase () * scale );
	}

	private void setStrokeDiff ( Stroke newStroke, Stroke oldStroke ) {
		if ( newStroke == oldStroke )
			return;
		if ( !( newStroke instanceof BasicStroke ) )
			return;
		BasicStroke nStroke = (BasicStroke) newStroke;
		boolean oldOk = oldStroke instanceof BasicStroke;
		BasicStroke oStroke = null;
		if ( oldOk )
			oStroke = (BasicStroke) oldStroke;
		if ( !oldOk || nStroke.getLineWidth () != oStroke.getLineWidth () )
			cb.setLineWidth ( nStroke.getLineWidth () );
		if ( !oldOk || nStroke.getEndCap () != oStroke.getEndCap () ) {
			switch ( nStroke.getEndCap () ) {
			case BasicStroke.CAP_BUTT:
				cb.setLineCap ( 0 );
				break;
			case BasicStroke.CAP_SQUARE:
				cb.setLineCap ( 2 );
				break;
			default:
				cb.setLineCap ( 1 );
			}
		}
		if ( !oldOk || nStroke.getLineJoin () != oStroke.getLineJoin () ) {
			switch ( nStroke.getLineJoin () ) {
			case BasicStroke.JOIN_MITER:
				cb.setLineJoin ( 0 );
				break;
			case BasicStroke.JOIN_BEVEL:
				cb.setLineJoin ( 2 );
				break;
			default:
				cb.setLineJoin ( 1 );
			}
		}
		if ( !oldOk || nStroke.getMiterLimit () != oStroke.getMiterLimit () )
			cb.setMiterLimit ( nStroke.getMiterLimit () );
		boolean makeDash;
		if ( oldOk ) {
			if ( nStroke.getDashArray () != null ) {
				if ( nStroke.getDashPhase () != oStroke.getDashPhase () ) {
					makeDash = true;
				} else
					makeDash = !java.util.Arrays.equals ( nStroke.getDashArray (), oStroke.getDashArray () );
			} else
				makeDash = oStroke.getDashArray () != null;
		} else {
			makeDash = true;
		}
		if ( makeDash ) {
			float[] dash = nStroke.getDashArray ();
			if ( dash == null )
				cb.setLiteral ( "[]0 d\n" );
			else {
				cb.setLiteral ( '[' );
				for ( float v : dash ) {
					cb.setLiteral ( v );
					cb.setLiteral ( ' ' );
				}
				cb.setLiteral ( ']' );
				cb.setLiteral ( nStroke.getDashPhase () );
				cb.setLiteral ( " d\n" );
			}
		}
	}

	@Override
	public void setRenderingHint ( Key arg0, Object arg1 ) {
		if ( arg1 != null ) {
			rhints.put ( arg0, arg1 );
		} else {
			if ( arg0 instanceof HyperLinkKey ) {
				rhints.put ( arg0, HyperLinkKey.VALUE_HYPERLINKKEY_OFF );
			} else {
				rhints.remove ( arg0 );
			}
		}
	}

	@Override
	public Object getRenderingHint ( Key arg0 ) {
		return rhints.get ( arg0 );
	}

	@Override
	public void addRenderingHints ( Map<?, ?> hints ) {
		rhints.putAll ( hints );
	}

	@Override
	public RenderingHints getRenderingHints () {
		return rhints;
	}

	@Override
	public void setRenderingHints ( Map<?, ?> hints ) {
		rhints.clear ();
		rhints.putAll ( hints );
	}

	@Override
	public void translate ( int x, int y ) {
		translate ( x, (double) y );
	}

	@Override
	public void translate ( double tx, double ty ) {
		transform.translate ( tx, ty );
	}

	@Override
	public void rotate ( double theta ) {
		transform.rotate ( theta );
	}

	@Override
	public void rotate ( double theta, double x, double y ) {
		transform.rotate ( theta, x, y );
	}

	@Override
	public void scale ( double sx, double sy ) {
		transform.scale ( sx, sy );
		this.stroke = transformStroke ( originalStroke );
	}

	@Override
	public void shear ( double shx, double shy ) {
		transform.shear ( shx, shy );
	}

	@Override
	public void transform ( AffineTransform tx ) {
		transform.concatenate ( tx );
		this.stroke = transformStroke ( originalStroke );
	}

	@Override
	public AffineTransform getTransform () {
		return new AffineTransform ( transform );
	}

	@Override
	public void setTransform ( AffineTransform t ) {
		transform = new AffineTransform ( t );
		this.stroke = transformStroke ( originalStroke );
	}

	@Override
	public Paint getPaint () {
		if ( realPaint != null ) {
			return realPaint;
		} else {
			return paint;
		}
	}

	@Override
	public void setPaint ( Paint paint ) {
		if ( paint == null )
			return;
		this.paint = paint;
		realPaint = paint;

		if ( composite instanceof AlphaComposite && paint instanceof Color ) {

			AlphaComposite co = (AlphaComposite) composite;

			if ( co.getRule () == 3 ) {
				Color c = (Color) paint;
				this.paint = new Color ( c.getRed (), c.getGreen (), c.getBlue (), (int) ( c.getAlpha () * alpha ) );
				realPaint = paint;
			}
		}

	}

	private void setPaint ( boolean fill ) {
		if ( paint instanceof Color ) {
			Color color = (Color) paint;
			int alpha = color.getAlpha ();
			if ( fill ) {
				if ( alpha != currentFillGState ) {
					currentFillGState = alpha;
					PdfGState gs = fillGState[alpha];
					if ( gs == null ) {
						gs = new PdfGState ();
						gs.setFillOpacity ( alpha / 255f );
						fillGState[alpha] = gs;
					}
					cb.setGState ( gs );
				}
				cb.setColorFill ( new BaseColor ( color.getRGB () ) );
			} else {
				if ( alpha != currentStrokeGState ) {
					currentStrokeGState = alpha;
					PdfGState gs = strokeGState[alpha];
					if ( gs == null ) {
						gs = new PdfGState ();
						gs.setStrokeOpacity ( alpha / 255f );
						strokeGState[alpha] = gs;
					}
					cb.setGState ( gs );
				}
				cb.setColorStroke ( new BaseColor ( color.getRGB () ) );
			}
		} else if ( paint instanceof GradientPaint ) {
			GradientPaint gp = (GradientPaint) paint;
			Point2D p1 = gp.getPoint1 ();
			transform.transform ( p1, p1 );
			Point2D p2 = gp.getPoint2 ();
			transform.transform ( p2, p2 );
			Color c1 = gp.getColor1 ();
			Color c2 = gp.getColor2 ();
			PdfShading shading = PdfShading.simpleAxial ( cb.getPdfWriter (), (float) p1.getX (), normalizeY ( (float) p1.getY () ), (float) p2.getX (),
							normalizeY ( (float) p2.getY () ), new BaseColor ( c1.getRGB () ), new BaseColor ( c2.getRGB () ) );
			PdfShadingPattern pat = new PdfShadingPattern ( shading );
			if ( fill )
				cb.setShadingFill ( pat );
			else
				cb.setShadingStroke ( pat );
		} else if ( paint instanceof TexturePaint ) {
			try {
				TexturePaint tp = (TexturePaint) paint;
				BufferedImage img = tp.getImage ();
				Rectangle2D rect = tp.getAnchorRect ();
				it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image image =
								it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image.getInstance ( img, null );
				PdfPatternPainter pattern = cb.createPattern ( image.getWidth (), image.getHeight () );
				AffineTransform inverse = this.normalizeMatrix ();
				inverse.translate ( rect.getX (), rect.getY () );
				inverse.scale ( rect.getWidth () / image.getWidth (), -rect.getHeight () / image.getHeight () );
				double[] mx = new double[6];
				inverse.getMatrix ( mx );
				pattern.setPatternMatrix ( (float) mx[0], (float) mx[1], (float) mx[2], (float) mx[3], (float) mx[4], (float) mx[5] );
				image.setAbsolutePosition ( 0, 0 );
				pattern.addImage ( image );
				if ( fill )
					cb.setPatternFill ( pattern );
				else
					cb.setPatternStroke ( pattern );
			} catch ( Exception ex ) {
				if ( fill )
					cb.setColorFill ( BaseColor.GRAY );
				else
					cb.setColorStroke ( BaseColor.GRAY );
			}
		} else {
			try {
				BufferedImage img;
				int type = BufferedImage.TYPE_4BYTE_ABGR;
				if ( paint.getTransparency () == Transparency.OPAQUE ) {
					type = BufferedImage.TYPE_3BYTE_BGR;
				}
				img = new BufferedImage ( (int) width, (int) height, type );
				Graphics2D g = (Graphics2D) img.getGraphics ();
				g.transform ( transform );
				AffineTransform inv = transform.createInverse ();
				Shape fillRect = new Rectangle2D.Double ( 0, 0, img.getWidth (), img.getHeight () );
				fillRect = inv.createTransformedShape ( fillRect );
				g.setPaint ( paint );
				g.fill ( fillRect );
				g.dispose ();
				it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image image =
								it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image.getInstance ( img, null );
				PdfPatternPainter pattern = cb.createPattern ( width, height );
				image.setAbsolutePosition ( 0, 0 );
				pattern.addImage ( image );
				if ( fill )
					cb.setPatternFill ( pattern );
				else
					cb.setPatternStroke ( pattern );
			} catch ( Exception ex ) {
				if ( fill )
					cb.setColorFill ( BaseColor.GRAY );
				else
					cb.setColorStroke ( BaseColor.GRAY );
			}
		}
	}

	@Override
	public Composite getComposite () {
		return composite;
	}

	@Override
	public void setComposite ( Composite comp ) {

		if ( comp instanceof AlphaComposite ) {

			AlphaComposite composite = (AlphaComposite) comp;

			if ( composite.getRule () == 3 ) {

				alpha = composite.getAlpha ();
				this.composite = composite;

				if ( realPaint != null && realPaint instanceof Color ) {

					Color c = (Color) realPaint;
					paint = new Color ( c.getRed (), c.getGreen (), c.getBlue (),
									(int) ( c.getAlpha () * alpha ) );
				}
				return;
			}
		}

		this.composite = comp;
		alpha = 1.0F;

	}

	@Override
	public Color getBackground () {
		return background;
	}

	@Override
	public void setBackground ( Color color ) {
		background = color;
	}

	@Override
	public Stroke getStroke () {
		return originalStroke;
	}

	@Override
	public void setStroke ( Stroke s ) {
		originalStroke = s;
		this.stroke = transformStroke ( s );
	}

	@Override
	public FontRenderContext getFontRenderContext () {
		boolean antialias = RenderingHints.VALUE_TEXT_ANTIALIAS_ON.equals ( getRenderingHint ( RenderingHints.KEY_TEXT_ANTIALIASING ) );
		boolean fractions = RenderingHints.VALUE_FRACTIONALMETRICS_ON.equals ( getRenderingHint ( RenderingHints.KEY_FRACTIONALMETRICS ) );
		return new FontRenderContext ( new AffineTransform (), antialias, fractions );
	}

	@Override
	public Graphics create () {
		PdfGraphics2D g2 = new PdfGraphics2D ();
		g2.rhints.putAll ( this.rhints );
		g2.onlyShapes = this.onlyShapes;
		g2.transform = new AffineTransform ( this.transform );
		g2.baseFonts = this.baseFonts;
		g2.fontMapper = this.fontMapper;
		g2.paint = this.paint;
		g2.fillGState = this.fillGState;
		g2.currentFillGState = this.currentFillGState;
		g2.strokeGState = this.strokeGState;
		g2.background = this.background;
		g2.mediaTracker = this.mediaTracker;
		g2.convertImagesToJPEG = this.convertImagesToJPEG;
		g2.jpegQuality = this.jpegQuality;
		g2.setFont ( this.font );
		g2.cb = this.cb.getDuplicate ();
		g2.cb.saveState ();
		g2.width = this.width;
		g2.height = this.height;
		g2.followPath ( new Area ( new Rectangle2D.Float ( 0, 0, width, height ) ), CLIP );
		if ( this.clip != null )
			g2.clip = new Area ( this.clip );
		g2.composite = composite;
		g2.stroke = stroke;
		g2.originalStroke = originalStroke;
		g2.strokeOne = (BasicStroke) g2.transformStroke ( g2.strokeOne );
		g2.oldStroke = g2.strokeOne;
		g2.setStrokeDiff ( g2.oldStroke, null );
		g2.cb.saveState ();
		if ( g2.clip != null )
			g2.followPath ( g2.clip, CLIP );
		g2.kid = true;
		if ( this.kids == null )
			this.kids = new ArrayList<> ();
		this.kids.add ( new Kid ( cb.getInternalBuffer ().size (), g2 ) );
		return g2;
	}

	public PdfContentByte getContent () {
		return this.cb;
	}

	@Override
	public Color getColor () {
		if ( paint instanceof Color ) {
			return (Color) paint;
		} else {
			return Color.black;
		}
	}

	@Override
	public void setColor ( Color color ) {
		setPaint ( color );
	}

	@Override
	public void setPaintMode () {
	}

	@Override
	public void setXORMode ( Color c1 ) {

	}

	@Override
	public Font getFont () {
		return font;
	}

	@Override
	public void setFont ( Font f ) {
		if ( f == null )
			return;
		if ( onlyShapes ) {
			font = f;
			return;
		}
		if ( f == font )
			return;
		font = f;
		fontSize = f.getSize2D ();
		baseFont = getCachedBaseFont ( f );
	}

	private BaseFont getCachedBaseFont ( Font f ) {
		synchronized ( baseFonts ) {
			BaseFont bf = baseFonts.get ( f.getFontName () );
			if ( bf == null ) {
				bf = fontMapper.awtToPdf ( f );
				baseFonts.put ( f.getFontName (), bf );
			}
			return bf;
		}
	}

	@Override
	public FontMetrics getFontMetrics ( Font f ) {
		return getDG2 ().getFontMetrics ( f );
	}

	@Override
	public Rectangle getClipBounds () {
		if ( clip == null )
			return null;
		return getClip ().getBounds ();
	}

	@Override
	public void clipRect ( int x, int y, int width, int height ) {
		Rectangle2D rect = new Rectangle2D.Double ( x, y, width, height );
		clip ( rect );
	}

	@Override
	public void setClip ( int x, int y, int width, int height ) {
		Rectangle2D rect = new Rectangle2D.Double ( x, y, width, height );
		setClip ( rect );
	}

	@Override
	public void clip ( Shape s ) {
		if ( s == null ) {
			setClip ( null );
			return;
		}
		s = transform.createTransformedShape ( s );
		if ( clip == null )
			clip = new Area ( s );
		else
			clip.intersect ( new Area ( s ) );
		followPath ( s, CLIP );
	}

	@Override
	public Shape getClip () {
		try {
			return transform.createInverse ().createTransformedShape ( clip );
		} catch ( NoninvertibleTransformException e ) {
			return null;
		}
	}

	@Override
	public void setClip ( Shape s ) {
		cb.restoreState ();
		cb.saveState ();
		if ( s != null )
			s = transform.createTransformedShape ( s );
		if ( s == null ) {
			clip = null;
		} else {
			clip = new Area ( s );
			followPath ( s, CLIP );
		}
		paintFill = paintStroke = null;
		currentFillGState = currentStrokeGState = -1;
		oldStroke = strokeOne;
	}

	@Override
	public void copyArea ( int x, int y, int width, int height, int dx, int dy ) {

	}

	@Override
	public void drawLine ( int x1, int y1, int x2, int y2 ) {
		Line2D line = new Line2D.Double ( x1, y1, x2, y2 );
		draw ( line );
	}

	@Override
	public void drawRect ( int x, int y, int width, int height ) {
		draw ( new Rectangle ( x, y, width, height ) );
	}

	@Override
	public void fillRect ( int x, int y, int width, int height ) {
		fill ( new Rectangle ( x, y, width, height ) );
	}

	@Override
	public void clearRect ( int x, int y, int width, int height ) {
		Paint temp = paint;
		setPaint ( background );
		fillRect ( x, y, width, height );
		setPaint ( temp );
	}

	@Override
	public void drawRoundRect ( int x, int y, int width, int height, int arcWidth, int arcHeight ) {
		RoundRectangle2D rect = new RoundRectangle2D.Double ( x, y, width, height, arcWidth, arcHeight );
		draw ( rect );
	}

	@Override
	public void fillRoundRect ( int x, int y, int width, int height, int arcWidth, int arcHeight ) {
		RoundRectangle2D rect = new RoundRectangle2D.Double ( x, y, width, height, arcWidth, arcHeight );
		fill ( rect );
	}

	@Override
	public void drawOval ( int x, int y, int width, int height ) {
		Ellipse2D oval = new Ellipse2D.Float ( x, y, width, height );
		draw ( oval );
	}

	@Override
	public void fillOval ( int x, int y, int width, int height ) {
		Ellipse2D oval = new Ellipse2D.Float ( x, y, width, height );
		fill ( oval );
	}

	@Override
	public void drawArc ( int x, int y, int width, int height, int startAngle, int arcAngle ) {
		Arc2D arc = new Arc2D.Double ( x, y, width, height, startAngle, arcAngle, Arc2D.OPEN );
		draw ( arc );

	}

	@Override
	public void fillArc ( int x, int y, int width, int height, int startAngle, int arcAngle ) {
		Arc2D arc = new Arc2D.Double ( x, y, width, height, startAngle, arcAngle, Arc2D.PIE );
		fill ( arc );
	}

	@Override
	public void drawPolyline ( int[] x, int[] y, int nPoints ) {
		PolylineShape polyline = new PolylineShape ( x, y, nPoints );
		draw ( polyline );
	}

	@Override
	public void drawPolygon ( int[] xPoints, int[] yPoints, int nPoints ) {
		Polygon poly = new Polygon ( xPoints, yPoints, nPoints );
		draw ( poly );
	}

	@Override
	public void fillPolygon ( int[] xPoints, int[] yPoints, int nPoints ) {
		Polygon poly = new Polygon ();
		for ( int i = 0; i < nPoints; i++ ) {
			poly.addPoint ( xPoints[i], yPoints[i] );
		}
		fill ( poly );
	}

	@Override
	public boolean drawImage ( Image img, int x, int y, ImageObserver observer ) {
		return drawImage ( img, x, y, null, observer );
	}

	@Override
	public boolean drawImage ( Image img, int x, int y, int width, int height, ImageObserver observer ) {
		return drawImage ( img, x, y, width, height, null, observer );
	}

	@Override
	public boolean drawImage ( Image img, int x, int y, Color bgcolor, ImageObserver observer ) {
		waitForImage ( img );
		return drawImage ( img, x, y, img.getWidth ( observer ), img.getHeight ( observer ), bgcolor, observer );
	}

	@Override
	public boolean drawImage ( Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer ) {
		waitForImage ( img );
		double scalex = width / (double) img.getWidth ( observer );
		double scaley = height / (double) img.getHeight ( observer );
		AffineTransform tx = AffineTransform.getTranslateInstance ( x, y );
		tx.scale ( scalex, scaley );
		return drawImage ( img, null, tx, bgcolor, observer );
	}

	@Override
	public boolean drawImage ( Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer ) {
		return drawImage ( img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null, observer );
	}

	@Override
	public boolean drawImage ( Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer ) {
		waitForImage ( img );
		double dwidth = (double) dx2 - dx1;
		double dheight = (double) dy2 - dy1;
		double swidth = (double) sx2 - sx1;
		double sheight = (double) sy2 - sy1;

		if ( dwidth == 0 || dheight == 0 || swidth == 0 || sheight == 0 )
			return true;

		double scalex = dwidth / swidth;
		double scaley = dheight / sheight;

		double transx = sx1 * scalex;
		double transy = sy1 * scaley;
		AffineTransform tx = AffineTransform.getTranslateInstance ( dx1 - transx, dy1 - transy );
		tx.scale ( scalex, scaley );

		BufferedImage mask = new BufferedImage ( img.getWidth ( observer ), img.getHeight ( observer ), BufferedImage.TYPE_BYTE_BINARY );
		Graphics g = mask.getGraphics ();
		g.fillRect ( sx1, sy1, (int) swidth, (int) sheight );
		drawImage ( img, mask, tx, null, observer );
		g.dispose ();
		return true;
	}

	@Override
	public void dispose () {
		if ( kid )
			return;
		if ( !disposeCalled ) {
			disposeCalled = true;
			cb.restoreState ();
			cb.restoreState ();
			if ( dg2 != null ) {
				dg2.dispose ();
				dg2 = null;
			}
			if ( kids != null ) {
				ByteBuffer buf = new ByteBuffer ();
				internalDispose ( buf );
				ByteBuffer buf2 = cb.getInternalBuffer ();
				buf2.reset ();
				buf2.append ( buf );
			}
		}
	}

	private void internalDispose ( ByteBuffer buf ) {
		int last = 0;
		int pos;
		ByteBuffer buf2 = cb.getInternalBuffer ();
		if ( kids != null ) {
			for ( Kid kid : kids ) {
				pos = kid.pos;
				PdfGraphics2D g2 = kid.graphics;
				g2.cb.restoreState ();
				g2.cb.restoreState ();
				buf.append ( buf2.getBuffer (), last, pos - last );
				if ( g2.dg2 != null ) {
					g2.dg2.dispose ();
					g2.dg2 = null;
				}
				g2.internalDispose ( buf );
				last = pos;
			}
		}
		buf.append ( buf2.getBuffer (), last, buf2.size () - last );
	}

	private void followPath ( Shape s, int drawType ) {
		if ( s == null )
			return;
		if ( drawType == STROKE ) {
			if ( !( stroke instanceof BasicStroke ) ) {
				s = stroke.createStrokedShape ( s );
				followPath ( s, FILL );
				return;
			}
		}
		if ( drawType == STROKE ) {
			setStrokeDiff ( stroke, oldStroke );
			oldStroke = stroke;
			setStrokePaint ();
		} else if ( drawType == FILL )
			setFillPaint ();
		PathIterator points;
		int traces = 0;
		if ( drawType == CLIP )
			points = s.getPathIterator ( IDENTITY );
		else
			points = s.getPathIterator ( transform );
		float[] coords = new float[6];
		double[] dcoords = new double[6];
		while ( !points.isDone () ) {
			++traces;
			int segtype = points.currentSegment ( dcoords );
			int numpoints = ( segtype == PathIterator.SEG_CLOSE ? 0
							: ( segtype == PathIterator.SEG_QUADTO ? 2
							: ( segtype == PathIterator.SEG_CUBICTO ? 3
							: 1 ) ) );
			for ( int i = 0; i < numpoints * 2; i++ ) {
				coords[i] = (float) dcoords[i];
			}
			normalizeY ( coords );
			switch ( segtype ) {
			case PathIterator.SEG_CLOSE:
				cb.closePath ();
				break;

			case PathIterator.SEG_CUBICTO:
				cb.curveTo ( coords[0], coords[1], coords[2], coords[3], coords[4], coords[5] );
				break;

			case PathIterator.SEG_LINETO:
				cb.lineTo ( coords[0], coords[1] );
				break;

			case PathIterator.SEG_MOVETO:
				cb.moveTo ( coords[0], coords[1] );
				break;

			case PathIterator.SEG_QUADTO:
				cb.curveTo ( coords[0], coords[1], coords[2], coords[3] );
				break;
			}
			points.next ();
		}
		switch ( drawType ) {
		case FILL:
			if ( traces > 0 ) {
				if ( points.getWindingRule () == PathIterator.WIND_EVEN_ODD )
					cb.eoFill ();
				else
					cb.fill ();
			}
			break;
		case STROKE:
			if ( traces > 0 )
				cb.stroke ();
			break;
		default:
			if ( traces == 0 )
				cb.rectangle ( 0, 0, 0, 0 );
			if ( points.getWindingRule () == PathIterator.WIND_EVEN_ODD )
				cb.eoClip ();
			else
				cb.clip ();
			cb.newPath ();
		}
	}

	private float normalizeY ( float y ) {
		return this.height - y;
	}

	private void normalizeY ( float[] coords ) {
		coords[1] = normalizeY ( coords[1] );
		coords[3] = normalizeY ( coords[3] );
		coords[5] = normalizeY ( coords[5] );
	}

	protected AffineTransform normalizeMatrix () {
		double[] mx = new double[6];
		AffineTransform result = AffineTransform.getTranslateInstance ( 0, 0 );
		result.getMatrix ( mx );
		mx[3] = -1;
		mx[5] = height;
		result = new AffineTransform ( mx );
		result.concatenate ( transform );
		return result;
	}

	private boolean drawImage ( Image img, Image mask, AffineTransform xform, Color bgColor, ImageObserver obs ) {
		if ( xform == null )
			xform = new AffineTransform ();
		else
			xform = new AffineTransform ( xform );
		xform.translate ( 0, img.getHeight ( obs ) );
		xform.scale ( img.getWidth ( obs ), img.getHeight ( obs ) );

		AffineTransform inverse = this.normalizeMatrix ();
		AffineTransform flipper = AffineTransform.getScaleInstance ( 1, -1 );
		inverse.concatenate ( xform );
		inverse.concatenate ( flipper );

		double[] mx = new double[6];
		inverse.getMatrix ( mx );
		if ( currentFillGState != 255 ) {
			PdfGState gs = fillGState[255];
			if ( gs == null ) {
				gs = new PdfGState ();
				gs.setFillOpacity ( 1 );
				fillGState[255] = gs;
			}
			cb.setGState ( gs );
		}

		try {
			it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image image;
			if ( !convertImagesToJPEG ) {
				image = it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image.getInstance ( img, bgColor );
			} else {
				BufferedImage scaled = new BufferedImage ( img.getWidth ( null ), img.getHeight ( null ), BufferedImage.TYPE_INT_RGB );
				Graphics2D g3 = scaled.createGraphics ();
				g3.drawImage ( img, 0, 0, img.getWidth ( null ), img.getHeight ( null ), null );
				g3.dispose ();

				ByteArrayOutputStream baos = new ByteArrayOutputStream ();
				ImageWriteParam iwparam = new JPEGImageWriteParam ( Locale.getDefault () );
				iwparam.setCompressionMode ( ImageWriteParam.MODE_EXPLICIT );
				iwparam.setCompressionQuality ( jpegQuality );
				ImageWriter iw = ImageIO.getImageWritersByFormatName ( "jpg" ).next ();
				ImageOutputStream ios = ImageIO.createImageOutputStream ( baos );
				iw.setOutput ( ios );
				iw.write ( null, new IIOImage ( scaled, null, null ), iwparam );
				iw.dispose ();
				ios.close ();

				scaled.flush ();
				image = it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image.getInstance ( baos.toByteArray () );
			}
			if ( mask != null ) {
				it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image msk =
								it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image.getInstance ( mask, null, true );
				msk.makeMask ();
				msk.setInverted ( true );
				image.setImageMask ( msk );
			}
			cb.addImage ( image, (float) mx[0], (float) mx[1], (float) mx[2], (float) mx[3], (float) mx[4], (float) mx[5] );
			Object url = getRenderingHint ( HyperLinkKey.KEY_INSTANCE );
			if ( url != null && !url.equals ( HyperLinkKey.VALUE_HYPERLINKKEY_OFF ) ) {
				PdfAction action = new PdfAction ( url.toString () );
				cb.setAction ( action, (float) mx[4], (float) mx[5], (float) ( mx[0] + mx[4] ), (float) ( mx[3] + mx[5] ) );
			}
		} catch ( Exception ex ) {
			throw new IllegalArgumentException ( ex );
		}
		if ( currentFillGState >= 0 && currentFillGState != 255 ) {
			PdfGState gs = fillGState[currentFillGState];
			cb.setGState ( gs );
		}
		return true;
	}

	private boolean checkNewPaint ( Paint oldPaint ) {
		if ( paint == oldPaint )
			return false;
		return !( paint instanceof Color && paint.equals ( oldPaint ) );
	}

	private void setFillPaint () {
		if ( checkNewPaint ( paintFill ) ) {
			paintFill = paint;
			setPaint ( true );
		}
	}

	private void setStrokePaint () {
		if ( checkNewPaint ( paintStroke ) ) {
			paintStroke = paint;
			setPaint ( false );
		}
	}

	private synchronized void waitForImage ( Image image ) {
		if ( mediaTracker == null )
			mediaTracker = new MediaTracker ( new FakeComponent () );
		mediaTracker.addImage ( image, 0 );
		try {
			mediaTracker.waitForID ( 0 );
		} catch ( InterruptedException ignored ) {
		}
		mediaTracker.removeImage ( image );
	}

	private static final class Kid {

		final int pos;

		final PdfGraphics2D graphics;

		Kid ( int pos, PdfGraphics2D graphics ) {
			this.pos = pos;
			this.graphics = graphics;
		}
	}


	static private class FakeComponent extends Component {

		private static final long serialVersionUID = 6450197945596086638L;
	}


	public static class HyperLinkKey extends Key {

		public static final HyperLinkKey KEY_INSTANCE = new HyperLinkKey ( 9999 );

		public static final Object VALUE_HYPERLINKKEY_OFF = "0";

		protected HyperLinkKey ( int arg0 ) {
			super ( arg0 );
		}

		@Override
		public boolean isCompatibleValue ( Object val ) {
			return true;
		}

		@Override
		public String toString () {
			return "HyperLinkKey";
		}
	}

}

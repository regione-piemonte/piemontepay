/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Chunk;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ListBody;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ListItem;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ListLabel;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Paragraph;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Phrase;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.Logger;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.LoggerFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.draw.DrawInterface;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.IAccessibleElement;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.languages.ArabicLigaturizer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


public class ColumnText {

	public static final int AR_NOVOWEL = ArabicLigaturizer.ar_novowel;

	public static final int AR_COMPOSEDTASHKEEL = ArabicLigaturizer.ar_composedtashkeel;

	public static final int AR_LIG = ArabicLigaturizer.ar_lig;

	public static final int DIGITS_EN2AN = ArabicLigaturizer.DIGITS_EN2AN;

	public static final int DIGITS_AN2EN = ArabicLigaturizer.DIGITS_AN2EN;

	public static final int DIGITS_EN2AN_INIT_LR = ArabicLigaturizer.DIGITS_EN2AN_INIT_LR;

	public static final int DIGITS_EN2AN_INIT_AL = ArabicLigaturizer.DIGITS_EN2AN_INIT_AL;

	public static final int DIGIT_TYPE_AN = ArabicLigaturizer.DIGIT_TYPE_AN;

	public static final int DIGIT_TYPE_AN_EXTENDED = ArabicLigaturizer.DIGIT_TYPE_AN_EXTENDED;

	public static final float GLOBAL_SPACE_CHAR_RATIO = 0;

	public static final int START_COLUMN = 0;

	public static final int NO_MORE_TEXT = 1;

	public static final int NO_MORE_COLUMN = 2;

	protected static final int LINE_STATUS_OK = 0;

	protected static final int LINE_STATUS_OFFLIMITS = 1;

	protected static final int LINE_STATUS_NOLINE = 2;

	private final Logger LOGGER = LoggerFactory.getLogger ( ColumnText.class );

	protected int runDirection = PdfWriter.RUN_DIRECTION_DEFAULT;

	protected float maxY;

	protected float minY;

	protected float leftX;

	protected float rightX;

	protected int alignment = Element.ALIGN_LEFT;

	protected ArrayList<float[]> leftWall;

	protected ArrayList<float[]> rightWall;

	//    protected ArrayList chunks = new ArrayList();
	protected BidiLine bidiLine;

	protected float yLine;

	protected float lastX;

	protected float currentLeading = 16;

	protected float fixedLeading = 16;

	protected float multipliedLeading = 0;

	protected PdfContentByte canvas;

	protected PdfContentByte[] canvases;

	protected int lineStatus;

	protected float indent = 0;

	protected float followingIndent = 0;

	protected float rightIndent = 0;

	protected float extraParagraphSpace = 0;

	protected float rectangularWidth = -1;

	protected boolean rectangularMode = false;

	protected float descender;

	protected boolean composite = false;

	protected ColumnText compositeColumn;

	protected LinkedList<Element> compositeElements;

	protected int listIdx = 0;

	protected int rowIdx = 0;

	protected Phrase waitPhrase;

	private float spaceCharRatio = GLOBAL_SPACE_CHAR_RATIO;

	private boolean lastWasNewline = true;

	private boolean repeatFirstLineIndent = true;

	private int linesWritten;

	private float firstLineY;

	private boolean firstLineYDone = false;

	private int arabicOptions = 0;

	private int splittedRow = -1;

	private boolean useAscender = false;

	private float filledWidth;

	private boolean adjustFirstLine = true;

	private boolean inheritGraphicState = false;

	public ColumnText ( final PdfContentByte canvas ) {
		this.canvas = canvas;
	}

	public static ColumnText duplicate ( final ColumnText org ) {
		ColumnText ct = new ColumnText ( null );
		ct.setACopy ( org );
		return ct;
	}

	public static float getWidth ( final Phrase phrase, final int runDirection, final int arabicOptions ) {
		ColumnText ct = new ColumnText ( null );
		ct.addText ( phrase );
		ct.addWaitingPhrase ();
		PdfLine line = ct.bidiLine.processLine ( 0, 20000, Element.ALIGN_LEFT, runDirection, arabicOptions, 0, 0, 0 );
		if ( line == null )
			return 0;
		else
			return 20000 - line.widthLeft ();
	}

	public static float getWidth ( final Phrase phrase ) {
		return getWidth ( phrase, PdfWriter.RUN_DIRECTION_NO_BIDI, 0 );
	}

	public static void showTextAligned ( final PdfContentByte canvas, int alignment, final Phrase phrase, final float x, final float y, final float rotation,
					final int runDirection, final int arabicOptions ) {
		if ( alignment != Element.ALIGN_LEFT && alignment != Element.ALIGN_CENTER
						&& alignment != Element.ALIGN_RIGHT )
			alignment = Element.ALIGN_LEFT;
		canvas.saveState ();
		ColumnText ct = new ColumnText ( canvas );
		float lly = -1;
		float ury = 2;
		float llx;
		float urx;
		switch ( alignment ) {
		case Element.ALIGN_LEFT:
			llx = 0;
			urx = 20000;
			break;
		case Element.ALIGN_RIGHT:
			llx = -20000;
			urx = 0;
			break;
		default:
			llx = -20000;
			urx = 20000;
			break;
		}
		if ( rotation == 0 ) {
			llx += x;
			lly += y;
			urx += x;
			ury += y;
		} else {
			double alpha = rotation * Math.PI / 180.0;
			float cos = (float) Math.cos ( alpha );
			float sin = (float) Math.sin ( alpha );
			canvas.concatCTM ( cos, sin, -sin, cos, x, y );
		}
		ct.setSimpleColumn ( phrase, llx, lly, urx, ury, 2, alignment );
		if ( runDirection == PdfWriter.RUN_DIRECTION_RTL ) {
			if ( alignment == Element.ALIGN_LEFT )
				alignment = Element.ALIGN_RIGHT;
			else if ( alignment == Element.ALIGN_RIGHT )
				alignment = Element.ALIGN_LEFT;
		}
		ct.setAlignment ( alignment );
		ct.setArabicOptions ( arabicOptions );
		ct.setRunDirection ( runDirection );
		try {
			ct.go ();
		} catch ( DocumentException e ) {
			throw new ExceptionConverter ( e );
		}
		canvas.restoreState ();
	}

	public static void showTextAligned ( final PdfContentByte canvas, final int alignment, final Phrase phrase, final float x, final float y,
					final float rotation ) {
		showTextAligned ( canvas, alignment, phrase, x, y, rotation, PdfWriter.RUN_DIRECTION_NO_BIDI, 0 );
	}

	private static boolean isTagged ( final PdfContentByte canvas ) {
		return ( canvas != null ) && ( canvas.pdf != null ) && ( canvas.writer != null ) && canvas.writer.isTagged ();
	}

	public void setACopy ( final ColumnText org ) {
		setSimpleVars ( org );
		if ( org.bidiLine != null )
			bidiLine = new BidiLine ( org.bidiLine );
	}

	protected void setSimpleVars ( final ColumnText org ) {
		maxY = org.maxY;
		minY = org.minY;
		alignment = org.alignment;
		leftWall = null;
		if ( org.leftWall != null )
			leftWall = new ArrayList<> ( org.leftWall );
		rightWall = null;
		if ( org.rightWall != null )
			rightWall = new ArrayList<> ( org.rightWall );
		yLine = org.yLine;
		currentLeading = org.currentLeading;
		fixedLeading = org.fixedLeading;
		multipliedLeading = org.multipliedLeading;
		canvas = org.canvas;
		canvases = org.canvases;
		lineStatus = org.lineStatus;
		indent = org.indent;
		followingIndent = org.followingIndent;
		rightIndent = org.rightIndent;
		extraParagraphSpace = org.extraParagraphSpace;
		rectangularWidth = org.rectangularWidth;
		rectangularMode = org.rectangularMode;
		spaceCharRatio = org.spaceCharRatio;
		lastWasNewline = org.lastWasNewline;
		repeatFirstLineIndent = org.repeatFirstLineIndent;
		linesWritten = org.linesWritten;
		arabicOptions = org.arabicOptions;
		runDirection = org.runDirection;
		descender = org.descender;
		composite = org.composite;
		splittedRow = org.splittedRow;
		if ( org.composite ) {
			compositeElements = new LinkedList<> ();
			for ( Element element : org.compositeElements ) {
				if ( element instanceof PdfPTable ) {
					compositeElements.add ( new PdfPTable ( (PdfPTable) element ) );
				} else {
					compositeElements.add ( element );
				}
			}
			if ( org.compositeColumn != null )
				compositeColumn = duplicate ( org.compositeColumn );
		}
		listIdx = org.listIdx;
		rowIdx = org.rowIdx;
		firstLineY = org.firstLineY;
		leftX = org.leftX;
		rightX = org.rightX;
		firstLineYDone = org.firstLineYDone;
		waitPhrase = org.waitPhrase;
		useAscender = org.useAscender;
		filledWidth = org.filledWidth;
		adjustFirstLine = org.adjustFirstLine;
		inheritGraphicState = org.inheritGraphicState;
	}

	private void addWaitingPhrase () {
		if ( bidiLine == null && waitPhrase != null ) {
			bidiLine = new BidiLine ();
			for ( Chunk c : waitPhrase.getChunks () ) {
				bidiLine.addChunk ( new PdfChunk ( c, null, waitPhrase.getTabSettings () ) );
			}
			waitPhrase = null;
		}
	}

	public void addText ( final Phrase phrase ) {
		if ( phrase == null || composite )
			return;
		addWaitingPhrase ();
		if ( bidiLine == null ) {
			waitPhrase = phrase;
			return;
		}
		for ( Chunk element : phrase.getChunks () ) {
			bidiLine.addChunk ( new PdfChunk ( element, null, phrase.getTabSettings () ) );
		}
	}

	public void setText ( final Phrase phrase ) {
		bidiLine = null;
		composite = false;
		compositeColumn = null;
		compositeElements = null;
		listIdx = 0;
		rowIdx = 0;
		splittedRow = -1;
		waitPhrase = phrase;
	}

	public void addElement ( Element element ) {
		if ( element == null )
			return;
		if ( element instanceof Image ) {
			Image img = (Image) element;
			PdfPTable t = new PdfPTable ( 1 );
			float w = img.getWidthPercentage ();
			if ( w == 0 ) {
				t.setTotalWidth ( img.getScaledWidth () );
				t.setLockedWidth ( true );
			} else
				t.setWidthPercentage ( w );
			t.setSpacingAfter ( img.getSpacingAfter () );
			t.setSpacingBefore ( img.getSpacingBefore () );
			switch ( img.getAlignment () ) {
			case Image.LEFT:
				t.setHorizontalAlignment ( Element.ALIGN_LEFT );
				break;
			case Image.RIGHT:
				t.setHorizontalAlignment ( Element.ALIGN_RIGHT );
				break;
			default:
				t.setHorizontalAlignment ( Element.ALIGN_CENTER );
				break;
			}
			PdfPCell c = new PdfPCell ( img, true );
			c.setPadding ( 0 );
			c.setBorder ( img.getBorder () );
			c.setBorderColor ( img.getBorderColor () );
			c.setBorderWidth ( img.getBorderWidth () );
			c.setBackgroundColor ( img.getBackgroundColor () );
			t.addCell ( c );
			element = t;
		}
		if ( element.type () == Element.CHUNK ) {
			element = new Paragraph ( (Chunk) element );
		} else if ( element.type () == Element.PHRASE ) {
			element = new Paragraph ( (Phrase) element );
		}
		if ( element.type () != Element.PARAGRAPH && element.type () != Element.LIST && element.type () != Element.PTABLE && element.type () != Element.YMARK && element.type () != Element.DIV )
			throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "element.not.allowed" ) );
		if ( !composite ) {
			composite = true;
			compositeElements = new LinkedList<> ();
			bidiLine = null;
			waitPhrase = null;
		}
		if ( element.type () == Element.PARAGRAPH ) {
			Paragraph p = (Paragraph) element;
			compositeElements.addAll ( p.breakUp () );
			return;
		}
		compositeElements.add ( element );
	}

	protected float findLimitsPoint ( final ArrayList<float[]> wall ) {
		lineStatus = LINE_STATUS_OK;
		if ( yLine < minY || yLine > maxY ) {
			lineStatus = LINE_STATUS_OFFLIMITS;
			return 0;
		}
		for ( float[] r : wall ) {
			if ( yLine < r[0] || yLine > r[1] )
				continue;
			return r[2] * yLine + r[3];
		}
		lineStatus = LINE_STATUS_NOLINE;
		return 0;
	}

	protected float[] findLimitsOneLine () {
		float x1 = findLimitsPoint ( leftWall );
		if ( lineStatus == LINE_STATUS_OFFLIMITS || lineStatus == LINE_STATUS_NOLINE )
			return null;
		float x2 = findLimitsPoint ( rightWall );
		if ( lineStatus == LINE_STATUS_NOLINE )
			return null;
		return new float[] { x1, x2 };
	}

	protected float[] findLimitsTwoLines () {
		boolean repeat = false;
		for ( ; ; ) {
			if ( repeat && currentLeading == 0 )
				return null;
			repeat = true;
			float[] x1 = findLimitsOneLine ();
			if ( lineStatus == LINE_STATUS_OFFLIMITS )
				return null;
			yLine -= currentLeading;
			if ( lineStatus == LINE_STATUS_NOLINE ) {
				continue;
			}
			float[] x2 = findLimitsOneLine ();
			if ( lineStatus == LINE_STATUS_OFFLIMITS )
				return null;
			if ( lineStatus == LINE_STATUS_NOLINE ) {
				yLine -= currentLeading;
				continue;
			}
			if ( x1[0] >= x2[1] || x2[0] >= x1[1] )
				continue;
			return new float[] { x1[0], x1[1], x2[0], x2[1] };
		}
	}

	public void setSimpleColumn ( final Phrase phrase, final float llx, final float lly, final float urx, final float ury, final float leading,
					final int alignment ) {
		addText ( phrase );
		setSimpleColumn ( llx, lly, urx, ury, leading, alignment );
	}

	public void setSimpleColumn ( final float llx, final float lly, final float urx, final float ury, final float leading, final int alignment ) {
		setLeading ( leading );
		this.alignment = alignment;
		setSimpleColumn ( llx, lly, urx, ury );
	}

	public void setSimpleColumn ( final float llx, final float lly, final float urx, final float ury ) {
		leftX = Math.min ( llx, urx );
		maxY = Math.max ( lly, ury );
		minY = Math.min ( lly, ury );
		rightX = Math.max ( llx, urx );
		yLine = maxY;
		rectangularWidth = rightX - leftX;
		if ( rectangularWidth < 0 )
			rectangularWidth = 0;
		rectangularMode = true;
	}

	public void setLeading ( final float fixedLeading, final float multipliedLeading ) {
		this.fixedLeading = fixedLeading;
		this.multipliedLeading = multipliedLeading;
	}

	public float getLeading () {
		return fixedLeading;
	}

	public void setLeading ( final float leading ) {
		fixedLeading = leading;
		multipliedLeading = 0;
	}

	public float getYLine () {
		return yLine;
	}

	public void setYLine ( final float yLine ) {
		this.yLine = yLine;
	}

	public int getAlignment () {
		return alignment;
	}

	public void setAlignment ( final int alignment ) {
		this.alignment = alignment;
	}

	public void setIndent ( final float indent, final boolean repeatFirstLineIndent ) {
		this.indent = indent;
		lastWasNewline = true;
		this.repeatFirstLineIndent = repeatFirstLineIndent;
	}

	public float getIndent () {
		return indent;
	}

	public void setIndent ( final float indent ) {
		setIndent ( indent, true );
	}

	public void setFollowingIndent ( final float indent ) {
		this.followingIndent = indent;
		lastWasNewline = true;
	}

	public void setRightIndent ( final float indent ) {
		this.rightIndent = indent;
		lastWasNewline = true;
	}

	public void setInheritGraphicState ( boolean inheritGraphicState ) {
		this.inheritGraphicState = inheritGraphicState;
	}

	public int go () throws DocumentException {
		return go ( false );
	}

	public int go ( final boolean simulate ) throws DocumentException {
		return go ( simulate, null );
	}

	public int go ( final boolean simulate, final IAccessibleElement elementToGo ) throws DocumentException {
		if ( composite )
			return goComposite ( simulate );

		ListBody lBody = null;
		if ( isTagged ( canvas ) && elementToGo instanceof ListItem ) {
			lBody = ( (ListItem) elementToGo ).getListBody ();
		}

		addWaitingPhrase ();
		if ( bidiLine == null )
			return NO_MORE_TEXT;
		descender = 0;
		linesWritten = 0;
		lastX = 0;
		boolean dirty = false;
		float ratio = spaceCharRatio;
		Object[] currentValues = new Object[2];
		PdfFont currentFont = null;
		float lastBaseFactor = (float) 0;
		currentValues[1] = lastBaseFactor;
		PdfDocument pdf = null;
		PdfContentByte graphics = null;
		PdfContentByte text = null;
		firstLineY = Float.NaN;
		int localRunDirection = PdfWriter.RUN_DIRECTION_NO_BIDI;
		if ( runDirection != PdfWriter.RUN_DIRECTION_DEFAULT )
			localRunDirection = runDirection;
		if ( canvas != null ) {
			graphics = canvas;
			pdf = canvas.getPdfDocument ();
			if ( !isTagged ( canvas ) )
				text = canvas.getDuplicate ( inheritGraphicState );
			else
				text = canvas;
		} else if ( !simulate )
			throw new NullPointerException ( MessageLocalization.getComposedMessage ( "columntext.go.with.simulate.eq.eq.false.and.text.eq.eq.null" ) );
		if ( !simulate ) {
			if ( ratio == GLOBAL_SPACE_CHAR_RATIO )
				ratio = text.getPdfWriter ().getSpaceCharRatio ();
			else if ( ratio < 0.001f )
				ratio = 0.001f;
		}
		if ( !rectangularMode ) {
			float max = 0;
			for ( PdfChunk c : bidiLine.chunks ) {
				max = Math.max ( max, c.height () );
			}
			currentLeading = fixedLeading + max * multipliedLeading;
		}
		float firstIndent;
		PdfLine line;
		float x1;
		int status;
		while ( true ) {
			firstIndent = lastWasNewline ? indent : followingIndent;
			if ( rectangularMode ) {
				if ( rectangularWidth <= firstIndent + rightIndent ) {
					status = NO_MORE_COLUMN;
					if ( bidiLine.isEmpty () )
						status |= NO_MORE_TEXT;
					break;
				}
				if ( bidiLine.isEmpty () ) {
					status = NO_MORE_TEXT;
					break;
				}
				line = bidiLine.processLine ( leftX, rectangularWidth - firstIndent - rightIndent, alignment, localRunDirection, arabicOptions, minY, yLine,
								descender );
				if ( line == null ) {
					status = NO_MORE_TEXT;
					break;
				}
				float[] maxSize = line.getMaxSize ( fixedLeading, multipliedLeading );
				if ( isUseAscender () && Float.isNaN ( firstLineY ) )
					currentLeading = line.getAscender ();
				else
					currentLeading = Math.max ( maxSize[0], maxSize[1] - descender );
				if ( yLine > maxY || yLine - currentLeading < minY ) {
					status = NO_MORE_COLUMN;
					bidiLine.restore ();
					break;
				}
				yLine -= currentLeading;
				if ( !simulate && !dirty ) {
					text.beginText ();
					dirty = true;
				}
				if ( Float.isNaN ( firstLineY ) )
					firstLineY = yLine;
				updateFilledWidth ( rectangularWidth - line.widthLeft () );
				x1 = leftX;
			} else {
				float yTemp = yLine - currentLeading;
				float[] xx = findLimitsTwoLines ();
				if ( xx == null ) {
					status = NO_MORE_COLUMN;
					if ( bidiLine.isEmpty () )
						status |= NO_MORE_TEXT;
					yLine = yTemp;
					break;
				}
				if ( bidiLine.isEmpty () ) {
					status = NO_MORE_TEXT;
					yLine = yTemp;
					break;
				}
				x1 = Math.max ( xx[0], xx[2] );
				float x2 = Math.min ( xx[1], xx[3] );
				if ( x2 - x1 <= firstIndent + rightIndent )
					continue;
				if ( !simulate && !dirty ) {
					text.beginText ();
					dirty = true;
				}
				line = bidiLine.processLine ( x1, x2 - x1 - firstIndent - rightIndent, alignment, localRunDirection, arabicOptions, minY, yLine, descender );
				if ( line == null ) {
					status = NO_MORE_TEXT;
					yLine = yTemp;
					break;
				}
			}
			if ( isTagged ( canvas ) && elementToGo instanceof ListItem ) {
				if ( !Float.isNaN ( firstLineY ) && !firstLineYDone ) {
					if ( !simulate ) {
						ListLabel lbl = ( (ListItem) elementToGo ).getListLabel ();
						canvas.openMCBlock ( lbl );
						Chunk symbol = new Chunk ( ( (ListItem) elementToGo ).getListSymbol () );
						symbol.setRole ( null );
						ColumnText.showTextAligned ( canvas, Element.ALIGN_LEFT, new Phrase ( symbol ), leftX + lbl.getIndentation (), firstLineY, 0 );
						canvas.closeMCBlock ( lbl );
					}
					firstLineYDone = true;
				}
			}
			if ( !simulate ) {
				if ( lBody != null ) {
					canvas.openMCBlock ( lBody );
					lBody = null;
				}
				currentValues[0] = currentFont;
				text.setTextMatrix ( x1 + ( line.isRTL () ? rightIndent : firstIndent ) + line.indentLeft (), yLine );
				lastX = pdf.writeLineToContent ( line, text, graphics, currentValues, ratio );
				currentFont = (PdfFont) currentValues[0];
			}
			lastWasNewline = repeatFirstLineIndent && line.isNewlineSplit ();
			yLine -= line.isNewlineSplit () ? extraParagraphSpace : 0;
			++linesWritten;
			descender = line.getDescender ();
		}
		if ( dirty ) {
			text.endText ();
			if ( canvas != text )
				canvas.add ( text );
		}
		return status;
	}

	public void setExtraParagraphSpace ( final float extraParagraphSpace ) {
		this.extraParagraphSpace = extraParagraphSpace;
	}

	public void setSpaceCharRatio ( final float spaceCharRatio ) {
		this.spaceCharRatio = spaceCharRatio;
	}

	public int getRunDirection () {
		return runDirection;
	}

	public void setRunDirection ( final int runDirection ) {
		if ( runDirection < PdfWriter.RUN_DIRECTION_DEFAULT || runDirection > PdfWriter.RUN_DIRECTION_RTL )
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "invalid.run.direction.1", runDirection ) );
		this.runDirection = runDirection;
	}

	public int getLinesWritten () {
		return this.linesWritten;
	}

	public float getLastX () {
		return lastX;
	}

	public void setArabicOptions ( final int arabicOptions ) {
		this.arabicOptions = arabicOptions;
	}

	public float getDescender () {
		return descender;
	}

	protected int goComposite ( final boolean simulate ) throws DocumentException {
		if ( !rectangularMode )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "irregular.columns.are.not.supported.in.composite.mode" ) );
		linesWritten = 0;
		descender = 0;
		boolean firstPass = true;
		main_loop:
		while ( true ) {
			if ( compositeElements.isEmpty () )
				return NO_MORE_TEXT;
			Element element = compositeElements.getFirst ();
			if ( element.type () == Element.PARAGRAPH ) {
				Paragraph para = (Paragraph) element;
				int status = 0;
				for ( int keep = 0; keep < 2; ++keep ) {
					float lastY = yLine;
					boolean createHere = false;
					if ( compositeColumn == null ) {
						compositeColumn = new ColumnText ( canvas );
						compositeColumn.setAlignment ( para.getAlignment () );
						compositeColumn.setIndent ( para.getIndentationLeft () + para.getFirstLineIndent (), false );
						compositeColumn.setExtraParagraphSpace ( para.getExtraParagraphSpace () );
						compositeColumn.setFollowingIndent ( para.getIndentationLeft () );
						compositeColumn.setRightIndent ( para.getIndentationRight () );
						compositeColumn.setLeading ( para.getLeading (), para.getMultipliedLeading () );
						compositeColumn.setRunDirection ( runDirection );
						compositeColumn.setArabicOptions ( arabicOptions );
						compositeColumn.setSpaceCharRatio ( spaceCharRatio );
						compositeColumn.addText ( para );
						if ( !( firstPass && adjustFirstLine ) ) {
							yLine -= para.getSpacingBefore ();
						}
						createHere = true;
					}
					compositeColumn.setUseAscender ( ( firstPass || descender == 0 ) && adjustFirstLine && useAscender );
					compositeColumn.setInheritGraphicState ( inheritGraphicState );
					compositeColumn.leftX = leftX;
					compositeColumn.rightX = rightX;
					compositeColumn.yLine = yLine;
					compositeColumn.rectangularWidth = rectangularWidth;
					compositeColumn.rectangularMode = rectangularMode;
					compositeColumn.minY = minY;
					compositeColumn.maxY = maxY;
					boolean keepCandidate = para.getKeepTogether () && createHere && !( firstPass && adjustFirstLine );
					boolean s = simulate || keepCandidate && keep == 0;
					if ( isTagged ( canvas ) && !s ) {
						canvas.openMCBlock ( para );
					}
					status = compositeColumn.go ( s );
					if ( isTagged ( canvas ) && !s ) {
						canvas.closeMCBlock ( para );
					}
					lastX = compositeColumn.getLastX ();
					updateFilledWidth ( compositeColumn.filledWidth );
					if ( ( status & NO_MORE_TEXT ) == 0 && keepCandidate ) {
						compositeColumn = null;
						yLine = lastY;
						return NO_MORE_COLUMN;
					}
					if ( simulate || !keepCandidate )
						break;
					if ( keep == 0 ) {
						compositeColumn = null;
						yLine = lastY;
					}
				}
				firstPass = false;
				if ( compositeColumn.getLinesWritten () > 0 ) {
					yLine = compositeColumn.yLine;
					linesWritten += compositeColumn.linesWritten;
					descender = compositeColumn.descender;
				}
				currentLeading = compositeColumn.currentLeading;
				if ( ( status & NO_MORE_TEXT ) != 0 ) {
					compositeColumn = null;
					compositeElements.removeFirst ();
					yLine -= para.getSpacingAfter ();
				}
				if ( ( status & NO_MORE_COLUMN ) != 0 ) {
					return NO_MORE_COLUMN;
				}
			} else if ( element.type () == Element.LIST ) {
				it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.List list =
								(it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.List) element;
				ArrayList<Element> items = list.getItems ();
				ListItem item = null;
				float listIndentation = list.getIndentationLeft ();
				int count = 0;
				Stack<Object[]> stack = new Stack<> ();
				for ( int k = 0; k < items.size (); ++k ) {
					Object obj = items.get ( k );
					if ( obj instanceof ListItem ) {
						if ( count == listIdx ) {
							item = (ListItem) obj;
							break;
						} else
							++count;
					} else if ( obj instanceof it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.List ) {
						stack.push ( new Object[] { list, k, listIndentation } );
						list = (it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.List) obj;
						items = list.getItems ();
						listIndentation += list.getIndentationLeft ();
						k = -1;
						continue;
					}
					if ( k == items.size () - 1 ) {
						if ( !stack.isEmpty () ) {
							Object[] objs = stack.pop ();
							list = (it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.List) objs[0];
							items = list.getItems ();
							k = (Integer) objs[1];
							listIndentation = (Float) objs[2];
						}
					}
				}
				int status = 0;
				for ( int keep = 0; keep < 2; ++keep ) {
					float lastY = yLine;
					boolean createHere = false;
					if ( compositeColumn == null ) {
						if ( item == null ) {
							listIdx = 0;
							compositeElements.removeFirst ();
							continue main_loop;
						}
						compositeColumn = new ColumnText ( canvas );
						compositeColumn.setUseAscender ( ( firstPass || descender == 0 ) && adjustFirstLine && useAscender );
						compositeColumn.setInheritGraphicState ( inheritGraphicState );
						compositeColumn.setAlignment ( item.getAlignment () );
						compositeColumn.setIndent ( item.getIndentationLeft () + listIndentation + item.getFirstLineIndent (), false );
						compositeColumn.setExtraParagraphSpace ( item.getExtraParagraphSpace () );
						compositeColumn.setFollowingIndent ( compositeColumn.getIndent () );
						compositeColumn.setRightIndent ( item.getIndentationRight () + list.getIndentationRight () );
						compositeColumn.setLeading ( item.getLeading (), item.getMultipliedLeading () );
						compositeColumn.setRunDirection ( runDirection );
						compositeColumn.setArabicOptions ( arabicOptions );
						compositeColumn.setSpaceCharRatio ( spaceCharRatio );
						compositeColumn.addText ( item );
						if ( !( firstPass && adjustFirstLine ) ) {
							yLine -= item.getSpacingBefore ();
						}
						createHere = true;
					}
					compositeColumn.leftX = leftX;
					compositeColumn.rightX = rightX;
					compositeColumn.yLine = yLine;
					compositeColumn.rectangularWidth = rectangularWidth;
					compositeColumn.rectangularMode = rectangularMode;
					compositeColumn.minY = minY;
					compositeColumn.maxY = maxY;
					assert item != null;
					boolean keepCandidate = item.getKeepTogether () && createHere && !( firstPass && adjustFirstLine );
					boolean s = simulate || keepCandidate && keep == 0;
					if ( isTagged ( canvas ) && !s ) {
						item.getListLabel ().setIndentation ( listIndentation );
						if ( list.getFirstItem () == item || ( compositeColumn != null && compositeColumn.bidiLine != null ) )
							canvas.openMCBlock ( list );
						canvas.openMCBlock ( item );
					}
					status = compositeColumn.go ( simulate || keepCandidate && keep == 0, item );
					if ( isTagged ( canvas ) && !s ) {
						canvas.closeMCBlock ( item.getListBody () );
						canvas.closeMCBlock ( item );
						if ( ( list.getLastItem () == item && ( status & NO_MORE_TEXT ) != 0 ) || ( status & NO_MORE_COLUMN ) != 0 )
							canvas.closeMCBlock ( list );
					}
					lastX = compositeColumn.getLastX ();
					updateFilledWidth ( compositeColumn.filledWidth );
					if ( ( status & NO_MORE_TEXT ) == 0 && keepCandidate ) {
						compositeColumn = null;
						yLine = lastY;
						return NO_MORE_COLUMN;
					}
					if ( simulate || !keepCandidate )
						break;
					if ( keep == 0 ) {
						compositeColumn = null;
						yLine = lastY;
					}
				}
				firstPass = false;
				yLine = compositeColumn.yLine;
				linesWritten += compositeColumn.linesWritten;
				descender = compositeColumn.descender;
				currentLeading = compositeColumn.currentLeading;
				if ( !isTagged ( canvas ) ) {
					if ( !Float.isNaN ( compositeColumn.firstLineY ) && !compositeColumn.firstLineYDone ) {
						if ( !simulate ) {
							showTextAligned ( canvas, Element.ALIGN_LEFT, new Phrase ( item.getListSymbol () ), compositeColumn.leftX + listIndentation,
											compositeColumn.firstLineY, 0 );
						}
						compositeColumn.firstLineYDone = true;
					}
				}
				if ( ( status & NO_MORE_TEXT ) != 0 ) {
					compositeColumn = null;
					++listIdx;
					yLine -= item.getSpacingAfter ();
				}
				if ( ( status & NO_MORE_COLUMN ) != 0 )
					return NO_MORE_COLUMN;
			} else if ( element.type () == Element.PTABLE ) {

				PdfPTable table = (PdfPTable) element;

				if ( table.size () <= table.getHeaderRows () ) {
					compositeElements.removeFirst ();
					continue;
				}

				float yTemp = yLine;
				yTemp += descender;
				if ( rowIdx == 0 && adjustFirstLine )
					yTemp -= table.spacingBefore ();

				if ( yTemp < minY || yTemp > maxY )
					return NO_MORE_COLUMN;

				// mark start of table
				float yLineWrite = yTemp;
				float x1 = leftX;
				currentLeading = 0;
				// get the width of the table
				float tableWidth;
				if ( table.isLockedWidth () ) {
					tableWidth = table.getTotalWidth ();
					updateFilledWidth ( tableWidth );
				} else {
					tableWidth = rectangularWidth * table.getWidthPercentage () / 100f;
					table.setTotalWidth ( tableWidth );
				}

				table.normalizeHeadersFooters ();
				int headerRows = table.getHeaderRows ();
				int footerRows = table.getFooterRows ();
				int realHeaderRows = headerRows - footerRows;
				float headerHeight = table.getHeaderHeight ();
				float footerHeight = table.getFooterHeight ();

				// do we need to skip the header?
				boolean skipHeader = table.isSkipFirstHeader () && rowIdx <= realHeaderRows && ( table.isComplete () || rowIdx != realHeaderRows );
				// if not, we wan't to be able to add more than just a header and a footer
				if ( !skipHeader ) {
					yTemp -= headerHeight;
					if ( yTemp < minY || yTemp > maxY ) {
						return NO_MORE_COLUMN;
					}
				}

				int k;
				if ( rowIdx < headerRows ) {
					rowIdx = headerRows;
				}
				if ( !table.isComplete () )
					yTemp -= footerHeight;
				PdfPTable.FittingRows fittingRows = table.getFittingRows ( yTemp - minY, rowIdx );
				k = fittingRows.lastRow + 1;
				yTemp -= fittingRows.height;

				LOGGER.info ( "Want to split at row " + k );
				int kTemp = k;
				while ( kTemp > rowIdx && kTemp < table.size () && table.getRow ( kTemp ).isMayNotBreak () ) {
					kTemp--;
				}
				if ( ( kTemp > rowIdx && kTemp < k ) || ( kTemp == 0 && table.getRow ( 0 ).isMayNotBreak () && table.isLoopCheck () ) ) {
					yTemp = minY;
					k = kTemp;
					table.setLoopCheck ( false );
				}
				LOGGER.info ( "Will split at row " + k );

				if ( table.isSplitLate () && k > 0 ) {
					fittingRows.correctLastRowChosen ( table, k - 1 );
				}
				// splitting row spans

				// only for incomplete tables:
				if ( !table.isComplete () )
					yTemp += footerHeight;

				// IF ROWS MAY NOT BE SPLIT
				if ( !table.isSplitRows () ) {
					splittedRow = -1;
					if ( k == rowIdx ) {
						// drop the whole table
						if ( k == table.size () ) {
							compositeElements.removeFirst ();
							continue;
						}
						// or drop the row
						else {
							table.getRows ().remove ( k );
							return NO_MORE_COLUMN;
						}
					}
				} else if ( table.isSplitLate () && rowIdx < k ) {
					splittedRow = -1;
				} else if ( k < table.size () ) {
					yTemp -= fittingRows.completedRowsHeight - fittingRows.height;

					float h = yTemp - minY;
					PdfPRow newRow = table.getRow ( k ).splitRow ( table, k, h );
					if ( newRow == null ) {
						LOGGER.info ( "Didn't split row!" );
						splittedRow = -1;
						if ( rowIdx == k )
							return NO_MORE_COLUMN;
					} else {
						if ( k != splittedRow ) {
							splittedRow = k + 1;
							table = new PdfPTable ( table );
							compositeElements.set ( 0, table );
							ArrayList<PdfPRow> rows = table.getRows ();
							for ( int i = headerRows; i < rowIdx; ++i )
								rows.set ( i, null );
						}
						yTemp = minY;
						table.getRows ().add ( ++k, newRow );
						LOGGER.info ( "Inserting row at position " + k );
					}
				}

				// We're no longer in the first pass
				firstPass = false;

				// if not in simulation mode, draw the table
				if ( !simulate ) {
					// set the alignment
					switch ( table.getHorizontalAlignment () ) {
					case Element.ALIGN_LEFT:
						break;
					case Element.ALIGN_RIGHT:
						x1 += rectangularWidth - tableWidth;
						break;
					default:
						x1 += ( rectangularWidth - tableWidth ) / 2f;
					}
					// copy the rows that fit on the page in a new table nt
					PdfPTable nt = PdfPTable.shallowCopy ( table );
					ArrayList<PdfPRow> sub = nt.getRows ();
					// first we add the real header rows (if necessary)
					if ( !skipHeader && realHeaderRows > 0 ) {
						ArrayList<PdfPRow> rows = table.getRows ( 0, realHeaderRows );
						if ( isTagged ( canvas ) )
							nt.getHeader ().rows = rows;
						sub.addAll ( rows );
					} else
						nt.setHeaderRows ( footerRows );
					// then we add the real content

					{
						ArrayList<PdfPRow> rows = table.getRows ( rowIdx, k );
						if ( isTagged ( canvas ) ) {
							nt.getBody ().rows = rows;
						}
						sub.addAll ( rows );
					}
					// do we need to show a footer?
					boolean showFooter = !table.isSkipLastFooter ();
					boolean newPageFollows = false;
					if ( k < table.size () ) {
						nt.setComplete ( true );
						showFooter = true;
						newPageFollows = true;
					}
					// we add the footer rows if necessary (not for incomplete tables)
					if ( footerRows > 0 && nt.isComplete () && showFooter ) {
						ArrayList<PdfPRow> rows = table.getRows ( realHeaderRows, realHeaderRows + footerRows );
						if ( isTagged ( canvas ) ) {
							nt.getFooter ().rows = rows;
						}
						sub.addAll ( rows );
					} else {
						footerRows = 0;
					}

					// we need a correction if the last row needs to be extended
					float rowHeight = 0;
					int lastIdx = sub.size () - 1 - footerRows;
					PdfPRow last = sub.get ( lastIdx );
					if ( table.isExtendLastRow ( newPageFollows ) ) {
						rowHeight = last.getMaxHeights ();
						last.setMaxHeights ( yTemp - minY + rowHeight );
						yTemp = minY;
					}

					// newPageFollows indicates that this table is being split
					if ( newPageFollows ) {
						PdfPTableEvent tableEvent = table.getTableEvent ();
						if ( tableEvent instanceof PdfPTableEventSplit ) {
							( (PdfPTableEventSplit) tableEvent ).splitTable ( table );
						}
					}

					// now we render the rows of the new table
					if ( canvases != null ) {
						if ( isTagged ( canvases[PdfPTable.TEXTCANVAS] ) ) {
							canvases[PdfPTable.TEXTCANVAS].openMCBlock ( table );
						}
						nt.writeSelectedRows ( 0, -1, 0, -1, x1, yLineWrite, canvases, false );
						if ( isTagged ( canvases[PdfPTable.TEXTCANVAS] ) ) {
							canvases[PdfPTable.TEXTCANVAS].closeMCBlock ( table );
						}
					} else {
						if ( isTagged ( canvas ) ) {
							canvas.openMCBlock ( table );
						}
						nt.writeSelectedRows ( 0, -1, 0, -1, x1, yLineWrite, canvas, false );
						if ( isTagged ( canvas ) ) {
							canvas.closeMCBlock ( table );
						}
					}

					if ( splittedRow == k && k < table.size () ) {
						PdfPRow splitted = table.getRows ().get ( k );
						splitted.copyRowContent ( nt, lastIdx );
					} else if ( k > 0 && k < table.size () ) {
						PdfPRow row = table.getRow ( k );
						row.splitRowspans ( table, k - 1, nt, lastIdx );
					}
					if ( table.isExtendLastRow ( newPageFollows ) ) {
						last.setMaxHeights ( rowHeight );
					}
					if ( newPageFollows ) {
						PdfPTableEvent tableEvent = table.getTableEvent ();
						if ( tableEvent instanceof PdfPTableEventAfterSplit ) {
							PdfPRow row = table.getRow ( k );
							( (PdfPTableEventAfterSplit) tableEvent ).afterSplitTable ( table, row, k );
						}
					}
				} else if ( table.isExtendLastRow () && minY > PdfPRow.BOTTOM_LIMIT ) {
					yTemp = minY;
				}

				yLine = yTemp;
				descender = 0;
				currentLeading = 0;
				if ( !( skipHeader || table.isComplete () ) )
					yLine += footerHeight;
				while ( k < table.size () ) {
					if ( table.getRowHeight ( k ) > 0 || table.hasRowspan ( k ) ) {
						break;
					}
					k++;
				}
				if ( k >= table.size () ) {
					// Use up space no more than left
					if ( yLine - table.spacingAfter () < minY ) {
						yLine = minY;
					} else {
						yLine -= table.spacingAfter ();
					}
					compositeElements.removeFirst ();
					splittedRow = -1;
					rowIdx = 0;
				} else {
					if ( splittedRow != -1 ) {
						ArrayList<PdfPRow> rows = table.getRows ();
						for ( int i = rowIdx; i < k; ++i )
							rows.set ( i, null );
					}
					rowIdx = k;
					return NO_MORE_COLUMN;
				}
			} else if ( element.type () == Element.YMARK ) {
				if ( !simulate ) {
					DrawInterface zh = (DrawInterface) element;
					zh.draw ( canvas, leftX, minY, rightX, maxY, yLine );
				}
				compositeElements.removeFirst ();
			} else if ( element.type () == Element.DIV ) {
				ArrayList<Element> floatingElements = new ArrayList<> ();
				do {
					floatingElements.add ( element );
					compositeElements.removeFirst ();
					element = !compositeElements.isEmpty () ? compositeElements.getFirst () : null;
				} while ( element != null && element.type () == Element.DIV );

				FloatLayout fl = new FloatLayout ( floatingElements, useAscender );
				fl.setSimpleColumn ( leftX, minY, rightX, yLine );
				int status = fl.layout ( canvas, simulate );

				//firstPass = false;
				yLine = fl.getYLine ();
				descender = 0;
				if ( ( status & NO_MORE_TEXT ) == 0 ) {
					compositeElements.addAll ( floatingElements );
					return status;
				}
			} else
				compositeElements.removeFirst ();
		}
	}

	public PdfContentByte getCanvas () {
		return canvas;
	}

	public void setCanvas ( final PdfContentByte canvas ) {
		this.canvas = canvas;
		this.canvases = null;
		if ( compositeColumn != null )
			compositeColumn.setCanvas ( canvas );
	}

	public void setCanvases ( final PdfContentByte[] canvases ) {
		this.canvases = canvases;
		this.canvas = canvases[PdfPTable.TEXTCANVAS];
		if ( compositeColumn != null )
			compositeColumn.setCanvases ( canvases );
	}

	public boolean zeroHeightElement () {
		return composite && !compositeElements.isEmpty () && compositeElements.getFirst ().type () == Element.YMARK;
	}

	public List<Element> getCompositeElements () {
		return compositeElements;
	}

	public boolean isUseAscender () {
		return useAscender;
	}

	public void setUseAscender ( final boolean useAscender ) {
		this.useAscender = useAscender;
	}

	public float getFilledWidth () {
		return filledWidth;
	}

	public void setFilledWidth ( final float filledWidth ) {
		this.filledWidth = filledWidth;
	}

	public void updateFilledWidth ( final float w ) {
		if ( w > filledWidth )
			filledWidth = w;
	}

	public void setAdjustFirstLine ( final boolean adjustFirstLine ) {
		this.adjustFirstLine = adjustFirstLine;
	}

}

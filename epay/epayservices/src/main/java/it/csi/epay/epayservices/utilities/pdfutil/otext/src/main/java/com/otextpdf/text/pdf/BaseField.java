/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Rectangle;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;

import java.io.IOException;
import java.util.HashMap;


public abstract class BaseField {

	public static final float BORDER_WIDTH_THIN = 1;

	public static final float BORDER_WIDTH_MEDIUM = 2;

	public static final float BORDER_WIDTH_THICK = 3;

	public static final int VISIBLE = 0;

	public static final int HIDDEN = 1;

	public static final int VISIBLE_BUT_DOES_NOT_PRINT = 2;

	public static final int HIDDEN_BUT_PRINTABLE = 3;

	public static final int READ_ONLY = PdfFormField.FF_READ_ONLY;

	public static final int REQUIRED = PdfFormField.FF_REQUIRED;

	public static final int MULTILINE = PdfFormField.FF_MULTILINE;

	public static final int DO_NOT_SCROLL = PdfFormField.FF_DONOTSCROLL;

	public static final int PASSWORD = PdfFormField.FF_PASSWORD;

	public static final int FILE_SELECTION = PdfFormField.FF_FILESELECT;

	public static final int DO_NOT_SPELL_CHECK = PdfFormField.FF_DONOTSPELLCHECK;

	public static final int EDIT = PdfFormField.FF_EDIT;

	public static final int MULTISELECT = PdfFormField.FF_MULTISELECT;

	public static final int COMB = PdfFormField.FF_COMB;

	private final static HashMap<PdfName, Integer> fieldKeys = new HashMap<> ();

	static {
		fieldKeys.putAll ( PdfCopyFieldsImp.fieldKeys );
		fieldKeys.put ( PdfName.T, 1 );
	}

	protected float borderWidth = BORDER_WIDTH_THIN;

	protected int borderStyle = PdfBorderDictionary.STYLE_SOLID;

	protected BaseColor borderColor;

	protected BaseColor backgroundColor;

	protected BaseColor textColor;

	protected BaseFont font;

	protected float fontSize = 0;

	protected int alignment = Element.ALIGN_LEFT;

	protected PdfWriter writer;

	protected String text;

	protected Rectangle box;

	protected int rotation = 0;

	protected int visibility;

	protected String fieldName;

	protected int options;

	protected int maxCharacterLength;

	public BaseField ( PdfWriter writer, Rectangle box, String fieldName ) {
		this.writer = writer;
		setBox ( box );
		this.fieldName = fieldName;
	}

	protected BaseFont getRealFont () throws IOException, DocumentException {
		if ( font == null )
			return BaseFont.createFont ( BaseFont.HELVETICA, BaseFont.WINANSI, false );
		else
			return font;
	}

	protected PdfAppearance getBorderAppearance () {
		PdfAppearance app = PdfAppearance.createAppearance ( writer, box.getWidth (), box.getHeight () );
		switch ( rotation ) {
		case 90:
			app.setMatrix ( 0, 1, -1, 0, box.getHeight (), 0 );
			break;
		case 180:
			app.setMatrix ( -1, 0, 0, -1, box.getWidth (), box.getHeight () );
			break;
		case 270:
			app.setMatrix ( 0, -1, 1, 0, 0, box.getWidth () );
			break;
		}
		app.saveState ();
		// background
		if ( backgroundColor != null ) {
			app.setColorFill ( backgroundColor );
			app.rectangle ( 0, 0, box.getWidth (), box.getHeight () );
			app.fill ();
		}
		// border
		if ( borderStyle == PdfBorderDictionary.STYLE_UNDERLINE ) {
			if ( borderWidth != 0 && borderColor != null ) {
				app.setColorStroke ( borderColor );
				app.setLineWidth ( borderWidth );
				app.moveTo ( 0, borderWidth / 2 );
				app.lineTo ( box.getWidth (), borderWidth / 2 );
				app.stroke ();
			}
		} else if ( borderStyle == PdfBorderDictionary.STYLE_BEVELED ) {
			if ( borderWidth != 0 && borderColor != null ) {
				app.setColorStroke ( borderColor );
				app.setLineWidth ( borderWidth );
				app.rectangle ( borderWidth / 2, borderWidth / 2, box.getWidth () - borderWidth, box.getHeight () - borderWidth );
				app.stroke ();
			}
			// beveled
			BaseColor actual = backgroundColor;
			if ( actual == null )
				actual = BaseColor.WHITE;
			app.setGrayFill ( 1 );
			drawTopFrame ( app );
			app.setColorFill ( actual.darker () );
			drawBottomFrame ( app );
		} else if ( borderStyle == PdfBorderDictionary.STYLE_INSET ) {
			if ( borderWidth != 0 && borderColor != null ) {
				app.setColorStroke ( borderColor );
				app.setLineWidth ( borderWidth );
				app.rectangle ( borderWidth / 2, borderWidth / 2, box.getWidth () - borderWidth, box.getHeight () - borderWidth );
				app.stroke ();
			}
			// inset
			app.setGrayFill ( 0.5f );
			drawTopFrame ( app );
			app.setGrayFill ( 0.75f );
			drawBottomFrame ( app );
		} else {
			if ( borderWidth != 0 && borderColor != null ) {
				if ( borderStyle == PdfBorderDictionary.STYLE_DASHED )
					app.setLineDash ( 3, 0 );
				app.setColorStroke ( borderColor );
				app.setLineWidth ( borderWidth );
				app.rectangle ( borderWidth / 2, borderWidth / 2, box.getWidth () - borderWidth, box.getHeight () - borderWidth );
				app.stroke ();
				if ( ( options & COMB ) != 0 && maxCharacterLength > 1 ) {
					float step = box.getWidth () / maxCharacterLength;
					float yb = borderWidth / 2;
					float yt = box.getHeight () - borderWidth / 2;
					for ( int k = 1; k < maxCharacterLength; ++k ) {
						float x = step * k;
						app.moveTo ( x, yb );
						app.lineTo ( x, yt );
					}
					app.stroke ();
				}
			}
		}
		app.restoreState ();
		return app;
	}

	private void drawTopFrame ( PdfAppearance app ) {
		app.moveTo ( borderWidth, borderWidth );
		app.lineTo ( borderWidth, box.getHeight () - borderWidth );
		app.lineTo ( box.getWidth () - borderWidth, box.getHeight () - borderWidth );
		app.lineTo ( box.getWidth () - 2 * borderWidth, box.getHeight () - 2 * borderWidth );
		app.lineTo ( 2 * borderWidth, box.getHeight () - 2 * borderWidth );
		app.lineTo ( 2 * borderWidth, 2 * borderWidth );
		app.lineTo ( borderWidth, borderWidth );
		app.fill ();
	}

	private void drawBottomFrame ( PdfAppearance app ) {
		app.moveTo ( borderWidth, borderWidth );
		app.lineTo ( box.getWidth () - borderWidth, borderWidth );
		app.lineTo ( box.getWidth () - borderWidth, box.getHeight () - borderWidth );
		app.lineTo ( box.getWidth () - 2 * borderWidth, box.getHeight () - 2 * borderWidth );
		app.lineTo ( box.getWidth () - 2 * borderWidth, 2 * borderWidth );
		app.lineTo ( 2 * borderWidth, 2 * borderWidth );
		app.lineTo ( borderWidth, borderWidth );
		app.fill ();
	}

	public void setBorderWidth ( float borderWidth ) {
		this.borderWidth = borderWidth;
	}

	public void setBorderStyle ( int borderStyle ) {
		this.borderStyle = borderStyle;
	}

	public void setBorderColor ( BaseColor borderColor ) {
		this.borderColor = borderColor;
	}

	public BaseColor getBackgroundColor () {
		return this.backgroundColor;
	}

	public void setBackgroundColor ( BaseColor backgroundColor ) {
		this.backgroundColor = backgroundColor;
	}

	public void setTextColor ( BaseColor textColor ) {
		this.textColor = textColor;
	}

	public BaseFont getFont () {
		return this.font;
	}

	public void setFont ( BaseFont font ) {
		this.font = font;
	}

	public float getFontSize () {
		return this.fontSize;
	}

	public void setFontSize ( float fontSize ) {
		this.fontSize = fontSize;
	}

	public int getAlignment () {
		return this.alignment;
	}

	public void setAlignment ( int alignment ) {
		this.alignment = alignment;
	}

	public String getText () {
		return this.text;
	}

	public void setText ( String text ) {
		this.text = text;
	}

	public Rectangle getBox () {
		return this.box;
	}

	public void setBox ( Rectangle box ) {
		if ( box == null ) {
			this.box = null;
		} else {
			this.box = new Rectangle ( box );
			this.box.normalize ();
		}
	}

	public int getRotation () {
		return this.rotation;
	}

	public void setRotation ( int rotation ) {
		if ( rotation % 90 != 0 )
			throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "rotation.must.be.a.multiple.of.90" ) );
		rotation %= 360;
		if ( rotation < 0 )
			rotation += 360;
		this.rotation = rotation;
	}

	public int getVisibility () {
		return this.visibility;
	}

	public void setVisibility ( int visibility ) {
		this.visibility = visibility;
	}

	public int getOptions () {
		return this.options;
	}

	public void setOptions ( int options ) {
		this.options = options;
	}

	public void setMaxCharacterLength ( int maxCharacterLength ) {
		this.maxCharacterLength = maxCharacterLength;
	}

	public PdfWriter getWriter () {
		return writer;
	}

	public void setWriter ( PdfWriter writer ) {
		this.writer = writer;
	}

}

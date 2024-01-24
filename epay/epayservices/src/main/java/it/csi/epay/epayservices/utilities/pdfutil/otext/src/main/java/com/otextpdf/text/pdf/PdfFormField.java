/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Rectangle;

import java.util.ArrayList;


public class PdfFormField extends PdfAnnotation {

	public static final int FF_READ_ONLY = 1;

	public static final int FF_REQUIRED = 2;

	public static final int FF_NO_EXPORT = 4;

	public static final int FF_NO_TOGGLE_TO_OFF = 16384;

	public static final int FF_RADIO = 32768;

	public static final int FF_PUSHBUTTON = 65536;

	public static final int FF_MULTILINE = 4096;

	public static final int FF_PASSWORD = 8192;

	public static final int FF_COMBO = 131072;

	public static final int FF_EDIT = 262144;

	public static final int FF_FILESELECT = 1048576;

	public static final int FF_MULTISELECT = 2097152;

	public static final int FF_DONOTSPELLCHECK = 4194304;

	public static final int FF_DONOTSCROLL = 8388608;

	public static final int FF_COMB = 16777216;

	public static final int FF_RADIOSINUNISON = 1 << 25;

	public static final int FF_RICHTEXT = 1 << 25;

	public static final int Q_LEFT = 0;

	public static final int Q_CENTER = 1;

	public static final int Q_RIGHT = 2;

	public static final int MK_NO_ICON = 0;

	public static final int MK_NO_CAPTION = 1;

	public static final int MK_CAPTION_BELOW = 2;

	public static final int MK_CAPTION_ABOVE = 3;

	public static final int MK_CAPTION_RIGHT = 4;

	public static final int MK_CAPTION_LEFT = 5;

	public static final int MK_CAPTION_OVERLAID = 6;

	public static final PdfName IF_SCALE_ALWAYS = PdfName.A;

	public static final PdfName IF_SCALE_BIGGER = PdfName.B;

	public static final PdfName IF_SCALE_SMALLER = PdfName.S;

	public static final PdfName IF_SCALE_NEVER = PdfName.N;

	public static final PdfName IF_SCALE_ANAMORPHIC = PdfName.A;

	public static final PdfName IF_SCALE_PROPORTIONAL = PdfName.P;

	public static final boolean MULTILINE = true;

	public static final boolean SINGLELINE = false;

	public static final boolean PLAINTEXT = false;

	public static final boolean PASSWORD = true;

	static PdfName[] mergeTarget = { PdfName.FONT, PdfName.XOBJECT, PdfName.COLORSPACE, PdfName.PATTERN };

	protected PdfFormField parent;

	protected ArrayList<PdfFormField> kids;

	protected PdfFormField ( PdfWriter writer ) {
		super ( writer, null );
		form = true;
		annotation = false;
	}

	protected static PdfFormField createButton ( PdfWriter writer ) {
		PdfFormField field = new PdfFormField ( writer );
		field.setButton ( PdfFormField.FF_PUSHBUTTON );
		return field;
	}

	public static PdfFormField createPushButton ( PdfWriter writer ) {
		return createButton ( writer );
	}

	static void mergeResources ( PdfDictionary result, PdfDictionary source, PdfStamperImp writer ) {
		PdfDictionary dic;
		PdfDictionary res;
		PdfName target;
		for ( PdfName pdfName : mergeTarget ) {
			target = pdfName;
			PdfDictionary pdfDict = source.getAsDict ( target );
			if ( ( dic = pdfDict ) != null ) {
				if ( ( res = (PdfDictionary) PdfReader.getPdfObject ( result.get ( target ), result ) ) == null ) {
					res = new PdfDictionary ();
				}
				res.mergeDifferent ( dic );
				result.put ( target, res );
				if ( writer != null )
					writer.markUsed ( res );
			}
		}
	}

	static void mergeResources ( PdfDictionary result, PdfDictionary source ) {
		mergeResources ( result, source, null );
	}

	public static PdfAnnotation shallowDuplicate ( PdfAnnotation annot ) {
		PdfAnnotation dup;
		if ( annot.isForm () ) {
			dup = new PdfFormField ( annot.writer );
			PdfFormField dupField = (PdfFormField) dup;
			PdfFormField srcField = (PdfFormField) annot;
			dupField.parent = srcField.parent;
			dupField.kids = srcField.kids;
		} else
			dup = new PdfAnnotation ( annot.writer, null );
		dup.merge ( annot );
		dup.form = annot.form;
		dup.annotation = annot.annotation;
		dup.templates = annot.templates;
		return dup;
	}

	public void setWidget ( Rectangle rect, PdfName highlight ) {
		put ( PdfName.TYPE, PdfName.ANNOT );
		put ( PdfName.SUBTYPE, PdfName.WIDGET );
		put ( PdfName.RECT, new PdfRectangle ( rect ) );
		annotation = true;
		if ( highlight != null && !highlight.equals ( HIGHLIGHT_INVERT ) )
			put ( PdfName.H, highlight );
	}

	public void setButton ( int flags ) {
		put ( PdfName.FT, PdfName.BTN );
		if ( flags != 0 )
			put ( PdfName.FF, new PdfNumber ( flags ) );
	}

	public PdfFormField getParent () {
		return parent;
	}

	public ArrayList<PdfFormField> getKids () {
		return kids;
	}

	public void setFieldFlags ( int flags ) {
		PdfNumber obj = (PdfNumber) get ( PdfName.FF );
		int old;
		if ( obj == null )
			old = 0;
		else
			old = obj.intValue ();
		int v = old | flags;
		put ( PdfName.FF, new PdfNumber ( v ) );
	}

	public void setValue ( PdfSignature sig ) {
		put ( PdfName.V, sig );
	}

	public void setFieldName ( String s ) {
		if ( s != null )
			put ( PdfName.T, new PdfString ( s, PdfObject.TEXT_UNICODE ) );
	}

	@Override
	public void setUsed () {
		used = true;
		if ( parent != null )
			put ( PdfName.PARENT, parent.getIndirectReference () );
		if ( kids != null ) {
			PdfArray array = new PdfArray ();
			for ( PdfFormField kid : kids )
				array.add ( kid.getIndirectReference () );
			put ( PdfName.KIDS, array );
		}
		if ( templates == null )
			return;
		PdfDictionary dic = new PdfDictionary ();
		for ( PdfTemplate template : templates ) {
			mergeResources ( dic, (PdfDictionary) template.getResources () );
		}
		put ( PdfName.DR, dic );
	}
}

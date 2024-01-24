/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Annotation;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Rectangle;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfAcroForm;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfAction;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfAnnotation;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfArray;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfFileSpecification;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfFormField;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfRectangle;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfString;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfTemplate;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;


public class PdfAnnotationsImp {

	protected PdfAcroForm acroForm;

	protected ArrayList<PdfAnnotation> annotations;

	protected ArrayList<PdfAnnotation> delayedAnnotations = new ArrayList<> ();

	public PdfAnnotationsImp ( PdfWriter writer ) {
		acroForm = new PdfAcroForm ( writer );
	}

	public static PdfAnnotation convertAnnotation ( PdfWriter writer, Annotation annot, Rectangle defaultRect ) throws IOException {
		switch ( annot.annotationType () ) {
		case Annotation.URL_NET:
			return new PdfAnnotation ( writer, annot.llx (), annot.lly (), annot.urx (), annot.ury (),
							new PdfAction ( (URL) annot.attributes ().get ( Annotation.URL ) ) );
		case Annotation.URL_AS_STRING:
			return new PdfAnnotation ( writer, annot.llx (), annot.lly (), annot.urx (), annot.ury (),
							new PdfAction ( (String) annot.attributes ().get ( Annotation.FILE ) ) );
		case Annotation.FILE_DEST:
			return new PdfAnnotation ( writer, annot.llx (), annot.lly (), annot.urx (), annot.ury (),
							new PdfAction ( (String) annot.attributes ().get ( Annotation.FILE ),
											(String) annot.attributes ().get ( Annotation.DESTINATION ) ) );
		case Annotation.SCREEN:
			boolean[] sparams = (boolean[]) annot.attributes ().get ( Annotation.PARAMETERS );
			String fname = (String) annot.attributes ().get ( Annotation.FILE );
			String mimetype = (String) annot.attributes ().get ( Annotation.MIMETYPE );
			PdfFileSpecification fs;
			if ( sparams[0] )
				fs = PdfFileSpecification.fileEmbedded ( writer, fname, fname, null );
			else
				fs = PdfFileSpecification.fileExtern ( writer, fname );
			return PdfAnnotation.createScreen ( writer, new Rectangle ( annot.llx (), annot.lly (), annot.urx (), annot.ury () ),
							fname, fs, mimetype, sparams[1] );
		case Annotation.FILE_PAGE:
			return new PdfAnnotation ( writer, annot.llx (), annot.lly (), annot.urx (), annot.ury (),
							new PdfAction ( (String) annot.attributes ().get ( Annotation.FILE ),
											(Integer) annot.attributes ().get ( Annotation.PAGE ) ) );
		case Annotation.NAMED_DEST:
			return new PdfAnnotation ( writer, annot.llx (), annot.lly (), annot.urx (), annot.ury (),
							new PdfAction ( (Integer) annot.attributes ().get ( Annotation.NAMED ) ) );
		case Annotation.LAUNCH:
			return new PdfAnnotation ( writer, annot.llx (), annot.lly (), annot.urx (), annot.ury (),
							new PdfAction ( (String) annot.attributes ().get ( Annotation.APPLICATION ),
											(String) annot.attributes ().get ( Annotation.PARAMETERS ),
											(String) annot.attributes ().get ( Annotation.OPERATION ),
											(String) annot.attributes ().get ( Annotation.DEFAULTDIR ) ) );
		default:
			return new PdfAnnotation ( writer, defaultRect.getLeft (), defaultRect.getBottom (), defaultRect.getRight (), defaultRect.getTop (),
							new PdfString ( annot.title (), PdfObject.TEXT_UNICODE ), new PdfString ( annot.content (), PdfObject.TEXT_UNICODE ) );
		}
	}

	public boolean hasValidAcroForm () {
		return acroForm.isValid ();
	}

	public PdfAcroForm getAcroForm () {
		return acroForm;
	}

	public void addAnnotation ( PdfAnnotation annot ) {
		if ( annot.isForm () ) {
			PdfFormField field = (PdfFormField) annot;
			if ( field.getParent () == null )
				addFormFieldRaw ( field );
		} else
			annotations.add ( annot );
	}

	public void addPlainAnnotation ( PdfAnnotation annot ) {
		annotations.add ( annot );
	}

	void addFormFieldRaw ( PdfFormField field ) {
		annotations.add ( field );
		ArrayList<PdfFormField> kids = field.getKids ();
		if ( kids != null ) {
			for ( PdfFormField kid : kids )
				addFormFieldRaw ( kid );
		}
	}

	public boolean hasUnusedAnnotations () {
		return !annotations.isEmpty ();
	}

	public void resetAnnotations () {
		annotations = delayedAnnotations;
		delayedAnnotations = new ArrayList<> ();
	}

	public PdfArray rotateAnnotations ( PdfWriter writer, Rectangle pageSize ) {
		PdfArray array = new PdfArray ();
		int rotation = pageSize.getRotation () % 360;
		int currentPage = writer.getCurrentPageNumber ();
		for ( PdfAnnotation dic : annotations ) {
			int page = dic.getPlaceInPage ();
			if ( page > currentPage ) {
				delayedAnnotations.add ( dic );
				continue;
			}
			if ( dic.isForm () ) {
				if ( !dic.isUsed () ) {
					HashSet<PdfTemplate> templates = dic.getTemplates ();
					if ( templates != null )
						acroForm.addFieldTemplates ( templates );
				}
				PdfFormField field = (PdfFormField) dic;
				if ( field.getParent () == null )
					acroForm.addDocumentField ( field.getIndirectReference () );
			}
			if ( dic.isAnnotation () ) {
				array.add ( dic.getIndirectReference () );
				if ( !dic.isUsed () ) {
					PdfArray tmp = dic.getAsArray ( PdfName.RECT );
					PdfRectangle rect;
					if ( tmp.size () == 4 ) {
						rect = new PdfRectangle ( tmp.getAsNumber ( 0 ).floatValue (), tmp.getAsNumber ( 1 ).floatValue (), tmp.getAsNumber ( 2 ).floatValue (),
										tmp.getAsNumber ( 3 ).floatValue () );
					} else {
						rect = new PdfRectangle ( tmp.getAsNumber ( 0 ).floatValue (), tmp.getAsNumber ( 1 ).floatValue () );
					}
					switch ( rotation ) {
					case 90:
						dic.put ( PdfName.RECT, new PdfRectangle (
										pageSize.getTop () - rect.bottom (),
										rect.left (),
										pageSize.getTop () - rect.top (),
										rect.right () ) );
						break;
					case 180:
						dic.put ( PdfName.RECT, new PdfRectangle (
										pageSize.getRight () - rect.left (),
										pageSize.getTop () - rect.bottom (),
										pageSize.getRight () - rect.right (),
										pageSize.getTop () - rect.top () ) );
						break;
					case 270:
						dic.put ( PdfName.RECT, new PdfRectangle (
										rect.bottom (),
										pageSize.getRight () - rect.left (),
										rect.top (),
										pageSize.getRight () - rect.right () ) );
						break;
					}
				}
			}
			if ( !dic.isUsed () ) {
				dic.setUsed ();
				try {
					writer.addToBody ( dic, dic.getIndirectReference () );
				} catch ( IOException e ) {
					throw new ExceptionConverter ( e );
				}
			}
		}
		return array;
	}
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal.PdfIsoKeys;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PdfArray extends PdfObject implements Iterable<PdfObject> {

	protected ArrayList<PdfObject> arrayList;

	public PdfArray () {
		super ( ARRAY );
		arrayList = new ArrayList<> ();
	}

	public PdfArray ( final PdfObject object ) {
		super ( ARRAY );
		arrayList = new ArrayList<> ();
		arrayList.add ( object );
	}

	public PdfArray ( final float[] values ) {
		super ( ARRAY );
		arrayList = new ArrayList<> ();
		add ( values );
	}

	public PdfArray ( final int[] values ) {
		super ( ARRAY );
		arrayList = new ArrayList<> ();
		add ( values );
	}

	public PdfArray ( final List<PdfObject> l ) {
		this ();
		for ( PdfObject element : l )
			add ( element );
	}

	public PdfArray ( final PdfArray array ) {
		super ( ARRAY );
		arrayList = new ArrayList<> ( array.arrayList );
	}

	@Override
	public void toPdf ( final PdfWriter writer, final OutputStream os ) throws IOException {
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_OBJECT, this );
		os.write ( '[' );

		Iterator<PdfObject> i = arrayList.iterator ();
		PdfObject object;
		int type;
		if ( i.hasNext () ) {
			object = i.next ();
			if ( object == null )
				object = PdfNull.PDFNULL;
			object.toPdf ( writer, os );
		}
		while ( i.hasNext () ) {
			object = i.next ();
			if ( object == null )
				object = PdfNull.PDFNULL;
			type = object.type ();
			if ( type != PdfObject.ARRAY && type != PdfObject.DICTIONARY && type != PdfObject.NAME && type != PdfObject.STRING )
				os.write ( ' ' );
			object.toPdf ( writer, os );
		}
		os.write ( ']' );
	}

	@Override
	public String toString () {
		return arrayList.toString ();
	}

	public PdfObject set ( final int idx, final PdfObject obj ) {
		return arrayList.set ( idx, obj );
	}

	public PdfObject remove ( final int idx ) {
		return arrayList.remove ( idx );
	}

	@Deprecated
	public ArrayList<PdfObject> getArrayList () {
		return arrayList;
	}

	public int size () {
		return arrayList.size ();
	}

	public boolean isEmpty () {
		return arrayList.isEmpty ();
	}

	public boolean add ( final PdfObject object ) {
		return arrayList.add ( object );
	}

	public boolean add ( final float[] values ) {
		for ( float value : values )
			arrayList.add ( new PdfNumber ( value ) );
		return true;
	}

	public boolean add ( final int[] values ) {
		for ( int value : values )
			arrayList.add ( new PdfNumber ( value ) );
		return true;
	}

	public void add ( final int index, final PdfObject element ) {
		arrayList.add ( index, element );
	}

	public void addFirst ( final PdfObject object ) {
		arrayList.add ( 0, object );
	}

	public boolean contains ( final PdfObject object ) {
		return arrayList.contains ( object );
	}

	public PdfObject getPdfObject ( final int idx ) {
		return arrayList.get ( idx );
	}

	public PdfObject getDirectObject ( final int idx ) {
		return PdfReader.getPdfObject ( getPdfObject ( idx ) );
	}

	public PdfDictionary getAsDict ( final int idx ) {
		PdfDictionary dict = null;
		PdfObject orig = getDirectObject ( idx );
		if ( orig != null && orig.isDictionary () )
			dict = (PdfDictionary) orig;
		return dict;
	}

	public PdfArray getAsArray ( final int idx ) {
		PdfArray array = null;
		PdfObject orig = getDirectObject ( idx );
		if ( orig != null && orig.isArray () )
			array = (PdfArray) orig;
		return array;
	}

	public PdfString getAsString ( final int idx ) {
		PdfString string = null;
		PdfObject orig = getDirectObject ( idx );
		if ( orig != null && orig.isString () )
			string = (PdfString) orig;
		return string;
	}

	public PdfNumber getAsNumber ( final int idx ) {
		PdfNumber number = null;
		PdfObject orig = getDirectObject ( idx );
		if ( orig != null && orig.isNumber () )
			number = (PdfNumber) orig;
		return number;
	}

	public PdfName getAsName ( final int idx ) {
		PdfName name = null;
		PdfObject orig = getDirectObject ( idx );
		if ( orig != null && orig.isName () )
			name = (PdfName) orig;
		return name;
	}

	public PdfIndirectReference getAsIndirectObject ( final int idx ) {
		PdfIndirectReference ref = null;
		PdfObject orig = getPdfObject ( idx );
		if ( orig instanceof PdfIndirectReference )
			ref = (PdfIndirectReference) orig;
		return ref;
	}

	public Iterator<PdfObject> iterator () {
		return arrayList.iterator ();
	}

}

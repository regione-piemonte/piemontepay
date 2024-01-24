/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal.PdfIsoKeys;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;


public class PdfDictionary extends PdfObject {

	public static final PdfName FONT = PdfName.FONT;

	public static final PdfName OUTLINES = PdfName.OUTLINES;

	public static final PdfName PAGE = PdfName.PAGE;

	public static final PdfName PAGES = PdfName.PAGES;

	public static final PdfName CATALOG = PdfName.CATALOG;

	protected HashMap<PdfName, PdfObject> hashMap;

	private PdfName dictionaryType = null;

	public PdfDictionary () {
		super ( DICTIONARY );
		hashMap = new HashMap<> ();
	}

	public PdfDictionary ( final PdfName type ) {
		this ();
		dictionaryType = type;
		put ( PdfName.TYPE, dictionaryType );
	}

	// METHODS OVERRIDING SOME PDFOBJECT METHODS

	@Override
	public void toPdf ( final PdfWriter writer, final OutputStream os ) throws IOException {
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_OBJECT, this );
		os.write ( '<' );
		os.write ( '<' );
		// loop over all the object-pairs in the HashMap
		PdfObject value;
		int type;
		for ( Entry<PdfName, PdfObject> e : hashMap.entrySet () ) {
			e.getKey ().toPdf ( writer, os );
			value = e.getValue ();
			type = value.type ();
			if ( type != PdfObject.ARRAY && type != PdfObject.DICTIONARY && type != PdfObject.NAME && type != PdfObject.STRING )
				os.write ( ' ' );
			value.toPdf ( writer, os );
		}
		os.write ( '>' );
		os.write ( '>' );
	}

	@Override
	public String toString () {
		if ( get ( PdfName.TYPE ) == null )
			return "Dictionary";
		return "Dictionary of type: " + get ( PdfName.TYPE );
	}

	public void put ( final PdfName key, final PdfObject object ) {
		if ( object == null || object.isNull () )
			hashMap.remove ( key );
		else
			hashMap.put ( key, object );
	}

	public void putAll ( final PdfDictionary dic ) {
		hashMap.putAll ( dic.hashMap );
	}

	public void remove ( final PdfName key ) {
		hashMap.remove ( key );
	}

	public void clear () {
		hashMap.clear ();
	}

	public PdfObject get ( final PdfName key ) {
		return hashMap.get ( key );
	}

	public PdfObject getDirectObject ( final PdfName key ) {
		return PdfReader.getPdfObject ( get ( key ) );
	}

	public Set<PdfName> getKeys () {
		return hashMap.keySet ();
	}

	public int size () {
		return hashMap.size ();
	}

	public boolean contains ( final PdfName key ) {
		return hashMap.containsKey ( key );
	}

	// DICTIONARY TYPE METHODS

	public boolean isFont () {
		return checkType ( FONT );
	}

	public boolean isPage () {
		return checkType ( PAGE );
	}

	public boolean isPages () {
		return checkType ( PAGES );
	}

	public boolean isCatalog () {
		return checkType ( CATALOG );
	}

	public boolean checkType ( PdfName type ) {
		if ( type == null )
			return false;
		if ( dictionaryType == null )
			dictionaryType = getAsName ( PdfName.TYPE );
		return type.equals ( dictionaryType );
	}

	// OTHER METHODS

	public void merge ( final PdfDictionary other ) {
		hashMap.putAll ( other.hashMap );
	}

	public void mergeDifferent ( final PdfDictionary other ) {
		for ( PdfName key : other.hashMap.keySet () ) {
			if ( !hashMap.containsKey ( key ) )
				hashMap.put ( key, other.hashMap.get ( key ) );
		}
	}

	public PdfDictionary getAsDict ( final PdfName key ) {
		PdfDictionary dict = null;
		PdfObject orig = getDirectObject ( key );
		if ( orig != null && orig.isDictionary () )
			dict = (PdfDictionary) orig;
		return dict;
	}

	public PdfArray getAsArray ( final PdfName key ) {
		PdfArray array = null;
		PdfObject orig = getDirectObject ( key );
		if ( orig != null && orig.isArray () )
			array = (PdfArray) orig;
		return array;
	}

	public PdfStream getAsStream ( final PdfName key ) {
		PdfStream stream = null;
		PdfObject orig = getDirectObject ( key );
		if ( orig != null && orig.isStream () )
			stream = (PdfStream) orig;
		return stream;
	}

	public PdfString getAsString ( final PdfName key ) {
		PdfString string = null;
		PdfObject orig = getDirectObject ( key );
		if ( orig != null && orig.isString () )
			string = (PdfString) orig;
		return string;
	}

	public PdfNumber getAsNumber ( final PdfName key ) {
		PdfNumber number = null;
		PdfObject orig = getDirectObject ( key );
		if ( orig != null && orig.isNumber () )
			number = (PdfNumber) orig;
		return number;
	}

	public PdfName getAsName ( final PdfName key ) {
		PdfName name = null;
		PdfObject orig = getDirectObject ( key );
		if ( orig != null && orig.isName () )
			name = (PdfName) orig;
		return name;
	}

	public PdfBoolean getAsBoolean ( final PdfName key ) {
		PdfBoolean bool = null;
		PdfObject orig = getDirectObject ( key );
		if ( orig != null && orig.isBoolean () )
			bool = (PdfBoolean) orig;
		return bool;
	}

	public PdfIndirectReference getAsIndirectObject ( final PdfName key ) {
		PdfIndirectReference ref = null;
		PdfObject orig = get ( key );
		if ( orig != null && orig.isIndirect () )
			ref = (PdfIndirectReference) orig;
		return ref;
	}
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import java.util.HashMap;
import java.util.HashSet;


class PageResources {

	protected PdfDictionary fontDictionary = new PdfDictionary ();

	protected PdfDictionary xObjectDictionary = new PdfDictionary ();

	protected PdfDictionary colorDictionary = new PdfDictionary ();

	protected PdfDictionary patternDictionary = new PdfDictionary ();

	protected PdfDictionary shadingDictionary = new PdfDictionary ();

	protected PdfDictionary extGStateDictionary = new PdfDictionary ();

	protected PdfDictionary propertyDictionary = new PdfDictionary ();

	protected HashSet<PdfName> forbiddenNames;

	protected PdfDictionary originalResources;

	protected int[] namePtr = { 0 };

	protected HashMap<PdfName, PdfName> usedNames;

	PageResources () {
	}

	void setOriginalResources ( PdfDictionary resources, int[] newNamePtr ) {
		if ( newNamePtr != null )
			namePtr = newNamePtr;
		forbiddenNames = new HashSet<> ();
		usedNames = new HashMap<> ();
		if ( resources == null )
			return;
		originalResources = new PdfDictionary ();
		originalResources.merge ( resources );
		for ( PdfName element : resources.getKeys () ) {
			PdfObject sub = PdfReader.getPdfObject ( resources.get ( element ) );
			if ( sub != null && sub.isDictionary () ) {
				PdfDictionary dic = (PdfDictionary) sub;
				forbiddenNames.addAll ( dic.getKeys () );
				PdfDictionary dic2 = new PdfDictionary ();
				dic2.merge ( dic );
				originalResources.put ( element, dic2 );
			}
		}
	}

	PdfName translateName ( PdfName name ) {
		PdfName translated = name;
		if ( forbiddenNames != null ) {
			translated = usedNames.get ( name );
			if ( translated == null ) {
				do {
					translated = new PdfName ( "Xi" + namePtr[0]++ );
				} while ( forbiddenNames.contains ( translated ) );
				usedNames.put ( name, translated );
			}
		}
		return translated;
	}

	PdfName addFont ( PdfName name, PdfIndirectReference reference ) {
		name = translateName ( name );
		fontDictionary.put ( name, reference );
		return name;
	}

	PdfName addXObject ( PdfName name, PdfIndirectReference reference ) {
		name = translateName ( name );
		xObjectDictionary.put ( name, reference );
		return name;
	}

	PdfName addColor ( PdfName name, PdfIndirectReference reference ) {
		name = translateName ( name );
		colorDictionary.put ( name, reference );
		return name;
	}

	void addDefaultColor ( PdfDictionary dic ) {
		colorDictionary.merge ( dic );
	}

	void addDefaultColorDiff ( PdfDictionary dic ) {
		colorDictionary.mergeDifferent ( dic );
	}

	PdfName addPattern ( PdfName name, PdfIndirectReference reference ) {
		name = translateName ( name );
		patternDictionary.put ( name, reference );
		return name;
	}

	PdfName addExtGState ( PdfName name, PdfIndirectReference reference ) {
		name = translateName ( name );
		extGStateDictionary.put ( name, reference );
		return name;
	}

	PdfName addProperty ( PdfName name, PdfIndirectReference reference ) {
		name = translateName ( name );
		propertyDictionary.put ( name, reference );
		return name;
	}

	PdfDictionary getResources () {
		PdfResources resources = new PdfResources ();
		if ( originalResources != null )
			resources.putAll ( originalResources );
		resources.add ( PdfName.FONT, fontDictionary );
		resources.add ( PdfName.XOBJECT, xObjectDictionary );
		resources.add ( PdfName.COLORSPACE, colorDictionary );
		resources.add ( PdfName.PATTERN, patternDictionary );
		resources.add ( PdfName.SHADING, shadingDictionary );
		resources.add ( PdfName.EXTGSTATE, extGStateDictionary );
		resources.add ( PdfName.PROPERTIES, propertyDictionary );
		return resources;
	}

}

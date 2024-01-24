/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.IPdfStructureElement;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class PdfStructureTreeRoot extends PdfDictionary implements IPdfStructureElement {

	private final HashMap<Integer, PdfObject> parentTree = new HashMap<> ();

	private final PdfIndirectReference reference;

	private final PdfWriter writer;

	protected HashMap<PdfName, PdfObject> classes = null;

	private PdfDictionary classMap = null;

	private HashMap<Integer, PdfIndirectReference> numTree = null;

	PdfStructureTreeRoot ( PdfWriter writer ) {
		super ( PdfName.STRUCTTREEROOT );
		this.writer = writer;
		reference = writer.getPdfIndirectReference ();
	}

	private void createNumTree () throws IOException {
		if ( numTree != null )
			return;
		numTree = new HashMap<> ();
		for ( Integer i : parentTree.keySet () ) {
			PdfObject obj = parentTree.get ( i );
			if ( obj.isArray () ) {
				PdfArray ar = (PdfArray) obj;
				numTree.put ( i, writer.addToBody ( ar ).getIndirectReference () );
			} else if ( obj instanceof PdfIndirectReference ) {
				numTree.put ( i, (PdfIndirectReference) obj );
			}
		}
	}

	public void mapClass ( PdfName name, PdfObject object ) {
		if ( classMap == null ) {
			classMap = new PdfDictionary ();
			classes = new HashMap<> ();
		}
		classes.put ( name, object );
	}

	public PdfObject getMappedClass ( PdfName name ) {
		if ( classes == null )
			return null;
		return classes.get ( name );
	}

	public PdfWriter getWriter () {
		return this.writer;
	}

	public HashMap<Integer, PdfIndirectReference> getNumTree () throws IOException {
		if ( numTree == null )
			createNumTree ();
		return numTree;
	}

	public PdfIndirectReference getReference () {
		return this.reference;
	}

	void setPageMark ( int page, PdfIndirectReference struc ) {
		Integer i = page;
		PdfArray ar = (PdfArray) parentTree.get ( i );
		if ( ar == null ) {
			ar = new PdfArray ();
			parentTree.put ( i, ar );
		}
		ar.add ( struc );
	}

	void setAnnotationMark ( int structParentIndex, PdfIndirectReference struc ) {
		parentTree.put ( structParentIndex, struc );
	}

	private void nodeProcess ( PdfDictionary struc, PdfIndirectReference reference ) throws IOException {
		PdfObject obj = struc.get ( PdfName.K );
		if ( obj != null && obj.isArray () ) {
			PdfArray ar = (PdfArray) obj;
			for ( int k = 0; k < ar.size (); ++k ) {
				PdfDictionary dictionary = ar.getAsDict ( k );
				if ( dictionary == null )
					continue;
				if ( !PdfName.STRUCTELEM.equals ( dictionary.get ( PdfName.TYPE ) ) )
					continue;
				if ( ar.getPdfObject ( k ) instanceof PdfStructureElement ) {
					PdfStructureElement e = (PdfStructureElement) dictionary;
					ar.set ( k, e.getReference () );
					nodeProcess ( e, e.getReference () );
				}
			}
		}
		if ( reference != null )
			writer.addToBody ( struc, reference );
	}

	void buildTree () throws IOException {
		createNumTree ();
		PdfDictionary dicTree = PdfNumberTree.writeTree ( numTree, writer );
		if ( dicTree != null )
			put ( PdfName.PARENTTREE, writer.addToBody ( dicTree ).getIndirectReference () );
		if ( classMap != null && !classes.isEmpty () ) {
			for ( Map.Entry<PdfName, PdfObject> entry : classes.entrySet () ) {
				PdfObject value = entry.getValue ();
				if ( value.isDictionary () )
					classMap.put ( entry.getKey (), writer.addToBody ( value ).getIndirectReference () );
				else if ( value.isArray () ) {
					PdfArray newArray = new PdfArray ();
					PdfArray array = (PdfArray) value;
					for ( int i = 0; i < array.size (); ++i ) {
						if ( array.getPdfObject ( i ).isDictionary () )
							newArray.add ( writer.addToBody ( array.getAsDict ( i ) ).getIndirectReference () );
					}
					classMap.put ( entry.getKey (), newArray );
				}
			}
			put ( PdfName.CLASSMAP, writer.addToBody ( classMap ).getIndirectReference () );
		}
		nodeProcess ( this, reference );
	}

	public PdfObject getAttribute ( PdfName name ) {
		PdfDictionary attr = getAsDict ( PdfName.A );
		if ( attr != null ) {
			if ( attr.contains ( name ) )
				return attr.get ( name );
		}
		return null;
	}

	public void setAttribute ( PdfName name, PdfObject obj ) {
		PdfDictionary attr = getAsDict ( PdfName.A );
		if ( attr == null ) {
			attr = new PdfDictionary ();
			put ( PdfName.A, attr );
		}
		attr.put ( name, obj );
	}
}

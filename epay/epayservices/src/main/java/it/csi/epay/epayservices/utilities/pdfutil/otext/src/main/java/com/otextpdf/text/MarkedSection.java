/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.api.Indentable;

import java.util.Collection;


public class MarkedSection extends MarkedObject implements Indentable {

	protected MarkedObject title = null;

	public MarkedSection ( Section section ) {
		super ();
		if ( section.title != null ) {
			title = new MarkedObject ( section.title );
			section.setTitle ( null );
		}
		this.element = section;
	}

	public void add ( int index, Element o ) {
		( (Section) element ).add ( index, o );
	}

	public boolean add ( Element o ) {
		return ( (Section) element ).add ( o );
	}

	@Override
	public boolean process ( ElementListener listener ) {
		try {
			Element element;
			for ( Element value : (Section) this.element ) {
				element = value;
				listener.add ( element );
			}
			return true;
		} catch ( DocumentException de ) {
			return false;
		}
	}

	public boolean addAll ( Collection<? extends Element> collection ) {
		return ( (Section) element ).addAll ( collection );
	}

	public MarkedObject getTitle () {
		Paragraph result = Section.constructTitle ( (Paragraph) title.element, ( (Section) element ).numbers, ( (Section) element ).numberDepth,
						( (Section) element ).numberStyle );
		MarkedObject mo = new MarkedObject ( result );
		mo.markupAttributes = title.markupAttributes;
		return mo;
	}

	public void setTitle ( MarkedObject title ) {
		if ( title.element instanceof Paragraph )
			this.title = title;
	}

	public float getIndentationLeft () {
		return ( (Section) element ).getIndentationLeft ();
	}

	public void setIndentationLeft ( float indentation ) {
		( (Section) element ).setIndentationLeft ( indentation );
	}

	public float getIndentationRight () {
		return ( (Section) element ).getIndentationRight ();
	}

	public void setIndentationRight ( float indentation ) {
		( (Section) element ).setIndentationRight ( indentation );
	}
}

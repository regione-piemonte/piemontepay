/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.api.Indentable;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.factories.RomanAlphabetFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.IAccessibleElement;

import java.util.ArrayList;
import java.util.HashMap;


public class List implements TextElementArray, Indentable, IAccessibleElement {

	public static final boolean ORDERED = true;

	public static final boolean UNORDERED = false;

	public static final boolean NUMERICAL = false;

	public static final boolean ALPHABETICAL = true;

	public static final boolean UPPERCASE = false;

	public static final boolean LOWERCASE = true;

	protected ArrayList<Element> list = new ArrayList<> ();

	protected boolean numbered = false;

	protected boolean lettered = false;

	protected boolean lowercase = false;

	protected boolean autoindent = false;

	protected boolean alignindent = false;

	protected int first = 1;

	protected Chunk symbol = new Chunk ( "- " );

	protected String preSymbol = "";

	protected String postSymbol = ". ";

	protected float indentationLeft = 0;

	protected float indentationRight = 0;

	protected float symbolIndent = 0;

	protected PdfName role = PdfName.L;

	protected HashMap<PdfName, PdfObject> accessibleAttributes = null;

	private AccessibleElementId id = null;

	public List () {
		this ( false, false );
	}

	public List ( final float symbolIndent ) {
		this.symbolIndent = symbolIndent;
	}

	public List ( final boolean numbered ) {
		this ( numbered, false );
	}

	public List ( final boolean numbered, final boolean lettered ) {
		this.numbered = numbered;
		this.lettered = lettered;
		this.autoindent = true;
		this.alignindent = true;
	}

	public List ( final boolean numbered, final float symbolIndent ) {
		this ( numbered, false, symbolIndent );
	}

	public List ( final boolean numbered, final boolean lettered, final float symbolIndent ) {
		this.numbered = numbered;
		this.lettered = lettered;
		this.symbolIndent = symbolIndent;
	}

	public boolean process ( final ElementListener listener ) {
		try {
			for ( Element element : list ) {
				listener.add ( element );
			}
			return true;
		} catch ( DocumentException de ) {
			return false;
		}
	}

	public int type () {
		return Element.LIST;
	}

	public java.util.List<Chunk> getChunks () {
		java.util.List<Chunk> tmp = new ArrayList<> ();
		for ( Element element : list ) {
			tmp.addAll ( element.getChunks () );
		}
		return tmp;
	}

	public boolean add ( final String s ) {
		if ( s != null ) {
			return this.add ( new ListItem ( s ) );
		}
		return false;
	}

	public boolean add ( final Element o ) {
		if ( o instanceof ListItem ) {
			ListItem item = (ListItem) o;
			if ( numbered || lettered ) {
				Chunk chunk = new Chunk ( preSymbol, symbol.getFont () );
				chunk.setAttributes ( symbol.getAttributes () );
				int index = first + list.size ();
				if ( lettered )
					chunk.append ( RomanAlphabetFactory.getString ( index, lowercase ) );
				else
					chunk.append ( String.valueOf ( index ) );
				chunk.append ( postSymbol );
				item.setListSymbol ( chunk );
			} else {
				item.setListSymbol ( symbol );
			}
			item.setIndentationLeft ( symbolIndent, autoindent );
			item.setIndentationRight ( 0 );
			return list.add ( item );
		} else if ( o instanceof List ) {
			List nested = (List) o;
			nested.setIndentationLeft ( nested.getIndentationLeft () + symbolIndent );
			first--;
			return list.add ( nested );
		}
		return false;
	}

	public void normalizeIndentation () {
		float max = 0;
		for ( Element o : list ) {
			if ( o instanceof ListItem ) {
				max = Math.max ( max, ( (ListItem) o ).getIndentationLeft () );
			}
		}
		for ( Element o : list ) {
			if ( o instanceof ListItem ) {
				( (ListItem) o ).setIndentationLeft ( max );
			}
		}
	}

	public void setListSymbol ( final String symbol ) {
		this.symbol = new Chunk ( symbol );
	}

	public ArrayList<Element> getItems () {
		return list;
	}

	public int size () {
		return list.size ();
	}

	public boolean isEmpty () {
		return list.isEmpty ();
	}

	public boolean isNumbered () {
		return numbered;
	}

	public boolean isLettered () {
		return lettered;
	}

	public boolean isLowercase () {
		return lowercase;
	}

	public void setLowercase ( final boolean uppercase ) {
		this.lowercase = uppercase;
	}

	public boolean isAutoindent () {
		return autoindent;
	}

	public void setAutoindent ( final boolean autoindent ) {
		this.autoindent = autoindent;
	}

	public boolean isAlignindent () {
		return alignindent;
	}

	public int getFirst () {
		return first;
	}

	public void setFirst ( final int first ) {
		this.first = first;
	}

	public Chunk getSymbol () {
		return symbol;
	}

	public float getIndentationLeft () {
		return indentationLeft;
	}

	public void setIndentationLeft ( final float indentation ) {
		this.indentationLeft = indentation;
	}

	public float getIndentationRight () {
		return indentationRight;
	}

	public void setIndentationRight ( final float indentation ) {
		this.indentationRight = indentation;
	}

	public boolean isContent () {
		return true;
	}

	public boolean isNestable () {
		return true;
	}

	public ListItem getFirstItem () {
		Element lastElement = !list.isEmpty () ? list.get ( 0 ) : null;
		if ( lastElement != null ) {
			if ( lastElement instanceof ListItem ) {
				return (ListItem) lastElement;
			} else if ( lastElement instanceof List ) {
				return ( (List) lastElement ).getFirstItem ();
			}
		}
		return null;
	}

	public ListItem getLastItem () {
		Element lastElement = !list.isEmpty () ? list.get ( list.size () - 1 ) : null;
		if ( lastElement != null ) {
			if ( lastElement instanceof ListItem ) {
				return (ListItem) lastElement;
			} else if ( lastElement instanceof List ) {
				return ( (List) lastElement ).getLastItem ();
			}
		}
		return null;
	}

	public PdfObject getAccessibleAttribute ( final PdfName key ) {
		if ( accessibleAttributes != null )
			return accessibleAttributes.get ( key );
		else
			return null;
	}

	public void setAccessibleAttribute ( final PdfName key, final PdfObject value ) {
		if ( accessibleAttributes == null )
			accessibleAttributes = new HashMap<> ();
		accessibleAttributes.put ( key, value );
	}

	public HashMap<PdfName, PdfObject> getAccessibleAttributes () {
		return accessibleAttributes;
	}

	public PdfName getRole () {
		return role;
	}

	public void setRole ( final PdfName role ) {
		this.role = role;
	}

	public AccessibleElementId getId () {
		if ( id == null )
			id = new AccessibleElementId ();
		return id;
	}

	public void setId ( final AccessibleElementId id ) {
		this.id = id;
	}

	public boolean isInline () {
		return false;
	}
}

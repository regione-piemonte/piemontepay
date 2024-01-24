/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.api.Indentable;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.IAccessibleElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class Section extends ArrayList<Element> implements TextElementArray, LargeElement, Indentable, IAccessibleElement {

	public static final int NUMBERSTYLE_DOTTED = 0;

	public static final int NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT = 1;

	private static final long serialVersionUID = 3324172577544748043L;

	protected Paragraph title;

	protected String bookmarkTitle;

	protected int numberDepth;

	protected int numberStyle = NUMBERSTYLE_DOTTED;

	protected float indentationLeft;

	protected float indentationRight;

	protected float indentation;

	protected boolean bookmarkOpen = true;

	protected boolean triggerNewPage = false;

	protected int subsections = 0;

	protected ArrayList<Integer> numbers = null;

	protected boolean complete = true;

	protected boolean addedCompletely = false;

	protected boolean notAddedYet = true;

	protected Section () {
		title = new Paragraph ();
		numberDepth = 1;
		title.setRole ( new PdfName ( "H" + numberDepth ) );
	}

	protected Section ( final Paragraph title, final int numberDepth ) {
		this.numberDepth = numberDepth;
		this.title = title;
		if ( title != null )
			title.setRole ( new PdfName ( "H" + numberDepth ) );
	}

	public static Paragraph constructTitle ( final Paragraph title, final ArrayList<Integer> numbers, final int numberDepth, final int numberStyle ) {
		if ( title == null ) {
			return null;
		}

		int depth = Math.min ( numbers.size (), numberDepth );
		if ( depth < 1 ) {
			return title;
		}
		StringBuilder buf = new StringBuilder ( " " );
		for ( int i = 0; i < depth; i++ ) {
			buf.insert ( 0, "." );
			buf.insert ( 0, numbers.get ( i ).intValue () );
		}
		if ( numberStyle == NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT ) {
			buf.deleteCharAt ( buf.length () - 2 );
		}
		Paragraph result = new Paragraph ( title );

		result.add ( 0, new Chunk ( buf.toString (), title.getFont () ) );
		return result;
	}

	public boolean process ( final ElementListener listener ) {
		try {
			Element element;
			for ( Element element2 : this ) {
				element = element2;
				listener.add ( element );
			}
			return true;
		} catch ( DocumentException de ) {
			return false;
		}
	}

	public int type () {
		return Element.SECTION;
	}

	public boolean isSection () {
		return type () == Element.SECTION;
	}

	public List<Chunk> getChunks () {
		List<Chunk> tmp = new ArrayList<> ();
		for ( Element element : this ) {
			tmp.addAll ( element.getChunks () );
		}
		return tmp;
	}

	public boolean isContent () {
		return true;
	}

	public boolean isNestable () {
		return false;
	}

	@Override
	public void add ( final int index, final Element element ) {
		if ( isAddedCompletely () ) {
			throw new IllegalStateException ( MessageLocalization.getComposedMessage ( "this.largeelement.has.already.been.added.to.the.document" ) );
		}
		try {
			if ( element.isNestable () ) {
				super.add ( index, element );
			} else {
				throw new ClassCastException ( MessageLocalization.getComposedMessage ( "you.can.t.add.a.1.to.a.section", element.getClass ().getName () ) );
			}
		} catch ( ClassCastException cce ) {
			throw new ClassCastException ( MessageLocalization.getComposedMessage ( "insertion.of.illegal.element.1", cce.getMessage () ) );
		}
	}

	@Override
	public boolean add ( final Element element ) {
		if ( isAddedCompletely () ) {
			throw new IllegalStateException ( MessageLocalization.getComposedMessage ( "this.largeelement.has.already.been.added.to.the.document" ) );
		}
		try {
			if ( element.type () == Element.SECTION ) {
				Section section = (Section) element;
				section.setNumbers ( ++subsections, numbers );
				return super.add ( section );
			} else if ( element instanceof MarkedSection && ( (MarkedObject) element ).element.type () == Element.SECTION ) {
				MarkedSection mo = (MarkedSection) element;
				Section section = (Section) mo.element;
				section.setNumbers ( ++subsections, numbers );
				return super.add ( mo );
			} else if ( element.isNestable () ) {
				return super.add ( element );
			} else {
				throw new ClassCastException ( MessageLocalization.getComposedMessage ( "you.can.t.add.a.1.to.a.section", element.getClass ().getName () ) );
			}
		} catch ( ClassCastException cce ) {
			throw new ClassCastException ( MessageLocalization.getComposedMessage ( "insertion.of.illegal.element.1", cce.getMessage () ) );
		}
	}

	@Override
	public boolean addAll ( final Collection<? extends Element> collection ) {
		if ( collection.isEmpty () )
			return false;
		for ( Element element : collection ) {
			this.add ( element );
		}
		return true;
	}

	public Paragraph getTitle () {
		return constructTitle ( title, numbers, numberDepth, numberStyle );
	}

	public void setTitle ( final Paragraph title ) {
		this.title = title;
	}

	public float getIndentationLeft () {
		return indentationLeft;
	}

	public void setIndentationLeft ( final float indentation ) {
		indentationLeft = indentation;
	}

	public float getIndentationRight () {
		return indentationRight;
	}

	public void setIndentationRight ( final float indentation ) {
		indentationRight = indentation;
	}

	public float getIndentation () {
		return indentation;
	}

	public boolean isBookmarkOpen () {
		return bookmarkOpen;
	}

	public boolean isTriggerNewPage () {
		return triggerNewPage && notAddedYet;
	}

	public Paragraph getBookmarkTitle () {
		if ( bookmarkTitle == null )
			return getTitle ();
		else
			return new Paragraph ( bookmarkTitle );
	}

	public void setChapterNumber ( final int number ) {
		numbers.set ( numbers.size () - 1, number );
		Object s;
		for ( Element element : this ) {
			s = element;
			if ( s instanceof Section ) {
				( (Section) s ).setChapterNumber ( number );
			}
		}
	}

	public int getDepth () {
		return numbers.size ();
	}

	private void setNumbers ( final int number, final ArrayList<Integer> numbers ) {
		this.numbers = new ArrayList<> ();
		this.numbers.add ( number );
		this.numbers.addAll ( numbers );
	}

	public boolean isNotAddedYet () {
		return notAddedYet;
	}

	public void setNotAddedYet ( final boolean notAddedYet ) {
		this.notAddedYet = notAddedYet;
	}

	protected boolean isAddedCompletely () {
		return addedCompletely;
	}

	protected void setAddedCompletely () {
		this.addedCompletely = true;
	}

	public void flushContent () {
		setNotAddedYet ( false );
		title = null;
		Element element;
		for ( Iterator<Element> i = iterator (); i.hasNext (); ) {
			element = i.next ();
			if ( element instanceof Section ) {
				Section s = (Section) element;
				if ( !s.isComplete () && size () == 1 ) {
					s.flushContent ();
					return;
				} else {
					s.setAddedCompletely ();
				}
			}
			i.remove ();
		}
	}

	public boolean isComplete () {
		return complete;
	}

	public void setComplete ( final boolean complete ) {
		this.complete = complete;
	}

	public PdfObject getAccessibleAttribute ( final PdfName key ) {
		return title.getAccessibleAttribute ( key );
	}

	public void setAccessibleAttribute ( final PdfName key, final PdfObject value ) {
		title.setAccessibleAttribute ( key, value );
	}

	public HashMap<PdfName, PdfObject> getAccessibleAttributes () {
		return title.getAccessibleAttributes ();
	}

	public PdfName getRole () {
		return title.getRole ();
	}

	public void setRole ( final PdfName role ) {
		title.setRole ( role );
	}

	public AccessibleElementId getId () {
		return title.getId ();
	}

	public void setId ( final AccessibleElementId id ) {
		title.setId ( id );
	}

	public boolean isInline () {
		return false;
	}
}

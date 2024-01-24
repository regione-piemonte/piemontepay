/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.api.Indentable;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.api.Spaceable;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfPTable;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.IAccessibleElement;

import java.util.ArrayList;
import java.util.HashMap;


public class Paragraph extends Phrase implements Indentable, Spaceable, IAccessibleElement {

	private static final long serialVersionUID = 7852314969733375514L;

	protected int alignment = Element.ALIGN_UNDEFINED;

	protected float indentationLeft;

	protected float indentationRight;

	protected float spacingBefore;

	protected float spacingAfter;

	protected boolean keeptogether = false;

	protected PdfName role = PdfName.P;

	protected HashMap<PdfName, PdfObject> accessibleAttributes = null;

	private float firstLineIndent = 0;

	private float extraParagraphSpace = 0;

	private AccessibleElementId id = null;

	public Paragraph () {
		super ();
	}

	public Paragraph ( float leading ) {
		super ( leading );
	}

	public Paragraph ( Chunk chunk ) {
		super ( chunk );
	}

	public Paragraph ( float leading, Chunk chunk ) {
		super ( leading, chunk );
	}

	public Paragraph ( String string ) {
		super ( string );
	}

	public Paragraph ( String string, Font font ) {
		super ( string, font );
	}

	public Paragraph ( float leading, String string ) {
		super ( leading, string );
	}

	public Paragraph ( float leading, String string, Font font ) {
		super ( leading, string, font );
	}

	public Paragraph ( Phrase phrase ) {
		super ( phrase );
		if ( phrase instanceof Paragraph ) {
			Paragraph p = (Paragraph) phrase;
			setAlignment ( p.alignment );
			setIndentationLeft ( p.getIndentationLeft () );
			setIndentationRight ( p.getIndentationRight () );
			setFirstLineIndent ( p.getFirstLineIndent () );
			setSpacingAfter ( p.getSpacingAfter () );
			setSpacingBefore ( p.getSpacingBefore () );
			setExtraParagraphSpace ( p.getExtraParagraphSpace () );
			setRole ( p.role );
			id = p.getId ();
			if ( p.accessibleAttributes != null )
				accessibleAttributes = new HashMap<> ( p.accessibleAttributes );
		}
	}

	public Paragraph cloneShallow ( boolean spacingBefore ) {
		Paragraph copy = new Paragraph ();
		copy.setFont ( getFont () );
		copy.setAlignment ( getAlignment () );
		copy.setLeading ( getLeading (), multipliedLeading );
		copy.setIndentationLeft ( getIndentationLeft () );
		copy.setIndentationRight ( getIndentationRight () );
		copy.setFirstLineIndent ( getFirstLineIndent () );
		copy.setSpacingAfter ( getSpacingAfter () );
		if ( spacingBefore )
			copy.setSpacingBefore ( getSpacingBefore () );
		copy.setExtraParagraphSpace ( getExtraParagraphSpace () );
		copy.setRole ( role );
		copy.id = getId ();
		if ( accessibleAttributes != null )
			copy.accessibleAttributes = new HashMap<> ( accessibleAttributes );
		copy.setTabSettings ( getTabSettings () );
		copy.setKeepTogether ( getKeepTogether () );
		return copy;
	}

	public java.util.List<Element> breakUp () {
		java.util.List<Element> list = new ArrayList<> ();
		Paragraph tmp = null;
		for ( Element e : this ) {
			if ( e.type () == Element.LIST || e.type () == Element.PTABLE || e.type () == Element.PARAGRAPH ) {
				if ( tmp != null && tmp.size () > 0 ) {
					tmp.setSpacingAfter ( 0 );
					list.add ( tmp );
					tmp = cloneShallow ( false );
				}
				if ( list.isEmpty () ) {
					switch ( e.type () ) {
					case Element.PTABLE:
						( (PdfPTable) e ).setSpacingBefore ( getSpacingBefore () );
						break;
					case Element.PARAGRAPH:
						( (Paragraph) e ).setSpacingBefore ( getSpacingBefore () );
						break;
					case Element.LIST:
						ListItem firstItem = ( (List) e ).getFirstItem ();
						if ( firstItem != null ) {
							firstItem.setSpacingBefore ( getSpacingBefore () );
						}
						break;
					default:
						break;
					}
				}
				list.add ( e );
			} else {
				if ( tmp == null ) {
					tmp = cloneShallow ( list.isEmpty () );
				}
				tmp.add ( e );
			}
		}
		if ( tmp != null && tmp.size () > 0 ) {
			list.add ( tmp );
		}
		if ( !list.isEmpty () ) {
			Element lastElement = list.get ( list.size () - 1 );
			switch ( lastElement.type () ) {
			case Element.PTABLE:
				( (PdfPTable) lastElement ).setSpacingAfter ( getSpacingAfter () );
				break;
			case Element.PARAGRAPH:
				( (Paragraph) lastElement ).setSpacingAfter ( getSpacingAfter () );
				break;
			case Element.LIST:
				ListItem lastItem = ( (List) lastElement ).getLastItem ();
				if ( lastItem != null ) {
					lastItem.setSpacingAfter ( getSpacingAfter () );
				}
				break;
			default:
				break;
			}
		}
		return list;
	}

	@Override
	public int type () {
		return Element.PARAGRAPH;
	}

	@Override
	public boolean add ( Element o ) {
		if ( o instanceof List ) {
			List list = (List) o;
			list.setIndentationLeft ( list.getIndentationLeft () + indentationLeft );
			list.setIndentationRight ( indentationRight );
			return super.add ( list );
		} else if ( o instanceof Image ) {
			super.addSpecial ( o );
			return true;
		} else if ( o instanceof Paragraph ) {
			super.addSpecial ( o );
			return true;
		}
		return super.add ( o );
	}

	public boolean getKeepTogether () {
		return keeptogether;
	}

	public void setKeepTogether ( boolean keeptogether ) {
		this.keeptogether = keeptogether;
	}

	public int getAlignment () {
		return alignment;
	}

	public void setAlignment ( int alignment ) {
		this.alignment = alignment;
	}

	public float getIndentationLeft () {
		return indentationLeft;
	}

	public void setIndentationLeft ( float indentation ) {
		this.indentationLeft = indentation;
	}

	public float getIndentationRight () {
		return indentationRight;
	}

	public void setIndentationRight ( float indentation ) {
		this.indentationRight = indentation;
	}

	public float getFirstLineIndent () {
		return this.firstLineIndent;
	}

	public void setFirstLineIndent ( float firstLineIndent ) {
		this.firstLineIndent = firstLineIndent;
	}

	public float getSpacingBefore () {
		return spacingBefore;
	}

	public void setSpacingBefore ( float spacing ) {
		this.spacingBefore = spacing;
	}

	public float getSpacingAfter () {
		return spacingAfter;
	}

	public void setSpacingAfter ( float spacing ) {
		this.spacingAfter = spacing;
	}

	public float getExtraParagraphSpace () {
		return this.extraParagraphSpace;
	}

	public void setExtraParagraphSpace ( float extraParagraphSpace ) {
		this.extraParagraphSpace = extraParagraphSpace;
	}

	@Deprecated
	public float spacingBefore () {
		return getSpacingBefore ();
	}

	@Deprecated
	public float spacingAfter () {
		return spacingAfter;
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

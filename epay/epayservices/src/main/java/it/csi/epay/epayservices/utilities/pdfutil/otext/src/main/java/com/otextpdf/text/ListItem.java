/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;


public class ListItem extends Paragraph {

	private static final long serialVersionUID = 1970670787169329006L;

	protected Chunk symbol;

	private ListBody listBody = null;

	private ListLabel listLabel = null;

	public ListItem () {
		super ();
		setRole ( PdfName.LI );
	}

	public ListItem ( final float leading ) {
		super ( leading );
		setRole ( PdfName.LI );
	}

	public ListItem ( final Chunk chunk ) {
		super ( chunk );
		setRole ( PdfName.LI );
	}

	public ListItem ( final String string ) {
		super ( string );
		setRole ( PdfName.LI );
	}

	public ListItem ( final String string, final Font font ) {
		super ( string, font );
		setRole ( PdfName.LI );
	}

	public ListItem ( final float leading, final Chunk chunk ) {
		super ( leading, chunk );
		setRole ( PdfName.LI );
	}

	public ListItem ( final float leading, final String string ) {
		super ( leading, string );
		setRole ( PdfName.LI );
	}

	public ListItem ( final float leading, final String string, final Font font ) {
		super ( leading, string, font );
		setRole ( PdfName.LI );
	}

	public ListItem ( final Phrase phrase ) {
		super ( phrase );
		setRole ( PdfName.LI );
	}

	@Override
	public int type () {
		return Element.LISTITEM;
	}

	public void setIndentationLeft ( final float indentation, final boolean autoindent ) {
		if ( autoindent ) {
			setIndentationLeft ( getListSymbol ().getWidthPoint () );
		} else {
			setIndentationLeft ( indentation );
		}
	}

	public void adjustListSymbolFont () {
		java.util.List<Chunk> cks = getChunks ();
		if ( !cks.isEmpty () && symbol != null )
			symbol.setFont ( cks.get ( 0 ).getFont () );
	}

	public Chunk getListSymbol () {
		return symbol;
	}

	public void setListSymbol ( final Chunk symbol ) {
		if ( this.symbol == null ) {
			this.symbol = symbol;
			if ( this.symbol.getFont ().isStandardFont () ) {
				this.symbol.setFont ( font );
			}
		}
	}

	public ListBody getListBody () {
		if ( listBody == null )
			listBody = new ListBody ( this );
		return listBody;
	}

	public ListLabel getListLabel () {
		if ( listLabel == null )
			listLabel = new ListLabel ( this );
		return listLabel;
	}

}

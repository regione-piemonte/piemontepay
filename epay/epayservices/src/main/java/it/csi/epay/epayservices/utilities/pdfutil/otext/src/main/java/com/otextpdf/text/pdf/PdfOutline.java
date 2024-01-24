/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Chunk;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Font;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Paragraph;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


public class PdfOutline extends PdfDictionary {

	protected ArrayList<PdfOutline> kids = new ArrayList<> ();

	protected PdfWriter writer;

	private PdfIndirectReference reference;

	private int count = 0;

	private PdfOutline parent;

	private PdfDestination destination;

	private PdfAction action;

	private String tag;

	private boolean open;

	private BaseColor color;

	private int style = 0;

	PdfOutline ( PdfWriter writer ) {
		super ( OUTLINES );
		open = true;
		parent = null;
		this.writer = writer;
	}

	public PdfOutline ( PdfOutline parent, PdfDestination destination, Paragraph title, boolean open ) {
		super ();
		StringBuilder buf = new StringBuilder ();
		for ( Chunk element : title.getChunks () ) {
			buf.append ( element.getContent () );
		}
		this.destination = destination;
		initOutline ( parent, buf.toString (), open );
	}

	void initOutline ( PdfOutline parent, String title, boolean open ) {
		this.open = open;
		this.parent = parent;
		writer = parent.writer;
		put ( PdfName.TITLE, new PdfString ( title, PdfObject.TEXT_UNICODE ) );
		parent.addKid ( this );
		if ( destination != null && !destination.hasPage () )
			setDestinationPage ( writer.getCurrentPage () );
	}

	public void setIndirectReference ( PdfIndirectReference reference ) {
		this.reference = reference;
	}

	public PdfIndirectReference indirectReference () {
		return reference;
	}

	public PdfOutline parent () {
		return parent;
	}

	public void setDestinationPage ( PdfIndirectReference pageReference ) {
		if ( destination == null ) {
			return;
		}
		destination.addPage ( pageReference );
	}

	int getCount () {
		return count;
	}

	void setCount ( int count ) {
		this.count = count;
	}

	public int level () {
		if ( parent == null ) {
			return 0;
		}
		return parent.level () + 1;
	}

	@Override
	public void toPdf ( PdfWriter writer, OutputStream os ) throws IOException {
		if ( color != null && !color.equals ( BaseColor.BLACK ) ) {
			put ( PdfName.C, new PdfArray ( new float[] { color.getRed () / 255f, color.getGreen () / 255f, color.getBlue () / 255f } ) );
		}
		int flag = 0;
		if ( ( style & Font.BOLD ) != 0 )
			flag |= 2;
		if ( ( style & Font.ITALIC ) != 0 )
			flag |= 1;
		if ( flag != 0 )
			put ( PdfName.F, new PdfNumber ( flag ) );
		if ( parent != null ) {
			put ( PdfName.PARENT, parent.indirectReference () );
		}
		if ( destination != null && destination.hasPage () ) {
			put ( PdfName.DEST, destination );
		}
		if ( action != null )
			put ( PdfName.A, action );
		if ( count != 0 ) {
			put ( PdfName.COUNT, new PdfNumber ( count ) );
		}
		super.toPdf ( writer, os );
	}

	public void addKid ( PdfOutline outline ) {
		kids.add ( outline );
	}

	public ArrayList<PdfOutline> getKids () {
		return kids;
	}

	public void setKids ( ArrayList<PdfOutline> kids ) {
		this.kids = kids;
	}

	public String getTag () {
		return tag;
	}

	public void setTag ( String tag ) {
		this.tag = tag;
	}

	public String getTitle () {
		PdfString title = (PdfString) get ( PdfName.TITLE );
		return title.toString ();
	}

	public void setTitle ( String title ) {
		put ( PdfName.TITLE, new PdfString ( title, PdfObject.TEXT_UNICODE ) );
	}

	public boolean isOpen () {
		return open;
	}

	public void setOpen ( boolean open ) {
		this.open = open;
	}

	public BaseColor getColor () {
		return this.color;
	}

	public void setColor ( BaseColor color ) {
		this.color = color;
	}

	public int getStyle () {
		return this.style;
	}

	public void setStyle ( int style ) {
		this.style = style;
	}

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.html.simpleparser;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Chunk;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ElementListener;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.html.HtmlTags;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.html.HtmlUtilities;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfPCell;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfPTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TableWrapper implements Element {

	private final Map<String, String> styles = new HashMap<> ();

	private final List<List<PdfPCell>> rows = new ArrayList<> ();

	private float[] colWidths;

	public TableWrapper ( final Map<String, String> attrs ) {
		this.styles.putAll ( attrs );
	}

	public void addRow ( List<PdfPCell> row ) {
		if ( row != null ) {
			Collections.reverse ( row );
			rows.add ( row );
		}
	}

	public void setColWidths ( final float[] colWidths ) {
		this.colWidths = colWidths;
	}

	public PdfPTable createTable () {
		// no rows = simplest table possible
		if ( rows.isEmpty () )
			return new PdfPTable ( 1 );
		// how many columns?
		int ncol = 0;
		for ( PdfPCell pc : rows.get ( 0 ) ) {
			ncol += pc.getColspan ();
		}
		PdfPTable table = new PdfPTable ( ncol );
		// table width
		String width = styles.get ( HtmlTags.WIDTH );
		if ( width == null )
			table.setWidthPercentage ( 100 );
		else {
			if ( width.endsWith ( "%" ) )
				table.setWidthPercentage ( Float.parseFloat ( width.substring ( 0, width.length () - 1 ) ) );
			else {
				table.setTotalWidth ( Float.parseFloat ( width ) );
				table.setLockedWidth ( true );
			}
		}
		// horizontal alignment
		String alignment = styles.get ( HtmlTags.ALIGN );
		int align = Element.ALIGN_LEFT;
		if ( alignment != null ) {
			align = HtmlUtilities.alignmentValue ( alignment );
		}
		table.setHorizontalAlignment ( align );
		// column widths
		try {
			if ( colWidths != null )
				table.setWidths ( colWidths );
		} catch ( Exception e ) {
			// fail silently
		}
		// add the cells
		for ( List<PdfPCell> col : rows ) {
			for ( PdfPCell pc : col ) {
				table.addCell ( pc );
			}
		}
		return table;
	}

	public List<Chunk> getChunks () {
		return null;
	}

	public boolean isContent () {
		return false;
	}

	public boolean isNestable () {
		return false;
	}

	public boolean process ( final ElementListener listener ) {
		return false;
	}

	public int type () {
		return 0;
	}

}

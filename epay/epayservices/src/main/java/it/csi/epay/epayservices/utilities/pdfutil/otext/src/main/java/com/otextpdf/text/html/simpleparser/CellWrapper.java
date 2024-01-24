/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.html.simpleparser;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Chunk;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ElementListener;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Phrase;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.TextElementArray;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.html.HtmlTags;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.html.HtmlUtilities;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfPCell;

import java.util.List;


public class CellWrapper implements TextElementArray {

	private final PdfPCell cell;

	private float width;

	private boolean percentage;

	public CellWrapper ( final String tag, final ChainedProperties chain ) {
		this.cell = createPdfPCell ( tag, chain );
		String value = chain.getProperty ( HtmlTags.WIDTH );
		if ( value != null ) {
			value = value.trim ();
			if ( value.endsWith ( "%" ) ) {
				percentage = true;
				value = value.substring ( 0, value.length () - 1 );
			}
			width = Float.parseFloat ( value );
		}
	}

	public PdfPCell createPdfPCell ( final String tag, final ChainedProperties chain ) {
		PdfPCell cell = new PdfPCell ( (Phrase) null );
		// colspan
		String value = chain.getProperty ( HtmlTags.COLSPAN );
		if ( value != null )
			cell.setColspan ( Integer.parseInt ( value ) );
		// rowspan
		value = chain.getProperty ( HtmlTags.ROWSPAN );
		if ( value != null )
			cell.setRowspan ( Integer.parseInt ( value ) );
		// horizontal alignment
		if ( tag.equals ( HtmlTags.TH ) )
			cell.setHorizontalAlignment ( Element.ALIGN_CENTER );
		value = chain.getProperty ( HtmlTags.ALIGN );
		if ( value != null ) {
			cell.setHorizontalAlignment ( HtmlUtilities.alignmentValue ( value ) );
		}
		// vertical alignment
		value = chain.getProperty ( HtmlTags.VALIGN );
		cell.setVerticalAlignment ( Element.ALIGN_MIDDLE );
		if ( value != null ) {
			cell.setVerticalAlignment ( HtmlUtilities.alignmentValue ( value ) );
		}
		// border
		value = chain.getProperty ( HtmlTags.BORDER );
		float border = 0;
		if ( value != null )
			border = Float.parseFloat ( value );
		cell.setBorderWidth ( border );
		// cellpadding
		value = chain.getProperty ( HtmlTags.CELLPADDING );
		if ( value != null )
			cell.setPadding ( Float.parseFloat ( value ) );
		cell.setUseDescender ( true );
		// background color
		value = chain.getProperty ( HtmlTags.BGCOLOR );
		cell.setBackgroundColor ( HtmlUtilities.decodeColor ( value ) );
		return cell;
	}

	public PdfPCell getCell () {
		return cell;
	}

	public float getWidth () {
		return width;
	}

	public boolean isPercentage () {
		return percentage;
	}

	public boolean add ( final Element o ) {
		cell.addElement ( o );
		return true;
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

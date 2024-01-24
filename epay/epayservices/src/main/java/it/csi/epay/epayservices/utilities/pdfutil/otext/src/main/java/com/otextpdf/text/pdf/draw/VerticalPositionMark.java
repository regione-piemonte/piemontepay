/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.draw;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Chunk;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ElementListener;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfContentByte;

import java.util.ArrayList;
import java.util.List;


public class VerticalPositionMark implements DrawInterface, Element {

	protected DrawInterface drawInterface = null;

	protected float offset = 0;

	public VerticalPositionMark () {
	}

	public void draw ( final PdfContentByte canvas, final float llx, final float lly, final float urx, final float ury, final float y ) {
		if ( drawInterface != null ) {
			drawInterface.draw ( canvas, llx, lly, urx, ury, y + offset );
		}
	}

	public boolean process ( final ElementListener listener ) {
		try {
			return listener.add ( this );
		} catch ( DocumentException e ) {
			return false;
		}
	}

	public int type () {
		return Element.YMARK;
	}

	public boolean isContent () {
		return true;
	}

	public boolean isNestable () {
		return false;
	}

	public List<Chunk> getChunks () {
		List<Chunk> list = new ArrayList<> ();
		list.add ( new Chunk ( this, true ) );
		return list;
	}

	public float getOffset () {
		return offset;
	}

	public void setOffset ( final float offset ) {
		this.offset = offset;
	}
}

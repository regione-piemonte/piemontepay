/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt.geom.AffineTransform;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Rectangle;


public class PdfRectangle extends NumberArray {

	private final float llx;

	private final float lly;

	private final float urx;

	private final float ury;

	public PdfRectangle ( float llx, float lly, float urx, float ury, int rotation ) {
		super ();
		if ( rotation == 90 || rotation == 270 ) {
			this.llx = lly;
			this.lly = llx;
			this.urx = ury;
			this.ury = urx;
		} else {
			this.llx = llx;
			this.lly = lly;
			this.urx = urx;
			this.ury = ury;
		}
		super.add ( new PdfNumber ( this.llx ) );
		super.add ( new PdfNumber ( this.lly ) );
		super.add ( new PdfNumber ( this.urx ) );
		super.add ( new PdfNumber ( this.ury ) );
	}

	public PdfRectangle ( float llx, float lly, float urx, float ury ) {
		this ( llx, lly, urx, ury, 0 );
	}

	public PdfRectangle ( float urx, float ury, int rotation ) {
		this ( 0, 0, urx, ury, rotation );
	}

	public PdfRectangle ( float urx, float ury ) {
		this ( 0, 0, urx, ury, 0 );
	}

	public PdfRectangle ( Rectangle rectangle, int rotation ) {
		this ( rectangle.getLeft (), rectangle.getBottom (), rectangle.getRight (), rectangle.getTop (), rotation );
	}

	public PdfRectangle ( Rectangle rectangle ) {
		this ( rectangle.getLeft (), rectangle.getBottom (), rectangle.getRight (), rectangle.getTop (), 0 );
	}

	public Rectangle getRectangle () {
		return new Rectangle ( left (), bottom (), right (), top () );
	}

	public boolean add ( PdfObject object ) {
		return false;
	}

	public boolean add ( float[] values ) {
		return false;
	}

	public boolean add ( int[] values ) {
		return false;
	}

	public void addFirst ( PdfObject object ) {
	}

	public float left () {
		return llx;
	}

	public float right () {
		return urx;
	}

	public float top () {
		return ury;
	}

	public float bottom () {
		return lly;
	}

	public float left ( int margin ) {
		return llx + margin;
	}

	public float right ( int margin ) {
		return urx - margin;
	}

	public float top ( int margin ) {
		return ury - margin;
	}

	public float bottom ( int margin ) {
		return lly + margin;
	}

	public float width () {
		return urx - llx;
	}

	public float height () {
		return ury - lly;
	}

	public PdfRectangle rotate () {
		return new PdfRectangle ( lly, llx, ury, urx, 0 );
	}

	public PdfRectangle transform ( AffineTransform transform ) {
		float[] pts = { llx, lly, urx, ury };
		transform.transform ( pts, 0, pts, 0, 2 );
		float[] dstPts = { pts[0], pts[1], pts[2], pts[3] };
		if ( pts[0] > pts[2] ) {
			dstPts[0] = pts[2];
			dstPts[2] = pts[0];
		}
		if ( pts[1] > pts[3] ) {
			dstPts[1] = pts[3];
			dstPts[3] = pts[1];
		}
		return new PdfRectangle ( dstPts[0], dstPts[1], dstPts[2], dstPts[3] );
	}
}

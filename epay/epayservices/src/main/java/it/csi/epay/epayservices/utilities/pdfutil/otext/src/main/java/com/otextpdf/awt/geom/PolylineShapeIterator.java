/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt.geom;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;

import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.util.NoSuchElementException;


public class PolylineShapeIterator implements PathIterator {

	protected PolylineShape poly;

	protected AffineTransform affine;

	protected int index;

	PolylineShapeIterator ( PolylineShape l, AffineTransform at ) {
		this.poly = l;
		this.affine = at;
	}

	public int currentSegment ( double[] coords ) {
		if ( isDone () ) {
			throw new NoSuchElementException ( MessageLocalization.getComposedMessage ( "line.iterator.out.of.bounds" ) );
		}
		int type = ( index == 0 ) ? SEG_MOVETO : SEG_LINETO;
		coords[0] = poly.x[index];
		coords[1] = poly.y[index];
		if ( affine != null ) {
			affine.transform ( coords, 0, coords, 0, 1 );
		}
		return type;
	}

	public int currentSegment ( float[] coords ) {
		if ( isDone () ) {
			throw new NoSuchElementException ( MessageLocalization.getComposedMessage ( "line.iterator.out.of.bounds" ) );
		}
		int type = ( index == 0 ) ? SEG_MOVETO : SEG_LINETO;
		coords[0] = poly.x[index];
		coords[1] = poly.y[index];
		if ( affine != null ) {
			affine.transform ( coords, 0, coords, 0, 1 );
		}
		return type;
	}

	public int getWindingRule () {
		return WIND_NON_ZERO;
	}

	public boolean isDone () {
		return ( index >= poly.np );
	}

	public void next () {
		index++;
	}

}

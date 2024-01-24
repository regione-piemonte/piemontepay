/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt.geom;

import java.io.Serializable;


public class Point extends Point2D implements Serializable {

	private static final long serialVersionUID = -5276940640259749850L;

	public double x;

	public double y;

	public Point () {
		setLocation ( 0, 0 );
	}

	public Point ( int x, int y ) {
		setLocation ( x, y );
	}

	public Point ( double x, double y ) {
		setLocation ( x, y );
	}

	public Point ( Point p ) {
		setLocation ( p.x, p.y );
	}

	@Override
	public boolean equals ( Object obj ) {
		if ( obj == this ) {
			return true;
		}
		if ( obj instanceof Point ) {
			Point p = (Point) obj;
			return x == p.x && y == p.y;
		}
		return false;
	}

	@Override
	public String toString () {
		return getClass ().getName () + "[x=" + x + ",y=" + y + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	@Override
	public double getX () {
		return x;
	}

	@Override
	public double getY () {
		return y;
	}

	public Point getLocation () {
		return new Point ( x, y );
	}

	public void setLocation ( Point p ) {
		setLocation ( p.x, p.y );
	}

	public void setLocation ( int x, int y ) {
		setLocation ( x, (double) y );
	}

	@Override
	public void setLocation ( double x, double y ) {
		this.x = x;
		this.y = y;
	}

	public void move ( int x, int y ) {
		move ( x, (double) y );
	}

	public void move ( double x, double y ) {
		setLocation ( x, y );
	}

	public void translate ( int dx, int dy ) {
		translate ( dx, (double) dy );
	}

	public void translate ( double dx, double dy ) {
		x += dx;
		y += dy;
	}

}


/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt.geom;

public abstract class RectangularShape implements Shape, Cloneable {

	protected RectangularShape () {
	}

	public abstract double getX ();

	public abstract double getY ();

	public abstract double getWidth ();

	public abstract double getHeight ();

	public abstract boolean isEmpty ();

	public abstract void setFrame ( double x, double y, double w, double h );

	public double getMinX () {
		return getX ();
	}

	public double getMinY () {
		return getY ();
	}

	public double getMaxX () {
		return getX () + getWidth ();
	}

	public double getMaxY () {
		return getY () + getHeight ();
	}

	public Rectangle2D getFrame () {
		return new Rectangle2D.Double ( getX (), getY (), getWidth (), getHeight () );
	}

	public void setFrame ( Rectangle2D r ) {
		setFrame ( r.getX (), r.getY (), r.getWidth (), r.getHeight () );
	}

	public boolean contains ( Point2D point ) {
		return contains ( point.getX (), point.getY () );
	}

	public boolean intersects ( Rectangle2D rect ) {
		return intersects ( rect.getX (), rect.getY (), rect.getWidth (), rect.getHeight () );
	}

	public boolean contains ( Rectangle2D rect ) {
		return contains ( rect.getX (), rect.getY (), rect.getWidth (), rect.getHeight () );
	}

	public Rectangle getBounds () {
		int x1 = (int) Math.floor ( getMinX () );
		int y1 = (int) Math.floor ( getMinY () );
		int x2 = (int) Math.ceil ( getMaxX () );
		int y2 = (int) Math.ceil ( getMaxY () );
		return new Rectangle ( x1, y1, x2 - x1, y2 - y1 );
	}

	@Override
	public Object clone () {
		try {
			return super.clone ();
		} catch ( CloneNotSupportedException e ) {
			throw new InternalError ();
		}
	}

}


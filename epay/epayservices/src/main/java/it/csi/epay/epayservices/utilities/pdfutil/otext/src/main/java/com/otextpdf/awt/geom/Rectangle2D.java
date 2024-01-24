/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt.geom;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt.geom.misc.HashCode;


public abstract class Rectangle2D extends RectangularShape {

	public static final int OUT_LEFT = 1;

	public static final int OUT_TOP = 2;

	public static final int OUT_RIGHT = 4;

	public static final int OUT_BOTTOM = 8;

	protected Rectangle2D () {
	}

	public static void union ( Rectangle2D src1, Rectangle2D src2, Rectangle2D dst ) {
		double x1 = Math.min ( src1.getMinX (), src2.getMinX () );
		double y1 = Math.min ( src1.getMinY (), src2.getMinY () );
		double x2 = Math.max ( src1.getMaxX (), src2.getMaxX () );
		double y2 = Math.max ( src1.getMaxY (), src2.getMaxY () );
		dst.setFrame ( x1, y1, x2 - x1, y2 - y1 );
	}

	public abstract void setRect ( double x, double y, double width, double height );

	public void setRect ( Rectangle2D r ) {
		setRect ( r.getX (), r.getY (), r.getWidth (), r.getHeight () );
	}

	@Override
	public void setFrame ( double x, double y, double width, double height ) {
		setRect ( x, y, width, height );
	}

	public Rectangle2D getBounds2D () {
		return (Rectangle2D) clone ();
	}

	public boolean intersectsLine ( double x1, double y1, double x2, double y2 ) {
		double rx1 = getX ();
		double ry1 = getY ();
		double rx2 = rx1 + getWidth ();
		double ry2 = ry1 + getHeight ();
		return
						( rx1 <= x1 && x1 <= rx2 && ry1 <= y1 && y1 <= ry2 ) ||
										( rx1 <= x2 && x2 <= rx2 && ry1 <= y2 && y2 <= ry2 ) ||
										Line2D.linesIntersect ( rx1, ry1, rx2, ry2, x1, y1, x2, y2 ) ||
										Line2D.linesIntersect ( rx2, ry1, rx1, ry2, x1, y1, x2, y2 );
	}

	public boolean contains ( double x, double y ) {
		if ( isEmpty () ) {
			return false;
		}

		double x1 = getX ();
		double y1 = getY ();
		double x2 = x1 + getWidth ();
		double y2 = y1 + getHeight ();

		return
						x1 <= x && x < x2 &&
										y1 <= y && y < y2;
	}

	public boolean intersects ( double x, double y, double width, double height ) {
		if ( isEmpty () || width <= 0.0 || height <= 0.0 ) {
			return false;
		}

		double x1 = getX ();
		double y1 = getY ();
		double x2 = x1 + getWidth ();
		double y2 = y1 + getHeight ();

		return
						x + width > x1 && x < x2 &&
										y + height > y1 && y < y2;
	}

	public boolean contains ( double x, double y, double width, double height ) {
		if ( isEmpty () || width <= 0.0 || height <= 0.0 ) {
			return false;
		}

		double x1 = getX ();
		double y1 = getY ();
		double x2 = x1 + getWidth ();
		double y2 = y1 + getHeight ();

		return
						x1 <= x && x + width <= x2 &&
										y1 <= y && y + height <= y2;
	}

	public void add ( double x, double y ) {
		double x1 = Math.min ( getMinX (), x );
		double y1 = Math.min ( getMinY (), y );
		double x2 = Math.max ( getMaxX (), x );
		double y2 = Math.max ( getMaxY (), y );
		setRect ( x1, y1, x2 - x1, y2 - y1 );
	}

	public void add ( Point2D p ) {
		add ( p.getX (), p.getY () );
	}

	public void add ( Rectangle2D r ) {
		union ( this, r, this );
	}

	@Override
	public int hashCode () {
		HashCode hash = new HashCode ();
		hash.append ( getX () );
		hash.append ( getY () );
		hash.append ( getWidth () );
		hash.append ( getHeight () );
		return hash.hashCode ();
	}

	@Override
	public boolean equals ( Object obj ) {
		if ( obj == this ) {
			return true;
		}
		if ( obj instanceof Rectangle2D ) {
			Rectangle2D r = (Rectangle2D) obj;
			return
							getX () == r.getX () &&
											getY () == r.getY () &&
											getWidth () == r.getWidth () &&
											getHeight () == r.getHeight ();
		}
		return false;
	}

	public static class Float extends Rectangle2D {

		public float x;

		public float y;

		public float width;

		public float height;

		public Float () {
		}

		public Float ( float x, float y, float width, float height ) {
			setRect ( x, y, width, height );
		}

		@Override
		public double getX () {
			return x;
		}

		@Override
		public double getY () {
			return y;
		}

		@Override
		public double getWidth () {
			return width;
		}

		@Override
		public double getHeight () {
			return height;
		}

		@Override
		public boolean isEmpty () {
			return width <= 0.0f || height <= 0.0f;
		}

		public void setRect ( float x, float y, float width, float height ) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}

		@Override
		public void setRect ( double x, double y, double width, double height ) {
			this.x = (float) x;
			this.y = (float) y;
			this.width = (float) width;
			this.height = (float) height;
		}

		@Override
		public void setRect ( Rectangle2D r ) {
			this.x = (float) r.getX ();
			this.y = (float) r.getY ();
			this.width = (float) r.getWidth ();
			this.height = (float) r.getHeight ();
		}

		@Override
		public Rectangle2D getBounds2D () {
			return new Float ( x, y, width, height );
		}

		@Override
		public String toString () {
			return getClass ().getName () + "[x=" + x + ",y=" + y + ",width=" + width + ",height=" + height + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		}
	}


	public static class Double extends Rectangle2D {

		public double x;

		public double y;

		public double width;

		public double height;

		public Double () {
		}

		public Double ( double x, double y, double width, double height ) {
			setRect ( x, y, width, height );
		}

		@Override
		public double getX () {
			return x;
		}

		@Override
		public double getY () {
			return y;
		}

		@Override
		public double getWidth () {
			return width;
		}

		@Override
		public double getHeight () {
			return height;
		}

		@Override
		public boolean isEmpty () {
			return width <= 0.0 || height <= 0.0;
		}

		@Override
		public void setRect ( double x, double y, double width, double height ) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}

		@Override
		public void setRect ( Rectangle2D r ) {
			this.x = r.getX ();
			this.y = r.getY ();
			this.width = r.getWidth ();
			this.height = r.getHeight ();
		}

		@Override
		public Rectangle2D getBounds2D () {
			return new Double ( x, y, width, height );
		}

		@Override
		public String toString () {
			return getClass ().getName () + "[x=" + x + ",y=" + y + ",width=" + width + ",height=" + height + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		}
	}

}


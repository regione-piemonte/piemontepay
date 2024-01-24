/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt.geom;

public abstract class Line2D implements Shape, Cloneable {

	protected Line2D () {
	}

	public static boolean linesIntersect ( double x1, double y1, double x2,
					double y2, double x3, double y3, double x4, double y4 ) {

		x2 -= x1; // A
		y2 -= y1;
		x3 -= x1; // B
		y3 -= y1;
		x4 -= x1; // C
		y4 -= y1;

		double AvB = x2 * y3 - x3 * y2;
		double AvC = x2 * y4 - x4 * y2;

		if ( AvB == 0.0 && AvC == 0.0 ) {
			if ( x2 != 0.0 ) {
				return
								( x4 * x3 <= 0.0 ) ||
												( ( x3 * x2 >= 0.0 ) &&
																( x2 > 0.0 ? x3 <= x2 || x4 <= x2 : x3 >= x2 || x4 >= x2 ) );
			}
			if ( y2 != 0.0 ) {
				return
								( y4 * y3 <= 0.0 ) ||
												( ( y3 * y2 >= 0.0 ) &&
																( y2 > 0.0 ? y3 <= y2 || y4 <= y2 : y3 >= y2 || y4 >= y2 ) );
			}
			return false;
		}

		double BvC = x3 * y4 - x4 * y3;

		return ( AvB * AvC <= 0.0 ) && ( BvC * ( AvB + BvC - AvC ) <= 0.0 );
	}

	public abstract double getX1 ();

	public abstract double getY1 ();

	public abstract double getX2 ();

	public abstract double getY2 ();

	public abstract Point2D getP1 ();

	public abstract Point2D getP2 ();

	public abstract void setLine ( double x1, double y1, double x2, double y2 );

	public void setLine ( Point2D p1, Point2D p2 ) {
		setLine ( p1.getX (), p1.getY (), p2.getX (), p2.getY () );
	}

	public void setLine ( Line2D line ) {
		setLine ( line.getX1 (), line.getY1 (), line.getX2 (), line.getY2 () );
	}

	public Rectangle getBounds () {
		return getBounds2D ().getBounds ();
	}

	public boolean contains ( double px, double py ) {
		return false;
	}

	public boolean contains ( Point2D p ) {
		return false;
	}

	public boolean contains ( Rectangle2D r ) {
		return false;
	}

	public boolean contains ( double rx, double ry, double rw, double rh ) {
		return false;
	}

	public boolean intersects ( double rx, double ry, double rw, double rh ) {
		return intersects ( new Rectangle2D.Double ( rx, ry, rw, rh ) );
	}

	public boolean intersects ( Rectangle2D r ) {
		return r.intersectsLine ( getX1 (), getY1 (), getX2 (), getY2 () );
	}

	@Override
	public Object clone () {
		try {
			return super.clone ();
		} catch ( CloneNotSupportedException e ) {
			throw new InternalError ();
		}
	}

	public static class Float extends Line2D {

		public float x1;

		public float y1;

		public float x2;

		public float y2;

		public Float () {
		}

		public Float ( float x1, float y1, float x2, float y2 ) {
			setLine ( x1, y1, x2, y2 );
		}

		public Float ( Point2D p1, Point2D p2 ) {
			setLine ( p1, p2 );
		}

		@Override
		public double getX1 () {
			return x1;
		}

		@Override
		public double getY1 () {
			return y1;
		}

		@Override
		public double getX2 () {
			return x2;
		}

		@Override
		public double getY2 () {
			return y2;
		}

		@Override
		public Point2D getP1 () {
			return new Point2D.Float ( x1, y1 );
		}

		@Override
		public Point2D getP2 () {
			return new Point2D.Float ( x2, y2 );
		}

		@Override
		public void setLine ( double x1, double y1, double x2, double y2 ) {
			this.x1 = (float) x1;
			this.y1 = (float) y1;
			this.x2 = (float) x2;
			this.y2 = (float) y2;
		}

		public void setLine ( float x1, float y1, float x2, float y2 ) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

		public Rectangle2D getBounds2D () {
			float rx, ry, rw, rh;
			if ( x1 < x2 ) {
				rx = x1;
				rw = x2 - x1;
			} else {
				rx = x2;
				rw = x1 - x2;
			}
			if ( y1 < y2 ) {
				ry = y1;
				rh = y2 - y1;
			} else {
				ry = y2;
				rh = y1 - y2;
			}
			return new Rectangle2D.Float ( rx, ry, rw, rh );
		}
	}


	public static class Double extends Line2D {

		public double x1;

		public double y1;

		public double x2;

		public double y2;

		public Double () {
		}

		public Double ( double x1, double y1, double x2, double y2 ) {
			setLine ( x1, y1, x2, y2 );
		}

		public Double ( Point2D p1, Point2D p2 ) {
			setLine ( p1, p2 );
		}

		@Override
		public double getX1 () {
			return x1;
		}

		@Override
		public double getY1 () {
			return y1;
		}

		@Override
		public double getX2 () {
			return x2;
		}

		@Override
		public double getY2 () {
			return y2;
		}

		@Override
		public Point2D getP1 () {
			return new Point2D.Double ( x1, y1 );
		}

		@Override
		public Point2D getP2 () {
			return new Point2D.Double ( x2, y2 );
		}

		@Override
		public void setLine ( double x1, double y1, double x2, double y2 ) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

		public Rectangle2D getBounds2D () {
			double rx, ry, rw, rh;
			if ( x1 < x2 ) {
				rx = x1;
				rw = x2 - x1;
			} else {
				rx = x2;
				rw = x1 - x2;
			}
			if ( y1 < y2 ) {
				ry = y1;
				rh = y2 - y1;
			} else {
				ry = y2;
				rh = y1 - y2;
			}
			return new Rectangle2D.Double ( rx, ry, rw, rh );
		}
	}

}

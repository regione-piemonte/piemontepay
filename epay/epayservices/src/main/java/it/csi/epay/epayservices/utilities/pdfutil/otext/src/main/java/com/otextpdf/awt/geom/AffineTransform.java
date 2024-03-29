/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt.geom;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt.geom.misc.HashCode;

import java.io.IOException;
import java.io.Serializable;


public class AffineTransform implements Cloneable, Serializable {

	public static final int TYPE_IDENTITY = 0;

	public static final int TYPE_TRANSLATION = 1;

	public static final int TYPE_UNIFORM_SCALE = 2;

	public static final int TYPE_GENERAL_SCALE = 4;

	public static final int TYPE_QUADRANT_ROTATION = 8;

	public static final int TYPE_GENERAL_ROTATION = 16;

	public static final int TYPE_GENERAL_TRANSFORM = 32;

	public static final int TYPE_FLIP = 64;

	public static final int TYPE_MASK_SCALE = TYPE_UNIFORM_SCALE | TYPE_GENERAL_SCALE;

	public static final int TYPE_MASK_ROTATION = TYPE_QUADRANT_ROTATION | TYPE_GENERAL_ROTATION;

	static final int TYPE_UNKNOWN = -1;

	static final double ZERO = 1E-10;

	private static final long serialVersionUID = 1330973210523860834L;

	double m00;

	double m10;

	double m01;

	double m11;

	double m02;

	double m12;

	transient int type;

	public AffineTransform () {
		type = TYPE_IDENTITY;
		m00 = m11 = 1.0;
		m10 = m01 = m02 = m12 = 0.0;
	}

	public AffineTransform ( AffineTransform t ) {
		this.type = t.type;
		this.m00 = t.m00;
		this.m10 = t.m10;
		this.m01 = t.m01;
		this.m11 = t.m11;
		this.m02 = t.m02;
		this.m12 = t.m12;
	}

	public AffineTransform ( float m00, float m10, float m01, float m11, float m02, float m12 ) {
		this.type = TYPE_UNKNOWN;
		this.m00 = m00;
		this.m10 = m10;
		this.m01 = m01;
		this.m11 = m11;
		this.m02 = m02;
		this.m12 = m12;
	}

	public AffineTransform ( double m00, double m10, double m01, double m11, double m02, double m12 ) {
		this.type = TYPE_UNKNOWN;
		this.m00 = m00;
		this.m10 = m10;
		this.m01 = m01;
		this.m11 = m11;
		this.m02 = m02;
		this.m12 = m12;
	}

	public AffineTransform ( double[] matrix ) {
		this.type = TYPE_UNKNOWN;
		m00 = matrix[0];
		m10 = matrix[1];
		m01 = matrix[2];
		m11 = matrix[3];
		if ( matrix.length > 4 ) {
			m02 = matrix[4];
			m12 = matrix[5];
		}
	}

	public static AffineTransform getTranslateInstance ( double mx, double my ) {
		AffineTransform t = new AffineTransform ();
		t.setToTranslation ( mx, my );
		return t;
	}

	public static AffineTransform getScaleInstance ( double scx, double scY ) {
		AffineTransform t = new AffineTransform ();
		t.setToScale ( scx, scY );
		return t;
	}

	public static AffineTransform getRotateInstance ( double angle ) {
		AffineTransform t = new AffineTransform ();
		t.setToRotation ( angle );
		return t;
	}

	public static AffineTransform getRotateInstance ( double angle, double x, double y ) {
		AffineTransform t = new AffineTransform ();
		t.setToRotation ( angle, x, y );
		return t;
	}

	public int getType () {
		if ( type != TYPE_UNKNOWN ) {
			return type;
		}

		int type = 0;

		if ( m00 * m01 + m10 * m11 != 0.0 ) {
			type |= TYPE_GENERAL_TRANSFORM;
			return type;
		}

		if ( m02 != 0.0 || m12 != 0.0 ) {
			type |= TYPE_TRANSLATION;
		} else if ( m00 == 1.0 && m11 == 1.0 && m01 == 0.0 && m10 == 0.0 ) {
			return type;
		}

		if ( m00 * m11 - m01 * m10 < 0.0 ) {
			type |= TYPE_FLIP;
		}

		double dx = m00 * m00 + m10 * m10;
		double dy = m01 * m01 + m11 * m11;
		if ( dx != dy ) {
			type |= TYPE_GENERAL_SCALE;
		} else if ( dx != 1.0 ) {
			type |= TYPE_UNIFORM_SCALE;
		}

		if ( ( m00 == 0.0 && m11 == 0.0 ) ||
						( m10 == 0.0 && m01 == 0.0 && ( m00 < 0.0 || m11 < 0.0 ) ) ) {
			type |= TYPE_QUADRANT_ROTATION;
		} else if ( m01 != 0.0 || m10 != 0.0 ) {
			type |= TYPE_GENERAL_ROTATION;
		}

		return type;
	}

	public boolean isIdentity () {
		return getType () == TYPE_IDENTITY;
	}

	public void getMatrix ( double[] matrix ) {
		matrix[0] = m00;
		matrix[1] = m10;
		matrix[2] = m01;
		matrix[3] = m11;
		if ( matrix.length > 4 ) {
			matrix[4] = m02;
			matrix[5] = m12;
		}
	}

	public void setTransform ( double m00, double m10, double m01, double m11, double m02, double m12 ) {
		this.type = TYPE_UNKNOWN;
		this.m00 = m00;
		this.m10 = m10;
		this.m01 = m01;
		this.m11 = m11;
		this.m02 = m02;
		this.m12 = m12;
	}

	public void setTransform ( AffineTransform t ) {
		type = t.type;
		setTransform ( t.m00, t.m10, t.m01, t.m11, t.m02, t.m12 );
	}

	public void setToTranslation ( double mx, double my ) {
		m00 = m11 = 1.0;
		m01 = m10 = 0.0;
		m02 = mx;
		m12 = my;
		if ( mx == 0.0 && my == 0.0 ) {
			type = TYPE_IDENTITY;
		} else {
			type = TYPE_TRANSLATION;
		}
	}

	public void setToScale ( double scx, double scy ) {
		m00 = scx;
		m11 = scy;
		m10 = m01 = m02 = m12 = 0.0;
		if ( scx != 1.0 || scy != 1.0 ) {
			type = TYPE_UNKNOWN;
		} else {
			type = TYPE_IDENTITY;
		}
	}

	public void setToRotation ( double angle ) {
		double sin = Math.sin ( angle );
		double cos = Math.cos ( angle );
		if ( Math.abs ( cos ) < ZERO ) {
			cos = 0.0;
			sin = sin > 0.0 ? 1.0 : -1.0;
		} else if ( Math.abs ( sin ) < ZERO ) {
			sin = 0.0;
			cos = cos > 0.0 ? 1.0 : -1.0;
		}
		m00 = m11 = cos;
		m01 = -sin;
		m10 = sin;
		m02 = m12 = 0.0;
		type = TYPE_UNKNOWN;
	}

	public void setToRotation ( double angle, double px, double py ) {
		setToRotation ( angle );
		m02 = px * ( 1.0 - m00 ) + py * m10;
		m12 = py * ( 1.0 - m00 ) - px * m10;
		type = TYPE_UNKNOWN;
	}

	public void translate ( double mx, double my ) {
		concatenate ( AffineTransform.getTranslateInstance ( mx, my ) );
	}

	public void scale ( double scx, double scy ) {
		concatenate ( AffineTransform.getScaleInstance ( scx, scy ) );
	}

	public void rotate ( double angle ) {
		concatenate ( AffineTransform.getRotateInstance ( angle ) );
	}

	public void rotate ( double angle, double px, double py ) {
		concatenate ( AffineTransform.getRotateInstance ( angle, px, py ) );
	}

	AffineTransform multiply ( AffineTransform t1, AffineTransform t2 ) {
		return new AffineTransform (
						t1.m00 * t2.m00 + t1.m10 * t2.m01,          // m00
						t1.m00 * t2.m10 + t1.m10 * t2.m11,          // m01
						t1.m01 * t2.m00 + t1.m11 * t2.m01,          // m10
						t1.m01 * t2.m10 + t1.m11 * t2.m11,          // m11
						t1.m02 * t2.m00 + t1.m12 * t2.m01 + t2.m02, // m02
						t1.m02 * t2.m10 + t1.m12 * t2.m11 + t2.m12 );// m12
	}

	public void concatenate ( AffineTransform t ) {
		setTransform ( multiply ( t, this ) );
	}

	public Point2D transform ( Point2D src, Point2D dst ) {
		if ( dst == null ) {
			if ( src instanceof Point2D.Double ) {
				dst = new Point2D.Double ();
			} else {
				dst = new Point2D.Float ();
			}
		}

		double x = src.getX ();
		double y = src.getY ();

		dst.setLocation ( x * m00 + y * m01 + m02, x * m10 + y * m11 + m12 );
		return dst;
	}

	public void transform ( Point2D[] src, int srcOff, Point2D[] dst, int dstOff, int length ) {
		while ( --length >= 0 ) {
			Point2D srcPoint = src[srcOff++];
			double x = srcPoint.getX ();
			double y = srcPoint.getY ();
			Point2D dstPoint = dst[dstOff];
			if ( dstPoint == null ) {
				if ( srcPoint instanceof Point2D.Double ) {
					dstPoint = new Point2D.Double ();
				} else {
					dstPoint = new Point2D.Float ();
				}
			}
			dstPoint.setLocation ( x * m00 + y * m01 + m02, x * m10 + y * m11 + m12 );
			dst[dstOff++] = dstPoint;
		}
	}

	public void transform ( double[] src, int srcOff, double[] dst, int dstOff, int length ) {
		int step = 2;
		if ( src == dst && srcOff < dstOff && dstOff < srcOff + length * 2 ) {
			srcOff = srcOff + length * 2 - 2;
			dstOff = dstOff + length * 2 - 2;
			step = -2;
		}
		while ( --length >= 0 ) {
			double x = src[srcOff];
			double y = src[srcOff + 1];
			dst[dstOff] = x * m00 + y * m01 + m02;
			dst[dstOff + 1] = x * m10 + y * m11 + m12;
			srcOff += step;
			dstOff += step;
		}
	}

	public void transform ( float[] src, int srcOff, float[] dst, int dstOff, int length ) {
		int step = 2;
		if ( src == dst && srcOff < dstOff && dstOff < srcOff + length * 2 ) {
			srcOff = srcOff + length * 2 - 2;
			dstOff = dstOff + length * 2 - 2;
			step = -2;
		}
		while ( --length >= 0 ) {
			float x = src[srcOff];
			float y = src[srcOff + 1];
			dst[dstOff] = (float) ( x * m00 + y * m01 + m02 );
			dst[dstOff + 1] = (float) ( x * m10 + y * m11 + m12 );
			srcOff += step;
			dstOff += step;
		}
	}

	public void transform ( float[] src, int srcOff, double[] dst, int dstOff, int length ) {
		while ( --length >= 0 ) {
			float x = src[srcOff++];
			float y = src[srcOff++];
			dst[dstOff++] = x * m00 + y * m01 + m02;
			dst[dstOff++] = x * m10 + y * m11 + m12;
		}
	}

	public void transform ( double[] src, int srcOff, float[] dst, int dstOff, int length ) {
		while ( --length >= 0 ) {
			double x = src[srcOff++];
			double y = src[srcOff++];
			dst[dstOff++] = (float) ( x * m00 + y * m01 + m02 );
			dst[dstOff++] = (float) ( x * m10 + y * m11 + m12 );
		}
	}

	@Override
	public String toString () {
		return
						getClass ().getName () +
										"[[" + m00 + ", " + m01 + ", " + m02 + "], [" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
										+ m10 + ", " + m11 + ", " + m12 + "]]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	@Override
	public Object clone () {
		try {
			return super.clone ();
		} catch ( CloneNotSupportedException e ) {
			throw new InternalError ();
		}
	}

	@Override
	public int hashCode () {
		HashCode hash = new HashCode ();
		hash.append ( m00 );
		hash.append ( m01 );
		hash.append ( m02 );
		hash.append ( m10 );
		hash.append ( m11 );
		hash.append ( m12 );
		return hash.hashCode ();
	}

	@Override
	public boolean equals ( Object obj ) {
		if ( obj == this ) {
			return true;
		}
		if ( obj instanceof AffineTransform ) {
			AffineTransform t = (AffineTransform) obj;
			return
							m00 == t.m00 && m01 == t.m01 &&
											m02 == t.m02 && m10 == t.m10 &&
											m11 == t.m11 && m12 == t.m12;
		}
		return false;
	}

	private void writeObject ( java.io.ObjectOutputStream stream ) throws IOException {
		stream.defaultWriteObject ();
	}

	private void readObject ( java.io.ObjectInputStream stream ) throws IOException, ClassNotFoundException {
		stream.defaultReadObject ();
		type = TYPE_UNKNOWN;
	}

}


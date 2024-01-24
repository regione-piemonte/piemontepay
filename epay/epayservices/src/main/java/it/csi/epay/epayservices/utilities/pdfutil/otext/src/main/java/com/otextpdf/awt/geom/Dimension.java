/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt.geom;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt.geom.misc.HashCode;

import java.io.Serializable;


public class Dimension extends Dimension2D implements Serializable {

	private static final long serialVersionUID = 4723952579491349524L;

	public double width;

	public double height;

	public Dimension ( double width, double height ) {
		setSize ( width, height );
	}

	@Override
	public int hashCode () {
		HashCode hash = new HashCode ();
		hash.append ( width );
		hash.append ( height );
		return hash.hashCode ();
	}

	@Override
	public boolean equals ( Object obj ) {
		if ( obj == this ) {
			return true;
		}
		if ( obj instanceof Dimension ) {
			Dimension d = (Dimension) obj;
			return ( d.width == width && d.height == height );
		}
		return false;
	}

	@Override
	public String toString () {
		return getClass ().getName () + "[width=" + width + ",height=" + height + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	public void setSize ( int width, int height ) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void setSize ( double width, double height ) {
		setSize ( (int) Math.ceil ( width ), (int) Math.ceil ( height ) );
	}

	public Dimension getSize () {
		return new Dimension ( width, height );
	}

	public void setSize ( Dimension d ) {
		setSize ( d.width, d.height );
	}

	@Override
	public double getHeight () {
		return height;
	}

	@Override
	public double getWidth () {
		return width;
	}

}


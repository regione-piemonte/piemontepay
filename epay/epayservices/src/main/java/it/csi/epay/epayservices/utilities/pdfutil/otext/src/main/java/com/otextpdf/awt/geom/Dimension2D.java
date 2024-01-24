/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt.geom;

public abstract class Dimension2D implements Cloneable {

	protected Dimension2D () {
	}

	public abstract double getWidth ();

	public abstract double getHeight ();

	public abstract void setSize ( double width, double height );

	public void setSize ( Dimension2D d ) {
		setSize ( d.getWidth (), d.getHeight () );
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


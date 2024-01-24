/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt.geom;

public interface Shape {

	boolean contains ( double x, double y );

	boolean contains ( double x, double y, double w, double h );

	boolean contains ( Point2D point );

	boolean contains ( Rectangle2D r );

	Rectangle getBounds ();

	Rectangle2D getBounds2D ();

	boolean intersects ( double x, double y, double w, double h );

	boolean intersects ( Rectangle2D r );
}

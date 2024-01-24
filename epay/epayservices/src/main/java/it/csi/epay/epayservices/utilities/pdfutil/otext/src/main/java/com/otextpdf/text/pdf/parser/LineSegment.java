/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.parser;

public class LineSegment {

	private final Vector startPoint;

	private final Vector endPoint;

	public LineSegment ( Vector startPoint, Vector endPoint ) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public Vector getStartPoint () {
		return startPoint;
	}

	public Vector getEndPoint () {
		return endPoint;
	}

	public float getLength () {
		return endPoint.subtract ( startPoint ).length ();
	}

	public LineSegment transformBy ( Matrix m ) {
		Vector newStart = startPoint.cross ( m );
		Vector newEnd = endPoint.cross ( m );
		return new LineSegment ( newStart, newEnd );
	}
}

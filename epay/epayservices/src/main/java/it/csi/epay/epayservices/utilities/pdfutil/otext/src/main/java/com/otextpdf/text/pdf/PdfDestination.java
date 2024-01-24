/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

public class PdfDestination extends PdfArray {

	public static final int XYZ = 0;

	public static final int FIT = 1;

	public static final int FITH = 2;

	public static final int FITV = 3;

	public static final int FITR = 4;

	public static final int FITB = 5;

	public static final int FITBH = 6;

	public static final int FITBV = 7;

	private boolean status = false;

	public PdfDestination ( int type, float parameter ) {
		super ( new PdfNumber ( parameter ) );
		switch ( type ) {
		default:
			addFirst ( PdfName.FITH );
			break;
		case FITV:
			addFirst ( PdfName.FITV );
			break;
		case FITBH:
			addFirst ( PdfName.FITBH );
			break;
		case FITBV:
			addFirst ( PdfName.FITBV );
		}
	}

	public PdfDestination ( float left, float top, float zoom ) {
		super ( PdfName.XYZ );
		if ( left < 0 )
			add ( PdfNull.PDFNULL );
		else
			add ( new PdfNumber ( left ) );
		if ( top < 0 )
			add ( PdfNull.PDFNULL );
		else
			add ( new PdfNumber ( top ) );
		add ( new PdfNumber ( zoom ) );
	}

	public boolean hasPage () {
		return status;
	}

	public void addPage ( PdfIndirectReference page ) {
		if ( !status ) {
			addFirst ( page );
			status = true;
		}
	}
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

public class PdfTransition {

	public static final int SPLITVOUT = 1;

	public static final int SPLITHOUT = 2;

	public static final int SPLITVIN = 3;

	public static final int SPLITHIN = 4;

	public static final int BLINDV = 5;

	public static final int BLINDH = 6;

	public static final int INBOX = 7;

	public static final int OUTBOX = 8;

	public static final int LRWIPE = 9;

	public static final int RLWIPE = 10;

	public static final int BTWIPE = 11;

	public static final int TBWIPE = 12;

	public static final int DISSOLVE = 13;

	public static final int LRGLITTER = 14;

	public static final int TBGLITTER = 15;

	public static final int DGLITTER = 16;

	protected int duration;

	protected int type;

	public PdfTransition ( int type, int duration ) {
		this.duration = duration;
		this.type = type;
	}

	public int getDuration () {
		return duration;
	}

	public int getType () {
		return type;
	}

	public PdfDictionary getTransitionDictionary () {
		PdfDictionary trans = new PdfDictionary ( PdfName.TRANS );
		switch ( type ) {
		case SPLITVOUT:
			trans.put ( PdfName.S, PdfName.SPLIT );
			trans.put ( PdfName.D, new PdfNumber ( duration ) );
			trans.put ( PdfName.DM, PdfName.V );
			trans.put ( PdfName.M, PdfName.O );
			break;
		case SPLITHOUT:
			trans.put ( PdfName.S, PdfName.SPLIT );
			trans.put ( PdfName.D, new PdfNumber ( duration ) );
			trans.put ( PdfName.DM, PdfName.H );
			trans.put ( PdfName.M, PdfName.O );
			break;
		case SPLITVIN:
			trans.put ( PdfName.S, PdfName.SPLIT );
			trans.put ( PdfName.D, new PdfNumber ( duration ) );
			trans.put ( PdfName.DM, PdfName.V );
			trans.put ( PdfName.M, PdfName.I );
			break;
		case SPLITHIN:
			trans.put ( PdfName.S, PdfName.SPLIT );
			trans.put ( PdfName.D, new PdfNumber ( duration ) );
			trans.put ( PdfName.DM, PdfName.H );
			trans.put ( PdfName.M, PdfName.I );
			break;
		case BLINDV:
			trans.put ( PdfName.S, PdfName.BLINDS );
			trans.put ( PdfName.D, new PdfNumber ( duration ) );
			trans.put ( PdfName.DM, PdfName.V );
			break;
		case BLINDH:
			trans.put ( PdfName.S, PdfName.BLINDS );
			trans.put ( PdfName.D, new PdfNumber ( duration ) );
			trans.put ( PdfName.DM, PdfName.H );
			break;
		case INBOX:
			trans.put ( PdfName.S, PdfName.BOX );
			trans.put ( PdfName.D, new PdfNumber ( duration ) );
			trans.put ( PdfName.M, PdfName.I );
			break;
		case OUTBOX:
			trans.put ( PdfName.S, PdfName.BOX );
			trans.put ( PdfName.D, new PdfNumber ( duration ) );
			trans.put ( PdfName.M, PdfName.O );
			break;
		case LRWIPE:
			trans.put ( PdfName.S, PdfName.WIPE );
			trans.put ( PdfName.D, new PdfNumber ( duration ) );
			trans.put ( PdfName.DI, new PdfNumber ( 0 ) );
			break;
		case RLWIPE:
			trans.put ( PdfName.S, PdfName.WIPE );
			trans.put ( PdfName.D, new PdfNumber ( duration ) );
			trans.put ( PdfName.DI, new PdfNumber ( 180 ) );
			break;
		case BTWIPE:
			trans.put ( PdfName.S, PdfName.WIPE );
			trans.put ( PdfName.D, new PdfNumber ( duration ) );
			trans.put ( PdfName.DI, new PdfNumber ( 90 ) );
			break;
		case TBWIPE:
			trans.put ( PdfName.S, PdfName.WIPE );
			trans.put ( PdfName.D, new PdfNumber ( duration ) );
			trans.put ( PdfName.DI, new PdfNumber ( 270 ) );
			break;
		case DISSOLVE:
			trans.put ( PdfName.S, PdfName.DISSOLVE );
			trans.put ( PdfName.D, new PdfNumber ( duration ) );
			break;
		case LRGLITTER:
			trans.put ( PdfName.S, PdfName.GLITTER );
			trans.put ( PdfName.D, new PdfNumber ( duration ) );
			trans.put ( PdfName.DI, new PdfNumber ( 0 ) );
			break;
		case TBGLITTER:
			trans.put ( PdfName.S, PdfName.GLITTER );
			trans.put ( PdfName.D, new PdfNumber ( duration ) );
			trans.put ( PdfName.DI, new PdfNumber ( 270 ) );
			break;
		case DGLITTER:
			trans.put ( PdfName.S, PdfName.GLITTER );
			trans.put ( PdfName.D, new PdfNumber ( duration ) );
			trans.put ( PdfName.DI, new PdfNumber ( 315 ) );
			break;
		}
		return trans;
	}
}


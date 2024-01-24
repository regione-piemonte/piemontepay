/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import java.util.ArrayList;


public class PdfTextArray {

	ArrayList<Object> arrayList = new ArrayList<> ();

	private String lastStr;

	private Float lastNum;

	public PdfTextArray ( String str ) {
		add ( str );
	}

	public PdfTextArray () {
	}

	public void add ( PdfNumber number ) {
		add ( (float) number.doubleValue () );
	}

	public void add ( float number ) {
		if ( number != 0 ) {
			if ( lastNum != null ) {
				lastNum = number + lastNum;
				if ( lastNum != 0 ) {
					replaceLast ( lastNum );
				} else {
					arrayList.remove ( arrayList.size () - 1 );
				}
			} else {
				lastNum = number;
				arrayList.add ( lastNum );
			}

			lastStr = null;
		}
	}

	public void add ( String str ) {
		if ( !str.isEmpty () ) {
			if ( lastStr != null ) {
				lastStr = lastStr + str;
				replaceLast ( lastStr );
			} else {
				lastStr = str;
				arrayList.add ( lastStr );
			}
			lastNum = null;
		}
	}

	ArrayList<Object> getArrayList () {
		return arrayList;
	}

	private void replaceLast ( Object obj ) {
		arrayList.set ( arrayList.size () - 1, obj );
	}
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl.xpath;

import java.util.ArrayList;
import java.util.List;


public class XMPPath {

	public static final int STRUCT_FIELD_STEP = 0x01;

	public static final int QUALIFIER_STEP = 0x02;

	public static final int ARRAY_INDEX_STEP = 0x03;

	public static final int ARRAY_LAST_STEP = 0x04;

	public static final int QUAL_SELECTOR_STEP = 0x05;

	public static final int FIELD_SELECTOR_STEP = 0x06;

	public static final int SCHEMA_NODE = 0x80000000;

	public static final int STEP_SCHEMA = 0;

	public static final int STEP_ROOT_PROP = 1;

	private final List segments = new ArrayList ( 5 );

	public void add ( XMPPathSegment segment ) {
		segments.add ( segment );
	}

	public XMPPathSegment getSegment ( int index ) {
		return (XMPPathSegment) segments.get ( index );
	}

	public int size () {
		return segments.size ();
	}

	public String toString () {
		StringBuilder result = new StringBuilder ();
		int index = 1;
		while ( index < size () ) {
			result.append ( getSegment ( index ) );
			if ( index < size () - 1 ) {
				int kind = getSegment ( index + 1 ).getKind ();
				if ( kind == STRUCT_FIELD_STEP ||
								kind == QUALIFIER_STEP ) {
					// all but last and array indices
					result.append ( '/' );
				}
			}
			index++;
		}

		return result.toString ();
	}
}

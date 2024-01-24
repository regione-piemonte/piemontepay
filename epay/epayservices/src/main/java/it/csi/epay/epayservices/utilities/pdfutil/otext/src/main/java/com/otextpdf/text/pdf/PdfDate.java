/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;


public class PdfDate extends PdfString {

	private static final int[] DATE_SPACE = { Calendar.YEAR, 4, 0, Calendar.MONTH, 2, -1, Calendar.DAY_OF_MONTH, 2, 0,
					Calendar.HOUR_OF_DAY, 2, 0, Calendar.MINUTE, 2, 0, Calendar.SECOND, 2, 0 };

	public PdfDate ( Calendar d ) {
		super ();
		StringBuilder date = new StringBuilder ( "D:" );
		date.append ( setLength ( d.get ( Calendar.YEAR ), 4 ) );
		date.append ( setLength ( d.get ( Calendar.MONTH ) + 1, 2 ) );
		date.append ( setLength ( d.get ( Calendar.DATE ), 2 ) );
		date.append ( setLength ( d.get ( Calendar.HOUR_OF_DAY ), 2 ) );
		date.append ( setLength ( d.get ( Calendar.MINUTE ), 2 ) );
		date.append ( setLength ( d.get ( Calendar.SECOND ), 2 ) );
		int timezone = ( d.get ( Calendar.ZONE_OFFSET ) + d.get ( Calendar.DST_OFFSET ) ) / ( 60 * 60 * 1000 );
		if ( timezone == 0 ) {
			date.append ( 'Z' );
		} else if ( timezone < 0 ) {
			date.append ( '-' );
			timezone = -timezone;
		} else {
			date.append ( '+' );
		}
		if ( timezone != 0 ) {
			date.append ( setLength ( timezone, 2 ) ).append ( '\'' );
			int zone = Math.abs ( ( d.get ( Calendar.ZONE_OFFSET ) + d.get ( Calendar.DST_OFFSET ) ) / ( 60 * 1000 ) ) - ( timezone * 60 );
			date.append ( setLength ( zone, 2 ) ).append ( '\'' );
		}
		value = date.toString ();
	}

	public PdfDate () {
		this ( new GregorianCalendar () );
	}

	public static String getW3CDate ( String d ) {
		if ( d.startsWith ( "D:" ) )
			d = d.substring ( 2 );
		StringBuilder sb = new StringBuilder ();
		if ( d.length () < 4 )
			return "0000";
		sb.append ( d, 0, 4 ); //year
		d = d.substring ( 4 );
		if ( d.length () < 2 )
			return sb.toString ();
		sb.append ( '-' ).append ( d, 0, 2 ); //month
		d = d.substring ( 2 );
		if ( d.length () < 2 )
			return sb.toString ();
		sb.append ( '-' ).append ( d, 0, 2 ); //day
		d = d.substring ( 2 );
		if ( d.length () < 2 )
			return sb.toString ();
		sb.append ( 'T' ).append ( d, 0, 2 ); //hour
		d = d.substring ( 2 );
		if ( d.length () < 2 ) {
			sb.append ( ":00Z" );
			return sb.toString ();
		}
		sb.append ( ':' ).append ( d, 0, 2 ); //minute
		d = d.substring ( 2 );
		if ( d.length () < 2 ) {
			sb.append ( 'Z' );
			return sb.toString ();
		}
		sb.append ( ':' ).append ( d, 0, 2 ); //second
		d = d.substring ( 2 );
		if ( d.startsWith ( "-" ) || d.startsWith ( "+" ) ) {
			String sign = d.substring ( 0, 1 );
			d = d.substring ( 1 );
			String h;
			String m = "00";
			if ( d.length () >= 2 ) {
				h = d.substring ( 0, 2 );
				if ( d.length () > 2 ) {
					d = d.substring ( 3 );
					if ( d.length () >= 2 )
						m = d.substring ( 0, 2 );
				}
				sb.append ( sign ).append ( h ).append ( ':' ).append ( m );
				return sb.toString ();
			}
		}
		sb.append ( 'Z' );
		return sb.toString ();
	}

	public static Calendar decode ( String s ) {
		try {
			if ( s.startsWith ( "D:" ) )
				s = s.substring ( 2 );
			GregorianCalendar calendar;
			int slen = s.length ();
			int idx = s.indexOf ( 'Z' );
			if ( idx >= 0 ) {
				slen = idx;
				calendar = new GregorianCalendar ( new SimpleTimeZone ( 0, "ZPDF" ) );
			} else {
				int sign = 1;
				idx = s.indexOf ( '+' );
				if ( idx < 0 ) {
					idx = s.indexOf ( '-' );
					if ( idx >= 0 )
						sign = -1;
				}
				if ( idx < 0 )
					calendar = new GregorianCalendar ();
				else {
					int offset = Integer.parseInt ( s.substring ( idx + 1, idx + 3 ) ) * 60;
					if ( idx + 5 < s.length () )
						offset += Integer.parseInt ( s.substring ( idx + 4, idx + 6 ) );
					calendar = new GregorianCalendar ( new SimpleTimeZone ( offset * sign * 60000, "ZPDF" ) );
					slen = idx;
				}
			}
			calendar.clear ();
			idx = 0;
			for ( int k = 0; k < DATE_SPACE.length; k += 3 ) {
				if ( idx >= slen )
					break;
				calendar.set ( DATE_SPACE[k], Integer.parseInt ( s.substring ( idx, idx + DATE_SPACE[k + 1] ) ) + DATE_SPACE[k + 2] );
				idx += DATE_SPACE[k + 1];
			}
			return calendar;
		} catch ( Exception e ) {
			return null;
		}
	}

	private String setLength ( int i, int length ) { // 1.3-1.4 problem fixed by Finn Bock
		StringBuilder tmp = new StringBuilder ();
		tmp.append ( i );
		while ( tmp.length () < length ) {
			tmp.insert ( 0, "0" );
		}
		tmp.setLength ( length );
		return tmp.toString ();
	}

	public String getW3CDate () {
		return getW3CDate ( value );
	}
}

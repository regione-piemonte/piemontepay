/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl.XMPDateTimeImpl;

import java.util.Calendar;
import java.util.TimeZone;


public final class XMPDateTimeFactory {

	private static final TimeZone UTC = TimeZone.getTimeZone ( "UTC" );

	private XMPDateTimeFactory () {
		// EMPTY
	}

	public static XMPDateTime createFromCalendar ( Calendar calendar ) {
		return new XMPDateTimeImpl ( calendar );
	}

	public static XMPDateTime create () {
		return new XMPDateTimeImpl ();
	}

	public static XMPDateTime create ( int year, int month, int day ) {
		XMPDateTime dt = new XMPDateTimeImpl ();
		dt.setYear ( year );
		dt.setMonth ( month );
		dt.setDay ( day );
		return dt;
	}

	public static XMPDateTime create ( int year, int month, int day,
					int hour, int minute, int second, int nanoSecond ) {
		XMPDateTime dt = new XMPDateTimeImpl ();
		dt.setYear ( year );
		dt.setMonth ( month );
		dt.setDay ( day );
		dt.setHour ( hour );
		dt.setMinute ( minute );
		dt.setSecond ( second );
		dt.setNanoSecond ( nanoSecond );
		return dt;
	}

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPDateTime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;


public class XMPDateTimeImpl implements XMPDateTime {

	private int year = 0;

	private int month = 0;

	private int day = 0;

	private int hour = 0;

	private int minute = 0;

	private int second = 0;

	private TimeZone timeZone = null;

	private int nanoSeconds;

	private boolean hasDate = false;

	private boolean hasTime = false;

	private boolean hasTimeZone = false;

	public XMPDateTimeImpl () {
		// EMPTY
	}

	public XMPDateTimeImpl ( Calendar calendar ) {
		// extract the date and timezone from the calendar provided
		Date date = calendar.getTime ();
		TimeZone zone = calendar.getTimeZone ();

		// put that date into a calendar the pretty much represents ISO8601
		// I use US because it is close to the "locale" for the ISO8601 spec
		GregorianCalendar intCalendar =
						(GregorianCalendar) Calendar.getInstance ( Locale.US );
		intCalendar.setGregorianChange ( new Date ( Long.MIN_VALUE ) );
		intCalendar.setTimeZone ( zone );
		intCalendar.setTime ( date );

		this.year = intCalendar.get ( Calendar.YEAR );
		this.month = intCalendar.get ( Calendar.MONTH ) + 1; // cal is from 0..12
		this.day = intCalendar.get ( Calendar.DAY_OF_MONTH );
		this.hour = intCalendar.get ( Calendar.HOUR_OF_DAY );
		this.minute = intCalendar.get ( Calendar.MINUTE );
		this.second = intCalendar.get ( Calendar.SECOND );
		this.nanoSeconds = intCalendar.get ( Calendar.MILLISECOND ) * 1000000;
		this.timeZone = intCalendar.getTimeZone ();

		// object contains all date components
		hasDate = hasTime = hasTimeZone = true;
	}

	public int getYear () {
		return year;
	}

	public void setYear ( int year ) {
		this.year = Math.min ( Math.abs ( year ), 9999 );
		this.hasDate = true;
	}

	public int getMonth () {
		return month;
	}

	public void setMonth ( int month ) {
		if ( month < 1 ) {
			this.month = 1;
		} else
			this.month = Math.min ( month, 12 );

		this.hasDate = true;
	}

	public int getDay () {
		return day;
	}

	public void setDay ( int day ) {
		if ( day < 1 ) {
			this.day = 1;
		} else
			this.day = Math.min ( day, 31 );

		this.hasDate = true;
	}

	public int getHour () {
		return hour;
	}

	public void setHour ( int hour ) {
		this.hour = Math.min ( Math.abs ( hour ), 23 );
		this.hasTime = true;
	}

	public int getMinute () {
		return minute;
	}

	public void setMinute ( int minute ) {
		this.minute = Math.min ( Math.abs ( minute ), 59 );
		this.hasTime = true;
	}

	public int getSecond () {
		return second;
	}

	public void setSecond ( int second ) {
		this.second = Math.min ( Math.abs ( second ), 59 );
		this.hasTime = true;
	}

	public int getNanoSecond () {
		return nanoSeconds;
	}

	public void setNanoSecond ( int nanoSecond ) {
		this.nanoSeconds = nanoSecond;
		this.hasTime = true;
	}

	public int compareTo ( Object dt ) {
		long d = getCalendar ().getTimeInMillis ()
						- ( (XMPDateTime) dt ).getCalendar ().getTimeInMillis ();
		if ( d == 0 ) {
			d = nanoSeconds - ( (XMPDateTime) dt ).getNanoSecond ();
		}
		return (int) Math.signum ( d );
	}

	public TimeZone getTimeZone () {
		return timeZone;
	}

	public void setTimeZone ( TimeZone timeZone ) {
		this.timeZone = timeZone;
		this.hasTime = true;
		this.hasTimeZone = true;
	}

	public boolean hasDate () {
		return this.hasDate;
	}

	public boolean hasTime () {
		return this.hasTime;
	}

	public boolean hasTimeZone () {
		return this.hasTimeZone;
	}

	public Calendar getCalendar () {
		GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance ( Locale.US );
		calendar.setGregorianChange ( new Date ( Long.MIN_VALUE ) );
		if ( hasTimeZone ) {
			calendar.setTimeZone ( timeZone );
		}
		calendar.set ( Calendar.YEAR, year );
		calendar.set ( Calendar.MONTH, month - 1 );
		calendar.set ( Calendar.DAY_OF_MONTH, day );
		calendar.set ( Calendar.HOUR_OF_DAY, hour );
		calendar.set ( Calendar.MINUTE, minute );
		calendar.set ( Calendar.SECOND, second );
		calendar.set ( Calendar.MILLISECOND, nanoSeconds / 1000000 );

		return calendar;
	}

	public String getISO8601String () {
		return ISO8601Converter.render ( this );
	}

	public String toString () {
		return getISO8601String ();
	}
}

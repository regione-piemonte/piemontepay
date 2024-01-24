/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp;

import java.util.Calendar;
import java.util.TimeZone;


public interface XMPDateTime extends Comparable {

	int getYear ();

	void setYear ( int year );

	int getMonth ();

	void setMonth ( int month );

	int getDay ();

	void setDay ( int day );

	int getHour ();

	void setHour ( int hour );

	int getMinute ();

	void setMinute ( int minute );

	int getSecond ();

	void setSecond ( int second );

	int getNanoSecond ();

	void setNanoSecond ( int nanoSecond );

	TimeZone getTimeZone ();

	void setTimeZone ( TimeZone tz );

	boolean hasDate ();

	boolean hasTime ();

	boolean hasTimeZone ();

	Calendar getCalendar ();

	String getISO8601String ();
}

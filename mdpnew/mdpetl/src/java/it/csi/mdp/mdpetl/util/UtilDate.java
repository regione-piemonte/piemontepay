/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class UtilDate {

	public static Date parseDate(String date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		if (date == null)
			return null;

		try {
			return sdf.parse(date);
		} catch (Exception e) {
		}

		return null;
	}

	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		if (date == null)
			return null;

		return sdf.format(date);
	}

	public static String formatDateByString(String date, String patternOrigin,
		String patternDest) {
		return formatDate(parseDate(date, patternOrigin), patternDest);
	}

	public static String formatDateByString(String date) {
		return formatDate(parseDate(date, "yyyy-MM-dd"), "dd/MM/yyyy");
	}

	public static String formatDateByString(Date date) {
		if(date!=null){
			return formatDate(parseDate(date.toString(), "yyyy-MM-dd"), "dd/MM/yyyy");
		}
		return null;
	}

	public static Integer getAnnoCorrente(){
		Calendar cal = Calendar.getInstance();
		return cal.get( Calendar.YEAR );
	}

	public static Timestamp convertStringToTimestamp(String str_date){
		DateFormat formatter ; 
		formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		try {
			return new Timestamp((formatter.parse(str_date)).getTime());
		} catch (ParseException e) {
			return null;
		}
	}

	public static Timestamp convertStringToTimestamp(String str_date,String formato){
		DateFormat formatter ; 
		formatter = new SimpleDateFormat(formato);
		try {
			return new Timestamp((formatter.parse(str_date)).getTime());
		} catch (ParseException e) {
			return null;
		}
	}


	public static boolean checkData(String data) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			formatter .setLenient(false) ;
			formatter.parse(data) ;
			return true;
		} catch ( Exception exception) {
			return false;
		}
	}

	public static String formatCalendar ( Calendar calendardate, String format ) {
		String strdate = "";

		SimpleDateFormat sdf = new SimpleDateFormat ( format );
		if ( calendardate != null ) {
			strdate = sdf.format ( calendardate.getTime () );
		}
		return strdate;
	}

	public static XMLGregorianCalendar convertTimestampToXmlGregorianCalendar ( Timestamp timestamp ) {
		if ( timestamp == null ) {
			return null;
		}

		try {
			final GregorianCalendar calendar = new GregorianCalendar ();
			calendar.setTimeInMillis ( timestamp.getTime () );
			return DatatypeFactory.newInstance ().newXMLGregorianCalendar (
				calendar );
		} catch ( final DatatypeConfigurationException ex ) {
			throw new RuntimeException ( "Unable to convert date '%s' to an XMLGregorianCalendar object" );
		}
	}
	
	public static XMLGregorianCalendar convertiTimestampInXmlGregorianCalendar ( Timestamp dateToConvert ) throws DatatypeConfigurationException {
        GregorianCalendar c = new GregorianCalendar ();
        c.setTime ( dateToConvert );
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance ().newXMLGregorianCalendar ( c );
        xmlDate.setTime ( DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED,
            DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED );
        xmlDate.setTimezone ( DatatypeConstants.FIELD_UNDEFINED );
        return xmlDate;
    }
}

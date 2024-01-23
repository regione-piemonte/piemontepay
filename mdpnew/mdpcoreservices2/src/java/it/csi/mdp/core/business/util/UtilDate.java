/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.*;
public class UtilDate {
/**
 * 
 * @param calendardate
 * @return
 */
	public static String getConvertCalendarToString(Calendar calendardate){
		String strdate = "";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		if (calendardate != null) {
			strdate = sdf.format(calendardate.getTime());
		} 
		return strdate;
	}
	
	//("dd/MM/yyyy HH:mm:ss")
	public static String getConvertCalendarToString(Calendar calendardate,String formato){
		String strdate = "";

		SimpleDateFormat sdf = new SimpleDateFormat(formato);

		if (calendardate != null) {
			strdate = sdf.format(calendardate.getTime());
		} 
		return strdate;
	}
	
	/**
	 * 
	 * @param calendardate
	 * @return Calendar
	 */
	public static Calendar getConvertStringToCalendar(String calendardate) {
		Calendar cal = null;
		DateFormat formatter;
		Date date;
		try {
			if(calendardate!=null && !calendardate.trim().equals("")){
				formatter = new SimpleDateFormat("dd/MM/yyyy");
				date = (Date) formatter.parse(calendardate);
				cal = Calendar.getInstance();
				cal.setTime(date);
			}
		} catch (ParseException e) {
			System.out.println("Exception :" + e);
		} 
		return cal;
	}

	/**
	 * 
	 * @param calendardate
	 * @return Calendar
	 */
	public static Date getConvertStringToDate(String calendardate) {
		DateFormat formatter;
		Date date = new Date();
		try {
			if(calendardate!=null && !calendardate.trim().equals("")){
				formatter = new SimpleDateFormat("dd/MM/yyyy");
				date = (Date) formatter.parse(calendardate);
			}
		} catch (ParseException e) {
			System.out.println("Exception :" + e);
		} 
		return date;
	}
	
	/**
	 * 
	 * @param value
	 * @param day
	 * @return
	 */
	public static Calendar addDay(Calendar value , int day) {
		if(value!=null){
			value.add(value.DATE, day);
		}
		return value;
	}
	
	/**
	 * 
	 * @param eventDate
	 * @param day
	 * @return
	 */
	public static  Date addDay(Date eventDate , int day) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(eventDate);
			calendar.add(Calendar.DATE, day);
			return calendar.getTime();
	}
	
    public  static String convertNull(String value,String replace){
		if(value==null || value.trim().equals("")){
			return replace;
		}
		return value;
	}
}
	

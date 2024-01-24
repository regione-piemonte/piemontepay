/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.util;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.*;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
public class UtilDate {


	protected static Logger log = Logger.getLogger(Constants.APPLICATION_CODE);
	
/**
 * 
 * @param calendardate
 * @return
 */
public static String  getConvertXMLGregorianCalendarToString(XMLGregorianCalendar calendardate){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(calendardate.toGregorianCalendar().getTime());
	}

	public static String  getConvertXMLGregorianCalendarToString(XMLGregorianCalendar calendardate,String formato){
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		return sdf.format(calendardate.toGregorianCalendar().getTime());
	}
	
	
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
		Date date = null;
		
		log.info("getConvertStringToDate calendardate " + calendardate+"**");
		
        if(calendardate!=null && !calendardate.trim().equals("")){
			try {
				if(calendardate!=null && !calendardate.trim().equals("")){
					formatter = new SimpleDateFormat("dd/MM/yyyy");
					date = (Date) formatter.parse(calendardate);
				}
			} catch (ParseException e) {
				//date = null;
				log.error("Exception " + e);
			}
        }
		return date;
	}
	
	/**
	 * 
	 * @param value
	 * @param day
	 * @return
	 */
	public  Calendar addDay(Calendar value , int day) {
		if(value!=null){
			value.add(value.DATE, day);
		}
		return value;
	}
	
	public static String controlloDate (String dataDa1,String dataA1){
		log.info("controlloDate Start ");
		
		String ris ="";

		long longDataDa1 = getConvertStringToDate(dataDa1).getTime();
		
		if(dataA1==null || dataA1.trim().equals("")){
			dataA1="19/08/2974";
		}
		long longDataA1 = getConvertStringToDate(dataA1).getTime();
		
		
		log.info("controlloDate longDataDa1 " + dataDa1);
		log.info("controlloDate longDataA1 " + dataA1);

		log.info("controlloDate longDataDa1 " + longDataDa1);
		log.info("controlloDate longDataA1 " + longDataA1);

		
		
		if(longDataDa1>longDataA1){
			ris ="Errore la data Da deve essere inferiore o uguale alla data A";
			log.info("err0 " + ris);
		}
		log.info("controlloDate stop ");

		return ris;		
	}
	
	public static String controlloSovrapposizioneDate (String dataDa1,String dataA1,String dataDa2,String dataA2){
		log.info("controlloSovrapposizioneDate Start");
		String ris ="";
		String data1000 = "19/08/2974";
		long longDataDa1 = 0;
		long longDataA1 = 0;
		long longDataDa2 = 0;
		long longDataA2 = 0;
		long longdata1000 = getConvertStringToDate(data1000).getTime();	
		
		log.info("dataDa1" +  dataDa1);
		log.info("dataA1" +  dataA1);
		log.info("dataDa2" +  dataDa2);
		log.info("dataA2" +  dataA2);

		if(dataA1!=null && !dataA1.trim().equals("")){
			 longDataA1 = getConvertStringToDate(dataA1).getTime();
		}else{
			longDataA1 = longdata1000;
		}
		
		if(dataA2!=null && !dataA2.trim().equals("")){
			 longDataA2 = getConvertStringToDate(dataA2).getTime();
		}else{
			longDataA2 = longdata1000;
		}
		
			

		
		if(dataA1==null){
			 longDataA1 = longdata1000;
		}
		if(dataA2==null){
			 longDataA2 = longdata1000;
		}

		


		if((longDataDa1<=longDataDa2) && (longDataA1>=longDataDa2)){
			ris ="i periodi temporali risultano sovrapposti ";
			log.info("err1 " + ris);
		}
		
		if((longDataDa1>=longDataDa2) &&(longDataA2>=longDataDa1)){		
			ris ="i periodi temporali risultano sovrapposti ";
			log.info("err2 " + ris);
		}
		
		if((longDataDa2<=longDataDa1)&&(longDataA2>=longDataA1)){
			ris ="i periodi temporali risultano sovrapposti ";
			log.info("err3 " + ris);
		}

		if((longDataDa1<=longDataDa2)&&(longDataA1>=longDataA2)){
			ris ="i periodi temporali risultano sovrapposti ";
			log.info("err4 " + ris);
		}

		if(longDataDa1>longDataA1){
			ris ="Errore la data Da deve essere inferiore o uguale alla data A";
			log.info("err5 " + ris);
		}

		if(longDataDa2>longDataA2){
			ris ="Errore la data Da deve essere inferiore o uguale alla data A";
			log.info("err6 " + ris);
		}
		log.info("controlloSovrapposizioneDate Stop");
		return ris;		
	}
	
	
	public static void main (String[] args){
		String dataDa1 =  "12/12/2000";
		String dataA1  =  "12/12/2001";
		String dataDa2 =  "12/12/2002";
		String dataA2  =  "12/12/2003";
		System.out.println(controlloSovrapposizioneDate ( dataDa1, dataA1, dataDa2, dataA2));
	}
	
	
	
}



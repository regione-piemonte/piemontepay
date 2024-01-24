/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;

public class DateUtils {
	
	
	public static String getStringDate(XMLGregorianCalendar xmlGregCal){
		String stringDate = null;
		
		if(xmlGregCal != null){
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date javaDate = xmlGregCal.toGregorianCalendar().getTime();
			//Date javaDate = xmlGregCal.toGregorianCalendar(timezone, aLocale, defaults)
			stringDate = df.format(javaDate);
			
			
		}
		
		return stringDate;
	}
	
	public static String getStringDate(Date date){
		String stringDate = null;
		
		if(date != null){
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			//Date javaDate = xmlGregCal.toGregorianCalendar(timezone, aLocale, defaults)
			stringDate = df.format(date);
			
			
		}
		
		return stringDate;
	}
	
	public static XMLGregorianCalendar getXmlGregorianCalendarDate(String data) throws Exception{
		if (StringUtils.isBlank(data)) return null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		// da string a date
		Date date = formatter.parse(data);
		
		// da date a XML gregorian calendar
		GregorianCalendar gregory = new GregorianCalendar();
		gregory.setTime(date);

		XMLGregorianCalendar dataAtto = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);

		return dataAtto;
	}

}

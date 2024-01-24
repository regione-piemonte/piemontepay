/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpiuvsrv.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class Utility {

	
	
	public String generaIUV(){
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("RF");
		
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		
		
		Utility u = new Utility();
		
		//System.currentTimeMillis()
		
		//cal.get(Calendar.getInstance());
		
		/*
		 Date d=gc.getTime();
		 
		 String dataDiOggi = new SimpleDateFormat("dd/MM/yyyy").format(d);
		 System.out.println("ora "+dataDiOggi);
		 */
		 GregorianCalendar gc = new GregorianCalendar();
		 
		 System.out.println("ora "+gc.getTime());
		 
		 System.out.println("giorno giuliano "+gc.get(Calendar.DAY_OF_YEAR));
		 System.out.println("anno "+gc.get(Calendar.YEAR));
		 
		 //System.out.println(" riempizeri "+u.riempiZero("1",6));
		 
		// System.out.println(" riempizeri "+u.mettiZero("23", 9));
		// System.out.println(" riempizeri "+u.mettiZero("9", 9));
		// System.out.println(" riempizeri "+u.mettiZero("", 9));
		// System.out.println(" riempizeri "+u.mettiZero("553242", 9));
		 
		 System.out.println("data mia "+getDataGiuliana("10/01/2014"));
	}
	
	public static String getDataGiuliana(String dataOdierna){
		
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date = format.parse(dataOdierna);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			
			
			// levo le 2 cifre del millennio e tengo solo l'anno
			sb.append(String.valueOf(calendar.get(Calendar.YEAR)).substring(2))
			.append(paddingDay(calendar.get(Calendar.DAY_OF_YEAR)));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//System.out.println("data giulina ---> "+sb.toString());
		
		return sb.toString();
	}
	
	public static String paddingDay(int day){
		String dayString = String.valueOf(day);
		
		if (day < 10)
			dayString = "00" + dayString;
		else if (day < 100)
			dayString = "0" + dayString;
		
		
		return dayString;
		
	}

	 public static String mettiZero(String s, int lunghCampo){
		 
		 String ris ="";
		 
		 if(null!=s){
			 //System.out.println(" lunghezza stringa "+ s.length());
			 
			 int zeriMancanti =lunghCampo -  s.length();
			 
			 //System.out.println(" zeri mancanti "+zeriMancanti);
			 
			 ris = s;
			 while (zeriMancanti>0) {
				
				 ris = 0 +ris;
				 zeriMancanti--;
				
			}
			 
		 }
		 
		 return ris;
		 
	 }
	 
	 
	
	 
	/*
	private String getCurrentDate() {
		Calendar cal = new GregorianCalendar();
		int era = cal.get(Calendar.ERA);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		String giorno = "" + day;
		String mese = "" + month;
		if (giorno.length() < 2) {
			giorno = "0" + giorno;
		}
		if (mese.length() < 2) {
			mese = "0" + mese;
		}
		return giorno + "/" + mese + "/" + year;
		//return dataDelGiorno;
	}
	*/
	protected Logger getLogger(String subsystem){
		  if (subsystem!=null)
		  	return Logger.getLogger(MdpiuvConstants.LOGGER_PREFIX+"."+subsystem);
		  else
		    return Logger.getLogger(MdpiuvConstants.LOGGER_PREFIX);
		}
}

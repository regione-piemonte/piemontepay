/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;

public class PDFUtil {

	// costanti
		static public final String APPLICATION_CODE = "epaypapdfgen";
		static public final String JOB_CONFIGFILENAME = "job-config.xml";
		static public final String UTENTE_INSERIMENTO = "SYSTEM";
		static public final String COD_ESITO_OK = "000";
		static public final String DET_ESITO_OK = "OK";

		// formati temporali
		static public final String DATE_Y4M2D2 = "yyyyMMdd";
		static public final String DATE_FORMAT = "dd/MM/yyyy";
		static public final String TIME_FORMAT = "HH:mm:ss";
		static public final String DATETIME_FORMAT = DATE_FORMAT + " " + TIME_FORMAT;
		
		// formati numerici
		static public final String BIGDECIMAL_FORMAT = "#.##";

	    static public final String TEN_DOUBLE_FORMAT = "0000000000";
	    
	    static public final String ELEVEN_DOUBLE_FORMAT = "00000000000";

	    static public final String TWELVE_LONG_FORMAT = "000000000000";
		// formato email
		static public final String EMAIL_FORMAT = ".+@.+";
		
//		public static final String EMAIL_FORMAT = "[a-zA-Z0-9_\\.\\+\\-]+@[a-zA-Z0-9\\-]+(\\.[a-zA-Z0-9\\-]+)*";



		static public String date2str(Date date, String format) {
			String str = null;

			if (date != null) {
				str = (new SimpleDateFormat(format)).format(date);
			}

			return str;
		}

		static public String date2strdate(Date date) {
			return date2str(date, DATE_FORMAT);
		}

		static public String date2strtime(Date date) {
			return date2str(date, TIME_FORMAT);
		}

		static public String date2strdatetime(Date date) {
			return date2str(date, DATETIME_FORMAT);
		}

		static public String calendar2str(GregorianCalendar calendar, String format) {
			String str = null;

			if (calendar != null) {
				str = (new SimpleDateFormat(format)).format(calendar.getTime());
			}

			return str;
		}

		static public String calendar2strdate(GregorianCalendar calendar) {
			return calendar2str(calendar, DATE_FORMAT);
		}

		static public String calendar2strtime(GregorianCalendar calendar) {
			return calendar2str(calendar, TIME_FORMAT);
		}

		static public String calendar2strdatetime(GregorianCalendar calendar) {
			return calendar2str(calendar, DATETIME_FORMAT);
		}

		static public String bd2str(BigDecimal bd, String format) {
			String str = null;

			if (bd != null) {
				str = (new DecimalFormat(format)).format(bd);
			}

			return str;
		}

		static public String bd2str(BigDecimal bd) {
			return bd2str(bd, BIGDECIMAL_FORMAT);
		}

		static public BigDecimal str2bdOrNull(String str) {
			BigDecimal bd;

			if (str != null) {
				try {
					bd = new BigDecimal(str);
		
				} catch (NumberFormatException e) {
					bd = null;
				}

			} else
				bd = null;
			
			return bd;
		}

	    static public String doubleWithTenZeros ( double db ) {
	        DecimalFormat df = new DecimalFormat ( TEN_DOUBLE_FORMAT );
	        return df.format ( db * 100.0 );
	    }
	    
	    static public String doubleWithElevenZeros ( double db ) {
            DecimalFormat df = new DecimalFormat ( ELEVEN_DOUBLE_FORMAT);
            return df.format ( db * 100.0 );
        }
	    

	    static public String longWithTwelveZeros ( Long db ) {
	        DecimalFormat df = new DecimalFormat ( TWELVE_LONG_FORMAT );
	        return df.format ( db );
	    }
		static public String quote(String value) {
			StringBuilder sb = new StringBuilder();
			if (value != null) {
				sb.append("\"");
				sb.append(value);
				sb.append("\"");
			} else {
				sb.append("null");
			}
			return sb.toString();
		}

		static public Date getLaterDate(Date date) {
			Date laterDate = null;
			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.DATE, 1);
				laterDate = calendar.getTime();
			}
			return laterDate;
		}

		static public String formatException(Exception e) {
			return formatException("", e);
		}

		static public String formatException(String msg, Exception e) {
			return msg + e.getMessage() != null ? e.getClass().getName() + ": " + e.getMessage() : e.getClass().getName();
		}

	    public static Date xmlGregorianCalendarToDate ( XMLGregorianCalendar xmlGregorianCalendar ) {
	        return null != xmlGregorianCalendar ? xmlGregorianCalendar.toGregorianCalendar ().getTime () : null;
	    }
}

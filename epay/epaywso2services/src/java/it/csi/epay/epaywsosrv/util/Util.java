/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Util {
	// codice identificativo applicazione
	static public final String APPLICATION_CODE = "epaywsosrv";

	// nome file configurazione per il monitoraggio
	static public final String MONITORING_CONFIGFILENAME = "monitoring.xml";

	// formati temposrali
	static public final String DATE_FORMAT = "dd/MM/yyyy";
	static public final String TIME_FORMAT = "HH:mm:ss";
	static public final String DATETIME_FORMAT = DATE_FORMAT + " " + TIME_FORMAT;

	// ci sono applicativi che sono abilitati a gestire piu codice di versamento, a questi applicativi viene associato questo codice di versamento speciale (seppure fittizio) 
	static public final String SPECIAL_COD_VERSAMENTO = "****";

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

	/**
	 * Due oggetti sono considetati uguali se coincidono come riferimento,
	 * oppure se sono entrambi null, o sono entrambi diversi da null ma uguali nei valori
	 * Assunzione: gli oggetti devono essere dello stesso tipo (classe)
	 * @param obj1
	 * @param obj2
	 * @return true o false a seconda che sia verificata o meno il criterio sopra descritto
	 */
	static public boolean areEquals(Object obj1, Object obj2) {
		return (obj1 == obj2) || ((obj1 == null) ? (obj2 == null) : (obj2 != null && obj1.equals(obj2)));
	}

}

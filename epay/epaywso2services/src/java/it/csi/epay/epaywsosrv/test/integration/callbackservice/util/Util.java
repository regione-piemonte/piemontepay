/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.test.integration.callbackservice.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Util {
	// codice identificativo applicazione
	static public final String APPLICATION_CODE = "epaywsosrv";

	// nome file configurazione per il monitoraggio
	static public final String MONITORING_CONFIGFILENAME = "monitoring.xml";

	// formati temposrali
	static private final String DATE_FORMAT = "dd/MM/yyyy";
	static private final String TIME_FORMAT = "HH:mm:ss";
	static private final String DATETIME_FORMAT = DATE_FORMAT + " " + TIME_FORMAT;

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

	static public String quote(Object value) {
		StringBuffer sb = new StringBuffer();
		if (value != null) {
			if (value instanceof java.lang.String) {
				sb.append("\"");
			}
			sb.append(value);
			if (value instanceof java.lang.String) {
				sb.append("\"");
			}
		} else {
			sb.append("null");
		}
		return sb.toString();
	}

}

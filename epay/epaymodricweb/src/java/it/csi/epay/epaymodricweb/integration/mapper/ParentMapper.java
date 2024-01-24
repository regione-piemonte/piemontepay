/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.integration.mapper;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

public abstract class ParentMapper {

	protected static String coalesce(String raw, String defVal) {
		if (raw == null || raw.isEmpty()) {
			return defVal;
		} else {
			return raw;
		}
	}
	
	protected static void copyProperties(Object dest, Object source) {
		try {
			PropertyUtils.copyProperties(dest, source);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	protected static Boolean fromFlagToBoolean(String flag) {
		if ("S".equals(flag)) return true;
		if ("N".equals(flag)) return null;
		return null;
	}
	
	protected static Boolean fromFlagSNToBoolean(String flag) {
		if ("S".equals(flag)) return true;
		if ("N".equals(flag)) return false;
		return null;
	}

	protected static String fromBooleanToSN(Boolean b) {
		if (b == null) return null;
		if (b) return "S";
		return "N";
	}

	protected static String fromBooleanToS(Boolean b) {
		if (b == null) return null;
		if (b) return "S";
		return null;
	}

	protected static String fromBooleanToSN(boolean b) {
		if (b) return "S";
		return "N";
	}
	
	protected static String fromBooleanToS(boolean b) {
		if (b) return "S";
		return null;
	}
	
	protected static LocalDateTime calendarToLocalDateTime(XMLGregorianCalendar input) {
		if (input == null) return null;
		return LocalDateTime.ofInstant(input.toGregorianCalendar().toInstant(), ZoneId.systemDefault());
	}

	protected static Integer parseIntegerFromString(String raw) {
		if (StringUtils.isNotBlank(raw)) {
			Integer i = Integer.valueOf(
					raw.replaceAll("\\.", "")
				);
			return Integer.valueOf(i);
		} else {
			return null;
		}
	}

	protected static Long parseLongFromString(String raw) {
		if (StringUtils.isNotBlank(raw)) {
			return Long.valueOf(raw);
		} else {
			return null;
		}
	}

	protected static Double parseDoubleFromCurrencyString(String raw) {
		if (StringUtils.isNotBlank(raw)) {
			Double d = Double.valueOf(
				raw
				.replaceAll("\\.", "")
				.replaceAll(",", ".")
			);
			return Double.valueOf(Math.round(d * 100.0) / 100.0);
		} else {
			return null;
		}
	}
	
	protected static Date parseDateFromString(String raw) throws ParseException {
		if (StringUtils.isBlank(raw)) {
			return null;
		}
		final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
		SimpleDateFormat dtf = new SimpleDateFormat(DATE_FORMAT);
		try {
			return dtf.parse(raw);
		} catch (ParseException e) {
			throw new ParseException("Errore nel parsing della stringa [" + raw + "] a data con formato " + DATE_FORMAT, e.getErrorOffset());
		}
	}

	protected static String serializeDateToString(Date raw) {
		if (raw == null) {
			return null;
		}
		final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
		SimpleDateFormat dtf = new SimpleDateFormat(DATE_FORMAT);
		return dtf.format(raw);
	}
	
	protected static String cleanString(String raw) {
		if (StringUtils.isBlank(raw)) {
			return null;
		} else {
			return raw;
		}
	}

	protected static Double parseDoubleFromBigDecimal(BigDecimal raw) {
		if (raw == null) {
			return null;
		}
		return Double.valueOf(Math.round(raw.doubleValue() * 100.0) / 100.0);
	}

	public static Date parseDateFromXmlGregorianCalendar(XMLGregorianCalendar raw) {
		if (raw == null) {
			return null;
		}
		return raw.toGregorianCalendar().getTime();
	}

	public static XMLGregorianCalendar parseXMLGregorianCalendarFromDate(Date raw) {
		if (raw == null) {
			return null;
		}
		
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(raw);
		try {
			XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			return date2;
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException(e);
		}
	}
}

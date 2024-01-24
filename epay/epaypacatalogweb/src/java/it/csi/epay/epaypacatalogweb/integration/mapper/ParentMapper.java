/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.integration.mapper;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.beanutils.PropertyUtils;

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
		if ("S".equals(flag)) {
            return true;
        }
		if ("N".equals(flag)) {
            return null;
        }
		return null;
	}

	protected static Boolean fromFlagSNToBoolean(String flag) {
		if ("S".equals(flag)) {
            return true;
        }
		if ("N".equals(flag)) {
            return false;
        }
		return null;
	}

	protected static String fromBooleanToSN(Boolean b) {
		if (b == null) {
            return null;
        }
		if (b) {
            return "S";
        }
		return "N";
	}

	protected static String fromBooleanToS(Boolean b) {
		if (b == null) {
            return null;
        }
		if (b) {
            return "S";
        }
		return null;
	}

	protected static String fromBooleanToSN(boolean b) {
		if (b) {
            return "S";
        }
		return "N";
	}

	protected static String fromBooleanToS(boolean b) {
		if (b) {
            return "S";
        }
		return null;
	}

	protected static LocalDateTime calendarToLocalDateTime(XMLGregorianCalendar input) {
		if (input == null) {
            return null;
        }
		return LocalDateTime.ofInstant(input.toGregorianCalendar().toInstant(), ZoneId.systemDefault());
	}

    protected static Date calendarToDate ( XMLGregorianCalendar input ) {
        if ( input == null ) {
            return null;
        }
        return input.toGregorianCalendar ().getTime ();
    }

	public static String daGiornoAData(String giornoSchedulazione) {
		if (giornoSchedulazione == null || giornoSchedulazione.isEmpty()) {
			return null;
		}

		LocalDate date = LocalDate.of(LocalDate.now().getYear(), 1, 1).plusDays(Integer.valueOf(giornoSchedulazione) - 1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
		return date.format(formatter);
	}

	public static String daDataAGiorno(String giornoSchedulazione) {
		if (giornoSchedulazione == null || giornoSchedulazione.isEmpty()) {
			return null;
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(giornoSchedulazione + "/" + LocalDate.now().getYear(), formatter);

		return "" + date.getDayOfYear();
	}

    public static XMLGregorianCalendar dateToCalendar ( Date date ) {
        if ( date == null ) {
            return null;
        }
        GregorianCalendar c = new GregorianCalendar ();
        c.setTime ( date );
        XMLGregorianCalendar date2;
        try {
            date2 = DatatypeFactory.newInstance ().newXMLGregorianCalendar ( c );
        } catch ( DatatypeConfigurationException e ) {
            throw new RuntimeException ( e );
        }
        return date2;
    }

}

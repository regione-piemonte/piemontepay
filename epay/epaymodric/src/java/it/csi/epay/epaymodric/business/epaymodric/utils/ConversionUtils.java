/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


/**
 *
 * @author vsgro
 */
public class ConversionUtils {

    final static Logger logger = LogManager.getLogger(ConversionUtils.class);

    private static final String PATTERN_TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSSSSS";
    private static final String PATTERN_DATE = "yyyy-MM-dd";

    public static XMLGregorianCalendar convertDateToXmlGregorianCalendar ( Date date ) {
        if ( date == null ) {
            return null;
        }

        try {
            final GregorianCalendar calendar = new GregorianCalendar ();
            calendar.setTimeInMillis ( date.getTime () );
            return DatatypeFactory.newInstance ().newXMLGregorianCalendar (
                calendar );
        } catch ( final DatatypeConfigurationException ex ) {
            throw new RuntimeException ( "Unable to convert date '%s' to an XMLGregorianCalendar object" );
        }
    }

    public static XMLGregorianCalendar convertTimestampToXmlGregorianCalendar(Timestamp timestamp) {
        if ( timestamp == null ) {
            return null;
        }

        try {
            final GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(timestamp.getTime());
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(
                calendar);
        }
        catch (final DatatypeConfigurationException ex) {
            throw new RuntimeException("Unable to convert date '%s' to an XMLGregorianCalendar object");
        }
    }

    public static String convertTimeStampToString(Timestamp timestamp) {
        String toReturn = null;
        if (timestamp != null) {
            try {
            toReturn =
                new SimpleDateFormat(PATTERN_TIMESTAMP, Locale.ITALY)
                        .format(new Date(timestamp.getTime ())
                );
            } catch (Exception e) {
                logger.error ("ConversionUtils.convertTimeStampToString: " + e.getMessage ());
            }
        }
        return toReturn;
    }

    public static Timestamp convertStringToTimeStamp(String valueString) {
        Timestamp toReturn = null;
        if (valueString != null) {
            try {
                toReturn = new Timestamp(
                    new SimpleDateFormat(PATTERN_TIMESTAMP, Locale.ITALY).parse(valueString).getTime ()
                );
            } catch (Exception e) {
                logger.error ("ConversionUtils.convertStringToTimeStamp: " + e.getMessage ());
            }
        }
        return toReturn;
    }

    public static Date convertStringTimestampToDate(String valueString) {
        Date toReturn = null;
        if (valueString != null) {
            try {
                toReturn = new Date(
                    new SimpleDateFormat(PATTERN_DATE, Locale.ITALY).parse(valueString).getTime ()
                );
            } catch (Exception e) {
                logger.error ("ConversionUtils.convertStringTimestampToDate: " + e.getMessage ());
            }
        }
        return toReturn;
    }

    public static String convertNumberToString(Object num) {
        String toReturn = null;
        if (num != null) {
            try {
                if (num instanceof Long
                    || num instanceof Integer
                ) {
                    toReturn = num.toString ();
                } else if (num instanceof BigDecimal) {
                    Format format = NumberFormat.getNumberInstance (Locale.ITALY);
                    toReturn = format.format(num);
                } else {
                    logger.warn ( "ConversionUtils.convertNumberToString: tipo non gestito per: "+num.getClass () );
                }
            } catch (Exception e) {
                logger.error ("ConversionUtils.convertNumberToString: " + e.getMessage ());
            }
        }
        return toReturn;
    }

    public static Object convertStringToNumber(String valueString, @SuppressWarnings ( "rawtypes" ) Class tipoOggetto) {
        Object toReturn = null;
        if (valueString != null) {
            try {
                if (tipoOggetto.getName ().equals ( Long.class.getName () ) ) {
                    toReturn = Long.valueOf ( valueString );
                } else if (tipoOggetto.getName ().equals ( Integer.class.getName () ) ) {
                    toReturn = Integer.valueOf ( valueString );
                } else if (tipoOggetto.getName ().equals ( BigDecimal.class.getName () ) ) {
                	DecimalFormat df = new DecimalFormat("###,###.00");
                	df.setParseBigDecimal(true);
                    BigDecimal retvalue = (BigDecimal) df.parse(valueString, new ParsePosition(0));
                    toReturn = retvalue;
                } else {
                    logger.warn ( "ConversionUtils.convertStringToNumber: tipo non gestito per: "+valueString.getClass () );
                }
            } catch (Exception e) {
                logger.error ("ConversionUtils.convertStringToNumber: " + e.getMessage ());
            }
        }
        return toReturn;
    }

    public static String convertBooleanToString(Boolean bool) {
        String toReturn = "false";
        if (bool != null) {
            try {
                toReturn = bool ? "true" : "false";
            } catch (Exception e) {
                logger.error ("ConversionUtils.convertBooleanToString: " + e.getMessage ());
            }
        }
        return toReturn;
    }

    public static Boolean convertStringToBoolean(String valueString) {
        Boolean toReturn = false;
        if (valueString != null) {
            try {
                toReturn = Boolean.valueOf ( valueString );
            } catch (Exception e) {
                logger.error ("ConversionUtils.convertStringToBoolean: " + e.getMessage ());
            }
        }
        return toReturn;
    }
    
    public static String convertXmlGregorianCalendarToString(XMLGregorianCalendar calendar) {
        String toReturn = null;
        if (calendar != null) {
            try {
            	
            toReturn =
                new SimpleDateFormat(PATTERN_DATE, Locale.ITALY)
                        .format(calendar.toGregorianCalendar().getTime());
            } catch (Exception e) {
                logger.error ("ConversionUtils.convertTimeStampToString: " + e.getMessage ());
            }
        }
        return toReturn;
    }

    public static XMLGregorianCalendar convertStringToXmlGregorianCalendar ( String calendar ) {
        XMLGregorianCalendar toReturn = null;
        if ( calendar != null ) {
            try {
                SimpleDateFormat format = new SimpleDateFormat ( PATTERN_DATE, Locale.ITALY );
                Date date = format.parse ( calendar );
                GregorianCalendar cal = new GregorianCalendar ();
                cal.setTime ( date );
                toReturn = DatatypeFactory.newInstance ().newXMLGregorianCalendar ( cal );
            } catch ( Exception e ) {
                logger.error ( "ConversionUtils.convertTimeStampToString: " + e.getMessage () );
            }
        }
        return toReturn;
    }

}

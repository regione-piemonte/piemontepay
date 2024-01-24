/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class DateUtils {

    private final static Logger logger = Logger.getLogger ( DateUtils.class );

    public static String getStringDate ( XMLGregorianCalendar xmlGregCal ) {
        String stringDate = null;

        if ( xmlGregCal != null ) {
            DateFormat df = new SimpleDateFormat ( "dd/MM/yyyy" );
            Date javaDate = xmlGregCal.toGregorianCalendar ().getTime ();
            //Date javaDate = xmlGregCal.toGregorianCalendar(timezone, aLocale, defaults)
            stringDate = df.format ( javaDate );

        }

        return stringDate;
    }
    

    public static Date getDate ( XMLGregorianCalendar xmlGregCal ) {
        Date date = null;

        if ( xmlGregCal != null ) {
            date = xmlGregCal.toGregorianCalendar ().getTime ();
        }

        return date;
    }    

    public static XMLGregorianCalendar getXmlGregorianCalendarDate ( String data ) throws Exception {
        if ( StringUtils.isBlank ( data ) ) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat ( "dd/MM/yyyy" );

        // da string a date
        Date date = formatter.parse ( data );

        // da date a XML gregorian calendar
        GregorianCalendar gregory = new GregorianCalendar ();
        gregory.setTime ( date );

        XMLGregorianCalendar dataAtto = DatatypeFactory.newInstance ().newXMLGregorianCalendar ( gregory );

        return dataAtto;
    }

    public static XMLGregorianCalendar getXmlGregorianCalendarDateTime ( String data ) throws Exception {
        if ( StringUtils.isBlank ( data ) ) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat ( "dd/MM/yyyy HH:mm:ss" );

        // da string a date
        Date date = formatter.parse ( data );

        // da date a XML gregorian calendar
        GregorianCalendar gregory = new GregorianCalendar ();
        gregory.setTime ( date );

        XMLGregorianCalendar dataAtto = DatatypeFactory.newInstance ().newXMLGregorianCalendar ( gregory );

        return dataAtto;
    }

    public static XMLGregorianCalendar getNowXmlGregorianCalendar () throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar ();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance ();
        XMLGregorianCalendar now = datatypeFactory.newXMLGregorianCalendar ( gregorianCalendar );
        return now;
    }

    /** Usato perch in alcuni casi ci arriva un XMLGregorianCalendar senza l'orario. */
    public static XMLGregorianCalendar setTime ( XMLGregorianCalendar date ) throws DatatypeConfigurationException {
        GregorianCalendar c = new GregorianCalendar ();
        c.setTime ( date.toGregorianCalendar ().getTime () );
        return DatatypeFactory.newInstance ().newXMLGregorianCalendar ( c );
    }


    public static XMLGregorianCalendar getXmlGregorianCalendarDate ( Date data ) {
        if ( null == data ) {
            return null;
        }

        // da date a XML gregorian calendar
        GregorianCalendar gregory = new GregorianCalendar ();
        gregory.setTime ( data );

        XMLGregorianCalendar dataAtto = null;
        try {
            dataAtto = DatatypeFactory.newInstance ().newXMLGregorianCalendar ( gregory );
        } catch ( DatatypeConfigurationException e ) {
            logger.error ( "Errore in fase di conversione della data: " + data, e );
        }
        return dataAtto;
    }

}

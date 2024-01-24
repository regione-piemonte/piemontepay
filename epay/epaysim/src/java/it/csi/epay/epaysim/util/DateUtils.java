/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.util;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Classe di utility per la trasformazione di diversi tipi di date.
 */
public class DateUtils {

    /**
     * Metodo che consente di trasformare un java.sql.Timestamp in XmlGregorianCalendar.
     * 
     * @param time input
     * @return XmlGregorianCalendar trasformato.
     * @throws DatatypeConfigurationException exception.
     */
    public static XMLGregorianCalendar getXmlGregorianCalendar ( Timestamp time ) throws DatatypeConfigurationException {
        if ( time == null ) {
            return null;
        } else {
            GregorianCalendar gregorianCalendar = new GregorianCalendar ();
            gregorianCalendar.setTimeInMillis ( time.getTime () );
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance ();
            XMLGregorianCalendar xmmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar ( gregorianCalendar );
            return xmmlGregorianCalendar;
        }
    }

    public static XMLGregorianCalendar dateToXMLGregorianCalendar ( Date date ) throws DatatypeConfigurationException {
        GregorianCalendar c = new GregorianCalendar ();
        c.setTime ( date );
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance ().newXMLGregorianCalendar ( c );
        return xmlDate;
    }

    public static Date xmlGregorianCalendarToDate ( XMLGregorianCalendar xmlGregorianCalendar ) {
        return xmlGregorianCalendar.toGregorianCalendar ().getTime ();
    }

    public static Timestamp xmlGregorianCalendarToTimestamp ( XMLGregorianCalendar xmlGregorianCalendar ) {
        Timestamp timestamp = new Timestamp ( xmlGregorianCalendarToDate ( xmlGregorianCalendar ).getTime () );
        return timestamp;
    }

    /**
     * Metodo che consente di trasformare un java.util.Date in XmlGregorianCalendar.
     * 
     * @param date input
     * @return XmlGregorianCalendar trasformato.
     * @throws DatatypeConfigurationException exception.
     */
    public static XMLGregorianCalendar getXmlGregorianCalendar ( Date date ) throws DatatypeConfigurationException {
        if ( date == null ) {
            return null;
        } else {
            GregorianCalendar gregorianCalendar = new GregorianCalendar ();
            gregorianCalendar.setTimeInMillis ( date.getTime () );
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance ();
            XMLGregorianCalendar xmmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar ( gregorianCalendar );
            return xmmlGregorianCalendar;
        }
    }

}

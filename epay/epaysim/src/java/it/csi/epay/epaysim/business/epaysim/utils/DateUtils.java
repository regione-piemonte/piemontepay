/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;


/**
 *
 * @author vsgro
 */
public class DateUtils {

    final static Logger logger = Logger.getLogger ( DateUtils.class );

    // Nuru : metodo che converte una String in Timestamp
    public static Timestamp stringToTimeStamp ( String strTime ) throws java.text.ParseException {
        Timestamp tempTimestamp = null;
        if ( strTime != null && !strTime.equals ( "" ) ) {
            //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dateFormat = new SimpleDateFormat ( "yyyy-MM-dd hh:mm:ss" );

            Date parsedTimeStamp = null;
            try {
                parsedTimeStamp = dateFormat.parse ( strTime.replace ( "\n", "" ) );
            } catch ( ParseException e ) {
                logger.info ( "DateUtils.Data non parsabile : errore" );
                return null;

            }

            tempTimestamp = new Timestamp ( parsedTimeStamp.getTime () );

        }
        return tempTimestamp;
    }

    //Lorenzo: conversione da Date a XMLGregorianCalendar (per WebServices)
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

    //Nuru Trasformazione date in  timestamp
    public static Timestamp dateToTimestamp ( Date data ) {
        java.sql.Timestamp ts = null;
        try {
            if ( data != null ) {
                ts = new Timestamp ( data.getTime () );
            }
        } catch ( Exception e ) {
            logger.error ( "Errore in fase di conversione della data in timestamp: ", e );
        }
        return ts;
    }

}

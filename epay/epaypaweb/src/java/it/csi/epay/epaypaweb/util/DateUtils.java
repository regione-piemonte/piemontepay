/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 *
 * @author vsgro
 */
public class DateUtils {

    final static Logger logger = LogManager.getLogger ( DateUtils.class );

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

    //Lorenzo: conversione da Date a XMLGregorianCalendar (per WebServices)
    public static XMLGregorianCalendar dateToXMLGregorianCalendar ( Date date ) throws DatatypeConfigurationException {
        if ( null == date ) {
            return null;
        } else {
            GregorianCalendar c = new GregorianCalendar ();
            c.setTime ( date );
			return DatatypeFactory.newInstance ().newXMLGregorianCalendar ( c );
        }
    }

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

    public static Date xmlGregorianCalendarToDate ( XMLGregorianCalendar xmlGregorianCalendar ) {
        return null != xmlGregorianCalendar ? xmlGregorianCalendar.toGregorianCalendar ().getTime () : null;
    }

    public static Timestamp xmlGregorianCalendarToTimestamp ( XMLGregorianCalendar xmlGregorianCalendar ) {
        if ( null != xmlGregorianCalendar ) {
			return new Timestamp ( xmlGregorianCalendarToDate ( xmlGregorianCalendar ).getTime () );
        } else {
            return null;
        }
    }

}

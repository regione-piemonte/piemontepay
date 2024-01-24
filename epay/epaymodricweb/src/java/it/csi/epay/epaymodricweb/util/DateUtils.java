/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class DateUtils {

    private final static Logger logger = LogManager.getLogger ( DateUtils.class );

    public static String getStringDate(XMLGregorianCalendar xmlGregCal){
        String stringDate = null;

        if(xmlGregCal != null){
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date javaDate = xmlGregCal.toGregorianCalendar().getTime();
            //Date javaDate = xmlGregCal.toGregorianCalendar(timezone, aLocale, defaults)
            stringDate = df.format(javaDate);


        }

        return stringDate;
    }

    public static XMLGregorianCalendar getXmlGregorianCalendarDate(String data) throws Exception{
        if (StringUtils.isBlank(data)) return null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        // da string a date
        Date date = formatter.parse(data);

        // da date a XML gregorian calendar
        GregorianCalendar gregory = new GregorianCalendar();
        gregory.setTime(date);

        XMLGregorianCalendar dataAtto = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);

        return dataAtto;
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

    public static Date getDateFromString ( String data ) {
        if ( StringUtils.isBlank ( data ) )
            return null;
        SimpleDateFormat formatter = new SimpleDateFormat ( "dd/MM/yyyy" );

        // da string a date
        try {
            return formatter.parse ( data );
        } catch ( ParseException e ) {
            e.printStackTrace ();
            return null;
        }
    }

}

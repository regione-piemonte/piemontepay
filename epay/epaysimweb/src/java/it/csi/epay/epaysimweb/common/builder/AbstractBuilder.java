/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.common.builder;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * interfaccia per tutte le classi builder.
 * 
 * @param <T> entita' principale
 */
public interface AbstractBuilder<T> {

    public abstract T build ();

    /**
     * Utility per convertire una java.util.Date in java.sql.Timestamp
     * 
     * @param date
     * @return Timestamp
     */
    default Timestamp getTimestamp ( Date date ) {
        return date == null ? null : new java.sql.Timestamp ( date.getTime () );
    }

    /**
     * Utility per convertire un XMLGregorianCalendar in java.util.Date
     * 
     * @param calendar da convertire
     * @return Date oggetto convertito oppure null.
     */
    default Date getDate ( XMLGregorianCalendar calendar ) {
        return null != calendar ? calendar.toGregorianCalendar ().getTime () : null;
    }

    /**
     * Utility per convertire un XMLGregorianCalendar in java.util.Date
     * 
     * @param calendar da convertire
     * @return Date oggetto convertito oppure null.
     */
    default XMLGregorianCalendar getXMLGregorianCalendar ( Date data ) {
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
            e.printStackTrace ();
        }
        return dataAtto;
    }

}

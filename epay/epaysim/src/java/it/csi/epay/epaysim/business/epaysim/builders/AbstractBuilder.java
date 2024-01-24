/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim.builders;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Classe astratta per tutte le classi builder.
 * 
 * @param <T> entita' principale
 */
public abstract class AbstractBuilder<T> {

    protected Date dataInserimento;

    protected Date dataModifica;

    protected String utenteInserimento;

    protected String utenteModifica;

    public abstract T build ();

    public AbstractBuilder<T> withDataInserimento ( Date dataInserimento ) {
        this.dataInserimento = dataInserimento;
        return this;
    }

    public AbstractBuilder<T> withDataInserimento ( Timestamp dataInserimento ) {
        this.dataInserimento = getDate ( dataInserimento );
        return this;
    }

    public AbstractBuilder<T> withDataModifica ( Date dataModifica ) {
        this.dataModifica = dataModifica;
        return this;
    }

    public AbstractBuilder<T> withDataModifica ( Timestamp dataModifica ) {
        this.dataModifica = getDate ( dataModifica );
        return this;
    }

    public AbstractBuilder<T> withUtenteInserimento ( String utenteInserimento ) {
        this.utenteInserimento = utenteInserimento;
        return this;
    }

    public AbstractBuilder<T> withUtenteModifica ( String utenteModifica ) {
        this.utenteModifica = utenteModifica;
        return this;
    }

    /**
     * Utility per convertire una java.util.Date in java.sql.Timestamp
     * 
     * @param date
     * @return Timestamp
     */
    protected Timestamp getTimestamp ( Date date ) {
        return date == null ? null : new java.sql.Timestamp ( date.getTime () );
    }

    /**
     * Utility per convertire un XMLGregorianCalendar in java.util.Date
     * 
     * @param calendar da convertire
     * @return Date oggetto convertito oppure null.
     */
    protected Date getDate ( XMLGregorianCalendar calendar ) {
        return null != calendar ? calendar.toGregorianCalendar ().getTime () : null;
    }

    /**
     * Utility per convertire un XMLGregorianCalendar in java.util.Date
     * 
     * @param calendar da convertire
     * @return Date oggetto convertito oppure null.
     */
    protected XMLGregorianCalendar getXMLGregorianCalendar ( Date data ) {
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

    /**
     * Utility per convertire un XMLGregorianCalendar in java.sql.Timestamp
     * 
     * @param calendar da convertire
     * @return Date oggetto convertito oppure null.
     */
    protected XMLGregorianCalendar getXMLGregorianCalendar ( Timestamp data ) {
        if ( null == data ) {
            return null;
        }
        return getXMLGregorianCalendar ( new Date ( data.getTime () ) );
    }

    /**
     * Utility per convertire un XMLGregorianCalendar in java.sql.Timestamp
     * 
     * @param calendar da convertire
     * @return Date oggetto convertito oppure null.
     */
    protected Date getDate ( Timestamp data ) {
        if ( null == data ) {
            return null;
        }
        return new Date ( data.getTime () );
    }
}

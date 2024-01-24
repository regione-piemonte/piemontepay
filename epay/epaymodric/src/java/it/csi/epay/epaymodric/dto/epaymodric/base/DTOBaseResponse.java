/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.base;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


//classe che contiene i dati di output al ritorno della chiamata al Webservice

@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoBaseResponse" )
public class DTOBaseResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    //private String esito = "OK";

    private String codice = "";

    private String descrizione = "";

    private String timestamp = new SimpleDateFormat ().format ( new Date () );

    //private String stacktrace = null;

    public DTOBaseResponse () {
        super ();
    }

    //    public String getEsito () {
    //        return esito;
    //    }
    //
    //    public void setEsito ( String esito ) {
    //        this.esito = esito;
    //    }

    public void setCodiceDescrizione(String codice,String descrizione) {
        this.codice = codice;
        this.descrizione = descrizione;
    }
    
    public String getCodice () {
        return codice;
    }

    //public void setCodice ( String codice ) {
    //    this.codice = codice;
    //}

    public String getDescrizione () {
        return descrizione;
    }

    //public void setDescrizione ( String descrizione ) {
    //    this.descrizione = descrizione;
    //}

    //    public String getStacktrace () {
    //        return stacktrace;
    //    }
    //
    //    public void setStacktrace ( String stacktrace ) {
    //        this.stacktrace = stacktrace;
    //    }

    public String getTimestamp () {
        return timestamp;
    }

    public void setTimestamp ( String timestamp ) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString () {
        return "DTOBaseResponse [codice=" + codice + ", descrizione=" + descrizione + ", timestamp=" + timestamp + "]";
    }

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.dto.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * @author vsgro
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoOutputWsEsito" )
public class DTOOutputWsEsito implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3669214391536565576L;

    private String esito;

    private String codiceErrore;

    private String descrizione;

    public DTOOutputWsEsito ( String esito, String codiceErrore, String descrizione ) {
        super ();
        this.esito = esito;
        this.codiceErrore = codiceErrore;
        this.descrizione = descrizione;
    }

    public DTOOutputWsEsito () {

    }

    public String getEsito () {
        return esito;
    }

    public void setEsito ( String esito ) {
        this.esito = esito;
    }

    public String getCodiceErrore () {
        return codiceErrore;
    }

    public void setCodiceErrore ( String codiceErrore ) {
        this.codiceErrore = codiceErrore;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ( "esito: " + esito + " " );
        stringBuffer.append ( "codiceErrore: " + codiceErrore + " " );
        stringBuffer.append ( "descrizione: " + descrizione + " " );
        return stringBuffer.toString ();
    }

}

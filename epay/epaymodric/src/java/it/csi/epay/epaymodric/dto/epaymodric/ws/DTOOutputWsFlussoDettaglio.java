/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOFlussoDettaglio;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOFlussoSintesi;


/**
 * 
 * @ author vsgro
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoOutputWsFlussoDettaglio" )
public class DTOOutputWsFlussoDettaglio implements Serializable {

    private static final long serialVersionUID = 6024907684664456910L;

    private DTOOutputWsEsito esito = null;

    private DTOFlussoSintesi flussoSintesi = new DTOFlussoSintesi();
    
    private ArrayList<DTOFlussoDettaglio> flussiDettaglio = new ArrayList<> ();

    public DTOOutputWsEsito getEsito () {
        return esito;
    }

    public void setEsito ( DTOOutputWsEsito esito ) {
        this.esito = esito;
    }

    public ArrayList<DTOFlussoDettaglio> getFlussiDettaglio () {
        return flussiDettaglio;
    }

    public void setFlussiDettaglio ( ArrayList<DTOFlussoDettaglio> flussiDettaglio ) {
        this.flussiDettaglio = flussiDettaglio;
    }
    
    public DTOFlussoSintesi getFlussoSintesi () {
        return flussoSintesi;
    }

    public void setFlussoSintesi ( DTOFlussoSintesi flussoSintesi ) {
        this.flussoSintesi = flussoSintesi;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ( super.toString () );
        stringBuffer.append ( "\n " );
        stringBuffer.append ( "esito: [" + esito.toString () + "]" );
        if ( flussiDettaglio != null ) {
            for ( int i = 0; i < flussiDettaglio.size (); i++ ) {
                DTOFlussoDettaglio dtFlussoDettaglio = flussiDettaglio.get ( i );
                if ( dtFlussoDettaglio != null ) {
                    stringBuffer.append ( i + ")  [" + dtFlussoDettaglio.toString () + "]" );
                }
            }
        }
        return stringBuffer.toString ();
    }

}

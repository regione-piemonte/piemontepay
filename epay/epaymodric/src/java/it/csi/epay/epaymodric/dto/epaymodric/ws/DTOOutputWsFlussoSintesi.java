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

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOFlussoSintesi;


/**
 * 
 * @ author vsgro
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoOutputWsFlussoSintesi" )
public class DTOOutputWsFlussoSintesi implements Serializable {

    private static final long serialVersionUID = 6024907684664456910L;

    private DTOOutputWsEsito esito = null;

    private DTOFlussoOrigine flussoOrigine = new DTOFlussoOrigine ();
    
    private ArrayList<DTOFlussoSintesi> flussiSintesi = new ArrayList<> ();

    public DTOOutputWsEsito getEsito () {
        return esito;
    }

    public void setEsito ( DTOOutputWsEsito esito ) {
        this.esito = esito;
    }

    public ArrayList<DTOFlussoSintesi> getFlussiSintesi () {
        return flussiSintesi;
    }

    public void setFlussiSintesi ( ArrayList<DTOFlussoSintesi> flussiSintesi ) {
        this.flussiSintesi = flussiSintesi;
    }
    
    public DTOFlussoOrigine getFlussoOrigine () {
        return flussoOrigine;
    }

    public void setFlussoOrigine ( DTOFlussoOrigine flussoOrigine ) {
        this.flussoOrigine = flussoOrigine;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ( super.toString () );
        stringBuffer.append ( "\n " );
        stringBuffer.append ( "esito: [" + esito.toString () + "]" );
        stringBuffer.append ( "flussoOrigine: [" + flussoOrigine.toString () + "]" );
        if ( flussiSintesi != null ) {
            for ( int i = 0; i < flussiSintesi.size (); i++ ) {
                DTOFlussoSintesi dtoFlussoSintesi = flussiSintesi.get ( i );
                if ( dtoFlussoSintesi != null ) {
                    stringBuffer.append ( i + ")  [" + dtoFlussoSintesi.toString () + "]" );
                }
            }
        }
        return stringBuffer.toString ();
    }

}

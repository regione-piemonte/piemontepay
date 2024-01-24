/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOErroreFlusso;


/**
 * 
 * @ author vsgro
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoOutputWsMotoreDiRiconciliazione" )
public class DTOOutputWsMotoreDiRiconciliazione implements Serializable {
    
    private static final long serialVersionUID = 6024907684664456910L;

    private DTOOutputWsEsito esito = null;

    private List<DTOErroreFlusso> listaErrori = new LinkedList<> ();

    public DTOOutputWsEsito getEsito () {
        return esito;
    }

    public void setEsito ( DTOOutputWsEsito esito ) {
        this.esito = esito;
    }

    public List<DTOErroreFlusso> getListaErrori () {
        return listaErrori;
    }

    public void setListaErrori ( List<DTOErroreFlusso> listaErrori ) {
        this.listaErrori = listaErrori;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();
        if (esito!=null) {
            stringBuffer.append ( "esito: [" + esito.toString () + "]" );
        }
        if ( listaErrori == null || listaErrori.isEmpty () ) {
            stringBuffer.append ( "LISTA ERRORI:  sono presenti: 0 errori" );
        } else {
            stringBuffer.append ( "LISTA ERRORI:  sono presenti: " + listaErrori.size () + " errori" );
            for ( int i = 0; i < listaErrori.size (); i++ ) {
                stringBuffer.append ( i + ") " + listaErrori.get ( i ).toString () + "\n" );
            }
        }
        return stringBuffer.toString ();
    }

}

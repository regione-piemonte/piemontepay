/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOFlussoOrigine;


/**
 * 
 * @ author vsgro
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoOutputWsFlussoOrigine" )
public class DTOOutputWsFlussoOrigine implements Serializable {

    private static final long serialVersionUID = -5207901778751393812L;

    private DTOOutputWsEsito esito = null;

    private List<DTOFlussoOrigine> flussiOrigine;

    private Integer start;
    
    private Integer length;
    
    private Long totalElements;
    
    public DTOOutputWsEsito getEsito () {
        return esito;
    }

    public void setEsito ( DTOOutputWsEsito esito ) {
        this.esito = esito;
    }

    public List<DTOFlussoOrigine> getFlussiOrigine () {
        return flussiOrigine;
    }

    public void setFlussiOrigine ( List<DTOFlussoOrigine> flussiOrigine ) {
        this.flussiOrigine = flussiOrigine;
    }

    /**
     * @return the start
     */
    public Integer getStart () {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart ( Integer start ) {
        this.start = start;
    }

    /**
     * @return the length
     */
    public Integer getLength () {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength ( Integer length ) {
        this.length = length;
    }

    /**
     * @return the totalElements
     */
    public Long getTotalElements () {
        return totalElements;
    }

    /**
     * @param totalElements the totalElements to set
     */
    public void setTotalElements ( Long totalElements ) {
        this.totalElements = totalElements;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ( super.toString () );
        stringBuffer.append ( "\n " );
        stringBuffer.append ( "esito: [" + esito.toString () + "]" );
        if ( flussiOrigine != null ) {
            for ( int i = 0; i < flussiOrigine.size (); i++ ) {
            	DTOFlussoOrigine dtoFlussoOrigine = flussiOrigine.get ( i );
                if ( dtoFlussoOrigine != null ) {
                    stringBuffer.append ( i + ")  [" + dtoFlussoOrigine.toString () + "]" );
                }
            }
        }
        return stringBuffer.toString ();
    }

}

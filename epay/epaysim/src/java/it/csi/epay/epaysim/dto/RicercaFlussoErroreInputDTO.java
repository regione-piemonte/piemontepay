/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * NG
 *
 */

//DTO di input per il servizio ricercaFlussiInErrore
@XmlAccessorType ( XmlAccessType.FIELD )
public class RicercaFlussoErroreInputDTO  extends ParentInput implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String identificativoFlusso;

    @XmlSchemaType ( name = "dateTime" )
    private XMLGregorianCalendar dataFlussoDa;

    @XmlSchemaType ( name = "dateTime" )
    private XMLGregorianCalendar dataFlussoA;

    public RicercaFlussoErroreInputDTO () {
        super ();
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getIdentificativoFlusso () {
        return identificativoFlusso;
    }

    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public XMLGregorianCalendar getDataFlussoDa () {
        return dataFlussoDa;
    }

    public void setDataFlussoDa ( XMLGregorianCalendar dataFlussoDa ) {
        this.dataFlussoDa = dataFlussoDa;
    }

    public XMLGregorianCalendar getDataFlussoA () {
        return dataFlussoA;
    }

    public void setDataFlussoA ( XMLGregorianCalendar dataFlussoA ) {
        this.dataFlussoA = dataFlussoA;
    }



}

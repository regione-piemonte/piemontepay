/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.dto.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import it.csi.epay.epaysim.dto.ParentInput;


@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "dtoInputWsRicercaProvvisori" )
public class DTOInputWsRicercaProvvisori extends ParentInput {

    private static final long serialVersionUID = -2049486103868951542L;

    private Long id;

    @XmlSchemaType ( name = "dateTime" )
    private XMLGregorianCalendar dataA;

    @XmlSchemaType ( name = "dateTime" )
    private XMLGregorianCalendar dataDa;

    private String idStatoFlusso;

    private String identificativoFlusso;

    public XMLGregorianCalendar getDataA () {
        return dataA;
    }

    public void setDataA ( XMLGregorianCalendar dataA ) {
        this.dataA = dataA;
    }

    public XMLGregorianCalendar getDataDa () {
        return dataDa;
    }

    public void setDataDa ( XMLGregorianCalendar dataDa ) {
        this.dataDa = dataDa;
    }

    public String getIdStatoFlusso () {
        return idStatoFlusso;
    }

    public void setIdStatoFlusso ( String idStatoFlusso ) {
        this.idStatoFlusso = idStatoFlusso;
    }

    public String getIdentificativoFlusso () {
        return identificativoFlusso;
    }

    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

}

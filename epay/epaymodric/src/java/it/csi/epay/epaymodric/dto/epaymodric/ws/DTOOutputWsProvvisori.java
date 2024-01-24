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

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOProvvisorio;


/**
 * 
 * @ author vsgro
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoOutputWsProvvisori" )
public class DTOOutputWsProvvisori implements Serializable {

    private static final long serialVersionUID = 6024907684664456910L;

    private DTOOutputWsEsito esito = null;

    private ArrayList<DTOProvvisorio> provvisori = new ArrayList<> ();

    public DTOOutputWsEsito getEsito () {
        return esito;
    }

    public ArrayList<DTOProvvisorio> getProvvisori () {
        return provvisori;
    }

    public void setEsito ( DTOOutputWsEsito esito ) {
        this.esito = esito;
    }

    public void setProvvisori ( ArrayList<DTOProvvisorio> provvisori ) {
        this.provvisori = provvisori;
    }

    @Override
    public String toString () {
        return "DTOOutputWsProvvisori [esito=" + esito + ", provvisori=" + provvisori + "]";
    }

}

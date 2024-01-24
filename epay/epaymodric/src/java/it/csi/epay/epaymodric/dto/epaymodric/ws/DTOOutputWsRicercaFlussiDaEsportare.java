/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoOutputWsRicercaFlussiDaEsportare" )
public class DTOOutputWsRicercaFlussiDaEsportare implements Serializable {

    private static final long serialVersionUID = -5207901778751393812L;

    private DTOOutputWsEsito esito;

    private String nomeFile;

    private byte [] contenuto;

    /**
     * @return the esito
     */
    public DTOOutputWsEsito getEsito () {
        return esito;
    }

    /**
     * @param esito the esito to set
     */
    public void setEsito ( DTOOutputWsEsito esito ) {
        this.esito = esito;
    }

    /**
     * @return the nomeFile
     */
    public String getNomeFile () {
        return nomeFile;
    }

    /**
     * @param nomeFile the nomeFile to set
     */
    public void setNomeFile ( String nomeFile ) {
        this.nomeFile = nomeFile;
    }

    /**
     * @return the contenuto
     */
    public byte [] getContenuto () {
        return contenuto;
    }

    /**
     * @param contenuto the contenuto to set
     */
    public void setContenuto ( byte [] contenuto ) {
        this.contenuto = contenuto;
    }

}

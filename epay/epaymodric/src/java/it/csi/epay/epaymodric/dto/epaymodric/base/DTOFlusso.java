/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.base;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoflusso", propOrder = { "idFlusso", "statoFlusso" } )
public class DTOFlusso implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idFlusso;

    private String statoFlusso;

    public DTOFlusso() {
        super ();
    }

    public String getIdFlusso () {
        return idFlusso;
    }

    public void setIdFlusso ( String idFlusso ) {
        this.idFlusso = idFlusso;
    }

    public String getStatoFlusso () {
        return statoFlusso;
    }

    public void setStatoFlusso ( String statoFlusso ) {
        this.statoFlusso = statoFlusso;
    }

}

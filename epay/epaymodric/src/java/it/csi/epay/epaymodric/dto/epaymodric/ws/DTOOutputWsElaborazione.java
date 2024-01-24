/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOBaseContabilizzatore;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "dtpOutputWsElaborazione")
public class DTOOutputWsElaborazione extends DTOBaseContabilizzatore implements Serializable {

    private String risultatoOutputEstemporanea;

    private String idEnte;

    private Long idElaborazione;

    private static final long serialVersionUID = 1L;

    public DTOOutputWsElaborazione() {
        super();
        risultatoOutputEstemporanea = null;
    }


    public String getRisultatoOutputEstemporanea() {
        return risultatoOutputEstemporanea;
    }


    public void setRisultatoOutputEstemporanea(String risultatoOutputEstemporanea) {
        this.risultatoOutputEstemporanea = risultatoOutputEstemporanea;
    }


    public String getIdEnte() {
        return idEnte;
    }


    public void setIdEnte(String idEnte) {
        this.idEnte = idEnte;
    }


    public Long getIdElaborazione () {
        return idElaborazione;
    }


    public void setIdElaborazione ( Long idElaborazione ) {
        this.idElaborazione = idElaborazione;
    }
}

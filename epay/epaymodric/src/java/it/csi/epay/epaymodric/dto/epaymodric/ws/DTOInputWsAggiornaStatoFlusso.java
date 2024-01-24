/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOInputBase;


@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoInputWsAggiornaStatoFlusso" )
public class DTOInputWsAggiornaStatoFlusso extends DTOInputBase {

    private static final long serialVersionUID = 1L;

    private List<String> identificativoFlusso;

    private String idEnte;

    private String nuovoStatoFlusso;

    public List<String> getIdentificativoFlusso () {
        return identificativoFlusso;
    }

    public void setIdentificativoFlusso ( List<String> identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public String getIdEnte () {
        return idEnte;
    }

    public void setIdEnte ( String idEnte ) {
        this.idEnte = idEnte;
    }

    public String getNuovoStatoFlusso () {
        return nuovoStatoFlusso;
    }

    public void setNuovoStatoFlusso ( String nuovoStatoFlusso ) {
        this.nuovoStatoFlusso = nuovoStatoFlusso;
    }

    @Override
    public String toString () {
        return "DTOInputWsAggiornaStatoFlusso [identificativoFlusso=" + identificativoFlusso + ", idEnte=" + idEnte + ", nuovoStatoFlusso=" + nuovoStatoFlusso
            + "]";
    }

}

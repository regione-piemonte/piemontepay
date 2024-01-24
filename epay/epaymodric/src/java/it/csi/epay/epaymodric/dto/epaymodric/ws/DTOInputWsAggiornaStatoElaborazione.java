/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOInputBase;


@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoInputWsAggiornaStatoElaborazione" )
public class DTOInputWsAggiornaStatoElaborazione extends DTOInputBase {

    private static final long serialVersionUID = 1L;

    private Long idElaborazione;

    private String nuovaStatoElaborazione;

    public Long getIdElaborazione () {
        return idElaborazione;
    }

    public void setIdElaborazione ( Long idElaborazione ) {
        this.idElaborazione = idElaborazione;
    }

    public String getNuovaStatoElaborazione () {
        return nuovaStatoElaborazione;
    }

    public void setNuovaStatoElaborazione ( String nuovaStatoElaborazione ) {
        this.nuovaStatoElaborazione = nuovaStatoElaborazione;
    }

    @Override
    public String toString () {
        return "DTOInputWsAggiornaStatoElaborazione [idElaborazione=" + idElaborazione + ", nuovaStatoElaborazione=" + nuovaStatoElaborazione + "]";
    }

}

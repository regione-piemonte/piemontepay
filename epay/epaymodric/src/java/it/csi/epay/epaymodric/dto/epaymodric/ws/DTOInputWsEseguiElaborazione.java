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
@XmlType ( name = "dtoInputWsEseguiElaborazione" )
public class DTOInputWsEseguiElaborazione extends DTOInputBase {

    private static final long serialVersionUID = 1L;

    private Long idElaborazione;

    public Long getIdElaborazione () {
        return idElaborazione;
    }

    public void setIdElaborazione ( Long idElaborazione ) {
        this.idElaborazione = idElaborazione;
    }

    @Override
    public String toString () {
        return "DTOInputWsEseguiElaborazione [idElaborazione=" + idElaborazione + "]";
    }

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;


public class StatistichePagamenti implements Serializable {

    private static final long serialVersionUID = 2433598082848468336L;

    private Long idMassimo;

    private Long numeroRecord;

    public Long getIdMassimo () {
        return idMassimo;
    }

    public void setIdMassimo ( Long idMassimo ) {
        this.idMassimo = idMassimo;
    }

    public Long getNumeroRecord () {
        return numeroRecord;
    }

    public void setNumeroRecord ( Long numeroRecord ) {
        this.numeroRecord = numeroRecord;
    }

}

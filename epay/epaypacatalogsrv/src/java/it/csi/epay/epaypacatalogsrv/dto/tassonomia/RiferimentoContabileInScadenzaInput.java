/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.tassonomia;

import java.io.Serializable;


public class RiferimentoContabileInScadenzaInput implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String idEnte;

    public String getIdEnte () {
        return idEnte;
    }

    public void setIdEnte ( String idEnte ) {
        this.idEnte = idEnte;
    }

}

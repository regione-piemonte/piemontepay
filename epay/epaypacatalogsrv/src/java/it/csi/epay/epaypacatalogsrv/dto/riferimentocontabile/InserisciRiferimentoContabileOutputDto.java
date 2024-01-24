/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile;

import java.io.Serializable;


public class InserisciRiferimentoContabileOutputDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Override
    public String toString () {
        return "InserisciRiferimentoContabileOutputDto [id=" + id + "]";
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

}

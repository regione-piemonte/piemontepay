/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile;

import java.util.Date;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;


public class ChiudiRiferimentoContabileInput extends ParentInput {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Date dataFineValidita;

    @Override
    public String toString () {
        return "ChiudiRiferimentoContabileInput [id=" + id + ", dataFineValidita=" + dataFineValidita + "]";
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public Date getDataFineValidita () {
        return dataFineValidita;
    }

    public void setDataFineValidita ( Date dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.codiceversamento;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;


public class GetCodiceVersamentoInput extends ParentInput {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Override
    public String toString () {
        return "GetCodiceVersamentoInput [id=" + id + "]";
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

}

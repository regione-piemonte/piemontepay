/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.autorizzazione;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;


public class GetEntiAssociatiOutput extends ParentOutput {

    private static final long serialVersionUID = 1L;

    private List<GetEntiAssociatiOutputDto> entiAssociati;

    public List<GetEntiAssociatiOutputDto> getEntiAssociati () {
        return entiAssociati;
    }

    public void setEntiAssociati ( List<GetEntiAssociatiOutputDto> entiAssociati ) {
        this.entiAssociati = entiAssociati;
    }

    @Override
    public String toString () {
        return "GetEntiAssociatiOutput [entiAssociati=" + entiAssociati + "]";
    }

}

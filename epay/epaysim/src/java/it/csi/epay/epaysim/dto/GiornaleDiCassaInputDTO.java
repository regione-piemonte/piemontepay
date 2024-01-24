/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.dto;

public class GiornaleDiCassaInputDTO extends ParentInput {

    private static final long serialVersionUID = -8524673589869522163L;

    private String identificativoFlussoBT;

    public String getIdentificativoFlussoBT () {
        return identificativoFlussoBT;
    }

    public void setIdentificativoFlussoBT ( String identificativoFlussoBT ) {
        this.identificativoFlussoBT = identificativoFlussoBT;
    }

}

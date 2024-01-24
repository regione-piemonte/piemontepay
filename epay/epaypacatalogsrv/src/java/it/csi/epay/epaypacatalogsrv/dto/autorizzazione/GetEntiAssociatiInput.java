/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.autorizzazione;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;


public class GetEntiAssociatiInput extends ParentInput {

    private static final long serialVersionUID = 1L;

    private String codiceFiscaleUtente;

    public String getCodiceFiscaleUtente () {
        return codiceFiscaleUtente;
    }

    public void setCodiceFiscaleUtente ( String codiceFiscaleUtente ) {
        this.codiceFiscaleUtente = codiceFiscaleUtente;
    }

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto;

import java.io.Serializable;


public class PrincipalInputDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codiceFiscale;

    private String codiceEnte;

    public PrincipalInputDto () {
        super ();
    }

    public PrincipalInputDto ( String codiceFiscale, String codiceEnte, String codiceRuolo ) {
        super ();
        this.codiceFiscale = codiceFiscale;
        this.codiceEnte = codiceEnte;
    }

    public String getCodiceEnte () {
        return codiceEnte;
    }

    public void setCodiceEnte ( String codiceEnte ) {
        this.codiceEnte = codiceEnte;
    }

    public String getCodiceFiscale () {
        return codiceFiscale;
    }

    public void setCodiceFiscale ( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }

    @Override
    public String toString () {
        return "PrincipalInputDto [codiceFiscale=" + codiceFiscale + ", codiceEnte=" + codiceEnte + "]";
    }
}

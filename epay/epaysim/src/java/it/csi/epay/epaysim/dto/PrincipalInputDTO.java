/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.dto;

import java.io.Serializable;


public class PrincipalInputDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codiceFiscale;

    private String codiceEnte;

    private String codiceRuolo;

    public PrincipalInputDTO () {
        super ();
    }

    public PrincipalInputDTO ( String codiceFiscale, String codiceEnte, String codiceRuolo ) {
        super ();
        this.codiceFiscale = codiceFiscale;
        this.codiceEnte = codiceEnte;
        this.codiceRuolo = codiceRuolo;
    }

    public String getCodiceEnte () {
        return codiceEnte;
    }

    public void setCodiceEnte ( String codiceEnte ) {
        this.codiceEnte = codiceEnte;
    }

    public String getCodiceRuolo () {
        return codiceRuolo;
    }

    public void setCodiceRuolo ( String codiceRuolo ) {
        this.codiceRuolo = codiceRuolo;
    }

    public String getCodiceFiscale () {
        return codiceFiscale;
    }

    public void setCodiceFiscale ( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }
}

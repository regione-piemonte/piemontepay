/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.frontend.dto.base;

import java.io.Serializable;


public class DTOPrincipal implements Serializable {

    private static final long serialVersionUID = 8404972200559120082L;

    private String codiceFiscale;

    private String codiceEnte;

    private String codiceRuolo;

    public String getCodiceFiscale () {
        return codiceFiscale;
    }

    public void setCodiceFiscale ( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
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

    @Override
    public String toString () {
        return "DTOPrincipal [codiceFiscale=" + codiceFiscale + ", codiceEnte=" + codiceEnte + ", codiceRuolo=" + codiceRuolo + "]";
    }

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.vo.security;

import java.io.Serializable;


public class PrincipalProfiloVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codice;

    private String descrizione;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCodice () {
        return codice;
    }

    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString () {
        return "PrincipalProfiloVO [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + "]";
    }

}

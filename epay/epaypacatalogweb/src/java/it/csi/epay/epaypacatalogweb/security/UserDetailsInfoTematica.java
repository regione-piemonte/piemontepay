/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.security;

import java.util.List;


public class UserDetailsInfoTematica implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected String codice;

    protected List<Long> codiciVersamento;

    protected String descrizione;

    protected Boolean flagVisibilitaTotale;

    protected Long id;

    public String getCodice () {
        return codice;
    }

    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    public List<Long> getCodiciVersamento () {
        return codiciVersamento;
    }

    public void setCodiciVersamento ( List<Long> codiciVersamento ) {
        this.codiciVersamento = codiciVersamento;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    public Boolean getFlagVisibilitaTotale () {
        return flagVisibilitaTotale;
    }

    public void setFlagVisibilitaTotale ( Boolean flagVisibilitaTotale ) {
        this.flagVisibilitaTotale = flagVisibilitaTotale;
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

}

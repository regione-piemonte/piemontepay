/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.dto;

import java.io.Serializable;

/**
 *
 */

public class EsitoDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String codice = "";

    private String descrizione = "";

    public EsitoDTO ( String codice, String descrizione ) {
        super ();
        this.codice = codice;
        this.descrizione = descrizione;
    }

    public EsitoDTO () {

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

}

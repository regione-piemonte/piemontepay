/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import java.io.Serializable;

/**
 *
 */

public class Configurazione implements Serializable {

    private static final long serialVersionUID = -520928713011664413L;

    private Ente ente;

    private String codice;

    private String valore;

    private String descrizione;

    public Ente getEnte () {
        return ente;
    }

    public void setEnte ( Ente ente ) {
        this.ente = ente;
    }

    public String getCodice () {
        return codice;
    }

    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    public String getValore () {
        return valore;
    }

    public void setValore ( String valore ) {
        this.valore = valore;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayweb.frontend.models;

import java.io.Serializable;

/**
 *
 */

public class RiferimentoPagamento implements Serializable {

    private static final long serialVersionUID = 549905853961949068L;

    private String nome;

    private String valore;

    public String getNome () {
        return nome;
    }

    public void setNome ( String nome ) {
        this.nome = nome;
    }

    public String getValore () {
        return valore;
    }

    public void setValore ( String valore ) {
        this.valore = valore;
    }

}

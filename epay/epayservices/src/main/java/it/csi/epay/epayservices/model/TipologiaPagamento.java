/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;


public class TipologiaPagamento implements Serializable {

    private static final long serialVersionUID = -8549332168048968214L;
    
    public enum TipoPagamentoType {

            LDS,
            PS,
            REDS,
            MABL,
            PABL;
    }

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

}

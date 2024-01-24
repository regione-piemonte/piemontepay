/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the epay_d_stato_pagamento database table.
 *
 */
@Entity
@Table ( name = "epay_d_tipologia_pagamento" )
public class EpayDTipologiaPagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column ( name = "id", unique = true, nullable = false )
    private Integer id;

    @Column ( name = "codice", nullable = false, length = 4 )
    private String codice;

    @Column ( name = "descrizione", nullable = true, length = 50 )
    private String descrizione;

    public EpayDTipologiaPagamento () {
    }

    public Integer getId () {
        return id;
    }

    public void setId ( Integer id ) {
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

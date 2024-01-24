/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaypa_d_tipo_pagamento database table.
 *
 */
@Entity
@Table ( name = "epaypa_d_tipo_pagamento" )
@NamedQueries ( { @NamedQuery (
                name = "EpaypaDTipoPagamento.findOneByCodice",
                query = "SELECT e "
                    + "FROM EpaypaDTipoPagamento e "
                    + "WHERE e.codice = :codice" ),
    @NamedQuery (
                    name = "EpaypaDTipoPagamento.findAll",
                    query = "SELECT e FROM EpaypaDTipoPagamento e" )
} )
public class EpaypaDTipoPagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column ( name = "id" )
    private Integer id;

    private String codice;

    private String descrizione;

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

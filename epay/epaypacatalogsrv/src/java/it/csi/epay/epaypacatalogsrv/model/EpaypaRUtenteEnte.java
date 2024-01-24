/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaypa_r_utente_ente database table.
 *
 */
@Entity
@Table ( name = "epaypa_r_utente_ente", schema = "epaypa" )
@NamedQuery ( name = "EpaypaRUtenteEnte.findAll", query = "SELECT e FROM EpaypaRUtenteEnte e" )
public class EpaypaRUtenteEnte implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private EpaypaRUtenteEntePK id;

    public EpaypaRUtenteEnte () {
    }

    public EpaypaRUtenteEntePK getId () {
        return id;
    }

    public void setId ( EpaypaRUtenteEntePK id ) {
        this.id = id;
    }

}

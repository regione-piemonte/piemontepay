/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the epay_t_configurazione database table.
 * 
 */
@Entity
@Table ( name = "epay_t_configurazione" )
public class EpayTConfigurazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @SequenceGenerator ( name = "EPAY_T_CONFIGURAZIONE_ID_GENERATOR", allocationSize = 1, sequenceName = "EPAY_T_CONFIGURAZIONE_ID_SEQ" )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_CONFIGURAZIONE_ID_GENERATOR" )
    @Column ( name = "id", unique = true, nullable = false )
    private Long id;

    //bi-directional many-to-one association to EpayTEnti
    @ManyToOne
    @JoinColumn ( name = "id_ente", nullable = false )
    private EpayTEnti epayTEnti;

    @Column ( length = 100, nullable = false )
    private String codice;

    @Column ( length = 100, nullable = false )
    private String valore;

    @Column ( length = 1000 )
    private String descrizione;

	public EpayTConfigurazione() {
	}

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public EpayTEnti getEpayTEnti () {
        return epayTEnti;
    }

    public void setEpayTEnti ( EpayTEnti epayTEnti ) {
        this.epayTEnti = epayTEnti;
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

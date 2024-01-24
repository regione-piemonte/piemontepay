/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaypacat_t_log_transazione database table.
 *
 */
@Entity
@Table ( name = "epaycat_t_log_transazione" )
@NamedQuery ( name = "LogTransazione.findAll", query = "SELECT e FROM LogTransazione e" )
public class LogTransazione implements Serializable {

    private static final long serialVersionUID = 6791024697056582488L;

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "id_sottoscrittore", referencedColumnName = "id" )
    private Sottoscrittore sottoscrittore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "id_transazione", referencedColumnName = "id" )
    private Transazione transazione; // do we need a complete reference to the table "transazione" ? I'll leave just the ID for now

    private String esito;

    private String messaggio;

    /**
     * @return the id
     */
    public final Long getId () {
        return id;
    }

    /**
     * @param id the id to set
     */
    public final void setId ( Long id ) {
        this.id = id;
    }

    /**
     * @return the esito
     */
    public final String getEsito () {
        return esito;
    }

    /**
     * @param esito the esito to set
     */
    public final void setEsito ( String esito ) {
        this.esito = esito;
    }

    /**
     * @return the sottoscrittore
     */
    public final Sottoscrittore getSottoscrittore () {
        return sottoscrittore;
    }

    /**
     * @param sottoscrittore the sottoscrittore to set
     */
    public final void setSottoscrittore ( Sottoscrittore sottoscrittore ) {
        this.sottoscrittore = sottoscrittore;
    }

    /**
     * @return the transazione
     */
    public final Transazione getTransazione () {
        return transazione;
    }

    /**
     * @param transazione the transazione to set
     */
    public final void setTransazione ( Transazione transazione ) {
        this.transazione = transazione;
    }

    /**
     * @return the messaggio
     */
    public final String getMessaggio () {
        return messaggio;
    }

    /**
     * @param messaggio the messaggio to set
     */
    public final void setMessaggio ( String messaggio ) {
        this.messaggio = messaggio;
    }

	@Override
	public String toString() {
		return "LogTransazione [id=" + id + "]";
	}

}

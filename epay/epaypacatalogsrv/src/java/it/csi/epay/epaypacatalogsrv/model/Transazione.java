/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the epaypacat_t_transazione database table.
 *
 */
@Entity
@Table ( name = "epaycat_t_transazione" )
@NamedQuery ( name = "Transazione.findAll", query = "SELECT e FROM Transazione e" )
public class Transazione implements Serializable {

    private static final long serialVersionUID = -5035269192763923435L;

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "id_operazione", referencedColumnName = "id" )
    private Operazione operazione;

    private String esito;

    @Temporal ( TemporalType.TIMESTAMP )
    @Column ( name = "data" )
    private Date data;

    @Column ( name = "id_transazione_esterna" )
    private String idTransazioneEsterna;

    public Transazione () {
    }

    public Transazione ( Long id ) {
        super ();
        this.id = id;
    }

    public String getIdTransazioneEsterna () {
        return idTransazioneEsterna;
    }

    public void setIdTransazioneEsterna ( String idTransazioneEsterna ) {
        this.idTransazioneEsterna = idTransazioneEsterna;
    }

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
     * @return the operazione
     */
    public final Operazione getOperazione () {
        return operazione;
    }

    /**
     * @param operazione the operazione to set
     */
    public final void setOperazione ( Operazione operazione ) {
        this.operazione = operazione;
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
     * @return the data
     */
    public final Date getData () {
        return data;
    }

    /**
     * @param data the data to set
     */
    public final void setData ( Date data ) {
        this.data = data;
    }

	@Override
	public String toString() {
		return "Transazione [id=" + id + ", idTransazioneEsterna=" + idTransazioneEsterna + "]";
	}

}

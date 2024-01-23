/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table ( name = "epay_t_configurazione" )
@SuppressWarnings ( "unused" )
public class EpayTConfigurazione implements Serializable {

	private static final long serialVersionUID = 370429326449997194L;

	@Id
	@Column ( name = "id" )
	private Long id;

	@Column ( name = "codice", nullable = false, length = 100 )
	private String codice;

	@Column ( name = "valore", nullable = false, length = 100 )
	private String valore;

	@Column ( name = "descrizione", length = 1000 )
	private String descrizione;

	@ManyToOne
	@JoinColumn ( name = "id_ente", referencedColumnName = "id_ente" )
	private EpayTEnti epayTEnti;

	public EpayTConfigurazione () {
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public Long getId () {
		return this.id;
	}

	public void setCodice ( String codice ) {
		this.codice = codice;
	}

	public String getCodice () {
		return this.codice;
	}

	public void setValore ( String valore ) {
		this.valore = valore;
	}

	public String getValore () {
		return this.valore;
	}

	public void setDescrizione ( String descrizione ) {
		this.descrizione = descrizione;
	}

	public String getDescrizione () {
		return this.descrizione;
	}

	public void setEpayTEnti ( EpayTEnti epayTEnti ) {
		this.epayTEnti = epayTEnti;
	}

	public EpayTEnti getEpayTEnti () {
		return this.epayTEnti;
	}

	@Override
	public String toString () {
		return "{ " +
			"id:" + id +
			", codice:" + codice +
			", valore:" + valore +
			", descrizione:" + descrizione +
			" }";
	}
}

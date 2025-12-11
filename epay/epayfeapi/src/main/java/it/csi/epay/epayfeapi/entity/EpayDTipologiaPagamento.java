/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table ( name = "epay_d_tipologia_pagamento" )
@SuppressWarnings ( "unused" )
public class EpayDTipologiaPagamento implements Serializable {

	private static final long serialVersionUID = -4059941947758700209L;

	@Id
	@Column ( name = "id", unique = true, nullable = false )
	private Integer id;

	@Column ( name = "codice", nullable = false, length = 4 )
	private String codice;

	@Column ( name = "descrizione", length = 50 )
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

	@Override
	public String toString () {
		return "{ " +
						"id:" + id +
						", codice:" + codice +
						", descrizione:" + descrizione +
						" }";
	}
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table ( name = "epay_d_esito_chiamata_esterna" )
@SuppressWarnings ( "unused" )
public class EpayDEsitoChiamataEsterna implements Serializable {

	private static final long serialVersionUID = -5221148222968386774L;

	@Id
	@Column ( name = "codice", unique = true, nullable = false )
	private String codice;

	@Column ( name = "descrizione", length = 50 )
	private String descrizione;

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
			"codice:" + codice +
			", descrizione:" + descrizione +
			" }";
	}
}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table ( name = "epay_t_parametri" )
@SuppressWarnings ( "unused" )
public class EpayTParametri implements Serializable {

	private static final long serialVersionUID = -3276165103601370807L;

	@EmbeddedId
	private EpayTParametriPK id;

	@Column ( length = 2000 )
	private String descrizione;

	private Integer progressivo;

	@Column ( nullable = false, length = 1000 )
	private String valore;

	public EpayTParametri () {
	}

	public EpayTParametriPK getId () {
		return this.id;
	}

	public void setId ( EpayTParametriPK id ) {
		this.id = id;
	}

	public String getDescrizione () {
		return this.descrizione;
	}

	public void setDescrizione ( String descrizione ) {
		this.descrizione = descrizione;
	}

	public Integer getProgressivo () {
		return this.progressivo;
	}

	public void setProgressivo ( Integer progressivo ) {
		this.progressivo = progressivo;
	}

	public String getValore () {
		return this.valore;
	}

	public void setValore ( String valore ) {
		this.valore = valore;
	}

	@Override
	public String toString () {
		return "{ " +
						"id:" + id +
						", descrizione:" + descrizione +
						", progressivo:" + progressivo +
						", valore:" + valore +
						" }";
	}
}

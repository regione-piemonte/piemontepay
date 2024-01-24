/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the nodo_errori database table.
 */
@Entity
@Table ( name = "nodo_errori" )
@NamedQuery ( name = "NodoErrori.findAll", query = "SELECT n FROM NodoErrori n" )
@SuppressWarnings ( "unused" )
public class NodoErrori implements Serializable {

	private static final long serialVersionUID = -4433024764416510148L;

	@Id
	@SequenceGenerator ( name = "NODO_ERRORI_ID_GENERATOR", sequenceName = "NODO_ERRORI_SEQ", allocationSize = 1 )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "NODO_ERRORI_ID_GENERATOR" )
	private Integer id;

	private Timestamp data;

	private String descrizione;

	@Column ( name = "transaction_id" )
	private String transactionId;

	@ManyToOne ( fetch = FetchType.LAZY )
	private Application application;

	public NodoErrori () {
	}

	public Integer getId () {
		return this.id;
	}

	public void setId ( Integer id ) {
		this.id = id;
	}

	public Timestamp getData () {
		return this.data;
	}

	public void setData ( Timestamp data ) {
		this.data = data;
	}

	public String getDescrizione () {
		return this.descrizione;
	}

	public void setDescrizione ( String descrizione ) {
		this.descrizione = descrizione;
	}

	public String getTransactionId () {
		return this.transactionId;
	}

	public void setTransactionId ( String transactionId ) {
		this.transactionId = transactionId;
	}

	public Application getApplication () {
		return this.application;
	}

	public void setApplication ( Application application ) {
		this.application = application;
	}

}

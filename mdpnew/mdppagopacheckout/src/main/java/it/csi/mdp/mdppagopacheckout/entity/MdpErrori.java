/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the mdp_errori database table.
 */
@Entity
@Table ( name = "mdp_errori" )
@NamedQuery ( name = "MdpErrori.findAll", query = "SELECT m FROM MdpErrori m" )
@SuppressWarnings ( "unused" )
public class MdpErrori implements Serializable {

	private static final long serialVersionUID = -8637929563292533256L;

	@Id
	@SequenceGenerator ( name = "MDP_ERRORI_ID_GENERATOR", sequenceName = "MDP_ERRORI_SEQ", allocationSize = 1 )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "MDP_ERRORI_ID_GENERATOR" )
	private Integer id;

	private Timestamp data;

	private String descrizione;

	@ManyToOne ( fetch = FetchType.LAZY )
	private Application application;

	@ManyToOne ( fetch = FetchType.LAZY )
	@JoinColumn ( name = "transaction_id" )
	private Transazione transazione;

	public MdpErrori () {
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

	public Application getApplication () {
		return this.application;
	}

	public void setApplication ( Application application ) {
		this.application = application;
	}

	public Transazione getTransazione () {
		return this.transazione;
	}

	public void setTransazione ( Transazione transazione ) {
		this.transazione = transazione;
	}

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * The persistent class for the transazione_extra_attribute database table.
 */
@Entity
@Table ( name = "transazione_extra_attribute" )
@NamedQuery ( name = "TransazioneExtraAttribute.findAll", query = "SELECT t FROM TransazioneExtraAttribute t" )
@SuppressWarnings ( "unused" )
public class TransazioneExtraAttribute implements Serializable {

	private static final long serialVersionUID = 2597766543215032178L;

	@EmbeddedId
	private TransazioneExtraAttributePK id;

	private String value;

	@ManyToOne ( fetch = FetchType.LAZY )
	@JoinColumn ( name = "transaction_id", insertable = false, updatable = false )
	private Transazione transazione;

	public TransazioneExtraAttribute () {
	}

	public TransazioneExtraAttributePK getId () {
		return this.id;
	}

	public void setId ( TransazioneExtraAttributePK id ) {
		this.id = id;
	}

	public String getValue () {
		return this.value;
	}

	public void setValue ( String value ) {
		this.value = value;
	}

	public Transazione getTransazione () {
		return this.transazione;
	}

	public void setTransazione ( Transazione transazione ) {
		this.transazione = transazione;
	}

}

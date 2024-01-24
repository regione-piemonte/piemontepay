/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the transazione_extra_attribute database table.
 * 
 */
@Entity
@Table(name="transazione_extra_attribute")
@NamedQuery(name="TransazioneExtraAttribute.findAll", query="SELECT t FROM TransazioneExtraAttribute t")
public class TransazioneExtraAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TransazioneExtraAttributePK id;

	private String value;

	//bi-directional many-to-one association to Transazione
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="transaction_id", insertable=false, updatable=false)
	private Transazione transazione;

	public TransazioneExtraAttribute() {
	}

	public TransazioneExtraAttributePK getId() {
		return this.id;
	}

	public void setId(TransazioneExtraAttributePK id) {
		this.id = id;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Transazione getTransazione() {
		return this.transazione;
	}

	public void setTransazione(Transazione transazione) {
		this.transazione = transazione;
	}

}

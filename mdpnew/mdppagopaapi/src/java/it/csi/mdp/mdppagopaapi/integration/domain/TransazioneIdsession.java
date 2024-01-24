/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the transazione_idsession database table.
 * 
 */
@Entity
@Table(name="transazione_idsession")
@NamedQuery(name="TransazioneIdsession.findAll", query="SELECT t FROM TransazioneIdsession t")
public class TransazioneIdsession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_session")
	private String idSession;

	@Column(name="transaction_id")
	private String transactionId;

	public TransazioneIdsession() {
	}

	public String getIdSession() {
		return this.idSession;
	}

	public void setIdSession(String idSession) {
		this.idSession = idSession;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}

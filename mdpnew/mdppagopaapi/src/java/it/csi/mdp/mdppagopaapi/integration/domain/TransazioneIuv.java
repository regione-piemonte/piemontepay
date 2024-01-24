/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the transazione_iuv database table.
 * 
 */
@Entity
@Table(name="transazione_iuv")
@NamedQuery(name="TransazioneIuv.findAll", query="SELECT t FROM TransazioneIuv t")
public class TransazioneIuv implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String iuv;

	@Column(name="transaction_id")
	private String transactionId;

	public TransazioneIuv() {
	}

	public String getIuv() {
		return this.iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}

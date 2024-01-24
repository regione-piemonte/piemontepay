/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * The persistent class for the transazione_iuv database table.
 */
@Entity
@Table ( name = "transazione_iuv" )
@NamedQuery ( name = "TransazioneIuv.findAll", query = "SELECT t FROM TransazioneIuv t" )
@SuppressWarnings ( "unused" )
public class TransazioneIuv implements Serializable {

	private static final long serialVersionUID = -3825859279737358014L;

	@Id
	private String iuv;

	@Column ( name = "transaction_id" )
	private String transactionId;

	public TransazioneIuv ( String transactionId, String iuv ) {
		this.transactionId = transactionId;
		this.iuv = iuv;
	}

	public TransazioneIuv () {
	}

	public String getIuv () {
		return this.iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public String getTransactionId () {
		return this.transactionId;
	}

	public void setTransactionId ( String transactionId ) {
		this.transactionId = transactionId;
	}

}

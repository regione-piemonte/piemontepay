/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the payment_gateway database table.
 * 
 */
@Entity
@Table(name="payment_gateway")
@NamedQuery(name="PaymentGateway.findAll", query="SELECT p FROM PaymentGateway p")
public class PaymentGateway implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PAYMENT_GATEWAY_PGID_GENERATOR", sequenceName="PAYMENT_GATEWAY_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAYMENT_GATEWAY_PGID_GENERATOR")
	@Column(name="pg_id")
	private long pgId;

	private String cod;

	private String nome;

	@Column(name="transaction_id")
	private String transactionId;

	public PaymentGateway() {
	}

	public long getPgId() {
		return this.pgId;
	}

	public void setPgId(long pgId) {
		this.pgId = pgId;
	}

	public String getCod() {
		return this.cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}

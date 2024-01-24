/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the strumento_pagamento database table.
 * 
 */
@Entity
@Table(name="strumento_pagamento")
@NamedQuery(name="StrumentoPagamento.findAll", query="SELECT s FROM StrumentoPagamento s")
public class StrumentoPagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cod;

	private String nome;

	@Column(name="sp_id")
	private BigDecimal spId;

	@Column(name="transaction_id")
	private String transactionId;

	public StrumentoPagamento() {
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

	public BigDecimal getSpId() {
		return this.spId;
	}

	public void setSpId(BigDecimal spId) {
		this.spId = spId;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}

/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;


/**
 * The persistent class for the epay_d_modalita_pagamento database table.
 * 0113153419
 */
	public class ModalitaPagamento implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idModalitaPagamento;
	private String descrizione;

	public ModalitaPagamento() {
	}

	public Integer getIdModalitaPagamento() {
		return this.idModalitaPagamento;
	}

	public void setIdModalitaPagamento(Integer idModalitaPagamento) {
		this.idModalitaPagamento = idModalitaPagamento;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}

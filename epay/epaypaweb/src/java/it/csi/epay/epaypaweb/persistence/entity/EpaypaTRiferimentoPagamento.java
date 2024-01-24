/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaypa_t_riferimento_pagamento database table.
 * 
 */
@Entity
@Table ( name = "epaypa_t_riferimento_pagamento" )
@NamedQuery ( name = "EpaypaTRiferimentoPagamento.findAll", query = "SELECT e FROM EpaypaTRiferimentoPagamento e" )
public class EpaypaTRiferimentoPagamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	@Column ( name = "id_riferimento" )
	private Long idRiferimento;

	private String nome;

	private String valore;

	public EpaypaTRiferimentoPagamento () {
		//empty constructor
	}

	public Long getIdRiferimento () {
		return idRiferimento;
	}

	public void setIdRiferimento ( Long idRiferimento ) {
		this.idRiferimento = idRiferimento;
	}

	public String getNome () {
		return nome;
	}

	public void setNome ( String nome ) {
		this.nome = nome;
	}

	public String getValore () {
		return valore;
	}

	public void setValore ( String valore ) {
		this.valore = valore;
	}

}

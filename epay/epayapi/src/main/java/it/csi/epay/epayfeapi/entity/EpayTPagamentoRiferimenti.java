/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table ( name = "epay_t_pagamento_riferimenti" )
@SuppressWarnings ( "unused" )
public class EpayTPagamentoRiferimenti implements Serializable {

	private static final long serialVersionUID = -6418092772491384991L;

	@Id
	@SequenceGenerator ( name = "EPAY_T_PAGAMENTO_RIFERIMENTI_IDRIFERIMENTO_GENERATOR", allocationSize = 1,
					sequenceName = "EPAY_T_PAGAMENTO_RIFERIMENTI_ID_RIFERIMENTO_SEQ" )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_PAGAMENTO_RIFERIMENTI_IDRIFERIMENTO_GENERATOR" )
	@Column ( name = "id_riferimento", unique = true, nullable = false )
	private Long idRiferimento;

	@Column ( nullable = false, length = 70 )
	private String nome;

	@Column ( nullable = false, length = 70 )
	private String valore;

	@Column ( nullable = false )
	private Integer progressivo;

	@Column ( name = "utente_ultima_modifica", nullable = false, length = 100 )
	private String utenteUltimaModifica;

	@ManyToOne
	@JoinColumn ( name = "id_pagamento", nullable = false )
	private EpayTPagamento epayTPagamento;

	public EpayTPagamentoRiferimenti () {
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

	public Integer getProgressivo () {
		return progressivo;
	}

	public void setProgressivo ( Integer progressivo ) {
		this.progressivo = progressivo;
	}

	public String getUtenteUltimaModifica () {
		return utenteUltimaModifica;
	}

	public void setUtenteUltimaModifica ( String utenteUltimaModifica ) {
		this.utenteUltimaModifica = utenteUltimaModifica;
	}

	public EpayTPagamento getEpayTPagamento () {
		return epayTPagamento;
	}

	public void setEpayTPagamento ( EpayTPagamento epayTPagamento ) {
		this.epayTPagamento = epayTPagamento;
	}

	@Override
	public String toString () {
		return "{ " +
			"idRiferimento:" + idRiferimento +
			", nome:" + nome +
			", valore:" + valore +
			", progressivo:" + progressivo +
			", utenteUltimaModifica:" + utenteUltimaModifica +
			// non esporre
			" }";
	}
}

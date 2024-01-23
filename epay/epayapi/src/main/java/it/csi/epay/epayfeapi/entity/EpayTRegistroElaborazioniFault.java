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
@Table ( name = "epay_t_registro_elaborazioni_fault" )
@SuppressWarnings ( "unused" )
public class EpayTRegistroElaborazioniFault implements Serializable {

	private static final long serialVersionUID = 3949110905665147809L;

	@Id
	@SequenceGenerator ( name = "EPAY_T_REGISTRO_ELABORAZIONI_FAULT_ID_GENERATOR", allocationSize = 1,
					sequenceName = "EPAY_T_REGISTRO_ELABORAZIONI_FAULT_ID_SEQ" )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_REGISTRO_ELABORAZIONI_FAULT_ID_GENERATOR" )
	@Column ( unique = true, nullable = false )
	private Long id;

	@Column ( name = "id_pagamento" )
	private Long idPagamento;

	@Column ( name = "codice_pagamento_rif_ente", length = 200 )
	private String codicePagamentoRifEnte;

	@Column ( name = "codice_messaggio", length = 3 )
	private String codiceMessaggio;

	@Column ( name = "descrizione_messaggio", length = 200 )
	private String descrizioneMessaggio;

	@ManyToOne
	@JoinColumn ( name = "id_registro_elaborazioni", nullable = false )
	private EpayTRegistroElaborazioni epayTRegistroElaborazioni;

	public EpayTRegistroElaborazioniFault () {
	}

	public Long getId () {
		return id;
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public Long getIdPagamento () {
		return idPagamento;
	}

	public void setIdPagamento ( Long idPagamento ) {
		this.idPagamento = idPagamento;
	}

	public String getCodicePagamentoRifEnte () {
		return codicePagamentoRifEnte;
	}

	public void setCodicePagamentoRifEnte ( String codicePagamentoRifEnte ) {
		this.codicePagamentoRifEnte = codicePagamentoRifEnte;
	}

	public String getCodiceMessaggio () {
		return codiceMessaggio;
	}

	public void setCodiceMessaggio ( String codiceMessaggio ) {
		this.codiceMessaggio = codiceMessaggio;
	}

	public String getDescrizioneMessaggio () {
		return descrizioneMessaggio;
	}

	public void setDescrizioneMessaggio ( String descrizioneMessaggio ) {
		this.descrizioneMessaggio = descrizioneMessaggio;
	}

	public EpayTRegistroElaborazioni getEpayTRegistroElaborazioni () {
		return epayTRegistroElaborazioni;
	}

	public void setEpayTRegistroElaborazioni ( EpayTRegistroElaborazioni epayTRegistroElaborazioni ) {
		this.epayTRegistroElaborazioni = epayTRegistroElaborazioni;
	}

	@Override
	public String toString () {
		return "{ " +
			"id:" + id +
			", idPagamento:" + idPagamento +
			", codicePagamentoRifEnte:" + codicePagamentoRifEnte +
			", codiceMessaggio:" + codiceMessaggio +
			", descrizioneMessaggio:" + descrizioneMessaggio +
			// non esporre epayTRegistroElaborazioni
			" }";
	}
}

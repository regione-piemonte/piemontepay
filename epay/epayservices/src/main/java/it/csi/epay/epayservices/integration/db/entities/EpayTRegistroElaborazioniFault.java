/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the epay_t_registro_elaborazioni_fault database table.
 * 
 */
@Entity
@Table(name="epay_t_registro_elaborazioni_fault")
@NamedQuery(name="EpayTRegistroElaborazioniFault.findAll", query="SELECT e FROM EpayTRegistroElaborazioniFault e")
public class EpayTRegistroElaborazioniFault implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EPAY_T_REGISTRO_ELABORAZIONI_FAULT_ID_GENERATOR", allocationSize=1, sequenceName="EPAY_T_REGISTRO_ELABORAZIONI_FAULT_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAY_T_REGISTRO_ELABORAZIONI_FAULT_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="id_pagamento")
	private Long idPagamento;

	@Column(name="codice_pagamento_rif_ente", length=200)
	private String codicePagamentoRifEnte;
	
	@Column(name="codice_messaggio", length=3)
	private String codiceMessaggio;

	@Column(name="descrizione_messaggio", length=200)
	private String descrizioneMessaggio;

	//bi-directional many-to-one association to EpayTRegistroElaborazioni
	@ManyToOne
	@JoinColumn(name="id_registro_elaborazioni", nullable=false)
	private EpayTRegistroElaborazioni epayTRegistroElaborazioni;

	public EpayTRegistroElaborazioniFault() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodiceMessaggio() {
		return this.codiceMessaggio;
	}

	public void setCodiceMessaggio(String codiceMessaggio) {
		this.codiceMessaggio = codiceMessaggio;
	}

	public String getDescrizioneMessaggio() {
		return this.descrizioneMessaggio;
	}

	public void setDescrizioneMessaggio(String descrizioneMessaggio) {
		this.descrizioneMessaggio = descrizioneMessaggio;
	}

	/**
	 * @return the idPagamento
	 */
	public Long getIdPagamento() {
		return idPagamento;
	}

	/**
	 * @param idPagamento the idPagamento to set
	 */
	public void setIdPagamento(Long idPagamento) {
		this.idPagamento = idPagamento;
	}

	/**
	 * @return the codicePagamentoRifEnte
	 */
	public String getCodicePagamentoRifEnte() {
		return codicePagamentoRifEnte;
	}

	/**
	 * @param codicePagamentoRifEnte the codicePagamentoRifEnte to set
	 */
	public void setCodicePagamentoRifEnte(String codicePagamentoRifEnte) {
		this.codicePagamentoRifEnte = codicePagamentoRifEnte;
	}

	public EpayTRegistroElaborazioni getEpayTRegistroElaborazioni() {
		return this.epayTRegistroElaborazioni;
	}
	
	public void setEpayTRegistroElaborazioni(EpayTRegistroElaborazioni epayTRegistroElaborazioni) {
		this.epayTRegistroElaborazioni = epayTRegistroElaborazioni;
	}

}

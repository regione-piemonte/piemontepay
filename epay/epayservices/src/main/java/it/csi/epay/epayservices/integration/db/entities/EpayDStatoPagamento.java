/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the epay_d_stato_pagamento database table.
 * 
 */
@Entity
@Table(name="epay_d_stato_pagamento")
public class EpayDStatoPagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_stato", unique=true, nullable=false)
	private Integer idStato;

	@Column(name="desc_stato", nullable=false, length=250)
	private String descStato;
	
	@Column(nullable=false)
	private Boolean pagabile;
	
	@Column(nullable=false)
	private Boolean modificabile;

	//bi-directional many-to-one association to EpayTPagamento
	@OneToMany(mappedBy="epayDStatoPagamento")
	private List<EpayTPagamento> epayTPagamentos;

	//bi-directional many-to-one association to EpayTRegistroVersamenti
	@OneToMany(mappedBy="epayDStatoPagamento")
	private List<EpayTRegistroVersamenti> epayTRegistroVersamentis;

	public EpayDStatoPagamento() {
	}

	public Integer getIdStato() {
		return this.idStato;
	}

	public void setIdStato(Integer idStato) {
		this.idStato = idStato;
	}

	public String getDescStato() {
		return this.descStato;
	}

	public void setDescStato(String descStato) {
		this.descStato = descStato;
	}

	public List<EpayTPagamento> getEpayTPagamentos() {
		return this.epayTPagamentos;
	}

	public void setEpayTPagamentos(List<EpayTPagamento> epayTPagamentos) {
		this.epayTPagamentos = epayTPagamentos;
	}

	public EpayTPagamento addEpayTPagamento(EpayTPagamento epayTPagamento) {
		getEpayTPagamentos().add(epayTPagamento);
		epayTPagamento.setEpayDStatoPagamento(this);

		return epayTPagamento;
	}

	public EpayTPagamento removeEpayTPagamento(EpayTPagamento epayTPagamento) {
		getEpayTPagamentos().remove(epayTPagamento);
		epayTPagamento.setEpayDStatoPagamento(null);

		return epayTPagamento;
	}

	public List<EpayTRegistroVersamenti> getEpayTRegistroVersamentis() {
		return this.epayTRegistroVersamentis;
	}

	public void setEpayTRegistroVersamentis(List<EpayTRegistroVersamenti> epayTRegistroVersamentis) {
		this.epayTRegistroVersamentis = epayTRegistroVersamentis;
	}

	public EpayTRegistroVersamenti addEpayTRegistroVersamenti(EpayTRegistroVersamenti epayTRegistroVersamenti) {
		getEpayTRegistroVersamentis().add(epayTRegistroVersamenti);
		epayTRegistroVersamenti.setEpayDStatoPagamento(this);

		return epayTRegistroVersamenti;
	}

	public EpayTRegistroVersamenti removeEpayTRegistroVersamenti(EpayTRegistroVersamenti epayTRegistroVersamenti) {
		getEpayTRegistroVersamentis().remove(epayTRegistroVersamenti);
		epayTRegistroVersamenti.setEpayDStatoPagamento(null);

		return epayTRegistroVersamenti;
	}
	
	public Boolean getPagabile() {
		return pagabile;
	}

	public void setPagabile(Boolean pagabile) {
		this.pagabile = pagabile;
	}
		
	public Boolean getModificabile() {
		return modificabile;
	}

	public void setModificabile(Boolean modificabile) {
		this.modificabile = modificabile;
	}
}

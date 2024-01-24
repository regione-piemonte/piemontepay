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
 * The persistent class for the epay_d_modalita_pagamento database table.
 * 
 */
@Entity
@Table(name="epay_d_modalita_pagamento")
public class EpayDModalitaPagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_modalita_pagamento", unique=true, nullable=false)
	private Integer idModalitaPagamento;

	@Column(nullable=false, length=200)
	private String descrizione;

	//bi-directional many-to-one association to EpayTEsitiRicevuti
	@OneToMany(mappedBy="epayDModalitaPagamento")
	private List<EpayTEsitiRicevuti> epayTEsitiRicevutis;

	public EpayDModalitaPagamento() {
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

	public List<EpayTEsitiRicevuti> getEpayTEsitiRicevutis() {
		return this.epayTEsitiRicevutis;
	}

	public void setEpayTEsitiRicevutis(List<EpayTEsitiRicevuti> epayTEsitiRicevutis) {
		this.epayTEsitiRicevutis = epayTEsitiRicevutis;
	}

	public EpayTEsitiRicevuti addEpayTEsitiRicevuti(EpayTEsitiRicevuti epayTEsitiRicevuti) {
		getEpayTEsitiRicevutis().add(epayTEsitiRicevuti);
		epayTEsitiRicevuti.setEpayDModalitaPagamento(this);

		return epayTEsitiRicevuti;
	}

	public EpayTEsitiRicevuti removeEpayTEsitiRicevuti(EpayTEsitiRicevuti epayTEsitiRicevuti) {
		getEpayTEsitiRicevutis().remove(epayTEsitiRicevuti);
		epayTEsitiRicevuti.setEpayDModalitaPagamento(null);

		return epayTEsitiRicevuti;
	}

}

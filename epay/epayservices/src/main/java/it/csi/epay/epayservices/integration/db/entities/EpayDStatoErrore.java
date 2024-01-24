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
 * The persistent class for the epay_d_stato_errore database table.
 * 
 */
@Entity
@Table(name="epay_d_stato_errore")
public class EpayDStatoErrore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_stato", unique=true, nullable=false)
	private Integer idStato;

	@Column(length=200)
	private String descrizione;

	//bi-directional many-to-one association to EpayTErrori
	@OneToMany(mappedBy="epayDStatoErrore")
	private List<EpayTErrori> epayTErroris;

	public EpayDStatoErrore() {
	}

	public Integer getIdStato() {
		return this.idStato;
	}

	public void setIdStato(Integer idStato) {
		this.idStato = idStato;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<EpayTErrori> getEpayTErroris() {
		return this.epayTErroris;
	}

	public void setEpayTErroris(List<EpayTErrori> epayTErroris) {
		this.epayTErroris = epayTErroris;
	}

	public EpayTErrori addEpayTErrori(EpayTErrori epayTErrori) {
		getEpayTErroris().add(epayTErrori);
		epayTErrori.setEpayDStatoErrore(this);

		return epayTErrori;
	}

	public EpayTErrori removeEpayTErrori(EpayTErrori epayTErrori) {
		getEpayTErroris().remove(epayTErrori);
		epayTErrori.setEpayDStatoErrore(null);

		return epayTErrori;
	}

}

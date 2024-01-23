/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table ( name = "epay_d_modalita_pagamento" )
@SuppressWarnings ( "unused" )
public class EpayDModalitaPagamento implements Serializable {

	private static final long serialVersionUID = 4338648259363070552L;

	@Id
	@Column ( name = "id_modalita_pagamento", unique = true, nullable = false )
	private Integer idModalitaPagamento;

	@Column ( nullable = false, length = 200 )
	private String descrizione;

	@OneToMany ( mappedBy = "epayDModalitaPagamento" )
	private List<EpayTEsitiRicevuti> epayTEsitiRicevutis;

	public EpayDModalitaPagamento () {
	}

	public Integer getIdModalitaPagamento () {
		return idModalitaPagamento;
	}

	public void setIdModalitaPagamento ( Integer idModalitaPagamento ) {
		this.idModalitaPagamento = idModalitaPagamento;
	}

	public String getDescrizione () {
		return descrizione;
	}

	public void setDescrizione ( String descrizione ) {
		this.descrizione = descrizione;
	}

	public List<EpayTEsitiRicevuti> getEpayTEsitiRicevutis () {
		return epayTEsitiRicevutis;
	}

	public void setEpayTEsitiRicevutis ( List<EpayTEsitiRicevuti> epayTEsitiRicevutis ) {
		this.epayTEsitiRicevutis = epayTEsitiRicevutis;
	}

	@Override
	public String toString () {
		return "{ " +
			"idModalitaPagamento:" + idModalitaPagamento +
			", descrizione:" + descrizione +
			", epayTEsitiRicevutis (valore non esposto)" + // ", epayTEsitiRicevutis:" + epayTEsitiRicevutis +
			" }";
	}
}

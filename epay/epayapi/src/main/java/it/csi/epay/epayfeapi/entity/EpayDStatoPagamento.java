/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;


@Entity
@Table ( name = "epay_d_stato_pagamento" )
@SuppressWarnings ( "unused" )
public class EpayDStatoPagamento implements Serializable {

	private static final long serialVersionUID = -7256017117766407613L;

	@Id
	@Column ( name = "id_stato", unique = true, nullable = false )
	private Integer idStato;

	@Column ( name = "desc_stato", nullable = false, length = 250 )
	private String descStato;

	@Column ( nullable = false )
	private Boolean pagabile;

	@Column ( nullable = false )
	private Boolean modificabile;

	@OneToMany ( mappedBy = "epayDStatoPagamento" )
	private List<EpayTPagamento> epayTPagamentos;

	@OneToMany ( mappedBy = "epayDStatoPagamento" )
	private List<EpayTRegistroVersamenti> epayTRegistroVersamentis;

	public EpayDStatoPagamento () {
	}

	public Integer getIdStato () {
		return idStato;
	}

	public void setIdStato ( Integer idStato ) {
		this.idStato = idStato;
	}

	public String getDescStato () {
		return descStato;
	}

	public void setDescStato ( String descStato ) {
		this.descStato = descStato;
	}

	public Boolean getPagabile () {
		return pagabile;
	}

	public void setPagabile ( Boolean pagabile ) {
		this.pagabile = pagabile;
	}

	public Boolean getModificabile () {
		return modificabile;
	}

	public void setModificabile ( Boolean modificabile ) {
		this.modificabile = modificabile;
	}

	public List<EpayTPagamento> getEpayTPagamentos () {
		return epayTPagamentos;
	}

	public void setEpayTPagamentos ( List<EpayTPagamento> epayTPagamentos ) {
		this.epayTPagamentos = epayTPagamentos;
	}

	public List<EpayTRegistroVersamenti> getEpayTRegistroVersamentis () {
		return epayTRegistroVersamentis;
	}

	public void setEpayTRegistroVersamentis ( List<EpayTRegistroVersamenti> epayTRegistroVersamentis ) {
		this.epayTRegistroVersamentis = epayTRegistroVersamentis;
	}

	@Override
	public String toString () {
		return "{ " +
			"idStato:" + idStato + 
			", descrStato:" + descStato + 
			", pagabile:" + pagabile + 
			", modificabile:" + modificabile +
			// non esporre epayTPagamentos
			// non esporre epayTRegistroVersamentis
			" }";
	}
}

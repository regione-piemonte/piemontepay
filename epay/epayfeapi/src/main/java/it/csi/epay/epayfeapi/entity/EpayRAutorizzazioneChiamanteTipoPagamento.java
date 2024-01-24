/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table ( name = "epay_r_chiamante_tipo_pagamento" )
@SuppressWarnings ( "unused" )
public class EpayRAutorizzazioneChiamanteTipoPagamento implements Serializable {

	private static final long serialVersionUID = 1767595276422992574L;

	@EmbeddedId
	private EpayRAutorizzazioneChiamanteTipoPagamentoPK id;

	@Column ( name = "data_fine_validita" )
	private Date dataFineValidita;

	public EpayRAutorizzazioneChiamanteTipoPagamento () {
		id = new EpayRAutorizzazioneChiamanteTipoPagamentoPK ();
	}

	public EpayRAutorizzazioneChiamanteTipoPagamentoPK getId () {
		return id;
	}

	public void setId ( EpayRAutorizzazioneChiamanteTipoPagamentoPK id ) {
		this.id = id;
	}

	public Date getDataFineValidita () {
		return dataFineValidita;
	}

	public void setDataFineValidita ( Date dataFineValidita ) {
		this.dataFineValidita = dataFineValidita;
	}

	public String getCodiceChiamante () {
		return id != null ? id.getCodiceChiamante () : null;
	}

	public void setCodiceChiamante ( String codiceChiamante ) {
		id.setCodiceChiamante ( codiceChiamante );
	}

	public Long getIdTipoPagamento () {
		return id != null ? id.getIdTipoPagamento () : null;
	}

	public void setIdTipoPagamento ( Long idTipoPagamento ) {
		id.setIdTipoPagamento ( idTipoPagamento );
	}

	public Date getDataInizioValidita () {
		return id != null ? id.getDataInizioValidita () : null;
	}

	public void setDataInizioValidita ( Date dataInizioValidita ) {
		id.setDataInizioValidita ( dataInizioValidita );
	}

	@Override
	public String toString () {
		return "{ " +
			"id:" + id +
			", dataFineValidita:" + dataFineValidita +
			" }";
	}
}

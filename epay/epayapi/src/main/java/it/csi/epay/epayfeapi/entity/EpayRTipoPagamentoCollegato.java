/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table ( name = "epay_r_tipo_pagamento_collegato", schema = "epay" )
@SuppressWarnings ( "unused" )
public class EpayRTipoPagamentoCollegato implements Serializable {

	private static final long serialVersionUID = 3972067648501204419L;

	@EmbeddedId
	private EpayRTipoPagamentoCollegatoKey id;

	public EpayRTipoPagamentoCollegato () {
		id = new EpayRTipoPagamentoCollegatoKey ();
	}

	public EpayRTipoPagamentoCollegatoKey getId () {
		return id;
	}

	public void setId ( EpayRTipoPagamentoCollegatoKey id ) {
		this.id = id;
	}

	public Long getIdTipoPagamentoPrincipale () {
		return id.getIdTipoPagamentoPrincipale ();
	}

	public void setIdTipoPagamentoPrincipale ( Long idTipoPagamentoPrincipale ) {
		this.id.setIdTipoPagamentoPrincipale ( idTipoPagamentoPrincipale );
	}

	public Long getIdTipoPagamentoSecondario () {
		return id.getIdTipoPagamentoSecondario ();
	}

	public void setIdTipoPagamentoSecondario ( Long idTipoPagamentoSecondario ) {
		this.id.setIdTipoPagamentoSecondario ( idTipoPagamentoSecondario );
	}

	@Override
	public String toString () {
		return "{ id:" + id + " }";
	}
}

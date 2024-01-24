/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model.v1;

import it.csi.epay.epayservices.model.Pagamento;

import java.io.Serializable;


public class PagamentoPerStampaAvvisoOutput implements Serializable {

	private static final long serialVersionUID = -4928245193034155129L;

	private boolean error;
	private String message;
	private Pagamento pagamento;

	public boolean isError () {
		return error;
	}

	public void setError ( boolean error ) {
		this.error = error;
	}

	public String getMessage () {
		return message;
	}

	public void setMessage ( String message ) {
		this.message = message;
	}

	public Pagamento getPagamento () {
		return pagamento;
	}

	public void setPagamento ( Pagamento pagamento ) {
		this.pagamento = pagamento;
	}

	@Override public String toString () {
		return "PagamentoPerStampaAvvisoOutput{" +
						"error=" + error +
						", message='" + message + '\'' +
						", pagamento=" + pagamento +
						'}';
	}
}

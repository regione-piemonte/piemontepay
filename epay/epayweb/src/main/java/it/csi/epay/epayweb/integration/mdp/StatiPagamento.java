/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.integration.mdp;

public enum StatiPagamento {
	NOTINITIALIZED(0),
	INITIALIZED(1),
	CONFIGURED(2),
	STARTED(3),
	SUCCESSFULL(4),
	UNSUCCESFULL(5),
	CANCELED(6),
	REFUNDED(7),
	TOBECONFIRMED(8);
	
	private int state;
	
	private StatiPagamento(int state) {
		this.state = state;
	}
	
	public int getState() {
		return state;
	}
	
	public StatiPagamento find(int state) throws IllegalArgumentException  {
		for (StatiPagamento statoPagamento : StatiPagamento.values()) {
			if (statoPagamento.state == state) {
				return statoPagamento;
			}
		}
		throw new IllegalArgumentException("Lo stato di pagamento cercato non esiste.");
	}
}
